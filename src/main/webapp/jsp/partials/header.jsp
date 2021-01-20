<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/18/21
  Time: 01:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resource.pagecontent" var="rb" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<nav class="navbar navbar-expand-lg bg-light navbar-light">
    <div class="container">
        <a class="navbar-brand" href="#">Food Ordering</a>
        <c:if test="${not empty userRole}">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_main">Main</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_menu">Menu</a>
                </li>
                <c:if test="${userRole == 'ADMIN'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop1" data-toggle="dropdown">
                            Add
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_adding_food_category">Add Food Category</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_adding_food">Add Food</a>
                        </div>
                    </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_all_orders">Orders</a>
                </li>
                </c:if>
            </ul>
            <ul class="navbar-nav ml-auto">
                <c:if test="${userRole == 'CUSTOMER'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_cart">Go to Cart</a>
                </li>
                </c:if>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">
                        Profile
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_my_profile">My Profile</a>
                        <c:if test="${userRole == 'CUSTOMER'}">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_my_orders">My Orders</a>
                        </c:if>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=logout">Logout</a>
                    </div>
                </li>
            </ul>
        </c:if>
        <c:if test="${empty userRole}">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_registration">Sign Up</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_login">Login</a>
                </li>
            </ul>
        </c:if>
    </div>
</nav>
