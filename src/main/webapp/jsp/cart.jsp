<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/18/21
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Cart</title>
        <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
    </head>
    <body onload="calculateAndSetTotalPrice()">
        <div class="container">
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
                            <th scope="col">Name</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
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
                <p id="totalPrice" class="text-left"><strong>TOTAL PRICE: </strong></p>
                <button type="submit" class="btn btn-primary">Order</button>
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
                console.log(rows);
                const len = rows.length;
                const cellNum = 3;
                let totalPrice = 0.0;
                for (let i = 1; i < len; i++) {
                    const cell = rows[i].cells[cellNum];
                    console.log(cell.innerHTML);
                    totalPrice += Number(cell.innerHTML);
                }
                document.getElementById('totalPrice').innerHTML = '<strong>TOTAL PRICE: ' + totalPrice.toString() + '</strong>';
            }
        </script>
    </body>
</html>
