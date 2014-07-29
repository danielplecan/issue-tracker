$(document).ready(function() {
    $('#textAreaComment').focus(function() {
        clearErrorMessages();
    });

    $("#submitComment").click(function() {
        var issueId = $("#issueState").attr('data-id');

        $(".commentError").empty();
        $("#submitComment").attr("disabled", "disabled");
        issueTrackerService.addComment(issueId, createCommentData()).done(function(data) {
            if (data.success) {
                insertRecievedComments(data);
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
        clearErrorMessages();

        var currentButton = $(this);
        var issueId = $("#issueState").attr("data-id");

        currentButton.attr("disabled", "disabled");
        currentButton.addClass('disabledButton');

        issueTrackerService.addComment(issueId, addCloseStateToCommentData(createCommentData()))
                .done(function(data) {
                    if (data.success) {
                        closeIssueSuccess();
                        insertRecievedComments(data);
                    } else {
                        $.each(data.errors, function(key, value) {
                            $("#" + key + "Error").append(value);
                        });
                    }
                    currentButton.removeAttr("disabled");
                    currentButton.removeClass("disabledButton");
                });
    });

    $("#changeState-open").click(function(event) {
        event.preventDefault();
        clearErrorMessages();

        var currentButton = $(this);
        var issueId = $("#issueState").attr("data-id");

        currentButton.attr("disabled", "disabled");
        currentButton.addClass('disabledButton');

        issueTrackerService.addComment(issueId, addOpenStateToCommentData(createCommentData()))
                .done(function(data) {
                    if (data.success) {
                        openIssueSuccess();
                        insertRecievedComments(data);
                    } else {
                        $.each(data.errors, function(key, value) {
                            $("#" + key + "Error").append(value);
                        });
                    }
                    currentButton.removeAttr("disabled");
                    currentButton.removeClass("disabledButton");
                });
    });


//helper methods
    var substringMatcher = function(strs) {
        return function findMatches(q, cb) {
            var matches, substrRegex;
            matches = [];
            substrRegex = new RegExp(q, 'i');

            $.each(strs, function(i, str) {
                if (substrRegex.test(str)) {
                    matches.push({value: str});
                }
            });

            cb(matches);
        };
    };
    
    var users = [];
    $("#assignTo").keyup(function() {
        var username = $("#assignTo").val();
        var issueId = $("#issueState").attr('data-id');
        issueTrackerService.getUsersAssignee(issueId, username)
                .done(function(data) {
                    if (data.success) {
                        var size = data.assignees.length;
                        while (users.length > 0) {
                            users.pop();
                        }
                        for (var i = 0; i < size; i++) {
                            users.push(data.assignees[i].username);
                        }
                    } else {
                        $.each(data.errors, function(key, value) {
                            $("#" + key + "Error").append(value);
                        });
                    }
                });
    })

    $('#scrollable-dropdown-menu .typeahead').typeahead({
        hint: true,
        highlight: true,
        minLength: 1
    },
    {
        name: 'users',
        displayKey: 'value',
        source: substringMatcher(users)
    });

    $("#assignButton").click(function(event) {
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
        var lastComment = $("#allComments .fullCommentBody").last();
        var comment = {};
        if ($("#allComments .fullCommentBody").size() === 0) {
            comment['id'] = -10;
        } else {
            comment['id'] = lastComment.attr('data-id');
        }
        comment['content'] = commentContent;
        return comment;
    }

    function addOpenStateToCommentData(comment) {
        comment['changeState'] = 'OPEN';
        return comment;
    }

    function addCloseStateToCommentData(comment) {
        comment['changeState'] = 'CLOSED';
        return comment;
    }

    function closeIssueSuccess() {
        $('.changeStateButton').addClass("hidden");

        $("#issueState").text("CLOSED");
        $("#issueState").removeClass("label-success");
        $("#issueState").addClass("label-danger");

        $('#changeState-open').removeClass("hidden");
    }

    function openIssueSuccess() {
        $('.changeStateButton').addClass("hidden");

        $("#issueState").text("OPEN");
        $("#issueState").removeClass("label-danger");
        $("#issueState").addClass("label-success");

        $("#changeState-close").removeClass("hidden");
    }

    function insertRecievedComments(data) {
        var size = data.comments.length;
        for (var i = 0; i < size; i++) {
            var comment = data.comments[i];
            var commentContent;
            var stateChangeDiv = "<div class=\"commentStateChanged\"></div>";
            var fullCommentDiv = $("<div class=\"fullCommentBody\" data-id=\"" + comment.id + "\"></div>");

            if (comment.changeState !== null) {
                if (comment.content.length !== 0) {
                    switch (comment.changeState) {
                        case 'OPEN':
                            stateChangeDiv = "<div class=\"commentStateChanged\">" +
                                    "<span class=\"glyphicon glyphicon-ok-circle openColor\"></span><span> <a href=\"/profile/"
                                    + comment.author.username + "\">" +
                                    comment.author.name +
                                    "</a> changed the state to <span class=\"openColor\">open</span> and said:</span>" +
                                    "</div>";
                            break;
                        case 'CLOSED':
                            stateChangeDiv = "<div class=\"commentStateChanged\">" +
                                    "<span class=\"glyphicon glyphicon-remove-circle closedColor\"></span><span> <a href=\"/profile/"
                                    + comment.author.username + "\">" +
                                    comment.author.name +
                                    "</a> changed the state to <span class=\"closedColor\">closed</span> and said:</span>" +
                                    "</div>";
                            break;
                    }
                } else {
                    switch (comment.changeState) {
                        case 'OPEN':
                            stateChangeDiv = "<div class=\"commentStateChanged\">" +
                                    "<span class=\"glyphicon glyphicon-ok-circle openColor\"></span><span> <a href=\"/profile/"
                                    + comment.author.username + "\">" +
                                    comment.author.name +
                                    "</a> changed the state to <span class=\"openColor\">open</span>.</span>" +
                                    "</div>";
                            break;
                        case 'CLOSED':
                            stateChangeDiv = "<div class=\"commentStateChanged\">" +
                                    "<span class=\"glyphicon glyphicon-remove-circle closedColor\"></span><span> <a href=\"/profile/"
                                    + comment.author.username + "\">" +
                                    comment.author.name +
                                    "</a> changed the state to <span class=\"closedColor\">closed</span>.</span>" +
                                    "</div>";
                            break;
                    }
                }
            }
            fullCommentDiv.append(stateChangeDiv);

            $("#allComments").append(fullCommentDiv);

            if (comment.content.length !== 0) {
                commentContent = $("<blockquote><p class=\"commentContent\"></p><small><a href=\"\">" +
                        comment.author.name + "</a> on  " + comment.dateFormat + "</small>" +
                        "</blockquote>");
                fullCommentDiv.append(commentContent);
                $("#allComments blockquote p").last().text(comment.content);
            }
            $("#textAreaComment").val('');
        }
    }

    function clearErrorMessages() {
        $('#contentError').text("");
    }

});


