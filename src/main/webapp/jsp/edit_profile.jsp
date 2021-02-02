<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/30/21
  Time: 00:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.edit.profile" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8" />
</head>
<body>
<div class="container">
    <h3><fmt:message key="label.edit.profile" /></h3>
    <div class="list-group list-group-horizontal-md">
        <a class="list-group-item list-group-item-action active" href="${pageContext.request.contextPath}/controller?command=show_editing_profile"><fmt:message key="label.edit.profile" /></a>
        <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/controller?command=show_editing_password"><fmt:message key="label.edit.password" /></a>
    </div>
    <c:if test="${not empty successEditProfile}">
        <div class="alert alert-success" role="alert">
                ${successEditProfile}
        </div>
    </c:if>
    <c:if test="${empty successEditProfile}">
    <form class="text-center" name="editProfileForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="edit_profile"/>
        <div class="form-group input-mini">
            <label for="firstName"><fmt:message key="label.firstname" />:</label>
            <input type="text" class="form-control" id="firstName" name="firstName" value="${currentFirstName}" autofocus required>
        </div>
        <c:if test="${not empty errorInvalidFirstName}">
            <div class="alert alert-danger" role="alert">
                    ${errorInvalidFirstName}
            </div>
        </c:if>
        <div class="form-group input-mini">
            <label for="lastName"><fmt:message key="label.lastname" />:</label>
            <input type="text" class="form-control" id="lastName" name="lastName" value="${currentLastName}" required>
        </div>
        <c:if test="${not empty errorInvalidLastName}">
            <div class="alert alert-danger" role="alert">
                    ${errorInvalidLastName}
            </div>
        </c:if>
        <div class="form-group input-mini">
            <label for="birthday"><fmt:message key="label.birthday" />:</label>
            <input type="date" class="form-control" id="birthday" name="birthday" value="${currentBirthday}">
        </div>
        <c:if test="${not empty errorInvalidBirthday}">
            <div class="alert alert-danger" role="alert">
                    ${errorInvalidBirthday}
            </div>
        </c:if>
        <div class="form-group input-mini">
            <label for="phoneNumber"><fmt:message key="label.phone.number" />:</label>
            <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" value="${currentPhoneNumber}" placeholder="+77001234567" required>
        </div>
        <c:if test="${not empty errorInvalidPhoneNumber}">
            <div class="alert alert-danger" role="alert">
                    ${errorInvalidPhoneNumber}
            </div>
        </c:if>
        <div class="form-group input-mini">
            <label for="email"><fmt:message key="label.email" />:</label>
            <input type="email" class="form-control" id="email" name="email" value="${currentEmail}" placeholder="example@email.com" required/>
        </div>
        <c:if test="${not empty errorInvalidEmail}">
            <div class="alert alert-danger" role="alert">
                    ${errorInvalidEmail}
            </div>
        </c:if>
        <button type="submit" class="btn btn-primary"><fmt:message key="label.edit" /></button>
    </form>
    </c:if>
</div>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8" />
</body>
</html>
