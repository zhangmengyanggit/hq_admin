package com.ruoyi.web.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.web.domain.KyEnterpriseProjectDeclaration;
import com.ruoyi.web.mapper.KyEnterpriseProjectDeclarationMapper;
import com.ruoyi.web.service.IKyEnterpriseProjectDeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 企业申请项目Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-15
 */
@Service
public class KyEnterpriseProjectDeclarationServiceImpl implements IKyEnterpriseProjectDeclarationService
{
    @Autowired
    private KyEnterpriseProjectDeclarationMapper kyEnterpriseProjectDeclarationMapper;

    /**
     * 查询企业申请项目
     * 
     * @param id 企业申请项目主键
     * @return 企业申请项目
     */
    @Override
    public KyEnterpriseProjectDeclaration selectKyEnterpriseProjectDeclarationById(Long id)
    {
        return kyEnterpriseProjectDeclarationMapper.selectKyEnterpriseProjectDeclarationById(id);
    }

    /**
     * 查询企业申请项目列表
     * 
     * @param kyEnterpriseProjectDeclaration 企业申请项目
     * @return 企业申请项目
     */
    @Override
    public List<KyEnterpriseProjectDeclaration> selectKyEnterpriseProjectDeclarationList(KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration)
    {
        return kyEnterpriseProjectDeclarationMapper.selectKyEnterpriseProjectDeclarationList(kyEnterpriseProjectDeclaration);
    }

    /**
     * 新增企业申请项目
     * 
     * @param kyEnterpriseProjectDeclaration 企业申请项目
     * @return 结果
     */
    @Override
    public int insertKyEnterpriseProjectDeclaration(KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration)
    {
        return kyEnterpriseProjectDeclarationMapper.insertKyEnterpriseProjectDeclaration(kyEnterpriseProjectDeclaration);
    }

    /**
     * 修改企业申请项目
     * 
     * @param kyEnterpriseProjectDeclaration 企业申请项目
     * @return 结果
     */
    @Override
    public int updateKyEnterpriseProjectDeclaration(KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration)
    {
        return kyEnterpriseProjectDeclarationMapper.updateKyEnterpriseProjectDeclaration(kyEnterpriseProjectDeclaration);
    }

    /**
     * 批量删除企业申请项目
     * 
     * @param ids 需要删除的企业申请项目主键
     * @return 结果
     */
    @Override
    public int deleteKyEnterpriseProjectDeclarationByIds(Long[] ids)
    {
        return kyEnterpriseProjectDeclarationMapper.deleteKyEnterpriseProjectDeclarationByIds(ids);
    }

    /**
     * 删除企业申请项目信息
     * 
     * @param id 企业申请项目主键
     * @return 结果
     */
    @Override
    public int deleteKyEnterpriseProjectDeclarationById(Long id)
    {
        return kyEnterpriseProjectDeclarationMapper.deleteKyEnterpriseProjectDeclarationById(id);
    }

    @Override
    public Long selectKyEnterpriseProjectDeclarationCountByParams(Map<String, Object> paramsMap) {
        return kyEnterpriseProjectDeclarationMapper.selectKyEnterpriseProjectDeclarationCountByParams(paramsMap);
    }
}
