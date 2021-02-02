<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 2/1/21
  Time: 00:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.edit.password" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8" />
</head>
<body>
<div class="container">
    <h3><fmt:message key="label.edit.password" /></h3>
    <div class="list-group list-group-horizontal-md">
        <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/controller?command=show_editing_profile"><fmt:message key="label.edit.profile" /></a>
        <a class="list-group-item list-group-item-action active" href="${pageContext.request.contextPath}/controller?command=show_editing_password"><fmt:message key="label.edit.password" /></a>
    </div>
<c:if test="${not empty successEditPassword}">
    <div class="alert alert-success" role="alert">
            ${successEditPassword}
    </div>
</c:if>
<c:if test="${empty successEditPassword}">
    <form class="text-center" name="editPasswordForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="edit_password"/>
        <div class="form-group input-mini">
            <label for="password"><fmt:message key="label.password" />:</label>
            <input type="password" class="form-control" id="password" name="password" value="" required/>
        </div>
        <c:if test="${not empty errorInvalidPassword}">
            <div class="alert alert-danger" role="alert">
                    ${errorInvalidPassword}
            </div>
        </c:if>
        <div class="form-group input-mini">
            <label for="confirmedPassword"><fmt:message key="label.confirm.password" />:</label>
            <input type="password" class="form-control" id="confirmedPassword" name="confirmedPassword" value="" required>
        </div>
        <c:if test="${not empty errorWrongConfirmedPassword}">
            <div class="alert alert-danger" role="alert">
                    ${errorWrongConfirmedPassword}
            </div>
        </c:if>
        <button type="submit" class="btn btn-primary"><fmt:message key="label.edit" /></button>
    </form>
</c:if>
</div>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8" />
</body>
</html>
