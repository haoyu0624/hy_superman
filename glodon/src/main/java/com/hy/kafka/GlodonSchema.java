package com.hy.kafka;

import com.glodon.kafka.api.internal.producer.avro.schema.SchemaMetadata;
import com.glodon.kafka.api.internal.producer.avro.type.PrimitiveTypes;
import org.apache.avro.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liuk-m
 *
 */
public class GlodonSchema {
	private static Logger logger = LoggerFactory.getLogger(GlodonSchema.class);

	public static Schema eventSchema = createEventSchema();

	public static final Schema createEventSchema() {
		Schema eventSchema = null;
		try {
			SchemaMetadata eventMetadata = SchemaMetadata.builder().name("event").type("record").namespace("octopus.avro")
					.addFilelds("eventId", PrimitiveTypes.Long)
					.addFilelds("reTry", PrimitiveTypes.Boolean)
					.addFilelds("rePush", PrimitiveTypes.Boolean)
					.build();
			eventSchema =  new Schema.Parser().parse(eventMetadata.json());
		}catch(Exception e){
			logger.info("eventSchema is error "+e.getMessage());
		}
		return eventSchema;
	}
	
}
