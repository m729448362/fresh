package com.htzhny.entity;

import java.sql.Date;
/**
 * 
 * @author mEssA9e
 *������־��
 */
public class OrderLog {
	private Long log_id;
	private String order_id;//����id
	private String handle_time;//����ʱ��
	private Integer event;//�����¼� 1���ύ�ᵥ 2������ 3��ȷ�϶��� 4������  5:�ύ�ۺ����� 6:�ۺ������  7�� ȷ���ջ� 8������  9:ȡ���ۺ�����

	public Long getLog_id() {
		return log_id;
	}
	public void setLog_id(Long log_id) {
		this.log_id = log_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getHandle_time() {
		return handle_time;
	}
	public void setHandle_time(String handle_time) {
		this.handle_time = handle_time;
	}
	public Integer getEvent() {
		return event;
	}
	public void setEvent(Integer event) {
		this.event = event;
	}
	public OrderLog(Long log_id, String order_id, String handle_time, Integer event) {
		super();
		this.log_id = log_id;
		this.order_id = order_id;
		this.handle_time = handle_time;
		this.event = event;
	}
	
	public OrderLog(String order_id, String handle_time, Integer event) {
		super();
		this.order_id = order_id;
		this.handle_time = handle_time;
		this.event = event;
	}
	public OrderLog() {
		super();
	}
	@Override
	public String toString() {
		return "OrderLog [log_id=" + log_id + ", order_id=" + order_id + ", handle_time=" + handle_time + ", event="
				+ event + "]";
	}
	
	
	
}
