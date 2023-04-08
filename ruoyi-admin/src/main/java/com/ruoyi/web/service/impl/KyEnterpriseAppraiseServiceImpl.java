package com.ruoyi.web.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.web.domain.KyEnterpriseAppraise;
import com.ruoyi.web.mapper.KyEnterpriseAppraiseMapper;
import com.ruoyi.web.service.IKyEnterpriseAppraiseService;
import com.ruoyi.web.service.IKyEnterpriseProjectDeclarationService;
import com.ruoyi.web.vo.KyEnterpriseProjectDeclarationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 企业用户评价Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-29
 */
@Service
public class KyEnterpriseAppraiseServiceImpl implements IKyEnterpriseAppraiseService
{
    @Autowired
    private KyEnterpriseAppraiseMapper kyEnterpriseAppraiseMapper;
    @Autowired
    private IKyEnterpriseProjectDeclarationService kyEnterpriseProjectDeclarationService;
    /**
     * 查询企业用户评价
     * 
     * @param id 企业用户评价主键
     * @return 企业用户评价
     */
    @Override
    public KyEnterpriseAppraise selectKyEnterpriseAppraiseById(Long id)
    {
        return kyEnterpriseAppraiseMapper.selectKyEnterpriseAppraiseById(id);
    }

    /**
     * 查询企业用户评价列表
     * 
     * @param kyEnterpriseAppraise 企业用户评价
     * @return 企业用户评价
     */
    @Override
    public List<KyEnterpriseAppraise> selectKyEnterpriseAppraiseList(KyEnterpriseAppraise kyEnterpriseAppraise)
    {
        return kyEnterpriseAppraiseMapper.selectKyEnterpriseAppraiseList(kyEnterpriseAppraise);
    }

    /**
     * 新增企业用户评价
     * 
     * @param kyEnterpriseAppraise 企业用户评价
     * @return 结果
     */
    @Override
    public int insertKyEnterpriseAppraise(KyEnterpriseAppraise kyEnterpriseAppraise)
    {
        return kyEnterpriseAppraiseMapper.insertKyEnterpriseAppraise(kyEnterpriseAppraise);
    }

    /**
     * 修改企业用户评价
     * 
     * @param kyEnterpriseAppraise 企业用户评价
     * @return 结果
     */
    @Override
    public int updateKyEnterpriseAppraise(KyEnterpriseAppraise kyEnterpriseAppraise)
    {
        return kyEnterpriseAppraiseMapper.updateKyEnterpriseAppraise(kyEnterpriseAppraise);
    }

    /**
     * 批量删除企业用户评价
     * 
     * @param ids 需要删除的企业用户评价主键
     * @return 结果
     */
    @Override
    public int deleteKyEnterpriseAppraiseByIds(Long[] ids)
    {
        return kyEnterpriseAppraiseMapper.deleteKyEnterpriseAppraiseByIds(ids);
    }

    /**
     * 删除企业用户评价信息
     * 
     * @param id 企业用户评价主键
     * @return 结果
     */
    @Override
    public int deleteKyEnterpriseAppraiseById(Long id)
    {
        return kyEnterpriseAppraiseMapper.deleteKyEnterpriseAppraiseById(id);
    }

    @Override
    public Long selectKyEnterpriseAppraiseCountByParasm(Map<String, Object> paramsMapTwo) {
        return kyEnterpriseAppraiseMapper.selectKyEnterpriseAppraiseCountByParasm(paramsMapTwo);
    }



    @Override
    @Transactional
    public void insertEnterpriseAppraise(Long[] ids,Long auditStatus) {
        for (Long id : ids) {
            KyEnterpriseProjectDeclarationVo enterpriseProjectDeclarationVo = kyEnterpriseProjectDeclarationService.selectKyEnterpriseProjectDeclarationById(id);
            if (enterpriseProjectDeclarationVo == null) {
                continue;
            }
            /**
             * 插入评价数据（评价环节既审核状态）
             * */
            KyEnterpriseAppraise kyEnterpriseAppraise = new KyEnterpriseAppraise();
            kyEnterpriseAppraise.setEnterpriseId(enterpriseProjectDeclarationVo.getEnterpriseId());
            kyEnterpriseAppraise.setIrrigationDitch("pc端");
           // kyEnterpriseAppraise.setSysUserEnterpriseId(SecurityUtils.getUserId());
            kyEnterpriseAppraise.setEnterpriseProjectDeclarationId(enterpriseProjectDeclarationVo.getId());
            kyEnterpriseAppraise.setReplyDepartment(enterpriseProjectDeclarationVo.getPublishingDepartment());
            kyEnterpriseAppraise.setAuditStatus(auditStatus);
            insertKyEnterpriseAppraise(kyEnterpriseAppraise);
        }
    }
}
