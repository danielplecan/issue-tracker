var profileClass = function() {
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
    var createTitleDiv = function(issue) {
        var titleDiv = $("<div/>");
        titleDiv.addClass("theIssueTitle");

        titleDiv.append(createIssueState(issue.state));
        titleDiv.append(createIssueTitle(issue.title, issue.id));

        return titleDiv;

    };
    var addIssue = function(issue) {
        var outerDiv = $("<div/>");
        outerDiv.addClass("theIssue col-xs-12 panel panel-default");
        outerDiv.attr("data-id", issue.id);

        outerDiv.append(createTitleDiv(issue));
        return outerDiv;
    };
    return{
        fillTheProgressBars: function(issues) {
            var numberOfIssues = issues.length;
            var numberOfOpenIssues = 0;
            var numberOfClosedIssues = 0;
            $.each(issues, function(index, item) {
                switch (item.state) {
                    case "OPEN":
                        numberOfOpenIssues++;
                        break;
                    case "CLOSED":
                        numberOfClosedIssues++;
                        break;
                }

            });
            $('#openIssues').css('width', numberOfOpenIssues / numberOfIssues * 100 + "%");
            $('#closedIssues').css('width', numberOfClosedIssues / numberOfIssues * 100 + "%");
            $('#numberOfOpenIssues').text(": " + numberOfOpenIssues + " out of " + numberOfIssues);
            $('#numberOfClosedIssues').text(": " + numberOfClosedIssues + " out of " + numberOfIssues);
        },
        addAllUserIssues: function(issues) {
            var allIssuesDiv = $("#allUserIssues");
            allIssuesDiv.empty();
            $.each(issues, function(index, item) {
                allIssuesDiv.append(addIssue(item));
            });
        },
        addAllAssignedIssues: function(issues) {
            var allIssuesDiv = $("#allAsignedIssues");
            allIssuesDiv.empty();
            $.each(issues, function(index, item) {
                allIssuesDiv.append(addIssue(item));
            });
        }
    };
}

$(document).ready(function() {
    
    var profile=profileClass();
    var filterData = {};
    var order = {};
    var userFilter = {};

    order["updateDate"] = "DESC";
    userFilter["owner"] = $('#themes').text();
    filterData["filters"] = userFilter;
    filterData["numberOfItemsPerPage"] = 2147483647;
    filterData["pageNumber"] = 1;
    filterData["orders"] = order;

    issueTrackerService.getFilteredIssues(filterData).done(function(data) {
        profile.fillTheProgressBars(data.issues);
        profile.addAllUserIssues(data.issues);
    });
    
    var asigneeFilter={};
    asigneeFilter["asignee"]=userFilter["owner"];
    filterData["filters"] = asigneeFilter;
    
    issueTrackerService.getFilteredIssues(filterData).done(function(data) {
        profile.addAllAssignedIssues(data.issues);
    });
});


