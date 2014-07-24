<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="row">
    <div class="col-lg-10 col-lg-offset-1">
        <div><h3>Welcome to our site, <sec:authentication property="principal.name" />!</h3>
            <h4>This website is an issue tracker that makes it easier for users to clean their codes, find bugs, solve issues and what not.</h4>
        </div>
        <br/>
        <div class="panel panel-default">
            <div class="panel-body">
                If you have an issue with your code, our site is just the right thing for you. 
                You can create an issue, add labels, and then just sit back and wait for other people to find it and help you out! 
                After an issue is solved, you can change it's state from <i>open</i> to <i>close</i>, so that other people will know that your problem is solved. 
                You can also reopen an issue, if you've changed your mind.
                To post your issue, just click on <i>Create an issue</i>.
                <br/><br/>
                <a href="/create-issue"><button type="button" class="btn btn-default">Create an Issue</button></a>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-body">
                Our website is an open source, pay-it-forward type of community. 
                This is why it's very important not only to have your issues fixed, but also to help other people with their problems - if you know how. 
                To be able to see the issues people post, just click on <i>View all issues </i>. If you want to help them, post a comment to their issue.
                <br/><br/>
                <a href="/issues"><button type="button" class="btn btn-default">View All Issues</button></a>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-body">
                If you wanna find out more about who made this website, check out our <i>About</i> page!
                <br/><br/>
                <a href="/about"><button type="button" class="btn btn-default">About</button></a>
            </div>
        </div>
    </div>
</div>
