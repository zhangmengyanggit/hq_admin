package com.ruoyi.web.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.domain.KyEnterprise;
import com.ruoyi.web.domain.KyEnterpriseCertificate;
import com.ruoyi.web.mapper.KyEnterpriseCertificateMapper;
import com.ruoyi.web.model.KyEnterpriseComprehensiveInformation;
import com.ruoyi.web.service.IKyEnterpriseCertificateService;
import com.ruoyi.web.service.IKyEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 企业证书详情Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-21
 */
@Service
public class KyEnterpriseCertificateServiceImpl implements IKyEnterpriseCertificateService {
    @Autowired
    private KyEnterpriseCertificateMapper kyEnterpriseCertificateMapper;
    @Autowired
    private IKyEnterpriseService iKyEnterpriseService;

    /**
     * 查询企业证书详情
     *
     * @param id 企业证书详情主键
     * @return 企业证书详情
     */
    @Override
    public KyEnterpriseCertificate selectKyEnterpriseCertificateById(Long id) {
        return kyEnterpriseCertificateMapper.selectKyEnterpriseCertificateById(id);
    }

    /**
     * 查询企业证书详情列表
     *
     * @param kyEnterpriseCertificate 企业证书详情
     * @return 企业证书详情
     */
    @Override
    public List<KyEnterpriseCertificate> selectKyEnterpriseCertificateList(KyEnterpriseCertificate kyEnterpriseCertificate) {
        return kyEnterpriseCertificateMapper.selectKyEnterpriseCertificateList(kyEnterpriseCertificate);
    }

    /**
     * 新增企业证书详情
     *
     * @param kyEnterpriseCertificate 企业证书详情
     * @return 结果
     */
    @Override
    public int insertKyEnterpriseCertificate(KyEnterpriseCertificate kyEnterpriseCertificate) {
        return kyEnterpriseCertificateMapper.insertKyEnterpriseCertificate(kyEnterpriseCertificate);
    }

    /**
     * 修改企业证书详情
     *
     * @param kyEnterpriseCertificate 企业证书详情
     * @return 结果
     */
    @Override
    public int updateKyEnterpriseCertificate(KyEnterpriseCertificate kyEnterpriseCertificate) {
        return kyEnterpriseCertificateMapper.updateKyEnterpriseCertificate(kyEnterpriseCertificate);
    }

    /**
     * 批量删除企业证书详情
     *
     * @param ids 需要删除的企业证书详情主键
     * @return 结果
     */
    @Override
    public int deleteKyEnterpriseCertificateByIds(Long[] ids) {
        return kyEnterpriseCertificateMapper.deleteKyEnterpriseCertificateByIds(ids);
    }

    /**
     * 删除企业证书详情信息
     *
     * @param id 企业证书详情主键
     * @return 结果
     */
    @Override
    public int deleteKyEnterpriseCertificateById(Long id) {
        return kyEnterpriseCertificateMapper.deleteKyEnterpriseCertificateById(id);
    }

    @Override
    @Transactional
    public AjaxResult syncToLocalEnterprise(KyEnterpriseComprehensiveInformation kyEnterpriseComprehensiveInformation) {
        //根据统一信用编码查询企业
        KyEnterprise enterprise = iKyEnterpriseService.selectKyEnterpriseBySocialUnifiedCreditCodeNumber(kyEnterpriseComprehensiveInformation.getUnifyCode());
        if (enterprise == null) {
            return AjaxResult.error("同步失败，本地企业库中不存在当前企业");
        }
        //查询当前企业证书信息是否已存在
        KyEnterpriseCertificate kyEnterpriseCertificate = new KyEnterpriseCertificate();
        kyEnterpriseCertificate.setType(1l);
        kyEnterpriseCertificate.setKyEnterpriseId(enterprise.getId());
        List<KyEnterpriseCertificate> kyEnterpriseCertificates = selectKyEnterpriseCertificateList(kyEnterpriseCertificate);
        KyEnterpriseCertificate kyEnterpriseCertificateUpdateOrInsert=null;
        int i=0;
        if (kyEnterpriseCertificates.size() > 0) {
            //修改
            kyEnterpriseCertificateUpdateOrInsert=kyEnterpriseCertificates.get(0);
            kyEnterpriseCertificateUpdateOrInsert.setCertificateStatus(kyEnterpriseComprehensiveInformation.getCertificateStatus());
            kyEnterpriseCertificateUpdateOrInsert.setCity(kyEnterpriseComprehensiveInformation.getCity());
            kyEnterpriseCertificateUpdateOrInsert.setDeterminationDate(kyEnterpriseComprehensiveInformation.getDeterminationDate());
            kyEnterpriseCertificateUpdateOrInsert.setDistinct(kyEnterpriseComprehensiveInformation.getDistinct());
            kyEnterpriseCertificateUpdateOrInsert.setIdentificationAuthority(kyEnterpriseComprehensiveInformation.getIdentificationAuthority());
            kyEnterpriseCertificateUpdateOrInsert.setIndustry(kyEnterpriseComprehensiveInformation.getIndustry());
            kyEnterpriseCertificateUpdateOrInsert.setProvince(kyEnterpriseComprehensiveInformation.getProvince());
             i= updateKyEnterpriseCertificate(kyEnterpriseCertificateUpdateOrInsert);
        } else {
            //新增
            kyEnterpriseCertificateUpdateOrInsert=new KyEnterpriseCertificate();
            kyEnterpriseCertificateUpdateOrInsert.setCertificateStatus(kyEnterpriseComprehensiveInformation.getCertificateStatus());
            kyEnterpriseCertificateUpdateOrInsert.setCity(kyEnterpriseComprehensiveInformation.getCity());
            kyEnterpriseCertificateUpdateOrInsert.setDeterminationDate(kyEnterpriseComprehensiveInformation.getDeterminationDate());
            kyEnterpriseCertificateUpdateOrInsert.setDistinct(kyEnterpriseComprehensiveInformation.getDistinct());
            kyEnterpriseCertificateUpdateOrInsert.setIdentificationAuthority(kyEnterpriseComprehensiveInformation.getIdentificationAuthority());
            kyEnterpriseCertificateUpdateOrInsert.setIndustry(kyEnterpriseComprehensiveInformation.getIndustry());
            kyEnterpriseCertificateUpdateOrInsert.setProvince(kyEnterpriseComprehensiveInformation.getProvince());

            kyEnterpriseCertificateUpdateOrInsert.setKyEnterpriseId(enterprise.getId());
            kyEnterpriseCertificateUpdateOrInsert.setType(1l);
            kyEnterpriseCertificateUpdateOrInsert.setCertificateNumber(kyEnterpriseComprehensiveInformation.getCertificateNumber());
            kyEnterpriseCertificateUpdateOrInsert.setSocialUnifiedCreditCodeNumber(kyEnterpriseComprehensiveInformation.getUnifyCode());
            i= insertKyEnterpriseCertificate(kyEnterpriseCertificateUpdateOrInsert);
        }
        return i>0?AjaxResult.success():AjaxResult.error();
    }
}
