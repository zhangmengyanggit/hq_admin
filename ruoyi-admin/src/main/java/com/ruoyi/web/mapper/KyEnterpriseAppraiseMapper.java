package com.ruoyi.web.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.web.domain.KyEnterpriseAppraise;

/**
 * 企业用户评价Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-29
 */
public interface KyEnterpriseAppraiseMapper 
{
    /**
     * 查询企业用户评价
     * 
     * @param id 企业用户评价主键
     * @return 企业用户评价
     */
    public KyEnterpriseAppraise selectKyEnterpriseAppraiseById(Long id);

    /**
     * 查询企业用户评价列表
     * 
     * @param kyEnterpriseAppraise 企业用户评价
     * @return 企业用户评价集合
     */
    public List<KyEnterpriseAppraise> selectKyEnterpriseAppraiseList(KyEnterpriseAppraise kyEnterpriseAppraise);

    /**
     * 新增企业用户评价
     * 
     * @param kyEnterpriseAppraise 企业用户评价
     * @return 结果
     */
    public int insertKyEnterpriseAppraise(KyEnterpriseAppraise kyEnterpriseAppraise);

    /**
     * 修改企业用户评价
     * 
     * @param kyEnterpriseAppraise 企业用户评价
     * @return 结果
     */
    public int updateKyEnterpriseAppraise(KyEnterpriseAppraise kyEnterpriseAppraise);

    /**
     * 删除企业用户评价
     *
     * @param id 企业用户评价主键
     * @return 结果
     */
    public int deleteKyEnterpriseAppraiseById(Long id);

    /**
     * 批量删除企业用户评价
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyEnterpriseAppraiseByIds(Long[] ids);

    Long selectKyEnterpriseAppraiseCountByParasm(Map<String, Object> paramsMapTwo);
}
