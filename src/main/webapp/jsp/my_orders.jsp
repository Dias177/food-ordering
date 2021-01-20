<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/20/21
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>My Orders</title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
    </head>
    <body>
        <div class="container">
            <div class="row row-cols-1 row-cols-md-3">
                <c:forEach var="order" items="${orders}" varStatus="statusOrder">
                    <div class="col mb-4">
                        <div class="card h-100 text-center" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title">Order #${statusOrder.count}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${orderStatuses[order.key.orderStatusId - 1].name}</h6>
                                <h6 class="card-subtitle mb-2 text-muted"><c:out value="${order.key.price}" /></h6>
                                <h6 class="card-subtitle mb-2 text-muted"><c:out value="${order.key.date}" /></h6>
                                <c:forEach var="orderDetail" items="${order.value}" varStatus="statusOrderDetail">
                                    <p class="card-text">${statusOrderDetail.count}. Quantity of ${foods[orderDetail.foodId - 1].name} - ${orderDetail.quantity}. Price - ${orderDetail.price}</p>
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
