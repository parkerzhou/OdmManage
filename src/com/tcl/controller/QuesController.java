package com.tcl.controller;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tcl.inter.Operation;
import com.tcl.inter.Ques;
import com.tcl.model.*;

@Controller
public class QuesController{
	@Autowired
    private Ques ques;
	@Autowired
    private Operation operation;
	@RequestMapping(value ="/Question")
	public ModelAndView Question(Model model,HttpServletRequest request,HttpServletResponse response){
		String workShop=(String)request.getSession().getAttribute("workShop");
		/*设置id的作用是分辨请求来自哪个问题项（有四个），不同id所调用问题检索函数不一样*/
		String id=request.getParameter("id");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat datetim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today=(String)request.getSession().getAttribute("today");
		List<Question> questions = null;
		List<Question> todaynew = null;
		List<Question> closedquestion = null;
		List<Question> unclose = null;
		List<Question> allquestion = null;
		if(id.equals("1")){
			todaynew=ques.selectTodayNew(workShop, today);
			questions=todaynew;
		}
		else if(id.equals("2")){
			unclose=ques.selectUnClose(workShop);
			questions=unclose;
		}
		else if(id.equals("3")){
			closedquestion=ques.selectCloseQuestion(workShop);
			questions=closedquestion;
		}
		else if(id.equals("4")){
			allquestion=ques.selectAllQuestion(workShop);
			questions=allquestion;
		}
		List<String> occurtime=new ArrayList<String>();
		List<String> plantime=new ArrayList<String>();
		List<String> closetime=new ArrayList<String>();
		for(Question question:questions){
			if(question.getOccur_tim()!=null){
				occurtime.add(datetim.format(question.getOccur_tim()));
			}
			else{
				occurtime.add(" ");
			}
			if(question.getSolve().getPlan_tim()!=null){
				plantime.add(dateFormat.format(question.getSolve().getPlan_tim()));
			}
			else{
				plantime.add(" ");
			}
			if(question.getClose_tim()!=null){
				closetime.add(dateFormat.format(question.getClose_tim()));
			}
			else{
				closetime.add(" ");
			}
		}
		request.getSession().setAttribute("id", id);
		request.getSession().setAttribute("occurtime", occurtime);
		request.getSession().setAttribute("plantime", plantime);
		request.getSession().setAttribute("closetime", closetime);
		/*存入session中，在返回没有修改权限信息的时候将会用到*/
		request.getSession().setAttribute("questions", questions);
		model.addAttribute("questions", questions);
		return new ModelAndView("Question");
}
	@RequestMapping(value = "/NewQuestion",method=RequestMethod.GET)
	public ModelAndView NewQuestion(Model model,HttpServletRequest request,HttpServletResponse response){
		User user=(User)request.getSession().getAttribute("user");
		List roleid=operation.selectRoleByUserId(user.getTu_id());
		/*只有当用户的角色为1或者5时，才可返回新建页面*/
		if(roleid.contains("1")||roleid.contains("5")){
			List<String> line=ques.selectLine();
			List<String> client=ques.selectClient();
			String workShop=(String)request.getSession().getAttribute("workShop");
			model.addAttribute("line", line);
			model.addAttribute("client", client);
			return new ModelAndView("newQuestion");
		}
		else 
		return new ModelAndView("NoNew","error5","抱歉，没有新建权限！");
     }
	@RequestMapping(value = "/NewQuestion",method=RequestMethod.POST)
	public ModelAndView NewQuestion(Model model,HttpServletRequest request) throws ParseException{
		Question question=new Question();
		int wsid=1;
		String workShop=(String)request.getSession().getAttribute("workShop");
		User user=(User)request.getSession().getAttribute("user");
		if(workShop.equals("MZ")){
			wsid=1;
		}
		else if(workShop.equals("FA")){
			wsid=2;
		}
		if(request.getParameter("line").isEmpty()||request.getParameter("client").isEmpty()||request.getParameter("pro_name").isEmpty()||request.getParameter("no").isEmpty()
				||request.getParameter("pro_plan_num").isEmpty()||request.getParameter("pro_num").isEmpty()
				||request.getParameter("bad_ratio").isEmpty()||request.getParameter("body").isEmpty()){
			List<String> line=ques.selectLine();
			List<String> client=ques.selectClient();
			model.addAttribute("line", line);
			model.addAttribute("client", client);
			return new ModelAndView("newQuestion","error2","信息填写不完整！");
		}
		java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("[0-9]*");
		java.util.regex.Matcher pro_plan_num=pattern.matcher(request.getParameter("pro_plan_num"));
		java.util.regex.Matcher pro_num=pattern.matcher(request.getParameter("pro_num"));
		if(pro_plan_num.matches()==false||pro_num.matches()==false){
			List<String> line=ques.selectLine();
			List<String> client=ques.selectClient();
			model.addAttribute("line", line);
			model.addAttribute("client", client);
			return new ModelAndView("newQuestion","error2","批次数量和已生产数必须填数字！");
		}
		question.setWs_id(wsid);
		question.setUser_id(user.getTu_id());
		question.setLine_id(Integer.valueOf(request.getParameter("line")));
		question.setClient_id(Integer.valueOf(request.getParameter("client")));
		question.setPro_name(request.getParameter("pro_name"));
		question.setNo(request.getParameter("no"));
		question.setPro_plan_num(Integer.valueOf(request.getParameter("pro_plan_num")));
		question.setPro_num(Integer.valueOf(request.getParameter("pro_num")));
		question.setAdd_tim(new Date());
		question.setBad_ratio(request.getParameter("bad_ratio"));
		question.setBody(request.getParameter("body"));
	    ques.insertQuestion(question);
		return new ModelAndView("newSucc");
     }
	@RequestMapping(value = "/Export")
	public ModelAndView Export(Model model,HttpServletRequest request,HttpServletResponse response){
		String workShop=(String)request.getSession().getAttribute("workShop");
		String id=request.getParameter("id");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat datetim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String today=(String)request.getSession().getAttribute("today");
		List<Question> questions = null;
		List<Question> todaynew = null;
		List<Question> closedquestion = null;
		List<Question> unclose = null;
		List<Question> allquestion = null;
		if(id.equals("1")){
			todaynew=ques.selectTodayNew(workShop, today);
			questions=todaynew;
		}
		else if(id.equals("2")){
			unclose=ques.selectUnClose(workShop);
			questions=unclose;
		}
		else if(id.equals("3")){
			closedquestion=ques.selectCloseQuestion(workShop);
			questions=closedquestion;
		}
		else if(id.equals("4")){
			allquestion=ques.selectAllQuestion(workShop);
			questions=allquestion;
		}
		List<String> occurtime=new ArrayList<String>();
		List<String> plantime=new ArrayList<String>();
		List<String> closetime=new ArrayList<String>();
		for(Question question:questions){
			if(question.getOccur_tim()!=null){
				occurtime.add(datetim.format(question.getOccur_tim()));
			}
			else{
				occurtime.add(" ");
			}
			if(question.getSolve().getPlan_tim()!=null){
				plantime.add(dateFormat.format(question.getSolve().getPlan_tim()));
			}
			else{
				plantime.add(" ");
			}
			if(question.getClose_tim()!=null){
				closetime.add(dateFormat.format(question.getClose_tim()));
			}
			else{
				closetime.add(" ");
			}
		}
		request.getSession().setAttribute("id", id);
		request.getSession().setAttribute("occurtime", occurtime);
		request.getSession().setAttribute("plantime", plantime);
		request.getSession().setAttribute("closetime", closetime);
		model.addAttribute("questions", questions);
		return new ModelAndView("exportExcel");
}
	@RequestMapping(value ="/AddClient")
	public ModelAndView AddClient(Model model,HttpServletRequest request,HttpServletResponse response){
		String addclient=request.getParameter("addclient");
		if(!addclient.isEmpty()){
	    ques.addClient(request.getParameter("addclient"));
		}
	    User user=(User)request.getSession().getAttribute("user");
		List roleid=operation.selectRoleByUserId(user.getTu_id());
		List<String> line=ques.selectLine();
		List<String> client=ques.selectClient();
		String workShop=(String)request.getSession().getAttribute("workShop");
		model.addAttribute("line", line);
		model.addAttribute("client", client);
		return new ModelAndView("newQuestion");
	}
	
}


