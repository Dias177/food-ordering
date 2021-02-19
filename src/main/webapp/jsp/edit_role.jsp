<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 19.02.2021
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <title><fmt:message key="label.edit.role" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
</head>
<body>
<div class="container">
    <h3><fmt:message key="label.edit.role" /></h3>
    <c:choose>
        <c:when test="${isSuccessEditRole}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="message.edit.role.success" />
            </div>
        </c:when>
        <c:when test="${isErrorEditRole}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="message.edit.role.error" />
            </div>
        </c:when>
        <c:otherwise>
            <form class="text-center" name="editRolesForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="edit_role"/>
                <div class="form-group input-mini">
                    <label for="roleId"><fmt:message key="label.role" />:</label>
                    <select class="custom-select custom-select-sm" id="roleId" name="roleId" onchange="changeRoleName()" autofocus required>
                        <c:forEach var="role" items="${roles}">
                            <option value="${role.id}">${role.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group input-mini">
                    <label for="roleName"><fmt:message key="label.name" />:</label>
                    <input type="text" class="form-control" id="roleName" name="roleName" value="" required>
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="label.edit" /></button>
            </form>
        </c:otherwise>
    </c:choose>
</div>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
<script type="text/javascript">
    function changeRoleName()
    {
        const roleIdElement = document.getElementById("roleId");
        const selectedRoleName = roleIdElement.options[roleIdElement.selectedIndex].text;
        const roleNameElement = document.getElementById("roleName");
        roleNameElement.value = selectedRoleName;
    }
</script>
</body>
</html>
