<head>
    <meta charset="utf-8">
    <script src="../resources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="../resources/js/bootstrap.js" type="text/javascript"></script>
    <link href="../resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/css/style.css" rel="stylesheet" type="text/css"/>
    
    
    <title>Issue #${issue.getId()}}</title>
</head>



<div>
    
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3>${issue.getTitle()}</h3>
            <h5>Status: <span id="issueState"  data-id="${issue.getId()}">${issue.getState()}</span> </h5>
            
            <div style="display: block; height:30px;">
                <button type="button" class="btn btn-primary btn-sm" id="changeState-open">Open</button>
                <button type="button" class="btn btn-primary btn-sm" id="changeState-close">Close</button>
                <button type="button" class="btn btn-primary btn-sm" id="changeState-reopen">Reopen</button>
            </div>
        </div>
        <div class="panel-body">
            <p class="lead"> ${issue.getContent()}</p>
            </br>Posted by <span class="text-primary"> ${issue.getOwner().getName()}</span></br>
            Date: <span class="text-primary"> ${issue.getDate()}</span>
        </div>
    </div>
    
</div>

<script type="text/javascript">
    var currentState = $("#issueState");
    if (currentState.text() == "OPEN"){
        currentState.addClass("label label-success");
        $("#changeState-open").hide();
        $("#changeState-reopen").hide();
    }
    if (currentState.text() == "CLOSED"){
        currentState.addClass("label label-danger");
        $("#changeState-close").hide();
        $("#changeState-open").hide();
    }
    if (currentState.text() == "REOPENED"){
        currentState.addClass("label label-warning");
        $("#changeState-reopen").hide();
        $("#changeState-open").hide();
    }
</script>
<script src="../resources/js/changeStateModule.js" type="text/javascript"></script>
