<%-- 
    Document   : Edit
    Created on : Sep 20, 2021, 2:20:29 PM
    Author     : antru
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <style>
            img{
                width: 300px;
                height: 220px;
            }
        </style>
    </head>
    <body>
        <div>
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="edit" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Cake</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <c:set value="${AddCakesErrors}" var="errors"></c:set>
                            <div class="modal-body">
                                <!-- name -->
                                <div class="form-group">
                                    <label>Name</label>
                                    <input value="${cake.name}" name="name" type="text" class="form-control" required>
                                <input type="hidden" name="id" value="${cake.cakesID}" />
                            </div>
                            <c:if test="${not empty errors.nameLengthErrors}">
                                <div class="form-group">
                                    <label style="color: red">${errors.nameLengthErrors}</label>
                                </div>
                            </c:if>

                            <!-- old image -->
                            <div class="form-group">
                                <label>Old Image</label>
                                <div class="content">
                                    <img src="${cake.image}">
                                    <input type="hidden" name="oleImage" value="${cake.image}" />
                                </div>
                            </div>  

                            <!-- new image -->
                            <div class="form-group">
                                <label>New Image</label>
                                <div class="custom-file">
                                    <input type="file" id="image" name="image_uploads" accept=".jpg, .jpeg, .png">
                                    <input type="hidden" name="image" id="imageHidden"/>
                                </div>
                            </div>       

                            <!-- created date -->
                            <div class="form-group">
                                <label>Created Date</label>
                                <input value="${cake.createDate}" type="date" id="createdDate" name="createdDate">
                            </div>
                            <c:if test="${not empty errors.createdDateErrors}">
                                <div class="form-group">
                                    <label style="color: red">${errors.createdDateErrors}</label>
                                </div>
                            </c:if>
                            <!-- expiration date -->
                            <div class="form-group">
                                <label>Expiration Date</label>
                                <input value="${cake.expirationDate}" type="date" id="expirationDate" name="expirationDate">
                            </div>
                            <c:if test="${not empty errors.expirationDateErrors}">
                                <div class="form-group">
                                    <label style="color: red">${errors.expirationDateErrors}</label>
                                </div>
                            </c:if>
                            <!-- price -->
                            <div class="form-group">
                                <label>Price</label>
                                <input value="${cake.price}" name="price" type="text" class="form-control" required>
                            </div>

                            <!-- quantity -->
                            <div class="form-group">
                                <label>Quantity</label>
                                <input value="${cake.quantity}" name="quantity" type="text" class="form-control" required>
                            </div>

                            <!-- description -->
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" class="form-control" required>${cake.description}</textarea>
                            </div>

                            <!-- category -->
                            <div class="form-group">
                                <label>Category</label>
                                <select name="categoryID" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${listCategory}" var="o">
                                        <option  value="${o.categoryID}" 
                                                 <c:if test="${o.categoryID == cake.categoryID}">selected</c:if>>
                                            ${o.categoryName}</option>
                                        </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Status</label>
                                <select name="statusID" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${cakeStatusList}" var="o">
                                        <option  value="${o.statusID}" 
                                                 <c:if test="${o.statusID == cake.statusID}">selected</c:if>>
                                            ${o.statusName}</option>
                                        </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a href="ManageProductServlet"><input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"></a>
                            <input type="submit" class="btn btn-success" value="Edit">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
