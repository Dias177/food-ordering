<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/18/21
  Time: 01:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<nav class="navbar navbar-expand-lg bg-light navbar-light">
    <div class="container">
        <a class="navbar-brand" href="#"><fmt:message key="label.title" /></a>
            <ul class="navbar-nav">
                <c:if test="${not empty userRole}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_main"><fmt:message key="label.main" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_menu"><fmt:message key="label.menu" /></a>
                </li>
                <c:if test="${userRole eq 'ADMIN'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop1" data-toggle="dropdown">
                            <fmt:message key="label.add" />
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_adding_food_category"><fmt:message key="label.add.food.category" /></a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_adding_food"><fmt:message key="label.add.food" /></a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_adding_order_status"><fmt:message key="label.add.order.status" /></a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">
                            <fmt:message key="label.edit" />
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_editing_food_category"><fmt:message key="label.edit.food.category" /></a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_editing_order_status"><fmt:message key="label.edit.order.status" /></a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop3" data-toggle="dropdown">
                            <fmt:message key="label.remove" />
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_delete_food_category"><fmt:message key="label.delete.food.category" /></a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_delete_order_status"><fmt:message key="label.delete.order.status" /></a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_delete_user"><fmt:message key="label.delete.user" /></a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_delete_role"><fmt:message key="label.delete.role" /></a>
                        </div>
                    </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_all_orders"><fmt:message key="label.orders" /></a>
                </li>
                </c:if>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=change_to_english_locale">EN</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=change_to_russian_locale">РУС</a>
                </li>
                <c:if test="${userRole eq 'CUSTOMER'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_cart"><fmt:message key="label.go.to.cart" /></a>
                </li>
                </c:if>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop4" data-toggle="dropdown">
                        <fmt:message key="label.profile" />
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_my_profile"><fmt:message key="label.my.profile" /></a>
                        <c:if test="${userRole eq 'CUSTOMER'}">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_my_orders"><fmt:message key="label.my.orders" /></a>
                        </c:if>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=show_editing_profile"><fmt:message key="label.settings" /></a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="label.logout" /></a>
                    </div>
                </li>
            </ul>
        </c:if>
        <c:if test="${empty userRole}">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=change_to_english_locale">EN</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=change_to_russian_locale">РУС</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_registration"><fmt:message key="label.signup" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_login"><fmt:message key="label.login" /></a>
                </li>
            </ul>
        </c:if>
    </div>
</nav>
