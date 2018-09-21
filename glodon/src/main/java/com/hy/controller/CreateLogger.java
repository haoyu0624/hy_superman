package com.hy.controller;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/9
 * Time: 11:04
 */

import com.hy.entity.EventSendRecord;
import com.hy.entity.EventStatusInfoRecord;
import com.hy.entity.TriggerStatusRecord;
import com.hy.filebeat.ProduceLog;
import com.hy.mongodb.MongoEventInfoRecordRepository;
import com.hy.mongodb.TriggerTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * @auther haoy
 * @create 2018/5/9
 */
@Controller
@RequestMapping("/createLogger")
public class CreateLogger {

    @Autowired
    private ProduceLog produceLog;


    @RequestMapping("/test")
    @ResponseBody
    String test(Long count) throws InterruptedException {
        produceLog.createLog(count);
        return "OK";
    }

}

