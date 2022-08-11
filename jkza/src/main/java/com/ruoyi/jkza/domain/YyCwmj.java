package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测温门禁对象 yy_cwmj
 *
 * @author ruoyi
 * @date 2022-06-15
 */
public class YyCwmj extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 通道所属组织名称 */
    @Excel(name = "通道所属组织名称")
    private String orgName;

    /** 进出门类型,1-进门,2-出门,3-进/出门 */
    @Excel(name = "进出门类型,1-进门,2-出门,3-进/出门")
    private Long enterOrExit;

    /** 健康码 */
    @Excel(name = "健康码")
    private String healthMode;

    @Excel(name = "体温")
    private Double curTemp;

    /** 卡号 */
    @Excel(name = "卡号")
    private String cardNumber;

    /** 卡片类型(0-普通卡,1-VIP 卡,2-来宾卡,
3-巡逻卡,4-黑名单卡,5-胁迫卡,6-巡检
卡,255-母卡) */
    @Excel(name = "卡片类型(0-普通卡,1-VIP 卡,2-来宾卡,3-巡逻卡,4-黑名单卡,5-胁迫卡,6-巡检卡,255-母卡)")
    private Long cardType;

    /** 抓图 传递图片完整 url 路径 */
    @Excel(name = "抓图 传递图片完整 url 路径")
    private String picture1;

    /** 设备编码（SaaS 层内部唯一编码） */
    @Excel(name = "设备编码", readConverterExp = "S=aaS,层=内部唯一编码")
    private String deviceCode;

    /** 人员编码 */
    @Excel(name = "人员编码")
    private String personCode;

    /** 人员身份证号 */
    @Excel(name = "人员身份证号")
    private String idNumber;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 开门方式（51 合法刷卡开门，45 合法指纹开
门，42 合法密码开门，61 合法人脸开门，40
未知开门方式，更多见数据字典） */
    @Excel(name = "开门方式", readConverterExp = "5=1,合=法刷卡开门，45,合=法指纹开门，42,合=法密码开门，61,合=法人脸开门，40未知开门方式，更多见数据字典")
    private Long openType;

    /** 人员姓名 */
    @Excel(name = "人员姓名")
    private String personName;

    /** 记录的唯一 id */
    @Excel(name = "记录的唯一 id")
    private String recordId;

    /** 是否超温，0-不超温，1-超温 */
    @Excel(name = "是否超温，0-不超温，1-超温")
    private Long isOverTemp;

    /** 通道所属组织 SaaS 编码 */
    @Excel(name = "通道所属组织 SaaS 编码")
    private String orgCode;

    /** 开门结果，0-失败，1-成功 */
    @Excel(name = "开门结果，0-失败，1-成功")
    private Long openResult;

    /** 通道名称 */
    @Excel(name = "通道名称")
    private String channelName;

    /** 卡的介质类型（0:IC，1:RFID，2: CPU 卡，
3:身份证，4:加密 IC 卡） */
    @Excel(name = "卡的介质类型", readConverterExp = "0=:IC，1:RFID，2:,C=PU,卡=，3:身份证，4:加密,I=C,卡=")
    private Long category;

    /** 刷卡时间，精确到毫秒，double 类型 */
    @Excel(name = "刷卡时间，精确到毫秒，double 类型")
    private Long capTime;

    /** 卡片状态：0-正常，1-挂失，2-注销，4-冻
结，8-欠费，16-逾期，32-预欠费(还是可以
开门,但有语音提示) */
    @Excel(name = "卡片状态：0-正常，1-挂失，2-注销，4-冻结，8-欠费，16-逾期，32-预欠费(还是可以开门,但有语音提示)")
    private String cardStatus;

    /** 通道编码（SaaS 层内部唯一编码） */
    @Excel(name = "通道编码", readConverterExp = "S=aaS,层=内部唯一编码")
    private String channelCode;

    /** 医院名称 */
    @Excel(name = "医院名称")
    private String hosName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    public String getOrgName()
    {
        return orgName;
    }
    public void setEnterOrExit(Long enterOrExit)
    {
        this.enterOrExit = enterOrExit;
    }

    public Long getEnterOrExit()
    {
        return enterOrExit;
    }
    public void setHealthMode(String healthMode)
    {
        this.healthMode = healthMode;
    }

    public String getHealthMode()
    {
        return healthMode;
    }
    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }
    public void setCardType(Long cardType)
    {
        this.cardType = cardType;
    }

    public Double getCurTemp() {
        return curTemp;
    }

    public void setCurTemp(Double curTemp) {
        this.curTemp = curTemp;
    }

    public Long getCardType()
    {
        return cardType;
    }
    public void setPicture1(String picture1)
    {
        this.picture1 = picture1;
    }

    public String getPicture1()
    {
        return picture1;
    }
    public void setDeviceCode(String deviceCode)
    {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode()
    {
        return deviceCode;
    }
    public void setPersonCode(String personCode)
    {
        this.personCode = personCode;
    }

    public String getPersonCode()
    {
        return personCode;
    }
    public void setIdNumber(String idNumber)
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber()
    {
        return idNumber;
    }
    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    public String getDeviceName()
    {
        return deviceName;
    }
    public void setOpenType(Long openType)
    {
        this.openType = openType;
    }

    public Long getOpenType()
    {
        return openType;
    }
    public void setPersonName(String personName)
    {
        this.personName = personName;
    }

    public String getPersonName()
    {
        return personName;
    }
    public void setRecordId(String recordId)
    {
        this.recordId = recordId;
    }

    public String getRecordId()
    {
        return recordId;
    }
    public void setIsOverTemp(Long isOverTemp)
    {
        this.isOverTemp = isOverTemp;
    }

    public Long getIsOverTemp()
    {
        return isOverTemp;
    }
    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    public String getOrgCode()
    {
        return orgCode;
    }
    public void setOpenResult(Long openResult)
    {
        this.openResult = openResult;
    }

    public Long getOpenResult()
    {
        return openResult;
    }
    public void setChannelName(String channelName)
    {
        this.channelName = channelName;
    }

    public String getChannelName()
    {
        return channelName;
    }
    public void setCategory(Long category)
    {
        this.category = category;
    }

    public Long getCategory()
    {
        return category;
    }
    public void setCapTime(Long capTime)
    {
        this.capTime = capTime;
    }

    public Long getCapTime()
    {
        return capTime;
    }
    public void setCardStatus(String cardStatus)
    {
        this.cardStatus = cardStatus;
    }

    public String getCardStatus()
    {
        return cardStatus;
    }
    public void setChannelCode(String channelCode)
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode()
    {
        return channelCode;
    }

    public void setHosName(String hosName)
    {
        this.hosName = hosName;
    }

    public String getHosName()
    {
        return hosName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orgName", getOrgName())
            .append("enterOrExit", getEnterOrExit())
            .append("healthMode", getHealthMode())
            .append("cardNumber", getCardNumber())
            .append("cardType", getCardType())
            .append("picture1", getPicture1())
            .append("deviceCode", getDeviceCode())
            .append("personCode", getPersonCode())
            .append("idNumber", getIdNumber())
            .append("deviceName", getDeviceName())
            .append("openType", getOpenType())
            .append("personName", getPersonName())
            .append("recordId", getRecordId())
            .append("isOverTemp", getIsOverTemp())
            .append("orgCode", getOrgCode())
            .append("openResult", getOpenResult())
            .append("channelName", getChannelName())
            .append("category", getCategory())
            .append("capTime", getCapTime())
            .append("cardStatus", getCardStatus())
            .append("channelCode", getChannelCode())
                .append("hosName", getHosName())
            .toString();
    }
}
