<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!--<!DOCTYPE html>-->
<html>
    <head>
        <title>Issue Tracker</title>
        
        <script src="/resources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
        <script src="/resources/js/bootstrap.js" type="text/javascript"></script>
        <script src="/resources/js/issueTrackerService.js" type="text/javascript"></script>
        <link href="/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
        <script src="/resources/js/registerValidator.js" type="text/javascript"></script>
    </head>
    <body>
        <tiles:insertAttribute name="header" />
        
        <div id="content" class="container" >
            <tiles:insertAttribute name="body" />
        </div>
        
        <tiles:insertAttribute name="footer" />
    </body>
</html>
