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
    },function() {});
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

    
function previewTheme(theme) {
    $(".theme-prev").hide();
    $(".theme-prev").each( function() {
        var targetPrevId = "#prev" + theme;
        $(targetPrevId).show();
            
    });
}

