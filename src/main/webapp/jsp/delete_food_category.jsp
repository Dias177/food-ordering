<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 12.02.2021
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.delete.food.category" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
</head>
<body>
<div class="container">
    <h3><fmt:message key="label.delete.food.category" /></h3>
        <c:choose>
            <c:when test="${isSuccessDeleteFoodCategory}">
                <div class="alert alert-success" role="alert">
                    <fmt:message key="message.delete.food.category.success" />
                </div>
            </c:when>
            <c:when test="${isErrorDeleteFoodCategory}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="message.delete.food.category.error" />
                </div>
            </c:when>
            <c:otherwise>
                <form class="text-center" name="deleteFoodCategoryForm" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="delete_food_category"/>
                    <div class="form-group input-mini">
                    <label for="foodCategory"><fmt:message key="label.category" />:</label>
                    <select class="custom-select custom-select-sm" id="foodCategory" name="foodCategory" autofocus required>
                        <c:forEach var="foodCategory" items="${foodCategories}">
                            <option value="${foodCategory.id}">${foodCategory.name}</option>
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
