package com.htzhny.entity;
/**
 * �˵���
 * @author mEssA9e
 *
 */
public class Bill {
	private String id; //�˵�id
	private Integer user_id;//�˵������û�id
	private String onemonth;//�˵������·�
	private double month_pay_money;//ÿ��Ӧ��֧���Ľ��
	private Integer flag;//�Ƿ����˵� 1:�����˵� 2������δ֧���˵���3����ʷ�˵�
	private String remake;//��ע
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getOnemonth() {
		return onemonth;
	}
	public void setOnemonth(String onemonth) {
		this.onemonth = onemonth;
	}
	public double getMonth_pay_money() {
		return month_pay_money;
	}
	public void setMonth_pay_money(double month_pay_money) {
		this.month_pay_money = month_pay_money;
	}
	public String getRemake() {
		return remake;
	}
	public void setRemake(String remake) {
		this.remake = remake;
	}
	

	public Bill() {
		super();
	}
	public Bill(String id, Integer user_id, String onemonth, double month_pay_money, Integer flag) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.onemonth = onemonth;
		this.month_pay_money = month_pay_money;
		this.flag = flag;
	}
	public Bill(String id, Integer user_id, String onemonth, double month_pay_money, Integer flag, String remake) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.onemonth = onemonth;
		this.month_pay_money = month_pay_money;
		this.flag = flag;
		this.remake = remake;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", user_id=" + user_id + ", onemonth=" + onemonth + ", month_pay_money="
				+ month_pay_money + ", flag=" + flag + ", remake=" + remake + "]";
	}

	
}
