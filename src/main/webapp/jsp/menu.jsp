<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/18/21
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
    <head>
        <title><fmt:message key="label.menu" /></title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
        <script type="text/javascript">
            function sortMenuItems(sortBy)
            {
                window.location.href = "http://localhost:8080${pageContext.request.contextPath}/controller?command=sort_menu_items&sort_by=" + sortBy + "&food_category_id=" + document.getElementById("categorySelect").value;
            }

            function showMenuItemsByCategory(foodCategoryId)
            {
                window.location.href = "http://localhost:8080${pageContext.request.contextPath}/controller?command=show_menu_items_by_category&food_category_id=" + foodCategoryId;
            }
            function showSuccessMessage()
            {
                document.getElementById('successAddFoodToCart').innerHTML='<div class="alert alert-success" role="alert"><fmt:message key="message.add.food.to.cart.success" /></div>';
                setTimeout(function() {document.getElementById('successAddFoodToCart').innerHTML='';}, 3000);
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h3><fmt:message key="label.menu" /></h3>
            <label for="sortSelect" class="text-muted"><fmt:message key="label.sort.by" />: </label>
            <select class="custom-select custom-select-sm" id="sortSelect" name="sortSelect" onchange="sortMenuItems(this.value)">
                <option disabled selected value><fmt:message key="label.select.option" /></option>
                <option value="name"><fmt:message key="label.name" /></option>
                <option value="price"><fmt:message key="label.price" /></option>
            </select>
            <label for="categorySelect" class="text-muted"><fmt:message key="label.category" />: </label>
            <select class="custom-select custom-select-sm" id="categorySelect" name="categorySelect" onchange="showMenuItemsByCategory(this.value)">
                <c:if test="${currentCategory ne 'All'}">
                    <option value="${currentCategory.id}" selected>${currentCategory.name}</option>
                    <option value="0">All</option>
                </c:if>
                <c:if test="${currentCategory eq 'All'}">
                    <option value="0" selected>${currentCategory}</option>
                </c:if>
                <c:forEach var="foodCategory" items="${foodCategories}">
                    <c:if test="${currentCategory ne foodCategory}">
                    <option value="${foodCategory.id}">${foodCategory.name}</option>
                    </c:if>
                </c:forEach>
            </select>
            <c:if test="${isSuccessAddFoodToCart}">
                <div id="successAddFoodToCart">
                    <script>showSuccessMessage();</script>
                </div>
            </c:if>
            <div class="row row-cols-1 row-cols-md-3">
                <c:forEach var="foodItem" items="${foodItems}">
                    <div class="col mb-4">
                        <div class="card h-100 text-center" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title"><c:out value="${foodItem.name}" /></h5>
                                <h6 class="card-subtitle mb-2 text-muted"><fmt:formatNumber value="${foodItem.price}" type="currency" currencySymbol="KZT" /></h6>
                                <p class="card-text"><c:out value="${foodItem.description}" /></p>
                                <a href="${pageContext.request.contextPath}/controller?command=show_food_detail&food_id=${foodItem.id}" class="btn btn-info btn-sm" role="button"><fmt:message key="label.get.info" /></a>
                                <c:if test="${userRole eq 'CUSTOMER'}">
                                    <a href="${pageContext.request.contextPath}/controller?command=add_food_to_cart&food_id=${foodItem.id}" class="btn btn-info btn-sm" role="button"><fmt:message key="label.add.to.cart" /></a>
                                </c:if>
                                <c:if test="${userRole eq 'ADMIN'}">
                                    <a href="${pageContext.request.contextPath}/controller?command=show_editing_food&food_id=${foodItem.id}" class="btn btn-info btn-sm" role="button"><fmt:message key="label.edit" /></a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
    </body>
</html>