<%-- 
    Document   : createView
    Created on : Jul 17, 2014, 9:46:09 AM
    Author     : vmiron
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="col-lg-10 col-lg-offset-1">
    <div class="col-lg-9 ">
        <h3 class="h3createanissue">Create an Issue</h3>
        <hr>
        <form class="form-horizontal" action="" method='POST'>
            <fieldset>
                <div id="divToChange" class="form-group">
                    <div class="col-lg-12">
                        <input class="form-control" id="textArea1" placeholder="Title" autocomplete="off" autofocus/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-12">
                        <textarea class="form-control" rows="7" id="textArea2" placeholder="Content"></textarea>
                    </div>
                </div>
            </fieldset>
            <div class="col-lg-12 txt-center">
                <div id="issueErrorSpan" class="commentError text-warning commentContent"></div>
                <button class="btn btn-default" id="buttonCreateIssue">Create</button>
            </div>
        </form>
    </div>
    <div id="label-list" class="col-lg-3">
        <div class="txt-center ">
            <h3 class="label-title">Labels</h3>
            <hr>
            <div id="createLabelContainer">
                <input class="form-control2" id="searchByLabelName" type="text" placeholder="Label">
                <button id="showCreateLabel" class="label-add-new label label-default cursorPointer">+</button>
            </div>
            <div id="createLabelH" class="hidden">
                <div id="currentColor">
                    <input id="newLabel" class="form-control3" type="text" name="color"  maxlength="7" disabled="">
                    <button id="submitNewLabel" class="label-add-new label label-default ">Create</button>
                </div>
                <%--<%@ include file="/resources/css/theme/dark/labelColor.jsp"%>--%>
                <div id="colorWidget">
                    <div class="label-line txt-center">
                        <div class="color-chooser-color" data-color="#FF8F8F" style="background-color:#FF8F8F"></div>
                        <div class="color-chooser-color" data-color="#FFC69E" style="background-color:#FFC69E"></div>
                        <div class="color-chooser-color" data-color="#FFF4C4" style="background-color:#FFF4C4"></div>
                        <div class="color-chooser-color" data-color="#E6FAFF" style="background-color:#E6FAFF"></div>
                        <div class="color-chooser-color" data-color="#D8FFC4" style="background-color:#D8FFC4"></div>
                        <div class="color-chooser-color" data-color="#E6E6E6" style="background-color:#E6E6E6"></div>
                        <div class="color-chooser-color" data-color="#B6BDCC" style="background-color:#B6BDCC"></div>
                    </div>
                </div>

                <span id="createLabelErrorField"></span>
            </div>
        </div>
        <div class="list-group label-list txt-center label-list-scroll" id="labelSelector">
            <c:forEach items="${labels}" var="label">
                <div class="list-group-item list-item-text cursorPointer" data-id="${label.id}" data-color="${label.color}">
                    <div style="background-color:${label.color}" class="labelCircle"></div>
                    <div><c:out value="${label.name}"/></div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script src="/resources/js/createIssue.js" type="text/javascript"></script>
