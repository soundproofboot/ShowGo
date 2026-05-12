<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<body>
<div class="container">
    <c:import url="nav.jsp" />
    <h1 class="text-center">Edit Venue Details</h1>
    <form action="${context}/editVenue/${venue.id}" method="POST" class="container mt-4">
        <h3 class="mb-4 text-center">Edit Venue</h3>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" name="name" id="name" value="${venue.name}" class="form-control">
        </div>
        <div class="mb-3">
            <label for="street_address" class="form-label">Street Address</label>
            <input type="text" name="street_address" id="street_address" value="${venue.streetAddress}" class="form-control">
        </div>
        <div class="mb-3">
            <label for="city" class="form-label">City</label>
            <input type="text" name="city" id="city" value="${venue.city}" class="form-control">
        </div>
        <div class="mb-3">
            <label for="state" class="form-label">State</label>
            <input type="text" name="state" id="state" value="${venue.state}" class="form-control">
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea name="description" id="description" class="form-control" rows="4">${venue.description}</textarea>
        </div>
        <div class="d-grid">
            <input type="submit" value="Update Venue" class="btn btn-primary">
        </div>
    </form>
</div>
</body>

