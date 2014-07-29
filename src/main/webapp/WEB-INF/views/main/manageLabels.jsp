<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="content" class=container">
    <div class="col-lg-10 col-lg-offset-1">
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
        <ul class="list-group labelList">
            <li class="list-group-item">
                <div class="col-lg-12">
                    <div class="navbar-left">label</div>
                    <div class="navbar-right">
                        <button type="button" class="btn btn-default" id="editLabel"><span class="glyphicon glyphicon-pencil"></span> Edit</button>
                        <button type="button" class="btn btn-default" id="deleteLabel"><span class="glyphicon glyphicon-remove"></span> Delete</button>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="navbar-left col-lg-8">
                        <div class="form-group col-lg-10">
                            <input class="form-control col-lg-4 labelInput" id="focusedInput" type="text" value="label">
                            <button class="color-chooser-color labelColorManageColors" style="background-color:data-color;"></button>
                        </div>
                    </div>
                    <div class="navbar-right">
                        <button type="button" class="btn btn-success" id="saveEditLabel"><span class="glyphicon glyphicon-ok-circle"></span> Save</button>
                        <button type="button" class="btn btn-danger" id="cancelEditLabel"><span class="glyphicon glyphicon-remove-circle"></span> Cancel</button>
                    </div>
                </div>
                <div>
                    <div id="currentColor">
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
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
