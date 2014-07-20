<%-- 
    Document   : createView
    Created on : Jul 17, 2014, 9:46:09 AM
    Author     : vmiron
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="resources/js/issueInputValidater.js" type="text/javascript"></script>

<div id="login-box">
    <legend>Create an Issue</legend>
    <form:form class="form-horizontal" modelAttribute='issue' action='create-issue' method='POST'>
        <fieldset>
            <div id="divToChange" class="form-group">
                <label for="textArea" class="col-lg-2 control-label">Title</label>
                <div class="col-lg-10">
                    <form:input path="title" class="form-control" id="textArea1"/>
                    <form:errors path="title" class="text-warning" />
                </div>
            </div>
            <div class="form-group">
                <label for="textArea" class="col-lg-2 control-label">Body</label>
                <div class="col-lg-10">
                    <form:textarea path="content" class="form-control" rows="4" id="textArea2"/>
                </div>
            </div>   

            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <button class="btn btn-default" id="buttonCreateIssue">Create</button>
                </div>
            </div>
        </fieldset>
    </form:form>
</div>
