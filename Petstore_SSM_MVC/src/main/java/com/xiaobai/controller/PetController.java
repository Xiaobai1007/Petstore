package com.xiaobai.controller;


import com.google.gson.Gson;
import com.xiaobai.dao.PetMapper;
import com.xiaobai.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/pet")
@Controller
public class PetController {

    @Autowired
    private PetMapper petMapper;

    @GetMapping()
    public String pet(){
        return "pet";
    }

    @PostMapping(value = "/insertPet", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String insertPet(Pet pet){
        petMapper.insert(pet);
        return "{\"msg\":\"宠物添加成功\"}";
    }

    @PostMapping(value = "/updatePet", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updatePet(Pet pet){
        petMapper.updateByPrimaryKey(pet);
        return "{\"msg\":\"宠物信息更新成功\"}";
    }

    @PostMapping(value = "/deletePet", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deletePet(int petId){
        petMapper.deleteByPrimaryKey(petId);
        return "{\"msg\":\"删除成功\"}";
    }

    @GetMapping(value = "/selectByPetStatus", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String selectByPetStatus(Pet pet){
        List<Pet> petList = petMapper.selectByPetStatus(pet);
        return new Gson().toJson(petList);
    }

    @GetMapping(value = "/selectByPetId", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String selectByPetId(Pet pet){
        List<Pet> petList = petMapper.selectByPrimaryKey(pet);
        System.out.println(petList);
        return new Gson().toJson(petList);
    }

    @GetMapping(value = "/selectPetAll", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String selectPetAll(){
        List<Pet> petList = petMapper.selectAll();
        return new Gson().toJson(petList);
    }

}
