async function deleteModal(id) {
    try {
        // Отправляем GET-запрос с параметром id
        const response = await fetch(`/admin/read?id=${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        // Проверяем успешность запроса
        if (response.ok) {
            const user = await response.json(); // Парсим ответ в формате JSON

            // Заполняем поля формы
            document.querySelector("#delete-id").value = user.id;
            document.querySelector("#delete-firstName").value = user.firstName;
            document.querySelector("#delete-lastName").value = user.lastName;
            document.querySelector("#delete-age").value = user.age;
            document.querySelector("#delete-email").value = user.email;
            document.querySelector("#delete-password").value = user.password;
            let temp = "";
            user.roles.forEach(role => {
                let roleText = role.name.substr(5)
                temp += `
                    <option value="${role}">${roleText}</option>
                `;
            })
            document.querySelector("#delete-roles").innerHTML = temp;
        } else {
            // Если пользователь не найден или произошла ошибка
            alert("Пользователь не найден или произошла ошибка");
        }
    } catch (error) {
        console.error('Ошибка при выполнении запроса:', error);
        alert('Ошибка при выполнении запроса');
    }
}

async function deleteUser(event) {
    event.preventDefault(); // Prevent form from submitting the traditional way

    // Extract selected roles from the multi-select dropdown
    const checkedRoles = () => {
        let selectedRoles = [];
        let options = document.querySelector('#delete-roles').options;
        for (let i = 0; i < options.length; i++) {
            if (options[i].selected) {
                selectedRoles.push(roleList[i]);
            }
        }
        return selectedRoles;
    }

    // Construct edited user object
    const deletedUser = {
        id: $("#delete-id").val().trim(),
        firstName: $("#delete-firstName").val().trim(),
        lastName: $("#delete-lastName").val().trim(),
        age: $("#delete-age").val().trim(),
        email: $("#delete-email").val(),
        password: $("#delete-password").val(),
        roles: checkedRoles() // Roles array
    };

    // Log the edited user data to check
    console.log(deletedUser);

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
        let response = await fetch("admin/delete", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
                // [csrfHeader]: csrfToken // Include CSRF token here
            },
            body: JSON.stringify(deletedUser)
        });

        // Check if the response is successful
        if (response.ok) {
            window.location.href = "admin"; // Redirect to the admin page if successful
        } else {
            // Handle response errors, if any
            const errorText = await response.text();
            console.error("Error deleting user:", errorText);
            alert("Error deleting user. Please try again.");
        }
    } catch (error) {
        // Handle network or unexpected errors
        console.error("Network error:", error);
        alert("There was a network error. Please try again later.");
    }
}

// Attach the function to form submission
document.querySelector("#deleteUserForm").addEventListener("submit", deleteUser);