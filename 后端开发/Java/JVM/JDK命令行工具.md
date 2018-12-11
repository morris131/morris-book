title: JDK命令行工具
date: 2018-10-20
categories: JVM
tags: [JDK命令行工具]
---

# JDK命令行工具

## jps：虚拟机进程状况工具
jps（JVM Process Status Tool）：列出正在运行的虚拟机进程，并显示虚拟机执行主类（Main Class,main()函数所在的类）名称以及这些进程的本地虚拟机唯一ID（Local Virtual Machine Identifier,LVMID）。

虽然功能比较单一，但它是使用频率最高的JDK命令行工具，因为其他的JDK工具大多需要输入它查询到的LVMID来确定要监控的是哪一个虚拟机进程。

对于本地虚拟机进程来说，LVMID与操作系统的进程ID（Process Identifier,PID）是一致的，使用Windows的任务管理器或者UNIX的ps命令也可以查询到虚拟机进程的LVMID，但如果同时启动了多个虚拟机进程，无法根据进程名称定位时，那就只能依赖jps命令显示主类的功能才能区分了。

命令格式：
```
usage: jps [-help]
       jps [-q] [-mlvV] [<hostid>]

Definitions:
    <hostid>:      <hostname>[:<port>]
```
option参数：
- -q：只输出LVMID
- -m：输出JVM启动时传递给main()的参数
- -l：输出主类全名或jar路径
- -v：输出JVM启动时显示指定的JVM参数
- -V：输出通过flag文件传递到JVM中的参数（.hotspotrc文件或-XX:Flags=所指定的文件） 

示例：
```
$ jps -l
13560 sun.tools.jps.Jps
8940 org.jetbrains.jps.cmdline.Launcher

```

## jinfo 虚拟机配置信息工具
jinfo（JVM Configuration Info）：实时查看和调整虚拟机运行参数。

命令格式：
```
Usage:
    jinfo [option] <pid>
        (to connect to running process)
    jinfo [option] <executable <core>
        (to connect to a core file)
    jinfo [option] [server_id@]<remote server IP or hostname>
        (to connect to remote debug server)

where <option> is one of:
    -flag <name>         to print the value of the named VM flag
    -flag [+|-]<name>    to enable or disable the named VM flag
    -flag <name>=<value> to set the named VM flag to the given value
    -flags               to print VM flags
    -sysprops            to print Java system properties
    <no option>          to print both of the above
    -h | -help           to print this help message
```
option参数：
- -flag name：输出对应名称的参数
- -flag [+|-]name：开启或者关闭对应名称的参数
- -flag name=value：设定对应名称的参数
- -flags：输出全部的参数
- -sysprops：输出系统属性
- no option：输出全部的参数和系统属性

示例：
```
$ jinfo -flags 8940
Attaching to process ID 8940, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.172-b11
Non-default VM flags: -XX:CICompilerCount=3 -XX:InitialHeapSize=132120576 -XX:MaxHeapSize=734003200 -XX:MaxNewSize=244318208 -XX:MinHeapDeltaBytes=524288 -XX:NewSize=44040192 -XX:OldSize=88080384 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:-UseLargePagesIndi
vidualAllocation -XX:+UseParallelGC
Command line:  -Xmx700m -Djava.awt.headless=true -Djava.endorsed.dirs="" -Djdt.compiler.useSingleThread=true -Dpreload.project.path=D:/gitPrj/morris-book/后端开发/Java/JVM -Dpreload.config.path=C:/Users/wj65651/.IntelliJIdea2018.1/config/options -Dexternal.project.config=C:\Users\wj65651\.IntelliJIdea201
8.1\system\external_build_system\jvm.664992bf -Dcompile.parallel=false -Drebuild.on.dependency.change=true -Djava.net.preferIPv4Stack=true -Dio.netty.initialSeedUniquifier=-6591130954637100000 -Dfile.encoding=GBK -Duser.language=zh -Duser.country=CN -Didea.paths.selector=IntelliJIdea2018.1 -Didea.home.pa
th=D:\Program Files\JetBrains\IntelliJ IDEA 2018.1.4 -Didea.config.path=C:\Users\wj65651\.IntelliJIdea2018.1\config -Didea.plugins.path=C:\Users\wj65651\.IntelliJIdea2018.1\config\plugins -Djps.log.dir=C:/Users/wj65651/.IntelliJIdea2018.1/system/log/build-log -Djps.fallback.jdk.home=D:/Program Files/JetB
rains/IntelliJ IDEA 2018.1.4/jre64 -Djps.fallback.jdk.version=1.8.0_152-release -Dio.netty.noUnsafe=true -Djava.io.tmpdir=C:/Users/wj65651/.IntelliJIdea2018.1/system/compile-server/jvm_30d198c8/_temp_ -Djps.backward.ref.index.builder=true
```

