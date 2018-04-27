package com.htzhny.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.OrderDao;
import com.htzhny.entity.Goods;
import com.htzhny.entity.Order;
import com.htzhny.entity.OrderQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.service.OrderService;



@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDao orderDao;

	@Override
	@Transactional(readOnly=true)
	public PageBean<OrderQuery> selectUserOrderByStatus(Integer currentPage, Integer status,Integer user_id) {
		PageBean<OrderQuery> pageBean=new PageBean<OrderQuery>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = orderDao.selectCountByUserByStatus(status, user_id);
	    pageBean.setTotalCount(totalCount);
	    //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //封装每页显示的数据
        List<OrderQuery> lists = orderDao.selectUserOrderByStatus(start, size, status,user_id);
        pageBean.setLists(lists);
		return pageBean;
		
	}

	@Override
	public Integer selectCountByStatus(Integer status) {
		
		return orderDao.selectCountByStatus(status);
	}

	@Override
	@Transactional(readOnly=true)
	public PageBean<OrderQuery> selectAllOrderByStatus(Integer currentPage, Integer status) {
		PageBean<OrderQuery> pageBean=new PageBean<OrderQuery>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = orderDao.selectCountByStatus(status);
	    pageBean.setTotalCount(totalCount);
	    //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //封装每页显示的数据
        List<OrderQuery> lists = orderDao.selectAllOrderByStatus(start, size, status);
        pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public PageBean<OrderQuery> selectUserOrderByPayStatus(Integer currentPage, Integer pay_status, Integer user_id) {
		PageBean<OrderQuery> pageBean=new PageBean<OrderQuery>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = orderDao.selectCountByUserByPayStatus(pay_status, user_id);
	    pageBean.setTotalCount(totalCount);
	    //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //封装每页显示的数据
        List<OrderQuery> lists = orderDao.selectUserOrderByPayStatus(start, size, pay_status, user_id);
        pageBean.setLists(lists);
		return pageBean;
	}
	@Override
	public PageBean<OrderQuery> selectAllOrderByPayStatus(Integer currentPage, Integer pay_status) {
		PageBean<OrderQuery> pageBean=new PageBean<OrderQuery>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = orderDao.selectCountByPayStatus(pay_status);
	    pageBean.setTotalCount(totalCount);
	    //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //封装每页显示的数据
        List<OrderQuery> lists = orderDao.selectAllOrderByPayStatus(start, size, pay_status);
        pageBean.setLists(lists);
		return pageBean;
	}
	@Override
	public Integer updatePayStatus(Integer pay_status, String id) {
		
		return orderDao.updatePayStatus(pay_status, id);
	}

	



	@Override
	public Integer addOrder(Order order) {
		return orderDao.addOrder(order);
	}

	@Override
	public Integer updateStatus(Integer status,String id) {
		
		return orderDao.updateStatus(status,id);
	}

	@Override
	public Integer updateRealPrice(double price, String id) {
		return orderDao.updateRealPrice(price, id);
	}

	@Override
	public Integer selectCountByUserByPayStatus(Integer pay_status, Integer user_id) {
		return orderDao.selectCountByUserByPayStatus(pay_status, user_id);
	}

	@Override
	public Integer selectCountByPayStatus(Integer pay_status) {
		return orderDao.selectCountByPayStatus(pay_status);
	}

	@Override
	public Integer selectCountByUserByStatus(Integer order_status, Integer user_id) {
		return orderDao.selectCountByUserByStatus(order_status, user_id);
	}

	@Override
	public Order selectOneOrderById(String id) {
		return orderDao.selectOneOrderById(id);
	}






	
		
	


}
