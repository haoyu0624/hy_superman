package com.hy.controller;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/9
 * Time: 11:04
 */

import com.alibaba.fastjson.JSONObject;
import com.hy.entity.EventSendRecord;
import com.hy.entity.EventStatusInfoRecord;
import com.hy.entity.Person;
import com.hy.entity.TriggerStatusRecord;
import com.hy.mongodb.MongoEventInfoRecordRepository;
import com.hy.mongodb.TriggerTaskRepository;
import com.hy.mongodb.mongoTemplate.MongoTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.data.domain.ExampleMatcher.matching;

/**
 * @auther haoy
 * @create 2018/5/9
 */
@Controller
//@SpringBootApplication
@RequestMapping("/eventInitController")
public class EventInitController {

    @Autowired
    private MongoEventInfoRecordRepository mongoEventInfoRecordRepository;
    @Autowired
    private TriggerTaskRepository triggerTaskRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MongoTemplateRepository mongoTemplateRepository;

    @RequestMapping("/eventorderStatusInfo")
    @ResponseBody
    String saveEventOrderStatusInfo() {
        EventStatusInfoRecord eventStatusInfoRecord = new EventStatusInfoRecord();
        eventStatusInfoRecord.setEventId(66L);
        eventStatusInfoRecord.setTriggerId(66L);
        eventStatusInfoRecord.setItemId(null);
        eventStatusInfoRecord.setSendStatus(EventStatusInfoRecord.SUCCESS);
        eventStatusInfoRecord.setNextItemId(null);
        eventStatusInfoRecord.setCreateDate(new Date());
        eventStatusInfoRecord.setUpdateDate(new Date());
        mongoEventInfoRecordRepository.save(eventStatusInfoRecord);
        Optional<EventStatusInfoRecord> byId = mongoEventInfoRecordRepository.findById(66L);
        EventStatusInfoRecord eventStatusInfoRecord1 = byId.get();
        System.out.println(eventStatusInfoRecord1.getCreateDate());
        return "{\"status\":\"success\"}";
    }

    @RequestMapping("/eventUnorderStatusInfo")
    @ResponseBody
    String saveEventUnorderStatusInfo() {
        EventStatusInfoRecord eventStatusInfoRecord = new EventStatusInfoRecord();
        eventStatusInfoRecord.setEventId(202L);
        eventStatusInfoRecord.setTriggerId(66L);
        eventStatusInfoRecord.setItemId(null);
        //eventStatusInfoRecord.setSendStatus(EventStatusInfoRecord.SUCCESS);
        eventStatusInfoRecord.setNextItemId(null);
        eventStatusInfoRecord.setCreateDate(new Date());
        eventStatusInfoRecord.setUpdateDate(new Date());
        mongoEventInfoRecordRepository.save(eventStatusInfoRecord);
        return "{\"status\":\"success\"}";
    }

    @RequestMapping("/updateSendFailData")
    @ResponseBody
    String updateSendFailData(Long eventId) {
        if(null != eventId){

        }
        EventStatusInfoRecord eventStatusInfoRecord = new EventStatusInfoRecord();
        eventStatusInfoRecord.setEventId(202L);
        eventStatusInfoRecord.setTriggerId(66L);
        eventStatusInfoRecord.setItemId(null);
        //eventStatusInfoRecord.setSendStatus(EventStatusInfoRecord.SUCCESS);
        eventStatusInfoRecord.setNextItemId(null);
        eventStatusInfoRecord.setCreateDate(new Date());
        eventStatusInfoRecord.setUpdateDate(new Date());
        mongoEventInfoRecordRepository.save(eventStatusInfoRecord);
        return "{\"status\":\"success\"}";
    }

    @RequestMapping("/updateRePull")
    @ResponseBody
    String updateRePull(Long triggerId) {
//        Example.getProbe()
//        triggerTaskRepository.findOne(triggerId);
//        mongoTemplateRepository.updateTriggerStatusRecordByTriggerId(triggerStatusRecord);
        TriggerStatusRecord triggerStatusRecord = new TriggerStatusRecord();
        triggerStatusRecord.setTriggerId(triggerId);
        Example<TriggerStatusRecord> example = Example.of(triggerStatusRecord);
        TriggerStatusRecord triggerStatusRecord2 = triggerTaskRepository.findOne(example).get();
        System.out.println(triggerStatusRecord2.getOrderId());
        return "{\"status\":\"success\"}";
    }

