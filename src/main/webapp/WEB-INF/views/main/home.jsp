<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="row">
    <div class="col-lg-10 col-lg-offset-1">
        <div><h3>Welcome to our site, <sec:authentication property="principal.name" />!</h3>
            <h4>This website is an issue tracker that makes it easier for users to clean their codes, find bugs, solve issues or what not.</h4>
        </div>
        <br/>
        <div class="panel panel-default">
            <div class="panel-body">
                If you have an issue with your code, our site is just the right thing for you. You can create an issue, add labels, and then just sit back and wait for other people to find it! After an issue is solved, you can change it's state from <i>open</i> to <i>close</i>, so that other people will know your problem is solved. You can also reopen an issue, if you've changed your mind.
                Just click on <i>Create issue</i>.
                <br/>
                <button type="button" class="btn btn-default"><a href="/create-issue">Create Issue</a></button>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-body">
                Our website is an open source, pay-it-forward type of community. This is why it's very important not only to have your issues fixed, but also to help other people with their problems - if you know how. Click on <i>View all issues </i>and you will be able to see the issues people post, and if you can help them, just post a comment to their issue.
                <br/>
                <button type="button" class="btn btn-default"><a href="/issues">View All Issues</a></button>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-body">
                If you wanna find out more about who made this website, check our <i>About</i> page!
                <br/>
                <button type="button" class="btn btn-default"><a href="/about">About</a></button>
            </div>
        </div>
    </div>
</div>
