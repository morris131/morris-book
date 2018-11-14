package com.morris.jvm.load;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// 自定义类加载器必须继承ClassLoader
public class MyClassLoader extends ClassLoader {

    private static final Path DEFAULT_CLASS_DIR = Paths.get("D:","classloader");

    private final Path classDir;

    public MyClassLoader() {
        this.classDir = DEFAULT_CLASS_DIR;
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
        this.classDir = DEFAULT_CLASS_DIR;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = this.readByte(name);

        if(null == bytes || 0 == bytes.length) {
            throw new ClassNotFoundException("Can not load the class " + name);
        }

        return this.defineClass(name, bytes, 0, bytes.length);
    }

    // 将class文件读入内存
    private byte[] readByte(String name) throws ClassNotFoundException{
       String classPath = name.replace(".", "/");

        Path classFullPath = this.classDir.resolve(classPath + ".class");

        if(!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("The class " + name + " not found.");
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("load the class " + name + " error", e);
        }
    }

}
