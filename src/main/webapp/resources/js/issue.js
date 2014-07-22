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
        });
    });
    
    $(".changeStateButton").click(function(event) {
        event.preventDefault();
        var stateAction = $(this).attr("id").split("-")[1];
        var issueId = $("#issueState").attr("data-id");
        
        var currentButton = $(this);
        currentButton.attr("disabled", "disabled");
        currentButton.addClass('disabledButton');
        
        issueTrackerService.changeStateOfIssue(issueId, stateAction).done(function(data) {
            if (data.success) {
                $('.changeStateButton').addClass("hidden");
                
                if(stateAction === "close") {
                    $("#issueState").text("CLOSED");
                    $("#issueState").removeClass("label-success label-warning");
                    $("#issueState").addClass("label-danger");
                    
                    $('#changeState-reopen').removeClass("hidden");
                } else {
                    $("#issueState").text("REOPENED");
                    $("#issueState").removeClass("label-danger");
                    $("#issueState").addClass("label-warning");
                    
                    $("#changeState-close").removeClass("hidden");
                }
                currentButton.removeAttr("disabled");
                currentButton.removeClass("disabledButton");
            }
        });
    });
});

