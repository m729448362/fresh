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
	public PageBean<Order> selectUserOrderByStatus(Integer currentPage, Integer status,Integer user_id) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//��װ��ǰҳ��
		pageBean.setCurrPage(currentPage);
		//ÿҳ��ʾ������
		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = orderDao.selectCountByStatus(status);
	    pageBean.setTotalCount(totalCount);
	    //��װ��ҳ��
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//����ȡ��
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //��װÿҳ��ʾ������
        List<Order> lists = orderDao.selectUserOrderByStatus(start, size, status,user_id);
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
		//��װ��ǰҳ��
		pageBean.setCurrPage(currentPage);
		//ÿҳ��ʾ������
		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = orderDao.selectCountByStatus(status);
	    pageBean.setTotalCount(totalCount);
	    //��װ��ҳ��
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//����ȡ��
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //��װÿҳ��ʾ������
        List<OrderQuery> lists = orderDao.selectAllOrderByStatus(start, size, status);
        pageBean.setLists(lists);
		return pageBean;
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
	public Integer updatePayStatus(Integer pay_status, String id) {
		
		return orderDao.updatePayStatus(pay_status, id);
	}

	
		
	


}
