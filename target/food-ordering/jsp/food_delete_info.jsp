<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 09.02.2021
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.food.delete.information" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
</head>
<body>
<div class="container">
    <h3><fmt:message key="label.food.delete.information" /></h3>
    <c:choose>
        <c:when test="${isSuccessDeleteFood}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="message.delete.food.success" />
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-danger" role="alert">
                <fmt:message key="message.delete.food.error" />
            </div>
        </c:otherwise>
    </c:choose>
</div>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
</body>
</html>
