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
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>
        <div id="label-list" class="col-lg-3 label-list-scroll">
            <div class="txt-center">
                <span class="label-title">Labels</span>
            </div>
            <div class="list-group label-list">
                <a href="#" class="list-group-item list-group-item-label-list" data-color="#FFFFFF"><div class="labelCircle"></div>Dapibus</a>
                <a href="#" class="list-group-item list-group-item-label-list" data-color="#FFFFFF"><div class="labelCircle"> </div>Dapibus</a>
                <a href="#" class="list-group-item list-group-item-label-list" data-color="#FFFFFF"><div class="labelCircle"></div>Dapibus</a>

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
<style>
.labelcircle {
    background-color: #FF0000;
    border-radius: 7px;
    display: inline-block;
    float: left;
    height: 12px;
    margin-left: 15px;
    margin-top: 4px;
    width: 12px;
}
</style>