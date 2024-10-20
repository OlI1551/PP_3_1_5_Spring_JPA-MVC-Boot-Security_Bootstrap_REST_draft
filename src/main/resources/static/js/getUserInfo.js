$(async function () {
    await getUserInfo();
});

async function getUserInfo() {
    let info = "";
    let temp = "";
    const brand = document.querySelector("#brand");
    const table = document.querySelector("#userTable tbody");
    await fetch("user/current", {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Referer': null
        }
    })
        .then(res => res.json())
        .then(user => {
            // const roles = user.authorities[0].name.toString().replace("ROLE_","");
            let roles = "";
            user.roles.forEach(role => roles += role.name.substr(5) + ' ');
            info = `
                <b>${user.email}</b>
                <span>  with roles:  </span>
                <span>${roles}</span>
            `;
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
        });
    brand.innerHTML = info;
    table.innerHTML = temp;
}