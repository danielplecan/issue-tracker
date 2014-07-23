function getContrastYIQ(hexcolor) {
    var color = hexcolor;
    color = color.substring(1);           // remove #
    color = parseInt(color, 16);          // convert to integer
    color = 0xFFFFFF ^ color;             // invert three bytes
    color = color.toString(16);           // convert to hex
    color = ("000000" + color).slice(-6); // pad with leading zeros
    color = "#" + color;                  // prepend #
    return color;
}
$(document).ready(function() {

    //pressing the button for creating an issue
    $('#buttonCreateIssue').click(function() {
        var labelIdList = [];
        $('#labelSelector').find('.selectedLabel').each(function() {
            labelIdList.push($(this).attr('data-id'));
        });

        var currentButton = $(this);
        currentButton.attr('disabled', 'disabled');
        issueTrackerService.createIssue($('#textArea1').val(), $('#textArea2').val(), labelIdList).done(function(data) {
            if (data.success) {
                window.location.replace(data.url);
            }
            currentButton.removeAttr('disabled');
        });
    });

    //selecting an existing label
    $('#labelSelector').delegate('.list-item-text', 'click', function() {
        var labelColorToSet;
        $(this).toggleClass('selectedLabel');
        if ($(this).hasClass('selectedLabel')) {
            labelColorToSet = $(this).attr('data-color');
            $(this).css('color', getContrastYIQ(labelColorToSet));
        } else {
            labelColorToSet = 'white';
            $(this).css('color', '#555');
        }
        $(this).css('background-color', labelColorToSet);
    });

    //filter labels
    $("#searchByLabelName").keyup(function() {
        var inputValue = $(this).val();
        $('#newLabel').val(inputValue);
        $("#labelSelector li").hide();
        $("#labelSelector li").each(function(index, elem) {
            var $elem = $(elem);
            if ($elem.text().indexOf(inputValue) >= 0) {
                $elem.show();
            }
        });

    });

    //displays the widget for creating new labels
    $("#showCreateLabel").click(function() {
        if ($("#showCreateLabel").text() === '-') {
            $("#showCreateLabel").text("+");
            $('#createLabelErrorField').text("");
        } else {
            $("#showCreateLabel").text("-");
        }
        $("#createLabelH").toggleClass("hidden");
        $("#newLabel").val($("#searchByLabelName").val());
    });

    //selects a proper color for the label
    $("#colorWidget").delegate("div .color-chooser-color", 'click', function() {
        $(".color-chooser-color").removeClass("label-selected");
        $(this).toggleClass("label-selected");
        var color = $(this).attr("data-color");

        $("#newLabel").css("background-color", color);
        $('#newLabel').css("color", getContrastYIQ(color));
        $('#newLabel').attr('data-color', color);
    });

    //submiting a new label to the REST service
    $('#submitNewLabel').click(function() {
        var labelColor = $('.label-selected').first().attr('data-color');
        var labelName = $('#newLabel').val();

        issueTrackerService.createLabel(labelName, labelColor).done(function(data) {
            if (data.success) {
                $('#labelSelector').append("<li class=\"list-group-item list-item-text cursorPointer\" data-id=" +
                        data.label.id + " data-color=" + data.label.color +
                        "><div style= background-color:" + data.label.color +
                        " class=\"labelCircle\"></div><div>" + data.label.name +
                        "</div></li>");
                $("#showCreateLabel").text("+");
                $("#createLabelH").toggleClass("hidden");
                $('#createLabelErrorField').text("");
            } else {
                var errorText = "";
                for (var error in data.errors) {
                    errorText += data.errors[error] + ";" + "\n";
                }
                $('#createLabelErrorField').text(errorText);
            }
        });
    });
});


