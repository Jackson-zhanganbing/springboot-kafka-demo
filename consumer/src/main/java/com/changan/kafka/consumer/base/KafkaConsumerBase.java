package com.changan.kafka.consumer.base;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.InputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

/**
 * kafka消费者原生api
 *
 * @author zab
 * @date 2020-11-01 12:26
 */
public class KafkaConsumerBase {

    public static void main(String[] args) throws Exception{
        InputStream resourceAsStream = KafkaConsumerBase.class.getResourceAsStream("/kafka.properties");
        Properties properties = new Properties();

        properties.load(resourceAsStream);
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Arrays.asList("topic3"));

        while(true){
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            Iterator<ConsumerRecord<String, String>> recordIterator = consumerRecords.iterator();
            Thread.sleep(3000);
            while (recordIterator.hasNext()){
                ConsumerRecord<String, String> record = recordIterator.next();
                String key = record.key();
                String value = record.value();
                long offset = record.offset();
                int partition = record.partition();
                System.out.println("-----------------------------key:"+key+",value:"+value+",partition:"+partition+",offset:"+offset);
            }
        }
    }
}
