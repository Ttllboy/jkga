package com.ruoyi.jkza.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dahuatech.hutool.http.Method;
import com.dahuatech.icc.oauth.http.DefaultClient;
import com.dahuatech.icc.oauth.http.IClient;
import com.dahuatech.icc.oauth.http.IccHttpHttpRequest;
import com.dahuatech.icc.oauth.model.v202010.GeneralRequest;
import com.dahuatech.icc.oauth.model.v202010.GeneralResponse;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.jkza.config.WebSocket;
import com.ruoyi.jkza.domain.*;
import com.ruoyi.jkza.mapper.*;
import com.ruoyi.jkza.service.*;
import com.ruoyi.jkza.util.AsyncUtil;
import com.ruoyi.jkza.util.StreetMap;
import com.ruoyi.jkza.util.VideoLive;
import com.ruoyi.jkza.util.yjsjUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.zip.GZIPInputStream;

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
    public List<?> getlowPointList() {
        YjLowPoint yjLowPoint = new YjLowPoint();
        List<YjLowPoint> yjLowPoints = yjLowPointService.selectYjLowPointList(yjLowPoint);
        return yjLowPoints;
    }

    //监测设备基础数据
    @PostMapping("/monitorEquipment")
    public Object getMonitorEquipment() {
        YjMonitorEquipment yjMonitorEquipment = new YjMonitorEquipment();
        List<YjMonitorEquipment> yjMonitorEquipments = yjMonitorEquipmentService.selectYjMonitorEquipmentList(yjMonitorEquipment);
        return yjMonitorEquipments.get(0);
    }

    //多跨协同
    @PostMapping("/synergy")
    public List<?> getSynergy(@RequestBody JSONObject jsonObject) {
        Integer eventType = jsonObject.getInteger("eventType");
        YjSynergy yjSynergy = new YjSynergy();
        yjSynergy.setEventType(eventType);
        List<YjSynergy> yjSynergies = yjSynergyService.selectYjSynergyList(yjSynergy);
        return yjSynergies;
    }

    //预警信息
    @PostMapping("/warn")
    public List<?> getWarn() {
        YjWarn yjWarn = new YjWarn();
        List<YjWarn> yjWarnList = yjWarnService.selectYjWarnList(yjWarn);
        return yjWarnList;
    }

    //避灾场所列表信息
    @PostMapping("/avoidDisasterList")
    public JSONObject getAvoidDisasterList() {
        JSONObject jsonObject = new JSONObject();
        YjAvoidDisaster yjAvoidDisaster = new YjAvoidDisaster();
        List<YjAvoidDisaster> yjAvoidDisasters = yjAvoidDisasterService.selectYjAvoidDisasterList(yjAvoidDisaster);
        jsonObject.put("count", yjAvoidDisasters.size());
        jsonObject.put("list", yjAvoidDisasters);
        return jsonObject;
    }

    @PostMapping("/openAvoidDisasterList")
    public JSONObject getOpenAvoidDisasterList() {
        JSONObject jsonObject = new JSONObject();
        YjAvoidDisaster yjAvoidDisaster = new YjAvoidDisaster();
        yjAvoidDisaster.setPlaceType(0);
        List<YjAvoidDisaster> yjAvoidDisasters = yjAvoidDisasterService.selectYjAvoidDisasterList(yjAvoidDisaster);
        jsonObject.put("count", yjAvoidDisasters.size());
        jsonObject.put("list", yjAvoidDisasters);
        return jsonObject;
    }

    //单个避灾场所信息
    @GetMapping("/avoidDisaster/{id}")
    public Object getAvoidDisaster(@PathVariable("id") Long id) {
        YjAvoidDisaster yjAvoidDisaster = new YjAvoidDisaster();
        yjAvoidDisaster = yjAvoidDisasterService.selectYjAvoidDisasterById(id);
        return yjAvoidDisaster;
    }

    //物资信息
    @PostMapping("/material")
    public List<?> getMaterial(@RequestBody JSONObject jsonObject) {
        YjMaterial yjMaterial = new YjMaterial();
        if (!jsonObject.getString("wzkName").isEmpty()) {
            yjMaterial.setMaterialStoreName(jsonObject.getString("wzkName"));
        }
        if (!jsonObject.getString("name").isEmpty()) {
            yjMaterial.setEmergencyMaterialName(jsonObject.getString("name"));
        }
        if (!jsonObject.getString("gg").isEmpty()) {
            yjMaterial.setSpecification(jsonObject.getString("gg"));
        }
        List<YjMaterial> yjMaterials = yjMaterialService.selectYjMaterialList(yjMaterial);
        return yjMaterials;
    }

    @Autowired
    private IYjOperatorService yjOperatorService;
    @Autowired
    private IYjZbdrService yjZbdrService;
    @Autowired
    private IYjLevService yjLevService;

    //值班人员
    @PostMapping("/operator")
    public JSONObject operator() throws Exception {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
        System.out.println(date);
        List<YjLev> yjLevs = yjLevService.selectYjLevList(new YjLev());
        YjLev yjLev = yjLevs.get(0);
        if (yjLev.getLev() == 0) {
            YjZbdr yjZbdr = new YjZbdr();
            yjZbdr.setZbDate(date);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("lev", 0);
            jsonObject.put("zbry", yjZbdrService.selectYjZbdrList(yjZbdr));
            jsonObject.put("leader", new JSONArray());
            return jsonObject;
        } else {
            JSONObject jsonObject = new JSONObject();
            List<YjOperator> operators = yjOperatorService.selectYjOperatorList(new YjOperator());
            YjZbdr yjZbdr = new YjZbdr();
            yjZbdr.setZbDate(date);
            jsonObject.put("lev", yjLev.getLev());
            jsonObject.put("zbry", yjZbdrService.selectYjZbdrList(yjZbdr));
            jsonObject.put("leader", operators);
            return jsonObject;
        }
    }

    @Autowired
    private IYjVideoBaseService yjVideoBaseService;

    //视频列表
    @PostMapping("/videoBase")
    public Object videoBase() {
        return yjVideoBaseService.selectYjVideoBaseList(new YjVideoBase()).get(0);
    }

    @Autowired
    private IYjVideoService yjVideoService;
    @Autowired
    private YjVideoMapper yjVideoMapper;

    //视频设备
    @PostMapping("/video")
    public JSONArray video() {
        yjVideo();
        List<YjVideo> yjVideos = yjVideoService.selectYjVideoList(new YjVideo());
        List<YjVideo> list0 = new ArrayList<>();
        List<YjVideo> list1 = new ArrayList<>();
        List<YjVideo> list2 = new ArrayList<>();
        List<YjVideo> list3 = new ArrayList<>();
        JSONObject jsonObject0 = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        for (int i = 0; i < yjVideos.size(); i++) {
            if (yjVideos.get(i).getLowpointId() != null) {
                if (yjVideos.get(i).getLowpointId() == 0) {
                    list0.add(yjVideos.get(i));
                }
                if (yjVideos.get(i).getLowpointId() == 1) {
                    list1.add(yjVideos.get(i));
                }
                if (yjVideos.get(i).getLowpointId() == 2) {
                    list2.add(yjVideos.get(i));
                }
            } else {
                list3.add(yjVideos.get(i));
            }
        }
        jsonObject0.put("name", "低洼点");
        jsonObject0.put("list", list0);
        jsonObject1.put("name", "下穿涵洞");
        jsonObject1.put("list", list1);
        jsonObject2.put("name", "闸站");
        jsonObject2.put("list", list2);
//        jsonObject3.put("name","未分类");
//        jsonObject3.put("list",list3);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject0);
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
//        jsonArray.add(jsonObject3);
        return jsonArray;
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

    //更新应急video
    public void yjVideo() {
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
    private YjLowPointMapper yjLowPointMapper;
    @Autowired
    private GdBaseMapper gdBaseMapper;

    //天气预报
    @PostMapping("/tqyb")
    public String tqyb() {
        File file = new File("tqyb.txt");
        if (file.isFile() && file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer sb = new StringBuffer();
                String text = null;
                while ((text = bufferedReader.readLine()) != null) {
                    sb.append(text);
                }
//                JSONObject jsonObject = JSONObject.parseObject(sb.toString());
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //实时天气
    @PostMapping("/sstq")
    public String sstq() {
        File file = new File("sstq.txt");
        if (file.isFile() && file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer sb = new StringBuffer();
                String text = null;
                while ((text = bufferedReader.readLine()) != null) {
                    sb.append(text);
                }
//                JSONObject jsonObject = JSONObject.parseObject(sb.toString());
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String unGZip(byte[] oResult) {
        try (InputStream inputStream = new ByteArrayInputStream(oResult);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream)) {
            byte[] buf = new byte[4096];
            int len = -1;
            while ((len = gzipInputStream.read(buf, 0, buf.length)) != -1) {
                byteArrayOutputStream.write(buf, 0, len);
            }
            return new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("天气报错");
        }
        return null;
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

    @PostMapping("/ifEvent")
    public boolean ifEvent(String eventType) {
        JSONArray array = new JSONArray();
        array.add("1270120001");
        array.add("1270030001");
        array.add("1270090001");
        array.add("1270080001");
        array.add("1270020001");
        for (int i = 0; i < array.size(); i++) {
            if (eventType.equals(array.get(i))) {
                return true;
            }
        }
        return false;
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


    @PostMapping("/test")
    public void test(@RequestBody JSONObject jsonQuery) throws Exception {
        //水位预警测试
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://133.1.254.22:18093/irsService/findHkMethodResult";
//        String url1 = "http://10.22.225.42:8099/gateway/api/001003019/dataSharing/wr2jWs3hc9gambcb.htm";
//        String startData = jsonQuery.getString("startData");
//        String endData = jsonQuery.getString("endData");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("url",url1);
//        jsonObject.put("data","{\"jsonData\":{\"startTime\":\""+startData+"\",\"stcds\":\"58457,K1167\",\"additional\":\"\",\"sttp\":\"ZZ\",\"endTime\":\""+endData+"\",\"group\":\"0\"}}\n");
//        System.out.println(jsonObject);
//        JSONObject reJson = restTemplate.postForObject(url,jsonObject,JSONObject.class);
//        System.out.println(reJson);
//        JSONObject reJson2 = (reJson.getJSONObject("data")).getJSONObject("data");
//        JSONArray reJsonArray = new JSONArray();
//        if (reJson2 != null) {
//            JSONArray result = reJson2.getJSONArray("result");
//            for (int i = 0; i < result.size(); i++) {
//                JSONObject swxx = result.getJSONObject(i);
//                System.out.println(swxx);
//            }
//            return reJsonArray;
//        }else {
//            return null;
//        }
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

    @Autowired
    private YjTqyjIdMapper yjTqyjIdMapper;

    //天气预警
    @PostMapping("/tqyj")
    public JSONArray tqyj(@RequestBody JSONObject jsonQuery) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://133.1.254.22:18093/irsService/findHkMethodResult";
        String url1 = "http://10.22.225.42:8099/gateway/jkgafj/zhtqyjxh";
        String data = jsonQuery.getString("data");
        Integer type = jsonQuery.getInteger("type");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", url1);
        jsonObject.put("data", "{\"page_now\": \"0\", \"page_size\": \"100\", \"yj_sendtime_start_date\": \"" + data + "\"}");
        JSONObject reJson = restTemplate.postForObject(url, jsonObject, JSONObject.class);
        JSONObject reJson2 = (reJson.getJSONObject("data")).getJSONObject("data");
        JSONArray reJsonArray = new JSONArray();
        if (reJson2 != null) {
            JSONArray result = reJson2.getJSONArray("result");
            for (int i = 0; i < result.size(); i++) {
                JSONObject qxzh = result.getJSONObject(i);
                if (qxzh.getString("yj_addr") != null) {
                    if (qxzh.getString("yj_addr").equals("浙江省_嘉兴市_无")) {
                        String ifValue = result.getJSONObject(i).getString("yj_ch");
                        Integer tip = ifValue.indexOf("发布");
                        if (tip > -1) {
                            String yjTitle = result.getJSONObject(i).getString("yj_ch").replace("嘉兴市气象台发布", "");
                            result.getJSONObject(i).put("yj_title", yjTitle);
                            reJsonArray.add(result.getJSONObject(i));
                        }
                    }
                    continue;
                }
                if (qxzh.getString("dsc_adm_region") != null) {
                    if (qxzh.getString("dsc_adm_region").equals("嘉兴市")) {
                        String ifValue = result.getJSONObject(i).getString("yj_ch");
                        Integer tip = ifValue.indexOf("发布");
                        if (tip > -1) {
                            String yjTitle = result.getJSONObject(i).getString("yj_ch").replace("嘉兴市气象台发布", "");
                            result.getJSONObject(i).put("yj_title", yjTitle);
                            reJsonArray.add(result.getJSONObject(i));
                        }
                    }
                }
            }
            //筛选出某个类型
            JSONArray tqyjJsonArray = new JSONArray();
            if (type == 0) {
                System.out.println("雨情预警");
                for (int i = 0; i < reJsonArray.size(); i++) {
                    String tqyjType = reJsonArray.getJSONObject(i).getString("yj_ch");
                    String yjLev = findYjLev(tqyjType);
                    reJsonArray.getJSONObject(i).put("yj_lev", yjLev);
                    if (tqyjType.indexOf("暴雨") > -1) {
                        reJsonArray.getJSONObject(i).put("yj_type", "暴雨预警");
                        ifExitTqyj(reJsonArray.getJSONObject(i));
                        tqyjJsonArray.add(reJsonArray.getJSONObject(i));
                    }
                }
            }
            if (type == 1) {
                System.out.println("台风预警");
                for (int i = 0; i < reJsonArray.size(); i++) {
                    String tqyjType = reJsonArray.getJSONObject(i).getString("yj_ch");
                    String yjLev = findYjLev(tqyjType);
                    reJsonArray.getJSONObject(i).put("yj_lev", yjLev);
                    if (tqyjType.indexOf("台风") > -1) {
                        reJsonArray.getJSONObject(i).put("yj_type", "台风预警");
                        ifExitTqyj(reJsonArray.getJSONObject(i));
                        tqyjJsonArray.add(reJsonArray.getJSONObject(i));
                    }
                }
            }
            if (type == 3) {
                System.out.println("雨雪冰冻预警");
                for (int i = 0; i < reJsonArray.size(); i++) {
                    String tqyjType = reJsonArray.getJSONObject(i).getString("yj_ch");
                    String yjLev = findYjLev(tqyjType);
                    reJsonArray.getJSONObject(i).put("yj_lev", yjLev);
                    if (tqyjType.indexOf("暴雪") > -1) {
                        reJsonArray.getJSONObject(i).put("yj_type", "暴雪预警");
                        ifExitTqyj(reJsonArray.getJSONObject(i));
                        tqyjJsonArray.add(reJsonArray.getJSONObject(i));
                    } else if (tqyjType.indexOf("寒潮") > -1) {
                        reJsonArray.getJSONObject(i).put("yj_type", "寒潮预警");
                        ifExitTqyj(reJsonArray.getJSONObject(i));
                        tqyjJsonArray.add(reJsonArray.getJSONObject(i));
                    } else if (tqyjType.indexOf("冰雹") > -1) {
                        reJsonArray.getJSONObject(i).put("yj_type", "冰雹预警");
                        ifExitTqyj(reJsonArray.getJSONObject(i));
                        tqyjJsonArray.add(reJsonArray.getJSONObject(i));
                    } else if (tqyjType.indexOf("霜冻") > -1) {
                        reJsonArray.getJSONObject(i).put("yj_type", "霜冻预警");
                        ifExitTqyj(reJsonArray.getJSONObject(i));
                        tqyjJsonArray.add(reJsonArray.getJSONObject(i));
                    }
                }
            }
            return tqyjJsonArray;
        } else {
            return null;
        }
    }

    public void ifExitTqyj(JSONObject jsonObject) {

        YjTqyjId tqyjId = new YjTqyjId();
        tqyjId.setTqyjId(jsonObject.getString("yj_id"));
        List<YjTqyjId> list = yjTqyjIdMapper.selectYjTqyjIdList(tqyjId);
        if (list.size() == 0) {
            //不存在数据库，则需要插入并预警
            tqyjId.setTqyjText(jsonObject.toString());
            yjTqyjIdMapper.insertYjTqyjId(tqyjId);
            JSONObject sendToWeb = new JSONObject();
            sendToWeb.put("type", 1);
            sendToWeb.put("message", tqyjId.getTqyjId());
            webSocket.sendMessage(sendToWeb.toString());
            System.out.println("成功发送天气预警:" + sendToWeb.toString());
        } else {
            System.out.println("该数据已经存在");
        }
    }

    public String findYjLev(String str) {
        String[] yjLev = {"蓝色预警", "黄色预警", "橙色预警", "红色预警"};
        for (int i = 0; i < yjLev.length; i++) {
            if (str.indexOf(yjLev[i]) > -1) {
                return yjLev[i];
            }
        }
        return null;
    }

    @PostMapping("/getYjTqyjId")
    public Object getYjTqyjId(@RequestBody JSONObject querryId) {
        YjTqyjId tqyjId = new YjTqyjId();
        tqyjId.setTqyjId(querryId.getString("querryId"));
        List<YjTqyjId> list = yjTqyjIdMapper.selectYjTqyjIdList(tqyjId);
        if (list.size() > 0) {
            return JSONObject.parseObject(list.get(0).getTqyjText());
        } else {
            return null;
        }
    }

    @PostMapping("/eventSub")
    public String eventSub() {
        String url = "https://133.1.3.101/zhcs/apiserve/smc-ac/accounts/authorize";
        JSONObject firJson = new JSONObject();
        firJson.put("userName", "admin");
        firJson.put("clientType", "web");
        String token = yjsjUtil.getTokenDemo("admin", "Dahua@1234567");
        System.out.println(token);
        String url2 = "https://133.1.3.101/zhcs/apiserve/smc-event-push/smc-event/event/subscribe";
        HttpRequest httpRequest = null;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("subscribeId", "a");
        jsonObject.put("subscribeUrl", "http://192.168.3.14:8083/yjData/receiveEventSub");
        jsonObject.put("eventTypeCodes", "");
        jsonObject.put("orgCodes", "33045");
        jsonObject.put("platformId", "");
        String jsonparam = jsonObject.toString();
        try {
            httpRequest = HttpUtil.createPost(url2);
            HashMap<String, String> headerMap = new HashMap<>();
            headerMap.put("X-Subject-Token", token);
            httpRequest.addHeaders(headerMap);
            httpRequest.body(jsonparam);
            httpRequest.setReadTimeout(15000);
            HttpResponse execute = httpRequest.execute();
            String body = execute.body(); // log.info("返回:" + body);
            return body;
        } catch (Exception e) {
            System.out.println("调用消息通知地址异常，pushMessageUrl：" + e);
            //log.error("调用消息通知地址异常，pushMessageUrl：", e);
        }
        return null;
    }

    @PostMapping("/receiveEventSub")
    public void receiveEventSub(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
    }


    public JSONArray forEvent(Integer pageNo, String userName, String password) {
        String url = "smc-event/event/sync/event";
        JSONObject firstJson = new JSONObject();
        firstJson.put("eventTypeCode", "");
        firstJson.put("id", pageNo);
        System.out.println("调用50条每次，每次开始的ID：" + pageNo);
        firstJson.put("limit", 50);
//        firstJson.put("orgCode",2);
        firstJson.put("orgCode", 33045);
        System.out.println("每次调用的请求参数:" + firstJson);
        String body = yjsjUtil.getEvent(firstJson, url, "admin", "Dahua@1234567");
        JSONObject data = JSONObject.parseObject(body);
        JSONArray dataList = new JSONArray();
        if (data.getInteger("code") == 200) {
            dataList = data.getJSONArray("data");
        } else {
            return null;
        }
        return dataList;
    }

    /**
     * 向文件中写入内容
     *
     * @param filepath 文件路径与名称
     * @param newstr   写入的内容
     * @return
     * @throws IOException
     */
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

    //删除文件


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

    @GetMapping("/videoLive/{channelCode}")
    public JSONObject videoLive(@PathVariable("channelCode") String channelCode) throws Exception {
        String body = VideoLive.getLive(channelCode, "yjglj002", "yjglj12345");
        JSONObject videoUrl = JSONObject.parseObject(body);
        return videoUrl;
    }

    @Autowired
    private YjZbdrMapper yjZbdrMapper;

    //值班导入
    @PostMapping("/zbdr")
    @ResponseBody
    public Integer uploadFile(MultipartFile file, HttpServletResponse response) throws Exception {
        try {
            InputStream inputStream = file.getInputStream();
            //解析excel文件
            ExcelReader reader = ExcelUtil.getReader(inputStream, 0);//指定输入流和sheet
            // 读取第二行到最后一行数据
            List<List<Object>> read = reader.read(3, reader.getRowCount());
            List<YjZbdr> zbdrs = new ArrayList<>();
            for (List<Object> objects : read) {
                YjZbdr zbdr = new YjZbdr();
                if ((objects.get(0).toString()).length() > 9) {
                    zbdr.setZbDate((objects.get(0).toString()).substring(0, 10));
                } else {
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
        } catch (Exception e) {
            return 0;
        }
    }

    //物资导入
    @Autowired
    private YjMaterialMapper yjMaterialMapper;

    @PostMapping("/wzdr")
    @ResponseBody
    public Integer wzdr(MultipartFile file, HttpServletResponse response) throws Exception {
        try {
            InputStream inputStream = file.getInputStream();
            //解析excel文件
            ExcelReader reader = ExcelUtil.getReader(inputStream, 0);//指定输入流和sheet
            // 读取第二行到最后一行数据
            List<List<Object>> read = reader.read(1, reader.getRowCount());
            List<YjMaterial> materials = new ArrayList<>();
            for (List<Object> objects : read) {
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
        } catch (Exception e) {
            return 0;
        }
    }


    //名称规格list
    @PostMapping("/mcggList")
    @ResponseBody
    public JSONObject mcggList() {
        List<YjMaterial> yjMaterials = yjMaterialMapper.selectYjMaterialList(new YjMaterial());
        JSONObject jsonObject = new JSONObject();
        HashSet name = new HashSet();
        HashSet specification = new HashSet();
        for (int i = 0; i < yjMaterials.size(); i++) {
            name.add(yjMaterials.get(i).getEmergencyMaterialName());
            specification.add(yjMaterials.get(i).getSpecification());
        }
        jsonObject.put("name", name);
        jsonObject.put("gg", specification);
        return jsonObject;
    }

    @GetMapping("/dwdList")
    public void dwdList() {
        YjVideo video = new YjVideo();
        video.setLowpointId(1L);
    }

    @GetMapping("/videoNameSerch/{videoName}")
    public List<?> videoNameSerch(@PathVariable("videoName") String videoName) {
        YjVideo video = new YjVideo();
        video.setChannelName(videoName);
        List<YjVideo> list = yjVideoService.selectYjVideoList(video);
        return list;
    }

    @Autowired
    private YjLowPointWarnMapper yjLowPointWarnMapper;

    //低洼点预警
    @PostMapping("/lowPointWarn")
    public List<YjLowPointWarn> lowPointWarn() {
        YjLowPointWarn yjLowPointWarn = new YjLowPointWarn();
        List<YjLowPointWarn> yjLowPointWarns = yjLowPointWarnMapper.selectYjLowPointWarnList(yjLowPointWarn);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String curDate = df.format(date);
        System.out.println(curDate);
        List<YjLowPointWarn> list = new ArrayList<>();
        for (int i = 0; i < yjLowPointWarns.size(); i++) {
            YjLowPointWarn lowPointWarn = yjLowPointWarns.get(i);
            String warnTime = lowPointWarn.getAlarmTime().substring(0, 10);
            if (curDate.equals(warnTime)) {
                System.out.println(warnTime);
                list.add(lowPointWarn);
            }
        }
        return list;
    }

    //低洼点查看监控
    @PostMapping("/lowPointWarnLive")
    public JSONObject lowPointWarnLive(@RequestBody JSONObject jsonObject) throws Exception {
        String deviceName = "'%" + jsonObject.getString("deviceName") + "%'";
        List<HashMap> maps = gdBaseMapper.selectChannelCode(deviceName);
        if (maps.size() > 0) {
            HashMap map = null;
            for (int i = 0; i < maps.size(); i++) {
                map = maps.get(i);
                System.out.println(map);
            }
            String channelCode = (String) map.get("channel_code");
            String body = VideoLive.getLive(channelCode, "yjglj002", "yjglj12345");
            JSONObject videoUrl = JSONObject.parseObject(body);
            return videoUrl;
        }
        return null;
    }

    @Autowired
    JkgaConfig jkgaConfig;
//    @PostMapping("/tqyb")
//    public void tqyb(){
//        RestTemplate restTemplate = new RestTemplate();
//        String url = jkgaConfig.getTqyb();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("page_now",0);
//        jsonObject.put("page_size",10);
//        jsonObject.put("ybpubtime_startdate","2022-01-01");
////        System.out.println(url1);
////        String url2 = jkgaConfig.getSfzh();
////        System.out.println(url2);
////        String url3 = jkgaConfig.getTflj();
////        System.out.println(url3);
////        String url4 = jkgaConfig.getJkm();
////        System.out.println(url4);
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1 = restTemplate.postForObject(url,jsonObject,JSONObject.class);
//        JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
//        JSONObject jsonObject3 = jsonObject2.getJSONObject("data");
//        JSONArray jsonArray = jsonObject3.getJSONArray("result");
//        for (int i = 0; i < jsonArray.size(); i++) {
//            System.out.println(jsonArray.getJSONObject(i));
//        }
//    }

    @PostMapping("/sfzh/{date}")
    public JSONArray sfzh(@PathVariable String date) {
        RestTemplate restTemplate = new RestTemplate();
        String url = jkgaConfig.getSfzh();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page_now", 0);
        jsonObject.put("page_size", 10);
        jsonObject.put("start_date", date);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1 = restTemplate.postForObject(url, jsonObject, JSONObject.class);
        JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
        JSONObject jsonObject3 = jsonObject2.getJSONObject("data");
        JSONArray jsonArray = jsonObject3.getJSONArray("result");
        for (int i = 0; i < jsonArray.size(); i++) {
            System.out.println(jsonArray.getJSONObject(i));
        }
        return jsonArray;
    }

    @PostMapping("/tflj/{date}")
    public JSONArray tflj(@PathVariable String date) {
        RestTemplate restTemplate = new RestTemplate();
        String url = jkgaConfig.getTflj();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page_now", 0);
        jsonObject.put("page_size", 10);
        jsonObject.put("start_date", date);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1 = restTemplate.postForObject(url, jsonObject, JSONObject.class);
        JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
        JSONObject jsonObject3 = jsonObject2.getJSONObject("data");
        JSONArray jsonArray = jsonObject3.getJSONArray("result");
        for (int i = 0; i < jsonArray.size(); i++) {
            System.out.println(jsonArray.getJSONObject(i));
        }
        return jsonArray;
    }

    @PostMapping("/jkm")
    public JSONArray jkm() {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = jkgaConfig.getJkm();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("page_now",0);
//        jsonObject.put("page_size",10);
//        jsonObject.put("sfzh_list","('320830198210015415','362322199101203915')");
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1 = restTemplate.postForObject(url,jsonObject,JSONObject.class);
//        JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
//        JSONObject jsonObject3 = jsonObject2.getJSONObject("data");
//        JSONArray jsonArray = jsonObject3.getJSONArray("result");
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject data = new JSONObject();
//            System.out.println(jsonArray.getJSONObject(i));
//            JSONObject jsonObject4 = jsonArray.getJSONObject(i);
//            if ((jsonObject4.getString("mzt").equals("黄码")) || (jsonObject4.getString("mzt").equals("红码"))) {
//
//            }
////            JSONObject jsonObject5 = jsonObject4.getJSONObject("wbtjbdxx");
////            System.out.println(jsonObject5);
//        }
//        return jsonArray;
        return null;
    }

    @Autowired
    private YjSynergyMapper yjSynergyMapper;

    @Resource
    private WebSocket webSocket;

    @PostMapping("/eventDb")
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

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
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

    //事件实时预警
    @PostMapping("/updateEvent")
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
            System.out.println("未连接专网");
        }
    }

    //事件比对
    public void eventCompare(List<YjSynergy> list) throws Exception {
        Long st = System.nanoTime();
        List<YjSynergy> list2 = yjSynergyMapper.selectYjSynergyList(new YjSynergy());
        list2.forEach(item -> item.setId(null));
        Integer size = list.size() >= list2.size() ? list.size() : list2.size();
        Map<String, Integer> map = new HashMap<>(size);
        list2.forEach(item -> map.put(String.valueOf(item.getEventId() + item.getEventType()), 1));
        list.forEach(item -> {
            String queryIf = String.valueOf(item.getEventId() + item.getEventType());
            if (map.get(queryIf) == null) {
                if (ifExitEvent(item.getEventId())) {
                    //不存在本地数据库，则新增
                    yjSynergyMapper.insertYjSynergy(item);
                    JSONObject sendToWeb = new JSONObject();
                    sendToWeb.put("type", 0);
                    sendToWeb.put("message", item.getEventId());
                    webSocket.sendMessage(sendToWeb.toString());
                    System.out.println("成功发送事件预警:" + sendToWeb.toString());
                } else {
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
    public boolean ifExitEvent(Long eventId) {
        //是否存在在本地数据库，如果不存在则返回ture，则执行新增方法
        YjSynergy yjSynergy = new YjSynergy();
        yjSynergy.setEventId(eventId);
        List<YjSynergy> synergies = yjSynergyMapper.selectYjSynergyList(yjSynergy);
        if (synergies.size() > 0) {
            //存在
            return false;
        } else {
            //不存在
            return true;
        }
    }

    //根据事件ID查询事件
    @PostMapping("/eventIdSelect")
    public YjSynergy eventIdSelect(@RequestBody JSONObject jsonObject) {
        String eventId = jsonObject.getString("eventId");
        YjSynergy yjSynergy = new YjSynergy();
        yjSynergy.setEventId(Long.valueOf(eventId));
        List<YjSynergy> synergies = yjSynergyMapper.selectYjSynergyList(yjSynergy);
        if (synergies.size() > 0) {
            return synergies.get(0);
        }
        return null;
    }


    //    @PostMapping("/delete")
    public void deLowPointWarn() {
        List<YjLowPointWarn> list = yjLowPointWarnMapper.selectYjLowPointWarnList(new YjLowPointWarn());
        Date date2 = new Date();
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            YjLowPointWarn yjLowPointWarn = list.get(i);
            Date date1 = DateUtils.parseDate(yjLowPointWarn.getAlarmTime());
            if (yjLowPointWarn.getAlarmTime() == null) {
                ids.add(yjLowPointWarn.getId());
            }
            Integer day = DateUtils.differentDaysByMillisecond(date1, date2);
            if (day > 30) {
                ids.add(yjLowPointWarn.getId());
                System.out.println(day);
            }
            if (ids.size() > 1000) {
                Long[] idsSub = ids.toArray(new Long[list.size()]);
                yjLowPointWarnMapper.deleteYjLowPointWarnByIds(idsSub);
                ids.clear();
            }
        }
        Long[] idsSub = ids.toArray(new Long[list.size()]);
        yjLowPointWarnMapper.deleteYjLowPointWarnByIds(idsSub);
    }

    //避灾点门状态
    @PostMapping("/avoidChanelType")
    public void avoidChanelType() throws Exception {
        //++++types 1436	人证合法开门
        IClient iClient = new DefaultClient("100.100.0.207", "jgkj-lj", "abca8ed2-6a9e-4af8-8451-daeed722e04b");
        String mqinfo = "{\"param\":{\"monitors\":[{\"monitor\":\"http://133.1.4.159:8083/yjData/avoidEventSubscribe\",\"monitorType\":\"url\",\"events\":[{\"category\":\"alarm\",\"subscribeAll\":1,\"domainSubscribe\":2,\"authorities\":[{\"types\":[\"1436\"]}]}]}],\"subsystem\":{\"subsystemType\":0,\"name\":\"133.1.4.159:8083\",\"magic\":\"133.1.4.159:8083\"}}}";
        GeneralRequest generalRequest = new GeneralRequest("/evo-apigw/evo-event/1.0.0/subscribe/mqinfo", Method.POST);
        generalRequest.body(mqinfo);
        GeneralResponse generalResponse = iClient.doAction(generalRequest, generalRequest.getResponseClass());
        JSONObject jsonObject = JSONObject.parseObject(generalResponse.getResult());
        System.out.println(jsonObject);

    }

    @PostMapping("/avoidEventSubscribe")
    public void avoidEventSubscribe(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
    }

}





