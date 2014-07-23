var issuesCreator = function() {
    var allIssuesDiv;
    var createIssueState = function(state) {
        var stateLabel;
        switch (state) {
            case "OPEN":
                stateLabel = $("<div>Open</div>");
                stateLabel.addClass("label label-success issueLength stateLabelIssues");
                break;
            case "CLOSED":
                stateLabel = $("<div>Closed</div>");
                stateLabel.addClass("label label-danger issueLength stateLabelIssues");
                break;
            case "REOPENED":
                stateLabel = $("<div>Reopened</div>");
                stateLabel.addClass("label label-warning issueLength stateLabelIssues");
                break;
        }
        return stateLabel;
    };
    var createIssueTitle = function(title, id) {
        var titleLabel = $("<a>" + title + "</a>");
        titleLabel.addClass("titleLink");
        titleLabel.attr("href", "/issue/" + id);
        return titleLabel;
    };
    var createIssue = function(issue) {
        var issueTitle = $("<div></div>");
        issueTitle.addClass("issueTitle");
        issueTitle.append(createIssueState(issue.state));
        issueTitle.append(createIssueTitle(issue.title, issue.id));
        return issueTitle;
    };
    var createLabel = function(text) {
        var label = $("<span>" + text + "</span>");
        label.addClass("text-primry");
        return label;
    };
    var createDateTimeUpdate = function(issue) {
        var dateTimeUpdate = $("<div></div>");
        dateTimeUpdate.addClass("issueDateTimeUpdate");
        dateTimeUpdate.append("<i>Posted by </i>");
        dateTimeUpdate.append(createLabel(issue.owner.name));
        dateTimeUpdate.append("<i> on </i>");
        dateTimeUpdate.append(createLabel(issue.dateFormat));
        return dateTimeUpdate;
    };
    var createIssueDateTime = function(issue) {
        var issueDateTime = $("<div></div>");
        issueDateTime.addClass("issueDateTime");
        issueDateTime.append("<i>Last update </i>");
        issueDateTime.append(createLabel(issue.timeInterval));
        issueDateTime.append("<i> ago</i>");
        return issueDateTime;
    };
    var createDates = function(issue) {
        var dates = $("<div></div>");
        dates.addClass("dates");
        dates.append(createDateTimeUpdate(issue));
        dates.append(createIssueDateTime(issue));
        return dates;
    };
    var addIssue = function(issue) {
        var titleDiv = $("<div></div>");
        titleDiv.append(createIssue(issue));
        allIssuesDiv.append(titleDiv);
        allIssuesDiv.append(createDates(issue));
    };
    return{
        addAllIssues: function(issues) {
            allIssuesDiv = $(".issueBoddy");
            allIssuesDiv.empty();
            $.each(issues, function(index,item) {
                addIssue(item);
            });
        }
    };
};


