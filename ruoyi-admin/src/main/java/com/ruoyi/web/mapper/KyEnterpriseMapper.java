package com.ruoyi.web.mapper;

import com.ruoyi.web.domain.KyEnterprise;

import java.util.List;

/**
 * 企业Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
public interface KyEnterpriseMapper 
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
     * 删除企业
     * 
     * @param id 企业主键
     * @return 结果
     */
    public int deleteKyEnterpriseById(Long id);

    /**
     * 批量删除企业
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyEnterpriseByIds(Long[] ids);

    List<Long> selectKyEnterpriseIds(KyEnterprise kyEnterprise);

    Long selectKyEnterpriseCount(KyEnterprise kyEnterprise);
}
