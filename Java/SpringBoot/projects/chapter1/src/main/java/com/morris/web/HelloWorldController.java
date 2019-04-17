package com.morris.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lian.chen on 2017/6/12.
 */
@RestController
public class HelloWorldController {

    @RequestMapping("hello")
    public String hello() {
        return "hello world";
    }
}
