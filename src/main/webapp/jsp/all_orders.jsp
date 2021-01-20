<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/20/21
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>All Orders</title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
    </head>
    <body>
        <div class="container">
            <c:if test="${not empty successEditingOrderStatus}">
                <div class="alert alert-success" role="alert">
                    ${successEditingOrderStatus}
                </div>
            </c:if>
            <div class="row row-cols-1 row-cols-md-3">
                <c:forEach var="order" items="${orders}" varStatus="statusOrder">
                    <form name="editOrderStatusForm" method="POST" action="${pageContext.request.contextPath}/controller?command=edit_order_status">
                    <input type="hidden" name="orderId" value="${order.key.id}"/>
                        <div class="col mb-4">
                        <div class="card h-100 text-center" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title">Order #${order.key.id}</h5>
                                <select class="custom-select custom-select-sm" name="orderStatusId${order.key.id}">
                                    <option value="${orderStatuses[order.key.orderStatusId - 1].id}" selected>${orderStatuses[order.key.orderStatusId - 1].name}</option>
                                    <c:forEach var="orderStatus" items="${orderStatuses}" varStatus="statusOrderStatus">
                                        <c:if test="${statusOrderStatus.count ne order.key.orderStatusId}">
                                            <option value="${orderStatus.id}">${orderStatus.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
<%--                                <h6 class="card-subtitle mb-2 text-muted">${orderStatuses[order.key.orderStatusId - 1].name}</h6>--%>
                                <h6 class="card-subtitle mb-2 text-muted"><c:out value="${order.key.price}" /></h6>
                                <h6 class="card-subtitle mb-2 text-muted"><c:out value="${order.key.date}" /></h6>
                                <c:forEach var="orderDetail" items="${order.value}" varStatus="statusOrderDetail">
                                    <p class="card-text">${statusOrderDetail.count}. Quantity of ${foods[orderDetail.foodId - 1].name} - ${orderDetail.quantity}. Price - ${orderDetail.price}</p>
                                </c:forEach>
                                <button type="submit" class="btn btn-primary">Edit</button>
                            </div>
                        </div>
                    </div>
                    </form>
                </c:forEach>
            </div>
        </div>
        <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
    </body>
</html>
