package com.ruoyi.web.service.impl;

import java.util.List;

import com.ruoyi.web.domain.KyProjectDeclaration;
import com.ruoyi.web.mapper.KyProjectDeclarationMapper;
import com.ruoyi.web.service.IKyProjectDeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目申报Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-15
 */
@Service
public class KyProjectDeclarationServiceImpl implements IKyProjectDeclarationService
{
    @Autowired
    private KyProjectDeclarationMapper kyProjectDeclarationMapper;

    /**
     * 查询项目申报
     * 
     * @param id 项目申报主键
     * @return 项目申报
     */
    @Override
    public KyProjectDeclaration selectKyProjectDeclarationById(Long id)
    {
        return kyProjectDeclarationMapper.selectKyProjectDeclarationById(id);
    }

    /**
     * 查询项目申报列表
     * 
     * @param kyProjectDeclaration 项目申报
     * @return 项目申报
     */
    @Override
    public List<KyProjectDeclaration> selectKyProjectDeclarationList(KyProjectDeclaration kyProjectDeclaration)
    {
        return kyProjectDeclarationMapper.selectKyProjectDeclarationList(kyProjectDeclaration);
    }

    /**
     * 新增项目申报
     * 
     * @param kyProjectDeclaration 项目申报
     * @return 结果
     */
    @Override
    public int insertKyProjectDeclaration(KyProjectDeclaration kyProjectDeclaration)
    {
        return kyProjectDeclarationMapper.insertKyProjectDeclaration(kyProjectDeclaration);
    }

    /**
     * 修改项目申报
     * 
     * @param kyProjectDeclaration 项目申报
     * @return 结果
     */
    @Override
    public int updateKyProjectDeclaration(KyProjectDeclaration kyProjectDeclaration)
    {
        return kyProjectDeclarationMapper.updateKyProjectDeclaration(kyProjectDeclaration);
    }

    /**
     * 批量删除项目申报
     * 
     * @param ids 需要删除的项目申报主键
     * @return 结果
     */
    @Override
    public int deleteKyProjectDeclarationByIds(Long[] ids)
    {
        return kyProjectDeclarationMapper.deleteKyProjectDeclarationByIds(ids);
    }

    /**
     * 删除项目申报信息
     * 
     * @param id 项目申报主键
     * @return 结果
     */
    @Override
    public int deleteKyProjectDeclarationById(Long id)
    {
        return kyProjectDeclarationMapper.deleteKyProjectDeclarationById(id);
    }
}
