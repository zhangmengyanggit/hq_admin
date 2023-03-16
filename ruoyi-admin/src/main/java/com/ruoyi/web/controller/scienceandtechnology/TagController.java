package com.ruoyi.web.controller.scienceandtechnology;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.domain.Tag;
import com.ruoyi.web.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 【标签】Controller
 * 
 * @author zmy
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/scienceandtechnology/tag")
public class TagController extends BaseController
{
    @Autowired
    private ITagService tagService;

    /**
     * 查询【标签】列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tag tag)
    {
        startPage();
        List<Tag> list = tagService.selectTagList(tag);
        return getDataTable(list);
    }

    /**
     * 导出【标签】列表
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:tag:export')")
    @Log(title = "【标签】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Tag tag)
    {
        List<Tag> list = tagService.selectTagList(tag);
        ExcelUtil<Tag> util = new ExcelUtil<Tag>(Tag.class);
        util.exportExcel(response, list, "【标签】数据");
    }

    /**
     * 获取【标签】详细信息
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:tag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tagService.selectTagById(id));
    }

    /**
     * 新增【标签】
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:tag:add')")
    @Log(title = "【标签】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Tag tag)
    {
        tag.setCreateBy(SecurityUtils.getUsername());
        return toAjax(tagService.insertTag(tag));
    }

    /**
     * 修改【标签】
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:tag:edit')")
    @Log(title = "【标签】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Tag tag)
    {
        return toAjax(tagService.updateTag(tag));
    }

    /**
     * 删除【标签】
     */
    @PreAuthorize("@ss.hasPermi('scienceandtechnology:tag:remove')")
    @Log(title = "【标签】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tagService.deleteTagByIds(ids));
    }

    @GetMapping(value = "/getTagsAll")
    public List<Tag> getTagsAll(){
       return   tagService.selectTagList(new Tag());
    }
}
