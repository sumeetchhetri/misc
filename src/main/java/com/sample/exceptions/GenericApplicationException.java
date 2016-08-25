package com.sample.exceptions;

import javax.ws.rs.core.Response.Status;
import javax.xml.ws.WebFault;

import com.sample.models.ErrorDetail;
import com.sample.models.ErrorDetailList;

/**
 * @author sumeetc
 * This is the checked wrapper exception for all the service methods which may throw
 * exceptions, that exception needs to be wrapped into a fault object to be thrown as a soap:fault
 */
@WebFault(name = "GenericApplicationException", targetNamespace="http://sample.app.com")
public class GenericApplicationException extends Exception {

	/**
	 * default serial VersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private ErrorDetailList errorDetails;
	
	public GenericApplicationException(){
		super();
		errorDetails = new ErrorDetailList();
	}
	
	public GenericApplicationException(String message)
	{
		super(message);
		errorDetails = new ErrorDetailList();
		errorDetails.add(new ErrorDetail(500, "A001", "Generic application error."));
	}
	
	public GenericApplicationException(Throwable cause)
	{
		super(cause);
		errorDetails = new ErrorDetailList();
		errorDetails.add(new ErrorDetail(500, "A001", "Generic application error."));
	}
	
	public GenericApplicationException(String message, Throwable cause) {
        super(message, cause);
        errorDetails = new ErrorDetailList();
		errorDetails.add(new ErrorDetail(500, "A001", "Generic application error."));
    }
	
	public GenericApplicationException(Status status, String errorCode, String message) {
        super(message);
        errorDetails = new ErrorDetailList();
		errorDetails.add(new ErrorDetail(status.getStatusCode(), message, errorCode));
    }
	
	public GenericApplicationException(Status status, String errorCode) {
        super();
        errorDetails = new ErrorDetailList();
		errorDetails.add(new ErrorDetail(status.getStatusCode(), errorCode));
    }
	
	public GenericApplicationException(Throwable cause, ErrorDetail faultInfo) {
        super(cause);
        errorDetails = new ErrorDetailList();
		errorDetails.add(faultInfo);
    }
	
	public GenericApplicationException(String message, ErrorDetail faultInfo)
	{
		super(message);
		errorDetails = new ErrorDetailList();
		errorDetails.add(faultInfo);
	}
	
	public GenericApplicationException(String message, ErrorDetail faultInfo, Throwable cause)
	{
		super(message, cause);
		errorDetails = new ErrorDetailList();
		errorDetails.add(faultInfo);
	}
	
	public GenericApplicationException(ErrorDetail errorDetail) {
		super();
		errorDetails = new ErrorDetailList();
		errorDetails.add(errorDetail);
	}
	
	public GenericApplicationException(ErrorDetailList errorDetailList) {
		super();
		errorDetails = new ErrorDetailList();
		errorDetails = errorDetailList;
	}
	
	public GenericApplicationException(Exception e)
	{
		errorDetails = new ErrorDetailList();
		errorDetails.add(new ErrorDetail(500, "A001", "Generic application error."));
	}

	public ErrorDetailList getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetail(ErrorDetailList errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	public ErrorDetailList getFaultInfo() {
        return errorDetails;
    }
}
