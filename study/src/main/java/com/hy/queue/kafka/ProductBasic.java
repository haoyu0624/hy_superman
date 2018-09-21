package com.hy.queue.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by haoy on 2017/7/20.
 */
public class ProductBasic {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.129.56.190:9592,10.129.56.190:9593");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 5; i++){
            System.out.println("i===========>>"+i);
            producer.send(new ProducerRecord<String, String>("hytest", Integer.toString(i), Integer.toString(i)));
        }
        producer.close();
    }
}
