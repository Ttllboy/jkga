package com.ruoyi.jkza.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.config.ThreadPoolConfig;
import com.ruoyi.jkza.config.Config2RestTemplate;
import com.ruoyi.jkza.domain.*;
import com.ruoyi.jkza.mapper.*;
import com.ruoyi.jkza.service.*;
import com.ruoyi.jkza.util.AsyncUtil;
import com.ruoyi.jkza.util.HttpClientUtils;
import com.ruoyi.jkza.util.VideoLive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

@RestController
@RequestMapping("/gdData")
public class GdData {
    @Autowired
    private IGdBaseService gdBaseService;

    @Autowired
    private IGdManagerCheckService gdManagerCheckService;

    @Autowired
    private IGdStreetService gdStreetService;

    @Autowired
    private IGdSynergyService gdSynergyService;

    @Autowired
    private IGdKeyPersonService gdKeyPersonService;

    @Autowired
    private IGdDustDataService gdDustDataService;

    @Autowired
    private IGdVideoBaseService gdVideoBaseService;

    @Autowired
    private IGdVideoService gdVideoService;

    @Autowired
    private IGdBuildingService gdBuildingService;

    @Autowired
    private IGdWorkerService gdWorkerService;

    @Autowired
    private IGdDustEquipmentService gdDustEquipmentService;

    @Autowired
    private IGdCheckLoseService gdCheckLoseService;
    @Autowired
    private IYyVideoBaseService yyVideoBaseService;
    @Autowired
    private IYyVideoService yyVideoService;
    @Autowired
    private YyVideoMapper yyVideoMapper;
    @Autowired
    private GdVideoMapper gdVideoMapper;
    @Autowired
    private GdBaseMapper gdBaseMapper;
    @Autowired
    private GdTowerMapper gdTowerMapper;
    @Autowired
    private ILtdqExcessivestatisticaldataService ltdqExcessivestatisticaldataService;
    @Autowired
    private GdSynergyMapper gdSynergyMapper;
    @Autowired
    private IGdDustDataTotalService gdDustDataTotalService;

    @PostMapping("/test")
    public JSONObject test()throws Exception{

//        gdBaseMapper.truncateTabel("gd_tower");
//        List<GdBuilding> gdBuildings = gdBuildingMapper.selectGdBuildingList(new GdBuilding());
//        RestTemplate client = new RestTemplate();
////        String url = "http://124.70.164.162:7300/device/getRecordNoList";
//        String url = "http://133.1.254.22:5100/developer-api/device/getRecordNoList";
////        String url2 = "http://124.70.164.162:7300/device/getRealTimeData";
//        String url2 = "http://133.1.254.22:5100/developer-api/device/getRealTimeData";
//        for (int i = 0; i < gdBuildings.size(); i++) {
//            GdBuilding building = gdBuildings.get(i);
//            String projectName = getProjectName(building.getProjectInfoNum());
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("projectId",building.getProjectInfoNum());
//            jsonObject.put("deviceFactoryName","zhiAn");
//            jsonObject.put("token","23b4f8af-ff00-40d9-9edc-7774ee825d61");
////            System.out.println(client.postForObject(url,jsonObject,JSONObject.class));
//            JSONObject jsonObject1 = client.postForObject(url,jsonObject,JSONObject.class);
//            System.out.println(jsonObject1);
//            if(jsonObject1.getJSONArray("data").size() > 0 ){
//                JSONArray jsonArray = jsonObject1.getJSONArray("data");
//                for (int j = 0; j < jsonArray.size(); j++) {
//                    jsonObject.put("recordNo",jsonArray.get(j));
//                    jsonObject.put("pageNum",1);
//                    jsonObject.put("pageSize",1);
//                    JSONObject jsonObject2 = client.postForObject(url2,jsonObject,JSONObject.class);
//                    if(jsonObject2.getJSONObject("data").getJSONArray("rows").size() > 0){
//                        JSONObject data = ((jsonObject2.getJSONObject("data")).getJSONArray("rows")).getJSONObject(0);
//                        System.out.println(data);
////                    System.out.println(data);
//                        GdTower tower = new GdTower();
//                        tower.setProjectId(building.getProjectInfoNum());
//                        tower.setWarnTime(data.getDate("datetime"));
//                        tower.setRecordNo(data.getString("recordNo"));
//                        tower.setMoment(data.getDouble("moment"));
//                        tower.setLoadWeight(data.getDouble("loadWeight"));
//                        tower.setMargin(data.getDouble("margin"));
//                        tower.setHeightUp(data.getDouble("heightUp"));
//                        tower.setHeightLower(data.getDouble("heightLower"));
//                        tower.setRotation(data.getDouble("rotation"));
//                        tower.setWindSpeed(data.getDouble("windSpeed"));
//                        JSONObject warning = (data.getJSONObject("alarmControl")).getJSONObject("warning");
//                        tower.setWeightWarn(warning.getString("weight"));
//                        tower.setMomentWarn(warning.getString("moment"));
//                        tower.setRightRotationWarn(warning.getString("rightRotation"));
//                        tower.setLeftRotationWarn(warning.getString("leftRotation"));
//                        tower.setHeightLowerWarn(warning.getString("heightLower"));
//                        tower.setHeightUpWarn(warning.getString("heightUp"));
//                        tower.setSmallMarginWarn(warning.getString("smallMargin"));
//                        tower.setBigMarginWarn(warning.getString("bigMargin"));
//                        tower.setInclinationYWarn(warning.getString("inclinationY"));
//                        tower.setInclinationXWarn(warning.getString("inclinationX"));
//                        tower.setWindSpeedWarn(warning.getString("windSpeed"));
//                        tower.setProjectName(projectName);
//                        //遍历warning里面有没有非normal的
//                        Iterator iterator = warning.entrySet().iterator();
//                        while (iterator.hasNext()){
//                            Map.Entry entry = (Map.Entry) iterator.next();
//                            if(!(entry.getValue().equals("normal"))){
//                                tower.setSensorType(1);
//                            }else {
//                                tower.setSensorType(0);
//                            }
//                        }
//                        gdTowerMapper.insertGdTower(tower);
//                    }
//                }
//            }
//        }
//        return null;
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
        return null;
    }

    public String getProjectName(String projectId){
        GdBuilding gdBuilding = gdBuildingMapper.selectGdBuildingByGuid(projectId);
        return gdBuilding.getBuildingSiteName();
    }
    //所有工地信息
    @PostMapping("/buildingList")
    public List<?> getBuildingList() {
        GdBuilding gdBuilding = new GdBuilding();
        List<GdBuilding> gdBuildings = gdBuildingService.selectGdBuildingList(gdBuilding);
        return gdBuildings;
    }

    @Autowired
    private IVPsWorkerbaseService vPsWorkerbaseService;
    @Autowired
    private IDtMachineService dtMachineService;

    //基础数据处理
    @PostMapping("/base")
    public Object getBase() {
        return gdBaseMapper.selectGdBaseById(1L);
    }

