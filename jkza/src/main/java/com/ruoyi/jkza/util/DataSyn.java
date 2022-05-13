package com.ruoyi.jkza.util;

import cn.hutool.db.DbUtil;
import com.ruoyi.jkza.domain.GdBuilding;
import com.ruoyi.jkza.domain.GdStreet;
import com.ruoyi.jkza.domain.StProject;
import com.ruoyi.jkza.mapper.GdBuildingMapper;
import com.ruoyi.jkza.service.IGdBuildingService;
import com.ruoyi.jkza.service.IGdStreetService;
import com.ruoyi.jkza.service.IStProjectService;
import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class DataSyn {


    @Autowired
    private IStProjectService stProjectService;

    @Autowired
    private IGdStreetService gdStreetService;

    @Autowired
    private IGdBuildingService gdBuildingService;

    @Autowired
    private GdBuildingMapper gdBuildingMapper;


    public void stProjectSyn() throws Exception {
        gdBuildingMapper.truncateGdBuilding();
        gdBuildingMapper.copyToGdBuilding();

    //StProject stProject = new StProject();
    //GdBuilding gdBuilding = new GdBuilding();
    //List<StProject> stProjects = stProjectService.selectStProjectList(stProject);
    //for(int i = 0; i < stProjects.size(); i++) {
    //    stProject = stProjects.get(i);
    //    gdBuilding.setProjectInfoNum(stProject.getGuid());
    //    gdBuilding.setBuildingSiteName(stProject.getProjectname());
    //    gdBuilding.setBuildingLicense(stProject.getSegmentid());
    //    gdBuilding.setBuildingSiteLocation(stProject.getProjectaddress());
    //
    //    GdStreet gdStreet = new GdStreet();
    //    gdStreet.setStreetName(stProject.getProjectarea());
    //    List<GdStreet> gdStreets = gdStreetService.selectGdStreetList(gdStreet);
    //    if (gdStreets.size() > 0) {
    //        gdStreet = gdStreets.get(0);
    //        gdBuilding.setStreetId(gdStreet.getId());
    //    }
    //    gdBuilding.setSpace(stProject.getBuildarea());
    //    gdBuilding.setPrice(stProject.getProjectprice());
    //    gdBuilding.setCoordinate(stProject.getCoordinate());
    //    gdBuildingService.insertGdBuilding(gdBuilding);
    //}
}
}
