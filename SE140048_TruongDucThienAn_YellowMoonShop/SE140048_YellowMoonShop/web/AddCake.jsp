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
        <script>
            $(document).ready(function () {
                $("#image").change(function () {
                    var fullPath = document.getElementById('image').value;
                    if (fullPath) {
                        var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
                        var filename = fullPath.substring(startIndex);
                        if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
                            filename = filename.substring(1);
                        }
                        document.getElementById("imageHidden").setAttribute('value', "img/" + filename);
                    }
                });
            });
        </script>
    </head>
    <body>
        <div>
            <div class="modal-dialog">
                <div class="modal-content">
                    <c:set value="${AddCakesErrors}" var="errors"></c:set>
                        <form action="addCakes" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>

                            <!-- name -->
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Name</label>
                                    <input value="${param.name}" name="name" type="text" class="form-control" required>
                            </div>
                            <c:if test="${not empty errors.nameLengthErrors}">
                                <div class="form-group">
                                    <label style="color: red">${errors.nameLengthErrors}</label>
                                </div>
                            </c:if>

                            <!-- image -->
                            <div class="form-group">
                                <label>Image</label>
                                <div class="custom-file">
                                    <label for="image_uploads">Choose images to upload (PNG, JPG)</label>
                                    <input type="file" id="image" name="image_uploads" accept=".jpg, .jpeg, .png">
                                    <input type="hidden" name="image" id="imageHidden"/>
                                </div>
                            </div> 
                            <c:if test="${not empty errors.imageErrors}">
                                <div class="form-group">
                                    <label style="color: red">${errors.imageErrors}</label>
                                </div>
                            </c:if>
                            
                            <!-- created date -->
                            <div class="form-group">
                                <label>Created Date</label>
                                <input type="date" id="createdDate" value="${param.createdDate}" name="createdDate">
                            </div>
                            <c:if test="${not empty errors.createdDateErrors}">
                                <div class="form-group">
                                    <label style="color: red">${errors.createdDateErrors}</label>
                                </div>
                            </c:if>
                            
                            <!-- Expiration date -->
                            <div class="form-group">
                                <label>Expiration Date</label>
                                <input type="date" value="${param.expirationDate}" id="expirationDate" name="expirationDate">
                            </div>
                            <c:if test="${not empty errors.expirationDateErrors}">
                                <div class="form-group">
                                    <label style="color: red">${errors.expirationDateErrors}</label>
                                </div>
                            </c:if>
                            
                            <!-- price -->
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" value="${param.price}" type="number" class="form-control" required>
                            </div>
                            
                            <!-- quantity -->
                            <div class="form-group">
                                <label>Quantity</label>
                                <input name="quantity" value="${param.quantity}" type="number" class="form-control" required>
                            </div>
                            
                            <!-- description -->
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" class="form-control" required>${param.description}</textarea>
                            </div>
                            
                            <!-- category -->
                            <div class="form-group">
                                <label>Category</label>
                                <select name="categoryID" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${listCategory}" var="o">
                                        <option value="${o.categoryID}" <c:if test="${o.categoryID == param.categoryID}">
                                               selected 
                                        </c:if>>${o.categoryName}</option>
                                    </c:forEach>
                                </select>
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
