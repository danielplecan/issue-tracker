(function editIssue() {

    var theLabels = [];
    var widget = uploadWidget($("#existingFiles"));
    widget.initialize($('#editIssueId').text());

    $("#addNewLabelToAnIssue").click(function() {
        $(this).hide();
        $("#newLabelInput").show();
        $("#cancelAddNewLabel").show();
        if (theLabels.length === 0) {
            $.ajax({
                url: location.origin + '/getAllLabels'
            }).done(function(data) {
                theLabels = data.labels;
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
               if($(element).text().trim() === entry.name) {
                   exists = true;
               }
            });

            if (entry.name.indexOf(inputValue) > -1 && exists === false) {
                var newLabel = $('<span class="label label-default labelEditLabels"></span>');
                newLabel.text(entry.name);
                newLabel.attr("data-color", entry.color);
                newLabel.attr('data-id', entry.id);
                newLabel.css("background-color", entry.color);
                newLabel.css("color", getContrastYIQ(entry.color));
                newLabel.css("margin-right", "3px;");

                $("#labelsSugestions").append(newLabel);
            }
        });
    });

    $("#labelsSugestions").delegate("span", "click", function() {
        var newLabel = $('<span class="label label-warning labelEditLabels">  </span>');
        var $this = $(this);
        newLabel.text($this.text());
        newLabel.attr("data-color", $this.data("color"));
        newLabel.attr('data-id', $this.data("id"));
        newLabel.css("background-color", $this.data("color"));
        newLabel.attr("style", "margin-right:3px;background-color:#D8FFC4;color:black");
        newLabel.append('<span class="glyphicon glyphicon-remove"></span>');

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
        $('#editIssueContent').val($('#issueContent').text().trim());
        $('#viewTheIssue').hide();
        $('#editTheIssue').show();
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
                $('#issueContent').text(data.editedIssue.content);
                $('#oldIssueLastUpdate').text(data.editedIssue.lastUpdateDate);
                $('#labelContainer').empty();

                if (data.editedLabels !== null) {
                    for (var i = 0; i < data.editedLabels.length; i++) {
                        $('#labelContainer').append(createOldLabelSpan(data.editedLabels[i]));
                    }
                }

                $('#viewTheIssue').show();
                $('#editTheIssue').hide();
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
        $('#viewTheIssue').show();
        $('#editTheIssue').hide();
    });


    function createOldLabelSpan(label) {
        var newLabel = $('<span class="label label-default ">  </span>');
        newLabel.text(label.name);
        newLabel.attr("data-color", label.color);
        newLabel.attr('data-id', label.id);
        newLabel.css("margin-right", "3px");
        newLabel.css("background-color", label.color);
        newLabel.css("color", getContrastYIQ(label.color));
        return newLabel;
    }

})();
