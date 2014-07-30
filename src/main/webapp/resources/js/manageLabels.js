$(document).ready(function() {
    $('#widgetContainer').delegate('ul>li>.labelPanel', 'click', function(event) {
        var listElementForDelete = $(this).parent();
        var showLabelPanel = $(this).find('.showLabelPane').first();
        var editLabelPanel = $(this).find('.editLabelPane').first();
        var labelNameShow = $(showLabelPanel).find('.labelName').first();
        var smallInputBoxEdit = $(editLabelPanel).find('.small-input-box').first();
        var toggleColorPickerEdit = $(editLabelPanel).find('.toggle-color-picker').first();
        var targetClick = $(event.target);

        function switchPanels() {
            $(showLabelPanel).show();
            $(editLabelPanel).hide();
            $(editLabelPanel).find('.theColorsList').first().hide();
        }



        if ($(targetClick).hasClass('btn-edit')) {
            $(smallInputBoxEdit).val($(labelNameShow).text());
            $(toggleColorPickerEdit).css('background-color', $(showLabelPanel).attr('data-color'));
            $(toggleColorPickerEdit).attr('data-color', $(showLabelPanel).attr('data-color'));
            $(showLabelPanel).hide();
            $(editLabelPanel).removeClass('hidden');
            $(editLabelPanel).show();

        } else if ($(targetClick).hasClass('btn-remove')) {
            var labelId = $(showLabelPanel).attr('data-id');
            issueTrackerService.removeLabel(labelId).done(function(data) {
                if (data.success) {
                    $(listElementForDelete).remove();
                }
            });
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

                        }
                    });
            $(targetClick).attr('disabled', false);

        } else if ($(targetClick).hasClass('btn-cancel-edit-label')) {
            switchPanels();

        } else if ($(targetClick).hasClass('toggle-color-picker')) {
            $(editLabelPanel).find('.theColorsList').toggle('hidden');
        } else if ($(targetClick).hasClass('color-square')) {
            $(toggleColorPickerEdit).css('background-color', $(targetClick).attr('data-color'));
            $(toggleColorPickerEdit).attr('data-color', $(targetClick).attr('data-color'));
        }
    });


    $('.small-input-box').on('input', function() {
        var newLabelName = $(this).val();
        var saveButton = $(this).parents('.editLabelPane').find('.btn-save-edit-label').first();

        if (newLabelName.length < 3 || newLabelName.length > 15) {
            $(saveButton).attr('disabled', true);
        } else {
            $(saveButton).attr('disabled', false);
        }
    });
});

