package com.hy.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author liuk-m
 *
 */
@Document
public class EventStatusInfoRecord {

	public static final int batchNumber = 100;//批量发送条数

	/**正常*/
	public static final int SUCCESS = 1;
	/**异常*/
	public static final int FAIL = 2;

	/**无序-可用*/
	public static final int AVAILABLE = 1;
	/**无序-不可用*/
	public static final int NOT_AVAILABLE = 2;

	/**正常流程*/
	public static final int NORMAL = 1;
	/**历史流程*/
	public static final int HISTORY = 2;
	/**重试流程*/
	public static final int RETRY = 3;
	/**无效流程*/
	public static final int INVALID = 4;

	@Id
	private Long eventId;

	private Long TriggerId;

	private String itemId;//当前数据

	@Transient
	private int flowStatus;//流程状态

	private String nextItemId;//下条数据

	private Date createDate;

	private Date updateDate;
	//========有序
	private Integer sendStatus;//发送状态：1 正常 2 异常

	private Integer failTime;//失败次数
	//========无序
	private Integer number;//发送条数

	private Integer runningStatus;//运行状态 1：可用 2不可用

	public Integer getRunningStatus() {
		return runningStatus;
	}

	public void setRunningStatus(Integer runningStatus) {
		this.runningStatus = runningStatus;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getTriggerId() {
		return TriggerId;
	}

	public void setTriggerId(Long triggerId) {
		TriggerId = triggerId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getNextItemId() {
		return nextItemId;
	}

	public void setNextItemId(String nextItemId) {
		this.nextItemId = nextItemId;
	}

	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Integer getFailTime() {
		return failTime;
	}

	public void setFailTime(Integer failTime) {
		this.failTime = failTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

//	public void setCreateDate(Date createDate) {
//		Calendar ca = Calendar.getInstance();
//		ca.setTime(createDate);
//		ca.add(Calendar.HOUR_OF_DAY, 8);
//		this.createDate = ca.getTime();
//	}

	public Date getUpdateDate() {
		return updateDate;
	}

//	public void setUpdateDate(Date updateDate) {
//		Calendar ca = Calendar.getInstance();
//		ca.setTime(updateDate);
//		ca.add(Calendar.HOUR_OF_DAY, 8);
//		this.updateDate = ca.getTime();
//	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(int flowStatus) {
		this.flowStatus = flowStatus;
	}

	public Integer getNumber() {
		if(this.number == null){
			this.number = 1;
		}
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void addFailTime() {
		if(this.failTime == null){
			this.failTime = 0;
		}else{
			this.failTime = this.failTime + 1;
		}
	}

	public void clearFailTime() {
		this.failTime = null;
	}

	/**
	 * 根据消息设置flowStatus
	 * @param eventMsg
	 */
	public void setFlowStatus(EventMessage eventMsg) {
		String itemId = this.getItemId();
		if(eventMsg.isReTry()){
			//重试流程
			this.setFlowStatus(EventStatusInfoRecord.RETRY);
		}else{
			if(StringUtils.isEmpty(itemId)){
				//历史流程
				this.setFlowStatus(EventStatusInfoRecord.HISTORY);
			}else{
				//正常流程
				this.setFlowStatus(EventStatusInfoRecord.NORMAL);
			}
		}
	}

	public void addNumber() {
		if(this.number == null){
			this.number = 1;
		}else{
			this.number++;
		}
	}

	public boolean getBatchSendFlag() {
		if(this.number == batchNumber){
			return true;
		}else{
			return false;
		}
	}
}
