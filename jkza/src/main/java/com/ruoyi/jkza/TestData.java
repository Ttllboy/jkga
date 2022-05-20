package com.ruoyi.jkza;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.ruoyi.jkza.domain.GdBuilding;
import com.ruoyi.jkza.domain.GdStreet;
import com.ruoyi.jkza.domain.StProject;
import com.ruoyi.jkza.service.IGdBuildingService;
import com.ruoyi.jkza.service.IGdStreetService;
import com.ruoyi.jkza.service.IStProjectService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestData {



    @Autowired
    private IStProjectService stProjectService;

    @Autowired
    private IGdStreetService gdStreetService;

    @Autowired
    private IGdBuildingService gdBuildingService;

    @Test
    public void main() {
        StProject stProject = new StProject();
        GdBuilding gdBuilding = new GdBuilding();
        List<StProject> stProjects = stProjectService.selectStProjectList(stProject);
        for(int i = 0; i < stProjects.size(); i++){
            stProject = stProjects.get(i);
            gdBuilding.setProjectInfoNum(stProject.getGuid());
            gdBuilding.setBuildingSiteName(stProject.getProjectname());
            gdBuilding.setBuildingLicense(stProject.getSegmentid());
            gdBuilding.setBuildingSiteLocation(stProject.getProjectaddress());

            GdStreet gdStreet = new GdStreet();
            gdStreet.setStreetName(stProject.getProjectarea());
            List<GdStreet> gdStreets = gdStreetService.selectGdStreetList(gdStreet);
            if(gdStreets.size() > 0){
                gdStreet = gdStreets.get(0);
                gdBuilding.setStreetId(gdStreet.getId());
            }
            gdBuilding.setCoordinate(stProject.getCoordinate());
            gdBuildingService.insertGdBuilding(gdBuilding);
        }
    }
}
