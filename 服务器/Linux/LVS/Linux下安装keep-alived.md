1. 打开官网 http://www.keepalived.org/  下载 keepalived-1.2.19.tar.gz
下载地址  http://www.keepalived.org/software/keepalived-1.2.19.tar.gz
2. 安装gcc等


[root@chen ~]#  yum --disablerepo=\* --enablerepo=c6-media -y install gcc kernel-devel openssl-devel 

已加载插件：fastestmirror, security

设置安装进程

Loading mirror speeds from cached hostfile

c6-media                                                           | 4.0 kB     00:00 ... 

解决依赖关系

--> 执行事务检查

---> Package gcc.i686 0:4.4.7-11.el6 will be 安装

--> 处理依赖关系 cpp = 4.4.7-11.el6，它被软件包 gcc-4.4.7-11.el6.i686 需要

--> 处理依赖关系 cloog-ppl >= 0.15，它被软件包 gcc-4.4.7-11.el6.i686 需要

---> Package kernel-devel.i686 0:2.6.32-504.el6 will be 安装

---> Package openssl-devel.i686 0:1.0.1e-30.el6 will be 安装

--> 处理依赖关系 zlib-devel，它被软件包 openssl-devel-1.0.1e-30.el6.i686 需要

--> 处理依赖关系 krb5-devel，它被软件包 openssl-devel-1.0.1e-30.el6.i686 需要

--> 执行事务检查

---> Package cloog-ppl.i686 0:0.15.7-1.2.el6 will be 安装

--> 处理依赖关系 libppl_c.so.2，它被软件包 cloog-ppl-0.15.7-1.2.el6.i686 需要

--> 处理依赖关系 libppl.so.7，它被软件包 cloog-ppl-0.15.7-1.2.el6.i686 需要

---> Package cpp.i686 0:4.4.7-11.el6 will be 安装

--> 处理依赖关系 libmpfr.so.1，它被软件包 cpp-4.4.7-11.el6.i686 需要

---> Package krb5-devel.i686 0:1.10.3-33.el6 will be 安装

--> 处理依赖关系 libselinux-devel，它被软件包 krb5-devel-1.10.3-33.el6.i686 需要

--> 处理依赖关系 libcom_err-devel，它被软件包 krb5-devel-1.10.3-33.el6.i686 需要

--> 处理依赖关系 keyutils-libs-devel，它被软件包 krb5-devel-1.10.3-33.el6.i686 需要

---> Package zlib-devel.i686 0:1.2.3-29.el6 will be 安装

--> 执行事务检查

---> Package keyutils-libs-devel.i686 0:1.4-5.el6 will be 安装

---> Package libcom_err-devel.i686 0:1.41.12-21.el6 will be 安装

---> Package libselinux-devel.i686 0:2.0.94-5.8.el6 will be 安装

--> 处理依赖关系 libsepol-devel >= 2.0.32-1，它被软件包 libselinux-devel-2.0.94-5.8.el6.i686 需要

--> 处理依赖关系 pkgconfig(libsepol)，它被软件包 libselinux-devel-2.0.94-5.8.el6.i686 需要

---> Package mpfr.i686 0:2.4.1-6.el6 will be 安装

---> Package ppl.i686 0:0.10.2-11.el6 will be 安装

--> 执行事务检查

---> Package libsepol-devel.i686 0:2.0.41-4.el6 will be 安装

--> 完成依赖关系计算


依赖关系解决


==========================================================================================

 软件包                      架构         版本                     仓库              大小

==========================================================================================

正在安装:

 gcc                         i686         4.4.7-11.el6             c6-media         8.2 M

 kernel-devel                i686         2.6.32-504.el6           c6-media         9.3 M

 openssl-devel               i686         1.0.1e-30.el6            c6-media         1.2 M

为依赖而安装:

 cloog-ppl                   i686         0.15.7-1.2.el6           c6-media          93 k

 cpp                         i686         4.4.7-11.el6             c6-media         3.4 M

 keyutils-libs-devel         i686         1.4-5.el6                c6-media          29 k

 krb5-devel                  i686         1.10.3-33.el6            c6-media         497 k

 libcom_err-devel            i686         1.41.12-21.el6           c6-media          32 k

 libselinux-devel            i686         2.0.94-5.8.el6           c6-media         137 k

 libsepol-devel              i686         2.0.41-4.el6             c6-media          64 k

 mpfr                        i686         2.4.1-6.el6              c6-media         153 k

 ppl                         i686         0.10.2-11.el6            c6-media         1.3 M

 zlib-devel                  i686         1.2.3-29.el6             c6-media          44 k


事务概要

