<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<head>
    <script>
        const initialPerformers = ${performersJson};
    </script>
    <script src="${context}/js/eventForm.js"></script>
    <style>
        li {
            width: fit-content;
            margin: .5em auto;
        }
    </style>
</head>
<body data-context="${context}">
<div class="container">
    <c:import url="nav.jsp" />
    <h3 class="mb-4 text-center">Edit Event</h3>
    <form action="${context}/cancelEvent/${event.id}" method="POST" class="text-center">
        <button type="submit" class="btn btn-danger">Cancel</button>
    </form>
    <form action="${context}/editEvent/${event.id}" method="POST" class="container mt-4">
        <input type="hidden" name="lineup" id="lineup" value="">
        <div class="mb-3">
            <label for="title" class="form-label">Event Title</label>
            <input type="text" class="form-control" id="title" name="title" value="${event.title}">
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control" id="description" name="description" value="${event.description}">
        </div>
        <div class="mb-3">
            <label for="ticket_price" class="form-label">Ticket Price</label>
            <div class="input-group">
                <span class="input-group-text">$</span>
                <input type="number" step="any" min="0" name="ticket_price" id="ticket_price" class="form-control" value="${event.ticketPrice}">
            </div>
        </div>
        <div class="mb-3">
            <label for="event_start" class="form-label">Event Start</label>
            <input type="datetime-local" name="event_start" id="event_start" required class="form-control" value="${eventStart}">
        </div>
        <div class="mb-3">
            <p class="fw-bold">Lineup</p>
            <ul id="lineupList" class="list-group mb-2"></ul>
        </div>
        <div class="mb-3">
            <label for="performerSearch" class="form-label">Search for bands to add to lineup</label>
            <div class="input-group">
                <input type="text" id="performerSearch" class="form-control">
                <button type="button" onclick="fetchPerformers()" class="btn btn-secondary">Search</button>
            </div>
            <ul id="performerList" class="list-group mt-2"></ul>
        </div>
        <div class="d-grid">
            <input type="submit" value="Update Event" class="btn btn-primary">
        </div>
    </form>
</div>
</body>
</html>