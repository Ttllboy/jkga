package com.ruoyi.jkza.util;

import cn.hutool.db.DbUtil;
import com.ruoyi.jkza.domain.*;
import com.ruoyi.jkza.mapper.GdBuildingMapper;
import com.ruoyi.jkza.mapper.GdDustDataMapper;
import com.ruoyi.jkza.mapper.LtdqExcessivestatisticaldataMapper;
import com.ruoyi.jkza.service.IGdBuildingService;
import com.ruoyi.jkza.service.IGdStreetService;
import com.ruoyi.jkza.service.IStProjectService;
import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

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





    @Scheduled(cron = "0 30 3 * * ?")
    //复制stproject表到gdbuilding表
    public void stProjectSyn() throws Exception {
        gdBuildingMapper.truncateGdBuilding();
        gdBuildingMapper.copyToGdBuilding();
    }

    @Autowired
    private LtdqExcessivestatisticaldataMapper ltdqExcessivestatisticaldataMapper;
    @Autowired
    private GdDustDataMapper gdDustDataMapper;

    @Scheduled(cron = "0 0 4 * * ?")
    //从ltdq方欣扬尘设备数据，保存到GdDustData表
    public void ltdqSyn()throws Exception{
        List<LtdqExcessivestatisticaldata> list = ltdqExcessivestatisticaldataMapper.selectLtdqExcessivestatisticaldataList(new LtdqExcessivestatisticaldata());
        for(int i = 0; i < list.size(); i++){
            LtdqExcessivestatisticaldata ltdqExcessivestatisticaldata = list.get(i);
            GdDustData gdDustData = new GdDustData();
            GdBuilding gdBuilding = new GdBuilding();
            gdBuilding = gdBuildingMapper.selectGdBuildingByGuid(ltdqExcessivestatisticaldata.getProjectguid());
            gdDustData.setBuildingId(gdBuilding.getId());
            gdDustData.setEquipmentNum(ltdqExcessivestatisticaldata.getDevicesn());
            gdDustData.setDate(ltdqExcessivestatisticaldata.getCreatedatet());
            gdDustData.setPm25(ltdqExcessivestatisticaldata.getPm25());
            gdDustData.setPm10(ltdqExcessivestatisticaldata.getPm10());
            gdDustData.setTemperature(ltdqExcessivestatisticaldata.getPd04());
            gdDustData.setHumidity(ltdqExcessivestatisticaldata.getPd05());
            gdDustData.setNoise(ltdqExcessivestatisticaldata.getPd09());
            gdDustDataMapper.insertGdDustData(gdDustData);
        }
    }
 }
