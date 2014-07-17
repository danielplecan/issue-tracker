<%-- 
    Document   : createView
    Created on : Jul 17, 2014, 9:46:09 AM
    Author     : vmiron
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <body>
        <div id="login-box">
            <legend>Create an Issue</legend>

            <form:form class="form-horizontal" modelAttribute='issue' action='create-issue' method='POST'>
                <fieldset>
                    <div class="form-group">
                        <label for="textArea" class="col-lg-2 control-label">Title</label>
                        <div class="col-lg-10">
                            <textarea class="form-control" rows="1" id="textArea"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="textArea" class="col-lg-2 control-label">Body</label>
                        <div class="col-lg-10">
                            <textarea class="form-control" rows="3" id="textArea"></textarea>
                        </div>
                    </div>   

                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <button class="btn btn-default">Create</button>
                        </div>
                    </div>

                </fieldset>
            </form:form>
        </div>
    </body>
</html>