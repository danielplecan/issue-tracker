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
                <div id="createLabelContainer">
                    <input class="form-control2" id="searchByLabelName" type="text" placeholder="Label">
                    <buton id="showCreateLabel" class="label-add-new label label-default">+</buton>
                </div>
                <div id="createLabelH" class="hidden">
                    <div id="currentColor">                      
                        <input id="newLabel" class="form-control3" type="text" name="color"  maxlength="7" disabled="">
                        <buton id="submitNewLabel" class="label-add-new label label-default">Create</buton>
                    </div>  
                    <div id="colorWidget">
                        <div class="label-line txt-center">
                            <div class="color-chooser-color" data-color="#800000" style="background-color:#800000"></div>
                            <div class="color-chooser-color" data-color="#660033" style="background-color:#660033"></div>                  
                            <div class="color-chooser-color" data-color="#0033CC" style="background-color:#0033CC"></div>
                            <div class="color-chooser-color" data-color="#006600" style="background-color:#006600"></div>
                            <div class="color-chooser-color" data-color="#FFFF00" style="background-color:#FFFF00"></div>
                            <div class="color-chooser-color" data-color="#989898" style="background-color:#989898"></div>
                            <div class="color-chooser-color" data-color="#303030" style="background-color:#303030"></div>
                        </div>
                        <div class="label-line txt-center">
                            <div class="color-chooser-color" data-color="#853385" style="background-color:#853385"></div>                                     
                            <div class="color-chooser-color" data-color="#8080E6" style="background-color:#8080E6"></div>
                            <div class="color-chooser-color" data-color="#99B2FF" style="background-color:#99B2FF"></div>
                            <div class="color-chooser-color" data-color="#66A366" style="background-color:#66A366"></div>         
                            <div class="color-chooser-color" data-color="#FFFFCC" style="background-color:#FFFFCC"></div>   
                            <div class="color-chooser-color" data-color="#FFB2B2" style="background-color:#FFB2B2"></div>
                            <div class="color-chooser-color" data-color="#FF5C33" style="background-color:#FF5C33"></div>
                        </div>
                    </div>
                    <span id="createLabelErrorField"></span>
                </div>
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
