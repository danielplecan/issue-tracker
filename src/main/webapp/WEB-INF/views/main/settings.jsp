<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="position:relative;">
    <div>
        
        </choose>
        <div class="checkbox">
            <label>
                <input type="checkbox" id="toggleNotifications" class="toggleBox"> Receive notifications 
            </label>
        </div>
        
    </div>
    <div id="optionContainer">
        Change theme:<br/>
        <ul id="themeSelect">
            <li value="1" id="op1">Light(default)</li>
            <li value="2" id="op2">Dark</li>
            <li value="3" id="op3">Modern</li>
            <li value="4" id="op4">Cold</li>
            <li value="5" id="op5">Silvery</li>
            <li value="6" id="op6">Pink</li>
            
        </ul>
    </div>
    <ul id="theme-preview">
        <li id="prev1" class="col-lg-4 row theme-prev"><img src="../../../resources/img/theme1.png" alt=""/></li>
        <li id="prev2" class="col-lg-4 row theme-prev"><img src="../../../resources/img/theme2.png" alt=""/></li>
        <li id="prev3" class="col-lg-4 row theme-prev"><img src="../../../resources/img/theme3.png" alt=""/></li>
        <li id="prev4" class="col-lg-4 row theme-prev"><img src="../../../resources/img/theme4.png" alt=""/></li>
        <li id="prev5" class="col-lg-4 row theme-prev"><img src="../../../resources/img/theme5.png" alt=""/></li>
        <li id="prev6" class="col-lg-4 row theme-prev"><img src="../../../resources/img/theme6.png" alt=""/></li>
    </ul>
</div>
<style>
    #themeSelect {
        width:30%;
        display:inline-block;        
    } 
        
    #themeSelect li {
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

<script>
       
$("#theme-preview > li").hide();       
function settingsInitialize() {
        
    $(".toggleBox").attr("checked", ${initialNotificationCheckbox});
    var initialTheme = (${initialTheme});
    $("#themeSelect li").each(function() {
        if ($(this).attr("value") != initialTheme) {
            $(this).hide();
        }
        else {
            $(this).show();
        }
    });
}
    
</script>
<script src="../../../resources/js/settings.js"></script>