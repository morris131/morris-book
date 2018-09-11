## Java查看动态代理生成的代码

### 自定义Agent
```
package com.morris.pattern.proxy;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class CustomAgent implements ClassFileTransformer {

	public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new CustomAgent());
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!className.startsWith("java") && !className.startsWith("sun")) {
            // 既不是java也不是sun开头的
            // 导出代码
            int lastIndexOf = className.lastIndexOf("/") + 1;
            String fileName = className.substring(lastIndexOf) + ".class";
            exportClazzToFile("d:/bytecode/xx/ss", fileName, classfileBuffer);
            System.out.println(className + " --> EXPORTED Succeess!");
        }    
        return classfileBuffer;
    }

    /**
     * 
     * @param dirPath
     *目录以/结尾，且必须存在
     * @param fileName
     * @param data
     */
    private void exportClazzToFile(String dirPath, String fileName, byte[] data) {
        try {
        	
        	File dir = new File(dirPath);
        	if(!dir.exists()) {
        		dir.mkdirs();
        	}
        	
            File file = new File(dirPath + fileName);
            if (!file.exists()) {
                file.createNewFile();    
            }    
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();    
        } catch (Exception e) {
            System.out.println("exception occured while doing some file operation");
            e.printStackTrace();
        }
    }
}
```

### 打包
将项目打包成jagent.jar，然后向其中添加META-INF/MANIFEST.MF文件，内容如下：

```
Manifest-Version: 1.0
Built-By: morris
Build-Jdk: 1.7.0_79
Created-By: Apache Maven 3.3.9
Archiver-Version: Plexus Archiver
Premain-Class: com.morris.pattern.proxy.CustomAgent


```

### 运行
把jagent.jar放到工程下，运行的时候加上jvm参数
```
-javaagent:jagent.jar
```


