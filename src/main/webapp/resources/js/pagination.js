var pagination = function() {
    var  pageLabel;
    var pageNumber;
    var numberOfPages;
 
    var enableFirst= function(flag){
        $(".first").each( function() {
            if(!flag)
                $(this).attr("disabled","disabled");
            else
                $(this).removeAttr("disabled");
        }); 
    };
    var enableLast= function(flag){
        $(".last").each( function() {
            if(!flag)
                $(this).attr("disabled","disabled");
            else
                $(this).removeAttr("disabled");
        }); 
    };
    var changeCurrentPage = function() {
        var paginationButtonContainer = $('.pagination');
        enableFirst(pageNumber > 1);
        pageLabel.children().text("page "+ pageNumber +" of "+ numberOfPages);
        enableLast(pageNumber < numberOfPages);
    };

    return{
        nextPage: function() {
            if(pageNumber>=numberOfPages)
                return;
            ++pageNumber;
            changeCurrentPage();
            issueTrackerService.getIssues(pageNumber, 15);
        },
        prevPage: function() {
            if(pageNumber<=1)
                return;
            --pageNumber;
            changeCurrentPage();
            issueTrackerService.getIssues(pageNumber, 15);
        },
        firstPage: function() {
            if(pageNumber<=1)
                return;
            pageNumber=1;
            changeCurrentPage();
            issueTrackerService.getIssues(1, 15);
        },
        lastPage: function() {
            if(pageNumber>=numberOfPages)
                return;
            pageNumber=numberOfPages;
            changeCurrentPage();
            issueTrackerService.getIssues(pageNumber, 15);
        },
        initializePagination: function(nrOfIssues) {
            pageNumber=1;
            numberOfPages = Math.round(nrOfIssues / 15);
            pageLabel=$(".pagination").find(".pageLabel");
            changeCurrentPage(1);
        }
    };
};
$(document).ready(function() {
    var pagObject = pagination();
    pagObject.initializePagination(300);

    var paginationButtonContainer = $('.pagination');
    paginationButtonContainer.find(".nextButton").click(pagObject.nextPage);
    paginationButtonContainer.find(".prevButton").click(pagObject.prevPage);
    paginationButtonContainer.find(".lastButton").click(pagObject.lastPage);
    paginationButtonContainer.find(".firstButton").click(pagObject.firstPage);
});

