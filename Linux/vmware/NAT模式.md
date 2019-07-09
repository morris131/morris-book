# NAT模式



桥接模式就是将主机网卡与虚拟机虚拟的网卡利用虚拟网桥进行通信。在桥接的作用下，类似于把物理主机虚拟为一个交换机，所有桥接设置的虚拟机连接到这个交换机的一个接口上，物理主机也同样插在这个交换机当中，所以所有桥接下的网卡与网卡都是交换模式的，相互可以访问而不干扰。在桥接模式下，虚拟机ip地址需要与主机在同一个网段，如果需要联网，则网关与DNS需要与主机网卡一致。其网络结构如下图所示：

![](https://gitee.com/morris131/morris-book/raw/master/Linux/vmware/image/桥接模式.png)


事实上，上面的原理图可以等价为下面的网络拓扑图：


因此，当虚拟机使用桥接模式上网时，你可以把这台虚拟机完全看作是宿主机所在局域中的一台真实主机，它使用的网络地址信息跟宿主机的完全一样。

![](https://gitee.com/morris131/morris-book/raw/master/Linux/vmware/image/桥接模式等价图.jpg)


## 配置

虚拟机网络适配器选择桥接模式。

先在宿主机上查看网络地址，

```
以太网适配器 本地连接:
......
   IPv4 地址 . . . . . . . . . . . . : 10.1.6.80(首选)
   子网掩码  . . . . . . . . . . . . : 255.255.255.0
   默认网关. . . . . . . . . . . . . : 10.1.6.254
   DNS 服务器  . . . . . . . . . . . : 202.96.134.133
                                       114.114.114.114
......
```

CentOS的网络配置如下：
```
DEVICE=eth0
HWADDR=00:0C:29:4E:FA:3F
TYPE=Ethernet
UUID=7e7c784a-8f70-4c14-a625-1ec1733ed8dc
ONBOOT=yes
NM_CONTROLLED=yes
BOOTPROTO=static
IPADDR=10.1.6.82
NETMASK=255.255.255.0
GATEWAY=10.1.6.254
DNS1=202.96.134.133
```

重启网络服务
```
service network restart
```

再查看IP信息
````
# ifconfig
eth0      Link encap:Ethernet  HWaddr 00:0C:29:4E:FA:3F  
          inet addr:10.1.6.82  Bcast:10.1.6.255  Mask:255.255.255.0
          inet6 addr: fe80::20c:29ff:fe4e:fa3f/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:88276 errors:0 dropped:0 overruns:0 frame:0
          TX packets:352 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:8822837 (8.4 MiB)  TX bytes:43650 (42.6 KiB)

lo        Link encap:Local Loopback  
          inet addr:127.0.0.1  Mask:255.0.0.0
          inet6 addr: ::1/128 Scope:Host
          UP LOOPBACK RUNNING  MTU:65536  Metric:1
          RX packets:24 errors:0 dropped:0 overruns:0 frame:0
          TX packets:24 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:0 
          RX bytes:2504 (2.4 KiB)  TX bytes:2504 (2.4 KiB)

````
测试:
```
# ping 10.1.6.80
PING 10.1.6.80 (10.1.6.80) 56(84) bytes of data.
64 bytes from 10.1.6.80: icmp_seq=1 ttl=64 time=0.128 ms
64 bytes from 10.1.6.80: icmp_seq=2 ttl=64 time=0.169 ms
64 bytes from 10.1.6.80: icmp_seq=3 ttl=64 time=0.190 ms
......
```













