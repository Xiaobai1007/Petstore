package com.xiaobai.controller;

import com.google.gson.Gson;
import com.xiaobai.dao.OrderMapper;
import com.xiaobai.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping("/createOrder")
    public String createOrder(Order order){
        orderMapper.insert(order);
        return "index";
    }

    @GetMapping("/selectByOrderId")
    @ResponseBody
    public String selectByOrderId(int orderId){
        Order order = orderMapper.selectByPrimaryKey(orderId);
        return new Gson().toJson(order);
    }

    @GetMapping("/deleteByOrderId")
    public String deleteByOrderId(int orderId){
        orderMapper.deleteByPrimaryKey(orderId);
        return "index";
    }
}
