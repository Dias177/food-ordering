<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 17.02.2021
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.delete.role" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
</head>
<body>
<div class="container">
    <h3><fmt:message key="label.delete.role" /></h3>
    <c:choose>
        <c:when test="${isSuccessDeleteRole}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="message.delete.role.success" />
            </div>
        </c:when>
        <c:when test="${isErrorDeleteRole}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="message.delete.role.error" />
            </div>
        </c:when>
        <c:otherwise>
            <form class="text-center" name="deleteRoleForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="delete_role"/>
                <div class="form-group input-mini">
                    <label for="role"><fmt:message key="label.role" />:</label>
                    <select class="custom-select custom-select-sm" id="role" name="role" autofocus required>
                        <c:forEach var="role" items="${roles}">
                            <option value="${role.id}">${role.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-danger"><fmt:message key="label.remove" /></button>
            </form>
        </c:otherwise>
    </c:choose>
</div>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
</body>
</html>
