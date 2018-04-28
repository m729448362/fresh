package com.htzhny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.Order_itemDao;
import com.htzhny.entity.Order;
import com.htzhny.entity.Order_item;
import com.htzhny.entity.Order_itemQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.service.Order_itemService;
@Service
@Transactional
public class Order_itemServiceImpl implements Order_itemService{
	@Autowired
	private Order_itemDao order_itemDao;
	@Override
	public Integer addOrder_item(Order_item item) {
		return order_itemDao.addOrder_item(item);
	}
	@Override
	public Integer updateRealPrice(double price,String id) {
		return order_itemDao.updateRealPrice(price, id);
	}
	  @Override
	
	public PageBean<Order_itemQuery> selectAllByOrderId(Integer currentPage,String id) {
		PageBean<Order_itemQuery> pageBean=new PageBean<Order_itemQuery>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = order_itemDao.selectCount(id);
	    pageBean.setTotalCount(totalCount);
	    //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //封装每页显示的数据
        List<Order_itemQuery> lists = order_itemDao.selectAllByOrderId(start, size, id);
        pageBean.setLists(lists);
		return pageBean;
	}
	
	@Override
	public Integer selectCount(String id) {
		return order_itemDao.selectCount(id);
	}
	@Override
	public List<Order_item> selectAllItem(String id) {
		return order_itemDao.selectAllItem(id);
	}


}
