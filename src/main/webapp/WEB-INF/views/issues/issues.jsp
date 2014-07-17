<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${issues}" var="issue">
    <div class="" data-id="${issue.getId()}">

        <div class="panel panel-default col-lg-offset-1 col-lg-10 ">
            <div class="panel-heading ">
                <div class="issueTitle ">
                    <span class="issueId">#${issue.getId()}</span>
                    <div class="label label-success">Open</div>
                    <a class="titleLink" href="/issue/${issue.getId()}"> ${issue.getTitle()}</a>
                </div>
            </div>
            <div class="issueDateTime">
                Posted by <span class="text-primary"> ${issue.getOwner().getName()}</span> on
                <span class="text-primary"> ${issue.getDate()} </span>
            </div>
        </div>    
    </div>
</c:forEach>





