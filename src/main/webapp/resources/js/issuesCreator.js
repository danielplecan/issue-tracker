var issuesCreator = function() {
    var allIssuesDiv;
    var createIssueState = function(state) {
        var stateLabel;
        switch (state) {
            case "OPEN":
                stateLabel = $("<div>Open</div>");
                stateLabel.addClass("label label-success theIssueState");
                break;
            case "CLOSED":
                stateLabel = $("<div>Closed</div>");
                stateLabel.addClass("label label-danger theIssueState");
                break;
            case "REOPENED":
                stateLabel = $("<div>Reopened</div>");
                stateLabel.addClass("label label-warning theIssueState");
                break;
        }
        return stateLabel;
    };
    var createIssueTitle = function(title, id) {
        var titleLabel = $("<a></a>");
        titleLabel.text(title);
        titleLabel.addClass("theIssueTitleLink");
        titleLabel.attr("href", "/issue/" + id);
        return titleLabel;
    };

    var createContentDiv = function(issue) {

        var issueContentDiv = $("<div/>");
        issueContentDiv.addClass("theIssueContent");

        var issueContentPreview = $("<div/>");
        issueContentPreview.addClass("theIssueContentPreview breakLongWord hidden");
        issueContentPreview.attr("data-contentId", issue.id);
        issueContentPreview.append($(markdown.toHTML(issue.content)));

        issueContentDiv.append(issueContentPreview);
        issueContentDiv.append(createIssueLabelsDiv(issue.labels));

        return issueContentDiv;

    };

    var createIssueLabelsDiv = function(labels) {
        var labelsDiv = $("<div/>");
        labelsDiv.addClass("theIssueLabels");
        if (labels === null)
            return labelsDiv;
        $.each(labels, function(index, label) {
            var spanLabel = $("<span/>");
            spanLabel.addClass("theIssueLabel label");
            spanLabel.css("background-color", label.color);
            spanLabel.css("color", getContrastYIQ(label.color));
            spanLabel.text(label.name);
            labelsDiv.append(spanLabel);
        });
        return labelsDiv;
    };

    var createPostedUserSpan = function(name, username) {
        var span = $("<span></span>");
        var a = $('<a/>');
        a.attr('href','/profile/'+username);
        a.text(name);
        span.append(a);
        span.addClass("text-primary");
        return span;
    };
     var createDateSpan = function(text) {
        var span = $("<span></span>");
        span.text(text);
        span.addClass("text-primary");
        return span;
    };
    var createPostedDetailsSpan = function(text) {
        var span = $("<span></span>");
        var a = $('<a/>');
        a.attr('href','/profile/'+text);
        a.text(text);
        span.append(a);
        span.addClass("text-primary");
        return span;
    };
    var createIssueLeftDetails = function(issue) {
        var dateTimeUpdate = $("<span>");
        dateTimeUpdate.addClass("theIssueLeftDetails");
        dateTimeUpdate.append("<i>Posted by </i>");

        dateTimeUpdate.append(createPostedUserSpan(issue.owner.name, issue.owner.username));
        dateTimeUpdate.append("<i> on </i>");
        dateTimeUpdate.append(createDateSpan(issue.dateFormat));
        return dateTimeUpdate;
    };
    var createIssueRightDetails = function(issue) {
        var issueDateTime = $("<span/>");
        issueDateTime.addClass("theIssueRightDetails");
        issueDateTime.append("<i>Last updated by </i>");
        issueDateTime.append(createPostedUserSpan(issue.lastUpdatedBy.name, issue.lastUpdatedBy.username));
        issueDateTime.append("<i>, </i>");
        issueDateTime.append(createDateSpan(issue.lastUpdateDate));       
        return issueDateTime;
    };
    var createIssueDetailsDiv = function(issue) {
        var dates = $("<div/>");
        dates.addClass("theIssueDetails");

        dates.append(createIssueLeftDetails(issue));
        dates.append(createIssueRightDetails(issue));
        return dates;
    };

    var createShowContentButton = function(issueId) {
        var span = $("<span/>");
        span.attr("title", "Show Issue Content");
        span.addClass("theIssueShowContent glyphicon glyphicon-chevron-down");
        span.attr("data-issueid", issueId);
        span.click(function() {
            $this = $(this);
            $this.toggleClass("glyphicon glyphicon-chevron-down");
            $this.toggleClass("glyphicon glyphicon-chevron-up");


            var selector = "div[data-contentId=" + $this.data().issueid + "]";
            $(selector).toggleClass("hidden");
        });
        return span;
    };

    var createTitleDiv = function(issue) {
        var titleDiv = $("<div/>");
        titleDiv.addClass("theIssueTitle");

        titleDiv.append(createShowContentButton(issue.id));
        titleDiv.append(createIssueState(issue.state));
        titleDiv.append(createIssueTitle(issue.title, issue.id));

        return titleDiv;

    };
    var noResultMessage = function(text) {
        var noResultLabel = $("<p/>");
        noResultLabel.addClass("col-xs-12 txt-center");
        noResultLabel.text(text);
        allIssuesDiv.append(noResultLabel);
    };
    var addIssue = function(issue) {
        var outerDiv = $("<div/>");
        outerDiv.addClass("theIssue col-xs-offset-1 col-xs-10 panel panel-default");
        outerDiv.attr("data-id", issue.id);

        outerDiv.append(createTitleDiv(issue));
        outerDiv.append(createContentDiv(issue));
        outerDiv.append(createIssueDetailsDiv(issue));

        return outerDiv;
    };
    return{
        addAllIssues: function(issues, totalResult) {
            allIssuesDiv = $("#allIssues");
            allIssuesDiv.empty();
            $.each(issues, function(index, item) {
                allIssuesDiv.append(addIssue(item));
            });
            noResultMessage(totalResult <= 0 ? "No results were found" : totalResult + " in total");
        }
    };
};


