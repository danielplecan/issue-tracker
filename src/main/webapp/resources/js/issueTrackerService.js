issueTrackerService = (function() {
    var self = {};

    self.login = function(loginData) {
        return $.ajax({
            url: "/security_check",
            type: "POST",
            data: loginData
        });
    };
    self.register = function(registerData) {
        return $.ajax({
            url: "/register",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            mimeType: "application/json",
            data: JSON.stringify(registerData)
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
            url: "/edit-profile",
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
            url: '/create-issue',
            contentType: "application/json",
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
            url: '/edit-issue',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(issueDTO)
        });
    };

    self.closeIssue = function(issueId) {
        return $.ajax({
            type: "POST",
            url: "/issue/" + issueId + "/change-state/close",
            dataType: "json"
        });
    };

    self.openIssue = function(issueId) {
        return $.ajax({
            type: "POST",
            url: "/issue/" + issueId + "/change-state/open",
            dataType: "json"
        });
    };

    self.addComment = function(issueId, commentData) {
        return $.ajax({
            url: "/issue/" + issueId + "/add-comment",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            mimeType: "application/json",
            data: JSON.stringify(commentData)
        });
    };

    self.assignTo = function(issueId, assignee) {
        return $.ajax({
            url: "/issue/" + issueId + "/add-assignee",
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
            url: "/issue/" + issueId + "/getUsers-assignee",
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
            url: "/issues/getFilterAssignees",
            type: "GET",
            dataType: "json",
            data: assigneeData
        });
    };

    self.getFilterOwners = function(id, owner) {
        var ownerData = {};
        ownerData = {
            'ownedBy': owner
        };
        return $.ajax({
            async: false,
            url: "/issues/get-owners",
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
            url: '/create-label',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(newLabel)
        });
    };

    self.removeLabel = function(labelId) {
        return $.ajax({
            type: 'DELETE',
            url: '/label/' + labelId + '/remove',
            contentType: "application/json",
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
            url: '/label/' + labelId + '/edit',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(editedLabel)
        });
    };

    self.getFilteredIssues = function(filterData) {
        return $.ajax({
            type: 'POST',
            url: '/issues/filter',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(filterData)
        });
    };

    self.removeFile = function(fileId) {
        return $.ajax({
            type: 'DELETE',
            url: '/attachment/remove/' + fileId,
            dataType: 'json'
        });
    };

    self.getAttachmentsForIssue = function(issueId) {
        return $.ajax({
            type: 'GET',
            url: '/issue/' + issueId + '/get-attachments',
            dataType: 'json'
        });
    };

    self.checkUsernameExistance = function(username) {
        return $.ajax({
            type: 'GET',
            url: '/userExistance/' + username,
            dataType: 'json'
        });
    };
    self.removeOrphanAttachments = function(attachments) {
        var attachmentsData = {
            'attachments': attachments
        };

        return $.ajax({
            type: 'DELETE',
            url: '/attachment/remove-orphans',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(attachmentsData)
        });

    };
    self.getAllLables = function() {
        return $.ajax({
            url: '/getAllLabels',
            dataType: 'json'
        });
    };

    self.changeTheme = function(theme) {
        $.ajax({
            type: 'POST',
            url: '/settings/changeTheme/' + theme,
            dataType: "json",
            success: function(data) {
                if (data.success) {
                    location.reload();
                }
            }
        });
    };

    self.getAllDataForAnIssue = function(issueId) {
        return $.ajax({
            type: 'GET',
            url: '/issue/' + issueId + '/get-all-data',
            dataType: 'json'
        });
    };

    return self;
})();
   