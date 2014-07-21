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