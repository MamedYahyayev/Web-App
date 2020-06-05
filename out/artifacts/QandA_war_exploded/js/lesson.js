var lessonGlobId = 0;
$(function () {
    $('body').layout({applyDemoStyles: true});

    $('.ui-layout-center,.ui-layout-east,.ui-layout-north,.ui-layout-west,.ui-layout-south').css('backgroundColor', 'aqua');


    $('#lessonTable').DataTable({
        'searching': false
    });


    $("#newLesson").dialog({
        title: "New Lesson",
        autoOpen: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "Save": function () {
                addLesson();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }
    });


    $("#newbtn").click(function () {
        $("#newLesson").load("add/addLesson.jsp", function () {
            $(this).dialog("open");
        });

    });

    $("#editLesson").dialog({
        title: "Update Lesson",
        autoOpen: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
            "Save": function () {
                updateLesson();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }
    });

    $("#searchBtn").click(function () {
        searchLesson();
    });

    $("#searchText").keyup(function () {
        searchLesson();
    })

});

function addLesson() {
    var lessonName = $("#lesson").val();

    var data = {
        "lessonName": lessonName
    };

    $.ajax({
        url: "cs?action=addLesson",
        type: "POST",
        dataType: "text",
        data: data,
        success: function (response) {
            alert("Lesson is added...");
            getLessonList();
        },
        error: function () {
            alert("Error");
        }

    });
}

function getLessonList() {
    $.ajax({
        url: "cs?action=getLessonListAjax",
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


function editLesson(lessonId) {
    lessonGlobId = lessonId;
    $.ajax({
        url: "cs?action=getLessonById",
        type: "GET",
        dataType: "html",
        data: "lessonId=" + lessonId,
        success: function (response) {
            $("#editLesson").html(response);
            $("#editLesson").dialog("open");
        }
    });
}

function updateLesson() {
    var lesson = $("#lessonU").val();

    var data = {
        "lesson": lesson,
        "lessonId": lessonGlobId
    };

    $.ajax({
        url: "cs?action=updateLesson",
        type: "POST",
        dataType: "text",
        data: data,
        success: function (response) {
            alert("Lesson is Updated...");
            getLessonList();
        },
        error: function () {
            alert("Error");
        }
    });


}

function deleteLesson(lessonId) {
    var isDelete = confirm("Are you sure?");
    if (isDelete) {
        $.ajax({
            url: "cs?action=deleteLesson",
            type: "POST",
            dataType: 'text',
            data: "lessonId=" + lessonId,
            success: function (response) {
                alert("Lesson is deleted...");
                getLessonList();
            },
            error: function () {
                alert("Error");
            }


        });
    }
}

function searchLesson() {
    var keyword = $("#searchText").val();

    $.ajax({
        url: "cs?action=searchLesson",
        type: "GET",
        dataType: "html",
        data: "keyword=" + keyword,
        success: function (response) {
            $(".ui-layout-center").html(response);
        }

    });
}
