$(document).ready( function() { 
    var initialNotificationCheckbox = $("#settingsContainer").data("notification");
    var initialNotificationAssigned = $("#settingsContainer").data("assigned");
    var initialTheme = $("#settingsContainer").data("theme");
    
    $(".toggleBox").attr("checked", initialNotificationCheckbox);
    $(".toggleBoxAssigned").attr("checked", initialNotificationAssigned);
   
    $("#optionContainer").hide();
   
    $("#changeTheme").click( function() {
        $("#optionContainer").show(100);
        $(this).hide(100);
    });
   
    settingsInit();

    function settingsInit() {
        $(".toggleBox").attr("checked", initialNotificationCheckbox);
        $("#themeSelect li").each(function() {
            if ($(this).attr("value") == initialTheme) {
                $(this).addClass("active");
            }
        });
    }

    $("#set").click( function() {
        $("#themeSelect li").each( function() {
            if ($(this).hasClass("active")) {
                issueTrackerService.changeTheme($(this).attr("value"));
                previewTheme($(this).attr("value"));
            }
        });
    });

    $("#preview").click( function() {
        $("#themeSelect li").each( function() {
            if ($(this).hasClass("active")) {
                previewTheme($(this).attr("value"));
            }
        });
    });

    $("#cancel").click( function() {
        restoreTheme(); 
        selectTheme(initialTheme);
        $("#optionContainer").hide(100);
        $(this).show(100);
        $("#changeTheme").show();
    });

    $("#themeSelect li").click( function() {
        selectTheme($(this).attr("value")); 
   
    });

    function selectTheme(theme) {
        $("#themeSelect li").each(function(index, elem) {
            $(this).removeClass("active");
            if ($(this).attr("value") == theme) {
                $("#op" + $(this).attr("value")).addClass("active");
            }
        });
    }

    function previewTheme(theme) {
        console.log("preview: " + theme);
        $("#bootstrapTheme").removeAttr("href");
        $("#bootstrapTheme").attr("href", "/resources/css/theme/" + theme + "/bootstrap.css");
        $("#colorTheme").removeAttr("href");
        $("#colorTheme").attr("href", "/resources/css/theme/" + theme + "/styleColor.css");
    }

    function restoreTheme() {
        previewTheme(initialTheme);
    }
   
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
    
    $("#toggleNotificationsAssigned").click(function() {
        $.ajax({
            type: 'POST',
            url: location.origin + '/settings/toggleNotificationsAssigned',
            dataType: "json",
            success: function(data) {
                
                $("#test").text(data.value);
                if (data.value === true) {
                    $("#toggleNotificationsAssigned").checked = true;
                }
                else {
                    $("#toggleNotificationsAssigned").checked = false;
                }
            }
        }); 
    });
});
