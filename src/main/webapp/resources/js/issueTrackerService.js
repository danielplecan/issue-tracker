issueTrackerService = (function() {
    var self = {};

    self.login = function(loginData) {
        return $.ajax({
            url: location.origin + "/security_check",
            type: "POST",
            data: loginData
        });
    };
    self.recoverPassword = function(username){
        var recoverPasswordData = {};
        recoverPasswordData = {
            'username': username
        };
        return $.ajax({
            url: location.origin + "/recover-password",
            type: "POST",
            data: recoverPasswordData
        });
    },
    self.changePassword = function(password) {
        var changePasswordData = {};
        changePasswordData = {
            'password': password
        };
        return $.ajax({
            url: location.origin + "/change-password",
            type: "POST",
            dataType: "json",
            data: changePasswordData
        });
    };
    self.edit = function(editData) {
        return $.ajax({
            url: location.origin + "/edit-profile",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            mimeType: "application/json",
            data: JSON.stringify(editData)
        });
    };
    self.createIssue = function(title, content, labelIdList, attachments) {
        var issue = {
            'title': title,
            'content': content
        };
        var issueDTO = {
            'issue': issue,
            'labelIdList': labelIdList,
            'attachments': attachments
        };
        return $.ajax({
            type: 'POST',
            url: location.origin + '/create-issue',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(issueDTO)
        });
    };
    
    self.editIssue = function(id, title, content, labelIdList, attachments) {
        var issue = {
            'id': id,
            'title': title,
            'content': content
        };
        var issueDTO = {
            'issue': issue,
            'labelIdList': labelIdList,
            'attachments': attachments
        };
        return $.ajax({
            type: 'POST',
            url: location.origin + '/edit-issue',
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

    self.openIssue = function(issueId) {
        return $.ajax({
            type: "POST",
            url: location.origin + "/issue/" + issueId + "/change-state/open",
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

    self.assignTo = function(issueId, assignee) {
        return $.ajax({
            url: location.origin + "/issue/" + issueId + "/add-assignee",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            mimeType: "application/json",
            data: JSON.stringify(assignee)
        });
    };

    self.getUsersAssignee = function(issueId, assignee) {
        var assigneeData = {};
        assigneeData = {
            'assignedTo': assignee
        };
        return $.ajax({
            async: false,
            url: location.origin + "/issue/" + issueId + "/getUsers-assignee",
            type: "GET",
            dataType: "json",
            data: assigneeData
        });
    };
    
    self.getFilterAssignee = function(id, assignee) {
        var assigneeData = {};
        assigneeData = {
            'assignedTo': assignee
        };
        return $.ajax({
            async: false,
            url: location.origin + "/issues/getFilterAssignees",
            type: "GET",
            dataType: "json",
            data: assigneeData
        });
    };
    
    self.getFilterOwners =function(id, owner) {
        var ownerData = {};
        ownerData = {
            'ownedBy': owner
        };
        return $.ajax({
            async: false,
            url: location.origin + "/issues/get-owners",
            type: "GET",
            dataType: "json",
            data: ownerData
        });
    };
    
    self.createLabel = function(labelName, labelColor) {
        var newLabel = {
            name: labelName,
            color: labelColor
        };
        return $.ajax({
            type: 'POST',
            url: location.origin + '/create-label',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(newLabel)
        });
    };

    self.removeLabel = function(labelId) {
        return $.ajax({
            type: 'DELETE',
            url: location.origin + '/label/' + labelId + '/remove',
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        });
    };
    
    self.editLabel = function(labelNewName, labelNewColor, labelId) {
        var editedLabel = {
            name: labelNewName,
            color: labelNewColor
        };
        return $.ajax({
            type: 'PUT',
            url: location.origin + '/label/' + labelId + '/edit',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(editedLabel)
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
    
    self.removeFile = function(fileId) {
        return $.ajax({
           type: 'DELETE',
           url: location.origin + '/attachment/remove/' + fileId,
           dataType: 'json'
        });
    };
    
    self.getAttachmentsForIssue = function(issueId) {
        return $.ajax({
            type: 'GET',
            url: location.origin + '/issue/' + issueId + '/get-attachments',
            dataType: 'json'
        });
    };
    
    self.checkUsernameExistance = function(username) {
         return $.ajax({
            type: 'GET',
            url: location.origin + '/userExistance/' + username,
            dataType: 'json'
        });
    };
    self.removeOrphanAttachments = function(attachments) {
        var attachmentsData = {
            'attachments': attachments
        };

        return $.ajax({
            type: 'DELETE',
            url: location.origin + '/attachment/remove-orphans',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(attachmentsData)
        });

    };
    return self;
})();
   