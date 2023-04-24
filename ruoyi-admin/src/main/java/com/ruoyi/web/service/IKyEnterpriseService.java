package com.ruoyi.web.service;

import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.web.domain.KyEnterprise;

import java.util.List;

/**
 * 企业Service接口
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
public interface IKyEnterpriseService 
{
    /**
     * 查询企业
     * 
     * @param id 企业主键
     * @return 企业
     */
    public KyEnterprise selectKyEnterpriseById(Long id);

    /**
     * 查询企业列表
     * 
     * @param kyEnterprise 企业
     * @return 企业集合
     */
    public List<KyEnterprise> selectKyEnterpriseList(KyEnterprise kyEnterprise);

    /**
     * 新增企业
     * 
     * @param kyEnterprise 企业
     * @return 结果
     */
    public int insertKyEnterprise(KyEnterprise kyEnterprise);

    /**
     * 修改企业
     * 
     * @param kyEnterprise 企业
     * @return 结果
     */
    public int updateKyEnterprise(KyEnterprise kyEnterprise);

    /**
     * 批量删除企业
     * 
     * @param ids 需要删除的企业主键集合
     * @return 结果
     */
    public int deleteKyEnterpriseByIds(Long[] ids);

    /**
     * 删除企业信息
     * 
     * @param id 企业主键
     * @return 结果
     */
    public int deleteKyEnterpriseById(Long id);

    /**
     * 导入企业数据
     *
     * @param enterpriseList 企业据列表
     * @param updateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importEnterprise(List<KyEnterprise> enterpriseList, boolean updateSupport, String operName);

    public List<Long>  selectKyEnterpriseIds(KyEnterprise kyEnterprise);

    public Long selectKyEnterpriseCount(KyEnterprise kyEnterprise);

    public int batchUpdateEnterprise(List<KyEnterprise> enterpriseList);

    public String selectAllSocialUnifiedCreditCodeNumber();

    public  boolean checkTagsByEnterprise(Long[] ids);

    public  KyEnterprise selectKyEnterpriseBySocialUnifiedCreditCodeNumber(String unifyCode);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param kyEnterprise 企业信息
     * @return 下拉树结构列表
     */
    public List<TreeSelect> selectEnterpriseTreeList(KyEnterprise kyEnterprise);
}
