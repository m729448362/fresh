package com.htzhny.service;

import java.util.List;

import com.htzhny.entity.Order;
import com.htzhny.entity.OrderQuery;
import com.htzhny.entity.PageBean;

public interface OrderService{
	public PageBean<Order> selectUserOrderByStatus(Integer currentPage,Integer status,Integer user_id);
	public Integer selectCountByStatus(Integer status);
	public PageBean<OrderQuery> selectAllOrderByStatus( Integer currentPage,Integer status);
	public Integer addOrder(Order order);
	public Integer updateStatus(Integer status,String id);
	public Integer updateRealPrice(double price,String id);
	public Integer updatePayStatus(Integer pay_status,String id);
}
