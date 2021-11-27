<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="header_area">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light">
            <!-- Brand and toggle get grouped for better mobile display -->

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                <ul class="nav navbar-nav menu_nav ml-auto">
                    <li class="nav-item active"><a class="nav-link" href="HomeShowHotelServlet">Home</a></li> 
                        <c:if test="${empty account}">
                        <li class="nav-item"><a class="nav-link" href="Login.jsp">Login</a></li>
                        </c:if>
                        <c:if test="${not empty account}">
                        <li class="nav-item"><a class="nav-link" href="accomodation.html">Logout</a></li>
                        </c:if>
                        <c:if test="${not empty account}">
                        <li class="nav-item"><a class="nav-link" href="gallery.html">Cart</a></li>
                        </c:if>
                        <c:if test="${not empty account}">
                        <li class="nav-item"><a class="nav-link" href="loadBookingHistory">Booking History</a></li>
                        </c:if>
                        <c:if test="${not empty account}">
                        <li class="nav-item"><a class="nav-link" href="Cart.jsp">Cart</a></li>
                        </c:if>
                </ul>
            </div> 
        </nav>
    </div>
</header>
