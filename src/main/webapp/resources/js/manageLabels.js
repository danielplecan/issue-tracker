function createLabelListElement(label) {
    var li = $('<li class=\"list-group-item labelListThing\">\r\n\n\
                <div class=\"labelPanel  editLabelPane\">\r\n\n\
                    <div class=\"showLabelPane\" data-id=\"' + label.id + '\" data-color=\"' + label.color + '\">\r\n\n\
                        <div class=\"navbar-left\">\r\n\n\
                            <form class=\"navbar-form navbar-left labelListEdit\">\r\n\n\
                                <span class=\"labelName theLabelListLabel label theLabelListText\" style=\"background-color: ' + label.color + '\"><\/span>\r\n\n\
                            <\/form>\r\n \n\
                       <\/div>\r\n\n\
                        <div class=\"navbar-right\">\r\n\n\
                            <button type=\"button\" class=\"btn btn-default btn-edit manageLabelsButton btn-sm\"><span class=\"glyphicon glyphicon-pencil\"><\/span> Edit<\/button>\r\n\n\
                            <button type=\"button\" class=\"btn btn-default btn-remove manageLabelsButton btn-sm\"><span class=\"glyphicon glyphicon-remove\"><\/span> Delete<\/button>\r\n\n\
                        <\/div>\r\n\n\
                    <\/div>\r\n\n\
                            \r\n\n\
                    <div class=\"editLabelPane hidden\">\r\n \n\
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
                                    <span class=\"color-chooser-color color-square\" data-color=\"#E6E6E6\" style=\"background-color:#E6E6E6\"><\/span>\r\n \n\
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
                <\/div>\r\n\n\
            <\/li>');
    $(li).find('.labelName').text(label.name);
    return li;
}

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

    //CLEAR INPUTS WHEN HIDING THE PANEL
    function clearInput() {
        toggleCreateLabelPanel();
        $(errorBox).text("");
        $(labelNameBox).val("");
        $(colorList).hide();
        $(createLabelButton).attr('disabled', true);
    }
    
    //SHOW / HIDE create new label panel
    function toggleCreateLabelPanel(){
         if ($(editLabelPanel).is(":visible")) {
            $(editLabelPanel).hide();
        } else {
            $(editLabelPanel).show();
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
        var newLabelName = $(labelNameBox).val();
        var newLabelColor = $(toggleColorPickerEdit).attr('data-color');
        issueTrackerService.createLabel(newLabelName, newLabelColor).done(function(data) {
            if (data.success) {
                var newLabelPanel = $(createLabelListElement(data.label));
                $(topPanel).siblings('ul.list-group').append(newLabelPanel);
                clearInput();
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
        var newLabelName = $(this).val();

        if (newLabelName.length < 3 || newLabelName.length > 15) {
            $(createLabelButton).attr('disabled', true);
            $(errorBox).text("A label name must contain between 3 and 15 characters.");

        } else {
            $(createLabelButton).attr('disabled', false);
            $(errorBox).text("");
        }
    });
}

$(document).ready(function() {

    //ADDING FUNCTIONALITY TO THE TOP PANEL OF THE WIDGET
    addFunctionalityToTopPanel($('.manageLabelsTopPanel'));


    //DELEGATING ALL SORT OF EVENTS TO THE LI ELEMENTS
    $('#widgetContainer').delegate('ul>li>.labelPanel', 'click', function(event) {
        var listElementForDelete = $(this).parent();
        var showLabelPanel = $(this).find('.showLabelPane').first();
        var editLabelPanel = $(this).find('.editLabelPane').first();
        var labelNameShow = $(showLabelPanel).find('.labelName').first();
        var smallInputBoxEdit = $(editLabelPanel).find('.small-input-box').first();
        var toggleColorPickerEdit = $(editLabelPanel).find('.toggle-color-picker').first();
        var targetClick = $(event.target);
        var errorBox = $(editLabelPanel).find('.errorMessageManageLabels').first();


        //CLEAR INPUTS WHEN HIDING THE PANEL
        function switchPanels() {
            $(errorBox).text("");
            $(showLabelPanel).show();
            $(editLabelPanel).hide();
            $(editLabelPanel).find('.theColorsList').first().hide();
            $(editLabelPanel).find('.btn-save-edit-label').first().attr('disabled', false);
        }


        //EDIT button 
        if ($(targetClick).hasClass('btn-edit')) {
            $(smallInputBoxEdit).val($(labelNameShow).text());
            $(toggleColorPickerEdit).css('background-color', $(showLabelPanel).attr('data-color'));
            $(toggleColorPickerEdit).attr('data-color', $(showLabelPanel).attr('data-color'));

            $(editLabelPanel).removeClass('hidden');
            $(showLabelPanel).hide();
            $(editLabelPanel).show();
            //REMOVE button
        } else if ($(targetClick).hasClass('btn-remove')) {
            var labelId = $(showLabelPanel).attr('data-id');
            issueTrackerService.removeLabel(labelId).done(function(data) {
                if (data.success) {
                    $(listElementForDelete).remove();
                }
            });
            //SAVE button
        } else if ($(targetClick).hasClass('btn-save-edit-label')) {
            var newNameForLabel = $(smallInputBoxEdit).val();
            var newColorForLabel = $(toggleColorPickerEdit).attr('data-color');
            var labelId = $(showLabelPanel).attr('data-id');

            $(targetClick).attr('disabled', true);
            issueTrackerService.editLabel(newNameForLabel, newColorForLabel, labelId).
                    done(function(data) {
                        if (data.success) {
                            $(showLabelPanel).attr('data-color', data.label.color);
                            $(labelNameShow).text(data.label.name);
                            $(labelNameShow).css('background-color', data.label.color);
                            switchPanels();
                        } else {
                            var errorText = "";
                            for (var error in data.errors) {
                                errorText += data.errors[error] + "\n";
                            }
                            $(errorBox).text(errorText);
                        }
                    });
            $(targetClick).attr('disabled', false);
            //CANCEL button
        } else if ($(targetClick).hasClass('btn-cancel-edit-label')) {
            switchPanels();
            //TOGGLE COLOR PICKER
        } else if ($(targetClick).hasClass('toggle-color-picker')) {
            $(editLabelPanel).find('.theColorsList').toggle('hidden');
            //SELECT SPECIFIC COLOR
        } else if ($(targetClick).hasClass('color-square')) {
            $(toggleColorPickerEdit).css('background-color', $(targetClick).attr('data-color'));
            $(toggleColorPickerEdit).attr('data-color', $(targetClick).attr('data-color'));
        }
    });

    //FUNCTION FOR CHEKING IF THE INPUT IS RIGHT
    $('.small-input-box').on('input', function() {
        var newLabelName = $(this).val();
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
});

