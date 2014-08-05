<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class=" col-lg-12 txt-center Absolute-Center ">
    <h3>
        <c:choose>
            <c:when test="${success == 'true'}">
                <span   >Activation successful</span>
                <span><a href="/login">Sign in</a></span>
            </c:when>
            <c:when test="${success == 'false'}"><span  >Activation failed</span></c:when>
        </c:choose>
    </h3>
</div>

