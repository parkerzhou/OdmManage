package com.tcl.inter;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tcl.model.*;

public interface Ques {
	public List<Question> selectTodayNew(@Param("workshop")String workshop,@Param("today")String today);
	public List<Question> selectUnClose(@Param("workshop")String workshop);
	public List<Question> selectCloseQuestion(@Param("workshop")String workshop);
	public List<Question> selectAllQuestion(@Param("workshop")String workshop);
	public List selectLine();
	public List selectClient();
	public List selectDept();
	public List selectSort();
	public void insertQuestion(Question question);
	public void addClient(String client);
}
