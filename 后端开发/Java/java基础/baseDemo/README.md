## ArrayList的遍历方式与fail-fast

### 遍历方式
1. 普通for循环
2. Iterator迭代
3. 增强for循环
4. ListIterator迭代

```
package com.morris.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayListTest {
	
	public static void main(String[] args) {
		
		List<Integer> list  = new ArrayList<>();
		
		for(int i = 0; i < 1000000; i++) {
			list.add(i);
		}
		
		testFor(list); // 1ms
		testIterator(list); // 7ms
		testForEach(list); // 8ms
		testListIterator(list); // 7ms
	}
	
	public static void testFor(List<Integer> list) {
		long begin = System.currentTimeMillis();
		for(int i = 0; i < list.size(); i++) {
		}
		long end = System.currentTimeMillis();
		System.out.println("testFor cost " + (end - begin));
	}
	
	public static void testIterator(List<Integer> list) {
		long begin = System.currentTimeMillis();
		Iterator<Integer> iterator=list.iterator();
		while (iterator.hasNext()) {
			iterator.next();
		}
		long end = System.currentTimeMillis();
		System.out.println("testIterator cost " + (end - begin));
	}
	
	public static void testForEach(List<Integer> list) {
		long begin = System.currentTimeMillis();
		for (Integer integer : list) {
		}
		long end = System.currentTimeMillis();
		System.out.println("testForEach cost " + (end - begin));
	}
	
	public static void testListIterator(List<Integer> list) {
		long begin = System.currentTimeMillis();
		ListIterator<Integer> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			listIterator.next();
		}
		long end = System.currentTimeMillis();
		System.out.println("testListIterator cost " + (end - begin));
	}

}

```
由测试结果可以看出普通for循环效率最高。而其他三种遍历方式效率差不多，原因是其他三种遍历方式底层机制都是采用Iterator迭代，增强for循环底层采用的Iterator迭代，而ListItr(ListIterator接口底层具体实现类的名称)只是继承了Itr(Iterator接口底层具体实现类的名称)，并未重写父类的方法。

### ListIterator与Iterator的区别

迭代器 没有当前所在元素一说，它只有一个游标(cursor)的概念，这个游标总是在元素之间，比如这样:

