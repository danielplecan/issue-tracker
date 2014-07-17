<%-- 
    Document   : register
    Created on : Jul 16, 2014, 5:16:32 PM
    Author     : inistor
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form:form class="form-horizontal"  modelAttribute="user" action="register" method='POST'>
            <fieldset>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="inputName">Name</label>
                    <div class="col-lg-10">
                        <input class="form-control" id="inputUsername" type="text" placeholder="Name">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="inputUsername">Username</label>
                    <div class="col-lg-10">
                        <input class="form-control" id="inputUsername" type="text" placeholder="Username">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="inputPassword">Password</label>
                    <div class="col-lg-10">
                        <input class="form-control" id="inputPassword" type="text" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="inputRetypePassword">Retype password</label>
                    <div class="col-lg-10">
                        <input class="form-control" id="inputRetypePassword" type="text" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="inputEmail">Email</label>
                    <div class="col-lg-10">
                        <input class="form-control" id="inputEmail" type="text" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button class="btn btn-default">Cancel</button>
                        <button class="btn btn-primary" type="submit">Submit</button>
                    </div>
                </div>
            </fieldset>
        </form:form>
    </body>
</html>
