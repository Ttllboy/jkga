package com.ruoyi.jkza.util;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.*;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class RbMq {
    /**
          * 创建连接连接到RabbitMQ
          * @return   0 means success
          *           -1 means failure
          */
//    @RabbitListener(queues = "jgkj_mj")
//    @RabbitHandler
    public void BaseConnection(String personMessage, Message message,Channel channel)throws Exception{
        System.out.println("消费者消费了:"+personMessage.toString());
        System.out.println("消费者消费了:"+message.toString());
        System.out.println("消费者消费了:"+channel.toString());
//        ConnectionFactory factory = new ConnectionFactory();        //设置MabbitMQ所在主机ip或者主机名
//        factory.setHost("133.1.3.119");
//        factory.setPort(15672);
//        factory.setUsername("admin");
//        factory.setPassword("123456");        //创建一个连接
//        //3.获取连接对象
//        Connection connection=factory.newConnection();
//        //4.获取通道Channel
//        Channel channel = connection.createChannel();
//        channel.exchangeDeclare("acs_event","topic");

    }
}
