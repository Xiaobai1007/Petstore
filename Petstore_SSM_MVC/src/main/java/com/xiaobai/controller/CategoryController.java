package com.xiaobai.controller;

import com.google.gson.Gson;
import com.xiaobai.dao.CategoryMapper;
import com.xiaobai.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/category")
@Controller
public class CategoryController {

    @Autowired
    CategoryMapper categoryMapper;

    @GetMapping(produces = "application/json;charset=utf-8")
    @ResponseBody
    public String selectfindAll(){
        List<Category> categoryList = categoryMapper.selectAll();
        return new Gson().toJson(categoryList);
    }

}
