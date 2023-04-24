package com.ruoyi.web.service.impl;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import com.ruoyi.web.domain.KyEnterprise;
import com.ruoyi.web.domain.SysUserEnterprise;
import com.ruoyi.web.mapper.SysUserEnterpriseMapper;
import com.ruoyi.web.service.ISysUserEnterpriseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

/**
 * 用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-21
 */
@Service
public class SysUserEnterpriseServiceImpl implements ISysUserEnterpriseService
{    private static final Logger log = LoggerFactory.getLogger(SysUserEnterpriseServiceImpl.class);
    @Autowired
    protected Validator validator;
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

    @Override
    public void checkUserDataScope(Long userId) {

            SysUserEnterprise user = new SysUserEnterprise();
            user.setUserId(userId);
            List<SysUserEnterprise> users = selectSysUserEnterpriseList(user);
            if (StringUtils.isEmpty(users))
            {
                throw new ServiceException("没有权限访问用户数据！");
            }

    }

    @Override
    public boolean checkUserNameUnique(SysUserEnterprise user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUserEnterprise info = sysUserEnterpriseMapper.checkUserNameUnique(user.getUserName());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public boolean checkPhoneUnique(SysUserEnterprise user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUserEnterprise info = sysUserEnterpriseMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public boolean checkEmailUnique(SysUserEnterprise user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUserEnterprise info = sysUserEnterpriseMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }


    @Override
    public int resetPwd(SysUserEnterprise user) {
        return sysUserEnterpriseMapper.updateSysUserEnterprise(user);
    }

    @Override
    public int updateUserStatus(SysUserEnterprise user) {
        return sysUserEnterpriseMapper.updateSysUserEnterprise(user);
    }
    /**
     * 导入用户数据
     *
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<SysUserEnterprise> userList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(userList) || userList.size() == 0)
        {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (SysUserEnterprise user : userList)
        {
            try
            {
                // 验证是否存在这个用户
                SysUserEnterprise u = sysUserEnterpriseMapper.selectUserEnterpriseByUserName(user.getUserName());
                if (StringUtils.isNull(u))
                {
                    BeanValidators.validateWithException(validator, user);
                    user.setPassword(SecurityUtils.encryptPassword(password));
                    user.setCreateBy(operName);
                    this.insertSysUserEnterprise(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, user);
                    checkUserDataScope(user.getUserId());
                    user.setUpdateBy(operName);
                    this.updateSysUserEnterprise(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getUserName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
