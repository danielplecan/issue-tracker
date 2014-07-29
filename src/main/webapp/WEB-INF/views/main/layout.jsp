<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Issue Tracker</title>
        <meta charset="utf-8">
        <script src="/resources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
        <script src="/resources/js/bootstrap.js" type="text/javascript"></script>
        <script src="/resources/js/issueTrackerService.js" type="text/javascript"></script>
        <script src="/resources/js/issue.js" type="text/javascript"></script>
        <!-- ${theme} -->
        <link id="theme-bootstrap" href="/resources/css/theme/2/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-rc2/css/bootstrap-glyphicons.css" rel="stylesheet" type="text/css"/>
        <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
        <link id="theme-style-color" href="/resources/css/theme/2/styleColor.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <tiles:insertAttribute name="header" />
        
        <div id="content" class="container" >
            <tiles:insertAttribute name="body" />
        </div>
        
        <tiles:insertAttribute name="footer" />
    </body>
    <script>
        $.ajax({
            type: 'POST',
            url: location.origin + '/initialThemeConfig',
            dataType: "json",
            success:function(data) {
                //change theme
            }
        });
    </script>
</html>
