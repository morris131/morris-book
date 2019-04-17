package com.morris.web;

import com.morris.domain.User;
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

    @RequestMapping("addUser")
    public String addUser(@ModelAttribute User user) {
        userMap.put(user.getId(), user);
        return "success";
    }

    @RequestMapping("getList")
    public List<User> getList() {
        List<User> users = new ArrayList<>(userMap.values());
        return users;
    }

    @RequestMapping("getById")
    public User getById(@PathVariable Long id) {
        return userMap.get(id);
    }

    @RequestMapping("delUser")
    public String delUser(@PathVariable Long id) {
        userMap.remove(id);
        return "success";
    }

}
