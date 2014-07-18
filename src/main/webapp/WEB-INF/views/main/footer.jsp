<%-- 
    Document   : footer.jsp
    Created on : Jul 17, 2014, 10:05:33 AM
    Author     : iapavaloaie
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<footer id="footer" >
    <hr>
    <nav class="navbar">
        <div class="navbar-inner navbar-content-center">
            <p class="text-muted credit">@Copyright Team Awesomeness</p>
        </div>
    </nav>
</footer>

<script>
    $(document).ready(function() {
        $("#content").css("margin-bottom", "65px");
        var x = $(document).height() - 220;
        $("#content").height(x);
    });
    $(window).resize(function() {
        $("#content").css("margin-bottom", "65px");

    });
</script>
