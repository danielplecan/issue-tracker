<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-default col-lg-10 col-lg-offset-1" id="widgetContainer">
    <div class="manageLabelsTopPanel topPanelThing">
        <div class="manageLabelsNav">
            <form class="navbar-form navbar-left manageLabelsNavLeft">
                <input type="text" class="form-control" placeholder="Search labels..">
                <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
            </form>
            <button type="button" class="btn btn-default manageLabelsNavRight btn-new-label" id="editLabel"><span class="glyphicon glyphicon-plus"></span>  Create new label</button>
        </div>
        <div class="editLabelPane" style="display: none">
            <div class="navbar-form manageLabelsNavLeft">
                <input type="text" class="form-control flLeft small-input-box" placeholder="label">
                <button class="toggle-color-picker color-chooser-color labelColorManageColors col-lg-1"></button>
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
            <div class="col-lg-12 errorMessageManageLabels commentError text-warning commentContent">Error message</div>
        </div>
    </div>
    <ul class="list-group">
        <c:forEach items="${labels}" var="label">
            <li class="list-group-item labelListThing">
                <div class="labelPanel  editLabelPane">
                    <div class="showLabelPane" data-id="${label.id}" data-color="${label.color}">
                        <div class="navbar-left">
                            <form class="navbar-form navbar-left labelListEdit">
                                <span class="labelName theLabelListLabel label theLabelListText" style="background-color: ${label.color}">${label.name}</span>
                            </form>
                        </div>
                        <div class="navbar-right">
                            <button type="button" class="btn btn-default btn-edit manageLabelsButton btn-sm"><span class="glyphicon glyphicon-pencil"></span> Edit</button>
                            <button type="button" class="btn btn-default btn-remove manageLabelsButton btn-sm"><span class="glyphicon glyphicon-remove"></span> Delete</button>
                        </div>
                    </div>
                            
                    <div class="editLabelPane hidden">
                        <div class="navbar-left ">
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
                        <div class="col-lg-12 errorMessageManageLabels commentError text-warning commentContent">Error message</div>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
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
        border-radius: 9px;
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
        margin:  0px;
        border: none;
    }
    .manageLabelsNav{
        /*padding: 15px;*/
        margin-top: 6px;
        margin-bottom: 6px;
        min-height: 50px;
    }
    .manageLabelsNavLeft{
        margin: 6px 0px;
    }
    .manageLabelsNavRight{
        right: 0;
        float: right;
        margin: 6px 0px;
    }
    #editLabel{
        margin-top: 7px;
    }
    .manageLabelsTopPanel{
        min-height: 100px;
    }
    .manageLabelsButton{
        vertical-align: central;
    }
    .labelListEdit{
        padding: 0;
        margin: 0;
        height: 30px;
        vertical-align: central;
        
    }
    .theLabelListLabel{
        display:inline-flex;
        padding:5px;
        margin-top:3px;
        margin-right:3px;
        height: 30px;
    }
    .theLabelListText{
        margin: 0;
        vertical-align: central;
    }
    .errorMessageManageLabels{
        left: 0;
        margin-top: 5px;
    }
    .labelListThing{
        margin-bottom: 50px;
        height: 30px;
        border-bottom: none;
    }

    .topPanelThing{
        margin-bottom: 40px;
    }
</style>

<script src="/resources/js/manageLabels.js" type="text/javascript"></script>