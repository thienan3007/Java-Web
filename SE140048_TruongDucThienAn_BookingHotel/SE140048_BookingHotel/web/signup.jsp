<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <title>Login Form</title>
    </head>
    <div id="logreg-forms">
        <form action="LoginServlet" method="post" class="form-signup">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign up</h1>
            <input name="user" type="text" id="user-name" class="form-control" placeholder="User id" required="" autofocus="">
            <c:if test="${not empty SIGNUP_ERROR.idIsExisted}">
                <p class="text-danger">${SIGNUP_ERROR.idIsExisted}</p>
            </c:if>
            <c:if test="${not empty SIGNUP_ERROR.idErrors}">
                <p class="text-danger">${SIGNUP_ERROR.idErrors}</p>
            </c:if>
            <input name="username" type="text" id="user-name" class="form-control" placeholder="User name" required="" autofocus="">
            <c:if test="${not empty SIGNUP_ERROR.usernameErrors}">
                <p class="text-danger">${SIGNUP_ERROR.usernameErrors}</p>
            </c:if>
            <c:if test="${not empty SIGNUP_ERROR.usernameLengthErrors}">
                <p class="text-danger">${SIGNUP_ERROR.usernameLengthErrors}</p>
            </c:if>
            <input name="pass" type="password" id="user-pass" class="form-control" placeholder="Password" required autofocus="">
            <input name="repass" type="password" id="user-repeatpass" class="form-control" placeholder="Repeat Password" required autofocus="">
            <c:if test="${not empty SIGNUP_ERROR.passwordAndConfirmPasswordIsNotMatched}">
                <p class="text-danger">${SIGNUP_ERROR.passwordAndConfirmPasswordIsNotMatched}</p>
            </c:if>
            <input name="phone" type="text" id="user-repeatpass" class="form-control" placeholder="Phone" required autofocus="">
            <c:if test="${not empty SIGNUP_ERROR.phoneErrors}">
                <p class="text-danger">${SIGNUP_ERROR.phoneErrors}</p>
            </c:if>
            <c:if test="${not empty SIGNUP_ERROR.phoneLengthErrors}">
                <p class="text-danger">${SIGNUP_ERROR.phoneLengthErrors}</p>
            </c:if>
            <input name="address" type="text" id="user-repeatpass" class="form-control" placeholder="Address" required autofocus="">

            <button class="btn btn-primary btn-block" type="submit" name="btnAction" value="signup"><i class="fas fa-user-plus"></i> Sign Up</button>
            <a href="#" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
        </form>
        <br>
    </div>
</html>
