package com.htzhny.controller;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSON;
import com.htzhny.entity.Goods;
import com.htzhny.entity.Order;
import com.htzhny.entity.OrderQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.entity.User;
import com.htzhny.service.OrderService;
import com.htzhny.service.Order_itemService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private Order_itemService order_itemService;
	@RequestMapping(value="selectCountByStatus", method = RequestMethod.POST)
	//��ѯĳ������״̬������
	public  @ResponseBody JSONObject selectCountByStatus(@RequestBody Map<String, Object> params){
		int status=1;
		Integer num=orderService.selectCountByStatus(status);
		System.out.println(num);
		return null;
	}
	@RequestMapping(value="selectUserOrderByStatus", method = RequestMethod.POST)
	//ͨ��״̬��ѯĳ���û������ж���
	public @ResponseBody JSONObject selectUserOrderByStatus(@RequestBody Map<String, Object> params,HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		Integer currentPage= (Integer)params.get("currentPage");
    	Integer user_id= (Integer)params.get("user_id");
    	if(user_id==0){
    		User user=(User) request.getSession().getAttribute("user");
			user_id=user.getId();
    	}
    	Integer status= (Integer)params.get("status");
		PageBean<Order> pageBean =orderService.selectUserOrderByStatus(currentPage, status,user_id);
		List<Order> list=pageBean.getLists();
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}
	
	@RequestMapping(value="selectAllOrderByStatus", method = RequestMethod.POST)
	//ͨ��״̬��ѯ�����û������ж���
	public @ResponseBody JSONObject selectAllOrderByStatus(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		
		Integer currentPage= (Integer)params.get("currentPage");
		Integer status= (Integer)params.get("status");
		PageBean<OrderQuery> pageBean =orderService.selectAllOrderByStatus(currentPage, status);
		List<OrderQuery> list=pageBean.getLists();
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}
	@RequestMapping(value="addOrder", method = RequestMethod.POST)
	//���ɶ���(���ﳵ����)
	public @ResponseBody JSONObject addOrder(@RequestBody Map<String, Object> params){
		
		
		
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("order");
   		Order order=JSON.parseObject(JSON.toJSONString(map),Order.class);
   		Integer result=orderService.addOrder(order);
   		jsonObject.put("result", result);
	return jsonObject;
	}
	@RequestMapping(value="updateStatus", method = RequestMethod.POST)
	//�޸Ķ���״̬
	public @ResponseBody JSONObject updateStatus(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();

		Integer status= (Integer)params.get("status");
		String id= (String)params.get("id");
		Integer result=orderService.updateStatus(status, id);
		jsonObject.put("result", result);
		return jsonObject;
	}
	@RequestMapping(value="updateRealPrice", method = RequestMethod.POST)
	//����
	public @ResponseBody JSONObject updateRealPrice(@RequestBody Map<String, Object> params){
		
		
		JSONObject jsonObject = new JSONObject();

		String order_id= (String)params.get("order_id");
		List<Double> list=order_itemService.selectAllItemRealPrice( order_id);
		Double orderRealPrice=0.00;
		for(Double realPrice:list){
			orderRealPrice+=realPrice;
		}
		BigDecimal bg=new BigDecimal(orderRealPrice);
		double  orderRealPriceFormat= bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		jsonObject.put("orderRealPriceFormat", orderRealPriceFormat);
		return jsonObject;
		
	}
	@RequestMapping(value="updatePayStatus", method = RequestMethod.POST)
	//�޸Ķ���֧��״̬
	public @ResponseBody JSONObject updatePayStatus(@RequestBody Map<String, Object> params){
		
		JSONObject jsonObject = new JSONObject();
		String order_id= (String)params.get("order_id");
		Integer pay_status= (Integer)params.get("pay_status");
		Integer result=orderService.updatePayStatus(pay_status, order_id);
		jsonObject.put("result", result);
		return jsonObject;
	}
}
