title: JDK命令行工具
date: 2018-10-20
categories: JVM
tags: [JDK命令行工具]
---

# JDK命令行工具

## jps:虚拟机进程状况工具
jps（JVM Process Status Tool）可以列出正在运行的虚拟机进程，并显示虚拟机执行主类（Main Class,main()函数所在的类）名称以及这些进程的本地虚拟机唯一ID（Local Virtual Machine Identifier,LVMID）。虽然功能比较单一，但它是使用频率最高的JDK命令行工具，因为其他的JDK工具大多需要输入它查询到的LVMID来确定要监控的是哪一个虚拟机进程。对于本地虚拟机进程来说，LVMID与操作系统的进程ID（Process Identifier,PID）是一致的，使用Windows的任务管理器或者UNIX的ps命令也可以查询到虚拟机进程的LVMID，但如果同时启动了多个虚拟机进程，无法根据进程名称定位时，那就只能依赖jps命令显示主类的功能才能区分了。

命令格式

```
 jps [options] [hostid]
```
option参数
- -l : 输出主类全名或jar路径
- -q : 只输出LVMID
- -m : 输出JVM启动时传递给main()的参数
- -v : 输出JVM启动时显示指定的JVM参数

示例：

```
>jps -l
4188 D:\Program
5436 sun.tools.jps.Jps
```

### jinfo:Java配置信息工具
jinfo(JVM Configuration info)这个命令作用是实时查看和调整虚拟机运行参数。之前的jps -v口令只能查看到显示指定的参数，如果想要查看未被显示指定的参数的值就要使用jinfo。

命令格式

```
jinfo [option] [args] LVMID
```
option参数
- -flag : 输出指定args参数的值
- -flags : 不需要args参数，输出所有JVM参数的值
- -sysprops : 输出系统属性，等同于System.getProperties()

示例：

```
>jinfo -flags 4188
Attaching to process ID 4188, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.172-b11
Non-default VM flags: -XX:CICompilerCount=3 -XX:ConcGCThreads=1 -XX:G1HeapRegion
Size=1048576 -XX:InitialHeapSize=268435456 -XX:MarkStackSize=4194304 -XX:MaxHeap
Size=1073741824 -XX:MaxNewSize=643825664 -XX:MinHeapDeltaBytes=1048576 -XX:+UseC
ompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -X
X:+UseG1GC -XX:-UseLargePagesIndividualAllocation -XX:+UseStringDeduplication
Command line:  -Dosgi.requiredJavaVersion=1.8 -Dosgi.instance.area.default=@user
.home/eclipse-workspace -XX:+UseG1GC -XX:+UseStringDeduplication -Dosgi.required
JavaVersion=1.8 -Xms256m -Xmx1024m -Declipse.p2.max.threads=10 -Doomph.update.ur
l=http://download.eclipse.org/oomph/updates/milestone/latest -Doomph.redirection
.index.redirection=index:/->http://git.eclipse.org/c/oomph/org.eclipse.oomph.git
/plain/setups/

```

### jstat：虚拟机统计信息监视工具
jstat(JVM statistics Monitoring)是用于监视虚拟机运行时状态信息的命令，它可以显示出虚拟机进程中的类装载、内存、垃圾收集、JIT编译等运行数据。

命令格式：

```
jstat -<option> [-t] [-h<lines>] <vmid> [<interval> [<count>]]
```
参数
- [option] : 操作参数
- LVMID : 本地虚拟机进程ID
- [interval] : 连续输出的时间间隔
- [count] : 连续输出的次数

对于命令格式中的VMID与LVMID需要特别说明一下：
如果是本地虚拟机进程，VMID与LVMID是一致的;
如果是远程虚拟机进程，那VMID的格式应当是：protocol://lvmid@hostname:port/servername
参数interval和count代表查询间隔(单位毫秒)和次数，如果省略这两个参数，说明只查询一次。

假设需要每250毫秒查询一次进程2764垃圾收集状况，一共查询20次，那命令应当是：jstat -gc 2764 250 20
j
选项option代表着用户希望查询的虚拟机信息，主要分为3类：类装载、垃圾收集、运行期编译状况，具体选项及作用如下：
- -class监视类装载，卸载数量，总空间以及类装载所耗费的时间
- -gc 监视Java堆状况，包括Eden区，两个survivor区，老年代，永久代的容量，已用空间，GC时间合计等信息
- -gccapacity内容与-gc基本相同，但主要输出Java堆各个区域的最大最小空间
- -gcutil内容与-gc基本相同，但主要关注已使用空间占总空间的百分比
- -gccause内容与-gcutil基本相同，但主要关注已使用空间占总空间的百分比,并输出导致上一次GC的原因
- -gcnew监视新生代GC情况
- -gcnewcapacity内容与-gcnew基本相同，但主要输出使用到的最大最小空间
- -gcold监视老年代GC情况
- -gcoldcapacity内容与-gcnew基本相同，但主要输出使用到的最大最小空间
- -gcpermcapacity输出永久代使用到的最大最小空间
- -complier输出JIT 编译器编译过的方法耗时的信息
- -printcompliter输出已经被JIT编译的方法
- 
option 参数详解

