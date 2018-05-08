package com.htzhny.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.htzhny.entity.OrderLog;
import com.htzhny.entity.Order_item;
import com.htzhny.entity.Order_itemAndAfter_saleQuery;
import com.htzhny.entity.Order_itemQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.entity.User;
import com.htzhny.service.After_saleService;
import com.htzhny.service.OrderService;
import com.htzhny.service.Order_itemService;
import com.htzhny.service.Order_logService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/after_sale")
public class After_saleController {
	@Autowired
	private After_saleService after_saleService;
	@Autowired
	private Order_itemService order_itemService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private Order_logService logService;
	
	//�����ۺ��¼
    @RequestMapping(value="addAfter_sale", method = RequestMethod.POST)
    public @ResponseBody JSONObject addAfter_sale(@RequestBody Map<String, Object> params) {
    
    	JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("after_sale");
   		After_sale after_sale=JSON.parseObject(JSON.toJSONString(map),After_sale.class);
   		String uuid=UUID.randomUUID().toString();
   		Integer user_id=after_sale.getUser_id();
   		String after_sale_id=user_id+uuid;
   		after_sale.setAfter_sale_id(after_sale_id);
   		Integer result=after_saleService.addAfter_sale(after_sale);
   		if(result!=0){
   			String order_id=after_sale.getA_order_id();
   			orderService.updateStatus(5,order_id );
   		}
   		String order_id=after_sale.getA_order_id();
   		Date dt =new Date(); 
		String formatDate = "";  
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH��ʾ24Сʱ�ƣ�  
	    formatDate = dFormat.format(dt); 
	    
   		OrderLog orderlog=new OrderLog(order_id,formatDate,5);
   		logService.addLog(orderlog);
   		jsonObject.put("result", result);
	return jsonObject;
    }
    //�޸��ۺ��¼
    @RequestMapping(value="updateAfter_sale", method = RequestMethod.POST)
    public @ResponseBody JSONObject updateAfter_sale(@RequestBody Map<String, Object> params){
    	
    	JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("after_sale");
   		After_sale after_sale=JSON.parseObject(JSON.toJSONString(map),After_sale.class);
   		Date dt =new Date(); 
		String formatDate = "";  
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH��ʾ24Сʱ�ƣ�  
	    formatDate = dFormat.format(dt);
	    after_sale.setHandle_time(formatDate);
	    String order_id=after_sale.getA_order_id();
	   
   		Integer result=after_saleService.updateAfter_sale(after_sale);
   		if(result!=0){
   			orderService.updateStatus(6, order_id);
   		}
   		OrderLog orderlog=new OrderLog(order_id,formatDate,6);
   		logService.addLog(orderlog);
   		OrderLog orderlog1=new OrderLog(order_id,formatDate,7);
   		logService.addLog(orderlog1);
   		jsonObject.put("result", result);
	return jsonObject;
    }
    //��ѯ�û���¼����
    @RequestMapping(value="selectCountByUser", method = RequestMethod.POST)
    public @ResponseBody JSONObject selectCountByUser(@RequestBody Map<String, Integer> params){
    	JSONObject jsonObject = new JSONObject();
    	Integer user_id= params.get("user_id");
    	Integer num=after_saleService.selectCountByUser(user_id);
    	jsonObject.put("num", num);
    	return jsonObject;
    }
    @RequestMapping(value="selectCount", method = RequestMethod.POST)
    //��ѯ�ܼ�¼��
    public@ResponseBody JSONObject  selectCount(@RequestBody Map<String, Integer> params){
    	JSONObject jsonObject = new JSONObject();
    	Integer num=after_saleService.selectCount();
    	jsonObject.put("num", num);
    	return jsonObject;
    }
    //��ҳ��ѯ(�û�)
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
    //����Ա��ѯ�ۺ��¼�
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
    //����Աͨ��ʱ���ѯ�ۺ��¼�
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
    //ͨ���ۺ��¼�id�Ͷ���id��ѯһ���������鼰��Ӧ���ۺ��¼�����
    @RequestMapping(value="selectOneById", method = RequestMethod.POST)
    public @ResponseBody JSONObject selectOneById(@RequestBody Map<String, Object> params){
    	
    	JSONObject jsonObject = new JSONObject();
    	int currentPage=(int) params.get("currentPage");
    	String order_id=(String) params.get("order_id");
    	PageBean<Order_itemAndAfter_saleQuery> pagebean =after_saleService.selectOneById(currentPage,order_id);
    	List<Order_itemAndAfter_saleQuery> list=pagebean.getLists();
    	List<OrderLog> orderLogList=logService.findOneOrderLog(order_id);
    	if(orderLogList.size()>0){
    		jsonObject.put("orderLogList",orderLogList);
    	}
    	jsonObject.put("list",list);
    	
    	return jsonObject;
    }
}
