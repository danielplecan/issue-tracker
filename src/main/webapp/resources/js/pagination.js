
var pager = function() {
    var pageLabel;
    var pageNumber;
    var numberOfPages;
    var filterData;
    var theState = "All";
    var theOrder = "updateDate";
    var theSort = "DESC";
    var labelIdList = [];

    var toggleFirstButtons = function(flag) {
        if (!flag) {
            $(".first").addClass("disabled");

        } else {
            $(".first").removeClass("disabled");
        }
    };
    var toggleLastButtons = function(flag) {
        if (!flag) {
            $(".last").addClass("disabled");
        } else {
            $(".last").removeClass("disabled");
        }
    };
    var updatePageNumber = function(number) {
        filterData["pageNumber"] = number;
        pageNumber = number;
    };
    var ReloadIssuesOnPage = function() {
        issueTrackerService.getFilteredIssues(filterData).done(function(data) {
            issuesCreator().addAllIssues(data.issues, data.totalResultCount);
            numberOfPages = data.numberOfPages;
            pageNumber = data.currentPage;
            if (pageNumber > numberOfPages)
                pageNumber = numberOfPages;
            toggleFirstButtons(pageNumber > 1);
            pageLabel.text("page " + pageNumber + " of " + numberOfPages);
            toggleLastButtons(pageNumber < numberOfPages);
        });
    };
    var createFilterData = function() {
        filterData = {};
        var filter = {};
        var order = {};

        filter["content"] = $("#searchFieldContent").val();
        filter["title"] = $("#searchFieldTitle").val();
        filter["asignee"] = $("#searchFieldAsignee").val();
        filter["owner"] = $("#searchFieldAuthor").val();
        
        while (labelIdList.length > 0) {
            labelIdList.pop();
        }
        $(".thisLabelIsSelected").each(function(index, element) {
            labelIdList.push($(element).data("id"));
            console.log($(element).data("id"));
        });


        order[theOrder] = theSort;
        filter["state"] = theState;
        filter["labels"] = labelIdList;
        filterData["filters"] = filter;
        filterData["pageNumber"] = pageNumber;
        filterData["numberOfItemsPerPage"] = 6;
        filterData["orders"] = order;


    };
    return{
        nextPage: function() {
            if (pageNumber >= numberOfPages)
                return;
            updatePageNumber(pageNumber + 1);
            ReloadIssuesOnPage();
        },
        prevPage: function() {
            if (pageNumber <= 1)
                return;
            updatePageNumber(pageNumber - 1);
            ReloadIssuesOnPage();
        },
        firstPage: function() {
            if (pageNumber <= 1)
                return;
            updatePageNumber(1);
            ReloadIssuesOnPage();
        },
        lastPage: function() {
            if (pageNumber >= numberOfPages)
                return;
            updatePageNumber(numberOfPages);
            ReloadIssuesOnPage();
        },
        initializePagination: function() {
            $("#stateOpen").click(function() {
                theState = "Open";
            });
            $("#stateClosed").click(function() {
                theState = "Closed";
            });
            $("#stateAll").click(function() {
                theState = "All";
            });

            $("#orderTitle").click(function() {
                theOrder = "title";
            });
            $("#orderUpdateDate").click(function() {
                theOrder = "updateDate";
            });
            $("#sortAsc").click(function() {
                theSort = "ASC";
            });
            $("#sortDesc").click(function() {
                theSort = "DESC";
            });

            pageNumber = 1;
            createFilterData();
            pageLabel = $(".pageLabel");
            ReloadIssuesOnPage();
        }
    };
};
$(document).ready(function() {
    var pagObject = pager();
    pagObject.initializePagination();

    var paginationButtonContainer = $('.pager');
    paginationButtonContainer.find(".nextButton").click(pagObject.nextPage);
    paginationButtonContainer.find(".prevButton").click(pagObject.prevPage);
    paginationButtonContainer.find(".lastButton").click(pagObject.lastPage);
    paginationButtonContainer.find(".firstButton").click(pagObject.firstPage);
    $('#searchButtonFilter').click(pagObject.initializePagination);
});
