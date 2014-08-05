<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="position:relative;" data-notification="${initialNotificationPosted}" data-theme=${initialTheme}  data-assigned="${initialNotificationAssigned}" id="settingsContainer">
    <div>
        <div class="checkbox">
            <label>
                <input type="checkbox" id="toggleNotifications" class="toggleBox"> Notifications for owned issues
            </label>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" id="toggleNotificationsAssigned" class="toggleBoxAssigned"> Notifications for assigned issues
            </label>
        </div>
        
    </div>
     <button type="button" class="btn btn-default" id="changeTheme">Change Theme</button>
     <div id="optionContainer">
         <p>Change theme:</p>
         <ul id="themeSelect" class="nav nav-pills nav-stacked">
             <li value="0" id="op0"><a>Light(default)</a></li>
             <li value="1" id="op1"><a>Dark</a></li>
             <li value="2" id="op2"><a>Cold</a></li>
                
         </ul></br>
         <button type="button" class="btn btn-default" id="preview">Preview</button>
         <button type="button" class="btn btn-default" id="set">Set theme</button>
         <button type="button" class="btn btn-default" id="cancel">Cancel</button>
     </div>
     <a href="/edit-profile"><button type="button" class="btn btn-default">Edit user profile</button></a>
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
</style>
<script src="../../../resources/js/settings.js"></script>