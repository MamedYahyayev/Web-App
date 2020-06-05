var globInformationId = 0;
$(function () {
    $('body').layout({applyDemoStyles: true});

    $('.ui-layout-center,.ui-layout-east,.ui-layout-north,.ui-layout-west,.ui-layout-south').css('backgroundColor', 'aqua');

    $('#informationTable').DataTable({
        'searching': false
    });


    $("#newInformation").dialog({
        title: "New Information",
        height: 700,
        width: 700,
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function () {
                addInformation();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }
    });

    $("#newbtn").click(function () {
        $("#newInformation").load("add/addInformation.jsp", function () {
            $(this).dialog("open");
        });
    });


    $("#editInformation").dialog({
        title: "Update Information",
        height: 700,
        width: 700,
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function () {
                updateInformation();
                $(this).dialog("close");
            },
            "Close": function () {
                $(this).dialog("close");
            }
        }
    });

    $("#searchBtn").click(function () {
        searchInformation();
    });

    $("#searchText").keyup(function () {
        searchInformation();
    });

});


function addInformation() {
    var information = $("#information").val();

    var data = {
        "information": information
    };

    $.ajax({
        url: "cs?action=addInformation",
        type: "POST",
        dataType: "text",
        data: data,
        success: function (response) {
            alert("Information is added...")
            getInformationList();
        },
        error: function () {
            alert("Error");
        }
    });
}

function getInformationList() {

    $.ajax({
        url: "cs?action=getInformationListAjax",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $(".ui-layout-center").html(response);
        },
        error: function () {
            alert("Error");
        }

    })

}

function editInformation(informationId) {
    globInformationId = informationId;
    $.ajax({
        url: "cs?action=getInformationById",
        type: "GET",
        dataType: "html",
        data: "informationId=" + informationId,
        success: function (response) {
            $("#editInformation").html(response);
            $("#editInformation").dialog("open");
        },
        error: function () {
            alert("Error");
        }

    });

}

function updateInformation() {
    var information = $("#informationU").val();

    var data = {
        "information": information,
        "informationId": globInformationId
    };

    $.ajax({
        url: "cs?action=updateInformation",
        type: "POST",
        dataType: "text",
        data: data,
        success: function (response) {
            alert("Information is updated...");
            getInformationList();
        },
        error: function () {
            alert("Error");
        }

    });


}

function deleteInformations(informationId) {
    var isDelete = confirm("Are you sure?");
    if (isDelete) {
        $.ajax({
            url: "cs?action=deleteInformation",
            type: "POST",
            dataType: 'text',
            data: "informationId=" + informationId,
            success: function (response) {
                alert("Information is deleted...");
                getInformationList();
            },
            error: function () {
                alert("Error");
            }


        });
    }
}

function searchInformation() {
    var keyword = $("#searchText").val();

    $.ajax({
        url: "cs?action=searchInformation",
        type: "GET",
        dataType: "html",
        data: "keyword=" + keyword,
        success: function (response) {
            $(".ui-layout-center").html(response);
        }
    });
}