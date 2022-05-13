package com.ruoyi.jkza.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.jkza.domain.*;
import com.ruoyi.jkza.service.*;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/stData")
public class StData {

    @Autowired
    private IStBaseService stBaseService;

    //生态页面基础数据
    @PostMapping("/base")
    public Object operator(){
        return stBaseService.selectStBaseList(new StBase()).get(0);
    }

    @Autowired
    private IStCapacityRankService stCapacityRankService;

    //生态页面固废点容量排名
    @PostMapping("/capacityRank")
    public List<?> capacityRank(){
        return stCapacityRankService.selectStCapacityRankList(new StCapacityRank());
    }

    @Autowired
    private IStIllegalTrendService stIllegalTrendService;

    //生态页面非法情况趋势图
    @PostMapping("/illegalTrend")
    public List<?> illegalTrend(){
        return stIllegalTrendService.selectStIllegalTrendList(new StIllegalTrend());
    }

    @Autowired
    private IStWarnProportionService stWarnProportionService;

    //生态页面预警信息占比
    @PostMapping("/warnProportion")
    public Object warnProportion(){
        return stWarnProportionService.selectStWarnProportionList(new StWarnProportion()).get(0);
    }

    @Autowired
    private IStIncidentProcessService stIncidentProcessService;

    //生态页面事件处理
    @PostMapping("/incidentProcess")
    public List<?> incidentProcess(){
        return stIncidentProcessService.selectStIncidentProcessList(new StIncidentProcess());
    }

    @Autowired
    private IStWarnService stWarnService;

    //生态页面非法捕捞预警信息
    @PostMapping("/warnFish")
    public List<?> warnFish(){
        StWarn stWarn = new StWarn();
        stWarn.setWarnType(0);
        return stWarnService.selectStWarnList(stWarn);
    }
    //生态页面非法倾倒预警信息
    @PostMapping("/warnDump")
    public List<?> warnDump(){
        StWarn stWarn = new StWarn();
        stWarn.setWarnType(1);
        return stWarnService.selectStWarnList(stWarn);
    }
    //生态页面重点人员预警信息
    @PostMapping("/warnPerson")
    public List<?> warnPerson(){
        StWarn stWarn = new StWarn();
        stWarn.setWarnType(2);
        return stWarnService.selectStWarnList(stWarn);
    }
    //生态页面重点车辆预警信息
    @PostMapping("/warnCar")
    public List<?> warnCar(){
        StWarn stWarn = new StWarn();
        stWarn.setWarnType(3);
        return stWarnService.selectStWarnList(stWarn);
    }

    @Autowired
    private IStSolidWasteService stSolidWasteService;

    //生态页面固废点
    @PostMapping("/solidWaste")
    public List<?> solidWaste(){
        return stSolidWasteService.selectStSolidWasteList(new StSolidWaste());
    }

    @Autowired
    private IStVideoBaseService stVideoBaseService;

    //生态页面视频列表
    @PostMapping("/videoBase")
    public Object videoBase(){
        return stVideoBaseService.selectStVideoBaseList(new StVideoBase()).get(0);
    }

    @Autowired
    private IStVideoService stVideoService;

    //生态页面视频设备
    @PostMapping("/video")
    public JSONArray video(){
        List<StVideo> stVideos = stVideoService.selectStVideoListAndName(new StVideo());
        //归类到以固废点为中心
        JSONArray jsonArrayNew = new JSONArray();
        HashMap mapNew = new HashMap();
        HashSet hashSet = new HashSet();
        for(int i = 0; i < stVideos.size(); i++){
            hashSet.add(stVideos.get(i).getSolidId());
        }
        List<Long> list = new ArrayList<Long>(hashSet);
        for(int j = 0; j < hashSet.size(); j++){
            Long solidId = list.get(j);
            String solidName = null;
            JSONArray jsonArray = new JSONArray();
            HashMap mapChildren = new HashMap();
            for(int k = 0; k < stVideos.size(); k++){
                if(solidId.equals(stVideos.get(k).getSolidId())){
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(stVideos.get(k)));
                    jsonArray.add(jsonObject);
                    solidName = jsonObject.getString("solidName");
                }
            }
            mapChildren.put("name",solidName);
            mapChildren.put("children",jsonArray);
            jsonArrayNew.add(mapChildren);
        }
        return jsonArrayNew;
    }




}
