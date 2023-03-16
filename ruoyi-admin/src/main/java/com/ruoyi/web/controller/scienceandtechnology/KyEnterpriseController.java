package com.ruoyi.web.controller.scienceandtechnology;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.ruoyi.web.domain.KyEnterprise;
import com.ruoyi.web.service.IKyEnterpriseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 企业Controller
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
@RestController
@RequestMapping("scienceandtechnology/enterprise")
public class KyEnterpriseController extends BaseController
{
    @Autowired
    private IKyEnterpriseService kyEnterpriseService;

    /**
     * 查询企业列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyEnterprise kyEnterprise)
    {
        startPage();
        List<KyEnterprise> list = kyEnterpriseService.selectKyEnterpriseList(kyEnterprise);
        return getDataTable(list);
    }

    /**
     * 导出企业列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:export')")
    @Log(title = "企业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyEnterprise kyEnterprise)
    {
        List<KyEnterprise> list = kyEnterpriseService.selectKyEnterpriseList(kyEnterprise);
        ExcelUtil<KyEnterprise> util = new ExcelUtil<KyEnterprise>(KyEnterprise.class);
        util.exportExcel(response, list, "企业数据");
    }

    /**
     * 获取企业详细信息
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(kyEnterpriseService.selectKyEnterpriseById(id));
    }

    /**
     * 新增企业
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:add')")
    @Log(title = "企业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyEnterprise kyEnterprise)
    {
        return toAjax(kyEnterpriseService.insertKyEnterprise(kyEnterprise));
    }

    /**
     * 修改企业
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:edit')")
    @Log(title = "企业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyEnterprise kyEnterprise)
    {
        return toAjax(kyEnterpriseService.updateKyEnterprise(kyEnterprise));
    }

    /**
     * 删除企业
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:remove')")
    @Log(title = "企业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(kyEnterpriseService.deleteKyEnterpriseByIds(ids));
    }


    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<KyEnterprise> util = new ExcelUtil<KyEnterprise>(KyEnterprise.class);
        util.importTemplateExcel(response, "企业数据");
    }

    @Log(title = "企业管理导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:enterprise:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<KyEnterprise> util = new ExcelUtil<KyEnterprise>(KyEnterprise.class);
        List<KyEnterprise> enterpriseList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = kyEnterpriseService.importEnterprise(enterpriseList, updateSupport, operName);
        return success(message);
    }
}