![image](https://img-blog.csdn.net/20161007210652687)

初始时它在第 0 个元素之前，调用 next() 游标后移一位：

![image](https://img-blog.csdn.net/20161007210926863)

调用 previous() 游标前移一位。当向后遍历完元素，游标就会在元素 N 的后面：

![image](https://img-blog.csdn.net/20161007211112819)


首先看一下Iterator接口的方法：

```
boolean hasNext(); // 如果迭代器指向位置后面还有元素，则返回 true，否则返回false
E next(); // 返回集合中Iterator指向位置后面的元素
void remove(); // 删除集合中Iterator指向位置后面的元素
```
再看一下ListIterator迭代器的方法：

```
boolean hasNext(); // 向后遍历list时，如果游标后面还有元素，则返回 true，否则返回false
E next(); // 返回游标后面的元素,并将游标后移一位
boolean hasPrevious(); // 向前遍历list，如果游标前面还有元素，则返回 true，否则返回false
E previous(); // 返回游标前面的元素
int nextIndex(); // 返回游标后边元素的索引位置，初始为 0 ；遍历 N 个元素结束时为 N;
int previousIndex(); // 返回游标前面元素的位置，初始时为 -1，同时报 java.util.NoSuchElementException 错;
void remove(); // 删除迭代器最后一次操作的元素，注意事项和 set 一样。
void set(E e); // 更新迭代器最后一次操作的元素为 E，也就是更新最后一次调用 next() 或者 previous() 返回的元素。
注意，当没有迭代，也就是没有调用 next() 或者 previous() 直接调用 set 时会报 java.lang.IllegalStateException 错;
void add(E e); // 在游标 前面 插入一个元素 注意，是前面
```

两者的区别：

1. 使用范围不同，Iterator可以应用于所有的集合，Set、List和Map和这些集合的子类型。而ListIterator只能用于List及其子类型。
2. ListIterator有add方法，可以向List中添加对象，而Iterator不能。
3. ListIterator和Iterator都有hasNext()和next()方法，可以实现顺序向后遍历，但是ListIterator有hasPrevious()和previous()方法，可以实现逆向（顺序向前）遍历。Iterator不可以。
4. ListIterator可以定位当前索引的位置，nextIndex()和previousIndex()可以实现。Iterator没有此功能。
5. 都可实现删除操作，但是ListIterator可以实现对象的修改，set()方法可以实现。Iterator仅能遍历，不能修改。

ListIterator的使用

```
package com.morris.base;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorTest {
	
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		//backwardIterator(list);
		//forwardIterator(list);
		
		//addItem(list);
		setItem(list);
		//removeItem(list);
		
	}
	
	/**
	 * 向后遍历
	 * @param list
	 */
	public static void backwardIterator(List<Integer> list)  {
		ListIterator<Integer> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			System.out.println(listIterator.nextIndex() + ":" + listIterator.next());
		}
	}
	
	/**
	 * 向前遍历
	 * @param list
	 */
	public static void forwardIterator(List<Integer> list)  {
		ListIterator<Integer> listIterator = list.listIterator(list.size()); // 将游标移至最后才能往前遍历
		while (listIterator.hasPrevious()) {
			System.out.println(listIterator.previousIndex() + ":" + listIterator.previous());
		}
	}
	
	/**
	 * 删除元素
	 * @param list
	 */
	public static void removeItem(List<Integer> list) {
		ListIterator<Integer> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			System.out.println(listIterator.next());
			listIterator.remove(); // 删除迭代器最后一次next()的元素
			System.out.println(list);
		}
		System.out.println(list.size());
	}
	
	/**
	 * 添加元素
	 * @param list
	 */
	public static void addItem(List<Integer> list) {
		ListIterator<Integer> listIterator = list.listIterator();
		listIterator.add(100); // 在游标前面插入一个元素 
		System.out.println(list);
	}
	
	/**
	 * 设置元素
	 * @param list
	 */
	public static void setItem(List<Integer> list) {
		ListIterator<Integer> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			System.out.println(listIterator.next());
			listIterator.set(100); // 设置迭代器最后一次next()的元素
			System.out.println(list);
		}
		System.out.println(list.size());
	}

}

```

### fail-fast重现

```
package com.morris.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FailFastTest {
	
	public static void main(String[] args) {
		List<Integer> list  = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		System.out.println("以下三种遍历会抛出异常java.util.ConcurrentModificationException");
		
		for (Integer integer : list) {
			System.out.println(integer);
			list.remove(1);
		}
		
		Iterator<Integer> iterator=list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			list.remove(2);
		}
		
		ListIterator<Integer> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			System.out.println(listIterator.next());
			list.remove(1);
		}
		
	}

}


```
从上文可知这三种遍历方法底层都是采用Iterator实现的，所以fail-fast发生在通过iterator去遍历某集合的过程该集合的内容被修改了，就会抛出ConcurrentModificationException异常，产生fail-fast事件。

### fail-fast出现的原因
产生fail-fast事件，是通过抛出ConcurrentModificationException异常来触发的。那么，ArrayList是如何抛出ConcurrentModificationException异常的呢?

```

// 此值从ArrayList父类AbstractList继承而来
protected transient int modCount = 0; // 用来记录List修改的次数：每修改一次(添加/删除等操作)，将modCount+1

// Itr为ArrayList的内部类
private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount; 
        

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

```

从中，我们可以发现在调用 next() 和 remove()时，都会执行 checkForComodification()。若 “modCount 不等于 expectedModCount”，则抛出ConcurrentModificationException异常，产生fail-fast事件。

要搞明白 fail-fast机制，我们就要需要理解什么时候“modCount 不等于 expectedModCount”！
从Itr类中，我们知道 expectedModCount 在创建Itr对象时，被赋值为 modCount。通过Itr，我们知道：expectedModCount不可能被修改为不等于 modCount。所以，需要考证的就是modCount何时会被修改。

```
/**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        System.arraycopy(elementData, index, elementData, index + 1,
                         size - index);
        elementData[index] = element;
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }

    public void clear() {
        modCount++;

        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew,
                             numMoved);

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }
```

从中，我们发现：无论是add()、remove()，还是clear()，只要涉及到修改集合中的元素个数时，都会改变modCount的值。

至此，我们就完全了解了fail-fast是如何产生的！
 即，当一个集合进行Iterator遍历的的时候，该集合的内容被所改变(即调用add、remove、clear等方法，改变了modCount的值)；这时，就会抛出ConcurrentModificationException异常，产生fail-fast事件。
 
 ### fail-fast解决办法一
 在单线程的遍历过程中，如果要进行remove操作，可以调用迭代器的remove方法而不是集合类的remove方法。看看ArrayList中迭代器的remove方法的源码：

 ```
 public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
 ```
 可以看到，该remove方法并不会修改modCount的值，并且不会对后面的遍历造成影响，因为该方法remove不能指定元素，只能remove当前遍历过的那个元素，所以调用该方法并不会发生fail-fast现象。该方法有局限性。
 
```
package com.morris.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NoFailTest {
	
	public static void main(String[] args) {
		
		List<Integer> list  = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()) {
			if(3 == iterator.next()) {
				iterator.remove();
			}
		}
		
		System.out.println(list);
	}

}
```
 
 ### fail-fast解决办法二
 
 只有用Iterator遍历的时候才会去检查modCount与expectedModCount是否相等，用普通for循环遍历不会导致fail-fast.
 
 
 ```
 package com.morris.base;

import java.util.ArrayList;
import java.util.List;

public class NoFailTest2 {
	
	public static void main(String[] args) {
		
		List<Integer> list  = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		for(int i = 0; i < list.size(); i++) {
			if(3 == list.get(i)) {
				list.remove(i);
			}
		}
		
		System.out.println(list);
	}

}
 
 ```
 
### fail-fast解决办法三
 前面两种方法都只能在单线程下解决fail-fast,在多线程下使用java.util.concurrent.CopyOnWriteArrayList替换ArrayList。
 
Copy-On-Write简称COW，是一种用于程序设计中的优化策略。其基本思路是，从一开始大家都在共享同一个内容，当某个人想要修改这个内容的时候，才会真正把内容Copy出去形成一个新的内容然后再改，这是一种延时懒惰策略。从JDK1.5开始Java并发包里提供了两个使用CopyOnWrite机制实现的并发容器,它们是CopyOnWriteArrayList和CopyOnWriteArraySet。CopyOnWrite容器非常有用，可以在非常多的并发场景中使用到。
 
 CopyOnWrite容器即写时复制的容器。通俗的理解是当我们往一个容器添加元素的时候，不直接往当前容器添加，而是先将当前容器进行Copy，复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器。这样做的好处是我们可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
 
 看看CopyOnWriteArrayList的源码:

```
    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }
    
        @SuppressWarnings("unchecked")
    private E get(Object[] a, int index) {
        return (E) a[index];
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        return get(getArray(), index);
    }

```
 读的时候不需要加锁，如果读的时候有多个线程正在向ArrayList添加数据，读还是会读到旧的数据，因为写的时候不会锁住旧的ArrayList。
 
 
 
 

### 参考文档
1. https://blog.csdn.net/zymx14/article/details/78394464
2. http://www.cnblogs.com/skywang12345/p/3308762.html
3. https://blog.csdn.net/dongbaoming/article/details/53034206


 
 