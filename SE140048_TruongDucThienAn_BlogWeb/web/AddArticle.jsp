<%-- 
    Document   : AddCake
    Created on : Sep 20, 2021, 4:09:13 PM
    Author     : antru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<c:if test="${not empty account}">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
            <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
            <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
            <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        </head>
        <body>
            <div>
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="postArticleControl" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Article</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>

                            <!-- name -->
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Title</label>
                                    <input value="${param.name}" name="title" type="text" class="form-control" required>
                                </div>
                                <c:if test="${not empty errors.nameLengthErrors}">
                                    <div class="form-group">
                                        <label style="color: red">${errors.nameLengthErrors}</label>
                                    </div>
                                </c:if>

                                <!-- short description -->
                                <div class="form-group">
                                    <label>Short Description</label>
                                    <textarea name="shortDes" class="form-control" rows="10" required>${param.description}</textarea>
                                </div>

                                <!-- description -->
                                <div class="form-group">
                                    <label>Content</label>
                                    <textarea name="content" class="form-control" rows="30" required>${param.description}</textarea>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <a href="ManageProductServlet"><input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"></a>
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </body>
    </html>
</c:if>
<c:if test="${empty account}">
    <jsp:include page="Login.jsp"></jsp:include>
</c:if>
