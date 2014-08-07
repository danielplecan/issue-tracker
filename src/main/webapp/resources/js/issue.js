var commentBuilder = (function() {
    var self = {};

    self.createFullCommentDiv = function(commentId) {
        var fullCommentDiv = $("<div class=\"fullCommentBody arrow_box col-lg-10 col-lg-offset-1\" style=\"clear:both;\"></div>");
        $(fullCommentDiv).attr('data-id', commentId);
        return fullCommentDiv;
    };

    self.createStateChangeDiv = function() {
        return $("<div class=\"commentStateChanged\"></div>");
    };

    self.createActionIcon = function(type) {
        var actionIcon;
        switch (type) {
            case 'OPEN':
                actionIcon = $('<span class=\"glyphicon-ok-circle openColor\">\n\
                                </span>');
                break;
            case 'CLOSE':
                actionIcon = $('<span class=\"glyphicon-remove-circle closedColor\">\n\
                                </span>');
                break;
            case 'TEXT':
                actionIcon = $(' <span class="glyphicon-comment">\n\
                                </span>');
                break;
        }
        $(actionIcon).addClass('glyphicon');

        return actionIcon;
    };

    self.createAuthorLink = function(username, name) {
        var authorLink = $('<a></a>').attr('href', '/profile/' + username).text(' ' + name);
        return authorLink;
    };

    self.createSimpleSpan = function() {
        return $('<span></span>');
    };

    self.createStateSpan = function(type) {
        var stateSpan;
        switch (type) {
            case 'OPEN':
                stateSpan = $('<span class=\"openColor\">open</span>');
                break;
            case 'CLOSE':
                stateSpan = $('<span class=\"closedColor\">closed</span>');
                break;
        }
        return stateSpan;
    };

    self.createCommentContent = function(comment) {
        var commentDiv = $('<div class=\"commentBlockThing\"></div>');
        if (comment.content.length !== 0) {
            var blockquote = $('<blockquote class=\"commentBlockquote\"><\/blockquote>');
            var pElement = $('<p class=\"commentContent\"><\/p>').append(markdown.toHTML(comment.content));
            var span = $('<span><\/span>').text('- on ' + comment.dateFormat);

            $(blockquote).append($(pElement));
            $(commentDiv).append($(blockquote));
            $(commentDiv).append($(span));
        }
        return commentDiv;
    };

    return self;
})();

var insertRecievedComments = null;

$(document).ready(function() {
    var widget = uploadWidget($("#commentFileUpload"));
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
                $('#oldIssueLastUpdate').text(" just now ");
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
                        $('#oldIssueLastUpdate').text(" just now ");
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
                        $('#oldIssueLastUpdate').text(" just now ");
                    } else {
                        $.each(data.errors, function(key, value) {
                            $("#" + key + "Error").append(value);
                        });
                    }
                    currentButton.removeAttr("disabled");
                    currentButton.removeClass("disabledButton");
                });
    });


//cosmina's autocomplete

    autocomplete.getAllUsersForAssignTo();
    $('#changeAssignButton').click(function() {
        $('#assignTo').val('');
        $('#scrollable-dropdown-menu').show('slow');
        $('#changeAssignButton').hide();
        $('#assignButton').hide();
        $('#cancelAssignButton').show();
        $('#clearAssignButton').hide();
    });

    $('#cancelAssignButton').click(function() {
        $('#scrollable-dropdown-menu').hide();
        if ($('#assignedName>a>i').text() !== 'none') {
            $('#clearAssignButton').show();
            $('#changeAssignButton').show();
        }
        else {
            $('#changeAssignButton').show();
        }
        $('#assignTo').val('');
    });
    var assignee;

    $("#assignTo").keydown(function(event) {
        var key = event.which;
        if (key !== 13) {
            $('#assignButton').hide();
            $('#cancelAssignButton').show();
        }
    });

    $('#assignTo').bind('typeahead:selected', function(obj, datum, name) {
        assignee = datum;
        $('#assignButton').show();
        $('#cancelAssignButton').show();
        $('#assigneeSpan').prop('data-id', datum.id);
    });

    $("#assignButton").click(function(event) {
        $('#scrollable-dropdown-menu').hide();
        $('#assignButtons').show();
        $('#changeAssignButton').show();
        $('#clearAssignButton').show();
        $('#changeAssignButton').text('Change');
        $('#assignTo').val('');
        if ($('#clearAssignButton').length === 0) {
            $('#assignButtons').append("<button id=\"clearAssignButton\" type=\"button\" class=\"btn btn-default btn-xs\" data-container=\"body\">Clear</button>");
        }
        event.preventDefault();
        var issueId = $("#issueState").attr('data-id');
        issueTrackerService.assignTo(issueId, assignee).done(function(data) {
            if (data.success) {
                $('#assignedName>a>i').text(data.assignedTo.username);
                $('#assignedName>a').attr('href', '/profile/' + data.assignedTo.username);
            }
        });
    });

    $('#assignButtons').delegate('#clearAssignButton', 'click', function(event) {
        event.preventDefault();
        $('#scrollable-dropdown-menu').hide();
        $('#assignButtons').show();
        $('#changeAssignButton').text('Assign');
        $('#changeAssignButton').show();
        $('#clearAssignButton').hide();
        $('#assignTo').val('');
        $('#assignedName>a').removeAttr('href');
        var issueId = $("#issueState").attr('data-id');
        issueTrackerService.assignTo(issueId, null).done(function(data) {
            if (data.success) {
                $('#assignedName>a>i').text('none');
            }
        });
    });

