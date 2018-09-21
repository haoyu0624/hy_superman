package com.hy.entity;

/**
 * @author liuk-m
 *
 */
public class EventMessage {

	private String reqId;
	private Long eventId;
	private boolean reTry;
//	private boolean order;
//	private String itemId;

	public EventMessage() {
	}

	public EventMessage(String reqId, Long eventId, boolean reTry) {
        super();
        this.reqId = reqId;
        this.eventId = eventId;
        this.reTry = reTry;
    }

	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public boolean isReTry() {
		return reTry;
	}

	public void setReTry(boolean reTry) {
		this.reTry = reTry;
	}

//	public boolean isOrder() {
//		return order;
//	}
//
//	public void setOrder(boolean order) {
//		this.order = order;
//	}
//
//	public String getItemId() {
//		return itemId;
//	}
//
//	public void setItemId(String itemId) {
//		this.itemId = itemId;
//	}
}
