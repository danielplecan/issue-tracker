<%-- 
    Document   : login.jsp
    Created on : Jul 17, 2014, 12:15:36 PM
    Author     : iapavaloaie
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="well col-lg-4 col-lg-offset-4"> 
    <form class="form-horizontal" action="security_check" method='POST'>
        <fieldset>
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

            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-4">
                    <button class="btn btn-primary" type="submit">Login</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>

