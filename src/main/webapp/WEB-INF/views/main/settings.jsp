<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    
</choose>
<div class="checkbox">
    <label>
        <input type="checkbox" id="toggleNotifications" class="omg"> Receive notifications 
    </label>
</div>
    
</div>
    
    
<script> 
    $(".omg").attr("checked", ${initialNotificationCheckbox} );
    
    $("#toggleNotifications").click( function() {
        
        $.ajax({
            type: 'POST',
            url: location.origin + '/settings/toggleNotifications',
            dataType: "json",
            success: function(data) {
                
                $("#test").text(data.value);
                if (data.value === true) {
                    $("#toggleNotifications").checked = true;
                }
                else {
                    $("#toggleNotifications").checked = false;
                }
            }
        });
        
    });
</script>