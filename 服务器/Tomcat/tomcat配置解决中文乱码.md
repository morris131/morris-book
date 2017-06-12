如果表单是以get方式提交就会出现中文乱码
这时可以在tomcat中配置解决中文乱码问题。方法如下：
在tomcat的conf文件夹下的conf中找到server.xml文件 再端口的那个地方 <Connector port="8081" protocol="HTTP/1.1"
connectionTimeout="20000"
redirectPort="8443" URIEncoding="UTF-8"/>
就ok了