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
    </head>
    <body>
        <div class="container">
            <div class="row row-cols-1 row-cols-md-3">
                <c:forEach var="foodItem" items="${foodItems}">
                    <div class="col mb-4">
                        <div class="card h-100 text-center" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title"><c:out value="${foodItem.name}" /></h5>
                                <h6 class="card-subtitle mb-2 text-muted"><c:out value="${foodItem.price}" /></h6>
                                <p class="card-text"><c:out value="${foodItem.description}" /></p>
                                <a href="${pageContext.request.contextPath}/controller?command=show_food_detail&food_id=${foodItem.id}" class="btn btn-info btn-sm" role="button"><fmt:message key="label.get.info" /></a>
                                <a href="${pageContext.request.contextPath}/controller?command=add_food_to_cart&food_id=${foodItem.id}" class="btn btn-info btn-sm" role="button"><fmt:message key="label.add.to.cart" /></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
    </body>
</html>