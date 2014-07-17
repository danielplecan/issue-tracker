<%-- 
    Document   : register
    Created on : Jul 16, 2014, 5:16:32 PM
    Author     : inistor
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="well col-lg-4 col-lg-offset-4"> 
        <form:form class="form-horizontal" modelAttribute="user" action="register" method='POST'>
            <fieldset>
                <div class="form-group">
                    
                    <div class="col-lg-10 col-lg-offset-1">
                        <form:input path="name" class="form-control" id="inputName" type="text" placeholder="Name" />
                        <form:errors path="name" class="errorValidation"></form:errors>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <form:input path="username" class="form-control" id="inputUsername" type="text" placeholder="Username" />
                        <form:errors path="username" class="errorValidation"></form:errors>
                    </div>
                </div>
                <div class="form-group">    
                    <div class="col-lg-10 col-lg-offset-1">
                        <form:input path="email" class="form-control" id="inputEmail" type="text" placeholder="Email" />
                        <form:errors path="email" class="errorValidation"></form:errors>
                    </div>
                </div>
                <div class="form-group">
                    
                    <div class="col-lg-10 col-lg-offset-1">
                        <form:input path="password" class="form-control" id="inputPassword" type="password" placeholder="Password" />
                        <form:errors path="password" class="errorValidation"></form:errors>
                    </div>
                </div>
                <div class="form-group">
                    
                    <div class="col-lg-10 col-lg-offset-1">
                        <input  class="form-control" id="inputRetypePassword" type="password" placeholder="Retype password" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-4">
                        <button class="btn btn-primary" type="submit">Sign up</button>
                    </div>
                </div>
            </fieldset>
        </form:form>
</div>




