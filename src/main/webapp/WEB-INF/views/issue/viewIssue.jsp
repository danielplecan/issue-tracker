<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-lg-offset-1 col-lg-10">
    <div class="panel panel-default  modal-content2">
        <div class="panel-heading">
            <h3>

                <c:out value="${issue.title}"/>
            </h3>
            <div class="issueDateTime">
                <div>Current State :
                    <c:choose>
                        <c:when test="${issue.state == 'OPEN'}"><span id="issueState" class="label label-success stateLabel" data-id="${issue.id}">${issue.state}</span></c:when>
                        <c:when test="${issue.state == 'CLOSED'}"><span id="issueState" class="label label-danger stateLabel" data-id="${issue.id}">${issue.state}</span></c:when>
                        <c:when test="${issue.state == 'REOPENED'}"><span id="issueState" class="label label-warning stateLabel" data-id="${issue.id}">${issue.state}</span></c:when>
                    </c:choose>
                </div>
                <br>
                <i>Posted by </i><span class="text-primary"><c:out value="${issue.owner.name}"/></span> <i>on</i>
                <span class="text-primary"> <c:out value="${issue.getDateFormat()}"/> </span><i>, last updated</i> 
                <span class="text-primary"> <c:out value="${issue.getLastUpdateDate()}"/> </span><i> ago</i>
            </div>  

            <div id="buttonsContainer">
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
        <div class="panel-body contentLine">  
            <c:choose>
                <c:when test="${not empty issue.content}">
                    <h4><div clsss="" id="issueContent"><c:out value="${issue.content}"/></div></h4>   
                </c:when>
            </c:choose>
                        <c:choose>
                            <c:when test="${labels != null}">
                            <div id="labelContainer">
                                <c:forEach var="label" items="${labels}">
                                    <span style="margin-right:3px;background-color:${label.color}" class="label label-warning"> <c:out value="${label.name}"/></span>
                                </c:forEach>
                            </div>
                        </c:when>
            </c:choose>

        </div>
    </div>
    <legend>&nbsp;&nbsp;&nbsp;&nbsp; Comments</legend>
    <div id="allComments" class="list-group">
        <c:forEach items="${comments}" var="comment">
            <blockquote>
                <p class="commentContent"><c:out value="${comment.content}"/></p>
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
        <span id="contentError" class="commentError text-warning"></span>
        <br />
        <button id="submitComment" class="btn btn-success hidden" type="button">Comment</button>
    </div>

</div>

<!--<script src="/resources/js/changeStateModule.js" type="text/javascript"></script>-->
<style>

</style>