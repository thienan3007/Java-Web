<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="account" value="${sessionScope.account}"></c:set>
<c:if test="${not empty account && account.roleID == 1}">
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
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
                            alert("img/" + filename);
                            document.getElementById("imageHidden").setAttribute('value', "img/" + filename);
                        }
                    });
                });
            </script>
            <style>
                img{
                    width: 200px;
                    height: 120px;
                }
            </style>
        <body>
            <div class="container">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>Manage <b>Product</b></h2>
                            </div>
                            <div class="col-sm-6">
                                <a href="loadAdd" class="btn btn-success"><i class="material-icons">&#xE147;</i><span>Add New Product</span></a>
                                <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="selectAll">
                                        <label for="selectAll"></label>
                                    </span>
                                </th>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Image</th>
                                <th>Price</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listCakes}" var="o">
                                <tr>
                                    <td>
                                        <span class="custom-checkbox">
                                            <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                            <label for="checkbox1"></label>
                                        </span>
                                    </td>
                                    <td>${o.cakesID}</td>
                                    <td>${o.name}</td>
                                    <td>
                                        <img src="${o.image}">
                                    </td>
                                    <td>${o.price} $</td>
                                    <td>
                                        <a href="loadData?cakesID=${o.cakesID}" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="#deleteEmployeeModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>6</b> out of <b>${count}</b> entries</div>
                        <ul class="pagination">
                            <li class="page-item ${tag == 1 ? "disabled" : ""}"><a href="ManageProductServlet?index=${tag-1}">Previous</a></li>
                                <c:forEach begin="1" end="${endPage}" var="i">
                                <li class="page-item ${tag == i ?"active" : ""}"><a href="ManageProductServlet?index=${i}" class="page-link">${i}</a></li>
                                </c:forEach>
                            <li class="page-item ${tag == endPage ? "disabled" : ""}"><a href="ManageProductServlet?index=${tag+1}" class="page-link">Next</a></li>
                        </ul>
                    </div>
                </div>
                <a href="HomeServlet"><button type="button" class="btn btn-primary">Back to home</button></a>

            </div>
            <!-- Create Modal HTML -->
            <div id="addEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="addCakes" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Name</label>
                                    <input name="name" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Image</label>
                                    <div class="custom-file">
                                        <label for="image_uploads">Choose images to upload (PNG, JPG)</label>
                                        <input type="file" id="image" name="image_uploads" accept=".jpg, .jpeg, .png">
                                        <input type="hidden" name="image" id="imageHidden"/>
                                    </div>
                                </div>                             
                                <div class="form-group">
                                    <label>Created Date</label>
                                    <input type="date" id="createdDate" name="createdDate">
                                </div>
                                <div class="form-group">
                                    <label>Expiration Date</label>
                                    <input type="date" id="expirationDate" name="expirationDate">
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input name="price" type="number" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Quantity</label>
                                    <input name="quantity" type="number" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="description" class="form-control" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="categoryID" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listCategory}" var="o">
                                            <option value="${o.categoryID}">${o.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Edit Modal HTML -->
            <div id="editEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="addCakes" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Name</label>
                                    <input name="name" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Old Image</label>
                                    <img src="">
                                </div>
                                <div class="form-group">
                                    <label>New Image</label>
                                    <div class="custom-file">
                                        <input type="file" id="image" name="image_uploads" accept=".jpg, .jpeg, .png">
                                        <input type="hidden" name="image" id="imageHidden"/>
                                    </div>
                                </div>                             
                                <div class="form-group">
                                    <label>Created Date</label>
                                    <input type="date" id="createdDate" name="createdDate">
                                </div>
                                <div class="form-group">
                                    <label>Expiration Date</label>
                                    <input type="date" id="expirationDate" name="expirationDate">
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input name="price" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Quantity</label>
                                    <input name="quantity" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="description" class="form-control" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="categoryID" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listCategory}" var="o">
                                            <option value="${o.categoryID}">${o.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Delete Modal HTML -->
            <div id="deleteEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form>
                            <div class="modal-header">						
                                <h4 class="modal-title">Delete Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <p>Are you sure you want to delete these Records?</p>
                                <p class="text-warning"><small>This action cannot be undone.</small></p>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-danger" value="Delete">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </a>
        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>
</c:if>
<c:if test="${empty account}">
    <jsp:include page="Login.jsp"></jsp:include>
</c:if>