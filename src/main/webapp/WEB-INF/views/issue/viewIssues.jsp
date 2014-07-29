<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="pageContent">

    <aside id="search_widget">
        <div class="panel-primary" style="margin-bottom:20px;">
            <div class="panel-heading">
                <h3 class="panel-title" style='display:inline-block;'>Filter issues</h3>
                <span class="close" id="header-arrow">&#x21d3</span>
            </div>
            <div class=" panel panel-body " id="panelBody">
                <div class="filterLeft  col-lg-7">
                    <div class="form-group">
                        <div class="col-lg-12">
                            <input class="form-control" id="searchFieldTitle" placeholder="Title" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12">
                            <input class="form-control" id="searchFieldContent" placeholder="Content" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12">
                            <input class="form-control" id="searchFieldAuthor" placeholder="Author" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12">
                            <input class="form-control" id="searchFieldAsignee" placeholder="Asignee" type="text">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-lg-3 labelFilterDetails">State</label>
                        <div class="col-lg-8 labelFilterList">                          
                            <button id="stateOpen" class="btn btn-default btn-sm typeOfState">Open</button>
                            <button id="stateClosed" class="btn btn-default btn-sm typeOfState">Closed </span></button>
                            <button id="stateAll" class="btn btn-default btn-sm typeOfState">All <span class="glyphicon glyphicon-ok"></button>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 labelFilterDetails">Order By</label>
                        <div class="col-lg-8 labelFilterList">                 
                            <button id="orderTitle" class="btn btn-default btn-sm typeOfOrder">Title </button>
                            <button id="orderUpdateDate" class="btn btn-default btn-sm typeOfOrder">UpdateDate <span class="glyphicon glyphicon-ok"></span></button>    
                            <input id="orderByAscDesc" type="checkbox"> Asc
                        </div>      
                    </div>                   
                </div>
                <div class="filterRight col-lg-5">
                    <label class="col-lg-3 labelFilterDetails">Lables</label>
                    <ul class="list-group col-lg-8 labelsFilterList">
                        <c:forEach items="${allLabels}" var="label">
                            <li class="list-group-item cursorPointer" data-id="${label.id}" data-color="${label.color}">                              
                                ${label.name}
                                <div style="background-color:${label.color}" class="labelCircle"></div>
                            </li>
                        </c:forEach>
                        <!--<li class="list-group-item cursorPointer">Cras justo </li>-->
                    </ul>
                </div>
                <div class="form-group">
                    <div class="col-lg-12 txt-center">                     
                        <button id="searchButtonFilter" class="btn btn-primary">Search</button>
                    </div>
                </div>
            </div>
        </div>
    </aside>

    <div id="allIssues"> 
    </div>

    <div class="col-xs-12 txt-center">
        <ul class="pager" style="display: inline-block">
            <li class="firstButton first disabled"><a >«</a></li>
            <li class="prevButton first disabled"><a>Prev</a></li>
        </ul>
        <label class="pageLabel"></label>
        <ul class="pager" style="display: inline-block">
            <li class="nextButton last"><a>Next</a></li>
            <li class="lastButton last"><a>»</a></li>
        </ul>
    </div>
</div>
<script>
    //  $("#panelBody").hide();

//    $('body').keydown(function(e) {
//        if (e.keyCode === 13) {
//            $('#searchBoxStructure button').trigger('click');
//        }
//    });
//
    $(".panel-heading").click(function() {
        $("#panelBody").toggle("slow", function() {
            if ($("#panelBody").is(':visible')) {
                document.getElementById('header-arrow').innerHTML = '&#x21d1';
                $("#searchFieldTitle").focus();
            }
            else {
                document.getElementById('header-arrow').innerHTML = '&#x21d3';
            }
        });
    });

    $(".labelsFilterList li").click(function() {
        var $this = $(this);

        if (this.style.backgroundColor !== "") {
            this.style.backgroundColor = "";
        }
        else {
            $this.css("background-color", $this.data("color"));
            $this.addClass("thisLabelIsSelected");
        }
    });

    $(".typeOfState").click(function() {
        $(".typeOfState").find('span').remove();
        var $this = $(this);
        var newContentItem = "";

        if ($this.find('span').size() > 0) {
            var newContentItem = $(this).text();
        }
        else {
            var newContentItem = $(this).text() + '  <span class="glyphicon glyphicon-ok"/>';
        }
        $(this).empty();
        $(this).append(newContentItem);
    });
    $(".typeOfOrder").click(function() {
        $(".typeOfOrder").find('span').remove();
        var $this = $(this);
        var newContentItem = "";

        if ($this.find('span').size() > 0) {
            var newContentItem = $(this).text();
        }
        else {
            var newContentItem = $(this).text() + '  <span class="glyphicon glyphicon-ok"/>';
        }
        $(this).empty();
        $(this).append(newContentItem);
    });

</script>
<script src="/resources/js/createIssue.js" type="text/javascript"></script>
<script src="/resources/js/issuesCreator.js" type="text/javascript"></script>
<script src="/resources/js/pagination.js" type="text/javascript"></script>
<style>    
    @media (min-width: 1200px) { 
        #search_widget{
            margin-left: 8.33333333%;
            width: 83.33333333%;
        }
    }

    .panel-heading span {
        display:inline-block; float:right;
    }

    .panel-heading span:hover {
        cursor:default;
    }


    .typeOfState, .typeOfOrder{
        margin-left: 20px;
    }
    .labelFilterDetails{
        margin-top: 10px;
        float:left;
        margin-left: 10px;
    }
    .labelFilterList{
        margin-top: 5px;
    }
    .labelsFilterList{
        height: 250px;
        overflow-y: auto;
    }

</style>