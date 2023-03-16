package com.ruoyi.web.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.domain.KyInterpretationPolicy;
import com.ruoyi.web.domain.KyOriginalPolicy;
import com.ruoyi.web.mapper.KyInterpretationPolicyMapper;
import com.ruoyi.web.service.IKyInterpretationPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 政策解读Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-13
 */
@Service
public class KyInterpretationPolicyServiceImpl implements IKyInterpretationPolicyService
{
    @Autowired
    private KyInterpretationPolicyMapper kyInterpretationPolicyMapper;

    /**
     * 查询政策解读
     * 
     * @param id 政策解读主键
     * @return 政策解读
     */
    @Override
    public KyInterpretationPolicy selectKyInterpretationPolicyById(Long id)
    {
        return kyInterpretationPolicyMapper.selectKyInterpretationPolicyById(id);
    }

    /**
     * 查询政策解读列表
     * 
     * @param kyInterpretationPolicy 政策解读
     * @return 政策解读
     */
    @Override
    public List<KyInterpretationPolicy> selectKyInterpretationPolicyList(KyInterpretationPolicy kyInterpretationPolicy)
    {
        return kyInterpretationPolicyMapper.selectKyInterpretationPolicyList(kyInterpretationPolicy);
    }

    /**
     * 新增政策解读
     * 
     * @param kyInterpretationPolicy 政策解读
     * @return 结果
     */
    @Override
    public int insertKyInterpretationPolicy(KyInterpretationPolicy kyInterpretationPolicy)
    {
        kyInterpretationPolicy.setCreateTime(DateUtils.getNowDate());
        return kyInterpretationPolicyMapper.insertKyInterpretationPolicy(kyInterpretationPolicy);
    }

    /**
     * 修改政策解读
     * 
     * @param kyInterpretationPolicy 政策解读
     * @return 结果
     */
    @Override
    public int updateKyInterpretationPolicy(KyInterpretationPolicy kyInterpretationPolicy)
    {
        return kyInterpretationPolicyMapper.updateKyInterpretationPolicy(kyInterpretationPolicy);
    }

    /**
     * 批量删除政策解读
     * 
     * @param ids 需要删除的政策解读主键
     * @return 结果
     */
    @Override
    public int deleteKyInterpretationPolicyByIds(Long[] ids)
    {
        return kyInterpretationPolicyMapper.deleteKyInterpretationPolicyByIds(ids);
    }

    /**
     * 删除政策解读信息
     * 
     * @param id 政策解读主键
     * @return 结果
     */
    @Override
    public int deleteKyInterpretationPolicyById(Long id)
    {
        return kyInterpretationPolicyMapper.deleteKyInterpretationPolicyById(id);
    }

    @Override
    public KyInterpretationPolicy selectKyInterpretationPolicyByOriginalpolicyId(Long originalpolicyId) {
        return kyInterpretationPolicyMapper.selectKyInterpretationPolicyByOriginalpolicyId(originalpolicyId);
    }
}
