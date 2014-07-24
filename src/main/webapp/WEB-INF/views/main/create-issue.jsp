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
                <div class="form-group">
                    <div class="txt-center">
                        <div id="issueErrorSpan" class="commentError text-warning commentContent"></div>
                        <button class="btn btn-default" id="buttonCreateIssue">Create</button>
                    </div>
                </div>
            </fieldset>
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
                <div id="colorWidget">
                    <div class="label-line txt-center">
                        <div class="color-chooser-color" data-color="#800000" style="background-color:#800000"></div>
                        <div class="color-chooser-color" data-color="#660033" style="background-color:#660033"></div>
                        <div class="color-chooser-color" data-color="#0033CC" style="background-color:#0033CC"></div>
                        <div class="color-chooser-color" data-color="#006600" style="background-color:#006600"></div>
                        <div class="color-chooser-color" data-color="#FFFF00" style="background-color:#FFFF00"></div>
                        <div class="color-chooser-color" data-color="#FFD1B2" style="background-color:#FFD1B2"></div>
                        <div class="color-chooser-color" data-color="#303030" style="background-color:#303030"></div>
                    </div>
                    <div class="label-line txt-center">
                        <div class="color-chooser-color" data-color="#853385" style="background-color:#853385"></div>
                        <div class="color-chooser-color" data-color="#5200A3" style="background-color:#5200A3"></div>
                        <div class="color-chooser-color" data-color="#99B2FF" style="background-color:#99B2FF"></div>
                        <div class="color-chooser-color" data-color="#003300" style="background-color:#003300"></div>
                        <div class="color-chooser-color" data-color="#FFFFCC" style="background-color:#FFFFCC"></div>
                        <div class="color-chooser-color" data-color="#FFB2B2" style="background-color:#FFB2B2"></div>
                        <div class="color-chooser-color" data-color="#A3C2C2" style="background-color:#A3C2C2"></div>
                    </div>
                </div>
                <span id="createLabelErrorField"></span>
            </div>
        </div>
        <div class="list-group label-list txt-center label-list-scroll" id="labelSelector">
            <c:forEach items="${labels}" var="label">
                <div class="list-group-item list-item-text cursorPointer" data-id="${label.id}" data-color="${label.color}">
                    <div style="background-color:${label.color}" class="labelCircle"></div>
                    <div>${label.name}</div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script src="/resources/js/createIssue.js" type="text/javascript"></script>
