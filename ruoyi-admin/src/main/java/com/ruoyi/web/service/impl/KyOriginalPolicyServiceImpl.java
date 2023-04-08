package com.ruoyi.web.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.sign.Md5Utils;
import com.ruoyi.web.domain.*;
import com.ruoyi.web.mapper.KyOriginalPolicyMapper;
import com.ruoyi.web.service.*;
import org.apache.commons.lang3.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 政策管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-11
 */
@Service
public class KyOriginalPolicyServiceImpl implements IKyOriginalPolicyService {
    @Autowired
    private KyOriginalPolicyMapper kyOriginalPolicyMapper;
    @Autowired
    private IKyEnterpriseService kyEnterpriseService;
    @Autowired
    private IKyProjectDeclarationService iKyProjectDeclarationService;
    @Autowired
    private IKyEnterpriseProjectDeclarationService iKyEnterpriseProjectDeclarationService;
    @Autowired
    private ISysUserEnterpriseService sysUserEnterpriseService;
    @Autowired
    private ISysNoticeEnterpriseService sysNoticeEnterpriseService;
    @Autowired
    private IKyEnterpriseAppraiseService enterpriseAppraiseService;


    //private static ThreadFactory addPhoneNoticeThreadFactory = new ThreadFactoryBuilder().setNameFormat("addPhoneNoticeThreadFactory").build();
    private  ExecutorService addPhoneNoticeExcutor = new ThreadPoolExecutor(5, 10,
            1L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(10),
            r -> new Thread(r, "addPhoneNoticeThreadFactory" + r.hashCode()),
            new ThreadPoolExecutor.AbortPolicy());

    /**
     * 查询政策管理
     *
     * @param id 政策管理主键
     * @return 政策管理
     */
    @Override
    public KyOriginalPolicy selectKyOriginalPolicyById(Long id) {
        return kyOriginalPolicyMapper.selectKyOriginalPolicyById(id);
    }

    /**
     * 查询政策管理列表
     *
     * @param kyOriginalPolicy 政策管理
     * @return 政策管理
     */
    @Override
    public List<KyOriginalPolicy> selectKyOriginalPolicyList(KyOriginalPolicy kyOriginalPolicy) {
        return kyOriginalPolicyMapper.selectKyOriginalPolicyList(kyOriginalPolicy);
    }

