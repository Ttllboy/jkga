package com.ruoyi.jkza.controller;


import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONWriter;
import com.dahuatech.hutool.http.Method;
import com.dahuatech.icc.exception.ClientException;
import com.dahuatech.icc.oauth.http.DefaultClient;
import com.dahuatech.icc.oauth.http.IClient;
import com.dahuatech.icc.oauth.model.v202010.GeneralRequest;
import com.dahuatech.icc.oauth.model.v202010.GeneralResponse;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.jkza.domain.*;
import com.ruoyi.jkza.mapper.*;
import com.ruoyi.jkza.service.*;
import com.ruoyi.jkza.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/yyData")
public class YyData {

    @Autowired
    private YyCwmjMapper yyCwmjMapper;
    @Autowired
    private MqCarMapper mqCarMapper;

    private String camaraIp = "http://133.1.3.15:9400";

    //医院基础信息
    @Autowired
    private IYyBaseService yyBaseService;
    @PostMapping("/base")
    public Object base(){
        YyBase yyBase = new YyBase();
        yyBase.setId(1L);
        List<YyYy> list = yyYyMapper.selectYyYyList(new YyYy());
        yyBase.setHosAccess((long) list.size());
        yyBase.setHosTotal((long) list.size());
        yyBaseService.updateYyBase(yyBase);
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
//        YyWarn yyWarn = new YyWarn();
//        yyWarn.setWarnType(2);
//        return yyWarnService.selectYyWarnList(yyWarn);
        List<YyCwmj> list = yyCwmjMapper.selectYyCwmjList(new YyCwmj());
        List<YyCwmj> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            YyCwmj cwmj = list.get(i);
            Double curTemp = cwmj.getCurTemp();
            if (curTemp > 37.2) {
                list2.add(cwmj);
            }
        }
        return list2;
    }

    //健康码预警
    @PostMapping("/warnHealthCode")
    public List<?> warnHealthCode(){
//        YyWarn yyWarn = new YyWarn();
//        yyWarn.setWarnType(3);
//        return yyWarnService.selectYyWarnList(yyWarn);
        List<YyCwmj> list = yyCwmjMapper.selectYyCwmjList(new YyCwmj());
        List<YyCwmj> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            YyCwmj cwmj = list.get(i);
            String  healthCode = cwmj.getHealthMode();
            if (healthCode.equals("黄码") || healthCode.equals("红码")) {
                list2.add(cwmj);
            }
        }
        return list2;
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
        freshYyVideo();
        List<YyVideo> list =  yyVideoService.selectYyVideoList(new YyVideo());
        HashSet hashSet = new HashSet();
        for (int i = 0; i < list.size(); i++) {
            YyVideo video = list.get(i);
            if (video.getHosName() != null){
                hashSet.add(video.getHosName());
            }
        }
        List set = new ArrayList(hashSet);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < set.size(); i++) {
            List<YyVideo> list1 = new ArrayList<>();
            JSONObject jsonObject = new JSONObject();
            String name = (String) set.get(i);
            for (int i1 = 0; i1 < list.size(); i1++) {
                if(list.get(i1).getHosName() != null){
                    if (list.get(i1).getHosName().equals(name)) {
                        list1.add(list.get(i1));
                    }
                }
            }
            jsonObject.put("name",name);
            jsonObject.put("list",list1);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    public void freshYyVideo(){
        yyVideoMapper.truncateYyVideo();
//        gdBaseMapper.truncateTabel("yy_yy");
        //下面是从大华调取视频设备接口的方法
        //分页查询个人有权限视频通道资源
//        String url = "resource-catalog/tripartite/authority/channel/page";
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("pageNo", 1);
//        jsonObject.put("pageSize", 200);
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("keyword", "");
//        jsonObject.put("param", jsonObject1);
//        String body = VideoLive.getVideoData(jsonObject, url, "shfzj001", "shfzj12345");
//        JSONObject videoList = JSONObject.parseObject(body);
//        JSONArray jsonArray = videoList.getJSONArray("records");

        JSONArray jsonArray = new JSONArray();
        int temp = 1;
        while (temp > 0){
            JSONArray jsonArrayRe = forVideo(temp,"shfzj002","shfzj12345");
            for (int i = 0; i < jsonArrayRe.size(); i++) {
                jsonArray.add(jsonArrayRe.getJSONObject(i));
            }
            if(jsonArrayRe.size() == 200){
                temp = temp + 1;
            }else {
                temp = 0;
            }
        }


        YyVideoBase yyVideoBase = new YyVideoBase();
        yyVideoBase.setId(1L);

        long videoOnline = 0;
        HashSet set = new HashSet();
        Integer videoTotal = 0;
        for (int q = 0; q < jsonArray.size(); q++) {
            JSONObject recordOnline = jsonArray.getJSONObject(q);
            YyVideo video = new YyVideo();
            video.setChannelName(recordOnline.getString("channelName"));
            video.setCameraType(recordOnline.getInteger("cameraType"));
            video.setNetworkStatus(recordOnline.getInteger("networkStatus"));
            video.setChannelCode(recordOnline.getString("channelCode"));
            video.setGpsX(recordOnline.getDouble("gpsX"));
            video.setGpsY(recordOnline.getDouble("gpsY"));
            video.setJscId(4);
            video.setDataType(0);
            List<HashMap> list = yyVideoMapper.selectYyClassify(recordOnline.getString("channelCode"));
            for (int i = 0; i < list.size(); i++) {
                HashMap map = list.get(i);
                if (map != null) {
                    if (!map.get("scene_name").equals("医院车辆出入口")) {
                        if(!map.get("scene_name").equals("医院人行出入口")){
                            video.setHosName((String) map.get("scene_name"));
                            set.add((String) map.get("scene_name"));
                        }
                    }
                }
            }
            if(video.getHosName() != null){
                videoTotal++;
            }else {
                continue;
            }
            if (recordOnline.getString("networkStatus").equals("1")) {
                videoOnline++;
            }
            yyVideoService.insertYyVideo(video);
        }
        List<YyYy> list = yyYyMapper.selectYyYyList(new YyYy());
        yyVideoBase.setVideoDuty(videoOnline);
        yyVideoBase.setVideoSum((long) videoTotal);
        yyVideoBaseService.updateYyVideoBase(yyVideoBase);
        YyBase yyBase = new YyBase();
        yyBase.setId(1L);
        yyBase.setMonitor((long) videoTotal);
        yyBase.setMonitorOnline(videoOnline);
        yyBase.setFaceCamera((long) videoTotal);
        yyBase.setFaceCameraOnline(videoOnline);
        yyBase.setHosTotal((long) list.size());
        yyBase.setHosAccess((long) list.size());
        yyBaseService.updateYyBase(yyBase);
        System.out.println("更新医院视频设备");
    }

    public JSONArray forVideo(Integer pageNo,String userName,String password){
        String url = "resource-catalog/tripartite/authority/channel/page";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNo", pageNo);
        jsonObject.put("pageSize", 200);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("keyword", "");
        jsonObject.put("param", jsonObject1);
        String body = VideoLive.getVideoData(jsonObject, url, userName, password);
        JSONObject videoList = JSONObject.parseObject(body);
        JSONArray jsonArray = videoList.getJSONArray("records");
        return jsonArray;
    }
    //视频设备搜索
    @GetMapping("/videoNameSerch/{videoName}")
    public List<?> videoNameSerch(@PathVariable("videoName") String videoName) {
        YyVideo video = new YyVideo();
        video.setChannelName(videoName);
        List<YyVideo> list = yyVideoService.selectYyVideoList(video);
        return list;
    }

    @GetMapping("/videoLive/{channelCode}")
    public JSONObject videoLive(@PathVariable("channelCode") String channelCode) throws Exception {
        String body = VideoLive.getLive(channelCode, "shfzj002", "shfzj12345");
        JSONObject videoUrl = JSONObject.parseObject(body);
        return videoUrl;
    }
    @Autowired
    private IYyPsychopathControlService yyPsychopathControlService;
    @Autowired
    private YyPsychopathControlMapper yyPsychopathControlMapper;

    //精神病人管控
    @PostMapping("/jsbrgk")
    public JSONObject jsbrgk(@RequestBody JSONObject jsonObject){
        String dateNow = DateUtils.getDate();
        String yearPart = dateNow.substring(0,4);
        YyPsychopathControl yyPsychopathControl = new YyPsychopathControl();
        if(!jsonObject.getString("streetName").isEmpty()){
            yyPsychopathControl.setStreetName(jsonObject.getString("streetName"));
        }
        List<YyPsychopathControl> list1 = yyPsychopathControlService.selectYyPsychopathControlList(yyPsychopathControl);
        Long count = 0L;
        for (int i = 0; i < list1.size(); i++) {
            count = count + list1.get(i).getAddPatientCount();
        }
        JSONArray month = new JSONArray();
        for (int i = 0; i < 12; i++) {
            JSONObject monthCount = new JSONObject();
            yyPsychopathControl.setMonth((long) (i+1));
            yyPsychopathControl.setYear(Long.valueOf(yearPart));
            List<YyPsychopathControl> list = yyPsychopathControlService.selectYyPsychopathControlList(yyPsychopathControl);
            if(list.size() == 0){
                monthCount.put("month",i+1);
                monthCount.put("patientCount",0);
                monthCount.put("addPatientCount",0);
                month.add(monthCount);
            }else {
                List<YyPsychopathControl> list2 = yyPsychopathControlMapper.selectTotal(yyPsychopathControl);
                Long P = list2.stream().collect(Collectors.summingLong(YyPsychopathControl::getAddPatientCount));
                Long A = list.stream().collect(Collectors.summingLong(YyPsychopathControl::getAddPatientCount));
                monthCount.put("patientCount",P);
                monthCount.put("addPatientCount",A);
                monthCount.put("month",i+1);
                month.add(monthCount);
            }
        }
        List<YyPsychopathControl> yyPsychopathControls = yyPsychopathControlService.selectYyPsychopathControlList(new YyPsychopathControl());
        JSONObject reJson = new JSONObject();
        HashSet streetName = new HashSet();
        for (int i = 0; i < yyPsychopathControls.size(); i++) {
            streetName.add(yyPsychopathControls.get(i).getStreetName());
        }
        reJson.put("streetName",streetName);
        reJson.put("list",month);
        reJson.put("count",count);
        return reJson;
    }

    public List<YyPsychopathControl> getBefore(Long year,Long month){
        YyPsychopathControl yyPsychopathControl = new YyPsychopathControl();
        yyPsychopathControl.setMonth(month);
        yyPsychopathControl.setYear(year);
        List<YyPsychopathControl> list = yyPsychopathControlMapper.selectTotal(yyPsychopathControl);
        return list;
    }


    @PostMapping("/cameraApi")
    public JSONObject cameraApi(HttpServletRequest request) throws Exception {
        String header = cameraRegister();
        System.out.println("第一次注册返回的header:"+header);
        DigestAuthInfo authObject = DigestUtils.getAuthInfoObject(header);
        authObject.setCnonce(DigestUtils.generateToken());
        authObject.setUsername("jgkjlj");
        authObject.setUri("/VIID/System/Register");
        System.out.println("添加完成的digest:"+authObject);
        String HA1 = SecureUtil.md5(authObject.getUsername() + ":" + authObject.getRealm() + ":" + "jgkjlj");
        String HD = authObject.getNonce() + ":" + "00000001" + ":" + authObject.getCnonce() + ":"
                + authObject.getQop();
        String HA2 = SecureUtil.md5("POST" + ":" + authObject.getUri());
//
        System.out.println(authObject.getUsername() + ":" + authObject.getRealm() + ":jgkjlj");
        System.out.println(authObject.getNonce() + ":" + "00000001" + ":" + authObject.getCnonce() + ":"
                + authObject.getQop());
        System.out.println("POST" + ":" + authObject.getUri());
        String responseValid = SecureUtil.md5(HA1 + ":" + HD + ":" + HA2);
        System.out.println("加密生成的response:"+responseValid);
        authObject.setResponse(responseValid);
        cameraRegisterSe(authObject);
        return null;
    }
    @Autowired
    private YyVideoMapper yyVideoMapper;
    @Autowired
    private GdBaseMapper gdBaseMapper;

    @Autowired
    private MqBkyjMapper mqBkyjMapper;
    @Autowired
    private YyYyMapper yyYyMapper;

    @Autowired
    private AdmYyCwMapper admYyCwMapper;

    @PostMapping("/test")
    public void test() throws ClientException {
        //删除七天前的测温门禁数据
//        List<YyCwmj> list = yyCwmjMapper.selectYyCwmjList(new YyCwmj());
//        YyCwmj yyCwmj = new YyCwmj();
//        List<YyCwmj> cwmjs = yyCwmjMapper.selectYyCwmjList(new YyCwmj());
//
//        yyCwmj.setHealthMode("绿码");
//        List<YyCwmj> greenCounts = yyCwmjMapper.selectYyCwmjList(yyCwmj);
//        Integer greenCount = greenCounts.size();
//        yyCwmj.setHealthMode("黄码");
//        List<YyCwmj> yellowCounts = yyCwmjMapper.selectYyCwmjList(yyCwmj);
//        Integer yellowCount = yellowCounts.size();
//        yyCwmj.setHealthMode("红码");
//        List<YyCwmj> redCounts = yyCwmjMapper.selectYyCwmjList(yyCwmj);
//        Integer redCount = redCounts.size();
//        Integer cwyc = 0;
//        for (int i = 0; i < cwmjs.size(); i++) {
//            YyCwmj cwmj = cwmjs.get(i);
//            Double curTemp = cwmj.getCurTemp();
//            if (curTemp > 37.2 || curTemp < 35.8) {
//                cwyc++;
//            }
//        }
//        Integer total = greenCount +yellowCount + redCount + cwyc;
//        DecimalFormat dF = new DecimalFormat("0.0000");
//        Double greenPer = Double.valueOf(dF.format((float) greenCount/total));
//        Double yellowPer = Double.valueOf(dF.format((float) yellowCount/total));
//        Double redPer = Double.valueOf(dF.format((float) redCount/total));
//        YyEpidemicPrevention yyEpidemicPrevention = new YyEpidemicPrevention();
//        yyEpidemicPrevention.setId(1L);
//        yyEpidemicPrevention.setGreen(Long.valueOf(greenCount));
//        yyEpidemicPrevention.setYellow(Long.valueOf(yellowCount));
//        yyEpidemicPrevention.setRed(Long.valueOf(redCount));
//        yyEpidemicPrevention.setGreenProportion(greenPer*100);
//        yyEpidemicPrevention.setYellowProportion(yellowPer*100);
//        yyEpidemicPrevention.setRedProportion(redPer*100);
//
//        Double cwycPer = Double.valueOf(dF.format((float) cwyc/total));
//        yyEpidemicPrevention.setTemperatureAbnormal(Long.valueOf(cwyc));
//        yyEpidemicPrevention.setTemperatureProportion(cwycPer*100);
//        yyEpidemicPrevention.setDataType(0);
//        yyEpidemicPreventionService.updateYyEpidemicPrevention(yyEpidemicPrevention);
        System.out.println("医院防疫信息更新");
        YyCwmj yyCwmj = new YyCwmj();
        List<YyCwmj> cwmjs = yyCwmjMapper.selectYyCwmjList(new YyCwmj());

        yyCwmj.setHealthMode("绿码");
        List<YyCwmj> greenCounts = yyCwmjMapper.selectYyCwmjList(yyCwmj);
        Integer greenCount = greenCounts.size();
        yyCwmj.setHealthMode("黄码");
        List<YyCwmj> yellowCounts = yyCwmjMapper.selectYyCwmjList(yyCwmj);
        Integer yellowCount = yellowCounts.size();
        yyCwmj.setHealthMode("红码");
        List<YyCwmj> redCounts = yyCwmjMapper.selectYyCwmjList(yyCwmj);
        Integer redCount = redCounts.size();
        Integer cwyc = 0;
        for (int i = 0; i < cwmjs.size(); i++) {
            YyCwmj cwmj = cwmjs.get(i);
            Double curTemp = cwmj.getCurTemp();
            if (curTemp > 37.2) {
                cwyc++;
            }
        }
        Integer total = greenCount + yellowCount + redCount + cwyc;
        if(total != 0){
            DecimalFormat dF = new DecimalFormat("0.0000");
            Double greenPer = Double.valueOf(dF.format((float) greenCount / total));
            Double yellowPer = Double.valueOf(dF.format((float) yellowCount / total));
            Double redPer = Double.valueOf(dF.format((float) redCount / total));
            YyEpidemicPrevention yyEpidemicPrevention = new YyEpidemicPrevention();
            yyEpidemicPrevention.setId(1L);
            yyEpidemicPrevention.setGreen(Long.valueOf(greenCount));
            yyEpidemicPrevention.setYellow(Long.valueOf(yellowCount));
            yyEpidemicPrevention.setRed(Long.valueOf(redCount));
            yyEpidemicPrevention.setGreenProportion(greenPer * 100);
            yyEpidemicPrevention.setYellowProportion(yellowPer * 100);
            yyEpidemicPrevention.setRedProportion(redPer * 100);

            Double cwycPer = Double.valueOf(dF.format((float) cwyc / total));
            yyEpidemicPrevention.setTemperatureAbnormal(Long.valueOf(cwyc));
            yyEpidemicPrevention.setTemperatureProportion(cwycPer * 100);
            yyEpidemicPrevention.setDataType(0);
            yyEpidemicPreventionService.updateYyEpidemicPrevention(yyEpidemicPrevention);
        }else {
            YyEpidemicPrevention yyEpidemicPrevention = new YyEpidemicPrevention();
            yyEpidemicPrevention.setId(1L);
            yyEpidemicPrevention.setGreen(0L);
            yyEpidemicPrevention.setYellow(0L);
            yyEpidemicPrevention.setRed(0L);
            yyEpidemicPrevention.setGreenProportion(0.0);
            yyEpidemicPrevention.setYellowProportion(0.0);
            yyEpidemicPrevention.setRedProportion(0.0);
            yyEpidemicPrevention.setTemperatureAbnormal(0L);
            yyEpidemicPrevention.setTemperatureProportion(0.0);
            yyEpidemicPreventionService.updateYyEpidemicPrevention(yyEpidemicPrevention);
        }

    }
    @Autowired
    private MqRlzpMapper mqRlzpMapper;
    @PostMapping("/rlzp")
    public List<?> rlzp(){
        return mqRlzpMapper.selectMqRlzpLimit();
    }

    //人脸抓拍信息
    @PostMapping("/faceTemperature")
    public List<?> faceTemperature(){
        return yyCwmjMapper.selectLimit();
    }

    public void iccApi(){

    }
    @PostMapping("/car")
    public JSONArray car(){
        List<MqCar> list = mqCarMapper.selectMqCarList2(new MqCar());
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            MqCar mqCar = list.get(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("img",mqCar.getCarImgUrl());
            Date date = new Date();
            Long time = mqCar.getCapTime();
            date.setTime(time);
            jsonObject.put("time",date);
            List<HashMap> list1 = yyVideoMapper.selectYyClassify(mqCar.getChannelCode());
            for (int j = 0; j < list1.size(); j++) {
                HashMap map = list1.get(j);
                if (map != null) {
                    if (!map.get("scene_name").equals("医院车辆出入口")) {
                        if(!map.get("scene_name").equals("医院人行出入口")){
                            jsonObject.put("place",(String) map.get("scene_name"));
                        }
                    }
                }else {
                    jsonObject.put("place","");
                }
            }
            jsonObject.put("carId",mqCar.getPlateNum());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    //删除测温门禁数据
//    @PostMapping("/delete")
    public void deleteCwmj(){
        List<YyCwmj> list = yyCwmjMapper.selectYyCwmjList(new YyCwmj());
        Date date2 = new Date();
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            YyCwmj cwmj = list.get(i);
            Date date1 = new Date(cwmj.getCapTime());
            if (cwmj.getCapTime() == null) {
                ids.add(cwmj.getId());
            }
            Integer day = DateUtils.differentDaysByMillisecond(date1,date2);
            System.out.println(day);
            if(day > 7){
                ids.add(cwmj.getId());
            }
            if(ids.size() > 1000){
                Long[] idsSub = ids.toArray(new Long[list.size()]);
                yyCwmjMapper.deleteYyCwmjByIds(idsSub);
                ids.clear();
            }
        }
        Long[] idsSub = ids.toArray(new Long[list.size()]);
        yyCwmjMapper.deleteYyCwmjByIds(idsSub);
    }
    //删除重点人员
//    @PostMapping("/delete")
    public void deleteZdry(){
        List<MqBkyj> list = mqBkyjMapper.selectMqBkyjList(new MqBkyj());
        Date date2 = new Date();
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            MqBkyj mqBkyj = list.get(i);
            Date date1 = new Date(mqBkyj.getAlarmTime());
            if(mqBkyj.getAlarmTime() == null){
                ids.add(mqBkyj.getId());
            }
            Integer day = DateUtils.differentDaysByMillisecond(date1,date2);
            if(day > 7){
                ids.add(mqBkyj.getId());
                System.out.println(day);
            }
            if(ids.size() > 1000){
                Long[] idsSub = ids.toArray(new Long[list.size()]);
                mqBkyjMapper.deleteMqBkyjByIds(idsSub);
                ids.clear();
            }
        }
        Long[] idsSub = ids.toArray(new Long[list.size()]);
        mqBkyjMapper.deleteMqBkyjByIds(idsSub);
    }

//    @PostMapping("/delete")
    public void deMqCar(){
        List<MqCar> list = mqCarMapper.selectMqCarList(new MqCar());
        Date date2 = new Date();
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            MqCar mqCar = list.get(i);
            Date date1 = new Date(mqCar.getCapTime());
            if(mqCar.getCapTime() == null){
                ids.add(mqCar.getId());
            }
            Integer day = DateUtils.differentDaysByMillisecond(date1,date2);
            if(day > 7){
                ids.add(mqCar.getId());
                System.out.println(day);
            }
            if(ids.size() > 1000){
                Long[] idsSub = ids.toArray(new Long[list.size()]);
                mqCarMapper.deleteMqCarByIds(idsSub);
                ids.clear();
            }
        }
        Long[] idsSub = ids.toArray(new Long[list.size()]);
        mqCarMapper.deleteMqCarByIds(idsSub);
    }
