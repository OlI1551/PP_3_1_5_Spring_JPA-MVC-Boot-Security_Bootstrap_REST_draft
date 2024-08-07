// let roleList = [
//     {id: 1, role: "ROLE_USER"},
//     {id: 2, role: "ROLE_ADMIN"}
// ]
// let isUser = true;

$(async function () {
    await getUserTable();
    await getUserInfo();
    await getAdminTable();
    // await getNewUserForm();
    // await getDefaultModal();
    // await createUser();

})
// объект для связи с REST API по всем методам
const userFetch = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },

    getAuthenticatedUser: async () => await fetch("user/author"),
    getUsers: async () => await fetch("admin/users")
    // findOneUser: async (id) => await fetch(`api/users/${id}`),

    // addNewUser: async (user) => await fetch('api/users', {
    //     method: 'POST',
    //     headers: userFetch.head,
    //     body: JSON.stringify(user)
    // }),

    // updateUser: async (user, id) => await fetch(`api/users/${id}`, {
    //     method: 'PUT',
    //     headers: userFetch.head,
    //     body: JSON.stringify(user)
    // }),

    // deleteUser: async (id) => await fetch(`api/users/${id}`, {
    //     method: 'DELETE',
    //     headers: userFetch.head
    // })
}

async function getUserInfo() {
    let temp = "";
    const author = document.querySelector("#author");
    await userFetch.getAuthenticatedUser()
        .then(res => res.json())
        .then(user => {
            let roles = "";
            user.roles.forEach(role => roles += role.name.substr(5) + ' ')
            temp = `
                <b>${user.email}</b>
                <span>  with roles:  </span> 
                <span>${roles}</span>               
            `;
        });
    author.innerHTML = temp;
}

async function getUserTable() {
    let temp = "";
    const table = document.querySelector("#userTable tbody");
    await userFetch.getAuthenticatedUser()
        .then(res => res.json())
        .then(user => {
            let roles = ""
            user.roles.forEach(role => roles += role.name.substr(5) + ' ')
            temp = `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${roles}</td>          
                </tr>
            `;
        })
    table.innerHTML = temp;
}