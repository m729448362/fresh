package com.htzhny.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSON;

import com.htzhny.entity.User;
import com.htzhny.service.UserService;

import net.sf.json.JSONObject;

/**
 * 用户控制器
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	Logger log = Logger.getLogger(UserController.class);
	@Autowired(required=true)
    private UserService userService;
	
    @RequestMapping("/view")
    public String view() {
        return "main/login";
    }

    @RequestMapping("/indexview")
    public String index() {
        return "main/index";
    }
    //登录
    @RequestMapping(value="login", method = RequestMethod.POST)
    public @ResponseBody JSONObject login(@RequestBody Map<String,Object> params,HttpServletRequest request) {
    	JSONObject jsonObject = new JSONObject();
    	String user_name=(String) params.get("user_name");
    	String password=(String) params.get("password");
    	User user = userService.findUserByUserName(user_name);
    	if(null!=user){
    		if(password.equals(user.getPassword())){
    			request.getSession().setAttribute("user", user);
    			String result="登录成功";
    			jsonObject.put("result",result);
    			System.out.println("1");
    			String user1=JSON.toJSONString(user);
    			
    			jsonObject.put("user",user1);
    			return jsonObject;
    
    			
    			
    		}
    		String result="密码错误";
    		jsonObject.put("result", result);
    		return jsonObject;
    	}
    	String result="该用户名不存在";
		jsonObject.put("result", result);
		return jsonObject;
    }
    //注册
    @RequestMapping(value="register", method = RequestMethod.POST)
    public @ResponseBody JSONObject register(@RequestBody Map<String,Object> params){
    	JSONObject jsonObject = new JSONObject();
    	Map<String,Object> map=(Map<String, Object>) params.get("user");
    	User user=JSON.parseObject(JSON.toJSONString(map),User.class);
    	Integer result=userService.addUser(user);
    	if(1==result){
    		String user1=JSON.toJSONString(user);
    		jsonObject.put("user", user1);
    		return jsonObject;
    	}
    	jsonObject.put("result", result);
		return jsonObject;
    }
    //修改密码
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
   //修改个人信息
   public @ResponseBody JSONObject updateMessage(@RequestBody Map<String,Object> params){
    	
	   	JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>) params.get("user");
   		User user=JSON.parseObject(JSON.toJSONString(map),User.class);
   		Integer result=userService.updateMessage(user);
   		if(1==result){
    		String user1=JSON.toJSONString(user);
    		jsonObject.put("user", user1);
    		return jsonObject;
    	}
   		jsonObject.put("result", result);
	return jsonObject;
    	
   }
    }
