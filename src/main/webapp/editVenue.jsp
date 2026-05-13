<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<head>
    <script src="${context}/js/fetchStates.js"></script>
</head>
<body data-context="${context}">
<div class="container">
    <c:import url="nav.jsp" />
    <h3 class="mb-4 text-center">Edit Venue</h3>
    <form action="${context}/deleteVenue/${venue.id}" method="POST" class="text-center">
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
    <form action="${context}/editVenue/${venue.id}" method="POST" class="container mt-4">
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
            <label for="stateSelect" class="form-label">State</label>
            <select name="state" id="stateSelect" required>
                <option value="">State</option>
            </select>
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