## jstat 虚拟机统计信息监视工具
jstat（JVM Statistics Monitoring）：用于监视虚拟机运行时状态信息的命令，它可以显示出虚拟机进程中的类装载、内存、垃圾收集、JIT编译等运行数据。

命令格式：
```
Usage: jstat -help|-options
       jstat -<option> [-t] [-h<lines>] <vmid> [<interval> [<count>]]

Definitions:
  <option>      An option reported by the -options option
  <vmid>        Virtual Machine Identifier. A vmid takes the following form:
                     <lvmid>[@<hostname>[:<port>]]
                Where <lvmid> is the local vm identifier for the target
                Java virtual machine, typically a process id; <hostname> is
                the name of the host running the target Java virtual machine;
                and <port> is the port number for the rmiregistry on the
                target host. See the jvmstat documentation for a more complete
                description of the Virtual Machine Identifier.
  <lines>       Number of samples between header lines.
  <interval>    Sampling interval. The following forms are allowed:
                    <n>["ms"|"s"]
                Where <n> is an integer and the suffix specifies the units as
                milliseconds("ms") or seconds("s"). The default units are "ms".
  <count>       Number of samples to take before terminating.
  -J<flag>      Pass <flag> directly to the runtime system.
```

参数：
- option： 参数选项
- -t： 可以在打印的列加上Timestamp列，用于显示系统运行的时间
- vmid： Virtual Machine ID（ 进程的 pid）
- -h： 可以在周期性数据数据的时候，可以在指定输出多少行以后输出一次表头
- interval： 执行每次的间隔时间，单位为毫秒
- count： 用于指定输出多少次记录，缺省则会一直打印

options参数：
```
$ jstat -options
-class
-compiler
-gc
-gccapacity
-gccause
-gcmetacapacity
-gcnew
-gcnewcapacity
-gcold
-gcoldcapacity
-gcutil
-printcompilation
```
选项option代表着用户希望查询的虚拟机信息，主要分为3类：类装载、垃圾收集、运行期编译状况，具体选项及作用如下：
- -class：类加载的行为统计
- -compiler：JIT编译器行为统计
- -gc：垃圾回收堆的行为统计
- -gccapacity：各个垃圾回收代容量和他们相应的空间统计
- -gccause：垃圾收集统计概述（同-gcutil），附加最近两次垃圾回收事件的原因。
- -gcmetacapacity：元空间行为统计
- -gcnew：新生代行为统计
- -gcnewcapacity：新生代与其相应的内存空间的统计
- -gcold：老年代行为统计
- -gcoldcapacity：老生代与其相应的内存空间的统计
- -gcutil：垃圾收集统计概述（百分比）
- -printcompilation：输出JIT编译的方法信息

### option 参数详解
#### -class
监视类装载、卸载数量、总空间以及耗费的时间。

示例：
```
$ jstat -class 8940
Loaded  Bytes  Unloaded  Bytes     Time
  3152  5933.9        0     0.0       0.87
```
- Loaded：加载class的数量
- Bytes：class字节大小
- Unloaded：未加载class的数量
- Bytes：未加载class的字节大小
- Time：加载时间

