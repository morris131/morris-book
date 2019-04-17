软件开发中，总有无穷无尽的新的功能要不断添加进来。
添加一个新功能时，你肯定不希望因为一些实验性质的代码，把主分支搞乱了，所以，每添加一个新功能，最好新建一个feature分支，在上面开发，完成后，合并，最后，删除该feature分支。
1. 创建feature分支

```shell
$ git checkout -b feature

Switched to a new branch 'feature'
```
2. 添加feature.txt并提交代码

```shell


$ git status

On branch feature

Untracked files:

  (use "git add 
<file>
..." to include in what will be committed)


        feature.txt


nothing added to commit but untracked files present (use "git add" to track)

$ git add feature.txt

$ git commit -m "add feature"

[feature 6d99405] add feature

 1 file changed, 0 insertions(+), 0 deletions(-)

 create mode 100644 feature.txt
```
3. 删除分支

```shell


$ git checkout dev

Switched to branch 'dev'

$ git branch -d feature

error: The branch 'feature' is not fully merged.

If you are sure you want to delete it, run 'git branch -D feature'.

```
4. 强行删除

```shell

$ git branch -D feature

Deleted branch feature (was 6d99405).

```
