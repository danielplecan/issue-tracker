<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="row">
    <div class="col-lg-10 col-lg-offset-1">
        <div><h3>Welcome to our site, <sec:authentication property="principal.name" />!</h3>
            <h4>Here is a list of links that might be useful to you:</h4>
        </div>
        <br/>
        <div class="btn-group btn-group-justified">
            <a href="/about" class="btn btn-default">About</a>
            <a href="/issues" class="btn btn-default">View All Issues</a>
            <a href="/create-issue" class="btn btn-default">Create Issue</a>
        </div>
    </div>
</div>
