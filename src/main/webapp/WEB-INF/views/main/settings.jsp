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

Change theme:<br/>
<select class="form-control" id="themeSelect">
    <option value="1">light (default)</option>
    <option value="2">dark</option>
    <option value="3">roz</option>
    <option value="4">idk</option>
    <option value="5">classy</option>
</select>

<script>
    $(".omg").attr("checked", ${initialNotificationCheckbox});

    $("#toggleNotifications").click(function() {

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
    
    $("#themeSelect").change( function() {
        var currentThemeSelection = $("#themeSelect option:selected").attr("value");
        console.log(currentThemeSelection);
        
        $.ajax({
            type: 'POST',
            url: location.origin + '/settings/changeTheme/' + currentThemeSelection,
            dataType: "json",
            }
        });
        
    });
    
</script>
