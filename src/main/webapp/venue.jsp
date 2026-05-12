<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<script>

    let performerArr = [];
    let lineupArr = [];

    function addPerformerToLineup(performer) {
        if (performer) {
            if (!lineupArr.some(p => p.id === performer.id)) {
                lineupArr.push(performer);
            }
            setLineupInputValue();
            updateLineupDisplayed();
        }
    }

    function setLineupInputValue() {
        let lineupInput = document.querySelector("#lineup");
        lineupInput.value = lineupArr.map(p => p.id).join(",");
    }

    function updateLineupDisplayed() {
        let lineupList = document.querySelector("#lineupList");
        lineupList.innerHTML = "";
        for (let performer of lineupArr) {
            let performerLi = document.createElement("li");
            let performerNameEl = document.createElement("span");
            performerNameEl.textContent = performer.name;

            let removeBtn = document.createElement("button");
            removeBtn.innerText = "X";
            removeBtn.addEventListener("click", () => {
                removePerformerFromLineup(performer.id);
                updateLineupDisplayed();
                setLineupInputValue();
                updatePerformerList();
            })
            performerLi.append(performerNameEl, removeBtn);

            lineupList.appendChild(performerLi);
        }
    }

    function removePerformerFromLineup(performerId) {
        lineupArr = lineupArr.filter(p => p.id !== performerId);
    }

    async function fetchPerformers() {
        let searchTerm = document.querySelector("#performerSearch").value;
        if (searchTerm) {
            let performerResponse = await fetch("${context}/api/performers?name=" + searchTerm);
            let data = await performerResponse.json();
            performerArr = data;
        }

        updatePerformerList();
    }

    function updatePerformerList() {
        let performerList = document.querySelector("#performerList");
        performerList.innerHTML = "";
        if (performerArr.length > 0) {
            for (let performer of performerArr) {
                let liEl = document.createElement("li");
                liEl.textContent = performer.name;


                if (!lineupArr.some(p => p.id === performer.id)) {
                    let buttonEl = document.createElement("button");
                    buttonEl.classList.add("btn", "btn-primary", "mx-2");
                    buttonEl.textContent = "Add";
                    buttonEl.addEventListener("click", () => {
                        addPerformerToLineup(performer);
                        updatePerformerList();
                    })
                    liEl.appendChild(buttonEl);
                }
                performerList.appendChild(liEl);
            }
        } else {
            let liEl = document.createElement("li");
            liEl.textContent = "No results";
            performerList.appendChild(liEl);
        }
    }
</script>
<style>
    li {
        width: fit-content;
        margin: .5em auto;
    }
</style>
<body>
<div class="container">

<c:import url="nav.jsp" />
<c:choose>
<%--    NO VENUE WITH THIS ID--%>
    <c:when test="${empty venue}">
        <h1>Not found</h1>
    </c:when>
    <c:otherwise>
        <h1 class="text-center">${venue.name}</h1>
        <h2 class="text-center">${venue.streetAddress} ${venue.city}, ${venue.state}</h2>
        <c:set var="isOwner" value="${venue.user.id == user.id}" />
        <c:choose>
            <c:when test="${isOwner}">
<%--        OWNER--%>
                <div class="text-center">
                    <a href="${context}/editVenue/${venue.id}" class="btn btn-primary">Edit</a>
                </div>
                <form action="${context}/newEvent" method="post" class="container mt-4" style="max-width: 600px;">
                    <input type="hidden" name="venue_id" id="venue_id" value="${venue.id}">
                    <input type="hidden" name="lineup" id="lineup" value="">
                    <h3 class="mb-4 text-center">Create New Event</h3>
                    <div class="mb-3">
                        <label for="title" class="form-label">Event Title</label>
                        <input type="text" name="title" id="title" required class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <textarea name="description" id="description" class="form-control" rows="3"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="ticket_price" class="form-label">Ticket Price</label>
                        <div class="input-group">
                            <span class="input-group-text">$</span>
                            <input type="number" step="any" min="0" name="ticket_price" id="ticket_price" class="form-control" placeholder="Leave blank if free">
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="event_start" class="form-label">Event Start</label>
                        <input type="datetime-local" name="event_start" id="event_start" required class="form-control">
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
                        <input type="submit" value="Create Event" class="btn btn-primary">
                    </div>
                </form>
                <h2 class="text-center">Upcoming events</h2>
                <c:choose>
                    <c:when test="${venue.events.size() == 0}">
                        <p>No upcoming events.</p>
                    </c:when>
                    <c:otherwise>
                        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-4 justify-content-center">
                            <c:forEach items="${venue.events}" var="event">
                                <c:set var="event" value="${event}" scope="request"/>
                                <jsp:include page="components/eventSimple.jsp"/>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
<%--                NOT OWNER--%>
                <c:set var="isFollowed" value="false" />
                <c:choose>
                    <c:when test="${not empty user}">
                        <c:forEach items="${user.venueFollows}" var="venueFollow">
                            <c:if test="${venueFollow.venue.id == venue.id}">
                                <c:set var="isFollowed" value="true" />
                            </c:if>
                        </c:forEach>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${isFollowed}">
                        <p class="text-center">Followed</p>
                        <form action="${context}/removeVenueFollow" method="POST" class="text-center">
                            <input type="hidden" name="venue_id" id="venue_id" value="${venue.id}">
                            <input type="submit" value="Remove" />
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="${context}/addVenueFollow" method="POST" class="text-center">
                            <input type="hidden" name="venue_id" id="venue_id" value="${venue.id}">
                            <input type="submit" value="Follow" />
                        </form>
                    </c:otherwise>
                </c:choose>
                <p><strong>Manager:</strong> <a href="${context}/users/${venue.user.username}">${venue.user.username}</a></p>
                <p><strong>Fans:</strong> ${venue.followers.size()}</p>
                <p>${venue.description}</p>
                <h2 class="text-center">Upcoming Events</h2>
                <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gap-4 justify-content-center">
                    <c:forEach items="${venue.events}" var="event">
                        <c:set var="event" value="${event}" scope="request"/>
                        <jsp:include page="components/eventSimple.jsp"/>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
</div>

</body>
</html>