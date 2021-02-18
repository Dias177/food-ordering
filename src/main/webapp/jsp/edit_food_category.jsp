<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 18.02.2021
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.edit.food.category" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
</head>
<body>
<div class="container">
<h3><fmt:message key="label.edit.food.category" /></h3>
    <c:choose>
        <c:when test="${isSuccessEditFoodCategory}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="message.edit.food.category.success" />
            </div>
        </c:when>
        <c:when test="${isErrorEditFoodCategory}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="message.edit.food.category.error" />
            </div>
        </c:when>
        <c:otherwise>
            <form class="text-center" name="editFoodCategoryForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="edit_food_category"/>
                <div class="form-group input-mini">
                    <label for="foodCategoryId"><fmt:message key="label.category" />:</label>
                    <select class="custom-select custom-select-sm" id="foodCategoryId" name="foodCategoryId" onchange="changeFoodCategoryName()" autofocus required>
                        <c:forEach var="foodCategory" items="${foodCategories}">
                            <option value="${foodCategory.id}">${foodCategory.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group input-mini">
                    <label for="foodCategoryName"><fmt:message key="label.name.for.food.category" />:</label>
                    <input type="text" class="form-control" id="foodCategoryName" name="foodCategoryName" value="" required>
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="label.edit" /></button>
            </form>
        </c:otherwise>
    </c:choose>
</div>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
<script type="text/javascript">
    function changeFoodCategoryName()
    {
        const foodCategoryIdElement = document.getElementById("foodCategoryId");
        const selectedFoodCategoryName = foodCategoryIdElement.options[foodCategoryIdElement.selectedIndex].text;
        const foodCategoryNameElement = document.getElementById("foodCategoryName");
        foodCategoryNameElement.value = selectedFoodCategoryName;
    }
</script>
</body>
</html>
