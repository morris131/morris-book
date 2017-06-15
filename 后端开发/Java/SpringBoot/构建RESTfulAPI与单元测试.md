下面我们尝试使用Spring MVC来实现一组对User对象操作的RESTful API，配合注释详细说明在Spring MVC中如何映射HTTP请求、如何传参、如何编写单元测试。

1. User实体定义：


详细代码参考[User.java](projects/chapter4/src/main/java/com/morris/domain/User.java)

```java
package com.morris.domain;

/**
 * Created by lian.chen on 2017/6/15.
 */
public class User {

    private Long id;

    private String name;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

```

2. 对User对象的操作接口:

详细代码参考[UserApiController.java](projects/chapter4/src/main/java/com/morris/web/UserApiController.java)

```java
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

```

3. 单元测试

详细代码参考[Chapter4ApplicationTests.java](projects/chapter4/src/test/java/com/morris/web/Chapter4ApplicationTests.java)

```java
package com.morris;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest
public class Chapter4ApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testAddUser() throws Exception{

		RequestBuilder request = MockMvcRequestBuilders.get("/addUser").param("id","111");
		MvcResult mvcResult = mvc.perform(request).andReturn();
		String contentAsString = mvcResult.getResponse().getContentAsString();
		System.out.println(contentAsString);
	}

}

```

> 注意注解@WebMvcTest和MockMvc的使用
