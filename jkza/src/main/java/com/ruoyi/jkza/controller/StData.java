package com.ruoyi.jkza.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.jkza.config.Config2RestTemplate;
import com.ruoyi.jkza.domain.*;
import com.ruoyi.jkza.mapper.StMassifMapper;
import com.ruoyi.jkza.mapper.StVideoMapper;
import com.ruoyi.jkza.service.*;
import com.ruoyi.jkza.util.VideoLive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/stData")
public class StData {

    @Autowired
    private IStBaseService stBaseService;

    //生态页面基础数据
    @PostMapping("/base")
    public Object operator() {
        return stBaseService.selectStBaseList(new StBase()).get(0);
    }

    @Autowired
    private IStCapacityRankService stCapacityRankService;

    //生态页面固废点容量排名
    @PostMapping("/capacityRank")
    public List<?> capacityRank() {
        return stCapacityRankService.selectStCapacityRankList(new StCapacityRank());
    }

    @Autowired
    private IStIllegalTrendService stIllegalTrendService;

    //生态页面非法情况趋势图
    @PostMapping("/illegalTrend")
    public List<?> illegalTrend() {
        return stIllegalTrendService.selectStIllegalTrendList(new StIllegalTrend());
    }

    @Autowired
    private IStWarnProportionService stWarnProportionService;

    //生态页面预警信息占比
    @PostMapping("/warnProportion")
    public Object warnProportion() {
        return stWarnProportionService.selectStWarnProportionList(new StWarnProportion()).get(0);
    }

    @Autowired
    private IStIncidentProcessService stIncidentProcessService;

    //生态页面事件处理
    @PostMapping("/incidentProcess")
    public List<?> incidentProcess() {
        return stIncidentProcessService.selectStIncidentProcessList(new StIncidentProcess());
    }

    @Autowired
    private IStWarnService stWarnService;

    //生态页面非法捕捞预警信息
    @PostMapping("/warnFish")
    public List<?> warnFish() {
        StWarn stWarn = new StWarn();
        stWarn.setWarnType(0);
        return stWarnService.selectStWarnList(stWarn);
    }

    //生态页面非法倾倒预警信息
    @PostMapping("/warnDump")
    public List<?> warnDump() {
        StWarn stWarn = new StWarn();
        stWarn.setWarnType(1);
        return stWarnService.selectStWarnList(stWarn);
    }

    //生态页面重点人员预警信息
    @PostMapping("/warnPerson")
    public List<?> warnPerson() {
        StWarn stWarn = new StWarn();
        stWarn.setWarnType(2);
        return stWarnService.selectStWarnList(stWarn);
    }

    //生态页面重点车辆预警信息
    @PostMapping("/warnCar")
    public List<?> warnCar() {
        StWarn stWarn = new StWarn();
        stWarn.setWarnType(3);
        return stWarnService.selectStWarnList(stWarn);
    }

    @Autowired
    private IStSolidWasteService stSolidWasteService;

    //生态页面固废点
    @PostMapping("/solidWaste")
    public List<?> solidWaste() {
        return stSolidWasteService.selectStSolidWasteList(new StSolidWaste());
    }

    @Autowired
    private IStVideoBaseService stVideoBaseService;

    //生态页面视频列表
    @PostMapping("/videoBase")
    public Object videoBase() {
        return stVideoBaseService.selectStVideoBaseList(new StVideoBase()).get(0);
    }

    @Autowired
    private IStVideoService stVideoService;
    @Autowired
    StVideoMapper stVideoMapper;

