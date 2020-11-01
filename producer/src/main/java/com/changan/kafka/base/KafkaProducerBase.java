package com.changan.kafka.base;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

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
        sendMsg("topic3", "A坐标");
    }

    private static void sendMsg(String topic, String msg) throws Exception {

        InputStream resourceAsStream = KafkaProducerBase.class.getResourceAsStream("/kafka.properties");
        Properties properties = new Properties();

        properties.load(resourceAsStream);

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);

        producer.send(record);

        producer.close();
    }

}
