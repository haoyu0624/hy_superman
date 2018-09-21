/**
 * 
 */
package com.hy.mongodb.mongoTemplate;


import com.hy.entity.EventSendRecord;
import com.hy.entity.TriggerStatusRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author songmm-b
 *
 */
@Repository
public class MongoTemplateRepository {

	private static Logger logger = LoggerFactory.getLogger(MongoTemplateRepository.class);
	
	@Autowired
    MongoTemplate mongoTemplate;

	/**
	 * @param triggerStatusRecord
	 */
	public void updateTriggerStatusRecordByTriggerId(TriggerStatusRecord triggerStatusRecord) {
		if(triggerStatusRecord == null){

		}
		Query query=new Query(Criteria.where("triggerId").is(null));
		Update update = new Update().pull("rePull", "20180613");
		mongoTemplate.updateMulti(query,update,TriggerStatusRecord.class);
	}

	public Long getEventHit(Long eventId, long startTime, long endTime) {
		Criteria criteria = Criteria.where("eventId").is(eventId);
		criteria.and("invalidFlag").is(0);//有效
		criteria.and("sendStatus").is(1);
		criteria.and("updateDate").gte(new Date(startTime)).lte(new Date(endTime));
		Query query=new Query(criteria);
//		long count = mongoTemplate.count(query, EventSendRecord.class);
		List<EventSendRecord> eventSendRecords = mongoTemplate.find(query, EventSendRecord.class);
		for (int i = 0; i < eventSendRecords.size(); i++) {
			EventSendRecord eventSendRecord = eventSendRecords.get(i);
			logger.info(eventSendRecord.geteReqId());
		}
		return 0L;
	}

}
