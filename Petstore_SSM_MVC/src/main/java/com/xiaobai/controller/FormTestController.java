package com.xiaobai.controller;

import com.xiaobai.dao.PetMapper;
import com.xiaobai.entity.Pet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.RichTextString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class FormTestController {

    @Autowired
    private PetMapper petMapper;

    @GetMapping("/form")
    public String input() {
        return "formtest";
    }

    @GetMapping("/formpost")
    public String input2() {
        return "formtest";
    }

    @PostMapping("/formpost")
    public String input3(MultipartFile ddd, String aaa, int bbb, String ccc) {
        return "formtest";
    }

    @PostMapping("/myupload")
    public @ResponseBody String myupload(MultipartFile fff) throws IOException {
        fff.transferTo(new File("d:/aaa.jpg"));
        return "sucess";
    }

    @GetMapping("/myupload")
    public String myup () {
        return "fileUpload";
    }

    @GetMapping("/download")
    ResponseEntity<byte[]> download() throws IOException {
        byte[] contents = test().toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("no-cache, no-store, must-revalidate");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(contents.length);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode("宠物信息表.xls", "utf-8"));
        return ResponseEntity.ok()
                .headers(headers)
                .body(contents);
    }

    public ByteArrayOutputStream test(){

        List<Pet> petList = petMapper.selectAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("宠物信息表");
        HSSFRow row = sheet.createRow(1);
        row.createCell(1).setCellValue("宠物编号");
        row.createCell(2).setCellValue("宠物种类");
        row.createCell(3).setCellValue("宠物名称");
        row.createCell(4).setCellValue("宠物价格");
        row.createCell(5).setCellValue("宠物照片");
        row.createCell(6).setCellValue("宠物性格");
        row.createCell(7).setCellValue("宠物状态");

        int i = 2;
        for (Pet pet : petList) {
            row = sheet.createRow(i);
            row.createCell(1).setCellValue(pet.getPetId());
            row.createCell(3).setCellValue(pet.getCategoryInfo().getCategoryName());
            row.createCell(2).setCellValue(pet.getPetName());
            row.createCell(4).setCellValue(String.valueOf(pet.getPetPrice()));
            row.createCell(5).setCellValue(pet.getPetPhoto());
            row.createCell(6).setCellValue(pet.getPetTag());
            row.createCell(7).setCellValue(pet.getPetStatus());
            i++;
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            workbook.write(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }
}
