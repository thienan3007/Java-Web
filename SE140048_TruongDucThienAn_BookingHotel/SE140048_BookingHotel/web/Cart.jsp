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
            <link rel="stylesheet" href="css/bootstrap.css">
            <link rel="stylesheet" href="vendors/linericon/style.css">
            <link rel="stylesheet" href="css/font-awesome.min.css">
            <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
            <link rel="stylesheet" href="vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
            <link rel="stylesheet" href="vendors/nice-select/css/nice-select.css">
            <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
            <!-- main css -->
            <link rel="stylesheet" href="css/style.css">
            <link rel="stylesheet" href="css/responsive.css">
        </head>

        <body>
            <c:if test="${not empty QUANTITY_ERROR}">
                <script>
                    var message = "Quantity of this cake is just ${QUANTITY_ERROR}!!!!!!";
                    alert(message);
                </script>
            </c:if>
            <div class="shopping-cart" style="display: block">
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
                                                        <div class="p-2 px-3 text-uppercase">Loại Phòng</div>
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
                                                    <th scope="col" class="border-0 bg-light">
                                                        <a href="room?hotelId=${hotel.hotelId}"class="py-2 text-uppercase">Back</a>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <!-- order list -->
                                                <c:forEach items="${booking.listBookingDetail}" var="o">
                                                    <c:url value="delete?roomId=${o.room.roomID}" var="delete"/>
                                                    <tr>
                                                        <th scope="row">
                                                            <div class="p-2">
                                                                <a href="${detail}">
                                                                    <img src="${o.room.image}" alt="" width="70">
                                                                </a>
                                                                <div class="ml-3 d-inline-block align-middle">
                                                                    <h5 class="mb-0"> <a href="" class="text-dark d-inline-block">${o.room.name}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <td class="align-middle"><strong>${o.room.roomType.name}</strong></td>
                                                        <td class="align-middle"><strong>${o.price}</strong></td>
                                                        <td class="align-middle">
                                                            <strong>${o.quantity}</strong>
                                                        </td>
                                                        <td class="align-middle"><a href="${delete}" class="text-dark">
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
                            <form action="booking" method="POST">
                                <div class="row py-5 p-4 bg-white rounded shadow-sm">
                                    <c:set value="${FORM_ERRORS}" var="errors"/>
                                    <div class="col-lg-7">
                                        <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold" style="text-align: center">Thông Tin Khách Hàng</div>
                                        <div class="form-group">
                                            <div class='input-group date' id='datetimepicker11'>
                                                <input type='text' class="form-control" placeholder=
                                                       "Arrival Date" name="arrivalDate" value="${param.arrivalDate}"/>
                                                <span class="input-group-addon">
                                                    <i class="fa fa-calendar" aria-hidden="true"></i>
                                                </span>
                                            </div>
                                        </div>
                                        <c:if test="${not empty ARRIVALDATE_ERROR}">
                                            <div class="form-group">
                                                <h5 style="color: red">Please form arrival date!!!</h5>
                                            </div>
                                        </c:if>
                                        <div class="form-group"> 
                                            <div class='input-group date' id='datetimepicker1'>
                                                <input type='text' class="form-control" placeholder=
                                                       "Departure Date" name="departureDate" value="${param.departureDate}"/>
                                                <span class="input-group-addon">
                                                    <i class="fa fa-calendar" aria-hidden="true"></i>
                                                </span>
                                            </div>
                                        </div>
                                        <c:if test="${not empty DEPARTURE_ERROR}">
                                            <div class="form-group">
                                                <h5 style="color: red">Please form departure date!!!</h5>
                                            </div>
                                        </c:if>
                                        <c:if test="${not empty DEPARTURE_ERROR_BEFORE_ARRIVAL}">
                                            <div class="form-group">
                                                <h5 style="color: red">Departure date must be after arrival date!!!</h5>
                                            </div>
                                        </c:if> 
                                        <c:if test="${not empty listErrorRoom}">
                                            <div class="form-group">
                                                <c:forEach items="${listErrorRoom}" var="o" >
                                                    <h5 style="color: red">${o.room.name} was in used from ${o.dateFromString} to ${o.dateToString}</h5>
                                                </c:forEach>
                                            </div>
                                        </c:if> 
                                        <div class="p-4">   
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
                                                    <input type="hidden" name="sum" value="${sum}" />
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
            <script src="js/jquery-3.2.1.min.js"></script>
            <script src="js/popper.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
            <script src="js/jquery.ajaxchimp.min.js"></script>
            <script src="js/mail-script.js"></script>
            <script src="vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.js"></script>
            <script src="vendors/nice-select/js/jquery.nice-select.js"></script>
            <script src="js/mail-script.js"></script>
            <script src="js/stellar.js"></script>
            <script src="vendors/lightbox/simpleLightbox.min.js"></script>
            <script src="js/custom.js"></script>
        </body>

    </html>
</html>
</c:if>
<c:if test="${empty account}">
    <jsp:include page="Login.jsp"></jsp:include>
</c:if>
