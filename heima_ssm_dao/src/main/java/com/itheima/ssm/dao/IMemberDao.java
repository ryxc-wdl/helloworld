package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IMemberDao {

    //会员信息查询

    @Select("select * from member where id=#{memberId}")
   public Member findById(String memberId)throws Exception;



}
