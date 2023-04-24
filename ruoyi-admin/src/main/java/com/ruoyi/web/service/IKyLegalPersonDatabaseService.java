package com.ruoyi.web.service;

import com.ruoyi.web.domain.KyEnterprise;

/**
 * 法人库Service接口
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
public interface IKyLegalPersonDatabaseService
{
    /**
     * 查询企业法人库信息
     * 
     * @param socialUnifiedCreditCodeNumber 社会统一信用代码号
     * @return 企业
     */
    public KyEnterprise selectKyEnterpriseBySocialUnifiedCreditCodeNumber(String socialUnifiedCreditCodeNumber);
    /**
     * 企业数据同步区法人库中所有数据
     */
    public void synLegalPersonDatabaseAll();
}
