package com.htzhny.controller;

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
import com.htzhny.entity.After_sale;
import com.htzhny.entity.After_saleQuery;
import com.htzhny.entity.Goods;
import com.htzhny.entity.PageBean;
import com.htzhny.service.GoodsService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@RequestMapping(value="addGoods", method = RequestMethod.POST)
	//�����Ʒ
	public  @ResponseBody JSONObject addGoods(@RequestBody Map<String, Object> params){
		
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("goods");
   		Goods goods=JSON.parseObject(JSON.toJSONString(map),Goods.class);
   		Integer result=goodsService.addGoods(goods);
   		jsonObject.put("result", result);
	return jsonObject;
	}
	@RequestMapping(value="updateGoods", method = RequestMethod.POST)
	//�޸���Ʒ
	public @ResponseBody JSONObject updateGoods(@RequestBody Map<String, Object> params){
		
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("goods");
   		Goods goods=JSON.parseObject(JSON.toJSONString(map),Goods.class);
   		Integer result=goodsService.updateGoods(goods);
   		jsonObject.put("result", result);
	return jsonObject;
	}
	@RequestMapping(value="selectCount", method = RequestMethod.POST)
	//��ѯ��Ʒ����
	public ModelAndView selectCount(){
		Integer num=goodsService.selectCount();
		System.out.println(num);
		return null;
	}
	@RequestMapping(value="selectCountByType", method = RequestMethod.POST)
	//��ѯĳ�������Ʒ����
	public ModelAndView selectCountByType(){
		Integer typeId=1;
		Integer num=goodsService.selectCountByType(1);
		System.out.println(num);
		return null;
	}
	@RequestMapping(value="selectGoodsById", method = RequestMethod.POST)
	//����id��ѯ��Ʒ
	public @ResponseBody JSONObject selectGoodsById(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
    	Integer goods_id= (Integer) params.get("id");
    	Goods goods=goodsService.selectGoodsById(goods_id);
    	jsonObject.put("goods", goods);
    	return jsonObject;
	}
	@RequestMapping(value="selectGoodsByType", method = RequestMethod.POST)
	//������Ʒ���Ͳ�ѯ��Ʒ
	public @ResponseBody JSONObject selectGoodsByType(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
    	
    	Integer currentPage= (Integer)params.get("currentPage");
    	String str= (String)params.get("goods_type");
    	Integer typeId=Integer.parseInt(str);
    	PageBean<Goods> pageBean=goodsService.selectGoodsByType(currentPage, typeId);
    	List<Goods> list=pageBean.getLists();
    	String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}
	@RequestMapping(value="selectAllGoods", method = RequestMethod.POST)
	//��ѯ������Ʒ
	public @ResponseBody JSONObject selectAllGoods(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		Integer currentPage= (Integer)params.get("currentPage");
		PageBean<Goods> pageBean=goodsService.selectAllGoods(currentPage);
    	List<Goods> list=pageBean.getLists();
    	String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}
	@RequestMapping(value="selectRecommendedGoods")
	//��ѯ�Ƽ���Ʒ
	public @ResponseBody JSONObject  selectRecommendedGoods(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		
		List<Goods> list=goodsService.selectRecommendedGoods();
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
		
	}
	@RequestMapping(value="updateGoodsRecommendedstatus")
	//�޸��Ƽ���Ʒ
	public @ResponseBody JSONObject  updateGoodsRecommendedstatus(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		String goods_id=(String) params.get("goods_id");
		Integer goods_Recommended=(Integer) params.get("goods_Recommended");
		Integer result=goodsService.updateGoodsRecommendedstatus(goods_id, goods_Recommended);
		jsonObject.put("result", result);
		return jsonObject;
	}
	@RequestMapping(value="selectAll")
	//��ѯ������Ʒ������ҳ��
	public @ResponseBody JSONObject selectAll(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		
		List<Goods> list=goodsService.selectAll();
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}
}
