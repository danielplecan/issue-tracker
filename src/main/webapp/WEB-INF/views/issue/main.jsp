<head>
    <title>Issue #${issue.getId()}}</title>
</head>
<div class="col-lg-offset-1 col-lg-10">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3><span id="issueState" data-id="${issue.getId()}">${issue.getState()}</span>${issue.getTitle()}</h3>
            <div class="issueDateTime">
                Posted by <span class="text-primary"> ${issue.getOwner().getName()}</span> on
                <span class="text-primary"> ${issue.getDateFormat()} </span>
            </div>     
            <div >
                <button type="button" class="btn btn-primary btn-sm toggle" id="changeState-open">Open</button>
                <button type="button" class="btn btn-primary btn-sm toggle" id="changeState-close">Close</button>
                <button type="button" class="btn btn-primary btn-sm toggle" id="changeState-reopen">Reopen</button>
            </div>
        </div>
        <div class="panel-body">
            <h3>${issue.getContent()}</h3>   
        </div>
    </div>    

    <div id="allComments" class="list-group">
        <div class="list-group-item">
            <h4 class="list-group-item-heading">List group item heading</h4>
            <p class="list-group-item-text"><a href="">${issue.getOwner().getName()}</a> on ${issue.getDateFormat()}</p>
        </div>
        <div class="list-group-item">
            <h4 class="list-group-item-heading">First Comment</h4>
            <p class="list-group-item-text"><a href="">${issue.getOwner().getName()}</a> on ${issue.getDateFormat()}</p>
        </div>
        <div class="list-group-item">
            <h4 class="list-group-item-heading">List group item heading</h4>
            <p class="list-group-item-text"><a href="">${issue.getOwner().getName()}</a> on ${issue.getDateFormat()}</p>
        </div>
        <blockquote>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
            <small><a href="">${issue.getOwner().getName()}</a> on ${issue.getDateFormat()}</small>
        </blockquote>
        <blockquote>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
            <small><a href="">${issue.getOwner().getName()}</a> on ${issue.getDateFormat()}</small>
        </blockquote>
        <blockquote>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
            <small><a href="">${issue.getOwner().getName()}</a> on ${issue.getDateFormat()}</small>
        </blockquote>


    </div>          
    <div id="addComment">
        <button id="addCommentButton" title="" data-original-title="" type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="left" data-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus.">Add Comment</button>

    </div>

</div>


<script type="text/javascript">
    $("#addComment").append("<div id=\"innerCommentDiv\"> <textarea id=\"textAreaComment\" rows=\"4\" class=\"form-control\" placeholder=\"Insert your comment here...\"></textarea>        </div>       ");
    $("#addComment").append("<button id=\"submitComment\" class=\"btn btn-success\" type=\"button\">Comment</button>");
    $("#innerCommentDiv").hide();
    $("#submitComment").hide();

    $("#addCommentButton").click(function() {
        $(this).hide();
        $("#innerCommentDiv").show();
        $("#submitComment").show();
    });
    $("#submitComment").click(function() {
        //send request to server      
        var valueForComment = $("#textAreaComment").val();
        console.log(valueForComment);
        //add the comment to page 
        var commentPattern = "<blockquote><p>" + valueForComment + "</p>" +
                " <small>Test on  18-07-2014 17:35:04</small>" +
                "</blockquote>";
        $("#allComments").append(commentPattern);

        $("#innerCommentDiv").hide();
        $("#submitComment").hide();
        $("#addCommentButton").show();
    });


    var currentState = $("#issueState");
    if (currentState.text() === "OPEN") {
        currentState.addClass("label label-success");
        $("#changeState-open").hide();
        $("#changeState-reopen").hide();
    }
    if (currentState.text() === "CLOSED") {
        currentState.addClass("label label-danger");
        $("#changeState-close").hide();
        $("#changeState-open").hide();
    }
    if (currentState.text() === "REOPENED") {
        currentState.addClass("label label-warning");
        $("#changeState-reopen").hide();
        $("#changeState-open").hide();
    }

</script>
<script src="/resources/js/changeStateModule.js" type="text/javascript"></script>
