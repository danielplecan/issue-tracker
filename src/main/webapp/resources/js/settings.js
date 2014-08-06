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
            if ($(this).data("val") == initialTheme) {
                $(this).addClass("active");
            }
        });
    }

    $("#set").click( function() {
        $("#themeSelect li").each( function() {
            if ($(this).hasClass("active")) {
                issueTrackerService.changeTheme($(this).data("val"));
                previewTheme($(this).data("val"));
            }
        });
    });

    $("#preview").click( function() {
        $("#themeSelect li").each( function() {
            if ($(this).hasClass("active")) {
                previewTheme($(this).data("val"));
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
        selectTheme($(this).data("val")); 
   
    });

    function selectTheme(theme) {
        $("#themeSelect li").each(function(index, elem) {
            $(this).removeClass("active");
            if ($(this).data("val") == theme) {
                $("#op" + $(this).data("val")).addClass("active");
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
   
    $("#toggleNotificationsPosted").click(function() {
        issueTrackerService.toggleNotificationsForPosted(); 
    });
    
    $("#toggleNotificationsAssigned").click(function() {
        issueTrackerService.toggleNotificationsForAssigned(); 
    });
});