#### -compiler
输出JIT编译过的方法数量耗时等。

示例：
```
$ jstat -compiler 8940
Compiled Failed Invalid   Time   FailedType FailedMethod
    1075      0       0     0.89          0
```
- Compiled : 编译数量
- Failed : 编译失败数量
- Invalid : 无效数量
- Time : 编译耗时
- FailedType : 失败类型
- FailedMethod : 失败方法的全限定名

#### -gc
垃圾回收堆的行为统计。

示例：
```
$ jstat -gc 8940 250 3
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
5120.0 5120.0  0.0   4439.7 32768.0  31665.7   86016.0    16464.0   13696.0 13279.9 1664.0 1584.6      1    0.004   0      0.000    0.004
5120.0 5120.0  0.0   4439.7 32768.0  31665.7   86016.0    16464.0   13696.0 13279.9 1664.0 1584.6      1    0.004   0      0.000    0.004
5120.0 5120.0  0.0   4439.7 32768.0  31665.7   86016.0    16464.0   13696.0 13279.9 1664.0 1584.6      1    0.004   0      0.000    0.004
```
C即Capacity 总容量，U即Used 已使用的容量，单位为KB
- S0C：survivor0区的总容量
- S1C：survivor1区的总容量
- S0U：survivor0区已使用的容量
- S1C：survivor1区已使用的容量
- EC：Eden区的总容量
- EU：Eden区已使用的容量
- OC：Old区的总容量
- OU：Old区已使用的容量
- MC：方法区的总容量
- MU：方法区已使用的容量
- CCSC：压缩类空间大小
- CCSU：压缩类空间使用大小
- YGC：新生代垃圾回收次数
- YGCT：新生代垃圾回收消耗时间
- FGC：老年代垃圾回收次数
- FGCT：老年代垃圾回收消耗时间
- GCT：垃圾回收消耗总时间

#### -gccapacity
堆内存统计。

示例：
```
$ jstat -gccapacity 8940
 NGCMN    NGCMX     NGC     S0C   S1C       EC      OGCMN      OGCMX       OGC         OC       MCMN     MCMX      MC     CCSMN    CCSMX     CCSC    YGC    FGC
 43008.0 238592.0  43008.0 5120.0 5120.0  32768.0    86016.0   478208.0    86016.0    86016.0      0.0 1060864.0  13696.0      0.0 1048576.0   1664.0      1     0
```
- NGCMN：新生代使用到的最小容量
- NGCMX：新生代使用到的最大容量
- NGC：当前新生代容量
- S0C：survivor0区的总容量
- S1C：survivor1区的总容量
- EC：Eden区的总容量
- OGCMN：老年代使用到的最小容量
- OGCMX：老年代使用到的最大容量
- OGC：当前老年代大小
- OC：当前老年代大小
- MCMN：元数据使用到的最小容量
- MCMX：元数据使用到的最大容量
- MC：当前元数据空间大小
- CCSMN：最小压缩类空间大小
- CCSMX：最大压缩类空间大小
- CCSC：当前压缩类空间大小
- YGC：年轻代GC次数
- FGC：老年代GC次数

#### -gcnew
新生代垃圾回收统计。

示例：
```
$ jstat -gcnew 8940
 S0C    S1C    S0U    S1U   TT MTT  DSS      EC       EU     YGC     YGCT
5120.0 5120.0    0.0 4439.7  7  15 5120.0  32768.0  31665.7      1    0.004
```
- S0C：survivor0区的总容量
- S1C：survivor1区的总容量
- S0U：survivor0区已使用的容量
- S1C：survivor1区已使用的容量
- TT：对象在新生代存活的次数
- MTT：对象在新生代存活的最大次数
- DSS：期望的survivor大小
- EC：Eden区的总容量
- EU：Eden区已使用的容量
- YGC：新生代垃圾回收次数
- YGCT：新生代垃圾回收消耗时间

