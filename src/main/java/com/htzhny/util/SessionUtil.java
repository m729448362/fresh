package com.htzhny.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.htzhny.session.MySessionContext;

public class SessionUtil {
	private SessionUtil() {}  
    private static SessionUtil single=null;  
    //��̬��������   
    public static SessionUtil getInstance() {  
         if (single == null) {    
             single = new SessionUtil();  
         }    
        return single;  
    }  
	//С����sessionÿ������session��ı䣬����sessionһ���� wyc
	public HttpSession getSession(HttpServletRequest request ){
		String sessionId = request.getHeader("Cookie");
		if(sessionId==null){
			return null;
		}
		MySessionContext myc= MySessionContext.getInstance();  
		HttpSession session = myc.getSession(sessionId);  
		return session;
	}
}
