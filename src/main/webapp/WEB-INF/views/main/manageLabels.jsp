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

<style>
    .manageButton{
        margin-bottom: 4px;
    }
    #manageRightButtons{
        float:right;
    }
    #manageLeftButtons{
        float:left;
    }
    .inputLabelName{
        float:left;
        border-radius: 5px;
        margin: 2px;
        height: 29px;
        width:150px;
    }
    .labelColorManageColors{
        border-radius: 9px;
        height: 30px;
        width: 30px;
    }
    .labelColorManageColors:hover{
        border-radius:9px;
        height: 28px;
        width: 28px;
    }
    .theColorsList{
        float:left;
        margin-top: 5px;
        margin-bottom: 5px;
    }
    .color-chooser-color{
        float:left;
    }
    .flLeft{
        float:left;
    }
    .labelPanel{
        margin: 0px;
    }
    .manageLabelsNav{
        padding: 0px;
        padding-top: 15px;
        margin-top: 6px;
        margin-bottom: 6px;
        border-bottom: 1px #000;
    }
    .manageLabelsNavLeft{
        margin: 6px 0px;
        padding: 0px;
    }
    .manageLabelsNavRight{
        right: 0;
        float: right;
        margin: 6px 0px;
        height: 35px;
    }
    #editLabel{
        margin-top: 7px;
    }
    .manageLabelsButton{
        vertical-align: central;
    }
    .labelListEdit{
        padding:0;
        margin:0;
        vertical-align: central;
    }
    .theLabelListLabel{
        display:inline-flex;
        padding:5px;
        margin-top:3px;
        margin-right:3px;
    }
    .theLabelListText{
        margin: 0;
        vertical-align: central;
        font-size: 13pt;
        color: black;
        font-weight: lighter;
    }
    .errorMessageManageLabels{
        left: 0;
        margin-top: 5px;
    }
    .labelListThing:first-child{
        border-top: 1px solid #F5F5F5;
    }
    .labelListThing:last-child{
        border-bottom: none;
    }
    .labelListThing{
        padding: 15px 0px;
        clear: both;
        border-bottom: 1px solid #F5F5F5;
    }
    .navbar-form{
        border-bottom: #000;
    }
    .manageLabelsTopPanel,
    .topPanelThing{
        border-bottom: #000;
    }
    .createLabelImput,
    .searchLabelsBar{
        height: 35px;
    }
    .editLabelPane{
        padding: 0px;
    }
    
</style>

<script src="/resources/js/manageLabels.js" type="text/javascript"></script>