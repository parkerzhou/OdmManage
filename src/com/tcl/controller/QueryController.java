package com.tcl.controller;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tcl.inter.Query;
import com.tcl.model.Question;

@Controller
public class QueryController {
	@Autowired
    private Query query;
	@RequestMapping(value = "/QueryQues")
	public ModelAndView QueryQues(Model model,HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("QueryQuestion");
	
	}
	@RequestMapping(value = "/QueryByData")
	public ModelAndView QueryByData(Model model,HttpServletRequest request,HttpServletResponse response){
		String stime=request.getParameter("st");
	 	String ctime=request.getParameter("ct");
	 	String workShop=(String)request.getSession().getAttribute("workShop");
	 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat datetim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	List<Question> querybydata=query.queryQuesByData(stime, ctime, workShop);
	 	List<String> occurtime=new ArrayList<String>();
		List<String> plantime=new ArrayList<String>();
		List<String> closetime=new ArrayList<String>();
		for(Question question:querybydata){
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
		request.getSession().setAttribute("occurtime", occurtime);
		request.getSession().setAttribute("plantime", plantime);
		request.getSession().setAttribute("closetime", closetime);
		model.addAttribute("querybydata", querybydata);
	 	return new ModelAndView("queryByData");
	}
	@RequestMapping(value = "/QueryBykwords")
	public ModelAndView QueryBykwords(Model model,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		/*js所传来的参数为中文时，需解码*/
		String kwords =new String(request.getParameter("kw").getBytes("ISO-8859-1"),"UTF-8"); 
		String workShop=(String)request.getSession().getAttribute("workShop");
	 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 	DateFormat datetim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	List<Question> querybykwords=query.queryQuesByKeyWords(kwords, workShop);
	 	List<String> occurtime=new ArrayList<String>();
		List<String> plantime=new ArrayList<String>();
		List<String> closetime=new ArrayList<String>();
		for(Question question:querybykwords){
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
		request.getSession().setAttribute("occurtime", occurtime);
		request.getSession().setAttribute("plantime", plantime);
		request.getSession().setAttribute("closetime", closetime);
		model.addAttribute("querybykwords", querybykwords);
	 	return new ModelAndView("queryByKwords");
	}
}
