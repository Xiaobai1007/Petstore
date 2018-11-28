package com.xiaobai.poi;

import com.xiaobai.dao.PetMapper;
import com.xiaobai.entity.Pet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class SqlPOIOutUp {

    public static void main(String[] args) {
        test();
    }

    public static void test(){

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

        PetMapper petMapper = null;

        int i = 2;

        try {
            OutputStream fileOut = new FileOutputStream("d:/宠物信息表.xls");
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
