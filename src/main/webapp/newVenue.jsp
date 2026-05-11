<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<body>
<div class="container">

    <c:if test="${empty user}">
        <c:redirect url="/" />
    </c:if>
    <c:import url="nav.jsp" />
    <h1>Register a new venue</h1>
    <form action="newVenue" method="post">
        <label for="venue_name">Name</label>
        <input type="text" name="venue_name" id="venue_name" required>
        <label for="street_address">Street Address</label>
        <input type="text" name="street_address" id="street_address" required>
        <label for="city">City</label>
        <input type="text" name="city" required>
        <label for="state">State</label>
        <input type="text" name="state" id="state" required>
        <label for="description">Tell us about the venue...</label>
        <textarea name="description" id="description"></textarea>
        <input type="submit">
    </form>
</div>

</body>
</html>