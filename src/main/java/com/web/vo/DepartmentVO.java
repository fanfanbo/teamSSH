package com.web.vo;

;import com.web.manage.entity.Department;

/**
 * Created by Administrator on 2017/7/27.
 */
public class DepartmentVO {
    private String departmentid;
    private String parentId;
    private String parentName;
    private String name;
    private String description;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public static DepartmentVO fromDepartment(Department department){
        if (department != null ){
            DepartmentVO departmentVO = new DepartmentVO();
            departmentVO.setDepartmentid(department.getDepartmentid());
            if (department.getDepartment() != null) {
                departmentVO.setParentId(department.getDepartment().getDepartmentid());
                departmentVO.setParentName(department.getDepartment().getName());
            }else {
                departmentVO.setParentId(null);
                departmentVO.setParentName(null);
            }
            departmentVO.setName(department.getName());
            departmentVO.setDescription(department.getDescription());
            return departmentVO;
        }

        return null;
    }

    public static Department fromDepartmentVO(DepartmentVO departmentVO) {
        Department department = null;
        if (departmentVO != null) {
            department = new Department();
            department.setName(departmentVO.getName());
            department.setDepartmentid(departmentVO.getDepartmentid());
            department.setDescription(departmentVO.getDescription());
        }
        return department;
    }
}
