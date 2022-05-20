package com.ruoyi.jkza.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.jkza.domain.*;
import com.ruoyi.jkza.mapper.*;
import com.ruoyi.jkza.service.*;
import com.ruoyi.jkza.util.HttpClientUtils;
import com.ruoyi.jkza.util.VideoLive;
import io.lettuce.core.ScriptOutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

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

    //所有工地信息
    @PostMapping("/buildingList")
    public List<?> getBuildingList(){
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
    public Object getBase(){
        JSONObject jsonObject = new JSONObject();
        List<GdBuilding> list = gdBuildingService.selectGdBuildingList(new GdBuilding());
        List<VPsWorkerbase> list2 = vPsWorkerbaseService.selectVPsWorkerbaseList(new VPsWorkerbase());
        List<DtMachine> list3 = dtMachineService.selectDtMachineList(new DtMachine());
        Double price = 0.00;
        Long liftingEquipment = 0L;
        Long liftingEquipmentFiling = 0L;
        for(int i = 0; i < list.size(); i++){
            price = price + list.get(i).getPrice();
        }
        for(int j = 0; j <list3.size(); j++){
            liftingEquipment = liftingEquipment + list3.get(j).getInstall();
            liftingEquipmentFiling = liftingEquipmentFiling + list3.get(j).getDetection();
        }

        DecimalFormat df = new DecimalFormat("#.##");
        price = Double.valueOf(df.format(price));
        jsonObject.put("buildingSite",list.size());
        jsonObject.put("buildingSitePrice",price);
        jsonObject.put("buildingSiteWorker",list2.size());
        jsonObject.put("buildingSiteDuty",9865);
        jsonObject.put("liftingEquipment",liftingEquipment);
        jsonObject.put("liftingEquipmentFiling",liftingEquipmentFiling);
        return jsonObject;
    }
    //管理人员考勤
    @PostMapping("/managerCheck")
    public Object getManagerCheck(){
        Calendar cal = Calendar.getInstance();
        DateCal dateCal = new DateCal();
        dateCal.setYear(cal.get(Calendar.YEAR));
        dateCal.setMonth(cal.get(Calendar.MONTH));
        Integer degree = (cal.getActualMaximum(Calendar.DATE)-1)*2;
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

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getPostName().equals("项目负责人")){
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
                manager = manager.add(tip);
                managerPer = managerPer.add(checkPer);
            }
            if(list.get(i).getPostName().equals("项目总监理工程师")){
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
                register = register.add(tip);
                registerPer = registerPer.add(checkPer);
            }
            if(list.get(i).getPostName().equals("安全员")){
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
                safety = safety.add(tip);
                safetyPer = safetyPer.add(checkPer);
            }
            if(list.get(i).getPostName().equals("专业监理工程师")){
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
                professinoal = professinoal.add(tip);
                professinoalPer = professinoalPer.add(checkPer);
            }
        }
        jsonObject.put("manager",manager);
        jsonObject.put("managerPer",(managerPer.divide(manager,4,BigDecimal.ROUND_HALF_UP)));
        jsonObject.put("register",register);
        jsonObject.put("registerPer",registerPer.divide(register,4,BigDecimal.ROUND_HALF_UP));
        jsonObject.put("safety",safety);
        jsonObject.put("safetyPer",safetyPer.divide(safety,4,BigDecimal.ROUND_HALF_UP));
        jsonObject.put("professinoal",professinoal);
        jsonObject.put("professinoalPer",professinoalPer.divide(professinoal,4,BigDecimal.ROUND_HALF_UP));
        return jsonObject;
    }

    //单个工地管理人员考勤
    @GetMapping("/buildingManagerCheck/{id}")
    public Object buildingManagerCheck(@PathVariable("id") Long id){
        Calendar cal = Calendar.getInstance();
        GdBuilding gdBuilding = gdBuildingService.selectGdBuildingById(id);
        DateCal dateCal = new DateCal();
        dateCal.setYear(cal.get(Calendar.YEAR));
        dateCal.setMonth(cal.get(Calendar.MONTH));
        dateCal.setProjectGuid(gdBuilding.getProjectInfoNum());
        Integer degree = (cal.getActualMaximum(Calendar.DATE)-1)*2;
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

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getPostName().equals("项目负责人")){
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
                manager = manager.add(tip);
                managerPer = managerPer.add(checkPer);
            }
            if(list.get(i).getPostName().equals("项目总监理工程师")){
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
                register = register.add(tip);
                registerPer = registerPer.add(checkPer);
            }
            if(list.get(i).getPostName().equals("安全员")){
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
                safety = safety.add(tip);
                safetyPer = safetyPer.add(checkPer);
            }
            if(list.get(i).getPostName().equals("专业监理工程师")){
                BigDecimal a = new BigDecimal(list.get(i).getDegree());
                BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
                professinoal = professinoal.add(tip);
                professinoalPer = professinoalPer.add(checkPer);
            }
        }
        jsonObject.put("manager",manager);
        if(manager.equals(BigDecimal.ZERO)){
            jsonObject.put("managerPer",0);
        }else{
            jsonObject.put("managerPer",(managerPer.divide(manager,4,BigDecimal.ROUND_HALF_UP)));
        }
        jsonObject.put("register",register);
        if(register.equals(BigDecimal.ZERO)){
            jsonObject.put("registerPer",0);
        }else {
            jsonObject.put("registerPer",registerPer.divide(register,4,BigDecimal.ROUND_HALF_UP));
        }
        jsonObject.put("safety",safety);
        if(safety.equals(BigDecimal.ZERO)){
            jsonObject.put("safetyPer",0);
        }else {
            jsonObject.put("safetyPer",safetyPer.divide(safety,4,BigDecimal.ROUND_HALF_UP));
        }
        jsonObject.put("professinoal",professinoal);
        if(professinoal.equals(BigDecimal.ZERO)){
            jsonObject.put("professinoalPer",0);
        }else {
            jsonObject.put("professinoalPer",professinoalPer.divide(professinoal,4,BigDecimal.ROUND_HALF_UP));
        }
        return jsonObject;
    }
    //街道信息
    @PostMapping("/street")
    public JSONArray getStreet(){
        //HashMap map = new HashMap();
        GdStreet gdStreet = new GdStreet();
        GdBuilding gdBuilding = new GdBuilding();
        List<GdStreet> gdStreets = gdStreetService.selectGdStreetList(gdStreet);
        List<GdBuilding> gdBuildings = gdBuildingService.selectGdBuildingList(gdBuilding);
        //map = singleData(map,"street",gdStreet,gdStreets);
        //查找到工地与街道，归类到以街道为中心
        JSONArray jsonArrayNew = new JSONArray();
        for(int i = 0; i < gdStreets.size(); i++){
            HashMap mapNew = new HashMap();
            Long streetId = gdStreets.get(i).getId();
            String streetName = gdStreets.get(i).getStreetName();

            JSONArray jsonArray = new JSONArray();
            for(int j = 0; j < gdBuildings.size(); j++){
                if(streetId.equals(gdBuildings.get(j).getStreetId())){
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(gdBuildings.get(j)));
                    jsonArray.add(jsonObject);
                }
            }
            //mapNew.put(streetName,jsonArray);
            mapNew.put("name",streetName);
            mapNew.put("streetId",streetId);
            mapNew.put("children",jsonArray);
            jsonArrayNew.add(mapNew);
        }
        return jsonArrayNew;
    }

    @Autowired
    private DtRectificationnoticeMapper dtRectificationnoticeMapper;
    //整改通知
    @PostMapping("/synergy")
    public List<?> getSynergy(){
        List<DtRectificationnotice> list = dtRectificationnoticeMapper.selectList(new DtRectificationnotice());
        return list;
    }
    //重点人员预警
    @PostMapping("/keyPerson")
    public List<?> getKeyPerson(){
        //HashMap map = new HashMap();
        GdKeyPerson gdKeyPerson = new GdKeyPerson();
        List<GdKeyPerson> gdKeyPeople = gdKeyPersonService.selectGdKeyPersonList(gdKeyPerson);
        //map = listData(map,"keyPerson", gdKeyPeople);
        return gdKeyPeople;
    }

    @Autowired
    private ILtdqExcessivestatisticaldataService ltdqExcessivestatisticaldataService;
    //扬尘设备数据
    @PostMapping("/dustData")
    public JSONArray getDustData() throws Exception{
        HashMap map = new HashMap();
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));

        //List<LtdqExcessivestatisticaldata> list = ltdqExcessivestatisticaldataService.selectLtdqExcessivestatisticaldataList(new LtdqExcessivestatisticaldata());
        List<GdDustData> list = gdDustDataMapper.selectGdDustDataList(new GdDustData());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-8);
        for(int i = 0; i < 7; i++){
            //averageDust(date,gdDustData1);
            calendar.add(Calendar.DAY_OF_MONTH,+1);
            date = calendar.getTime();
            jsonArray.add(averageDust(calendar,list));
        }
        //map = listData(map,"dustData", gdDustData1);
        return jsonArray;
    }
    //扬尘设备数量(总)
    @PostMapping("/dustEquipment")
    public Integer getDustEquipment(){
        GdDustEquipment gdDustEquipment = new GdDustEquipment();
        List<GdDustEquipment> gdDustEquipments = gdDustEquipmentService.selectGdDustEquipmentList(gdDustEquipment);
        return gdDustEquipments.size();
    }

    //视频列表
    @PostMapping("/videoBase")
    public List<?> getVideoBase(){
        //HashMap map = new HashMap();
        GdVideoBase gdVideoBase = new GdVideoBase();
        List<GdVideoBase> gdVideoBases = gdVideoBaseService.selectGdVideoBaseList(gdVideoBase);
        //map = listData(map,"videoBase", gdVideoBases);
        return gdVideoBases;
    }


    //视频设备信息
    @PostMapping("/video")
    public JSONArray getVideo(){
        //下面是从大华调取视频设备接口的方法
        //分页查询个人有权限视频通道资源
        String url = "resource-catalog/tripartite/authority/channel/page";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNo",1);
        jsonObject.put("pageSize",200);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("keyword","");
        jsonObject.put("param",jsonObject1);
        String body = VideoLive.getVideoData(jsonObject,url,"jsjtj001","jsjtj12345");
        //String body = VideoLive.getVideoData(jsonObject,url,"cnjd001","cnjd12345");
        System.out.println(body);
        JSONObject videoList = JSONObject.parseObject(body);
        String records = videoList.getString("records");
        JSONArray jsonArray = JSONArray.parseArray(records);
        //遍历出拥有工地编号的视频
        for(int i = 0; i < jsonArray.size();i++){
            JSONObject gdRecord = jsonArray.getJSONObject(i);
            if(!gdRecord.containsKey("installAddr")){
                jsonArray.remove(i);
                i--;
            }
        }
        int videoTotal = jsonArray.size();
        int videoOnline = 0;
        for(int q = 0; q < videoTotal; q++){
            JSONObject recordOnline = jsonArray.getJSONObject(q);
            if(recordOnline.getString("networkStatus").equals("1")){
                videoOnline++;
            }
        }
        GdVideoBase gdVideoBase = new GdVideoBase();
        gdVideoBase.setId(1L);
        gdVideoBase.setVideoSum((long) videoTotal);
        gdVideoBase.setVideoDuty((long) videoOnline);
        gdVideoBaseService.updateGdVideoBase(gdVideoBase);


        HashSet hashSet = new HashSet();
        for(int i = 0; i < jsonArray.size(); i++){
            hashSet.add(jsonArray.getJSONObject(i).getString("installAddr"));
        }
        List<String> gdGuidList = new ArrayList<String>(hashSet);
        JSONArray jsonArrayNew = new JSONArray();

        for(int j = 0; j < hashSet.size(); j++){
            String gdGuid = gdGuidList.get(j);
            GdBuilding gdBuilding = new GdBuilding();
            gdBuilding = gdBuildingMapper.selectGdBuildingByGuid(gdGuid);
            JSONObject gdAndVideo = new JSONObject();
            gdAndVideo.put("name",gdBuilding.getBuildingSiteName());
            JSONArray videoChildrenList = new JSONArray();
            for(int k = 0; k < jsonArray.size(); k++){
                if(gdGuid.equals(jsonArray.getJSONObject(k).getString("installAddr"))){
                    videoChildrenList.add(jsonArray.getJSONObject(k));
                }
                gdAndVideo.put("children",videoChildrenList);
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

    @PostMapping("/test")
    public void test(){

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
        //List<TPersoninfo> list = tPersoninfoMapper.selectList(new TPersoninfo());
        //
        //HashSet hashSet = new HashSet();
        //for(int i = 0; i < list.size(); i++){
        //    TPersoninfo tPersoninfo = new TPersoninfo();
        //}


        //处理管理人员考勤数据
        //Calendar cal = Calendar.getInstance();
        //DateCal dateCal = new DateCal();
        //dateCal.setYear(cal.get(Calendar.YEAR));
        //dateCal.setMonth(cal.get(Calendar.MONTH));
        //Integer degree = (cal.getActualMaximum(Calendar.DATE)-1)*2;
        //BigDecimal total = new BigDecimal(degree);
        //List<TAttAttendancemonthstat> list = tAttAttendancemonthstatMapper.selectList(dateCal);
        //JSONObject jsonObject = new JSONObject();
        //BigDecimal manager = new BigDecimal(0);
        //BigDecimal managerPer = new BigDecimal(0);
        //BigDecimal safety = new BigDecimal(0);
        //BigDecimal safetyPer = new BigDecimal(0);
        //BigDecimal register = new BigDecimal(0);
        //BigDecimal registerPer = new BigDecimal(0);
        //BigDecimal professinoal = new BigDecimal(0);
        //BigDecimal professinoalPer = new BigDecimal(0);
        //BigDecimal tip = new BigDecimal(1);
        //
        //for(int i = 0; i < list.size(); i++){
        //    if(list.get(i).getPostName().equals("项目负责人")){
        //        BigDecimal a = new BigDecimal(list.get(i).getDegree());
        //        BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
        //        manager = manager.add(tip);
        //        managerPer = managerPer.add(checkPer);
        //    }
        //    if(list.get(i).getPostName().equals("项目总监理工程师")){
        //        BigDecimal a = new BigDecimal(list.get(i).getDegree());
        //        BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
        //        register = register.add(tip);
        //        registerPer = registerPer.add(checkPer);
        //    }
        //    if(list.get(i).getPostName().equals("安全员")){
        //        BigDecimal a = new BigDecimal(list.get(i).getDegree());
        //        BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
        //        safety = safety.add(tip);
        //        safetyPer = safetyPer.add(checkPer);
        //    }
        //    if(list.get(i).getPostName().equals("专业监理工程师")){
        //        BigDecimal a = new BigDecimal(list.get(i).getDegree());
        //        BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
        //        professinoal = professinoal.add(tip);
        //        professinoalPer = professinoalPer.add(checkPer);
        //    }
        //
        //    System.out.println(list.get(i).getPostName());
        //    BigDecimal a = new BigDecimal(list.get(i).getDegree());
        //    BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
        //    System.out.println(list.get(i).getDegree());
        //    System.out.println(checkPer);
        //}
        //jsonObject.put("项目负责人",manager);
        //jsonObject.put("项目负责人百分比",(managerPer.divide(manager,4,BigDecimal.ROUND_HALF_UP)));
        //jsonObject.put("项目总监理工程师",register);
        //jsonObject.put("项目总监理工程师百分比",registerPer.divide(register,4,BigDecimal.ROUND_HALF_UP));
        //jsonObject.put("安全员",safety);
        //jsonObject.put("安全员百分比",safetyPer.divide(safety,4,BigDecimal.ROUND_HALF_UP));
        //jsonObject.put("专业监理工程师",professinoal);
        //jsonObject.put("专业监理工程师百分比",professinoalPer.divide(professinoal,4,BigDecimal.ROUND_HALF_UP));




        //处理工人考勤数据


        //把工地信息从stproject拷贝到工地表
        //gdBuildingMapper.truncateGdBuilding();
        //gdBuildingMapper.copyToGdBuilding();

        //String projectInfoNum = "0af85c37-e419-4dd0-9d8b-13d85049de63";
        //GdBuilding gdBuilding = new GdBuilding();
        //gdBuilding = gdBuildingMapper.selectGdBuildingByGuid(projectInfoNum);
        //System.out.println(gdBuilding);


        //List<GdBuilding> gdBuildings = gdBuildingService.selectGdBuildingList(new GdBuilding());
        //List<GdStreet> gdStreets = gdStreetService.selectGdStreetList(new GdStreet());
        //for(int i = 0; i < gdBuildings.size(); i++){
        //    GdBuilding gdBuilding = gdBuildings.get(i);
        //    if(!gdBuilding.getStreetName().isEmpty()){
        //
        //        gdBuilding.getStreetName()
        //    }
        //}

        //StProject stProject = new StProject();
        //GdBuilding gdBuilding = new GdBuilding();
        //List<StProject> stProjects = stProjectService.selectStProjectList(stProject);
        //for(int i = 0; i < stProjects.size(); i++){
        //    stProject = stProjects.get(i);
        //    gdBuilding.setProjectInfoNum(stProject.getGuid());
        //    gdBuilding.setBuildingSiteName(stProject.getProjectname());
        //    gdBuilding.setBuildingLicense(stProject.getSegmentid());
        //    gdBuilding.setBuildingSiteLocation(stProject.getProjectaddress());
        //
        //    GdStreet gdStreet = new GdStreet();
        //    gdStreet.setStreetName(stProject.getProjectarea());
        //    List<GdStreet> gdStreets = gdStreetService.selectGdStreetList(gdStreet);
        //    if(gdStreets.size() > 0){
        //        gdStreet = gdStreets.get(0);
        //        gdBuilding.setStreetId(gdStreet.getId());
        //    }
        //    gdBuilding.setSpace(stProject.getBuildarea());
        //    gdBuilding.setPrice(stProject.getProjectprice());
        //    gdBuilding.setCoordinate(stProject.getCoordinate());
        //    gdBuildingService.insertGdBuilding(gdBuilding);
        //}
    }

    //单个工地信息
    @GetMapping("/buildingId/{id}")
    public GdBuilding getBuildingId(@PathVariable("id") Long id){
        GdBuilding gdBuilding = new GdBuilding();
        gdBuilding = gdBuildingService.selectGdBuildingById(id);
        return gdBuilding;
    }
    //单个工地页面基础数据
    @GetMapping("/buildingBase/{id}")
    public Object getBuildingBase(@PathVariable("id") Long id){
        GdBuilding gdBuilding = new GdBuilding();
        gdBuilding = gdBuildingService.selectGdBuildingById(id);
        VPsWorkerbase vPsWorkerbase = new VPsWorkerbase();
        vPsWorkerbase.setProjectguid(gdBuilding.getProjectInfoNum());
        JSONObject jsonObject = new JSONObject();
        List<GdBuilding> list = gdBuildingService.selectGdBuildingList(gdBuilding);
        List<VPsWorkerbase> list2 = vPsWorkerbaseService.selectVPsWorkerbaseList(vPsWorkerbase);
        Double price = 0.00;
        for(int i = 0; i < list.size(); i++){
            price = price + list.get(i).getPrice();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        price = Double.valueOf(df.format(price));
        jsonObject.put("totalPrice",price);
        jsonObject.put("people",list2.size());
        jsonObject.put("buildingSiteDuty",0);
        return jsonObject;
    }
    //单个工地页面整改通知
    @GetMapping("/buildingSynergy/{id}")
    public List<?> getBuildingSynergy(@PathVariable("id") Long id){
        GdBuilding gdBuilding = gdBuildingService.selectGdBuildingById(id);
        DtRectificationnotice dtRectificationnotice = new DtRectificationnotice();
        dtRectificationnotice.setProjectguid(gdBuilding.getProjectInfoNum());
        List<DtRectificationnotice> list = dtRectificationnoticeMapper.selectList(dtRectificationnotice);
        return list;
    }
    //单个工地页面预警信息
    @GetMapping("/buildingKeyPerson/{id}")
    public List<?> getBuildingKeyPerson(@PathVariable("id") Long id){
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
    public JSONArray getBuildingDustData(@PathVariable("id") Long id) throws Exception{
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
        calendar.add(Calendar.DAY_OF_MONTH,-8);
        for(int i = 0; i < 7; i++){
            calendar.add(Calendar.DAY_OF_MONTH,+1);
            date = calendar.getTime();
            jsonArray.add(averageDust(calendar,list));
        }
        //map = listData(map,"dustData", gdDustData1);
        return jsonArray;
    }
    //单个工地页面扬尘设备数量
    @GetMapping("/buildingDustEquipment/{id}")
    public Integer getBuildingDustEquipment(@PathVariable("id") Long id){
        GdDustEquipment gdDustEquipment = new GdDustEquipment();
        gdDustEquipment.setBuildingId(id);
        List<GdDustEquipment> gdDustEquipments = gdDustEquipmentService.selectGdDustEquipmentList(gdDustEquipment);
        return gdDustEquipments.size();
    }
    //单个工地视频设备
    @GetMapping("/buildingVideo/{id}")
    public JSONArray getBuildingVideo(@PathVariable("id") Long id){
        GdBuilding gdBuilding = gdBuildingService.selectGdBuildingById(id);
        String guid = gdBuilding.getProjectInfoNum();
        //分页查询个人有权限视频通道资源
        String url = "resource-catalog/tripartite/authority/channel/page";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNo",1);
        jsonObject.put("pageSize",200);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("keyword","");
        jsonObject.put("param",jsonObject1);
        String body = VideoLive.getVideoData(jsonObject,url,"jsjtj001","jsjtj12345");
        //System.out.println(body);
        JSONObject videoList = JSONObject.parseObject(body);
        String records = videoList.getString("records");
        JSONArray jsonArray = JSONArray.parseArray(records);
        JSONArray jsonArrayNew = new JSONArray();
        //遍历出拥有工地编号的视频
        for(int i = 0; i < jsonArray.size();i++){
            JSONObject gdRecord = jsonArray.getJSONObject(i);
            if(gdRecord.containsKey("installAddr")){
                if(guid.equals(gdRecord.getString("installAddr"))){
                    jsonArrayNew.add(gdRecord);
                }
            }
        }
        return jsonArrayNew;
    }

    //总页面考勤缺失人员
    @PostMapping("/checkLose")
    public TableDataInfo getCheckLose(@RequestBody JSONObject jsonObject)throws Exception{
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
    public TableDataInfo getBuildingCheckLose(@RequestBody JSONObject jsonObject)throws Exception{
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
    public TableDataInfo getNoRegister(@RequestBody JSONObject jsonObject)throws Exception{
        //GdWorker gdWorker = new GdWorker();
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        //gdWorker.setPeopleType(1);
        //List<GdWorker> list = gdWorkerService.selectGdWorkerList(gdWorker);
        List<VPsWorkerbase> list = vPsWorkerbaseService.selectVPsWorkerbaseList(new VPsWorkerbase());

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(PageByList.starPage(list,pageNum,pageSize));
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    //分工地页面未登记小程序人员
    @PostMapping("/buildingNoRegister")
    public TableDataInfo getBuildingNoRegister(@RequestBody JSONObject jsonObject){
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
        rspData.setRows(PageByList.starPage(list,pageNum,pageSize));
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    @Autowired
    private IGdWorkerCheckService gdWorkerCheckService;

    //总工地页面考勤未测温人员
    @PostMapping("/noTemperature")
    public TableDataInfo getNoTemperature(@RequestBody JSONObject jsonObject) throws Exception{
        GdWorkerCheck gdWorkerCheck = new GdWorkerCheck();
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        gdWorkerCheck.setTemperatureType(1);
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
        List<TPersoninfo> list = tPersoninfoMapper.selectList(new TPersoninfo());
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(PageByList.starPage(list,pageNum,pageSize));
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    //分工地页面考勤未测温人员
    @PostMapping("/buildingNoTemperature")
    public TableDataInfo getBuildingNoTemperature(@RequestBody JSONObject jsonObject)throws Exception{
        GdWorkerCheck gdWorkerCheck = new GdWorkerCheck();
        Long id = Long.valueOf(jsonObject.get("id").toString());
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        gdWorkerCheck.setBuildingId(id);
        gdWorkerCheck.setTemperatureType(1);
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

        GdBuilding gdBuilding = gdBuildingService.selectGdBuildingById(id);
        TPersoninfo tPersoninfo = new TPersoninfo();
        tPersoninfo.setProjectguid(gdBuilding.getProjectInfoNum());
        List<TPersoninfo> list = tPersoninfoMapper.selectList(tPersoninfo);

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(PageByList.starPage(list,pageNum,pageSize));
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    //总工地页面考勤预警数据
    @PostMapping("/checkWarn")
    public HashMap getCheckWarn() throws Exception{
        DecimalFormat df = new DecimalFormat("#.##");
        HashMap map = new HashMap();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
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
        calendar2.add(Calendar.DAY_OF_MONTH,-8);
        date2 = calendar2.getTime();
        //for(int i = 0;i < gdCheckLoses.size(); i++){
        //    if(gdCheckLoses.get(i).getCheckTime().before(date2)){
        //        gdCheckLoses.remove(i);
        //        i--;
        //    }
        //}

        List<TPersoninfo> tPersoninfolist = tPersoninfoMapper.selectTPersoninfoListSize(new TPersoninfo());

        Double checkLosePercentage = Double.valueOf(gdWorkerSize - tPersoninfolist.size()) / (Double.valueOf(gdWorkerSize));
        map.put("checkLose",gdWorkerSize - tPersoninfolist.size());
        map.put("checkLosePercentage",df.format(checkLosePercentage));

        gdWorker.setPeopleType(1);

        //List<GdWorker> gdWorkers2 = gdWorkerService.selectGdWorkerList(gdWorker);

        //Double noRegisterPercentage = Double.valueOf(gdWorkers2.size()) / Double.valueOf(gdWorkerSize);
        map.put("noRegister",vPsWorkerbases.size());
        //map.put("noRegisterPercentage",df.format(noRegisterPercentage));
        map.put("noRegisterPercentage","1");

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
        map.put("workerCheck",tPersoninfolist.size());
        map.put("workerCheckPercentage",df.format(workerCheckPercentage));
        System.out.println(map);
        return map;
    }

    //分工地页面考勤预警数据
    @GetMapping("/buildingCheckWarn/{id}")
    public HashMap buildingCheckWarn(@PathVariable("id") Long id) throws Exception{
        DecimalFormat df = new DecimalFormat("#.##");
        HashMap map = new HashMap();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
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
        calendar2.add(Calendar.DAY_OF_MONTH,-8);
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
        map.put("checkLose",gdWorkerSize - tPersoninfolist.size());
        if(checkLosePercentage.isNaN()){
            map.put("checkLosePercentage","0");
        }else{
            map.put("checkLosePercentage",df.format(checkLosePercentage));
        }


        //gdWorker.setPeopleType(1);
        //List<GdWorker> gdWorkers2 = gdWorkerService.selectGdWorkerList(gdWorker);
        //Double noRegisterPercentage = Double.valueOf(gdWorkers2.size()) / Double.valueOf(gdWorkerSize);
        map.put("noRegister",vPsWorkerbases.size());
        //map.put("noRegisterPercentage",noRegisterPercentage);
        map.put("noRegisterPercentage","1");


        //gdWorkerCheck.setCheckTime(date);
        gdWorkerCheck.setTemperatureType(1);
        List<GdWorkerCheck> gdWorkerChecks =gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);

        //for(int i = 0;i < gdWorkerChecks.size(); i++){
        //    if(gdWorkerChecks.get(i).getCheckTime().before(date2)){
        //        gdWorkerChecks.remove(i);
        //        i--;
        //    }
        //}

        Double workerCheckPercentage = Double.valueOf(tPersoninfolist.size()) / (Double.valueOf(gdWorkerSize));
        map.put("workerCheck",tPersoninfolist.size());
        if(workerCheckPercentage.isNaN()){
            map.put("workerCheckPercentage","0");
        }else {
            map.put("workerCheckPercentage",df.format(workerCheckPercentage));
        }

        System.out.println(map);
        return map;
    }

    //总工地页面实名认证
    @PostMapping("/checkRegister")
    public HashMap getCheckRegister() throws Exception{
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
        calendar.add(Calendar.DAY_OF_MONTH,-8);
        for(int i = 0; i < 7; i++){
            calendar.add(Calendar.DAY_OF_MONTH,+1);
            date = calendar.getTime();
            gdWorkerCheck.setCheckTime(date);
            gdWorkerCheck.setPeopleType(0);
            List<GdWorkerCheck> gdWorkerChecks = gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);
            HashMap map = new HashMap();
            map.put("date",date);
            map.put("num",gdWorkerChecks.size());
            checkRegister.add(map);
            gdWorkerCheckAll.setCheckTime(date);
            List<GdWorkerCheck> gdWorkerChecksAll = gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheckAll);
            HashMap mapAll = new HashMap();
            mapAll.put("date",date);
            mapAll.put("num",gdWorkerChecksAll.size());
            allWorkerCheck.add(mapAll);
        }
        reMap.put("register",checkRegister);
        reMap.put("all",allWorkerCheck);
        return reMap;
    }

    //分工地页面实名认证
    @GetMapping("/buildingCheckRegister/{id}")
    public HashMap buildingCheckRegister(@PathVariable("id") Long id) throws Exception{
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
        calendar.add(Calendar.DAY_OF_MONTH,-8);
        for(int i = 0; i < 7; i++){
            calendar.add(Calendar.DAY_OF_MONTH,+1);
            date = calendar.getTime();
            gdWorkerCheck.setCheckTime(date);
            gdWorkerCheck.setPeopleType(0);
            List<GdWorkerCheck> gdWorkerChecks = gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);
            HashMap map = new HashMap();
            map.put("date",date);
            map.put("num",gdWorkerChecks.size());
            checkRegister.add(map);
            gdWorkerCheckAll.setCheckTime(date);
            List<GdWorkerCheck> gdWorkerChecksAll = gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheckAll);
            HashMap mapAll = new HashMap();
            mapAll.put("date",date);
            mapAll.put("num",gdWorkerChecksAll.size());
            allWorkerCheck.add(mapAll);
        }
        reMap.put("register",checkRegister);
        reMap.put("all",allWorkerCheck);

        return reMap;
    }

    //单个工地视频列表总数
    @GetMapping("/buildingVideoBase/{id}")
    public List<?> buildingVideoBase(@PathVariable("id") Long id){
        GdVideoBase gdVideoBase = new GdVideoBase();
        gdVideoBase.setId(id);
        return gdVideoBaseService.selectGdVideoBaseList(gdVideoBase);
    }

    //视频设备实时直播地址
    @GetMapping("/videoLive/{channelCode}")
    public JSONObject videoLive(@PathVariable("channelCode") String channelCode)throws Exception{
        String body = VideoLive.getLive(channelCode,"jsjtj001","jsjtj12345");
        JSONObject videoUrl = JSONObject.parseObject(body);
        return videoUrl;
    }

    //视频设备名称搜索
    @GetMapping("/videoNameSerch/{videoName}")
    public JSONArray videoNameSerch(@PathVariable("videoName") String videoName)throws Exception{
        String url = "resource-catalog/tripartite/authority/channel/page";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNo",1);
        jsonObject.put("pageSize",200);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("keyword",videoName);
        jsonObject.put("param",jsonObject1);
        System.out.println(jsonObject);
        String body = VideoLive.getVideoData(jsonObject,url,"jsjtj001","jsjtj12345");
        //String body = VideoLive.getVideoData(jsonObject,url,"cnjd001","cnjd12345");
        //System.out.println(body);
        JSONObject videoList = JSONObject.parseObject(body);
        String records = videoList.getString("records");
        JSONArray jsonArray = JSONArray.parseArray(records);
        //遍历出拥有工地编号的视频
        for(int i = 0; i < jsonArray.size();i++){
            JSONObject gdRecord = jsonArray.getJSONObject(i);
            if(!gdRecord.containsKey("installAddr")){
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
    public JSONArray healthCode()throws Exception{
        List<VPsWorkerbase> vPsWorkerbases = vPsWorkerbaseService.selectVPsWorkerbaseList(new VPsWorkerbase());
        JSONArray jsonArray = new JSONArray();
        System.out.println(new Date());
        for(int i = 0; i < vPsWorkerbases.size(); i++){
            JSONObject jsonObject = new JSONObject();
            VPsWorkerbase vPsWorkerbase = vPsWorkerbases.get(i);
            jsonObject.put("idNumber",vPsWorkerbase.getIdcard());
            JSONObject warn = healthApi(jsonObject);
            if(!(warn == null)){
                jsonArray.add(warn);
            }
        }
        System.out.println(new Date());
        return jsonArray;
    }














    //调用健康码接口方法
    public JSONObject healthApi(JSONObject jsonObject)throws Exception{
        String url = "http://133.1.254.22:18093/provinceImportant/findNewHealthCodeResultByIdNumber";
        JSONObject data = HttpClientUtils.doPost(url,jsonObject);
        System.out.println(data);
        JSONObject jsonObject1 = data.getJSONObject("data");
        JSONObject jsonObject2 = jsonObject1.getJSONObject("datas");
        JSONArray jsonArray = jsonObject2.getJSONArray("data");
        for(int i = 0; i < jsonArray.size(); i++){
            JSONObject ifwarn = jsonArray.getJSONObject(i);
            if(ifwarn.getString("mzt").equals("黄码") && ifwarn.getString("mffd").equals("嘉兴市")){
                System.out.println(ifwarn);
                return ifwarn;
            }
            if(ifwarn.getString("mzt").equals("红码") && ifwarn.getString("mffd").equals("嘉兴市") ){
                System.out.println(ifwarn);
                return ifwarn;
            }
        }
        return null;
    }

    //
    //列表数据处理
    public HashMap listData(HashMap map,String name,List<?> objects){
        if(objects.size() > 0){
            map.put(name,objects);
        }else {
            map.put(name,"null");
        }
        return map;
    }
    //单个数据处理
    public HashMap singleData(HashMap map,String name,Object object,List<?> objects){
        if(objects.size() > 0){
            object = objects.get(0);
            //JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(object,SerializerFeature.WriteMapNullValue));
            //map.put("name",jsonObject);
            map.put("name",object);
        }else {
            map.put("name","null");
        }
        return map;
    }
    //获取当前天的扬尘设备的平均值
    public HashMap averageDust(Calendar calendar,List<GdDustData> list){
        HashMap map = new HashMap();

        Double pm25T = 0.00;
        Long tip25T =0L;
        Long tip10T = 0L;
        BigDecimal tip = new BigDecimal(1);
        for(int i = 0; i < list.size(); i++){
            Date date1 = list.get(i).getDate();
            Calendar call = Calendar.getInstance();
            call.setTime(date1);
            call.set(Calendar.HOUR_OF_DAY, 0);
            call.set(Calendar.MINUTE, 0);
            call.set(Calendar.SECOND, 0);
            call.set(Calendar.MILLISECOND, 0);
            if(calendar.equals(call)){
                tip25T++;
                //BigDecimal listPm25 = new BigDecimal(list.get(i).getPm25());
                pm25T = pm25T+list.get(i).getPm25() ;
            }
        }
        BigDecimal pm25 = new BigDecimal(pm25T);
        BigDecimal tip25 = new BigDecimal(tip25T);
        if(tip25T.equals(0L)){
            map.put("pm25",0);
        }else {
            System.out.println(pm25);
            System.out.println(tip25T);
            System.out.println(pm25.divide(tip25,2,BigDecimal.ROUND_HALF_UP));
            map.put("pm25",pm25.divide(tip25,2,BigDecimal.ROUND_HALF_UP));
        }

        Double pm10T = 0.00;
        for(int i = 0; i < list.size(); i++){
            Date date1 = list.get(i).getDate();
            Calendar call = Calendar.getInstance();
            call.setTime(date1);
            call.set(Calendar.HOUR_OF_DAY, 0);
            call.set(Calendar.MINUTE, 0);
            call.set(Calendar.SECOND, 0);
            call.set(Calendar.MILLISECOND, 0);
            if(calendar.equals(call)){
                tip10T++;
                pm10T = pm10T+ list.get(i).getPm10();
            }
        }
        BigDecimal pm10 = new BigDecimal(pm10T);
        BigDecimal tip10 = new BigDecimal(tip10T);
        if(tip10T.equals(0L)){
            map.put("pm10",0);
        }else {
            map.put("pm10",pm10.divide(tip10,2,BigDecimal.ROUND_HALF_UP));
        }
        map.put("date",calendar.getTime());
        return map;
    }

    //返回7天内数据
    public List<?> sevenData(List<?> list,Date dateCheckTime,Integer i) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-8);
        date = calendar.getTime();
        if(dateCheckTime.before(date)){
                list.remove(i);
        }
        return list;
    }
}























