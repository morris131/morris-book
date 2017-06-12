1. 注册github账号，官网地址： https://github.com/
2. 生成ssh keys

```shell
$ ssh-keygen -t rsa -C "morris131@163.com"

Generating public/private rsa key pair.

Enter file in which to save the key (/c/Documents and Settings/lian.chen/.ssh/id_rsa):

Enter passphrase (empty for no passphrase):

Enter same passphrase again:

Your identification has been saved in /c/Documents and Settings/lian.chen/.ssh/id_rsa.

Your public key has been saved in /c/Documents and Settings/lian.chen/.ssh/id_rsa.pub.

The key fingerprint is:

SHA256:rHaH9YurFuP5UvEkJE77iNKRDnmFK4yDFMdDupNxd5Q morris131@163.com

The key's randomart image is:

+---[RSA 2048]----+

| .+o   o.        |

| .oo  oE+ .      |

|.+ +.o B +       |

|. B * *.o o .    |

| + . * oSo.=     |

|  . . +.+oo..    |

|     .o.o=. .    |

|     . .=. . .   |

|       ..++..    |

+----[SHA256]-----+
```

3.将ssh keys 添加到github 
在用户目录的.ssh下找到id_rsa.pub,打开全选复制里面的内容粘贴到下面如图所示




4. 测试是否添加成功


$ ssh -T git@github.com

The authenticity of host 'github.com (192.30.252.130)' can't be established.

RSA key fingerprint is SHA256:nThbg6kXUpJWGl7E1IGOCspRomTxdCARLviKw6E5SY8.

Are you sure you want to continue connecting (yes/no)? yes

Warning: Permanently added 'github.com,192.30.252.130' (RSA) to the list of known hosts.

Hi morris131! You've successfully authenticated, but GitHub does not provide shell access.

