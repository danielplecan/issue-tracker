
var pager = function() {
    var pageLabel;
    var pageNumber;
    var numberOfPages;

    var enableFirst = function(flag) {
        $(".first").each(function() {
            if (!flag)
                $(this).addClass("disabled", "disabled");
            else
                $(this).removeClass("disabled");
        });
    };
    var enableLast = function(flag) {
        $(".last").each(function() {
            if (!flag)
                $(this).addClass("disabled");
            else
                $(this).removeClass("disabled");
        });
    };
    var changeCurrentPage = function() {
        var paginationButtonContainer = $('.pager');
        issueTrackerService.getIssues(createFilterData()).done(function(data) {
            issuesCreator().addAllIssues(data.issues);
            numberOfPages = data.numberOfPages;
            pageNumber=data.currentPage;
            enableFirst(pageNumber > 1);
            pageLabel.text("page " + pageNumber + " of " + numberOfPages);
            enableLast(pageNumber < numberOfPages);
        });
    };
    var createFilterData = function() {
        var inputs = $('table').find('input');
        var state = $('table').find('select').val();

        var response = {};
        var filter = {};
        $.each(inputs, function(index, item) {
            filter[item.name] = item.value;
        });
        filter["state"] = state;
        response["filters"] = filter;
        response["pageNumber"] = pageNumber;
        response["numberOfItemsPerPage"] = 3;
        return response;
    };
    return{
        nextPage: function() {
            if (pageNumber >= numberOfPages)
                return;
            ++pageNumber;
            changeCurrentPage();
        },
        prevPage: function() {
            if (pageNumber <= 1)
                return;
            --pageNumber;
            changeCurrentPage();
        },
        firstPage: function() {
            if (pageNumber <= 1)
                return;
            pageNumber = 1;
            changeCurrentPage();
        },
        lastPage: function() {
            if (pageNumber >= numberOfPages )
                return;
            pageNumber = numberOfPages;
            changeCurrentPage();
        },
        search: function() {
            issueTrackerService.getIssues(createFilterData());
        },
        initializePagination: function() {
            pageLabel=$(".pageLabel");
            changeCurrentPage();
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
    $('table').find('button').click(pagObject.search);
});
