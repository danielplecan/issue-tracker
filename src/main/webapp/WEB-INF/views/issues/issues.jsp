<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="allIssues">
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
                    <span class="text-primary"> ${issue.getDateFormat()} </span>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
