<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${issues}" var="issue">
    <div class="" data-id="${issue.getId()}">

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3>${issue.getTitle()}</h3>
                <h5>Status: <span id="issueState">${issue.getState()}</span> </h5>
            </div>
            <div class="panel-body">
                <p class="lead"> ${issue.getContent()}</p>
                </br>Posted by <span class="text-primary"> ${issue.getOwner().getName()}</span></br>
                Date: <span class="text-primary"> soon </span>
            </div>
        </div>    
    </div>
</c:forEach>





