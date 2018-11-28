package com.xiaobai.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SqlPOIInUp {

    public static void main(String[] args) {
        inPuttest();
    }

    public static void inPuttest() {
        try {
            Workbook workbook = readExcelFromFileName("d:/宠物信息表.xls");
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(2);
            String value = row.getCell(2).getStringCellValue();
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Workbook readExcelFromFileName(String fileName) throws Exception {
        FileInputStream fis;
        Workbook workbook;
        try{
            fis = new FileInputStream(fileName);
            workbook = new HSSFWorkbook(fis);
            fis.close();
        }catch (OfficeXmlFileException e){
            fis = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(fis);
            fis.close();
        }
        return workbook;
    }
}
