package com.morris.jvm.classloader;

public class BreakDelegateClassLoader extends MyClassLoader {

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // 根据类的全限定名进行加锁，确保一个类在多线程下只被加载一次
        synchronized (getClassLoadingLock(name)) {
            // 到已加载类的缓存中查看是否已经加载过，如果已加载则直接返回
            Class<?> c = findLoadedClass(name);
            if (null == c) {
                // 尝试使用自定义类加载器加载
                c = this.findClass(name);
            }

            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
}
