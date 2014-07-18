<%-- 
    Document   : register
    Created on : Jul 16, 2014, 5:16:32 PM
    Author     : inistor
--%>
<script src="resources/js/registerValidator.js" type="text/javascript"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="well col-lg-4 col-lg-offset-4"> 
        <form id="registerForm" class="form-horizontal" action="" method="POST">
            <fieldset>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input path="name" class="form-control" id="inputName" type="text" placeholder="Name" />
                        <form:errors path="name" class="text-warning"></form:errors>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input path="username" class="form-control" id="inputUsername" type="text" placeholder="Username" />
                        <form:errors path="username" class="text-warning"></form:errors>
                    </div>
                </div>
                <div class="form-group">    
                    <div class="col-lg-10 col-lg-offset-1">
                        <input path="email" class="form-control" id="inputEmail" type="text" placeholder="Email" />
                        <form:errors path="email" class="text-warning"></form:errors>
                    </div>
                </div>
                <div class="form-group">
                    
                    <div class="col-lg-10 col-lg-offset-1">
                        <input path="password" class="form-control" id="inputPassword" type="password" placeholder="Password" />
                        <form:errors path="password" class="text-warning"></form:errors>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input  class="form-control" id="inputRetypePassword" type="password" placeholder="Retype password" />
                        <label id="passwordMatchError" class="text-warning hidden" >Passwords don't match </label>
                    </div>
                </div>
            </fieldset>
        </form>
     <div class="form-group">
        <div class="col-lg-10 col-lg-offset-4">
            <button class="btn btn-primary" id="submitButton" type="submit">Sign up</button>
        </div>
    </div>
</div>




