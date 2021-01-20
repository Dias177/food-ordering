<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/16/21
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Add Food Category</title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
    </head>
    <body>
    <div class="container">
        <form class="text-center" name="addFoodCategoryForm" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="add_food_category"/>
            <c:if test="${not empty successAddFoodCategoryName}">
            <div class="alert alert-success" role="alert">
                ${successAddFoodCategoryName}
            </div>
            </c:if>
            <div class="form-group input-mini">
            <label for="foodCategoryName">Name For Food Category:</label>
            <input type="text" class="form-control" id="foodCategoryName" name="foodCategoryName" value="" autofocus required>
            </div>
            <c:if test="${not empty errorInvalidFoodCategoryName}">
                <div class="alert alert-danger" role="alert">
                        ${errorInvalidFoodCategoryName}
                </div>
            </c:if>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>
    <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
    </body>
</html>
