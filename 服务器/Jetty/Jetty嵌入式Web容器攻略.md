Jetty��һ���� Java ʵ�֡���Դ�����ڱ�׼�ģ����Ҿ��зḻ���ܵ� Http �������� Web ������Jetty��Ӧ����㷺��һ��ܾ��ǿ�����ΪǶ��ʽWeb������
 
�ڿ����׶Σ�����ʹ��Jetty��Eclipse��ֱ������Ӧ�ã���������Tomcat�����������ȰѼ�ʮ��Ӧ�ô����Ȼ���ٸ��Ƶ�ĳ��Ŀ¼����������
�ڲ��Խ׶Σ�����ֱ���ڲ�������������Jetty���������Ƚ�Ӧ�ô������������
�����н׶Σ����Խ�war�����ó�ֱ���ܹ����е�Ӧ��
���Ľ����ؽ����������ʹ��Jetty��Ƕ��ʽWeb�������ܣ�����Jetty�Ļ������ú͹�����ο� 
 
һ�������׶�
1��ʹ��maven����Jetty
�����޸���Դ���ʱ��eclipse���Զ����룬Jetty Maven Plugin������ֱ����ļ��б仯����Զ����µ�jetty�����У��ǳ��������ǽ��п�����
 
���ȶ���Jetty�İ汾����
 
    <properties>
        <jetty.version>8.1.9.v20130131</jetty.version>
    </properties>
