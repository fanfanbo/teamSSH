package com.web.manage.dao.impl;

import com.web.base.dao.impl.BaseDaoImpl;
import com.web.manage.dao.EmployeeDao;
import com.web.manage.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee,String> implements EmployeeDao{


    @Override
    public List<Employee> getEmployeeListByPage(Integer pageNumber, Integer pageSize ,String name) {
        String hql = "from Employee e where e.name like'%"+name+"%'";
        return this.getListByPage(pageNumber,pageSize,hql,new String[] {});
    }

    @Override
    public Integer getListSize(String name) {
        String hql = "from Employee e where e.name like'%"+name+"%'";
        return this.getSize(hql);
    }

    @Override
    public int checkname(String name) {
        //设置一个标识符用于返回
        int flag = 0;
        try{
            StringBuffer sbf=new StringBuffer();
            sbf.append("from Employee e where e.name =");
            sbf.append("'"+name+"'");
            //如果从数据库中查询出数据，表示用户名重复
            int i =this.getSize(sbf.toString());
            if( i > 0){
                flag = 1;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;

    }
}
