package com.jhj.alibaba.dingtalk;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserListsimpleRequest;
import com.dingtalk.api.request.OapiV2DepartmentListsubRequest;
import com.dingtalk.api.response.OapiUserListsimpleResponse;
import com.dingtalk.api.response.OapiV2DepartmentListsubResponse;
import com.jhj.alibaba.dingtalk.constant.DingTalkConstant;
import com.jhj.alibaba.dingtalk.model.DepartmentTreeVO;
import com.jhj.alibaba.dingtalk.model.DepartmentUserVO;
import com.jhj.utils.BeanCopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.jhj.alibaba.dingtalk.constant.DingTalkConstant.CLIENT_URL_GET_DEPARTMENT_LISTSUB;
import static com.jhj.alibaba.dingtalk.constant.DingTalkConstant.CLIENT_URL_GET_DEPARTMENT_USER;


/**
 */
@Slf4j
@Component
public class DingTalkDepartInterface {

    private static long size = 100L;

    @Autowired
    DingTalkTokenInterface dingTalkTokenInterface;


    /**
     * 获取钉钉所有的部门集合
     */
    public List<DepartmentTreeVO> queryAllDingTalkDepartList() {
        try {
            return this.queryDepartmentTree(DingTalkConstant.DEPT_ID_ROOT, dingTalkTokenInterface.getAccessToken());
        } catch (Exception e) {
            log.error("queryAllDingTalkDepartList_error", e);
            return null;
        }
    }


    /**
     * 根据部门id获取所有用户
     *
     * @param departId 部门id
     * @return
     */
    public List<DepartmentUserVO> queryUserBYDepartId(Integer departId) {
        try {
            if (departId == null) {
                return new ArrayList<>();
            }
            String accessToken = dingTalkTokenInterface.getAccessToken();
            LinkedList<DepartmentUserVO> list = new LinkedList<>();
            setDepartmentUserVOs(departId.longValue(), 0L, accessToken, list);
            return list;
        } catch (Exception e) {
            log.error("queryAllDingTalkDepartList_error", e);
            return null;
        }
    }

    /**
     * 调用钉钉接口查询部门用户
     *
     * @param cursor 分页游标
     * @return
     */
    private void setDepartmentUserVOs(long departId, long cursor, String accessToken, List<DepartmentUserVO> list) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient(CLIENT_URL_GET_DEPARTMENT_USER);
        OapiUserListsimpleRequest req = new OapiUserListsimpleRequest();
        req.setDeptId(departId);
        req.setCursor(cursor);
        req.setSize(100L);
        req.setOrderField("modify_desc");
        req.setContainAccessLimit(false);
        req.setLanguage(DingTalkConstant.LANGUAGE_CN);
        OapiUserListsimpleResponse rsp = client.execute(req, accessToken);
        if (rsp == null || !DingTalkConstant.SUCCESS_CODE.equals(rsp.getErrorCode())) {
            log.error("queryUserBYDepartId_error");
            throw new Exception("queryUserBYDepartId_error");
        }
        OapiUserListsimpleResponse.PageResult result = rsp.getResult();
        List<OapiUserListsimpleResponse.ListUserSimpleResponse> resultList = result.getList();
        for (OapiUserListsimpleResponse.ListUserSimpleResponse userResult : resultList) {
            DepartmentUserVO departmentUserVO = new DepartmentUserVO();
            departmentUserVO.setUserId(userResult.getUserid());
            departmentUserVO.setName(userResult.getName());
            list.add(departmentUserVO);
        }
        //如果还有数据，循环查
        if (result.getHasMore()) {
            setDepartmentUserVOs(departId, result.getNextCursor(), accessToken, list);
        }
    }


    /**
     * 根据部门Id获取子部门列表（递归）
     *
     * @param deptId      部门Id
     * @param accessToken token
     * @return 所有子部门列表
     * @throws Exception
     */
    public List<DepartmentTreeVO> queryDepartmentTree(Long deptId, String accessToken) throws Exception {
        List<DepartmentTreeVO> departmentTreeVOS = this.queryDingTalkDepartListByDeptId(deptId, accessToken);
        if (!CollectionUtils.isEmpty(departmentTreeVOS)) {
            for (DepartmentTreeVO departmentTreeVO : departmentTreeVOS) {
                List<DepartmentTreeVO> sonDepartmentTree = queryDepartmentTree(departmentTreeVO.getDeptId(), accessToken);
                departmentTreeVO.setDepartmentTreeSubs(sonDepartmentTree);
            }
        }
        return departmentTreeVOS;
    }

    /**
     * 根据部门Id获取子部门集合
     *
     * @param deptId      部门id
     * @param accessToken token
     * @return 子部门集合
     * @throws Exception
     */
    private List<DepartmentTreeVO> queryDingTalkDepartListByDeptId(Long deptId, String accessToken) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient(CLIENT_URL_GET_DEPARTMENT_LISTSUB);
        OapiV2DepartmentListsubRequest req = new OapiV2DepartmentListsubRequest();
        req.setDeptId(deptId);
        req.setLanguage(DingTalkConstant.LANGUAGE_CN);
        OapiV2DepartmentListsubResponse rsp = client.execute(req, accessToken);
        if (rsp == null || !DingTalkConstant.SUCCESS_CODE.equals(rsp.getErrorCode())) {
            log.error("queryDingTalkDepartListByDeptId_error");
            throw new Exception("queryDingTalkDepartListByDeptId_error");
        }
        return CollectionUtils.isEmpty(rsp.getResult()) ? null : BeanCopyUtil.copyListProperties(rsp.getResult(), DepartmentTreeVO::new);
    }


}
