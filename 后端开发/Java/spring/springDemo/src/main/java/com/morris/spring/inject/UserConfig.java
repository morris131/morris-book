package com.morris.spring.inject;

import org.springframework.context.annotation.*;

@ComponentScan
@Configuration
@ImportResource("classpath:spring-inject-setter.xml")
@Import(SystemConfig.class)
public class UserConfig {

    @Bean
    public User createUser() {
        return new User();
    }

}
