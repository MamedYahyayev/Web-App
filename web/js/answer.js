var globAnswerId = 0;

$(function () {
    $('body').layout({applyDemoStyles: true});

    $('.ui-layout-center,.ui-layout-east,.ui-layout-north,.ui-layout-west,.ui-layout-south').css('backgroundColor', 'aqua');

    $('#answerTable').DataTable({
        'searching': false
    });


    $('#newAnswer').dialog({
        title: 'New Answer',
        height: 690,
        width: 650,
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function () {
                addAnswer();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }
    });

    $("#newbtn").click(function () {
        $("#newAnswer").load("add/addAnswer.jsp", function () {
            $(this).dialog("open");
        })
    });


    $('#editAnswer').dialog({
        title: 'Update Answer',
        height: 690,
        width: 650,
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function () {
                updateAnswer();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }
    });

    $("#searchBtn").click(function () {
        searchAnswer();
    });

    $("#searchText").keyup(function () {
        searchAnswer();
    });

});

function addAnswer() {
    var question = $("#question").val();
    var answerStatus = $("[name = answerStatus]:checked").val();
    var answer = $("#answer").val();

    var data = {
        "question": question,
        "answerStatus": answerStatus,
        "answer": answer
    };

    $.ajax({
        url: "cs?action=addAnswer",
        type: "POST",
        dataType: "text",
        data: data,
        success: function (response) {
            alert("Answer is added...");
            getAnswerList();
        },
        error: function () {
            alert("Error");
        }
    });

}

function getAnswerList() {

    $.ajax({
        url: "cs?action=getAnswerListAjax",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $(".ui-layout-center").html(response)
        },
        error: function () {
            alert("Error");
        }
    });

}

function editAnswer(answerId) {
    globAnswerId = answerId;

    $.ajax({
        url: "cs?action=getAnswerById",
        type: "GET",
        dataType: 'html',
        data: "answerId=" + answerId,
        success: function (response) {
            $("#editAnswer").html(response);
            $("#editAnswer").dialog("open");
        },
        error: function () {
            alert("Error");
        }


    });
}


function updateAnswer() {
    var question = $("#questionU").val();
    var answerStatus = $("[name = answerStatusU]:checked").val();
    var answer = $("#answerU").val();

    var data = {
        "question": question,
        "answerStatus": answerStatus,
        "answer": answer,
        "answerId": globAnswerId
    };

    $.ajax({
        url: "cs?action=updateAnswer",
        type: "POST",
        dataType: "text",
        data: data,
        success: function (response) {
            alert("Answer is updated...");
            getAnswerList();
        },
        error: function () {
            alert("Error");
        }
    });

}

function deleteAnswer(answerId) {
    var isDelete = confirm("Are you sure?");
    if (isDelete) {
        $.ajax({
            url: "cs?action=deleteAnswer",
            type: "POST",
            dataType: 'text',
            data: "answerId=" + answerId,
            success: function (response) {
                alert("Answer is deleted...");
                getAnswerList();
            },
            error: function () {
                alert("Error");
            }


        });
    }
}


function searchAnswer() {
    var keyword = $("#searchText").val();


    $.ajax({
        url: "cs?action=searchAnswer",
        type: "GET",
        dataType: "html",
        data: "keyword=" + keyword,
        success: function (response) {
            $(".ui-layout-center").html(response);
        }


    });


}
