package com.htzhny.entity;

public class Order_itemQuery {
	private String id;
	private String goods_name;//商品名
	private String order_id;//订单ID
	private double goods_amount;//商品数量
	private double goods_low_price;//订单项商品总共的最低价
	private double goods_high_price;//订单项商品总共的最高价
	private double goods_real_price;//单个商品实际报价
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public double getGoods_amount() {
		return goods_amount;
	}
	public void setGoods_amount(double goods_amount) {
		this.goods_amount = goods_amount;
	}
	public double getGoods_low_price() {
		return goods_low_price;
	}
	public void setGoods_low_price(double goods_low_price) {
		this.goods_low_price = goods_low_price;
	}
	public double getGoods_high_price() {
		return goods_high_price;
	}
	public void setGoods_high_price(double goods_high_price) {
		this.goods_high_price = goods_high_price;
	}
	public double getGoods_real_price() {
		return goods_real_price;
	}
	public void setGoods_real_price(double goods_real_price) {
		this.goods_real_price = goods_real_price;
	}


	@Override
	public String toString() {
		return "Order_itemQuery [id=" + id + ", goods_name=" + goods_name + ", order_id=" + order_id + ", goods_amount="
				+ goods_amount + ", goods_low_price=" + goods_low_price + ", goods_high_price=" + goods_high_price
				+ ", goods_real_price=" + goods_real_price + "]";
	}
	public Order_itemQuery(String id, String goods_name, String order_id, double goods_amount, double goods_low_price,
			double goods_high_price, double goods_real_price) {
		super();
		this.id = id;
		this.goods_name = goods_name;
		this.order_id = order_id;
		this.goods_amount = goods_amount;
		this.goods_low_price = goods_low_price;
		this.goods_high_price = goods_high_price;
		this.goods_real_price = goods_real_price;
	}
	public Order_itemQuery() {
		super();
	}
	
}
