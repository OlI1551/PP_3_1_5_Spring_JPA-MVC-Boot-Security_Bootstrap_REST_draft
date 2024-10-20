async function createUser(event) {
    event.preventDefault(); // Prevent form from submitting the traditional way

    // Extract selected roles from the multi-select dropdown
    const checkedRoles = () => {
        let selectedRoles = [];
        let options = document.querySelector('#create-roles').options;
        for (let i = 0; i < options.length; i++) {
            if (options[i].selected) {
                selectedRoles.push(roleList[i]);
            }
        }
        return selectedRoles;
    }

    // Construct new user object
    const newUser = {
        firstName: $("#create-firstName").val().trim(),
        lastName: $("#create-lastName").val().trim(),
        age: $("#create-age").val().trim(),
        email: $("#create-email").val(),
        password: $("#create-password").val(),
        roles: checkedRoles() // Roles array
    };

    // Log the new user data to check
    console.log(newUser);

    // // Retrieve CSRF token and header from the HTML meta tags
    // let csrfToken = document.querySelector('meta[name="_csrf"]')?.getAttribute('content');
    // let csrfHeader = document.querySelector('meta[name="_csrf_header"]')?.getAttribute('content');
    //
    // // Log the csrfHeader and csrfToken for debugging
    // console.log("CSRF Token:", csrfToken);
    // console.log("CSRF Header:", csrfHeader);
    //
    // if (!csrfToken || !csrfHeader) {
    //     console.error("CSRF token or header not found in the HTML.");
    //     return;
    // }

    try {
        // Send data to the server via a POST request
        let response = await fetch("admin/create", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
                // [csrfHeader]: csrfToken // Include CSRF token here
            },
            body: JSON.stringify(newUser)
        });

        // Check if the response is successful
        if (response.ok) {
            window.location.href = "admin"; // Redirect to the admin page if successful
        } else {
            // Handle response errors, if any
            const errorText = await response.text();
            console.error("Error creating user:", errorText);
            alert("Error creating user. Please try again.");
        }
    } catch (error) {
        // Handle network or unexpected errors
        console.error("Network error:", error);
        alert("There was a network error. Please try again later.");
    }
}

// Attach the function to form submission
document.querySelector("#newUserForm").addEventListener("submit", createUser);
