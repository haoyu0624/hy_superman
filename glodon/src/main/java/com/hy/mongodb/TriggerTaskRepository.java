package com.hy.mongodb;

import com.hy.entity.TriggerStatusRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author liuk-m
 *
 */
public interface TriggerTaskRepository extends MongoRepository<TriggerStatusRecord, Long> {

}
