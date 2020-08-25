$(document).ready(function () {
    createTable();
});

function createTable() {
    $("#tableBody").empty();
    $.ajax("/rest/all", {
        dataType: "json",
        success: function (data) {
            const users = JSON.parse(JSON.stringify(data));

            //Записываем лист ролей в цикле
            for (let i = 0; i < users.length; i++) {
                let listRoles = '';
                for (let element of users[i].authorities) {
                    listRoles += " " + element.login;
                }

                var tr = $("<tr>").attr("id", users[i].id);
                tr.append("" +
                    "<td>" + users[i].id + "</td>" +
                    "<td>" + users[i].login + "</td>" +
                    "<td>" + users[i].password + "</td>" +
                    "<td>" + users[i].email + "</td>" +
                    "<td>" + listRoles + "</td>" +

                    "<td><button onclick='getUserForEdit(" + users[i].id + ")' class='btn btn-md btn-info' data-toggle='modal' data-target='#myModal'>Edit</button></td>" +
                    "<td><button onclick='deleteUser(" + users[i].id + ")' class='btn btn-md btn-danger'>Del</button> </td>"
                );
                $("#myTable").append(tr)
            }
        },
        error: function (er) {
            console.log(er);
        }
    })
}

//Добавление пользователя в таблицу//

$(document).ready(function () {

    $('#addNewUser').submit(function (event) {
        event.preventDefault()
        var user = {
            login: $('#inputLogin').val(),
            password: $('#inputPassword').val(),
            email: $('#inputEmail').val(),
            roles: $('#role').val(),
        }


        $.ajax({
            // processData: false,
            url: "/rest/addUserRest",
            data: JSON.stringify(user),
            dataType: 'json',
            type: 'POST',
            contentType: 'application/JSON; charset=utf-8',
            success: function (data) {
                // alert("yes!!!")
                createTable();
                $('#usersTab').addClass("show active")
                $('#navTable').addClass('active')
                $('#newUser').removeClass("show active")
                $('#navNewUser').removeClass("active")

            },
            error: function (er) {
                alert(Array.from(role))
            }
        })
    })
})


//Редактирование пользователя//

$(document).ready(
    function getForEdit() {
        $('#editUserModal').on('show.bs.modal', function (event) {
            let button = $(event.relatedTarget);
            getUserForEdit(); //здесь получить данные пользователя
        });
    });

function getUserForEdit(id) {
    $.ajax({
        type: "GET",
        url: "/rest/user/" + id,
        dataType: 'json',
        success: function (user) { //заполнение таблицы данными
            $(".modal-body #Eid").val(user.id)
            $(".modal-body #Elogin").val(user.login)
            $(".modal-body #Epassword").val(user.password)
            $(".modal-body #Eemail").val(user.email)
            $(".modal-body #Eroles").val(user.roles)

        }
    });
}

//Эдит пользователя//

function updateUser() {

    let userId = $("#Eid").val();

    var user = {
        id: $("#Eid").val(),
        login: $('#Elogin').val(),
        password: $('#Epassword').val(),
        email: $('#Eemail').val(),
        roles: $('#Eroles').val(),
    }


    $.ajax({
        // processData: false,
        url: "/rest/editSave",
        data: JSON.stringify(user),
        dataType: 'json',
        type: 'PUT',
        contentType: 'application/JSON; charset=utf-8',
        success: function () {
            createTable();
            // $("#tableBody #" + id).update();
        },
        error: function (er) {
            alert(Array.from(role))
        }
    })
}

//Обновить таблицу пользователей//

function updateTable() {
    $.ajax("/rest/update", {
        dataType: "json",
        success: function (data) {
            var users = JSON.parse(JSON.stringify(data));

            for (var i in users) {
                var tr = $("<tr>").attr("id", users[i].id);
                tr.append("" +
                    "<td>" + users[i].id + "</td>" +
                    "<td>" + users[i].login + "</td>" +
                    "<td>" + users[i].password + "</td>" +
                    "<td>" + users[i].email + "</td>" +
                    "<td>" + users[i].roles + "</td>" +
                    "<td><button onclick='openModalEdit(" + users[i].id + ")' class='btn btn-md btn-info' data-toggle='modal' data-target='#myModal'>Edit</button></td>" +
                    "<td><button onclick='deleteUser(" + users[i].id + ")' class='btn btn-md btn-danger'>Del</button> </td>"
                );
                $("#myTable").append(tr)
            }
        },
        error: function (er) {
            console.log(er);
        }
    })
}

//Удалить пользователя из таблицы//

function deleteUser(id) {
    $.ajax({
            url: "/rest/delete/" + id,
            type: "DELETE",
            contentType: "application/json",
            success: function (data) {

                $("#myTable #" + id).remove();

                console.log(data)
            },
            error: function (er) {
                console.log(er)
            }
        }
    );
}
