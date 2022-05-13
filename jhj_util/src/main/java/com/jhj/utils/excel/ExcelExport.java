package com.jhj.utils.excel;

import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * @author Jeremy
 */
@Component
public class ExcelExport extends AbstractExcelExport implements ExcelInterface {

    private ExportConfig config;

    //将list根据config 转化为 List <String[]>
    @Override
    public <T> List <String[]> getRows(List <T> list) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] dbColumns = config.getDbColumn();
        if (!CollectionUtils.isEmpty(list)) {
            T t0 = list.get(0);
            Class <?> aClass = t0.getClass();
            List <Method> methods = new LinkedList <>();
            int length = dbColumns.length;
            for (String dbColumn : dbColumns) {
                Method method = aClass.getMethod(dbColumn);
                methods.add(method);
            }
            ArrayList <String[]> rows = new ArrayList <>();
            for (T t : list) {
                String[] row = new String[length];
                for (int i = 0; i < methods.size(); i++) {
                    Method method = methods.get(i);
                    Object obj = method.invoke(t);
                    if (null == obj) {
                        row[i] = "";
                    } else {
                        row[i] = obj.toString();
                    }
                }
                rows.add(row);
            }
            return rows;
        }
        return null;
    }

    @Override
    public ExcelExportBean createWorkBook() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(config.getSheetName());
        String[] headers = config.getCellHeaders();
        XSSFRow row0 = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row0.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        String[] dbColumn = config.transferDbClassAddGetPrefix().getDbColumn();
        return new ExcelExportBean(sheet, dbColumn, workbook);
    }

    @Override
    public void setExcelBean(ExportConfig config) {
        this.config = config;
    }
}
