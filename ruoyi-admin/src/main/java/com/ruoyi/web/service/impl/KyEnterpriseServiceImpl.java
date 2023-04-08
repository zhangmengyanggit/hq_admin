package com.ruoyi.web.service.impl;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import com.ruoyi.web.domain.KyEnterprise;
import com.ruoyi.web.domain.KyEnterpriseProjectDeclaration;
import com.ruoyi.web.domain.SysArea;
import com.ruoyi.web.domain.Tag;
import com.ruoyi.web.mapper.KyEnterpriseMapper;
import com.ruoyi.web.service.IKyEnterpriseProjectDeclarationService;
import com.ruoyi.web.service.IKyEnterpriseService;
import com.ruoyi.web.service.ISysAreaService;
import com.ruoyi.web.service.ITagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 企业Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-08
 */
@Service
public class KyEnterpriseServiceImpl implements IKyEnterpriseService {
    private static final Logger log = LoggerFactory.getLogger(KyEnterpriseServiceImpl.class);
    @Autowired
    private KyEnterpriseMapper kyEnterpriseMapper;
    @Autowired
    protected Validator validator;
    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private ITagService iTagService;
    @Autowired
    private ISysAreaService sysAreaService;
    @Autowired
    private IKyEnterpriseProjectDeclarationService iKyEnterpriseProjectDeclarationService;

    /**
     * 查询企业
     *
     * @param id 企业主键
     * @return 企业
     */
    @Override
    public KyEnterprise selectKyEnterpriseById(Long id) {
        return kyEnterpriseMapper.selectKyEnterpriseById(id);
    }

