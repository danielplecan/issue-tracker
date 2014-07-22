<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-lg-offset-1 col-lg-10">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3>
                <c:choose>
                    <c:when test="${issue.state == 'OPEN'}"><span id="issueState" class="label label-success" data-id="${issue.id}">${issue.state}</span></c:when>
                    <c:when test="${issue.state == 'CLOSED'}"><span id="issueState" class="label label-danger" data-id="${issue.id}">${issue.state}</span></c:when>
                    <c:when test="${issue.state == 'REOPENED'}"><span id="issueState" class="label label-warning" data-id="${issue.id}">${issue.state}</span></c:when>
                </c:choose>
                
                
                ${issue.title}
            
            </h3>
             <c:forEach var="label" items="${labels}">
             <span style="margin-right:3px;" class="label label-warning"> ${label.name} </span>
            </c:forEach>
            <div class="issueDateTime">
                Posted by <span class="text-primary"> ${issue.owner.name}</span> on
                <span class="text-primary"> ${issue.getDateFormat()} </span>
            </div>     
            <div >
                <c:choose>
                    <c:when test="${issue.state == 'OPEN'}">
                        <button type="button" class="btn btn-primary btn-sm toggle changeStateButton" id="changeState-close">Close</button>
                        <button type="button" class="btn btn-primary btn-sm toggle hidden changeStateButton" id="changeState-reopen">Reopen</button>
                    </c:when>
                    <c:when test="${issue.state == 'CLOSED'}">
                        <button type="button" class="btn btn-primary btn-sm toggle hidden changeStateButton" id="changeState-close">Close</button>
                        <button type="button" class="btn btn-primary btn-sm toggle changeStateButton" id="changeState-reopen">Reopen</button>
                    </c:when>
                    <c:when test="${issue.state == 'REOPENED'}">
                        <button type="button" class="btn btn-primary btn-sm toggle changeStateButton" id="changeState-close">Close</button>
                        <button type="button" class="btn btn-primary btn-sm toggle hidden changeStateButton" id="changeState-reopen">Reopen</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
        <div class="panel-body">
            <h3>${issue.content}</h3>   
        </div>
    </div>

    <div id="allComments" class="list-group">
        <c:forEach items="${comments}" var="comment">
            <blockquote>
                <p><c:out value="${comment.content}"/></p>
                <small><a href=""><c:out value="${comment.author.name}"/></a> on <c:out value="${comment.getDateFormat()}"/></small>
            </blockquote>
        </c:forEach>
    </div>          

    <div id="addComment">
        <button id="addCommentButton" title="" data-original-title="" type="button" class="btn btn-default" data-container="body" 
                data-toggle="popover" data-placement="left" data-content="Add comment.">
            Add Comment
        </button>

        <div id="innerCommentDiv" class="hidden"> 
            <textarea id="textAreaComment" rows="4" class="form-control" placeholder="Insert your comment here..."></textarea>        
        </div>

        <button id="submitComment" class="btn btn-success hidden" type="button">Comment</button>
    </div>

</div>

<!--<script src="/resources/js/changeStateModule.js" type="text/javascript"></script>-->
