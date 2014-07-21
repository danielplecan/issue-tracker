<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-lg-offset-1 col-lg-10">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3><span id="issueState" data-id="${issue.id}">${issue.state}</span>${issue.title}</h3>
            <div class="issueDateTime">
                Posted by <span class="text-primary"> ${issue.owner.name}</span> on
                <span class="text-primary"> ${issue.getDateFormat()} </span>
            </div>     
            <div >
                <button type="button" class="btn btn-primary btn-sm toggle" id="changeState-open">Open</button>
                <button type="button" class="btn btn-primary btn-sm toggle" id="changeState-close">Close</button>
                <button type="button" class="btn btn-primary btn-sm toggle" id="changeState-reopen">Reopen</button>
            </div>
        </div>
        <div class="panel-body">
            <h3>${issue.content}</h3>   
        </div>
    </div>

    <div id="allComments" class="list-group">
        <c:forEach items="${comments}" var="comment">
            <blockquote>
                <p>${comment.content}</p>
                <small><a href="">${comment.author.name}</a> on ${comment.date}</small>
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

<script src="/resources/js/issueService.js" type="text/javascript"></script>
<script src="/resources/js/changeStateModule.js" type="text/javascript"></script>
