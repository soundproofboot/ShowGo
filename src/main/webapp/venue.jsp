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
<body>
<div class="container">

<c:import url="nav.jsp" />
<c:choose>
<%--    NO VENUE WITH THIS ID--%>
    <c:when test="${empty venue}">
        <h1>Not found</h1>
    </c:when>
    <c:otherwise>
        <h1>${venue.name}</h1>
        <c:set var="isOwner" value="${venue.user.id == user.id}" />
        <c:choose>
            <c:when test="${isOwner}">
<%--        OWNER--%>
                <p>Upcoming events</p>
                <c:choose>
                    <c:when test="${venue.events.size() == 0}">
                        <p>No upcoming events.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${venue.events}" var="event">
                            <p>name: ${event.title}</p>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <p>New Event</p>
                <form action="${context}/newEvent" method="post">
                    <input type="hidden" name="venue_id" id="venue_id" value="${venue.id}">
                    <input type="hidden" name="lineup" id="lineup" value="">
                    <label for="title">Event Title</label>
                    <input type="text" name="title" id="title" required>
                    <label for="event_start">Event start</label>
                    <input
                            type="datetime-local"
                            name="event_start"
                            id="event_start"
                            required
                    >
                    <div>
                        <p>Lineup</p>
                        <ul id="lineupList">

                        </ul>

                    </div>
                    <p>Search for bands to add to lineup</p>
                    <input type="text" id="performerSearch">
                    <button type="button" onclick="fetchPerformers()">Search</button>
                    <ul id="performerList"></ul>
                    <input type="submit" value="Create Event">
                </form>
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
                        <p>Followed</p>
                        <form action="${context}/removeVenueFollow" method="POST">
                            <input type="hidden" name="venue_id" id="venue_id" value="${venue.id}">
                            <input type="submit" value="Remove" />
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="${context}/addVenueFollow" method="POST">
                            <input type="hidden" name="venue_id" id="venue_id" value="${venue.id}">
                            <input type="submit" value="Follow" />
                        </form>
                    </c:otherwise>
                </c:choose>
                <p>managed by: <a href="${context}/users/${venue.user.username}">${venue.user.username}</a></p>
                <p>Fans: ${venue.followers.size()}</p>
                <p>events</p>
                <ul>
                    <c:forEach items="${venue.events}" var="event">
                        <li>
                            <h3><a href="${context}/events/${event.id}">${event.title}</a></h3>
                            <p>lineup</p>
                            <ul>
                                <c:forEach items="${event.performers}" var="eventPerformer">
                                    <li>${eventPerformer.performer.name}</li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
</div>

</body>
</html>