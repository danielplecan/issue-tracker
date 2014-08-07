<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <div class="logo-header"><img src="/resources/img/small${sessionScope.theme}.png" alt="Issue Tracker" class="bugLogoIssueTracker" width="50" height="50"></div>
            <a class="navbar-brand" href="/">IssueTracker</a>
            <button data-target="#navbar-main" data-toggle="collapse" type="button" class="navbar-toggle">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div id="navbar-main" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/about">About</a></li>
                <li><a href="/issues">View All Issues</a></li>
                <li><a href="/create-issue">Create an issue</a></li>
                <li><a href="/manage-labels">Manage Labels</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a id="themes" href="#" data-toggle="dropdown" class="dropdown-toggle">
                        ${sessionScope.user.username}
                        <span class="caret">
                        </span>
                    </a>
                    <ul aria-labelledby="themes" class="dropdown-menu">
                        <li><a href="/profile/${sessionScope.user.username}">Profile</a></li>
                        <li class="divider"></li>
                        <li><a href="/settings"><span class="glyphicon glyphicon-wrench"></span>  Settings</a></li>
                        <li><a href="/logout"><span class="glyphicon glyphicon-off"></span>  Log out</a></li>
                    </ul>
                </li>
                <li><img src="/resources/img/yellowBulb.png" id="lightBulbPic" title="Press to change theme" style="height:40px;width:45px;margin-top:5px;"/></li>
            </ul>
        </div>
    </div>                      
</div>