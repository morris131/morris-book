## Bug分支

软件开发中，bug就像家常便饭一样。有了bug就需要修复，在Git中，由于分支是如此的强大，所以，每个bug都可以通过一个新的临时分支来修复，修复后，合并分支，然后将临时分支删除。
1. 查看工作区状态
```shell 
$ git status
On branch master
Your branch is ahead of 'origin/master' by 6 commits.
  (use "git push" to publish your local commits)
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)
        modified:   readme.txt
no changes added to commit (use "git add" and/or "git commit -a")
         ```
2. 储藏当前工作现场“储藏”起来：

```shell
$ git stash
Saved working directory and index state WIP on master: 07dd582 merge no-ff
HEAD is now at 07dd582 merge no-ff
$ git status
On branch master
Your branch is ahead of 'origin/master' by 6 commits.
  (use "git push" to publish your local commits)
nothing to commit, working directory clean
```
3. 创建bug分支

```shell
$ git checkout -b bug1
Switched to a new branch 'bug1'
```
4. 修复bug，并提交代码

```shell
$ git status
On branch bug1
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)
        modified:   readme.txt
no changes added to commit (use "git add" and/or "git commit -a")
$ git add readme.txt
$ git commit -m "fixed bug1"
[bug1 95d8722] fixed bug1
 1 file changed, 1 insertion(+), 1 deletion(-)
 ```
5. 切换回master分支，合并分支

```shell
$ git checkout master
Switched to branch 'master'
Your branch is ahead of 'origin/master' by 6 commits.
  (use "git push" to publish your local commits)
$ git merge bug1
Updating 07dd582..95d8722
Fast-forward
 readme.txt | 2 +-
 1 file changed, 1 insertion(+), 1 deletion(-)
```
6. 删除bug1分支

```shell
$ git branch -d bug1
Deleted branch bug1 (was 95d8722).
```

7. 查看储藏的现场

```shell
$ git stash list
stash@{0}: WIP on master: 07dd582 merge no-ff
8. 恢复现场
$ git stash apply stash@{0}
Auto-merging readme.txt
On branch master
Your branch is ahead of 'origin/master' by 7 commits.
  (use "git push" to publish your local commits)
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)
        modified:   readme.txt
no changes added to commit (use "git add" and/or "git commit -a")
$ git stash list
stash@{0}: WIP on master: 07dd582 merge no-ff
```
另一种方式是用git stash pop恢复现场，恢复的同时把stash内容也删了
