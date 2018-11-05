package com.xiaobai.controller;

import com.xiaobai.dao.UserMapper;
import com.xiaobai.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET)
    public String home(){
        return "login";
    }

    @GetMapping("/login")
    @ResponseBody
    public String login(User user, HttpServletRequest request){
        int count = userMapper.login(user);
        if(count > 0){
            request.getSession().setAttribute("user",user.getUserName());
            return "{\"msg\":\"登录成功\"}";
        }
        return "{\"msg\":\"用户名或密码错误\"}";
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request){
        if(request.getSession().getAttribute("user") != null){
            return "login";
        }else{
            return "{\"msg\":\"你还未登录账号\"}";
        }
    }

    @PostMapping("createUser")
    @ResponseBody
    public String createUser(User user){
        userMapper.insert(user);
        return "{\"msg\":\"创建成功\"}";
    }

    @PostMapping("updateUser")
    @ResponseBody
    public String updateUser(User user){
        userMapper.updateByPrimaryKey(user);
        return "{\"msg\":\"修改成功\"}";
    }

    @PostMapping("deleteUser")
    @ResponseBody
    public String deleteUser(int userId){
        userMapper.deleteByPrimaryKey(userId);
        return "{\"msg\":\"删除成功\"}";
    }
}
