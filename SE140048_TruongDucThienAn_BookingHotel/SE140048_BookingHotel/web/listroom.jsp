<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="image/favicon.png" type="image/png">
        <title>Royal Hotel</title>
        <!-- Bootstrap CSS -->
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
        <style>
            img{
                width: 270px;
                height: 270px
            }
        </style>
    </head>
    <body>
        <!--================Header Area =================-->
        <jsp:include page="header.jsp"></jsp:include>
            <!--================Header Area =================-->

            <!--================Banner Area =================-->
            <section class="banner_area">
                <div class="booking_table d_flex align-items-center">
                    <div class="overlay bg-parallax" data-stellar-ratio="0.9" data-stellar-vertical-offset="0" data-background=""></div>
                    <div class="container">
                        <div class="banner_content text-center">
                            <h6>Away from monotonous life</h6>
                            <h2>Relax Your Mind</h2>
                            <p>If you are looking at blank cassettes on the web, you may be very confused at the<br> difference in price. You may see some for as low as $.17 each.</p>
                            <a href="#" class="btn theme_btn button_hover">Get Started</a>
                        </div>
                    </div>
                </div>

                <!-- booking hotel -->
                <form action="SearchRoomServlet" method="POST">
                    <div class="hotel_booking_area position">
                        <div class="container">
                            <div class="hotel_booking_table">
                                <div class="col-md-3">
                                    <h2>Book<br> Your Room</h2>
                                </div>
                                <div class="col-md-9">
                                    <div class="boking_table">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="book_tabel_item">
                                                    <div class="form-group">
                                                        <div class='input-group date' id='datetimepicker11'>
                                                            <input type='text' class="form-control" placeholder=
                                                                   "Arrival Date" name="arrivalDate" value="${param.arrivalDate}"/>
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-calendar" aria-hidden="true"></i>
                                                            </span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group"> 
                                                        <div class='input-group date' id='datetimepicker1'>
                                                            <input type='text' class="form-control" placeholder=
                                                                   "Departure Date" name="departureDate" value="${param.departureDate}"/>
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-calendar" aria-hidden="true"></i>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="book_tabel_item">
                                                    <div class="input-group">
                                                        <select class="wide" name="roomTypeId">
                                                            <option data-display="Room Type" >Room Type</option>
                                                            <option value="0" <c:if test="${roomTypeId == 0}">selected="selected"</c:if>>All Type</option>
                                                        <c:forEach items="${listRoomType}" var="r">
                                                            <option value="${r.id}" <c:if test="${roomTypeId == r.id}">selected="selected"</c:if>>${r.name}</option>  
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="input-group">
                                                    <select class="wide" name="hotelId">
                                                        <option data-display="Room Type" >Hotel</option>
                                                        <c:forEach items="${hotelList}" var="o">
                                                            <option value="${o.hotelId}" <c:if test="${hotelId == o.hotelId}">selected="selected"</c:if>>${o.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="book_tabel_item">
                                                <div class="input-group">
                                                    <input type="text" name="roomAmount" value="${param.roomAmount}" placeholder="Amount of room" onfocus="this.placeholder = 'Amount of room'" onblur="this.placeholder = 'Amount of room'" required class="single-input">
                                                </div>
                                                <input class="book_now_btn button_hover" type="submit"  value="Book Now" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <!-- end of booking hotel -->
        </section>
        <!--================Banner Area =================-->

        <!--================ Accomodation Area  =================-->
        <section class="accomodation_area section_gap">
            <div class="container">
                <div class="section_title text-center">
                    <h2 class="title_color">Hotel Accomodation</h2>
                    <p>We all live in an age that belongs to the young at heart. Life that is becoming extremely fast, </p>
                </div>
                <c:if test="${empty Error}">
                    <div class="row mb_30">
                        <c:forEach items="${listRoomByRoomType == null ? roomList : listRoomByRoomType}" var="o">
                            <div class="col-lg-3 col-sm-6">
                                <div class="accomodation_item text-center">
                                    <div class="hotel_img">
                                        <img src="${o.image}" alt="">
                                        <a href="addToCart?roomId=${o.roomID}" class="btn theme_btn button_hover">Book Now</a>
                                    </div>
                                    <a href="#"><h4 class="sec_h4">${o.name}</h4></a>
                                    <h5>$${o.price}<small>/night</small></h5>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${not empty Error}">
                    <div class="section_title text-center">
                        <h2 class="title_color">Has no room</h2>
                    </div>
                </c:if>
            </div>
        </section>
        <!--================ Accomodation Area  =================-->

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
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