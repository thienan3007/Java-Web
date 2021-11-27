<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
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

                    <div class="col-sm-9">
                    <c:if test="${not empty categoryNew}">
                        <a href="activeAllControl" class="btn btn-dark btn-block">Active all</a>
                    </c:if>
                    <c:forEach items="${articleAdmin == null ? articleMember : articleAdmin}" var="o">
                        <div class="row">
                            <div class="card" style="width: 900px">
                                <div class="card-body">
                                    <h4 class="card-title show_txt"><a href="#" title="View Product">${o.title}</a></h4>
                                    <p class="card-text show_txt">${o.description}
                                    </p>
                                    <div class="row">
                                        <div class="col">
                                            <a href="detailControl?did=${o.id}" class="btn btn-success btn-block">View Detail</a>
                                        </div>
                                        <c:if test="${o.statusID == 1 && not empty articleAdmin}">
                                            <div class="col">
                                                <a href="activeControl?aid=${o.id}" class="btn btn-info btn-block">Active</a>
                                            </div>
                                        </c:if>
                                        <c:if test="${not empty articleAdmin && o.statusID != 2}">
                                            <div class="col">
                                                <a href="deleteControl?aid=${o.id}" class="btn btn-danger btn-block">Delete</a>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
                <c:if test="${endPage > 1}">
                    <div class="col-sm-12">
                        <div class="row">
                            <nav aria-label="..." style="margin: 0 auto">
                                <ul class="pagination">
                                    <li class="page-item ${tag == 1 ?"disabled" : ""}">

                                        <c:if test="${empty search && empty category && empty searchName}">
                                            <a class="page-link" href="homePageControl?index=${tag-1}">Previous</a>
                                        </c:if>

                                        <c:if test="${not empty search}">
                                            <a class="page-link" href="searchControl?index=${tag-1}&keyword=${keyword}">Previous</a>
                                        </c:if>
                                        <c:if test="${not empty category}">
                                            <a class="page-link" href="categoryControl?index=${tag-1}&cid=${cid}">Previous</a>
                                        </c:if>

                                        <c:if test="${not empty searchName}">
                                            <a class="page-link" href="searchNameControl?index=${tag-1}&keyword=${keyword}">Previous</a>
                                        </c:if>
                                    </li>
                                    <c:forEach begin="1" end="${endPage}" var="i">
                                        <c:if test="${empty search && empty category && empty searchName}">
                                            <li class="page-item ${tag == i?"active":""}">
                                                <a class="page-link" href="homePageControl?index=${i}">${i}</a>
                                            </li>
                                        </c:if>

                                        <c:if test="${not empty search}">
                                            <li class="page-item ${tag == i?"active":""}">
                                                <a class="page-link" href="searchControl?index=${i}&keyword=${keyword}">${i}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${not empty category}">
                                            <li class="page-item ${tag == i?"active":""}">
                                                <a class="page-link" href="categoryControl?index=${i}&cid=${cid}">${i}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${not empty searchName}">
                                            <li class="page-item ${tag == i?"active":""}">
                                                <a class="page-link" href="searchNameControl?index=${i}&keyword=${keyword}">${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                    <li class="page-item ${tag == endPage ?"disabled" : ""}">
                                        <c:if test="${empty search && empty category && empty searchName}">
                                            <a class="page-link" href="homePageControl?index=${tag+1}">Next</a>
                                        </c:if>

                                        <c:if test="${not empty search}">
                                            <a class="page-link" href="searchControl?index=${tag+1}&keyword=${keyword}">Next</a>
                                        </c:if>
                                        <c:if test="${not empty category}">
                                            <a class="page-link" href="searchControl?index=${tag+1}&cid=${cid}">Next</a>
                                        </c:if>
                                        <c:if test="${not empty searchName}">
                                            <a class="page-link" href="searchNameControl?index=${tag+1}&keyword=${keyword}">Next</a>
                                        </c:if>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </c:if>

            </div>
        </div>

        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>

