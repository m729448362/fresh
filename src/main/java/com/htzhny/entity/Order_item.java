package com.htzhny.entity;
/**
 * 
 * @author mEssA9e
 *������
 */
public class Order_item {
	private String id;
	private int goods_id;//��ƷID
	private String order_id;//����ID
	private Integer goods_amount;//��Ʒ����
	private double goods_real_price;//������Ʒʵ�ʱ���
	private Integer is_after_sale;//�Ƿ��ۺ�  0:���������� 1���ۺ󶩵��� 2����������
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Integer getGoods_amount() {
		return goods_amount;
	}
	public void setGoods_amount(Integer goods_amount) {
		this.goods_amount = goods_amount;
	}

	public double getGoods_real_price() {
		return goods_real_price;
	}
	public void setGoods_real_price(double goods_real_price) {
		this.goods_real_price = goods_real_price;
	}
	
	public Integer getIs_after_sale() {
		return is_after_sale;
	}
	public void setIs_after_sale(Integer is_after_sale) {
		this.is_after_sale = is_after_sale;
	}
	public Order_item() {
		super();
	}
	
	public Order_item(String id, int goods_id, String order_id, Integer goods_amount, double goods_real_price,Integer is_after_sale) {
		super();
		this.id = id;
		this.goods_id = goods_id;
		this.order_id = order_id;
		this.goods_amount = goods_amount;
		this.goods_real_price = goods_real_price;
		this.is_after_sale=is_after_sale;
	}
	
	public Order_item(String id, int goods_id, String order_id, Integer goods_amount, double goods_real_price) {
		super();
		this.id = id;
		this.goods_id = goods_id;
		this.order_id = order_id;
		this.goods_amount = goods_amount;
		this.goods_real_price = goods_real_price;
	}
	@Override
	public String toString() {
		return "Order_item [id=" + id + ", goods_id=" + goods_id + ", order_id=" + order_id + ", goods_amount="
				+ goods_amount + ", goods_low_price=" 
				+ ", goods_real_price=" + goods_real_price + "]";
	}
	
	
}
