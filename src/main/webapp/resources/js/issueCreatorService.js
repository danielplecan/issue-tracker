$(document).ready(function(){
    $('#buttonCreateIssue').click(function() {
        $(this).attr('disabled',true);
        issueTrackerService.createIssue($('#textArea1').val(),$('#textArea2').val()).done( function(data) {
            window.location.replace(data.url);
        });
        $(this).attr('disabled',false);
    });
});


