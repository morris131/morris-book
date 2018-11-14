package com.morris.jvm.load;

// 自定义类加载器必须继承ClassLoader
public class CustomerClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

}
