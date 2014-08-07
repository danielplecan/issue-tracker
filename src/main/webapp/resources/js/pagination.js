
var pager = function() {
    var pageLabel;
    var pageNumber;
    var numberOfPages;
    var filterData;
    var theState = $('#filterByStateSelect').val();
    var theOrder = $('#orderByFirstSelect').val();//orderby title
    var theSort = $('#orderBySecondSelect').val();//asc/desc
    var labelIdList = [];
    var authorId = '';
    var assigneeId = '';

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

    var onOptionAutocompleteSelected = function() {
        $('#searchFieldAsignee').bind('typeahead:selected', function(obj, datum, name) {
            $('#assigneeLabel').show();
            $('#assigneeLabel').text(datum.username);
            var xLabel = $('<span class="glyphicon glyphicon-remove assigneeLabelX" style="cursor:pointer;"></span>');
            $('#assigneeLabel').append(xLabel);
            assigneeId = datum.id;
        });
        $('#searchFieldAuthor').bind('typeahead:selected', function(obj, datum, name) {
            $('#autorLabel').show();
            $('#autorLabel').text(datum.username);
            var xLabel = $('<span class="glyphicon glyphicon-remove autorLabelX" style="cursor:pointer;"></span>');
            $('#autorLabel').append(xLabel);

            authorId = datum.id;
        });
        $('#assigneeLabel').delegate('.assigneeLabelX', 'click', function() {
            $('#assigneeLabel').hide();
            $('#searchFieldAsignee').val('');
            assigneeId = '';
        });

        $('#autorLabel').delegate('.autorLabelX', 'click', function() {
            $('#autorLabel').hide();
            $('#searchFieldAuthor').val('');
            authorId = '';
        });
    };

    var createFilterData = function() {
        filterData = {};
        var filter = {};
        var order = {};

        filter["content"] = $("#searchFieldContent").val();
        filter["title"] = $("#searchFieldTitle").val();
        filter["assignee"] = assigneeId;
        filter["owner"] = authorId;

        theState = $('#filterByStateSelect').val();
        theOrder = $('#orderByFirstSelect').val();//orderby title
        theSort = $('#orderBySecondSelect').val();//asc/desc

        while (labelIdList.length > 0) {
            labelIdList.pop();
        }
        labelIdList = $("#e8_2").select2("val");
        var longLabelIdList = [];
        $(labelIdList).each(function(index, elem) {
            longLabelIdList.push(parseInt(elem));
        });


        order[theOrder] = theSort;
        filter["state"] = theState;
        filter["labels"] = longLabelIdList;
        filterData["filters"] = filter;
        filterData["pageNumber"] = pageNumber;
        filterData["numberOfItemsPerPage"] = 6;
        filterData["orders"] = order;

        $('#orderByFirstSelect').val();//orderby title
        $('#orderBySecondSelect').val();//orderby asc
        //state

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
            pageNumber = 1;
            onOptionAutocompleteSelected();
            createFilterData();
            pageLabel = $(".pageLabel");
            ReloadIssuesOnPage();
        }
    };
};

$(document).ready(function() {
    autocomplete.filterOwners();
    autocomplete.filterAssignees();
    var pagObject = pager();
    pagObject.initializePagination();

    var paginationButtonContainer = $('.pager');
    paginationButtonContainer.find(".nextButton").click(pagObject.nextPage);
    paginationButtonContainer.find(".prevButton").click(pagObject.prevPage);
    paginationButtonContainer.find(".lastButton").click(pagObject.lastPage);
    paginationButtonContainer.find(".firstButton").click(pagObject.firstPage);
    $('#searchButtonFilter').click(pagObject.initializePagination);
});
