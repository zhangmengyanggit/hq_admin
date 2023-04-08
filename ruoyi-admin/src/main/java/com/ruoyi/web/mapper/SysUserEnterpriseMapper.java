package com.ruoyi.web.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.web.domain.SysUserEnterprise;

import java.util.List;

/**
 * 用户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-21
 */
public interface SysUserEnterpriseMapper 
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

    /**
     * 删除用户信息
     * 
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteSysUserEnterpriseByUserId(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserEnterpriseByUserIds(Long[] userIds);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    public  SysUserEnterprise checkUserNameUnique(String userName);

    public void updateSysUserEnterpriseByParams(SysUserEnterprise sysUserEnterprise);
}
