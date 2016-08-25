package com.sample.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.sample.exceptions.GenericApplicationException;

@Path("/log")
public class LogServiceImpl {

	@GET
	@Path("/")
	public String getLog() throws GenericApplicationException {
		return "[INFO] Some example log data is returned from the log service...";
	}
}
