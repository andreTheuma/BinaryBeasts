package com.binarybeasts.sport.models;

public class BookingResponse extends BookingRequest {

    private Long Id;

		public BookingResponse(){
			this(null, null, null, null, null);
		}

    public BookingResponse(Long Id, Long clientId, Long resourceId, Long startTime, Long length) {
        super(clientId, resourceId, startTime, length);
        this.Id = Id;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }
}
