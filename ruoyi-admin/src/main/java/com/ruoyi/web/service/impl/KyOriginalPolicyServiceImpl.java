package com.ruoyi.web.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.web.domain.KyEnterprise;
import com.ruoyi.web.domain.KyEnterpriseProjectDeclaration;
import com.ruoyi.web.domain.KyOriginalPolicy;
import com.ruoyi.web.domain.KyProjectDeclaration;
import com.ruoyi.web.mapper.KyOriginalPolicyMapper;
import com.ruoyi.web.service.IKyEnterpriseProjectDeclarationService;
import com.ruoyi.web.service.IKyEnterpriseService;
import com.ruoyi.web.service.IKyOriginalPolicyService;
import com.ruoyi.web.service.IKyProjectDeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 政策管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-11
 */
@Service
public class KyOriginalPolicyServiceImpl implements IKyOriginalPolicyService
{
    @Autowired
    private KyOriginalPolicyMapper kyOriginalPolicyMapper;
    @Autowired
    private IKyEnterpriseService kyEnterpriseService;
    @Autowired
    private IKyProjectDeclarationService iKyProjectDeclarationService;

    @Autowired
    private IKyEnterpriseProjectDeclarationService iKyEnterpriseProjectDeclarationService;
    /**
     * 查询政策管理
     * 
     * @param id 政策管理主键
     * @return 政策管理
     */
    @Override
    public KyOriginalPolicy selectKyOriginalPolicyById(Long id)
    {
        return kyOriginalPolicyMapper.selectKyOriginalPolicyById(id);
    }

    /**
     * 查询政策管理列表
     * 
     * @param kyOriginalPolicy 政策管理
     * @return 政策管理
     */
    @Override
    public List<KyOriginalPolicy> selectKyOriginalPolicyList(KyOriginalPolicy kyOriginalPolicy)
    {
        return kyOriginalPolicyMapper.selectKyOriginalPolicyList(kyOriginalPolicy);
    }

    /**
     * 新增政策管理
     * 
     * @param kyOriginalPolicy 政策管理
     * @return 结果
     */
    @Override
    public int insertKyOriginalPolicy(KyOriginalPolicy kyOriginalPolicy)
    {
        kyOriginalPolicy.setCreateTime(DateUtils.getNowDate());
        return kyOriginalPolicyMapper.insertKyOriginalPolicy(kyOriginalPolicy);
    }

    /**
     * 修改政策管理
     * 
     * @param kyOriginalPolicy 政策管理
     * @return 结果
     */
    @Override
    public int updateKyOriginalPolicy(KyOriginalPolicy kyOriginalPolicy)
    {
        return kyOriginalPolicyMapper.updateKyOriginalPolicy(kyOriginalPolicy);
    }

    /**
     * 批量删除政策管理
     * 
     * @param ids 需要删除的政策管理主键
     * @return 结果
     */
    @Override
    public int deleteKyOriginalPolicyByIds(Long[] ids)
    {
        return kyOriginalPolicyMapper.deleteKyOriginalPolicyByIds(ids);
    }

    /**
     * 删除政策管理信息
     * 
     * @param id 政策管理主键
     * @return 结果
     */
    @Override
    public int deleteKyOriginalPolicyById(Long id)
    {
        return kyOriginalPolicyMapper.deleteKyOriginalPolicyById(id);
    }

