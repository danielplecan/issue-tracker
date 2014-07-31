function editIssue() {

    var theLabels = [];

    $("#addNewLabelToAnIssue").click(function() {
        $(this).hide();
        $("#newLabelInput").show();
        $("#cancelAddNewLabel").show();
        if (theLabels.length == 0) {
            $.ajax({
                url: location.origin + '/getAllLabels',
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
            if (entry.name.indexOf(inputValue) > -1 && ($("#modifiedIssueLabelsList").text()).indexOf(entry.name) < 0) {
                var newLabel = $('<span class="label label-default labelEditLabels"></span>');
                newLabel.text(entry.name);
                newLabel.data("data-color", entry.color);
                newLabel.attr('data-id', entry.id);
                newLabel.css("background-color", entry.color);
                newLabel.css("color", getContrastYIQ(entry.color));
                $("#labelsSugestions").append(newLabel);
            }
        });
    });

    $("#labelsSugestions").delegate("span", "click", function() {
        var newLabel = $('<span class="label btn-sm label-default labelEditLabels">  </span>');
        var $this = $(this);
        newLabel.text($this.text());
        newLabel.attr("data-color", $this.data("color"));
        newLabel.attr('data-id', $this.data("id"));
        newLabel.css("background-color", $this.data("color"));
        newLabel.append('<span class="glyphicon glyphicon-remove"></span>');

        $("#modifiedIssueLabelsList").append(newLabel);
        $this.remove();
    });

    $("#modifiedIssueLabelsList").delegate("span > span", "click", function() {
        $(this).parent().remove();
        $("#newLabelInput").keyup();
    });

    $("#editTheIssueButton").click(function() {
        $('#viewTheIssue').hide();
        $('#editTheIssue').show();
    });

    $('#saveChangesEdit').click(function() {
        $('#viewTheIssue').show();
        $('#editTheIssue').hide();
    });
    $('#discardChangesEdit').click(function() {
        $('#viewTheIssue').show();
        $('#editTheIssue').hide();
    });
}
editIssue();