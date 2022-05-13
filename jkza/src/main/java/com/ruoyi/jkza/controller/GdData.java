package com.ruoyi.jkza.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.jkza.domain.*;
import com.ruoyi.jkza.mapper.GdBuildingMapper;
import com.ruoyi.jkza.mapper.TAttAttendancemonthstatMapper;
import com.ruoyi.jkza.service.*;
import com.ruoyi.jkza.util.VideoLive;
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
    //基础数据处理
    @PostMapping("/base")
    public Object getBase(){
        //HashMap map = new HashMap();
        GdBase gdBase = new GdBase();
        List<GdBase> gdBaseslist = gdBaseService.selectGdBaseList(gdBase);
        //map = singleData(map,"base",gdBase,gdBaseslist);
        gdBase = gdBaseslist.get(0);
        return gdBase;
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
        System.out.println(jsonObject);
        return jsonObject;

    }

    //单个工地页面考勤
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
        jsonObject.put("managerPer",(managerPer.divide(manager,4,BigDecimal.ROUND_HALF_UP)));
        jsonObject.put("register",register);
        jsonObject.put("registerPer",registerPer.divide(register,4,BigDecimal.ROUND_HALF_UP));
        jsonObject.put("safety",safety);
        jsonObject.put("safetyPer",safetyPer.divide(safety,4,BigDecimal.ROUND_HALF_UP));
        jsonObject.put("professinoal",professinoal);
        jsonObject.put("professinoalPer",professinoalPer.divide(professinoal,4,BigDecimal.ROUND_HALF_UP));
        System.out.println(jsonObject);
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
    //整改通知
    @PostMapping("/synergy")
    public List<?> getSynergy(){
        //HashMap map = new HashMap();
        GdSynergy gdSynergy =new GdSynergy();
        List<GdSynergy> gdSynergies = gdSynergyService.selectGdSynergyList(gdSynergy);
        //map = listData(map,"synergy", gdSynergies);
        return gdSynergies;
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
    //扬尘设备数据
    @PostMapping("/dustData")
    public JSONArray getDustData() throws Exception{
        HashMap map = new HashMap();
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        GdDustData gdDustData = new GdDustData();
        List<GdDustData> gdDustData1 = gdDustDataService.selectGdDustDataList(gdDustData);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-8);
        for(int i = 0; i < 7; i++){
            //averageDust(date,gdDustData1);
            calendar.add(Calendar.DAY_OF_MONTH,+1);
            date = calendar.getTime();
            jsonArray.add(averageDust(date,gdDustData1));

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

    @PostMapping("/test")
    public void test(){

        //处理管理人员考勤数据
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
        //
        //    System.out.println(list.get(i).getPostName());
        //    BigDecimal a = new BigDecimal(list.get(i).getDegree());
        //    BigDecimal checkPer = a.divide(total,4,BigDecimal.ROUND_HALF_UP);
        //    System.out.println(list.get(i).getDegree());
        //    System.out.println(checkPer);
        }
        jsonObject.put("项目负责人",manager);
        jsonObject.put("项目负责人百分比",(managerPer.divide(manager,4,BigDecimal.ROUND_HALF_UP)));
        jsonObject.put("项目总监理工程师",register);
        jsonObject.put("项目总监理工程师百分比",registerPer.divide(register,4,BigDecimal.ROUND_HALF_UP));
        jsonObject.put("安全员",safety);
        jsonObject.put("安全员百分比",safetyPer.divide(safety,4,BigDecimal.ROUND_HALF_UP));
        jsonObject.put("专业监理工程师",professinoal);
        jsonObject.put("专业监理工程师百分比",professinoalPer.divide(professinoal,4,BigDecimal.ROUND_HALF_UP));




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
    public HashMap getBuildingBase(@PathVariable("id") Long id){
        GdBuilding gdBuilding = new GdBuilding();
        gdBuilding = gdBuildingService.selectGdBuildingById(id);
        HashMap map = new HashMap();
        map.put("totalPrice",gdBuilding.getPrice());
        map.put("people",gdBuilding.getWorkerNum());
        GdWorker gdWorker = new GdWorker();
        List<GdWorker> gdWorkers = gdWorkerService.selectGdWorkerList(gdWorker);
        int dutyPeople = 0;
        for(int i = 0; i < gdWorkers.size(); i++){
            if(gdWorkers.get(i).getBuildingId() == id && gdWorkers.get(i).getType() == 0){
                dutyPeople++;
            }
        }
        map.put("dutyPeople",dutyPeople);
        return map;
    }
    //单个工地页面整改通知
    @GetMapping("/buildingSynergy/{id}")
    public List<?> getBuildingSynergy(@PathVariable("id") Long id){
        //HashMap map = new HashMap();
        GdSynergy gdSynergy =new GdSynergy();
        gdSynergy.setBuildingId(id);
        List<GdSynergy> gdSynergies = gdSynergyService.selectGdSynergyList(gdSynergy);
        //map = listData(map,"synergy", gdSynergies);
        return gdSynergies;
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
    //单个工地页面扬尘设备
    @GetMapping("/buildingDustData/{id}")
    public JSONArray getBuildingDustData(@PathVariable("id") Long id) throws Exception{
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        GdDustData gdDustData = new GdDustData();
        gdDustData.setBuildingId(id);
        List<GdDustData> gdDustData1 = gdDustDataService.selectGdDustDataList(gdDustData);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-8);
        for(int i = 0; i < 7; i++){
            calendar.add(Calendar.DAY_OF_MONTH,+1);
            date = calendar.getTime();
            jsonArray.add(averageDust(date,gdDustData1));
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
        GdCheckLose gdCheckLose = new GdCheckLose();
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        List<GdCheckLose> list = gdCheckLoseService.selectGdCheckLoseList(gdCheckLose);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-8);
        date = calendar.getTime();
        for(int i = 0;i < list.size(); i++){
            if(list.get(i).getCheckTime().before(date)){
                list.remove(i);
                i--;
            }
        }
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(PageByList.starPage(list,pageNum,pageSize));
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    //分工地页面考勤缺失人员
    @PostMapping("/buildingCheckLose")
    public TableDataInfo getBuildingCheckLose(@RequestBody JSONObject jsonObject)throws Exception{
        GdCheckLose gdCheckLose = new GdCheckLose();
        Long id = Long.valueOf(jsonObject.get("id").toString());
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        gdCheckLose.setId(id);
        List<GdCheckLose> list = gdCheckLoseService.selectGdCheckLoseList(gdCheckLose);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-8);
        date = calendar.getTime();
        for(int i = 0;i < list.size(); i++){
            if(list.get(i).getCheckTime().before(date)){
                list.remove(i);
                i--;
            }
        }

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(PageByList.starPage(list,pageNum,pageSize));
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    //总工地页面未登记小程序人员
    @PostMapping("/noRegister")
    public TableDataInfo getNoRegister(@RequestBody JSONObject jsonObject)throws Exception{
        GdWorker gdWorker = new GdWorker();
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        gdWorker.setPeopleType(1);
        List<GdWorker> list = gdWorkerService.selectGdWorkerList(gdWorker);

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
        GdWorker gdWorker = new GdWorker();
        Long id = Long.valueOf(jsonObject.get("id").toString());
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        gdWorker.setBuildingId(id);
        gdWorker.setPeopleType(1);
        List<GdWorker> list = gdWorkerService.selectGdWorkerList(gdWorker);
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
        List<GdWorkerCheck> list =gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-8);
        date = calendar.getTime();
        for(int i = 0;i < list.size(); i++){
            if(list.get(i).getCheckTime().before(date)){
                list.remove(i);
                i--;
            }
        }

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
        List<GdWorkerCheck> list =gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-8);
        date = calendar.getTime();
        for(int i = 0;i < list.size(); i++){
            if(list.get(i).getCheckTime().before(date)){
                list.remove(i);
                i--;
            }
        }

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
        DecimalFormat df = new DecimalFormat("#0.00");
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
        List<GdWorker> gdWorkers = gdWorkerService.selectGdWorkerList(gdWorker);
        Integer gdWorkerSize = gdWorkers.size();

        List<GdCheckLose> gdCheckLoses = gdCheckLoseService.selectGdCheckLoseList(gdCheckLose);

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = null;
        date = simpleDateFormat2.parse(simpleDateFormat.format(new Date()));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.add(Calendar.DAY_OF_MONTH,-8);
        date2 = calendar2.getTime();
        for(int i = 0;i < gdCheckLoses.size(); i++){
            if(gdCheckLoses.get(i).getCheckTime().before(date2)){
                gdCheckLoses.remove(i);
                i--;
            }
        }

        Double checkLosePercentage = Double.valueOf(gdCheckLoses.size()) / (Double.valueOf(gdWorkerSize)*7);
        map.put("checkLose",gdCheckLoses.size());
        map.put("checkLosePercentage",df.format(checkLosePercentage));

        gdWorker.setPeopleType(1);

        List<GdWorker> gdWorkers2 = gdWorkerService.selectGdWorkerList(gdWorker);
        Double noRegisterPercentage = Double.valueOf(gdWorkers2.size()) / Double.valueOf(gdWorkerSize);
        map.put("noRegister",gdWorkers2.size());
        map.put("noRegisterPercentage",df.format(noRegisterPercentage));

        //gdWorkerCheck.setCheckTime(date);
        gdWorkerCheck.setTemperatureType(1);
        List<GdWorkerCheck> gdWorkerChecks =gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);

        for(int i = 0;i < gdWorkerChecks.size(); i++){
            if(gdWorkerChecks.get(i).getCheckTime().before(date2)){
                gdWorkerChecks.remove(i);
                i--;
            }
        }

        Double workerCheckPercentage = Double.valueOf(gdWorkerChecks.size()) / (Double.valueOf(gdWorkerSize)*7);
        map.put("workerCheck",gdWorkerChecks.size());
        map.put("workerCheckPercentage",df.format(workerCheckPercentage));
        System.out.println(map);
        return map;
    }

    //分工地页面考勤预警数据
    @GetMapping("/buildingCheckWarn/{id}")
    public HashMap buildingCheckWarn(@PathVariable("id") Long id) throws Exception{
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
        //gdCheckLose.setCheckTime(date);
        List<GdWorker> gdWorkers = gdWorkerService.selectGdWorkerList(gdWorker);
        Integer gdWorkerSize = gdWorkers.size();

        List<GdCheckLose> gdCheckLoses = gdCheckLoseService.selectGdCheckLoseList(gdCheckLose);

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = null;
        date = simpleDateFormat2.parse(simpleDateFormat.format(new Date()));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.add(Calendar.DAY_OF_MONTH,-8);
        date2 = calendar2.getTime();
        for(int i = 0;i < gdCheckLoses.size(); i++){
            if(gdCheckLoses.get(i).getCheckTime().before(date2)){
                gdCheckLoses.remove(i);
                i--;
            }
        }

        Double checkLosePercentage = Double.valueOf(gdCheckLoses.size()) / (Double.valueOf(gdWorkerSize)*7);
        map.put("checkLose",gdCheckLoses.size());
        map.put("checkLosePercentage",checkLosePercentage);

        gdWorker.setPeopleType(1);
        List<GdWorker> gdWorkers2 = gdWorkerService.selectGdWorkerList(gdWorker);
        Double noRegisterPercentage = Double.valueOf(gdWorkers2.size()) / Double.valueOf(gdWorkerSize);
        map.put("noRegister",gdWorkers2.size());
        map.put("noRegisterPercentage",noRegisterPercentage);

        //gdWorkerCheck.setCheckTime(date);
        gdWorkerCheck.setTemperatureType(1);
        List<GdWorkerCheck> gdWorkerChecks =gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);

        for(int i = 0;i < gdWorkerChecks.size(); i++){
            if(gdWorkerChecks.get(i).getCheckTime().before(date2)){
                gdWorkerChecks.remove(i);
                i--;
            }
        }

        Double workerCheckPercentage = Double.valueOf(gdWorkerChecks.size()) / (Double.valueOf(gdWorkerSize)*7);
        map.put("workerCheck",gdWorkerChecks.size());
        map.put("workerCheckPercentage",workerCheckPercentage);
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
    public HashMap averageDust(Date date,List<GdDustData> gdDustData1){
        HashMap map = new HashMap();
        Double pm25 = 0.0;
        int tip25 = 0;
        for(int i = 0; i < gdDustData1.size(); i++){
            if(date.equals(gdDustData1.get(i).getDate())){
                tip25++;
                pm25 = pm25 + gdDustData1.get(i).getPm25();
            }
        }
        pm25 = pm25/tip25;
        map.put("pm25",pm25);
        Double pm10 = 0.0;
        int tip10 = 0;
        for(int i = 0; i < gdDustData1.size(); i++){
            if(date.equals(gdDustData1.get(i).getDate())){
                tip10++;
                pm10 = pm10 + gdDustData1.get(i).getPm10();
            }
        }
        pm10 = pm10/tip10;
        map.put("pm10",pm10);
        map.put("date",date);
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