-class

监视类装载、卸载数量、总空间以及耗费的时间

$ jstat -class 11589

 Loaded  Bytes  Unloaded  Bytes     Time   

  7035  14506.3     0     0.0       3.67
•
Loaded : 加载class的数量

•
Bytes : class字节大小

•
Unloaded : 未加载class的数量

•
Bytes : 未加载class的字节大小

•
Time : 加载时间


-compiler

输出JIT编译过的方法数量耗时等

$ jstat -compiler 1262

Compiled Failed Invalid   Time   FailedType FailedMethod

2573      1       0    47.60          1               org/apache/catalina/loader/WebappClassLoader findResourceInternal
•
Compiled : 编译数量

•
Failed : 编译失败数量

•
Invalid : 无效数量

•
Time : 编译耗时

•
FailedType : 失败类型

•
FailedMethod : 失败方法的全限定名


-gc

垃圾回收堆的行为统计，常用命令jstat -gc 2764 250 20

S0　　　S1   　　E　　　　O　　　　P　　　　YGC　　YGCT　　　FGC　　FGCT   GCT

0.00　　0.00 　　6.20　　41.42　　47.20　　16　　　0.105　　 3　　　 0.472   0.577

查询结果表明：这台服务器的新生代Eden区（E，表示Eden）使用了6.2%的空间，两个Survivor区（S0、S1，表示Survivor0、Survivor1）里面都是空的，老年代（O，表示Old）和永久代（P，表示Permanent）则分别使用了41.42%和47.20%的空间。程序运行以来共发生Minor GC（YGC，表示Young GC）16次，总耗时0.105秒，发生Full GC（FGC，表示Full GC）3次，Full GC总耗时（FGCT，表示Full GC Time）为0.472秒，所有GC总耗时（GCT，表示GC Time）为0.577秒。

C即Capacity 总容量，U即Used 已使用的容量
•
S0C : survivor0区的总容量

•
S1C : survivor1区的总容量

•
S0U : survivor0区已使用的容量

•
S1C : survivor1区已使用的容量

•
EC : Eden区的总容量

•
EU : Eden区已使用的容量

•
OC : Old区的总容量

•
OU : Old区已使用的容量

•
PC 当前perm的容量 (KB)

•
PU perm的使用 (KB)

•
YGC : 新生代垃圾回收次数

•
YGCT : 新生代垃圾回收时间

•
FGC : 老年代垃圾回收次数

•
FGCT : 老年代垃圾回收时间

•
GCT : 垃圾回收总消耗时间


-gccapacity

同-gc，不过还会输出Java堆各区域使用到的最大、最小空间

$ jstat -gccapacity 1262
•
NGCMN : 新生代占用的最小空间

•
NGCMX : 新生代占用的最大空间

•
OGCMN : 老年代占用的最小空间

•
OGCMX : 老年代占用的最大空间

•
OGC：当前年老代的容量 (KB)

•
OC：当前年老代的空间 (KB)

•
PGCMN : perm占用的最小空间

•
PGCMX : perm占用的最大空间


-gcutil

同-gc，不过输出的是已使用空间占总空间的百分比

-gccause

垃圾收集统计概述（同-gcutil），附加最近两次垃圾回收事件的原因

$ jstat -gccause 28920

 S0     S1     E      O      P       YGC     YGCT    FGC    FGCT     GCT       LGCC                 GCC                 

 12.45   0.00  33.85   0.00   4.44      4    0.242     0    0.000    0.242   Allocation Failure   No GC
•
LGCC：最近垃圾回收的原因

•
GCC：当前垃圾回收的原因


-gcnew

统计新生代的行为

$ jstat -gcnew 28920

 S0C      S1C      S0U        S1U  TT  MTT  DSS      EC        EU         YGC     YGCT  

 419392.0 419392.0 52231.8    0.0  6   6    209696.0 3355520.0 1172246.0  4       0.242
•
TT：Tenuring threshold(提升阈值)

