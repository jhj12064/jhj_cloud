package com.jhj.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExcelUtil {

    private static Workbook wb;

    private static Sheet sheet;

    private static Row row;

    private static void getWorkbook(InputStream in, String fileName) {
        try {
            if (fileName.endsWith(".xlsx")) {
                wb = new XSSFWorkbook(in);
            }else if (fileName.endsWith(".xls")) {
                wb = new HSSFWorkbook(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Map<String,String>> readExcel(InputStream in, String fileName){
        getWorkbook(in,fileName);
        List<Map<String,String>> excelList = new LinkedList<>();
        Map<Integer,String> excelHeader = readExcelTitle();
        Map<Integer,Map<Integer,String>> excelContent = readExcelContent();
        for (Map.Entry<Integer,Map<Integer,String>> row : excelContent.entrySet()) {// 一行一行循环 组装 column：value
            Map<String,String> listValueMap = new HashMap<>();
            for(Map.Entry<Integer,String> cell : row.getValue().entrySet()){
                listValueMap.put(excelHeader.get(cell.getKey()),cell.getValue());
            }
            excelList.add(listValueMap);
        }
        return excelList;
    }
    /**
     * 读取表头
     * @return
     */
    private static Map<Integer,String> readExcelTitle() {
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        Map<Integer,String> map = new HashMap<>();
        for (int i = 0; i < colNum; i++) {
            map.put(i, row.getCell(i).getStringCellValue());
        }
        return map;
    }

    /**
     * 读取内容
     * @return
     */
    private static Map<Integer,Map<Integer,String>> readExcelContent() {
        Map<Integer,Map<Integer,String> > content = new HashMap<>();
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int z = 0;
            Map<Integer,String> cellValue = new HashMap<>();
            while (z < colNum) {
                String obj = getCellFormatValue(row.getCell(z));
                cellValue.put(z, obj);
                z++;
            }
            content.put(i, cellValue);
        }
        return content;
    }

    private static String getCellFormatValue(Cell cell) {
        String cellValue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellTypeEnum()){
                case NUMERIC:
                    DecimalFormat df = new DecimalFormat("0");
                    cellValue = df.format(cell.getNumericCellValue());
                    break;
                case FORMULA:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
                        Instant instant = cell.getDateCellValue().toInstant();
                        ZoneId zoneId = ZoneId.systemDefault();
                        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
                        cellValue = dateTimeFormatter.format(localDateTime);
                    } else {
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case STRING:
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                case BLANK:
                    cellValue = "";
                    break;
                case ERROR:
                    cellValue = "非法格式";
                    break;
                case _NONE:
                    cellValue = "未知数据类型";
                    break;
            }
        }
        return cellValue;
    }

}
