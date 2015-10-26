package com.tcl.controller;
	import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tcl.inter.Login;
import com.tcl.inter.Operation;
import com.tcl.inter.Ques;
import com.tcl.model.Question;
import com.tcl.model.User;
	@Controller
	public class OperationController {
			@Autowired
		    private Operation operation;
			@Autowired
		    private Ques ques;
			@RequestMapping(value ="/Alter")
			public ModelAndView Alter(Model model,HttpServletRequest request,HttpServletResponse response){
				String user_id=(String)request.getParameter("user_id");
				User user=(User)request.getSession().getAttribute("user");
				/*为返回没有修改权限信息做准备*/
				List<Question> questions=(List<Question>)request.getSession().getAttribute("questions");
				model.addAttribute("questions", questions);
				/*设置page的作用是分辨修改来自主页面还是来自查询，来自主页面，提示信息返回页面，但来自查询就要返回一个新页面*/
				String pagenum=(String)request.getParameter("page");
				String page = null;
				if(pagenum.equals("1")){
					page="Question";
				}
				else {
					page="NoAlter";
				}
				if(!user_id.trim().equals(String.valueOf(user.getTu_id()))){
					return new ModelAndView(page,"error3","抱歉，您没有修改权限！");
				}
				String ques_id=(String)request.getParameter("ques_id");
				List<Question> alterques=operation.selectQuesById(Integer.valueOf(ques_id));
				Question alterquestion=null;
				for(Question aq:alterques){
					alterquestion=aq;
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				/*将状态0,1转换成“关闭”“打开”*/
				int state=alterquestion.getState();
				String sstate=null;
				if(state==0){
					sstate="关闭";
				}
				else {
					sstate="打开";
				}
				model.addAttribute("state", state);
				model.addAttribute("sstate", sstate);
				model.addAttribute("alterquestion", alterquestion);
				/*将这几项数据检索出来放到下拉菜单*/
				List<String> line=ques.selectLine();
				List<String> client=ques.selectClient();
				List<String> dept=ques.selectDept();
				List<String> sort=ques.selectSort();
				model.addAttribute("line", line);
				model.addAttribute("client", client);
				model.addAttribute("dept", dept);
				model.addAttribute("sort", sort);
				String workShop=(String)request.getSession().getAttribute("workShop");
				/*action的作用是传达被修改问题的id*/
				return new ModelAndView("alterQuestion","action",alterquestion.getId());
		}
			@RequestMapping(value ="/SaveAlter")
			public ModelAndView SaveAlter(Model model,HttpServletRequest request,HttpServletResponse response) throws ParseException{
				String action=(String)request.getParameter("action");
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
				if(request.getParameter("line").isEmpty()||request.getParameter("client").isEmpty()
						||request.getParameter("pro_name").isEmpty()||request.getParameter("no").isEmpty()
						||request.getParameter("pro_plan_num").isEmpty()||request.getParameter("pro_num").isEmpty()
						||request.getParameter("bad_ratio").isEmpty()||request.getParameter("body").isEmpty()){
					/*为下拉菜单作准备*/
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
				question.setBad_ratio(request.getParameter("bad_ratio"));
				question.setBody(request.getParameter("body"));
				question.setId(Integer.valueOf(action));
				operation.updateQues(question);
				return new ModelAndView("newSucc");
			}
			@RequestMapping(value ="/Reply")
			public ModelAndView Reply(Model model,HttpServletRequest request,HttpServletResponse response){
				String ques_id=(String)request.getParameter("ques_id");
				String first_id=(String)request.getParameter("first_id");
				String second_id=(String)request.getParameter("second_id");
				User user=(User)request.getSession().getAttribute("user");
				/*根据用户的id获得用户的所拥有的权限，以数组形式返回*/
				List roleid=operation.selectRoleByUserId(user.getTu_id());
				List<Question> alterques=operation.selectQuesById(Integer.valueOf(ques_id));
				Question alterquestion=null;
				for(Question aq:alterques){
					alterquestion=aq;
				}
				List<String> dept=ques.selectDept();
				List<String> sort=ques.selectSort();
				model.addAttribute("dept", dept);
				model.addAttribute("sort", sort);
				int state=alterquestion.getState();
				String sstate=null;
				if(state==0){
					sstate="关闭";
				}
				else {
					sstate="打开";
				}
				/*处理确认人是否确认*/
				int iscomfirm=alterquestion.getSolve().getIscomfirm();
				String ccomfirm=null;
				if(iscomfirm==0){
					ccomfirm="待确认";
				}
				else {
					ccomfirm="已确认";
				}
				request.getSession().setAttribute("iscomfirm",iscomfirm);
				model.addAttribute("ccomfirm", ccomfirm);
				model.addAttribute("state", state);
				model.addAttribute("sstate", sstate);
				model.addAttribute("alterquestion", alterquestion);
				/*处理计划完成时间的格式*/
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String plan_time=new String();
				if(alterquestion.getSolve().getPlan_tim()==null){
					model.addAttribute("plan_time", plan_time);
				}
				else{
					plan_time=dateFormat.format(alterquestion.getSolve().getPlan_tim());
					model.addAttribute("plan_time", plan_time);
				}
				/*权限问题的处理*/
				/*情况一：第一和第二部分都没人回复*/
				if(first_id.equals("0")&&second_id.equals("0")){
					if((roleid.contains("2")||roleid.contains("1"))&&roleid.contains("3")){
						return new ModelAndView("Reply");
					}
					else if(roleid.contains("1")||roleid.contains("2")){
						return new ModelAndView("firstReply");
					}
					else if(roleid.contains("3")){
						return new ModelAndView("secondReply");
					}
					else if(roleid.contains("4")){
						return new ModelAndView("thirdReply");
					}
					else
						return new ModelAndView("NoReply","error4","抱歉，没有回复权限！");
				} 
				
				/*情况二：第一部分已回复，第二部分没回复*/
				else if(!first_id.equals("0")&&second_id.equals("0")){
					/*只有回复第一部分的人可修改第一部分*/
					if(user.getTu_id()==Integer.valueOf(first_id)&&roleid.contains("3")){
						return new ModelAndView("Reply");
					}
					else if(user.getTu_id()==Integer.valueOf(first_id)){
						return new ModelAndView("firstReply");
					}
					else if(roleid.contains("3")){
						return new ModelAndView("secondReply");
					}
					else if(roleid.contains("4")){
						return new ModelAndView("thirdReply");
					}
					else
						return new ModelAndView("NoReply","error4","抱歉，没有回复权限！");
				}
				/*情况三：第一部分没回复，第二部分已回复*/
				else if(first_id.equals("0")&&!second_id.equals("0")){
					/*只有回复第二部分的人可修改第二部分*/
					if(user.getTu_id()==Integer.valueOf(second_id)&&(roleid.contains("1")||roleid.contains("2"))){
						return new ModelAndView("Reply");
					}
					else if(user.getTu_id()==Integer.valueOf(second_id)){
						return new ModelAndView("secondReply");
					}
					else if(roleid.contains("1")||roleid.contains("2")){
						return new ModelAndView("firstReply");
					}
					else if(roleid.contains("4")){
						return new ModelAndView("thirdReply");
					}
					else
						return new ModelAndView("NoReply","error4","抱歉，没有回复权限！");
				}
				/*情况四：第一和第二部分都已回复*/
				else if(!first_id.equals("0")&&!second_id.equals("0")){
					if(user.getTu_id()==Integer.valueOf(first_id)&&user.getTu_id()==Integer.valueOf(second_id)){
						return new ModelAndView("Reply");
					}
					else if(user.getTu_id()==Integer.valueOf(first_id)){
						return new ModelAndView("firstReply");
					}
					else if(user.getTu_id()==Integer.valueOf(second_id)){
						return new ModelAndView("secondReply");
					}
					else if(roleid.contains("4")){
						return new ModelAndView("thirdReply");
					}
					else
						return new ModelAndView("NoReply","error4","抱歉，没有回复权限！");
				}
			
				return null;
	}
			@RequestMapping(value ="/SaveReply")
			public ModelAndView SaveReply(Model model,HttpServletRequest request,HttpServletResponse response){
				String reasonid=request.getParameter("reasonid");
				String solveid=request.getParameter("solveid");
				String quesid=request.getParameter("quesid");
				User user=(User)request.getSession().getAttribute("user");
				/*设置No的作用是分辨该请求来自哪个Reply（有三个）*/
				String No=request.getParameter("No");
				/*0代表来自Reply*/
				if(No.equals("0")){
					/*reason由无到有*/
					if(reasonid.equals("1")&&!request.getParameter("reason").isEmpty()){
						operation.insertReason(request.getParameter("reason"));
						operation.updateReasonId(operation.getReasonId(), Integer.valueOf(quesid));
					}
					/*reason被修改*/
					else if(!reasonid.equals("1")&&!request.getParameter("reason").isEmpty()){
						operation.updateReason(request.getParameter("reason"), Integer.valueOf(reasonid));
					}
					/*reason由有到无*/
					else if(!reasonid.equals("1")&&request.getParameter("reason").isEmpty()){
						operation.updateReasonId(1,Integer.valueOf(quesid));
					}
					/*解决方案由无到有*/
					if(solveid.equals("1")&&(!request.getParameter("tem_metnod").isEmpty()||!request.getParameter("basic_method").isEmpty()
					||!request.getParameter("plan_tim").isEmpty()||!request.getParameter("head").isEmpty()
					||!request.getParameter("comfirm").isEmpty())){
						operation.insertSolve0(request.getParameter("tem_metnod"), request.getParameter("basic_method"), 
								request.getParameter("plan_tim"), request.getParameter("head"), request.getParameter("comfirm"));
						/*获得最大solveid,插入名字*/
						operation.saveComfirmName(user.getTu_user_name(), operation.getSolveId());
						/*更新question中的solevid*/
						operation.updateSolveId(operation.getSolveId(), Integer.valueOf(quesid));
					}
					/*解决方案被修改*/
					else if(!solveid.equals("1")&&(!request.getParameter("tem_metnod").isEmpty()||!request.getParameter("basic_method").isEmpty()
							||!request.getParameter("plan_tim").isEmpty()||!request.getParameter("head").isEmpty()
							||!request.getParameter("comfirm").isEmpty())){
						operation.updateSolve0(Integer.valueOf(solveid),request.getParameter("tem_metnod"), request.getParameter("basic_method"), 
								request.getParameter("plan_tim"), request.getParameter("head"), request.getParameter("comfirm"));
					}
					/*解决方案由有到无*/
					else if(!solveid.equals("1")&&request.getParameter("tem_metnod").isEmpty()&&request.getParameter("basic_method").isEmpty()
							&&request.getParameter("plan_tim").isEmpty()&&request.getParameter("head").isEmpty()
							&&request.getParameter("comfirm").isEmpty()){
						operation.updateSolveId(1, Integer.valueOf(quesid));
					}
					operation.updateDeptSort(Integer.valueOf(request.getParameter("dept")), Integer.valueOf(request.getParameter("sort")),Integer.valueOf(quesid));
					operation.saveFirstId(user.getTu_id(), Integer.valueOf(quesid));
					operation.saveSecondId(user.getTu_id(), Integer.valueOf(quesid));
				}
				/*1代表来自firstReply*/
				else if(No.equals("1")){
					if(reasonid.equals("1")&&!request.getParameter("reason").isEmpty()){
						operation.insertReason(request.getParameter("reason"));
						operation.updateReasonId(operation.getReasonId(), Integer.valueOf(quesid));
					}
					else if(!reasonid.equals("1")&&!request.getParameter("reason").isEmpty()){
						operation.updateReason(request.getParameter("reason"), Integer.valueOf(reasonid));
					}
					else if(!reasonid.equals("1")&&request.getParameter("reason").isEmpty()){
						operation.updateReasonId(1,Integer.valueOf(quesid));
					}
					if(solveid.equals("1")&&(!request.getParameter("tem_metnod").isEmpty()||!request.getParameter("head").isEmpty()
					||!request.getParameter("comfirm").isEmpty())){
						operation.insertSolve(request.getParameter("tem_metnod"), request.getParameter("head"), request.getParameter("comfirm"));
						operation.saveComfirmName(user.getTu_user_name(), operation.getSolveId());
						operation.updateSolveId(operation.getSolveId(), Integer.valueOf(quesid));
					}
					else if(!solveid.equals("1")&&(!request.getParameter("tem_metnod").isEmpty()||!request.getParameter("head").isEmpty()
							||!request.getParameter("comfirm").isEmpty())){
						operation.updateSolve(Integer.valueOf(solveid),request.getParameter("tem_metnod"), request.getParameter("head"), request.getParameter("comfirm"));
						operation.saveComfirmName(user.getTu_user_name(), Integer.valueOf(solveid));
					}
					else if(!solveid.equals("1")&&request.getParameter("tem_metnod").isEmpty()&&request.getParameter("head").isEmpty()
							&&request.getParameter("comfirm").isEmpty()){
						operation.updateSolveId(1, Integer.valueOf(quesid));
						operation.saveComfirmName(user.getTu_user_name(), Integer.valueOf(solveid));
					}
					
					operation.updateDeptSort(Integer.valueOf(request.getParameter("dept")), Integer.valueOf(request.getParameter("sort")),Integer.valueOf(quesid));
					operation.saveFirstId(user.getTu_id(), Integer.valueOf(quesid));
				}
				/*2代表来自secondReply*/
				else if(No.equals("2")){
					if(solveid.equals("1")&&(!request.getParameter("basic_method").isEmpty()
							||!request.getParameter("plan_tim").isEmpty())){
								operation.insertSolve2(request.getParameter("basic_method"), 
										request.getParameter("plan_tim"));
								operation.updateSolveId(operation.getSolveId(), Integer.valueOf(quesid));
							}
							else if(!solveid.equals("1")&&(!request.getParameter("basic_method").isEmpty()
									||!request.getParameter("plan_tim").isEmpty())){
								operation.updateSolve2(Integer.valueOf(solveid),request.getParameter("basic_method"), request.getParameter("plan_tim"));
							}
							else if(!solveid.equals("1")&&request.getParameter("basic_method").isEmpty()
									&&request.getParameter("plan_tim").isEmpty()){
								operation.updateSolveId(1, Integer.valueOf(quesid));
							}
					operation.saveSecondId(user.getTu_id(), Integer.valueOf(quesid));
				}
				return new ModelAndView("newSucc");
	}
			@RequestMapping(value ="/SaveQA")
			public ModelAndView SaveQA(Model model,HttpServletRequest request,HttpServletResponse response){
				User user=(User)request.getSession().getAttribute("user");
				String solveid=request.getParameter("solveid");
				String quesid=request.getParameter("quesid");
				/*先判断该解决方法中的iscomfirm是什么情况*/
				String iscomfirm=String.valueOf(operation.selectIsComfirm(Integer.valueOf(solveid)));
				/*iscomfirm若为1，则进行下一步*/
				if(iscomfirm.equals("1")){
					/*再判断用户是否选择确定问题，是则存储QA确认人，关闭问题，添加关闭时间*/
					if(request.getParameter("QAcomfirm").equals("0")){
						operation.saveQA(user.getTu_user_name(), Integer.valueOf(request.getParameter("solveid")));
						operation.updateClosetime(new Date(), Integer.valueOf(quesid));
						operation.updateState(0, Integer.valueOf(quesid));
						return new ModelAndView("newSucc");
					}
					/*若不是，则什么都不做*/
					else
					{
						return new ModelAndView("newSucc");
					}
				}
				/*iscomfirm为null，则说明还没有回复解决方法；为0，则还没有确定*/
				else 
				return new ModelAndView("NoComfirm","error7","问题无法确认！");
			}
	}


