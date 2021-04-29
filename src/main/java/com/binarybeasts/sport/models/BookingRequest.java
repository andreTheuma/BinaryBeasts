package com.binarybeasts.sport.models;

import javax.validation.constraints.*;

public class BookingRequest{
	private Long clientId;//has to exist
	private Long resourceId;//has to exist
	private Long startTime;//cannot be less than now

	@Min(value = 900, message="Booking length cannot be less than 15 minutes")
	private Long length;//changed to length instead of endTime, there are multiple advantages.

	public BookingRequest(){
		this(null,null,null,null);
	}

	public BookingRequest(Long clientId, Long resourceId, Long startTime, Long length){
		this.clientId = clientId;
		this.resourceId = resourceId;
		this.startTime = startTime;
		this.length = length;
	}

	public Long getClientId() {return this.clientId;}
	public void setClientId(Long cid) {this.clientId = cid;}

	public Long getResourceId() {return this.resourceId;}
	public void setResourceId(Long rid) {this.resourceId = rid;}

	public Long getStartTime() {return this.startTime;}
	public void setStartTime(Long start) {this.startTime = start;}

	public Long getLength() {return this.length;}
	public void setLength(Long length) {this.length = length;}

	public boolean isWithin(Long time){
		return startTime >= time && time <= startTime + length;
	}
}
