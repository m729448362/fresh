package com.htzhny.service;

import java.util.List;

import com.htzhny.entity.Bill;

public interface BillService {
	public Integer addBill(Bill bill);
	public Bill selectBillByUserId(Integer user_id);
	public Integer updateMonthPayMoney(double month_pay_money);
	public List<Bill> selectAllBill();
}
