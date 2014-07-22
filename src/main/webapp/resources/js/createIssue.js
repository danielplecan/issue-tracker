$(document).ready(function() {
    $('#buttonCreateIssue').click(function() {
        var currentButton = $(this);
        currentButton.attr('disabled', 'disabled');
        issueTrackerService.createIssue($('#textArea1').val(), $('#textArea2').val()).done(function(data) {
            if (data.success) {
                window.location.replace(data.url);
            }

            currentButton.removeAttr('disabled');
        });
    });
});