//    @PostMapping("/delete")
    public void deMqRlzp() {
        List<MqRlzp> list = mqRlzpMapper.selectMqRlzpList(new MqRlzp());
        Date date2 = new Date();
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            MqRlzp mqRlzp = list.get(i);
            Date date1 = new Date(mqRlzp.getCapTime());
            if(mqRlzp.getCapTime() == null){
                ids.add(mqRlzp.getId());
            }
            Integer day = DateUtils.differentDaysByMillisecond(date1,date2);
            if(day > 7){
                ids.add(mqRlzp.getId());
                System.out.println(day);
            }
            if(ids.size() > 1000){
                Long[] idsSub = ids.toArray(new Long[1000]);
                mqRlzpMapper.deleteMqRlzpByIds(idsSub);
                ids.clear();
            }
        }
        Long[] idsSub = ids.toArray(new Long[1000]);
        mqRlzpMapper.deleteMqRlzpByIds(idsSub);
    }






    public String cameraRegister()throws Exception{
        String url = camaraIp + "/VIID/System/Register";
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject.put("RegisterObject", jsonObject1);
        jsonObject1.put("DeviceID", "33049900005030015406");
        JSONObject data = HttpClientUtils.cameraApiPost(url, jsonObject);
        return data.getString("header");
    }
    public void cameraRegisterSe(DigestAuthInfo authObject)throws Exception{
        String url = camaraIp + "/VIID/System/Register";
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject.put("RegisterObject", jsonObject1);
        jsonObject1.put("DeviceID", "33049900005030015406");
        String Authorization = "Digest username=\"" + authObject.getUsername()
                +"\"," + "realm=\"" + authObject.getRealm()
                +"\"," + "nonce=\"" + authObject.getNonce()
                +"\"," + "uri=\"" + authObject.getUri()
                +"\"," + "response=\"" + authObject.getResponse()
                +"\"," + "algorithm=\"" + "MD5"
                +"\"," + "qop=" + authObject.getQop()
                +"," + "nc=" + "00000001"
                +"," + "cnonce=\"" + authObject.getCnonce()
                +"\""
                ;
        System.out.println("二次注册添加的请求头"+Authorization);
        JSONObject data = HttpClientUtils.cameraApiPostSe(url, jsonObject,Authorization);
        System.out.println("二次注册返回结果："+data);
    }

    @Autowired
    private YyBaseMapper yyBaseMapper;
    @PostMapping("/cwmjIsOnline")
    public void cwmjIsOnline() throws ClientException {
        List<AdmYyCw> list = admYyCwMapper.selectAdmYyCwList(new AdmYyCw());
        for (int i = 0; i < list.size(); i++) {
            AdmYyCw yyCw = list.get(i);
            String deviceCode = yyCw.getDeviceCode();
            yyCw.setOnlineType(getIsOnline(deviceCode));
            admYyCwMapper.updateAdmYyCw(yyCw);
        }
        YyBase base = new YyBase();
        AdmYyCw admYyCw = new AdmYyCw();
        admYyCw.setOnlineType(1);
        List<AdmYyCw> list2 = admYyCwMapper.selectAdmYyCwList(admYyCw);
        base.setId(1L);
        base.setTemperature((long) list.size());
        base.setTemperatureOnline((long) list2.size());
        yyBaseMapper.updateYyBase(base);
    }
    //测温一体机数量在线状态
    public Integer getIsOnline(String deviceCode) throws ClientException {
        IClient iClient = new DefaultClient("100.100.0.207", "jgkj-lj", "abca8ed2-6a9e-4af8-8451-daeed722e04b");
        GeneralRequest generalRequest = new GeneralRequest("/evo-apigw/evo-brm/1.0.0/device/" + deviceCode, Method.GET);
        GeneralResponse generalResponse = iClient.doAction(generalRequest, generalRequest.getResponseClass());
        JSONObject jsonObject = JSONObject.parseObject(generalResponse.getResult());
        JSONObject data = jsonObject.getJSONObject("data");
        System.out.println(deviceCode+":"+data);
        JSONObject unit = data.getJSONArray("units").getJSONObject(0);
        JSONObject channel = unit.getJSONArray("channels").getJSONObject(0);
        Integer isOnline = channel.getInteger("isOnline");
        return isOnline;
    }




}
