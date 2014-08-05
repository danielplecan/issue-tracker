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
     <button type="button" class="btn btn-default" id="changeTheme">Change Theme</button>
     <div id="optionContainer">
         <p>Change theme:</p>
         <ul id="themeSelect" class="nav nav-pills nav-stacked">
             <li value="1" id="op1"><a>Light(default)</a></li>
             <li value="2" id="op2"><a>Dark</a></li>
             <li value="3" id="op3"><a>Cold</a></li>
             <li value="4" id="op4"><a>Salad</a></li>
             <li value="5" id="op5"><a>Pink</a></li>
                
         </ul></br>
         <button type="button" class="btn btn-default" id="preview">Preview</button>
         <button type="button" class="btn btn-default" id="set">Set theme</button>
         <button type="button" class="btn btn-default" id="cancel">Cancel</button>
     </div>
    
    <ul id="theme-preview">
        <li id="prev1" class="col-lg-4 row theme-prev">
            <img src="/resources/img/theme1.png" alt=""/>
        </li>
        <li id="prev2" class="col-lg-4 row theme-prev"><img src="/resources/img/theme2.png" alt=""/>
        </li>
        <li id="prev3" class="col-lg-4 row theme-prev"><img src="/resources/img/theme3.png" alt=""/>
        </li>
        <li id="prev4" class="col-lg-4 row theme-prev"><img src="/resources/img/theme4.png" alt=""/>
        </li>
        <li id="prev5" class="col-lg-4 row theme-prev"><img src="/resources/img/theme5.png" alt=""/>
        </li>
    </ul>
</div>
<style>
    #themeSelect {
        width:30%;
        display:inline-block;   
        list-style: none;
        font-size:20px; 
    } 
    
    #themeSelect li {
        width:200px;
        display:inline-block;
    }
    
    #themeSelect li:hover {
        color:#0e80e5;
        cursor:pointer;
    }
        
    #optionContainer{
        width:30%;
        display:inline-block;
    }
    
    #theme-preview > li {
        list-style: none;
        width:600px;
        height:400px;
    }  
    #theme-preview > li >img {
/*        width:30%;
        height:30%;
        margin:3%;*/
    }
    
    #theme-preview {
        display:inline-block;
        float:right;
        margin-right:15%;
    }
</style>
<script src="../../../resources/js/settings.js"></script>