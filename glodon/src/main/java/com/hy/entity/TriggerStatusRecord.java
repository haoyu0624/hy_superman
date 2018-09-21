package com.hy.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author liuk-m
 *
 */
@Document
public class TriggerStatusRecord {

	@Id
	private Long triggerId;
	
	private String firstDataItemId;
	
	private String lastDataItemId;
	
	private Object lastData;
	
	private Object orderId;

	private Object rePullDate;
	
	private Long updateTime;

	public Long getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(Long triggerId) {
		this.triggerId = triggerId;
	}

	public String getFirstDataItemId() {
		return firstDataItemId;
	}

	public void setFirstDataItemId(String firstDataItemId) {
		this.firstDataItemId = firstDataItemId;
	}

	public String getLastDataItemId() {
		return lastDataItemId;
	}

	public void setLastDataItemId(String lastDataItemId) {
		this.lastDataItemId = lastDataItemId;
	}

	public Object getLastData() {
		return lastData;
	}

	public void setLastData(Object lastData) {
		this.lastData = lastData;
	}

	public Object getOrderId() {
		return orderId;
	}

	public void setOrderId(Object orderId) {
		this.orderId = orderId;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Object getRePullDate() {
		return rePullDate;
	}

	public void setRePullDate(Object rePullDate) {
		this.rePullDate = rePullDate;
	}
}
