<%-- 
    Document   : header
    Created on : Jul 17, 2014, 10:17:16 AM
    Author     : iapavaloaie
--%>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/home">Issue Tracker</a>
        </div>
        <div id="navbar-main" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/create-issue">Create an issue</a>
                </li>
                <li>
                    <a href="/issues">View All Issues</a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a id="themes" href="#" data-toggle="dropdown" class="dropdown-toggle">username<span class="caret"></span></a>
                    <ul aria-labelledby="themes" class="dropdown-menu">
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">My issues</a></li>
                        <li><a href="#">Assigned Issues</a></li>
                        <li class="divider"></li>
                        <li><a href="/logout">Log out</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
