//package com.example.rabbitmq.rabbitmq.dto;
//
//import com.example.rabbitmq.rabbitmq.config.MessagingConfig;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class User {
//
//    @RabbitListener(queues = MessagingConfig.QUEUE)
//    public void consumeMessageFromQueue(OrderStatus orderStatus){
//        System.out.println("message received from queue: "+orderStatus );
//    }
//}
