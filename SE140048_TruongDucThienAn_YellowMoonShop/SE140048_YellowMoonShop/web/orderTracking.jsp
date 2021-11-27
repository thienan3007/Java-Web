<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<script src="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<link href="css/order.css" rel="stylesheet" type="text/css"/>
<c:if test="${not empty account}">
    <div class="container-fluid my-5 d-flex justify-content-center">
        <div class="card card-1">
            <div class="card-header bg-white">
                <div class="media flex-sm-row flex-column-reverse justify-content-between ">
                    <div class="col my-auto">
                        <h4 class="mb-0">Thanks for your Order,<span class="change-color">${account.fullname}</span> !</h4>
                    </div>
                    <div class="col-auto text-center my-auto pl-0 pt-sm-4"> <img class="img-fluid my-auto align-items-center mb-0 pt-3" src="img/logo.jpg" width="115" height="115">
                    </div>
                </div>
            </div>
            <div class="card-body">

                <!-- list cake here -->
                <c:set value="${0}" var="total"></c:set>
                <c:forEach items="${orderTracking.listOrderDetail}" var="o">
                    <div class="row mt-4">
                        <div class="col">
                            <div class="card card-2">
                                <div class="card-body">
                                    <div class="media">
                                        <div class="sq align-self-center "> <img class="img-fluid my-auto align-self-center mr-2 mr-md-4 pl-0 p-0 m-0" src="${o.cakeDTO.image}" width="135" height="135" /> </div>
                                        <div class="media-body my-auto text-right">
                                            <div class="row my-auto flex-column flex-md-row">
                                                <div class="col my-auto">
                                                    <h6 class="mb-0"> ${o.cakeDTO.name}</h6>
                                                </div>
                                                <div class="col-auto my-auto"> <small> ${o.cakeDTO.price} </small></div>
                                                <div class="col my-auto"> <small>Price : ${o.price}</small></div>
                                                <div class="col my-auto"> <small>Qty : ${o.quantity}</small></div>
                                                <div class="col my-auto">
                                                    <h6 class="mb-0">&#8363;${o.price * o.quantity}</h6>
                                                    <input type="hidden" name="total" value="${total = total + (o.price * o.quantity)}" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <hr class="my-3 ">
                                    <div class="row">
                                        <div class="col-md-3 mb-3"> <small> Track Order <span><i class=" ml-2 fa fa-refresh" aria-hidden="true"></i></span></small> </div>
                                        <div class="col mt-auto">
                                            <div class="progress my-auto">
                                                <div class="progress-bar progress-bar rounded" style="width: 62%" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                            <div class="media row justify-content-between ">
                                                <div class="col-auto text-right"><span> <small class="text-right mr-sm-2"></small> <i class="fa fa-circle active"></i> </span></div>
                                                <div class="flex-col"> <span> <small class="text-right mr-sm-2">Out for delivary</small><i class="fa fa-circle active"></i></span></div>
                                                <div class="col-auto flex-col-auto"><small class="text-right mr-sm-2">Delivered</small><span> <i class="fa fa-circle"></i></span></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <!-- order detail -->
                <div class="row mt-4">
                    <div class="col">
                        <div class="row justify-content-between">
                            <div class="flex-sm-col text-right col">
                                <p class="mb-1"><b>Total</b></p>
                            </div>
                            <div class="flex-sm-col col-auto">
                                <p class="mb-1">&#8363;${total}</p>
                            </div>
                        </div>
                        <div class="row justify-content-between">
                            <div class="flex-sm-col text-right col">
                                <p class="mb-1"><b>VAT 10%</b></p>
                            </div>
                            <div class="flex-sm-col col-auto">
                                <p class="mb-1">&#8363;${total * 0.1}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row invoice ">
                    <div class="col">
                        <p class="mb-1"> Invoice Number : ${orderTracking.orderID}</p>
                        <p class="mb-1">Invoice Date : ${orderTracking.orderDate}</p>
                        <p class="mb-1"><a href="HomeServlet"><input type="submit" value="Back To Home"></a></p>
                    </div>
                </div>
            </div>

            <!-- footer -->
            <div class="card-footer">
                <div class="jumbotron-fluid">
                    <div class="row justify-content-between ">
                        <div class="col-sm-auto col-auto my-auto"><img class="img-fluid my-auto align-self-center " src="img/logo.jpg" width="115" height="115"></div>
                        <div class="col-auto my-auto ">
                            <h2 class="mb-0 font-weight-bold">TOTAL PAID</h2>
                        </div>
                        <div class="col-auto my-auto ml-auto">
                            <h1 class="display-3 ">&#8363; ${total + (total * 0.1)}</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${empty account}">
    <jsp:include page="loadOrderTracking.jsp"></jsp:include>
</c:if>