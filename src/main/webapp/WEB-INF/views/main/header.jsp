<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container"> 
        <div class="logo-header"><img src="/resources/img/small.png" alt="Issue Tracker" width="50" height="50"></div>
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Issue Tracker</a>
        </div>
        <div id="navbar-main" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/about">About</a>
                </li>
                <li>
                    <a href="/issues">View All Issues</a>
                </li>
                <li>
                    <a href="/create-issue">Create an issue</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a id="themes" href="#" data-toggle="dropdown" class="dropdown-toggle"><sec:authentication property="principal.name" /><span class="caret"></span></a>
                    <ul aria-labelledby="themes" class="dropdown-menu">
                        <li><a id="profile-link">Profile</a></li>
                        <li><a href="#">My issues</a></li>
                        <li><a href="#">Assigned Issues</a></li>
                        <li class="divider"></li>
                        <li><a href="/settings">Settings</a></li>
                        <li><a href="/logout">Log out</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <span id="username"><sec:authentication property="principal.username" /></span>
</div>
<script>
    var username = $("#username").text();
    $("#username").hide();
    $("#profile-link").attr('href', '/profile/' + username);
</script>
