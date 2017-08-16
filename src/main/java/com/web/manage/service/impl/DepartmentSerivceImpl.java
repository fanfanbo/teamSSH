package com.web.manage.service.impl;

import com.web.base.service.impl.BaseServiceImpl;
import com.web.manage.dao.DepartmentDao;
import com.web.manage.entity.Department;
import com.web.manage.service.DepartmentSerivce;
import com.web.util.Pager;
import com.web.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
@Service
public class DepartmentSerivceImpl extends BaseServiceImpl<Department,String> implements DepartmentSerivce{
    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        super.setBaseDao(departmentDao);
    }

    @Override
    public Department getDById(String id) {
        return departmentDao.get(id);
    }

    @Override
    public Pager getDepartmentListByPage(Integer pageNumber, Integer pageSize,String departmentName,String departmentParentName) {
        List<Department> list = departmentDao.getDepartmentListByPage(pageNumber,pageSize,departmentName,departmentParentName);
        List<DepartmentVO> departmentVOList = new ArrayList<DepartmentVO>();
        if (list != null && !list.isEmpty()) {
            for (Department department : list) {
                DepartmentVO departmentVO = DepartmentVO.fromDepartment(department);
                departmentVOList.add(departmentVO);
            }
        }
        Integer totalRows = departmentDao.count();
        Pager pager = new Pager();
        pager.setPageNumber(pageNumber);
        pager.setPageSize(pageSize);
        pager.setTotalRows(totalRows);
        pager.setTotalPage(pager.getTotalPage());
        pager.setList(departmentVOList);
        return pager;
    }

    public List<Department> getAllList() {
        return departmentDao.getAll();
    }

    @Override
    public List<DepartmentVO> getList() {
        List<Department> departments = departmentDao.getAll();
        List<DepartmentVO> departmentVOS = new ArrayList<DepartmentVO>();
        if (departments != null) {
            for (Department department:departments) {
                DepartmentVO departmentVO = DepartmentVO.fromDepartment(department);
                departmentVOS.add(departmentVO);
            }
        }
        return departmentVOS;
    }

    @Override
    public Department getFromId(String id) {
        return departmentDao.get(id);
    }

    @Override
    public DepartmentVO getIdToVO(String id) {
        return DepartmentVO.fromDepartment(departmentDao.get(id));
    }

    @Override
    public void fromSave(DepartmentVO departmentVO) {
        Department department = DepartmentVO.fromDepartmentVO(departmentVO);
        department.setDepartment(departmentDao.get(departmentVO.getParentId()));
        departmentDao.save(department);
    }

    @Override
    public void fromEdit(DepartmentVO departmentVO) {
        Department department = DepartmentVO.fromDepartmentVO(departmentVO);
        department.setDepartment(departmentDao.get(departmentVO.getParentId()));
        departmentDao.update(department);
    }
}
