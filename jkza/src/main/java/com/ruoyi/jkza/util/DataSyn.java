package com.ruoyi.jkza.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dahuatech.hutool.http.Method;
import com.dahuatech.icc.exception.ClientException;
import com.dahuatech.icc.oauth.http.DefaultClient;
import com.dahuatech.icc.oauth.http.IClient;
import com.dahuatech.icc.oauth.model.v202010.GeneralRequest;
import com.dahuatech.icc.oauth.model.v202010.GeneralResponse;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.jkza.config.Config2RestTemplate;
import com.ruoyi.jkza.config.WebSocket;
import com.ruoyi.jkza.domain.*;
import com.ruoyi.jkza.mapper.*;
import com.ruoyi.jkza.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static com.ruoyi.jkza.controller.YjData.unGZip;

@Configuration
@EnableScheduling
public class DataSyn {


    @Autowired
    private IStProjectService stProjectService;

    @Autowired
    private IGdStreetService gdStreetService;

    @Autowired
    private IGdBuildingService gdBuildingService;

    @Autowired
    private GdBuildingMapper gdBuildingMapper;

    @Transactional
    @Scheduled(cron = "0 30 3 * * ?")
    //复制stproject表到gdbuilding表
    public void stProjectSyn() throws Exception {
        System.out.println("工地项目更新");
        gdBuildingMapper.truncateGdBuilding();
        gdBuildingMapper.copyToGdBuilding();
    }

    @Autowired
    private LtdqExcessivestatisticaldataMapper ltdqExcessivestatisticaldataMapper;
    @Autowired
    private GdDustDataMapper gdDustDataMapper;
    @Autowired
    private IGdDustDataService gdDustDataService;

    @Transactional
    @Scheduled(cron = "0 0 4 * * ?")
    //从ltdq方欣扬尘设备数据，保存到GdDustData表
    public void ltdqSyn() throws Exception {
        List<LtdqExcessivestatisticaldata> list = ltdqExcessivestatisticaldataMapper.selectLtdqExcessivestatisticaldataList(new LtdqExcessivestatisticaldata());
        for(int i = 0; i < list.size(); i++){
            LtdqExcessivestatisticaldata ltdqExcessivestatisticaldata = list.get(i);
            GdDustData gdDustData = new GdDustData();
            GdBuilding gdBuilding = gdBuildingMapper.selectGdBuildingByGuid(ltdqExcessivestatisticaldata.getProjectguid());
            if(!(gdBuilding == null)){
                gdDustData.setBuildingId(gdBuilding.getId());
            }
            gdDustData.setEquipmentNum(ltdqExcessivestatisticaldata.getDevicesn());
            gdDustData.setDate(ltdqExcessivestatisticaldata.getCreatedatet());
            gdDustData.setPm25(ltdqExcessivestatisticaldata.getPm25());
            gdDustData.setPm10(ltdqExcessivestatisticaldata.getPm10());
            gdDustData.setTemperature(ltdqExcessivestatisticaldata.getPd04());
            gdDustData.setHumidity(ltdqExcessivestatisticaldata.getPd05());
            gdDustData.setNoise(ltdqExcessivestatisticaldata.getPd09());
            gdDustDataMapper.insertGdDustData(gdDustData);
        }
        //上面是从方欣那边获取数据保存到gddustdata表
        //下面是造假数据
//        Calendar calendar = Calendar.getInstance();
//        Random r = new Random();
//        DecimalFormat df = new DecimalFormat("#.##");
//        List<GdBuilding> gdBuildings = gdBuildingService.selectGdBuildingList(new GdBuilding());
//        for (int i = 0; i < gdBuildings.size(); i++) {
//            double pm25 = r.nextDouble() * 10 + 10;
//            pm25 = Double.parseDouble(df.format(pm25));
//            double pm10 = r.nextDouble() * 10 + 20;
//            pm10 = Double.parseDouble(df.format(pm10));
//            GdBuilding gdBuilding = gdBuildings.get(i);
//            GdDustData gdDustData = new GdDustData();
//            gdDustData.setBuildingId(gdBuilding.getId());
//            gdDustData.setEquipmentNum(gdBuilding.getProjectInfoNum());
//            gdDustData.setDate(calendar.getTime());
//            gdDustData.setPm25(pm25);
//            gdDustData.setPm10(pm10);
//            gdDustDataService.insertGdDustData(gdDustData);
//        }
//        gdDustTotal();
    }


    @Autowired
    private GdDustDataTotalMapper gdDustDataTotalMapper;

    @Transactional
    public void gdDustTotal() throws Exception {
        GdDustDataTotal gdDustDataTotal = new GdDustDataTotal();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Random r = new Random();
        Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        gdDustDataTotal.setDate(date);
        gdDustDataTotal.setPm10(r.nextDouble() * 10 + 20);
        gdDustDataTotal.setPm25(r.nextDouble() * 10 + 10);
        System.out.println(gdDustDataTotal);
        gdDustDataTotalMapper.insertGdDustDataTotal(gdDustDataTotal);
    }

    @Autowired
    private IYyVideoBaseService yyVideoBaseService;
    @Autowired
    private YyVideoMapper yyVideoMapper;
    @Autowired
    private IYyVideoService yyVideoService;
    @Autowired
    private IYyBaseService yyBaseService;

    //定时任务

//    @Scheduled(cron = "0 0 5 * * ?")
//    @Transactional
    public void dataReFresh() {
        yyVideo();
        gdVideo();
        yjVideo();
        stVideo();
        gdDevice();
    }

//    @Scheduled(cron = "0 0 * * * ?")
    public void dataReFresh2() throws Exception {
        getGdCountDuty();
        yyZdry();
        gdTower();
        freshSynergy();
        cwmjIsOnline();
        fyxxSyn();
        yyGzxx();
        eventDb();
    }
    @Scheduled(cron = "0/10 * * * * ?")
    public void checkData(){
        //检查工地base数据是否正常
        try{
            List<GdBase> gdBases = gdBaseMapper.selectGdBaseList(new GdBase());
            if (gdBases.size() == 0) {
                getGdCountDuty();
            }
            if(gdBases.size() > 1){
                gdBaseMapper.truncateTabel("gd_base");
                getGdCountDuty();
            }
        } catch (Exception e) {
        System.out.println("未连接专网");
    }

    }



    //更新医院视频设备
    @Autowired
    private YyYyMapper yyYyMapper;