#### -gcnewcapacity
新生代内存统计。

示例：
```
$ jstat -gcnewcapacity 8940
  NGCMN      NGCMX       NGC      S0CMX     S0C     S1CMX     S1C       ECMX        EC      YGC   FGC
   43008.0   238592.0    43008.0  79360.0   5120.0  79360.0   5120.0   237568.0    32768.0     1     0
```
- NGCMN：新生代使用到的最小容量
- NGCMX：新生代使用到的最大容量
- NGC：当前新生代容量
- S0CMX：survivor0区使用到的最大容量
- S0C：当前survivor0区大小
- S1CMX：survivor1区使用到的最大容量
- S1C：当前survivor1区大小
- ECMX：最大Eden区大小
- EC：Eden区的总容量
- YGC：新生代垃圾回收次数
- FGC：老年代回收次数

#### -gcold
老年代垃圾回收统计。

示例
```
$ jstat -gcold 8940
   MC       MU      CCSC     CCSU       OC          OU       YGC    FGC    FGCT     GCT
 13696.0  13279.9   1664.0   1584.6     86016.0     16464.0      1     0    0.000    0.004
```
- MC：方法区大小
- MU：方法区使用大小
- CCSC：压缩类空间大小
- CCSU：压缩类空间使用大小
- OC：老年代大小
- OU：老年代使用大小
- YGC：年轻代垃圾回收次数
- FGC：老年代垃圾回收次数
- FGCT：老年代垃圾回收消耗时间
- GCT：垃圾回收消耗总时间

#### -gcoldcapacity
老年代内存统计。

示例：
```
$ jstat -gcoldcapacity 8940
   OGCMN       OGCMX        OGC         OC       YGC   FGC    FGCT     GCT
    86016.0    478208.0     86016.0     86016.0     1     0    0.000    0.004
```
- OGCMN：老年代最小容量
- OGCMX：老年代最大容量
- OGC：当前老年代大小
- OC：老年代大小
- YGC：年轻代垃圾回收次数
- FGC：老年代垃圾回收次数
- FGCT：老年代垃圾回收消耗时间
- GCT：垃圾回收消耗总时间

#### -gcmetacapacity
元数据空间统计

示例：
```
$ jstat -gcmetacapacity 8940
   MCMN       MCMX        MC       CCSMN      CCSMX       CCSC     YGC   FGC    FGCT     GCT
       0.0  1060864.0    13696.0        0.0  1048576.0     1664.0     1     0    0.000    0.004
```
- MCMN:最小元数据容量
- MCMX：最大元数据容量
- MC：当前元数据空间大小
- CCSMN：最小压缩类空间大小
- CCSMX：最大压缩类空间大小
- CCSC：当前压缩类空间大小
- YGC：年轻代垃圾回收次数
- FGC：老年代垃圾回收次数
- FGCT：老年代垃圾回收消耗时间
- GCT：垃圾回收消耗总时间

#### -gcutil
总结垃圾回收统计

示例：
```
$ jstat -gcutil 8940
  S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT
  0.00  86.71  96.64  19.14  96.96  95.23      1    0.004     0    0.000    0.004
```
- S0：幸存1区当前使用比例
- S1：幸存2区当前使用比例
- E：伊甸园区使用比例
- O：老年代使用比例
- M：元数据区使用比例
- CCS：压缩使用比例
- YGC：年轻代垃圾回收次数
- FGC：老年代垃圾回收次数
- FGCT：老年代垃圾回收消耗时间
- GCT：垃圾回收消耗总时间

### jmap：Java内存映像工具
jmap（Memory Map for Java）：用于生成堆转储快照（一般称为heapdump或dump文件）。

