
// Array to store user details
let users = [];

// Switch to Sign Up page
document.getElementById('signup-link').addEventListener('click', function (e) {
    e.preventDefault();
    document.getElementById('signin').classList.remove('show', 'active');
    document.getElementById('signup').classList.add('show', 'active');
});

// Switch to Sign In page
document.getElementById('signin-link').addEventListener('click', function (e) {
    e.preventDefault();
    document.getElementById('signup').classList.remove('show', 'active');
    document.getElementById('signin').classList.add('show', 'active');
});

// Handle Sign Up form submission
document.getElementById('signupForm').addEventListener('submit', function(e) {
    e.preventDefault();

    // Get field values
    const name = document.getElementById('signupName').value;
    const email = document.getElementById('signupEmail').value;
    const password = document.getElementById('signupPassword').value;
    const phone = document.getElementById('signupPhone').value;
    const address = document.getElementById('signupAddress').value;
    const dob = document.getElementById('signupDob').value;

    // Validate fields
    if (!/^[a-zA-Z\s]+$/.test(name)) {
        alert('Please enter a valid name (alphabetic characters only).');
        return;
    }

    if (!/^\S+@\S+\.\S+$/.test(email)) {
        alert('Please enter a valid email address.');
        return;
    }

    if (password.length < 6) {
        alert('Password must be at least 6 characters long.');
        return;
    }

    if (!/^\d{10}$/.test(phone)) {
        alert('Please enter a valid phone number (10 digits).');
        return;
    }

    if (address.trim() === '') {
        alert('Address cannot be empty.');
        return;
    }

    if (!dob) {
        alert('Please enter your date of birth.');
        return;
    }

    // Store the new user details if validation passes
    users.push({ name, email, password, phone, address, dob });
    alert('Account created successfully! Please sign in.');

    // Switch to the sign-in page after successful signup
    document.getElementById('signup').classList.remove('show', 'active');
    document.getElementById('signin').classList.add('show', 'active');
});

// Handle Sign In form submission
document.getElementById('signinForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const email = document.getElementById('signinEmail').value;
    const password = document.getElementById('signinPassword').value;

    // Check if the user exists and password matches
    const user = users.find(user => user.email === email && user.password === password);
    if (user) {
        alert('Login successful!');
        // Redirect to next page (adjust this path accordingly)
        window.location.href = "https://dinesh200512.github.io/CSS";
    } else {
        alert('Invalid email or password. Please try again.');
    }
});
