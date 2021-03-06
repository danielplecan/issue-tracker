<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="panel panel-default col-lg-4 profile-panel">
    <div class="panel-body">
        <img src="/resources/img/bug2.png" alt="Issue Tracker"  class="profile-picture">
    </div>
    <table class="table table-striped col-lg-4">
        <tr>
            <td >Username: <span data-usernameid="${user.id}" id="username">${user.username}<span/></td>
        </tr>
        <tr>
            <td>Name: ${user.name}</td>
        </tr>
        <tr>
            <td>Email: ${user.email}</td>
        </tr>
    </table>
</div>
<div class="col-lg-8">
    <ul class="nav nav-tabs profile-tab">
        <li class="active"><a href="#home" data-toggle="tab">Home</a>
        </li>
        <li><a href="#assigned" data-toggle="tab">Assigned issues</a>
        </li>
        <li><a href="#posted" data-toggle="tab">Posted issues</a>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade active in col-lg-8" id="home">
            <br/>These are some status bars that measure what part of your posted issues are open and closed.<br/><br/>
            <span class="col-lg-3">Open issues</span>
            <div id="numberOfOpenIssues" class="col-lg-9"></div>
            <div class="col-lg-12">
                <div class=" progress ">
                    <div class=" progress-bar progress-bar-success" id="openIssues" ></div>
                </div>
            </div>
            <span class="col-lg-3">Closed issues</span>

            <div id="numberOfClosedIssues" class="col-lg-9"></div>
            <div class="col-lg-12">
                <div class="progress ">
                    <div class=" progress-bar progress-bar-danger" id="closedIssues"></div>
                </div>
            </div>

        </div>     
        <div class="tab-pane fade col-lg-12" id="assigned">
            <p><br/>These are the issues that were assigned to you</p>
            <div class="  col-lg-12 issuesList"  id="allAsignedIssues"> 
            </div>
        </div>
        <div class="tab-pane fade col-lg-12" id="posted">
            <p><br/>These are the issues that you posted</p>
            <div class="  col-lg-12 issuesList"  id="allUserIssues"> 
            </div>
        </div>

    </div>
</div>
<script src="/resources/js/profile.js" type="text/javascript"></script>