package com.changan.kafka.base;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;

import java.io.InputStream;
import java.util.*;

/**
 * kafka topic基础操作
* @author zab
* @date 2020-11-01 22:44
*/
public class TopicBase {

    public static void main(String[] args) throws Exception {
        InputStream resourceAsStream = KafkaProducerBase.class.getResourceAsStream("/kafka.properties");
        Properties properties = new Properties();

        properties.load(resourceAsStream);

        KafkaAdminClient adminClient= (KafkaAdminClient) KafkaAdminClient.create(properties);

        //查询topics
        KafkaFuture<Set<String>> nameFutures = adminClient.listTopics().names();
        for (String name : nameFutures.get()) {
            System.out.println(name);
        }

        //创建Topics
        List<NewTopic> newTopics = Arrays.asList(new NewTopic("topic3", 2, (short) 3));

        //删除Topic
        adminClient.deleteTopics(Arrays.asList("testTopic"));

        //查看Topic详情
        DescribeTopicsResult describeTopics = adminClient.describeTopics(Arrays.asList("topic3"));
        Map<String, TopicDescription> tdm = describeTopics.all().get();
        for (Map.Entry<String, TopicDescription> entry : tdm.entrySet()) {
            System.out.println(entry.getKey()+"  "+entry.getValue());
        }

        adminClient.close();
    }
}