•
MTT：最大的tenuring threshold

•
DSS：survivor区域大小 (KB)


-gcnewcapacity

新生代与其相应的内存空间的统计

$ jstat -gcnewcapacity 28920
•
NGC:当前年轻代的容量 (KB)

•
S0CMX:最大的S0空间 (KB)

•
S0C:当前S0空间 (KB)

•
ECMX:最大eden空间 (KB)

•
EC:当前eden空间 (KB)


-gcold

统计旧生代的行为

 $ jstat -gcold 28920

-gcoldcapacity

统计旧生代的大小和空间

$ jstat -gcoldcapacity 28920

-gcpermcapacity

永生代行为统计

 $ jstat -gcpermcapacity 28920

-printcompilation

hotspot编译方法统计

 $ jstat -printcompilation 28920

    Compiled  Size  Type Method

    1291      78     1    java/util/ArrayList indexOf
•
Compiled：被执行的编译任务的数量

•
Size：方法字节码的字节数

•
Type：编译类型

•
Method：编译方法的类名和方法名。类名使用”/” 代替 “.” 作为空间分隔符. 方法名是给出类的方法名. 格式是一致于HotSpot – XX:+PrintComplation 选项



### jmap：Java内存映像工具
jmap（Memory Map for Java）命令用于生成堆转储快照（一般称为heapdump或dump文件）。

jmap的作用并不仅仅是为了获取dump文件，它还可以查询finalize执行队列、Java堆和永久代的详细信息，如空间使用率、当前用的是哪种收集器等。和jinfo命令一样，jmap有不少功能在Windows平台下都是受限的，除了生成dump文件的-dump选项和用于查看每个类的实例、空间占用统计的-histo选项在所有操作系统都提供之外，其余选项都只能在Linux/Solaris下使用。

命令格式

```
jmap [option] <pid>
```

option参数
- dump : 生成堆转储快照，格式为:-dump:[live, ]format=b,file=<filename>,其中live子参数说明是否只dump出存活的对象。
- finalizerinfo : 显示在F-Queue队列等待Finalizer线程执行finalizer方法的对象
- heap : 显示Java堆详细信息
- histo : 显示堆中对象的统计信息，GC使用的算法，heap的配置及wise
- heap的使用情况,可以用此来判断内存目前的使用情况以及垃圾回收情况
- permstat : to print permanent generation statistics
- F : 当-dump没有响应时，强制生成dump快照
- 

```
Attaching to process ID 28920, please wait...
  Debugger attached successfully.
  Server compiler detected.
  JVM version is 24.71-b01  
  using thread-local object allocation.
  Parallel GC with 4 thread(s)//GC 方式  
  Heap Configuration: //堆内存初始化配置
     MinHeapFreeRatio = 0 //对应jvm启动参数-XX:MinHeapFreeRatio设置JVM堆最小空闲比率(default 40)
     MaxHeapFreeRatio = 100 //对应jvm启动参数 -XX:MaxHeapFreeRatio设置JVM堆最大空闲比率(default 70)
     MaxHeapSize      = 2082471936 (1986.0MB) //对应jvm启动参数-XX:MaxHeapSize=设置JVM堆的最大大小
     NewSize          = 1310720 (1.25MB)//对应jvm启动参数-XX:NewSize=设置JVM堆的‘新生代’的默认大小
     MaxNewSize       = 17592186044415 MB//对应jvm启动参数-XX:MaxNewSize=设置JVM堆的‘新生代’的最大大小
     OldSize          = 5439488 (5.1875MB)//对应jvm启动参数-XX:OldSize=<value>:设置JVM堆的‘老生代’的大小
     NewRatio         = 2 //对应jvm启动参数-XX:NewRatio=:‘新生代’和‘老生代’的大小比率
     SurvivorRatio    = 8 //对应jvm启动参数-XX:SurvivorRatio=设置年轻代中Eden区与Survivor区的大小比值 
     PermSize         = 21757952 (20.75MB)  //对应jvm启动参数-XX:PermSize=<value>:设置JVM堆的‘永生代’的初始大小
     MaxPermSize      = 85983232 (82.0MB)//对应jvm启动参数-XX:MaxPermSize=<value>:设置JVM堆的‘永生代’的最大大小
     G1HeapRegionSize = 0 (0.0MB)  
  Heap Usage://堆内存使用情况
  PS Young Generation
  Eden Space://Eden区内存分布
     capacity = 33030144 (31.5MB)//Eden区总容量
     used     = 1524040 (1.4534378051757812MB)  //Eden区已使用
     free     = 31506104 (30.04656219482422MB)  //Eden区剩余容量
     4.614088270399305% used //Eden区使用比率
  From Space:  //其中一个Survivor区的内存分布
     capacity = 5242880 (5.0MB)
     used     = 0 (0.0MB)
     free     = 5242880 (5.0MB)
     0.0% used
  To Space:  //另一个Survivor区的内存分布
     capacity = 5242880 (5.0MB)
     used     = 0 (0.0MB)
     free     = 5242880 (5.0MB)
     0.0% used
  PS Old Generation //当前的Old区内存分布
     capacity = 86507520 (82.5MB)
     used     = 0 (0.0MB)
     free     = 86507520 (82.5MB)
     0.0% used
  PS Perm Generation//当前的 “永生代” 内存分布
     capacity = 22020096 (21.0MB)
     used     = 2496528 (2.3808746337890625MB)
     free     = 19523568 (18.619125366210938MB)
     11.337498256138392% used  
interned Strings occupying 43720 bytes.
```


