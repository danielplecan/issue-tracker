<%-- 
    Document   : login.jsp
    Created on : Jul 17, 2014, 12:15:36 PM
    Author     : iapavaloaie
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class=" col-sm-4 col-sm-offset-4 Absolute-Center" >


    <form class="form-horizontal" action="security_check" method='POST'>

        <fieldset>
            <div class="logo">
                <img src="/resources/img/logo.png" alt="Issue Tracker" class="col-lg-12">
            </div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-1">
                    <input class="form-control" id="inputUsername" type="text" placeholder="Username" name="username" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-1">
                    <input class="form-control" id="inputPassword" type="password" placeholder="Password" name="password" />
                </div>
            </div>
            <span id="loginError" class="text-warning errors hidden txt-center col-lg-12">Login has failed. Wrong username or password.</span>
            <br /><br />
            <div class="form-group">
                <div class="col-lg-12 txt-center">
                    <button class="btn btn-primary btn-lg" id="loginButton" type="submit">Login</button>
                    <br />

                </div>
            </div>
        </fieldset>
    </form>
    <span class="col-lg-12 txt-center">If you don't have an account please <a href="/register"> Register</a></span>
    <script src="/resources/js/login.js" type="text/javascript"></script>
</div>
