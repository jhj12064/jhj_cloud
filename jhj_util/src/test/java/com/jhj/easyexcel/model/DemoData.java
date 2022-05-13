package com.jhj.easyexcel.model;


import com.jhj.utils.DateUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
//@ToString
public class DemoData {
    private String string;
    private Date date;
    private Double doubleData;

    @Override
    public String toString() {
        return "DemoData{" +
                "string='" + string + '\'' +
                ", date=" + DateUtil.styleDate(date, "yyyy/MM/dd HH:mm:ss") +
                ", doubleData=" + doubleData +
                '}';
    }
}