package com.htzhny.entity;

public class Order_itemAndAfter_saleQuery {

	
	private Integer goods_amount;//��Ʒ����
	private double goods_low_price; //��ͼ�
	private double goods_high_price;//��߼�
	private double goods_real_price;//������Ʒʵ�ʱ���
	private String after_sale_reason;//�����ۺ�����
	private int handle_status; //1��δ���� 2�������� 3���������
	private String handle_way; //�ۺ���ʽ
	private String handle_person;//�ۺ�����
	private String handle_time; //����ʱ��
	private String company;
	private String prove_img;//֤��ͼƬ
	private String delivery_time;

	public String getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(String delivery_time) {
		this.delivery_time = delivery_time;
	}
	public Integer getGoods_amount() {
		return goods_amount;
	}
	public void setGoods_amount(Integer goods_amount) {
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
	public String getAfter_sale_reason() {
		return after_sale_reason;
	}
	public void setAfter_sale_reason(String after_sale_reason) {
		this.after_sale_reason = after_sale_reason;
	}
	public int getHandle_status() {
		return handle_status;
	}
	public void setHandle_status(int handle_status) {
		this.handle_status = handle_status;
	}
	public String getHandle_way() {
		return handle_way;
	}
	public void setHandle_way(String handle_way) {
		this.handle_way = handle_way;
	}
	public String getHandle_person() {
		return handle_person;
	}
	public void setHandle_person(String handle_person) {
		this.handle_person = handle_person;
	}
	public String getHandle_time() {
		return handle_time;
	}
	public void setHandle_time(String handle_time) {
		this.handle_time = handle_time;
	}
	public String getProve_img() {
		return prove_img;
	}
	public void setProve_img(String prove_img) {
		this.prove_img = prove_img;
	}
	
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}


	public Order_itemAndAfter_saleQuery(Integer goods_amount, double goods_low_price, double goods_high_price,
			double goods_real_price, String after_sale_reason, int handle_status, String handle_way,
			String handle_person, String handle_time, String company, String prove_img, String delivery_time) {
		super();
		this.goods_amount = goods_amount;
		this.goods_low_price = goods_low_price;
		this.goods_high_price = goods_high_price;
		this.goods_real_price = goods_real_price;
		this.after_sale_reason = after_sale_reason;
		this.handle_status = handle_status;
		this.handle_way = handle_way;
		this.handle_person = handle_person;
		this.handle_time = handle_time;
		this.company = company;
		this.prove_img = prove_img;
		this.delivery_time = delivery_time;
	}
	@Override
	public String toString() {
		return "Order_itemAndAfter_saleQuery [goods_amount=" + goods_amount + ", goods_low_price=" + goods_low_price
				+ ", goods_high_price=" + goods_high_price + ", goods_real_price=" + goods_real_price
				+ ", after_sale_reason=" + after_sale_reason + ", handle_status=" + handle_status + ", handle_way="
				+ handle_way + ", handle_person=" + handle_person + ", handle_time=" + handle_time + ", company="
				+ company + ", prove_img=" + prove_img + ", delivery_time=" + delivery_time + "]";
	}
	public Order_itemAndAfter_saleQuery() {
		super();
	}

}