    //生态页面视频设备
    @PostMapping("/video")
    public List<?> video() {
        return stVideoService.selectStVideoList(new StVideo());
    }
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
        String body = VideoLive.getLive(channelCode, "sthjfj002", "sthjfj12345");
        JSONObject videoUrl = JSONObject.parseObject(body);
        return videoUrl;
    }

    @GetMapping("/videoNameSerch/{videoName}")
    public List<?> videoNameSerch(@PathVariable("videoName") String videoName) {
        StVideo video = new StVideo();
        video.setChannelName(videoName);
        List<StVideo> list = stVideoService.selectStVideoList(video);
        return list;
    }

    @Autowired
    Config2RestTemplate config2RestTemplate;

    //大气环境质量
    @PostMapping("/envQuality")
    public JSONObject envQuality() throws Exception {
        JSONObject reJson = new JSONObject();
        RestTemplate client = config2RestTemplate.restTemplate();
//        String ip = "http://101.71.159.242:30004";
        String ip = "http://133.1.254.22:5100";
        //获取国控点位类型的最新AQI数据的api
        String lastValIaqiForAllPointApi = "/api/FactorData/LastValIaqiForAllPoint";
        JSONObject lastValIaqiForAllPointJson = new JSONObject();
        lastValIaqiForAllPointJson.put("level", 0);
        JSONArray jsonArray = client.postForObject(ip + lastValIaqiForAllPointApi, lastValIaqiForAllPointJson, JSONArray.class);
        String pm25 = "";
        String pm10 = "";
        String o3 = "";
        Double aqi = 0.0;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject airData = jsonArray.getJSONObject(i);
            String Facid = airData.getString("Facid");
            if (Facid.equals("ce444502-7fb7-46d5-896f-ba4ca9cca773")) {
                pm25 = airData.getString("AvgVal");
            }
            if (Facid.equals("0d2638ef-6fd9-4416-bc57-4542a18a06d4")) {
                pm10 = airData.getString("AvgVal");
                aqi = airData.getDouble("IAqi");
            }
            if (Facid.equals("86959e64-688b-47ad-a4dd-46f7985e6df5")) {
                o3 = airData.getString("AvgVal");
            }
        }
        reJson.put("pm25", pm25);
        reJson.put("pm10", pm10);
        reJson.put("o3", o3);
        reJson.put("aqi", aqi);
        //判断污染等级
        String pollute = "";
        if (aqi <= 50) {
            pollute = "优";
        }
        if (aqi > 50 && aqi <= 100) {
            pollute = "良";
        }
        if (aqi > 100 && aqi <= 150) {
            pollute = "轻度";
        }
        if (aqi > 150 && aqi <= 200) {
            pollute = "中度";
        }
        if (aqi > 200 && aqi <= 300) {
            pollute = "重度";
        }
        if (aqi > 300) {
            pollute = "严重";
        }
        reJson.put("pollute", pollute);
        //获取当年至今累计
        //获取当前年份的最开始时间
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        //获取最新时间
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = year + "-01-01 00:00";
        String eDate = df.format(day);
        String fromType = "0";
        String[] refIds = {"141CA928-F551-4AA1-B461-08313D8805E3",
                "D4863AA4-B693-4980-B2B1-16CBB8562FE3",
                "D39AFA4A-EF8A-4EFF-A113-212ACE596CEF",
                "B3C4B75D-69E1-42EC-9D49-2733A49E0E7F",
                "AAF13197-EF54-43FD-8D7E-4E92AA2D2892",
                "D9CBA1C0-D0FA-4EEC-8497-C372A30055F2",
                "A1CF19DF-C16A-4D47-88EB-DF2C5ED8FBAF",
        };
        JSONObject airRateJson = new JSONObject();
        airRateJson.put("sDate", sDate);
        airRateJson.put("eDate", eDate);
        airRateJson.put("fromType", fromType);
        airRateJson.put("refIds", refIds);
        //获取当年至今累计api
        String airRateApi = "/api/FactorData/AirRateCalculateForecastForMultiple";
        Float cumulative = null;
        while (cumulative == null) {
            try {
                cumulative = client.postForObject(ip + airRateApi, airRateJson, Float.class);
            } catch (Exception e) {
                System.out.println("获取当年至今累计接口发生网络错误");
            }
        }
        reJson.put("cumulative", cumulative);
        reJson.put("primaryPollutant", "PM10");
        reJson.put("pm25Val", 31);
        reJson.put("pm25Target", 30);
        reJson.put("excellentRateVal", 84);
        reJson.put("excellentRateTarget", 85);
        return reJson;
    }

    //水环境质量
    @PostMapping("/waterQuality")
    public JSONObject waterQuality() throws Exception {
        JSONObject reJson = new JSONObject();
        RestTemplate client = config2RestTemplate.restTemplate();
//        String ip = "http://101.71.159.242:30004";
        String ip = "http://133.1.254.22:5100";

        String lastValIaqiForAllPointApi = "/api/Section/PagedList";
        JSONObject lastValIaqiForAllPointJson = new JSONObject();
        lastValIaqiForAllPointJson.put("searchKey", "");
        lastValIaqiForAllPointJson.put("pageIndex", -1);
        JSONObject waterListJson = null;
        while (waterListJson == null) {
            try {
                waterListJson = client.postForObject(ip + lastValIaqiForAllPointApi, lastValIaqiForAllPointJson, JSONObject.class);
            } catch (Exception e) {
                System.out.println("获取获取水断面列表接口发生网络错误");
            }
        }

        JSONArray Items = waterListJson.getJSONArray("Items");
        JSONArray waterSource = new JSONArray();//水源地断面
        JSONArray handover = new JSONArray();//交接断面
        JSONArray surfaceWater = new JSONArray();//地表水断面

        for (int i = 0; i < Items.size(); i++) {
            System.out.println(Items.getJSONObject(i));
            JSONObject item = Items.getJSONObject(i);
            Integer prop6 = item.getInteger("prop6");
            if (prop6 == 1) {
                waterSource.add(item);
            }
            String timesplit = item.getString("timesplit");
            if (timesplit.substring(0, 1).equals("0")) {
                handover.add(item);
            }
            if (timesplit.substring(0, 1).equals("1")) {
                surfaceWater.add(item);
            }
        }
        JSONArray waterSourceRefIds = new JSONArray();
        for (int i = 0; i < waterSource.size(); i++) {
            JSONObject item = waterSource.getJSONObject(i);
            String refId = item.getString("id");
            waterSourceRefIds.add(refId);
        }
        JSONObject waterSourceStandard = new JSONObject();
        waterSourceStandard.put("refIds", waterSourceRefIds);
        String newWaterDataApi = "/api/FactorData/GetWaterRefLastAndOverTimes";
        String waterSourceNewDataStr = null;
        Integer tip = 0;
        while (waterSourceNewDataStr == null && tip < 20) {
            try {
                waterSourceNewDataStr = client.postForObject(ip + newWaterDataApi, waterSourceStandard, String.class);
            } catch (Exception e) {
                tip++;
                System.out.println("获取水源地断面最新数据接口发生网络错误");
            }
        }
        tip = 0;
        JSONArray waterSourceNewData = JSONArray.parseArray(waterSourceNewDataStr);
        Integer exceedStandard = 0;//水源地超标数
        for (int i = 0; i < waterSourceNewData.size(); i++) {
            JSONObject waterSourceNewDataJson = waterSourceNewData.getJSONObject(i);
            JSONArray timesDatas = waterSourceNewDataJson.getJSONObject("info").getJSONArray("timesDatas");
            if (timesDatas.size() > 0) {
                exceedStandard++;
            }
        }
        Double waterSourcePre = Double.valueOf(Chufa(waterSourceNewData.size() - exceedStandard, waterSourceNewData.size()));
        //水源地达标率
        reJson.put("waterSourcePre", waterSourcePre);
        //交接断面优良率
        JSONObject handOverQueryJson = getRefIds(handover);
        String handoverNewDataStr = null;
        while (handoverNewDataStr == null && tip < 20) {
            try {
                handoverNewDataStr = client.postForObject(ip + newWaterDataApi, handOverQueryJson, String.class);
            } catch (Exception e) {
                tip++;
                System.out.println("获取交接断面最新数据接口发生网络错误");
            }
        }
        tip = 0;
        JSONArray handoverNewDataArray = JSONArray.parseArray(handoverNewDataStr);
        Integer handoverStand = 0;//水断面达标数
        for (int i = 0; i < handoverNewDataArray.size(); i++) {
            JSONObject handoverItem = handoverNewDataArray.getJSONObject(i);
            JSONObject handoverGrade = handoverItem.getJSONObject("info").getJSONObject("grade");
            Integer handoverIdx = handoverGrade.getInteger("idx");
            if (handoverIdx < 2) {
                handoverStand++;
            }
        }
        Double handoverPre = Double.valueOf(Chufa(handoverStand, handoverNewDataArray.size()));
        String handoverEvaluate = "";
        if (handoverPre > 0.6) {
            handoverEvaluate = "良好";
        } else {
            handoverEvaluate = "差";
        }
        reJson.put("handoverEvaluate", handoverEvaluate);
        //地表水优良率
        JSONObject surfaceWaterQueryJson = getRefIds(surfaceWater);
        String surfaceWaterNewDataStr = null;
        while (surfaceWaterNewDataStr == null && tip < 20) {
            try {
                surfaceWaterNewDataStr = client.postForObject(ip + newWaterDataApi, surfaceWaterQueryJson, String.class);
            } catch (Exception e) {
                tip++;
                System.out.println("获取地表断面最新数据接口发生网络错误");
            }
        }
        JSONArray surfaceWaterNewDataArray = JSONArray.parseArray(surfaceWaterNewDataStr);
        Integer surfaceWaterStand = 0;
        for (int i = 0; i < surfaceWaterNewDataArray.size(); i++) {
            JSONObject surfaceWaterItem = surfaceWaterNewDataArray.getJSONObject(i);
            JSONObject surfaceWaterGrade = surfaceWaterItem.getJSONObject("info").getJSONObject("grade");
            Integer surfaceWaterIdx = surfaceWaterGrade.getInteger("idx");
            if (surfaceWaterIdx <= 2) {
                surfaceWaterStand++;
            }
        }
        Double surfaceWaterPre = Double.valueOf(Chufa(surfaceWaterStand, surfaceWaterNewDataArray.size()));
        reJson.put("surfaceWaterPre", surfaceWaterPre);
        //传日期
        String dateNow = DateUtils.getDate();
        String yearPart = dateNow.substring(2, 4);
        String monthPart = dateNow.substring(5, 7);
        String dayPart = dateNow.substring(8, 10);
        String dateStr = yearPart + "年" + monthPart + "月" + dayPart + "日";
        reJson.put("date", dateStr);
        return reJson;
    }

    //街道空气质量排名
    @PostMapping("/streetAirQuality")
