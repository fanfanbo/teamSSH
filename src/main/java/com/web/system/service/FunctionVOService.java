package com.web.system.service;

import com.web.system.entity.User;
import com.web.vo.FunctionVO;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
public interface FunctionVOService {
    /**
     * 通过用户获取其对应的所有权限
     * @param user
     * @return
     */
    List<FunctionVO> getFunctionListVO(User user);
}
