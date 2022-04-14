package com.example.rabbitmq.rabbitmq.publisher;

import com.example.rabbitmq.rabbitmq.config.MessagingConfig;
import com.example.rabbitmq.rabbitmq.dto.Order;
import com.example.rabbitmq.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.rabbitmq.rabbitmq.config.MessagingConfig.ROUTING_KEY;

//@RestController
//public class OrderPublisher {
//
//    @Autowired
//    public RabbitTemplate rabbitTemplate;
//

//
//    @PostMapping("/post")
//    public String send(@RequestBody Message message){
//        rabbitTemplate.convertAndSend(topicExchange.getName(),ROUTING_KEY,message);
//        return "Message sent successfully";
//    }
//
//    @RequestMapping("/order/")
//    public String bookOrder(@RequestBody Order order, String restaurantName){
//        order.setOrderId(UUID.randomUUID().toString());
//          //restaurantService
//        //payment service
//        OrderStatus orderStatus = new OrderStatus(order, "PROCESS","OrderPlaced successfully in "+ restaurantName);
//        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, ROUTING_KEY,orderStatus);
//    return "Success ";
//    }
//}



@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    public TopicExchange topicExchange;

    @PostMapping("/post")
    public String send(@RequestBody Message message){
        template.convertAndSend(topicExchange.getName(),ROUTING_KEY,message);
        return "Message sent successfully";
    }

    @PostMapping("/rest/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        //restaurantservice
        //payment service
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed succesfully in " + restaurantName);
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
        return "Success !!";
    }
}
