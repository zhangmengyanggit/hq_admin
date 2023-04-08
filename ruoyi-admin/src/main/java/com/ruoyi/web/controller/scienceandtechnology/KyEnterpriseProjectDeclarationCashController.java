package com.ruoyi.web.controller.scienceandtechnology;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.domain.KyEnterpriseProjectDeclaration;
import com.ruoyi.web.service.IKyEnterpriseAppraiseService;
import com.ruoyi.web.service.IKyEnterpriseProjectDeclarationService;
import com.ruoyi.web.vo.KyEnterpriseProjectDeclarationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 企业申请项目(兑付)Controller
 * 
 * @author ruoyi
 * @date 2023-03-16
 */
@RestController
@RequestMapping("/scienceandtechnology/cash")
public class KyEnterpriseProjectDeclarationCashController extends BaseController
{
    @Autowired
    private IKyEnterpriseProjectDeclarationService kyEnterpriseProjectDeclarationService;
    @Autowired
    private IKyEnterpriseAppraiseService enterpriseAppraiseService;
    /**
     * 查询企业申请项目列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:cash:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyEnterpriseProjectDeclarationVo kyEnterpriseProjectDeclarationVo)
    {
        startPage();
        kyEnterpriseProjectDeclarationVo.setAuditStatusStr("2,3");
        List<KyEnterpriseProjectDeclarationVo> list = kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationList(kyEnterpriseProjectDeclarationVo);
        return getDataTable(list);
    }


    /**
     * 获取企业申请项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:cash:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationById(id));
    }




    /**
     * 财务局兑付企业申请项目
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:cash:cash')")
    @Log(title = "企业申请项目部门兑付", businessType = BusinessType.UPDATE)
	@PostMapping("/batchCash/{ids}")
    public AjaxResult batchAudit(@PathVariable Long[] ids)
    {

        enterpriseAppraiseService.insertEnterpriseAppraise(ids,3l);
        return toAjax(kyEnterpriseProjectDeclarationService.auditKyEnterpriseProjectDeclarationByIds(ids,3));
    }

    /**
     * 导出企业申请项目列表(已兑现)
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:cash:export')")
    @Log(title = "企业申请项目导出已兑现", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyEnterpriseProjectDeclarationVo kyEnterpriseProjectDeclaration)
    {
        kyEnterpriseProjectDeclaration.setAuditStatus(3l);
        List<KyEnterpriseProjectDeclarationVo> list = kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationList(kyEnterpriseProjectDeclaration);
        //数据处理
        for (KyEnterpriseProjectDeclarationVo e: list) {
            e.setAuditStatusStr( DictUtils.getDictLabel("audit_status",e.getAuditStatus().toString()));
            e.setPolicyLevel(DictUtils.getDictLabel("rank",e.getPolicyLevel().toString()));
        }

        if(list.size()>0){
            ExcelUtil<KyEnterpriseProjectDeclarationVo> util = new ExcelUtil<KyEnterpriseProjectDeclarationVo>(KyEnterpriseProjectDeclarationVo.class);
            util.exportExcel(response, list, "企业已兑现项目数据");
        }

    }
}
