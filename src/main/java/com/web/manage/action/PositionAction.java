package com.web.manage.action;

import com.web.base.action.BaseAction;
import com.web.manage.entity.Department;
import com.web.manage.entity.Position;
import com.web.manage.service.DepartmentSerivce;
import com.web.manage.service.PositionService;
import com.web.util.Pager;
import com.web.util.ResultData;
import com.web.vo.PositionVO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 */
@ParentPackage("struts-default")
@Namespace("/manage/position")
public class PositionAction extends BaseAction {

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentSerivce departmentSerivce;

    private String name;    //需要查询的字段

    private PositionVO positionVO;  //获取到修改的值的对象属性

    private List<Position> list;    //获取上级岗位的下拉框集合

    private List<Department> dl;    //获取上级部门的下拉框集合

    private String  positionId;     //获取修改所需要的字段

    private String positionIds;     //批量删除所需的字段


    @Action(value = "PositionAction_list",results ={
            @Result(location = "list_position.jsp")
    })
    public String positionManage() {
        return SUCCESS;
    }


    /**
     * 分页和查询
     * @throws IOException
     */
    @Action("PositionAction_list1")
    public void findByPage() throws IOException {
        Pager pager = positionService.getPositionListByPage(page,rows,name);
        resultData = ResultData.buildSuccessResult("获取列表成功",pager);
        printJSONObject(resultData,new String[]{});
    }

    //新增的保存
    @Action(value = "Position_save")
    public void save() throws IOException {
        //获取上级部门
        Department department = departmentSerivce.get(positionVO.getDepartmentParentId());
        //获取上级岗位
        Position position2 = positionService.get(positionVO.getPositionTopId());
        //将VO对象转化为实体对象
        Position position = PositionVO.fromPositionVO(positionVO, department, position2);
        //如果有id就进行修改操作，不然进行保存操作
        if(positionVO.getPositionid()!=null && !"".equals(positionVO.getPositionid())){
            positionService.update(position);
        } else {
            positionService.save(position);
        }
        resultData = ResultData.buildSuccessResult("保存成功");
        printJSONObject(resultData);
    }


    //去修改或新增的action
    @Action(value = "toEditPage",results = {
            @Result(name = "success",location = "edit_position.jsp")
})
    public String edit() {
        if (positionId != null && !positionId.isEmpty()){
            positionVO = positionService.getPositionVO(positionId);
        }
        list = positionService.getAll();
        dl = departmentSerivce.getAll();
        return  SUCCESS;
    }

    //删除
    @Action("PositionAction_delete")
    public void  delete () throws IOException {
        boolean status = positionService.deleteByIds(positionIds);
        if (status) {
            resultData = ResultData.buildSuccessResult("删除成功");
        } else {
            resultData = ResultData.buildFailureResult("删除失败");
        }
        printJSONObject(resultData);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PositionVO getPositionVO() {
        return positionVO;
    }

    public void setPositionVO(PositionVO positionVO) {
        this.positionVO = positionVO;
    }

    public List<Position> getList() {
        return list;
    }

    public void setList(List<Position> list) {
        this.list = list;
    }

    public List<Department> getDl() {
        return dl;
    }

    public void setDl(List<Department> dl) {
        this.dl = dl;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(String positionIds) {
        this.positionIds = positionIds;
    }
}