    @Override
    @Transactional
    public AjaxResult publishToAllEnterprise(Long id) {
        //查询政策信息
        KyOriginalPolicy kyOriginalPolicy= selectKyOriginalPolicyById(id);
         //判断当前政策是否有效
        if (kyOriginalPolicy.getValidStatus().equals(1l)){
            return AjaxResult.error("当前政策已无效");
        }
        //判断当前政策是否过期
        if (kyOriginalPolicy.getValidDate().isBefore(LocalDate.now())){
            return AjaxResult.error("当前政策已过期");
        }
        //查询所有符合条件企业
        KyEnterprise kyEnterprise=new KyEnterprise();
        kyEnterprise.setTagIds(kyOriginalPolicy.getTagIds());
        //kyEnterprise.setBusinessTerm(new Date());
        kyEnterprise.setRegistrationRegion(19973);//猇亭写死
        List<Long>  enterpriseIdList=  kyEnterpriseService.selectKyEnterpriseIds(kyEnterprise);
        if(enterpriseIdList.size()==0){
            return AjaxResult.error("符合条件的企业不存在");
        }
        if (!kyOriginalPolicy.getPublishStatus().equals(2l)){
            //更改政策表状态
            kyOriginalPolicy.setPublishStatus(2l);
            kyOriginalPolicy.setPublisher(SecurityUtils.getUsername());
            kyOriginalPolicy.setPublishTime(new Date());
            updateKyOriginalPolicy(kyOriginalPolicy);
        }
        KyProjectDeclaration kyProjectDeclaration=new KyProjectDeclaration();
        kyProjectDeclaration.setOriginalPolicyId(id);
        List<KyProjectDeclaration>  kyProjectDeclarations=  iKyProjectDeclarationService.selectKyProjectDeclarationList(kyProjectDeclaration);
        if (kyProjectDeclarations.size()==0){
            //插入项目申报表
            iKyProjectDeclarationService.insertKyProjectDeclaration(kyProjectDeclaration);
        }else{
            kyProjectDeclaration.setId(kyProjectDeclarations.get(0).getId());
        }

        for (Long enterpriseId:enterpriseIdList) {
            KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration=new KyEnterpriseProjectDeclaration();
            kyEnterpriseProjectDeclaration.setProjectDeclarationId(kyProjectDeclaration.getId());
            kyEnterpriseProjectDeclaration.setEnterpriseId(enterpriseId);
            List<KyEnterpriseProjectDeclaration>  kyEnterpriseProjectDeclarations=   iKyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationList(kyEnterpriseProjectDeclaration);
            if (kyEnterpriseProjectDeclarations.size()==0){
                //插入企业申请项目表
                iKyEnterpriseProjectDeclarationService.insertKyEnterpriseProjectDeclaration(kyEnterpriseProjectDeclaration);
                //异步发消息通知企业

            }
        }
        return AjaxResult.success("发布成功");
    }

    @Override
    @Transactional
    public AjaxResult publishByenterpriseAndIds(String ids, Long originalpolicyId) {
        //查询政策信息
        KyOriginalPolicy kyOriginalPolicy= selectKyOriginalPolicyById(originalpolicyId);
        //判断当前政策是否有效
        if (kyOriginalPolicy.getValidStatus().equals(1l)){
            return AjaxResult.error("当前政策已无效");
        }
        //判断当前政策是否过期
        if (kyOriginalPolicy.getValidDate().isBefore(LocalDate.now())){
            return AjaxResult.error("当前政策已过期");
        }
        //查询所有符合条件企业
        KyEnterprise kyEnterprise=new KyEnterprise();
        kyEnterprise.setTagIds(kyOriginalPolicy.getTagIds());
        //kyEnterprise.setBusinessTerm(new Date());
        kyEnterprise.setRegistrationRegion(19973);//猇亭写死

        long[] enterpriseIdArr = Arrays.asList(ids.split(",")).stream().mapToLong(Long::parseLong).toArray();

        if(enterpriseIdArr.length==0){
            return AjaxResult.error("符合条件的企业不存在");
        }
        if (!kyOriginalPolicy.getPublishStatus().equals(2l)){
            //更改政策表状态
            kyOriginalPolicy.setPublishStatus(2l);
            kyOriginalPolicy.setPublisher(SecurityUtils.getUsername());
            kyOriginalPolicy.setPublishTime(new Date());
            updateKyOriginalPolicy(kyOriginalPolicy);
        }
        KyProjectDeclaration kyProjectDeclaration=new KyProjectDeclaration();
        kyProjectDeclaration.setOriginalPolicyId(originalpolicyId);
        List<KyProjectDeclaration>  kyProjectDeclarations=  iKyProjectDeclarationService.selectKyProjectDeclarationList(kyProjectDeclaration);
        if (kyProjectDeclarations.size()==0){
            //插入项目申报表
            iKyProjectDeclarationService.insertKyProjectDeclaration(kyProjectDeclaration);
        }else{
            kyProjectDeclaration.setId(kyProjectDeclarations.get(0).getId());
        }

        for (Long enterpriseId:enterpriseIdArr) {
            KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration=new KyEnterpriseProjectDeclaration();
            kyEnterpriseProjectDeclaration.setProjectDeclarationId(kyProjectDeclaration.getId());
            kyEnterpriseProjectDeclaration.setEnterpriseId(enterpriseId);
            List<KyEnterpriseProjectDeclaration>  kyEnterpriseProjectDeclarations=   iKyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationList(kyEnterpriseProjectDeclaration);
            if (kyEnterpriseProjectDeclarations.size()==0){
                //插入企业申请项目表
                iKyEnterpriseProjectDeclarationService.insertKyEnterpriseProjectDeclaration(kyEnterpriseProjectDeclaration);
                //异步发消息通知企业

            }
        }
        return AjaxResult.success("发布成功");
    }
}
