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
	private int pageSize =10;//ÿҳ��ʾ10����¼
	@Autowired
	private Order_itemDao order_itemDao;
	@Override
	public Integer addOrder_item(Order_item item) {
		return order_itemDao.addOrder_item(item);
	}
	@Override
	public Integer updateRealPrice(Order_item item) {
		return order_itemDao.updateRealPrice(item);
	}
	  @Override
	
	public PageBean<Order_itemQuery> selectAllByOrderId(Integer currentPage,String id) {
		PageBean<Order_itemQuery> pageBean=new PageBean<Order_itemQuery>();
		//��װ��ǰҳ��
		pageBean.setCurrPage(currentPage);
		//ÿҳ��ʾ������
//		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = order_itemDao.selectCount(id);
	    pageBean.setTotalCount(totalCount);
	    //��װ��ҳ��
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//����ȡ��
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //��װÿҳ��ʾ������
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
	@Override
	public Integer deleteOrderItem(String id) {
		
		return order_itemDao.deleteOrderItem(id);
	}
	@Override
	public Integer addCompleteOrderItem(Order_item item) {
		return order_itemDao.addCompleteOrderItem(item);
	}
	@Override
	public Integer updateAfterSale(Integer is_after_sale,String id) {
		return order_itemDao.updateAfterSale(is_after_sale,id);
	}




}