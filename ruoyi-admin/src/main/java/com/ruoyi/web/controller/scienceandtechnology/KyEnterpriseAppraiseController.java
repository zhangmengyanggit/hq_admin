package com.ruoyi.web.controller.scienceandtechnology;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.web.domain.KyEnterpriseAppraise;
import com.ruoyi.web.service.IKyEnterpriseAppraiseService;
import com.ruoyi.web.service.IKyEnterpriseProjectDeclarationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业用户评价Controller
 * 
 * @author ruoyi
 * @date 2023-03-29
 */
@RestController
@RequestMapping("/scienceandtechnology/appraise")
public class KyEnterpriseAppraiseController extends BaseController
{
    @Autowired
    private IKyEnterpriseAppraiseService kyEnterpriseAppraiseService;
    @Autowired
    private IKyEnterpriseProjectDeclarationService kyEnterpriseProjectDeclarationService;

    @Autowired
    private ISysDictDataService dictDataService;
    /**
     * 查询企业用户评价列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:appraise:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyEnterpriseAppraise kyEnterpriseAppraise)
    {
        startPage();
        List<KyEnterpriseAppraise> list = kyEnterpriseAppraiseService.selectKyEnterpriseAppraiseList(kyEnterpriseAppraise);
        return getDataTable(list);
    }

    /**
     * 导出企业用户评价列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:appraise:export')")
    @Log(title = "企业用户评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyEnterpriseAppraise kyEnterpriseAppraise)
    {
        List<KyEnterpriseAppraise> list = kyEnterpriseAppraiseService.selectKyEnterpriseAppraiseList(kyEnterpriseAppraise);
        ExcelUtil<KyEnterpriseAppraise> util = new ExcelUtil<KyEnterpriseAppraise>(KyEnterpriseAppraise.class);
        util.exportExcel(response, list, "企业用户评价数据");
    }

    /**
     * 获取企业用户评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:appraise:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(kyEnterpriseAppraiseService.selectKyEnterpriseAppraiseById(id));
    }


    /**
     * 回复企业用户评价
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:appraise:edit')")
    @Log(title = "企业用户评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyEnterpriseAppraise kyEnterpriseAppraise)
    {
        kyEnterpriseAppraise.setReplyTime(new Date());
        kyEnterpriseAppraise.setReplyStatus(1);
        return toAjax(kyEnterpriseAppraiseService.updateKyEnterpriseAppraise(kyEnterpriseAppraise));
    }

    @GetMapping("/getStatistic")
    public AjaxResult getStatistic()
    {
        AjaxResult result=new AjaxResult();
        /**
        * 应评价数
        * */
        Map<String,Object> paramsMap=new HashMap<>();
         //超级管理员看全部，各部门看自己创建的政策
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            paramsMap.put("department", SecurityUtils.getDeptId());
        }
        //paramsMap.put("status",0);
        Long  count= kyEnterpriseAppraiseService.selectKyEnterpriseAppraiseCountByParasm(paramsMap);
        result.put("needValue",count);
        /**
         * 已评价数
         * */
        Map<String,Object> paramsMapTwo=new HashMap<>();
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            paramsMapTwo.put("replyDepartment", SecurityUtils.getDeptId());
        }
        paramsMapTwo.put("status",1);
        //paramsMapTwo.put("replyStatus",0);
        Long hasAppraiseCount=kyEnterpriseAppraiseService.selectKyEnterpriseAppraiseCountByParasm(paramsMapTwo);
        result.put("hasValue",hasAppraiseCount);
        /**
         * 未评价数
         * */
        Map<String,Object> paramsMapThree=new HashMap<>();
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            paramsMapThree.put("replyDepartment", SecurityUtils.getDeptId());
        }
        paramsMapThree.put("status",0);
        paramsMapThree.put("replyStatus",0);
        Long notAppraiseCount=kyEnterpriseAppraiseService.selectKyEnterpriseAppraiseCountByParasm(paramsMapThree);
        result.put("notValue",notAppraiseCount);
        /**
         * 未回复数
         * */
        Map<String,Object> paramsMapnotAply=new HashMap<>();
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            paramsMapnotAply.put("replyDepartment", SecurityUtils.getDeptId());
        }
        paramsMapnotAply.put("status",1);
        paramsMapnotAply.put("replyStatus",0);
        Long notAplyCount=kyEnterpriseAppraiseService.selectKyEnterpriseAppraiseCountByParasm(paramsMapnotAply);
        result.put("notAplyValue",notAplyCount);
        /**
         * 已回复数
         * */
        Map<String,Object> paramsMapFourAply=new HashMap<>();
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            paramsMapFourAply.put("replyDepartment", SecurityUtils.getDeptId());
        }
        paramsMapFourAply.put("status",1);
        paramsMapFourAply.put("replyStatus",1);
        Long hasAplyCount=kyEnterpriseAppraiseService.selectKyEnterpriseAppraiseCountByParasm(paramsMapFourAply);
        result.put("hasAplyValue",hasAplyCount);
        /**
         * 五星评价数
         * */
        Map<String,Object> paramsMapFive=new HashMap<>();
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            paramsMapFive.put("replyDepartment", SecurityUtils.getDeptId());
        }
        paramsMapFive.put("starRating",5);
        Long fiveStarCount=kyEnterpriseAppraiseService.selectKyEnterpriseAppraiseCountByParasm(paramsMapFive);
        result.put("fiveStarValue",fiveStarCount);
        /**
         * 四星评价数
         * */
        Map<String,Object> paramsMapFour=new HashMap<>();
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            paramsMapFour.put("replyDepartment", SecurityUtils.getDeptId());
        }
        paramsMapFour.put("starRating",4);
        Long fourStarCount=kyEnterpriseAppraiseService.selectKyEnterpriseAppraiseCountByParasm(paramsMapFour);
        result.put("fourStarValue",fourStarCount);
        /**
         * 二星评价数
         * */
        Map<String,Object> twoParamsMap=new HashMap<>();
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            twoParamsMap.put("replyDepartment", SecurityUtils.getDeptId());
        }
        twoParamsMap.put("starRating",2);
        Long twoStarCount=kyEnterpriseAppraiseService.selectKyEnterpriseAppraiseCountByParasm(twoParamsMap);
        result.put("twoStarValue",twoStarCount);
        /**
         * 三星评价数
         * */
        Map<String,Object> threeparamsMap=new HashMap<>();
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            threeparamsMap.put("replyDepartment", SecurityUtils.getDeptId());
        }
        threeparamsMap.put("starRating",3);
        Long threeStarCount=kyEnterpriseAppraiseService.selectKyEnterpriseAppraiseCountByParasm(threeparamsMap);
        result.put("threeStarValue",threeStarCount);
        /**
         * 一星评价数
         * */
        Map<String,Object> paramsMapOne=new HashMap<>();
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            paramsMapOne.put("replyDepartment", SecurityUtils.getDeptId());
        }
        paramsMapOne.put("starRating",1);
        Long oneStarCount=kyEnterpriseAppraiseService.selectKyEnterpriseAppraiseCountByParasm(paramsMapOne);
        result.put("oneStarValue",oneStarCount);
        return result;
    }
}
