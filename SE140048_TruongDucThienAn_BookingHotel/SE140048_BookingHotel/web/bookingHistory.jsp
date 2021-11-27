<%-- 
    Document   : Cart
    Created on : Oct 31, 2020, 9:42:21 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${not empty account}">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Giỏ Hàng</title>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
                  integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        </head>

        <body>
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
                                                        <div class="p-2 px-3 text-uppercase">Khách sạn</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="p-2 px-3 text-uppercase">Số Phòng</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Đơn Giá</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Ngày đặt phòng</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Action</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <a href="HomeShowHotelServlet"class="py-2 text-uppercase">Home</a>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <!-- order list -->
                                                <c:forEach items="${listBooking}" var="o">
                                                    <%--<c:url value="detail?cakesId=${o.cakeDTO.cakesID}" var="detail"/>--%>
                                                    <tr>
                                                        <th scope="row">
                                                            <div class="p-2">
                                                                <a href="${detail}">
                                                                    <img src="${o.hotelDTO.image}" alt="" width="70">
                                                                </a>
                                                                <div class="ml-3 d-inline-block align-middle">
                                                                    <h5 class="mb-0"> <a href="" class="text-dark d-inline-block">${o.hotelDTO.name}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <td class="align-middle"><strong>${o.roomAmount}</strong></td>
                                                        <td class="align-middle"><strong>${o.totalPrice}</strong></td>
                                                        <td class="align-middle">
                                                            <strong>${o.bookingDateString}</strong>
                                                        </td>
                                                        <td class="align-middle"><a href="checkout?bookingId=${o.id}" class="text-dark">
                                                                <c:if test="${o.bookingStatusID == 1}">
                                                                    <button type="button" class="btn btn-danger">Checkout</button>
                                                                </c:if>
                                                            </a>
                                                            <a href="delete?bookingId=${o.id}" class="text-dark">
                                                                <c:if test="${o.bookingStatusID == 2}">
                                                                    <button type="button" class="btn btn-block">Delete</button>
                                                                </c:if>
                                                            </a>
                                                        </td>
                                                    </tr> 
                                                </c:forEach>
                                                <c:if  test="${not empty BOOKING_HISTORY_ERROR}">
                                                <h1>no history</h1>
                                            </c:if>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- End -->
                                </div>
                            </div>
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
</c:if>
<c:if test="${empty account}">
    <jsp:include page="Login.jsp"></jsp:include>
</c:if>
