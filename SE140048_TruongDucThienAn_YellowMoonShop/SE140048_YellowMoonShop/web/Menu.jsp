<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of menu-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="HomeServlet">Home</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- session attribute of admin and user login -->
        <c:set value="${sessionScope.account}" var="account"></c:set>

            <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                <ul class="navbar-nav m-auto">

                    <!-- when admin login -->
                <c:if test="${not empty account && account.roleID == 1}">
                    <li class="nav-item">
                        <a class="nav-link" href="ManageProductServlet">Manager Product</a>
                    </li>
                </c:if>
                <c:if test="${not empty account}">
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">Hello ${account.fullname}</a>
                    </li>
                </c:if>
                <c:if test="${not empty account && account.roleID == 2}">
                    <li class="nav-item">
                        <a class="nav-link disabled" href="loadOrderTracking.jsp">Order Tracking</a>
                    </li>
                </c:if>
                <c:if test="${empty account}">
                    <li class="nav-item">
                        <a class="nav-link" href="Login.jsp">Login</a>
                    </li>
                </c:if>
            </ul>

            <form action="search" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input name="name" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <c:set var="order" value="${order}"/>
                <a class="btn btn-success btn-sm ml-3" href="viewCart">
                    <i class="fa fa-shopping-cart"></i> Cart
                    <span class="badge badge-light">${fn:length(order.orderDetail)}</span>
                </a>
            </form>
        </div>
    </div>
</nav>
<!--end of menu-->
