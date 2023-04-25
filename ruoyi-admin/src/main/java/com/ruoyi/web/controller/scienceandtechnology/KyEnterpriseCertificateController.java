package com.ruoyi.web.controller.scienceandtechnology;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.web.domain.KyEnterpriseCertificate;
import com.ruoyi.web.model.KyEnterpriseComprehensiveInformation;
import com.ruoyi.web.service.IKyEnterpriseCertificateService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;

/**
 * 企业证书详情Controller
 * 
 * @author ruoyi
 * @date 2023-04-21
 */
@RestController
@RequestMapping("/scienceandtechnology/certificate")
public class KyEnterpriseCertificateController extends BaseController
{
    @Autowired
    private IKyEnterpriseCertificateService kyEnterpriseCertificateService;

    /**
     * 查询企业证书详情列表
     */
    @GetMapping("/list")
    public Object list(KyEnterpriseCertificate kyEnterpriseCertificate)
    {
        List<KyEnterpriseCertificate> list = kyEnterpriseCertificateService.selectKyEnterpriseCertificateList(kyEnterpriseCertificate);
        return list;
    }



    /**
     * 获取企业证书详情详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(kyEnterpriseCertificateService.selectKyEnterpriseCertificateById(id));
    }

    /**
     * 同步企业证书信息
     */
    @Log(title = "同步企业证书信息", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:certificate:syncToLocalEnterprise')")
    @PostMapping(value = "/syncToLocalEnterprise")
    public AjaxResult syncToLocalEnterprise(@RequestBody KyEnterpriseComprehensiveInformation kyEnterpriseCertificate)
    {
        return kyEnterpriseCertificateService.syncToLocalEnterprise(kyEnterpriseCertificate);
    }


}
