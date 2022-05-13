package com.jhj.oss.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
/**
 * @author Jeremy
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FileTest extends BaseEntity{

    @ApiModelProperty("原文件名")
    private String originalName;

    //@Column(unique = true)
    @ApiModelProperty("文件url")
    private String url;

    private String contentType;

    private long fileSize;
}
