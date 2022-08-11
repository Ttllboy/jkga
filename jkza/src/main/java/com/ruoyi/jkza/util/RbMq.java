package com.ruoyi.jkza.util;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.*;
import com.ruoyi.jkza.domain.*;
import com.ruoyi.jkza.mapper.*;
import com.ruoyi.jkza.service.IMqBkyjService;
import com.ruoyi.jkza.service.IYjLowPointWarnService;
import com.ruoyi.jkza.service.IYyCwmjService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component
public class RbMq {
    @Autowired
    private IYyCwmjService yyCwmjService;
    @Autowired
    private AdmYyCwMapper admYyCwMapper;
    /**
          * 创建连接连接到RabbitMQ
          * @return   0 means success
          *           -1 means failure
          */
    //门禁rbmq
    @RabbitListener(queues = "jgkj_mj")
    @RabbitHandler
    public void mj(String personMessage, Message message,Channel channel)throws Exception{
//        System.out.println("消费者消费了:"+personMessage.toString());
        JSONObject jsonObject = JSONObject.parseObject(personMessage);
        String hosName = "";
        if(!jsonObject.isEmpty()){
            Integer tip = 0;
            if (jsonObject.getString("deviceCode") != null) {
                String deviceCode = jsonObject.getString("deviceCode");
                List<AdmYyCw> list = admYyCwMapper.selectAdmYyCwList(new AdmYyCw());
                for (int i = 0; i < list.size(); i++) {
                    AdmYyCw yyCw = list.get(i);
                    if(deviceCode.equals(yyCw.getDeviceCode())){
                        tip = 1;
                        hosName = yyCw.getDeviceName();
                    }
                }
            }
            if(tip == 1){
                YyPerceptionType yyPerceptionType = new YyPerceptionType();
                yyPerceptionType.setType("测温门禁");
                yyPerceptionType.setHosName(hosName);
                YyCwmj cwmj = new YyCwmj();
                if (jsonObject.getString("orgName") != null) {
                    cwmj.setOrgName(jsonObject.getString("orgName"));
                }
                if (jsonObject.getLong("enterOrExit") != null) {
                    cwmj.setEnterOrExit(jsonObject.getLong("enterOrExit"));
                }
                if (jsonObject.getDouble("curTemp") != null) {
                    cwmj.setCurTemp(jsonObject.getDouble("curTemp"));
                }
                if (jsonObject.getString("healthMode") != null) {
                    cwmj.setHealthMode(jsonObject.getString("healthMode"));
                }
                if (jsonObject.getString("cardNumber ") != null) {
                    cwmj.setCardNumber(jsonObject.getString("cardNumber "));
                }
                if (jsonObject.getLong("cardType") != null) {
                    cwmj.setCardType(jsonObject.getLong("cardType"));
                }
                if (jsonObject.getString("picture1") != null) {
                    cwmj.setPicture1(jsonObject.getString("picture1"));
                    yyPerceptionType.setImgUrl(jsonObject.getString("picture1"));
                }
                if (jsonObject.getString("deviceCode") != null) {
                    cwmj.setDeviceCode(jsonObject.getString("deviceCode"));
                }
                if (jsonObject.getString("personCode ") != null) {
                    cwmj.setPersonCode(jsonObject.getString("personCode "));
                }
                if (jsonObject.getString("idNumber") != null) {
                    cwmj.setIdNumber(jsonObject.getString("idNumber"));
                }
                if (jsonObject.getString("deviceName") != null) {
                    cwmj.setDeviceName(jsonObject.getString("deviceName"));
                }
                if (jsonObject.getLong("openType") != null) {
                    cwmj.setOpenType(jsonObject.getLong("openType"));
                }
                if (jsonObject.getString("personName") !=null) {
                    cwmj.setPersonName(jsonObject.getString("personName"));
                }
                if (jsonObject.getString("recordId") != null) {
                    cwmj.setRecordId(jsonObject.getString("recordId"));
                }
                if (jsonObject.getLong("isOverTemp") != null) {
                    cwmj.setIsOverTemp(jsonObject.getLong("isOverTemp"));
                }
                if (jsonObject.getString("orgCode") != null) {
                    cwmj.setOrgCode(jsonObject.getString("orgCode"));
                }
                if (jsonObject.getLong("openResult") != null) {
                    cwmj.setOpenResult(jsonObject.getLong("openResult"));
                }
                if (jsonObject.getString("channelName") != null) {
                    cwmj.setChannelName(jsonObject.getString("channelName"));
                }
                if (jsonObject.getLong("category") != null) {
                    cwmj.setCategory(jsonObject.getLong("category"));
                }
                if (jsonObject.getLong("capTime") != null) {
                    cwmj.setCapTime(jsonObject.getLong("capTime"));
                    yyPerceptionType.setCapTime(jsonObject.getLong("capTime"));
                }
                if (jsonObject.getString("cardStatus") != null) {
                    cwmj.setCardStatus(jsonObject.getString("cardStatus"));
                }
                if (jsonObject.getString("channelCode") != null) {
                    cwmj.setChannelCode(jsonObject.getString("channelCode"));
                }
                yyCwmjService.insertYyCwmj(cwmj);
                yyPerceptionTypeMapper.insertYyPerceptionType(yyPerceptionType);
            }
        }

//
//        .out.println("消费者消费了:"+message.toString());
//        System.out.println("消费者消费了:"+channel.toString());
    }
    @Autowired
    private IMqBkyjService mqBkyjService;
    @RabbitListener(queues = "jgkj_zdry")
    @RabbitHandler
    public void zdry(String personMessage, Message message,Channel channel)throws Exception{
//        System.out.println("消费:"+personMessage.toString());
//        System.out.println("第二:"+message.toString());
//        System.out.println("第三:"+channel.toString());
        JSONObject jsonObject = JSONObject.parseObject(personMessage);
        if(!jsonObject.isEmpty()){
            MqBkyj mqBkyj = new MqBkyj();
            if (jsonObject.getString("channelName") != null) {
                mqBkyj.setRepositoryCode(jsonObject.getString("channelName"));
            }
            if (jsonObject.getString("imgUrl2") != null) {
                mqBkyj.setImgUrl2(jsonObject.getString("imgUrl2"));
            }
            if (jsonObject.getLong("recordType") != null) {
                mqBkyj.setRecordType(jsonObject.getLong("recordType"));
            }
            if (jsonObject.getLong("alarmTime") != null) {
                mqBkyj.setAlarmTime(jsonObject.getLong("alarmTime"));
            }
            if (jsonObject.getString("imgUrl1") != null) {
                mqBkyj.setImgUrl1(jsonObject.getString("imgUrl1"));
            }
            if (jsonObject.getString("repositoryName") != null) {
                mqBkyj.setRepositoryName(jsonObject.getString("repositoryName"));
            }
            if (jsonObject.getString("recordId") != null) {
                mqBkyj.setRecordId(jsonObject.getString("recordId"));
            }
            if (jsonObject.getString("objId") != null) {
                mqBkyj.setObjId(jsonObject.getString("objId"));
            }
            if (jsonObject.getString("channelName") != null) {
                mqBkyj.setChannelName(jsonObject.getString("channelName"));
            }
            if (jsonObject.getString("surveyOrg") != null) {
                mqBkyj.setSurveyOrg(jsonObject.getString("surveyOrg"));
            }
            if (jsonObject.getLong("objType") != null) {
                mqBkyj.setObjType(jsonObject.getLong("objType"));
            }
            if (jsonObject.getLong("capTime") != null) {
                mqBkyj.setCapTime(jsonObject.getLong("capTime"));
            }
            if (jsonObject.getString("channelId") != null) {
                mqBkyj.setChannelId(jsonObject.getString("channelId"));
            }
            if (jsonObject.getLong("gpsX") != null) {
                mqBkyj.setGpsX(jsonObject.getLong("gpsX"));
            }
            if (jsonObject.getLong("gpsY") != null) {
                mqBkyj.setGpsY(jsonObject.getLong("gpsY"));
            }
            if (jsonObject.getString("imgUrl3") != null) {
                mqBkyj.setImgUrl3(jsonObject.getString("imgUrl3"));
            }
            if (jsonObject.getString("cardNo") != null) {
                mqBkyj.setCardNo(jsonObject.getString("cardNo"));
            }
            if (jsonObject.getString("cardType") != null) {
                mqBkyj.setCardType(jsonObject.getString("cardType"));
            }
            if (jsonObject.getString("name") != null) {
                mqBkyj.setName(jsonObject.getString("name"));
            }
            mqBkyjService.insertMqBkyj(mqBkyj);
        }
    }
    @Autowired
    private IYjLowPointWarnService yjLowPointWarnService;
    @RabbitListener(queues = "jgkj_water_warn")
    @RabbitHandler
    public void waterWarn(String personMessage, Message message,Channel channel)throws Exception{
//        System.out.println("消费:"+personMessage.toString());
        JSONObject jsonObject = JSONObject.parseObject(personMessage);
        if(!jsonObject.isEmpty()){
            if(jsonObject.getLong("alarmType") == 16995){
                YjLowPointWarn lowPointWarn = new YjLowPointWarn();
                if (jsonObject.getString("alarmCode") != null) {
                    lowPointWarn.setAlarmCode(jsonObject.getString("alarmCode"));
                }
                if (jsonObject.getString("alarmPicture") != null) {
                    lowPointWarn.setAlarmPicture(jsonObject.getString("alarmPicture"));
                }
                if (jsonObject.getLong("alarmType") != null) {
                    lowPointWarn.setAlarmType(jsonObject.getLong("alarmType"));
                }
                if (jsonObject.getString("alarmTime") != null) {
                    lowPointWarn.setAlarmTime(jsonObject.getString("alarmTime"));
                }
                if (jsonObject.getString("orgCode") != null) {
                    lowPointWarn.setOrgCode(jsonObject.getString("orgCode"));
                }
                if (jsonObject.getString("orgName") != null) {
                    lowPointWarn.setOrgName(jsonObject.getString("orgName"));
                }
                if (jsonObject.getString("deviceCode") != null) {
                    lowPointWarn.setDeviceCode(jsonObject.getString("deviceCode"));
                }
                if (jsonObject.getString("deviceName") != null) {
                    lowPointWarn.setDeviceName(jsonObject.getString("deviceName"));
                }
                if (jsonObject.getString("channelCode") != null) {
                    lowPointWarn.setChannelCode(jsonObject.getString("channelCode"));
                }
                if (jsonObject.getString("channelName") != null) {
                    lowPointWarn.setChannelName(jsonObject.getString("channelName"));
                }
                if (jsonObject.getLong("alarmStat") != null) {
                    lowPointWarn.setAlarmStat(jsonObject.getLong("alarmStat"));
                }
                lowPointWarn.setDataType(0);
                yjLowPointWarnService.insertYjLowPointWarn(lowPointWarn);
            }
        }
    }
    @Autowired
    private MqCarMapper mqCarMapper;
    @Autowired
    private YyVideoMapper yyVideoMapper;
    @RabbitListener(queues = "jgkj_car")
    @RabbitHandler
    public void car(String personMessage, Message message,Channel channel)throws Exception{
//        System.out.println("消费:"+personMessage.toString());
        JSONObject jsonObject = JSONObject.parseObject(personMessage);
        MqCar mqCar = new MqCar();
        List<YyVideo> list = yyVideoMapper.selectYyVideoList(new YyVideo());

        if(!jsonObject.isEmpty()){
            for (int i = 0; i < list.size(); i++) {
                if (jsonObject.getString("channelCode") != null) {
                    if(jsonObject.getString("channelCode").equals(list.get(i).getChannelCode())){
//                        System.out.println("属于医院");
                        YyPerceptionType yyPerceptionType = new YyPerceptionType();
                        yyPerceptionType.setType("车辆抓拍");
                        yyPerceptionType.setHosName(list.get(i).getHosName());
                        if (jsonObject.getString("recordId") != null) {
                            mqCar.setRecordId(jsonObject.getString("recordId"));
                        }
                        if (jsonObject.getString("carImgUrl") != null) {
                            mqCar.setCarImgUrl(jsonObject.getString("carImgUrl"));
                            yyPerceptionType.setImgUrl(jsonObject.getString("carImgUrl"));
                        }
                        if (jsonObject.getString("plateType") != null) {
                            mqCar.setPlateType(jsonObject.getString("plateType"));
                        }
                        if (jsonObject.getString("plateNum") != null) {
                            mqCar.setPlateNum(jsonObject.getString("plateNum"));
                        }
                        if (jsonObject.getString("plateNumurl") != null) {
                            mqCar.setPlateNumUrl(jsonObject.getString("plateNumurl"));
                        }
                        if (jsonObject.getString("deviceId") != null) {
                            mqCar.setDeviceId(jsonObject.getString("deviceId"));
                        }
                        if (jsonObject.getLong("capTime") != null) {
                            mqCar.setCapTime(jsonObject.getLong("capTime"));
                            yyPerceptionType.setCapTime(jsonObject.getLong("capTime"));
                        }
                        if (jsonObject.getString("channelCode") != null) {
                            mqCar.setChannelCode(jsonObject.getString("channelCode"));
                        }
                        mqCar.setDataType(0);
                        mqCarMapper.insertMqCar(mqCar);
                        yyPerceptionTypeMapper.insertYyPerceptionType(yyPerceptionType);
                    }
                }
            }

        }
    }
    @Autowired
    private MqRlzpMapper mqRlzpMapper;
    @Autowired
    private GdBaseMapper gdBaseMapper;
    @Autowired
    private YyPerceptionTypeMapper yyPerceptionTypeMapper;
    @RabbitListener(queues = "jgkj_rlzp")
    @RabbitHandler
    public void jgkj_rlzp(String personMessage, Message message,Channel channel)throws Exception {
//        System.out.println("消费:"+personMessage.toString());
        JSONObject jsonObject = JSONObject.parseObject(personMessage);
        MqRlzp mqRlzp = new MqRlzp();
        List<HashMap> list = gdBaseMapper.selectAdmTdcjgl();
        if(!jsonObject.isEmpty()){
            if (jsonObject.getString("channelId") != null) {
                for (int i = 0; i < list.size(); i++) {
                    HashMap map = list.get(i);
                    String channelId = (String) map.get("channel_code");
                    String yyName = (String) map.get("scene_name");
                    if(yyName.equals("医院车辆出入口") || yyName.equals("医院人行出入口")){
//                        System.out.println("医院车辆出入口");
                        continue;
                    }
                    if(channelId.equals(jsonObject.getString("channelId"))){
                        YyPerceptionType yyPerceptionType = new YyPerceptionType();
                        yyPerceptionType.setType("人脸抓拍");
                        yyPerceptionType.setHosName(yyName);
                        if (jsonObject.getString("recordId") != null) {
                            mqRlzp.setRecordId(jsonObject.getString("recordId"));
                        }
                        if (jsonObject.getString("imgUrl") != null) {
                            mqRlzp.setImgUrl(jsonObject.getString("imgUrl"));
                            yyPerceptionType.setImgUrl(jsonObject.getString("imgUrl"));
                        }
                        if (jsonObject.getString("faceImgUrl") != null) {
                            mqRlzp.setFaceImgUrl(jsonObject.getString("faceImgUrl"));
                        }
                        if (jsonObject.getString("deviceId") != null) {
                            mqRlzp.setDeviceId(jsonObject.getString("deviceId"));
                        }
                        if(jsonObject.getInteger("dataSource") != null){
                            mqRlzp.setDataSource(jsonObject.getInteger("dataSource"));
                        }
                        if(jsonObject.getInteger("faceImgType") != null){
                            mqRlzp.setFaceImgType(jsonObject.getInteger("faceImgType"));
                        }
                        if (jsonObject.getString("channelId") != null) {
                            mqRlzp.setChannelId(jsonObject.getString("channelId"));
                        }
                        if (jsonObject.getLong("capTime") != null) {
                            mqRlzp.setCapTime(jsonObject.getLong("capTime"));
                            yyPerceptionType.setCapTime(jsonObject.getLong("capTime"));
                        }
                        mqRlzpMapper.insertMqRlzp(mqRlzp);
                        yyPerceptionTypeMapper.insertYyPerceptionType(yyPerceptionType);
                    }
                }
            }
        }
    }

}
