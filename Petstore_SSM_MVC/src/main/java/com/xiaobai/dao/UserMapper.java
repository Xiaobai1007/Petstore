package com.xiaobai.dao;

import com.xiaobai.entity.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    int login(String userName, String userPassword);

    List<User> selectByUserName(User user);
}