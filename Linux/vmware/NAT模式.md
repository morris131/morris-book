# NAT模式

在NAT模式中，主机网卡直接与虚拟NAT设备相连，然后虚拟NAT设备与虚拟DHCP服务器一起连接在虚拟交换机VMnet8上，这样就实现了虚拟机联网。那么我们会觉得很奇怪，为什么需要虚拟网卡VMware Network Adapter VMnet8呢？原来我们的VMware Network Adapter VMnet8虚拟网卡主要是为了实现主机与虚拟机之间的通信。在之后的设置步骤中，我们可以加以验证。




虚拟机网络适配器选择NAT模式。

先在宿主机上查看网络地址，

```
以太网适配器 VMware Network Adapter VMnet8:
......
   IPv4 地址 . . . . . . . . . . . . : 192.168.252.1(首选)
   子网掩码  . . . . . . . . . . . . : 255.255.255.0 
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
IPADDR=192.168.252.10
NETMASK=255.255.255.0
GATEWAY=192.168.252.2
```

重启网络服务
```
service network restart
```

再查看IP信息
````
# ifconfig
eth0      Link encap:Ethernet  HWaddr 00:0C:29:4E:FA:3F  
          inet addr:192.168.252.10  Bcast:192.168.252.255  Mask:255.255.255.0
          inet6 addr: fe80::20c:29ff:fe4e:fa3f/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:95183 errors:0 dropped:0 overruns:0 frame:0
          TX packets:833 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:9486370 (9.0 MiB)  TX bytes:101373 (98.9 KiB)

lo        Link encap:Local Loopback  
          inet addr:127.0.0.1  Mask:255.0.0.0
          inet6 addr: ::1/128 Scope:Host
          UP LOOPBACK RUNNING  MTU:65536  Metric:1
          RX packets:92 errors:0 dropped:0 overruns:0 frame:0
          TX packets:92 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:0 
          RX bytes:9118 (8.9 KiB)  TX bytes:9118 (8.9 KiB)

````
测试:
```
# ping 192.168.252.1
PING 192.168.252.1 (192.168.252.1) 56(84) bytes of data.
64 bytes from 192.168.252.1: icmp_seq=1 ttl=64 time=0.109 ms
64 bytes from 192.168.252.1: icmp_seq=2 ttl=64 time=0.176 ms
64 bytes from 192.168.252.1: icmp_seq=3 ttl=64 time=0.163 ms
.......
# ping www.baidu.com
PING www.a.shifen.com (14.215.177.39) 56(84) bytes of data.
64 bytes from 14.215.177.39: icmp_seq=1 ttl=128 time=5.52 ms
64 bytes from 14.215.177.39: icmp_seq=2 ttl=128 time=4.97 ms
64 bytes from 14.215.177.39: icmp_seq=3 ttl=128 time=6.73 ms
......
```













