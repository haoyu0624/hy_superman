package com.hy.kafka;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/17
 * Time: 15:30
 */

import com.glodon.kafka.api.internal.producer.reporter.Order;
import com.glodon.kafka.api.internal.producer.reporter.Reporter;
import com.glodon.kafka.api.internal.producer.sender.ProducerProxy;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @auther haoy
 * @create 2018/5/17
 */
public class EventKafkaInform {
    private ProducerProxy<String, GenericRecord> producer;

    public static void main(String[] args) throws Exception {
//        long start = System.currentTimeMillis();
//        List ss = new ArrayList();
//        for (int i = 0; i < 50000000; i++) {
//            if(true){
//                ss.add(i);
//            }
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end-start);
        EventKafkaInform p = new EventKafkaInform();
        p.init();
        String topic = "octopusEventTopic";
        Long eventId = 66L;
        GenericRecord record = new GenericData.Record(GlodonSchema.eventSchema);
        record.put("eventId", eventId);
        record.put("reTry", false);
        record.put("rePush", false);
        p.sendMessage(topic,eventId.toString(),record);
    }

    public void sendMessage(String topic,String key,GenericRecord record) throws Exception{

        ProducerRecord<String, GenericRecord> producerRecord = new ProducerRecord<String, GenericRecord>(topic, key, record);

        Future<RecordMetadata> future = producer.send(producerRecord);

        try {
//            long s = System.currentTimeMillis();
            future.get();
//            long e = System.currentTimeMillis();
//            System.out.println("耗时："+(e-s));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //    @PostConstruct
    public void init() {
//        Reporter reporter = Reporter.newBuilder().keepInOrder(GlodonSchema.isOrder()).bootstrapServers(bootstrapServers).build();
        Properties props = new Properties();
        props.put("acks", "all");
        props.put("retries", Integer.valueOf(2));
        props.put("retry.backoff.ms", Integer.valueOf(200));
        props.put("batch.size", Integer.valueOf(16384));
        props.put("linger.ms", Integer.valueOf(1));
        props.put("buffer.memory", Integer.valueOf(33554432));
        props.put("request.timeout.ms", Integer.valueOf(10000));
        props.put("max.block.ms", Integer.valueOf('\uea60'));

        Reporter reporter = Reporter.newBuilder().keepInOrder(Order.FALSE).bootstrapServers("10.129.56.190:9592,10.129.56.190:9593").properties(props).build();

        //producer = (SyncProduceSender) reporter.getSender();

        producer = reporter.getSender();
    }
}
