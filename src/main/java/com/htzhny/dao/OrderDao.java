package com.htzhny.dao;

import java.util.List;

import com.htzhny.entity.Order;
import com.htzhny.entity.OrderQuery;

public interface OrderDao {
	public List<Order> selectUserOrderByStatus( Integer start,Integer size,Integer status,Integer user_id);
	public Integer selectCountByStatus(Integer order_status);
	public List<OrderQuery> selectAllOrderByStatus( Integer start,Integer size,Integer status);
	public Integer addOrder(Order order);
	public Integer updateStatus(Integer status,String id);
	public Integer updateRealPrice(double price,String id);
	public Integer updatePayStatus(Integer pay_status,String id);
	
}
