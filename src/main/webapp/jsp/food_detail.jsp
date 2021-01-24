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
<%--    TODO: Change to card?--%>
        <div class="container text-center">
            <h1>${food.name}</h1>
            <p>${food.foodCategoryId}</p>
            <p>${food.description}</p>
            <p>${food.price}</p>
            <a href="${pageContext.request.contextPath}/controller?command=add_food_to_cart&food_id=${food.id}" class="btn btn-info btn-sm" role="button"><fmt:message key="label.add.to.cart" /></a>
        </div>
        <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
    </body>
</html>
