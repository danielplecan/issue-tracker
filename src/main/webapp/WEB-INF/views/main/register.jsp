<%-- 
    Document   : register
    Created on : Jul 16, 2014, 5:16:32 PM
    Author     : inistor
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<section id="registerSection">
    <div class="col-lg-4 col-lg-offset-4 Absolute-Center login-register-form">
        <div>
            <img src="/resources/img/logo.png" alt="Issue Tracker" class="col-lg-12 logo">
        </div>
        <form id="registerForm" class="form-horizontal col-lg-12" method='POST'>
            <fieldset>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input class="form-control" id="inputName" type="text" placeholder="Name" name="name" autofocus/>
                        <span id="nameError" class="text-warning errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input class="form-control" id="inputUsername" type="text" placeholder="Username" name="username" />
                        <span id="usernameError" class="text-warning errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input class="form-control" id="inputEmail" type="email" placeholder="Email" name="email" />
                        <span id="emailError" class="text-warning errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input class="form-control" id="inputPassword" type="password" placeholder="Password" name="password" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input  class="form-control" id="inputRetypePassword" type="password" placeholder="Retype password" />
                        <label id="passwordMatchError" class="text-warning hidden" >Passwords don't match </label>
                        <span id="passwordError" class="text-warning errors"></span>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-lg-12">
                        <button class="btn btn-primary" id="submitButton">Sign up</button>
                    </div>
                </div>
            </fieldset>
        </form>
        <span class="col-lg-12 txt-center">If you already have an account, please <a href="/login"> Login</a></span>
    </div>
    <script src="/resources/js/register.js" type="text/javascript"></script>