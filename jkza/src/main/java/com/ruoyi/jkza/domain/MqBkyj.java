package com.ruoyi.jkza.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 布控预警对象 mq_bkyj
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
public class MqBkyj extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 布控库 ID */
    @Excel(name = "布控库 ID")
    private String repositoryCode;

    /** 抓拍人脸抠图 */
    @Excel(name = "抓拍人脸抠图")
    private String imgUrl2;

    /** 时空数据类型 */
    @Excel(name = "时空数据类型")
    private Long recordType;

    /** 报警时间 */
    @Excel(name = "报警时间")
    private Long alarmTime;

    /** 抓拍原图 */
    @Excel(name = "抓拍原图")
    private String imgUrl1;

    /** 布控库名称 */
    @Excel(name = "布控库名称")
    private String repositoryName;

    /** 报警唯一 ID */
    @Excel(name = "报警唯一 ID")
    private String recordId;

    /** 时空数据标识 */
    @Excel(name = "时空数据标识")
    private String objId;

    /** 抓拍点位名称 */
    @Excel(name = "抓拍点位名称")
    private String channelName;

    /** 布控单位编码 */
    @Excel(name = "布控单位编码")
    private String surveyOrg;

    /** 时空数据类型 */
    @Excel(name = "时空数据类型")
    private Long objType;

    /** 抓拍时间 */
    @Excel(name = "抓拍时间")
    private Long capTime;

    /** 抓拍点位 ID */
    @Excel(name = "抓拍点位 ID")
    private String channelId;

    /** 经度 */
    @Excel(name = "经度")
    private Long gpsX;

    /** 纬度 */
    @Excel(name = "纬度")
    private Long gpsY;

    /** 证件图 */
    @Excel(name = "证件图")
    private String imgUrl3;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String cardNo;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String cardType;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 驾驶舱ID */
    @Excel(name = "驾驶舱ID")
    private Long jscId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRepositoryCode(String repositoryCode) 
    {
        this.repositoryCode = repositoryCode;
    }

    public String getRepositoryCode() 
    {
        return repositoryCode;
    }
    public void setImgUrl2(String imgUrl2) 
    {
        this.imgUrl2 = imgUrl2;
    }

    public String getImgUrl2() 
    {
        return imgUrl2;
    }
    public void setRecordType(Long recordType) 
    {
        this.recordType = recordType;
    }

    public Long getRecordType() 
    {
        return recordType;
    }
    public void setAlarmTime(Long alarmTime) 
    {
        this.alarmTime = alarmTime;
    }

    public Long getAlarmTime() 
    {
        return alarmTime;
    }
    public void setImgUrl1(String imgUrl1) 
    {
        this.imgUrl1 = imgUrl1;
    }

    public String getImgUrl1() 
    {
        return imgUrl1;
    }
    public void setRepositoryName(String repositoryName) 
    {
        this.repositoryName = repositoryName;
    }

    public String getRepositoryName() 
    {
        return repositoryName;
    }
    public void setRecordId(String recordId) 
    {
        this.recordId = recordId;
    }

    public String getRecordId() 
    {
        return recordId;
    }
    public void setObjId(String objId) 
    {
        this.objId = objId;
    }

    public String getObjId() 
    {
        return objId;
    }
    public void setChannelName(String channelName) 
    {
        this.channelName = channelName;
    }

    public String getChannelName() 
    {
        return channelName;
    }
    public void setSurveyOrg(String surveyOrg) 
    {
        this.surveyOrg = surveyOrg;
    }

    public String getSurveyOrg() 
    {
        return surveyOrg;
    }
    public void setObjType(Long objType) 
    {
        this.objType = objType;
    }

    public Long getObjType() 
    {
        return objType;
    }
    public void setCapTime(Long capTime) 
    {
        this.capTime = capTime;
    }

    public Long getCapTime() 
    {
        return capTime;
    }
    public void setChannelId(String channelId) 
    {
        this.channelId = channelId;
    }

    public String getChannelId() 
    {
        return channelId;
    }
    public void setGpsX(Long gpsX) 
    {
        this.gpsX = gpsX;
    }

    public Long getGpsX() 
    {
        return gpsX;
    }
    public void setGpsY(Long gpsY) 
    {
        this.gpsY = gpsY;
    }

    public Long getGpsY() 
    {
        return gpsY;
    }
    public void setImgUrl3(String imgUrl3) 
    {
        this.imgUrl3 = imgUrl3;
    }

    public String getImgUrl3() 
    {
        return imgUrl3;
    }
    public void setCardNo(String cardNo) 
    {
        this.cardNo = cardNo;
    }

    public String getCardNo() 
    {
        return cardNo;
    }
    public void setCardType(String cardType) 
    {
        this.cardType = cardType;
    }

    public String getCardType() 
    {
        return cardType;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setJscId(Long jscId) 
    {
        this.jscId = jscId;
    }

    public Long getJscId() 
    {
        return jscId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("repositoryCode", getRepositoryCode())
            .append("imgUrl2", getImgUrl2())
            .append("recordType", getRecordType())
            .append("alarmTime", getAlarmTime())
            .append("imgUrl1", getImgUrl1())
            .append("repositoryName", getRepositoryName())
            .append("recordId", getRecordId())
            .append("objId", getObjId())
            .append("channelName", getChannelName())
            .append("surveyOrg", getSurveyOrg())
            .append("objType", getObjType())
            .append("capTime", getCapTime())
            .append("channelId", getChannelId())
            .append("gpsX", getGpsX())
            .append("gpsY", getGpsY())
            .append("imgUrl3", getImgUrl3())
            .append("cardNo", getCardNo())
            .append("cardType", getCardType())
            .append("name", getName())
            .append("jscId", getJscId())
            .toString();
    }
}
