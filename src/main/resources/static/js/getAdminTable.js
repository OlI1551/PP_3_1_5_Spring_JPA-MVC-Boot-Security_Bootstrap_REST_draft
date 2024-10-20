let roleList = [
    {
        "id": 2,
        "name": "ROLE_ADMIN",
        "users": null,
        "authority": "ROLE_ADMIN"
    },
    {
        "id": 1,
        "name": "ROLE_USER",
        "users": null,
        "authority": "ROLE_USER"
    }
]

$(async function () {
    await getAdminTable();
});

async function getAdminTable() {
    let temp = "";
    const table = document.querySelector("#adminTable tbody");
    await fetch("admin/users", {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Referer': null
        }
    })
        .then(res => res.json())
        .then(users => users.forEach(user => {
            let roles = ""
            user.roles.forEach(role => roles += role.name.substr(5) + ' ')
            temp += `                
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${roles}</td> 
                    <td>                        
                        <button type="button" id="editUserButton" class="btn btn-sm btn-success" data-toggle="modal"
                            data-target="#editModal" 
                            onclick="editModal(${user.id})">Edit
                        </button>
                    </td>
                    <td>                        
                        <button type="button" id="deleteUserButton" class="btn btn-sm btn-danger" data-toggle="modal"
                            data-target="#deleteModal" 
                            onclick="deleteModal(${user.id})">Delete
                        </button>
                    </td>                        
                </tr>                
            `;
        }))
    table.innerHTML = temp;
}