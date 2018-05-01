package com.htzhny.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.htzhny.entity.Order;
import com.htzhny.entity.Order_item;
import com.htzhny.entity.Order_itemQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.service.OrderService;
import com.htzhny.service.Order_itemService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="order_item")
public class Order_itemController {
	@Autowired
	private Order_itemService order_itemService;
	@RequestMapping(value="addOrder_item")
	//生成订单项
	public @ResponseBody JSONObject addOrder_item(@RequestBody Map<String, Object> params){
		
		
		
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("order_item");
   		Order_item order_item=JSON.parseObject(JSON.toJSONString(map),Order_item.class);
   		Integer result=order_itemService.addOrder_item(order_item);
   		jsonObject.put("result", result);
	return jsonObject;
	}
	@RequestMapping(value="updateRealPrice")
	//报价
	public @ResponseBody JSONObject updateRealPrice(@RequestBody Map<String, Object> params){
		
		
		
		JSONObject jsonObject = new JSONObject();

		Map<String, Object> map= (Map<String, Object>) params.get("itemList");
		
   		List<Order_item> itemList=JSON.parseObject(JSON.toJSONString(map),List.class);
		
		for(Order_item item :itemList){
			Order_item order_item=JSON.parseObject(JSON.toJSONString(item),Order_item.class);
			Integer result =order_itemService.updateRealPrice(order_item);
			jsonObject.put("result", result);
			return jsonObject;
		}
		return jsonObject;
	}
	@RequestMapping(value="selectCount")
	//查询某个订单的订单项总数
	public ModelAndView selectCount(){
		String id="045465";
		Integer num=order_itemService.selectCount(id);
		System.out.println(num);
		return null;
	}
	@RequestMapping(value="selectAllByOrderId")
	//查询某个订单的所有订单项
	public @ResponseBody JSONObject  selectAllByOrderId(@RequestBody Map<String, Object> params){
		
		String id= (String)params.get("id");
		Integer currentPage= (Integer)params.get("currentPage");
		JSONObject jsonObject = new JSONObject();
		PageBean<Order_itemQuery> pageBean =order_itemService.selectAllByOrderId(currentPage, id);
		List<Order_itemQuery> list=pageBean.getLists();
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}

}
