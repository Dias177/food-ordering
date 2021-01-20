<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/12/21
  Time: 00:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Food Ordering</title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
    </head>
    <body>
        <div class="container">
                <form class="text-center" name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="login"/>
                    <div class="form-group input-mini">
                        <label for="login">Email:</label>
                        <input type="email" class="form-control" id="login" name="login" value="" autofocus required/>
                    </div>
                    <div class="form-group input-mini">
                        <label for="password">Password:</label>
                        <input type="password" class="form-control" id="password" name="password" value="" required/>
                    </div>
                    <c:if test="${not empty errorLoginPassMessage}">
                        <div class="alert alert-danger" role="alert">
                            ${errorLoginPassMessage}
                        </div>
                    </c:if>
                    <button type="submit" class="btn btn-primary">Login</button>
                    <p>Not registered? <a href="${pageContext.request.contextPath}/controller?command=show_registration">Create an account</a></p>
                </form>
        </div>
        <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
    </body>
</html>
