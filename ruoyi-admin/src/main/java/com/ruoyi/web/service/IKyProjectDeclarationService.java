package com.ruoyi.web.service;

import com.ruoyi.web.domain.KyProjectDeclaration;

import java.util.List;

/**
 * 项目申报Service接口
 * 
 * @author ruoyi
 * @date 2023-03-15
 */
public interface IKyProjectDeclarationService 
{
    /**
     * 查询项目申报
     * 
     * @param id 项目申报主键
     * @return 项目申报
     */
    public KyProjectDeclaration selectKyProjectDeclarationById(Long id);

    /**
     * 查询项目申报列表
     * 
     * @param kyProjectDeclaration 项目申报
     * @return 项目申报集合
     */
    public List<KyProjectDeclaration> selectKyProjectDeclarationList(KyProjectDeclaration kyProjectDeclaration);

    /**
     * 新增项目申报
     * 
     * @param kyProjectDeclaration 项目申报
     * @return 结果
     */
    public int insertKyProjectDeclaration(KyProjectDeclaration kyProjectDeclaration);

    /**
     * 修改项目申报
     * 
     * @param kyProjectDeclaration 项目申报
     * @return 结果
     */
    public int updateKyProjectDeclaration(KyProjectDeclaration kyProjectDeclaration);

    /**
     * 批量删除项目申报
     * 
     * @param ids 需要删除的项目申报主键集合
     * @return 结果
     */
    public int deleteKyProjectDeclarationByIds(Long[] ids);

    /**
     * 删除项目申报信息
     * 
     * @param id 项目申报主键
     * @return 结果
     */
    public int deleteKyProjectDeclarationById(Long id);
}
