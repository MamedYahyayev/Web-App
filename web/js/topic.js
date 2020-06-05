var globTopicId = 0;
$(function () {
    $('body').layout({applyDemoStyles: true});

    $('.ui-layout-center,.ui-layout-east,.ui-layout-north,.ui-layout-west,.ui-layout-south').css('backgroundColor', 'aqua');


    $('#topicTable').DataTable({
        'searching': false
    });


    $("#newTopic").dialog({
        title: "New Topic",
        height: 500,
        autoOpen: false,
        width: 500,
        modal: true,
        buttons: {
            "Save": function () {
                addTopic();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }
    });


    $("#newbtn").click(function () {
        $("#newTopic").load("add/addTopic.jsp", function () {
            $(this).dialog("open");
        });
    });

    $("#editTopic").dialog({
        title: "Update Topic",
        height: 500,
        autoOpen: false,
        width: 500,
        modal: true,
        buttons: {
            "Save": function () {
                updateTopic();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }
    });


    $("#searchBtn").click(function () {
        searchTopic()
    });

    $("#searchText").keyup(function () {
        searchTopic();
    })

});


function addTopic() {
    var classRoom = $("#classRoom option:selected").val();
    var lesson = $("#lesson option:selected").val();
    var topicName = $("#topicName").val();

    var data = {
        "classRoom": classRoom,
        "lesson": lesson,
        "topicName": topicName
    };


    $.ajax({
        url: "cs?action=addTopic",
        type: "POST",
        dataType: "text",
        data: data,
        success: function (response) {
            alert("Topic is added...")
            getTopicList();
        },
        error: function (response) {
            alert("Error");
        }
    });

}

function getTopicList() {
    $.ajax({
        url: "cs?action=getTopicListAjax",
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

function editTopic(topicId) {
    globTopicId = topicId;

    $.ajax({
        url: "cs?action=getTopicById",
        type: "GET",
        dataType: "html",
        data: "topicId=" + topicId,
        success: function (response) {
            $("#editTopic").html(response);
            $("#editTopic").dialog("open");
        },
        error: function () {
            alert("Error");
        }

    });
}

function updateTopic() {
    var classRoom = $("#classRoomU").val();
    var lesson = $("#lessonU").val();
    var topic = $("#topicNameU").val();

    var data = {
        "classRoom": classRoom,
        "lesson": lesson,
        "topic": topic,
        "topicId": globTopicId
    };

    $.ajax({
        url: "cs?action=updateTopic",
        type: "POST",
        dataType: "text",
        data: data,
        success: function (response) {
            alert("Topic is updated...")
            getTopicList();
        },
        error: function (response) {
            alert("Error");
        }
    });
}

function deleteTopic(topicId) {
    var isDelete = confirm("Are you sure?");
    if (isDelete) {
        $.ajax({
            url: "cs?action=deleteTopic",
            type: "POST",
            dataType: 'text',
            data: "topicId=" + topicId,
            success: function (response) {
                alert("Topic is deleted...");
                getTopicList();
            },
            error: function () {
                alert("Error");
            }


        });
    }
}


function searchTopic() {
    var keyword = $("#searchText").val();

    $.ajax({
        url: "cs?action=searchTopic",
        type: "GET",
        dataType: "html",
        data: "keyword=" + keyword,
        success: function (response) {
            $(".ui-layout-center").html(response);
        }


    });
}