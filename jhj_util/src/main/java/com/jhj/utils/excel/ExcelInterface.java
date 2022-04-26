package com.jhj.utils.excel;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ExcelInterface {

    ExcelExportBean createWorkBook();

    <T> List <String[]> getRows(List <T> list) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

}
