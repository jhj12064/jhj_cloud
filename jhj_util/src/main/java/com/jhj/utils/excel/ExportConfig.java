package com.jhj.utils.excel;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
/**
 * @author Jeremy
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExportConfig {
    private static final long serialVersionUID = -8927829417286200015L;
    @ApiModelProperty(value = "表头列说明")
    private String[] cellHeaders = new String[0];

    @ApiModelProperty(value = "取值")
    private String[] dbColumn = new String[0];

    @ApiModelProperty(value = "文件名")
    private String fileName = String.valueOf(System.currentTimeMillis());

    @ApiModelProperty(value = "sheet名")
    private String sheetName = "sheet";

    public ExportConfig transferDbClassAddGetPrefix(){
        for (int i = 0; i < dbColumn.length; i++) {
            if(StringUtils.isNotEmpty(dbColumn[i])){
                char[] chars = dbColumn[i].toCharArray();
                if(chars[0]>='a' && chars[0]<='z'){
                    chars[0] = (char) (chars[0]-32);
                }
                dbColumn[i] = "get" +new String(chars);
            }
        }
        return this;
    }
}
