package com.web.manage.action;

import com.web.base.action.BaseAction;
import com.web.manage.entity.Department;
import com.web.manage.service.DepartmentSerivce;
import com.web.util.Pager;
import com.web.util.ResultData;
import com.web.vo.DepartmentVO;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
@ParentPackage("json-default")
@Namespace("/manage/position")
public class DepartmentAction extends BaseAction{
    @Autowired
    private DepartmentSerivce departmentSerivce;

    private String name;

    private String departmentid;

    private Department department;

    private DepartmentVO deptVO;

    private List<DepartmentVO> departmentVOS;

    private String departmentName;

    private String departmentParentName;


    @Action(value = "DepartmentAction_list",results = {
            @Result(location = "list_dept.jsp")
    })
    /**
     * 分页和查询
     *
     */
    public String departmentManage(){
        return SUCCESS;
    }
    @Action(value = "DepartmentAction_findByPage")
    public void findByPage() throws IOException {
        if(departmentName==null){
            departmentName="";
        }
        if(departmentParentName == null) {
            departmentParentName = "";
        }
        Pager pager = departmentSerivce.getDepartmentListByPage(page,rows,departmentName,departmentParentName);
        resultData = ResultData.buildSuccessResult("获取列表成功",pager);
        printJSONObject(resultData, "positions", "announcements", "departments");
    }
    /**
    *
    * 添加
    */
    @Action(value = "DepartmentAction_add",results = {
            @Result(location = "edit_dept.jsp")
    })
    public String add(){
        departmentVOS = departmentSerivce.getList();
        return SUCCESS;
    }
    /*
    修改or新增
     */
    @Action(value = "DepartmentAction_edit",results = {
            @Result(location = "edit_dept.jsp")
    })
    public String edit(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String id = request.getParameter("id");
        deptVO = departmentSerivce.getIdToVO(id);
        departmentVOS = departmentSerivce.getList();
        return SUCCESS;
    }
    @Action(value = "Department_save")
    public void yedit() throws IOException {
        if (deptVO.getDepartmentid() != null && !"".equals(deptVO.getDepartmentid())) {
            departmentSerivce.fromEdit(deptVO);
        } else {
            departmentSerivce.fromSave(deptVO);
        }
        resultData = ResultData.buildSuccessResult("修改成功");
        printJSONObject(resultData);
    }
    /*
    删除
     */
    @Action(value = "DepartmentAction_remove",results = {
            @Result(name = SUCCESS,type = "json",params = {
                    "root","ResultData"
            })
    })
    public void deleteDepartment() throws IOException {
        String[] ids = departmentid.split(",");
        System.out.println(ids);
        departmentSerivce.delete(departmentid);
        resultData = ResultData.buildSuccessResult("删除成功");
        printJSONObject(resultData);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<DepartmentVO> getDepartmentVOS() {
        return departmentVOS;
    }

    public void setDepartmentVOS(List<DepartmentVO> departmentVOS) {
        this.departmentVOS = departmentVOS;
    }

    public DepartmentVO getDeptVO() {
        return deptVO;
    }

    public void setDeptVO(DepartmentVO deptVO) {
        this.deptVO = deptVO;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentParentName() {
        return departmentParentName;
    }

    public void setDepartmentParentName(String departmentParentName) {
        this.departmentParentName = departmentParentName;
    }
}
