//package com.jhj.utils.scan;
//
//import cn.hutool.core.lang.ClassScanner;
//import cn.hutool.core.map.TableMap;
//import com.jhj.model.annotation.BatchSynchTable;
//import com.jhj.model.annotation.TransformRule;
//import com.jhj.model.common.ColumnMap;
//import com.jhj.model.common.Rule;
//import com.jhj.model.common.TableMap;
//import lombok.extern.slf4j.Slf4j;
//
//import java.lang.reflect.Field;
//import java.util.*;
//
///**
// * @description:表扫描
// * @program:wq_dwh
// * @author:Wang.Fuming
// * @creat:2022-04-27-13:47
// **/
//@Slf4j
//public class TableMapScan {
//
//    public static Map <String, TableMap> packageScan(String packageName) {
//        Set <Class <?>> classes = ClassScanner.scanPackageByAnnotation(packageName, BatchSynchTable.class);
//        if (classes == null || classes.size() == 0) {
//            log.debug("无相关类需要操作");
//            return null;
//        }
//        return scan(classes);
//    }
//
//    public static Map <String, TableMap> classScan(String className) {
//        try {
//            Class <?> clazz = Class.forName(className);
//            Set <Class <?>> classes = new HashSet <>();
//            classes.add(clazz);
//            return scan(classes);
//        } catch (ClassNotFoundException e) {
//            log.error("该类不存在");
//            return null;
//        }
//    }
//
//    private static Map <String, TableMap> scan(Set <Class <?>> classes) {
//        HashMap <String, TableMap> map = new HashMap <>();
//        classes.forEach(
//                clazz -> {
//                    BatchSynchTable batchSynchTable = clazz.getAnnotation(BatchSynchTable.class);
//                    if (!batchSynchTable.openSynch()) {
//                        return;
//                    }
//                    TableMap tableMap = new TableMap();
//                    tableMap.setClassName(clazz.getName());
//                    tableMap.setClassSimpleName(clazz.getSimpleName());
//
//                    String source = batchSynchTable.source();
//                    String sink = batchSynchTable.sink();
//                    String sourceId = batchSynchTable.pkId();
//                    tableMap.setSourceTable(source);
//                    tableMap.setSinkTable(sink);
//
//                    Field[] fields = clazz.getDeclaredFields();
//                    List <ColumnMap> columnMaps = new LinkedList <>();
//                    for (Field field : fields) {
//                        boolean annotationPresent = field.isAnnotationPresent(TransformRule.class);
//                        if (annotationPresent) {
//                            TransformRule transformRule = field.getAnnotation(TransformRule.class);
//                            if (!transformRule.ignore()) {
//                                if (field.getName().equals(sourceId)) {
//                                    tableMap.setSinkPk(transformRule.sinkColName());
//                                }
//                                ;
//                                List <Rule> rules = Arrays.asList(transformRule.rule());
//                                ColumnMap columnMap = new ColumnMap(field.getName(), field.getType().getSimpleName(), transformRule.sinkColName(), transformRule.dataType().getSimpleName(), rules);
//                                setMethodName(columnMap, new StringBuilder(field.getName()));
//                                columnMaps.add(columnMap);
//                            }
//                        }
//                    }
//                    if (columnMaps.size() > 0) {
//                        tableMap.setColumnMaps(columnMaps);
//                        map.put(clazz.getSimpleName(), tableMap);
//                    }
//
//                }
//        );
//        return map;
//    }
//
//    private static void setMethodName(ColumnMap columnMap, StringBuilder sourceName) {
//        if (Character.isLowerCase(sourceName.charAt(0))) {
//            if (sourceName.length() == 1 || !Character.isUpperCase(sourceName.charAt(1))) {
//                sourceName.setCharAt(0, Character.toUpperCase(sourceName.charAt(0)));
//                columnMap.setSetterName("set" + sourceName);
//                columnMap.setGetterName("get" + sourceName);
//            }
//        }
//    }
//}
