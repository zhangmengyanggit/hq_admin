package com.ruoyi.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.web.domain.KyEnterpriseAppraise;
import com.ruoyi.web.domain.KyEnterpriseProjectDeclaration;
import com.ruoyi.web.mapper.KyEnterpriseProjectDeclarationMapper;
import com.ruoyi.web.service.IKyEnterpriseProjectDeclarationService;
import com.ruoyi.web.vo.KyEnterpriseProjectDeclarationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeOperationsException;

/**
 * 企业申请项目Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-15
 */
@Service
public class KyEnterpriseProjectDeclarationServiceImpl implements IKyEnterpriseProjectDeclarationService {
    @Autowired
    private KyEnterpriseProjectDeclarationMapper kyEnterpriseProjectDeclarationMapper;

    /**
     * 查询企业申请项目
     *
     * @param id 企业申请项目主键
     * @return 企业申请项目
     */
    @Override
    public KyEnterpriseProjectDeclarationVo selectKyEnterpriseProjectDeclarationById(Long id) {
        return kyEnterpriseProjectDeclarationMapper.selectKyEnterpriseProjectDeclarationById(id);
    }

    /**
     * 查询企业申请项目列表
     *
     * @param kyEnterpriseProjectDeclaration 企业申请项目
     * @return 企业申请项目
     */
    @Override
    public List<KyEnterpriseProjectDeclarationVo> selectKyEnterpriseProjectDeclarationList(KyEnterpriseProjectDeclarationVo kyEnterpriseProjectDeclaration) {
        List<KyEnterpriseProjectDeclarationVo> kyEnterpriseProjectDeclarationVos = kyEnterpriseProjectDeclarationMapper.selectKyEnterpriseProjectDeclarationList(kyEnterpriseProjectDeclaration);
        kyEnterpriseProjectDeclarationVos.stream().forEach(e -> {
            if (!RuoYiConfig.isDemoEnabled()) {
                //本地
                e.setBusinessLicenseUrl(RuoYiConfig.getSeverWebUrl() + e.getBusinessLicenseUrl());
                e.setIdentityCardUrl(RuoYiConfig.getSeverWebUrl() + e.getIdentityCardUrl());
            } else {
                //政务内网
                e.setBusinessLicenseUrl(RuoYiConfig.getSeverWebUrl() + "/prod-api" + e.getBusinessLicenseUrl());
                e.setIdentityCardUrl(RuoYiConfig.getSeverWebUrl() + "/prod-api" + e.getIdentityCardUrl());
            }

        });

        return kyEnterpriseProjectDeclarationVos;
    }

    /**
     * 新增企业申请项目
     *
     * @param kyEnterpriseProjectDeclaration 企业申请项目
     * @return 结果
     */
    @Override
    @Transactional
    public int insertKyEnterpriseProjectDeclaration(KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration) {
        return kyEnterpriseProjectDeclarationMapper.insertKyEnterpriseProjectDeclaration(kyEnterpriseProjectDeclaration);
    }

    /**
     * 修改企业申请项目
     *
     * @param kyEnterpriseProjectDeclaration 企业申请项目
     * @return 结果
     */
    @Override
    @Transactional
    public int updateKyEnterpriseProjectDeclaration(KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration) {
        return kyEnterpriseProjectDeclarationMapper.updateKyEnterpriseProjectDeclaration(kyEnterpriseProjectDeclaration);
    }


    @Override
    public Long selectKyEnterpriseProjectDeclarationCountByParams(Map<String, Object> paramsMap) {
        return kyEnterpriseProjectDeclarationMapper.selectKyEnterpriseProjectDeclarationCountByParams(paramsMap);
    }

    @Override
    @Transactional
    public int auditKyEnterpriseProjectDeclarationByIds(Long[] ids, Integer status) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("ids", ids);
        paramsMap.put("auditStatus", status);
        return kyEnterpriseProjectDeclarationMapper.updateByIds(paramsMap);
    }

    @Override
    public List<KyEnterpriseProjectDeclaration> selectList(KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration) {
        return kyEnterpriseProjectDeclarationMapper.selectList(kyEnterpriseProjectDeclaration);
    }

    @Override
    @Transactional
    public int acceptKyEnterpriseProjectDeclarationByIds(Long[] ids, Integer status) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("ids", ids);
        paramsMap.put("auditStatus", status);
        return kyEnterpriseProjectDeclarationMapper.updateByIds(paramsMap);
    }

    @Override
    public Long selectCountByParams(Map<String, Object> paramsMap) {
        return kyEnterpriseProjectDeclarationMapper.selectCountByParams(paramsMap);
    }
}