    @Transactional
    public void yyVideo() {
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
        while (temp > 0) {
            JSONArray jsonArrayRe = forVideo(temp, "shfzj002", "shfzj12345");
            for (int i = 0; i < jsonArrayRe.size(); i++) {
                jsonArray.add(jsonArrayRe.getJSONObject(i));
            }
            if (jsonArrayRe.size() == 200) {
                temp = temp + 1;
            } else {
                temp = 0;
            }
        }


        YyVideoBase yyVideoBase = new YyVideoBase();
        yyVideoBase.setId(1L);
        yyVideoBase.setVideoSum((long) jsonArray.size());
        long videoOnline = 0;
        HashSet set = new HashSet();
        for (int q = 0; q < jsonArray.size(); q++) {
            JSONObject recordOnline = jsonArray.getJSONObject(q);
            if (recordOnline.getString("networkStatus").equals("1")) {
                videoOnline++;
            }
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
                        if (!map.get("scene_name").equals("医院人行出入口")) {
                            video.setHosName((String) map.get("scene_name"));
                            set.add((String) map.get("scene_name"));
                        }
                    }
                }
            }
            yyVideoService.insertYyVideo(video);
        }
        List<YyYy> list = yyYyMapper.selectYyYyList(new YyYy());
        yyVideoBase.setVideoDuty(videoOnline);
        yyVideoBaseService.updateYyVideoBase(yyVideoBase);
        YyBase yyBase = new YyBase();
        yyBase.setId(1L);
        yyBase.setMonitor((long) jsonArray.size());
        yyBase.setMonitorOnline(videoOnline);
        yyBase.setFaceCamera((long) jsonArray.size());
        yyBase.setFaceCameraOnline(videoOnline);
        yyBase.setHosTotal((long) list.size());
        yyBase.setHosAccess((long) list.size());
        yyBaseService.updateYyBase(yyBase);
        System.out.println("更新医院视频设备");
    }

    @Autowired
    private GdVideoMapper gdVideoMapper;
    @Autowired
    private IGdVideoService gdVideoService;
    @Autowired
    private IGdVideoBaseService gdVideoBaseService;

    @Transactional
    public void gdVideo() {
        gdVideoMapper.truncateGdVideo();
        //下面是从大华调取视频设备接口的方法
        //分页查询个人有权限视频通道资源
//        String url = "resource-catalog/tripartite/authority/channel/page";
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("pageNo", 1);
//        jsonObject.put("pageSize", 200);
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("keyword", "");
//        jsonObject.put("param", jsonObject1);
//        String body = VideoLive.getVideoData(jsonObject, url, "jsjtj001", "jsjtj12345");
//        JSONObject videoList = JSONObject.parseObject(body);
//        String records = videoList.getString("records");
//        JSONArray jsonArray = JSONArray.parseArray(records);

        JSONArray jsonArray = new JSONArray();
        int temp = 1;
        while (temp > 0) {
            JSONArray jsonArrayRe = forVideo(temp, "jsjtj002", "jsjtj12345");
            for (int i = 0; i < jsonArrayRe.size(); i++) {
                jsonArray.add(jsonArrayRe.getJSONObject(i));
            }
            if (jsonArrayRe.size() == 200) {
                temp = temp + 1;
            } else {
                temp = 0;
            }
        }

        int videoTotal = jsonArray.size();
        int videoOnline = 0;
        for (int q = 0; q < videoTotal; q++) {
            JSONObject recordOnline = jsonArray.getJSONObject(q);
            if (recordOnline.getString("networkStatus").equals("1")) {
                videoOnline++;
            }
            GdVideo video = new GdVideo();
            video.setChannelName(recordOnline.getString("channelName"));
            video.setCameraType(recordOnline.getInteger("cameraType"));
            video.setNetworkStatus(recordOnline.getInteger("networkStatus"));
            video.setChannelCode(recordOnline.getString("channelCode"));
            video.setGpsX(recordOnline.getDouble("gpsX"));
            video.setGpsY(recordOnline.getDouble("gpsY"));
            video.setJscId(1);
            video.setDataType(0);
            gdVideoService.insertGdVideo(video);
        }
        GdVideoBase gdVideoBase = new GdVideoBase();
        gdVideoBase.setId(1L);
        gdVideoBase.setVideoSum((long) videoTotal);
        gdVideoBase.setVideoDuty((long) videoOnline);
        gdVideoBaseService.updateGdVideoBase(gdVideoBase);
        System.out.println("更新工地视频设备");
    }

    @Autowired
    private IYjVideoBaseService yjVideoBaseService;
    @Autowired
    private IYjVideoService yjVideoService;
    @Autowired
    private YjVideoMapper yjVideoMapper;

    @Transactional
    public void yjVideo() {
        //下面是从大华调取视频设备接口的方法
        //分页查询个人有权限视频通道资源
//        String url = "resource-catalog/tripartite/authority/channel/page";
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("pageNo", 1);
//        jsonObject.put("pageSize", 200);
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("keyword", "");
//        jsonObject.put("param", jsonObject1);
//        String body = VideoLive.getVideoData(jsonObject, url, "yjglj001", "yjglj12345");
//        JSONObject videoList = JSONObject.parseObject(body);
//        JSONArray jsonArray = videoList.getJSONArray("records");
        yjVideoMapper.truncateYjVideo();
        JSONArray jsonArray = new JSONArray();
        int temp = 1;
        while (temp > 0) {
            JSONArray jsonArrayRe = forVideo(temp, "yjglj002", "yjglj12345");
            for (int i = 0; i < jsonArrayRe.size(); i++) {
                jsonArray.add(jsonArrayRe.getJSONObject(i));
            }
            if (jsonArrayRe.size() == 200) {
                temp = temp + 1;
            } else {
                temp = 0;
            }
        }

        int videoTotal = jsonArray.size();
        int videoOnline = 0;

        for (int q = 0; q < videoTotal; q++) {
            JSONObject recordOnline = jsonArray.getJSONObject(q);
            if (recordOnline.getString("networkStatus").equals("1")) {
                videoOnline++;
            }
            YjVideo video = new YjVideo();
            video.setChannelName(recordOnline.getString("channelName"));
            video.setCameraType(recordOnline.getInteger("cameraType"));
            video.setNetworkStatus(recordOnline.getInteger("networkStatus"));
            video.setChannelCode(recordOnline.getString("channelCode"));
            video.setGpsX(recordOnline.getDouble("gpsX"));
            video.setGpsY(recordOnline.getDouble("gpsY"));
            video.setJscId(2);
            List<HashMap<String, String>> list = yjVideoMapper.selectYjClassify(recordOnline.getString("channelCode"));
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                if (map != null) {
                    if (map.get("scene_name").equals("低洼点")) {
                        video.setLowpointId(0L);
                    }
                    if (map.get("scene_name").equals("下穿涵洞")) {
                        video.setLowpointId(1L);
                    }
                    if (map.get("scene_name").equals("闸站")) {
                        video.setLowpointId(2L);
                    }
                }
            }
            video.setDataType(0);
            yjVideoService.insertYjVideo(video);
        }
        YjVideoBase yjVideoBase = new YjVideoBase();
        yjVideoBase.setId(1L);
        yjVideoBase.setVideoSum((long) videoTotal);
        yjVideoBase.setVideoDuty((long) videoOnline);
        yjVideoBaseService.updateYjVideoBase(yjVideoBase);
        System.out.println("更新应急视频设备");
        yjLowPoint();
    }

    @Autowired
    private IStVideoService stVideoService;
    @Autowired
    private IStVideoBaseService stVideoBaseService;
    @Autowired
    StVideoMapper stVideoMapper;

    @Transactional
    public void stVideo() {
        stVideoMapper.truncateStVideo();
//        String url = "resource-catalog/tripartite/authority/channel/page";
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("pageNo", 1);
//        jsonObject.put("pageSize", 200);
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("keyword", "");
//        jsonObject.put("param", jsonObject1);
//        String body = VideoLive.getVideoData(jsonObject, url, "sthjfj001", "sthjfj12345");
//        JSONObject videoList = JSONObject.parseObject(body);
//        JSONArray jsonArray = videoList.getJSONArray("records");
        JSONArray jsonArray = new JSONArray();
        int temp = 1;
        while (temp > 0) {
            JSONArray jsonArrayRe = forVideo(temp, "sthjfj002", "sthjfj12345");
            for (int i = 0; i < jsonArrayRe.size(); i++) {
                jsonArray.add(jsonArrayRe.getJSONObject(i));
            }
            if (jsonArrayRe.size() == 200) {
                temp = temp + 1;
            } else {
                temp = 0;
            }
        }
        StVideoBase stVideoBase = new StVideoBase();
        stVideoBase.setId(1L);
        stVideoBase.setVideoSum((long) jsonArray.size());
        Long videoOnline = 0L;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject recordOnline = jsonArray.getJSONObject(i);
            if (recordOnline.getString("networkStatus").equals("1")) {
                videoOnline++;
            }
            StVideo video = new StVideo();
            video.setChannelName(recordOnline.getString("channelName"));
            video.setCameraType(recordOnline.getInteger("cameraType"));
            video.setNetworkStatus(recordOnline.getInteger("networkStatus"));
            video.setChannelCode(recordOnline.getString("channelCode"));
            video.setGpsX(recordOnline.getDouble("gpsX"));
            video.setGpsY(recordOnline.getDouble("gpsY"));
            video.setJscId(3);
            video.setDataType(0);
            stVideoService.insertStVideo(video);
        }
        stVideoBase.setVideoDuty(videoOnline);
        stVideoBaseService.updateStVideoBase(stVideoBase);
        System.out.println("更新生态视频设备");
    }

    @Autowired
    private IYjLowPointService yjLowPointService;
    @Autowired
    private YjLowPointMapper yjLowPointMapper;

    @Transactional
    public void yjLowPoint() {
        yjLowPointMapper.truncateYjLowPoint();
        YjVideo video = new YjVideo();
        video.setLowpointId(0L);
        List<YjVideo> videos = yjVideoMapper.selectYjVideoList(video);
        for (int i = 0; i < videos.size(); i++) {
            YjVideo yjVideo = videos.get(i);
            String str = yjVideo.getChannelName();
            str = str.replaceAll("[a-zA-Z]", "");
            str = str.replaceAll("[\\d]", "");
            YjLowPoint yjLowPoint = new YjLowPoint();
            yjLowPoint.setLowPointName(str);
            yjLowPoint.setWaterLevelWarn(20L);
            yjLowPoint.setChannelCode(yjVideo.getChannelCode());
            yjLowPoint.setGpsX(yjVideo.getGpsX());
            yjLowPoint.setGpsY(yjVideo.getGpsY());
            yjLowPointService.insertYjLowPoint(yjLowPoint);
        }
        System.out.println("更新应急低洼点");
    }

    @Autowired
    private IVPsWorkerbaseService vPsWorkerbaseService;
    @Autowired
    private IDtMachineService dtMachineService;
    @Autowired
    private TPersoninfoMapper tPersoninfoMapper;
    @Autowired
    private GdBaseMapper gdBaseMapper;


    @Transactional
    @Scheduled(cron = "0 0 * * * ?")
    public void getGdCountDuty() {
        try {
            System.out.println("工地基础数据更新");
            gdBaseMapper.truncateTabel("gd_base");
            JSONObject jsonObject = new JSONObject();
            List<GdBuilding> list = gdBuildingService.selectGdBuildingList(new GdBuilding());
            List<VPsWorkerbase> list2 = vPsWorkerbaseService.selectVPsWorkerbaseList(new VPsWorkerbase());
            List<DtMachine> list3 = dtMachineService.selectDtMachineList(new DtMachine());
            List<TPersoninfo> tPersoninfos = tPersoninfoMapper.selectTPersoninfoList(new TPersoninfo());
            Double price = 0.00;
            Long liftingEquipment = 0L;
            Long liftingEquipmentFiling = 0L;
            for (int i = 0; i < list.size(); i++) {
                price = price + list.get(i).getPrice();
            }
            for (int j = 0; j < list3.size(); j++) {
                liftingEquipment = liftingEquipment + list3.get(j).getInstall();
                liftingEquipmentFiling = liftingEquipmentFiling + list3.get(j).getDetection();
            }
//        List<TPersoninfo> list1 = new ArrayList<>();
            for (int k = 0; k < tPersoninfos.size(); k++) {
                if (!(tPersoninfos.get(k).getCheckoutt().equals(""))) {
                    tPersoninfos.remove(k);
                    k--;
                }
            }
            String url = "http://133.1.254.22:5100/gongdi/api/zjj/zjjxjk/v1/entrance/summary/today";
            String authorization = "Basic empqI3pqanhqazp6amojMjAyMg==";
            String data = HttpClientUtils.getSummary(url, "", authorization);
            JSONObject jsonObject2 = JSONObject.parseObject(data);
            JSONObject count = jsonObject2.getJSONObject("data");
            Long num = count.getLong("count");
            DecimalFormat df = new DecimalFormat("#.##");
            price = Double.valueOf(df.format(price));
            GdBase gdBase = new GdBase();
            gdBase.setBuildingSite((long) list.size());
            gdBase.setBuildingSitePrice(BigDecimal.valueOf(price));
            gdBase.setBuildingSiteWorker((long) list2.size());
            gdBase.setBuildingSiteWorkerDuty(num);
            gdBase.setLiftingEquipment(liftingEquipment);
            gdBase.setLiftingEquipmentFiling(liftingEquipmentFiling);
            gdBaseMapper.insertGdBase(gdBase);
        }
        catch (Exception e){
            System.out.println("工地基础数据更新未连接专网");
        }
    }

    @Autowired
    private MqBkyjMapper mqBkyjMapper;
    @Autowired
    private IYyWarnService yyWarnService;

    //医院重点人员
    @Transactional
    @Scheduled(cron = "0 0 * * * ?")
    public void yyZdry() {
        System.out.println("重点人员更新");
        gdBaseMapper.truncateTabel("yy_warn");
        List<YyVideo> list = yyVideoService.selectYyVideoList(new YyVideo());
        List<MqBkyj> mqBkyjs = mqBkyjMapper.selectMqBkyjList(new MqBkyj());
        List<MqBkyj> listnew = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String channelCode = list.get(i).getChannelCode();
            for (int j = 0; j < mqBkyjs.size(); j++) {
                String mqChannelCode = mqBkyjs.get(j).getChannelId();
                if (channelCode.equals(mqChannelCode)) {
                    listnew.add(mqBkyjs.get(j));
                }
            }
        }
        for (int i = 0; i < listnew.size(); i++) {
            MqBkyj mqBkyj = listnew.get(i);
            YyWarn warn = new YyWarn();
            if (mqBkyj.getRecordType() == 1) {
                warn.setWarnType(1);
                warn.setWarnImg(mqBkyj.getImgUrl1());
                String channelName = mqBkyj.getChannelName();
                channelName = channelName.replaceAll("[a-zA-Z]", "");
                channelName = channelName.replaceAll("[\\d]", "");
                warn.setWarnPlace(channelName);
                String carNum = mqBkyj.getObjId();
                warn.setReplyInfo(carNum.substring(0, 7));
                Date date = new Date();
                date.setTime(mqBkyj.getAlarmTime());
                warn.setWarnTime(date);
                warn.setDataType(0);
                yyWarnService.insertYyWarn(warn);
            }
            if (mqBkyj.getRecordType() == 3) {
                warn.setWarnType(0);
                warn.setWarnImg(mqBkyj.getImgUrl1());
                String channelName = mqBkyj.getChannelName();
                channelName = channelName.replaceAll("[a-zA-Z]", "");
                channelName = channelName.replaceAll("[\\d]", "");
                warn.setWarnPlace(channelName);
                warn.setReplyInfo(mqBkyj.getName());
                Date date = new Date();
                date.setTime(mqBkyj.getAlarmTime());
                warn.setWarnTime(date);
                yyWarnService.insertYyWarn(warn);
            }
        }
    }

    @Autowired
    private ILtdqExcessivestatisticaldataService ltdqExcessivestatisticaldataService;
    @Autowired
    private IGdDustEquipmentService gdDustEquipmentService;

    //更新工地视频设备
    @Transactional
    public void gdDevice() {
        gdBaseMapper.truncateTabel("gd_dust_equipment");
        List<LtdqExcessivestatisticaldata> list = ltdqExcessivestatisticaldataService.selectLtdqExcessivestatisticaldataList(new LtdqExcessivestatisticaldata());
        HashSet set = new HashSet();
        for (int i = 0; i < list.size(); i++) {
            set.add(list.get(i).getDevicesn());
        }
        List<String> devices = new ArrayList<>(set);
        for (int i = 0; i < devices.size(); i++) {
            GdDustEquipment gdDustEquipment = new GdDustEquipment();
            gdDustEquipment.setBuildingId(1L);
            gdDustEquipment.setEquipmentName(devices.get(i));
            gdDustEquipmentService.insertGdDustEquipment(gdDustEquipment);
        }
    }

    @Autowired
    private GdTowerMapper gdTowerMapper;
    @Autowired
    Config2RestTemplate config2RestTemplate;

    @Transactional
    @Scheduled(cron = "0 0 * * * ?")
    public void gdTower() throws Exception {
        System.out.println("塔吊数据更新");
        gdBaseMapper.truncateTabel("gd_tower");
        RestTemplate client = config2RestTemplate.restTemplate();
        List<GdBuilding> gdBuildings = gdBuildingMapper.selectGdBuildingList(new GdBuilding());
//        RestTemplate client = new RestTemplate();
        String url = "http://133.1.254.22:5100/developer-api/device/getRecordNoList";
        String url2 = "http://133.1.254.22:5100/developer-api/device/getRealTimeData";
//        String url = "https://cloud.zqanke.cn/developer-api/device/getRecordNoList";
//        String url2 = "https://cloud.zqanke.cn/developer-api/device/getRealTimeData";
        for (int i = 0; i < gdBuildings.size(); i++) {
            GdBuilding building = gdBuildings.get(i);
            String projectName = getProjectName(building.getProjectInfoNum());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("projectId", building.getProjectInfoNum());
            jsonObject.put("deviceFactoryName", "zhiAn");
            jsonObject.put("token", "23b4f8af-ff00-40d9-9edc-7774ee825d61");
            System.out.println(client.postForObject(url, jsonObject, JSONObject.class));
            JSONObject jsonObject1 = client.postForObject(url, jsonObject, JSONObject.class);
//            JSONObject jsonObject1 = HttpClientUtils.doPost(url,jsonObject);
            System.out.println(jsonObject1);

            if (jsonObject1.getJSONArray("data").size() > 0) {
                JSONArray jsonArray = jsonObject1.getJSONArray("data");
                for (int j = 0; j < jsonArray.size(); j++) {
                    jsonObject.put("recordNo", jsonArray.get(j));
                    jsonObject.put("pageNum", 1);
                    jsonObject.put("pageSize", 1);
                    JSONObject jsonObject2 = client.postForObject(url2, jsonObject, JSONObject.class);
                    JSONObject data = new JSONObject();
                    JSONObject data2 = jsonObject2.getJSONObject("data");
                    if (data2.getJSONArray("rows").size() > 0) {
                        data = data2.getJSONArray("rows").getJSONObject(0);
                        GdTower tower = new GdTower();
                        tower.setProjectId(building.getProjectInfoNum());
                        tower.setWarnTime(data.getDate("datetime"));
                        tower.setRecordNo(data.getString("recordNo"));
                        tower.setMoment(data.getDouble("moment"));
                        tower.setLoadWeight(data.getDouble("loadWeight"));
                        tower.setMargin(data.getDouble("margin"));
                        tower.setHeightUp(data.getDouble("heightUp"));
                        tower.setHeightLower(data.getDouble("heightLower"));
                        tower.setRotation(data.getDouble("rotation"));
                        tower.setWindSpeed(data.getDouble("windSpeed"));
                        JSONObject warning = (data.getJSONObject("alarmControl")).getJSONObject("warning");
                        tower.setWeightWarn(warning.getString("weight"));
                        tower.setMomentWarn(warning.getString("moment"));
                        tower.setRightRotationWarn(warning.getString("rightRotation"));
                        tower.setLeftRotationWarn(warning.getString("leftRotation"));
                        tower.setHeightLowerWarn(warning.getString("heightLower"));
                        tower.setHeightUpWarn(warning.getString("heightUp"));
                        tower.setSmallMarginWarn(warning.getString("smallMargin"));
                        tower.setBigMarginWarn(warning.getString("bigMargin"));
                        tower.setInclinationYWarn(warning.getString("inclinationY"));
                        tower.setInclinationXWarn(warning.getString("inclinationX"));
                        tower.setWindSpeedWarn(warning.getString("windSpeed"));
                        tower.setProjectName(projectName);
                        //遍历warning里面有没有非normal的
                        Iterator iterator = warning.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry entry = (Map.Entry) iterator.next();
                            if (!(entry.getValue().equals("normal"))) {
                                tower.setSensorType(1);
                            } else {
                                tower.setSensorType(0);
                            }
                        }
                        gdTowerMapper.insertGdTower(tower);
                    }
//                    System.out.println(data);
                }
            }
        }
    }


    public String getProjectName(String projectId) {
        GdBuilding gdBuilding = gdBuildingMapper.selectGdBuildingByGuid(projectId);
        if (gdBuilding != null) {
            return gdBuilding.getBuildingSiteName();
        } else {
            return null;
        }
    }

    @Autowired
    private GdSynergyMapper gdSynergyMapper;

    @Transactional
    @Scheduled(cron = "0 0 * * * ?")
    public void freshSynergy() {
        System.out.println("工地整改通知更新");
        gdBaseMapper.truncateTabel("gd_synergy");
        gdSynergyMapper.freshGdSynergy();
    }

    public JSONArray forVideo(Integer pageNo, String userName, String password) {
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

    //应急页面事件处理
    public JSONObject yjEvent() {

        String url = "smc-event/event/sync/event";
        JSONObject firstJson = new JSONObject();
        firstJson.put("eventTypeCode", "");
        firstJson.put("id", 1);
        firstJson.put("limit", 50);
        firstJson.put("orgCode", 2);
//        firstJson.put("orgCode",33045);
        String body = yjsjUtil.getEvent(firstJson, url, "admin", "Dahua@1234567");
        JSONObject data = JSONObject.parseObject(body);
        if (data.getInteger("code") == 200) {
            JSONArray dataList = data.getJSONArray("data");
            for (int i = 0; i < dataList.size(); i++) {
                JSONObject jsonObject = dataList.getJSONObject(i);
                Double latitude = jsonObject.getDouble("latitude");
                Double longitude = jsonObject.getDouble("longitude");
                Integer temp = 5;
                if (latitude != null && longitude != null) {
                    temp = StreetMap.forIsStreet(longitude, latitude);
                }
                String streetName = "";
                if (temp == 0) {
                    streetName = "城南街道";
                }
                if (temp == 1) {
                    streetName = "嘉北街道";
                }
                if (temp == 2) {
                    streetName = "长水街道";
                }
                if (temp == 3) {
                    streetName = "塘汇街道";
                }
                if (temp == 4) {
                    streetName = "商交园";
                }
                if (temp == 5) {
                    streetName = "未知";
                }

                YjSynergy synergy = new YjSynergy();
                synergy.setDataType(0);
                Date date = new Date();
                date.setTime(jsonObject.getLong("eventOccurrenceTime"));
                synergy.setCheckTime(date);
                synergy.setStreetName(streetName);
                synergy.setIncidentDescribe(jsonObject.getString("eventDesc"));
                synergy.setIncidentType(jsonObject.getString("secEventTypeName"));
                if (jsonObject.getString("firstEventTypeName") == "生产经营单位") {
                    synergy.setEventType(0);
                }
                if (jsonObject.getString("firstEventTypeName") == "居民住宅") {
                    synergy.setEventType(1);
                }
                if (jsonObject.getString("firstEventTypeName") == "室外自然环境") {
                    synergy.setEventType(2);
                }
            }
        }
        return data;
    }

    @Scheduled(cron = "0 0 * * * ?")
    @Transactional
    public void getTqyb() throws Exception {
        System.out.println("天气预报更新");
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("location", "101210301");
        map.put("key", "ada19a8ee60a4ae2ac04db3b1c5ff09f");
        byte[] bytes = restTemplate.getForObject("http://133.1.254.22:5100/v7/weather/7d?location={location}&key={key}", byte[].class, map);
        String str = unGZip(bytes);
        creatTqyb(str);
        getSstq();
    }

    public void getSstq() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("location", "101210301");
        map.put("key", "ada19a8ee60a4ae2ac04db3b1c5ff09f");
        byte[] bytes = restTemplate.getForObject("http://133.1.254.22:5100/v7/weather/now?location={location}&key={key}", byte[].class, map);
        String str = unGZip(bytes);
        creatSstq(str);
    }

    public void creatTqyb(String str) throws Exception {
        File file = new File("tqyb.txt");
        if (!file.exists()) {
            file.createNewFile();
            writeFileContent("tqyb.txt", str);
        } else {
            delFile();
            file.createNewFile();
            writeFileContent("tqyb.txt", str);
        }
    }

    public void creatSstq(String str) throws Exception {
        File file = new File("sstq.txt");
        if (!file.exists()) {
            file.createNewFile();
            writeFileContent("sstq.txt", str);
        } else {
            delSstqFile();
            file.createNewFile();
            writeFileContent("sstq.txt", str);
        }
    }

    public boolean writeFileContent(String filepath, String newstr) throws IOException {
        Boolean bool = false;
        String filein = newstr + "\r\n";//新写入的行，换行
        String temp = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
//将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
//文件原有内容
            for (int i = 0; (temp = br.readLine()) != null; i++) {
                buffer.append(temp);
// 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
// TODO: handle exception
            e.printStackTrace();
        } finally {
//不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }

    public boolean delFile() {
        Boolean bool = false;
//        String filenameTemp = path+fileName+".txt";
        File file = new File("tqyb.txt");
        try {
            if (file.exists()) {
                file.delete();
                bool = true;
            }
        } catch (Exception e) {
// TODO: handle exception
        }
        return bool;
    }

    public boolean delSstqFile() {
        Boolean bool = false;
//        String filenameTemp = path+fileName+".txt";
        File file = new File("sstq.txt");
        try {
            if (file.exists()) {
                file.delete();
                bool = true;
            }
        } catch (Exception e) {
// TODO: handle exception
        }
        return bool;
    }

    @Autowired
    private AdmYyCwMapper admYyCwMapper;
    @Autowired
    private YyBaseMapper yyBaseMapper;

    //获取医院测温设备在线状态
    @Transactional
    @Scheduled(cron = "0 0 * * * ?")
    public void cwmjIsOnline() throws ClientException {
        System.out.println("医院测温设备在线状态更新");
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
        JSONObject unit = data.getJSONArray("units").getJSONObject(0);
        JSONObject channel = unit.getJSONArray("channels").getJSONObject(0);
        Integer isOnline = channel.getInteger("isOnline");
        return isOnline;
    }

    @Autowired
    private YyCwmjMapper yyCwmjMapper;
    @Autowired
    private IYyEpidemicPreventionService yyEpidemicPreventionService;

    //医院防疫信息更新
    @Transactional
    @Scheduled(cron = "0 0 * * * ?")
    public void fyxxSyn() {
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
    private MqCarMapper mqCarMapper;
    @Autowired
    private IYyPerceptionService yyPerceptionService;
    @Autowired
    private MqRlzpMapper mqRlzpMapper;

    //医院感知信息
    @Transactional
    @Scheduled(cron = "0 0 * * * ?")
    public void yyGzxx() {
        YyPerception perception = new YyPerception();
        perception.setId(1L);
        List<MqCar> mqCars = mqCarMapper.selectMqCarList(new MqCar());
        perception.setCarSnap((long) mqCars.size());
        List<YyCwmj> cwmjs = yyCwmjMapper.selectYyCwmjList(new YyCwmj());
        perception.setTemperatureSnap((long) cwmjs.size());
        List<MqRlzp> rlzps = mqRlzpMapper.selectMqRlzpList(new MqRlzp());
        perception.setFaceSnap((long) rlzps.size());
        yyPerceptionService.updateYyPerception(perception);
    }

    @Autowired
    private IYjSynergyService yjSynergyService;

    //应急事件数据库处理
//    @Transactional
//    @Scheduled(cron = "0 0 * * * ?")
    public void eventDb() throws Exception {
        gdBaseMapper.truncateTabel("yj_synergy");
        String sql = "select * from tb_event where org_id = 3788";
//        String sql = "select * from adm_zj where jsc_id = 2";
        // 1、加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2、获取连接
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://133.1.3.41:3306/service-case?useUnicode=true&characterEncoding" +
                        "=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone" +
                        "=GMT%2B8&rewriteBatchedStatements" +
                        "=true&druid.mysql.usePingMethod=false", "smcmysql",
                "qGZHcxKD72DdWfmQX@zYKsX36w6dXy");
        // 3、获取paperStatement
//        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 4、执行sql
//        String sql = "select * from a1";
//        ResultSet resultSet = statement.executeQuery(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        // 5、获取结果
        while (resultSet.next()) {
            YjSynergy synergy = new YjSynergy();
            String eventType = resultSet.getString("event_type");
            JSONObject jsonObject = getEventType(eventType);
            if (jsonObject.getInteger("typeBig") == null || jsonObject.getInteger("typeBig") == 3) {
                continue;
            }
            synergy.setIncidentType(jsonObject.getString("type"));
            synergy.setEventType(jsonObject.getInteger("typeBig"));

            synergy.setEventId(resultSet.getLong("event_id"));
            synergy.setAddress(resultSet.getString("address"));
            Double longitude = resultSet.getDouble("longitude");
            Double latitude = resultSet.getDouble("latitude");
            String streetName = getStreet(longitude, latitude);
            synergy.setStreetName(streetName);
            Long time = resultSet.getLong("occurrence_time");
            Date date = new Date();
            date.setTime(time);
            synergy.setCheckTime(date);
            synergy.setIncidentDescribe(resultSet.getString("description"));
            synergy.setAlarmName(resultSet.getString("contact_name"));
            synergy.setAlarmPhone(resultSet.getString("contact_phone"));

            System.out.println(synergy);

            yjSynergyService.insertYjSynergy(synergy);
        }
        // 6、关闭连接，释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    public String getStreet(Double longitude, Double latitude) {
        Integer temp = 5;
        if (latitude != null && longitude != null) {
            temp = StreetMap.forIsStreet(longitude, latitude);
        }
        String streetName = "";
        if (temp == 0) {
            streetName = "城南街道";
        }
        if (temp == 1) {
            streetName = "嘉北街道";
        }
        if (temp == 2) {
            streetName = "长水街道";
        }
        if (temp == 3) {
            streetName = "塘汇街道";
        }
        if (temp == 4) {
            streetName = "商交园";
        }
        if (temp == 5) {
            streetName = "未知";
        }
        return streetName;
    }

    public JSONObject getEventType(String eventType) {
        JSONObject jsonObject = new JSONObject();
        String type = "";
        Integer typeBig = null;
        //typeBig 0生产经营单位，1居民住宅，2室外自然环境
        if (eventType.equals("1270120001")) {
            type = "其他报警";
            typeBig = 3;
        }
        if (eventType.equals("1270120002")) {
            type = "其他报警";
            typeBig = 0;
        }
        if (eventType.equals("1270120003")) {
            type = "其他报警";
            typeBig = 1;
        }
        if (eventType.equals("1270120004")) {
            type = "其他报警";
            typeBig = 2;
        }
        if (eventType.equals("1270030001")) {
            type = "火警";
            typeBig = 3;
        }
        if (eventType.equals("1270030002")) {
            type = "火警";
            typeBig = 0;
        }
        if (eventType.equals("1270030003")) {
            type = "火警";
            typeBig = 1;
        }
        if (eventType.equals("1270030004")) {
            type = "火警";
            typeBig = 2;
        }
        if (eventType.equals("1270090001")) {
            type = "灾害事故";
            typeBig = 3;
        }
        if (eventType.equals("1270090002")) {
            type = "灾害事故";
            typeBig = 0;
        }
        if (eventType.equals("1270090003")) {
            type = "灾害事故";
            typeBig = 1;
        }
        if (eventType.equals("1270090004")) {
            type = "灾害事故";
            typeBig = 2;
        }
        if (eventType.equals("1270080001")) {
            type = "事件";
            typeBig = 3;
        }
        if (eventType.equals("1270080002")) {
            type = "事件";
            typeBig = 0;
        }
        if (eventType.equals("1270080003")) {
            type = "事件";
            typeBig = 1;
        }
        if (eventType.equals("1270080004")) {
            type = "事件";
            typeBig = 2;
        }
        if (eventType.equals("1270020001")) {
            type = "交通";
            typeBig = 3;
        }
        if (eventType.equals("1270020002")) {
            type = "交通";
            typeBig = 0;
        }
        if (eventType.equals("1270020003")) {
            type = "交通";
            typeBig = 1;
        }
        if (eventType.equals("1270020004")) {
            type = "交通";
            typeBig = 2;
        }
        jsonObject.put("type", type);
        jsonObject.put("typeBig", typeBig);
        return jsonObject;
    }

    @Autowired
    private YjSynergyMapper yjSynergyMapper;

    @Resource
    private WebSocket webSocket;

    //事件实时预警
    @Scheduled(cron = "0/10 * * * * ?")
    @Transactional
    public void updateEvent() throws Exception {
        try {
            String sql = "select * from tb_event where org_id = 3788";
//        String sql = "select * from adm_zj where jsc_id = 2";
            // 1、加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2、获取连接
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://133.1.3.41:3306/service-case?useUnicode=true&characterEncoding" +
                            "=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone" +
                            "=GMT%2B8&rewriteBatchedStatements" +
                            "=true&druid.mysql.usePingMethod=false", "smcmysql",
                    "qGZHcxKD72DdWfmQX@zYKsX36w6dXy");
            // 3、获取paperStatement
//        Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 4、执行sql
//        String sql = "select * from a1";
//        ResultSet resultSet = statement.executeQuery(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 5、获取结果
            List<YjSynergy> list = new ArrayList<>();
            while (resultSet.next()) {
                YjSynergy synergy = new YjSynergy();
                String eventType = resultSet.getString("event_type");
                JSONObject jsonObject = getEventType(eventType);
                if (jsonObject.getInteger("typeBig") == null || jsonObject.getInteger("typeBig") == 3) {
                    continue;
                }
                synergy.setIncidentType(jsonObject.getString("type"));
                synergy.setEventType(jsonObject.getInteger("typeBig"));

                Long eventIdIf = resultSet.getLong("event_id");

                synergy.setEventId(resultSet.getLong("event_id"));
                synergy.setAddress(resultSet.getString("address"));
                Double longitude = resultSet.getDouble("longitude");
                Double latitude = resultSet.getDouble("latitude");
                String streetName = getStreet(longitude, latitude);
                synergy.setStreetName(streetName);
                Long time = resultSet.getLong("occurrence_time");
                Date date = new Date();
                date.setTime(time);
                synergy.setCheckTime(date);
                synergy.setIncidentDescribe(resultSet.getString("description"));
                synergy.setAlarmName(resultSet.getString("contact_name"));
                synergy.setAlarmPhone(resultSet.getString("contact_phone"));
                list.add(synergy);
            }
            eventCompare(list);
            // 6、关闭连接，释放资源
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("事件预警未连接专网");
        }
    }
    //事件比对
    public void eventCompare(List<YjSynergy> list)throws Exception{
        List<YjSynergy> list2 = yjSynergyMapper.selectYjSynergyList(new YjSynergy());
        list2.forEach(item -> item.setId(null));
        Integer size = list.size() >= list2.size() ? list.size() : list2.size();
        Map<String,Integer> map = new HashMap<>(size);
        list2.forEach(item -> map.put(String.valueOf(item.getEventId()+item.getEventType()),1));
        list.forEach(item -> {
            String queryIf = String.valueOf(item.getEventId()+item.getEventType());
            if(map.get(queryIf) == null){
                if(ifExitEvent(item.getEventId())){
                    //不存在本地数据库，则新增
                    yjSynergyMapper.insertYjSynergy(item);
                    JSONObject sendToWeb = new JSONObject();
                    sendToWeb.put("type", 0);
                    sendToWeb.put("message", item.getEventId());
                    webSocket.sendMessage(sendToWeb.toString());
                    System.out.println("成功发送事件预警:" + sendToWeb.toString());
                }else {
                    //存在本地数据库则更新
                    yjSynergyMapper.updateEvent(item);
                    JSONObject sendToWeb = new JSONObject();
                    sendToWeb.put("type", 0);
                    sendToWeb.put("message", item.getEventId());
                    webSocket.sendMessage(sendToWeb.toString());
                    System.out.println("成功发送事件预警:" + sendToWeb.toString());
                }
            }
        });
    }

    //根据事件ID查询事件方法
    public boolean ifExitEvent(Long eventId){
        //是否存在在本地数据库，如果不存在则返回ture，则执行新增方法
        YjSynergy yjSynergy = new YjSynergy();
        yjSynergy.setEventId(eventId);
        List<YjSynergy> synergies = yjSynergyMapper.selectYjSynergyList(yjSynergy);
        if (synergies.size() > 0) {
            //存在
            return false;
        }else{
            //不存在
            return true;
        }
    }


    //查询数据库旧方法
    public void eventSyn() throws Exception {
        try {
            String sql = "select * from tb_zjg_event_handle";
//        String sql = "select * from adm_zj where jsc_id = 2";
            // 1、加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2、获取连接
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://133.1.3.41:3306/service-case?useUnicode=true&characterEncoding" +
                            "=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone" +
                            "=GMT%2B8&rewriteBatchedStatements" +
                            "=true&druid.mysql.usePingMethod=false", "smcmysql",
                    "qGZHcxKD72DdWfmQX@zYKsX36w6dXy");
            // 3、获取paperStatement
//        Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 4、执行sql
//        String sql = "select * from a1";
//        ResultSet resultSet = statement.executeQuery(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 5、获取结果
            while (resultSet.next()) {
                Integer orgId = resultSet.getInt("org_id");
                if (orgId == 3788) {
                    System.out.println("组织ID为3788");
                    YjSynergy synergy = new YjSynergy();
                    synergy.setEventId(resultSet.getLong("event_id"));
                    synergy.setAddress(resultSet.getString("address"));
                    Double longitude = resultSet.getDouble("longitude");
                    Double latitude = resultSet.getDouble("latitude");
                    String streetName = getStreet(longitude, latitude);
                    synergy.setStreetName(streetName);
                    Long time = resultSet.getLong("occurrence_time");
                    Date date = new Date();
                    date.setTime(time);
                    synergy.setCheckTime(date);
                    synergy.setIncidentDescribe(resultSet.getString("description"));
                    synergy.setAlarmName(resultSet.getString("contact_name"));
                    synergy.setAlarmPhone(resultSet.getString("contact_phone"));
                    String eventType = resultSet.getString("event_type");
                    JSONObject jsonObject = getEventType(eventType);
                    synergy.setIncidentType(jsonObject.getString("type"));
                    synergy.setEventType(jsonObject.getInteger("typeBig"));
                    yjSynergyMapper.updateEvent(synergy);
                    String eventId = String.valueOf(resultSet.getLong("event_id"));
                    String deSql = "delete from tb_zjg_event_handle where event_id =" + eventId;
                    PreparedStatement preparedStatementDelete = connection.prepareStatement(deSql);
                    preparedStatementDelete.execute();
                    JSONObject sendToWeb = new JSONObject();
                    sendToWeb.put("type",0);
                    sendToWeb.put("message",eventId);
                    webSocket.sendMessage(sendToWeb.toString());
                    System.out.println("成功发送eventId"+sendToWeb.toString());
                }
                else {
                    String eventId = String.valueOf(resultSet.getLong("event_id"));
                    String deSql = "delete from tb_zjg_event_handle where event_id =" + eventId;
                    PreparedStatement preparedStatementDelete = connection.prepareStatement(deSql);
                    preparedStatementDelete.execute();
                }
            }
            // 6、关闭连接，释放资源
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("未连接专网");
        }
    }


    //删除七天之外的医院测温门禁
    @Scheduled(cron = "0 0 1 * * ?")
    public void deYyCwmj(){
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
                Long[] idsSub = ids.toArray(new Long[1000]);
                yyCwmjMapper.deleteYyCwmjByIds(idsSub);
                ids = null;
            }
        }
        Long[] idsSub = ids.toArray(new Long[1000]);
        yyCwmjMapper.deleteYyCwmjByIds(idsSub);
    }

    //删除七天之外的重点人员
    @Scheduled(cron = "0 0 1 * * ?")
    public void deZdry(){
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
                Long[] idsSub = ids.toArray(new Long[1000]);
                mqBkyjMapper.deleteMqBkyjByIds(idsSub);
                ids.clear();
            }
        }
        Long[] idsSub = ids.toArray(new Long[1000]);
        mqBkyjMapper.deleteMqBkyjByIds(idsSub);
    }

    //删除七天之外的车辆抓拍
    @Scheduled(cron = "0 0 1 * * ?")
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
                Long[] idsSub = ids.toArray(new Long[1000]);
                mqCarMapper.deleteMqCarByIds(idsSub);
                ids.clear();
            }
        }
        Long[] idsSub = ids.toArray(new Long[1000]);
        mqCarMapper.deleteMqCarByIds(idsSub);
    }

    @Autowired
    private YjLowPointWarnMapper yjLowPointWarnMapper;

    //删除三十天之外的低洼点预警
    @Scheduled(cron = "0 0 1 * * ?")
    public void deLowPointWarn(){
        List<YjLowPointWarn> list = yjLowPointWarnMapper.selectYjLowPointWarnList(new YjLowPointWarn());
        Date date2 = new Date();
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            YjLowPointWarn yjLowPointWarn = list.get(i);
            Date date1 = DateUtils.parseDate(yjLowPointWarn.getAlarmTime());
            if(yjLowPointWarn.getAlarmTime() == null){
                ids.add(yjLowPointWarn.getId());
            }
            Integer day = DateUtils.differentDaysByMillisecond(date1,date2);
            if(day > 30){
                ids.add(yjLowPointWarn.getId());
                System.out.println(day);
            }
            if(ids.size() > 1000){
                Long[] idsSub = ids.toArray(new Long[1000]);
                yjLowPointWarnMapper.deleteYjLowPointWarnByIds(idsSub);
                ids.clear();
            }
        }
        Long[] idsSub = ids.toArray(new Long[1000]);
        yjLowPointWarnMapper.deleteYjLowPointWarnByIds(idsSub);
    }

    //删除七天之外的人脸抓拍
    @Scheduled(cron = "0 0 1 * * ?")
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

    @Autowired
    private YyPerceptionTypeMapper yyPerceptionTypeMapper;
    //删除感知筛选中的七天前的数据
    //删除七天之外的人脸抓拍
    @Scheduled(cron = "0 0 1 * * ?")
    public void deGzsx(){
        List<YyPerceptionType> list = yyPerceptionTypeMapper.selectYyPerceptionTypeList(new YyPerceptionType());
        Date date2 = new Date();
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            YyPerceptionType yyPerceptionType = list.get(i);
            Date date1 = new Date(yyPerceptionType.getCapTime());
            if(yyPerceptionType.getCapTime() == null){
                ids.add(yyPerceptionType.getId());
            }
            Integer day = DateUtils.differentDaysByMillisecond(date1,date2);
            if(day > 7){
                ids.add(yyPerceptionType.getId());
                System.out.println(day);
            }
            if(ids.size() > 1000){
                Long[] idsSub = ids.toArray(new Long[1000]);
                yyPerceptionTypeMapper.deleteYyPerceptionTypeByIds(idsSub);
                ids.clear();
            }
        }
        Long[] idsSub = ids.toArray(new Long[1000]);
        yyPerceptionTypeMapper.deleteYyPerceptionTypeByIds(idsSub);
    }




}


