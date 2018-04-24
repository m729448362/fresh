package com.htzhny.entity;

import java.util.ArrayList;
import java.util.List;



public class BuyCart {
	List<BuyItem> items = new ArrayList<BuyItem>();
	//��ӹ�����
	public void addItem(BuyItem item){
		if(items.contains(item)){
			for(BuyItem it:items){
				if(it.equals(item)){
					Integer result=it.getAmount()+item.getAmount();
					it.setAmount(result);
				}
			}
		}else{
			items.add(item);
		}
		
	}
	//ɾ��һ��
	public void deleteItem(BuyItem item){
		items.remove(item);
	}
	//��Ʒ����
	public Double getGoodsAmount(){
		double result=0;
		for(BuyItem item:items){
			result+=item.getAmount();
		}
		return result;
	}
	//�õ��ܹ���Ʒ����ͼ�
	public Double getGoodsTotalLowPrice(){
		double totalLowPrice=0.00;
		for(BuyItem item:items){
			totalLowPrice+=item.getGoods().getGoods_low_price()*item.getAmount();
		}
		return totalLowPrice;
	}
	//�õ��ܹ���Ʒ����߼�
	public Double getGoodsTotalHighPrice(){
		double totalHighPrice=0.00;
		for(BuyItem item:items){
			totalHighPrice+=item.getGoods().getGoods_high_price()*item.getAmount();
		}
		return totalHighPrice;
	}
	//��չ��ﳵ
	public void clearCart(){
		items.clear();
	}
	public List<BuyItem> getItems() {
		return items;
	}
	public void setItems(List<BuyItem> items) {
		this.items = items;
	}
}
