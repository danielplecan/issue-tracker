$(document).ready(function() {
    $("#addCommentButton").click(function() {
        $(this).addClass("hidden");
        $("#innerCommentDiv").removeClass("hidden");
        $("#submitComment").removeClass("hidden");
    });

    $("#submitComment").click(function() {
        var issueId = $("#issueState").attr('data-id');
        $(".commentError").empty();
        $("#submitComment").attr("disabled", "disabled");
        issueTrackerService.addComment(issueId, createCommentData()).done(function(data) {
            if (data.success) {
                var size = data.comments.length;
                for (var i = 0; i < size; i++) {
                    var comment = data.comments[i];
                    var commentContent = "<blockquote data-id=" + comment.id + "><p class=\"commentContent\"></p><small><a href=\"\">" +
                            comment.author.name + "</a> on  " + comment.dateFormat + "</small>" +
                            "</blockquote>";
                    $("#allComments").append(commentContent);
                    $("#allComments blockquote p").last().text(comment.content);
                    $("#innerCommentDiv").addClass("hidden");
                    $("#submitComment").addClass("hidden");
                    $("#addCommentButton").removeClass("hidden");
                    $("#textAreaComment").val('');
                }
            } else {
                $.each(data.errors, function(key, value) {
                    $("#" + key + "Error").append(value);
                });
            }

            $("#submitComment").removeAttr("disabled");
        });
    });

    $("#changeState-close").click(function(event) {
        event.preventDefault();

        var currentButton = $(this);
        var issueId = $("#issueState").attr("data-id");

        currentButton.attr("disabled", "disabled");
        currentButton.addClass('disabledButton');

        issueTrackerService.closeIssue(issueId).done(function(data) {
            if (data.success) {
                $('.changeStateButton').addClass("hidden");

                $("#issueState").text("CLOSED");
                $("#issueState").removeClass("label-success label-warning");
                $("#issueState").addClass("label-danger");

                $('#changeState-reopen').removeClass("hidden");
            }
            currentButton.removeAttr("disabled");
            currentButton.removeClass("disabledButton");
        });
    });

    $("#changeState-reopen").click(function(event) {
        event.preventDefault();

        var currentButton = $(this);
        var issueId = $("#issueState").attr("data-id");

        currentButton.attr("disabled", "disabled");
        currentButton.addClass('disabledButton');

        issueTrackerService.reopenIssue(issueId).done(function(data) {
            if (data.success) {
                $('.changeStateButton').addClass("hidden");

                $("#issueState").text("REOPENED");
                $("#issueState").removeClass("label-danger label-success");
                $("#issueState").addClass("label-warning");

                $("#changeState-close").removeClass("hidden");
            }
            currentButton.removeAttr("disabled");
            currentButton.removeClass("disabledButton");
        });
    });

    $("#assignButton").click(function(event){
        event.preventDefault();
        var issueId = $("#issueState").attr('data-id');
        var username = $("#assignTo").val();
        issueTrackerService.assignTo(issueId, username).done(function(data) {
            if (data.success) {
                console.log(data.assignedTo);
            }
        });
    });

    function createCommentData() {
        var commentContent = $("#textAreaComment").val();
        var lastComment = $("#allComments blockquote").last();
        var comment = {};
        if ($("#allComments blockquote").size() === 0) {
            comment['id'] = -10;
        } else {
            comment['id'] = lastComment.attr('data-id');
        }
        comment['content'] = commentContent;
        return comment;
    }
});

