// join.js

$(document).ready(function() {
    let joined = false;
    let currentUsername = "";

    // Create and append the Join button
    $('<button id="join-button" style="background-color: #333; color: white; padding: 10px 20px; border-radius: 5px; border: 1px solid yellow; cursor: pointer; margin: 10px; font-family: \'Lucida Console\', monospace;">Join</button>')
        .insertAfter('nav a:last'); // Place it after the last nav link

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

    // Join button click event
    $('#join-button').click(function() {
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

        // Success alert
        alert("Welcome to Play Tested!");
        $('#join-form').hide();
        joined = true;
        currentUsername = username;
        $('#join-button').text("Welcome " + currentUsername + "!");
        // Here you would send the user data to your server
    });
});