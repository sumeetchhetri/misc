package com.sample.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.sample.exceptions.DummyHTTPStatusException;
import com.sample.exceptions.GenericApplicationException;

/**
 * @author sumeetc
 * All Exception thrown by the JAX-RS Service layer are wrapped up into proper
 * HTTP response error json/xml objects and returned back in the response
 */
public class ServiceJAXRSExceptionMapper implements ExceptionMapper<Exception> {
    
	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	public Response toResponse(Exception ex) {
    	if(ex instanceof GenericApplicationException)
    	{
    		GenericApplicationException exe = (GenericApplicationException)ex;
    		return Response.status(exe.getErrorDetails().getErrorDetails().get(0).getStatus()).entity(exe.getErrorDetails()).build();
    	}
    	else if(ex instanceof DummyHTTPStatusException)
    	{
    		DummyHTTPStatusException exe = (DummyHTTPStatusException)ex;
    		return Response.status(exe.getResponseCode()).entity(exe.getEntity()).build();
    	}
    	else	
    	{
    		return Response.status(500).entity(ex.getMessage()).build();
    	}
    }
}