<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/16/21
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
    <head>
        <title><fmt:message key="label.add.food.category" /></title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
    </head>
    <body>
    <div class="container">
        <h3><fmt:message key="label.add.food.category" /></h3>
        <form class="text-center" name="addFoodCategoryForm" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="add_food_category"/>
            <c:if test="${isSuccessAddFoodCategoryName}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="message.add.food.category.success" />
            </div>
            </c:if>
            <div class="form-group input-mini">
            <label for="foodCategoryName"><fmt:message key="label.name.for.food.category" />:</label>
            <input type="text" class="form-control" id="foodCategoryName" name="foodCategoryName" value="" autofocus required>
            </div>
            <c:if test="${isErrorInvalidFoodCategoryName}">
                <div class="alert alert-danger" role="alert">
                        <fmt:message key="message.food.category.name.error" />
                </div>
            </c:if>
            <button type="submit" class="btn btn-primary"><fmt:message key="label.add" /></button>
        </form>
    </div>
    <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
    </body>
</html>
