网络拓扑图如下：


1. RealServer服务器的配置
1). 安装httpd
2). 配置ip 192.168.92.131
3). 编辑脚本并运行


[root@chen ~]# cat /etc/init.d/lvs_DR_node 

#!/bin/sh

#descript : start real server DR

VIP=192.168.92.100

. /etc/rc.d/init.d/functions

case "$1" in

       start)

             echo "start LVS of RealServer DR"

             ifconfig lo:0 $VIP/32 broadcast $VIP up

             echo "1" > /proc/sys/net/ipv4/conf/lo/arp_ignore

             echo "2" > /proc/sys/net/ipv4/conf/lo/arp_announce

	     echo "1" > /proc/sys/net/ipv4/conf/all/arp_ignore

             echo "2" > /proc/sys/net/ipv4/conf/all/arp_announce

	     sysctl -p > /dev/null 2>&1

	     echo "Real Server Start OK!"

               ;;

       stop)

             ifconfig lo:0 down

             echo "close LVS of RealServer DR"

	     route del $VIP > /dev/null 2>&1

             echo "0" > /proc/sys/net/ipv4/conf/all/arp_ignore

             echo "0" > /proc/sys/net/ipv4/conf/all/arp_announce

              ;;

         *)

              echo "Usage : $0 {start|stop}"

              exit 1

esac

[root@chen ~]# cd /etc/init.d/

[root@chen init.d]# chmod a+x /etc/init.d/lvs_DR_node

[root@chen init.d]# /etc/init.d/lvs_DR_node start

start LVS of RealServer DR

Real Server Start OK!

4). 启动httpd


2. Master服务器的配置
1). 配置ip 192.168.92.130
2). 安装ipvsadm
3). 安装keepalived
4). 配置keepalived


[root@chen keepalived]# cat /etc/keepalived/keepalived.conf

! Configuration File for keepalived


global_defs {

   router_id LVS_DEVEL

}


vrrp_instance VI_1 {

    state BACKUP

    interface eth0

    virtual_router_id 51

    priority 80

    advert_int 1

    authentication {

        auth_type PASS

        auth_pass 1111

    }

    virtual_ipaddress {

        192.168.92.100

    }

}


virtual_server 192.168.92.100 80 {

    delay_loop 6

    lb_algo wlc

    lb_kind DR

    nat_mask 255.255.255.0

    persistence_timeout 50

    protocol TCP


    real_server 192.168.92.131 80 {

        weight 1

        TCP_CHECK {

            url {

            connect_timeout 3

            nb_get_retry 3

            delay_before_retry 3

	    connect_port 80

        }

    }

}
5). 关闭防火墙，启动ipvsadm,keepalived


[root@chen sbin]# service iptables stop

[root@chen sbin]# service ipvsadm restart

ipvsadm: Clearing the current IPVS table:                  [确定]

ipvsadm: Unloading modules:                                [确定]

ipvsadm: Clearing the current IPVS table:                  [确定]

ipvsadm: Applying IPVS configuration:                      [确定]

[root@chen sbin]# service keepalived restart

停止 keepalived：                                          [确定]

正在启动 keepalived：                                      [确定]

6). 查看状态


[root@chen sbin]# ip addr list

1: lo: 
<LOOPBACK
,
UP
,
LOWER_UP
>
 mtu 65536 qdisc noqueue state UNKNOWN 

    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00

    inet 127.0.0.1/8 scope host lo

    inet6 ::1/128 scope host 

       valid_lft forever preferred_lft forever

2: eth0: 
<BROADCAST
,
MULTICAST
,
UP
,
LOWER_UP
>
 mtu 1500 qdisc pfifo_fast state UNKNOWN qlen 1000

    link/ether 00:0c:29:25:68:9f brd ff:ff:ff:ff:ff:ff

    inet 192.168.92.130/24 brd 192.168.92.255 scope global eth0

    inet 192.168.92.100/32 scope global eth0

    inet6 fe80::20c:29ff:fe25:689f/64 scope link 

       valid_lft forever preferred_lft forever

[root@chen sbin]# ipvsadm -l -n

IP Virtual Server version 1.2.1 (size=4096)

