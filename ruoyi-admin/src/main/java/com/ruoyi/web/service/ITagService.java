package com.ruoyi.web.service;



import com.ruoyi.web.domain.Tag;

import java.util.List;

/**
 * 【标签】Service接口
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
public interface ITagService 
{
    /**
     * 查询【标签】
     * 
     * @param id 【标签】主键
     * @return 【标签】
     */
    public Tag selectTagById(Long id);

    /**
     * 查询【标签】列表
     * 
     * @param tag 【标签】
     * @return 【标签】集合
     */
    public List<Tag> selectTagList(Tag tag);

    /**
     * 新增【标签】
     * 
     * @param tag 【标签】
     * @return 结果
     */
    public int insertTag(Tag tag);

    /**
     * 修改【标签】
     * 
     * @param tag 【标签】
     * @return 结果
     */
    public int updateTag(Tag tag);

    /**
     * 批量删除【标签】
     * 
     * @param ids 需要删除的【标签】主键集合
     * @return 结果
     */
    public int deleteTagByIds(Long[] ids);

    /**
     * 删除【标签】信息
     * 
     * @param id 【标签】主键
     * @return 结果
     */
    public int deleteTagById(Long id);


    List<Tag> selectTagByIds(String tagIds);
}
