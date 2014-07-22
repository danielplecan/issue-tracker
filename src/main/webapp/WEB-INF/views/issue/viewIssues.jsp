<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="pageContent">
    
    <aside id="search_widget">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Filter issues</h3>
            </div>
            <div class="panel-body" id="panelBody">
                
                <div class="checkbox">
                    <label>
                        <input type="checkbox" id="searchByTitle" value="ON" /> Search by title
                    </label>  
                    <input type="text" class="form-control" id="searchFieldTitle" placeholder="Title keyword"/>
                </div>
                
                <div></div>
                
                <div class="checkbox">
                    <label>
                        <input type="checkbox" id="searchByContent" value="ON" /> Search by content
                    </label>
                    <input type="text" class="form-control" id="searchFieldContent" placeholder="Content keyword"/>
                </div>
                
                
                <label for="select" class="col-lg-2 control-label">Show</label>
                <div class="col-lg-10">
                    <select class="form-control" id="select">
                        <option>All</option>
                        <option>Only opened issues</option>
                        <option>Only closed issues</option>
                        <option>Only reopened issues</option>
                    </select>
                </div>
                <button type="button" class="btn btn-primary">Search</button>
            </div>
        </div>   
    </aside>
    
    <div id="allIssues" >
        <c:forEach items="${issues}" var="issue">
            <div class="firstRow" data-id="${issue.getId()}">
                <div class="panel panel-default col-lg-offset-1 col-lg-10 noBorder">
                    <div>
                        <div class="issueTitle ">
                            <span class="issueId">#${issue.getId()}</span>
                            <c:choose>
                                <c:when test="${issue.state == 'OPEN'}"> <div class="label label-success issueLength">Open</div></c:when>
                                <c:when test="${issue.state == 'CLOSED'}"> <div class="label label-danger issueLength">Closed</div></c:when>
                                <c:when test="${issue.state == 'REOPENED'}"> <div class="label label-warning issueLength">Reopened</div></c:when>
                            </c:choose>
                            <a class="titleLink" href="/issue/${issue.id}"> ${issue.title}</a>
                        </div>
                    </div>
                    <div class="issueDateTime">
                        Posted by <span class="text-primary"> ${issue.owner.name}</span> on
                        <span class="text-primary"> ${issue.getTimeInterval()} </span>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    
    
    
</div>


<style>    
    #pageContent{
        position:relative;
    }
    
    #searchFieldTitle {
        display:inline-block;
        float:right;
        margin-right:100px;
        height:25px;
        width:60%;
    }
    
    #searchFieldContent {
        display:inline-block;
        float:right;
        margin-right:100px;
        height:25px;
        width:60%
    }
    
    #search_widget{
        margin: 0px auto;
        width:75%;
        right:0px;
    }
    
    #select {
        width:30%;
    }
</style>

<script>
    $("#searchFieldTitle").hide();
    $("#searchFieldContent").hide();
    $("#panelBody").hide();

    $("#searchByTitle").click( function() {
        if (this.checked) {
            $("#searchFieldTitle").show();
        }
        else {
            $("#searchFieldTitle").hide();
        }
    });
    
    $("#searchByContent").click( function() {
        if (this.checked) {
            $("#searchFieldContent").show();
        }
        else {
            $("#searchFieldContent").hide();
        }
    });
    
    $(".panel-title").click( function() {
         $("#panelBody").toggle("slow", function() {});
    });

</script>
