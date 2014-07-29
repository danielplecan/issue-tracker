<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="widgetContainer">
    <div class="panel panel-default panel-body">
        <div class="navbar-left">
            <form class="navbar-form navbar-left">
                <input type="text" class="form-control" placeholder="Search labels..">
                <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
            </form>
        </div>
        <div class="navbar-right">
            <button type="button" class="btn btn-default" id="editLabel"><span class="glyphicon glyphicon-plus"></span>  Create new label</button>
        </div>
    </div>
    <div class="panel panel-default panel-body">
        <div class="editLabelPane">
            <div class="navbar-left">
                <form class="navbar-form navbar-left ">
                    <input type="text" class="form-control flLeft" placeholder="label">
                    <button class="color-chooser-color labelColorManageColors col-lg-1" style="background-color:#FF8F8F;"></button>            <div class="theColorsList">
                        <span class="color-chooser-color" data-color="#FF8F8F" style="background-color:#FF8F8F"></span>
                        <span class="color-chooser-color" data-color="#FFC69E" style="background-color:#FFC69E"></span>
                        <span class="color-chooser-color" data-color="#FFF4C4" style="background-color:#FFF4C4"></span>
                        <span class="color-chooser-color" data-color="#E6FAFF" style="background-color:#E6FAFF"></span>
                        <span class="color-chooser-color" data-color="#D8FFC4" style="background-color:#D8FFC4"></span>
                        <span class="color-chooser-color" data-color="#E6E6E6" style="background-color:#E6E6E6"></span>
                        <span class="color-chooser-color" data-color="#B6BDCC" style="background-color:#B6BDCC"></span>
                    </div>
                </form>
            </div>
            <div class="navbar-right">
                <button type="button" class="btn btn-success btn-sm manageButton btn-save-edit-label"><span class="glyphicon glyphicon-ok-circle"></span> Save</button>
                <button type="button" class="btn btn-danger btn-sm manageButton btn-cancel-edit-label"><span class="glyphicon glyphicon-remove-circle"></span> Cancel</button>
            </div>
        </div>
    </div>




    <ul style="list-style: none">
        <li>
            <div class="panel panel-default panel-body">
                <div class="showLabelPane">
                    <div class="navbar-left">
                        <form class="navbar-form navbar-left">
                            <span class="">label</span>  
                        </form>
                    </div>
                    <div class="navbar-right">
                        <button type="button" class="btn btn-default btn-edit"><span class="glyphicon glyphicon-pencil"></span> Edit</button>
                        <button type="button" class="btn btn-default btn-remove"><span class="glyphicon glyphicon-remove"></span> Delete</button>
                    </div>
                </div>
                <div class="editLabelPane hidden">
                    <div class="navbar-left">
                        <form class="navbar-form navbar-left ">
                            <input type="text" class="form-control flLeft" placeholder="label">
                            <button class="color-chooser-color labelColorManageColors col-lg-1" style="background-color:#FF8F8F;"></button>            <div class="theColorsList">
                                <span class="color-chooser-color" data-color="#FF8F8F" style="background-color:#FF8F8F"></span>
                                <span class="color-chooser-color" data-color="#FFC69E" style="background-color:#FFC69E"></span>
                                <span class="color-chooser-color" data-color="#FFF4C4" style="background-color:#FFF4C4"></span>
                                <span class="color-chooser-color" data-color="#E6FAFF" style="background-color:#E6FAFF"></span>
                                <span class="color-chooser-color" data-color="#D8FFC4" style="background-color:#D8FFC4"></span>
                                <span class="color-chooser-color" data-color="#E6E6E6" style="background-color:#E6E6E6"></span>
                                <span class="color-chooser-color" data-color="#B6BDCC" style="background-color:#B6BDCC"></span>
                            </div>
                        </form>
                    </div>
                    <div class="navbar-right">
                        <button type="button" class="btn btn-success btn-sm manageButton btn-save-edit-label"><span class="glyphicon glyphicon-ok-circle"></span> Save</button>
                        <button type="button" class="btn btn-danger btn-sm manageButton btn-cancel-edit-label"><span class="glyphicon glyphicon-remove-circle"></span> Cancel</button>
                    </div>
                </div>
            </div>   
        </li>
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

</style>

<script src="/resources/js/manageLabels.js" type="text/javascript"></script>