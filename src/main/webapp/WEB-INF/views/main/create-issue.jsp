<%-- 
    Document   : createView
    Created on : Jul 17, 2014, 9:46:09 AM
    Author     : vmiron
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="/resources/js/validateIssue.js" type="text/javascript"></script>

<div>
    <legend class="col-lg-10 col-lg-offset-1 ">Create an Issue</legend>
    <div class="well col-lg-10 col-lg-offset-1">
        <div class="col-lg-9  ">
            <form:form class="form-horizontal" modelAttribute='issue' action='' method='POST'>
                <fieldset>
                    <div id="divToChange" class="form-group">
                        <div class="col-lg-12">
                            <form:input path="title" class="form-control" id="textArea1" placeholder="Title" />
                            <form:errors path="title" class="text-warning" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12">
                            <form:textarea path="content" class="form-control" rows="7" id="textArea2" placeholder="Content" />
                            <form:errors path="content" class="text-warning" />
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>
        <div id="label-list" class="col-lg-3">
            <div class="txt-center">
                <span class="label-title">Labels</span>
                <input class="form-control" id="searchByLabelName" type="text" placeholder="Label">
            </div>
            <div class="list-group label-list txt-center label-list-scroll" id="labelSelector">
                <c:forEach items="${labels}" var="label">
                    <li class="list-group-item list-item-text cursorPointer" data-id="${label.id}" data-color="${label.color}">
                        <div style="background-color:${label.color}" class="labelCircle"></div>
                        <div>${label.name}</div>
                    </li>
                </c:forEach>
            </div>
        </div>
        <div class="form-group col-lg-12">
            <div class="txt-center">
                <button class="btn btn-default" id="buttonCreateIssue">Create</button>
            </div>
        </div>
    </div>

</div>

<script src="/resources/js/createIssue.js" type="text/javascript"></script>
