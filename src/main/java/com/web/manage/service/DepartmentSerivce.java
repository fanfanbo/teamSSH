package com.web.manage.service;

import com.web.base.service.BaseService;
import com.web.manage.entity.Department;
import com.web.util.Pager;
import com.web.vo.DepartmentVO;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
public interface DepartmentSerivce extends BaseService<Department, String> {
    Pager getDepartmentListByPage(Integer pageNumber,Integer pageSize,String departmentName,String departmentParentName);

    List<Department> getAllList();

    List<DepartmentVO> getList();

    Department getFromId(String id);

    Department getDById(String id);

    DepartmentVO getIdToVO(String id);

    void fromSave(DepartmentVO departmentVO);

    void fromEdit(DepartmentVO departmentVO);
}
