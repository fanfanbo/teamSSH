package com.web.vo;

import com.web.system.entity.User;

/**
 * Created by Administrator on 2017/8/4.
 */
public class UserVV {
    private String id;
    private String name;

    public static UserVV fromUser(User user){
        if (user != null){
            UserVV userVV = new UserVV();
            userVV.setId(user.getId());
            userVV.setName(user.getUsername());
            return userVV;
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
