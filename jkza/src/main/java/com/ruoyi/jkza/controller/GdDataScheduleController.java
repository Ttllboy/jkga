package com.ruoyi.jkza.controller;


import com.ruoyi.jkza.domain.GdCheckLose;
import com.ruoyi.jkza.domain.GdWorker;
import com.ruoyi.jkza.domain.GdWorkerCheck;
import com.ruoyi.jkza.service.IGdCheckLoseService;
import com.ruoyi.jkza.service.IGdWorkerCheckService;
import com.ruoyi.jkza.service.IGdWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class GdDataScheduleController {

    @Autowired
    private IGdWorkerService gdWorkerService;

    @Autowired
    private IGdWorkerCheckService gdWorkerCheckService;

    @Autowired
    private IGdCheckLoseService gdCheckLoseService;

    //@Scheduled(cron = "*/5 * * * * ?")
    public void test()throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);calendar.add(Calendar.DAY_OF_MONTH,-1);
        date = calendar.getTime();
        System.out.println(date);
        GdWorker gdWorker = new GdWorker();
        List<GdWorker> gdWorkers = gdWorkerService.selectGdWorkerList(gdWorker);
        GdWorkerCheck gdWorkerCheck = new GdWorkerCheck();
        gdWorkerCheck.setCheckTime(date);
        List<GdWorkerCheck> gdWorkerChecks = gdWorkerCheckService.selectGdWorkerCheckList(gdWorkerCheck);
        GdCheckLose gdCheckLose = new GdCheckLose();
        List<GdCheckLose> gdCheckLoses = null;
        for(int i = 0; i < gdWorkers.size(); i++){
            gdWorker = gdWorkers.get(i);
            for(int j = 0; j <gdWorkerChecks.size(); j++){
                gdWorkerCheck = gdWorkerChecks.get(j);
                if(!gdWorker.getWorkerLicense().equals(gdWorkerCheck.getWorkerLicense())){
                    gdCheckLose.setPeopleType(gdWorker.getPeopleType());
                    gdCheckLose.setWorkerName(gdWorker.getWorkerName());
                    gdCheckLose.setWorkerLicense(gdWorker.getWorkerLicense());
                    gdCheckLose.setPhone(gdWorker.getPhone());
                    gdCheckLose.setCheckTime(date);
                    gdCheckLose.setBuildingId(gdWorker.getBuildingId());
                    gdCheckLose.setDataType(1);
                    gdCheckLoses = gdCheckLoseService.selectGdCheckLoseList(gdCheckLose);
                    if(gdCheckLoses.size() == 0){
                        gdCheckLoseService.insertGdCheckLose(gdCheckLose);
                    }else {
                        System.out.println("数据已存在");
                    }
                }
            }
        }
    }

}
