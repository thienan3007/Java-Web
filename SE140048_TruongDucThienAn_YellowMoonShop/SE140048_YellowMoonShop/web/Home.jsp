<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                height: 200px;
                width: 100%;
            }

            #list::after {
                content: "\a";
                white-space: pre;
            }
            nav{
                display: block;
            }
        </style>
    </head>
    <body>
        <c:if test="${not empty QUANTITY_ERROR}">
            <script>
                var message = "Quantity of this cake is just ${QUANTITY_ERROR}!!!!!!";
                alert(message);
            </script>
        </c:if>
        <c:if test="${not empty orderID}">
            <script>
                var message = "Your order ID is ${orderID}!!!!!!";
                alert(message);
            </script>
        </c:if>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="Home.jsp">Home</a></li>
                                <li class="breadcrumb-item"><a href="#">Category</a></li>
                                <li class="breadcrumb-item active" aria-current="#">Sub-category</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                <jsp:include page="Left.jsp"></jsp:include>
                <c:if test="${not empty cakeA || not empty cakeM || not empty cakeC || not empty cakeN}">
                    <div class="col-sm-9">
                        <div class="row">
                            <c:forEach items="${cakeA == null ? (cakeM == null ? (cakeC == null ? cakeN : cakeC) : cakeM) : cakeA}" var="o">
                                <div class="col-12 col-md-6 col-lg-4">
                                    <div class="card" style="margin: 10px">
                                        <c:url value="addToCart?cakesId=${o.cakesID}" var="addToCart"/>
                                        <c:url value="detail?cakesId=${o.cakesID}" var="detail"/>
                                        <a href="${detail}">
                                            <img src="${o.image}" alt="Card image cap">
                                        </a>
                                        <div class="card-body">
                                            <h4 class="card-title show_txt" style="text-align: center"><a href="${detail}" title="View Product">${o.name}</a></h4>
                                            <p class="card-text show_txt" style="text-align: center">${o.name}
                                            </p>
                                            <div class="row">
                                                <div class="col">
                                                    <p class="btn btn-danger btn-block">${o.price} &#8363;</p>
                                                </div>
                                                <div class="col">
                                                    <a href="${addToCart}">
                                                        <input type="submit" class="btn btn-success btn-block" value="Add To Cart" name="btnAction" 
                                                               <c:if test="${sessionScope.account.roleID == 1}">disabled</c:if>/>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                            </c:forEach>
                        </div>
                    </div>
                    <c:if test="${endPage > 1}">
                        <div class="col-sm-12">
                            <div class="row">
                                <nav aria-label="..." style="margin: 0 auto">
                                    <ul class="pagination">
                                        <li class="page-item ${tag == 1 ?"disabled" : ""}">
                                            <a class="page-link" href="<c:if test="${not empty cakeC}">searchCakesByCategoryServlet?cid=${cid}&index=${tag-1}</c:if>
                                               <c:if test="${not empty cakeM}">searchCakesByMoneyServlet?txtFrom=${min}&txtTo=${max}&index=${tag-1}</c:if>
                                               <c:if test="${not empty cakeA}">HomeServlet?index=${tag-1}</c:if>
                                               <c:if test="${not empty cakeN}">search?name=${name}&index=${tag-1}</c:if>">Previous</a>
                                            </li>
                                        <c:forEach begin="1" end="${endPage}" var="i">
                                            <li class="page-item ${tag == i?"active":""}"><a class="page-link" href="<c:if test="${not empty cakeC}">searchCakesByCategoryServlet?cid=${cid}&index=${i}</c:if>
                                                                                             <c:if test="${not empty cakeM}">searchCakesByMoneyServlet?txtFrom=${min}&txtTo=${max}&index=${i}</c:if>
                                                                                             <c:if test="${not empty cakeA}">HomeServlet?index=${i}</c:if>
                                                                                             <c:if test="${not empty cakeN}">search?name=${name}&index=${i}</c:if>">${i}</a></li>
                                            </c:forEach>
                                        <li class="page-item ${tag == endPage ?"disabled" : ""}">
                                            <a class="page-link" href="<c:if test="${not empty cakeC}">searchCakesByCategoryServlet?cid=${cid}&index=${tag+1}</c:if>
                                               <c:if test="${not empty cakeM}">searchCakesByMoneyServlet?txtFrom=${min}&txtTo=${max}&index=${tag+1}</c:if>
                                               <c:if test="${not empty cakeA}">HomeServlet?index=${tag+1}</c:if>
                                               <c:if test="${not empty cakeN}">search?name=${name}&index=${tag+1}</c:if>">Next</a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                    </c:if>
                </c:if>
                <c:if test="${empty cakeA && empty cakeC && empty cakeM && empty cakeN}">
                    <div class="col-sm-9">
                        <div class="row" style="margin: 0 auto">
                            <h3 style="text-align: center">Hiện không có sản phầm quý khách cần tìm</h3>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>

        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>

