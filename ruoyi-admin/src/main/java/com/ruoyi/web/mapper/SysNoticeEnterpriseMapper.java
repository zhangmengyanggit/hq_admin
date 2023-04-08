package com.ruoyi.web.mapper;

import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.web.domain.SysNoticeEnterprise;

import java.util.List;

/**
 * 通知公告表 数据层
 * 
 * @author ruoyi
 */
public interface SysNoticeEnterpriseMapper
{
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public SysNoticeEnterprise selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<SysNoticeEnterprise> selectNoticeList(SysNoticeEnterprise notice);

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(SysNoticeEnterprise notice);

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(SysNoticeEnterprise notice);


}
