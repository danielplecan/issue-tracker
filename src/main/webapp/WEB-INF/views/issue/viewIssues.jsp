<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="pageContent">

    <aside id="search_widget">
        <div class="panel-primary" style="margin-bottom:20px;">
            <div class="panel-heading">
                <h3 class="panel-title" style='display:inline-block;'>Filter issues</h3>
                <span class="close" id="header-arrow">&#x21d3</span>
            </div>
            <div class=" panel panel-body " id="panelBody">
                <div class="form-group col-lg-12">
                    <label for="searchFieldTitle" class="col-lg-2 control-label">Title</label>
                    <div class="col-lg-9">
                        <input class="form-control" id="searchFieldTitle" placeholder="Title" type="text">
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label for="searchFieldContent" class="col-lg-2 control-label">Content</label>
                    <div class="col-lg-9">
                        <input class="form-control" id="searchFieldContent" placeholder="Content" type="text">
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label for="stateSelect" class="col-lg-2 control-label">State</label>
                    <div class="col-lg-2 col-lg-offset-3">
                        <select id="stateSelect" class="form-control">
                            <option value="All">All</option>
                            <option value="Open">Open</option>
                            <option value="Closed">Closed</option>                      
                        </select>  
                    </div>
                </div>
                <div class="form-group col-lg-12">
                    <label for="orderBySelect" class="col-lg-2 control-label">Order By</label>
                    <div class="col-lg-2 col-lg-offset-3">
                        <select id="orderBySelect" class="form-control">
                            <option value="UpdateDate">UpdateDate</option>
                            <option value="Title">Title</option>                            
                        </select>  
                    </div>
                    <div  class="checkbox col-lg-2">
                        <label>
                            <input id="orderByAscDesc" type="checkbox"> Asc
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-12 txt-center">                     
                        <button id="searchButtonFilter" class="btn btn-primary">Search</button>
                    </div>
                </div>



            </div> 
            <!--            <div class=" panel panel-body " id="panelBody">
                            <table id="searchBoxStructure">
            
                                <tr>
                                    <td id="labelCol">
                                        <label>Title:</label>  
                                    </td>
                                    <td  id="inputCol">
                                        <input type="text" name="title" class="form-control" id="searchFieldTitle" placeholder="Title keyword" autocomplete="off"/>
                                    </td>
                                </tr>
            
                                <tr>  
                                    <td>
                                        <label>Content: </label>
                                    </td>
                                    <td>
                                        <input type="text" name="content" class="form-control" id="searchFieldContent" placeholder="Content keyword"/>
                                    </td>
            
                                    <td>
                                        <button type="button" class="btn btn-primary">Search</button>
                                    <td>
                                </tr>
            
            
                                <tr>    
                                    <td>
                                        <label>State</label>
                                    </td>
                                    <td>
            
                                        <select class="form-control">
                                            <option>All</option>
                                            <option>Open</option>
                                            <option>Closed</option>
                                            <option>Reopened</option>
                                        </select>
            
                                    </td>
            
                                </tr>
                            </table>
                        </div>-->
        </div>
    </aside>

    <div id="allIssues"> 
    </div>

    <div class="col-lg-12 txt-center">
        <ul class="pager" style="display: inline-block">
            <li class="firstButton first disabled"><a >«</a></li>
            <li class="prevButton first disabled"><a>Prev</a></li>
        </ul>
        <label class="pageLabel"></label>
        <ul class="pager" style="display: inline-block">
            <li class="nextButton last"><a>Next</a></li>
            <li class="lastButton last"><a>»</a></li>
        </ul>
    </div>
</div>
<script>
    $("#panelBody").hide();

    $('body').keydown(function(e) {
        if (e.keyCode === 13) {
            $('#searchBoxStructure button').trigger('click');
        }
    });

    $(".panel-heading").click(function() {
        $("#panelBody").toggle("slow", function() {
            if ($("#panelBody").is(':visible')) {
                document.getElementById('header-arrow').innerHTML = '&#x21d1';
                $("#searchFieldTitle").focus();
            }
            else {
                document.getElementById('header-arrow').innerHTML = '&#x21d3';
            }
        });
    });
</script>
<script src="/resources/js/createIssue.js" type="text/javascript"></script>
<script src="/resources/js/issuesCreator.js" type="text/javascript"></script>
<script src="/resources/js/pagination.js" type="text/javascript"></script>
<style>    
    @media (min-width: 1200px) { 
        #search_widget{
            margin-left: 8.33333333%;
            width: 83.33333333%;
        }
    }

    .panel-heading span {
        display:inline-block; float:right;
    }

    .panel-heading span:hover {
        cursor:default;
    }

</style>