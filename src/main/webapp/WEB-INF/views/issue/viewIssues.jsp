<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="pageContent">
    
    <aside id="search_widget">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Filter issues</h3>
            </div>
            <div class="panel-body" id="panelBody">
                <table id="searchBoxStructure">
                    
                    <tr>
                        <td id="labelCol">
                            <label>Title:</label>  
                        </td>
                        <td  id="inputCol">
                            <input type="text" class="form-control" id="searchFieldTitle" placeholder="Title keyword"/>
                        </td>
                    </tr>
                    
                    <tr>  
                        <td>
                            <label>Content: </label>
                        </td>
                        <td>
                            <input type="text" class="form-control" id="searchFieldContent" placeholder="Content keyword"/>
                        </td>
                        
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
                    <tr>
                        <td colspan="2">
                            <button type="button" class="btn btn-primary">Search</button>
                        <td>
                    </tr>
                </table>
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
    
    #search_widget{
        margin: 0px auto;
        width:75%;
        right:0px;
    }
    
    #searchBoxStructure {
        width:100%
    }
    
    #labelCol {
        width:15%;
    }
    
     #inputCol {
        width:50%;
    }
    
    td {
        padding-left:20px;
    }
</style>

<script>
    $("#panelBody").hide();
    
    $(".panel-title").click( function() {
        $(".panel-title").attr('disabled', 'disabled');
        $("#panelBody").toggle("slow", function() {    
            $(".panel-title").removeAttr('disabled');
        });
    });

</script>
