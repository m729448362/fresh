package com.htzhny.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.htzhny.entity.Address;
import com.htzhny.entity.After_sale;
import com.htzhny.entity.After_saleQuery;
import com.htzhny.entity.Order_item;
import com.htzhny.entity.Order_itemAndAfter_saleQuery;
import com.htzhny.entity.Order_itemQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.entity.User;
import com.htzhny.service.After_saleService;
import com.htzhny.service.OrderService;
import com.htzhny.service.Order_itemService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/after_sale")
public class After_saleController {
	@Autowired
	private After_saleService after_saleService;
	@Autowired
	private Order_itemService order_itemService;
	
	//增加售后记录
    @RequestMapping(value="addAfter_sale", method = RequestMethod.POST)
    public @ResponseBody JSONObject addAfter_sale(@RequestBody Map<String, Object> params) {
    
    	JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("after_sale");
   		After_sale after_sale=JSON.parseObject(JSON.toJSONString(map),After_sale.class);
   		Integer result=after_saleService.addAfter_sale(after_sale);
   		jsonObject.put("result", result);
	return jsonObject;
    }
    //修改售后记录
    @RequestMapping(value="updateAfter_sale", method = RequestMethod.POST)
    public @ResponseBody JSONObject updateAfter_sale(@RequestBody Map<String, Object> params){
    	
    	JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("after_sale");
   		After_sale after_sale=JSON.parseObject(JSON.toJSONString(map),After_sale.class);
   		Integer result=after_saleService.updateAfter_sale(after_sale);
   		jsonObject.put("result", result);
	return jsonObject;
    }
    //查询用户记录总数
    @RequestMapping(value="selectCountByUser", method = RequestMethod.POST)
    public @ResponseBody JSONObject selectCountByUser(@RequestBody Map<String, Integer> params){
    	JSONObject jsonObject = new JSONObject();
    	Integer user_id= params.get("user_id");
    	Integer num=after_saleService.selectCountByUser(user_id);
    	jsonObject.put("num", num);
    	return jsonObject;
    }
    @RequestMapping(value="selectCount", method = RequestMethod.POST)
    //查询总记录数
    public@ResponseBody JSONObject  selectCount(@RequestBody Map<String, Integer> params){
    	JSONObject jsonObject = new JSONObject();
    	Integer num=after_saleService.selectCount();
    	jsonObject.put("num", num);
    	return jsonObject;
    }
    //分页查询(用户)
    @RequestMapping(value="findByPage", method = RequestMethod.POST)
    public @ResponseBody JSONObject findByPage(@RequestBody Map<String, Object> params){
    	JSONObject jsonObject = new JSONObject();
    	
    	Integer currentPage= (Integer) params.get("currentPage");
    	Integer user_id= (Integer) params.get("user_id");
    	
    	PageBean<After_saleQuery> pageBean=after_saleService.findByPage(currentPage, user_id);
    	List<After_saleQuery> list=pageBean.getLists();
    	String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
    	
    }
    //管理员查询售后事件
    @RequestMapping(value="findByPageByAdmin", method = RequestMethod.POST)
    public @ResponseBody JSONObject  findByPageByAdmin(@RequestBody Map<String, Object> params){
    	JSONObject jsonObject = new JSONObject();
    	Integer currentPage= (Integer) params.get("currentPage");
    	PageBean<After_saleQuery> pageBean=after_saleService.findByPageByAdmin(currentPage);

    	List<After_saleQuery> list=pageBean.getLists();
    	String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
    }
    //管理员通过时间查询售后事件
    @RequestMapping(value="findByPageByTime", method = RequestMethod.POST)
    public @ResponseBody JSONObject  findByPageByTime(@RequestBody Map<String, Object> params){
    	JSONObject jsonObject = new JSONObject();
    	int currentPage=(int) params.get("currentPage");
    	String time=(String) params.get("delivery_time");
    	
    	PageBean<After_saleQuery> pageBean=after_saleService.findByPageByTime(currentPage, time);
    	List<After_saleQuery> list=pageBean.getLists();
    	String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
    }
    //通过售后事件id和订单id查询一个订单详情及对应的售后事件详情
    @RequestMapping(value="selectOneById", method = RequestMethod.POST)
    public @ResponseBody JSONObject selectOneById(@RequestBody Map<String, Object> params){
    	
    	JSONObject jsonObject = new JSONObject();
    	int currentPage=(int) params.get("currentPage");
    	String order_id=(String) params.get("order_id");
    	PageBean<Order_itemAndAfter_saleQuery> pagebean =after_saleService.selectOneById(currentPage,order_id);
    	List<Order_itemAndAfter_saleQuery> list=pagebean.getLists();

    	jsonObject.put("list",list);
    	
    	return jsonObject;
    }
}
