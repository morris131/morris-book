lvs已经编译到linux内核中，只需要安装lvs的管理软件ipvsadm即可
1. 插入光盘，查找设备


[root@chen ~]# ls -l /dev | grep cdrom

lrwxrwxrwx. 1 root root           3 12月 28 07:29 cdrom -> sr0

crw-rw----. 1 root cdrom    21,   1 12月 28 07:29 sg1

brw-rw----. 1 root cdrom    11,   0 12月 28 07:29 sr0
2. 加载光驱


[root@chen ~]# mount /dev/cdrom /mnt

mount: block device /dev/sr0 is write-protected, mounting read-only
3. 配置仓库
vi /etc/yum.repos.d/CentOS-Media.repo
修改前


# CentOS-Media.repo

#

#  This repo can be used with mounted DVD media, verify the mount point for

#  CentOS-6.  You can use this repo and yum to install items directly off the

#  DVD ISO that we release.

#

# To use this repo, put in your DVD and use it with the other repos too:

#  yum --enablerepo=c6-media [command]

#

# or for ONLY the media repo, do this:

#

#  yum --disablerepo=\* --enablerepo=c6-media [command]

[c6-media]

name=CentOS-$releasever - Media

baseurl=file:///media/CentOS/

        file:///media/cdrom/

        file:///media/cdrecorder/

gpgcheck=1

enabled=0

gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-6
修改后


# CentOS-Media.repo

#

#  This repo can be used with mounted DVD media, verify the mount point for

#  CentOS-6.  You can use this repo and yum to install items directly off the

#  DVD ISO that we release.

#

# To use this repo, put in your DVD and use it with the other repos too:

#  yum --enablerepo=c6-media [command]

#

# or for ONLY the media repo, do this:

#

#  yum --disablerepo=\* --enablerepo=c6-media [command]

[c6-media]

name=CentOS-$releasever - Media

baseurl=file:///media/CentOS/

        file:///media/cdrom/

        file:///media/cdrecorder/

gpgcheck=1

enabled=0

gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-6


4. 安装ipvsadm


[root@chen ~]# yum --disablerepo=\* --enablerepo=c6-media install ipvsadm

已加载插件：fastestmirror, security

设置安装进程

Loading mirror speeds from cached hostfile

c6-media                                                           | 4.0 kB     00:00 ... 

c6-media/primary_db                                                | 3.5 MB     00:02 ... 

解决依赖关系

--> 执行事务检查

---> Package ipvsadm.i686 0:1.26-4.el6 will be 安装

--> 完成依赖关系计算


依赖关系解决


==========================================================================================

 软件包              架构             版本                     仓库                  大小

==========================================================================================

正在安装:

 ipvsadm             i686             1.26-4.el6               c6-media              40 k


事务概要

==========================================================================================

Install       1 Package(s)


总下载量：40 k

Installed size: 65 k

确定吗？[y/N]：y

下载软件包：

warning: rpmts_HdrFromFdno: Header V3 RSA/SHA1 Signature, key ID c105b9de: NOKEY

Retrieving key from file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-6

Importing GPG key 0xC105B9DE:

 Userid : CentOS-6 Key (CentOS 6 Official Signing Key) 
<centos-6-key
@
centos
.
org
>

 Package: centos-release-6-6.el6.centos.12.2.i686 (@anaconda-CentOS-201410241409.i386/6.6)

 From   : /etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-6

确定吗？[y/N]：y

运行 rpm_check_debug 

执行事务测试

事务测试成功

执行事务

  正在安装   : ipvsadm-1.26-4.el6.i686                                                1/1 

  Verifying  : ipvsadm-1.26-4.el6.i686                                                1/1 


已安装:

  ipvsadm.i686 0:1.26-4.el6                                                               


完毕！
5. 查看是否安装成功


[root@chen ~]# ipvsadm

IP Virtual Server version 1.2.1 (size=4096)

Prot LocalAddress:Port Scheduler Flags

  -> RemoteAddress:Port           Forward Weight ActiveConn InActConn

