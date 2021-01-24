<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/12/21
  Time: 00:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
    <head>
        <title><fmt:message key="label.title" /></title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8" />
    </head>
    <body>
        <c:if test="${not empty userRole}">
            <c:redirect url="/controller?command=show_main"/>
        </c:if>
        <div class="container">
                <form class="text-center" name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="login" />
                    <div class="form-group input-mini">
                        <label for="login"><fmt:message key="label.email" />:</label>
                        <input type="email" class="form-control" id="login" name="login" value="" autofocus required />
                    </div>
                    <div class="form-group input-mini">
                        <label for="password"><fmt:message key="label.password" />:</label>
                        <input type="password" class="form-control" id="password" name="password" value="" required />
                    </div>
                    <c:if test="${not empty errorLoginPassMessage}">
                        <div class="alert alert-danger" role="alert">
                            ${errorLoginPassMessage}
                        </div>
                    </c:if>
                    <button type="submit" class="btn btn-primary"><fmt:message key="label.login" /></button>
                    <p><fmt:message key="label.not.registered" />? <a href="${pageContext.request.contextPath}/controller?command=show_registration"><fmt:message key="label.create.an.account" /></a></p>
                </form>
        </div>
        <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8" />
    </body>
</html>