jmap的作用并不仅仅是为了获取dump文件，它还可以查询finalize执行队列、Java堆和永久代的详细信息，如空间使用率、当前用的是哪种收集器等。和jinfo命令一样，jmap有不少功能在Windows平台下都是受限的，除了生成dump文件的-dump选项和用于查看每个类的实例、空间占用统计的-histo选项在所有操作系统都提供之外，其余选项都只能在Linux/Solaris下使用。

命令格式：
```
Usage:
    jmap [option] <pid>
        (to connect to running process)
    jmap [option] <executable <core>
        (to connect to a core file)
    jmap [option] [server_id@]<remote server IP or hostname>
        (to connect to remote debug server)

where <option> is one of:
    <none>               to print same info as Solaris pmap
    -heap                to print java heap summary
    -histo[:live]        to print histogram of java object heap; if the "live"
                         suboption is specified, only count live objects
    -clstats             to print class loader statistics
    -finalizerinfo       to print information on objects awaiting finalization
    -dump:<dump-options> to dump java heap in hprof binary format
                         dump-options:
                           live         dump only live objects; if not specified,
                                        all objects in the heap are dumped.
                           format=b     binary format
                           file=<file>  dump heap to <file>
                         Example: jmap -dump:live,format=b,file=heap.bin <pid>
    -F                   force. Use with -dump:<dump-options> <pid> or -histo
                         to force a heap dump or histogram when <pid> does not
                         respond. The "live" suboption is not supported
                         in this mode.
    -h | -help           to print this help message
    -J<flag>             to pass <flag> directly to the runtime system

```
option参数：
- dump : 生成堆转储快照，格式为:-dump:[live, ]format=b,file=<filename>,其中live子参数说明是否只dump出存活的对象。
- finalizerinfo : 显示在F-Queue队列等待Finalizer线程执行finalizer方法的对象
- heap : 显示Java堆详细信息
- histo : 显示堆中对象的统计信息，GC使用的算法，heap的配置及wise
- heap的使用情况,可以用此来判断内存目前的使用情况以及垃圾回收情况
- F : 当-dump没有响应时，强制生成dump快照

示例：
```
$ jmap -heap 8940
Attaching to process ID 8940, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.172-b11

using thread-local object allocation.
Parallel GC with 4 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 0
   MaxHeapFreeRatio         = 100
   MaxHeapSize              = 734003200 (700.0MB)
   NewSize                  = 44040192 (42.0MB)
   MaxNewSize               = 244318208 (233.0MB)
   OldSize                  = 88080384 (84.0MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 0 (0.0MB)

Heap Usage:
PS Young Generation
Eden Space:
   capacity = 33554432 (32.0MB)
   used     = 32425712 (30.923568725585938MB)
   free     = 1128720 (1.0764312744140625MB)
   96.63615226745605% used
From Space:
   capacity = 5242880 (5.0MB)
   used     = 4546224 (4.3356170654296875MB)
   free     = 696656 (0.6643829345703125MB)
   86.71234130859375% used
To Space:
   capacity = 5242880 (5.0MB)
   used     = 0 (0.0MB)
   free     = 5242880 (5.0MB)
   0.0% used
PS Old Generation
   capacity = 88080384 (84.0MB)
   used     = 16859168 (16.078155517578125MB)
   free     = 71221216 (67.92184448242188MB)
   19.14066133045015% used

5329 interned Strings occupying 483136 bytes.
```

## jhat：虚拟机堆转储快照分析工具
jhat（JVM Heap Analysis Tool）命令与jmap搭配使用，来分析jmap生成的堆转储快照。jhat内置了一个微型的HTTP/HTML服务器，生成dump文件的分析结果后，可以在浏览器中查看。

jhat的分析功能相对来说比较简陋，VisualVM，以及专业用于分析dump文件的Eclipse Memory Analyzer、IBM HeapAnalyzer等工具，都能实现比jhat更强大更专业的分析功能。

命令格式

