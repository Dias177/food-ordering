<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 12.02.2021
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.delete.user" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
</head>
<body>
<div class="container">
    <h3><fmt:message key="label.delete.user" /></h3>
    <c:choose>
        <c:when test="${isSuccessDeleteUser}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="message.delete.user.success" />
            </div>
        </c:when>
        <c:when test="${isErrorDeleteUser}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="message.delete.user.error" />
            </div>
        </c:when>
        <c:otherwise>
            <label for="sortSelect" class="text-muted"><fmt:message key="label.sort.by" />: </label>
            <select class="custom-select custom-select-sm" id="sortSelect" name="sortSelect" onchange="sortUsers(this.value)">
                <option disabled selected value><fmt:message key="label.select.option" /></option>
                <option value="name"><fmt:message key="label.name" /></option>
                <option value="birthday"><fmt:message key="label.birthday" /></option>
            </select>
            <div class="row row-cols-1 row-cols-md-3">
                <c:forEach var="user" items="${users}">
                    <div class="col mb-4">
                        <div class="card h-100 text-center" style="width: 18rem;">
                            <ctg:user-card-body user="${user}" type="delete">
                                <fmt:message key="label.remove" />
                            </ctg:user-card-body>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
<script type="text/javascript">
    function sortUsers(sortBy)
    {
        window.location.href = "http://localhost:8080${pageContext.request.contextPath}/controller?command=sort_users&sort_by=" + sortBy;
    }
</script>
</body>
</html>
