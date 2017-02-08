网络拓扑图如下：

1. Director Server配置


[root@chen ~]# ifconfig eth0:1 192.168.10.100/32 broadcast 192.168.10.100 up

[root@chen ~]# route add -host 192.168.10.100 dev eth0:1

[root@chen ~]# ifconfig eth0:1 192.168.92.100/32 broadcast 192.168.92.100 up

[root@chen ~]# route add -host 192.168.92.100 dev eth0:1

[root@chen ~]# ipvsadm -A -t 192.168.92.100:80 -s wlc

[root@chen ~]# ipvsadm -a -t 192.168.92.100:80 -g -r 192.168.92.131 -w 1

[root@chen ~]# ipvsadm -a -t 192.168.92.100:80 -g -r 192.168.92.128 -w 2

[root@chen ~]# ipvsadm -L -n

IP Virtual Server version 1.2.1 (size=4096)

Prot LocalAddress:Port Scheduler Flags

  -> RemoteAddress:Port           Forward Weight ActiveConn InActConn

TCP  192.168.92.100:80 wlc

  -> 192.168.92.128:80            Route   2      0          0         

  -> 192.168.92.131:80            Route   1      0          0         

[root@chen ~]# service ipvsadm save

ipvsadm: Saving IPVS table to /etc/sysconfig/ipvsadm:      [确定]


2. Real Server1的配置


[root@chen ~]# echo 1 > /proc/sys/net/ipv4/conf/lo/arp_ignore 

[root@chen ~]# echo 2 > /proc/sys/net/ipv4/conf/lo/arp_announce 

[root@chen ~]# echo 1 > /proc/sys/net/ipv4/conf/all/arp_ignore 

[root@chen ~]# echo 2 > /proc/sys/net/ipv4/conf/all/arp_announce 

[root@chen ~]# ifconfig lo:0 192.168.92.100/32 broadcast 192.168.92.100 up

[root@chen ~]# route add -host 192.168.92.100 dev lo:0

[root@chen ~]# ifconfig

eth0      Link encap:Ethernet  HWaddr 00:0C:29:FB:8C:5C  

          inet addr:192.168.92.128  Bcast:192.168.92.255  Mask:255.255.255.0

          inet6 addr: fe80::20c:29ff:fefb:8c5c/64 Scope:Link

          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1

          RX packets:12291 errors:0 dropped:0 overruns:0 frame:0

          TX packets:4363 errors:0 dropped:0 overruns:0 carrier:0

          collisions:0 txqueuelen:1000 

          RX bytes:939667 (917.6 KiB)  TX bytes:495443 (483.8 KiB)

          Interrupt:19 Base address:0x2000 


lo        Link encap:Local Loopback  

          inet addr:127.0.0.1  Mask:255.0.0.0

          inet6 addr: ::1/128 Scope:Host

          UP LOOPBACK RUNNING  MTU:65536  Metric:1

          RX packets:50 errors:0 dropped:0 overruns:0 frame:0

          TX packets:50 errors:0 dropped:0 overruns:0 carrier:0

          collisions:0 txqueuelen:0 

          RX bytes:17800 (17.3 KiB)  TX bytes:17800 (17.3 KiB)


lo:0      Link encap:Local Loopback  

          inet addr:192.168.92.100  Mask:0.0.0.0

          UP LOOPBACK RUNNING  MTU:65536  Metric:1
3. Real Server2的配置与Real Server1的配置一样，ip为192.168.92.131
4. 测试
客户端浏览器输入 http://192.168.92.100/index.html
