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
            <div class="list-group label-list" id="labelSelector">
                <li href="#" class="list-group-item list-item-text cursorPointer" data-id="1" data-color="black">
                       <div style="background-color:black" class="labelCircle"></div>
                       <div>123456789123456</div>
                </li>
                <li href="#" class="list-group-item list-item-text cursorPointer" data-id="1" data-color="black">
                       <div style="background-color:red" class="labelCircle"></div>
                       <div>123456789123456</div>
                </li>
                  <li href="#" class="list-group-item list-item-text cursorPointer" data-id="1" data-color="black">
                       <div style="background-color:green" class="labelCircle"></div>
                       <div>123456789123456</div>
                </li>

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
.cursorPointer {
    cursor: pointer;
    -webkit-touch-callout: none;
-webkit-user-select: none;
-khtml-user-select: none;
-moz-user-select: none;
-ms-user-select: none;
user-select: none;
}   
    
.labelcircle {
    border-radius: 7px;
    display: inline-block;
    float: left;
    height: 12px;
    margin-left: 9px;
    margin-right: 9px;
    margin-top: 4px;
    width: 12px;
    text-align: left;
}
.list-item-text{
     background-color: #FFFFFF;
    border: 1px solid #F5F5F5;
    display: block;
    margin-bottom: -1px;
    padding: 2px 10px;
    position: relative;
    text-align: left;
   
}
.list-group-item.list-item-text.cursorPointer{
    border-radius: 5px;
}
</style>