package com.tcl.inter;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tcl.model.Question;
public interface Operation {
	public List<Question> selectQuesById(int ques_id);
	public void updateQues(Question question);
	public String selectReasonById(int tr_id);
	public void insertReason(String reason);
	public int getReasonId();
	public void updateReasonId(@Param("tr_id")int tr_id,@Param("quesid")int quesid);
	public void updateReason(@Param("reason")String reason,@Param("tr_id")int tr_id);
	public void updateSolve0(@Param("ts_id")int ts_id,@Param("tem_metnod")String tem_metnod,
			@Param("basic_method")String basic_method,@Param("plan_tim")String plan_tim,
			@Param("head")String head,@Param("comfirm")String comfirm);
	public void updateSolve(@Param("ts_id")int ts_id,@Param("tem_metnod")String tem_metnod,
			@Param("head")String head,@Param("comfirm")String comfirm);
	public void updateSolve2(@Param("ts_id")int ts_id,@Param("basic_method")String basic_method,@Param("plan_tim")String plan_tim);
	public void insertSolve0(@Param("tem_metnod")String tem_metnod,
			@Param("basic_method")String basic_method,@Param("plan_tim")String plan_tim,
			@Param("head")String head,@Param("comfirm")String comfirm);
	public void insertSolve(@Param("tem_metnod")String tem_metnod,
			@Param("head")String head,@Param("comfirm")String comfirm);
	public void insertSolve2(@Param("basic_method")String basic_method,@Param("plan_tim")String plan_tim);
	public int getSolveId();
	public void updateSolveId(@Param("ts_id")int ts_id,@Param("quesid")int quesid);
	public void updateDeptSort(@Param("dept")int dept,@Param("sort")int sort,@Param("quesid")int quesid);
	public void updateClosetime(@Param("closetime")Date closetime,@Param("quesid")int quesid);
	public void updateState(@Param("state")int state,@Param("quesid")int quesid);
	public void saveQA(@Param("QA")String QA,@Param("solveid")int solveid);
	public List selectRoleByUserId(int userid);
	public void saveFirstId(@Param("firstid")int firstid,@Param("quesid")int quesid);
	public void saveSecondId(@Param("secondid")int secondid,@Param("quesid")int quesid);
	public void saveComfirmName(@Param("comfirmname")String comfirmname,@Param("solveid")int solveid);
	public Object selectIsComfirm(@Param("solveid")int solveid);
	public void deleteFirstId(int quesid);
	public void deleteSecondId(int quesid);
}
