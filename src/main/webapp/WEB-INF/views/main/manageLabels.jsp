<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-default col-lg-10 col-lg-offset-1" id="widgetContainer">
    <div class="manageLabelsTopPanel">
        <div class="manageLabelsNav">
            <form class="navbar-form navbar-left manageLabelsNavLeft">
                <input type="text" class="form-control" placeholder="Search labels..">
                <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
            </form>
            <button type="button" class="btn btn-default manageLabelsNavRight btn-new-label" id="editLabel"><span class="glyphicon glyphicon-plus"></span>  Create new label</button>
        </div>
        <div class="editLabelPane" style="display:none">
            <div class="navbar-left">
                <div class="navbar-form navbar-left ">
                    <input type="text" class="small-input-box form-control flLeft" placeholder="label">
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
            <div class="manageLabelsNavRight">
                <button type="button" class="btn btn-success btn-sm manageButton btn-create-edit-label"><span class="glyphicon glyphicon-ok-circle"></span> Create</button>
                <button type="button" class="btn btn-danger btn-sm manageButton btn-cancel-edit-label"><span class="glyphicon glyphicon-remove-circle"></span> Cancel</button>
            </div>
        </div>
    </div>

    <ul class="list-group">
        <c:forEach items="${labels}" var="label">
            <li  class="list-group-item">
                <div class="labelPanel">
                    <div class="showLabelPane" data-id="${label.id}" data-color="${label.color}">
                        <div class="navbar-left">
                            <form class="navbar-form navbar-left">
                                <span class="labelName" style="background-color: ${label.color}">${label.name}</span>
                            </form>
                        </div>
                        <div class="navbar-right">
                            <button type="button" class="btn btn-default btn-edit"><span class="glyphicon glyphicon-pencil"></span> Edit</button>
                            <button type="button" class="btn btn-default btn-remove"><span class="glyphicon glyphicon-remove"></span> Delete</button>
                        </div>
                    </div>
                    <div class="editLabelPane hidden">
                        <div class="navbar-left">
                            <div class="navbar-form navbar-left ">
                                <input type="text" class="small-input-box form-control flLeft" placeholder="label">
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
        min-height: 83px;
        background-color: #222222;
        margin:  15px;
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
    .editLabelPane{

    }
    #editLabel{
        margin-top: 7px;
    }
    .manageLabelsTopPanel{
        height: 100px;
        min-height: 50px;
    }



</style>

<script src="/resources/js/manageLabels.js" type="text/javascript"></script>