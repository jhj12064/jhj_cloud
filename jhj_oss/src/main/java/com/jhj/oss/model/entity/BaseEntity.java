package com.jhj.oss.model.entity;

import com.jhj.common.enums.DeleteFlag;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
@TypeDefs(@TypeDef(name = "json", typeClass = JsonStringType.class))
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    @Id
    @GenericGenerator(name = "snowflakeId", strategy = "com.jhj.oss.config.GenerateSnowflakeId")
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private DeleteFlag deleteFlag;

    @CreatedDate
    private Timestamp createTime;

    @LastModifiedDate
    private Timestamp updateTime;

    public BaseEntity() {
        this.deleteFlag = DeleteFlag.NORMAL;
    }

    public BaseEntity delete() {
        this.deleteFlag = DeleteFlag.DELETE;
        return this;
    }
}
