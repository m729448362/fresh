package com.htzhny.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htzhny.entity.User;

import net.sf.json.JSONObject;

@Controller
public class TestController {

    @RequestMapping(value = "/user/test", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject CeShi(@RequestBody Map<String,Object> params) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", params.get("name").toString());
        jsonObject.put("pw", params.get("pw").toString());
        return jsonObject;
    }
    @RequestMapping("/user/editItemSubmit_RequestJson")  
    public @ResponseBody JSONObject editItemSubmit_RequestJson(@RequestBody User items) throws Exception {  
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("result", "≤‚ ‘");
    	return jsonObject;  
  
    }  
}
