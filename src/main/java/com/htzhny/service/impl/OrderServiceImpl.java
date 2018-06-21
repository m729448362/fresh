package com.htzhny.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.BillDao;
import com.htzhny.dao.OrderDao;
import com.htzhny.dao.OrderLogDao;
import com.htzhny.entity.Bill;
import com.htzhny.entity.Goods;
import com.htzhny.entity.Order;
import com.htzhny.entity.OrderLog;
import com.htzhny.entity.OrderQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.service.OrderService;
import com.htzhny.service.Order_logService;



@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	private int pageSize =10;//ÿҳ��ʾ10����¼
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private BillDao billDao;
	@Autowired
	private OrderLogDao logDao;
	@Override
	@Transactional(readOnly=true)
	public PageBean<OrderQuery> selectUserOrderByStatus(Integer currentPage, Integer status,Integer user_id) {
		PageBean<OrderQuery> pageBean=new PageBean<OrderQuery>();
		//��װ��ǰҳ��
		pageBean.setCurrPage(currentPage);
		//ÿҳ��ʾ������
//		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = orderDao.selectCountByUserByStatus(status, user_id);
	    pageBean.setTotalCount(totalCount);
	    //��װ��ҳ��
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//����ȡ��
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //��װÿҳ��ʾ������
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
		//��װ��ǰҳ��
		pageBean.setCurrPage(currentPage);
		//ÿҳ��ʾ������
//		int pageSize=2;
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
	public PageBean<OrderQuery> selectUserOrderByBillStatus(Integer currentPage, Integer bill_status, Integer user_id) {
		PageBean<OrderQuery> pageBean=new PageBean<OrderQuery>();
		//��װ��ǰҳ��
		pageBean.setCurrPage(currentPage);
		//ÿҳ��ʾ������
//		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = orderDao.selectCountByUserByBillStatus(bill_status, user_id);
	    pageBean.setTotalCount(totalCount);
	    //��װ��ҳ��
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//����ȡ��
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //��װÿҳ��ʾ������
        List<OrderQuery> lists = orderDao.selectUserOrderByBillStatus(start, size, bill_status, user_id);
        pageBean.setLists(lists);
		return pageBean;
	}
	@Override
	public PageBean<OrderQuery> selectAllOrderByBillStatus(Integer currentPage, Integer bill_status) {
		PageBean<OrderQuery> pageBean=new PageBean<OrderQuery>();
		//��װ��ǰҳ��
		pageBean.setCurrPage(currentPage);
		//ÿҳ��ʾ������
//		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = orderDao.selectCountByBillStatus(bill_status);
	    pageBean.setTotalCount(totalCount);
	    //��װ��ҳ��
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//����ȡ��
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //��װÿҳ��ʾ������
        List<OrderQuery> lists = orderDao.selectAllOrderByBillStatus(start, size, bill_status);
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
		if(status==6){
			Order order=orderDao.selectOneOrderById(id);
			double real_price=order.getOrder_real_price();
			Integer user_id=order.getUser_id();
			Bill bill=billDao.selectBillByUserId(user_id,1);
			double month_pay_money=bill.getMonth_pay_money()+real_price;
			billDao.updateMonthPayMoney(month_pay_money,user_id);
		}
		Date dt =new Date(); 
		String formatDate = "";  
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH��ʾ24Сʱ�ƣ�  
	    formatDate = dFormat.format(dt); 
	    if(status==3){
   			OrderLog orderlog=new OrderLog(id,formatDate,2);
   			logDao.addLog(orderlog);
   		}else if(status==4){
   			if(logDao.findOrderLog(id, 4)!=null){
   				OrderLog orderlog=new OrderLog(id,formatDate,9);
   	   			logDao.addLog(orderlog);
   			}else{
   			OrderLog orderlog=new OrderLog(id,formatDate,4);
   			logDao.addLog(orderlog);}
   		}else if(status==6){
   			OrderLog orderlog=new OrderLog(id,formatDate,7);
   			logDao.addLog(orderlog);
   		}
		return orderDao.updateStatus(status,id);
	}

	@Override
	public Integer updateRealPrice(double price, String id) {
		return orderDao.updateRealPrice(price, id);
	}



	@Override
	public Integer selectCountByUserByStatus(Integer order_status, Integer user_id) {
		return orderDao.selectCountByUserByStatus(order_status, user_id);
	}

	@Override
	public Order selectOneOrderById(String id) {
		return orderDao.selectOneOrderById(id);
	}

	@Override
	public Integer updatePayStatusByUser(Integer user_id) {
		return orderDao.updatePayStatusByUser(user_id);
	}

	@Override
	public List<OrderQuery> selectUserOrderByStatusNoPage(Integer status, Integer user_id) {
		return orderDao.selectUserOrderByStatusNoPage(status, user_id);
	}

	@Override
	public List<OrderQuery> selectUserOrder(Integer user_id) {
		return orderDao.selectUserOrder(user_id);
	}






	
		
	


}