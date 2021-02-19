<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/11/21
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.error" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
</head>
<body>
<div class="container">
    <h3><fmt:message key="label.error" /></h3>
    Request from ${pageContext.errorData.requestURI} is failed <br/>
    Servlet name or type: ${pageContext.errorData.servletName} <br/>
    Status code: ${pageContext.errorData.statusCode} <br/>
    Exception: ${pageContext.errorData.throwable}
</div>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
</body>
</html>