package com.web.vo;

import com.web.manage.entity.Business;
import com.web.manage.entity.Contract;
import com.web.manage.service.BusinessService;
import com.web.system.entity.User;
import com.web.system.service.UserService;
import com.web.system.service.impl.UserServiceImpl;
import com.web.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Administrator on 2017/8/3.
 */
public class ContractVO {
        private String contractid;
        private String businessid; //商机编号
        private String businessCustomerId;//客户id
        private String businessCustomerName;//客户姓名
        private String contractnumber;//合同编号
        private Double price;
        private String duetime;
        private String ownerUserId; //负责人编号
        private String ownerUserName;//负责人姓名
        private String createUserId;
        private String businessConnectionId;//
        private String businessConnectionName;//
        private String contractcontent;
        private String description;
        private String createtime;
        private String updatetime;
        private String starttime;
        private String endTime;
        private String status;
        private String deleteUserId;
        private String deleteUserName;
        private String deleteTime;
        private Boolean isdeleted;





    public String getContractid() {
        return contractid;
    }

    public void setContractid(String contractid) {
        this.contractid = contractid;
    }

    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }

    public String getBusinessCustomerId() {
        return businessCustomerId;
    }

    public void setBusinessCustomerId(String businessCustomerId) {
        this.businessCustomerId = businessCustomerId;
    }

    public String getBusinessCustomerName() {
        return businessCustomerName;
    }

    public void setBusinessCustomerName(String businessCustomerName) {
        this.businessCustomerName = businessCustomerName;
    }

    public String getContractnumber() {
        return contractnumber;
    }

    public void setContractnumber(String contractnumber) {
        this.contractnumber = contractnumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDuetime() {
        return duetime;
    }

    public void setDuetime(String duetime) {
        this.duetime = duetime;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }


    public String getContractcontent() {
        return contractcontent;
    }

    public void setContractcontent(String contractcontent) {
        this.contractcontent = contractcontent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public String getDeleteUserName() {
        return deleteUserName;
    }

    public void setDeleteUserName(String deleteUserName) {
        this.deleteUserName = deleteUserName;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Boolean getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getBusinessConnectionId() {
        return businessConnectionId;
    }

    public void setBusinessConnectionId(String businessConnectionId) {
        this.businessConnectionId = businessConnectionId;
    }

    public String getBusinessConnectionName() {
        return businessConnectionName;
    }

    public void setBusinessConnectionName(String businessConnectionName) {
        this.businessConnectionName = businessConnectionName;
    }

    public static ContractVO fromContract(Contract contract) {
        if(contract!=null){
            ContractVO contractVO = new ContractVO();
            //复制相同字段
            BeanUtils.copyProperties(contract,contractVO);
            if(contract.getContractcontent()!=null) {
                contractVO.setContractcontent(new String(contract.getContractcontent()));
            }
            if(contract.getBusiness()!=null){
                contractVO.setBusinessid(contract.getBusiness().getBusinessid());
                if(contract.getBusiness().getCustomer()!=null){
                    contractVO.setBusinessCustomerId(contract.getBusiness().getCustomer().getCustomerid());
                    contractVO.setBusinessCustomerName(contract.getBusiness().getCustomer().getName());
                }
            }
            if(contract.getBusiness()!=null){
                if(contract.getBusiness().getBusinessContacts()!=null){
                    contractVO.setBusinessConnectionId(contract.getBusiness().getBusinessContacts().getContactsid());
                    contractVO.setBusinessConnectionName(contract.getBusiness().getBusinessContacts().getName());
                }
            }
            contractVO.setDuetime(DateUtil.formatDate(contract.getDuetime(),"yyyy-MM-dd HH:mm:ss"));
            contractVO.setCreatetime(DateUtil.formatDate(contract.getCreatetime(),"yyyy-MM-dd HH:mm:ss"));
            contractVO.setUpdatetime(DateUtil.formatDate(contract.getUpdatetime(),"yyyy-MM-dd HH:mm:ss"));
            contractVO.setStarttime(DateUtil.formatDate(contract.getStartdate(),"yyyy-MM-dd HH:mm:ss"));
            contractVO.setEndTime(DateUtil.formatDate(contract.getEnddate(),"yyyy-MM-dd HH:mm:ss"));
            if(contract.getOwnerUser()!=null){
                contractVO.setOwnerUserId(contract.getOwnerUser().getId());
                contractVO.setOwnerUserName(contract.getOwnerUser().getUsername());
            }
            if(contract.getCreateUser()!=null){
                contractVO.setCreateUserId(contract.getCreateUser().getId());
                contractVO.setBusinessConnectionName(contract.getCreateUser().getUsername());
            }
            return contractVO;
        }
        return null;
    }

    public static Contract fromContractVO(ContractVO contractVO) {
        if(contractVO!=null) {
          Contract contract = new Contract();
            //复制相同字段
            BeanUtils.copyProperties(contractVO,contract);
            contract.setContractcontent(contractVO.getContractcontent().getBytes());
            Business business = new Business();
            business.setBusinessid(contractVO.getBusinessid());
            contract.setBusiness(business);
            User user1 = new User();
            user1.setId(contractVO.getOwnerUserId());
            contract.setOwnerUser(user1);
            if(contractVO.getContractid()!=null && !"".equals(contractVO.getContractid())){
                User user2= new User();
                user2.setId(contractVO.getCreateUserId());
                contract.setCreateUser(user2);
            }


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date2 = null;
            Date date3 = null;
            try {
                date2 = sdf.parse(contractVO.getStarttime());
                date3 =sdf.parse(contractVO.getEndTime());
               /* date4 = sdf.parse(contractVO.getDeleteTime());*/
            } catch (ParseException e) {
                e.printStackTrace();
            }

            contract.setStartdate(date2);
            contract.setEnddate(date3);
            contract.setDuetime(DateUtil.parseDate(contractVO.getDuetime(),"yyyy-MM-dd HH:mm:ss"));

            return  contract;
        }
        return null;
    }



}
