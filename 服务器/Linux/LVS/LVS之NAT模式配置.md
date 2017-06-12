网络拓扑图如下：


1. Real Server1配置如下：
1). 安装httpd
2). 修改/var/www/html/index.html内容为RealServer1
3). 配置ip和网关
vi /etc/sysconfig/network-scripts/ifcfg-eth0


DEVICE=eth0

TYPE=Ethernet

UUID=fc884f22-dbf3-49bc-80fd-433db7d5c309

ONBOOT=yes

NM_CONTROLLED=yes

BOOTPROTO=static

IPADDR=192.168.92.131

NETMASK=255.255.255.0

GATEWAY=192.168.92.130
service network restart  重启网络服务
ip为192.168.92.131，指向Director Server的dip 192.168.92.130
3). 关闭防火墙 service iptables stop
4). 启动httpd服务 service httpd restart


2. Real Server1的配置与Real Server2的配置一样，只是ip为192.168.92.128


3. Director Server的配置
1). 安装ipvsadm客户端
2). 关闭防火墙  service iptables stop
3). 配置eth0的ip为192.168.92.130，eth1的ip为192.168.1.111
4). 配置ipvsadm
ipvsadm -A -t 192.168.1.111:80 -s rr

ipvsadm -a -t 192.168.1.111:80 -r 192.168.92.131:80 -m -w 1

ipvsadm -a -t 192.168.1.111:80 -r 192.168.92.128:80 -m -w 2

service ipvsadm save

5). 开启ip转发
vi etc/sysctl.conf 设置 net.ipv4.ip_forward = 1

4.测试
客户端浏览器输入 192.168.1.111/index.html


