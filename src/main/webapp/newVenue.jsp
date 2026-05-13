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
    <form action="newVenue" method="post" class="container mt-4">
        <div class="mb-3">
            <label for="venue_name" class="form-label">Name</label>
            <input type="text" name="venue_name" id="venue_name" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="street_address" class="form-label">Street Address</label>
            <input type="text" name="street_address" id="street_address" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="city" class="form-label">City</label>
            <input type="text" name="city" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="state" class="form-label">State</label>
            <input type="text" name="state" id="state" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Tell us about the venue...</label>
            <textarea name="description" id="description" class="form-control" rows="4"></textarea>
        </div>
        <input type="submit" class="btn btn-primary">
    </form>
</div>

</body>
</html>