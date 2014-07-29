<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class=" col-lg-12 txt-center Absolute-Center ">
    <h3>
        <c:choose>
            <c:when test="${success == 'true'}">
                <span   >Activation successful</span>
                <p><a href="/login">Sign in</a></p>
            </c:when>
            <c:when test="${success == 'false'}"><span  >Activation failed</span></c:when>
        </c:choose>
    </h3>
</div>

