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
        var titleLabel = $("<a></a>");
        titleLabel.text(title);
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
    var createLabelDiv = function(labels) {
        var labelsDiv = $("<div/>");
        if(labels===null)
            return labelsDiv;
        $.each(labels, function(index,label) {
            var spanLabel = $("<span/>");
            spanLabel.addClass("label");
            spanLabel.css("margin-left","2px");
            spanLabel.css("background-color",label.color);
            spanLabel.css("color",getContrastYIQ(label.color));
            spanLabel.text(label.name);
            labelsDiv.append(spanLabel);
        });
        return labelsDiv;
    };
    var createDateSpan = function(text) {
        var label = $("<span></span>");
        label.text(text);
        label.addClass("text-primary");
        return label;
    };
    var createDateTimeUpdate = function(issue) {
        var dateTimeUpdate = $("<div></div>");
        dateTimeUpdate.addClass("issueDateTimeUpdate");
        dateTimeUpdate.append("<i>Posted by </i>");
        dateTimeUpdate.append(createDateSpan(issue.owner.name));
        dateTimeUpdate.append("<i> on </i>");
        dateTimeUpdate.append(createDateSpan(issue.dateFormat));
        return dateTimeUpdate;
    };
    var createIssueDateTime = function(issue) {
        var issueDateTime = $("<div></div>");
        issueDateTime.addClass("issueDateTime");
        issueDateTime.append("<i>Last updated </i>");
        issueDateTime.append(createDateSpan(issue.lastUpdateDate));
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
    var noResultMessage = function(text) {
        var noResultLabel = $("<p/>");
        noResultLabel.addClass("col-lg-12 txt-center");
        noResultLabel.text(text);
        allIssuesDiv.append(noResultLabel);
    };
    var addIssue = function(issue) {
        var outerDiv = $("<div/>");
        outerDiv.addClass("firstRow");
        outerDiv.attr("data-id", issue.id);

        var innerDiv = $("<div/>");
        innerDiv.addClass("panel panel-default col-lg-offset-1 col-lg-10 noBorder");

        outerDiv.append(innerDiv);
        var titleDiv = $("<div></div>");
        titleDiv.append(createIssue(issue));
        allIssuesDiv.append(outerDiv);
        innerDiv.append(titleDiv);
        innerDiv.append(createLabelDiv(issue.labels));
        innerDiv.append(createDates(issue));
    };
    return{
        addAllIssues: function(issues,totalResult) {
            allIssuesDiv = $("#allIssues");
            allIssuesDiv.empty();
            $.each(issues, function(index, item) {
                addIssue(item);
            });
            noResultMessage(totalResult <= 0 ? "No results were found" : totalResult + " in total");
        }
    };
};


