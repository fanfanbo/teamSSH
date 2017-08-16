package com.web.manage.service.impl;

import com.web.base.service.impl.BaseServiceImpl;
import com.web.manage.dao.CustomerDao;
import com.web.manage.entity.Customer;
import com.web.manage.service.CustomerService;
import com.web.util.Pager;
import com.web.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer,String> implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        super.setBaseDao(customerDao);
    }


    @Override
    public Pager getCustomerListByPage(Integer pageNumber, Integer pageSize) {
        List<Customer> list = customerDao.getCustomerListByPage(pageNumber,pageSize);
        List<CustomerVO> customerVOList = new ArrayList<CustomerVO>();
        if(list!=null&&!list.isEmpty()) {
            for (Customer customer:list){
                CustomerVO customerVO = CustomerVO.fromCustomer(customer);
                customerVOList.add(customerVO);
            }
        }
        Integer totalRows = customerDao.count();
        Pager pager = new Pager();
        pager.setPageNumber(pageNumber);
        pager.setPageSize(pageSize);
        pager.setTotalRows(totalRows);
        pager.setTotalPage(pager.getTotalPage());
        pager.setList(customerVOList);
        return pager;
    }

    @Override
    public boolean follow(String id) {
        Customer customer = this.get(id);
        if (customer.getIslocked()) {
            return true;
        }
        return false;
    }

    @Override
    public CustomerVO fromIdToCustomerVO(String id) {
        Customer customer = customerDao.get(id);
        return CustomerVO.fromCustomer(customer);
    }

    @Override
    public CustomerVO followTrue(String id) {
        Customer customer = customerDao.get(id);
        customer.setIslocked(true);
        customerDao.update(customer);
        return CustomerVO.fromCustomer(customer);
    }

    @Override
    public CustomerVO followFalse(String id) {
        Customer customer = customerDao.get(id);
        customer.setIslocked(false);
        customerDao.update(customer);
        return CustomerVO.fromCustomer(customer);
    }

    @Override
    public List<CustomerVO> getAllData() {
        List<Customer> list = customerDao.getAll();
        List<CustomerVO> list1 = new ArrayList<CustomerVO>();
        if(list!=null){
            for(Customer customer :list) {
                CustomerVO customerVO = new CustomerVO();
                customerVO = CustomerVO.fromCustomer(customer);
                list1.add(customerVO);
            }
        }
        return list1;

    }




}