Prot LocalAddress:Port Scheduler Flags

  -> RemoteAddress:Port           Forward Weight ActiveConn InActConn

TCP  192.168.92.100:80 wlc persistent 50

  -> 192.168.92.131:80            Route   1      0          0     
3. BackUp服务器的配置
1). 配置ip 192.168.92.130
2). 安装ipvsadm
3). 安装keepalived
4). 配置keepalived


[root@chen keepalived]# cat /etc/keepalived/keepalived.conf

! Configuration File for keepalived


global_defs {

   router_id LVS_DEVEL

}


vrrp_instance VI_1 {

    state BACKUP

    interface eth0

    virtual_router_id 51

    priority 80

    advert_int 1

    authentication {

        auth_type PASS

        auth_pass 1111

    }

    virtual_ipaddress {

        192.168.92.100

    }

}


virtual_server 192.168.92.100 80 {

    delay_loop 6

    lb_algo wlc

    lb_kind DR

    nat_mask 255.255.255.0

    persistence_timeout 50

    protocol TCP


    real_server 192.168.92.131 80 {

        weight 1

        TCP_CHECK {

            url {

            connect_timeout 3

            nb_get_retry 3

            delay_before_retry 3

	    connect_port 80

        }

    }

}
5). 关闭防火墙，启动ipvsadm,keepalived



[root@chen sbin]# service iptables stop

[root@chen sbin]# service ipvsadm restart

ipvsadm: Clearing the current IPVS table:                  [确定]

ipvsadm: Unloading modules:                                [确定]

ipvsadm: Clearing the current IPVS table:                  [确定]

ipvsadm: Applying IPVS configuration:                      [确定]

[root@chen sbin]# service keepalived restart

停止 keepalived：                                          [确定]

正在启动 keepalived：                                      [确定]
6). 查看状态


[root@chen keepalived]# ipvsadm -l -n

IP Virtual Server version 1.2.1 (size=4096)

Prot LocalAddress:Port Scheduler Flags

  -> RemoteAddress:Port           Forward Weight ActiveConn InActConn

TCP  192.168.92.100:80 wlc persistent 50

  -> 192.168.92.131:80            Route   1      0          0         

[root@chen keepalived]# ip addr list

1: lo: 
<LOOPBACK
,
UP
,
LOWER_UP
>
 mtu 65536 qdisc noqueue state UNKNOWN 

    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00

    inet 127.0.0.1/8 scope host lo

    inet6 ::1/128 scope host 

       valid_lft forever preferred_lft forever

2: eth0: 
<BROADCAST
,
MULTICAST
,
UP
,
LOWER_UP
>
 mtu 1500 qdisc pfifo_fast state UNKNOWN qlen 1000

    link/ether 00:0c:29:fb:8c:5c brd ff:ff:ff:ff:ff:ff

    inet 192.168.92.128/24 brd 192.168.92.255 scope global eth0

    inet6 fe80::20c:29ff:fefb:8c5c/64 scope link 

       valid_lft forever preferred_lft forever
4. 测试
1). 客户端（192.168.92.1）浏览器输入  http://192.168.92.100/index.html  正常访问
2). 停止Master keepalived服务， service keepalived stop, 刷新浏览器还能正常访问
在BACKUP服务器会发现多了一个192.168.92.100的ip


[root@chen keepalived]# ip addr list

1: lo: 
<LOOPBACK
,
UP
,
LOWER_UP
>
 mtu 65536 qdisc noqueue state UNKNOWN 

    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00

    inet 127.0.0.1/8 scope host lo

    inet6 ::1/128 scope host 

       valid_lft forever preferred_lft forever

2: eth0: 
<BROADCAST
,
MULTICAST
,
UP
,
LOWER_UP
>
 mtu 1500 qdisc pfifo_fast state UNKNOWN qlen 1000

    link/ether 00:0c:29:fb:8c:5c brd ff:ff:ff:ff:ff:ff

    inet 192.168.92.128/24 brd 192.168.92.255 scope global eth0

    inet 192.168.92.100/32 scope global eth0

    inet6 fe80::20c:29ff:fefb:8c5c/64 scope link 

       valid_lft forever preferred_lft forever





