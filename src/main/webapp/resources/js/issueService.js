$(document).ready(function() {
    $("#addCommentButton").click(function() {
        $(this).addClass("hidden");
        $("#innerCommentDiv").removeClass("hidden");
        $("#submitComment").removeClass("hidden");
    });

    $("#submitComment").click(function() {
        
    });
});

$("#submitComment").click(function() {
    //send request to server      
    var valueForComment = $("#textAreaComment").val();
    console.log(valueForComment);

    var action = 'add-comment';
    var issueId = $("#issueState").attr('data-id');
    var url = location.origin + '/issue/' + issueId + '/' + action;

    $.ajax({
        type: 'POST',
        url: url,
        contentType: "application/json; charset=utf-8",
        data: valueForComment,
        dataType: "json",
        statusCode: {
            201: function(msg) {
                var commentPattern = "<blockquote><p>" + msg.content + "</p>" +
                        " <small><a href=\"\">" + msg.author.name + "</a> on  " + msg.date + "</small>" +
                        "</blockquote>";
                $("#allComments").append(commentPattern);

                $("#innerCommentDiv").hide();
                $("#submitComment").hide();
                $("#addCommentButton").show();
            },
            404: function() {
                console.log("400");
            }
        }
    });
});




//console.log(valueForComment);
//add the comment to page 
//        var commentPattern = "<blockquote><p>" + valueForComment + "</p>" +
//                " <small>Test on  18-07-2014 17:35:04</small>" +
//                "</blockquote>";
//        $("#allComments").append(commentPattern);
//
//        $("#innerCommentDiv").hide();
//        $("#submitComment").hide();
//        $("#addCommentButton").show();



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