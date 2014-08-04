<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Issue Tracker</title>
        <meta charset="utf-8">

        <script src="/resources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
        <script src="/resources/js/bootstrap.js" type="text/javascript"></script>
        <script src="/resources/js/typeahead.bundle.js" type="text/javascript"></script>
        <script src="/resources/js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="/resources/js/jquery.ui.widget.js" type="text/javascript"></script>
        <script src="/resources/js/jquery.iframe-transport.js" type="text/javascript"></script>
        <script src="/resources/js/jquery.fileupload.js" type="text/javascript"></script>
        <script src="/resources/js/jquery.fileupload-process.js" type="text/javascript"></script>
        <script src="/resources/js/jquery.fileupload-validate.js" type="text/javascript"></script>
        
        
        <script src="/resources/js/markdown.js" type="text/javascript"></script>
        <script src="/resources/js/to-markdown.js" type="text/javascript"></script>
        <script src="/resources/js/bootstrap-markdown.js" type="text/javascript"></script>
        <script src="/resources/js/jquery.hotkeys.js" type="text/javascript"></script>
        <script src="/resources/js/issueTrackerService.js" type="text/javascript"></script>
        <script src="/resources/js/autocomplete.js" type="text/javascript"></script>
        <script src="/resources/js/uploadWidget.js" type="text/javascript"></script>
        <script src="/resources/js/issue.js" type="text/javascript"></script>
        


        <!-- ${theme} -->
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-rc2/css/bootstrap-glyphicons.css" rel="stylesheet" type="text/css"/>


        <sec:authorize var="loggedIn" access="isAuthenticated()" />
        <c:choose>
            <c:when test="${loggedIn}">
                <link id="bootstrapTheme" href="/resources/css/theme/${sessionScope.theme}/bootstrap.css" rel="stylesheet" type="text/css"/>
                <link id="colorTheme" href="/resources/css/theme/${sessionScope.theme}/styleColor.css" rel="stylesheet" type="text/css"/>
            </c:when>
            <c:otherwise>
                <link href="/resources/css/theme/1/bootstrap.css" rel="stylesheet" type="text/css"/>
                <link href="/resources/css/theme/1/styleColor.css" rel="stylesheet" type="text/css"/> 
            </c:otherwise>
        </c:choose>


        <link href="/resources/css/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <tiles:insertAttribute name="header" />

        <div id="content" class="container" >
            <tiles:insertAttribute name="body" />
        </div>

        <tiles:insertAttribute name="footer" />
    </body>

</html>
