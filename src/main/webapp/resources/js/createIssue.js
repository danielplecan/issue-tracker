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

//ISSUE VALIDATOR
var createIssueValidator = (function() {
    var self = {};
    self.validateTitle = function(issueTitle) {
        var title = issueTitle.trim();
        var size = title.length;
        if ((size < 3) || size > 50)
            return false;
        return true;
    };

    self.validateContent = function(issueContent) {
        var content = issueContent.trim();
        var size = content.length;
        if (size > 500)
            return false;
        return true;
    };

    return self;
})();


//VALIDATES A FORM FOR CREATING AN ISSUE
function validateIssueForm(form) {
    var title = $(form).find('#textArea1').val();
    var content = $(form).find('#textArea2').val();
    var service = createIssueValidator;
    var hasErrors = false;

    if (!service.validateTitle(title)) {
        $(form).find('#titleError').
                text('The title must have between 3 and 50 characters.');
        hasErrors = true;
    }
    if (!service.validateContent(content)) {
        $(form).find('#contentError').
                text('The content must have a maximum of 500 characters.');
        hasErrors = true;
    }
    return !hasErrors;
};

//EVENT HANDLERS FOR ACTIONS ON FORM ELEMENTS
$('#textArea1').on('input', function() {
    var title = $(this).val().trim();
    if (!createIssueValidator.validateTitle(title)) {
        $('#titleError').text('The title must have between 3 and 50 characters.');
    } else {
        $('#titleError').text('');
    }
});

$('#textArea2').on('input', function() {
    var content = $(this).val().trim();
    if (!createIssueValidator.validateContent(content)) {
        $('#contentError').text('The content must have a maximum of 500 characters.');
    } else {
        $('#contentError').text('');
    }
});

$('#textArea1').on('focus', function() {
        $('#titleError').text('');
});

$('#textArea2').on('focus', function() {
        $('#contentError').text('');
});

//CLEAR FORM FIELDS
function clearFormFields(form) {
    $(form).find('.form-control').each(function(index, element){
        $(element).val('');
    });
    $(form).find('.errors').each(function(index, element) {
       $(element).text(''); 
    });
};



$(document).ready(function() {

    clearFormFields($('#createIssueForm'));
    
    var widget = uploadWidget($("#attachments"));

    //pressing the button for creating an issue
    $('#buttonCreateIssue').click(function(event) {
        event.preventDefault();

        if (!validateIssueForm($('#createIssueForm'))) 
            return;

        var labelIdList = [];
        $('#labelSelector').find('.selectedLabel').each(function() {
            labelIdList.push($(this).attr('data-id'));
        });

        var attachments = widget.getUploadedFiles();

        issueTrackerService.createIssue($('#textArea1').val(),
                $('#textArea2').val(), labelIdList, attachments).done(function(data) {
            if (data.success) {
                window.location.replace(data.url);
            }
            else {
                $.each(data.errors, function(key, value) {
                    var errorKey = key.replace('issue.', ''); 
                    $('#' + errorKey + 'Error').text(value); 
                });
            }
        });
    });

    var backgroundColor = $('#labelSelector').find('.list-item-text').first().css('background-color');
    var textColor = $('#labelSelector').find('.list-item-text').first().css('color');

    //selecting an existing label
    $('#labelSelector').delegate('.list-item-text', 'click', function() {
        var labelColorToSet;
        $(this).toggleClass('selectedLabel');
        if ($(this).hasClass('selectedLabel')) {
            labelColorToSet = $(this).attr('data-color');
            $(this).css('color', 'black');
        } else {
            labelColorToSet = backgroundColor;
            $(this).css('color', textColor);
        }
        $(this).css('background-color', labelColorToSet);
    });

    //filter labels
    $("#searchByLabelName").keyup(function() {
        var inputValue = $(this).val();
        $('#newLabel').val(inputValue);

        $("#labelSelector>div").each(function(index, elem) {
            var $elem = $(elem);
            if ($elem.text().indexOf(inputValue) >= 0) {
                $elem.show();
            } else {
                $elem.hide();
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
        var labelName = $('#newLabel').val().trim();

        issueTrackerService.createLabel(labelName, labelColor).done(function(data) {
            if (data.success) {
                var div1 = $("<div class=\"list-group-item list-item-text cursorPointer\" data-id=" +
                        data.label.id + " data-color=" +
                        data.label.color + "></div>");
                var div2 = $("<div style= background-color:" + data.label.color + " class=\"labelCircle\"></div>");
                var div3 = $("<div></div>");
                div3.text(data.label.name);
                div1.append(div2);
                div1.append(div3);
                $('#labelSelector').append(div1);

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


