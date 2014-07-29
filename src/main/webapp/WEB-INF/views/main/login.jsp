<%-- 
    Document   : login.jsp
    Created on : Jul 17, 2014, 12:15:36 PM
    Author     : iapavaloaie
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<section id="loginSection">
    <div class=" col-lg-4 col-lg-offset-4 Absolute-Center login-register-form">
        <div>
            <img src="/resources/img/logo.png" alt="Issue Tracker" class="col-lg-12 logo">
        </div>
        <form class="form-horizontal col-lg-12" action="security_check" method='POST'>
            <fieldset>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input class="form-control" id="inputUsername" type="text" placeholder="Username" name="username" autofocus/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input class="form-control" id="inputPassword" type="password" placeholder="Password" name="password" />
                    </div>
                </div>
                <span id="loginErrorCredential" class="text-warning errors hidden txt-center col-lg-12">Login has failed. Wrong username or password.</span>
                <span id="loginErrorActivation" class="text-warning errors hidden txt-center col-lg-12">The account is not active.</span>
                <br /><br />
                <div class="form-group">
                    <div class="col-lg-12 txt-center">
                        <button class="btn btn-primary btn-lg" id="loginButton" type="submit">Login</button>
                        <br />
                    </div>
                </div>
            </fieldset>
        </form>
        <span class="col-lg-12 txt-center">If you don't have an account, please <a href="/register"> Register</a></span>
        <script src="/resources/js/login.js" type="text/javascript"></script>
    </div>
</section>