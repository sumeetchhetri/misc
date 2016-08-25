package com.sample.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.annotations.RestSuccessResponseStatusCode;
import com.sample.exceptions.DummyHTTPStatusException;
import com.sample.exceptions.GenericApplicationException;
import com.sample.models.ErrorDetail;
import com.sample.models.ExampleBean;
import com.sample.models.ExampleBean2;

@WebService
@Path("/example")
public class ExampleBeanServiceImpl {

	private Map<String, ExampleBean> beans = new ConcurrentHashMap<String, ExampleBean>();
	
	@Autowired
	private AuthenticationServiceImpl authServiceImpl;
	
	@PostConstruct
	public void init()
	{
		for (int i = 0; i < 10; i++) {
			ExampleBean bean = new ExampleBean();
			bean.setId((i+1)+"");
			bean.setName("bean"+bean.getId());
			bean.setOrder(i+1);
			bean.setValid(true);
			
			bean.setBean(new ExampleBean2());
			bean.getBean().setProp1("bean2"+bean.getId());
			bean.getBean().setProp2(1);
			bean.getBean().setProp3(2L);
			bean.getBean().setProp4(new Date());
			bean.getBean().setProp5(true);
			bean.getBean().setProp6(new ArrayList<String>());
			bean.getBean().getProp6().add("abc");
			bean.getBean().getProp6().add("def");
			bean.getBean().setProp7(new HashMap<String, String>());
			bean.getBean().getProp7().put("gh", "ij");
			bean.getBean().getProp7().put("kl", "mn");
			
			ExampleBean2 exb2 = new ExampleBean2();
			exb2.setProp1("bean3"+bean.getId());
			exb2.setProp2(1);
			exb2.setProp3(2L);
			exb2.setProp4(new Date());
			exb2.setProp5(true);
			exb2.setProp6(new ArrayList<String>());
			exb2.getProp6().add("abc");
			exb2.getProp6().add("def");
			exb2.setProp7(new HashMap<String, String>());
			exb2.getProp7().put("gh", "ij");
			exb2.getProp7().put("kl", "mn");
			
			bean.setBeans(new ArrayList<ExampleBean2>());
			bean.getBeans().add(exb2);
			exb2.setProp1("bean3"+bean.getId());
			bean.getBeans().add(exb2);
			
			bean.setMapofBeans(new HashMap<String, ExampleBean2>());
			bean.getMapofBeans().put("bean2"+bean.getId(), bean.getBean());
			bean.getMapofBeans().put("bean3"+bean.getId(), exb2);
			
			beans.put(bean.getId(), bean);
		}
	}

	@GET
	@Path("/")
	@WebMethod
	public List<ExampleBean> getBeans(@QueryParam("token") String token) throws GenericApplicationException {
		if(token==null || authServiceImpl.getAuthView(token)==null)
			throw new GenericApplicationException(new ErrorDetail(401, "Unauthenticated, please login", "EBS01"));
		List<ExampleBean> beanlist = new ArrayList<ExampleBean>(beans.values());
		return beanlist;
	}

    @GET
    @Path("/error")
    @WebMethod
    public List<ExampleBean> getBeansError(@QueryParam("token") String token) throws GenericApplicationException {
        if(token==null || authServiceImpl.getAuthView(token)==null)
            throw new GenericApplicationException(new ErrorDetail(401, "Unauthenticated, please login", "EBS01"));
        List<ExampleBean> beanlist = new ArrayList<ExampleBean>(beans.values());
        throw new DummyHTTPStatusException(Status.BAD_REQUEST, beanlist);
    }

	@POST
	@Path("/")
	@WebMethod
	@RestSuccessResponseStatusCode(Status.CREATED)
	public ExampleBean addBean(@QueryParam("token") String token, ExampleBean bean) throws GenericApplicationException {
		if(token==null || authServiceImpl.getAuthView(token)==null)
			throw new GenericApplicationException(new ErrorDetail(401, "Unauthenticated, please login", "EBS01"));
		if(bean==null || bean.getId()==null)
			throw new GenericApplicationException(new ErrorDetail(400, "Inavlid Bean details specified", "EBS01"));
		bean.setId("bean11");
		beans.put("bean11", bean);
		return bean;
	}
	
