function createLabelListElement(label) {
    var li = $(' <div class=\"labelListThing col-lg-12\">\r\n\n\
                <div class=\"labelPanel editLabelPane\">\r\n\n\
                    <div class=\"showLabelPane\" data-id=\"' + label.id + '\" data-color=\"' + label.color + '\">\r\n\n\
                        <div class=\"navbar-left\">\r\n\n\
                            <form class=\"navbar-form navbar-left labelListEdit\">\r\n\n\
                                <span class=\"labelName theLabelListLabel label theLabelListText\" style=\"min-width:100px; background-color: ' + label.color + '\"><\/span>\r\n\n\
                            <\/form>\r\n\n\
                        <\/div>\r\n\n\
                        <div class=\"navbar-right\">\r\n\n\
                            <button type=\"button\" class=\"btn btn-default btn-edit manageLabelsButton btn-sm\"><span class=\"glyphicon glyphicon-pencil\"><\/span> Edit<\/button>\r\n\n\
                            <button type=\"button\" class=\"btn btn-default btn-remove manageLabelsButton btn-sm\"><span class=\"glyphicon glyphicon-remove\"><\/span> Delete<\/button>\r\n\n\
                        <\/div>\r\n\n\
                    <\/div>\r\n\n\
                            \r\n\n\
                    <div class=\"editLabelPane hidden\">\r\n\n\
                        <div class=\"navbar-left \">\r\n\n\
                            <div class=\"navbar-form navbar-left labelListEdit\">\r\n\n\
                                <input type=\"text\" class=\"small-input-box form-control flLeft labelListEdit\" placeholder=\"label\">\r\n\n\
                                <button class=\"toggle-color-picker color-chooser-color labelColorManageColors col-lg-1\"><\/button>\r\n\n\
                                <div class=\"theColorsList\" style=\"display: none\">\r\n\n\
                                    <span class=\"color-chooser-color color-square\" data-color=\"#FF8F8F\" style=\"background-color:#FF8F8F\"><\/span>\r\n\n\
                                    <span class=\"color-chooser-color color-square\" data-color=\"#FFC69E\" style=\"background-color:#FFC69E\"><\/span>\r\n\n\
                                    <span class=\"color-chooser-color color-square\" data-color=\"#FFF4C4\" style=\"background-color:#FFF4C4\"><\/span>\r\n\n\
                                    <span class=\"color-chooser-color color-square\" data-color=\"#E6FAFF\" style=\"background-color:#E6FAFF\"><\/span>\r\n\n\
                                    <span class=\"color-chooser-color color-square\" data-color=\"#D8FFC4\" style=\"background-color:#D8FFC4\"><\/span>\r\n\n\
                                    <span class=\"color-chooser-color color-square\" data-color=\"#E6E6E6\" style=\"background-color:#E6E6E6\"><\/span>\r\n\n\
                                    <span class=\"color-chooser-color color-square\" data-color=\"#B6BDCC\" style=\"background-color:#B6BDCC\"><\/span>\r\n\n\
                                <\/div>\r\n\n\
                            <\/div>\r\n\n\
                        <\/div>\r\n\n\
                        <div class=\"navbar-right\">\r\n\n\
                            <button type=\"button\" class=\"btn btn-success btn-sm manageButton btn-save-edit-label\"><span class=\"glyphicon glyphicon-ok-circle\"><\/span> Save<\/button>\r\n\n\
                            <button type=\"button\" class=\"btn btn-danger btn-sm manageButton btn-cancel-edit-label\"><span class=\"glyphicon glyphicon-remove-circle\"><\/span> Cancel<\/button>\r\n\n\
                        <\/div>\r\n\n\
                        <div class=\"col-lg-12 errorMessageManageLabels commentError text-warning commentContent\"><\/div>\r\n\n\
                    <\/div>\r\n\n\
                    <div class=\"deleteLabelPanel\" style=\"display: none\">\r\n\n\
                        <div class=\"navbar-left leftWarningDeleteLabel\">\r\n\n\
                            <span>Are you sure you want to permanently delete the label?<\/span>\r\n\n\
                        <\/div>\r\n\n\
                        <div class=\"navbar-right\">\r\n\n\
                            <button type=\"button\" class=\"btn btn-danger btn-sm manageButton btn-delete-edit-label\"><span class=\"glyphicon glyphicon-remove-sign\"><\/span> Delete<\/button>\r\n\n\
                            <button type=\"button\" class=\"btn btn-default btn-sm manageButton btn-cancel-delete-label\"><span class=\"glyphicon glyphicon-minus-sign\"><\/span> Cancel<\/button>\r\n\n\
                        <\/div>\r\n\n\
                    <\/div>\r\n\n\
                <\/div>\r\n\n\
            <\/div>');
    $(li).find('.labelName').text(label.name);
    return li;
}

