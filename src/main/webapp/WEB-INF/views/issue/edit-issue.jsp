<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="/resources/js/viewIssueColorLabels.js" type="text/javascript"></script>
<script src="/resources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="/resources/js/typeahead.bundle.js" type="text/javascript"></script>
<div id="viewTheIssue">
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
                <div>
                    <span><i>Assigned to: </i>&nbsp;
                        <span id = "assignedName">
                            <c:choose>
                                <c:when test="${not empty issue.assignee.username}">
                                    <c:out value="${issue.assignee.username}"/>
                                </c:when>
                                <c:otherwise>
                                    <i>none</i>
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </span>
                    <button id="changeAssignButton" type="button" class="btn btn-default btn-xs" data-container="body">
                        Change assignee
                    </button>  
                    <div class="col-lg-12" id="scrollable-dropdown-menu" style="display:none;">
                        <input class="form-control typeahead" id="assignTo" placeholder="Assign to" />
                        <span id = "assigneeSpan" class="help-block"></span>
                        <button id="assignButton" type="button" class="btn btn-default btn-xs" data-container="body" style="display:none;">
                            Assign
                        </button>  
                        <button id="cancelAssignButton" type="button" class="btn btn-default btn-xs" data-container="body">
                            Cancel
                        </button>
                    </div>
                </div>
                <div>
                    <button id="editTheIssueButton" class="btn btn-default btn-xs" type="button">Edit The ISSSUE</button>
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
</div>
<div id="editTheIssue">   
    <div class="col-lg-10 col-lg-offset-1">
        <div class="col-lg-12 ">
            <h3 class="h3createanissue">Edit an Issue</h3>
            <hr>          
            <fieldset>
                <div id="divToChange" class="form-group">
                    <label class="col-lg-1 control-label" for="textArea">Title</label>
                    <div class="col-lg-11">
                        <input class="form-control" id="textArea1" placeholder="Title" autocomplete="off" autofocus/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label" for="textArea">Content</label>
                    <div class="col-lg-11">
                        <textarea id="textArea" rows="6" class="form-control"></textarea>                          
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label" for="textArea">Labels</label>
                    <div class="col-lg-11 ">
                        <div id="existingLabels" class="well"> 
                            <div id="modifiedIssueLabelsList" class="row">
                                <c:forEach items="${labels}" var="label">
                                    <span class="label btn-sm label-default labelEditLabels"><c:out value="${label.name}"/> <span class="glyphicon glyphicon-remove"></span></span>
                                    </c:forEach>
                            </div>
                            <hr>
                            <div class="row">
                                <button id="addNewLabelToAnIssue" class="btn btn-default btn-xs" type="button" ><span class="glyphicon glyphicon-plus"></span>  Add new Label</button>
                                <div class="form-group col-lg-5 " >
                                    <input id="newLabelInput" class="form-control" placeholder="NewLabel" autocomplete="off" autofocus />
                                    <button id="cancelAddNewLabel" class="btn btn-default btn-xs" type="button" >Cancel</button>
                                </div>
                                <div id="labelsSugestions" class="col-lg-12"></div>
                            </div>
                        </div>                         
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label" for="textArea">Attachments</label>
                    <div class="col-lg-11 ">
                        <div id="existingFiles" class="well"> 
                            <div class="row">
                                <c:forEach items="${labels}" var="label">
                                    <span class="label label-default labelEditLabels"><c:out value="${label.name}"/> <span class="glyphicon glyphicon-remove"></span></span>
                                    </c:forEach>
                            </div>
                            <hr>
                            <div class="row">
                                <button class="btn btn-default btn-xs" type="button" ><span class="glyphicon glyphicon-plus"></span> Add new File</button>
                            </div>
                        </div>                         
                    </div>
                </div>
                <button id="saveChangesEdit" class="btn btn-default btn-xs" type="button">Save Changes</button>
                <button id="discardChangesEdit" class="btn btn-default btn-xs" type="button">Dismiss</button>
            </fieldset>           
        </div>

    </div>
</div>
<style>
    .labelEditLabels{
        display:inline-block;
        font-size:13px;
    }
    .labelEditLabels > span{
        color:#C42D16;
    }
    .labelEditLabels > span:hover{
        cursor: pointer;
    }
    #existingLabels > .row{
        margin-left: 1px;
    }
    #newLabelInput, #cancelAddNewLabel{
        display:none;
        float:left;
    }
    #addNewLabelToAnIssue{
        float:left;
    }
    #labelsSugestions > span{
        cursor:pointer;
    }
    #editTheIssue{
        display: none;
    }
</style>
<script src="/resources/js/viewIssueColorLabels.js" type="text/javascript"></script>
<script src="/resources/js/editIssue.js" type="text/javascript"></script>
