package com.ruoyi.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.LegalPersonDatabaseUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.web.domain.KyEnterprise;
import com.ruoyi.web.domain.SysArea;
import com.ruoyi.web.domain.Tag;
import com.ruoyi.web.mapper.KyEnterpriseMapper;
import com.ruoyi.web.model.KyLegalPersonDatabase;
import com.ruoyi.web.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.validation.Validator;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 法人库Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-08
 */
@Service
public class KyLegalPersonDatabaseServiceImpl implements IKyLegalPersonDatabaseService {
    private static final Logger log = LoggerFactory.getLogger(KyLegalPersonDatabaseServiceImpl.class);
    @Autowired
    private IKyEnterpriseService iKyEnterpriseService;
    @Autowired
    private ISysDictDataService dictDataService;

    @Override
    @Transactional
    public KyEnterprise selectKyEnterpriseBySocialUnifiedCreditCodeNumber(String socialUnifiedCreditCodeNumber) {
        KyEnterprise enterprise = new KyEnterprise();
        RedisCache redisCache = SpringUtils.getBean(RedisCache.class);
        List<KyLegalPersonDatabase> kyLegalPersonDatabaseList = Collections.EMPTY_LIST;
        String result = null;
        if (redisCache.hasKey(CacheConstants.LEGAL_PERSON_DATABASE)) {
            kyLegalPersonDatabaseList = redisCache.getCacheObject(CacheConstants.LEGAL_PERSON_DATABASE);
            log.info("kyLegalPersonDatabaseList:size={}" + kyLegalPersonDatabaseList.size());
        } else {
            result = LegalPersonDatabaseUtils.getLegalPersonDatabase();
            if (result != null) {
                kyLegalPersonDatabaseList = JSONObject.parseArray(result, KyLegalPersonDatabase.class);
                //放入缓存
                SpringUtils.getBean(RedisCache.class).setCacheObject(CacheConstants.LEGAL_PERSON_DATABASE, kyLegalPersonDatabaseList, 1, TimeUnit.DAYS);
            }
        }
        for (KyLegalPersonDatabase legalPersonDatabase : kyLegalPersonDatabaseList) {
            if (socialUnifiedCreditCodeNumber.equals(legalPersonDatabase.getShxy_code())) {
                //行业
                getDictData(enterprise, legalPersonDatabase, "trade");
                //企业性质
                getDictData(enterprise, legalPersonDatabase, "enterprise_nature");
                //2022年07月06日-》2013-11-05
                try {
                    enterprise.setCreateTime(DateUtils.parseDate(legalPersonDatabase.getCj_date().replace("年", "-").replace("月", "-").replace("日", ""), DateUtils.YYYY_MM_DD));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                enterprise.setNatureOfBusiness(legalPersonDatabase.getJyfw());
                enterprise.setStatus(legalPersonDatabase.getQy_stutas());
                enterprise.setJuridicalPerson(legalPersonDatabase.getFddbr_name());
                enterprise.setRegisteredCapital(legalPersonDatabase.getZczb());
                enterprise.setName(legalPersonDatabase.getQy_name());
                enterprise.setRegistrationAuthority(legalPersonDatabase.getDjjg());
                break;
            }
        }

        return enterprise;
    }

    private void getDictData(KyEnterprise enterprise, KyLegalPersonDatabase legalPersonDatabase, String dictType) {
        if (StringUtils.isNotEmpty(legalPersonDatabase.getHy_code())) {
            String dictValue = null;
            if (dictType.equals("trade")) {
                //行业
                if (StringUtils.isEmpty(legalPersonDatabase.getHy_code())) {
                    return;
                }
                dictValue = DictUtils.getDictValue("trade", legalPersonDatabase.getHy_code().trim());
            } else if (dictType.equals("enterprise_nature")) {
                //企业性质
                if (StringUtils.isEmpty(legalPersonDatabase.getQy_type())) {
                    return;
                }
                dictValue = DictUtils.getDictValue("enterprise_nature", legalPersonDatabase.getQy_type().trim());
            }
            if (StringUtils.isEmpty(dictValue)) {
                //创建行业
                SysDictData dict = new SysDictData();
                dict.setDictType(dictType);
                dict.setStatus("0");
                dict.setDictLabel(legalPersonDatabase.getHy_code().trim());
                //获取最后的value
                dictValue = dictDataService.getLastDictValueByDictType(dictType);
                log.info("dictValue:{}:{}", dictType, dictValue);
                dict.setDictValue((Long.valueOf(dictValue) + 1) + "");
                dict.setDictSort(Long.valueOf(dictValue));
                dict.setCreateTime(new Date());
                dict.setCreateBy("同步法人库自动创建");
                dictDataService.insertDictData(dict);
                log.info("新增一条{}字典信息。字典编码为：{}", dictType, dict.getDictCode());
            }
            if (dictType.equals("trade")) {
                enterprise.setIndustryTwoLevel(dictValue);
            } else if (dictType.equals("enterprise_nature")) {
                enterprise.setEnterpriseNature(dictValue);
            }
        }
    }

    @Override
    @Transactional
    public void synLegalPersonDatabaseAll() {
        //1查询所有法人库信息
        String result = LegalPersonDatabaseUtils.getLegalPersonDatabase();
        if (result != null) {
            List<KyLegalPersonDatabase> kyLegalPersonDatabaseList = JSONObject.parseArray(result, KyLegalPersonDatabase.class);
            //放入缓存
            SpringUtils.getBean(RedisCache.class).setCacheObject(CacheConstants.LEGAL_PERSON_DATABASE, kyLegalPersonDatabaseList, 1, TimeUnit.DAYS);
            //查询所有企业
            List<KyEnterprise> kyEnterpriseList= iKyEnterpriseService.selectKyEnterpriseList(new KyEnterprise());
            List<KyEnterprise> enterpriseList = new LinkedList<>();
            for (KyLegalPersonDatabase legalPersonDatabase : kyLegalPersonDatabaseList) {
                if (StringUtils.isEmpty(legalPersonDatabase.getShxy_code())) {
                    continue;
                }
                for (KyEnterprise kyEnterprise:kyEnterpriseList) {
                    if (kyEnterprise.getSocialUnifiedCreditCodeNumber().equals(legalPersonDatabase.getShxy_code())){
                        KyEnterprise enterprise = new KyEnterprise();
                        if(StringUtils.isEmpty(kyEnterprise.getIndustryTwoLevel())){
                            //行业
                            getDictData(enterprise, legalPersonDatabase, "trade");
                        }
                        if(StringUtils.isEmpty(kyEnterprise.getEnterpriseNature())){
                            //企业性质
                            getDictData(enterprise, legalPersonDatabase, "enterprise_nature");
                        }
                        try {
                            enterprise.setCreateTime(DateUtils.parseDate(legalPersonDatabase.getCj_date().replace("年", "-").replace("月", "-").replace("日", ""), DateUtils.YYYY_MM_DD));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        enterprise.setNatureOfBusiness(StringUtils.isEmpty(kyEnterprise.getNatureOfBusiness())?legalPersonDatabase.getJyfw():null);
                        enterprise.setStatus(legalPersonDatabase.getQy_stutas());
                        enterprise.setJuridicalPerson(StringUtils.isEmpty(kyEnterprise.getJuridicalPerson())?legalPersonDatabase.getFddbr_name():null);
                        enterprise.setRegisteredCapital(StringUtils.isEmpty(kyEnterprise.getRegisteredCapital())?legalPersonDatabase.getZczb():null);
                        enterprise.setName(StringUtils.isEmpty(kyEnterprise.getName())?legalPersonDatabase.getQy_name():null);
                        enterprise.setRegistrationAuthority(StringUtils.isEmpty(kyEnterprise.getRegistrationAuthority())?legalPersonDatabase.getDjjg():null);
                        enterprise.setSocialUnifiedCreditCodeNumber(legalPersonDatabase.getShxy_code());
                        enterpriseList.add(enterprise);
                        break;
                    }
                }
            }
            //2同步到企业根据社会统一信用代码号匹配
            if (enterpriseList.size() > 0) {
                 iKyEnterpriseService.batchUpdateEnterprise(enterpriseList);
                log.info("共更新{}条法人库数据", enterpriseList.size());
            }
        }
    }

    public static void main(String[] args) {
        try {
            Date a = DateUtils.parseDate("2022-07-06", DateUtils.YYYY_MM_DD);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
