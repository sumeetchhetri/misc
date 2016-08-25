package com.sample.interceptors;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;


 
/**
 * @author sumeetc
 * This interceptor processes the message at pre protocol phase, it adds the _type
 * query parameter to opt between json/xml response types
 *
 */
public class PreProtocolInterceptor extends AbstractPhaseInterceptor<Message> {
    public PreProtocolInterceptor() {
        super(Phase.PRE_PROTOCOL);
    }
 
    /* (non-Javadoc)
     * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
     */
    public void handleMessage(Message message) {
    	HttpServletRequest request = (HttpServletRequest)message.get("HTTP.REQUEST");
    	String queryStr = (String) message.get(Message.QUERY_STRING);
    	if (queryStr != null
                && (queryStr.contains("wsdl") || queryStr.contains("WSDL"))
                && request.getMethod().equals("GET")) {
            return; //let the schema request pass
        }
        try {
        	if(queryStr==null)
        		message.put(Message.QUERY_STRING, "_type=json");
        	else if(queryStr.indexOf("_type=")!=0 && queryStr.indexOf("&_type=")==-1 && !queryStr.equals("_wadl"))
        		message.put(Message.QUERY_STRING, queryStr + "&_type=json");
        } catch (Exception ce) {
        }
    }
 
    /* (non-Javadoc)
     * @see org.apache.cxf.phase.AbstractPhaseInterceptor#handleFault(org.apache.cxf.message.Message)
     */
    public void handleFault(Message message) {
    }
}