### Linux下yum安装MongoDB

#### 配置yum仓库
创建/etc/yum.repos.d/mongodb.repo
```
[mongodb-org-3.2]
name=MongoDB Repository
baseurl=https://repo.mongodb.org/yum/redhat/$releasever/mongodb-org/3.2/x86_64/
gpgcheck=1
enabled=1
gpgkey=https://www.mongodb.org/static/pgp/server-3.2.asc
```

#### yum安装
```
yum install -y mongodb-org
```