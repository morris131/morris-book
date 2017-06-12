

[root@chen ~]# ipvsadm -help

ipvsadm v1.26 2008/5/15 (compiled with popt and IPVS v1.2.1)

Usage:

  ipvsadm -A|E -t|u|f service-address [-s scheduler] [-p [timeout]] [-M netmask] [--pe persistence_engine]

  ipvsadm -D -t|u|f service-address

  ipvsadm -C

  ipvsadm -R

  ipvsadm -S [-n]

  ipvsadm -a|e -t|u|f service-address -r server-address [options]

  ipvsadm -d -t|u|f service-address -r server-address

  ipvsadm -L|l [options]

  ipvsadm -Z [-t|u|f service-address]

  ipvsadm --set tcp tcpfin udp

  ipvsadm --start-daemon state [--mcast-interface interface] [--syncid sid]

  ipvsadm --stop-daemon state

  ipvsadm -h


Commands:

Either long or short options are allowed.

  --add-service     -A        add virtual service with options

  --edit-service    -E        edit virtual service with options

  --delete-service  -D        delete virtual service

  --clear           -C        clear the whole table

  --restore         -R        restore rules from stdin

  --save            -S        save rules to stdout

  --add-server      -a        add real server with options

  --edit-server     -e        edit real server with options

  --delete-server   -d        delete real server

  --list            -L|-l     list the table

  --zero            -Z        zero counters in a service or all services

  --set tcp tcpfin udp        set connection timeout values

  --start-daemon              start connection sync daemon

  --stop-daemon               stop connection sync daemon

  --help            -h        display this help message


Options:

  --tcp-service  -t service-address   service-address is host[:port]

  --udp-service  -u service-address   service-address is host[:port]

  --fwmark-service  -f fwmark         fwmark is an integer greater than zero

  --ipv6         -6                   fwmark entry uses IPv6

  --scheduler    -s scheduler         one of rr|wrr|lc|wlc|lblc|lblcr|dh|sh|sed|nq,

                                      the default scheduler is wlc.

  --pe            engine              alternate persistence engine may be sip,

                                      not set by default.

  --persistent   -p [timeout]         persistent service

  --netmask      -M netmask           persistent granularity mask

  --real-server  -r server-address    server-address is host (and port)

  --gatewaying   -g                   gatewaying (direct routing) (default)

  --ipip         -i                   ipip encapsulation (tunneling)

  --masquerading -m                   masquerading (NAT)

  --weight       -w weight            capacity of real server

  --u-threshold  -x uthreshold        upper threshold of connections

  --l-threshold  -y lthreshold        lower threshold of connections

  --mcast-interface interface         multicast interface for connection sync

  --syncid sid                        syncid for connection sync (default=255)

  --connection   -c                   output of current IPVS connections

  --timeout                           output of timeout (tcp tcpfin udp)

  --daemon                            output of daemon information

  --stats                             output of statistics information

  --rate                              output of rate information

  --exact                             expand numbers (display exact values)

  --thresholds                        output of thresholds information

  --persistent-conn                   output of persistent connection info

  --nosort                            disable sorting output of service/server entries

  --sort                              does nothing, for backwards compatibility

  --ops          -o                   one-packet scheduling

  --numeric      -n                   numeric output of addresses and ports
ipvsadm有两种命令选项格式，长的和短的，具有相同的意思。在实际使用时，两种都可 以。
-A –add-service 在内核的虚拟服务器表中添加一条新的虚拟服务器记录。也 就是增加一台新的虚拟服务器。
-E –edit-service 编辑内核虚拟服务器表中的一条虚拟服务器记录。
-D –delete-service 删除内核虚拟服务器表中的一条虚拟服务器记录。
-C –clear 清除内核虚拟服务器表中的所有记录。
-R –restore 恢复虚拟服务器规则
-S –save 保存虚拟服务器规则，输出为-R 选项可读的格式
-a –add-server 在内核虚拟服务器表的一条记录里添加一条新的真实服务器 记录。也就是在一个虚拟服务器中增加一台新的真实服务器
-e –edit-server 编辑一条虚拟服务器记录中的某条真实服务器记录
-d –delete-server 删除一条虚拟服务器记录中的某条真实服务器记录
-L|-l –list 显示内核虚拟服务器表
-Z –zero 虚拟服务表计数器清零（清空当前的连接数量等）
–set tcp tcpfin udp 设置连接超时值
–start-daemon 启动同步守护进程。他后面可以是master 或backup，用来说 明LVS Router 是master 或是backup。在这个功能上也可以采用keepalived 的 VRRP 功能。
–stop-daemon 停止同步守护进程
-h –help 显示帮助信息
其他的选项:
-t –tcp-service service-address 说明虚拟服务器提供的是tcp 的服务
[vip:port] or [real-server-ip:port]
-u –udp-service service-address 说明虚拟服务器提供的是udp 的服务
[vip:port] or [real-server-ip:port]
-f –fwmark-service fwmark 说明是经过iptables 标记过的服务类型。
-s –scheduler scheduler 使用的调度算法，有这样几个选项 rr|wrr|lc|wlc|lblc|lblcr|dh|sh|sed|nq, 默认的调度算法是： wlc.
-p –persistent [timeout] 持久稳固的服务。这个选项的意思是来自同一个客 户的多次请求，将被同一台真实的服务器处理。timeout 的默认值为300 秒。
-M –netmask netmask persistent granularity mask
-r –real-server server-address 真实的服务器[Real-Server:port]
-g –gatewaying 指定LVS 的工作模式为直接路由模式（也是LVS 默认的模式）
-i –ipip 指定LVS 的工作模式为隧道模式
-m –masquerading 指定LVS 的工作模式为NAT 模式
-w –weight weight 真实服务器的权值
–mcast-interface interface 指定组播的同步接口
-c –connection 显示LVS 目前的连接 如：ipvsadm -L -c
–timeout 显示tcp tcpfin udp 的timeout 值 如：ipvsadm -L –timeout
–daemon 显示同步守护进程状态
–stats 显示统计信息
–rate 显示速率信息
–sort 对虚拟服务器和真实服务器排序输出
–numeric -n 输出IP 地址和端口的数字形式
