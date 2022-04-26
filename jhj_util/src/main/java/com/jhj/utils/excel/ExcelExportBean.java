package com.jhj.utils.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelExportBean implements Serializable {

    XSSFSheet xssfSheet;

    String[] dbColumn;

    XSSFWorkbook workbook;
}
