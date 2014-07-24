<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="pageContent">

    <aside id="search_widget">
        <div class="panel-primary" style="margin-bottom:20px;">
            <div class="panel-heading">
                <h3 class="panel-title" style='display:inline-block;'>Filter issues</h3>
                <span class="close">&#x21d3</span>
            </div>
            <div class=" panel panel-body " id="panelBody">
                <table id="searchBoxStructure">

                    <tr>
                        <td id="labelCol">
                            <label>Title:</label>  
                        </td>
                        <td  id="inputCol">
                            <input type="text" name="title" class="form-control" id="searchFieldTitle" placeholder="Title keyword"/>
                        </td>
                    </tr>

                    <tr>  
                        <td>
                            <label>Content: </label>
                        </td>
                        <td>
                            <input type="text" name="content" class="form-control" id="searchFieldContent" placeholder="Content keyword"/>
                        </td>

                        <td>
                            <button type="button" class="btn btn-primary">Search</button>
                        <td>
                    </tr>


                    <tr>    
                        <td>
                            <label>State</label>
                        </td>
                        <td>

                            <select class="form-control">
                                <option>All</option>
                                <option>Open</option>
                                <option>Closed</option>
                                <option>Reopened</option>
                            </select>

                        </td>

                    </tr>
                </table>
            </div> 
        </div>
    </aside>

    <div id="allIssues" >
        <c:forEach items="${issues}" var="issue">
            <div class="firstRow" data-id="${issue.id}">
                <div class="panel panel-default col-lg-offset-1 col-lg-10 noBorder">
                    <div>
                        <div class="issueTitle ">
                            <!--<span class="issueId"><c:out value="#${issue.id}"/></span>-->
                            <c:choose>
                                <c:when test="${issue.state == 'OPEN'}"> <div class="label label-success issueLength stateLabelIssues">Open</div></c:when>
                                <c:when test="${issue.state == 'CLOSED'}"> <div class="label label-danger issueLength stateLabelIssues">Closed</div></c:when>
                                <c:when test="${issue.state == 'REOPENED'}"> <div class="label label-warning issueLength stateLabelIssues">Reopened</div></c:when>
                            </c:choose>
                            <a class="titleLink" href="/issue/${issue.id}"><c:out value="${issue.title}"/></a>
                        </div>
                    </div>
                    <div>
                        <c:forEach items="${labels[issue.id]}" var="label">
                            <span class="label label-primary"><c:out value="${label.name}"/></span>
                        </c:forEach>  
                    </div>
                    <div class = "dates">
                        <div class="issueDateTimeUpdate">
                            <i>Posted by</i> <span class="text-primary"><c:out value="${issue.owner.name}"/></span><i> on</i>
                            <span class="text-primary"><c:out value="${issue.getDateFormat()}"/> </span>
                        </div>   
                        <div class="issueDateTime">
                            <i>Last updated </i>
                            <span class="text-primary"><c:out value="${issue.getLastUpdateDate()}"/> </span>
                            <i> ago</i>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="col-lg-12 txt-center">
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
    $("#panelBody").hide();

    $('body').keydown( function (e) {
        if (e.keyCode === 13){
            $('#searchBoxStructure button').trigger('click');
        }
    });

    $(".panel-heading").click(function() {
        $("#panelBody").toggle("slow", function() {
            if ($("#panelBody").is(':visible')) {
                $("#searchFieldTitle").focus();
            }
        });
    });
</script>
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
    
</style>