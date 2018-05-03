package com.htzhny.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.htzhny.entity.Address;
import com.htzhny.entity.BuyCart;
import com.htzhny.entity.BuyItem;
import com.htzhny.entity.Goods;
import com.htzhny.entity.Order;
import com.htzhny.entity.Order_item;
import com.htzhny.entity.User;
import com.htzhny.service.AddressService;
import com.htzhny.service.GoodsService;
import com.htzhny.service.OrderService;
import com.htzhny.service.Order_itemService;
import com.htzhny.service.UserService;
import com.htzhny.util.CartItemUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="buyCart")
public class BuyCartController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private Order_itemService order_itemService;
	
	//加入购物车
	@RequestMapping(value="addGoods")
	public @ResponseBody JSONObject addGoods(@RequestBody Map<String, Object> params,HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		
		String str= (String)params.get("goodsId");
		Integer goods_id=Integer.parseInt(str);
		Integer amount= (Integer)params.get("amount");
		
		BuyCart buyCart=null;
		
		if(null!=request.getSession().getAttribute("buyCart")){
			buyCart=(BuyCart) request.getSession().getAttribute("buyCart");
		}
		if(null==buyCart){
			buyCart=new BuyCart();
		}
		BuyItem buyItem=new BuyItem();
		buyItem.setAmount(amount);
		Goods goods=goodsService.selectGoodsById(goods_id);
		buyItem.setGoods(goods);
		buyCart.addItem(buyItem);
		
		
		request.getSession().setAttribute("buyCart", buyCart);
		jsonObject.put("buyCart",buyCart);
		return jsonObject;
	}
	//清空购物车
	@RequestMapping(value="clearBuyCart")
	public  @ResponseBody JSONObject clearBuyCart(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		BuyCart buyCart=(BuyCart) request.getSession().getAttribute("buyCart");
		
		if(null!=buyCart){
			buyCart.getItems().clear();
			
			jsonObject.put("result","清空购物车成功！");
			return jsonObject;
		}
		
		return null;
	}
	//删除一个购物项
	@RequestMapping(value="deleteItem")
	public @ResponseBody JSONObject deleteItem(@RequestBody Map<String, Object> params,HttpServletRequest request ){
		JSONObject jsonObject = new JSONObject();
		Integer goods_id= (Integer)params.get("goods_id");
		BuyCart buyCart=(BuyCart) request.getSession().getAttribute("buyCart");
		if(null!=buyCart){
			Goods goods=new Goods();
			goods.setId(goods_id);
			BuyItem buyItem=new BuyItem();
			buyItem.setGoods(goods);
			buyCart.deleteItem(buyItem);
			
			jsonObject.put("result","删除成功！");
			return jsonObject;
		}
		return null;
	}
	//查询购物车中的所有商品
	@RequestMapping(value="selectBuyCart")
	public @ResponseBody JSONObject selectBuyCart(HttpServletRequest request ){
		JSONObject jsonObject = new JSONObject();
		BuyCart buyCart=(BuyCart) request.getSession().getAttribute("buyCart");
		if(null!=buyCart){
			jsonObject.put("buyCart",buyCart);
			return jsonObject;
		}else{
			jsonObject.put("result","buyCartIsNull");
		return jsonObject;
		}
	}
	//结算（生成订单）
		@RequestMapping(value="createOrder")
		public @ResponseBody JSONObject createOrder(@RequestBody Map<String, Object> params,HttpServletRequest request ){
			JSONObject jsonObject = new JSONObject();
			String uuid=UUID.randomUUID().toString();
			User user=(User) request.getSession().getAttribute("user");
			Integer user_id=user.getId();
			String id=user_id.toString();
			
			String order_id=id+uuid;
			jsonObject.put("order_id",order_id);
			List<Address> list=addressService.findAddressByUserId(user_id);
			jsonObject.put("Address",list);
			List<CartItemUtil> list1=(List<CartItemUtil>)params.get("list");
			BuyCart buyCart=(BuyCart) request.getSession().getAttribute("buyCart");
			List<BuyItem> items=buyCart.getItems();
			for(int i=0;i<list1.size();i++){
				CartItemUtil cartItem=JSON.parseObject(JSON.toJSONString(list1.get(i)),CartItemUtil.class);
				for(BuyItem item:items){
					if(item.getGoods().getId()==cartItem.getGoods_id()){
						item.setAmount(cartItem.getAmount());
					}
				}
			}
				
			
			request.getSession().setAttribute("buyCart", buyCart);
			return jsonObject;
		}
		//提交订单、、、、、、、、
		@RequestMapping(value="submitOrder")
		public @ResponseBody JSONObject submitOrder(@RequestBody Map<String, Object> params,HttpServletRequest request ){
			JSONObject jsonObject = new JSONObject();
			BuyCart buyCart=(BuyCart) request.getSession().getAttribute("buyCart");
			if(null==buyCart){
					return null;
							}
			Date dt =new Date(); 
			String formatDate = "";  
			DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；  
		    formatDate = dFormat.format(dt); 
		    String create_time = DateFormat.getDateInstance().format(dt);  
			String order_id= (String) params.get("order_id");
			String delivery_time=(String) params.get("delivery_time");
			String address=(String) params.get("address");
			User user=(User) request.getSession().getAttribute("user");
			Integer user_id=user.getId();
			Double order_low_price=buyCart.getGoodsTotalLowPrice();
			Double order_High_price=buyCart.getGoodsTotalHighPrice();
			Integer order_status=1;
			Integer pay_status=0;
			Order order=new Order(order_id,address,delivery_time,order_low_price,order_High_price,0.00,order_status,create_time,user_id,pay_status);
			orderService.addOrder(order);
			
			List<BuyItem>list=buyCart.getItems();
			for(BuyItem item:list){
			String id=UUID.randomUUID().toString();
			Goods goods=item.getGoods();
			Integer goods_id=goods.getId();
			Integer goods_amount=item.getAmount();
			
			Order_item order_item=new Order_item(id,goods_id,order_id,goods_amount,0.00);
			order_itemService.addOrder_item(order_item);
			
			}
			buyCart.clearCart();
			jsonObject.put("result","提交订单成功");
			return jsonObject;
				}
}
