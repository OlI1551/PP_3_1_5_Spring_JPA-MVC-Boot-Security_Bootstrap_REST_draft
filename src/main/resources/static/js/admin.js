async function getAdminTable() {
    let temp = "";
    const table = document.querySelector("#adminTable tbody");
    await userFetch.getUsers()
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
                        <button type="button" class="btn btn-sm edit" data-toggle="modal"
                            th:data-target="'#editModal'+${user.id}">Edit
                        </button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-sm delete" data-toggle="modal"
                            th:data-target="'#deleteModal'+ ${user.id}">Delete
                        </button>
                    </td>                        
                </tr>                
            `;
        }))
    table.innerHTML = temp;
}