ͨ���������������ã����WebӦ���Ѿ����Թ���һ����¼���񡣵��ǣ�������������ΪCAS Client�˵�WebӦ��ֻȡ�����û���¼������Ϣ������ʵ��Ӧ���У�WebӦ��������Ҫ��õ�¼�û��������Ϣ�������Ա�ȼ����Ա�סַ�ȡ�Ҫ�ﵽ��Ŀ�ģ�ֻ���Server�������޸ļ���ʵ�֡�
 
1.      ��������ü��޸�
 
�ٶ������洢�û���Ϣ�����ݱ�userinfo�л�����һ����Ϊaddress�����ڴ洢�û���ַ���ֶΣ���WebӦ�ó���ϣ���ܹ���CAS Server����õ�ǰ��¼�û��ĵ�ַ��Ϣ����Server����Ҫ�����������޸�deployerConfigContext.xml����������˵����μ�ע�͡�
 
<!--��ԭ��attributeRepository����ע�� -->       
 
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
 
<!--����attributeRepository����(��ʼ) -->
 
<bean class="org.jasig.services.persondir.support.jdbc.SingleRowJdbcPersonAttributeDao"id="attributeRepository">
 
        <!-- ָ��ʹ�õ�����Դ���˴�dataSource�������úõ�����Դ -->
 
        <constructor-arg index="0"ref="dataSource"/>
 
        <!-- �����ݿ��в�ѯ��Ϣ��SQL��䣬ͨ��ֻ��Ҫ�޸ı������� -->
 
       <constructor-arg index="1" value="select * fromuserinfo where {0}"/>
 
        <propertyname="queryAttributeMapping">
 
            <map>
 
<!-- ������ѯ�Ĳ�������userName�滻Ϊ���б�ʾ�û������ֶ����� -->
 
                <entrykey="username" value="userName"/>
 
            </map>
 
        </property>
 
        <propertyname="resultAttributeMapping">
 
            <map>
 
<!-- ��Ҫ���ظ�WebӦ�õ�������Ϣ�������Ϣʱ�ɼ�������entry�ڵ�-->
 
<!--keyֵΪ���ݱ��е��ֶ����ƣ�valueֵΪClient��ȡֵʱ�����Ʊ�ʶ-->
 
                <entry key="address" value="address"/>
 
            </map>
 
        </property>
 
</bean>
 
     <!--����attributeRepository����(����) -->
 
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
 
                        <!--���Ӵ������� -->
 
                       <property name="ignoreAttributes" value="true"/>
 
                    </bean>
 
                    �� ��                 
 
                </list>
 
            </property>
 
        </bean>
 
CASServerҪ���������Ϣ������Client�ˣ�����Ҫ�޸������Ϣ��װ���ļ�WEB-INF/view/jsp/protocol/2.0/casServiceValidationSuccess.jsp��casServiceValidationSuccess.jsp������װ�����û���Ϣ��XML������޸Ĳ����ǽ���Ҫ���ݵĶ�����Ϣ���뵽���������ɵ�XML�ļ�֮�С������޸����£�
 
<cas:serviceResponsexmlns:cas='http://www.yale.edu/tp/cas'>
 
<cas:authenticationSuccess>  <cas:user>${fn:escapeXml(assertion.chainedAuthentications[fn:length(assertion.chainedAuthentications)-1].principal.id)}</cas:user>
 
<!-- ����������Ϣ(��ʼ) -->
 
  <c:iftest="${fn:length(assertion.chainedAuthentications[fn:length(assertion.chainedAuthentications)-1].principal.attributes)> 0}">
 
                    <cas:attributes>
 
                             <c:forEachvar="attr"items="${assertion.chainedAuthentications[fn:length(assertion.chainedAuthentications)-1].principal.attributes}">
 
<!--ע����е���ȷд�����������ϻ������Ǵ����-->           <cas:${fn:escapeXml(attr.key)}>${fn:escapeXml(attr.value)}</cas:${fn:escapeXml(attr.key)}>
 
                             </c:forEach>
 
                    </cas:attributes>
 
           </c:if>
 
<!-- ����������Ϣ(����) -->
 
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
 
 
 
2.      Java Client��ȡ�ø����û���Ϣ
 
Java Client�˲���Ҫ���κ��޸ľͿ��Լ�������ʹ��CAS���������Ҫȡ���û�������Ϣ������ͨ��AttributePrincipal����ȡ��Attribute�б�(һ��Map����)����в�ѯ��
 
�޸�ǰ��Java Client��ʾ�����룬�����׷��ȡ��address��Ϣ�Ĵ��룬�����������·���ҳ�棬���Կ���ҳ������ʾ�˵�ǰ�û���address��Ϣ��
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