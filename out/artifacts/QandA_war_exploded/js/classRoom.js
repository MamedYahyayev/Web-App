var globClassRoomId = 0;

$(function () {
    $('body').layout({applyDemoStyles: true});

    $('.ui-layout-center,.ui-layout-east,.ui-layout-north,.ui-layout-west,.ui-layout-south').css('backgroundColor', 'aqua');


    $('#classRoomTable').DataTable({
        'searching': false
    });


    $('#newClassRoom').dialog({
        title: "New Class Room",
        height: 200,
        autoOpen: false,
        width: 400,
        modal: true,
        buttons: {
            "Save": function () {
                addClassRoom();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }

    });

    $("#newbtn").click(function () {
        $("#newClassRoom").load("add/addClassRoom.jsp", function () {
            $(this).dialog("open");
        });
    });

    $('#editClassRoom').dialog({
        title: "Update Class Room",
        height: 200,
        autoOpen: false,
        width: 400,
        modal: true,
        buttons: {
            "Save": function () {
                updateClassRoom();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }

    });

    $("#searchBtn").click(function () {
        searchClassRoom();
    });

    $("#searchText").keyup(function () {
        searchClassRoom();
    })

});

function addClassRoom() {
    var classRoom = $("#classRoom").val();

    var data = {
        "classRoom": classRoom
    };

    $.ajax({
        url: "cs?action=addClassRoom",
        type: "POST",
        dataType: "text",
        data: data,
        success: function (response) {
            alert("Class Room is added...");
            getClassRoomList();
        },
        error: function () {
            alert("Error");
        }
    });

}

function getClassRoomList() {

    $.ajax({
        url: "cs?action=getClassRoomListAjax",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $(".ui-layout-center").html(response);
        },
        error: function () {
            alert("Error");
        }
    });

}

function editClassRoom(classRoomId) {
    globClassRoomId = classRoomId;

    $.ajax({
        url: "cs?action=getClassRoomById",
        type: "GET",
        dataType: "html",
        data: "classRoomId=" + classRoomId,
        success: function (response) {
            $("#editClassRoom").html(response);
            $("#editClassRoom").dialog("open");
        },
        error: function () {
            alert("Error");
        }
    });

}

function updateClassRoom() {
    var classRoom = $("#classRoomU").val();

    var data = {
        "classRoom": classRoom,
        "classRoomId": globClassRoomId
    };

    $.ajax({
        url: "cs?action=updateClassRoom",
        type: "POST",
        dataType: "text",
        data: data,
        success: function (response) {
            alert("ClassRoom is updated...");
            getClassRoomList();
        },
        error: function () {
            alert("Error");
        }
    });


}

function deleteClassRoom(classRoomId) {
    var isDelete = confirm("Are you sure?");
    if (isDelete) {
        $.ajax({
            url: "cs?action=deleteClassRoom",
            type: "POST",
            dataType: 'text',
            data: "classRoomId=" + classRoomId,
            success: function (response) {
                alert("ClassRoom is deleted...");
                getClassRoomList();
            },
            error: function () {
                alert("Error");
            }


        });
    }
}

function searchClassRoom() {
    var keyword = $("#searchText").val();

    $.ajax({
        url: "cs?action=searchClassRoom",
        dataType: "html",
        type: "GET",
        data: "keyword=" + keyword,
        success: function (response) {
            $(".ui-layout-center").html(response);
        }
    });
}