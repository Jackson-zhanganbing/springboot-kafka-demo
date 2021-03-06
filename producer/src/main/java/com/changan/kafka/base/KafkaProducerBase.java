package com.changan.kafka.base;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * kafka原生api
 *
 * @author zab
 * @date 2020-11-01 12:04
 */
public class KafkaProducerBase {
    public static void main(String[] args) throws Exception {
        System.out.println(KafkaProducerBase.class.getResource("/").getPath());
        sendMsg("topic3", "A坐标");
    }

    private static void sendMsg(String topic, String msg) throws Exception {

        File file = new File("/Users/zhanganbing/IdeaProjects/springboot-kafka-demo/producer/out/production/resources/kafka.properties");
        InputStream resourceAsStream = new FileInputStream(file);
        Properties properties = new Properties();

        properties.load(resourceAsStream);

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);

        producer.send(record);

        producer.close();
    }

}
