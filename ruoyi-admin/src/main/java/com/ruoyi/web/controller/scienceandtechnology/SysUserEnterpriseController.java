package com.ruoyi.web.controller.scienceandtechnology;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.domain.SysUserEnterprise;
import com.ruoyi.web.service.ISysUserEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户信息
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/scienceandtechnology/userenterprise")
public class SysUserEnterpriseController extends BaseController
{
    @Autowired
    private ISysUserEnterpriseService userService;


    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:userenterprise:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserEnterprise user)
    {
        startPage();
        List<SysUserEnterprise> list = userService.selectSysUserEnterpriseList(user);
        return getDataTable(list);
    }







    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUserEnterprise user)
    {
        if (!userService.checkUserNameUnique(user))
        {
            return error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(user))
        {
            return error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(user))
        {
            return error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertSysUserEnterprise(user));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysUserEnterprise user)
    {
        userService.checkUserDataScope(user.getUserId());
        if (!userService.checkUserNameUnique(user))
        {
            return error("修改用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(user))
        {
            return error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(user))
        {
            return error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(getUsername());
        return toAjax(userService.updateSysUserEnterprise(user));
    }



    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:user:resetPwd')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUserEnterprise user)
    {
        userService.checkUserDataScope(user.getUserId());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(getUsername());
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:user:edit')")
    @Log(title = "企业用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUserEnterprise user)
    {
        userService.checkUserDataScope(user.getUserId());
        user.setUpdateBy(getUsername());
        return toAjax(userService.updateUserStatus(user));
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:user:query')")
    @GetMapping(value = { "/", "/{userId}" })
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId)
    {
        userService.checkUserDataScope(userId);
        AjaxResult ajax = AjaxResult.success();
        if (StringUtils.isNotNull(userId))
        {
            SysUserEnterprise sysUser = userService.selectSysUserEnterpriseByUserId(userId);
            ajax.put(AjaxResult.DATA_TAG, sysUser);
        }
        return ajax;
    }

    @Log(title = "企业用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysUserEnterprise> util = new ExcelUtil<SysUserEnterprise>(SysUserEnterprise.class);
        List<SysUserEnterprise> userList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = userService.importUser(userList, updateSupport, operName);
        return success(message);
    }
    @Log(title = "企业用户管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:user:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUserEnterprise user)
    {
        List<SysUserEnterprise> list = userService.selectSysUserEnterpriseList(user);
        ExcelUtil<SysUserEnterprise> util = new ExcelUtil(SysUserEnterprise.class);
        util.exportExcel(response, list, "企业用户数据");
    }
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<SysUserEnterprise> util = new ExcelUtil(SysUserEnterprise.class);
        util.importTemplateExcel(response, "企业用户数据");
    }
}
