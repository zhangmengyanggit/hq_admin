package com.ruoyi.web.service.impl;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.web.domain.KyEnterprise;
import com.ruoyi.web.domain.SysUserEnterprise;
import com.ruoyi.web.mapper.SysUserEnterpriseMapper;
import com.ruoyi.web.service.ISysUserEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-21
 */
@Service
public class SysUserEnterpriseServiceImpl implements ISysUserEnterpriseService
{
    @Autowired
    private SysUserEnterpriseMapper sysUserEnterpriseMapper;
    @Autowired
    private ISysConfigService configService;
    /**
     * 查询用户信息
     * 
     * @param userId 用户信息主键
     * @return 用户信息
     */
    @Override
    public SysUserEnterprise selectSysUserEnterpriseByUserId(Long userId)
    {
        return sysUserEnterpriseMapper.selectSysUserEnterpriseByUserId(userId);
    }

    /**
     * 查询用户信息列表
     * 
     * @param sysUserEnterprise 用户信息
     * @return 用户信息
     */
    @Override
    public List<SysUserEnterprise> selectSysUserEnterpriseList(SysUserEnterprise sysUserEnterprise)
    {
        return sysUserEnterpriseMapper.selectSysUserEnterpriseList(sysUserEnterprise);
    }

    /**
     * 新增用户信息
     * 
     * @param sysUserEnterprise 用户信息
     * @return 结果
     */
    @Override
    public int insertSysUserEnterprise(SysUserEnterprise sysUserEnterprise)
    {
        sysUserEnterprise.setCreateTime(DateUtils.getNowDate());
        return sysUserEnterpriseMapper.insertSysUserEnterprise(sysUserEnterprise);
    }

    /**
     * 修改用户信息
     * 
     * @param sysUserEnterprise 用户信息
     * @return 结果
     */
    @Override
    public int updateSysUserEnterprise(SysUserEnterprise sysUserEnterprise)
    {
        sysUserEnterprise.setUpdateTime(DateUtils.getNowDate());
        return sysUserEnterpriseMapper.updateSysUserEnterprise(sysUserEnterprise);
    }




    @Override
    public Long insertSysUserEnterpriseByEnterprise(KyEnterprise enterprise) {
        //当前企业如无账号则创建企业端登录账号
        SysUserEnterprise  sysUserEnterprise=new SysUserEnterprise();
        sysUserEnterprise.setEnterpriseId(enterprise.getId());
        List<SysUserEnterprise> info = sysUserEnterpriseMapper.selectSysUserEnterpriseList(sysUserEnterprise);
        if(info.size()==0){
            sysUserEnterprise.setCreateBy(SecurityUtils.getUsername());
            sysUserEnterprise.setEnterpriseId(enterprise.getId());
            sysUserEnterprise.setUserType("00");
            sysUserEnterprise.setEmail(enterprise.getEnterpriseMailbox());
            //负责人
            sysUserEnterprise.setNickName(enterprise.getLinkman());
            sysUserEnterprise.setPhonenumber(enterprise.getLinkmanPhone());
            sysUserEnterprise.setSex("2");
            sysUserEnterprise.setRemark("部门发布政策时添加企业用户");
            String password = configService.selectConfigByKey("sys.user.initPassword");
            sysUserEnterprise.setPassword(SecurityUtils.encryptPassword(password));
            sysUserEnterprise.setUserName(enterprise.getLinkmanPhone());
            insertSysUserEnterprise(sysUserEnterprise);
        }else{
            return info.get(0).getUserId();
        }
        return sysUserEnterprise.getUserId();
    }

    @Override
    public void updateSysUserEnterpriseByEnterprise(KyEnterprise enterpriseOrld, KyEnterprise kyEnterprise) {
        if(enterpriseOrld==null||kyEnterprise.getLinkmanPhone().equals(enterpriseOrld.getLinkmanPhone())){
            return;
        }
        SysUserEnterprise sysUserEnterprise=new SysUserEnterprise();
        sysUserEnterprise.setNickName(kyEnterprise.getLinkman());
        sysUserEnterprise.setPhonenumber(kyEnterprise.getLinkmanPhone());
        sysUserEnterprise.setUserName(kyEnterprise.getLinkmanPhone());
        sysUserEnterprise.setEnterpriseId(enterpriseOrld.getId());
        sysUserEnterpriseMapper.updateSysUserEnterpriseByParams(sysUserEnterprise);
    }
}
