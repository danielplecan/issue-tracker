<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/resources/js/select2.js" type="text/javascript"></script>
<link href="/resources/css/select2.css" rel="stylesheet" type="text/css"/>

<div id="pageContent">
    <section class="well col-sm-10 col-sm-offset-1">
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
                    <button id="stateClosed" class="btn btn-default btn-sm typeOfState secondColButton">Closed </span></button>
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
        <div class="col-lg-12 txt-center">                     
            <button id="searchButtonFilter" class="btn btn-primary">Search</button>
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
                            <button id="stateClosed" class="btn btn-default btn-sm typeOfState secondColButton">Closed </span></button>
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
                            <select class="populate select2-offscreen" multiple="" id="e8_2" tabindex="-1">                            <
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
<style>
    .clearFormGroup{
        clear:both;
        padding: 0;
    }
    .formLeft{
        float:left;
        left: 0;
        position: relative;
        padding-right: 0px;
    }
    .formRight{
        float: right;
        right: 0;
        position: relative;
        padding: 0px;
    }
    .select2-choices{
        margin-left:20px;
    }
</style>
<script>
    $(document).ready(function() {
        $("#e8_2").select2({placeholder: "Labels"});

        $("#showLabelsInput").click(function() {
            $("#showLabelsInputDiv").show();
        })
    });
</script>


<script src="/resources/js/createIssue.js" type="text/javascript"></script>
<script src="/resources/js/issuesCreator.js" type="text/javascript"></script>
<script src="/resources/js/pagination.js" type="text/javascript"></script>

