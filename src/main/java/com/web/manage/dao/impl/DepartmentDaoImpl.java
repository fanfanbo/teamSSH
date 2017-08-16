package com.web.manage.dao.impl;

import com.web.base.dao.impl.BaseDaoImpl;
import com.web.manage.dao.DepartmentDao;
import com.web.manage.entity.Department;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
@Repository
public class DepartmentDaoImpl extends BaseDaoImpl <Department,String>implements DepartmentDao {
    @Override
    public List<Department> getDepartmentListByPage(Integer pageNumber, Integer pageSize,String departmentName,String departmentParentName) {
        String hql = null;
        if("".equals(departmentParentName) && !"".equals(departmentName)) {
            hql = "from Department d where d.name like '%"+departmentName+"%'";
        }
        if(!"".equals(departmentParentName) && "".equals(departmentName)) {
            hql = "from Department d where d.department.name like '%"+departmentParentName+"%'";
        }
        if (!"".equals(departmentParentName) && !"".equals(departmentName)) {
            hql = "from Department d where d.department.name like '%"+departmentParentName+"%' and d.name like '%"+departmentName+"%'";
        }
        if("".equals(departmentName) && "".equals(departmentParentName)) {
            hql = "from Department";
        }
        return this.getListByPage(pageNumber,pageSize,hql);
    }

    @Override
    public Department getFromName(String name) {
        String hql = "from Department where name=:name";
        Query query = getSession().createQuery(hql);
        query.setParameter("name",name);
        List<Department> departments = query.list();
        return departments.get(0);
    }
}
