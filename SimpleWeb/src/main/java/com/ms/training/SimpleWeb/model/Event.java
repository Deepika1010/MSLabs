package com.ms.training.SimpleWeb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Event {

	@Id
	private int id;
	private String eventName;
	private String eventPayload;
	@Temporal(TemporalType.TIMESTAMP)
	private Date eventTime;

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventName=" + eventName + ", eventPayload=" + eventPayload + ", eventTime="
				+ eventTime + "]";
	}

	public Event(int id, String eventName, String eventPayload, Date eventTime) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.eventPayload = eventPayload;
		this.eventTime = eventTime;
	}

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventPayload() {
		return eventPayload;
	}

	public void setEventPayload(String eventPayload) {
		this.eventPayload = eventPayload;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

}
