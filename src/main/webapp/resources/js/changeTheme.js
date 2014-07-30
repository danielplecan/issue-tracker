function changeTheme() {
    $.ajax({
        type: 'POST',
        url: location.origin + '/getCurrentThemeSetting',
        dataType: "json",
        success:function(data) {
            var themeBootstrapRef = "/resources/css/theme/" + data.currentTheme + "/bootstrap.css";
            var themeColorRef = "/resources/css/theme/" + data.currentTheme +"/styleColor.css";
            
            $("#theme-bootstrap").attr("href", themeBootstrapRef);
            $("#theme-style-color").attr("href", themeColorRef);
        }
    });   
}

changeTheme();
