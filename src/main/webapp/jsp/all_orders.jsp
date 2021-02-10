<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/20/21
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
    <head>
        <title><fmt:message key="label.orders" /></title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
    </head>
    <body>
        <div class="container">
            <h3><fmt:message key="label.orders" /></h3>
            <c:if test="${isSuccessEditingOrderStatus}">
                <div class="alert alert-success" role="alert">
                    <fmt:message key="message.edit.order.status.success" />
                </div>
            </c:if>
            <c:if test="${isErrorEditingOrderStatus}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="message.edit.order.status.error" />
                </div>
            </c:if>
            <c:if test="${not (isSuccessEditingOrderStatus or isErrorEditingOrderStatus)}">
            <label for="sortSelect" class="text-muted"><fmt:message key="label.sort.by" />: </label>
            <select class="custom-select custom-select-sm" id="sortSelect" name="sortSelect" onchange="sortAllOrders(this.value)">
                <option disabled selected value><fmt:message key="label.select.option" /></option>
                <option value="date"><fmt:message key="label.date" /></option>
                <option value="price"><fmt:message key="label.price" /></option>
            </select>
            <div class="row row-cols-1 row-cols-md-3">
                <c:forEach var="order" items="${orders}" varStatus="statusOrder">
                    <form name="editOrderStatusForm" method="POST" action="${pageContext.request.contextPath}/controller?command=edit_order_status">
                    <input type="hidden" name="orderId" value="${order.key.id}"/>
                        <div class="col mb-4">
                        <div class="card h-100 text-center" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title"><fmt:message key="label.order" /> #${order.key.id}</h5>
                                <select class="custom-select custom-select-sm" name="orderStatusId${order.key.id}">
                                    <option value="${orderStatuses[order.key.orderStatusId - 1].id}" selected>${orderStatuses[order.key.orderStatusId - 1].name}</option>
                                    <c:forEach var="orderStatus" items="${orderStatuses}" varStatus="statusOrderStatus">
                                        <c:if test="${statusOrderStatus.count ne order.key.orderStatusId}">
                                            <option value="${orderStatus.id}">${orderStatus.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <h6 class="card-subtitle mb-2 text-muted">${users[statusOrder.count - 1].firstName} ${users[statusOrder.count - 1].lastName}</h6>
                                <h6 class="card-subtitle mb-2 text-muted"><fmt:formatNumber value="${order.key.price}" type="currency" currencySymbol="KZT" /></h6>
                                <h6 class="card-subtitle mb-2 text-muted"><fmt:formatDate value="${order.key.date}" type="both" /></h6>
                                <c:forEach var="orderDetail" items="${order.value}" varStatus="statusOrderDetail">
                                    <c:forEach var="food" items="${foods}">
                                        <c:if test="${orderDetail.foodId eq food.id}">
                                            <p class="card-text">${statusOrderDetail.count}. ${food.name}: ${orderDetail.quantity}x<fmt:formatNumber value="${food.price}" type="number" />=<fmt:formatNumber value="${orderDetail.price}" type="currency" currencySymbol="KZT"/></p>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                                <button type="submit" class="btn btn-primary"><fmt:message key="label.edit" /></button>
                            </div>
                        </div>
                    </div>
                    </form>
                </c:forEach>
            </div>
            </c:if>
        </div>
        <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
        <script type="text/javascript">
            function sortAllOrders(sortBy)
            {
                window.location.href = "http://localhost:8080${pageContext.request.contextPath}/controller?command=sort_all_orders&sort_by=" + sortBy;
            }
        </script>
    </body>
</html>
