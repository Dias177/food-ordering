<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 19.02.2021
  Time: 00:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.edit.order.status" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
</head>
<body>
<div class="container">
    <h3><fmt:message key="label.edit.order.status" /></h3>
    <c:choose>
        <c:when test="${isSuccessEditOrderStatus}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="message.edit.order.status.success" />
            </div>
        </c:when>
        <c:when test="${isErrorEditOrderStatus}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="message.edit.order.status.error" />
            </div>
        </c:when>
        <c:otherwise>
            <form class="text-center" name="editOrderStatusForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="edit_order_status"/>
                <div class="form-group input-mini">
                    <label for="orderStatusId"><fmt:message key="label.order.status" />:</label>
                    <select class="custom-select custom-select-sm" id="orderStatusId" name="orderStatusId" onchange="changeOrderStatusName()" autofocus required>
                        <c:forEach var="orderStatus" items="${orderStatuses}">
                            <option value="${orderStatus.id}">${orderStatus.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group input-mini">
                    <label for="orderStatusName"><fmt:message key="label.name.for.order.status" />:</label>
                    <input type="text" class="form-control" id="orderStatusName" name="orderStatusName" value="" required>
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="label.edit" /></button>
            </form>
        </c:otherwise>
    </c:choose>
</div>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
<script type="text/javascript">
    function changeOrderStatusName()
    {
        const orderStatusIdElement = document.getElementById("orderStatusId");
        const selectedOrderStatusName = orderStatusIdElement.options[orderStatusIdElement.selectedIndex].text;
        const orderStatusNameElement = document.getElementById("orderStatusName");
        orderStatusNameElement.value = selectedOrderStatusName;
    }
</script>
</body>
</html>
