package com.sample.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@SuppressWarnings("serial")
@XmlRootElement(name="MessageRequest")
@JsonAutoDetect(getterVisibility=Visibility.NONE, fieldVisibility=Visibility.ANY, isGetterVisibility=Visibility.NONE)
@JsonSerialize(include=Inclusion.NON_NULL)
public class ExampleBean2 implements Serializable {
	
	private String prop1;
	
	private Integer prop2;
	
	private Long prop3;
	
	private Date prop4;
	
	private boolean prop5;

	private List<String> prop6 = new ArrayList<String>();
	
	Map<String, String> prop7;

	public String getProp1() {
		return prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public Integer getProp2() {
		return prop2;
	}

	public void setProp2(Integer prop2) {
		this.prop2 = prop2;
	}

	public Long getProp3() {
		return prop3;
	}

	public void setProp3(Long prop3) {
		this.prop3 = prop3;
	}

	public Date getProp4() {
		return prop4;
	}

	public void setProp4(Date prop4) {
		this.prop4 = prop4;
	}

	public boolean isProp5() {
		return prop5;
	}

	public void setProp5(boolean prop5) {
		this.prop5 = prop5;
	}

	public List<String> getProp6() {
		return prop6;
	}

	public void setProp6(List<String> prop6) {
		this.prop6 = prop6;
	}

	public Map<String, String> getProp7() {
		return prop7;
	}

	public void setProp7(Map<String, String> prop7) {
		this.prop7 = prop7;
	}
}
