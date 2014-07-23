$(document).ready(function() {
    $("#buttonCreateIssue").attr("disabled", "disabled");
    $('#textArea1').keyup(function() {
        if ($(this).val().length > 0) {
            $('#buttonCreateIssue').removeAttr('disabled');
        }
        else {
            $('#buttonCreateIssue').attr('disabled', 'disabled');
        }
    });
});
