<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 12.02.2021
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.delete.user" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
</head>
<body>
<div class="container">
    <h3><fmt:message key="label.delete.user" /></h3>
    <c:choose>
        <c:when test="${isSuccessDeleteUser}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="message.delete.user.success" />
            </div>
        </c:when>
        <c:when test="${isErrorDeleteUser}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="message.delete.user.error" />
            </div>
        </c:when>
        <c:otherwise>
            <div class="row row-cols-1 row-cols-md-3">
                <c:forEach var="user" items="${users}">
                    <div class="col mb-4">
                        <div class="card h-100 text-center" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title">${user.firstName} ${user.lastName}</h5>
                                <p class="card-text">${user.birthday}</p>
                                <p class="card-text">${user.phoneNumber}</p>
                                <p class="card-text">${user.email}</p>
                                <p class="card-text">${roles[user.roleId - 1].name}</p>
                                <a href="${pageContext.request.contextPath}/controller?command=delete_user&user_id=${user.id}" class="btn btn-danger" role="button"><fmt:message key="label.remove" /></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
</body>
</html>
