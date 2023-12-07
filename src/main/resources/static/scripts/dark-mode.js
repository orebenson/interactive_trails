document.getElementById('darkModeToggle').addEventListener('click', function() {
    document.body.classList.toggle('dark-mode');
    document.querySelector('.nav').classList.toggle('dark-mode');
    document.querySelector('.page-container').classList.toggle('dark-mode');
    // Add more elements with dark mode toggling as needed
});