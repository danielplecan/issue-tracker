<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="pageContent">

    <aside class="col-lg-10 col-lg-offset-1">
        <div class="panel-primary" style="margin-bottom:20px;">
            <div class="panel-heading">
                <h3 class="panel-title" style='display:inline-block;'>Filter issues</h3>
                <span class="close" id="header-arrow">&#x21d3</span>
            </div>
            <div class=" panel panel-body " id="panelBody">
                <div class="filterLeft col-lg-8 formPosfilterLeft">
                    <div class="form-group formLeft col-lg-5">
                        <div class="clearFormGroup col-lg-12">
                            <input class="form-control" id="searchFieldTitle" placeholder="Title" type="text">
                        </div>
                        <div class="clearFormGroup col-lg-12">
                            <input class="form-control" id="searchFieldContent" placeholder="Content" type="text">
                        </div>
                        <div class="clearFormGroup col-lg-12">
                            <input class="form-control typeaheadOwners" id="searchFieldAuthor" placeholder="Author" type="text">
                        </div>
                        <div class="clearFormGroup col-lg-12">
                            <input class="form-control" id="searchFieldAsignee" placeholder="Asignee" type="text">
                        </div>
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
                        
                    </div>
                </div>
                
                <div class="filterRight col-lg-4">
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
    

</style>
    
    
    
<script src="/resources/js/createIssue.js" type="text/javascript"></script>
<script src="/resources/js/issuesCreator.js" type="text/javascript"></script>
<script src="/resources/js/pagination.js" type="text/javascript"></script>

