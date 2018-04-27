package com.htzhny.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSON;
import com.htzhny.entity.Bill;
import com.htzhny.entity.User;
import com.htzhny.service.BillService;
import com.htzhny.service.UserService;

import net.sf.json.JSONObject;

/**
 * �û�������
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	Logger log = Logger.getLogger(UserController.class);
	@Autowired(required=true)
    private UserService userService;
	
	@Autowired(required=true)
    private BillService billService;
    @RequestMapping("/view")
    public String view() {
        return "main/login";
    }

    @RequestMapping("/indexview")
    public String index() {
        return "main/index";
    }
    //��¼
    @RequestMapping(value="login", method = RequestMethod.POST)
    public @ResponseBody JSONObject login(@RequestBody Map<String,Object> params,HttpServletRequest request) {
    	JSONObject jsonObject = new JSONObject();
    	String user_name=(String) params.get("user_name");
    	String password=(String) params.get("password");
    	User user = userService.findUserByUserName(user_name);
    	if(null!=user){
    		if(password.equals(user.getPassword())){
    			request.getSession().setAttribute("user", user);
    			String result="��¼�ɹ�";
    			jsonObject.put("result",result);
    			User user2=(User) request.getSession().getAttribute("user");
    			String user1=JSON.toJSONString(user);
    			jsonObject.put("user",user1);
    			Integer user_id=user.getId();
    			Bill bill=billService.selectBillByUserId(user_id);
    			Calendar cal=Calendar.getInstance();
    			Date dt =new Date(); 
    			cal.setTime(dt);
    			int month=cal.get(Calendar.MONTH);
    			int year=cal.get(Calendar.YEAR);
    			if(bill!=null){
    				String a=bill.getOnemonth(); //�õ��˵�������
    				Integer oldYear=Integer.parseInt(a.substring(0, 4));
    			
    				Integer oldMonth=Integer.parseInt(a.substring(5));
    			
    			

    				if(oldYear==year){//����Ƿ����
    					if(oldMonth<month){ //�µ��·�����������ݿ��д洢���·ݣ�Ϊ���û�����һ���·ݵ��˵���
    						String id =UUID.randomUUID().toString();
    						String year_month=year+"-"+month;
    						Bill newBill=new Bill(id,user_id,year_month,0.00,1,"");
    						billService.addBill(newBill);
    						bill.setFlag(0);
    					}
    				}else if(oldYear<year){
    					String id =UUID.randomUUID().toString();
    					String year_month=year+"-"+month;
    					Bill newBill=new Bill(id,user_id,year_month,0.00,1,"");
    					billService.addBill(newBill);
    					bill.setFlag(0);
    			}
    			}else {
    				String id =UUID.randomUUID().toString();
					String year_month=year+"-"+month;
					Bill newBill=new Bill(id,user_id,year_month,0.00,1,"");
					billService.addBill(newBill);
    			}
    			
    		    
    			return jsonObject;
    
    			
    			
    		}
    		String result="�������";
    		jsonObject.put("result", result);
    		return jsonObject;
    	}
    	String result="���û���������";
		jsonObject.put("result", result);
		return jsonObject;
    }
    //ע��
    @RequestMapping(value="register", method = RequestMethod.POST)
    public @ResponseBody JSONObject register(@RequestBody Map<String,Object> params,HttpServletRequest request){
    	JSONObject jsonObject = new JSONObject();
    	Map<String,Object> map=(Map<String, Object>) params.get("user");
    	User user=JSON.parseObject(JSON.toJSONString(map),User.class);
    	Date dt =new Date(); 
		String formatDate = "";  
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH��ʾ24Сʱ�ƣ�  
	    formatDate = dFormat.format(dt); 
	    String create_time = DateFormat.getDateInstance().format(dt);  
	    user.setCreate_time(formatDate);
	    user.setUser_last_login_time(formatDate);
    	Integer result=userService.addUser(user);
    	if(1==result){
    		request.getSession().setAttribute("user", user);
    		String user1=JSON.toJSONString(user);
    		jsonObject.put("user", user1);
    		return jsonObject;
    	}
    	jsonObject.put("result", result);
		return jsonObject;
    }
    //�޸�����
    @RequestMapping(value="updatePassword", method = RequestMethod.POST)
    public @ResponseBody JSONObject updatePassword(@RequestBody Map<String,Object> params){
    	JSONObject jsonObject = new JSONObject();
    	String user_name=(String) params.get("user_name");
    	String password=(String) params.get("password");
    	Integer result=userService.updatePassword(user_name, password);
    	jsonObject.put("result", result);
		return jsonObject;
    	
    }
   @RequestMapping(value="updateMessage", method = RequestMethod.POST)
   //�޸ĸ�����Ϣ
   public @ResponseBody JSONObject updateMessage(@RequestBody Map<String,Object> params){
    	
	   	JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>) params.get("user");
   		User user1=JSON.parseObject(JSON.toJSONString(map),User.class);
   		Integer result=userService.updateMessage(user1);
   		if(1==result){
   			User user=userService.findUserByUserName(user1.getUser_name());
    		String user2=JSON.toJSONString(user);
    		jsonObject.put("user", user2);
    		return jsonObject;
    	}
   		jsonObject.put("result", result);
	return jsonObject;
    	
   }
    }