    //管理人员考勤
    @PostMapping("/managerCheck")
    public Object getManagerCheck() {
        Calendar cal = Calendar.getInstance();
        DateCal dateCal = new DateCal();
        dateCal.setYear(cal.get(Calendar.YEAR));
        dateCal.setMonth(cal.get(Calendar.MONTH));
        Integer degree = (cal.getActualMaximum(Calendar.DATE) - 1) * 2;
        BigDecimal total = new BigDecimal(degree);
        List<TAttAttendancemonthstat> list = tAttAttendancemonthstatMapper.selectList(dateCal);
        JSONObject jsonObject = new JSONObject();
        BigDecimal manager = new BigDecimal(0);
        BigDecimal managerPer = new BigDecimal(0);
        BigDecimal safety = new BigDecimal(0);
        BigDecimal safetyPer = new BigDecimal(0);
        BigDecimal register = new BigDecimal(0);
        BigDecimal registerPer = new BigDecimal(0);
        BigDecimal professinoal = new BigDecimal(0);
        BigDecimal professinoalPer = new BigDecimal(0);
        BigDecimal tip = new BigDecimal(1);

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getPostName() == null){
                continue;
            }
            if (list.get(i).getPostName().equals("项目负责人")) {
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total, 4, BigDecimal.ROUND_HALF_UP);
                manager = manager.add(tip);
                managerPer = managerPer.add(checkPer);
            }
            if (list.get(i).getPostName().equals("项目总监理工程师")) {
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total, 4, BigDecimal.ROUND_HALF_UP);
                register = register.add(tip);
                registerPer = registerPer.add(checkPer);
            }
            if (list.get(i).getPostName().equals("安全员")) {
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total, 4, BigDecimal.ROUND_HALF_UP);
                safety = safety.add(tip);
                safetyPer = safetyPer.add(checkPer);
            }
            if (list.get(i).getPostName().equals("专业监理工程师")) {
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total, 4, BigDecimal.ROUND_HALF_UP);
                professinoal = professinoal.add(tip);
                professinoalPer = professinoalPer.add(checkPer);
            }
        }
        jsonObject.put("manager", manager);
        jsonObject.put("managerPer", (managerPer.divide(manager, 4, BigDecimal.ROUND_HALF_UP)));
        jsonObject.put("register", register);
        jsonObject.put("registerPer", registerPer.divide(register, 4, BigDecimal.ROUND_HALF_UP));
        jsonObject.put("safety", safety);
        jsonObject.put("safetyPer", safetyPer.divide(safety, 4, BigDecimal.ROUND_HALF_UP));
        jsonObject.put("professinoal", professinoal);
        jsonObject.put("professinoalPer", professinoalPer.divide(professinoal, 4, BigDecimal.ROUND_HALF_UP));
        return jsonObject;
    }

    //单个工地管理人员考勤
    @GetMapping("/buildingManagerCheck/{id}")
    public Object buildingManagerCheck(@PathVariable("id") Long id) {
        Calendar cal = Calendar.getInstance();
        GdBuilding gdBuilding = gdBuildingService.selectGdBuildingById(id);
        DateCal dateCal = new DateCal();
        dateCal.setYear(cal.get(Calendar.YEAR));
        dateCal.setMonth(cal.get(Calendar.MONTH));
        dateCal.setProjectGuid(gdBuilding.getProjectInfoNum());
        Integer degree = (cal.getActualMaximum(Calendar.DATE) - 1) * 2;
        BigDecimal total = new BigDecimal(degree);
        List<TAttAttendancemonthstat> list = tAttAttendancemonthstatMapper.selectListByGd(dateCal);
        JSONObject jsonObject = new JSONObject();
        BigDecimal manager = new BigDecimal(0);
        BigDecimal managerPer = new BigDecimal(0);
        BigDecimal safety = new BigDecimal(0);
        BigDecimal safetyPer = new BigDecimal(0);
        BigDecimal register = new BigDecimal(0);
        BigDecimal registerPer = new BigDecimal(0);
        BigDecimal professinoal = new BigDecimal(0);
        BigDecimal professinoalPer = new BigDecimal(0);
        BigDecimal tip = new BigDecimal(1);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPostName().equals("项目负责人")) {
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total, 4, BigDecimal.ROUND_HALF_UP);
                manager = manager.add(tip);
                managerPer = managerPer.add(checkPer);
            }
            if (list.get(i).getPostName().equals("项目总监理工程师")) {
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total, 4, BigDecimal.ROUND_HALF_UP);
                register = register.add(tip);
                registerPer = registerPer.add(checkPer);
            }
            if (list.get(i).getPostName().equals("安全员")) {
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total, 4, BigDecimal.ROUND_HALF_UP);
                safety = safety.add(tip);
                safetyPer = safetyPer.add(checkPer);
            }
            if (list.get(i).getPostName().equals("专业监理工程师")) {
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total, 4, BigDecimal.ROUND_HALF_UP);
                professinoal = professinoal.add(tip);
                professinoalPer = professinoalPer.add(checkPer);
            }
        }
        jsonObject.put("manager", manager);
        if (manager.equals(BigDecimal.ZERO)) {
            jsonObject.put("managerPer", 0);
        } else {
            jsonObject.put("managerPer", (managerPer.divide(manager, 4, BigDecimal.ROUND_HALF_UP)));
        }
        jsonObject.put("register", register);
        if (register.equals(BigDecimal.ZERO)) {
            jsonObject.put("registerPer", 0);
        } else {
            jsonObject.put("registerPer", registerPer.divide(register, 4, BigDecimal.ROUND_HALF_UP));
        }
        jsonObject.put("safety", safety);
        if (safety.equals(BigDecimal.ZERO)) {
            jsonObject.put("safetyPer", 0);
        } else {
            jsonObject.put("safetyPer", safetyPer.divide(safety, 4, BigDecimal.ROUND_HALF_UP));
        }
        jsonObject.put("professinoal", professinoal);
        if (professinoal.equals(BigDecimal.ZERO)) {
            jsonObject.put("professinoalPer", 0);
        } else {
            jsonObject.put("professinoalPer", professinoalPer.divide(professinoal, 4, BigDecimal.ROUND_HALF_UP));
        }
        return jsonObject;
    }

    //街道信息
    @PostMapping("/street")
    public JSONArray getStreet() {
        //HashMap map = new HashMap();
        GdStreet gdStreet = new GdStreet();
        GdBuilding gdBuilding = new GdBuilding();
        List<GdStreet> gdStreets = gdStreetService.selectGdStreetList(gdStreet);
        List<GdBuilding> gdBuildings = gdBuildingService.selectGdBuildingList(gdBuilding);
        //map = singleData(map,"street",gdStreet,gdStreets);
        //查找到工地与街道，归类到以街道为中心
        JSONArray jsonArrayNew = new JSONArray();
        for (int i = 0; i < gdStreets.size(); i++) {
            HashMap mapNew = new HashMap();
            Long streetId = gdStreets.get(i).getId();
            String streetName = gdStreets.get(i).getStreetName();

            JSONArray jsonArray = new JSONArray();
            for (int j = 0; j < gdBuildings.size(); j++) {
                if (streetId.equals(gdBuildings.get(j).getStreetId())) {
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(gdBuildings.get(j)));
                    jsonArray.add(jsonObject);
                }
            }
            //mapNew.put(streetName,jsonArray);
            mapNew.put("name", streetName);
            mapNew.put("streetId", streetId);
            mapNew.put("children", jsonArray);
            jsonArrayNew.add(mapNew);
        }
        return jsonArrayNew;
    }

    @Autowired
    private DtRectificationnoticeMapper dtRectificationnoticeMapper;

    //整改通知
    @PostMapping("/synergy")
    public List<?> getSynergy() {
        List<DtRectificationnotice> list = dtRectificationnoticeMapper.selectList(new DtRectificationnotice());
        return list;
    }
    @Autowired
    private MqBkyjMapper mqBkyjMapper;
    //重点人员预警
    @PostMapping("/keyPerson")
    public List<?> getKeyPerson() {
        gdBaseMapper.truncateTabel("gd_key_person");
        MqBkyj bkyj = new MqBkyj();
        bkyj.setRecordType(3L);
        List<MqBkyj> mqBkyjs = mqBkyjMapper.selectMqBkyjList(bkyj);
        List<MqBkyj> list = new ArrayList<>();
        for (int i = 0; i < mqBkyjs.size(); i++) {
            String channelId = mqBkyjs.get(i).getChannelId();
            List<HashMap> belongGd = gdBaseMapper.ifGd("\""+channelId+"\"");
            if (belongGd.size()>0) {
                list.add(mqBkyjs.get(i));
                MqBkyj mqBkyj = mqBkyjs.get(i);
                System.out.println(mqBkyjs.get(i));
                GdKeyPerson keyPerson = new GdKeyPerson();
                keyPerson.setWarnImg(mqBkyj.getImgUrl1());
                Date date = new Date(mqBkyj.getCapTime());
                keyPerson.setWarnTime(date);
                keyPerson.setWarnSite(mqBkyj.getChannelName());
                keyPerson.setWarnType("重点人员预警");
                gdKeyPersonService.insertGdKeyPerson(keyPerson);
            }
        }
        return list;
    }
    @Autowired
    private GdDustDataTotalMapper gdDustDataTotalMapper;

    //扬尘设备数据
    @PostMapping("/dustData")
    public List<?> getDustData() throws Exception {
//        HashMap map = new HashMap();
//        JSONArray jsonArray = new JSONArray();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
//
//        //List<LtdqExcessivestatisticaldata> list = ltdqExcessivestatisticaldataService.selectLtdqExcessivestatisticaldataList(new LtdqExcessivestatisticaldata());
//        List<GdDustData> list = gdDustDataMapper.selectGdDustDataList(new GdDustData());
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DAY_OF_MONTH, -8);
//        for (int i = 0; i < 7; i++) {
//            //averageDust(date,gdDustData1);
//            calendar.add(Calendar.DAY_OF_MONTH, +1);
//            date = calendar.getTime();
//            Random r = new Random();
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("date",date);
//            jsonObject.put("pm25",r.nextDouble() * 10 + 10);
//            jsonObject.put("pm10",r.nextDouble() * 10 + 20);
////            jsonArray.add(averageDust(calendar, list));
//            jsonArray.add(jsonObject);
//        }
//        //map = listData(map,"dustData", gdDustData1);
//        return jsonArray;