```
$ jhat -help
Usage:  jhat [-stack <bool>] [-refs <bool>] [-port <port>] [-baseline <file>] [-debug <int>] [-version] [-h|-help] <file>

        -J<flag>          Pass <flag> directly to the runtime system. For
                          example, -J-mx512m to use a maximum heap size of 512MB
        -stack false:     Turn off tracking object allocation call stack.
        -refs false:      Turn off tracking of references to objects
        -port <port>:     Set the port for the HTTP server.  Defaults to 7000
        -exclude <file>:  Specify a file that lists data members that should
                          be excluded from the reachableFrom query.
        -baseline <file>: Specify a baseline object dump.  Objects in
                          both heap dumps with the same ID and same class will
                          be marked as not being "new".
        -debug <int>:     Set debug level.
                            0:  No debug output
                            1:  Debug hprof file parsing
                            2:  Debug hprof file parsing, no server
        -version          Report version number
        -h|-help          Print this help and exit
        <file>            The file to read

For a dump file that contains multiple heap dumps,
you may specify which dump in the file
by appending "#<number>" to the file name, i.e. "foo.hprof#3".

All boolean options default to "true"

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


## jstack：Java堆栈跟踪工具

jstack（Stack Trace for Java）：用于生成虚拟机当前时刻的线程快照（一般称为threaddump或者javacore文件）。线程快照就是当前虚拟机内每一条线程正在执行的方法堆栈的集合，生成线程快照的主要目的是定位线程出现长时间停顿的原因，如线程间死锁、死循环、请求外部资源导致的长时间等待等都是导致线程长时间停顿的常见原因。线程出现停顿的时候通过jstack来查看各个线程的调用堆栈，就可以知道没有响应的线程到底在后台做些什么事情，或者等待着什么资源。

命令格式
```
$ jstack -help
Usage:
    jstack [-l] <pid>
        (to connect to running process)
    jstack -F [-m] [-l] <pid>
        (to connect to a hung process)
    jstack [-m] [-l] <executable> <core>
        (to connect to a core file)
    jstack [-m] [-l] [server_id@]<remote server IP or hostname>
        (to connect to a remote debug server)

Options:
    -F  to force a thread dump. Use when jstack <pid> does not respond (process is hung)
    -m  to print both java and native frames (mixed mode)
    -l  long listing. Prints additional information about locks
    -h or -help to print this help message

```
option参数：
- -F : 当正常输出请求不被响应时，强制输出线程堆栈
- -l : 除堆栈外，显示关于锁的附加信息
- -m : 如果调用到本地方法的话，可以显示C/C++的堆栈

示例：
```
$ jstack -l 8940
2018-12-04 15:50:27
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.172-b11 mixed mode):

"NettythreadDeathWatcher-2-1" #14 daemon prio=1 os_prio=-2 tid=0x00000000591e6000 nid=0x1e30 waiting on condition [0x000000005b16f000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at io.netty.util.ThreadDeathWatcher$Watcher.run(ThreadDeathWatcher.java:152)
        at io.netty.util.concurrent.DefaultThreadFactory$DefaultRunnableDecorator.run(DefaultThreadFactory.java:138)
        at java.lang.Thread.run(Thread.java:748)

   Locked ownable synchronizers:
        - None
        
        ... ...

"VM Thread" os_prio=2 tid=0x0000000054e84000 nid=0x37ac runnable

"GC task thread#0 (ParallelGC)" os_prio=0 tid=0x0000000002a03800 nid=0x4200 runnable

"GC task thread#1 (ParallelGC)" os_prio=0 tid=0x0000000002a05000 nid=0x3b48 runnable

"GC task thread#2 (ParallelGC)" os_prio=0 tid=0x0000000002a07000 nid=0x3a90 runnable

"GC task thread#3 (ParallelGC)" os_prio=0 tid=0x0000000002a08800 nid=0x2654 runnable

"VM Periodic Task Thread" os_prio=2 tid=0x00000000563c2800 nid=0x4208 waiting on condition

JNI global references: 255
```


