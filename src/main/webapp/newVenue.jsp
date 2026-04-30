<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<body>
    <c:if test="${empty user}">
        <c:redirect url="/" />
    </c:if>
    <c:import url="nav.jsp" />
    <h1>Register a new venue</h1>
    <form action="newVenue" method="post">
        <%--        TODO more info--%>
        <label for="venue_name">Name</label>
        <input type="text" name="venue_name" id="venue_name" required>
        <label for="city">City</label>
        <input type="text" name="city" name="state" required>
        <label for="state">State</label>
        <input type="text" name="state" id="state" required>
        <input type="submit">
    </form>
</body>
</html>