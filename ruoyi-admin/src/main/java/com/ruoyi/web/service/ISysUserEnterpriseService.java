package com.ruoyi.web.service;

import com.ruoyi.web.domain.KyEnterprise;
import com.ruoyi.web.domain.SysUserEnterprise;

import java.util.List;

/**
 * 用户信息Service接口
 * 
 * @author ruoyi
 * @date 2023-03-21
 */
public interface ISysUserEnterpriseService 
{
    /**
     * 查询用户信息
     * 
     * @param userId 用户信息主键
     * @return 用户信息
     */
    public SysUserEnterprise selectSysUserEnterpriseByUserId(Long userId);

    /**
     * 查询用户信息列表
     * 
     * @param sysUserEnterprise 用户信息
     * @return 用户信息集合
     */
    public List<SysUserEnterprise> selectSysUserEnterpriseList(SysUserEnterprise sysUserEnterprise);

    /**
     * 新增用户信息
     * 
     * @param sysUserEnterprise 用户信息
     * @return 结果
     */
    public int insertSysUserEnterprise(SysUserEnterprise sysUserEnterprise);

    /**
     * 修改用户信息
     * 
     * @param sysUserEnterprise 用户信息
     * @return 结果
     */
    public int updateSysUserEnterprise(SysUserEnterprise sysUserEnterprise);



    public Long insertSysUserEnterpriseByEnterprise(KyEnterprise enterprise);

    public void updateSysUserEnterpriseByEnterprise(KyEnterprise enterpriseOrld, KyEnterprise kyEnterprise);
}
