<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/25/21
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.food.editing" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8" />
</head>
<body>
<div class="container">
    <h3><fmt:message key="label.food.editing" /></h3>
    <c:if test="${isSuccessEditFood}">
        <div class="alert alert-success text-center" role="alert">
                <fmt:message key="message.edit.food.success" />
        </div>
    </c:if>
    <c:if test="${not isSuccessEditFood}">
    <form class="text-center" name="editFoodForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="edit_food" />
        <input type="hidden" name="foodId" value="${foodId}" />
        <div class="form-group input-mini">
            <label for="foodName"><fmt:message key="label.food.name" />:</label>
            <input type="text" class="form-control" id="foodName" name="foodName" value="${currentFoodName}" autofocus required>
        </div>
        <c:if test="${isErrorInvalidFoodName}">
            <div class="alert alert-danger" role="alert">
                    <fmt:message key="message.food.name.error" />
            </div>
        </c:if>
        <div class="form-group input-mini">
            <label for="foodCategory"><fmt:message key="label.category" />:</label>
            <select class="custom-select custom-select-sm" id="foodCategory" name="foodCategoryId" required>
                <option value="${foodCategories[currentFoodCategoryId - 1].id}" selected>${foodCategories[currentFoodCategoryId - 1].name}</option>
                <c:forEach var="fc" items="${foodCategories}" varStatus="statusOrderStatus">
                    <c:if test="${statusOrderStatus.count ne currentFoodCategoryId}">
                        <option value="${fc.id}">${fc.name}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <div class="form-group input-mini">
            <label for="foodDescription"><fmt:message key="label.description" />:</label>
            <input type="text" class="form-control" id="foodDescription" name="foodDescription" value="${currentFoodDescription}">
        </div>
        <div class="form-group input-mini">
            <label for="foodPrice"><fmt:message key="label.price" />, KZT:</label>
            <input type="number" class="form-control" id="foodPrice" name="foodPrice" min="0" step="0.01" value="${currentFoodPrice}" required>
        </div>
        <c:if test="${isErrorInvalidFoodPrice}">
            <div class="alert alert-danger" role="alert">
                    <fmt:message key="message.food.price.error" />
            </div>
        </c:if>
        <button type="submit" class="btn btn-primary"><fmt:message key="label.edit" /></button>
    </form>
    </c:if>
</div>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8" />
</body>
</html>