//    public void streetAirQuality(@RequestBody JSONObject queryJson)throws Exception{
    public JSONArray streetAirQuality() throws Exception {

        JSONArray reArray = new JSONArray();
        RestTemplate client = config2RestTemplate.restTemplate();
//        String ip = "http://101.71.159.242:30004";
        String ip = "http://133.1.254.22:5100";

        String lastValIaqiInListApi = "/api/FactorData/LastValIaqiInList";
        JSONObject lastValIaqiForAllPointJson = new JSONObject();
        lastValIaqiForAllPointJson.put("dis", "");
        lastValIaqiForAllPointJson.put("level", 3);
        String streetTotalDataStr = client.postForObject(ip + lastValIaqiInListApi, lastValIaqiForAllPointJson, String.class);
        JSONArray streetTotalDataArray = JSONArray.parseArray(streetTotalDataStr);

        for (int i = 0; i < streetTotalDataArray.size(); i++) {
            JSONObject streetAirItem = streetTotalDataArray.getJSONObject(i);
            System.out.println(streetAirItem);
            if (streetAirItem.getDouble("AQI") != null) {
                JSONObject reJson = new JSONObject();
                Double streetAirAqi = streetAirItem.getDouble("AQI");
                String streetName = streetAirItem.getString("Locname");
                reJson.put("streetAirAqi", streetAirAqi);
                reJson.put("streetName", streetName);
                reArray.add(reJson);
                System.out.println(streetAirAqi);
            }
        }
        reArray.sort(Comparator.comparing(obj -> ((JSONObject) obj).getDouble("streetAirAqi")).reversed());
        return reArray;
    }

    //街道水质量排名
    @PostMapping("/streetWaterQuality")
    public JSONArray streetWaterQuality() {
        JSONObject chengNan = new JSONObject();
        chengNan.put("name", "城南街道");
        chengNan.put("val", 2.4267);
        JSONObject tangHui = new JSONObject();
        tangHui.put("name", "塘汇街道");
        tangHui.put("val", 2.4733);
        JSONObject changShui = new JSONObject();
        changShui.put("name", "长水街道");
        changShui.put("val", 2.5567);
        JSONObject jiaBei = new JSONObject();
        jiaBei.put("name", "嘉北街道");
        jiaBei.put("val", 3.5883);
        JSONArray reArray = new JSONArray();
        reArray.add(chengNan);
        reArray.add(tangHui);
        reArray.add(changShui);
        reArray.add(jiaBei);
        return reArray;
    }

    //生态环境重点工作
    @PostMapping("/keyWork")
    public JSONArray keyWork() {
        String data = "[\n" +
                "\t{\n" +
                "\t\t\"name\": \"生态督查科\",\n" +
                "\t\t\"content\": \"协调相关部门、街道落实第二轮中央生态环保督察信访件问题整改,完成验收销号。\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"name\": \"环评规划科\",\n" +
                "\t\t\"content\": \"污染物减排、碳减排相关工作\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"name\": \"环评规划科\",\n" +
                "\t\t\"content\": \"国家级经济技术开发区规划环评、现代服务业集聚区规划环评“六张清单”修编\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"name\": \"环评规划科\",\n" +
                "\t\t\"content\": \"“十四五”排污权初始核定\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"name\": \"环评规划科\",\n" +
                "\t\t\"content\": \"生态保护与环境治理业投资工作\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"name\": \"水气环境管理科\",\n" +
                "\t\t\"content\": \"2021年空气质量优良率达84%\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"name\": \"监信固管科\",\n" +
                "\t\t\"content\": \"固废三率达标（危险固废综合处置率达100%,一般工业固废综合利用率≥95%,工业固废利用处置率≥99%）\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"name\": \"监信固管科\",\n" +
                "\t\t\"content\": \"土壤分地块安全利用率达100%\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"name\": \"环境执法科\",\n" +
                "\t\t\"content\": \"切实加强生态环境领域安全生产专项整治,保障区内生态环境安全；全面排查各类环境污染问题,严厉打击环境违法犯罪行为。%\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"name\": \"综合法宣科\",\n" +
                "\t\t\"content\": \"1.加强环境宣教。以提高公众生态环境质量满意度为目标,依托微信公众号、微博、抖音等平台,宣传报道生态环境有关工作,确保全年作品发布量不少于700条,抖音及微信公众号粉丝增长量不少于1万%\"\n" +
                "\t}\n" +
                "]";

        return JSONArray.parseArray(data);
    }

    //土壤固废质量
    @PostMapping("/solidQuality")
    public JSONArray solidQuality() {
        String data = "[\n" +
                "        {\n" +
                "            \"time\":\"当年至今\",\n" +
                "            \"totalProduction\":34023.482,\n" +
                "            \"totalDisposal\":32389.5001,\n" +
                "            \"storageCapacity\":1843.9269,\n" +
                "            \"wasteRate\":\"94.73%\",\n" +
                "        },{\n" +
                "            \"time\":\"2021年\",\n" +
                "            \"totalProduction\":74145.5189,\n" +
                "            \"totalDisposal\":74355.7756,\n" +
                "            \"storageCapacity\":174.549,\n" +
                "            \"wasteRate\":\"97.91%\",\n" +
                "        }\n" +
                "]";
        return JSONArray.parseArray(data);
    }

    //土壤危废质量
    @PostMapping("/harmQuality")
    public JSONArray harmQuality() {
        String data = "[\n" +
                "        {\n" +
                "            \"time\":\"当年至今\",\n" +
                "            \"totalProduction\":11780.13943,\n" +
                "            \"totalDisposal\":11291.1884,\n" +
                "            \"storageCapacity\":694.82063,\n" +
                "            \"wasteRate\":\"95.25%\",\n" +
                "        },{\n" +
                "            \"time\":\"2021年\",\n" +
                "            \"totalProduction\":19693.5565,\n" +
                "            \"totalDisposal\":20518.1256,\n" +
                "            \"storageCapacity\":165.726,\n" +
                "            \"wasteRate\":\"99.95%\",\n" +
                "        }\n" +
                "]";
        return JSONArray.parseArray(data);
    }
    //恶臭和环境的点位
    @PostMapping("/stenchEnvironment")
    public JSONArray stenchEnvironment()throws Exception{
        JSONArray reArray = new JSONArray();
        Integer tip = 0;
        RestTemplate client = config2RestTemplate.restTemplate();
//        String ip = "http://101.71.159.242:30004";
        String ip = "http://133.1.254.22:5100";

        String stenchApi = "/api/location/PagedList";
        //恶臭查询Json
        JSONObject stenchQuerry = new JSONObject();
        stenchQuerry.put("pageIndex",-1);
        stenchQuerry.put("type",3);
//        stenchQuerry.put("type",null);
        stenchQuerry.put("subType",null);
        String stenchReJson = null;
        Integer if0 = 0;//判断是否有subType=0的数据
        while (stenchReJson ==null && tip < 50){
            try{
                stenchReJson = client.postForObject(ip+stenchApi,stenchQuerry,String.class);
                JSONObject stenchJson2 = JSONObject.parseObject(stenchReJson);
                JSONArray allArray2 = stenchJson2.getJSONArray("Items");
                for (int i = 0; i < allArray2.size(); i++) {
                    JSONObject testJson = allArray2.getJSONObject(i);
                    if(testJson.getInteger("subtype") == 0){
                        if0 = 1;
                    }
                }
                if(if0 == 0){
                    stenchReJson = null;
                }
            }catch (Exception e){
                tip++;
                System.out.println("获取恶臭和环境接口发生网络错误");
            }
        }

        JSONObject stenchJson = JSONObject.parseObject(stenchReJson);
        JSONArray allArray = stenchJson.getJSONArray("Items");
        JSONArray stenchArray = new JSONArray();
        JSONArray vocsArray = new JSONArray();


        for (int i = 0; i < allArray.size(); i++) {
            JSONObject jsonObject = allArray.getJSONObject(i);
            if (jsonObject.getInteger("subtype") == 5) {
                stenchArray.add(jsonObject);
            }if (jsonObject.getInteger("subtype") == 0) {
                vocsArray.add(jsonObject);
            }
        }

        JSONArray stenchRefIds = new JSONArray();
        for (int i = 0; i < stenchArray.size(); i++) {
            JSONObject stenchItem = stenchArray.getJSONObject(i);
            String refId = stenchItem.getString("id");
//            System.out.println(stenchItem);
            stenchRefIds.add(refId);
        }
//        System.out.println(stenchRefIds);
        String stenchQuerryApi = "/api/FactorData/GetLastRefFacVals";
        JSONObject stenchQuerryJson = new JSONObject();
        stenchQuerryJson.put("refIds",stenchRefIds);
        stenchQuerryJson.put("fromType",0);
        stenchQuerryJson.put("facId","F23BDEE6-6C84-457B-B8C6-1BC4F833AF06");
        String stenchNewStr = null;

        tip = 0;
        while(stenchNewStr == null && tip < 50){
            try{
                stenchNewStr = client.postForObject(ip+stenchQuerryApi,stenchQuerryJson,String.class);
            }catch (Exception e){
                tip++;
                System.out.println("获取恶臭最新数据发生网络错误");
            }
        }
        tip = 0;

        JSONArray stenchNewDataArray = JSONArray.parseArray(stenchNewStr);
//        System.out.println(stenchNewDataArray);
        for (int i = 0; i < stenchNewDataArray.size(); i++) {
            JSONObject stenchItem = stenchNewDataArray.getJSONObject(i);
            String id = stenchItem.getString("refId");
            Double val = stenchItem.getDouble("val");
            for (int j = 0; j < stenchArray.size(); j++) {
                String id2 = stenchArray.getJSONObject(j).getString("id");
                if(id.equals(id2)){
                    stenchArray.getJSONObject(j).put("val",val);
                }
            }
//            System.out.println(stenchItem);
        }

        String vocsApi = "/api/FactorData/GetLastTVoc?refId={refId}&fromType={fromType}";
        for (int i = 0; i < vocsArray.size(); i++) {
            JSONObject vocsItem = vocsArray.getJSONObject(i);
            String refId = vocsItem.getString("id");
            HashMap vocsNewDataQuerry = new HashMap();
            vocsNewDataQuerry.put("refId",refId);
            vocsNewDataQuerry.put("fromType",0);
            String vocsNewDataStr = client.getForObject(ip+vocsApi,String.class,vocsNewDataQuerry);
            JSONObject vocsNewDataJson = JSONObject.parseObject(vocsNewDataStr);
            Double val = vocsNewDataJson.getDouble("tvoc");
            vocsArray.getJSONObject(i).put("val",val);
            System.out.println(vocsNewDataStr);
            reArray.add(vocsArray.getJSONObject(i));
        }

        for (int i = 0; i < stenchArray.size(); i++) {
            reArray.add(stenchArray.getJSONObject(i));
        }

        return reArray;
    }
    @Autowired
    private StMassifMapper stMassifMapper;
    @PostMapping("/massif")
    public StMassif massif(){
        return stMassifMapper.selectStMassifById(1L);
    }


    public JSONObject getRefIds(JSONArray data) {
        JSONArray refIdsArray = new JSONArray();
        for (int i = 0; i < data.size(); i++) {
            JSONObject item = data.getJSONObject(i);
            String refId = item.getString("id");
            refIdsArray.add(refId);
        }
        JSONObject refIds = new JSONObject();
        refIds.put("refIds", refIdsArray);
        return refIds;
    }

    @PostMapping("/aaa")
    public Double aaa() {
        int a = 1;
        int b = 3;
        Double c = Double.valueOf(Chufa(a, b));
        return c;
    }

    //除法方法
    public static String Chufa(int a, int b) {
        //“0.00000000”确定精度
        DecimalFormat dF = new DecimalFormat("0.0000");
        return dF.format((float) a / b);
    }


}
