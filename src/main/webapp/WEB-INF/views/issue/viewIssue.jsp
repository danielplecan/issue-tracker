<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/resources/js/viewIssueColorLabels.js" type="text/javascript"></script>
<div id="viewTheIssue">
    <div class="col-lg-offset-1 col-lg-10">
        <div class="panel panel-default  modal-content2">
            <div id="editIssueId" class="hidden">${issue.id}</div>           
            <div class="panel-heading">
                <button id="editTheIssueButton" class="btn btn-default" type="button" style="float:right;"><span class="glyphicon glyphicon-edit"/> Edit</button>
                <div id="oldIssueTitle">
                    <c:out value="${issue.title}"/>
                </div>
                <div class="issueDateTime">
                    <div>Current State :
                        <c:choose>
                            <c:when test="${issue.state == 'OPEN'}"><span id="issueState" class="label label-success stateLabel" data-id="${issue.id}">${issue.state}</span></c:when>
                            <c:when test="${issue.state == 'CLOSED'}"><span id="issueState" class="label label-danger stateLabel" data-id="${issue.id}">${issue.state}</span></c:when>
                        </c:choose>
                    </div>
                    <br>
                    <span><i>Posted by </i>&nbsp;<span class="text-primary"><a href="/profile/<c:out value="${issue.owner.username}"/>">&nbsp;<c:out value="${issue.owner.name}"/></a></span>
                        &nbsp;<i>on</i>&nbsp;<span class="text-primary textDate"> <c:out value="${issue.getDateFormat()}"/> </span></span>
                    <span class="viewIssueLastUpdated">&nbsp;
                        <i>Last updated by</i>&nbsp;
                        <span class="text-primary"><a href="/profile/<c:out value="${issue.lastUpdatedBy.username}"/>"><c:out value="${issue.lastUpdatedBy.name}"/></a>,</span>
                        <span id="oldIssueLastUpdate" class="text-primary textDate"><c:out value="${issue.getLastUpdateDate()}"/>&nbsp;</span>
                    </span>
                </div>
            </div>      
            <div class="panel-body contentLine">
                <c:choose>
                    <c:when test="${not empty issue.content}">                       
                        <div id="issueContent"></div>                    
                    </c:when>  
                    <c:otherwise>
                        <div class="hidden"><div id="issueContent"></div> </div> 
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${labels != null}">
                        <div id="labelContainer">
                            <c:forEach var="label" items="${labels}">
                                <span style="margin-right:3px;background-color:${label.color}" data-color="${label.color}" data-id="${label.id}" class="label label-warning"> <c:out value="${label.name}"/></span>
                            </c:forEach>
                        </div>
                    </c:when>
                </c:choose>
                <div id="attachmentsContainer" class="container attachmentsLine">
                    <c:forEach items="${attachments}" var="attachment">
                        <a href="/attachment/download/<c:out value="${attachment.id}"/>">
                            <span class="btn btn-default attachmentWidth">
                                <span class="buttontext " >${attachment.originalName}</span>
                            </span>
                        </a>
                    </c:forEach>
                </div>
                <div class='col-lg-10'>
                    <span><i>Assigned to: </i>&nbsp;
                        <span id = "assignedName">
                            <c:choose>
                                <c:when test="${not empty issue.assignee.username}">
                                    <a href="/profile/<c:out value="${issue.assignee.username}"/>"/>
                                    <i><c:out value="${issue.assignee.username}"/></i>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a><i>none</i></a>
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </span>
                    <span id = "assignButtons">
                        <button id="changeAssignButton" type="button" class="btn btn-default btn-xs" data-container="body">
                            <c:choose>
                                <c:when test="${not empty issue.assignee.username}">
                                    Change
                                </c:when>
                                <c:otherwise>
                                    Assign
                                </c:otherwise>
                            </c:choose>
                        </button>
                        <c:choose>
                            <c:when test="${not empty issue.assignee.username}">
                                <button id="clearAssignButton" type="button" class="btn btn-default btn-xs" data-container="body">
                                    Clear
                                </button>
                            </c:when>
                        </c:choose>
                    </span>
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
            </div>
        </div>
        <div class="col-lg-10 col-lg-offset-1 commentsLabelTextSize">Comments<hr></div>
        <div id="allComments" class="list-group">
            
        </div>
        <div id="addComment" class="col-lg-10 col-lg-offset-1">
            <div id="innerCommentDiv"> 
                <textarea id="textAreaComment" rows="4" class="form-control markdownArea" placeholder="Insert your comment here..."></textarea>        
            </div>
            <div id="commentFileUpload" class="well"></div>
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
                <input id="editIssueId" class="hidden"/>
                <div id="divToChange" class="form-group">
                    <label class="col-lg-2 control-label" for="editIssueTitle">Title</label>
                    <div class="col-lg-10">
                        <input class="form-control" id="editIssueTitle" placeholder="Title" autocomplete="off" autofocus />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="editIssueContent">Content</label>
                    <div class="col-lg-10">
                        <textarea id="editIssueContent" rows="6" class="form-control markdownArea"></textarea>                          
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="textArea">Labels</label>
                    <div class="col-lg-10 ">
                        <div id="existingLabels" class="well"> 
                            <div id="modifiedIssueLabelsList" class="row">
                                <c:forEach var="label" items="${labels}">
                                    <span style="margin-right:3px;background-color:${label.color};color:black" data-color="${label.color}" data-id="${label.id}" class="label label-warning labelEditLabels"> <c:out value="${label.name}"/> <span class="glyphicon glyphicon-remove"></span></span>
                                    </c:forEach>
                            </div>
                            <hr>
                            <div class="row">
                                <button id="addNewLabelToAnIssue" class="btn btn-default btn-sm" type="button" ><span class="glyphicon glyphicon-plus"></span> Add new Label</button>
                                <div class="form-group ">
                                    <div class="col-lg-5">
                                        <input id="newLabelInput" class="form-control" placeholder="NewLabel" autocomplete="off" autofocus/>
                                    </div>
                                    <button id="cancelAddNewLabel" class="btn btn-default" type="button">Done</button>
                                </div>
                                <div id="labelsSugestions" class="col-lg-12"></div>
                            </div>
                        </div>                         
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="textArea">Attachments</label>
                    <div class="col-lg-10 ">
                        <div id="existingFiles" class="well"> 

                        </div>                         
                    </div>
                </div>
                <div id="editIssueErrors" class="form-group text-warning txt-center">
                </div>
                <div class="form-group txt-center">
                    <button id="saveChangesEdit" class="btn btn-default btn-sm" type="button">Save Changes</button>
                    <button id="discardChangesEdit" class="btn btn-default btn-sm" type="button">Dismiss</button>
                </div>
            </fieldset>           
        </div>
    </div>
</div>
<script src="/resources/js/viewIssueColorLabels.js" type="text/javascript"></script>
<script src="/resources/js/editIssue.js" type="text/javascript"></script>
<script src="/resources/js/markdown-editor.js" type="text/javascript"></script>
<script src="/resources/js/getIssueData.js" type="text/javascript"></script>