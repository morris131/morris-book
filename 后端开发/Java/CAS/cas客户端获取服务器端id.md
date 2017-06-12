1. UsernamePasswordCredential 添加属性systemId
 @NotNull  
    @Size(min=1, message = "required.systemId")  
    private String systemId; 
    
    public String getSystemId() {  
        return systemId;  
    }  
  
    public void setSystemId(String systemId) {  
        this.systemId = systemId;  
    }  
  
     public String toStringSystemId() {  
        return "[systemId: " + this.systemId + "]";  
    }  
2. deployerConfigContext.xml
<bean id="primaryAuthenticationHandler"
          class="org.jasig.cas.adaptors.jdbc.QueryDatabaseAuthenticationHandler">
        <property name="attributeRepository" ref="attributeRepository" />
        <property name="dataSource" ref="casDataSource" />
	  <property name="sql" 
	           value="select passwd from sp_supplier_user t where t.login_name = ?" />
	  <property  name="passwordEncoder"  ref="passwordEncoder"/>
    </bean>
    
    <bean id="passwordEncoder" class="org.jasig.cas.authentication.handler.PlainTextPasswordEncoder"/>
    
    <!-- 数据源配置,使用应用内的Tomcat JDBC连接池 -->
		<bean id="casDataSource" class="org.apache.tomcat.jdbc.pool.DataSource"
			destroy-method="close">
			<!-- Connection Info -->
			<property name="driverClassName" value="oracle.jdbc.driver.OracleDriver" />
			<property name="url" value="jdbc:oracle:thin:@172.16.92.24:1521:neikong" />
			<property name="username" value="tcpmain1" />
			<property name="password" value="tcpmain9" />
			<property name="maxActive" value="100" />
			<property name="maxIdle" value="100" />
			<property name="minIdle" value="0" />
			<property name="defaultAutoCommit" value="true" />
			<!-- 连接Idle10分钟后超时，每1分钟检查一次 -->
			<property name="timeBetweenEvictionRunsMillis" value="60000" />
			<property name="minEvictableIdleTimeMillis" value="600000" />
		</bean>
3. QueryDatabaseAuthenticationHandler
 @NotNull
    private String sql;
    
    @NotNull
    private StubPersonAttributeDao attributeRepository;
    public void setAttributeRepository(StubPersonAttributeDao attributeRepository) {
		this.attributeRepository = attributeRepository;
	}
	/** {@inheritDoc} */
    @Override
    protected final HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {
        final String username = credential.getUsername();
        //System.out.println("XXX:"+dbPassword);
        System.out.println("传入:"+credential.getPassword());
        final String encryptedPassword = this.getPasswordEncoder().encode(credential.getPassword());
        System.out.println("加密:"+encryptedPassword);
        String mySystemId = credential.getSystemId();  
        //String[] systemIdGroup=mySystemId.split(",");  
       // String systemId= systemIdGroup[0];  
        System.out.println("systemId---------"+mySystemId+"----------------systemid value");
        
        List<Object> systemId = new ArrayList<Object>();
        systemId.add(mySystemId);
        
        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        map.put("systemId", systemId);
       attributeRepository.setBackingMap(map);
        
        
        try {
        	System.out.println("sql:"+sql);
        	//getJdbcTemplate().q
            final String dbPassword = getJdbcTemplate().queryForObject(this.sql, String.class, username);
            System.out.println("数据库中:"+dbPassword);
            if (!dbPassword.equals(encryptedPassword)) {
                throw new FailedLoginException("Password does not match value on record.");
            }
        } catch (final IncorrectResultSizeDataAccessException e) {
        	System.out.println(e.getMessage());
            if (e.getActualSize() == 0) {
                throw new AccountNotFoundException(username + " not found with SQL query");
            } else {
                throw new FailedLoginException("Multiple records found for " + username);
            }
        } catch (final DataAccessException e) {
            throw new PreventedException("SQL exception while executing query for " + username, e);
        }
        return createHandlerResult(credential, new SimplePrincipal(username), null);
    }
    /**
     * @param sql The sql to set.
     */
    public void setSql(final String sql) {
        this.sql = sql;
    }
4.casServiceValidationSuccess.jsp
<%@ page session="false" contentType="application/xml; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<cas:serviceResponse xmlns:cas='http://www.yale.edu/tp/cas'>
	<cas:authenticationSuccess>
		<cas:user>${fn:escapeXml(assertion.primaryAuthentication.principal.id)}</cas:user>
		<c:if test="${fn:length(assertion.chainedAuthentications[fn:length(assertion.chainedAuthentications)-1].principal.attributes) > 0}">
            <cas:attributes>
                <c:forEach var="attr" items="${assertion.chainedAuthentications[fn:length(assertion.chainedAuthentications)-1].principal.attributes}">
                    <cas:${fn:escapeXml(attr.key)}>${fn:escapeXml(attr.value)}</cas:${fn:escapeXml(attr.key)}>
                </c:forEach>
            </cas:attributes>
        </c:if>
		
        <c:if test="${not empty pgtIou}">
        		<cas:proxyGrantingTicket>${pgtIou}</cas:proxyGrantingTicket>
        </c:if>
        <c:if test="${fn:length(assertion.chainedAuthentications) > 1}">
		  <cas:proxies>
            <c:forEach var="proxy" items="${assertion.chainedAuthentications}" varStatus="loopStatus" begin="0" end="${fn:length(assertion.chainedAuthentications)-2}" step="1">
			     <cas:proxy>${fn:escapeXml(proxy.principal.id)}</cas:proxy>
            </c:forEach>
		  </cas:proxies>
        </c:if>
	</cas:authenticationSuccess>
</cas:serviceResponse>
5. 客户端的获取方式
		AttributePrincipal principal = (AttributePrincipal) req.getUserPrincipal();
		
		String username = principal.getName();// 用户名
		Map attributes = principal.getAttributes(); //附加属性
