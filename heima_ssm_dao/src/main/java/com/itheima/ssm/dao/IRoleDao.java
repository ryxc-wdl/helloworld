package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);


    //查询用户的时候  根据用户id查询出所有对应的角色,并通过多表查询 角色和权限表 查出 权限名
    @Select("select * from role where id in(select * from users_role  where id=#{userid})")
    @Results({
            @Result(id = true,column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            //根据实际要求增加permission，     根据id 作为联合表role_user的外键  进行联合、嵌套查询
            @Result(property = "permissions",column = "id",javaType =java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId"))

    })
    public List<Role > findRoleByUserId(String userid);
}
