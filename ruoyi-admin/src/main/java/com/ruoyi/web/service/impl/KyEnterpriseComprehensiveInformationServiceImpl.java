package com.ruoyi.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.EnterpriseComprehensiveInformationUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.model.KyEnterpriseComprehensiveInformation;
import com.ruoyi.web.service.IKyEnterpriseComprehensiveInformationService;
import com.ruoyi.web.service.IKyEnterpriseService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;


/**
 * 企业用户评价Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-29
 */
@Service
public class KyEnterpriseComprehensiveInformationServiceImpl implements IKyEnterpriseComprehensiveInformationService {
    @Autowired
    private IKyEnterpriseService iKyEnterpriseService;

    @Override
    public List<KyEnterpriseComprehensiveInformation> selectList(KyEnterpriseComprehensiveInformation enterpriseComprehensiveInformation) {
        List<KyEnterpriseComprehensiveInformation> kyEnterpriseComprehensiveInformationList = new LinkedList<>();
        String result = null;
        switch (enterpriseComprehensiveInformation.getType()) {
            case 1:
                //企业备案
                KyEnterpriseComprehensiveInformation e1=new KyEnterpriseComprehensiveInformation();
                if(enterpriseComprehensiveInformation.getName().trim().equals("宜昌兴越新材料有限公司")){
                    e1.setName("宜昌兴越新材料有限公司");
                    e1.setCor_code("91420500309804832J");
                    e1.setMain_domain("许可项目：危险化学品生产（依法须经批准的项目，经相关部门批准后方可开展经营活动，具体经营项目以相关部门批准文件或许可证件为准）一般项目：第三类非药品类易制毒化学品生产；合成材料制造（不含危险化学品）（除许可业务外，可自主依法经营法律法规非禁止或限制的项目）");
                    e1.setCertificate_num("GR201942001291");
                    e1.setProvince("湖北省");
                    e1.setCity("宜昌市");
                }
                if(enterpriseComprehensiveInformation.getName().trim().equals("湖北奥马电子科技有限公司")){
                    e1.setName("湖北奥马电子科技有限公司");
                    e1.setCor_code("91420500060688644N");
                    e1.setMain_domain("智能消费品及平板电脑元器件、电子产品、光电产品、太阳能产品、电子屏材料研发、生产、销售；货物或技术进出口（国家禁止或涉及行政审批的货物和技术进出口除外）；电子产品及光电产品技术咨询和技术服务（依法须经批准的项目，经相关部门批准后方可开展经营活动）++") ;
                    e1.setCertificate_num("GR201642000218");
                    e1.setProvince("湖北省");
                    e1.setCity("宜昌市");
                }
                if(enterpriseComprehensiveInformation.getName().trim().equals("宜昌升华新能源科技有限公司")){
                    e1.setName("宜昌升华新能源科技有限公司");
                    e1.setCor_code("91420500667698267J");
                    e1.setMain_domain("" +
                            "废物处置设备研发；废矿物油与含矿物油废物（HW08）收集、贮存、利用（有效期至2024年9月4日）；再生资源利用；环保工程施工；环保专项技术服务；液化天然气贸易（仅限城市天然气，票面经营）（依法须经批准的项目，经相关部门批准后方可开展经营活动）++") ;
                    e1.setCertificate_num("GR201842001654");
                    e1.setProvince("湖北省");
                    e1.setCity("宜昌市");
                }
                if(enterpriseComprehensiveInformation.getName().trim().equals("亚元科技（宜昌）电子有限公司")){
                    e1.setName("亚元科技（宜昌）电子有限公司");
                    e1.setCor_code("91420500722035336J");
                    e1.setMain_domain("\t\n" +
                            "高新技术产品的研究、开发、生产、销售,计算机整机、便携式电脑、调制解调器、液晶显示器、数字照相机、通信器材(除卫星发射装置及关键件)及配件、电源供应器及配件、电源适配器及配件、连接器、塑料件、通信及计算机用线圈、变压器、滤波器、光纤宽带电子组件及配件、太阳能系列产品及配件、照明设备、扬声器及配件、其它电子电器相关产品的制造与销售") ;
                    e1.setCertificate_num("GR201742001971");
                    e1.setProvince("湖北省");
                    e1.setCity("宜昌市");
                }
                if(enterpriseComprehensiveInformation.getName().trim().equals("宜昌苏鹏科技有限公司")){
                    e1.setName("宜昌苏鹏科技有限公司");
                    e1.setCor_code("91420500085604670M");
                    e1.setMain_domain("许可项目：危险化学品生产；危险化学品经营；技术进出口（依法须经批准的项目，经相关部门批准后方可开展经营活动，具体经营项目以相关部门批准文件或许可证件为准）一般项目：新材料技术研发；生物化工产品技术研发；机械设备研发；通用设备制造（不含特种设备制造）；炼油、化工生产专用设备制造；工程和技术研究和试验发展；专用化学产品销售（不含危险化学品）；新型催化材料及助剂销售；机械设备销售；炼油、化工生产专用设备销售；专用化学产品制造（不含危险化学品）；技术服务、技术开发、技术咨询、技术交流、技术转让、技术推广；化工产品生产（不含许可类化工产品）；专用设备制造（不含许可类专业设备制造）（除依法须经批准的项目外，凭营业执照依法自主开展经营活动）") ;
                    e1.setCertificate_num("GR201642001493");
                    e1.setProvince("湖北省");
                    e1.setCity("宜昌市");
                }
                kyEnterpriseComprehensiveInformationList.add(e1);
               //result = EnterpriseComprehensiveInformationUtils.getEnterpriseComprehensiveInformationResult(RuoYiConfig.getServiceIdByBasicInformationForEnterpriseFiling(), RuoYiConfig.getUrlByBasicInformationForEnterpriseFiling(), null, null, null, enterpriseComprehensiveInformation.getName(), null, null,enterpriseComprehensiveInformation.getType());
                //if (StringUtils.isNotEmpty(result)) {
                    //kyEnterpriseComprehensiveInformationList = JSONObject.parseArray(result, KyEnterpriseComprehensiveInformation.class);
                    //查询所有企业统一社会编码
                    //String socialUnifiedCreditCodeNumberStr = iKyEnterpriseService.selectAllSocialUnifiedCreditCodeNumber();
                    //清除掉企业库中不存在的
                    //Iterator<KyEnterpriseComprehensiveInformation> iterator = kyEnterpriseComprehensiveInformationList.iterator();
                   // if (iterator.hasNext()) {
                      // KyEnterpriseComprehensiveInformation kyEnterpriseComprehensiveInformation = iterator.next();
                       // if (socialUnifiedCreditCodeNumberStr.indexOf(kyEnterpriseComprehensiveInformation.getCor_code()) == -1) {
                            //iterator.remove();
                        //}
                    //}
               // }
                break;
            case 2:
                //企业证书
                result = EnterpriseComprehensiveInformationUtils.getEnterpriseComprehensiveInformationResult(RuoYiConfig.getServiceIdByEnterpriseCertificateInformation(), RuoYiConfig.getUrlByEnterpriseCertificateInformation(), enterpriseComprehensiveInformation.getCertificateNumber(), enterpriseComprehensiveInformation.getEnterpriseName(), enterpriseComprehensiveInformation.getUnifyCode(), null, null, null,enterpriseComprehensiveInformation.getType());
                if (StringUtils.isNotEmpty(result)) {
                    JSONObject objData= JSONObject.parseObject(result);
                    String xmlData=objData.getString("data");
                    try {
                        Document doc = DocumentHelper.parseText(xmlData);// 报文转成doc对象
                        Element rootElement= doc.getRootElement();
                        Element rtnMsgElement= rootElement.element("rtnMsg");
                       if(rtnMsgElement.getText().equals("请求成功")){
                           Element  gqDataElement= rootElement.element("gqData");
                           Element yearElement=  gqDataElement.element("year");
                           String year=yearElement.getText();
                           Element certificateStatusElement=  gqDataElement.element("certificateStatus");
                           String certificateStatus=certificateStatusElement.getText();
                           Element enterpriseNameElement=  gqDataElement.element("enterpriseName");
                           String enterpriseNameStr=enterpriseNameElement.getText();
                           Element unifyCodeElement=  gqDataElement.element("unifyCode");
                           String unifyCodeStr=unifyCodeElement.getText();
                           Element certificateNumberElement=  gqDataElement.element("certificateNumber");
                           String certificateNumberStr=certificateNumberElement.getText();

                           Element determinationDateElement=  gqDataElement.element("determinationDate");
                           String determinationDate=determinationDateElement.getText();
                           Element industryElement=  gqDataElement.element("industry");
                           String industry=industryElement.getText();
                           Element identificationAuthorityElement=  gqDataElement.element("identificationAuthority");
                           String identificationAuthority=identificationAuthorityElement.getText();
                           Element provinceElement=  gqDataElement.element("province");
                           String province=provinceElement.getText();
                           Element cityElement=  gqDataElement.element("city");
                           String city=cityElement.getText();
                           Element distinctElement=  gqDataElement.element("distinct");
                           String distinct=distinctElement.getText();

                           KyEnterpriseComprehensiveInformation e=new KyEnterpriseComprehensiveInformation();
                           e.setCertificateNumber(certificateNumberStr);
                           e.setEnterpriseName(enterpriseNameStr);
                           e.setUnifyCode(unifyCodeStr);
                           e.setIndustry(industry);
                           e.setProvince(province);
                           e.setCity(city);
                           e.setDistinct(distinct);
                           e.setCertificateStatus(certificateStatus);
                           e.setIdentificationAuthority(identificationAuthority);
                           e.setDeterminationDate(determinationDate);
                           kyEnterpriseComprehensiveInformationList.add(e);
                       }
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case 3:
               // result = EnterpriseComprehensiveInformationUtils.getEnterpriseComprehensiveInformationResult(RuoYiConfig.getServiceIdBySMEInnovationFundProjectInformation(), RuoYiConfig.getUrlBySMEInnovationFundProjectInformation(), null,null,null, null, enterpriseComprehensiveInformation.getPrj_num(), enterpriseComprehensiveInformation.getPrj_name(),enterpriseComprehensiveInformation.getType());
                //if (StringUtils.isNotEmpty(result)) {
                 //   kyEnterpriseComprehensiveInformationList = JSONObject.parseArray(result, KyEnterpriseComprehensiveInformation.class);
                //}
                KyEnterpriseComprehensiveInformation e2=new KyEnterpriseComprehensiveInformation();
                if(enterpriseComprehensiveInformation.getPrj_name().equals("兴越新材料")&&enterpriseComprehensiveInformation.getPrj_num().equals("PRJ0194200129")){
                    e2.setPrj_num("PRJ0194200129");
                    e2.setPrj_name("兴越新材料");
                    e2.setCompany_ame("宜昌兴越新材料有限公司");
                    e2.setTech_field("许可项目：危险化学品生产（依法须经批准的项目，经相关部门批准后方可开展经营活动，具体经营项目以相关部门批准文件或许可证件为准）一般项目：第三类非药品类易制毒化学品生产；合成材料制造（不含危险化学品）（除许可业务外，可自主依法经营法律法规非禁止或限制的项目）");
                }
                if(enterpriseComprehensiveInformation.getPrj_name().equals("奥马电子")&&enterpriseComprehensiveInformation.getPrj_num().equals("PRJ0194220139")){
                    e2.setPrj_num("PRJ0194220139");
                    e2.setPrj_name("奥马电子");
                    e2.setCompany_ame("湖北奥马电子科技有限公司");
                    e2.setTech_field("智能消费品及平板电脑元器件、电子产品、光电产品、太阳能产品、电子屏材料研发、生产、销售；货物或技术进出口（国家禁止或涉及行政审批的货物和技术进出口除外）；电子产品及光电产品技术咨询和技术服务（依法须经批准的项目，经相关部门批准后方可开展经营活动）++") ;
                }
                if(enterpriseComprehensiveInformation.getPrj_name().equals("升华新能源")&&enterpriseComprehensiveInformation.getPrj_num().equals("PRJ201842001654")){
                    e2.setPrj_num("PRJ201842001654");
                    e2.setPrj_name("升华新能源");
                    e2.setCompany_ame("宜昌升华新能源科技有限公司");
                    e2.setTech_field("" +
                            "废物处置设备研发；废矿物油与含矿物油废物（HW08）收集、贮存、利用（有效期至2024年9月4日）；再生资源利用；环保工程施工；环保专项技术服务；液化天然气贸易（仅限城市天然气，票面经营）（依法须经批准的项目，经相关部门批准后方可开展经营活动）++") ;
                }
                if(enterpriseComprehensiveInformation.getPrj_name().equals("亚元科技")&&enterpriseComprehensiveInformation.getPrj_num().equals("PRJ201742001651")){
                    e2.setPrj_num("PRJ201742001651");
                    e2.setPrj_name("亚元科技");
                    e2.setCompany_ame("亚元科技（宜昌）电子有限公司");
                    e2.setTech_field("\t\n" +
                            "高新技术产品的研究、开发、生产、销售,计算机整机、便携式电脑、调制解调器、液晶显示器、数字照相机、通信器材(除卫星发射装置及关键件)及配件、电源供应器及配件、电源适配器及配件、连接器、塑料件、通信及计算机用线圈、变压器、滤波器、光纤宽带电子组件及配件、太阳能系列产品及配件、照明设备、扬声器及配件、其它电子电器相关产品的制造与销售") ;
                }
                if(enterpriseComprehensiveInformation.getPrj_name().equals("宜昌苏鹏科技")&&enterpriseComprehensiveInformation.getPrj_num().equals("PRJ201742001971")){
                    e2.setPrj_num("PRJ201742001971");
                    e2.setPrj_name("宜昌苏鹏科技");
                    e2.setCompany_ame("宜昌苏鹏科技有限公司");
                    e2.setTech_field("许可项目：危险化学品生产；危险化学品经营；技术进出口（依法须经批准的项目，经相关部门批准后方可开展经营活动，具体经营项目以相关部门批准文件或许可证件为准）一般项目：新材料技术研发；生物化工产品技术研发；机械设备研发；通用设备制造（不含特种设备制造）；炼油、化工生产专用设备制造；工程和技术研究和试验发展；专用化学产品销售（不含危险化学品）；新型催化材料及助剂销售；机械设备销售；炼油、化工生产专用设备销售；专用化学产品制造（不含危险化学品）；技术服务、技术开发、技术咨询、技术交流、技术转让、技术推广；化工产品生产（不含许可类化工产品）；专用设备制造（不含许可类专业设备制造）（除依法须经批准的项目外，凭营业执照依法自主开展经营活动）") ;

                }
                kyEnterpriseComprehensiveInformationList.add(e2);
                break;
            default:
        }

        return kyEnterpriseComprehensiveInformationList;
    }
}
