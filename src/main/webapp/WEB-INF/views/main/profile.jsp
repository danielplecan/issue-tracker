<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="panel panel-default col-lg-4 profile-panel">
    <div class="panel-body">
        <img src="/resources/img/bug2.png" alt="Issue Tracker"  class="profile-picture">
    </div>
    <table class="table table-striped col-lg-4">
        <tr>
            <td>Username: ${user.username}</td>
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
            <br/><i>PAGE UNDER CONSTRUCTION - coming soon.</i>
            <br/>These are some status bars that will measure what part of your posted issues are open, closed and reopened.<br/><br/>
            <span>Open issues</span>

            <div class="progress">
                <div class="progress-bar progress-bar-success" style="width: 10%"></div>
            </div>
            <span>Closed issues</span>

            <div class="progress">
                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
            </div>
            <span>Reopened issues</span>
            <div class="progress">


                <div class="progress-bar progress-bar-warning" style="width: 30%"></div>
            </div>
        </div>
        <div class="tab-pane fade" id="assigned">
            <p><br/><i>PAGE UNDER CONSTRUCTION - coming soon.</i><br/>Here there will be all the issues that were assigned to you</p>
        </div>
        <div class="tab-pane fade" id="posted">
            <p><br/><i>PAGE UNDER CONSTRUCTION - coming soon.</i><br/>Here there will be all the issues that you posted</p>
        </div>
    </div>
</div>
