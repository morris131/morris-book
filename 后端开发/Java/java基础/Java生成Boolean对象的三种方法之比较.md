new操作符
Boolean boolean = new Boolean(false);

静态方法valueOf
Boolean boolean = Boolean.valueOf(false);

自动装箱
Boolean boolean = false;

区别
先看一段代码
Boolean[] boolean1 = new Boolean[100];
Boolean[] boolean2 = new Boolean[100];
Boolean[] boolean3 = new Boolean[100];
for (int i = 0; i < 100;i++){
    boolean1[i] = Boolean.valueOf(1==1);
}
for (int i = 0;i < 100;i++){
    boolean2[i] = new Boolean(1==1);
}
for (int i = 0; i < 100;i++){
    boolean3[i] = 1==1;
}
System.out.println("valueOf: " + String.valueOf(boolean1[1] == boolean1[2]));
System.out.println("new Boolean: " + String.valueOf(boolean2[1] == boolean2[2]));
System.out.println("auto wrap: " + String.valueOf(boolean3[1] == boolean3[2]));
输出结果是：
valueOf: true
new Boolean: false
auto wrap: true
为什么会这样呢？
原因就在于用new创建的Boolean对象是不断的新创建一个实例对象，而valueOf则是返回Boolean类里的静态成员变量，不会产生大量相同的实例变量。
查看自动装箱class源码，发现底层还是调用的Boolean.valueOf方法。
    0  iconst_0
     1  invokestatic java.lang.Boolean.valueOf(boolean) : java.lang.Boolean [16]
     4  astore_1 [i1]
     5  iconst_0
     6  invokestatic java.lang.Boolean.valueOf(boolean) : java.lang.Boolean [16]
     9  astore_2 [i2]
实际上jdk文档也是建议用valueOf代替new方式来创建Boolean类对象。