//comments
    function createCommentData() {
        var commentContent = $("#textAreaComment").val().trim();
        var lastComment = $("#allComments .fullCommentBody").last();
        var comment = {};
        if ($("#allComments .fullCommentBody").size() === 0) {
            comment['id'] = -10;
        } else {
            comment['id'] = lastComment.attr('data-id');
        }
        comment['content'] = commentContent;
        comment['attachments'] = widget.getUploadedFiles();
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

    insertRecievedComments = function(data) {
        var size = data.comments.length;
        for (var i = 0; i < size; i++) {
            var comment = data.comments[i];
            var stateChangeDiv = commentBuilder.createStateChangeDiv();
            var fullCommentDiv = commentBuilder.createFullCommentDiv(comment.id);
            var span = commentBuilder.createSimpleSpan();
            var authorLink = commentBuilder.createAuthorLink(
                    comment.author.username, comment.author.name
                    );

            //DANIEL
            var attachments = $("<div class='attachments' />");
            for (var j = 0; j < comment.attachments.length; j++) {
                var link = "/attachment/download/" + comment.attachments[j].id;
                var attachmentAnchor = $("<a></a>");
                $(attachmentAnchor).attr("href", link);
                var button = $("<span class='btn btn-default attachmentWidth'></span>");
                var buttonText = $("<span class='buttontext'></span>");
                $(buttonText).text(comment.attachments[j].originalName);
                $(button).append(buttonText);
                $(attachmentAnchor).append(button);
                $(attachments).append(attachmentAnchor);
            }


            if (comment.changeState !== null) {
                if (comment.content.length !== 0) {
                    switch (comment.changeState) {
                        case 'OPEN':
                            $(stateChangeDiv).append(commentBuilder.createActionIcon('OPEN'));
                            $(span).append(authorLink);
                            $(span).append(commentBuilder.createStateSpan('OPEN'));
                            break;
                        case 'CLOSED':
                            $(stateChangeDiv).append(commentBuilder.createActionIcon('CLOSE'));
                            $(span).append(authorLink);
                            $(span).append(commentBuilder.createStateSpan('CLOSE'));
                            break;
                    }
                    $(span).append(' and said :');
                } else {
                    switch (comment.changeState) {
                        case 'OPEN':
                            $(stateChangeDiv).append(commentBuilder.createActionIcon('OPEN'));
                            $(span).append(authorLink);
                            $(span).append(commentBuilder.createStateSpan('OPEN'));
                            break;
                        case 'CLOSED':
                            $(stateChangeDiv).append(commentBuilder.createActionIcon('CLOSE'));
                            $(span).append(authorLink);
                            $(span).append(commentBuilder.createStateSpan('CLOSE'));
                            break;
                    }
                    $(span).append(' on ' + comment.dateFormat + '.');
                }
                $(authorLink).after(' changed the state to ');
                stateChangeDiv.append($(span));
                if (comment.content.length === 0) {
                    $(attachments).addClass('stateChangeAttachment');
                    stateChangeDiv.append($(attachments));
                }
            } else {
                $(stateChangeDiv).append(commentBuilder.createActionIcon('TEXT'));
                $(stateChangeDiv).append(authorLink);
                $(stateChangeDiv).append(' said: ');
            }

            fullCommentDiv.append(stateChangeDiv);

            var commentContent = commentBuilder.createCommentContent(comment);
            fullCommentDiv.append(commentContent);
            $("#allComments").append(fullCommentDiv);
            $("#textAreaComment").val('');

            $(commentContent).find('blockquote').first().append(attachments);
        }
        widget.reset();
    };

    function clearErrorMessages() {
        $('#contentError').text("");
    }

    $('#textAreaComment').on('input', function() {
        var commentContent = $(this).val().trim();
        var size = commentContent.length;
        if ((size > 0 && size < 3) || size > 500) {
            $('#contentError').text('A comment must contain between 3 and 500 characters.');
        } else {
            $('#contentError').text('');
        }
    });

});
