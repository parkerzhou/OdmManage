package com.tcl.model;

import java.util.Date;

public class Question {
    private int id;
    private int user_id;
    private int ws_id;
    private int dept_id;
    private int line_id;
    private int sort_id;
    private int client_id;
    private int first_id;
    private int second_id;
    private String no;
    private String pro_name;
    private int pro_plan_num;
    private int pro_num;
    private String bad_ratio;
    private Date add_tim;
    private Date occur_tim;
    private Date close_tim;
    private String body;
    private int state;
    private Client client;
    private Dept dept;
    private Line line;
    private Reason reason;
    private Solve solve;
    private Sort sort;
    private WorkShop workshop;
    
	
	public int getFirst_id() {
		return first_id;
	}
	public void setFirst_id(int first_id) {
		this.first_id = first_id;
	}
	public int getSecond_id() {
		return second_id;
	}
	public void setScond_id(int scond_id) {
		this.second_id = scond_id;
	}
	public int getWs_id() {
		return ws_id;
	}
	public void setWs_id(int ws_id) {
		this.ws_id = ws_id;
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public int getLine_id() {
		return line_id;
	}
	public void setLine_id(int line_id) {
		this.line_id = line_id;
	}
	public int getSort_id() {
		return sort_id;
	}
	public void setSort_id(int sort_id) {
		this.sort_id = sort_id;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public int getPro_plan_num() {
		return pro_plan_num;
	}
	public void setPro_plan_num(int pro_plan_num) {
		this.pro_plan_num = pro_plan_num;
	}
	public int getPro_num() {
		return pro_num;
	}
	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}
	public String getBad_ratio() {
		return bad_ratio;
	}
	public void setBad_ratio(String bad_ratio) {
		this.bad_ratio = bad_ratio;
	}
	public Date getOccur_tim() {
		return occur_tim;
	}
	public void setOccur_tim(Date occur_tim) {
		this.occur_tim = occur_tim;
	}
	public Date getClose_tim() {
		return close_tim;
	}
	public void setClose_tim(Date string) {
		this.close_tim = string;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public Reason getReason() {
		return reason;
	}
	public void setReason(Reason reason) {
		this.reason = reason;
	}
	public Solve getSolve() {
		return solve;
	}
	public void setSolve(Solve solve) {
		this.solve = solve;
	}
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	public WorkShop getWorkshop() {
		return workshop;
	}
	public void setWorkshop(WorkShop workshop) {
		this.workshop = workshop;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getAdd_tim() {
		return add_tim;
	}
	public void setAdd_tim(Date add_tim) {
		this.add_tim = add_tim;
	}
    
}
