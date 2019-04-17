package com.morris.spring.inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserConfig.class)
public class AutoInjectTest {

    @Autowired
    private User user;

    @Test
    public void test() {
        Assert.assertNotNull(user);
    }

}