var listOfLabels = function() {
    function scrollToElement(selector, time, verticalOffset) {
        time = typeof (time) !== 'undefined' ? time : 1000;
        verticalOffset = typeof (verticalOffset) !== 'undefined' ? verticalOffset : 0;
        var element = $(selector);
        var offset = element.offset();
        var offsetTop = offset.top + verticalOffset;
        $('html, body').animate({
            scrollTop: offsetTop
        }, time);
    }

    function compareElements(element1, element2) {
        var labelName1 = $(element1).find('.labelName').first().text();
        var labelName2 = $(element2).find('.labelName').first().text();
        return labelName1 < labelName2;
    }

    return {
        positionElement: function(element) {
            $(element).detach();
            var color = $(element).find('.showLabelPane').first().attr('data-color');
            var listContainer = $('#list-all-labels');
            var elementList = $(listContainer).find('.labelListThing');
            for (var i = 0; i < elementList.size(); i++) {
                if (compareElements(element, elementList.get(i))) {
                    $(elementList).eq(i).before($(element));
                    scrollToElement($(element), 300, -150);
                    $(element).effect("highlight", {color: color}, 2000);
                    return;
                }
            }
            $(listContainer).append($(element));
            scrollToElement($(element), 300, -150);
            $(element).effect("highlight", {color: color}, 2000);
        }
    };
};



//ATTACH FUNCTIONALITY TO A TOP PANEL
function addFunctionalityToTopPanel(topPanel) {
    var newLabelButton = $(topPanel).find('.btn-new-label').first();
    var editLabelPanel = $(topPanel).find('.editLabelPane').first();
    var createLabelButton = $(editLabelPanel).find('.btn-create-edit-label').first();
    var cancelButton = $(editLabelPanel).find('.btn-cancel-edit-label').first();
    var labelNameBox = $(editLabelPanel).find('.small-input-box').first();
    var toggleColorPickerEdit = $(editLabelPanel).find('.toggle-color-picker').first();
    var colorList = $(editLabelPanel).find('.theColorsList').first();
    var errorBox = $(editLabelPanel).find('.errorMessageManageLabels').first();
    var searchBox = $(topPanel).find('.manageLabelsNav').first().find('input.form-control');


    //CLEAR INPUTS WHEN HIDING THE PANEL
    function clearInput() {
        toggleCreateLabelPanel();
        $(errorBox).text("");
        $(labelNameBox).val("");
        $(colorList).hide();
        $(createLabelButton).attr('disabled', true);
    }

    //SHOW / HIDE create new label panel
    function toggleCreateLabelPanel() {
        if ($(editLabelPanel).is(":visible")) {
            $(editLabelPanel).hide('slow');
        } else {
            $(editLabelPanel).show('slow');
        }
    }

    //SELECT SPECIFIC COLOR
    $(colorList).delegate('.color-square', 'click', function() {
        $(toggleColorPickerEdit).css('background-color', $(this).attr('data-color'));
        $(toggleColorPickerEdit).attr('data-color', $(this).attr('data-color'));
    });

    //TOGGLE COLOR PICKER
    $(toggleColorPickerEdit).click(function() {
        colorList.toggle('hidden');
    });

    //DISPLAYING THE PANEL FOR CREATING A NEW LABEL
    $(newLabelButton).click(function() {
        clearInput();
    });

    //SUBMIT A NEW LABEL FOR CREATION
    $(createLabelButton).click(function() {
        var newLabelName = $(labelNameBox).val().trim();
        var newLabelColor = $(toggleColorPickerEdit).attr('data-color');
        issueTrackerService.createLabel(newLabelName, newLabelColor).done(function(data) {
            if (data.success) {
                var newLabelPanel = $(createLabelListElement(data.label));
                clearInput();
                listOfLabels().positionElement($(newLabelPanel));
            } else {
                var errorText = "";
                for (var error in data.errors) {
                    errorText += data.errors[error] + "\n";
                }
                $(errorBox).text(errorText);
            }
        });
    });

    //CANCEL CREATING A NEW LABEL
    $(cancelButton).click(function() {
        $(editLabelPanel).toggle('hidden');
        clearInput();
    });

    //CHEKS FOR ERRORS IN LABEL NAME
    $(labelNameBox).on('input', function() {
        var newLabelName = $(this).val().trim();

        if (newLabelName.length < 3 || newLabelName.length > 15) {
            $(createLabelButton).attr('disabled', true);
            $(errorBox).text("A label name must contain between 3 and 15 characters.");
        } else {
            $(createLabelButton).attr('disabled', false);
            $(errorBox).text("");
        }
    });

    //THE INPUT IS CHANGED IN THE SEARCH BOX
    $(searchBox).keyup(function() {
        var inputValue = $(this).val().trim();

        $(topPanel).siblings('div.list-group').find('div.labelListThing').each(function(index, elem) {
            var $elem = $(elem);
            var elemText = $(elem).find('span.labelName');

            if (elemText.text().indexOf(inputValue) >= 0) {
                $elem.show();
            } else {
                $elem.hide();
            }
        });
    });
}

