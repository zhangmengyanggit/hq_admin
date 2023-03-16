package com.ruoyi.web.controller.scienceandtechnology;

import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.domain.KyInterpretationPolicy;
import com.ruoyi.web.domain.KyOriginalPolicy;
import com.ruoyi.web.service.IKyInterpretationPolicyService;
import com.ruoyi.web.service.IKyOriginalPolicyService;
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
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;
import java.util.Map;

/**
 * 政策管理Controller
 * 
 * @author ruoyi
 * @date 2023-03-11
 */
@RestController
@RequestMapping("/scienceandtechnology/originalpolicy")
public class KyOriginalPolicyController extends BaseController
{
    @Autowired
    private IKyOriginalPolicyService kyOriginalPolicyService;
    @Autowired
    private IKyInterpretationPolicyService iKyInterpretationPolicyService;
    /**
     * 查询政策管理列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:originalpolicy:query')")
    @GetMapping("/list")
    public TableDataInfo list(KyOriginalPolicy kyOriginalPolicy)
    {
        startPage();
        List<KyOriginalPolicy> list = kyOriginalPolicyService.selectKyOriginalPolicyList(kyOriginalPolicy);
        return getDataTable(list);
    }

    /**
     * 导出政策管理列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:originalpolicy:export')")
    @Log(title = "政策管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyOriginalPolicy kyOriginalPolicy)
    {
        List<KyOriginalPolicy> list = kyOriginalPolicyService.selectKyOriginalPolicyList(kyOriginalPolicy);
        ExcelUtil<KyOriginalPolicy> util = new ExcelUtil<KyOriginalPolicy>(KyOriginalPolicy.class);
        util.exportExcel(response, list, "政策管理数据");
    }

    /**
     * 获取政策管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:originalpolicy:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(kyOriginalPolicyService.selectKyOriginalPolicyById(id));
    }

    /**
     * 新增政策管理
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:originalpolicy:add')")
    @Log(title = "政策管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyOriginalPolicy kyOriginalPolicy)
    {
        kyOriginalPolicy.setCreateBy(SecurityUtils.getUsername());
        return toAjax(kyOriginalPolicyService.insertKyOriginalPolicy(kyOriginalPolicy));
    }

    /**
     * 修改政策管理
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:originalpolicy:edit')")
    @Log(title = "政策管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyOriginalPolicy kyOriginalPolicy)
    {
        return toAjax(kyOriginalPolicyService.updateKyOriginalPolicy(kyOriginalPolicy));
    }

    /**
     * 删除政策管理
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:originalpolicy:remove')")
    @Log(title = "政策管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(kyOriginalPolicyService.deleteKyOriginalPolicyByIds(ids));
    }

    /**
     * 获取政策解读信息
     */
    @GetMapping(value = "/getInterpretationPolicy/{id}")
    public AjaxResult getInterpretationPolicy(@PathVariable("id") Long id)
    {
        KyInterpretationPolicy interpretationPolicy=  iKyInterpretationPolicyService.selectKyInterpretationPolicyByOriginalpolicyId(id);
        if(interpretationPolicy==null){
            interpretationPolicy=new KyInterpretationPolicy();
        }
        return success(interpretationPolicy);
    }

    /**
     * 新增政策解读
     */
    @Log(title = "政策解读", businessType = BusinessType.INSERT)
    @PostMapping(value = "/addInterpretationPolicy")
    public AjaxResult addInterpretationPolicy(@RequestBody KyInterpretationPolicy kyInterpretationPolicy)
    {
        //更新政策状态为已解读
        KyOriginalPolicy kyOriginalPolicy=new KyOriginalPolicy();
        kyOriginalPolicy.setId(kyInterpretationPolicy.getOriginalPolicyId());
        kyOriginalPolicy.setPublishStatus(1l);
        kyOriginalPolicyService.updateKyOriginalPolicy(kyOriginalPolicy);
        kyInterpretationPolicy.setCreateBy(SecurityUtils.getUsername());
        return toAjax(iKyInterpretationPolicyService.insertKyInterpretationPolicy(kyInterpretationPolicy));
    }

    /**
     * 修改政策解读
     */

    @Log(title = "政策解读", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/editInterpretationPolicy")
    public AjaxResult editInterpretationPolicy(@RequestBody KyInterpretationPolicy kyInterpretationPolicy)
    {
        return toAjax(iKyInterpretationPolicyService.updateKyInterpretationPolicy(kyInterpretationPolicy));
    }
    /**
     * 发布全部
     */

    @Log(title = "政策发布", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/publishToAllEnterprise")
    public AjaxResult publishToAllEnterprise(@RequestBody Long id)
    {
        return kyOriginalPolicyService.publishToAllEnterprise(id);
    }

    /**
     * 发布（根据选择的企业）
     */

    @Log(title = "政策发布", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/publishByenterpriseAndIds")
    public AjaxResult publishByenterpriseAndIds(@RequestBody Map<String,Object> paramsMap)
    {
       String ids=String.valueOf( paramsMap.get("ids"));
       Long originalpolicyId= Long.valueOf(paramsMap.get("originalpolicyId").toString());
        return kyOriginalPolicyService.publishByenterpriseAndIds(ids,originalpolicyId);
    }
}
