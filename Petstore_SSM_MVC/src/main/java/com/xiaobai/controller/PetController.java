package com.xiaobai.controller;


import com.google.gson.Gson;
import com.xiaobai.dao.PetMapper;
import com.xiaobai.entity.Category;
import com.xiaobai.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    @GetMapping("/updatePetForm")
    public String updatePetForm(int petId,Model model){
        model.addAttribute("petId",petId);
        return "updatePetForm";
    }

    @GetMapping("/insertPetForm")
    public String insertPetForm(){
        return "insertPetForm";
    }

    @PostMapping(value = "/insertPet", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String insertPet(Category category,Pet pet){
        pet.setCategoryInfo(category);
        petMapper.insert(pet);
        return "{\"msg\":\"宠物添加成功\"}";
    }

    @PostMapping(value = "/updatePet", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updatePet(Category category, Pet pet){
        pet.setCategoryInfo(category);
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
        return new Gson().toJson(petList);
    }

    @GetMapping(value = "/selectPetAll", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String selectPetAll(){
        List<Pet> petList = petMapper.selectAll();
        return new Gson().toJson(petList);
    }

    //下载文件
    @PostMapping(value = "/input", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String imageshangchuan(@RequestPart("multipartFile") MultipartFile multipart, Model model) {
        String file = multipart.getOriginalFilename();
        try {
            //获取文件格式
            BufferedImage image = ImageIO.read(multipart.getInputStream());
            //判断格式，大小是否符合要求
            if (image != null && multipart.getSize() < 1024*1024*1024*5 && !multipart.isEmpty()) {
                //生成文件
                multipart.transferTo(new File("E:/JavaIdea/Petstore_SSM_MVC/src/main/webapp/images/"+file));
                return "{\"img\":\""+file+"\"}";
            } else {
                return "{\"err\":\"上传失败\"}";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
