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
                        <input class="form-control" id="inputName" type="text" placeholder="Name" title="Name must contain between 5 and 60 characters." name="name" autofocus/>
                        <span id="nameError" class="text-warning errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input class="form-control" id="inputUsername" type="text" placeholder="Username" name="username" title="Username must contain between 5 and 20 characters."/>
                        <span id="usernameError" class="text-warning errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input class="form-control" id="inputEmail" type="email" placeholder="Email" name="email" title="Provide a valid email."/>
                        <span id="emailError" class="text-warning errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input class="form-control" id="inputPassword" type="password" placeholder="Password" name="password" title="Password must contain between 5 and 20 characters." />
                        <span id="mainPasswordError" class="text-warning errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-1">
                        <input  class="form-control" id="inputRetypePassword" type="password" placeholder="Retype password" title="Retype the password."/>
                        <label id="passwordMatchError" class="text-warning hidden" >Passwords don't match </label>
                        <span id="passwordError" class="text-warning errors"></span>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-lg-12">
                        <button class="btn btn-primary" id="submitButton" >Sign up</button>
                    </div>
                </div>
            </fieldset>
        </form>
        <span class="col-lg-12 txt-center">If you already have an account, please <a href="/login"> Login</a></span>
    </div>
    <style>
        .ui-tooltip, .arrow:after {
            background: white;
            border: 1px solid #999;       
        }      

        .ui-tooltip {
            padding: 10px 12px;
            color: Black;
            font: 8pt "Helvetica Neue", Sans-Serif;   
            max-width: 150px;
            border: 1px solid #999;
            position: absolute;
        } 
        .arrow {
            height: 0;
            width: 0;    
            overflow: hidden;
            position: absolute;
            top: 50%;
            margin-top: -10px;
            left: -20px;
            border:10px solid #000;
            border-top-color:transparent;
            border-left-color:transparent;
            border-bottom-color:transparent;

        }

        .arrow:after {
            content: "";
            position: absolute;
            width: 25px;  
            height: 25px;
            border-width: 0 15px 2px;
            box-shadow:6px 5px 9px -9px black;
            -webkit-transform-origin: 5px 25px;
            -webkit-transform: rotate(45deg);
            -moz-transform: rotate(45deg);
            -ms-transform: rotate(45deg);
            -o-transform: rotate(45deg);
            tranform: rotate(45deg);
        }

    </style>
    <script src="/resources/js/register.js" type="text/javascript"></script>
