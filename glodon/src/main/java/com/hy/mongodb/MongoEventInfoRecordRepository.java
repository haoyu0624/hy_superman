/**
 * 
 */
package com.hy.mongodb;

import com.hy.entity.EventStatusInfoRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @author haoy
 *
 */
@Component
public interface MongoEventInfoRecordRepository extends MongoRepository<EventStatusInfoRecord, Long> {

}
