package com.tcl.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tcl.model.Question;

public interface Query {
       public List<Question> queryQuesByData(@Param("stime")String stime,@Param("ctime")String ctime,
    		   @Param("workshop")String workshop);
       public List<Question> queryQuesByKeyWords(@Param("kwords")String kwords,@Param("workshop")String workshop);
}
