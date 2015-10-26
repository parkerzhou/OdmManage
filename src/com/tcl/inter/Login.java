package com.tcl.inter;

import org.apache.ibatis.annotations.Param;

import com.tcl.model.User;

public interface Login {
	public User findUserByUserName(String username);
	public int selectTodayNewNum(@Param("workshop")String workshop,@Param("today")String today);
	public int selectNnCloseQuesNum(@Param("workshop")String workshop);
	public int selectClosedQuesNum(@Param("workshop")String workshop);
	public void changepw(@Param("userid")int userid,@Param("password")String password);
}
