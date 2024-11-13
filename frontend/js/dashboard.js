// Retrieve the username from localStorage and display it on the dashboard page
document.addEventListener("DOMContentLoaded", function() {
    const username = localStorage.getItem('username');
    const usernameDisplay = document.getElementById('username-display');

    if (username && usernameDisplay) {
        usernameDisplay.textContent = username;
    }
});
