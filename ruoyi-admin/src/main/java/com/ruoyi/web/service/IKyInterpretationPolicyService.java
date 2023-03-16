package com.ruoyi.web.service;

import com.ruoyi.web.domain.KyInterpretationPolicy;
import com.ruoyi.web.domain.KyOriginalPolicy;

import java.util.List;

/**
 * 政策解读Service接口
 * 
 * @author ruoyi
 * @date 2023-03-13
 */
public interface IKyInterpretationPolicyService 
{
    /**
     * 查询政策解读
     * 
     * @param id 政策解读主键
     * @return 政策解读
     */
    public KyInterpretationPolicy selectKyInterpretationPolicyById(Long id);

    /**
     * 查询政策解读列表
     * 
     * @param kyInterpretationPolicy 政策解读
     * @return 政策解读集合
     */
    public List<KyInterpretationPolicy> selectKyInterpretationPolicyList(KyInterpretationPolicy kyInterpretationPolicy);

    /**
     * 新增政策解读
     * 
     * @param kyInterpretationPolicy 政策解读
     * @return 结果
     */
    public int insertKyInterpretationPolicy(KyInterpretationPolicy kyInterpretationPolicy);

    /**
     * 修改政策解读
     * 
     * @param kyInterpretationPolicy 政策解读
     * @return 结果
     */
    public int updateKyInterpretationPolicy(KyInterpretationPolicy kyInterpretationPolicy);

    /**
     * 批量删除政策解读
     * 
     * @param ids 需要删除的政策解读主键集合
     * @return 结果
     */
    public int deleteKyInterpretationPolicyByIds(Long[] ids);

    /**
     * 删除政策解读信息
     * 
     * @param id 政策解读主键
     * @return 结果
     */
    public int deleteKyInterpretationPolicyById(Long id);

    KyInterpretationPolicy selectKyInterpretationPolicyByOriginalpolicyId(Long id);
}
