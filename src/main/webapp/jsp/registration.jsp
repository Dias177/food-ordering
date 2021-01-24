<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/13/21
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
    <head>
        <title><fmt:message key="label.registration" /></title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
    </head>
    <body>
        <div class="container">
            <h3><fmt:message key="label.registration" /></h3>
        <form class="text-center" name="registrationForm" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="register"/>
            <div class="form-group input-mini">
                <label for="firstName"><fmt:message key="label.firstname" />:</label>
                <input type="text" class="form-control" id="firstName" name="firstName" value="" autofocus required>
            </div>
            <c:if test="${not empty errorInvalidFirstName}">
                <div class="alert alert-danger" role="alert">
                        ${errorInvalidFirstName}
                </div>
            </c:if>
            <div class="form-group input-mini">
                <label for="lastName"><fmt:message key="label.lastname" />:</label>
                <input type="text" class="form-control" id="lastName" name="lastName" value="" required>
            </div>
            <c:if test="${not empty errorInvalidLastName}">
                <div class="alert alert-danger" role="alert">
                        ${errorInvalidLastName}
                </div>
            </c:if>
            <div class="form-group input-mini">
                <label for="birthday"><fmt:message key="label.birthday" />:</label>
                <input type="date" class="form-control" id="birthday" name="birthday" value="">
            </div>
            <c:if test="${not empty errorInvalidBirthday}">
                <div class="alert alert-danger" role="alert">
                        ${errorInvalidBirthday}
                </div>
            </c:if>
            <div class="form-group input-mini">
                <label for="phoneNumber"><fmt:message key="label.phone.number" />:</label>
                <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" value="" placeholder="+77001234567" required>
            </div>
            <c:if test="${not empty errorInvalidPhoneNumber}">
                <div class="alert alert-danger" role="alert">
                        ${errorInvalidPhoneNumber}
                </div>
            </c:if>
            <div class="form-group input-mini">
                <label for="email"><fmt:message key="label.email" />:</label>
                <input type="email" class="form-control" id="email" name="email" value="" placeholder="example@email.com" required/>
            </div>
            <c:if test="${not empty errorInvalidEmail}">
                <div class="alert alert-danger" role="alert">
                        ${errorInvalidEmail}
                </div>
            </c:if>
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
            <button type="submit" class="btn btn-primary"><fmt:message key="label.register" /></button>
        </form>
        </div>
        <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
    </body>
</html>
