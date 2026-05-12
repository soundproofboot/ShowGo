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
    let context = document.body.dataset.context;
    let searchTerm = document.querySelector("#performerSearch").value;
    if (searchTerm) {
        let performerResponse = await fetch(`${context}/api/performers?name=` + searchTerm);
        performerArr = await performerResponse.json();
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
                buttonEl.setAttribute("type", "button");
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

window.onload=() => {
    if (initialPerformers.length) {
        lineupArr = [...initialPerformers];
        setLineupInputValue();
        updateLineupDisplayed();
    }
}