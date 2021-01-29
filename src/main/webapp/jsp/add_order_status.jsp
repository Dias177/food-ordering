<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/28/21
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.add.order.status" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
</head>
<body>
<div class="container">
<h3><fmt:message key="label.add.order.status" /></h3>
    <form class="text-center" name="addOrderStatusForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="add_order_status"/>
        <c:if test="${not empty successAddOrderStatusName}">
            <div class="alert alert-success" role="alert">
                    ${successAddOrderStatusName}
            </div>
        </c:if>
        <div class="form-group input-mini">
            <label for="orderStatusName"><fmt:message key="label.name.for.order.status" />:</label>
            <input type="text" class="form-control" id="orderStatusName" name="orderStatusName" value="" autofocus required>
        </div>
        <c:if test="${not empty errorInvalidOrderStatusName}">
            <div class="alert alert-danger" role="alert">
                    ${errorInvalidOrderStatusName}
            </div>
        </c:if>
        <button type="submit" class="btn btn-primary"><fmt:message key="label.add" /></button>
    </form>
</div>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
</body>
</html>
