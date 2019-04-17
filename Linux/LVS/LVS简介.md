1. 什么是LVS
     LVS是Linux Virtual Server的简称，也就是Linux虚拟服务器。这是一个由章文嵩博士发起的一个开源项目，它的官方网站是http://www.linuxvirtualserver.org/.现在LVS已经是Linux内核标准的一部分。
     使用LVS可以达到的技术目标是：通过LVS达到的负载均衡技术和Linux操作系统实现一个高性能高可用的Linux服务器群集，它具有良好的可靠性，可拓展性和可操作性。从而以低廉的成本实现最优的性能。
     LVS从1998年开始，发展到现在已经是一个比较成熟的项目了。利用LVS技术可以实现高性能，高可压缩的网路服务，例如WWW服务，FTP服务，MAIL服务等。比较著名的就是www.linux.com以及www.real.com。


2、LVS的体系架构


     L oad Balancer层：位于整个集群系统的最前端，有一台或者多台负载调度器（Director Server）组成，LVS模块就安装在Director Server上，而Director的主要作用类似于一个路由器，它含有完成LVS功能所设定的路由表，通过这些路由表把用户的请求分发给Server Array层的应用服务器（Real Server）上。同时，在Director Server上还要安装对Real Server服务的监控模块Ldirectord，此模块用于监测各个Real Server服务的健康状况。在Real Server不可用时把它从LVS路由表中剔除，恢复时重新加入。
     Server Array层：由一组实际运行应用服务的机器组成，Real Server可以是WEB服务器、MAIL服务器、FTP服务器、DNS服务器、视频服务器中的一个或者多个，每个Real Server之间通过高速的LAN或分布在各地的WAN相连接。在实际的应用中，Director Server也可以同时兼任Real Server的角色。
     Shared Storage层：是为所有Real Server提供共享存储空间和内容一致性的存储区域，在物理上，一般有磁盘阵列设备组成，为了提供内容的一致性，一般可以通过NFS网络文件系统共享数 据，但是NFS在繁忙的业务系统中，性能并不是很好，此时可以采用集群文件系统，例如Red hat的GFS文件系统，oracle提供的OCFS2文件系统等。
     从整个LVS结构可以看出，Director Server是整个LVS的核心，目前，用于Director Server的操作系统只能是Linux和FreeBSD，linux2.6内核不用任何设置就可以支持LVS功能，而FreeBSD作为 Director Server的应用还不是很多，性能也不是很好。对于Real Server，几乎可以是所有的系统平台，Linux、windows、Solaris、AIX、BSD系列都能很好的支持。


4. LVS的特点
很好的可伸缩性（Scalability）。
很好的可靠性（Reliability）。
很好的可管理性（Manageability）。
开源、免费。


5. LVS的适用环境
     LVS 对前端Director Server目前仅支持Linux和FreeBSD系统，但是支持大多数的TCP和UDP协议，支持TCP协议的应用有：HTTP，HTTPS，FTP，SMTP，，POP3，IMAP4，PROXY，LDAP，SSMTP等等。 支持UDP协议的应用有：DNS，NTP，ICP，视频、音频流播放协议等。
     LVS对Real Server的操作系统没有任何限制，Real Server可运行在任何支持TCP/IP的操作系统上，包括Linux，各种Unix（如FreeBSD、Sun Solaris、HP Unix等），Mac/OS和Windows等。

