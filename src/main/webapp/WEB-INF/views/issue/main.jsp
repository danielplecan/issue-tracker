<head>
    <meta charset="utf-8">
    <script src="../resources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="../resources/js/bootstrap.js" type="text/javascript"></script>
    <link href="../resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/css/style.css" rel="stylesheet" type="text/css"/>
        
    <title>Issue #${issue.getId()}}</title>
</head>



<div data-id="${issue.getId()}">
    
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3>${issue.getTitle()}</h3>
            <h5>Status: <span id="issueState">${issue.getState()}</span> </h5>
                
            <div style="display: block; height:30px;">
                <button type="button" class="btn btn-primary btn-sm" id="changeState">Change state</button>
                <label id="confirmLabel">Are you sure?</label>
                <button type="button" class="btn btn-primary btn-xs" id="confirmButton">I am sure!</button>
            </div>
        </div>
        <div class="panel-body">
            <p class="lead"> ${issue.getContent()}</p>
            </br>Posted by <span class="text-primary"> ${issue.getOwner().getName()}</span></br>
            Date: <span class="text-primary"> ${issue.getDate()}</span>
        </div>
    </div>
        
</div>

<script>
    var element = $("#issueState");
    element.removeClass("label label-success");
    element.removeClass("label label-warning");
    element.removeClass("label label-danger");
    
    if (element.text() == "OPEN"){
        element.addClass("label label-success");
    }
    if (element.text() == "REOPENED"){
        element.addClass("label label-warning");
    }
    if (element.text() == "CLOSED"){
        element.addClass("label label-danger");
    }
    
    var confLabel = $("#confirmLabel");
    var confButton = $("#confirmButton");
    confLabel.hide();
    confButton.hide();
    
    $("#changeState").click(function() {
        $(this).hide();
        confLabel.show();
        confButton.show();
    })
    
    $("#confirmButton").click(function() {
        $("#changeState").show();
        confLabel.hide();
        $(this).hide();
    })
</script>