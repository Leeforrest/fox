package com.coolfish.gmserver.mybatis.mapper;

import java.util.List;

import com.coolfish.gmserver.mvc.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User selectByUserName(String userName);
	
	List<User> select();
}