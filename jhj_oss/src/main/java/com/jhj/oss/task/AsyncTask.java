package com.jhj.oss.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AsyncTask {


    @Transactional(rollbackFor = Exception.class)
    @Async("asyncTask")
    public void syncDeptFromDingTalk() {

        /*List<DepartmentTreeVO> departmentTreeVOS = dingTalkDepartInterface.queryAllDingTalkDepartList();
        List<Department> list = new LinkedList<>();
        if (!CollectionUtils.isEmpty(departmentTreeVOS)) {
            departmentTreeVOS.forEach(e -> {
                Long pid = -1L;
                loopDepartmentTree(pid, e, list);
            });
        }*/
    }

   /* private void loopDepartmentTree(Long pid, DepartmentTreeVO parent, List<Department> children) {
        if (parent != null) {
            Department department = new Department();
            department.setName(parent.getName());
            department.setPid(pid);
            department.setId(parent.getDeptId());
            departmentDao.save(department);
            children.add(department);
            if (!CollectionUtils.isEmpty(parent.getDepartmentTreeSubs())) {
                pid = department.getId();
                for (DepartmentTreeVO departmentTreeVO : parent.getDepartmentTreeSubs()) {
                    loopDepartmentTree(pid, departmentTreeVO, children);
                }
            }
        }
    }*/

    /**
     * 字符串拼接
     *
     * @param delimiter
     * @param Strings
     * @return
     */
    private String stringJoin(String delimiter, String... Strings) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Strings.length; i++) {
            if (StringUtils.isNotBlank(Strings[i])) {
                list.add(Strings[i]);
            }
        }
        return String.join(delimiter, list.toArray(new String[list.size()]));
    }


}
