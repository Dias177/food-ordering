<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/20/21
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
    <head>
        <title><fmt:message key="label.my.orders" /></title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
    </head>
    <body>
        <div class="container">
            <h3><fmt:message key="label.my.orders" /></h3>
            <c:if test="${empty orders}">
                <div class="alert alert-info text-center" role="alert">
                    <fmt:message key="message.no.orders" />
                </div>
            </c:if>
            <div class="row row-cols-1 row-cols-md-3">
                <c:forEach var="order" items="${orders}" varStatus="statusOrder">
                    <div class="col mb-4">
                        <div class="card h-100 text-center" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title"><fmt:message key="label.order" /> #${statusOrder.count}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${orderStatuses[order.key.orderStatusId - 1].name}</h6>
                                <c:set var="orderPrice" value="${order.key.price}" />
                                <c:set var="orderDate" value="${order.key.date}" />
                                <h6 class="card-subtitle mb-2 text-muted"><fmt:formatNumber value="${orderPrice}" type="currency" currencySymbol="KZT" /></h6>
                                <h6 class="card-subtitle mb-2 text-muted"><fmt:formatDate value="${orderDate}" type="both" /></h6>
                                <c:forEach var="orderDetail" items="${order.value}" varStatus="statusOrderDetail">
                                    <c:set var="orderDetailPrice" value="${orderDetail.price}" />
                                    <c:set var="foodItemPrice" value="${foods[orderDetail.foodId - 1].price}" />
                                    <p class="card-text">${statusOrderDetail.count}. ${foods[orderDetail.foodId - 1].name}: ${orderDetail.quantity}x<fmt:formatNumber value="${foodItemPrice}" type="number" />=<fmt:formatNumber value="${orderDetailPrice}" type="currency" currencySymbol="KZT"/></p>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
    </body>
</html>
