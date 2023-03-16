package com.ruoyi.web.service;

import com.ruoyi.web.domain.KyEnterpriseProjectDeclaration;

import java.util.List;
import java.util.Map;

/**
 * 企业申请项目Service接口
 * 
 * @author ruoyi
 * @date 2023-03-15
 */
public interface IKyEnterpriseProjectDeclarationService 
{
    /**
     * 查询企业申请项目
     * 
     * @param id 企业申请项目主键
     * @return 企业申请项目
     */
    public KyEnterpriseProjectDeclaration selectKyEnterpriseProjectDeclarationById(Long id);

    /**
     * 查询企业申请项目列表
     * 
     * @param kyEnterpriseProjectDeclaration 企业申请项目
     * @return 企业申请项目集合
     */
    public List<KyEnterpriseProjectDeclaration> selectKyEnterpriseProjectDeclarationList(KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration);

    /**
     * 新增企业申请项目
     * 
     * @param kyEnterpriseProjectDeclaration 企业申请项目
     * @return 结果
     */
    public int insertKyEnterpriseProjectDeclaration(KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration);

    /**
     * 修改企业申请项目
     * 
     * @param kyEnterpriseProjectDeclaration 企业申请项目
     * @return 结果
     */
    public int updateKyEnterpriseProjectDeclaration(KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration);

    /**
     * 批量删除企业申请项目
     * 
     * @param ids 需要删除的企业申请项目主键集合
     * @return 结果
     */
    public int deleteKyEnterpriseProjectDeclarationByIds(Long[] ids);

    /**
     * 删除企业申请项目信息
     * 
     * @param id 企业申请项目主键
     * @return 结果
     */
    public int deleteKyEnterpriseProjectDeclarationById(Long id);

    Long selectKyEnterpriseProjectDeclarationCountByParams(Map<String, Object> paramsMap);
}
