package com.jhj.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageInfo implements Serializable {
    private static final long serialVersionUID = 854080164317948150L;
    @ApiModelProperty(hidden = true)
    private long offset = 0;
    @ApiModelProperty("多少条")
    private long limit = 25;
    @ApiModelProperty("第几页")
    private long page = 1;

    public long getOffset() {
        return Math.max(page - 1, 0) * limit;
    }
}
