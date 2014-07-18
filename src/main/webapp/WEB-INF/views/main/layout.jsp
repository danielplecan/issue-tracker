<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
    <head>
        <meta charset="utf-8">
        <script src="/resources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
        <script src="/resources/js/bootstrap.js" type="text/javascript"></script>

        <link href="/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>

        <title>Issue Tracker</title>
    </head>

    <body>

        <tiles:insertAttribute name="header" />
        <div id="content" class="container" >
            <tiles:insertAttribute name="body" />
        </div>

    </body>

    <tiles:insertAttribute name="footer" />

</html>
