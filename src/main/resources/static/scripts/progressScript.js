function newProgress(){
    let newValue = document.getElementById("completed").value;
    let workingProgressBar = document.getElementById("progressBar");
    let progression = Math.min(Math.max(parseInt(newValue), 0), 100);
    let titleStatus = document.getElementById("status");
    let newStoredProgress = 0;

    workingProgressBar.style.width = progression + "%";
    workingProgressBar.innerHTML = progression + "%";

    if(progression >= 100){
        workingProgressBar.innerHTML = "Trail Complete!"
        titleStatus.innerHTML = "Status: Completed!"
    }
    if(progression <=99){
        titleStatus.innerHTML = "Status: Incomplete"
    }


}

function storedProgress(value){
    newStoredProgress = newValue;
    newProgress(newValue);
}

function completedTrail(){
    function completeTrailTitle(){


    }
}