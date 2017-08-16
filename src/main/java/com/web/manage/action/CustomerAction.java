package com.web.manage.action;

import com.web.base.action.BaseAction;
import com.web.manage.entity.Customer;
import com.web.manage.service.CustomerService;
import com.web.util.Pager;
import com.web.util.ResultData;
import com.web.vo.CustomerVO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
@ParentPackage("struts-default")
@Namespace("/manage/customer")
public class CustomerAction extends BaseAction {
    @Autowired
    private CustomerService customerService;

    private String customerid;
    private CustomerVO customerVO;

    /**
     * 跳转到主页
     * @return
     */
    @Action(value = "CustomerAction_customer",results = {
            @Result(location = "list.jsp")
    })
    public String customerManage() {
        return SUCCESS;
    }

    /**
     * 跳转到修改or新增
     * @return
     */
    @Action(value = "CustomerAction_showAdd", results = {
            @Result(location = "add.jsp")
    })
    public String toSaveOrEdit() {
        return SUCCESS;
    }

    /**
     * 查看
     * @return
     */
    @Action(value = "CustomerAction_showSee", results = {
            @Result(location = "show.jsp")
    })
    public String toShow() {
        customerVO = customerService.fromIdToCustomerVO(customerVO.getCustomerid());
        return SUCCESS;
    }



    //分页
    @Action("CustomerAction_pagination")
    public void findByPage() throws IOException {
        Pager pager = customerService.getCustomerListByPage(page,rows);
        resultData = ResultData.buildSuccessResult("获取列表成功",pager);
        printJSONObject(resultData,new String[]{});
    }

    /**
     * 是否关注
     * @throws IOException
     */
    @Action("CustomerAction_isfollowed")
    public void isFollow() throws IOException {
        customerVO = customerService.fromIdToCustomerVO(customerid);
        if (customerVO.getIslocked()) {
            printJSONObject(ResultData.buildSuccessResult());
        } else {
            printJSONObject(ResultData.buildFailureResult());
        }
    }

    //关注客户
    @Action("CustomerAction_follow")
    public  void follow() throws IOException {
        customerVO = customerService.followTrue(customerid);
        if (customerVO.getIslocked()) {
            printJSONObject(ResultData.buildSuccessResult());
        } else {
            printJSONObject(ResultData.buildFailureResult());
        }
    }

    @Action("CustomerAction_unfollow")
    public void unfollow() throws IOException {
        customerVO = customerService.followFalse(customerid);
        if (!customerVO.getIslocked()) {
            printJSONObject(ResultData.buildSuccessResult());
        } else {
            printJSONObject(ResultData.buildFailureResult());
        }
    }




    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public CustomerVO getCustomerVO() {
        return customerVO;
    }

    public void setCustomerVO(CustomerVO customerVO) {
        this.customerVO = customerVO;
    }
}
