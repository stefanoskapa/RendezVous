$(document).on("click", ".informasi", function () {
    $("#get-number").html($(this).children(".my-number").text());
    $(".start-chat,.get-new").addClass("show").removeClass("hide");
    $(".home-chat,.head-home").addClass("hide").removeClass("show");
    $("#get-nama").html($(this).children(".info-chat").children(".chat-nama").text());
    $("#get-label").html($(this).children(".info-chat").children(".chat-label").text());
});

$(document).on("click", ".close-chat", function () {
    $("#whatsapp-chat").addClass("hide").removeClass("show");
});

$(document).on("click", ".blantershow-chat", function () {
    $("#whatsapp-chat").addClass("show").removeClass("hide");
    $(".blantershow-chat").css("animation-play-state", "paused");
});

//the p* variables are loaded only on client's company_date_pick page
let pCompId = $("#comp-id").val(); //has value only on client's company_date_pick page
let pFName = $("#fname").val();
let pLName = $("#lname").val();
let pCompanyName = $("#displayName").val();

let myRole;
let myUserId;
let myConvId;
let full = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');
let myAvatar;
let yourAvatar;

let xhttp = new XMLHttpRequest();
//alert(compId.val());

xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
        let whoAmI = JSON.parse(this.responseText);
        myRole = whoAmI.role;
        myUserId = whoAmI.userId;
        myAvatar = "https://eu.ui-avatars.com/api/?name=" + whoAmI.fname + "-" + whoAmI.lname + "&background=90EE90";
        let showChatPartners = "";
        let companyName = "";
        let avatar;
        if (!pCompId) {
            for (let i = 0; i < (whoAmI.convPartners).length; i++) {
                if (whoAmI.convPartners[i].companyName) {
                    companyName = whoAmI.convPartners[i].companyName;
                }
                let fname = whoAmI.convPartners[i].fname;
                let lname = whoAmI.convPartners[i].lname;
                avatar = "https://eu.ui-avatars.com/api/?name=" + fname +
                        "-" + lname + "&background=B0C4DE";

                showChatPartners += ("<a class='informasi' onclick='loadMessages(" +
                        whoAmI.convPartners[i].idByRole + ");'> <div class='info-avatar'>" +
                        "<img src='" + avatar + "'/>" + "</div><div class='info-chat'>" +
                        "<span class='chat-label'>" + companyName + "</span><span class='chat-nama'>"
                        + fname + " " + lname + "</span>" + "</div></a>");

            }
        } else {
            companyName = pCompanyName;
            fname = pFName;
            lname = pLName;
            avatar = "https://eu.ui-avatars.com/api/?name=" + fname +
                    "-" + lname + "&background=B0C4DE";
            showChatPartners += ("<a class='informasi' onclick='loadMessages(" +
                    pCompId + ");'> <div class='info-avatar'>" +
                    "<img src='" + avatar + "'/>" + "</div><div class='info-chat'>" +
                    "<span class='chat-label'>" + companyName + "</span><span class='chat-nama'>"
                    + fname + " " + lname + "</span>" + "</div></a>");
        }
        $('.home-chat').html(showChatPartners);
    }
};
xhttp.open("GET", full + "/api/v1/whoami");
xhttp.send();

function convertDate(date) {
    return date.getDate() + "/" + (date.getMonth() + 1) + " " + date.getHours() + ":" + date.getMinutes();
}
function loadMessages(partnerId) {

    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let partnerName = $("#get-nama").html().replace(" ", "-");
            yourAvatar = "https://eu.ui-avatars.com/api/?name=" + partnerName + "&background=B0C4DE";
            let prevMsgs = JSON.parse(this.responseText);
            myConvId = prevMsgs[0].conversationId;
            for (let i = 0; i < prevMsgs.length; i++) {
                insertChat(prevMsgs[i].userId, prevMsgs[i].message, prevMsgs[i].timestamp);
            }
        }
    };
    xhttp.open("GET", full + "/api/v1/" + myRole + "/history/" + partnerId);
    xhttp.send();
}

function insertChat(uid, text, dateTime) {
    let date = new Date(dateTime);
    let timeStamp = convertDate(date);
    let speechBubble = $('#msgframe').html();

    if (uid == myUserId) {
        $('#msgframe').html(speechBubble + "<div class='container1'><img src='" +
                myAvatar + "' style='width:100%;'><p>" + text + "</p>" +
                "<span class='time-right'>" + timeStamp + "</span></div>");
    } else {
        $('#msgframe').html(speechBubble + "<div class='container1'><img src='" +
                yourAvatar + "' class='right' style='width:100%;'><p>" + text + "</p>" +
                "<span class='time-left'>" + timeStamp + "</span></div>");

    }
    $("#msgframe").scrollTop($("#msgframe")[0].scrollHeight);

}

$('#send-it').click(function () {
    $("#chat-input").trigger({type: 'keydown', which: 13, keyCode: 13});
})

jQuery(function ($) {
    let stompClient;
    if (!stompClient) {
        const socket = new SockJS(full + "/ws");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function () {
            stompClient.subscribe('/user/topic/messages', function (response) { //recieve message from server and display it
                if ($("#whatsapp-chat").hasClass("hide")) {
                    $(".blantershow-chat").css("animation-play-state", "running");
                }
                let messageBody = JSON.parse(response.body);
                if (messageBody.conversationId == myConvId) { //make sure it is the right conversation
                    insertChat(messageBody.userId, messageBody.message, messageBody.timestamp);
                }
            });
            stompClient.send("/app/start", {});

            $("#chat-input").on("keydown", function (e) {
                if (e.which == 13) {
                    let text = $(this).val();
                    if (text !== "") {
                        let dateNow = new Date();
                        let msgObj = {};
                        msgObj.message = text;
                        msgObj.timestamp = dateNow.toISOString();
                        msgObj.userId = myUserId;
                        msgObj.conversationId = myConvId;
                        stompClient.send("/app/" + myRole + "/history", {}, JSON.stringify(msgObj));
                        $(this).val('');
                    }
                }
            });

        });
    }
});

