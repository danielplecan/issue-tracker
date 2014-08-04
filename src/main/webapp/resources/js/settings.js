$(document).ready( function() { 
    
    var initialNotificationCheckbox = $("#settingsContainer").data("notification");
    var initialTheme = $("#settingsContainer").data("theme");
    
    $("#theme-preview > li").hide();       
    function settingsInitialize() {
        
        $(".toggleBox").attr("checked", initialNotificationCheckbox);
        $("#themeSelect li").each(function() {
            if ($(this).attr("value") != initialTheme) {
                $(this).hide();
            }
            else {
                $(this).show();
            }
        });
    }
    
    settingsInitialize();
    
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
    
    $("#themeSelect li").each(function(index, elem) {
        $(this).hover(function() {
            previewTheme($(this).attr("value"));
        },function() {
            restoreOriginalTheme(initialTheme);
        });
        $(this).click( function() {
            var currentThemeSelection = $(this).attr("value");
            
            $.ajax({
                type: 'POST',
                url: location.origin + '/settings/changeTheme/' + currentThemeSelection,
                dataType: "json",
                success:function(data) {
                    if (data.success) {
                        location.reload();
                    }
                }
            });
        });
    });
    
    $("#optionContainer").hover(function() {
        $("#themeSelect li").show(100);
    }, function() {
        $("#themeSelect li").hide(100, settingsInitialize);
        $(".theme-prev").hide();
    });
    
});

function previewTheme(theme) {
    $(".theme-prev").hide();
    
    var targetPrevId = "#prev" + theme;
    $(targetPrevId).show();
    $("#bootstrapTheme").removeAttr("href");
    $("#bootstrapTheme").attr("href", "/resources/css/theme/" + theme + "/bootstrap.css");
    console.log("href", "/resources/css/theme/" + theme + "/bootstrap.css");
    console.log("href", "/resources/css/theme/" + theme + "/styleColor.css");
    $("#colorTheme").removeAttr("href");
    $("#colorTheme").attr("href", "/resources/css/theme/" + theme + "/styleColor.css");
    $(".navbar").height(50); //hack
}


function restoreOriginalTheme(theme) {
    $("#bootstrapTheme").removeAttr("href");
    $("#bootstrapTheme").attr("href", "/resources/css/theme/" + theme + "/bootstrap.css");
    $("#colorTheme").removeAttr("href");
    $("#colorTheme").attr("href", "/resources/css/theme/" + theme + "/styleColor.css");
}