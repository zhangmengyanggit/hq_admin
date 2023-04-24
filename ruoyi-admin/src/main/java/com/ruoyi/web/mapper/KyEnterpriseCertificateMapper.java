package com.ruoyi.web.mapper;

import com.ruoyi.web.domain.KyEnterpriseCertificate;

import java.util.List;

/**
 * 企业证书详情Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-21
 */
public interface KyEnterpriseCertificateMapper 
{
    /**
     * 查询企业证书详情
     * 
     * @param id 企业证书详情主键
     * @return 企业证书详情
     */
    public KyEnterpriseCertificate selectKyEnterpriseCertificateById(Long id);

    /**
     * 查询企业证书详情列表
     * 
     * @param kyEnterpriseCertificate 企业证书详情
     * @return 企业证书详情集合
     */
    public List<KyEnterpriseCertificate> selectKyEnterpriseCertificateList(KyEnterpriseCertificate kyEnterpriseCertificate);

    /**
     * 新增企业证书详情
     * 
     * @param kyEnterpriseCertificate 企业证书详情
     * @return 结果
     */
    public int insertKyEnterpriseCertificate(KyEnterpriseCertificate kyEnterpriseCertificate);

    /**
     * 修改企业证书详情
     * 
     * @param kyEnterpriseCertificate 企业证书详情
     * @return 结果
     */
    public int updateKyEnterpriseCertificate(KyEnterpriseCertificate kyEnterpriseCertificate);

    /**
     * 删除企业证书详情
     * 
     * @param id 企业证书详情主键
     * @return 结果
     */
    public int deleteKyEnterpriseCertificateById(Long id);

    /**
     * 批量删除企业证书详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyEnterpriseCertificateByIds(Long[] ids);
}
