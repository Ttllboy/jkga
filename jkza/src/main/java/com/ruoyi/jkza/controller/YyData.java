package com.ruoyi.jkza.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.jkza.domain.*;
import com.ruoyi.jkza.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/yyData")
public class YyData {


    //医院基础信息
    @Autowired
    private IYyBaseService yyBaseService;
    @PostMapping("/base")
    public Object base(){
        return yyBaseService.selectYyBaseList(new YyBase()).get(0);
    }

    //医院感知信息
    @Autowired
    private IYyPerceptionService yyPerceptionService;
    @PostMapping("/perception")
    public Object perception(){
        return yyPerceptionService.selectYyPerceptionList(new YyPerception()).get(0);
    }

    //医院防疫详情
    @Autowired
    private IYyEpidemicPreventionService yyEpidemicPreventionService;
    @PostMapping("/epidemicPrevention")
    public Object epidemicPrevention(){
        return yyEpidemicPreventionService.selectYyEpidemicPreventionList(new YyEpidemicPrevention()).get(0);
    }

    //人脸抓拍信息
    @Autowired
    private IYyFaceCaptureService yyFaceCaptureService;
    @PostMapping("/faceCapture")
    public Object faceCapture(){
        return yyFaceCaptureService.selectYyFaceCaptureList(new YyFaceCapture());
    }

    //人员预警
    @Autowired
    private IYyWarnService yyWarnService;
    @PostMapping("/warnPeople")
    public List<?> warnPeople(){
        YyWarn yyWarn = new YyWarn();
        yyWarn.setWarnType(0);
        return yyWarnService.selectYyWarnList(yyWarn);
    }

    //车辆预警
    @PostMapping("/warnCar")
    public List<?> warnCar(){
        YyWarn yyWarn = new YyWarn();
        yyWarn.setWarnType(1);
        return yyWarnService.selectYyWarnList(yyWarn);
    }

    //测温预警
    @PostMapping("/warnTemperature")
    public List<?> warnTemperature(){
        YyWarn yyWarn = new YyWarn();
        yyWarn.setWarnType(2);
        return yyWarnService.selectYyWarnList(yyWarn);
    }

    //健康码预警
    @PostMapping("/warnHealthCode")
    public List<?> warnHealthCode(){
        YyWarn yyWarn = new YyWarn();
        yyWarn.setWarnType(3);
        return yyWarnService.selectYyWarnList(yyWarn);
    }

    @Autowired
    private IYyYyService yyYyService;
    //医院信息
    @PostMapping("/hos")
    public List<?> hos(){
        return yyYyService.selectYyYyList(new YyYy());
    }


    @Autowired
    private IYyVideoBaseService yyVideoBaseService;
    //视频列表
    @PostMapping("/videoBase")
    public Object videoBase(){
        return yyVideoBaseService.selectYyVideoBaseList(new YyVideoBase()).get(0);
    }

    @Autowired
    private IYyVideoService yyVideoService;
    //视频设备
    @PostMapping("/video")
    public JSONArray video(){
        List<YyVideo> yyVideos = yyVideoService.selectYyVideoListAndName(new YyVideo());
        //归类到以医院为中心
        JSONArray jsonArrayNew = new JSONArray();
        HashMap mapNew = new HashMap();
        HashSet hashSet = new HashSet();
        for(int i = 0; i < yyVideos.size(); i++){
            hashSet.add(yyVideos.get(i).getHosId());
        }
        List<Long> list = new ArrayList<Long>(hashSet);
        for(int j = 0; j < hashSet.size(); j++){
            Long hosId = list.get(j);
            String hosName = null;
            JSONArray jsonArray = new JSONArray();
            HashMap mapChildren = new HashMap();
            for(int k = 0; k < yyVideos.size(); k++){
                if(hosId.equals(yyVideos.get(k).getHosId())){
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(yyVideos.get(k)));
                    jsonArray.add(jsonObject);
                    hosName = jsonObject.getString("hosName");
                }
            }
            mapChildren.put("name",hosName);
            mapChildren.put("children",jsonArray);
            jsonArrayNew.add(mapChildren);
        }
        return jsonArrayNew;
    }




}
