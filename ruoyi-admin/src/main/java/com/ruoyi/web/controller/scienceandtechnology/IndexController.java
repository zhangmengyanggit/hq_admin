package com.ruoyi.web.controller.scienceandtechnology;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.web.domain.KyEnterprise;
import com.ruoyi.web.domain.KyOriginalPolicy;
import com.ruoyi.web.domain.Tag;
import com.ruoyi.web.service.IKyEnterpriseProjectDeclarationService;
import com.ruoyi.web.service.IKyEnterpriseService;
import com.ruoyi.web.service.IKyOriginalPolicyService;
import com.ruoyi.web.service.ITagService;
import com.ruoyi.web.vo.KyEnterpriseProjectDeclarationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 【首页】Controller
 * 
 * @author zmy
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/scienceandtechnology/index")
public class IndexController extends BaseController
{
    @Autowired
    private IKyEnterpriseService kyEnterpriseService;
    @Autowired
    private IKyOriginalPolicyService iKyOriginalPolicyService;
    @Autowired
    private IKyEnterpriseProjectDeclarationService kyEnterpriseProjectDeclarationService;
    @Autowired
    private IKyOriginalPolicyService kyOriginalPolicyService;

    /**
     * 查询企业数和政策数
     */
    @GetMapping("/getOriginalPolicyCountAndEnterpriseCount")
    public AjaxResult getOriginalPolicyCountAndEnterpriseCount()
    {

        AjaxResult AjaxResult=new AjaxResult();
        //查询企业数
        KyEnterprise  kyEnterprise=new KyEnterprise();
        kyEnterprise.setBusinessTerm(new Date());
        kyEnterprise.setRegistrationRegion(19973);
        Long  businessNum= kyEnterpriseService.selectKyEnterpriseCount(kyEnterprise);
        AjaxResult.put("businessNum",businessNum);

        KyOriginalPolicy originalPolicy=new KyOriginalPolicy();
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            originalPolicy.setPublishingDepartment(SecurityUtils.getDeptId()+"");
        }
        originalPolicy.setValidStatus(0l);
        Long originalpolicyNum=iKyOriginalPolicyService.selectKyOriginalPolicyCount(originalPolicy);
        AjaxResult.put("originalpolicyNum",originalpolicyNum);
        return AjaxResult;
    }

    /**
     * 查询最近5条审核数据和政策数据
     */
    @GetMapping("/getEnterpriseProjectDeclarationAndOriginalPolicyFive")
    public AjaxResult getEnterpriseProjectDeclarationAndOriginalPolicyFive()
    {

        AjaxResult AjaxResult=new AjaxResult();
        //查询最近5条审核数据
        KyEnterpriseProjectDeclarationVo kyEnterpriseProjectDeclarationVo=new KyEnterpriseProjectDeclarationVo();
        //超级管理员看全部，各部门看自己创建的政策
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            kyEnterpriseProjectDeclarationVo.setPublishingDepartment(SecurityUtils.getDeptId());
        }
        kyEnterpriseProjectDeclarationVo.setLimit(5l);
        List<KyEnterpriseProjectDeclarationVo> list = kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationList(kyEnterpriseProjectDeclarationVo);
        List<String> recentAuditList=new LinkedList<>();
        for (KyEnterpriseProjectDeclarationVo kyEnterpriseProjectDeclarationVo1: list) {
            String status= DictUtils.getDictLabel("audit_status",kyEnterpriseProjectDeclarationVo1.getAuditStatus().toString());
            recentAuditList.add("企业名称:"+kyEnterpriseProjectDeclarationVo1.getName()+"  政策标题:"+kyEnterpriseProjectDeclarationVo1.getTittle()+"  审核状态:"+status);
        }
        AjaxResult.put("recentAuditList",recentAuditList);

        KyOriginalPolicy originalPolicy=new KyOriginalPolicy();
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            originalPolicy.setPublishingDepartment(SecurityUtils.getDeptId()+"");
        }
        originalPolicy.setLimit(5l);
        List<KyOriginalPolicy> originalPolicies = kyOriginalPolicyService.selectKyOriginalPolicyList(originalPolicy);

        List<String> recentOriginalPolicyList=new LinkedList<>();
        for (KyOriginalPolicy originalPolicy1:originalPolicies) {
            String status= DictUtils.getDictLabel("publish_status",originalPolicy1.getPublishStatus().toString());
            recentOriginalPolicyList.add("政策标题:"+originalPolicy1.getTittle()+"  有效截止时间:"+originalPolicy1.getValidDate()+"  发布状态:"+status);
        }
        AjaxResult.put("recentOriginalPolicyList",recentOriginalPolicyList);
        return AjaxResult;
    }

    /**
     * 查询各政策数，各状态企业数，当前月审核数
     */
    @GetMapping("/getAllData")
    public AjaxResult getAllData()
    {

        AjaxResult AjaxResult=new AjaxResult();

        List<Map<String,Object>> list=new LinkedList<>();
        Map<String,Object> map1=new HashMap<>();
        //获取未申请企业政策数量
        Map<String, Object> paramsMap=new HashMap<>();
        paramsMap.put("auditStatus",0);
        //超级管理员看全部，各部门看自己创建的政策
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            paramsMap.put("department",SecurityUtils.getDeptId());
        }
        Long count1=kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationCountByParams(paramsMap);
        map1.put("value",count1);
        map1.put("name","未申请");
        list.add(map1);
        Map<String,Object> map2=new HashMap<>();
        //获取未审核企业政策数量
        paramsMap.put("auditStatus",1);
        Long count2=kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationCountByParams(paramsMap);
        map2.put("value",count2);
        map2.put("name","未审核");
        list.add(map2);
        Map<String,Object> map3=new HashMap<>();
        //获取未审核企业政策数量
        paramsMap.put("auditStatus",2);
        Long count3=kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationCountByParams(paramsMap);
        map3.put("value",count3);
        map3.put("name","未兑现");
        list.add(map3);
        AjaxResult.put("businessList",list);


        List<Map<String,Object>> list2=new LinkedList<>();
        //获取已解读政策数
        KyOriginalPolicy originalPolicy=new KyOriginalPolicy();
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            originalPolicy.setPublishingDepartment(SecurityUtils.getDeptId()+"");
        }
        originalPolicy.setValidStatus(0l);
        originalPolicy.setPublishStatus(1l);
        count1=kyOriginalPolicyService.selectKyOriginalPolicyCount(originalPolicy);
        Map<String,Object> map1OriginalPolicy=new HashMap<>();
        map1OriginalPolicy.put("value",count1);
        map1OriginalPolicy.put("name","已解读政策");
        list2.add(map1OriginalPolicy);
        //获取未解读政策数
        originalPolicy.setPublishStatus(1l);
         count2=kyOriginalPolicyService.selectKyOriginalPolicyCount(originalPolicy);
        Map<String,Object> mapOriginalPolicy2=new HashMap<>();
        mapOriginalPolicy2.put("value",count2);
        mapOriginalPolicy2.put("name","未解读政策");
        list2.add(mapOriginalPolicy2);


        //获取已发布政策数
        originalPolicy.setPublishStatus(2l);
        count3=kyOriginalPolicyService.selectKyOriginalPolicyCount(originalPolicy);
        Map<String,Object> mapOriginalPolicy=new HashMap<>();
        mapOriginalPolicy.put("value",count3);
        mapOriginalPolicy.put("name","已发布政策");
        list2.add(mapOriginalPolicy);
        AjaxResult.put("originalpolicyList",list2);


        List<Map<String,Object>> list3=new LinkedList<>();
        //当月已审核数据
        Map<String, Object> paramsMap2=new HashMap<>();
        paramsMap2.put("auditStatuss","2,3");
        paramsMap2.put("month",1);
        //超级管理员看全部，各部门看自己创建的政策
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            paramsMap2.put("department",SecurityUtils.getDeptId());
        }
        count1=kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationCountByParams(paramsMap2);
        Map<String, Object> EnterpriseProjectDeclarationMap=new HashMap<>();
        EnterpriseProjectDeclarationMap.put("value",count1);
        EnterpriseProjectDeclarationMap.put("name","当月已审核数据");
        list3.add(EnterpriseProjectDeclarationMap);
        //当月未审核数据
        paramsMap2.put("auditStatuss","0,1");
        count2=kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationCountByParams(paramsMap2);
        Map<String, Object> EnterpriseProjectDeclarationMap2=new HashMap<>();
        EnterpriseProjectDeclarationMap2.put("value",count2);
        EnterpriseProjectDeclarationMap2.put("name","当月未审核数据");
        list3.add(EnterpriseProjectDeclarationMap2);


        AjaxResult.put("monthAuditList",list3);
        return AjaxResult;
    }
}