### jhat：虚拟机堆转储快照分析工具
jhat（JVM Heap Analysis Tool）命令与jmap搭配使用，来分析jmap生成的堆转储快照。jhat内置了一个微型的HTTP/HTML服务器，生成dump文件的分析结果后，可以在浏览器中查看。

jhat的分析功能相对来说比较简陋，VisualVM，以及专业用于分析dump文件的Eclipse Memory Analyzer、IBM HeapAnalyzer等工具，都能实现比jhat更强大更专业的分析功能。

命令格式

```
 jhat [option] [dumpfile]
```

参数
- -stack false|true 关闭对象分配调用栈跟踪(tracking object allocation call stack)。 如果分配位置信息在堆转储中不可用. 则必须将此标志设置为 false. 默认值为 true.>
- -refs false|true 关闭对象引用跟踪(tracking of references to objects)。 默认值为 true. 默认情况下, 返回的指针是指向其他特定对象的对象,如反向链接或输入引用(referrers or incoming references), 会统计/计算堆中的所有对象。>
- -port port-number 设置 jhat HTTP server 的端口号. 默认值 7000.> 
- -exclude exclude-file 指定对象查询时需要排除的数据成员列表文件(a file that lists data members that should be excluded from the reachable objects query)。 例如, 如果文件列列出了 java.lang.String.value , 那么当从某个特定对象 Object o 计算可达的对象列表时, 引用路径涉及 java.lang.String.value 的都会被排除。>
- -baseline exclude-file 指定一个基准堆转储(baseline heap dump)。 在两个 heap dumps 中有相同 object ID 的对象会被标记为不是新的(marked as not being new). 其他对象被标记为新的(new). 在比较两个不同的堆转储时很有用.>
- -debug int 设置 debug 级别. 0 表示不输出调试信息。 值越大则表示输出更详细的 debug 信息.>
- -version 启动后只显示版本信息就退出>
- -J< flag > 因为 jhat 命令实际上会启动一个JVM来执行, 通过 -J 可以在启动JVM时传入一些启动参数. 例如, -J-Xmx512m 则指定运行 jhat 的Java虚拟机使用的最大堆内存为 512 MB. 如果需要使用多个JVM启动参数,则传入多个 -Jxxxxxx.


屏幕显示“Server is ready.”的提示后，用户在浏览器中键入http://localhost:7000/就可以看到分析结果.

分析结果默认是以包为单位进行分组显示，分析内存泄漏问题主要会使用到其中的“Heap Histogram”（与jmap -histo功能一样）与OQL页签的功能，前者可以找到内存中总容量最大的对象，后者是标准的对象查询语言，使用类似SQL的语法对内存中的对象进行查询统计.


### jstack：Java堆栈跟踪工具

jstack（Stack Trace for Java）命令用于生成虚拟机当前时刻的线程快照（一般称为threaddump或者javacore文件）。线程快照就是当前虚拟机内每一条线程正在执行的方法堆栈的集合，生成线程快照的主要目的是定位线程出现长时间停顿的原因，如线程间死锁、死循环、请求外部资源导致的长时间等待等都是导致线程长时间停顿的常见原因。线程出现停顿的时候通过jstack来查看各个线程的调用堆栈，就可以知道没有响应的线程到底在后台做些什么事情，或者等待着什么资源。

命令格式

 jstack [option] LVMID

option参数
- -F : 当正常输出请求不被响应时，强制输出线程堆栈
- -l : 除堆栈外，显示关于锁的附加信息
- -m : 如果调用到本地方法的话，可以显示C/C++的堆栈

