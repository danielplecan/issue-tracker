
var pager = function() {
    var pageLabel;
    var pageNumber;
    var numberOfPages;

    var toggleFirstButtons = function(flag) {
        if(!flag) {
            $(".first").addClass("disabled");
            
        } else {
            $(".first").removeClass("disabled");
        }
    };
    var toggleLastButtons = function(flag) {
        if(!flag) {
            $(".last").addClass("disabled");
        } else {
            $(".last").removeClass("disabled");
        }
    };
    var changeCurrentPage = function() {
        issueTrackerService.getFilteredIssues(createFilterData()).done(function(data) {
            issuesCreator().addAllIssues(data.issues);
            numberOfPages = data.numberOfPages;
            pageNumber = data.currentPage;
            toggleFirstButtons(pageNumber > 1);
            pageLabel.text("page " + pageNumber + " of " + numberOfPages);
            toggleLastButtons(pageNumber < numberOfPages);
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
        response["numberOfItemsPerPage"] = 5;
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
            pageLabel = $(".pageLabel");
            pageNumber = 1;
            changeCurrentPage();
        },
        initializePagination: function() {
            pageLabel = $(".pageLabel");
            pageNumber = 1;
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
