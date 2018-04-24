package com.htzhny.entity;

import java.util.Date;
/**
 * 
 * @author mEssA9e
 *�ջ���ַ��
 */
public class Address {
	private int id;
	private String receiver; //�ջ���
	private String address_detail;//s�ջ���ַ
	private String create_time;//����ʱ��
	private int status; //1������ 2��ʧЧ
	private int user_id;//�����û�
	private String receiver_phone;//�ջ��˵绰
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getReceiver_phone() {
		return receiver_phone;
	}
	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", receiver=" + receiver + ", address_detail=" + address_detail + ", create_time="
				+ create_time + ", status=" + status + ", user_id=" + user_id + "]";
	}
	public Address(int id, String receiver, String address_detail, String create_time, int status, int user_id,String receiver_phone) {
		super();
		this.id = id;
		this.receiver = receiver;
		this.address_detail = address_detail;
		this.create_time = create_time;
		this.status = status;
		this.user_id = user_id;
		this.receiver_phone=receiver_phone;
	}
	public Address() {
		super();
	}
	public Address(String receiver, String address_detail, String create_time, int status, int user_id,String receiver_phone) {
		super();
		this.receiver = receiver;
		this.address_detail = address_detail;
		this.create_time = create_time;
		this.status = status;
		this.user_id = user_id;
		this.receiver_phone=receiver_phone;
	}
	public Address(int id, String receiver, String address_detail,String receiver_phone) {
		super();
		this.id = id;
		this.receiver = receiver;
		this.address_detail = address_detail;
		this.receiver_phone=receiver_phone;
	}
	public Address(int id, String receiver, String address_detail) {
		super();
		this.id = id;
		this.receiver = receiver;
		this.address_detail = address_detail;
	}



	
}
