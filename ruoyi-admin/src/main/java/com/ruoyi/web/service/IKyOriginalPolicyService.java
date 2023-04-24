package com.ruoyi.web.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.domain.KyOriginalPolicy;

/**
 * 政策管理Service接口
 * 
 * @author ruoyi
 * @date 2023-03-11
 */
public interface IKyOriginalPolicyService 
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
     * 批量删除政策管理
     * 
     * @param ids 需要删除的政策管理主键集合
     * @return 结果
     */
    public int deleteKyOriginalPolicyByIds(Long[] ids);

    /**
     * 删除政策管理信息
     * 
     * @param id 政策管理主键
     * @return 结果
     */
    public int deleteKyOriginalPolicyById(Long id);
    /**
     * 发布当前政策给所有符合条件的企业
     *
     * @param id 政策管理主键
     * @return 结果
     */
    AjaxResult publishToAllEnterprise(Long id);


    AjaxResult publishByenterpriseAndIds(String ids, Long originalpolicyId);

    Long selectKyOriginalPolicyCount(KyOriginalPolicy originalPolicy);

    Long selectCountByParams(Map<String, Object> paramsMap);

    public  boolean checkTagsByOriginalPolicy(Long[] ids);
}
