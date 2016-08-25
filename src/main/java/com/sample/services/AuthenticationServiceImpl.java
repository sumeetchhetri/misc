package com.sample.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.codec.binary.Base64;
import com.sample.annotations.RestSuccessResponseStatusCode;
import com.sample.exceptions.GenericApplicationException;
import com.sample.models.User;

@WebService
@Path("/auth")
public class AuthenticationServiceImpl {

	private Map<String, User> sessions = new ConcurrentHashMap<String, User>();
	
	private Map<String, User> users = new HashMap<String, User>();
	
	@PostConstruct
	public void init()
	{
		for (int i = 0; i < 10; i++) {
			User bean = new User();
			bean.setId((i+1)+"");
			bean.setUserName("user"+bean.getId());
			bean.setPassword("password");
			bean.setRole("ROLE_USER");
			users.put(bean.getUserName()+bean.getPassword(), bean);
		}
	}
	
	
	@POST
	@Path("/loginh")
	@WebMethod
	public User loginHeaderParams(@HeaderParam("username") String username, 
			@HeaderParam("password") String password) throws GenericApplicationException {
		return attempAuthentication(username, password);
	}
	
	@POST
	@Path("/loginf")
	@WebMethod(exclude = true)
	public User loginFormParams(@FormParam("username") String username, 
			@FormParam("password") String password) throws GenericApplicationException {
		return attempAuthentication(username, password);
	}
	
	@POST
	@Path("/loginb")
	@WebMethod
	public User loginBasicAuth(@HeaderParam("authorization") String authorization) throws GenericApplicationException {
		if(authorization!=null && authorization.toLowerCase().startsWith("basic ")) {
			
			String userpass = authorization.substring(6);
			
			if (!Base64.isBase64(userpass.getBytes())) {
	            throw new GenericApplicationException(Status.UNAUTHORIZED, "AU01");
	        }
			
			userpass = new String(Base64.decodeBase64(userpass.getBytes()));
			
			String username = userpass.substring(0, userpass.indexOf(":"));
			String password = userpass.substring(userpass.indexOf(":")+1);
			
			return attempAuthentication(username, password);
		} else {
			throw new GenericApplicationException(Status.UNAUTHORIZED, "AU01", "Only Basic Authorization supported");
		}
	}
	
	@PUT
	@Path("/logout")
	@WebMethod
	@RestSuccessResponseStatusCode(Status.OK)
	public void logout(@QueryParam("token") String token) throws GenericApplicationException {
		if(token!=null && sessions.containsKey(token)) {
			sessions.remove(token);
			return;
		}
		throw new GenericApplicationException(Status.BAD_REQUEST, "AU02", "Invalid authentication token provided");
	}
	
	private User attempAuthentication(String username, String password) throws GenericApplicationException 
	{
		if(username!=null && password!=null) {
			User user = users.get(username+password);
			if(user!=null) {
				if(user.getPassword().equals(password)) {
			        String token = getNewEncodedToken();
			        sessions.put(token, user);
			        user.setToken(token);
			        return new User(user);
				} else {
					throw new GenericApplicationException(Status.UNAUTHORIZED, "AU01", "Invalid Password");
				}
			} else {
				throw new GenericApplicationException(Status.UNAUTHORIZED, "AU01", "User not Found");
			}
		} else {
			throw new GenericApplicationException(Status.UNAUTHORIZED, "AU01", "Invalid username/password provided");
		}
	}
	
	private String getNewEncodedToken()
	{
		String token = Base64.encodeBase64String(UUID.randomUUID().toString().getBytes());
        token = token.replaceAll("\r\n", "");
        return token;
	}
	
	public User getAuthView(String token)
	{
		return sessions.get(token);
	}
}
