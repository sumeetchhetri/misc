package com.sample.models;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@SuppressWarnings("serial")
@XmlRootElement(name="ExampleBean")
@JsonAutoDetect(getterVisibility=Visibility.NONE, fieldVisibility=Visibility.ANY, isGetterVisibility=Visibility.NONE)
@JsonSerialize(include=Inclusion.NON_NULL)
public class ExampleBean implements Serializable {
	
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name;
	
	private Integer order;
	
	private Boolean valid;
	
	private ExampleBean2 bean;
	
	private List<ExampleBean2> beans;
	
	private Map<String, ExampleBean2> mapofBeans;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public ExampleBean2 getBean() {
		return bean;
	}

	public void setBean(ExampleBean2 bean) {
		this.bean = bean;
	}

	public List<ExampleBean2> getBeans() {
		return beans;
	}

	public void setBeans(List<ExampleBean2> beans) {
		this.beans = beans;
	}

	public Map<String, ExampleBean2> getMapofBeans() {
		return mapofBeans;
	}

	public void setMapofBeans(Map<String, ExampleBean2> mapofBeans) {
		this.mapofBeans = mapofBeans;
	}
}
