<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/18/21
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
    <head>
        <title><fmt:message key="label.cart" /></title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
    </head>
    <body onload="calculateAndSetTotalPrice()">
        <div class="container">
            <h3><fmt:message key="label.cart" /></h3>
            <c:if test="${not empty cart}">
                <c:if test="${not empty errorInvalidOrderFoodQuantity}">
                    <div class="alert alert-danger" role="alert">
                        ${errorInvalidOrderFoodQuantity}
                    </div>
                </c:if>
            <form class="text-center" name="orderFoodForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="order_food"/>
                <table id="orderTable" class="table table-bordered table-hover">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><fmt:message key="label.name" /></th>
                            <th scope="col"><fmt:message key="label.quantity" /></th>
                            <th scope="col"><fmt:message key="label.price" />, KZT</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cartItem" items="${cart}" varStatus="status">
                            <tr>
                                <th scope="row"><c:out value="${status.count}" /></th>
                                <td><c:out value="${cartItem.name}" /></td>
                                <td><input type="number" class="form-control" id="foodQuantity${status.count}" onchange="changePrice(this.value, ${cartItem.price}, ${status.count})" name="foodQuantity${status.count}" min="1" value="1" required/></td>
                                <td id="foodPrice${status.count}"><c:out value="${cartItem.price}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <p class="text-left"><fmt:message key="label.total.price" />, KZT: <strong id="totalPrice"></strong></p>
                <button type="submit" class="btn btn-primary"><fmt:message key="label.order.action" /></button>
            </form>
            </c:if>
                <c:if test="${empty cart}">
                    <div class="alert alert-info text-center" role="alert">
                        ${cartEmpty}
                    </div>
                </c:if>
        </div>

        <c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
        <script type="text/javascript">
            function changePrice(quantity, price, id)
            {
                document.getElementById('foodPrice' + id).innerHTML = (price * quantity).toString();
                calculateAndSetTotalPrice();
            }
        </script>
        <script type="text/javascript">
            function calculateAndSetTotalPrice()
            {
                const rows = document.getElementById('orderTable').rows;
                const len = rows.length;
                const cellNum = 3;
                let totalPrice = 0.0;
                for (let i = 1; i < len; i++) {
                    const cell = rows[i].cells[cellNum];
                    totalPrice += Number(cell.innerHTML);
                }
                document.getElementById('totalPrice').innerHTML = totalPrice.toString();
            }
        </script>
    </body>
</html>
