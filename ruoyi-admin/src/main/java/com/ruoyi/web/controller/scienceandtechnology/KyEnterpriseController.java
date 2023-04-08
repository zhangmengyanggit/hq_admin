package com.ruoyi.web.controller.scienceandtechnology;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.ruoyi.web.domain.KyEnterprise;
import com.ruoyi.web.domain.SysUserEnterprise;
import com.ruoyi.web.service.IKyEnterpriseProjectDeclarationService;
import com.ruoyi.web.service.IKyEnterpriseService;
import com.ruoyi.web.service.ISysUserEnterpriseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 企业Controller
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
@RestController
@RequestMapping("scienceandtechnology/enterprise")
public class KyEnterpriseController extends BaseController
{
    @Autowired
    private IKyEnterpriseService kyEnterpriseService;
    @Autowired
    private  ISysUserEnterpriseService iSysUserEnterpriseService;
    @Autowired
    private IKyEnterpriseProjectDeclarationService kyEnterpriseProjectDeclarationService;
    /**
     * 查询企业列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyEnterprise kyEnterprise)
    {
        startPage();
        List<KyEnterprise> list = kyEnterpriseService.selectKyEnterpriseList(kyEnterprise);
        return getDataTable(list);
    }

    /**
     * 导出企业列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:export')")
    @Log(title = "企业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyEnterprise kyEnterprise)
    {
        List<KyEnterprise> list = kyEnterpriseService.selectKyEnterpriseList(kyEnterprise);
        ExcelUtil<KyEnterprise> util = new ExcelUtil<KyEnterprise>(KyEnterprise.class);
        util.exportExcel(response, list, "企业数据");
    }

    /**
     * 获取企业详细信息
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(kyEnterpriseService.selectKyEnterpriseById(id));
    }

    /**
     * 新增企业
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:add')")
    @Log(title = "企业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyEnterprise kyEnterprise)
    {
        //判断企业名称是否已存在
        KyEnterprise kyEnterpriseCheck=new KyEnterprise();
        kyEnterpriseCheck.setName(kyEnterprise.getName());
        Long count=  kyEnterpriseService.selectKyEnterpriseCount(kyEnterpriseCheck);
        if(count>0){
            return  AjaxResult.error("企业名称已存在");
        }
        //判断社会统一信用代码号唯一性
        kyEnterpriseCheck.setName(null);
        kyEnterpriseCheck.setSocialUnifiedCreditCodeNumber(kyEnterprise.getSocialUnifiedCreditCodeNumber());
        Long countSocialUnifiedCreditCodeNumber=  kyEnterpriseService.selectKyEnterpriseCount(kyEnterpriseCheck);
        if(countSocialUnifiedCreditCodeNumber>0){
            return  AjaxResult.error("统一社会信用代码已存在");
        }
        return toAjax(kyEnterpriseService.insertKyEnterprise(kyEnterprise));
    }

    /**
     * 修改企业
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:edit')")
    @Log(title = "企业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyEnterprise kyEnterprise)
    {
        //根据企业id查询企业信息
        KyEnterprise enterpriseOrld=    kyEnterpriseService.selectKyEnterpriseById(kyEnterprise.getId());
        KyEnterprise kyEnterpriseCheck=null;
        if(enterpriseOrld!=null&&!kyEnterprise.getName().equals(enterpriseOrld.getName())){
            //判断企业名称是否已存在
            kyEnterpriseCheck =new KyEnterprise();
            kyEnterpriseCheck.setName(kyEnterprise.getName());
            Long count=  kyEnterpriseService.selectKyEnterpriseCount(kyEnterpriseCheck);
            if(count>0){
                return  AjaxResult.error("企业名称已存在");
            }
        }

        if(enterpriseOrld!=null&&!kyEnterprise.getSocialUnifiedCreditCodeNumber().equals(enterpriseOrld.getSocialUnifiedCreditCodeNumber())){
            //判断社会统一信用代码号唯一性
            kyEnterpriseCheck =new KyEnterprise();
            kyEnterpriseCheck.setSocialUnifiedCreditCodeNumber(kyEnterprise.getSocialUnifiedCreditCodeNumber());
            Long countSocialUnifiedCreditCodeNumber=  kyEnterpriseService.selectKyEnterpriseCount(kyEnterpriseCheck);
            if(countSocialUnifiedCreditCodeNumber>0){
                return  AjaxResult.error("统一社会信用代码已存在");
            }
        }


            //企业联系人电话更改则更新前端用户登录名称
            iSysUserEnterpriseService.updateSysUserEnterpriseByEnterprise(enterpriseOrld,kyEnterprise);

        return toAjax(kyEnterpriseService.updateKyEnterprise(kyEnterprise));
    }

    /**
     * 删除企业
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:remove')")
    @Log(title = "企业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        //判断当前企业是否已经申请企业申请项目
        Map<String, Object> paramsMap=new HashMap<>();
        paramsMap.put("enterpriseIds",ids);
        Long count=   kyEnterpriseProjectDeclarationService.selectCountByParams(paramsMap);
        if(count>0){
            return error("已经申请政策的企业无法删除");
        }
        return toAjax(kyEnterpriseService.deleteKyEnterpriseByIds(ids));
    }


    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<KyEnterprise> util = new ExcelUtil<KyEnterprise>(KyEnterprise.class);
        util.importTemplateExcel(response, "企业数据");
    }

    @Log(title = "企业管理导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<KyEnterprise> util = new ExcelUtil<KyEnterprise>(KyEnterprise.class);
        List<KyEnterprise> enterpriseList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = kyEnterpriseService.importEnterprise(enterpriseList, updateSupport, operName);
        return success(message);
    }
}
