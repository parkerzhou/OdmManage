package com.tcl.model;

import java.util.Date;

public class Solve {
	private int id;
    private String tem_metnod;
    private String basic_method;
    private Date plan_tim;
    private String head;
    private String comfirm;
    private int iscomfirm;
    private String QA_comf;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTem_metnod() {
		return tem_metnod;
	}
	public void setTem_metnod(String tem_metnod) {
		this.tem_metnod = tem_metnod;
	}
	public String getBasic_method() {
		return basic_method;
	}
	public void setBasic_method(String basic_method) {
		this.basic_method = basic_method;
	}
	public Date getPlan_tim() {
		return plan_tim;
	}
	public void setPlan_tim(Date plan_tim) {
		this.plan_tim = plan_tim;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getComfirm() {
		return comfirm;
	}
	public void setComfirm(String comfirm) {
		this.comfirm = comfirm;
	}
	public String getQA_comf() {
		return QA_comf;
	}
	public void setQA_comf(String qA_comf) {
		QA_comf = qA_comf;
	}
	public int getIscomfirm() {
		return iscomfirm;
	}
	public void setIscomfirm(int iscomfirm) {
		this.iscomfirm = iscomfirm;
	}
    
}
