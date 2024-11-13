// JavaScript function to handle login form submission
function handleLogin(event) {
    event.preventDefault(); // Prevent the default form submission

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (username && password) {
        // Store the username in localStorage for use on the dashboard
        localStorage.setItem('username', username);
        
        // Simulate successful login - in real application, add authentication logic here
        console.log("Username:", username);
        console.log("Password:", password);
        
        // Redirect to dashboard
        window.location.href = "dashboard.html";
    } else {
        alert("Please enter both username and password.");
    }
}

// JavaScript function to handle signup form submission
function handleSignUp(event) {
    event.preventDefault(); // Prevent the default form submission

    const username = document.getElementById('signup-username').value;
    const password = document.getElementById('signup-password').value;
    const confirmPassword = document.getElementById('confirm-password').value;

    // Check if passwords match
    if (password !== confirmPassword) {
        alert("Passwords do not match. Please try again.");
        return;
    }

    if (username && password) {
        // Store the username in localStorage for use on the dashboard
        localStorage.setItem('username', username);

        // Simulate successful signup - in real application, add signup logic here
        console.log("Signup Username:", username);
        console.log("Signup Password:", password);

        // Redirect to dashboard
        window.location.href = "dashboard.html";
    } else {
        alert("Please fill out all fields.");
    }
}

// JavaScript to retrieve the username and display it on the dashboard page
document.addEventListener("DOMContentLoaded", function() {
    // Check if we're on the dashboard page
    if (window.location.pathname.includes("dashboard.html")) {
        const username = localStorage.getItem('username');
        const usernameDisplay = document.getElementById('username-display');

        if (username && usernameDisplay) {
            usernameDisplay.textContent = username;
        }
    }
});
