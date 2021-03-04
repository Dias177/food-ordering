<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/18/21
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
    <head>
        <title>${food.name} <fmt:message key="label.info" /></title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
    </head>
    <body>
        <div class="container">
            <h3>${food.name} <fmt:message key="label.info" /></h3>
            <div class="card text-center mx-auto" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${food.name}</h5>
                    <c:forEach var="foodCategory" items="${foodCategories}">
                        <c:if test="${foodCategory.id = food.foodCategoryId}">
                            <p class="card-text">${foodCategory.name}</p>
                        </c:if>
                    </c:forEach>
                    <p class="card-text">${food.description}</p>
                    <p class="card-text"><fmt:formatNumber value="${food.price}" type="currency" currencySymbol="KZT" /></p>
                    <c:if test="${userRole eq 'CUSTOMER'}">
                        <a href="${pageContext.request.contextPath}/controller?command=add_food_to_cart&food_id=${food.id}" class="btn btn-info btn-sm" role="button"><fmt:message key="label.add.to.cart" /></a>
                    </c:if>
                    <c:if test="${userRole eq 'ADMIN'}">
                        <a href="${pageContext.request.contextPath}/controller?command=show_editing_food&food_id=${food.id}" class="btn btn-info btn-sm" role="button"><fmt:message key="label.edit" /></a>
                        <a href="${pageContext.request.contextPath}/controller?command=delete_food&food_id=${food.id}" class="btn btn-danger btn-sm" role="button"><fmt:message key="label.remove" /></a>
                    </c:if>
                </div>
            </div>
        </div>
        <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
    </body>
</html>
