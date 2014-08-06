(function editIssue() {

    var theLabels = [];
    var widget = uploadWidget($("#existingFiles"));
    widget.initialize($('#editIssueId').text());

    $("#addNewLabelToAnIssue").click(function() {
        $(this).hide();
        $("#newLabelInput").show();
        $("#cancelAddNewLabel").show();
        if (theLabels.length === 0) {
            issueTrackerService.getAllLables().done(function(data) {
                theLabels = data.labels;
                $("#newLabelInput").keyup();
            });
        }
    });

    $("#cancelAddNewLabel").click(function() {
        $(this).hide();
        $("#newLabelInput").hide();
        $("#addNewLabelToAnIssue").show();
        $("#labelsSugestions").empty();
    });

    $("#newLabelInput").keyup(function() {
        $("#labelsSugestions").empty();
        var inputValue = $(this).val();
        theLabels.forEach(function(entry) {
            var exists = false;
            $("#modifiedIssueLabelsList > span").each(function(index, element) {
                if ($(element).text().trim() === entry.name) {
                    exists = true;
                }
            });

            if (entry.name.indexOf(inputValue) > -1 && exists === false) {
                var newLabel = createNewLabel(entry.name, entry.id, entry.color, "label label-default labelEditLabels", "black", false)
                $("#labelsSugestions").append(newLabel);
            }
        });
    });

    $("#labelsSugestions").delegate("span", "click", function() {
        var $this = $(this);
        var newLabel = createNewLabel($this.text(), $this.data('id'), $this.data('color'), "label label-warning labelEditLabels", "black", true)
        $("#modifiedIssueLabelsList").append(newLabel);
        $this.remove();
    });

    $("#modifiedIssueLabelsList").delegate("span > span", "click", function() {
        $(this).parent().remove();
        if ($('#cancelAddNewLabel').attr('style') !== 'display: none;')
            $("#newLabelInput").keyup();
    });

    $("#editTheIssueButton").click(function() {
        $('#editIssueTitle').val($('#oldIssueTitle').text().trim());
//        $('#editIssueContent').val($('#issueContent').text().trim());
        $('#viewTheIssue').hide("slow");
        $('#editTheIssue').show("slow");

        $('#modifiedIssueLabelsList').empty();
        $('#labelContainer > span').each(function() {
            var $this = $(this);
            var newLabel = createNewLabel($this.text(), $this.data('id'), $this.data('color'), "label label-warning labelEditLabels", "black", true);
            $('#modifiedIssueLabelsList').append(newLabel);
        });
    });

    $('#saveChangesEdit').click(function() {
        var newEditLabels = [];
        var newEditIssueId = $('#editIssueId').text();
        var newEditTitle = $('#editIssueTitle').val();
        var newEditContent = $('#editIssueContent').val();
        var newEditAttachments = widget.getUploadedFiles();

        $('#modifiedIssueLabelsList > span').each(function(index) {
            newEditLabels.push($(this).data('id'));
        });

        issueTrackerService.editIssue(newEditIssueId, newEditTitle, newEditContent, newEditLabels, newEditAttachments).done(function(data) {
            if (data.success) {
                $('#oldIssueTitle').text(data.editedIssue.title);
                $('#oldIssueLastUpdate').text(data.editedIssue.lastUpdateDate);
                $("#issueContent").empty();
                $("#issueContent").append($(markdown.toHTML(data.editedIssue.content)));
                var contentLength = data.editedIssue.content.length;

                if (contentLength !== 0) {
                    $('#issueContent').parent().show();
                }
                else {
                    $('#issueContent').parent().hide();
                }

                $('#labelContainer').empty();
                $('#attachmentsContainer').empty();

                if (data.editedLabels !== null) {
                    for (var i = 0; i < data.editedLabels.length; i++) {
                        var entry = data.editedLabels[i];
                        var oldLabel = createNewLabel(entry.name, entry.id, entry.color, "label label-warning", "black", false);
                        $('#labelContainer').append(oldLabel);
                    }
                }

                if (data.editedAttachments !== null) {
                    for (var i = 0; i < data.editedAttachments.length; i++) {
                        $('#attachmentsContainer').append(createAtachment(data.editedAttachments[i]));
                    }
                }

                $('#viewTheIssue').show("slow");
                $('#editTheIssue').hide("slow");
            }
            else {
                var createIssueErrors = "";
                $.each(data.errors, function(key, value) {
                    createIssueErrors += value;
                    createIssueErrors += "\n";
                });
                $("#editIssueErrors").text(createIssueErrors);
                ;
            }
        });
    });

    $('#discardChangesEdit').click(function() {
        issueTrackerService.removeOrphanAttachments(widget.getUploadedFiles());
        $('#viewTheIssue').show("slow");
        $('#editTheIssue').hide("slow");
    });

    function createAtachment(attachment) {
        var a = $('<a/>');
        a.attr('href', '/attachment/download/' + attachment.id);

        var span = $('<span/>');
        span.attr('class', 'btn btn-default attachmentWidth');

        var innerSpan = $('<span/>');
        innerSpan.attr('class', 'buttontext');
        innerSpan.text(attachment.originalName);

        span.append(innerSpan);
        a.append(span);

        return a;
    }

    function createNewLabel(text, id, color, labelClass, textColor, removeButton) {
        var label = $('<span/>')
        label.addClass(labelClass);
        label.text(text);
        label.attr("data-color", color);
        label.attr('data-id', id);
        label.css("margin-right", "3px");
        label.css("background-color", color);
        label.css("color", textColor);
        label.attr("style", "color:" + textColor + ";margin-right:3px;background-color:" + color);

        if (removeButton === true) {
            label.append('<span class="glyphicon glyphicon-remove"></span>');
        }
        return label;
    }

})();
