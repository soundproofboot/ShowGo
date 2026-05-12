<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<body>
<div class="container">
    <c:import url="nav.jsp" />
        <form action="${context}/editPerformer/${performer.id}" method="POST" class="container mt-4">
            <h3 class="mb-4 text-center">Edit Performer</h3>
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" name="name" id="name" class="form-control" value="${performer.name}">
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea name="description" id="description" class="form-control">${performer.description}</textarea>
            </div>
            <div class="mb-3">
                <label for="genre">Genre</label>
                <input type="text" name="genre" id="genre" class="form-control" value="${performer.genre}">
            </div>
            <div class="d-grid">
                <input type="submit" value="Update Performer" class="btn btn-primary">
            </div>
        </form>
</div>
</body>
</html>