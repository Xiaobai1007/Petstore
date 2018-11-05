package com.xiaobai.controller;


import com.google.gson.Gson;
import com.xiaobai.dao.PetMapper;
import com.xiaobai.dao.PhotoMapper;
import com.xiaobai.entity.Pet;
import com.xiaobai.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/pet")
@Controller
public class PetController {

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private PhotoMapper photoMapper;

    @PostMapping("/insertPet")
    @ResponseBody
    public String insertPet(Pet pet){
        petMapper.insert(pet);
        return "{\"msg\":\"宠物添加成功\"}";
    }

    @PostMapping("/updatePet")
    @ResponseBody
    public String updatePet(Pet pet){
        petMapper.updateByPrimaryKey(pet);
        return "{\"msg\":\"宠物信息更新成功\"}";
    }

    @GetMapping("/selectByPetStatus")
    @ResponseBody
    public String selectByPetStatus(String petStatus){
        Pet pet = petMapper.selectByPetStatus(petStatus);
        return new Gson().toJson(pet);
    }

    @GetMapping("/selectByPetId")
    @ResponseBody
    public String selectByPetId(int petId){
        Pet pet = petMapper.selectByPrimaryKey(petId);
        return new Gson().toJson(pet);
    }

    @PostMapping("/deletePet")
    @ResponseBody
    public String deletePet(int petId){
        petMapper.deleteByPrimaryKey(petId);
        return "{\"msg\":\"删除成功\"}";
    }

    @PostMapping("/inputPhoto")
    @ResponseBody
    public String inputPhoto(Photo photo){
        photoMapper.insert(photo);
        return "{\"msg\":\"上传成功\"}";
    }
}