Ȼ������Jetty����
 
 
    <!-- jetty -->
    <dependency>
        <groupId>org.eclipse.jetty.aggregate</groupId>
        <artifactId>jetty-webapp</artifactId>
        <version>${jetty.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.eclipse.jetty</groupId>
        <artifactId>jetty-jsp</artifactId>
        <version>${jetty.version}</version>
        <scope>test</scope>
    </dependency>
 
����Jetty Maven Plugin�����ʾ������
 
 
    <plugin>
        <groupId>org.mortbay.jetty</groupId>
        <artifactId>jetty-maven-plugin</artifactId>
        <version>${jetty.version}</version>
        <configuration>
            <systemProperties>
                <systemProperty>
                    <name>spring.profiles.active</name>
                    <value>development</value>
                </systemProperty>
            </systemProperties>
            <useTestClasspath>true</useTestClasspath>
    
            <webAppConfig>
                <contextPath>/${project.artifactId}</contextPath>
            </webAppConfig>
        </configuration>
    </plugin>
 
 
 
����������jetty��ָ��spring��profileΪdevelopment��ͬʱ�趨webӦ�õ������ĵ�ַ��Ӧ�ñ����artifactIdһ�¡�
 
ִ��������������Jetty������ͨ��http://localhost:8080/${project.artifactId}����WebӦ�á�
 
mvn jetty:run
 
 
Jetty Maven Plugin���֧�ֶ��maven goals����õľ���run������Ĳ���֧�ִ󲿷ֵ�goals
 
��1������Jetty������֧������goals��
 
<connectors>����ѡ����������org.eclipse.jetty.server.Connector��Jetty�˿ڼ��������б������ָ���ò�������������Ĭ�ϵ�8080�˿ڡ�
<jettyXml>����ѡ������ָ��Jetty�����ļ���·��
<scanIntervalSeconds>����ѡ����������WebӦ��ɨ�����ͣʱ�䣬WebӦ��ɨ����������޸��˳��򣬻��Զ����²��𡣸ò���Ĭ��ֵΪ0�����������Ȳ���
<systemProperties>����ѡ���������ò��ִ��ʱ��ϵͳ�������������������ʾ����ָ����spring��profileΪdevelopment����������øò���������Ҫ����maven��spring��profileһ�£�ͬʱ��mvn����������-Pdevelopmentѡ�����ֱ����spring�����ļ�������spring.profiles.activeΪdevelopment
<systemPropertiesFile>����ѡ����������ϵͳ���������ļ���λ�ã���������ִ�����е�ϵͳ��������
<loginServices>����ѡ����������org.eclipse.jetty.security.LoginServiceʵ������б�
<requestLog>����ѡ����������������־�ӿڣ�org.eclipse.jetty.server.RequestLog��ʵ���࣬����������־�Ĵ����ʽ��
����org.mortbay.jetty.NCSARequestLog����һ��NCSA��ʽ�������������ҳ������㼼��Ӧ������ (NCSA) ���ø�ʽ���ǳ��õı�׼��־��ʽ����ʵ�֡�
 
��2������WebӦ�ó��򣨲�֧��run-forked��stop����goals��
 
<webApp>����jetty6.1.6rc0��ʹ��webAppConfig��webӦ�ó������ø��ڵ�
<contextPath>������webӦ�ó����context·����Ĭ������£��������óɸ���Ŀ��pom.xml��<artifactId>
<descriptor>������webӦ�ó����������ļ�web.xml��·����Ĭ�ϸ������ļ�λ��WEB-INFĿ¼��
<defaultsDescriptor>����������web.xmlִ�е�webdefault.xml�����ļ���·��
<overrideDescriptor>��������web.xml��ȡ֮��ִ�е������ļ���ʹ�øò������Ը��ǻ�����web.xml�е�����
<tempDirectory>��WebӦ�õ���ʱĿ¼��Jetty�����ڴ�Ŀ¼����jsp�ļ����߸���jar����Ĭ��·��Ϊ${project.build.outputDirectory}/tmp
<baseResource>��ָ��WebӦ�þ�̬��Դ���ڵ�·����Ĭ��·��Ϊsrc/main/webapp
<resourceBases>��ָ�����WebӦ�þ�̬��Դ���ڵ�·����ʹ�ö��ŷָ�
<baseAppFirst>����ѡ������Ĭ��ֵΪtrue�������Ƿ������WebӦ�õ�ԭʼ��Դ֮ǰ��֮����Ӷ��war��
<jettyEnvXml>����ѡ������ָ��jetty-env.xml�����ļ���·��
<containerIncludeJarPattern>��jetty-8.1.x֮��İ汾����ʹ�ã���ѡ���������ü��ص�Jetty���� Classloader�е�Jar����·����ƥ��ģʽ������������jar�����ᱻ���META-INF����Դ��tld����ļ̳й�ϵ
<webInfIncludeJarPattern>��jetty-8.1.x֮��İ汾����ʹ�ã���ѡ���������ü��ص�WebӦ�ó����Classloader��WEB-INF classpath���е�Jar����·����ƥ��ģʽ������������jar�����ᱻ���META-INF����Դ��tld����ļ̳й�ϵ
<contextXml>����ѡ������ָ��context xml�����ļ���·��
run goals��������Jetty������Ӧ�ó��򣬲���ҪӦ�ó�������war��������run��֧��webapp�ڵ���������ԣ�
 
<classesDirectory>��WebӦ�ó����������·��
<testClassesDirectory>��WebӦ�ó���Ԫ�����������·����Ĭ��ֵΪ${project.build.testOutputDirectory}.
<useTestScope>��Jetty-7֮ǰ�İ汾��������ΪuseTestClasspath���������Ϊtrue����������ģʽ��<testClassesDirectory>��ָ�����༰�����������ȱ����ص�classpath�У�Ĭ��ֵΪfalse
<useProvidedScope>���������Ϊtrue��������Χ��ʾΪ��provided���������������ص�������classpath�У��ò�������ʹ�á�
<webAppSourceDirectory>��WebӦ�ó���̬��Դ·����Ĭ��ֵΪ${basedir}/src/main/webapp
<scanTargets>�����ó��˲���Զ�ɨ���λ���⣬������Ҫɨ���Ŀ¼���ļ��б�
<scanTargetPatterns>�����ó��˲���Զ�ɨ���λ���⣬������Ҫɨ���Ŀ¼���ļ���ƥ��ģʽ
<skip>��Ĭ��ֵΪfalse���������Ϊtrue������ִֹͣ�в��
Jetty Maven Plugin���֧�ֵ�����goals������£����http://wiki.eclipse.org/Jetty/Feature/Jetty_Maven_Plugin����
 
run-war����WebӦ�ó�������war��������Jetty�С�
run-exploded��ʹ��war explodedģʽ���ļ���ģʽ����WebӦ�ó�����������Jetty��
deploy-war��������run-war���ƣ��������maven���������в�����package�׶�
run-forked��jetty-7.5.2֮��İ汾���ã�ǿ��Jettyʹ��һ���µ�JVM����Ӧ�ó���
start��jetty-7.6.0֮��İ汾���ã�һ������ϲ���е�execution�ڵ�ʹ�ã�test-compile�׶�֮���ִ�й�����ȷ����Ҫ���༰�ļ������ɺ��ˡ�һ�����ڼ��ɲ���ʱ����Jetty�����ĵڶ����ֲ��Խ׶λ�����ϸ����
stop���ر������е�Jetty����
2����java������Jetty
SpringSide4�з�װ��Jetty�Ĳ����ṩ�˹�����JettyFactory �������ǿ��Ժܼ򵥵�����Jetty������JettyFactory�������£�
 
    /**
     * Copyright (c) 2005-2012 springside.org.cn
     *
     * Licensed under the Apache License, Version 2.0 (the "License");
     */
    package org.springside.modules.test.jetty;
    
    import java.util.List;
    
    import org.apache.commons.lang3.StringUtils;
    import org.eclipse.jetty.server.Connector;
    import org.eclipse.jetty.server.Server;
    import org.eclipse.jetty.server.nio.SelectChannelConnector;
    import org.eclipse.jetty.webapp.WebAppClassLoader;
    import org.eclipse.jetty.webapp.WebAppContext;
    
    import com.google.common.collect.Lists;
    
    /**
     * ����Jetty Server�Ĺ�����.
     * 
     * @author calvin
     */
    public class JettyFactory {
    
        private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";
        private static final String WINDOWS_WEBDEFAULT_PATH = "jetty/webdefault-windows.xml";
    
        /**
         * �������ڿ������е��Ե�Jetty Server, ��src/main/webappΪWebӦ��Ŀ¼.
         */
        public static Server createServerInSource(int port, String contextPath) {
            Server server = new Server();
            // ������JVM�˳�ʱ�ر�Jetty�Ĺ��ӡ�
            server.setStopAtShutdown(true);
    
            SelectChannelConnector connector = new SelectChannelConnector();
            connector.setPort(port);
            // ���Windows���ظ�����Jetty��Ȼ������˿ڳ�ͻ������.
            connector.setReuseAddress(false);
            server.setConnectors(new Connector[] { connector });
    
            WebAppContext webContext = new WebAppContext(DEFAULT_WEBAPP_PATH, contextPath);
            // �޸�webdefault.xml�����Windows��Jetty Lockס��̬�ļ�������.
            webContext.setDefaultsDescriptor(WINDOWS_WEBDEFAULT_PATH);
            server.setHandler(webContext);
    
            return server;
        }
    
        /**
         * ���ó�jstl-*.jar��������tld�ļ���jar��������.
         * jar���Ʋ���Ҫ�汾�ţ���sitemesh, shiro-web
         */
        public static void setTldJarNames(Server server, String... jarNames) {
            WebAppContext context = (WebAppContext) server.getHandler();
            List<String> jarNameExprssions = Lists.newArrayList(".*/jstl-[^/]*\\.jar$", ".*/.*taglibs[^/]*\\.jar$");
            for (String jarName : jarNames) {
                jarNameExprssions.add(".*/" + jarName + "-[^/]*\\.jar$");
            }
    
            context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                    StringUtils.join(jarNameExprssions, '|'));
    
        }
    
        /**
         * ������������application������target/classes��target/test-classes.
         */
        public static void reloadContext(Server server) throws Exception {
            WebAppContext context = (WebAppContext) server.getHandler();
    
            System.out.println("[INFO] Application reloading");
            context.stop();
    
            WebAppClassLoader classLoader = new WebAppClassLoader(context);
            classLoader.addClassPath("target/classes");
            classLoader.addClassPath("target/test-classes");
            context.setClassLoader(classLoader);
    
            context.start();
    
            System.out.println("[INFO] Application reloaded");
        }
    }
 
 
 
