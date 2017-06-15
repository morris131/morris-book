package com.morris.web;

import com.morris.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by lian.chen on 2017/6/15.
 */
@RestController
public class UserApiController {

    static Map<Long, User> userMap = Collections.synchronizedMap(new HashMap<Long, User>());

    @ApiOperation(value = "添加用户", notes = "根据User对象添加用户")
    @ApiImplicitParam(name = "user", value = "待创建的用户实体")
    @RequestMapping("addUser")
    public String addUser(@ModelAttribute User user) {
        userMap.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping("getList")
    public List<User> getList() {
        List<User> users = new ArrayList<>(userMap.values());
        return users;
    }

    @ApiOperation(value = "查询用户", notes = "根据用户ID获取用户详情")
    @ApiImplicitParam(name = "id", value = "用户ID")
    @RequestMapping("getById")
    public User getById(@PathVariable Long id) {
        return userMap.get(id);
    }

    @ApiOperation(value = "删除用户", notes = "根据用户ID删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID")
    @RequestMapping("delUser")
    public String delUser(@PathVariable Long id) {
        userMap.remove(id);
        return "success";
    }

}
