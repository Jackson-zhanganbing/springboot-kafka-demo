package com.changan.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private KafkaTemplate kafkaTemplate;
    @RequestMapping("/send")
    public String sendMsg(String msg){
        kafkaTemplate.send("testTopic",msg);
        return msg;
    }
}
