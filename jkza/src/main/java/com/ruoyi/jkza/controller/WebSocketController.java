package com.ruoyi.jkza.controller;

import com.ruoyi.jkza.config.WebSocket;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/web")
public class WebSocketController {
    @Resource
    private WebSocket webSocket;

    @PostMapping("/eventPop")
    public void eventPop(){
        System.out.println(1);
        String msg = "";
        int a = 0;
        for (int i = 0; i < 100; i++) {
            msg = String.valueOf(a);
            webSocket.sendMessage(msg);
            a=a+1;
        }
    }

}
