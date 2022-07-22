package com.jhj.alibaba.dingtalk.model;

import lombok.Data;

import java.util.List;

/**
 * 部门——树
 *
 */
@Data
public class DepartmentTreeVO {

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父部门id
     */
    private Long parentId;

    /**
     * 是否同步创建一个关联此部门的企业群：
     */
    private boolean createDeptGroup;

    /**
     * 部门群已经创建后，有新人加入部门是否会自动加入该群：
     */
    private boolean autoAddUser;

    /**
     * 子部门集合
     */
    private List<DepartmentTreeVO> departmentTreeSubs;

}