	@POST
	@Path("/")
	@WebMethod
	@RestSuccessResponseStatusCode(Status.CREATED)
	public void addBeans(@QueryParam("token") String token, List<ExampleBean> beanList) throws GenericApplicationException {
		if(token==null || authServiceImpl.getAuthView(token)==null)
			throw new GenericApplicationException(new ErrorDetail(401, "Unauthenticated, please login", "EBS01"));
		int counter = 11;
		for (ExampleBean bean : beanList) {
			if(bean==null || bean.getId()==null)
				throw new GenericApplicationException(new ErrorDetail(400, "Inavlid Bean details specified", "EBS01"));
			bean.setId("bean" + counter);
			beans.put(bean.getId(), bean);
			counter++;
		}
	}

	/*@DELETE
	@Path("/{beanId}/")
	@WebMethod
	public ExampleBean deleteBean(@QueryParam("token") String token, @PathParam("beanId") String id) throws GenericApplicationException {
		if(token==null || authServiceImpl.getAuthView(token)==null)
			throw new GenericApplicationException(new ErrorDetail(401, "Unauthenticated, please login", "EBS01"));
		ExampleBean existingBean = beans.get(id);
		if(existingBean!=null) {
			return existingBean;
		} else {
			throw new GenericApplicationException(new ErrorDetail(400, "Invalid beanid specified", "EBS01"));
		}
	}

	@PUT
	@Path("/{beanId}/")
	@WebMethod
	public ExampleBean updateBean(@QueryParam("token") String token, @PathParam("beanId") String id, ExampleBean bean) throws GenericApplicationException {
		if(token==null || authServiceImpl.getAuthView(token)==null)
			throw new GenericApplicationException(new ErrorDetail(401, "Unauthenticated, please login", "EBS01"));
		ExampleBean existingBean = beans.get(id);
		if(existingBean!=null) {
			return existingBean;
		} else {
			throw new GenericApplicationException(new ErrorDetail(400, "Invalid beanid specified", "EBS01"));
		}
	}

	@GET
	@Path("/{beanId}/")
	@WebMethod
	public ExampleBean getBean(@QueryParam("token") String token, @PathParam("beanId") String id) throws GenericApplicationException {
		if(token==null || authServiceImpl.getAuthView(token)==null)
			throw new GenericApplicationException(new ErrorDetail(401, "Unauthenticated, please login", "EBS01"));
		ExampleBean existingBean = beans.get(id);
		if(existingBean!=null) {
			return existingBean;
		} else {
			throw new GenericApplicationException(new ErrorDetail(400, "Invalid beanid specified", "EBS01"));
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getLog() throws GenericApplicationException {
		return "[INFO] Some example log data is returned from the log service...";
	}
	
	@GET
	@Path("/u")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String upload(Attachment attach) throws GenericApplicationException {
		return "[INFO] Some example log data is returned from the log service...";
	}
	
	@GET
	@Path("/u1")
	public String upload1(org.apache.cxf.message.Attachment attach) throws GenericApplicationException {
		return "[INFO] Some example log data is returned from the log service...";
	}
	
	@GET
	@Path("/u2")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String upload2(MultipartBody attach) throws GenericApplicationException {
		return "[INFO] Some example log data is returned from the log service...";
	}
	
	@GET
	@Path("/u3")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String upload3(MultivaluedHashMap<String, String> attach) throws GenericApplicationException {
		return "[INFO] Some example log data is returned from the log service...";
	}
	
	@GET
	@Path("/u4")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String upload4(MultivaluedMap<String, String> attach) throws GenericApplicationException {
		return "[INFO] Some example log data is returned from the log service...";
	}*/
}
