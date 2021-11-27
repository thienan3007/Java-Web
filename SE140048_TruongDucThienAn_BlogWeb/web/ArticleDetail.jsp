<%-- 
    Document   : AddCake
    Created on : Sep 20, 2021, 4:09:13 PM
    Author     : antru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
                    <div class="modal-header">						
                        <h4 class="modal-title">Article Detail</h4>
                        <button type="button"  class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>

                    <!-- name -->
                    <div class="modal-body">					
                        <div class="form-group">
                            <label>Title</label><br>
                            &emsp;&emsp;${article.title}
                        </div

                        <!-- short description -->
                        <div class="form-group">
                            <label>Short Description</label><br>
                            &emsp;&emsp;${article.description}
                        </div>

                        <!-- description -->
                        <div class="form-group">
                            <label>Content</label><br>
                            &emsp;&emsp;${article.content}
                        </div>
                        <label>Author: </label> ${article.author.name}<br>
                        <label>Posting Date: </label> ${article.postingDate}<br>
                        <label>COMMENT:  </label><br>
                        <c:if test="${empty article.comments}">
                            NO COMMENTS
                        </c:if>
                        <c:forEach items="${article.comments}" var="o">
                            <label>&emsp;&emsp; User: </label> ${o.user.name}<br>
                            <label>&emsp;&emsp; Content: </label> ${o.content}<br>
                            <label>&emsp;&emsp; Comment Date: </label> ${o.commentDate}<br><br>
                        </c:forEach>
                        <c:if test="${account.role.id != 1}">
                            <form action="commentControl?articleID=${article.id}&did=${article.id}" method="POST">
                                <div class="form-group">
                                    <label>Leave a comment for this Article: </label>
                                    <textarea name="comment" class="form-control" rows="30" required></textarea>
                                </div>
                                <input type="submit" value="Submit" />
                            </form>
                        </c:if>


                    </div>
                    <div class="modal-footer">
                        <a href="homePageControl"><input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"></a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
