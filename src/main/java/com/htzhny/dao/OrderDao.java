package com.htzhny.dao;

import java.util.List;

import com.htzhny.entity.Order;
import com.htzhny.entity.OrderQuery;

public interface OrderDao {
	public List<OrderQuery> selectUserOrderByStatus( Integer start,Integer size,Integer status,Integer user_id);
	public Integer selectCountByStatus(Integer order_status);
	public Integer selectCountByUserByStatus(Integer order_status,Integer user_id);
	public List<OrderQuery> selectAllOrderByStatus( Integer start,Integer size,Integer status);
	public Integer addOrder(Order order);
	public Integer updateStatus(Integer status,String id);
	public Integer updateRealPrice(double price,String id);
	public Integer updatePayStatus(Integer pay_status,String id);
	public List<OrderQuery> selectUserOrderByPayStatus(Integer start,Integer size,Integer pay_status,Integer user_id);
	public List<OrderQuery> selectAllOrderByPayStatus(Integer start,Integer size,Integer pay_status);
	public Integer selectCountByUserByPayStatus(Integer pay_status,	Integer user_id);
	public Integer selectCountByPayStatus(Integer pay_status);
	public Order selectOneOrderById(String id);
	
}
