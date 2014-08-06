$(function(){
    issueTrackerService.getAllDataForAnIssue($("#issueState").attr('data-id')).done(function(data) {
        if (data.success) {
            insertRecievedComments(data);
            
        }
        
        $("#issueContent").append($(markdown.toHTML(data.issue.content)));
        $("#editIssueContent").text(data.issue.content);
    });
});

