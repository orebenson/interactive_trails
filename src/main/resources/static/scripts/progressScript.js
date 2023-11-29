function newProgress(){
    var newValue = document.getElementById("completed").value;
    var progressBar = document.getElementById("progressBar");
    var progression = Math.min(Math.max(parseInt(newValue), 0), 100);

    
    progressBar.style.width = progression + "%";
    progressBar.innerHTML = progression + "%";
}