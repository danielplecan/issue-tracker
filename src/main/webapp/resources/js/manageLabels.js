$(document).ready(function() {
    $('#widgetContainer').delegate('ul>li', 'click', function(event) {
        var showLabelPane = $(this).find('.showLabelPane').first();
        var editLabelPane = $(this).find('.editLabelPane').first();

        if ($(event.target).hasClass('btn-edit')) {
            $(showLabelPane).hide();
            $(editLabelPane).removeClass('hidden');
            $(editLabelPane).show();
        } else if ($(event.target).hasClass('btn-remove')) {
            console.log('remove');
        } else if ($(event.target).hasClass('btn-save-edit-label')) {
            console.log('save');
        } else if ($(event.target).hasClass('btn-cancel-edit-label')) {
            $(showLabelPane).show();
            $(editLabelPane).hide();
        }
    });
});

