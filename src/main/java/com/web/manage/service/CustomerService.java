package com.web.manage.service;

import com.web.base.service.BaseService;
import com.web.manage.entity.Customer;
import com.web.util.Pager;
import com.web.vo.CustomerVO;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface CustomerService extends BaseService<Customer,String> {

    Pager getCustomerListByPage(Integer pageNumber,Integer pageSize);

    boolean follow(String id);

    CustomerVO fromIdToCustomerVO(String id);

    CustomerVO followTrue(String id);

    CustomerVO followFalse(String id);

    List<CustomerVO> getAllData();

}
