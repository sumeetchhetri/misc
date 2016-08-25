package com.sample.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "errorDetailList", namespace = "http://mtel.proconconsole.com")
@JsonAutoDetect(getterVisibility=Visibility.NONE, fieldVisibility=Visibility.ANY, isGetterVisibility=Visibility.NONE)
@JsonSerialize(include=Inclusion.NON_NULL)
public class ErrorDetailList {

	@XmlElement
	private List<ErrorDetail> errorDetails = new ArrayList<ErrorDetail>();

	/**
	 * @return the errorDetails
	 */
	public List<ErrorDetail> getErrorDetails() {
		if(errorDetails==null)
			errorDetails = new ArrayList<ErrorDetail>();
		return errorDetails;
	}

	/**
	 * @param errorDetails the errorDetails to set
	 */
	public void setErrorDetails(List<ErrorDetail> errorDetails) {
		this.errorDetails = errorDetails;
	}

	public void add(ErrorDetail error) {
		getErrorDetails().add(error);
	}
	
	public ErrorDetailList() {};

	public ErrorDetailList(List<ErrorDetail> errorDetails) {
		this.errorDetails = errorDetails;
	}
}
