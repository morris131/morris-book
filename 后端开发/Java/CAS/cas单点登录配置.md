1. ֤�������
1).  ������������֤��
D:\>keytool -genkey -alias morris -keyalg RSA -keystore D:/keys/morriskey
������Կ�����:
�ٴ������¿���:
����������������ʲô?
  [Unknown]:  localhost
������֯��λ������ʲô?
  [Unknown]:  tempus
������֯������ʲô?
  [Unknown]:  tempus.tb
�����ڵĳ��л�����������ʲô?
  [Unknown]:  szx
�����ڵ�ʡ/��/������������ʲô?
  [Unknown]:  guangdong
�õ�λ��˫��ĸ����/����������ʲô?
  [Unknown]:  cn
CN=localhost, OU=tempus, O=tempus.tb, L=szx, ST=guangdong, C=cn�Ƿ���ȷ?
  [��]:  y
���� <morris> ����Կ����
        (�������Կ�������ͬ, ���س�):
2).  �������˵���֤��
D:\>keytool -export -file d:/keys/morris.crt -alias morris -keystore d:/keys/morriskey
������Կ�����:
�洢���ļ� <d:/keys/morris.crt> �е�֤��
3).  ����֤�鵽�ͻ���JDK��
D:\>keytool -import -keystore "D:\Program Files\Java\jdk1.7.0_71\jre\lib\security\cacerts" -file "D:/keys/morris.crt" -alias morris
������Կ�����:
������: CN=localhost, OU=tempus, O=tempus.tb, L=szx, ST=guangdong, C=cn
������: CN=localhost, OU=tempus, O=tempus.tb, L=szx, ST=guangdong, C=cn
���к�: 58526d2b
��Ч�ڿ�ʼ����: Thu Jan 07 15:28:41 CST 2016, ��ֹ����: Wed Apr 06 15:28:41 CST2016
֤��ָ��:
         MD5: E0:70:62:58:9A:29:63:2C:92:AA:A6:51:38:46:CB:71
         SHA1: C5:5A:0D:39:2A:D2:86:22:C9:B5:D8:74:09:BE:39:80:D8:AA:DD:0E
         SHA256: 38:87:F9:C6:C8:0F:3B:C0:EF:28:95:1B:A2:38:F2:CF:61:6F:9D:21:4D:2B:73:2D:29:5D:78:08:FF:6A:9E:54
         ǩ���㷨����: SHA256withRSA
         �汾: 3
��չ:
#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: B0 43 E8 A6 69 35 C8 73   4F 83 97 FD E9 2E FA FA  .C..i5.sO.......0010: 77 DF 90 23                                        w..#
]
]
�Ƿ����δ�֤��? [��]:  y
֤������ӵ���Կ����

2. �������˵�����
1). ����CAS�ķ���ˣ���ѹ���ѽ�ѹ����ļ���modules�ļ����е�cas-server-webapp-3.4.8.war�ļ�������%TOMCAT_HOME%\webapps�£����޸��ļ���Ϊ��cas.war��
CAS������ https://www.apereo.org/ 
Դ�����ص�ַ��https://github.com/Jasig/cas/releases
2).  �޸�%TOMCAT_HOME%\conf\server.xml�ļ�
<Connector port="8443" protocol="org.apache.coyote.http11.Http11Protocol" SSLEnabled="true"
               maxThreads="150" scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS" 
			   keystoreFile="D:/keys/morris131key" 
			   keystorePass="123456"/>   
 keystorePass��֤�������õ�����
keystoreFile�����ɵ�֤���λ��
3). ����tomcat���������ַ������ http://localhost:8080/cas/login










