---
title: git设置mergetool可视化工具BeyondCompare
date: 2019-04-17
categories: git
tags: [git,mergetool,BeyondCompare]
---

可以设置BeyondCompare作为git的比较和合并的可视化工具,方便操作.
设置如下:

1. 先下载并安装BeyondCompare
2. 命令输入如下配置
```
#difftool 配置
git config --global diff.tool bc4
git config --global difftool.bc4.cmd "\"C:/Program Files/Beyond Compare 4/BComp.exe\" \"$LOCAL\" \"$REMOTE\""

#mergeftool 配置
git config --global merge.tool bc4
git config --global mergetool.bc4.cmd  "\"C:/Program Files/Beyond Compare 4/BComp.exe\" \"$LOCAL\" \"$REMOTE\" \"$BASE\" \"$MERGED\""
git config --global mergetool.bc4.trustExitCode true

#让git mergetool不再生成备份文件（*.orig）
git config --global mergetool.keepBackup false
```

若未生效，直接修改用户目录下.gitconfig文件
```
[diff]
	tool = bc4
[difftool "bc4"]
	cmd = \"C:/Program Files/Beyond Compare 4/BComp.exe\" \"$LOCAL\" \"$REMOTE\"
[merge]
	tool = bc4
[mergetool "bc4"]
	cmd = \"C:/Program Files/Beyond Compare 4/BComp.exe\" \"$LOCAL\" \"$REMOTE\" \"$BASE\" \"$MERGED\"
	trustExitCode = true
[mergetool]
	keepBackup = false
```

使用方法如下:
```
# diff使用方法:
git difftool HEAD // 比较当前修改情况

# merge使用方法
# git mergetool
```