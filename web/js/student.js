var globStudentId = 0;

$(function () {
    $('body').layout({applyDemoStyles: true});

    $('.ui-layout-center,.ui-layout-east,.ui-layout-north,.ui-layout-west,.ui-layout-south').css('backgroundColor', 'aqua');

    $('#studentTable').DataTable({
        'searching': false
    });


    $('#newStudent').dialog({
        title: 'New Student',
        height: 520,
        autoOpen: false,
        modal: true,
        width: 500,
        buttons: {
            "Save": function () {
                addStudent();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }
    });

    $('#newbtn').click(function () {
        $('#newStudent').load("add/addStudent.jsp", function () {
            $(this).dialog("open");
        });
    });

    $('#editStudentDialog').dialog({
        title: 'Update Student',
        height: 520,
        autoOpen: false,
        modal: true,
        width: 500,
        buttons: {
            "Update": function () {
                updateStudent();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }
    });

    $("#searchBtn").click(function () {
        searchStudent();
    });

    $("#searchText").keyup(function () {
        searchStudent();
    });

});

function addStudent() {
    var name = $("#name").val();
    var surname = $("#surname").val();
    var username = $("#username").val();
    var password = $("#password").val();
    var role = $("[name = role]:checked").val();


    var data = {
        "name": name,
        "surname": surname,
        "username": username,
        "password": password,
        "role": role
    };

    $.ajax({
        url: "cs?action=addStudent",
        type: "POST",
        dataType: "text",
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert("Student is added...");
                getStudentList();
            } else {
                alert("Problem!!!");
            }
        }


    });
}

function getStudentList() {
    $.ajax({
        url: "cs?action=getStudentListAjax",
        type: "GET",
        dataType: 'html',
        success: function (response) {
            $(".content").html(response);
        },
        error: function () {
            alert("Error");
        }
    })
}

function editStudent(studentId) {
    globStudentId = studentId;
    var data = {
        "studentId": studentId
    };

    $.ajax({
        url: "cs?action=getStudentById",
        type: "GET",
        dataType: "html",
        data: data,
        success: function (response) {
            $("#editStudentDialog").html(response);
            $("#editStudentDialog").dialog("open");
        },
        error: function () {
            alert("Error");
        }
    });
}

function updateStudent() {
    var name = $("#nameU").val();
    var surname = $("#surnameU").val();
    var username = $("#usernameU").val();
    var password = $("#passwordU").val();
    var role = $("[name = roleU]:checked").val();


    var data = {
        "name": name,
        "surname": surname,
        "username": username,
        "password": password,
        "role": role,
        "studentId": globStudentId
    };

    $.ajax({
        url: "cs?action=updateStudent",
        type: "POST",
        dataType: "text",
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert("Student is updated...");
                getStudentList();
            } else {
                alert("Problem!!!");
            }
        }


    });
}

function deleteStudent(studentId) {
    var isDelete = confirm("Are you sure?");
    if (isDelete) {
        $.ajax({
            url: "cs?action=deleteStudent",
            type: "POST",
            dataType: 'text',
            data: "studentId=" + studentId,
            success: function (response) {
                alert("Student is deleted...");
                getStudentList();
            },
            error: function () {
                alert("Error");
            }


        });
    }
}

function searchStudent() {
    var keyword = $("#searchText").val();

    $.ajax({
        url: "cs?action=searchStudent",
        type: "GET",
        dataType: "html",
        data: "keyword=" + keyword,
        success: function (response) {
            $(".ui-layout-center").html(response);

        }


    });

}