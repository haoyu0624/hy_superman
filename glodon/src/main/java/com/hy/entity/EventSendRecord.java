package com.hy.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/6/29
 * Time: 11:01
 */
@Document
//@CompoundIndexes(value = { @CompoundIndex(name="search_index", def = "{invalidFlag : 1, eventId : 1, sendStatus : 1}"))
@CompoundIndexes({
        @CompoundIndex(name="unorderRunningStatus_index", def = "{eventId : 1, invalidFlag : 1, sendStatus : 1}"),
        @CompoundIndex(name="retry_index", def = "{sendStatus : 1, mustOrdered : 1, invalidFlag : 1}")
})
public class EventSendRecord {

    public static final int FLOW_SUCCESS = 1; //成功
    public static final int FLOW_FAIL = 2;    //失败
    public static final int FLOW_RETRYING = 3;//重试中
    public static final int FLOW_SKIP = 4;    //已跳过
    public static final int FLOW_EXCEPTION = 5;//异常

    public static final int MAX_FAIL_TIME = 3;//最大失败次数

    @Id
    private String id;//itemId+eventId

    private String reqId;

    private String eReqId;

    private String itemId;

    private Map<String, ?> itemData;//数据信息
    @Indexed
    private Long eventId;

    private int flowStatus;//流程状态 1.成功 2.失败 3.重试中 4.已跳过 5.异常

    private Integer failTime;//失败次数

    private Long reTryTime;//重试时间

    private Date createDate;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String geteReqId() {
        return eReqId;
    }

    public void seteReqId(String eReqId) {
        this.eReqId = eReqId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Map<String, ?> getItemData() {
        return itemData;
    }

    public void setItemData(Map<String, ?> itemData) {
        this.itemData = itemData;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public int getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(int flowStatus) {
        this.flowStatus = flowStatus;
    }

    public Integer getFailTime() {
        return failTime;
    }

    public void setFailTime(Integer failTime) {
        this.failTime = failTime;
    }

    public Long getReTryTime() {
        return reTryTime;
    }

    public void setReTryTime(Long reTryTime) {
        this.reTryTime = reTryTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
