<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/16/21
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
    <head>
        <title><fmt:message key="label.add.food" /></title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
    </head>
    <body>
    <div class="container">
        <h3><fmt:message key="label.add.food" /></h3>
        <form class="text-center" name="addFoodForm" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="add_food"/>
            <c:if test="${isSuccessAddFood}">
                <div class="alert alert-success" role="alert">
                        <fmt:message key="message.add.food.success" />
                </div>
            </c:if>
            <div class="form-group input-mini">
            <label for="foodName"><fmt:message key="label.food.name" />:</label>
            <input type="text" class="form-control" id="foodName" name="foodName" value="" autofocus required>
            </div>
            <c:if test="${isErrorInvalidFoodName}">
                <div class="alert alert-danger" role="alert">
                        <fmt:message key="message.food.name.error" />
                </div>
            </c:if>
            <div class="form-group input-mini">
            <label for="foodCategory"><fmt:message key="label.category" />:</label>
                <select class="custom-select custom-select-sm" id="foodCategory" name="foodCategory" required>
                    <c:forEach var="foodCategory" items="${foodCategories}">
                        <option value="${foodCategory.id}">${foodCategory.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group input-mini">
            <label for="foodDescription"><fmt:message key="label.description" />:</label>
            <input type="text" class="form-control" id="foodDescription" name="foodDescription" value="">
            </div>
            <div class="form-group input-mini">
            <label for="foodPrice"><fmt:message key="label.price" />, KZT:</label>
            <input type="number" class="form-control" id="foodPrice" name="foodPrice" min="0" step="0.01" value="" required>
            </div>
            <c:if test="${isErrorInvalidFoodPrice}">
                <div class="alert alert-danger" role="alert">
                        <fmt:message key="message.food.price.error" />
                </div>
            </c:if>
            <button type="submit" class="btn btn-primary"><fmt:message key="label.add" /></button>
        </form>
    </div>
    <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
    </body>
</html>
