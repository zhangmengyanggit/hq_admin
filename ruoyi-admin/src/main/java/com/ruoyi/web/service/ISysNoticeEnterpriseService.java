package com.ruoyi.web.service;

import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.web.domain.SysNoticeEnterprise;

import java.util.List;

/**
 * 公告 服务层
 * 
 * @author ruoyi
 */
public interface ISysNoticeEnterpriseService
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
