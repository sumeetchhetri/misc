package com.sample.exceptions;

import javax.ws.rs.core.Response.Status;

public class DummyHTTPStatusException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Status responseCode;
	
	private Object entity;

	public DummyHTTPStatusException(Status responseCode) {
		super();
		this.responseCode = responseCode;
	}
	
	public DummyHTTPStatusException(Status responseCode, Object entity) {
		super();
		this.responseCode = responseCode;
		this.entity = entity;
	}
	
	public Status getResponseCode() {
		return responseCode;
	}
	
	public Object getEntity() {
		return entity;
	}
}
