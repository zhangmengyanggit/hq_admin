package com.ruoyi.web.service;

import com.ruoyi.common.core.domain.entity.SysUser;
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


    public  void checkUserDataScope(Long userId);


    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public boolean checkUserNameUnique(SysUserEnterprise user);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public  boolean checkPhoneUnique(SysUserEnterprise user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public   boolean checkEmailUnique(SysUserEnterprise user);



    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    public  int resetPwd(SysUserEnterprise user);

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     * @return 结果
     */
    public  int updateUserStatus(SysUserEnterprise user);
    /**
     * 导入用户数据
     *
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importUser(List<SysUserEnterprise> userList, Boolean isUpdateSupport, String operName);
}
