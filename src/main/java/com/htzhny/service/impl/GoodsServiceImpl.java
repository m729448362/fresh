package com.htzhny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.GoodsDao;
import com.htzhny.entity.After_saleQuery;
import com.htzhny.entity.Goods;
import com.htzhny.entity.PageBean;
import com.htzhny.service.GoodsService;


@Service
@Transactional
public class GoodsServiceImpl implements GoodsService{
	@Resource
	GoodsDao goodsDao;

	@Override
	public Integer addGoods(Goods goods) {
		return goodsDao.addGoods(goods);
	}

	@Override
	public Integer updateGoods(Goods goods) {
		return goodsDao.updateGoods(goods);
	}

	@Override
	@Transactional(readOnly=true)
	public Goods selectGoodsById(Integer id) {
		return goodsDao.selectGoodsById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public PageBean<Goods> selectGoodsByType(Integer currentPage,Integer typeId) {
		PageBean<Goods> pageBean=new PageBean<Goods>();
		//��װ��ǰҳ��
		pageBean.setCurrPage(currentPage);
		//ÿҳ��ʾ������
		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = goodsDao.selectCountByType(typeId);
	    pageBean.setTotalCount(totalCount);
	    //��װ��ҳ��
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//����ȡ��
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //��װÿҳ��ʾ������
        List<Goods> lists = goodsDao.selectGoodsByType(start,size, typeId);
        pageBean.setLists(lists);
		return pageBean;
	
	}

	@Override
	public Integer selectCount() {
		return goodsDao.selectCount();
	}

	@Override
	public Integer selectCountByType(Integer typeId) {
		return goodsDao.selectCountByType(typeId);
	}

	@Override
	public PageBean<Goods> selectAllGoods(Integer currentPage) {
		PageBean<Goods> pageBean=new PageBean<Goods>();
		//��װ��ǰҳ��
		pageBean.setCurrPage(currentPage);
		//ÿҳ��ʾ������
		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = goodsDao.selectCount();
	    pageBean.setTotalCount(totalCount);
	    //��װ��ҳ��
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//����ȡ��
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //��װÿҳ��ʾ������
        List<Goods> lists = goodsDao.selectAllGoods(start, size);
        pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public List<Goods> selectRecommendedGoods() {
		
		return goodsDao.selectRecommendedGoods();
	}

	@Override
	public Integer updateGoodsRecommendedstatus(String goods_id,Integer goods_Recommended) {
		
		return goodsDao.updateGoodsRecommendedstatus(goods_id, goods_Recommended);
	}

	@Override
	public List<Goods> selectAll() {
		return goodsDao.selectAll();
	}

	@Override
	public List<Goods> selectGoodsStatus(Integer good_status) {
		return goodsDao.selectGoodsStatus(good_status);
	}

	@Override
	public Integer updateGoodsStatus(Integer goods_status,Integer goods_id) {
		return goodsDao.updateGoodsStatus(goods_status, goods_id);
	}
}
