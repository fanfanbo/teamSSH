package com.web.manage.action;

import com.web.base.action.BaseAction;
import com.web.manage.entity.Business;
import com.web.manage.entity.Contract;
import com.web.manage.service.BusinessService;
import com.web.manage.service.ContractService;
import com.web.system.entity.User;
import com.web.util.Pager;
import com.web.util.ResultData;
import com.web.vo.BusinessVO;
import com.web.vo.ContractVO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */
@ParentPackage("struts-default")
@Namespace("/manage/contract")
public class ContractAction extends BaseAction {

    private Integer contractOne;    //第一个查询的参数
    private Integer contractTwo;    //第二个查询的参数
    private String contract;        //第三个查询的参数

    private BusinessVO businessVO;
    private ContractVO contractVO;
    private String contractid;

    private String contractIds;

    @Autowired
    private ContractService contractService;
    @Autowired
    private BusinessService businessService;
    //分页的页面
    @Action(value = "ContractAction_list",results = {
            @Result(name = "success",location = "list_contract.jsp")
    })
    public  String topage() {
        return SUCCESS;
    }

    //获取分页的数据
    @Action("ContractAction_findByPage")
    public void findByPage() throws IOException {
        Pager pager =contractService.getContractListByPage(page,rows,contractOne,contractTwo,contract);
        resultData = ResultData.buildSuccessResult("获取列表成功",pager);
        printJSONObject(resultData);
    }

    //去添加或修改的页面
    @Action(value = "ContractAction_edit",results={
            @Result(name = "success",location = "edit_contract.jsp")
    })
    public String add() {
       /* list = businessService.getAll();*/
       if(contractid!=null&&!contractid.isEmpty()){
          contractVO = contractService.getContractVO(contractid);
       }
        return SUCCESS;
    }
    //新增和修改的保存页面
    @Action(value = "ContractAction_saveOrUpdate")
    public void save() throws IOException{
        Contract contract = ContractVO.fromContractVO(contractVO);
        contract.setCreateUser((User)getSession().getAttribute("user"));
        if(contractVO.getContractid()!=null&&!contractVO.getContractid().isEmpty()){
            contractService.update(contract);
        }else {
        contractService.save(contract);
        }
        resultData = ResultData.buildSuccessResult("保存成功");
        printJSONObject(resultData);
    }

    //删除的action
    @Action(value = "ContractAction_delete")
    public void del() throws IOException {
        boolean status =  contractService.deleteByIds(contractIds);
        if (status) {
            resultData = ResultData.buildSuccessResult("删除成功");
        } else {
            resultData = ResultData.buildFailureResult("删除失败");
        }
        printJSONObject(resultData);

    }

    //查看的action
    @Action(value = "ContractAction_see",results={
            @Result(name = "success",location = "see_contract.jsp")
    })
    public String see() {
        if (contractid != null && !contractid.isEmpty()) {
            contractVO = contractService.getContractVO(contractid);
        }
        return SUCCESS;
    }


    //获取下拉框
    @Action("ContractAction_findAll")
    public void FindAll() throws IOException {
        List<BusinessVO> list = businessService.getAllId();
        resultData = ResultData.buildSuccessResult("获取下拉框所有的商机",list);
        printJSONObject(resultData);
    }



    public Integer getContractOne() {
        return contractOne;
    }

    public void setContractOne(Integer contractOne) {
        this.contractOne = contractOne;
    }

    public Integer getContractTwo() {
        return contractTwo;
    }

    public void setContractTwo(Integer contractTwo) {
        this.contractTwo = contractTwo;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }


    public BusinessVO getBusinessVO() {
        return businessVO;
    }

    public void setBusinessVO(BusinessVO businessVO) {
        this.businessVO = businessVO;
    }

    public ContractVO getContractVO() {
        return contractVO;
    }

    public void setContractVO(ContractVO contractVO) {
        this.contractVO = contractVO;
    }

    public String getContractid() {
        return contractid;
    }

    public void setContractid(String contractid) {
        this.contractid = contractid;
    }


    public String getContractIds() {
        return contractIds;
    }

    public void setContractIds(String contractIds) {
        this.contractIds = contractIds;
    }
}
