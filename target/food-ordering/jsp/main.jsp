<%--
  Created by IntelliJ IDEA.
  User: dias
  Date: 1/12/21
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />

<html>
<head>
    <title><fmt:message key="label.main" /></title>
    <c:import url="/jsp/partials/header.jsp" charEncoding="UTF-8"/>
</head>
<body>
<div class="container">
    <h3><fmt:message key="label.welcome" />!</h3>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc in pretium dolor, sed convallis ipsum. Aliquam at efficitur elit. Sed luctus ante diam, nec laoreet eros tincidunt a. Proin auctor ex tellus, sit amet viverra metus dignissim id. Praesent consequat turpis ut quam rutrum consequat. Fusce ac odio eget turpis porttitor tristique sed non magna. Morbi pharetra sollicitudin arcu, quis suscipit lectus commodo at. Curabitur nec odio sed urna lobortis viverra nec semper nunc. Quisque quis nisi sit amet mauris vestibulum rutrum. Integer at tempus turpis. In tincidunt sem et ligula posuere pharetra eget non metus.</p>
    <p>Duis tincidunt, enim in fringilla scelerisque, erat lacus mollis tortor, vel facilisis odio sapien eget nisi. Integer ornare scelerisque malesuada. Cras sodales placerat pellentesque. Donec sed libero non lectus varius pharetra. Proin accumsan libero tellus, in pulvinar justo accumsan eu. Sed libero diam, dignissim a lacinia vel, aliquam nec nisl. Morbi ante nibh, rutrum sit amet dui a, faucibus faucibus elit. Suspendisse quis viverra leo. Ut maximus purus ex, hendrerit imperdiet justo semper vulputate. Donec rutrum vestibulum ipsum, vitae ullamcorper mauris auctor vel. Phasellus porttitor consequat cursus. In hac habitasse platea dictumst. Nunc sit amet viverra lectus, vel sollicitudin nulla. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse eu rutrum eros.</p>
</div>
<c:import url="/jsp/partials/footer.jsp" charEncoding="UTF-8"/>
</body>
</html>
