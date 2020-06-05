var globQuestionId = 0;

$(function () {
    $('body').layout({applyDemoStyles: true});

    $('.ui-layout-center,.ui-layout-east,.ui-layout-north,.ui-layout-west,.ui-layout-south').css('backgroundColor', 'aqua');

    $('#questionTable').DataTable({
        'searching': false
    });


    $('#newQuestion').dialog({
        title: 'New Question',
        height: 600,
        width: 650,
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function () {
                addQuestion();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }
    });

    $("#newbtn").click(function () {
        $("#newQuestion").load("add/addQuestion.jsp", function () {
            $(this).dialog("open");
        });
    });

    $('#editQuestion').dialog({
        title: 'Update Question',
        height: 600,
        width: 650,
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function () {
                updateQuestions();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }
    })

    $("#searchBtn").click(function () {
        searchQuestion();
    });

    $("#searchText").keyup(function () {
        searchQuestion();
    });

});

function addQuestion() {
    var topic = $("#topic").val();
    var question = $("#question").val();

    var data = {
        "topic": topic,
        "question": question
    };

    $.ajax({
        url: "cs?action=addQuestion",
        type: "POST",
        dataType: "text",
        data: data,
        success: function (response) {
            alert("Question is added...");
            getQuestionList();
        },
        error: function () {
            alert("Error");
        }
    });

}

function getQuestionList() {

    $.ajax({
        url: "cs?action=getQuestionListAjax",
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

function editQuestions(questionId) {
    globQuestionId = questionId;

    var data = {
        "questionId": questionId
    };

    $.ajax({
        url: "cs?action=getQuestionById",
        dataType: "html",
        type: "GET",
        data: data,
        success: function (response) {
            $("#editQuestion").html(response);
            $("#editQuestion").dialog("open");
        },
        error: function () {
            alert("Error");
        }
    });

}

function updateQuestions() {
    var topic = $("#topicU").val();
    var question = $("#questionU").val();

    var data = {
        "topic": topic,
        "question": question,
        "questionId": globQuestionId
    };

    $.ajax({
        url: "cs?action=updateQuestion",
        dataType: "text",
        type: "POST",
        data: data,
        success: function (response) {
            alert("Question is updated...");
            getQuestionList();
        },
        error: function () {
            alert("Error");
        }
    });

}

function deleteQuestion(questionId) {
    var isDelete = confirm("Are you sure?");
    if (isDelete) {
        $.ajax({
            url: "cs?action=deleteQuestion",
            type: "POST",
            dataType: 'text',
            data: "questionId=" + questionId,
            success: function (response) {
                alert("Question is deleted...");
                getQuestionList();
            },
            error: function () {
                alert("Error");
            }


        });
    }
}

function searchQuestion() {
    var keyword = $("#searchText").val();

    $.ajax({
        url: "cs?action=searchQuestion",
        dataType: "html",
        type: "GET",
        data: "keyword=" + keyword,
        success: function (response) {
            $(".ui-layout-center").html(response);
        }


    });
}