package com.ruoyi.web.service;

import com.ruoyi.web.domain.KyEnterpriseProjectDeclaration;
import com.ruoyi.web.vo.KyEnterpriseProjectDeclarationVo;

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
    public KyEnterpriseProjectDeclarationVo selectKyEnterpriseProjectDeclarationById(Long id);

    /**
     * 查询企业申请项目列表
     * 
     * @param kyEnterpriseProjectDeclaration 企业申请项目
     * @return 企业申请项目集合
     */
    public List<KyEnterpriseProjectDeclarationVo> selectKyEnterpriseProjectDeclarationList(KyEnterpriseProjectDeclarationVo kyEnterpriseProjectDeclaration);

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




    Long selectKyEnterpriseProjectDeclarationCountByParams(Map<String, Object> paramsMap);
    /**
     * 批量审核企业申请项目
     *
     * @param ids 需要删除的企业申请项目主键集合
     * @return 结果
     */
    public int auditKyEnterpriseProjectDeclarationByIds(Long[] ids,Integer status);

    List<KyEnterpriseProjectDeclaration> selectList(KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration);

    /**
     * 受理企业申请项目
     *
     * @param ids 需要删除的企业申请项目主键集合
     * @param status s
     * @return 结果
     */
    public  int acceptKyEnterpriseProjectDeclarationByIds(Long[] ids,Integer status);

    Long selectCountByParams(Map<String, Object> paramsMap);
}
