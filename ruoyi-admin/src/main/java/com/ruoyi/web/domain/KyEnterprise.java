package com.ruoyi.web.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * 企业对象 ky_enterprise
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
public class KyEnterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String name;

    /** 注册企业地址 */
    @Excel(name = "注册企业地址")
    private String address;

    /** 企业法人 */
    @Excel(name = "企业法人",prompt="企业法人不能为空")
    private String juridicalPerson;

    /** 企业法人电话 */
    @Excel(name = "企业法人电话")
    private String juridicalPersonPhone;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd",height = 28)
    private Date registrationTime;

    /** 注册省份 */
        private Integer registrationProvince;

    /** 注册市 */
    private Integer registrationCity;

    /** 注册区 */
    private Integer registrationRegion;

    /** 经营地址 */
    @Excel(name = "经营地址")
    private String manageAddress;

    /** 社会统一信用代码号 */
    @Excel(name = "社会统一信用代码号",width = 30)
    private String socialUnifiedCreditCodeNumber;

    /** 企业性质或控股类型 */
    @Excel(name = "企业性质或控股类型", dictType = "enterprise_nature",prompt = "请填写系统字典管理中的企业性质或控股类型名称")
    private String enterpriseNature;

    /** 注册街道（用于导入数据） */
    @Excel(name = "注册街道")
    private String registrationType;

    /** 资产总额区间：1. 0---50万，2. 50---200万,3.200--500万，4.500--1000万，5.1000万--1亿，6.1亿以上 */
    @Excel(name = "资产总额区间",readConverterExp = "1=0到50万,2=50到200万,3=200到500万,4=500到1000万,5=1000万到1亿,6=1亿以上",combo = {"0到50万","50到200万","200到500万","500到1000万","1000万到1亿","1亿以上"})
    private String totalAssetsRange;

    /** 注册资本(万元) */
    @Excel(name = "注册资本(万元)")
    private String registeredCapital;

    /** 实收资本(万元) */
    @Excel(name = "实收资本(万元)")
    private String seeAlso;

    /** 官方网站(没有写无) */
    @Excel(name = "官方网站(没有写无)")
    private String officialWebsite;

    /** 企业邮箱 */
    @Excel(name = "企业邮箱")
    private String enterpriseMailbox;

    /** 产业类型*/
    @Excel(name = "产业类型", dictType = "applicable_industries",prompt = "请填写系统字典管理中的产业类型")
    private String industryOneLevel;

    /** 行业*/
    @Excel(name = "行业", dictType = "trade",prompt = "请填写系统字典管理中的行业")
    private String industryTwoLevel;

    /** 在职人数
    */
    @Excel(name = "在职人数")
    private String numberOfEmployees;

    /** 营业执照文件地址 */
    private String businessLicenseUrl;
    /** 企业标签集合 */
    @Excel(name = "企业标签(标签id集合，例:1,2)",width = 30)
    private String tagIds;
    @Excel(name = "联系人")
    private String  linkman;
    @Excel(name = "联系人电话")
    private String  linkmanPhone;
    /** 营业期限 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    //@Excel(name = "营业期限", width = 30, dateFormat = "yyyy-MM-dd")
    private Date businessTerm;
    /** 营业期限 */
    @Excel(name = "营业期限", width = 30)
    private String businessTermStr;
    /** 营业期限开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    //@Excel(name = "营业期限开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date businessTermStart;
    /** 经营范围 */
    @Excel(name = "经营范围")
    private String  natureOfBusiness;
    /** 登记机关 */
    @Excel(name = "登记机关")
    private String  registrationAuthority;
    /** 登记日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "登记日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date  registrationDate;
    /** 注册街道 */
   /* @Excel(name = "注册街道")*/
    private Integer  registrationStreet;
    /** 主营业务 */
    @Excel(name = "主营业务")
    private String mainBusiness;
    /** 符合政策信息 */
    @Excel(name = "符合政策信息")
    private String complianceWithPolicyInformation;

    /** 开户银行 */
    @Excel(name = "开户银行")
    private String bankAccounts;

    /** 银行卡号 */
    @Excel(name = "银行卡号")
    private String bankCardNumber;
    /** 身份证上传地址 */
    private String identityCardUrl;

    private  Boolean isSelected=false;
    private  Boolean disabled=true;
    private Long originalpolicyId;

    public String getBusinessTermStr() {
        return businessTermStr;
    }

    public void setBusinessTermStr(String businessTermStr) {
        this.businessTermStr = businessTermStr;
    }

    public String getIdentityCardUrl() {
        return identityCardUrl;
    }

    public void setIdentityCardUrl(String identityCardUrl) {
        this.identityCardUrl = identityCardUrl;
    }

    public Date getBusinessTermStart() {
        return businessTermStart;
    }

    public void setBusinessTermStart(Date businessTermStart) {
        this.businessTermStart = businessTermStart;
    }

    public String getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(String bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public Long getOriginalpolicyId() {
        return originalpolicyId;
    }

    public void setOriginalpolicyId(Long originalpolicyId) {
        this.originalpolicyId = originalpolicyId;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getComplianceWithPolicyInformation() {
        return complianceWithPolicyInformation;
    }

    public void setComplianceWithPolicyInformation(String complianceWithPolicyInformation) {
        this.complianceWithPolicyInformation = complianceWithPolicyInformation;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public Integer getRegistrationStreet() {
        return registrationStreet;
    }

    public void setRegistrationStreet(Integer registrationStreet) {
        this.registrationStreet = registrationStreet;
    }

    public String getRegistrationAuthority() {
        return registrationAuthority;
    }

    public void setRegistrationAuthority(String registrationAuthority) {
        this.registrationAuthority = registrationAuthority;
    }

    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public void setNatureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    public Date getBusinessTerm() {
        return businessTerm;
    }

    public void setBusinessTerm(Date businessTerm) {
        this.businessTerm = businessTerm;
    }

    @NotBlank(message= "联系人不能为空")
    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }
    @NotBlank(message= "联系人电话不能为空")
    @Size(min = 11, max = 12, message = "手机号码长度不能超过12个字符")
    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }
    @NotBlank(message= "企业标签不能为空")
    public String getTagIds() {
        return tagIds;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }
    @NotBlank(message= "企业名称不能为空")
    public String getName() 
    {
        return name;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setJuridicalPerson(String juridicalPerson) 
    {
        this.juridicalPerson = juridicalPerson;
    }
    @NotBlank(message= "企业法人不能为空")
    public String getJuridicalPerson() 
    {
        return juridicalPerson;
    }
    public void setJuridicalPersonPhone(String juridicalPersonPhone) 
    {
        this.juridicalPersonPhone = juridicalPersonPhone;
    }

    public String getJuridicalPersonPhone() 
    {
        return juridicalPersonPhone;
    }
    public void setRegistrationTime(Date registrationTime) 
    {
        this.registrationTime = registrationTime;
    }

    public Date getRegistrationTime() 
    {
        return registrationTime;
    }
    public void setRegistrationProvince(Integer registrationProvince)
    {
        this.registrationProvince = registrationProvince;
    }

    public Integer getRegistrationProvince()
    {
        return registrationProvince;
    }
    public void setRegistrationCity(Integer registrationCity)
    {
        this.registrationCity = registrationCity;
    }

    public Integer getRegistrationCity()
    {
        return registrationCity;
    }
    public void setRegistrationRegion(Integer registrationRegion)
    {
        this.registrationRegion = registrationRegion;
    }

    public Integer getRegistrationRegion()
    {
        return registrationRegion;
    }
    public void setManageAddress(String manageAddress) 
    {
        this.manageAddress = manageAddress;
    }

    public String getManageAddress() 
    {
        return manageAddress;
    }
    public void setSocialUnifiedCreditCodeNumber(String socialUnifiedCreditCodeNumber) 
    {
        this.socialUnifiedCreditCodeNumber = socialUnifiedCreditCodeNumber;
    }
    @NotBlank(message= "社会统一信用代码号不能为空")
    public String getSocialUnifiedCreditCodeNumber() 
    {
        return socialUnifiedCreditCodeNumber;
    }
    public void setEnterpriseNature(String enterpriseNature) 
    {
        this.enterpriseNature = enterpriseNature;
    }

    public String getEnterpriseNature() 
    {
        return enterpriseNature;
    }
    public void setRegistrationType(String registrationType) 
    {
        this.registrationType = registrationType;
    }
    //@NotBlank(message= "注册街道不能为空")
    public String getRegistrationType() 
    {
        return registrationType;
    }
    public void setTotalAssetsRange(String totalAssetsRange)
    {
        this.totalAssetsRange = totalAssetsRange;
    }

    public String getTotalAssetsRange()
    {
        return totalAssetsRange;
    }
    public void setRegisteredCapital(String registeredCapital)
    {
        this.registeredCapital = registeredCapital;
    }

    public String getRegisteredCapital()
    {
        return registeredCapital;
    }
    public void setSeeAlso(String seeAlso)
    {
        this.seeAlso = seeAlso;
    }

    public String getSeeAlso()
    {
        return seeAlso;
    }
    public void setOfficialWebsite(String officialWebsite) 
    {
        this.officialWebsite = officialWebsite;
    }

    public String getOfficialWebsite() 
    {
        return officialWebsite;
    }
    public void setEnterpriseMailbox(String enterpriseMailbox) 
    {
        this.enterpriseMailbox = enterpriseMailbox;
    }
    @Email(message = "邮箱格式不正确")
    public String getEnterpriseMailbox() 
    {
        return enterpriseMailbox;
    }
    public void setIndustryOneLevel(String industryOneLevel) 
    {
        this.industryOneLevel = industryOneLevel;
    }

    public String getIndustryOneLevel() 
    {
        return industryOneLevel;
    }
    public void setIndustryTwoLevel(String industryTwoLevel) 
    {
        this.industryTwoLevel = industryTwoLevel;
    }

    public String getIndustryTwoLevel() 
    {
        return industryTwoLevel;
    }
    public void setNumberOfEmployees(String numberOfEmployees)
    {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getNumberOfEmployees()
    {
        return numberOfEmployees;
    }
    public void setBusinessLicenseUrl(String businessLicenseUrl)
    {
        this.businessLicenseUrl = businessLicenseUrl;
    }

    public String getBusinessLicenseUrl()
    {
        return businessLicenseUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("address", getAddress())
            .append("juridicalPerson", getJuridicalPerson())
            .append("juridicalPersonPhone", getJuridicalPersonPhone())
            .append("registrationTime", getRegistrationTime())
            .append("registrationProvince", getRegistrationProvince())
            .append("registrationCity", getRegistrationCity())
            .append("registrationRegion", getRegistrationRegion())
            .append("manageAddress", getManageAddress())
            .append("socialUnifiedCreditCodeNumber", getSocialUnifiedCreditCodeNumber())
            .append("enterpriseNature", getEnterpriseNature())
            .append("registrationType", getRegistrationType())
            .append("totalAssetsRange", getTotalAssetsRange())
            .append("registeredCapital", getRegisteredCapital())
            .append("seeAlso", getSeeAlso())
            .append("officialWebsite", getOfficialWebsite())
            .append("enterpriseMailbox", getEnterpriseMailbox())
            .append("industryOneLevel", getIndustryOneLevel())
            .append("industryTwoLevel", getIndustryTwoLevel())
            .append("numberOfEmployees", getNumberOfEmployees())
            .append("business licenseUrl", getBusinessLicenseUrl())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("tagIds", getTagIds())
            .toString();
    }
}
