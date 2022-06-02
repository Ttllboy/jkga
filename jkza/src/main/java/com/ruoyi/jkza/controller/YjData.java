package com.ruoyi.jkza.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.jkza.domain.*;
import com.ruoyi.jkza.mapper.YjMaterialMapper;
import com.ruoyi.jkza.mapper.YjZbdrMapper;
import com.ruoyi.jkza.service.*;
import com.ruoyi.jkza.util.VideoLive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private IYjZbdrService yjZbdrService;
    //值班人员
    @PostMapping("/operator")
    public List<?> operator(){
//        return yjOperatorService.selectYjOperatorList(new YjOperator());
        return yjZbdrService.selectYjZbdrList(new YjZbdr());
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
        //下面是从大华调取视频设备接口的方法
        //分页查询个人有权限视频通道资源
        String url = "resource-catalog/tripartite/authority/channel/page";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNo",1);
        jsonObject.put("pageSize",200);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("keyword","");
        jsonObject.put("param",jsonObject1);
        String body = VideoLive.getVideoData(jsonObject,url,"yjglj001","yjglj12345");
        JSONObject videoList = JSONObject.parseObject(body);
        JSONArray jsonArray = videoList.getJSONArray("records");
        int videoTotal = jsonArray.size();
        int videoOnline = 0;
        for (int q = 0; q < videoTotal; q++) {
            JSONObject recordOnline = jsonArray.getJSONObject(q);
            if (recordOnline.getString("networkStatus").equals("1")) {
                videoOnline++;
            }
        }
        YjVideoBase yjVideoBase = new YjVideoBase();
        yjVideoBase.setId(1L);
        yjVideoBase.setVideoSum((long) videoTotal);
        yjVideoBase.setVideoDuty((long) videoOnline);
        yjVideoBaseService.updateYjVideoBase(yjVideoBase);
        return jsonArray;
//        List<YjVideo>  yjVideos= yjVideoService.selectYjVideoListAndName(new YjVideo());
//        //归类到以低洼点为中心
//        JSONArray jsonArrayNew = new JSONArray();
//        HashMap mapNew = new HashMap();
//        HashSet hashSet = new HashSet();
//        for(int i = 0; i < yjVideos.size(); i++){
//            hashSet.add(yjVideos.get(i).getBuildingSiteId());
//        }
//        List<Long> list = new ArrayList<Long>(hashSet);
//        for(int j = 0; j < hashSet.size(); j++){
//            Long buildingSiteId = list.get(j);
//            String buildingSiteName = null;
//            JSONArray jsonArray = new JSONArray();
//            HashMap mapChildren = new HashMap();
//            for(int k = 0; k < yjVideos.size(); k++){
//                if(buildingSiteId.equals(yjVideos.get(k).getBuildingSiteId())){
//                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(yjVideos.get(k)));
//                    jsonArray.add(jsonObject);
//                    buildingSiteName = jsonObject.getString("lowPointName");
//                }
//            }
//            mapChildren.put("name",buildingSiteName);
//            mapChildren.put("children",jsonArray);
//            jsonArrayNew.add(mapChildren);
//        }
//        return jsonArrayNew;
    }
    @Autowired
    private YjZbdrMapper yjZbdrMapper;

    @PostMapping("/zbdr")
    @ResponseBody
    public Integer uploadFile(MultipartFile file, HttpServletResponse response)throws Exception {
        try {
            InputStream inputStream = file.getInputStream();
            //解析excel文件
            ExcelReader reader = ExcelUtil.getReader(inputStream,0);//指定输入流和sheet
            // 读取第二行到最后一行数据
            List<List<Object>> read = reader.read(1,reader.getRowCount());
            List<YjZbdr> zbdrs = new ArrayList<>();
            for(List<Object> objects : read){
                YjZbdr zbdr = new YjZbdr();
                if((objects.get(0).toString()).length() >9){
                    zbdr.setZbDate((objects.get(0).toString()).substring(0,10));
                }else {
                    zbdr.setZbDate(objects.get(0).toString());
                }
                zbdr.setWeekDate((objects.get(1)).toString());
                zbdr.setLeaderName((objects.get(2)).toString());
                zbdr.setLeaderPhone((objects.get(3)).toString());
                zbdr.setChiefName((objects.get(4)).toString());
                zbdr.setChiefPhone((objects.get(5)).toString());
                zbdr.setDayNameOne((objects.get(6)).toString());
                zbdr.setDayNameTwo((objects.get(7)).toString());
                zbdr.setNightNameOne((objects.get(8)).toString());
                zbdr.setNightNameTwo((objects.get(9)).toString());
                zbdrs.add(zbdr);
            }
            yjZbdrMapper.batchYjZbdr(zbdrs);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
    @Autowired
    private YjMaterialMapper yjMaterialMapper;
    @PostMapping("/wzdr")
    @ResponseBody
    public Integer wzdr(MultipartFile file, HttpServletResponse response)throws Exception {
        try {
            InputStream inputStream = file.getInputStream();
            //解析excel文件
            ExcelReader reader = ExcelUtil.getReader(inputStream,0);//指定输入流和sheet
            // 读取第二行到最后一行数据
            List<List<Object>> read = reader.read(1,reader.getRowCount());
            List<YjMaterial> materials = new ArrayList<>();
            for(List<Object> objects : read){
                YjMaterial material = new YjMaterial();

                material.setMaterialStoreName(objects.get(0).toString());
                material.setMaterialLv((objects.get(1)).toString());
                material.setDetailAddress((objects.get(2)).toString());
                material.setAffiliatedUnit((objects.get(3)).toString());
                material.setContactName((objects.get(4)).toString());
                material.setContactPhone((objects.get(5)).toString());
                material.setEmergencyMaterialName((objects.get(6)).toString());
                material.setSpecification((objects.get(7)).toString());
                material.setEmergencyMaterialNum(Long.valueOf((objects.get(8)).toString()));
                material.setMeasuringUnit((objects.get(9)).toString());
                materials.add(material);
            }
            yjMaterialMapper.batchYjMaterial(materials);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

}
