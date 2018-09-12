1. 在主页面或者公共页面中加入：session.setMaxInactiveInterval(600);参数600单位是秒，即在没有10分钟活动后，session将失效。这里要注意这个session设置的时间是根据服务器来计算的，而不是客户端。所以如果是在调试程序，应该是修改服务器端时间来测试，而不是客户端。

2. 在项目的web.xml中设置
　　<!-- 设置session失效，单位分 -->
　　<session-config>
　　<session-timeout>1</session-timeout>
　　</session-config>
// 设置为0，-1 表示永不超时
 
3. 可以在tomcat目录下conf/web.xml中找到<session-config>元素，tomcat默认设置是30分钟，只要修改这个值就可以了。
 
需要注意的是如果上述三个地方如果都设置了，有个优先级的问题，从高到低：（1）>（2）>（3）