    /**
     * 查询企业列表
     *
     * @param kyEnterprise 企业
     * @return 企业
     */
    @Override
    public List<KyEnterprise> selectKyEnterpriseList(KyEnterprise kyEnterprise) {
        //发布时禁用，选中判断
        List<KyEnterprise>  kyEnterprises=   kyEnterpriseMapper.selectKyEnterpriseList(kyEnterprise);
        if(StringUtils.isNotNull(kyEnterprise.getOriginalpolicyId())) {
            for (KyEnterprise enterprise:kyEnterprises) {
                Map<String,Object> paramsMap=new HashMap<String,Object>();
                paramsMap.put("originalpolicyId",kyEnterprise.getOriginalpolicyId());
                paramsMap.put("enterpriseId",enterprise.getId());
                Long count=   iKyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationCountByParams(paramsMap);
                if (count>0){
                    enterprise.setDisabled(false);
                    enterprise.setSelected(true);
                }
            }
        }

        for (KyEnterprise enterprise:kyEnterprises) {
            if(ObjectUtils.isEmpty(enterprise.getBusinessTerm())&&ObjectUtils.isEmpty(enterprise.getBusinessTermStart())){
                enterprise.setBusinessTermStr("无固定期限");
            }else{
                if(ObjectUtils.isEmpty(enterprise.getBusinessTerm())){
                    enterprise.setBusinessTermStr(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,enterprise.getBusinessTermStart())+" 至 "+"无固定期限");
                }else  if(ObjectUtils.isEmpty(enterprise.getBusinessTermStart())){
                    enterprise.setBusinessTermStr("无固定期限"+" 至 "+DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,enterprise.getBusinessTerm()));
                }else {
                    enterprise.setBusinessTermStr(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,enterprise.getBusinessTermStart())+" 至 "+DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,enterprise.getBusinessTerm()));
                }


            }

        }
        return kyEnterprises;
    }

    /**
     * 新增企业
     *
     * @param kyEnterprise 企业
     * @return 结果
     */
    @Override
    @Transactional
    public int insertKyEnterprise(KyEnterprise kyEnterprise) {
        kyEnterprise.setCreateTime(DateUtils.getNowDate());
        return kyEnterpriseMapper.insertKyEnterprise(kyEnterprise);
    }

    /**
     * 修改企业
     *
     * @param kyEnterprise 企业
     * @return 结果
     */
    @Override
    @Transactional
    public int updateKyEnterprise(KyEnterprise kyEnterprise) {
        return kyEnterpriseMapper.updateKyEnterprise(kyEnterprise);
    }

    /**
     * 批量删除企业
     *
     * @param ids 需要删除的企业主键
     * @return 结果
     */
    @Override
    public int deleteKyEnterpriseByIds(Long[] ids) {
        return kyEnterpriseMapper.deleteKyEnterpriseByIds(ids);
    }

    /**
     * 删除企业信息
     *
     * @param id 企业主键
     * @return 结果
     */
    @Override
    public int deleteKyEnterpriseById(Long id) {
        return kyEnterpriseMapper.deleteKyEnterpriseById(id);
    }

    @Override
    @Transactional
    public String importEnterprise(List<KyEnterprise> enterpriseList, boolean updateSupport, String operName) {
        if (StringUtils.isNull(enterpriseList) || enterpriseList.size() == 0) {
            throw new ServiceException("导入企业数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for (KyEnterprise enterprise : enterpriseList) {
            if(StringUtils.isEmpty(enterprise.getName())){
                continue;
            }
            enterprise.setName(enterprise.getName().trim());
            //导入数据处理
            setingsKyEnterprise(enterprise);
            int isRunFail = 0;
            try {


                // 验证是否存在这个企业
                KyEnterprise enterprise1 = new KyEnterprise();
                enterprise1.setName(enterprise.getName());
                List<KyEnterprise> enterprises = kyEnterpriseMapper.selectKyEnterpriseList(enterprise1);


                if (enterprises.size() == 0) {
                    if (validateEnterpriseByEnterpriseNature(enterprise)) {
                        failureNum++;
                        isRunFail++;
                        failureMsg.append("<br/>" + failureNum + "、企业名称 " + enterprise.getName() + " 的数据不存在于系统字典管理中的企业性质或控股类型名称中");
                    } else if (validateEnterpriseByIndustryOneLevel(enterprise)) {
                        failureNum++;
                        isRunFail++;
                        failureMsg.append("<br/>" + failureNum + "、企业名称 " + enterprise.getName() + " 的数据不存在于系统字典管理中的产业类型标签中");
                    } else if (validateEnterpriseByIndustryTwoLevel(enterprise)) {
                        failureNum++;
                        isRunFail++;
                        failureMsg.append("<br/>" + failureNum + "、企业名称 " + enterprise.getName() + " 的数据不存在于系统字典管理中的行业标签中");
                    } else if (validateEnterpriseByTags(enterprise)) {
                        failureNum++;
                        isRunFail++;
                        failureMsg.append("<br/>" + failureNum + "、企业名称 " + enterprise.getName() + " 的数据不存在于标签中");
                    } else if (validateEnterpriseByStreet(enterprise)) {
                        failureNum++;
                        isRunFail++;
                        failureMsg.append("<br/>" + failureNum + "、企业名称 " + enterprise.getName() + " 的街道信息有误，查询不到当前街道信息");
                    } else {
                        BeanValidators.validateWithException(validator, enterprise);
                        insertKyEnterprise(enterprise);
                        successNum++;
                        successMsg.append("<br/>" + successNum + "、企业名称 " + enterprise.getName() + " 导入成功");
                    }
                } else if (updateSupport) {
                    if (validateEnterpriseByEnterpriseNature(enterprise)) {
                        failureNum++;
                        isRunFail++;
                        failureMsg.append("<br/>" + failureNum + "、企业名称 " + enterprise.getName() + " 的数据不存在于系统字典管理中的企业性质或控股类型名称中");
                    } else if (validateEnterpriseByIndustryOneLevel(enterprise)) {
                        failureNum++;
                        isRunFail++;
                        failureMsg.append("<br/>" + failureNum + "、企业名称 " + enterprise.getName() + " 的数据不存在于系统字典管理中的产业类型标签中");
                    } else if (validateEnterpriseByIndustryTwoLevel(enterprise)) {
                        failureNum++;
                        isRunFail++;
                        failureMsg.append("<br/>" + failureNum + "、企业名称 " + enterprise.getName() + " 的数据不存在于系统字典管理中的行业标签中");
                    } else if (validateEnterpriseByTags(enterprise)) {
                        failureNum++;
                        isRunFail++;
                        failureMsg.append("<br/>" + failureNum + "、企业名称 " + enterprise.getName() + " 的数据不存在于标签中");
                    } else if (validateEnterpriseByStreet(enterprise)) {
                        failureNum++;
                        isRunFail++;
                        failureMsg.append("<br/>" + failureNum + "、企业名称 " + enterprise.getName() + " 的街道信息有误，查询不到当前街道信息");
                    } else {
                        BeanValidators.validateWithException(validator, enterprise);
                        enterprise.setId(enterprises.get(0).getId());
                        this.updateKyEnterprise(enterprise);
                        successNum++;
                        successMsg.append("<br/>" + successNum + "、企业名称 " + enterprise.getName() + " 更新成功");
                    }
                } else {
                    failureNum++;
                    isRunFail++;
                    failureMsg.append("<br/>" + failureNum + "、企业名称 " + enterprise.getName() + " 已存在");
                }
            } catch (Exception e) {
                if (isRunFail == 0) {
                    failureNum++;
                }

                String msg = "<br/>" + failureNum + "、企业名称 " + enterprise.getName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    private void setingsKyEnterprise(KyEnterprise enterprise) {
        //营业期限处理
       if(StringUtils.isNotNull(enterprise.getBusinessTermStr())){
           if(!enterprise.getBusinessTermStr().trim().equals("无固定期限")){
               if(enterprise.getBusinessTermStr().indexOf("至")!=-1){
                   if(enterprise.getBusinessTermStr().indexOf("无固定期限")==-1){
                       String businessTerm=enterprise.getBusinessTermStr().substring(enterprise.getBusinessTermStr().indexOf("至")+1,enterprise.getBusinessTermStr().length()).trim();
                       enterprise.setBusinessTerm(DateUtils.parseDate(businessTerm));
                   }
                   String businessTermStr=enterprise.getBusinessTermStr().substring(0,enterprise.getBusinessTermStr().indexOf("至")).trim();
                   enterprise.setBusinessTermStart(DateUtils.parseDate(businessTermStr));
               }
           }
       }

    }

    @Override
    public List<Long> selectKyEnterpriseIds(KyEnterprise kyEnterprise) {
        return kyEnterpriseMapper.selectKyEnterpriseIds(kyEnterprise);
    }

    @Override
    public Long selectKyEnterpriseCount(KyEnterprise kyEnterprise) {
        return kyEnterpriseMapper.selectKyEnterpriseCount(kyEnterprise);
    }

    private boolean validateEnterpriseByIndustryTwoLevel(KyEnterprise enterprise) {
        {
            //校验是否存在于当前字典中
            SysDictData sysDictData = new SysDictData();
            sysDictData.setDictType("trade");
            List<SysDictData> sysDictDataList =DictUtils.getDictCache("trade");
            sysDictDataList = sysDictDataList.stream().filter(s -> s.getDictValue().equals(enterprise.getIndustryTwoLevel())).collect(Collectors.toList());
            if (sysDictDataList.size() == 0) {
                return true;
            }
            return false;
        }
    }

    private boolean validateEnterpriseByIndustryOneLevel(KyEnterprise enterprise) {
        {
            //校验是否存在于当前字典中
            List<SysDictData> sysDictDataList =DictUtils.getDictCache("applicable_industries");
            sysDictDataList = sysDictDataList.stream().filter(s -> s.getDictValue().equals(enterprise.getIndustryOneLevel())).collect(Collectors.toList());
            if (sysDictDataList.size() == 0) {
                return true;
            }
            return false;
        }
    }

    private boolean validateEnterpriseByStreet(KyEnterprise enterprise) {
        {
              if(StringUtils.isEmpty(enterprise.getRegistrationType())){
                  return false;
              }
            //校验当前街道是否正确查询到则插入省份，市，区信息
            SysArea sysAreaParams = new SysArea();
            sysAreaParams.setName(enterprise.getRegistrationType());//街道名称
            sysAreaParams.setType(4);
            SysArea sysArea = sysAreaService.selectBySysArea(sysAreaParams);
            if (sysArea == null) {
                return true;
            }
            enterprise.setRegistrationStreet(sysArea.getId());
            enterprise.setRegistrationRegion(sysArea.getPid());//区
            //查询区
            SysArea sysAreaRegion = sysAreaService.selectSysRegionByRegionId(sysArea.getPid());
            enterprise.setRegistrationCity(sysAreaRegion.getPid());
            //查询市
            SysArea sysAreaCity = sysAreaService.selectSysRegionByRegionId(sysAreaRegion.getPid());
            enterprise.setRegistrationProvince(sysAreaCity.getPid());
            return false;
        }
    }

    private boolean validateEnterpriseByTags(KyEnterprise enterprise) {
        if (StringUtils.isEmpty(enterprise.getTagIds())) {
            return true;
        }
        //校验标签集合是否存在于当前字典中
        List<Tag> tags = iTagService.selectTagByIds(enterprise.getTagIds());
        if (tags.size() == 0) {
            return true;
        }
        return false;
    }

    private boolean validateEnterpriseByEnterpriseNature(KyEnterprise enterprise) {
        //校验企业性质是否存在于当前字典中
        List<SysDictData> sysDictDataList = DictUtils.getDictCache("enterprise_nature");
        sysDictDataList = sysDictDataList.stream().filter(s -> s.getDictValue().equals(enterprise.getEnterpriseNature())).collect(Collectors.toList());
        if (sysDictDataList.size() == 0) {
            return true;
        }
        return false;
    }
}
