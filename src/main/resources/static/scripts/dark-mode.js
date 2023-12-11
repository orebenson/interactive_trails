document.addEventListener('DOMContentLoaded', function () {
    const isDarkMode = localStorage.getItem('darkMode') === 'true';
    const body = document.body;

    if (isDarkMode) {
        body.classList.add('dark-mode');
    }

    const toggleButton = document.getElementById('darkModeToggle');

    toggleButton.addEventListener('click', function () {
        body.classList.toggle('dark-mode');
        const isDarkModeNow = body.classList.contains('dark-mode');
        localStorage.setItem('darkMode', isDarkModeNow.toString());
    });
});