==========================================================================================

Install      13 Package(s)


总下载量：24 M

Installed size: 58 M

下载软件包：

------------------------------------------------------------------------------------------

总计                                                       11 MB/s |  24 MB     00:02     

运行 rpm_check_debug 

执行事务测试

事务测试成功

执行事务

  正在安装   : libsepol-devel-2.0.41-4.el6.i686                                      1/13 

  正在安装   : libselinux-devel-2.0.94-5.8.el6.i686                                  2/13 

  正在安装   : libcom_err-devel-1.41.12-21.el6.i686                                  3/13 

  正在安装   : zlib-devel-1.2.3-29.el6.i686                                          4/13 

  正在安装   : keyutils-libs-devel-1.4-5.el6.i686                                    5/13 

  正在安装   : kernel-devel-2.6.32-504.el6.i686                                      6/13 

  正在安装   : krb5-devel-1.10.3-33.el6.i686                                         7/13 

  正在安装   : ppl-0.10.2-11.el6.i686                                                8/13 

  正在安装   : cloog-ppl-0.15.7-1.2.el6.i686                                         9/13 

  正在安装   : mpfr-2.4.1-6.el6.i686                                                10/13 

  正在安装   : openssl-devel-1.0.1e-30.el6.i686                                     11/13 

  正在安装   : cpp-4.4.7-11.el6.i686                                                12/13 

  正在安装   : gcc-4.4.7-11.el6.i686                                                13/13 

  Verifying  : cpp-4.4.7-11.el6.i686                                                 1/13 

  Verifying  : libselinux-devel-2.0.94-5.8.el6.i686                                  2/13 

  Verifying  : kernel-devel-2.6.32-504.el6.i686                                      3/13 

  Verifying  : keyutils-libs-devel-1.4-5.el6.i686                                    4/13 

  Verifying  : openssl-devel-1.0.1e-30.el6.i686                                      5/13 

  Verifying  : zlib-devel-1.2.3-29.el6.i686                                          6/13 

  Verifying  : mpfr-2.4.1-6.el6.i686                                                 7/13 

  Verifying  : ppl-0.10.2-11.el6.i686                                                8/13 

  Verifying  : gcc-4.4.7-11.el6.i686                                                 9/13 

  Verifying  : cloog-ppl-0.15.7-1.2.el6.i686                                        10/13 

  Verifying  : krb5-devel-1.10.3-33.el6.i686                                        11/13 

  Verifying  : libcom_err-devel-1.41.12-21.el6.i686                                 12/13 

  Verifying  : libsepol-devel-2.0.41-4.el6.i686                                     13/13 


已安装:

  gcc.i686 0:4.4.7-11.el6                     kernel-devel.i686 0:2.6.32-504.el6         

  openssl-devel.i686 0:1.0.1e-30.el6         


作为依赖被安装:

  cloog-ppl.i686 0:0.15.7-1.2.el6             cpp.i686 0:4.4.7-11.el6                    

  keyutils-libs-devel.i686 0:1.4-5.el6        krb5-devel.i686 0:1.10.3-33.el6            

  libcom_err-devel.i686 0:1.41.12-21.el6      libselinux-devel.i686 0:2.0.94-5.8.el6     

  libsepol-devel.i686 0:2.0.41-4.el6          mpfr.i686 0:2.4.1-6.el6                    

  ppl.i686 0:0.10.2-11.el6                    zlib-devel.i686 0:1.2.3-29.el6             


完毕！
3. 将 keepalived-1.2.19.tar.gz 上传到服务器的/usr/local/目录
4. 解压 keepalived-1.2.19.tar.gz
tar -zxvf  keepalived-1.2.19.tar.gz
5. 配置keepalived


[root@chen keepalived-1.2.19]#  ./configure --sysconf=/etc/ --with-kernel-dir=/usr/src/kernels/2.6.32-504.el6.i686/

checking for gcc... gcc

checking whether the C compiler works... yes

checking for C compiler default output file name... a.out

checking for suffix of executables... 

checking whether we are cross compiling... no

checking for suffix of object files... o

checking whether we are using the GNU C compiler... yes

checking whether gcc accepts -g... yes

checking for gcc option to accept ISO C89... none needed

checking for a BSD-compatible install... /usr/bin/install -c

checking for strip... strip

checking how to run the C preprocessor... gcc -E

checking for grep that handles long lines and -e... /bin/grep

checking for egrep... /bin/grep -E

checking for ANSI C header files... yes

checking for sys/wait.h that is POSIX.1 compatible... yes

checking for sys/types.h... yes

checking for sys/stat.h... yes

checking for stdlib.h... yes

