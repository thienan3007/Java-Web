<%-- 
    Document   : Cart
    Created on : Oct 31, 2020, 9:42:21 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ Hàng</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>

    <body>
        <c:if test="${not empty QUANTITY_ERROR}">
            <script>
                    var message = "Quantity of this cake is just ${QUANTITY_ERROR}!!!!!!";
                    alert(message);
            </script>
        </c:if>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">
                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="p-2 px-3 text-uppercase">Sản Phẩm</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Đơn Giá</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Số Lượng</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Xóa</div>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <!-- order list -->
                                            <c:forEach items="${order.orderDetail}" var="o">
                                                <c:url value="detail?cakesId=${o.cakeDTO.cakesID}" var="detail"/>
                                                <tr>
                                                    <th scope="row">
                                                        <div class="p-2">
                                                            <a href="${detail}">
                                                                <img src="${o.cakeDTO.image}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                            </a>
                                                            <div class="ml-3 d-inline-block align-middle">
                                                                <h5 class="mb-0"> <a href="${detail}" class="text-dark d-inline-block">${o.cakeDTO.name}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </th>
                                                    <td class="align-middle"><strong>${o.cakeDTO.price * o.quantity}</strong></td>
                                                    <td class="align-middle">
                                                        <a href="sub?id=${o.cakeDTO.cakesID}"><button class="btnSub">-</button></a> 
                                                        <strong>${o.quantity}</strong>
                                                        <a href="add?id=${o.cakeDTO.cakesID}"><button class="btnAdd">+</button></a>
                                                    </td>
                                                    <td class="align-middle"><a href="remove?id=${o.cakeDTO.cakesID}" class="text-dark">
                                                            <button type="button" class="btn btn-danger">Delete</button>
                                                        </a>
                                                    </td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- End -->
                            </div>
                        </div>

                        <!-- customer information -->
                        <form action="order" method="POST">
                            <div class="row py-5 p-4 bg-white rounded shadow-sm">
                                <c:set value="${FORM_ERRORS}" var="errors"/>
                                <div class="col-lg-7">
                                    <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold" style="text-align: center">Thông Tin Khách Hàng</div>
                                    <div class="p-4">   
                                        <c:if test="${empty sessionScope.account}">
                                            <div class="input-group mb-4 border rounded-pill p-2">
                                                <input name="name" value="${param.name}" type="text" placeholder="Nhập Tên *" aria-describedby="button-addon3" class="form-control border-0">               
                                            </div>
                                            <c:if test="${not empty errors.nameError}">
                                                <span>
                                                    <font color="red"> 
                                                    ${errors.nameError}
                                                    </font>
                                                </span>
                                            </c:if>
                                            <div class="input-group mb-4 border rounded-pill p-2">
                                                <input name="phone" value="${param.phone}" type="text" placeholder="Nhập Số Điện Thoại *" aria-describedby="button-addon3" class="form-control border-0">               
                                            </div>
                                            <c:if test="${not empty errors.phoneError}">
                                                <span>
                                                    <font color="red"> 
                                                    ${errors.phoneError}
                                                    </font>
                                                </span>
                                            </c:if>
                                            <div class="input-group mb-4 border rounded-pill p-2">
                                                <input name="address" value="${param.address}" type="text" placeholder="Nhập Địa Chỉ *" aria-describedby="button-addon3" class="form-control border-0">               
                                            </div>
                                            <c:if test="${not empty errors.addressError}" >
                                                <span>
                                                    <font color="red"> 
                                                    ${errors.addressError}
                                                    </font>
                                                </span>
                                            </c:if>
                                            <div class="input-group mb-4 border rounded-pill p-2">
                                                <input name="email" value="${param.email}" type="text" placeholder="Nhập Email *" aria-describedby="button-addon3" class="form-control border-0">               
                                            </div>
                                            <c:if test="${not empty errors.email}" >
                                                <span>
                                                    <font color="red"> 
                                                    ${errors.email}
                                                    </font>
                                                </span>
                                            </c:if>
                                        </c:if>
                                        <select name="payment" class="input-group mb-4 border rounded-pill p-2">
                                            <option value="1"> Cash </option>
                                            <option value="2"> Banking </option>
                                        </select>
                                        <input type="submit" value="Mua hàng" class="btn btn-dark rounded-pill py-2 btn-block"/>
                                    </div>
                                </div>
                                <div class="col-lg-5">
                                    <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành tiền</div>
                                    <div class="p-4">
                                        <ul class="list-unstyled mb-4">
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng tiền hàng</strong><strong>${total} &#8363;</strong></li>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">VAT</strong><strong>${VAT} &#8363;</strong></li>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng thanh toán</strong>
                                                <h5 class="font-weight-bold">${sum} &#8363;</h5>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>

</html>
</html>
