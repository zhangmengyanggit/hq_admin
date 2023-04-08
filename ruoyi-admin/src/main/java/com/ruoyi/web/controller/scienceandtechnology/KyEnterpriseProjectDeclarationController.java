package com.ruoyi.web.controller.scienceandtechnology;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.web.domain.KyEnterpriseAppraise;
import com.ruoyi.web.domain.KyEnterpriseProjectDeclaration;
import com.ruoyi.web.service.IKyEnterpriseAppraiseService;
import com.ruoyi.web.service.IKyEnterpriseProjectDeclarationService;
import com.ruoyi.web.vo.KyEnterpriseProjectDeclarationVo;
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

/**
 * 企业申请项目Controller
 *
 * @author ruoyi
 * @date 2023-03-16
 */
@RestController
@RequestMapping("/scienceandtechnology/enterpriseprojectdeclaration")
public class KyEnterpriseProjectDeclarationController extends BaseController {
    @Autowired
    private IKyEnterpriseProjectDeclarationService kyEnterpriseProjectDeclarationService;
    @Autowired
    private IKyEnterpriseAppraiseService enterpriseAppraiseService;

    /**
     * 查询企业申请项目列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterpriseprojectdeclaration:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyEnterpriseProjectDeclarationVo kyEnterpriseProjectDeclarationVo) {
        startPage();
        //超级管理员看全部，各部门看自己创建的政策
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId())) {
            kyEnterpriseProjectDeclarationVo.setPublishingDepartment(SecurityUtils.getDeptId());
        }
        List<KyEnterpriseProjectDeclarationVo> list = kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationList(kyEnterpriseProjectDeclarationVo);
        return getDataTable(list);
    }

    /**
     * 导出企业申请项目列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterpriseprojectdeclaration:export')")
    @Log(title = "企业申请项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyEnterpriseProjectDeclarationVo kyEnterpriseProjectDeclaration) {
        kyEnterpriseProjectDeclaration.setAuditStatus(2l);
        List<KyEnterpriseProjectDeclarationVo> list = kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationList(kyEnterpriseProjectDeclaration);
        //数据处理
        for (KyEnterpriseProjectDeclarationVo e : list) {
            e.setAuditStatusStr(DictUtils.getDictLabel("audit_status", e.getAuditStatus().toString()));
            e.setPolicyLevel(DictUtils.getDictLabel("rank", e.getPolicyLevel().toString()));
        }

        if (list.size() > 0) {
            ExcelUtil<KyEnterpriseProjectDeclarationVo> util = new ExcelUtil<KyEnterpriseProjectDeclarationVo>(KyEnterpriseProjectDeclarationVo.class);
            util.exportExcel(response, list, "企业已审核项目数据");
        }

    }

    /**
     * 获取企业申请项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterpriseprojectdeclaration:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationById(id));
    }

    /**
     * 新增企业申请项目
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterpriseprojectdeclaration:add')")
    @Log(title = "企业申请项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration) {
        return toAjax(kyEnterpriseProjectDeclarationService.insertKyEnterpriseProjectDeclaration(kyEnterpriseProjectDeclaration));
    }

    /**
     * 回复操作
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterpriseprojectdeclaration:edit')")
    @Log(title = "企业申请项目评价回复", businessType = BusinessType.UPDATE)
    @PostMapping("/answer")
    public AjaxResult answer(@RequestBody KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration) {
        kyEnterpriseProjectDeclaration.setAppraiseAnswerTime(new Date());
        return toAjax(kyEnterpriseProjectDeclarationService.updateKyEnterpriseProjectDeclaration(kyEnterpriseProjectDeclaration));
    }

    /**
     * 审核通过企业申请项目
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterpriseprojectdeclaration:audit')")
    @Log(title = "企业申请项目", businessType = BusinessType.UPDATE)
    @PostMapping("/batchAudit/{ids}")
    public AjaxResult batchAudit(@PathVariable Long[] ids) {

        enterpriseAppraiseService.insertEnterpriseAppraise(ids,2l);
        return toAjax(kyEnterpriseProjectDeclarationService.auditKyEnterpriseProjectDeclarationByIds(ids, 2));
    }

    /**
     * 受理企业申请项目
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterpriseprojectdeclaration:accept')")
    @Log(title = "企业申请项目", businessType = BusinessType.UPDATE)
    @PostMapping("/batchAccept/{ids}")
    public AjaxResult accept(@PathVariable Long[] ids) {
        enterpriseAppraiseService.insertEnterpriseAppraise(ids,4l);
        return toAjax(kyEnterpriseProjectDeclarationService.acceptKyEnterpriseProjectDeclarationByIds(ids, 4));
    }



    /**
     * 驳回企业申请项目
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterpriseprojectdeclaration:audit')")
    @Log(title = "企业申请项目驳回", businessType = BusinessType.UPDATE)
    @PostMapping("/reject")
    public AjaxResult reject(@RequestBody KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration) {
        kyEnterpriseProjectDeclaration.setAuditStatus(5l);

        /**
         * 插入评价数据（评价环节既审核状态）
         * */
        KyEnterpriseProjectDeclarationVo enterpriseProjectDeclarationVo = kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationById(kyEnterpriseProjectDeclaration.getId());
        if(enterpriseProjectDeclarationVo!=null){
            KyEnterpriseAppraise kyEnterpriseAppraise = new KyEnterpriseAppraise();
            kyEnterpriseAppraise.setEnterpriseId(enterpriseProjectDeclarationVo.getEnterpriseId());
            kyEnterpriseAppraise.setIrrigationDitch("pc端");
            //kyEnterpriseAppraise.setSysUserEnterpriseId(SecurityUtils.getUserId());
            kyEnterpriseAppraise.setEnterpriseProjectDeclarationId(enterpriseProjectDeclarationVo.getId());
            kyEnterpriseAppraise.setReplyDepartment(enterpriseProjectDeclarationVo.getPublishingDepartment());
            kyEnterpriseAppraise.setAuditStatus(5l);
            enterpriseAppraiseService.insertKyEnterpriseAppraise(kyEnterpriseAppraise);
        }
        return toAjax(kyEnterpriseProjectDeclarationService.updateKyEnterpriseProjectDeclaration(kyEnterpriseProjectDeclaration));
    }
}
