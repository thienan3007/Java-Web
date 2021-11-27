<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <form action="searchCakesByMoneyServlet" method="POST">
            From <input type="text" name="txtFrom" value="${min}" style="margin-top: 10px"/> <br>
            To <input type="text" name="txtTo" value="${max}" style="margin-left: 18.7px; margin-top: 10px"/>
            <input type="submit" value="Search" name="btnAction" class="btn-info" style="margin: 10px; margin-left: 50px"/>
        </form>
    </div>
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
        <ul class="list-group category_block">
            <c:forEach items="${listCategory}" var="o">
                <li class="list-group-item text-white ${cid == o.categoryID ? "active" : ""}">
                    <a href="searchCakesByCategoryServlet?cid=${o.categoryID}">${o.categoryName}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
            
</div>