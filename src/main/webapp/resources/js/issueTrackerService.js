issueTrackerService = (function () {
    var self = {};
    
    self.login = function(loginData) {
        return $.ajax({
            url: location.origin + "/security_check",
            type: "POST",
            data: loginData
        });
    };
    
    self.register = function(registerData) {
        return $.ajax({
            url: location.origin + "/register",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            mimeType: "application/json",
            data: JSON.stringify(registerData)
        });
    };
    self.createIssue = function(title, content, labelIdList){        
        var issue = {
            'title':title,
            'content':content
        };
        var issueDTO = {
            'issue':issue,
            'labelIdList':labelIdList
        };
        return $.ajax({
            type: 'POST',
            url: location.origin + '/create-issue',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(issueDTO)
            });
    };
    
    self.closeIssue = function(issueId) {
        return $.ajax({
           type: "POST",
           url: location.origin + "/issue/" + issueId + "/change-state/close",
           dataType: "json"
        });
    };
    
    self.reopenIssue = function(issueId) {
        return $.ajax({
           type: "POST",
           url: location.origin + "/issue/" + issueId + "/change-state/reopen",
           dataType: "json"
        });
    };
    
    self.addComment = function(issueId, commentData) {
        return $.ajax({
            url: location.origin + "/issue/" + issueId + "/add-comment",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            mimeType: "application/json",
            data: JSON.stringify(commentData)
        });
    };
    
    self.createLabel = function(labelName, labelColor){        
        var newLabel = {
            name:labelName,
            color:labelColor
        };
        return $.ajax({
            type: 'POST',
            url: location.origin + '/create-label',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(newLabel)
            });
    };
    
    self.getFilteredIssues = function(filterData) {
        return $.ajax({
            type: 'POST',
            url: location.origin + '/issues/filter',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(filterData)
        });
    };
    return self;
})();