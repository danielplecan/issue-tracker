issueTrackerService = (function () {
    var self = {};
    
    self.login = function(loginData) {
        return $.ajax({
            url: location.origin + "/login",
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
    self.createIssue = function(title, content){        
        var issue = {
            'title':title,
            'content':content
        };
        return $.ajax({
            type: 'POST',
            url: location.origin + '/create-issue',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(issue)
            });
    };
    
    self.changeStateOfIssue = function(issueId, stateAction) {
        return $.ajax({
           type: "POST",
           url: location.origin + "/issue/" + issueId + "/change-state/" + stateAction,
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
    
    return self;
})();