JettyFactory������������
 
createServerInSource����src/main/webappΪWebӦ��Ŀ¼����Jetty WebServer��ȷ��jvm�˳�ʱ�ر�Jetty����ͬһ���˿��������jettyʱ����˿ڳ�ͻ���������javascript��css�Ⱦ�̬�ļ���jetty�����������޸ĵ����⡣
setTldJarNames��Jetty��tomcat��web����ͨ�������classloader����չ��Jetty�е�org.mortbay.jetty.webapp.WebAppClassLoader�������һ��WebӦ��context�е�Ӧ���ࡣ
Jetty��jsp��������������Glassfish��Ҫ��JSF��ǩ����λ��Jetty������classpath�У�����λ��WebӦ�õ�classpath�У���Jetty��WebAppClassLoader����ʹ�ø�classloader�����࣬����tld�ļ��������ص���classloader�У���Jetty��classpath�и���ɨ�費�������Ի�����Ҳ���tld�ļ��������setTldJarNames�����������ý�����tld��jar�����ص�Jetty��classpath�С�
 
reloadContext�����¼���Jetty��context
����JettyFactory��Jetty�����е���Maven WebӦ�õ�ʾ���������£�
 
 
    package org.springside.examples.quickstart;
    
    import org.eclipse.jetty.server.Server;
    import org.springside.modules.test.jetty.JettyFactory;
    import org.springside.modules.test.spring.Profiles;
    
    /**
     * ʹ��Jetty���е���WebӦ��, ��Console����س��������¼���Ӧ��.
     * 
     * @author calvin
     */
    public class QuickStartServer {
    
        public static final int PORT = 8080;
        public static final String CONTEXT = "/quickstart";
        public static final String[] TLD_JAR_NAMES = new String[] { "sitemesh", "spring-webmvc", "shiro-web",
                "springside-core" };
    
        public static void main(String[] args) throws Exception {
            // �趨Spring��profile
            Profiles.setProfileAsSystemProperty(Profiles.DEVELOPMENT);
    
            // ����Jetty
            Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
            JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);
    
            try {
                server.start();
    
                System.out.println("[INFO] Server running at http://localhost:" + PORT + CONTEXT);
                System.out.println("[HINT] Hit Enter to reload the application quickly");
    
                // �ȴ��û�����س�����Ӧ��.
                while (true) {
                    char c = (char) System.in.read();
                    if (c == '\n') {
                        JettyFactory.reloadContext(server);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }
 
 
 
�϶δ��뻹�ṩ��ͨ��������console������Ļس��Զ��������������ģ�����������Class�ļ����������Ӧ�ٶȡ�
 
�������Խ׶�
�ڹ��ܲ��Ի򼯳ɲ��Խ׶Σ�ϣ���ڲ��Կ�ʼʱ�Զ�����Jetty������Ŀ���в��ԣ��������ʱֹͣJetty������Jetty Maven Plugin������԰���������������Զ�������������ʾ�����£�
 
 
    <plugin>
        <groupId>org.mortbay.jetty</groupId>
        <artifactId>jetty-maven-plugin</artifactId>
        <configuration>
            <scanIntervalSeconds>10</scanIntervalSeconds>
            <stopKey>foo</stopKey>
            <stopPort>9999</stopPort>
        </configuration>
        <executions>
            <execution>
                <id>start-jetty</id>
                <phase>pre-integration-test</phase>
                <goals>
                    <goal>start</goal>
                </goals>
                <configuration>
                    <scanIntervalSeconds>0</scanIntervalSeconds>
                    <daemon>true</daemon>
                </configuration>
            </execution>
            <execution>
                <id>stop-jetty</id>
                <phase>post-integration-test</phase>
                <goals>
                    <goal>stop</goal>
                </goals>
            </execution>
        </executions>
 
   </plugin>
 
 
 
 �����������У�ͨ��execution���Զ������н׶Σ�
 
��pre-integration-test�׶�����start goals����Jetty����
��post-integration-test�׶�����stop goalsֹͣJetty������
ʹ��<daemon>true</daemon>����ѡ����Ԥ��Jetty���������У���ʹ��ֻ��ִ��Mavenʱ�����С�
 
�������н׶�
Ϊ���ܹ���������ֱ�����е�war������Ҫ��jetty jar���⿪�������е�classֱ�ӱ��뵽war���У�����Ҫ��war���ṩһ�����Դ���������Jetty��Main�����������ṩ����ʵ�ַ�����
 
����һ
SpringSide4���ṩ��һ��ʵ�ַ������Լ��޸��Ż��������£�
 
1��ʹ��maven-assembly-plugin���´��
maven-assembly-plugin����ܽ�Ӧ�ó�������ָ����ʽ�ķַ���������Ҫ�����ܹ��Զ������/�ų�ָ����Ŀ¼���ļ���
 
Ϊ�����������������һ��Maven Profile���ڴ�����������£�
 
 
    <profile>
        <id>standalone</id>
        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-assembly-plugin</artifactId>
                    <executions>
                        <execution>
                            <phase>package</phase>
                            <goals>
                                <goal>single</goal>
                            </goals>
                            <configuration>
                                <descriptors>
                                    <descriptor>assembly-standalone.xml</descriptor>
                                </descriptors>
                                <archive>
                                    <manifest>
                                        <mainClass>org.springside.examples.showcase.Main</mainClass>
                                    </manifest>
                                </archive>
                            </configuration>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>
   </profile>
 
 
 
 ���������У�ͨ��execution���ô��������package�׶ο�ʼ������assembly-standalone.xml�ļ��������Ĺ�������archive�޸�war���е�META-INF/Manifest.mf���滻main classΪorg.springside.examples.showcase.Main��
 
assembly-standalone.xml�е��������£�
 
 
    <?xml version="1.0" encoding="UTF-8"?>
    <assembly xmlns="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2 http://maven.apache.org/xsd/assembly-1.1.2.xsd">
        <id>standalone</id>
        <formats>
            <format>war</format>
        </formats>
        <includeBaseDirectory>false</includeBaseDirectory>
    
        <dependencySets>
            <dependencySet>
                <outputDirectory>/</outputDirectory>
                <includes>
                    <include>org.eclipse.jetty*:*</include>
                </includes>
                <scope>provided</scope>
                <unpack>true</unpack>
                <unpackOptions>
                    <excludes>
                        <exclude>*</exclude>
                        <exclude>META-INF/*</exclude>
                        <exclude>about_files/*</exclude>
                    </excludes>
                </unpackOptions>
            </dependencySet>
        </dependencySets>
    
        <fileSets>
            <fileSet>
                <directory>${project.basedir}/target/${project.build.finalName}</directory>
                <outputDirectory>/</outputDirectory>
                <excludes>
                    <exclude>META-INF/**/*</exclude>
                </excludes>
            </fileSet>
            <fileSet>
                <directory>${project.basedir}/target/classes</directory>
                <includes>
                    <include>**/*/Main.class</include>
                </includes>
                <outputDirectory>/</outputDirectory>
            </fileSet>
        </fileSets>
    </assembly>
 
 
 
assembly-standalone.xml�漰�������ؼ��㣺
 
formatsָ������ĸ�ʽΪwar
includeBaseDirectoryΪfalse��ʾ������ѹ������ʹ��Ӧ�ó�������ƴ�����Ŀ¼
dependencySets��������ѡ���������������մ����ʲôĿ¼����������ͨ��include���ô�������а���Jetty������jar����unpack���ý�Jetty��jar����ѹ����unpackOptions�е�exclude����Ŀ¼��META-INF��about_files��������ų�������ǩ����ͻͬʱ��Сwar���Ĵ�С
fileSets�����û�ͨ���ļ���Ŀ¼�����������ƴ���������һ��fileSetָ����${project.basedir}/target/${project.build.finalName}�е����о�̬��Դ�������Ŀ¼�£����ų�META-INFĿ¼�µ������ļ����ڶ���fileSet��${project.basedir}/target/classes�е�����class�������ļ��������Ŀ¼�£�����org.springside.examples.showcase.Main������Main.class��ӵ�war����
2��ʹ�ô��봴��Jetty����
 
    package org.springside.examples.quickstart;
    
    import java.io.File;
    import java.net.URL;
    import java.security.ProtectionDomain;
    
    import org.eclipse.jetty.server.Server;
    import org.eclipse.jetty.webapp.WebAppContext;
    
    /**
     * Main Class for standalone running.
     *
     * @author calvin
     */
    public class Main {
    
        public static void main(String[] args) throws Exception {
    
            String contextPath = "/";
            int port = Integer.getInteger("port", 8080);
    
            Server server = createServer(contextPath, port);
    
            try {
                server.start();
                server.join();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(100);
            }
        }
    
        private static Server createServer(String contextPath, int port) {
            // use Eclipse JDT compiler
            System.setProperty("org.apache.jasper.compiler.disablejsr199", "true");
    
            Server server = new Server(port);
            server.setStopAtShutdown(true);
    
            ProtectionDomain protectionDomain = Main.class.getProtectionDomain();
            URL location = protectionDomain.getCodeSource().getLocation();
    
            String warFile = location.toExternalForm();
            WebAppContext context = new WebAppContext(warFile, contextPath);
            context.setServer(server);
    
            // ����work dir,war������ѹ����Ŀ¼��jsp�������ļ�Ҳ���������С�
            String currentDir = new File(location.getPath()).getParent();
            File workDir = new File(currentDir, "work");
            context.setTempDirectory(workDir);
    
            server.setHandler(context);
            return server;
        }
    }
 
 
 
 createServer�������𴴽�Jetty���񣬻�ȡwar��·��������context������Ŀ¼
 
main�����������createServer��������Jetty��������������·���������˿ڣ�������Jetty�����������war�����ڵ�·���������ģ����ȡ·���Ĵ���Ӧ�޸�Ϊ��
 
    ProtectionDomain protectionDomain = Main.class.getProtectionDomain();
    URL location = protectionDomain.getCodeSource().getLocation();
    location = java.net.URLDecoder.decode(location , "utf-8");
 
 
3��ע������
ͨ���������ã��Ѿ�������WebӦ�ó�����Ƕ��Jetty�����ˣ�������Ҫע�����¼���
 
Maven Pom�е�Jetty����ע��scope�޸�Ϊprovided����ֹJetty��Jar������WEB-INF/lib�С�
�����Ҫ����jspҳ�棬��Ҫ�������м���jsp-2.1-glassfish�������ã�ע����scope��������Ϊprovided
    <dependency>
        <groupId>org.mortbay.jetty</groupId>
        <artifactId>jsp-2.1-glassfish</artifactId>
        <version>2.1.v20100127</version>
    </dependency>
 
 
����Jettyֻ����������classpath��Ѱ��jstl tags��������Ҫע�⽫jstl�������Jetty������classpath�У�����jetty-7 (7.6.9)��jetty-8 (8.1.9)��jetty-9 (9.0.0.M4)֮��İ汾��Ƕ��jstl��������Ҫ���jstl����
4������
ִ���������WebӦ�ô����war������${project.basedir}/targetĿ¼�½�������Ƕ��Jetty������war����
 
mvn package -Pstandalone
 
 
ͨ��������������war����
 
Java -Xms2048m -Xmx2048m -XX:MaxPermSize=128m -jar xxx.war
 
 
������
����һ����Ҫ��ʹ����maven-assembly-plugin�����Զ�����������֮�⻹����ʹ��maven-war-plugin��maven-antrun-plugin��maven-dependency-plugin��maven-compiler-plugin��ͬʵ�ִ�����ִ�е�war��
 
Maven POM����ʾ�����£�
 
���ƴ���
    <profile>
        <id>standalone</id>
        <build>
            <finalName>quickstart</finalName>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-war-plugin</artifactId>
                    <version>2.3</version>
                    <configuration>
                        <archive>
                            <manifest>
                                <mainClass>org.springside.examples.quickstart.Main</mainClass>
                            </manifest>
                        </archive>
                        <warName>${project.artifactId}-standalone</warName>
                    </configuration>
                </plugin>
    
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-antrun-plugin</artifactId>
                    <version>1.7</version>
                    <executions>
                        <execution>
                            <id>main-class-placement</id>
                            <phase>prepare-package</phase>
                            <configuration>
                                <target>
                                    <move todir="${project.build.directory}/${project.artifactId}/">
                                        <fileset dir="${project.build.directory}/classes/">
                                            <include name="**/*/Main.class" />
                                        </fileset>
                                    </move>
                                </target>
                            </configuration>
                            <goals>
                                <goal>run</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-dependency-plugin</artifactId>
                    <version>2.5.1</version>
                    <executions>
                        <execution>
                            <id>jetty-classpath</id>
                            <phase>prepare-package</phase>
                            <goals>
                                <goal>unpack-dependencies</goal>
                            </goals>
                            <configuration>
                                <includeGroupIds>org.eclipse.jetty, org.eclipse.jetty.orbit,
                                    javax.servlet</includeGroupIds>
                                <includeScope>provided</includeScope>
                                <!-- remove some files in order to decrease size -->
                                <excludes>*, about_files/*, META-INF/*</excludes>
                                <!-- <excludeArtifactIds>jsp-api,jstl</excludeArtifactIds> -->
                                <outputDirectory>
                                    ${project.build.directory}/${project.artifactId}
                                </outputDirectory>
                            </configuration>
                        </execution>
                    </executions>
                </plugin>
                <!-- to support compilation in linux -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>2.5.1</version>
                    <configuration>
                        <target>1.6</target>
                        <source>1.6</source>
                        <encoding>UTF-8</encoding>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </profile>
 
maven-war-plugin��������������Main����ʵ����Ϊorg.springside.examples.quickstart.Main
maven-antrun-pluginִ��ant��target���񣬽�org.springside.examples.quickstart.Main��ת�Ƶ�war���ĸ�·����
maven-dependency-plugin����jetty��ص�jar�����в�������������war����·���£��Ա����´��
maven-compiler-plugin�������webӦ��
ע�����org.springside.examples.showcase.Main��ʵ�ּ����з���ͬ����һ��

��Դ�� http://www.2cto.com/kf/201401/269724.html