    @RequestMapping("/upsetTest")
    @ResponseBody
    String upsetTest(String id) {
//        EventSendRecord eventSendRecord = new EventSendRecord();
//        eventSendRecord.setId(id);
//        eventSendRecord.setCreateDate(new Date());
//        eventSendRecord.setUpdateDate(new Date());
//        Query query=new Query(Criteria.where("id").is(eventSendRecord.getId()));
//        Update update = new Update();
//        update.setOnInsert("createDate",eventSendRecord.getCreateDate());
//        update.set("updateCreate",eventSendRecord.getUpdateDate());
//        update.set("eventId",id);
//        update.set("flowStatus",8);
//        mongoTemplate.upsert(query, update, EventSendRecord.class);

        long start = 1530460799000L;
        long end = 1531929599000L;
        Criteria criteria = Criteria.where("eventId").is(202L);
        criteria.and("invalidFlag").is(0);//有效
        criteria.and("sendStatus").is(1);
        criteria.and("updateDate").gte(new Date(start)).lte(new Date(end));
        Query query=new Query(criteria);
        System.out.println(query.toString());
        long count = mongoTemplate.count(query, EventSendRecord.class);
        System.out.println(count);
//        Query query1=new Query(Criteria.where("eventId").is(id));
//        Update update1 = new Update();
//        update1.set("flowStatus",5);
//        mongoTemplate.updateMulti(query1,update1,EventSendRecord.class);

//        Criteria criteria = Criteria.where("eventId").is(id);
////        criteria.and("status").is(EventSendRecord.FAIL);
//        criteria.and("flowStatus").ne(8);
//        Query query=new Query(criteria);
//        List<EventSendRecord> eventSendRecords = mongoTemplate.find(query, EventSendRecord.class);
        return "{\"status\":\"success\"}";
    }

    @RequestMapping("/mongoTest")
    @ResponseBody
    String mongoTest() {
        Long eventHit = mongoTemplateRepository.getEventHit(479L, 1531929600000L, 1532015999000L);
        return eventHit.toString();
    }


    public static void main(String[] args) {
//            List result = new ArrayList();
//        List result = null;
//            System.out.println(JSONObject.toJSONString(result));
//
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        Calendar lastDate = Calendar.getInstance();
//        String end = sdf.format(lastDate.getTime());
//        System.out.println("end"+end);
//
//        lastDate.roll(Calendar.DATE, -7);//日期回滚7天
//        String start = sdf.format(lastDate.getTime());
//        System.out.println("start"+start);
//        EventInitController.A a = new EventInitController.A();

        String ss = "dffsd${222}";
        Pattern pattern = Pattern.compile("\\$\\{.*?\\}");
        Matcher matcher = pattern.matcher(ss);
        if(matcher.find()){
            String matchParam = matcher.group();
            String matchValue = matchParam.substring(2, matchParam.length() - 1);
            System.out.println("matchValue = " + matchValue);
        }
        Person a = new Person(1,"1");
        Person b = new Person(2,"2");
        List<Person> list = new ArrayList();
        list.add(a);
        list.add(b);
        Map<Integer, String> collect = list.stream().collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println("collect = " + collect);

        EventSendRecord es = new EventSendRecord();
        es.setId("1");
        es.setItemId("1");
        EventSendRecord es2 = new EventSendRecord();
//        es2.setId("2");
        es2.setItemId("3");
        List<EventSendRecord> list1 = new ArrayList();
        list1.add(es);
        list1.add(es2);
//        list1.remove(es2);
        System.out.println("list1 = " + list1);
        Map<String, String> collect1 = list1.stream().collect(Collectors.toMap((c) -> {
            return StringUtils.isEmpty(c.getId()) ? c.getItemId() : c.getId();
//            return c.getId();
        }, EventSendRecord::getItemId));
        System.out.println("collect1 = " + collect1);


    }

    public void B(){
        A a1 = new A();
        String ttt = a1.ttt;
    }
    class A {
        private String ttt="11";
    }

}

