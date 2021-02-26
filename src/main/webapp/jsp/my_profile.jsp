<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/20/21
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.my.profile" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
</head>
<body>
<div class="container">
    <h3><fmt:message key="label.my.profile" /></h3>
    <div class="card text-center mx-auto" style="width: 18rem;">
        <ctg:user-card-body user="${user}" />
    </div>
</div>
</body>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
</html>