checking for string.h... yes

checking for memory.h... yes

checking for strings.h... yes

checking for inttypes.h... yes

checking for stdint.h... yes

checking for unistd.h... yes

checking fcntl.h usability... yes

checking fcntl.h presence... yes

checking for fcntl.h... yes

checking syslog.h usability... yes

checking syslog.h presence... yes

checking for syslog.h... yes

checking for unistd.h... (cached) yes

checking sys/ioctl.h usability... yes

checking sys/ioctl.h presence... yes

checking for sys/ioctl.h... yes

checking sys/time.h usability... yes

checking sys/time.h presence... yes

checking for sys/time.h... yes

checking openssl/ssl.h usability... yes

checking openssl/ssl.h presence... yes

checking for openssl/ssl.h... yes

checking openssl/md5.h usability... yes

checking openssl/md5.h presence... yes

checking for openssl/md5.h... yes

checking openssl/err.h usability... yes

checking openssl/err.h presence... yes

checking for openssl/err.h... yes

checking whether ETHERTYPE_IPV6 is declared... yes

checking for crypt in -lcrypt... yes

checking for MD5_Init in -lcrypto... yes

checking for SSL_CTX_new in -lssl... yes

checking for nl_socket_alloc in -lnl-3... no

checking for nl_socket_modify_cb in -lnl... no

configure: WARNING: keepalived will be built without libnl support.

checking for kernel version... 2.6.32

checking for IPVS syncd support... yes

checking for kernel macvlan support... yes

checking whether SO_MARK is declared... yes

checking for an ANSI C-conforming const... yes

checking for pid_t... yes

checking whether time.h and sys/time.h may both be included... yes

checking whether gcc needs -traditional... no

checking for working memcmp... yes

checking return type of signal handlers... void

checking for gettimeofday... yes

checking for select... yes

checking for socket... yes

checking for strerror... yes

checking for strtol... yes

checking for uname... yes

configure: creating ./config.status

config.status: creating Makefile

config.status: creating genhash/Makefile

config.status: creating keepalived/core/Makefile

config.status: creating lib/config.h

config.status: creating keepalived.spec

config.status: creating keepalived/Makefile

config.status: creating lib/Makefile

config.status: creating keepalived/vrrp/Makefile

config.status: creating keepalived/check/Makefile

config.status: creating keepalived/libipvs-2.6/Makefile


Keepalived configuration

------------------------

Keepalived version       : 1.2.19

Compiler                 : gcc

Compiler flags           : -g -O2

Extra Lib                : -lssl -lcrypto -lcrypt 

Use IPVS Framework       : Yes

IPVS sync daemon support : Yes

IPVS use libnl           : No

fwmark socket support    : Yes

Use VRRP Framework       : Yes

Use VRRP VMAC            : Yes

SNMP support             : No

SHA1 support             : No

Use Debug flags          : No
6. 编译


[root@chen keepalived-1.2.19]# make;make install

make -C lib || exit 1;

make[1]: Entering directory `/usr/local/keepalived-1.2.19/lib'

gcc -I. -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_WITHOUT_SNMP_ -c memory.c

gcc -I. -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_WITHOUT_SNMP_ -c utils.c

gcc -I. -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_WITHOUT_SNMP_ -c notify.c

gcc -I. -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_WITHOUT_SNMP_ -c timer.c

gcc -I. -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_WITHOUT_SNMP_ -c scheduler.c

gcc -I. -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_WITHOUT_SNMP_ -c vector.c

gcc -I. -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_WITHOUT_SNMP_ -c list.c

gcc -I. -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_WITHOUT_SNMP_ -c html.c

gcc -I. -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_WITHOUT_SNMP_ -c parser.c

gcc -I. -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_WITHOUT_SNMP_ -c signals.c

gcc -I. -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_WITHOUT_SNMP_ -c logger.c

make[1]: Leaving directory `/usr/local/keepalived-1.2.19/lib'

make -C keepalived

make[1]: Entering directory `/usr/local/keepalived-1.2.19/keepalived'

make[2]: Entering directory `/usr/local/keepalived-1.2.19/keepalived/core'

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c main.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c daemon.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c pidfile.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c layer4.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c smtp.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c global_data.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c global_parser.c

make[2]: Leaving directory `/usr/local/keepalived-1.2.19/keepalived/core'

make[2]: Entering directory `/usr/local/keepalived-1.2.19/keepalived/check'

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c check_daemon.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c check_data.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c check_parser.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c check_api.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c check_tcp.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c check_http.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c check_ssl.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c check_smtp.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c check_misc.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c ipwrapper.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_WITH_VRRP_ -D_WITHOUT_SNMP_ -D_WITH_SO_MARK_  -c ipvswrapper.c

