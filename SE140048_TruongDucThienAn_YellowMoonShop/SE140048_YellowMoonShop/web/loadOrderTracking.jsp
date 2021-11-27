<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<script src="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<link href="css/order.css" rel="stylesheet" type="text/css"/>
<div class="container-fluid my-5 d-flex justify-content-center">
    <div class="card card-1">
        <div class="card-header bg-white">
            <c:if test="${not empty account}">
                <div class="media flex-sm-row flex-column-reverse justify-content-between ">
                    <div class="col my-auto">
                        <h4 class="mb-0">Thanks for your Order,<span class="change-color">${account.fullname}</span> !</h4>
                    </div>
                    <div class="col-auto text-center my-auto pl-0 pt-sm-4"> <img class="img-fluid my-auto align-items-center mb-0 pt-3" src="img/logo.jpg" width="115" height="115">
                    </div>
                </div>
            </c:if>
            <c:if test="${empty account}">
                <div class="media flex-sm-row flex-column-reverse justify-content-between ">
                    <div class="col my-auto">
                        <h4 class="mb-0">You have not login yet!!!</h4>
                    </div>
                    <div class="col-auto text-center my-auto pl-0 pt-sm-4"> <img class="img-fluid my-auto align-items-center mb-0 pt-3" src="img/logo.jpg" width="115" height="115">
                    </div>
                </div>
            </c:if>
        </div>      
        <div class="card-body">
            <c:if test="${not empty account}">
                <div class="row">
                    <form action="orderTracking" method="POST">
                        Please input Order's ID: <input type="text" name="orderID" value="" />
                    </form>
                </div>
            </c:if>
            <c:if test="${empty account}">
                <div class="row">
                    Please Login here: <a href="Login.jsp"><input class="btn-click" type="submit" value="Login" /></a>

                </div>
            </c:if>

        </div>
    </div>
</div>