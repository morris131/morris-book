����Spring Boot�ܹ����ٿ�������ݲ�������ԣ������кܴ�һ����Spring Boot���û�����������RESTful API�������ǹ���RESTful API��Ŀ��ͨ���������ڶ��ն˵�ԭ����Щ�ն˻Ṳ�úܶ�ײ�ҵ���߼���������ǻ���������һ����ͬʱ�����ڶ���ƶ��˻���Webǰ�ˡ�

����һ�������ǵ�RESTful API���п���Ҫ��Զ��������Ա���������Ŷӣ�IOS������Android��������Web�����ȡ�Ϊ�˼����������Ŷ�ƽʱ�����ڼ��Ƶ����ͨ�ɱ�����ͳ�������ǻᴴ��һ��RESTful API�ĵ�����¼���нӿ�ϸ�ڣ�Ȼ�����������������¼������⣺
* ���ڽӿ��ڶ࣬����ϸ�ڸ��ӣ���Ҫ���ǲ�ͬ��HTTP�������͡�HTTPͷ����Ϣ��HTTP�������ݵȣ����������ش�������ĵ�������Ǽ��ǳ��������£����εı�Թ�������ڶ���
* ����ʱ�����ƣ������޸Ľӿ�ʵ�ֵ�ʱ�򶼱���ͬ���޸Ľӿ��ĵ������ĵ�������ִ���������ͬ��ý�飬�������ϸ�Ĺ�����ƣ���Ȼ�����׵��²�һ������

Ϊ�˽���������������⣬���Ľ�����RESTful API���ذ��û��Swagger2�����������ɵ����ϵ�Spring Boot�У�����Spring MVC���������֯��ǿ��RESTful API�ĵ������ȿ��Լ������Ǵ����ĵ��Ĺ�������ͬʱ˵��������������ʵ�ִ����У���ά���ĵ����޸Ĵ�������Ϊһ�壬�������������޸Ĵ����߼���ͬʱ������޸��ĵ�˵��������Swagger2Ҳ�ṩ��ǿ���ҳ����Թ���������ÿ��RESTful API��

������[����RESTful API�뵥Ԫ����](����RESTfulAPI�뵥Ԫ����.md)�Ĺ���Ϊ���������Swagger2��

1. ���Swagger2����

��ϸ����ο�[pom.xml](projects/chapter5/pom.xml)

```xml
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.2.2</version>
		</dependency>

		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.2.2</version>
		</dependency>
```

2. ����Swagger2������

��ϸ����ο�[Swagger2.java](projects/chapter5/src/main/java/com/morris/Swagger2.java)

```java
package com.morris;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by lian.chen on 2017/6/15.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.morris.web"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot��ʹ��Swagger2����RESTful APIs")
                .description("����Spring Boot����������ע��https://github.com/morris131/morris-book")
                .termsOfServiceUrl("https://github.com/morris131/morris-book")
                .contact("morris")
                .version("1.0")
                .build();
    }
}

```
> ���ϴ�����ʾ��ͨ��@Configurationע�⣬��Spring�����ظ������á���ͨ��@EnableSwagger2ע��������Swagger2����ͨ��createRestApi��������Docket��Bean֮��apiInfo()����������Api�Ļ�����Ϣ����Щ������Ϣ��չ�����ĵ�ҳ���У���select()��������һ��ApiSelectorBuilderʵ������������Щ�ӿڱ�¶��Swagger��չ�֣���������ָ��ɨ��İ�·�������壬Swagger��ɨ��ð�������Controller�����API���������ĵ����ݣ����˱�@ApiIgnoreָ�������󣩡�

3. ����ĵ�����

��������������ú���ʵ�Ѿ����������ĵ����ݣ������������ĵ���Ҫ�����������������Ҫ��Դ�ں������������������û������Ѻã�����ͨ����Ҫ�Լ�����һЩ˵�����ḻ�ĵ����ݡ�������ʾ������ͨ��@ApiOperationע������API����˵����ͨ��@ApiImplicitParams��@ApiImplicitParamע��������������˵����

��ϸ����ο�[UserApiController.java](projects/chapter5/src/main/java/com/morris/web/UserApiController.java)

```java
package com.morris.web;

import com.morris.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by lian.chen on 2017/6/15.
 */
@RestController
public class UserApiController {

    static Map<Long, User> userMap = Collections.synchronizedMap(new HashMap<Long, User>());

    @ApiOperation(value = "����û�", notes = "����User��������û�")
    @ApiImplicitParam(name = "user", value = "���������û�ʵ��")
    @RequestMapping("addUser")
    public String addUser(@ModelAttribute User user) {
        userMap.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "��ȡ�û��б�", notes = "")
    @RequestMapping("getList")
    public List<User> getList() {
        List<User> users = new ArrayList<>(userMap.values());
        return users;
    }

    @ApiOperation(value = "��ѯ�û�", notes = "�����û�ID��ȡ�û�����")
    @ApiImplicitParam(name = "id", value = "�û�ID")
    @RequestMapping("getById")
    public User getById(@PathVariable Long id) {
        return userMap.get(id);
    }

    @ApiOperation(value = "ɾ���û�", notes = "�����û�IDɾ���û�")
    @ApiImplicitParam(name = "id", value = "�û�ID")
    @RequestMapping("delUser")
    public String delUser(@PathVariable Long id) {
        userMap.remove(id);
        return "success";
    }

}

```

4. ������Ŀ
���ʵ�ַ��http://localhost:8080/swagger-ui.html��ҳ�����£�
![8.jpg](images/8.jpg "")

5. API�ĵ����������

Swagger���˲鿴�ӿڹ����⣬���ṩ�˵��Բ��Թ��ܣ����ǿ��Ե����ͼ���Ҳ��Model Schema����ɫ������ָ����User�����ݽṹ������ʱValue�о�����user�����ģ�壬����ֻ��Ҫ�����޸ģ�����·���Try it out������ť�����������һ��������ã�

��ʱ����Ҳ����ͨ������GET��������֤֮ǰ��POST�����Ƿ���ȷ��

���Ϊ��Щ�ӿڱ�д�ĵ��Ĺ������������ӵ����������Ƿǳ��ٶ��Ҿ���ģ�����ԭ�д��������Ҳ�����ܷ�Χ֮�ڡ���ˣ��ڹ���RESTful API��ͬʱ������swagger����API�ĵ����й����Ǹ������ѡ��
