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
<h1>Register a new performer</h1>
<form action="newPerformer" method="post">
    <label for="performer_name">Name</label>
    <input type="text" name="performer_name" id="performer_name" required>
    <label for="description">Describe your act</label>
    <input type="text" name="description" id="description" required>
    <label for="genre">Genre</label>
    <input type="text" name="genre" id="genre" required>
    <input type="submit">
</form>
</div>

</body>
</html>