//        List<GdDustDataTotal> list = gdDustDataTotalMapper.selectGdDustDataTotalList(new GdDustDataTotal());
//        return list;
        List<GdDustDataTotal> list = gdDustDataTotalMapper.selectGdDustDataTotalList(new GdDustDataTotal());
        Collections.reverse(list);
        return list;
    }

    //扬尘设备数量(总)
    @PostMapping("/dustEquipment")
    public Integer getDustEquipment() {
        GdDustEquipment gdDustEquipment = new GdDustEquipment();
        List<GdDustEquipment> gdDustEquipments = gdDustEquipmentService.selectGdDustEquipmentList(gdDustEquipment);
        return gdDustEquipments.size();
    }

    //视频列表
    @PostMapping("/videoBase")
    public List<?> getVideoBase() {
        //HashMap map = new HashMap();
        GdVideoBase gdVideoBase = new GdVideoBase();
        List<GdVideoBase> gdVideoBases = gdVideoBaseService.selectGdVideoBaseList(gdVideoBase);
        //map = listData(map,"videoBase", gdVideoBases);
        return gdVideoBases;
    }


    //视频设备信息
    @PostMapping("/video")
    public JSONArray getVideo() {
        //下面是从大华调取视频设备接口的方法
        //分页查询个人有权限视频通道资源
        String url = "resource-catalog/tripartite/authority/channel/page";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNo", 1);
        jsonObject.put("pageSize", 200);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("keyword", "");
        jsonObject.put("param", jsonObject1);
        String body = VideoLive.getVideoData(jsonObject, url, "jsjtj002", "jsjtj12345");
        //String body = VideoLive.getVideoData(jsonObject,url,"cnjd001","cnjd12345");
        JSONObject videoList = JSONObject.parseObject(body);
        String records = videoList.getString("records");
        JSONArray jsonArray = JSONArray.parseArray(records);
        //遍历出拥有工地编号的视频
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject gdRecord = jsonArray.getJSONObject(i);
            if (!gdRecord.containsKey("installAddr")) {
                jsonArray.remove(i);
                i--;
            }
        }
        int videoTotal = jsonArray.size();
        int videoOnline = 0;
        for (int q = 0; q < videoTotal; q++) {
            JSONObject recordOnline = jsonArray.getJSONObject(q);
            if (recordOnline.getString("networkStatus").equals("1")) {
                videoOnline++;
            }
        }
        GdVideoBase gdVideoBase = new GdVideoBase();
        gdVideoBase.setId(1L);
        gdVideoBase.setVideoSum((long) videoTotal);
        gdVideoBase.setVideoDuty((long) videoOnline);
        gdVideoBaseService.updateGdVideoBase(gdVideoBase);


        HashSet hashSet = new HashSet();
        for (int i = 0; i < jsonArray.size(); i++) {
            hashSet.add(jsonArray.getJSONObject(i).getString("installAddr"));
        }
        List<String> gdGuidList = new ArrayList<String>(hashSet);
        JSONArray jsonArrayNew = new JSONArray();

        for (int j = 0; j < hashSet.size(); j++) {
            String gdGuid = gdGuidList.get(j);
            GdBuilding gdBuilding = new GdBuilding();
            gdBuilding = gdBuildingMapper.selectGdBuildingByGuid(gdGuid);
            JSONObject gdAndVideo = new JSONObject();
            gdAndVideo.put("name", gdBuilding.getBuildingSiteName());
            JSONArray videoChildrenList = new JSONArray();
            for (int k = 0; k < jsonArray.size(); k++) {
                if (gdGuid.equals(jsonArray.getJSONObject(k).getString("installAddr"))) {
                    videoChildrenList.add(jsonArray.getJSONObject(k));
                }
                gdAndVideo.put("children", videoChildrenList);
            }
            jsonArrayNew.add(gdAndVideo);
        }
        return jsonArrayNew;
    }

    @Autowired
    private GdBuildingMapper gdBuildingMapper;

    @Autowired
    private IStProjectService stProjectService;

    @Autowired
    private TAttAttendancemonthstatMapper tAttAttendancemonthstatMapper;

    @Autowired
    private TPersoninfoMapper tPersoninfoMapper;
    @Autowired
    private GdDustDataMapper gdDustDataMapper;






    //单个工地信息
    @GetMapping("/buildingId/{id}")
    public GdBuilding getBuildingId(@PathVariable("id") Long id) {
        GdBuilding gdBuilding = new GdBuilding();
        gdBuilding = gdBuildingService.selectGdBuildingById(id);
        return gdBuilding;
    }

    //单个工地页面基础数据
    @GetMapping("/buildingBase/{id}")
    public Object getBuildingBase(@PathVariable("id") Long id) {
        GdBuilding gdBuilding = new GdBuilding();
        gdBuilding = gdBuildingService.selectGdBuildingById(id);
        VPsWorkerbase vPsWorkerbase = new VPsWorkerbase();
        vPsWorkerbase.setProjectguid(gdBuilding.getProjectInfoNum());
        JSONObject jsonObject = new JSONObject();
        List<GdBuilding> list = gdBuildingService.selectGdBuildingList(gdBuilding);
        List<VPsWorkerbase> list2 = vPsWorkerbaseService.selectVPsWorkerbaseList(vPsWorkerbase);
        TPersoninfo tPersoninfo = new TPersoninfo();
        tPersoninfo.setProjectguid(gdBuilding.getProjectInfoNum());
        List<TPersoninfo> tPersoninfos = tPersoninfoMapper.selectTPersoninfoList(tPersoninfo);
        Double price = 0.00;
        for (int i = 0; i < list.size(); i++) {
            price = price + list.get(i).getPrice();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        price = Double.valueOf(df.format(gdBuilding.getPrice()));
        jsonObject.put("totalPrice", price);
        jsonObject.put("people", list2.size());
        for (int k = 0; k < tPersoninfos.size(); k++) {
            if (!(tPersoninfos.get(k).getCheckoutt().equals(""))) {
                tPersoninfos.remove(k);
                k--;
            }
        }
        jsonObject.put("buildingSiteDuty", tPersoninfos.size());
        return jsonObject;
    }

    //单个工地页面整改通知
    @GetMapping("/buildingSynergy/{id}")
    public List<?> getBuildingSynergy(@PathVariable("id") Long id) {
        GdBuilding gdBuilding = gdBuildingService.selectGdBuildingById(id);
        DtRectificationnotice dtRectificationnotice = new DtRectificationnotice();
        dtRectificationnotice.setProjectguid(gdBuilding.getProjectInfoNum());
        List<DtRectificationnotice> list = dtRectificationnoticeMapper.selectList(dtRectificationnotice);
        return list;
    }

    //单个工地页面预警信息
    @GetMapping("/buildingKeyPerson/{id}")
    public List<?> getBuildingKeyPerson(@PathVariable("id") Long id) {
        //HashMap map = new HashMap();
        GdKeyPerson gdKeyPerson = new GdKeyPerson();
        gdKeyPerson.setBuildingId(id);
        List<GdKeyPerson> gdKeyPeople = gdKeyPersonService.selectGdKeyPersonList(gdKeyPerson);
        //map = listData(map,"keyPerson", gdKeyPeople);
        return gdKeyPeople;
    }

    @Autowired
    private LtdqExcessivestatisticaldataMapper ltdqExcessivestatisticaldataMapper;

    //单个工地页面扬尘设备
    @GetMapping("/buildingDustData/{id}")
    public JSONArray getBuildingDustData(@PathVariable("id") Long id) throws Exception {
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));

        //LtdqExcessivestatisticaldata ltdqExcessivestatisticaldata = new LtdqExcessivestatisticaldata();
        //GdBuilding gdBuilding = gdBuildingService.selectGdBuildingById(id);
        //ltdqExcessivestatisticaldata.setProjectguid(gdBuilding.getProjectInfoNum());
        //List<LtdqExcessivestatisticaldata> list = ltdqExcessivestatisticaldataMapper.selectList(ltdqExcessivestatisticaldata);
        GdDustData gdDustData = new GdDustData();
        gdDustData.setBuildingId(id);
        List<GdDustData> list = gdDustDataMapper.selectGdDustDataList(gdDustData);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -8);
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, +1);
            date = calendar.getTime();
            jsonArray.add(averageDust(calendar, list));
        }
        //map = listData(map,"dustData", gdDustData1);
        return jsonArray;
    }

    //单个工地页面扬尘设备数量
    @GetMapping("/buildingDustEquipment/{id}")
    public Integer getBuildingDustEquipment(@PathVariable("id") Long id) {
        GdDustEquipment gdDustEquipment = new GdDustEquipment();
        gdDustEquipment.setBuildingId(id);
        List<GdDustEquipment> gdDustEquipments = gdDustEquipmentService.selectGdDustEquipmentList(gdDustEquipment);
        return gdDustEquipments.size();
    }

    //单个工地视频设备
    @GetMapping("/buildingVideo/{id}")
    public JSONArray getBuildingVideo(@PathVariable("id") Long id) {
        GdBuilding gdBuilding = gdBuildingService.selectGdBuildingById(id);
        String guid = gdBuilding.getProjectInfoNum();
        //分页查询个人有权限视频通道资源
        String url = "resource-catalog/tripartite/authority/channel/page";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNo", 1);
        jsonObject.put("pageSize", 200);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("keyword", "");
        jsonObject.put("param", jsonObject1);
        String body = VideoLive.getVideoData(jsonObject, url, "jsjtj002", "jsjtj12345");
        //System.out.println(body);
        JSONObject videoList = JSONObject.parseObject(body);
        String records = videoList.getString("records");
        JSONArray jsonArray = JSONArray.parseArray(records);
        JSONArray jsonArrayNew = new JSONArray();
        //遍历出拥有工地编号的视频
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject gdRecord = jsonArray.getJSONObject(i);
            if (gdRecord.containsKey("installAddr")) {
                if (guid.equals(gdRecord.getString("installAddr"))) {
                    jsonArrayNew.add(gdRecord);
                }
            }
        }
        return jsonArrayNew;
    }

    //总页面考勤缺失人员
    @PostMapping("/checkLose")
    public TableDataInfo getCheckLose(@RequestBody JSONObject jsonObject) throws Exception {
        //GdCheckLose gdCheckLose = new GdCheckLose();
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        //List<GdCheckLose> list = gdCheckLoseService.selectGdCheckLoseList(gdCheckLose);
        List<VPsWorkerbase> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date date = null;
        //date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(date);
        //calendar.add(Calendar.DAY_OF_MONTH,-8);
        //date = calendar.getTime();
        //for(int i = 0;i < list.size(); i++){
        //    if(list.get(i).getCheckTime().before(date)){
        //        list.remove(i);
        //        i--;
        //    }
        //}
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        //rspData.setRows(PageByList.starPage(list,pageNum,pageSize));
        rspData.setRows(jsonArray);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    //分工地页面考勤缺失人员
    @PostMapping("/buildingCheckLose")
    public TableDataInfo getBuildingCheckLose(@RequestBody JSONObject jsonObject) throws Exception {
        //GdCheckLose gdCheckLose = new GdCheckLose();
        Long id = Long.valueOf(jsonObject.get("id").toString());
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        //gdCheckLose.setId(id);
        //List<GdCheckLose> list = gdCheckLoseService.selectGdCheckLoseList(gdCheckLose);

        List<VPsWorkerbase> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date date = null;
        //date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(date);
        //calendar.add(Calendar.DAY_OF_MONTH,-8);
        //date = calendar.getTime();
        //for(int i = 0;i < list.size(); i++){
        //    if(list.get(i).getCheckTime().before(date)){
        //        list.remove(i);
        //        i--;
        //    }
        //}

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        //rspData.setRows(PageByList.starPage(list,pageNum,pageSize));
        rspData.setRows(jsonArray);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    //总工地页面未登记小程序人员
    @PostMapping("/noRegister")
    public TableDataInfo getNoRegister(@RequestBody JSONObject jsonObject) throws Exception {
        //GdWorker gdWorker = new GdWorker();
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        //gdWorker.setPeopleType(1);
        //List<GdWorker> list = gdWorkerService.selectGdWorkerList(gdWorker);
        List<VPsWorkerbase> list = vPsWorkerbaseService.selectVPsWorkerbaseList(new VPsWorkerbase());

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(PageByList.starPage(list, pageNum, pageSize));
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    //分工地页面未登记小程序人员
    @PostMapping("/buildingNoRegister")
    public TableDataInfo getBuildingNoRegister(@RequestBody JSONObject jsonObject) {
        //GdWorker gdWorker = new GdWorker();
        Long id = Long.valueOf(jsonObject.get("id").toString());
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        //gdWorker.setBuildingId(id);
        //gdWorker.setPeopleType(1);
        //List<GdWorker> list = gdWorkerService.selectGdWorkerList(gdWorker);
        GdBuilding gdBuilding = gdBuildingService.selectGdBuildingById(id);
        VPsWorkerbase vPsWorkerbase = new VPsWorkerbase();
        vPsWorkerbase.setProjectguid(gdBuilding.getProjectInfoNum());
        List<VPsWorkerbase> list = vPsWorkerbaseService.selectVPsWorkerbaseList(vPsWorkerbase);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(PageByList.starPage(list, pageNum, pageSize));
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    @Autowired
    private IGdWorkerCheckService gdWorkerCheckService;

    //总工地页面考勤未测温人员
    @PostMapping("/noTemperature")
    public TableDataInfo getNoTemperature(@RequestBody JSONObject jsonObject) throws Exception {
        GdWorkerCheck gdWorkerCheck = new GdWorkerCheck();
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        gdWorkerCheck.setTemperatureType(1);

        PageHelper.startPage(pageNum,pageSize);
        List<TPersoninfo> list = tPersoninfoMapper.selectTPersoninfoList(new TPersoninfo());
        List<VPsWorkerbase> vPsWorkerbases = vPsWorkerbaseService.selectVPsWorkerbaseList(new VPsWorkerbase());
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        for (int i = 0; i < list.size(); i++) {
            String idCard = list.get(i).getIdcard();
            for (int i1 = 0; i1 < vPsWorkerbases.size(); i1++) {
                if(idCard.equals(vPsWorkerbases.get(i1).getIdcard())){
                    jsonArray.getJSONObject(i).put("personphone",vPsWorkerbases.get(i1).getPersonphone());
                }
            }
        }
        //List<GdWorkerCheck> list =gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date date = null;
        //date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(date);
        //calendar.add(Calendar.DAY_OF_MONTH,-8);
        //date = calendar.getTime();
        //for(int i = 0;i < list.size(); i++){
        //    if(list.get(i).getCheckTime().before(date)){
        //        list.remove(i);
        //        i--;
        //    }
        //}
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(jsonArray);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    //分工地页面考勤未测温人员
    @PostMapping("/buildingNoTemperature")
    public TableDataInfo getBuildingNoTemperature(@RequestBody JSONObject jsonObject) throws Exception {
        GdWorkerCheck gdWorkerCheck = new GdWorkerCheck();
        Long id = Long.valueOf(jsonObject.get("id").toString());
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        gdWorkerCheck.setBuildingId(id);
        gdWorkerCheck.setTemperatureType(1);

        GdBuilding gdBuilding = gdBuildingService.selectGdBuildingById(id);
        TPersoninfo tPersoninfo = new TPersoninfo();
        tPersoninfo.setProjectguid(gdBuilding.getProjectInfoNum());
        PageHelper.startPage(pageNum,pageSize);
        List<TPersoninfo> list = tPersoninfoMapper.selectTPersoninfoList(tPersoninfo);
        List<VPsWorkerbase> vPsWorkerbases = vPsWorkerbaseService.selectVPsWorkerbaseList(new VPsWorkerbase());
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        for (int i = 0; i < list.size(); i++) {
            String idCard = list.get(i).getIdcard();
            for (int i1 = 0; i1 < vPsWorkerbases.size(); i1++) {
                if(idCard.equals(vPsWorkerbases.get(i1).getIdcard())){
                    jsonArray.getJSONObject(i).put("personphone",vPsWorkerbases.get(i1).getPersonphone());
                }
            }
        }
        //List<GdWorkerCheck> list =gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);
        //
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date date = null;
        //date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(date);
        //calendar.add(Calendar.DAY_OF_MONTH,-8);
        //date = calendar.getTime();
        //for(int i = 0;i < list.size(); i++){
        //    if(list.get(i).getCheckTime().before(date)){
        //        list.remove(i);
        //        i--;
        //    }
        //}



        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(jsonArray);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    //总工地页面考勤预警数据
    @PostMapping("/checkWarn")
    public HashMap getCheckWarn() throws Exception {
        DecimalFormat df = new DecimalFormat("#.##");
        HashMap map = new HashMap();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        GdWorker gdWorker = new GdWorker();
        GdWorkerCheck gdWorkerCheck = new GdWorkerCheck();
        GdCheckLose gdCheckLose = new GdCheckLose();
        //gdCheckLose.setCheckTime(date);
        //List<GdWorker> gdWorkers = gdWorkerService.selectGdWorkerList(gdWorker);
        List<VPsWorkerbase> vPsWorkerbases = vPsWorkerbaseService.selectVPsWorkerbaseList(new VPsWorkerbase());
        Integer gdWorkerSize = vPsWorkerbases.size();

        List<GdCheckLose> gdCheckLoses = gdCheckLoseService.selectGdCheckLoseList(gdCheckLose);

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = null;
        date = simpleDateFormat2.parse(simpleDateFormat.format(new Date()));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.add(Calendar.DAY_OF_MONTH, -8);
        date2 = calendar2.getTime();
        //for(int i = 0;i < gdCheckLoses.size(); i++){
        //    if(gdCheckLoses.get(i).getCheckTime().before(date2)){
        //        gdCheckLoses.remove(i);
        //        i--;
        //    }
        //}

        List<TPersoninfo> tPersoninfolist = tPersoninfoMapper.selectTPersoninfoListSize(new TPersoninfo());

        Double checkLosePercentage = Double.valueOf(gdWorkerSize - tPersoninfolist.size()) / (Double.valueOf(gdWorkerSize));
        map.put("checkLose", gdWorkerSize - tPersoninfolist.size());
        map.put("checkLosePercentage", df.format(checkLosePercentage));

        gdWorker.setPeopleType(1);

        //List<GdWorker> gdWorkers2 = gdWorkerService.selectGdWorkerList(gdWorker);

        //Double noRegisterPercentage = Double.valueOf(gdWorkers2.size()) / Double.valueOf(gdWorkerSize);
        map.put("noRegister", vPsWorkerbases.size());
        //map.put("noRegisterPercentage",df.format(noRegisterPercentage));
        map.put("noRegisterPercentage", "1");

        //gdWorkerCheck.setCheckTime(date);
        gdWorkerCheck.setTemperatureType(1);
        //List<GdWorkerCheck> gdWorkerChecks =gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);

        //for(int i = 0;i < gdWorkerChecks.size(); i++){
        //    if(gdWorkerChecks.get(i).getCheckTime().before(date2)){
        //        gdWorkerChecks.remove(i);
        //        i--;
        //    }
        //}

        Double workerCheckPercentage = Double.valueOf(tPersoninfolist.size()) / (Double.valueOf(gdWorkerSize));
        map.put("workerCheck", tPersoninfolist.size());
        map.put("workerCheckPercentage", df.format(workerCheckPercentage));
        System.out.println(map);
        return map;
    }

    //分工地页面考勤预警数据
    @GetMapping("/buildingCheckWarn/{id}")
    public HashMap buildingCheckWarn(@PathVariable("id") Long id) throws Exception {
        DecimalFormat df = new DecimalFormat("#.##");
        HashMap map = new HashMap();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        GdWorker gdWorker = new GdWorker();
        gdWorker.setBuildingId(id);
        GdWorkerCheck gdWorkerCheck = new GdWorkerCheck();
        gdWorkerCheck.setBuildingId(id);
        GdCheckLose gdCheckLose = new GdCheckLose();
        gdCheckLose.setBuildingId(id);

        GdBuilding gdBuilding = gdBuildingService.selectGdBuildingById(id);
        VPsWorkerbase vPsWorkerbase = new VPsWorkerbase();
        vPsWorkerbase.setProjectguid(gdBuilding.getProjectInfoNum());

        //gdCheckLose.setCheckTime(date);
        //List<GdWorker> gdWorkers = gdWorkerService.selectGdWorkerList(gdWorker);
        List<VPsWorkerbase> vPsWorkerbases = vPsWorkerbaseService.selectVPsWorkerbaseList(vPsWorkerbase);
        Integer gdWorkerSize = vPsWorkerbases.size();

        List<GdCheckLose> gdCheckLoses = gdCheckLoseService.selectGdCheckLoseList(gdCheckLose);

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = null;
        date = simpleDateFormat2.parse(simpleDateFormat.format(new Date()));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.add(Calendar.DAY_OF_MONTH, -8);
        date2 = calendar2.getTime();
        //for(int i = 0;i < gdCheckLoses.size(); i++){
        //    if(gdCheckLoses.get(i).getCheckTime().before(date2)){
        //        gdCheckLoses.remove(i);
        //        i--;
        //    }
        //}
        TPersoninfo tPersoninfo = new TPersoninfo();
        tPersoninfo.setProjectguid(gdBuilding.getProjectInfoNum());
        List<TPersoninfo> tPersoninfolist = tPersoninfoMapper.selectTPersoninfoListSize(tPersoninfo);
        Double checkLosePercentage = Double.valueOf(gdWorkerSize - tPersoninfolist.size()) / (Double.valueOf(gdWorkerSize));
        map.put("checkLose", gdWorkerSize - tPersoninfolist.size());
        if (checkLosePercentage.isNaN()) {
            map.put("checkLosePercentage", "0");
        } else {
            map.put("checkLosePercentage", df.format(checkLosePercentage));
        }


        //gdWorker.setPeopleType(1);
        //List<GdWorker> gdWorkers2 = gdWorkerService.selectGdWorkerList(gdWorker);
        //Double noRegisterPercentage = Double.valueOf(gdWorkers2.size()) / Double.valueOf(gdWorkerSize);
        map.put("noRegister", vPsWorkerbases.size());
        //map.put("noRegisterPercentage",noRegisterPercentage);
        map.put("noRegisterPercentage", "1");


        //gdWorkerCheck.setCheckTime(date);
        gdWorkerCheck.setTemperatureType(1);
        List<GdWorkerCheck> gdWorkerChecks = gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);

        //for(int i = 0;i < gdWorkerChecks.size(); i++){
        //    if(gdWorkerChecks.get(i).getCheckTime().before(date2)){
        //        gdWorkerChecks.remove(i);
        //        i--;
        //    }
        //}

        Double workerCheckPercentage = Double.valueOf(tPersoninfolist.size()) / (Double.valueOf(gdWorkerSize));
        map.put("workerCheck", tPersoninfolist.size());
        if (workerCheckPercentage.isNaN()) {
            map.put("workerCheckPercentage", "0");
        } else {
            map.put("workerCheckPercentage", df.format(workerCheckPercentage));
        }

        System.out.println(map);
        return map;
    }

    //总工地页面实名认证
    @PostMapping("/checkRegister")
    public HashMap getCheckRegister() throws Exception {
        HashMap reMap = new HashMap();
        JSONArray checkRegister = new JSONArray();
        JSONArray allWorkerCheck = new JSONArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        GdWorkerCheck gdWorkerCheck = new GdWorkerCheck();
        GdWorkerCheck gdWorkerCheckAll = new GdWorkerCheck();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -8);
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, +1);
            date = calendar.getTime();
            gdWorkerCheck.setCheckTime(date);
            gdWorkerCheck.setPeopleType(0);
            List<GdWorkerCheck> gdWorkerChecks = gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);
            HashMap map = new HashMap();
            map.put("date", date);
            map.put("num", gdWorkerChecks.size());
            checkRegister.add(map);
            gdWorkerCheckAll.setCheckTime(date);
            List<GdWorkerCheck> gdWorkerChecksAll = gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheckAll);
            HashMap mapAll = new HashMap();
            mapAll.put("date", date);
            mapAll.put("num", gdWorkerChecksAll.size());
            allWorkerCheck.add(mapAll);
        }
        reMap.put("register", checkRegister);
        reMap.put("all", allWorkerCheck);
        return reMap;
    }

    //分工地页面实名认证
    @GetMapping("/buildingCheckRegister/{id}")
    public HashMap buildingCheckRegister(@PathVariable("id") Long id) throws Exception {
        HashMap reMap = new HashMap();
        JSONArray checkRegister = new JSONArray();
        JSONArray allWorkerCheck = new JSONArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        GdWorkerCheck gdWorkerCheck = new GdWorkerCheck();
        gdWorkerCheck.setBuildingId(id);
        GdWorkerCheck gdWorkerCheckAll = new GdWorkerCheck();
        gdWorkerCheckAll.setBuildingId(id);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -8);
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, +1);
            date = calendar.getTime();
            gdWorkerCheck.setCheckTime(date);
            gdWorkerCheck.setPeopleType(0);
            List<GdWorkerCheck> gdWorkerChecks = gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);
            HashMap map = new HashMap();
            map.put("date", date);
            map.put("num", gdWorkerChecks.size());
            checkRegister.add(map);
            gdWorkerCheckAll.setCheckTime(date);
            List<GdWorkerCheck> gdWorkerChecksAll = gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheckAll);
            HashMap mapAll = new HashMap();
            mapAll.put("date", date);
            mapAll.put("num", gdWorkerChecksAll.size());
            allWorkerCheck.add(mapAll);
        }
        reMap.put("register", checkRegister);
        reMap.put("all", allWorkerCheck);

        return reMap;
    }

    //单个工地视频列表总数
    @GetMapping("/buildingVideoBase/{id}")
    public List<?> buildingVideoBase(@PathVariable("id") Long id) {
        GdVideoBase gdVideoBase = new GdVideoBase();
        gdVideoBase.setId(id);
        return gdVideoBaseService.selectGdVideoBaseList(gdVideoBase);
    }

    //视频设备实时直播地址
    @GetMapping("/videoLive/{channelCode}")
    public JSONObject videoLive(@PathVariable("channelCode") String channelCode) throws Exception {
        String body = VideoLive.getLive(channelCode, "jsjtj002", "jsjtj12345");
        JSONObject videoUrl = JSONObject.parseObject(body);
        return videoUrl;
    }

    //视频设备名称搜索
    @GetMapping("/videoNameSerch/{videoName}")
    public JSONArray videoNameSerch(@PathVariable("videoName") String videoName) throws Exception {
        String url = "resource-catalog/tripartite/authority/channel/page";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNo", 1);
        jsonObject.put("pageSize", 200);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("keyword", videoName);
        jsonObject.put("param", jsonObject1);
        System.out.println(jsonObject);
        String body = VideoLive.getVideoData(jsonObject, url, "jsjtj002", "jsjtj12345");
        //String body = VideoLive.getVideoData(jsonObject,url,"cnjd001","cnjd12345");
        //System.out.println(body);
        JSONObject videoList = JSONObject.parseObject(body);
        String records = videoList.getString("records");
        JSONArray jsonArray = JSONArray.parseArray(records);
        //遍历出拥有工地编号的视频
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject gdRecord = jsonArray.getJSONObject(i);
            if (!gdRecord.containsKey("installAddr")) {
                jsonArray.remove(i);
                i--;
            }
        }
        System.out.println(jsonArray);
        int i = 0;
        return jsonArray;
    }

    //总工地健康码接口
    @PostMapping("/healthCode")
    public JSONArray healthCode() throws Exception {
        List<VPsWorkerbase> vPsWorkerbases = vPsWorkerbaseService.selectVPsWorkerbaseList(new VPsWorkerbase());
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < vPsWorkerbases.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            VPsWorkerbase vPsWorkerbase = vPsWorkerbases.get(i);
            jsonObject.put("idNumber", vPsWorkerbase.getIdcard());
            JSONObject warn = healthApi(jsonObject);
            if (!(warn == null)) {
                warn.put("gdmc", vPsWorkerbase.getProjectname());
                jsonArray.add(warn);
            }
        }
        return jsonArray;
    }
    //根据备案号查询塔吊信息
    @GetMapping("/tower/{recordNo}")
    public GdTower tower(@PathVariable String recordNo){
        GdTower tower = new GdTower();
        tower.setRecordNo(recordNo);
        List<GdTower> gdTowers = gdTowerMapper.selectGdTowerList(tower);
        return gdTowers.get(0);
    }
    @Autowired
    Config2RestTemplate config2RestTemplate;
    @PostMapping("/gdTower")
    public void gdTower() throws Exception {
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
            jsonObject.put("projectId",building.getProjectInfoNum());
            jsonObject.put("deviceFactoryName","zhiAn");
            jsonObject.put("token","23b4f8af-ff00-40d9-9edc-7774ee825d61");
            System.out.println(client.postForObject(url,jsonObject,JSONObject.class));
            JSONObject jsonObject1 = client.postForObject(url,jsonObject,JSONObject.class);
//            JSONObject jsonObject1 = HttpClientUtils.doPost(url,jsonObject);
            System.out.println(jsonObject1);

            if(jsonObject1.getJSONArray("data").size() > 0 ){
                JSONArray jsonArray = jsonObject1.getJSONArray("data");
                for (int j = 0; j < jsonArray.size(); j++) {
                    jsonObject.put("recordNo",jsonArray.get(j));
                    jsonObject.put("pageNum",1);
                    jsonObject.put("pageSize",1);
                    JSONObject jsonObject2 = client.postForObject(url2,jsonObject,JSONObject.class);
                    JSONObject data = new JSONObject();
                    JSONObject data2 = jsonObject2.getJSONObject("data");
                    if(data2.getJSONArray("rows").size() > 0){
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
                        while (iterator.hasNext()){
                            Map.Entry entry = (Map.Entry) iterator.next();
                            if(!(entry.getValue().equals("normal"))){
                                tower.setSensorType(1);
                            }else {
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
    @PostMapping("/aaa")
    public void aaa() throws Exception {
        List<GdBuilding> gdBuildings = gdBuildingMapper.selectGdBuildingList(new GdBuilding());
        RestTemplate client = config2RestTemplate.restTemplate();
        String url = "https://cloud.zqanke.cn/developer-api/device/getRecordNoList";
        String url2 = "https://cloud.zqanke.cn/developer-api/device/getRealTimeData";
        for (int i = 0; i < gdBuildings.size(); i++) {
            GdBuilding building = gdBuildings.get(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("projectId",building.getProjectInfoNum());
            jsonObject.put("deviceFactoryName","zhiAn");
            jsonObject.put("token","23b4f8af-ff00-40d9-9edc-7774ee825d61");
            System.out.println("遍历projectId的备案号的请求数据:" + jsonObject);
            JSONObject jsonObject1 = client.postForObject(url,jsonObject,JSONObject.class);
            System.out.println("返回的数据" + jsonObject1);
        }
    }

    //塔吊预警
    @PostMapping("/towerWarn")
    public List<?> towerWarn(){
        GdTower tower = new GdTower();
        tower.setSensorType(1);
        List<GdTower> gdTowers = gdTowerMapper.selectGdTowerList(tower);
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < gdTowers.size(); i++) {
            GdTower tower1 = gdTowers.get(i);
            GdTower tower2 = null;
            if(!(tower1.getWeightWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("重量预警");

                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getMomentWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("力矩预警");
                
                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getRightRotationWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("右回转预警");
                
                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getLeftRotationWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("左回转预警");
                
                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getHeightLowerWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("高度下预警");
                
                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getHeightUpWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("高度上预警");
                
                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getInclinationYWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("Y轴倾角预警");
                
                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getInclinationXWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("X轴倾角预警");
                
                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getWindSpeedWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("风速预警");
                
                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getSmallMarginWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("小幅预警");
                
                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getBigMarginWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("大幅度预警");
                
                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
        }
        return jsonArray;
    }

    @PostMapping("/towerRecordNo")
    public JSONArray towerRecordNo(){
        List<GdTower> gdTowers = gdTowerMapper.selectGdTowerList(new GdTower());
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < gdTowers.size(); i++) {
            GdTower tower = gdTowers.get(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("recordNo",tower.getRecordNo());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    //单个工地塔吊信息
    @GetMapping("/towerSin/{projectId}")
    public GdTower towerSin(@PathVariable String projectId){
        GdTower tower = new GdTower();
        tower.setProjectId(projectId);
        List<GdTower> gdTowers = gdTowerMapper.selectGdTowerList(tower);
        return gdTowers.get(0);
    }

    @GetMapping("/towerSinWarn/{projectId}")
    public JSONArray towerSinWarn(@PathVariable String projectId){
        GdTower tower = new GdTower();
        tower.setProjectId(projectId);
        tower.setSensorType(1);
        List<GdTower> gdTowers = gdTowerMapper.selectGdTowerList(tower);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < gdTowers.size(); i++) {
            GdTower tower1 = gdTowers.get(i);
            GdTower tower2 = null;
            if(!(tower1.getWeightWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("重量预警");

                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getMomentWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("力矩预警");

                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getRightRotationWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("右回转预警");

                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getLeftRotationWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("左回转预警");

                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getHeightLowerWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("高度下预警");

                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getHeightUpWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("高度上预警");

                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getInclinationYWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("Y轴倾角预警");

                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getInclinationXWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("X轴倾角预警");

                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getWindSpeedWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("风速预警");

                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getSmallMarginWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("小幅预警");

                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
            if(!(tower1.getBigMarginWarn().equals("normal"))){
                tower2 = new GdTower();
                tower2.setSensorStatus("大幅度预警");

                tower2.setWarnTime(tower1.getWarnTime());
                tower2.setRecordNo(tower1.getRecordNo());
                tower2.setSensorType(1);
                tower2.setProjectName(tower1.getProjectName());
                jsonArray.add(tower2);
            }
        }
        return jsonArray;

    }


    @Autowired JkgaConfig jkgaConfig;
    //调用健康码接口方法
    public JSONObject healthApi(JSONObject jsonObject) throws Exception {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        cal1.add(Calendar.DATE, -4);
        String url = "http://133.1.254.22:18093/provinceImportant/findNewHealthCodeResultByIdNumber";
        JSONObject data = HttpClientUtils.doPost(url, jsonObject);
        JSONObject jsonObject1 = data.getJSONObject("data");
        JSONObject jsonObject2 = jsonObject1.getJSONObject("datas");
        JSONArray jsonArray = jsonObject2.getJSONArray("data");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject ifwarn = jsonArray.getJSONObject(i);
            String zjgxsj = ifwarn.getString("zjgxsj");
            System.out.println(zjgxsj);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = df.parse(zjgxsj);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (ifwarn.getString("mzt").equals("黄码") && ifwarn.getString("mffd").equals("嘉兴市")) {
                if (calendar.after(cal1)) {
                    return ifwarn;
                }
            }
            if (ifwarn.getString("mzt").equals("红码") && ifwarn.getString("mffd").equals("嘉兴市")) {
                if (calendar.after(cal1)) {
                    return ifwarn;
                }
            }
        }
        return null;
    }
    @Autowired
    private ThreadPoolConfig threadPoolConfig;
    @Autowired
    private AsyncUtil asyncUtil;

    @PostMapping("/jkm3")
    public JSONArray jkm3()throws Exception{
        List<VPsWorkerbase> list = vPsWorkerbaseService.selectVPsWorkerbaseList(new VPsWorkerbase());
        JSONArray idcardArray = new JSONArray();
        String idCard = "(";
        int count = 0;
        for (int i = 0; i < list.size()-1; i++) {
            idCard = idCard + "'" + list.get(i).getIdcard() + "',";
            count++;
            if (count == 1000) {
                idCard = idCard.substring(0, idCard.length() - 1);
                idCard = idCard + ")";
                idcardArray.add(idCard);
                idCard = "(";
                count = 0;
            }
        }
        idCard = idCard + "'" +  list.get(list.size()-1).getIdcard() + "')";
        idcardArray.add(idCard);
        BlockingQueue<Future<JSONObject>> queue = new LinkedBlockingQueue();
        Future<JSONObject> future;
        for (int i = 0; i < idcardArray.size(); i++) {
            JSONObject sendJson = new JSONObject();
            sendJson.put("page_now",0);
            sendJson.put("page_size",10000);
            sendJson.put("sfzh_list",idcardArray.get(i));
            future = asyncUtil.queryJkm(sendJson);
            queue.add(future);
        }
        int queueSize = queue.size();
        JSONArray data = new JSONArray();
        for (int j = 0; j < queueSize; j++) {
            System.out.println(j);
            JSONObject jsonObject1 = queue.take().get();
            JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
            JSONObject jsonObject3 = jsonObject2.getJSONObject("data");
            JSONArray jsonArray = jsonObject3.getJSONArray("result");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject4 = jsonArray.getJSONObject(i);
                if (jsonObject4.getString("mzt").equals("黄码") && jsonObject4.getString("mffd").equals("嘉兴市")) {
                    String sfzh = jsonObject4.getString("sfzh");
                    VPsWorkerbase vPsWorkerbase = new VPsWorkerbase();
                    vPsWorkerbase.setIdcard(sfzh);
                    List<VPsWorkerbase> vPsWorkerbases = vPsWorkerbaseService.selectVPsWorkerbaseList(vPsWorkerbase);
                    String gdmc = "";
                    if (vPsWorkerbases.size() > 0) {
                        gdmc = vPsWorkerbases.get(0).getProjectname();
                    }
                    jsonObject4.put("gdmc", gdmc);
                    data.add(jsonObject4);
                }
                if (jsonObject4.getString("mzt").equals("红码") && jsonObject4.getString("mffd").equals("嘉兴市")) {
                    String sfzh = jsonObject4.getString("sfzh");
                    VPsWorkerbase vPsWorkerbase = new VPsWorkerbase();
                    vPsWorkerbase.setIdcard(sfzh);
                    List<VPsWorkerbase> vPsWorkerbases = vPsWorkerbaseService.selectVPsWorkerbaseList(vPsWorkerbase);
                    String gdmc = "";
                    if (vPsWorkerbases.size() > 0) {
                        gdmc = vPsWorkerbases.get(0).getProjectname();
                    }
                    jsonObject4.put("gdmc", gdmc);
                    data.add(jsonObject4);
                }

            }
        }
        return data;
    }

    //健康码预警
    @PostMapping("/jkm")
    public JSONArray jkm(){
        RestTemplate restTemplate = new RestTemplate();
        String url = jkgaConfig.getJkm();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page_now",0);
        jsonObject.put("page_size",10000);
        List<VPsWorkerbase> list = vPsWorkerbaseService.selectVPsWorkerbaseList(new VPsWorkerbase());
        String idCard = "(";
        for (int i = 0; i < list.size()-1; i++) {
            idCard = idCard  + "'" +  list.get(i).getIdcard() + "'," ;
        }
        idCard = idCard + "'" +  list.get(list.size()-1).getIdcard() + "')";
        jsonObject.put("sfzh_list",idCard);
//        jsonObject.put("sfzh_list","('511023197608144190','362232197407240047')");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1 = restTemplate.postForObject(url,jsonObject,JSONObject.class);
        JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
        JSONObject jsonObject3 = jsonObject2.getJSONObject("data");
        JSONArray jsonArray = jsonObject3.getJSONArray("result");
        for (int i = 0; i < jsonArray.size(); i++) {
            System.out.println(jsonArray.getJSONObject(i));
        }
        JSONArray data = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject4 = jsonArray.getJSONObject(i);
            if (jsonObject4.getString("mzt").equals("黄码") && jsonObject4.getString("mffd").equals("嘉兴市")) {
                String sfzh = jsonObject4.getString("sfzh");
                VPsWorkerbase vPsWorkerbase = new VPsWorkerbase();
                vPsWorkerbase.setIdcard(sfzh);
                List<VPsWorkerbase> vPsWorkerbases = vPsWorkerbaseService.selectVPsWorkerbaseList(vPsWorkerbase);
                String gdmc = "";
                if (vPsWorkerbases.size()>0) {
                    gdmc = vPsWorkerbases.get(0).getProjectname();
                }
                jsonObject4.put("gdmc",gdmc);
                data.add(jsonObject4);
            }
            if (jsonObject4.getString("mzt").equals("红码") && jsonObject4.getString("mffd").equals("嘉兴市")) {
                String sfzh = jsonObject4.getString("sfzh");
                VPsWorkerbase vPsWorkerbase = new VPsWorkerbase();
                vPsWorkerbase.setIdcard(sfzh);
                List<VPsWorkerbase> vPsWorkerbases = vPsWorkerbaseService.selectVPsWorkerbaseList(vPsWorkerbase);
                String gdmc = "";
                if (vPsWorkerbases.size()>0) {
                    gdmc = vPsWorkerbases.get(0).getProjectname();
                }
                jsonObject4.put("gdmc",gdmc);
                data.add(jsonObject4);
            }
//            JSONObject jsonObject5 = jsonObject4.getJSONObject("wbtjbdxx");
//            System.out.println(jsonObject5);
        }
        return data;
    }

    @GetMapping("/jkmSin/{id}")
    public JSONArray jkmSin(@PathVariable Long id){
        RestTemplate restTemplate = new RestTemplate();
        String url = jkgaConfig.getJkm();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page_now",0);
        jsonObject.put("page_size",1000);
        GdBuilding gdBuilding = gdBuildingService.selectGdBuildingById(id);
        VPsWorkerbase vPsWorkerbase1 = new VPsWorkerbase();
        vPsWorkerbase1.setProjectguid(gdBuilding.getProjectInfoNum());
        List<VPsWorkerbase> list = vPsWorkerbaseService.selectVPsWorkerbaseList(vPsWorkerbase1);
        String idCard = "(";
        for (int i = 0; i < list.size()-1; i++) {
            idCard = idCard  + "'" +  list.get(i).getIdcard() + "'," ;
        }
        idCard = idCard + "'" +  list.get(list.size()-1).getIdcard() + "')";
        jsonObject.put("sfzh_list",idCard);
//        jsonObject.put("sfzh_list","('511023197608144190','362232197407240047')");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1 = restTemplate.postForObject(url,jsonObject,JSONObject.class);
        JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
        JSONObject jsonObject3 = jsonObject2.getJSONObject("data");
        JSONArray jsonArray = jsonObject3.getJSONArray("result");
        for (int i = 0; i < jsonArray.size(); i++) {
            System.out.println(jsonArray.getJSONObject(i));
        }
        JSONArray data = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject4 = jsonArray.getJSONObject(i);
            if (jsonObject4.getString("mzt").equals("黄码") && jsonObject4.getString("mffd").equals("嘉兴市")) {
                String sfzh = jsonObject4.getString("sfzh");
                VPsWorkerbase vPsWorkerbase = new VPsWorkerbase();
                vPsWorkerbase.setIdcard(sfzh);
                List<VPsWorkerbase> vPsWorkerbases = vPsWorkerbaseService.selectVPsWorkerbaseList(vPsWorkerbase);
                String gdmc = "";
                if (vPsWorkerbases.size()>0) {
                    gdmc = vPsWorkerbases.get(0).getProjectname();
                }
                jsonObject4.put("gdmc",gdmc);
                data.add(jsonObject4);
            }
            if (jsonObject4.getString("mzt").equals("红码") && jsonObject4.getString("mffd").equals("嘉兴市")) {
                String sfzh = jsonObject4.getString("sfzh");
                VPsWorkerbase vPsWorkerbase = new VPsWorkerbase();
                vPsWorkerbase.setIdcard(sfzh);
                List<VPsWorkerbase> vPsWorkerbases = vPsWorkerbaseService.selectVPsWorkerbaseList(vPsWorkerbase);
                String gdmc = "";
                if (vPsWorkerbases.size()>0) {
                    gdmc = vPsWorkerbases.get(0).getProjectname();
                }
                jsonObject4.put("gdmc",gdmc);
                data.add(jsonObject4);
            }
//            JSONObject jsonObject5 = jsonObject4.getJSONObject("wbtjbdxx");
//            System.out.println(jsonObject5);
        }
        return data;
    }

    //
    //列表数据处理
    public HashMap listData(HashMap map, String name, List<?> objects) {
        if (objects.size() > 0) {
            map.put(name, objects);
        } else {
            map.put(name, "null");
        }
        return map;
    }

    //单个数据处理
    public HashMap singleData(HashMap map, String name, Object object, List<?> objects) {
        if (objects.size() > 0) {
            object = objects.get(0);
            //JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(object,SerializerFeature.WriteMapNullValue));
            //map.put("name",jsonObject);
            map.put("name", object);
        } else {
            map.put("name", "null");
        }
        return map;
    }

    //获取当前天的扬尘设备的平均值
    public HashMap averageDust(Calendar calendar, List<GdDustData> list) {
        HashMap map = new HashMap();

        Double pm25T = 0.00;
        Long tip25T = 0L;
        Long tip10T = 0L;
        BigDecimal tip = new BigDecimal(1);
        for (int i = 0; i < list.size(); i++) {
            Date date1 = list.get(i).getDate();
            Calendar call = Calendar.getInstance();
            call.setTime(date1);
            call.set(Calendar.HOUR_OF_DAY, 0);
            call.set(Calendar.MINUTE, 0);
            call.set(Calendar.SECOND, 0);
            call.set(Calendar.MILLISECOND, 0);
            if (calendar.equals(call)) {
                tip25T++;
                //BigDecimal listPm25 = new BigDecimal(list.get(i).getPm25());
                pm25T = pm25T + list.get(i).getPm25();
            }
        }
        BigDecimal pm25 = new BigDecimal(pm25T);
        BigDecimal tip25 = new BigDecimal(tip25T);
        if (tip25T.equals(0L)) {
            map.put("pm25", 0);
        } else {
            System.out.println(pm25);
            System.out.println(tip25T);
            System.out.println(pm25.divide(tip25, 2, BigDecimal.ROUND_HALF_UP));
            map.put("pm25", pm25.divide(tip25, 2, BigDecimal.ROUND_HALF_UP));
        }

        Double pm10T = 0.00;
        for (int i = 0; i < list.size(); i++) {
            Date date1 = list.get(i).getDate();
            Calendar call = Calendar.getInstance();
            call.setTime(date1);
            call.set(Calendar.HOUR_OF_DAY, 0);
            call.set(Calendar.MINUTE, 0);
            call.set(Calendar.SECOND, 0);
            call.set(Calendar.MILLISECOND, 0);
            if (calendar.equals(call)) {
                tip10T++;
                pm10T = pm10T + list.get(i).getPm10();
            }
        }
        BigDecimal pm10 = new BigDecimal(pm10T);
        BigDecimal tip10 = new BigDecimal(tip10T);
        if (tip10T.equals(0L)) {
            map.put("pm10", 0);
        } else {
            map.put("pm10", pm10.divide(tip10, 2, BigDecimal.ROUND_HALF_UP));
        }
        map.put("date", calendar.getTime());
        return map;
    }

    //返回7天内数据
    public List<?> sevenData(List<?> list, Date dateCheckTime, Integer i) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -8);
        date = calendar.getTime();
        if (dateCheckTime.before(date)) {
            list.remove(i);
        }
        return list;
    }
    @PostMapping("/delData")
    public void delData(){
        deMqRlzp();
        deYyCwmj();
        deZdry();
        deMqCar();
        deLowPointWarn();
        deGzsx();
    }


    @Autowired
    private MqRlzpMapper mqRlzpMapper;
    @Autowired
    private YyCwmjMapper yyCwmjMapper;
    @Autowired
    private YjLowPointWarnMapper yjLowPointWarnMapper;
    @Autowired
    private MqCarMapper mqCarMapper;

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

    @Autowired
    private YyPerceptionTypeMapper yyPerceptionTypeMapper;
    //删除感知筛选中的七天前的数据
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























