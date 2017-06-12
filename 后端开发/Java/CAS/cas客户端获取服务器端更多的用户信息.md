通过上述部署与配置，多个Web应用已经可以共用一个登录服务。但是，上述过程中作为CAS Client端的Web应用只取得了用户登录名称信息，而在实际应用中，Web应用往往需要获得登录用户更多的信息，例如会员等级、性别、住址等。要达到此目的，只需对Server端稍做修改即可实现。
 
1.      服务端配置及修改
 
假定上述存储用户信息的数据表userinfo中还包含一个名为address的用于存储用户地址的字段，而Web应用程序希望能够从CAS Server处获得当前登录用户的地址信息，则Server端需要按以下内容修改deployerConfigContext.xml。部分配置说明请参见注释。
 
<!--将原有attributeRepository配置注释 -->       
 
<!--
 
<beanid="attributeRepository"
 
           class="org.jasig.services.persondir.support.StubPersonAttributeDao">
 
           <propertyname="backingMap">
 
                    <map>
 
                             <entrykey="uid" value="uid" />
 
                             <entrykey="eduPersonAffiliation" value="eduPersonAffiliation"/>
 
                             <entrykey="groupMembership" value="groupMembership" />
 
                    </map>
 
           </property>
 
</bean>
 
-->
 
<!--新增attributeRepository配置(开始) -->
 
<bean class="org.jasig.services.persondir.support.jdbc.SingleRowJdbcPersonAttributeDao"id="attributeRepository">
 
        <!-- 指定使用的数据源，此处dataSource是已配置好的数据源 -->
 
        <constructor-arg index="0"ref="dataSource"/>
 
        <!-- 从数据库中查询信息的SQL语句，通常只需要修改表名即可 -->
 
       <constructor-arg index="1" value="select * fromuserinfo where {0}"/>
 
        <propertyname="queryAttributeMapping">
 
            <map>
 
<!-- 上述查询的参数，将userName替换为表中表示用户名的字段名称 -->
 
                <entrykey="username" value="userName"/>
 
            </map>
 
        </property>
 
        <propertyname="resultAttributeMapping">
 
            <map>
 
<!-- 需要返回给Web应用的其它信息，多个信息时可继续增加entry节点-->
 
<!--key值为数据表中的字段名称，value值为Client端取值时的名称标识-->
 
                <entry key="address" value="address"/>
 
            </map>
 
        </property>
 
</bean>
 
     <!--新增attributeRepository配置(结束) -->
 
<bean
 
           id="serviceRegistryDao"
 
       class="org.jasig.cas.services.InMemoryServiceRegistryDaoImpl">
 
            <propertyname="registeredServices">
 
                <list>
 
                    <beanclass="org.jasig.cas.services.RegexRegisteredService">
 
                        <propertyname="id" value="0" />
 
                        <propertyname="name" value="HTTP and IMAP" />
 
                        <propertyname="description" value="Allows HTTP(S) and IMAP(S)protocols" />
 
                        <propertyname="serviceId" value="^(https?|imaps?)://.*" />
 
                        <propertyname="evaluationOrder" value="10000001" />
 
                        <!--增加此项配置 -->
 
                       <property name="ignoreAttributes" value="true"/>
 
                    </bean>
 
                    … …                 
 
                </list>
 
            </property>
 
        </bean>
 
CASServer要将额外的信息传递至Client端，还需要修改完成信息组装的文件WEB-INF/view/jsp/protocol/2.0/casServiceValidationSuccess.jsp。casServiceValidationSuccess.jsp负责组装包含用户信息的XML，因此修改部分是将需要传递的额外信息加入到它最终生成的XML文件之中。具体修改如下：
 
<cas:serviceResponsexmlns:cas='http://www.yale.edu/tp/cas'>
 
<cas:authenticationSuccess>  <cas:user>${fn:escapeXml(assertion.chainedAuthentications[fn:length(assertion.chainedAuthentications)-1].principal.id)}</cas:user>
 
<!-- 新增额外信息(开始) -->
 
  <c:iftest="${fn:length(assertion.chainedAuthentications[fn:length(assertion.chainedAuthentications)-1].principal.attributes)> 0}">
 
                    <cas:attributes>
 
                             <c:forEachvar="attr"items="${assertion.chainedAuthentications[fn:length(assertion.chainedAuthentications)-1].principal.attributes}">
 
<!--注意此行的正确写法，网上资料基本都是错误的-->           <cas:${fn:escapeXml(attr.key)}>${fn:escapeXml(attr.value)}</cas:${fn:escapeXml(attr.key)}>
 
                             </c:forEach>
 
                    </cas:attributes>
 
           </c:if>
 
<!-- 新增额外信息(结束) -->
 
           <c:if test="${not emptypgtIou}">
 
                    <cas:proxyGrantingTicket>${pgtIou}</cas:proxyGrantingTicket>
 
           </c:if>
 
           <c:if test="${fn:length(assertion.chainedAuthentications)> 1}">
 
                    <cas:proxies>
 
                             <c:forEachvar="proxy" items="${assertion.chainedAuthentications}"varStatus="loopStatus" begin="0"end="${fn:length(assertion.chainedAuthentications)-2}"step="1">
 
                                       <cas:proxy>${fn:escapeXml(proxy.principal.id)}</cas:proxy>
 
                             </c:forEach>
 
                    </cas:proxies>
 
           </c:if>
 
</cas:authenticationSuccess>
 
</cas:serviceResponse>
 
 
 
2.      Java Client端取得更多用户信息
 
Java Client端不需要做任何修改就可以继续正常使用CAS服务，如果需要取得用户更多信息，可以通过AttributePrincipal对象取得Attribute列表(一个Map对象)后进行查询。
 
修改前述Java Client的示例代码，在最后追加取得address信息的代码，重启服务并重新访问页面，可以看到页面上显示了当前用户的address信息。
<%@pageimport="org.jasig.cas.client.authentication.AttributePrincipal" %>
 
<%@pageimport="org.jasig.cas.client.validation.Assertion" %>
 
<%@page import="java.util.*" %>
 
 
 
<%
 
String loginName1 = request.getRemoteUser();
 
%>
 
request.getRemoteUser(): <%=loginName1%><br/>
 
 
 
<%
 
AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();
 
String loginName2 = principal.getName();
 
%>
 
request.getUserPrincipal().getName():<%=loginName2%><br/>
 
 
 
<%
 
         Object object =request.getSession().getAttribute("_const_cas_assertion_");
 
         Assertion assertion =(Assertion)object;
 
         String loginName3 =assertion.getPrincipal().getName();
 
%>
 
request.getSession().getAttribute("_const_cas_assertion_").getPrincipal().getName():<%=loginName3%><br/>
 
 
 
<br/>