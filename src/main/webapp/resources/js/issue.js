function createCommentData() {
    var commentContent = $("#textAreaComment").val();
    var comment = {};
    comment['content'] = commentContent;
    return comment;
}
$(document).ready(function() {
    $("#addCommentButton").click(function() {
        $(this).addClass("hidden");
        $("#innerCommentDiv").removeClass("hidden");
        $("#submitComment").removeClass("hidden");
    });

    $("#submitComment").click(function() {
        var issueId = $("#issueState").attr('data-id');

        issueTrackerService.addComment(issueId, createCommentData()).done(function(data) {
            if (data.success) {
                var commentContent = "<blockquote>" +
                                        "<p>" + data.comment.content + "</p>" +
                                        "<small><a href=\"\">" + data.comment.author.name + "</a> on  " + data.comment.date + "</small>" +
                                     "</blockquote>";
                $("#allComments").append(commentContent);

                $("#innerCommentDiv").addClass("hidden");
                $("#submitComment").addClass("hidden");
                $("#addCommentButton").removeClass("hidden");
            }
            else {
//                   $.each(data.errors, function(key, value) {
//                        $("#" + key + "Error").append(value);
//                    }); 
            }
        });
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
});

