package com.tcl.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tcl.inter.Login;
import com.tcl.inter.Ques;
import com.tcl.model.Question;
import com.tcl.model.User;
  

@Controller
public class LoginController {
	@Autowired
    private Login login;
	@Autowired
    private Ques ques;
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public ModelAndView login(Model model,HttpServletRequest request){
		String workShop=new String("MZ");
		request.getSession().setAttribute("workShop", workShop);
		model.addAttribute(new User());
		return new ModelAndView("login");
	}
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user")User user,Model model,HttpServletRequest request){
		String workShop=(String)request.getSession().getAttribute("workShop");
		/*定义id的目的是登录之后返回的界面是今日新增*/
		String id="1";
		Date dateTmp =new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat datetim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = dateFormat.format(dateTmp);
		if(user.getTu_login_name().isEmpty()||user.getTu_login_pwd().isEmpty()){
    		return new ModelAndView("login", "error1", "用户名或密码不能为空！");
    	}
		User checkuser=login.findUserByUserName(user.getTu_login_name());
		if(null!=checkuser){
			if(checkuser.getTu_login_pwd().equals(user.getTu_login_pwd())){
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
				/*转换时间格式*/
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
				request.getSession().setAttribute("today", today);
				request.getSession().setAttribute("user", checkuser);
				request.getSession().setAttribute("todayNewNum", login.selectTodayNewNum(workShop,today));
				request.getSession().setAttribute("unCloseQuesNum", login.selectNnCloseQuesNum(workShop));
				request.getSession().setAttribute("closedQuesNum", login.selectClosedQuesNum(workShop));
				request.getSession().setAttribute("allQuesNum", login.selectClosedQuesNum(workShop)+login.selectNnCloseQuesNum(workShop));
				request.getSession().setAttribute("id", id);
				request.getSession().setAttribute("occurtime", occurtime);
				request.getSession().setAttribute("plantime", plantime);
				request.getSession().setAttribute("closetime", closetime);
				request.getSession().setAttribute("questions", questions);
				model.addAttribute("questions", questions);
				return new ModelAndView("Question");
			}
			else
			return new ModelAndView("login", "error1", "密码错误!");
		}
		else
			return new ModelAndView("login", "error1", "用户名错误!");
	}
	@RequestMapping(value = "/Back")
	public ModelAndView Back(HttpServletRequest request,HttpServletResponse response){
		String workShop=(String)request.getSession().getAttribute("workShop");
		String today=(String)request.getSession().getAttribute("today");
		/*必须更新数量*/
		request.getSession().setAttribute("todayNewNum", login.selectTodayNewNum(workShop,today));
		request.getSession().setAttribute("unCloseQuesNum", login.selectNnCloseQuesNum(workShop));
		request.getSession().setAttribute("closedQuesNum", login.selectClosedQuesNum(workShop));
		request.getSession().setAttribute("allQuesNum", login.selectClosedQuesNum(workShop)+login.selectNnCloseQuesNum(workShop));
		return new ModelAndView("displayQuestion");
     }
	@RequestMapping(value ="/Switch")
	public ModelAndView Switch(Model model,HttpServletRequest request,HttpServletResponse response){
		String workShop=(String)request.getSession().getAttribute("workShop");
		String today=(String)request.getSession().getAttribute("today");
		if(workShop.equals("MZ")){
			workShop="FA";
		}
		else if(workShop.equals("FA")){
			workShop="MZ";
		}
		request.getSession().setAttribute("workShop", workShop);
		/*必须更新数量*/
		request.getSession().setAttribute("todayNewNum", login.selectTodayNewNum(workShop,today));
		request.getSession().setAttribute("unCloseQuesNum", login.selectNnCloseQuesNum(workShop));
		request.getSession().setAttribute("closedQuesNum", login.selectClosedQuesNum(workShop));
		request.getSession().setAttribute("allQuesNum", login.selectClosedQuesNum(workShop)+login.selectNnCloseQuesNum(workShop));
		return new ModelAndView("displayQuestion");
}
	@RequestMapping(value ="/ChangePW")
	public ModelAndView ChangePW(HttpServletRequest request,HttpServletResponse response){
		User user=(User)request.getSession().getAttribute("user");
		if(request.getParameter("PassWord").isEmpty()||request.getParameter("comfPassWord").isEmpty()){
			return new ModelAndView("changepw", "error8", "密码不能为空！");
		}
		else if(!request.getParameter("PassWord").equals(request.getParameter("comfPassWord"))){
			return new ModelAndView("changepw", "error8", "两次输入密码不一致！");
		}
		else{
			login.changepw(user.getTu_id(), request.getParameter("PassWord"));
			return new ModelAndView("newSucc");
		}
	}
	@RequestMapping(value ="/ToChangePW")
	public ModelAndView ToChangePW(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("changepw");
	}
}
