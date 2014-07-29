<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/resources/js/viewIssueColorLabels.js" type="text/javascript"></script>
<script src="/resources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="/resources/js/typeahead.bundle.js" type="text/javascript"></script>
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
                    </c:choose>
                </div>
                <br>
                <span><i>Posted by </i>&nbsp;<span class="text-primary"><a href="/profile/<c:out value="${issue.owner.username}"/>">&nbsp;<c:out value="${issue.owner.name}"/></span></a> 
                    &nbsp;<i>on</i>&nbsp;<span class="text-primary"> <c:out value="${issue.getDateFormat()}"/> </span></span>
                <span class="viewIssueLastUpdated">&nbsp<i>Last updated</i>&nbsp<span class="text-primary">&nbsp;<c:out value="${issue.getLastUpdateDate()}"/>&nbsp;</span>
                </span>
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
                            <span style="margin-right:3px;background-color:${label.color}" data-color="${label.color}" class="label label-warning"> <c:out value="${label.name}"/></span>
                        </c:forEach>
                    </div>
                </c:when>
            </c:choose>
            <div class="col-lg-12" id="scrollable-dropdown-menu">
                <input class="form-control typeahead" id="assignTo" placeholder="Assign to" />
                <button id="assignButton" type="button" class="btn btn-default" data-container="body" title="Assign">
                    Assign
                </button>  
            </div>
        </div>
    </div>
    <legend>&nbsp;&nbsp;&nbsp;&nbsp; Comments</legend>
    <div id="allComments" class="list-group">
        <c:forEach items="${comments}" var="comment">
            <div class="fullCommentBody" data-id="${comment.id}">
                <!--When the comment is not empty and state has been changed-->
                <c:choose>
                    <c:when test="${not empty comment.changeState}">
                        <c:choose>
                            <c:when test = "${not empty comment.content}">
                                <c:choose>
                                    <c:when test="${comment.changeState == 'OPEN'}">
                                        <div class="commentStateChanged">
                                            <span class="glyphicon glyphicon-ok-circle openColor"></span><span> <a href="/profile/<c:out value="${comment.author.username}"/>"><c:out value="${comment.author.name}"/></a> changed the state to <span class="openColor">open</span> and said:</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${comment.changeState == 'CLOSED'}">
                                        <div class="commentStateChanged">
                                            <span class="glyphicon glyphicon-remove-circle closedColor"></span><span> <a href="/profile/<c:out value="${comment.author.username}"/>"><c:out value="${comment.author.name}"/></a> changed the state to <span class="closedColor">closed</span> and said:</span>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${comment.changeState == 'OPEN'}">
                                        <div class="commentStateChanged">
                                            <span class="glyphicon glyphicon-ok-circle openColor"></span><span> <a href="/profile/<c:out value="${comment.author.username}"/>"><c:out value="${comment.author.name}"/></a> changed the state to <span class="openColor">open</span>.</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${comment.changeState == 'CLOSED'}">
                                        <div class="commentStateChanged">
                                            <span class="glyphicon glyphicon-remove-circle closedColor"></span><span> <a href="/profile/<c:out value="${comment.author.username}"/>"><c:out value="${comment.author.name}"/></a> changed the state to <span class="closedColor">closed</span>.</span>
                                        </div>
                                    </c:when>
                                </c:choose>

                            </c:otherwise>
                        </c:choose>
                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test = "${not empty comment.content}">
                        <blockquote>
                            <p class="commentContent"><c:out value="${comment.content}"/></p>
                            <small><a href="/profile/<c:out value="${comment.author.username}"/>"><c:out value="${comment.author.name}"/></a> on <c:out value="${comment.getDateFormat()}"/></small>
                        </blockquote>
                    </c:when>
                </c:choose>

            </div>
        </c:forEach>
    </div>
    <div id="addComment">
        <div id="innerCommentDiv"> 
            <textarea id="textAreaComment" rows="4" class="form-control" placeholder="Insert your comment here..."></textarea>        
        </div>
        <span id="contentError" class="commentError text-warning"></span>
        <br />
        <div class = "centerButtonPanel">
            <c:choose>
                <c:when test="${issue.state == 'OPEN'}">
                    <button type="button" class="btn btn-primary toggle changeStateButton" id="changeState-close">Close issue</button>
                    <button type="button" class="btn btn-primary toggle hidden changeStateButton" id="changeState-open">Open issue</button>
                </c:when>
                <c:when test="${issue.state == 'CLOSED'}">
                    <button type="button" class="btn btn-primary toggle hidden changeStateButton" id="changeState-close">Close issue</button>
                    <button type="button" class="btn btn-primary toggle changeStateButton" id="changeState-open">Open issue</button>
                </c:when>
            </c:choose>
            <button id="submitComment" class="btn btn-success" type="button">Comment</button>
        </div>
    </div>
</div>

<style>
    .centerButtonPanel {
        margin-left: auto;
        margin-right: auto;
    }
</style>