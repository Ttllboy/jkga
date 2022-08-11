package com.ruoyi.jkza.util;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.jkza.config.Config2RestTemplate;
import com.ruoyi.jkza.config.WebSocket;
import com.ruoyi.jkza.domain.JkgaConfig;
import com.ruoyi.jkza.domain.YjSynergy;
import com.ruoyi.jkza.mapper.YjSynergyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class AsyncUtil {
    @Autowired
    Config2RestTemplate config2RestTemplate;
    @Autowired
    JkgaConfig jkgaConfig;
    @Autowired
    private YjSynergyMapper yjSynergyMapper;
    @Resource
    private WebSocket webSocket;

    @Async("threadPoolTaskExecutor")
    public Future<JSONObject> queryJkm(JSONObject sendJson) throws Exception {
        RestTemplate restTemplate = config2RestTemplate.restTemplate();
        String url = jkgaConfig.getJkm();
        JSONObject jsonObject1 = restTemplate.postForObject(url, sendJson, JSONObject.class);
        return new AsyncResult<JSONObject>(jsonObject1);
    }

    @Async("threadPoolTaskExecutor")
    public Future<JSONObject> compareEvent(YjSynergy synergy, List<YjSynergy> list2) throws Exception {
        for (int i = 0; i < list2.size(); i++) {
            Long eventId = list2.get(i).getEventId();
            if (synergy.getEventId().equals(eventId)) {
                Integer eventType = list2.get(i).getEventType();
                if (!synergy.getEventType().equals(eventType)) {
                    System.out.println("发现事件类型变更");
                    yjSynergyMapper.updateEvent(synergy);
                    JSONObject sendToWeb = new JSONObject();
                    sendToWeb.put("type", 0);
                    sendToWeb.put("message", eventId);
                    webSocket.sendMessage(sendToWeb.toString());
                    System.out.println("成功发送事件预警:" + sendToWeb.toString());
                }else {
                    System.out.println(eventId+"事件未变更");
                }
            }
        }
        return null;
    }
}
