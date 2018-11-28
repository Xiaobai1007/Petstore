package com.xiaobai.controller;

import com.google.gson.Gson;
import com.xiaobai.dao.UserMapper;
import com.xiaobai.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping("/cors")
    @ResponseBody
    public String cors(@RequestParam(defaultValue = "callback") String callback, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","http://192.168.1.226:8081/index");
        return callback+"('hello')";
    }

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET)
    public String home(){
        return "login";
    }

    @RequestMapping(value = "/success")
    public String index(HttpServletRequest request){
        String user = (String) request.getSession().getAttribute("user");
        if(!user.isEmpty())
            return "index";
        return "login";
    }

    @GetMapping("/updateForm")
    public String updateForm(Model model, int userId){
        model.addAttribute("userId", userId);
        return "updateForm";
    }

    @GetMapping("/insertForm")
    public String insertForm(){
        return "insertForm";
    }

    @GetMapping(value = "/login", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(String userName, String userPassword, HttpServletRequest request){
        int count = userMapper.login(userName, userPassword);
        if(count > 0){
            request.getSession().setAttribute("user",userName);
            return "{\"msg\":\"success\"}";
        }
        return "{\"msg\":\"fail\"}";
    }

    @GetMapping(value = "/logout", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response){
        if(request.getSession().getAttribute("user") != null){
            request.getSession().removeAttribute("user");
            try {
                response.sendRedirect("/user");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "{\"msg\":\"你还未登录账号\"}";
    }

    @GetMapping(value = "/findAllUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findAllUser(){
        List<User> userList = userMapper.selectAll();
        return new Gson().toJson(userList);
    }

    @GetMapping(value = "/finByIdUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findByIdUser(int userId){
        User user = userMapper.selectByPrimaryKey(userId);
        return new Gson().toJson(user);
    }

    @GetMapping(value = "/findByUserName", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findByUserName(User record){
        List<User> user = userMapper.selectByUserName(record);
        return new Gson().toJson(user);
    }


    @PostMapping(value = "/createUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String createUser(User user){
        userMapper.insert(user);
        return "{\"msg\":\"创建成功\"}";
    }

    @PostMapping(value = "/updateUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateUser(User user){
        userMapper.updateByPrimaryKey(user);
        return "{\"msg\":\"修改成功\"}";
    }

    @GetMapping(value = "/deleteUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteUser(int userId){
        userMapper.deleteByPrimaryKey(userId);
        return "{\"msg\":\"删除成功\"}";
    }
}
