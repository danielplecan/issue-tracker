<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="position:relative;" data-notification="${initialNotificationCheckbox}" data-theme=${initialTheme} id="settingsContainer">
    <div>
        
        </choose>
        <div class="checkbox">
            <label>
                <input type="checkbox" id="toggleNotifications" class="toggleBox"> Receive notifications for owned issues
            </label>
        </div>
        
    </div>
    <div id="optionContainer">
        Change theme:<br/>
        <ul id="themeSelect">
            <li value="1" id="op1">Light(default)</li>
            <li value="2" id="op2">Dark</li>
            <li value="3" id="op3">Cold</li>
            <li value="4" id="op4">Salad</li>
            <li value="5" id="op5">Pink</li>
            
        </ul>
    </div>
    <ul id="theme-preview">
        <li id="prev1" class="col-lg-4 row theme-prev"><img src="/resources/img/theme1.png" alt=""/></li>
        <li id="prev2" class="col-lg-4 row theme-prev"><img src="/resources/img/theme2.png" alt=""/></li>
        <li id="prev3" class="col-lg-4 row theme-prev"><img src="/resources/img/theme3.png" alt=""/></li>
        <li id="prev4" class="col-lg-4 row theme-prev"><img src="/resources/img/theme4.png" alt=""/></li>
        <li id="prev5" class="col-lg-4 row theme-prev"><img src="/resources/img/theme5.png" alt=""/></li>
    </ul>
</div>
<style>
    #themeSelect {
        width:30%;
        display:inline-block;   
        list-style: none;
        font-size:20px; 
    } 
    
    #themeSelect li:hover {
        color:#0e80e5;
        cursor:pointer;
    }
        
    #optionContainer{
        width:30%;
    }
        
    #theme-preview > li {
        margin-top:20px;
        list-style: none;
    }  
    #theme-preview > li >img {
        width:480px;
        height:320px;
    }
</style>
<script src="../../../resources/js/settings.js"></script>