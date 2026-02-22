// JoinFunction.js

// Helper functions for cookie management
function setCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        // Set expiration date in milliseconds
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + value + expires + "; path=/";
    console.log("Cookie set:", document.cookie); // Log current cookies for debugging
}

function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        // Remove leading spaces
        while (c.charAt(0) === ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) === 0) {
            console.log("Cookie retrieved:", c.substring(nameEQ.length, c.length));
            return c.substring(nameEQ.length, c.length);
        }
    }
    return null;
}

$(document).ready(function() {
    let joined = false;
    let currentUsername = "";
    
    // Check for existing cookie on load
    var savedUsername = getCookie("ptUser"); // "ptUser" is our cookie name
    if (savedUsername) {
        joined = true;
        currentUsername = savedUsername;
        console.log("User already joined:", currentUsername);
    } else {
        console.log("No existing cookie found.");
    }
    
    // Create and append the Join button with proper text if already joined
    var joinButtonText = joined ? "Welcome " + currentUsername + "!" : "Join";
    $('<button id="join-button" style="background-color: #333; color: white; padding: 10px 20px; border-radius: 5px; border: 1px solid yellow; cursor: pointer; margin: 10px; font-family: \'Lucida Console\', monospace;">' + joinButtonText + '</button>')
        .insertAfter('nav a:last');

    // Create and append the Join form (initially hidden)
    $('body').append(`
        <div id="join-form" style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background-color: #333; color: white; padding: 20px; border: 4px solid yellow; z-index: 1001; border-radius: 10px;">
            <h2>Join Play Tested</h2>
            <label for="email">Email:</label><br>
            <input type="email" id="email" name="email" style="background-color: #444; color: white; border: 1px solid yellow; padding: 5px; margin-bottom: 10px; width: 250px;"><br>
            <label for="username">Username:</label><br>
            <input type="text" id="username" name="username" style="background-color: #444; color: white; border: 1px solid yellow; padding: 5px; margin-bottom: 10px; width: 250px;"><br>
            <label for="password">Password:</label><br>
            <input type="password" id="password" name="password" style="background-color: #444; color: white; border: 1px solid yellow; padding: 5px; margin-bottom: 10px; width: 250px;"><br>
            <button id="submit-join" style="background-color: #424242; color: white; padding: 10px 20px; border-radius: 5px; border: 1px solid yellow; cursor: pointer;">Submit</button>
            <button id="cancel-join" style="background-color: #424242; color: white; padding: 10px 20px; border-radius: 5px; border: 1px solid yellow; cursor: pointer;">Cancel</button>
        </div>
    `);

    // If already joined, disable the join button to prevent reopening the form
    if (joined) {
        $('#join-button').prop('disabled', true);
    }

    // Join button click event
    $('#join-button').click(function() {
        // Only show the form if not already joined
        if (!joined) {
            $('#join-form').show();
        }
    });

    // Cancel join click event
    $('#cancel-join').click(function() {
        $('#join-form').hide();
    });

    // Submit join click event
    $('#submit-join').click(function() {
        let email = $('#email').val();
        let username = $('#username').val();
        let password = $('#password').val();

        // Email validation
        let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert("Please enter a valid email address.");
            return;
        }

        // Username validation (at least 3 characters)
        if (username.length < 3) {
            alert("Username must be at least 3 characters.");
            return;
        }

        // Password validation (at least 8 characters)
        if (password.length < 8) {
            alert("Password must be at least 8 characters.");
            return;
        }

        // Success handling: notify user, set cookie, and update the join button
        alert("Welcome to Play Tested!");
        $('#join-form').hide();
        joined = true;
        currentUsername = username;
        setCookie("ptUser", currentUsername, 7); // Save cookie for 7 days
        $('#join-button').text("Welcome " + currentUsername + "!");
        $('#join-button').prop('disabled', true);
        // Here you would also send the user data to your server if needed
    });
});
