package com.web.system.service;

import com.web.base.service.BaseService;
import com.web.system.entity.Function;
import com.web.vo.FunctionVO;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public interface FunctionService extends BaseService<Function, String>{

    public List<Function> getAssignList(String id);

    public List<FunctionVO> getAssignlistAll();

    /**
     * 通过id查询
     * @param id
     * @return
     */
    FunctionVO getOne(String id);

    /**
     * 查询所有function对象
     * @return
     */
    List<Function> getAllFunction();

    /**
     * 将functionVO对象转成function对象
     * @param functionVO
     * @return
     */
    Function saveFunction(FunctionVO functionVO);

    /**
     * 执行新增function对象操作
     * @param function
     */
    void saveFunction(Function function);

    /**
     * 执行修改function对象操作
     * @param function
     */
    void updateFunction(Function function);

    void delete(String id);

}
