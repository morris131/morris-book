## HashMap和HashTable的区别
[TOC]

### 代码版本
#### 时间不同
HashTable产生于JDK 1.1，而HashMap产生于JDK 1.2。从时间的维度上来看，HashMap要比HashTable出现得晚一些。

#### 作者不同
```java
以下是HashTable的作者：
以下代码及注释来自java.util.HashTable
* @author  Arthur van Hoff
* @author  Josh Bloch
* @author  Neal Gafter

以下是HashMap的作者：
以下代码及注释来自java.util.HashMap
 * @author  Doug Lea
 * @author  Josh Bloch
 * @author  Arthur van Hoff
 * @author  Neal Gafter
 ```
可以看到HashMap的作者多了大神Doug Lea。

#### 继承关系不同
```java

public class Hashtable<K,V>
    extends Dictionary<K,V>
    implements Map<K,V>, Cloneable, java.io.Serializable {

public class HashMap<K,V>
    extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable
````

#### Null Key & Null Value

HashMap是支持null键和null值的，而HashTable在遇到null时，会抛出NullPointerException异常。这并不是因为HashTable有什么特殊的实现层面的原因导致不能支持null键和null值，这仅仅是因为HashMap在实现时对null做了特殊处理，将null的hashCode值定为了0，从而将其存放在哈希表的第0个bucket中。

#### 算法不同
```java
以下代码及注释来自java.util.HashTable

// 哈希表默认初始大小为11
public Hashtable() {
    this(11, 0.75f);
}

protected void rehash() {
    int oldCapacity = table.length;
    Entry<K,V>[] oldMap = table;

    // 每次扩容为原来的2n+1
    int newCapacity = (oldCapacity << 1) + 1;
    // ...
}

以下代码及注释来自java.util.HashMap

// 哈希表默认初始大小为2^4=16
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

void addEntry(int hash, K key, V value, int bucketIndex) {
    // 每次扩充为原来的2n 
    if ((size >= threshold) && (null != table[bucketIndex])) {
       resize(2 * table.length);
}
```

#### 线程安全
HashTable是同步的，HashMap不是，也就是说HashTable在多线程使用的情况下，不需要做额外的同步，而HashMap则不行。

#### HashTable被淘汰
如果你不需要线程安全，那么使用HashMap，如果需要线程安全，那么使用ConcurrentHashMap。

