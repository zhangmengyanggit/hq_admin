package com.ruoyi.web.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.web.domain.KyOriginalPolicy;

/**
 * 政策管理Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-11
 */
public interface KyOriginalPolicyMapper 
{
    /**
     * 查询政策管理
     * 
     * @param id 政策管理主键
     * @return 政策管理
     */
    public KyOriginalPolicy selectKyOriginalPolicyById(Long id);

    /**
     * 查询政策管理列表
     * 
     * @param kyOriginalPolicy 政策管理
     * @return 政策管理集合
     */
    public List<KyOriginalPolicy> selectKyOriginalPolicyList(KyOriginalPolicy kyOriginalPolicy);

    /**
     * 新增政策管理
     * 
     * @param kyOriginalPolicy 政策管理
     * @return 结果
     */
    public int insertKyOriginalPolicy(KyOriginalPolicy kyOriginalPolicy);

    /**
     * 修改政策管理
     * 
     * @param kyOriginalPolicy 政策管理
     * @return 结果
     */
    public int updateKyOriginalPolicy(KyOriginalPolicy kyOriginalPolicy);

    /**
     * 删除政策管理
     * 
     * @param id 政策管理主键
     * @return 结果
     */
    public int deleteKyOriginalPolicyById(Long id);

    /**
     * 批量删除政策管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyOriginalPolicyByIds(Long[] ids);

    Long selectKyOriginalPolicyCount(KyOriginalPolicy originalPolicy);

    Long selectCountByParams(Map<String, Object> paramsMap);
}
