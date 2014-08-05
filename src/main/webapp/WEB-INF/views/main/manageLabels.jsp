<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-default col-lg-10 col-lg-offset-1" id="widgetContainer">
    <div class="manageLabelsTopPanel topPanelThing">
        <div class="manageLabelsNav col-lg-12">
            <form class="navbar-form navbar-left manageLabelsNavLeft">
                <input type="text" class="form-control searchLabelsBar" placeholder="Search labels..">
            </form>
            <button type="button" class="btn btn-default manageLabelsNavRight btn-new-label" id="editLabel"><span class="glyphicon glyphicon-plus"></span>  Create new label</button>
        </div>
        <div class="editLabelPane col-lg-12" style="display: none">
            <div class="navbar-form manageLabelsNavLeft">
                <input type="text" class="form-control flLeft small-input-box createLabelImput" placeholder="label">
                <button class="toggle-color-picker color-chooser-color labelColorManageColors col-lg-1" style="background-color: #FF8F8F" data-color="#FF8F8F"></button>
                <div class="theColorsList" style="display: none;">
                    <span class="color-chooser-color color-square" data-color="#FF8F8F" style="background-color:#FF8F8F"></span>
                    <span class="color-chooser-color color-square" data-color="#FFC69E" style="background-color:#FFC69E"></span>
                    <span class="color-chooser-color color-square" data-color="#FFF4C4" style="background-color:#FFF4C4"></span>
                    <span class="color-chooser-color color-square" data-color="#E6FAFF" style="background-color:#E6FAFF"></span>
                    <span class="color-chooser-color color-square" data-color="#D8FFC4" style="background-color:#D8FFC4"></span>
                    <span class="color-chooser-color color-square" data-color="#E6E6E6" style="background-color:#E6E6E6"></span>
                    <span class="color-chooser-color color-square" data-color="#B6BDCC" style="background-color:#B6BDCC"></span>
                </div>
            </div>
            <div class="manageLabelsNavRight">
                <button type="button" class="btn btn-success btn-sm manageButton btn-create-edit-label"><span class="glyphicon glyphicon-ok-circle"></span> Create</button>
                <button type="button" class="btn btn-danger btn-sm manageButton btn-cancel-edit-label"><span class="glyphicon glyphicon-remove-circle"></span> Cancel</button>
            </div>
            <br/>
            <div class="col-lg-12 errorMessageManageLabels commentError text-warning commentContent"></div>
        </div>
    </div>
    <div class="list-group" id="list-all-labels">
        <c:forEach items="${labels}" var="label">
            <div class="labelListThing col-lg-12">
                <div class="labelPanel editLabelPane">
                    <div class="showLabelPane" data-id="${label.id}" data-color="${label.color}">
                        <div class="navbar-left">
                            <form class="navbar-form navbar-left labelListEdit">
                                <span class="labelName theLabelListLabel label theLabelListText" style="min-width:100px; background-color: ${label.color}"><c:out value="${label.name}"/></span>
                            </form>
                        </div>
                        <div class="navbar-right">
                            <button type="button" class="btn btn-default btn-edit manageLabelsButton btn-sm"><span class="glyphicon glyphicon-pencil"></span> Edit</button>
                            <button type="button" class="btn btn-default btn-remove manageLabelsButton btn-sm"><span class="glyphicon glyphicon-remove"></span> Delete</button>
                        </div>
                    </div>
                            
                    <div class="editLabelPane hidden">
                        <div class="navbar-left">
                            <div class="navbar-form navbar-left labelListEdit">
                                <input type="text" class="small-input-box form-control flLeft labelListEdit" placeholder="label">
                                <button class="toggle-color-picker color-chooser-color labelColorManageColors col-lg-1"></button>
                                <div class="theColorsList" style="display: none">
                                    <span class="color-chooser-color color-square" data-color="#FF8F8F" style="background-color:#FF8F8F"></span>
                                    <span class="color-chooser-color color-square" data-color="#FFC69E" style="background-color:#FFC69E"></span>
                                    <span class="color-chooser-color color-square" data-color="#FFF4C4" style="background-color:#FFF4C4"></span>
                                    <span class="color-chooser-color color-square" data-color="#E6FAFF" style="background-color:#E6FAFF"></span>
                                    <span class="color-chooser-color color-square" data-color="#D8FFC4" style="background-color:#D8FFC4"></span>
                                    <span class="color-chooser-color color-square" data-color="#E6E6E6" style="background-color:#E6E6E6"></span>
                                    <span class="color-chooser-color color-square" data-color="#B6BDCC" style="background-color:#B6BDCC"></span>
                                </div>
                            </div>
                        </div>
                        <div class="navbar-right">
                            <button type="button" class="btn btn-success btn-sm manageButton btn-save-edit-label"><span class="glyphicon glyphicon-ok-circle"></span> Save</button>
                            <button type="button" class="btn btn-danger btn-sm manageButton btn-cancel-edit-label"><span class="glyphicon glyphicon-remove-circle"></span> Cancel</button>
                        </div>
                        <div class="col-lg-12 errorMessageManageLabels commentError text-warning commentContent"></div>
                    </div>
                    <div class="deleteLabelPanel" style="display: none">
                        <div class="navbar-left leftWarningDeleteLabel">
                            <span>Are you sure you want to permanently delete the label?</span>
                        </div>
                        <div class="navbar-right">
                            <button type="button" class="btn btn-danger btn-sm manageButton btn-delete-edit-label"><span class="glyphicon glyphicon-remove-sign"></span> Delete</button>
                            <button type="button" class="btn btn-default btn-sm manageButton btn-cancel-delete-label"><span class="glyphicon glyphicon-minus-sign"></span> Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
                            
        </c:forEach>
    </div>
</div>

<script src="/resources/js/manageLabels.js" type="text/javascript"></script>