package com.ruoyi.jkza.util;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CustomerThread implements Runnable {
    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();        //设置MabbitMQ所在主机ip或者主机名
        factory.setHost("133.1.3.119");
        factory.setPort(15672);
        factory.setUsername("admin");
        factory.setPassword("123456");        //创建一个连接
        //3.获取连接对象
        Connection connection= null;
        try {
            connection = factory.newConnection();
            //4.获取通道Channel
            Channel channel = connection.createChannel();
            // 声明队列
            channel.queueDeclare("jgkj", false, false, false, null);
            System.out.println("Customer Waiting Received messages");

            // 绑定队列到交换机
            channel.queueBind("jgkj", "acs_event", "event.acs.swingcard.1");
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Customer Received '" + message + "'");
                }
            };
//            DefaultConsumer consumer = new DefaultConsumer(channel);
//            channel.basicConsume("jgkj", true, consumer);

//            while(true){
//                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//                String message = new String(delivery.getBody(),"UTF-8");
//                System.out.println(" 【[x] Received 】：" + message);
//            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

    }
}
