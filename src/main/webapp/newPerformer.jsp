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
    <form action="newPerformer" method="post" class="container mt-4">
        <div class="mb-3">
            <label for="performer_name" class="form-label">Name</label>
            <input type="text" name="performer_name" id="performer_name" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Describe your act</label>
            <input type="text" name="description" id="description" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="genre" class="form-label">Genre</label>
            <input type="text" name="genre" id="genre" class="form-control" required>
        </div>
        <input type="submit" class="btn btn-primary">
    </form>
</div>

</body>
</html>