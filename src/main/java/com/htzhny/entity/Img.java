package com.htzhny.entity;
/**
 * 
 * @author mEssA9e
 *ͼƬ
 */
public class Img {
	private int id;
	private int goods_id;//������Ʒid
	private String img_url;//ͼƬ��ַ
	private int img_status;//ͼƬ״̬
	private int img_type;//1����ƷͼƬ 2���ۺ�֤��ͼƬ 3���û�ͷ��
	private int after_sale_id;//�ۺ��¼�id
	private int user_id;//�û�id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public int getImg_status() {
		return img_status;
	}
	public void setImg_status(int img_status) {
		this.img_status = img_status;
	}
	public int getImg_type() {
		return img_type;
	}
	public void setImg_type(int img_type) {
		this.img_type = img_type;
	}
	public int getAfter_sale_id() {
		return after_sale_id;
	}
	public void setAfter_sale_id(int after_sale_id) {
		this.after_sale_id = after_sale_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "Img [id=" + id + ", goods_id=" + goods_id + ", img_url=" + img_url + ", img_status=" + img_status
				+ ", img_type=" + img_type + ", after_sale_id=" + after_sale_id + ", user_id=" + user_id + "]";
	}
	
}
