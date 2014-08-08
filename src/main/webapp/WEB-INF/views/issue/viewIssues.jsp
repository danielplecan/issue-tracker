<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="/resources/js/select2.js" type="text/javascript"></script>
<link href="/resources/css/select2/select2.css" rel="stylesheet" type="text/css"/>
<script src="/resources/js/attachTooltip.js" type="text/javascript"></script>
<div id="pageContent">
    <section class="well col-sm-10 col-sm-offset-1 ">
        <div class="col-lg-12">
            <div class="col-lg-4 filterInput advancedFilterInput">
                <span class="col-lg-12 advancedFilterInputTitle">Title</span>
                <input class="col-lg-12 form-control" placeholder="Title" id="searchFieldTitle" type="text">           
            </div>
            <div class="col-lg-4 filterInput advancedFilterInput">
                <span class="col-lg-12 advancedFilterInputTitle orderByAdvancedFilterInputTitle">Order By</span>
                <div class="col-lg-6">
                    <select id="orderByFirstSelect" class="form-control orderBySelect1"> 
                        <option value="updateDate" selected="selected">Update Date</option>                   
                        <option value="title">Title</option>
                    </select>               
                </div>
                <div class="col-lg-6 advancedFilterInput">
                    <select id="orderBySecondSelect" class="form-control orderBySelect2"> 
                        <option value="desc" selected="selected">Descendent</option>
                        <option value="asc">Ascendent</option>
                    </select>               
                </div>
            </div>
            <div class="col-lg-4 filterInput advancedFilterInput searchAndAdvancedSearchBtns">                
                <button id="searchButtonFilter" class="btn btn-primary">Search</button>      
                <span class="glyphicon glyphicon-collapse-down advancedSearchIcon" title="Advanced Search"></span> 
            </div>
        </div>

        <div class="advancedFilterMargin col-lg-12">
            <div class="col-lg-5 filterInput advancedFilterInput">
                <span class="col-lg-12 advancedFilterInputTitle">Content</span>
                <input class="form-control" id="searchFieldContent" placeholder="Content" type="text">          
            </div>
            <div class="col-lg-5 col-lg-offset-1 filterInput advancedFilterInput">
                <span class="col-lg-12 advancedFilterInputTitle">State</span>
                <select id="filterByStateSelect" class="form-control"> 
                    <option value="all" selected="selected">All</option>
                    <option value="open">Open</option> 
                    <option value="closed">Closed</option>                  
                </select>    
            </div>
            <div class="col-lg-6 filterInput advancedFilterInput">
                <span class="col-lg-12 advancedFilterInputTitle">Posted By <span id="autorLabel" style="display:none"><span class="glyphicon glyphicon-remove autorLabelX"></span></span></span>               
                <input class="form-control typeaheadOwners" id="searchFieldAuthor" placeholder="Author" type="text">    
            </div>
            <div class="col-lg-6 filterInput advancedFilterInput">
                <span class="col-lg-12 advancedFilterInputTitle">Assigned to <span id="assigneeLabel" style="display:none"><span class="glyphicon glyphicon-remove assigneeLabelX"></span></span></span>
                <input class="form-control typeaheadAssignees" id="searchFieldAsignee" placeholder="Asignee" type="text">           
            </div>
            <div class="col-lg-11 filterInput advancedFilterInput">
                <span class="col-lg-12 advancedFilterInputTitle">Labels</span>
                <select class="populate select2-offscreen" multiple="" id="e8_2" tabindex="-1" style="width:100%"> 
                    <c:forEach items="${allLabels}" var="label">
                        <option value="${label.id}">
                            <c:out value="${label.name}"/>
                        </option>
                    </c:forEach>
                </select>       
            </div>
            <div class="col-lg-11 searchBottom">                
            </div>
        </div>
    </section>
    <section class="well col-sm-10 col-sm-offset-1 hidden">
        <div class="col-lg-6 filterInput">
            <input class="form-control" id="searchFieldTitle" placeholder="Title" type="text">
            <input class="form-control" id="searchFieldContent" placeholder="Content" type="text">
            <input class="form-control typeaheadOwners" id="searchFieldAuthor" placeholder="Author" type="text">
            <input class="form-control typeaheadAssignees" id="searchFieldAsignee" placeholder="Asignee" type="text">
            <select class="populate select2-offscreen" multiple="" id="e8_2" tabindex="-1" style="width:100%"> 
                <c:forEach items="${allLabels}" var="label">
                    <option value="${label.id}">
                        <c:out value="${label.name}"/>
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col-lg-6">
            <div class="labelFilterList">
                <span class="col-lg-3 labelFilterDetails">Order By</span>
                <div class="col-lg-8 labelFilterList" style="padding:0px">     
                    <button id="stateOpen" class="btn btn-default btn-sm typeOfState firstColButton">Open</button>
                    <button id="stateClosed" class="btn btn-default btn-sm typeOfState secondColButton">Closed</button>
                    <button id="stateAll" class="btn btn-default btn-sm typeOfState thirdColButton">All <span class="glyphicon glyphicon-ok"></button>
                </div>
            </div>
            <div class="labelFilterList">
                <span class="col-lg-3 labelFilterDetails">Sort By</span>
                <div class="col-lg-8 labelFilterList" style="padding:0px">                 
                    <button id="orderTitle" class="btn btn-default btn-sm typeOfOrder firstColButton">Title </button>
                    <button id="orderUpdateDate" class="btn btn-default btn-sm typeOfOrder secondColButton">Update <span class="glyphicon glyphicon-ok"></span></button>
                    <button id="sortAsc" class="btn btn-default btn-sm typeOfSort firstColButton">Asc </button>
                    <button id="sortDesc" class="btn btn-default btn-sm typeOfSort secondColButton">Desc <span class="glyphicon glyphicon-ok"></span></button>
                </div>
            </div>
        </div>
    </section>
    <aside class="col-lg-10 col-lg-offset-1 hidden">
        <div class="panel-primary">
            <div class=" panel panel-body " id="panelBody">
                <div class="filterLeft col-lg-12 formPosfilterLeft">
                    <div class="form-group formLeft col-lg-5">
                        <input class="form-control" id="searchFieldTitle" placeholder="Title" type="text">
                        <input class="form-control" id="searchFieldContent" placeholder="Content" type="text">
                        <input class="form-control typeaheadOwners" id="searchFieldAuthor" placeholder="Author" type="text">
                        <input class="form-control" id="searchFieldAsignee" placeholder="Asignee" type="text">

                    </div>

                    <div class="form-group formRight col-lg-7">
                        <span class="col-lg-4 labelFilterDetails">State</span>
                        <div class="col-lg-8 labelFilterList" style="padding: 0px">                          
                            <button id="stateOpen" class="btn btn-default btn-sm typeOfState firstColButton">Open</button>
                            <button id="stateClosed" class="btn btn-default btn-sm typeOfState secondColButton">Closed</button>
                            <button id="stateAll" class="btn btn-default btn-sm typeOfState thirdColButton">All <span class="glyphicon glyphicon-ok"></button>
                        </div>
                        <span class="col-lg-4 labelFilterDetails">Order By</span>
                        <div class="col-lg-8 labelFilterList" style="padding:0px">                 
                            <button id="orderTitle" class="btn btn-default btn-sm typeOfOrder firstColButton">Title </button>
                            <button id="orderUpdateDate" class="btn btn-default btn-sm typeOfOrder secondColButton">Update <span class="glyphicon glyphicon-ok"></span></button>
                        </div>
                        <div class="col-lg-8 labelFilterList" style="padding:0px">
                            <button id="sortAsc" class="btn btn-default btn-sm typeOfSort firstColButton">Asc </button>
                            <button id="sortDesc" class="btn btn-default btn-sm typeOfSort secondColButton">Desc <span class="glyphicon glyphicon-ok"></span></button>
                        </div>
                        <p>
                            <select class="populate select2-offscreen" multiple="" id="e8_2" tabindex="-1">                           
                                <option value="AK">Alaska</option>
                                <option value="HI">Hawaii</option>                           
                                <option value="CA">California</option>
                                <option value="NV">Nevada</option>
                                <option value="OR">Oregon</option>
                                <option value="WA">Washington</option>  

                            </select>
                        </p>
                    </div>
                </div>

                <div class="filterRight col-lg-4 hidden">
                    <label class="txt-center">Labels</label>
                    <div class="labelsOuterDiv">
                        <ul class="list-group labelsFilterList" >
                            <c:forEach items="${allLabels}" var="label">
                                <li class="list-group-item cursorPointer labelListElementFilter" data-id="${label.id}" data-color="${label.color}">
                                    <c:out value="${label.name}"/>
                                    <div style="background-color:${label.color}" class="labelCircle"></div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-12 txt-center">                     
                        <button id="searchButtonFilter" class="btn btn-primary">Search</button>
                    </div>
                </div>             
            </div>
        </div>
    </aside>
    
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
    <div id="allIssues"> 
    </div>

</div>
<script>
    $(document).ready(function() {
        $("#e8_2").select2({placeholder: "Labels"});

        $("#showLabelsInput").click(function() {
            $("#showLabelsInputDiv").show();
        })
    });
    $(document).keypress(function(e) {
        if (e.which == 13) {
            $('#searchButtonFilter').click();
        }
    });

    $('.advancedFilterMargin').hide();
    
    var toggleFlag=true;
    $('.advancedSearchIcon').click(function() {
        var $this = $(this);
        if(toggleFlag==true){
            toggleFlag=false;
            $this.toggleClass('glyphicon glyphicon-collapse-up');
            $this.toggleClass('glyphicon glyphicon-collapse-down');
            $('.advancedFilterMargin').toggle(400, function() {
                toggleFlag=true;
            });
            attachTooltips($this);
        }
    });

    attachTooltips($('.advancedSearchIcon'));

</script>

<script src="/resources/js/createIssue.js" type="text/javascript"></script>
<script src="/resources/js/issuesCreator.js" type="text/javascript"></script>
<script src="/resources/js/pagination.js" type="text/javascript"></script>