//DISABLING ENTER KEY PRESS IN FORM
$(".manageLabelsNav").first().find('form').bind("keydown", function(e) {
    if (e.keyCode === 13)
        return false;
});

(function() {
    //ADDING FUNCTIONALITY TO THE TOP PANEL OF THE WIDGET
    addFunctionalityToTopPanel($('.manageLabelsTopPanel'));

    //DOM ELEMENT FINDERS
    function getListElementContainer(element) {
        return $(element).parents('.labelPanel').first().
                parents('.labelListThing').first();
    }

    function getShowLabelPanel(element) {
        return $(element).parents('.labelPanel').first().
                find('.showLabelPane').first();
    }

    function getEditLabelPanel(element) {
        return $(element).parents('.labelPanel').first().
                find('.editLabelPane').first();
    }

    function getLabelNameShow(element) {
        return $(element).parents('.labelPanel').first().
                find('.labelName').first();
    }

    function getSmallInputBoxEdit(element) {
        return $(element).parents('.labelPanel').first().
                find('.small-input-box').first();
    }

    function getToggleColorPickerEdit(element) {
        return $(element).parents('.labelPanel').first().
                find('.toggle-color-picker').first();
    }

    function getErrorBox(element) {
        return $(element).parents('.labelPanel').first().
                find('.errorMessageManageLabels').first();
    }

    function getDeleteLabelPanel(element) {
        return $(element).parents('.labelPanel').first().
                find('.deleteLabelPanel').first();
    }

    function switchPanelsShowEdit(elem) {
        $(getErrorBox($(elem))).text("");
        $(getShowLabelPanel($(elem))).show();
        $(getEditLabelPanel($(elem))).hide();
        $(getEditLabelPanel($(elem))).find('.theColorsList').first().hide();
        $(getEditLabelPanel($(elem))).find('.btn-save-edit-label').first().attr('disabled', false);
    }

    function switchPanelsShowDelete(elem) {
        $(getShowLabelPanel($(elem))).hide();
        $(getDeleteLabelPanel($(elem))).show();
    }

    function switchPanelsHideDelete(elem) {
        $(getDeleteLabelPanel($(elem))).hide();
        $(getShowLabelPanel($(elem))).show();
    }

    //EDIT
    $('#widgetContainer').delegate('.labelPanel .btn-edit', 'click', function(event) {
        var smallInputBoxEdit = getSmallInputBoxEdit($(this));
        var labelNameShow = getLabelNameShow($(this));
        var toggleColorPickerEdit = getToggleColorPickerEdit($(this));
        var showLabelPanel = getShowLabelPanel($(this));
        var editLabelPanel = getEditLabelPanel($(this));

        $(smallInputBoxEdit).val($(labelNameShow).text());
        $(toggleColorPickerEdit).css('background-color', $(showLabelPanel).attr('data-color'));
        $(toggleColorPickerEdit).attr('data-color', $(showLabelPanel).attr('data-color'));

        $(editLabelPanel).removeClass('hidden');
        $(showLabelPanel).hide();
        $(editLabelPanel).show();
    });

    //REMOVE
    $('#widgetContainer').delegate('.labelPanel .btn-remove', 'click', function(event) {
        switchPanelsShowDelete($(this));
    });

    //SAVE
    $('#widgetContainer').delegate('.labelPanel .btn-save-edit-label', 'click', function(event) {
        var showLabelPanel = getShowLabelPanel($(this));
        var labelNameShow = getLabelNameShow($(this));
        var newNameForLabel = $(getSmallInputBoxEdit($(this))).val().trim();
        var newColorForLabel = $(getToggleColorPickerEdit($(this))).attr('data-color');
        var labelId = $(showLabelPanel).attr('data-id');

        $(this).attr('disabled', true);
        issueTrackerService.editLabel(newNameForLabel, newColorForLabel, labelId).
                done(function(data) {
                    if (data.success) {
                        $(showLabelPanel).attr('data-color', data.label.color);
                        $(labelNameShow).text(data.label.name);
                        $(labelNameShow).css('background-color', data.label.color);
                        switchPanelsShowEdit($(event.target));
                        listOfLabels().positionElement($(getListElementContainer($(event.target))));
                    } else {
                        var errorText = "";
                        for (var error in data.errors) {
                            errorText += data.errors[error] + "\n";
                        }
                        $(getErrorBox($(event.target))).text(errorText);
                    }
                });
        $(this).attr('disabled', false);
    });

    //CANCEL
    $('#widgetContainer').delegate('.labelPanel .btn-cancel-edit-label', 'click', function(event) {
        switchPanelsShowEdit($(this));
    });

    //TOGGLE COLOR PICKER
    $('#widgetContainer').delegate('.labelPanel .toggle-color-picker', 'click', function(event) {
        $(getEditLabelPanel($(this))).find('.theColorsList').toggle('hidden');
    });

    //PICK COLOR
    $('#widgetContainer').delegate('.labelPanel .color-square', 'click', function(event) {
        var toggleColorPickerEdit = getToggleColorPickerEdit($(this));

        $(toggleColorPickerEdit).css('background-color', $(this).attr('data-color'));
        $(toggleColorPickerEdit).attr('data-color', $(this).attr('data-color'));
    });

    //CONFIRM DELETE LABEL
    $('#widgetContainer').delegate('.labelPanel .btn-delete-edit-label', 'click', function(event) {
        var labelId = $(getShowLabelPanel($(this))).attr('data-id');
        issueTrackerService.removeLabel(labelId).done(function(data) {
            if (data.success) {
                $($(getListElementContainer($(event.target)))).hide("slow", function() {
                    $(this).remove();
                });
            }
        });
    });

    //CANCEL DELETE LABEL
    $('#widgetContainer').delegate('.labelPanel .btn-cancel-delete-label', 'click', function(event) {
        switchPanelsHideDelete($(this));
    });

    //FUNCTION FOR CHEKING IF THE INPUT IS RIGHT
    $('#widgetContainer').delegate('.small-input-box', 'input', function() {
        var newLabelName = $(this).val().trim();
        var saveButton = $(this).parents('.editLabelPane').find('.btn-save-edit-label').first();
        var errorBox = $(saveButton).parent().siblings('.errorMessageManageLabels').first();

        if (newLabelName.length < 3 || newLabelName.length > 15) {
            $(saveButton).attr('disabled', true);
            $(errorBox).text("A label name must contain between 3 and 15 characters.");
        } else {
            $(saveButton).attr('disabled', false);
            $(errorBox).text("");
        }
    });
})();