make[2]: Leaving directory `/usr/local/keepalived-1.2.19/keepalived/check'

make[2]: Entering directory `/usr/local/keepalived-1.2.19/keepalived/vrrp'

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_daemon.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_print.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_data.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_parser.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp.c

vrrp.c: 在函数‘vrrp_in_chk’中:

vrrp.c:313: 警告：格式‘%lu’需要类型‘long unsigned int’，但实参 4 的类型为‘unsigned int’

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_notify.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_scheduler.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_sync.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_index.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_netlink.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_arp.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_if.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_track.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_ipaddress.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_iproute.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_ipsecah.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_ndisc.c

gcc -I../include -I../../lib -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes -D_KRNL_2_6_ -D_WITH_LVS_ -D_HAVE_IPVS_SYNCD_ -D_HAVE_VRRP_VMAC_ -D_WITHOUT_SNMP_  -c vrrp_vmac.c

make[2]: Leaving directory `/usr/local/keepalived-1.2.19/keepalived/vrrp'

make[2]: Entering directory `/usr/local/keepalived-1.2.19/keepalived/libipvs-2.6'

gcc -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -DLIBIPVS_DONTUSE_NL -Wall -Wunused -c -o libipvs.o libipvs.c

gcc -g -O2  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -DLIBIPVS_DONTUSE_NL -Wall -Wunused -c -o ip_vs_nl_policy.o ip_vs_nl_policy.c

ar rv libipvs.a libipvs.o ip_vs_nl_policy.o

ar: creating libipvs.a

a - libipvs.o

a - ip_vs_nl_policy.o

rm libipvs.o ip_vs_nl_policy.o

make[2]: Leaving directory `/usr/local/keepalived-1.2.19/keepalived/libipvs-2.6'

Building ../bin/keepalived

strip ../bin/keepalived


Make complete

make[1]: Leaving directory `/usr/local/keepalived-1.2.19/keepalived'

make -C genhash

make[1]: Entering directory `/usr/local/keepalived-1.2.19/genhash'

gcc -I../lib -g -O2 -D_WITH_SO_MARK_  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes   -c -o main.o main.c

gcc -I../lib -g -O2 -D_WITH_SO_MARK_  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes   -c -o sock.o sock.c

gcc -I../lib -g -O2 -D_WITH_SO_MARK_  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes   -c -o layer4.o layer4.c

gcc -I../lib -g -O2 -D_WITH_SO_MARK_  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes   -c -o http.o http.c

gcc -I../lib -g -O2 -D_WITH_SO_MARK_  -I/usr/src/kernels/2.6.32-504.el6.i686//include -I/usr/src/kernels/2.6.32-504.el6.i686//include -Wall -Wunused -Wstrict-prototypes   -c -o ssl.o ssl.c

Building ../bin/genhash

strip ../bin/genhash


Make complete

make[1]: Leaving directory `/usr/local/keepalived-1.2.19/genhash'


Make complete

make -C keepalived install

make[1]: Entering directory `/usr/local/keepalived-1.2.19/keepalived'

install -d /usr/local/sbin

install -m 700 ../bin/keepalived /usr/local/sbin/

install -d /etc/rc.d/init.d

install -m 755 etc/init.d/keepalived.init /etc/rc.d/init.d/keepalived

install -d /etc/sysconfig

install -m 644 etc/init.d/keepalived.sysconfig /etc/sysconfig/keepalived

install -d /etc/keepalived/samples

install -m 644 etc/keepalived/keepalived.conf /etc/keepalived/

install -m 644 ../doc/samples/* /etc/keepalived/samples/

install -d /usr/local/share/man/man5

install -d /usr/local/share/man/man8

install -m 644 ../doc/man/man5/keepalived.conf.5 /usr/local/share/man/man5

install -m 644 ../doc/man/man8/keepalived.8 /usr/local/share/man/man8

make[1]: Leaving directory `/usr/local/keepalived-1.2.19/keepalived'

make -C genhash install

make[1]: Entering directory `/usr/local/keepalived-1.2.19/genhash'

install -d /usr/local/bin

install -m 755 ../bin/genhash /usr/local/bin/

install -d /usr/local/share/man/man1

install -m 644 ../doc/man/man1/genhash.1 /usr/local/share/man/man1

make[1]: Leaving directory `/usr/local/keepalived-1.2.19/genhash'
7. 创建链接
ln -s /usr/local/sbin/keepalived/ /usr/sbin/
8. 启动keepalived
[root@chen keepalived]# service keepalived start
正在启动 keepalived：



