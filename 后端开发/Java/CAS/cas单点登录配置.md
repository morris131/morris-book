1. 证书的配置
1).  服务器端生成证书
D:\>keytool -genkey -alias morris -keyalg RSA -keystore D:/keys/morriskey
输入密钥库口令:
再次输入新口令:
您的名字与姓氏是什么?
  [Unknown]:  localhost
您的组织单位名称是什么?
  [Unknown]:  tempus
您的组织名称是什么?
  [Unknown]:  tempus.tb
您所在的城市或区域名称是什么?
  [Unknown]:  szx
您所在的省/市/自治区名称是什么?
  [Unknown]:  guangdong
该单位的双字母国家/地区代码是什么?
  [Unknown]:  cn
CN=localhost, OU=tempus, O=tempus.tb, L=szx, ST=guangdong, C=cn是否正确?
  [否]:  y
输入 <morris> 的密钥口令
        (如果和密钥库口令相同, 按回车):
2).  服务器端导出证书
D:\>keytool -export -file d:/keys/morris.crt -alias morris -keystore d:/keys/morriskey
输入密钥库口令:
存储在文件 <d:/keys/morris.crt> 中的证书
3).  导入证书到客户端JDK中
D:\>keytool -import -keystore "D:\Program Files\Java\jdk1.7.0_71\jre\lib\security\cacerts" -file "D:/keys/morris.crt" -alias morris
输入密钥库口令:
所有者: CN=localhost, OU=tempus, O=tempus.tb, L=szx, ST=guangdong, C=cn
发布者: CN=localhost, OU=tempus, O=tempus.tb, L=szx, ST=guangdong, C=cn
序列号: 58526d2b
有效期开始日期: Thu Jan 07 15:28:41 CST 2016, 截止日期: Wed Apr 06 15:28:41 CST2016
证书指纹:
         MD5: E0:70:62:58:9A:29:63:2C:92:AA:A6:51:38:46:CB:71
         SHA1: C5:5A:0D:39:2A:D2:86:22:C9:B5:D8:74:09:BE:39:80:D8:AA:DD:0E
         SHA256: 38:87:F9:C6:C8:0F:3B:C0:EF:28:95:1B:A2:38:F2:CF:61:6F:9D:21:4D:2B:73:2D:29:5D:78:08:FF:6A:9E:54
         签名算法名称: SHA256withRSA
         版本: 3
扩展:
#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: B0 43 E8 A6 69 35 C8 73   4F 83 97 FD E9 2E FA FA  .C..i5.sO.......0010: 77 DF 90 23                                        w..#
]
]
是否信任此证书? [否]:  y
证书已添加到密钥库中

2. 服务器端的配置
1). 下载CAS的服务端，解压，把解压后的文件中modules文件夹中的cas-server-webapp-3.4.8.war文件拷贝的%TOMCAT_HOME%\webapps下，并修改文件名为：cas.war。
CAS官网： https://www.apereo.org/ 
源码下载地址：https://github.com/Jasig/cas/releases
2).  修改%TOMCAT_HOME%\conf\server.xml文件
<Connector port="8443" protocol="org.apache.coyote.http11.Http11Protocol" SSLEnabled="true"
               maxThreads="150" scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS" 
			   keystoreFile="D:/keys/morris131key" 
			   keystorePass="123456"/>   
 keystorePass：证书中设置的密码
keystoreFile：生成的证书的位置
3). 启动tomcat，浏览器地址栏输入 http://localhost:8080/cas/login










