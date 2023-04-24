package com.ruoyi.web.controller.scienceandtechnology;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.model.KyEnterpriseComprehensiveInformation;
import com.ruoyi.web.service.IKyEnterpriseComprehensiveInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 企业综合信息查询Controller
 * 
 * @author zmy
 * @date 2023-04-07
 */
@RestController
@RequestMapping("/scienceandtechnology/enterprisecomprehensiveinformation")
public class KyEnterpriseComprehensiveInformationController extends BaseController
{
    @Autowired
    private IKyEnterpriseComprehensiveInformationService enterpriseComprehensiveInformationService;

    /**
     * 查询科技部_高新技术企业备案基础信息
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprisecomprehensiveinformation:basicInformationforenterprisefiling:list')")
    @GetMapping("/listBasicInformationForEnterpriseFiling")
    public Object listBasicInformationForEnterpriseFiling(KyEnterpriseComprehensiveInformation enterpriseComprehensiveInformation)
    {
        enterpriseComprehensiveInformation.setType(1);
        List<KyEnterpriseComprehensiveInformation> list = enterpriseComprehensiveInformationService.selectList(enterpriseComprehensiveInformation);
        return list;
    }
    /**
     * 查询科技部_高新技术企业证书信息
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprisecomprehensiveinformation:enterprisecertificateinformation:list')")
    @GetMapping("/listEnterpriseCertificateInformation")
    public Object listEnterpriseCertificateInformation(KyEnterpriseComprehensiveInformation enterpriseComprehensiveInformation)
    {
        enterpriseComprehensiveInformation.setType(2);
        List<KyEnterpriseComprehensiveInformation> list = enterpriseComprehensiveInformationService.selectList(enterpriseComprehensiveInformation);
        return list;
    }
    /**
     * 查询科技部_科技型中小企业创新基金项目信息
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprisecomprehensiveinformation:smeinnovationfundprojectinformation:list')")
    @GetMapping("/listSMEInnovationFundProjectInformation")
    public Object listSMEInnovationFundProjectInformation(KyEnterpriseComprehensiveInformation enterpriseComprehensiveInformation)
    {
        enterpriseComprehensiveInformation.setType(3);
        List<KyEnterpriseComprehensiveInformation> list = enterpriseComprehensiveInformationService.selectList(enterpriseComprehensiveInformation);
        return list;
    }
}
