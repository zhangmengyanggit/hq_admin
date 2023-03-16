package com.ruoyi.web.service.impl;



import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.domain.Tag;
import com.ruoyi.web.mapper.TagMapper;
import com.ruoyi.web.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

/**
 * 【标签】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
@Service
public class TagServiceImpl implements ITagService
{
    @Autowired
    private TagMapper tagMapper;

    /**
     * 查询【标签】
     * 
     * @param id 【标签】主键
     * @return 【标签】
     */
    @Override
    public Tag selectTagById(Long id)
    {
        return tagMapper.selectTagById(id);
    }

    /**
     * 查询【标签】列表
     * 
     * @param tag 【标签】
     * @return 【标签】
     */
    @Override
    public List<Tag> selectTagList(Tag tag)
    {
        return tagMapper.selectTagList(tag);
    }

    /**
     * 新增【标签】
     * 
     * @param tag 【标签】
     * @return 结果
     */
    @Override
    public int insertTag(Tag tag)
    {
        tag.setCreateTime(DateUtils.getNowDate());
        return tagMapper.insertTag(tag);
    }

    /**
     * 修改【标签】
     * 
     * @param tag 【标签】
     * @return 结果
     */
    @Override
    public int updateTag(Tag tag)
    {
        return tagMapper.updateTag(tag);
    }

    /**
     * 批量删除【标签】
     * 
     * @param ids 需要删除的【标签】主键
     * @return 结果
     */
    @Override
    public int deleteTagByIds(Long[] ids)
    {
        return tagMapper.deleteTagByIds(ids);
    }

    /**
     * 删除【标签】信息
     * 
     * @param id 【标签】主键
     * @return 结果
     */
    @Override
    public int deleteTagById(Long id)
    {
        return tagMapper.deleteTagById(id);
    }

    @Override
    public List<Tag> selectTagByIds(String tagIds) {
        long[] array = Arrays.asList(tagIds.split(",")).stream().mapToLong(Long::parseLong).toArray();
        return tagMapper.selectTagListByIds(array);
    }


}
