package com.binarybeasts.client.models;


public class Booking {
    private Long Id;
    private Long clientId;
    private Long resourceId;
    private Long startTime;
    private Long length;

    public Booking(){
        this(null,null,null,null,null);
    }

    public Booking(Long clientId, Long resourceId, Long startTime, Long length){
        this(null, clientId, resourceId, startTime, length);
    }

    public Booking(Long Id, Long clientId, Long resourceId, Long startTime, Long length){
        this.Id = Id;
        this.clientId = clientId;
        this.resourceId = resourceId;
        this.startTime = startTime;
        this.length = length;
    }

    public Long getId() {return this.Id;}
    public void setId(Long Id) {this.Id = Id;}

    public Long getClientId() {return this.clientId;}
    public void setClientId(Long cid) {this.clientId = cid;}

    public Long getResourceId() {return this.resourceId;}
    public void setResourceId(Long rid) {this.resourceId = rid;}

    public Long getStartTime() {return this.startTime;}
    public void setStartTime(Long start) {this.startTime = start;}

    public Long getLength() {return this.length;}
    public void setLength(Long length) {this.length = length;}

    public boolean isWithin(Long time){
        return startTime >= time && time <= length;
    }
}