    /**
     * 新增政策管理
     *
     * @param kyOriginalPolicy 政策管理
     * @return 结果
     */
    @Override
    public int insertKyOriginalPolicy(KyOriginalPolicy kyOriginalPolicy) {
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
    public int updateKyOriginalPolicy(KyOriginalPolicy kyOriginalPolicy) {
        return kyOriginalPolicyMapper.updateKyOriginalPolicy(kyOriginalPolicy);
    }

    /**
     * 批量删除政策管理
     *
     * @param ids 需要删除的政策管理主键
     * @return 结果
     */
    @Override
    public int deleteKyOriginalPolicyByIds(Long[] ids) {
        return kyOriginalPolicyMapper.deleteKyOriginalPolicyByIds(ids);
    }

    /**
     * 删除政策管理信息
     *
     * @param id 政策管理主键
     * @return 结果
     */
    @Override
    public int deleteKyOriginalPolicyById(Long id) {
        return kyOriginalPolicyMapper.deleteKyOriginalPolicyById(id);
    }

    @Override
    @Transactional
    public AjaxResult publishToAllEnterprise(Long id) {
        String userName = SecurityUtils.getUsername();
        //查询政策信息
        KyOriginalPolicy kyOriginalPolicy = selectKyOriginalPolicyById(id);
        //判断当前政策是否有效
        if (kyOriginalPolicy.getValidStatus().equals(1l)) {
            return AjaxResult.error("当前政策已无效");
        }
        //判断当前政策是否过期
        if (kyOriginalPolicy.getValidDate().isBefore(LocalDate.now())) {
            return AjaxResult.error("当前政策已过期");
        }
        //查询所有符合条件企业
        KyEnterprise kyEnterprise = new KyEnterprise();
        kyEnterprise.setTagIds(kyOriginalPolicy.getTagIds());
        //kyEnterprise.setBusinessTerm(new Date());
        kyEnterprise.setRegistrationRegion(19973);//猇亭写死
        List<Long> enterpriseIdList = kyEnterpriseService.selectKyEnterpriseIds(kyEnterprise);
        if (enterpriseIdList.size() == 0) {
            return AjaxResult.error("符合条件的企业不存在");
        }
        if (!kyOriginalPolicy.getPublishStatus().equals(2l)) {
            //更改政策表状态
            kyOriginalPolicy.setPublishStatus(2l);
            kyOriginalPolicy.setPublisher(SecurityUtils.getUsername());
            kyOriginalPolicy.setPublishTime(new Date());
            updateKyOriginalPolicy(kyOriginalPolicy);
        }
        KyProjectDeclaration kyProjectDeclaration = new KyProjectDeclaration();
        kyProjectDeclaration.setOriginalPolicyId(id);
        List<KyProjectDeclaration> kyProjectDeclarations = iKyProjectDeclarationService.selectKyProjectDeclarationList(kyProjectDeclaration);
        if (kyProjectDeclarations.size() == 0) {
            //插入项目申报表
            iKyProjectDeclarationService.insertKyProjectDeclaration(kyProjectDeclaration);
        } else {
            kyProjectDeclaration.setId(kyProjectDeclarations.get(0).getId());
        }

        for (Long enterpriseId : enterpriseIdList) {
            //根据企业id查询企业信息
            KyEnterprise enterprise = kyEnterpriseService.selectKyEnterpriseById(enterpriseId);
            if (enterprise != null) {
                //发送消息到企业前端
                Long sysUserEnterpriseId = sysUserEnterpriseService.insertSysUserEnterpriseByEnterprise(enterprise);

                KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration = new KyEnterpriseProjectDeclaration();
                kyEnterpriseProjectDeclaration.setProjectDeclarationId(kyProjectDeclaration.getId());
                kyEnterpriseProjectDeclaration.setEnterpriseId(enterpriseId);
                List<KyEnterpriseProjectDeclaration> kyEnterpriseProjectDeclarations = iKyEnterpriseProjectDeclarationService.selectList(kyEnterpriseProjectDeclaration);
                if (kyEnterpriseProjectDeclarations.size() == 0) {
                    /**
                     * 发送消息到企业前端
                     * */
                    SysNoticeEnterprise notice = new SysNoticeEnterprise();
                    notice.setNoticeContent("温馨提醒:政策标题为[" + kyOriginalPolicy.getTittle() + "]的政策发布了，请去政策申请申请当前惠企政策");
                    notice.setNoticeTitle("政策发布通知");
                    notice.setNoticeType("1");
                    notice.setCreateBy(userName);
                    notice.setCreateTime(new Date());
                    notice.setUserId(sysUserEnterpriseId);
                    sysNoticeEnterpriseService.insertNotice(notice);

                  /**
                   * 插入企业申请项目表
                   * */
                    kyEnterpriseProjectDeclaration.setCreateTime(new Date());
                    kyEnterpriseProjectDeclaration.setReviewer(SecurityUtils.getUsername());
                    kyEnterpriseProjectDeclaration.setReviewerPhone(SecurityUtils.getLoginUser().getUser().getPhonenumber());
                    //申报联系人
                    kyEnterpriseProjectDeclaration.setDeclarationContact(enterprise.getLinkman());
                    //申报联系人电话
                    kyEnterpriseProjectDeclaration.setContactNumber(enterprise.getLinkmanPhone());
                    iKyEnterpriseProjectDeclarationService.insertKyEnterpriseProjectDeclaration(kyEnterpriseProjectDeclaration);
                    /**
                     * 插入评价数据（评价环节既审核状态）
                     * */
                       //查询审核状态

                        KyEnterpriseAppraise kyEnterpriseAppraise=new KyEnterpriseAppraise();
                        kyEnterpriseAppraise.setEnterpriseId(enterprise.getId());
                        kyEnterpriseAppraise.setIrrigationDitch("pc端");
                        kyEnterpriseAppraise.setSysUserEnterpriseId(sysUserEnterpriseId);
                        kyEnterpriseAppraise.setEnterpriseProjectDeclarationId(kyEnterpriseProjectDeclaration.getId());
                        kyEnterpriseAppraise.setReplyDepartment(Long.valueOf(kyOriginalPolicy.getPublishingDepartment()));
                        kyEnterpriseAppraise.setAuditStatus(0l);
                        enterpriseAppraiseService.insertKyEnterpriseAppraise(kyEnterpriseAppraise);


                    /**
                     * 异步发消息通知企业
                     * */
                    // this.addPhoneNoticeExcutor = Executors.newFixedThreadPool(10, r -> new Thread(r, "addPhoneNoticeThreads-" + r.hashCode()));
                    addPhoneNoticeExcutor.execute(
                            new Runnable() {
                               @Override
                                public void run() {
                                    //保存当前政策的解密后密文
                                    byte[] mdfHashBytes= Md5Utils.md5(kyOriginalPolicy.getId().toString());
                                    KyOriginalPolicy originalPolicy=new KyOriginalPolicy();
                                    originalPolicy.setMd5Hash(Md5Utils.toHex(mdfHashBytes));
                                    originalPolicy.setId(kyOriginalPolicy.getId());
                                    updateKyOriginalPolicy(originalPolicy);

                                   //替换当前文件为带签章的文件，必须确保为pdf文件
                                   if(StringUtils.isNotNull(kyOriginalPolicy.getMeansUrl())){
                                       String[] urlArr = kyOriginalPolicy.getMeansUrl().split(",");
                                       String urlPdfAdmin = null;
                                       for (String url : urlArr) {
                                           if (url.endsWith(".pdf")) {
                                               urlPdfAdmin = url.replace("/profile", RuoYiConfig.getProfile());
                                               break;
                                           }
                                       }
                                       if (urlPdfAdmin == null) {
                                           throw new ServiceException("找不到当前政策文件");
                                       }
                                       String urlOfd = urlPdfAdmin.replace(".pdf", ".ofd");
                                       OfficialSealUtils.getOfficialSeal(urlPdfAdmin,urlOfd);
                                   }
                                      //发送短信
                                    String msg="您所在企业可以申请惠企业务,请复制链接进行查看:"+ RuoYiConfig.getSeverWebUrl() +"/originalpolicyView?id="+ originalPolicy.getMd5Hash() +"【猇亭区商务局】";
                                    LinkSMS.sendMSM(enterprise.getLinkmanPhone(),msg);
                               }
                            }

                );
              }
            }
        }
        //关闭线程池
       // Threads.shutdownAndAwaitTermination(addPhoneNoticeExcutor);
        return AjaxResult.success("发布成功");
    }

    @Override
    @Transactional
    public AjaxResult publishByenterpriseAndIds(String ids, Long originalpolicyId) {
        String userName = SecurityUtils.getUsername();
        //查询政策信息
        KyOriginalPolicy kyOriginalPolicy = selectKyOriginalPolicyById(originalpolicyId);
        //判断当前政策是否有效
        if (kyOriginalPolicy.getValidStatus().equals(1l)) {
            return AjaxResult.error("当前政策已无效");
        }
        //判断当前政策是否过期
        if (kyOriginalPolicy.getValidDate().isBefore(LocalDate.now())) {
            return AjaxResult.error("当前政策已过期");
        }

        long[] enterpriseIdArr = Arrays.asList(ids.split(",")).stream().mapToLong(Long::parseLong).toArray();

        if (enterpriseIdArr.length == 0) {
            return AjaxResult.error("符合条件的企业不存在");
        }
        if (!kyOriginalPolicy.getPublishStatus().equals(2l)) {
            //更改政策表状态
            kyOriginalPolicy.setPublishStatus(2l);
            kyOriginalPolicy.setPublisher(SecurityUtils.getUsername());
            kyOriginalPolicy.setPublishTime(new Date());
            updateKyOriginalPolicy(kyOriginalPolicy);
        }
        KyProjectDeclaration kyProjectDeclaration = new KyProjectDeclaration();
        kyProjectDeclaration.setOriginalPolicyId(originalpolicyId);
        List<KyProjectDeclaration> kyProjectDeclarations = iKyProjectDeclarationService.selectKyProjectDeclarationList(kyProjectDeclaration);
        if (kyProjectDeclarations.size() == 0) {
            //插入项目申报表
            iKyProjectDeclarationService.insertKyProjectDeclaration(kyProjectDeclaration);
        } else {
            kyProjectDeclaration.setId(kyProjectDeclarations.get(0).getId());
        }

        for (Long enterpriseId : enterpriseIdArr) {

            //根据企业id查询企业信息
            KyEnterprise enterprise = kyEnterpriseService.selectKyEnterpriseById(enterpriseId);
            if (enterprise != null) {
                Long  sysUserEnterpriseId = sysUserEnterpriseService.insertSysUserEnterpriseByEnterprise(enterprise);

                KyEnterpriseProjectDeclaration kyEnterpriseProjectDeclaration = new KyEnterpriseProjectDeclaration();
                kyEnterpriseProjectDeclaration.setProjectDeclarationId(kyProjectDeclaration.getId());
                kyEnterpriseProjectDeclaration.setEnterpriseId(enterpriseId);
                List<KyEnterpriseProjectDeclaration> kyEnterpriseProjectDeclarations = iKyEnterpriseProjectDeclarationService.selectList(kyEnterpriseProjectDeclaration);
                if (kyEnterpriseProjectDeclarations.size() == 0) {
                  /**
                   * 插入企业申请项目表
                   * */
                    kyEnterpriseProjectDeclaration.setCreateTime(new Date());
                    kyEnterpriseProjectDeclaration.setReviewer(SecurityUtils.getUsername());
                    kyEnterpriseProjectDeclaration.setReviewerPhone(SecurityUtils.getLoginUser().getUser().getPhonenumber());
                    //申报联系人
                    kyEnterpriseProjectDeclaration.setDeclarationContact(enterprise.getLinkman());
                    //申报联系人电话
                    kyEnterpriseProjectDeclaration.setContactNumber(enterprise.getLinkmanPhone());
                    iKyEnterpriseProjectDeclarationService.insertKyEnterpriseProjectDeclaration(kyEnterpriseProjectDeclaration);

                   /**
                    * 发送消息到企业前端
                    * */
                    SysNoticeEnterprise notice = new SysNoticeEnterprise();
                    notice.setNoticeContent("温馨提醒:政策标题为[" + kyOriginalPolicy.getTittle() + "]的政策发布了，请去政策申请申请当前惠企政策");
                    notice.setNoticeTitle("政策发布通知");
                    notice.setNoticeType("1");
                    notice.setCreateBy(userName);
                    notice.setCreateTime(new Date());
                    notice.setUserId(sysUserEnterpriseId);
                    sysNoticeEnterpriseService.insertNotice(notice);
                    /**
                     * 插入评价数据（评价环节既审核状态）
                     * */

                        KyEnterpriseAppraise kyEnterpriseAppraise=new KyEnterpriseAppraise();
                        kyEnterpriseAppraise.setEnterpriseId(enterpriseId);
                        kyEnterpriseAppraise.setIrrigationDitch("pc端");
                        kyEnterpriseAppraise.setSysUserEnterpriseId(sysUserEnterpriseId);
                        kyEnterpriseAppraise.setEnterpriseProjectDeclarationId(kyEnterpriseProjectDeclaration.getId());
                        kyEnterpriseAppraise.setReplyDepartment(Long.valueOf(kyOriginalPolicy.getPublishingDepartment()));
                        kyEnterpriseAppraise.setAuditStatus(0l);
                        enterpriseAppraiseService.insertKyEnterpriseAppraise(kyEnterpriseAppraise);



                    /**
                     * 异步发消息通知企业
                     * */
                    //this.addPhoneNoticeExcutor = Executors.newFixedThreadPool(10, r -> new Thread(r, "addPhoneNoticeThreads-" + r.hashCode()));
                   addPhoneNoticeExcutor.execute(
                         new Runnable() {
                               @Override
                                public void run() {
                                    //保存当前政策的解密后密文
                                    byte[] mdfHashBytes= Md5Utils.md5(kyOriginalPolicy.getId().toString());
                                    KyOriginalPolicy originalPolicy=new KyOriginalPolicy();
                                    originalPolicy.setMd5Hash(Md5Utils.toHex(mdfHashBytes));
                                    originalPolicy.setId(kyOriginalPolicy.getId());
                                    updateKyOriginalPolicy(originalPolicy);

                                   //替换当前文件为带签章的文件，必须确保为pdf文件
                                   if(StringUtils.isNotNull(kyOriginalPolicy.getMeansUrl())){
                                       String[] urlArr = kyOriginalPolicy.getMeansUrl().split(",");
                                       String urlPdfAdmin = null;
                                       for (String url : urlArr) {
                                           if (url.endsWith(".pdf")) {
                                               urlPdfAdmin = url.replace("/profile", RuoYiConfig.getProfile());
                                               break;
                                           }
                                       }
                                       if (urlPdfAdmin == null) {
                                           throw new ServiceException("找不到当前政策文件");
                                       }
                                       String urlOfd = urlPdfAdmin.replace(".pdf", ".ofd");
                                       OfficialSealUtils.getOfficialSeal(urlPdfAdmin,urlOfd);
                                   }

                                   //发送短信
                                    String msg="您所在企业可以申请惠企业务,请复制链接进行查看:"+RuoYiConfig.getSeverWebUrl()+"/originalpolicyView?id="+ originalPolicy.getMd5Hash() +"【猇亭区商务局】";
                                    LinkSMS.sendMSM(enterprise.getLinkmanPhone(),msg);
                              }
                           }

                   );
                }

            }
        }
        //关闭线程池
       // Threads.shutdownAndAwaitTermination(addPhoneNoticeExcutor);

        return AjaxResult.success("发布成功");
    }

    @Override
    public Long selectKyOriginalPolicyCount(KyOriginalPolicy originalPolicy) {
        return kyOriginalPolicyMapper.selectKyOriginalPolicyCount(originalPolicy);
    }

    @Override
    public Long selectCountByParams(Map<String, Object> paramsMap) {
        return kyOriginalPolicyMapper.selectCountByParams(paramsMap);
    }


}
