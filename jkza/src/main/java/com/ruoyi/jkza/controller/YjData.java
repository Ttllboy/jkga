package com.ruoyi.jkza.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.jkza.domain.*;
import com.ruoyi.jkza.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/yjData")
public class YjData {

    @Autowired
    private IYjLowPointService yjLowPointService;

    @Autowired
    private IYjMonitorEquipmentService yjMonitorEquipmentService;

    @Autowired
    private IYjSynergyService yjSynergyService;

    @Autowired
    private IYjWarnService yjWarnService;

    @Autowired
    private IYjAvoidDisasterService yjAvoidDisasterService;

    @Autowired
    private IYjMaterialService yjMaterialService;

    //所有低洼点信息
    @PostMapping("/lowPointList")
    public List<?> getlowPointList(){
        YjLowPoint yjLowPoint = new YjLowPoint();
        List<YjLowPoint> yjLowPoints = yjLowPointService.selectYjLowPointList(yjLowPoint);
        return yjLowPoints;
    }

    //监测设备基础数据
    @PostMapping("/monitorEquipment")
    public Object getMonitorEquipment(){
        YjMonitorEquipment yjMonitorEquipment = new YjMonitorEquipment();
        List<YjMonitorEquipment> yjMonitorEquipments = yjMonitorEquipmentService.selectYjMonitorEquipmentList(yjMonitorEquipment);
        return yjMonitorEquipments.get(0);
    }

    //多跨协同
    @PostMapping("/synergy")
    public List<?> getSynergy(){
        YjSynergy yjSynergy = new YjSynergy();
        List<YjSynergy> yjSynergies = yjSynergyService.selectYjSynergyList(yjSynergy);
        return yjSynergies;
    }

    //预警信息
    @PostMapping("/warn")
    public List<?> getWarn(){
        YjWarn yjWarn = new YjWarn();
        List<YjWarn> yjWarnList = yjWarnService.selectYjWarnList(yjWarn);
        return yjWarnList;
    }

    //避灾场所列表信息
    @PostMapping("/avoidDisasterList")
    public List<?> getAvoidDisasterList(){
        YjAvoidDisaster yjAvoidDisaster = new YjAvoidDisaster();
        List<YjAvoidDisaster> yjAvoidDisasters = yjAvoidDisasterService.selectYjAvoidDisasterList(yjAvoidDisaster);
        return yjAvoidDisasters;
    }

    //单个避灾场所信息
    @GetMapping("/avoidDisaster/{id}")
    public Object getAvoidDisaster(@PathVariable("id") Long id){
        YjAvoidDisaster yjAvoidDisaster = new YjAvoidDisaster();
        yjAvoidDisaster = yjAvoidDisasterService.selectYjAvoidDisasterById(id);
        return yjAvoidDisaster;
    }

    //物资信息
    @PostMapping("/material")
    public List<?> getMaterial(){
        YjMaterial yjMaterial = new YjMaterial();
        List<YjMaterial> yjMaterials = yjMaterialService.selectYjMaterialList(yjMaterial);
        return yjMaterials;
    }

    @Autowired
    private IYjOperatorService yjOperatorService;
    //值班人员
    @PostMapping("/operator")
    public List<?> operator(){
        return yjOperatorService.selectYjOperatorList(new YjOperator());
    }

    @Autowired
    private IYjVideoBaseService yjVideoBaseService;
    //视频列表
    @PostMapping("/videoBase")
    public Object videoBase(){
        return yjVideoBaseService.selectYjVideoBaseList(new YjVideoBase()).get(0);
    }

    @Autowired
    private IYjVideoService yjVideoService;
    //视频设备
    @PostMapping("/video")
    public JSONArray video(){
        List<YjVideo>  yjVideos= yjVideoService.selectYjVideoListAndName(new YjVideo());
        //归类到以低洼点为中心
        JSONArray jsonArrayNew = new JSONArray();
        HashMap mapNew = new HashMap();
        HashSet hashSet = new HashSet();
        for(int i = 0; i < yjVideos.size(); i++){
            hashSet.add(yjVideos.get(i).getBuildingSiteId());
        }
        List<Long> list = new ArrayList<Long>(hashSet);
        for(int j = 0; j < hashSet.size(); j++){
            Long buildingSiteId = list.get(j);
            String buildingSiteName = null;
            JSONArray jsonArray = new JSONArray();
            HashMap mapChildren = new HashMap();
            for(int k = 0; k < yjVideos.size(); k++){
                if(buildingSiteId.equals(yjVideos.get(k).getBuildingSiteId())){
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(yjVideos.get(k)));
                    jsonArray.add(jsonObject);
                    buildingSiteName = jsonObject.getString("lowPointName");
                }
            }
            mapChildren.put("name",buildingSiteName);
            mapChildren.put("children",jsonArray);
            jsonArrayNew.add(mapChildren);
        }
        return jsonArrayNew;
    }




}
