

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3">

    <c:if test="${not empty account}">
        <c:if test="${account.role.id == 1}">
            <div class="card bg-light mb-3">
                <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                <ul class="list-group category_block">
                    <c:forEach items="${statusList}" var="o">
                        <li class="list-group-item text-white <c:if test="${o.id == cid}">active</c:if>"><a href="categoryControl?cid=${o.id}">${o.name}</a></li>
                        </c:forEach>
                </ul>
            </div>
            <div class="card bg-light mb-3">
                <div class="card-header bg-success text-white text-uppercase">Search Name</div>
                <div class="card-body">
                    <form action="SearchNameController" method="POST">
                        <div class="input-group input-group-sm">
                            <input name="keyword" type="text" value="${keyword}" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-secondary btn-number">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
    </c:if>
</div>