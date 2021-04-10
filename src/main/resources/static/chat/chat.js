let full = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');
let myEmail, myFullName, partnerEmail, myAvatar, yourAvatar, sessionId, xhttp, stompClient = null;
let compEmail = $("#compEmail").val();
let compName = $("#displayName").val();

fetchMyInfo();
connect();

if (compEmail) { // ==true means we are in company_date_pick.jsp
    $("a.blantershow-chat").html("Ask us a question!");
    loadMessages(compEmail); //will create a new conversation
}

function fetchMyInfo() { //get personal info
    $.getJSON(full + "/api/v1/myprops", function (res) {
        let fname = res.fname;
        let lname = res.lname;
        myEmail = res.email;
        myAvatar = "https://eu.ui-avatars.com/api/?name=" + fname +
                "-" + lname + "&background=90EE90";
    }
    );
}

function fetchPartners() {
    $.getJSON(full + "/api/v1/conv", function (res) {
        let showChatPartners = "", companyName = "";
        for (let i = 0; i < res.length; i++) {
            if (res[i].companyName) {
                companyName = res[i].companyName;
            }
            let fname = res[i].fname;
            let lname = res[i].lname;
            let emailWrap = res[i].email;
            let avatar = "https://eu.ui-avatars.com/api/?name=" +
                    fname + "-" + lname + "&background=B0C4DE";
            showChatPartners += ('<a class="informasi" onclick="loadMessages(\'' +
                    emailWrap + '\');"> <div class="info-avatar">' + "<img src='" +
                    avatar + "'/>" + "</div><div class='info-chat'>" +
                    "<span class='chat-label'>" + companyName +
                    "</span><span class='chat-nama'>" + fname +
                    " " + lname + "</span>" + "</div></a>");
        }
        $('#partnerframe').html(showChatPartners);
        if (compName) {
            $("a.informasi:contains('" + compName + "')").trigger("click");
        }

    });
}

function convertDate(date) {
    return date.getDate() + "/" + (date.getMonth() + 1) + " " + date.getHours() + ":" + date.getMinutes();
}

function loadMessages(pEmail) {
    $.getJSON(full + "/api/v1/load/" + pEmail, function (res) {
        let partnerName = $("#get-nama").html().replace(" ", "-");
            partnerEmail = pEmail;
            yourAvatar = "https://eu.ui-avatars.com/api/?name=" + partnerName + "&background=B0C4DE";
            $("#msgframe").html("");
            for (let i = 0; i < res.length; i++) {
                insertChat(res[i].from === myEmail, res[i].text, res[i].timestamp);
            }
    });
}

function insertChat(side, text, dateTime) {
    let date = new Date(dateTime);
    let timeStamp = convertDate(date);
    let speechBubble = $('#msgframe').html();
    if (side) {
        $('#msgframe').html(speechBubble + "<div class='container1'><img src='" +
                myAvatar + "' style='width:100%;'><p class='msg'>" + text + "</p>" +
                "<span class='time-right'>" + timeStamp + "</span></div>");
    } else {
        $('#msgframe').html(speechBubble + "<div class='container1'><img src='" +
                yourAvatar + "' class='right' style='width:100%;'><p class='msg'>" + text + "</p>" +
                "<span class='time-left'>" + timeStamp + "</span></div>");
    }
    $("#msgframe").scrollTop($("#msgframe")[0].scrollHeight);
}

function connect() {

  const socket = new SockJS("/secured/room");


    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        var url = stompClient.ws._transport.url;
        url = url.replace("wss://","");
        url = url.replace("ws://" + location.hostname + (location.port ? ':' + location.port : '') + "/secured/room/", "");
        url = url.replace("/websocket", "");
        url = url.replace(/^[0-9]+\//, "");
        console.log("Your current session is: " + url);
        sessionId = url;
        subscribe();
    });
}

function subscribe() {
    stompClient.subscribe('/secured/user/queue/specific-user'
            + '-user' + sessionId, function (response) { //recieve message from server and display it
                let messageBody = JSON.parse(response.body);
                if ($("#whatsapp-chat").hasClass("hideCh")) {
                    $("a.blantershow-chat").css("animation-play-state", "running");
                }
                if (messageBody.from == partnerEmail) {
                    insertChat(false, messageBody.text, messageBody.timestamp);
                }
            });
}

$(document).on("click", ".informasi", function () {
    $(".start-chat,.get-new").addClass("showCh").removeClass("hideCh");
    $(".home-chat,.head-home").addClass("hideCh").removeClass("showCh");
    document.getElementById("get-nama").innerHTML = $(this).children(".info-chat").children(".chat-nama").text();
    document.getElementById("get-label").innerHTML = $(this).children(".info-chat").children(".chat-label").text();
    $(".close-chat").html("<");
});
$(document).on("click", ".close-chat", function () {
    if ($(".close-chat").html() == "×") {
        $("#whatsapp-chat").addClass("hideCh").removeClass("showCh");
    } else {
        $(".start-chat,.get-new").addClass("hideCh").removeClass("showCh");
        $(".home-chat,.head-home").addClass("showCh").removeClass("hideCh");
        $("get-nama, get-label").html("");
        $(".close-chat").html("×");
    }
});
$(document).on("click", ".blantershow-chat", function () {
    $("a.blantershow-chat").css("animation-play-state", "paused");
    fetchPartners();
    $("#whatsapp-chat").addClass("showCh").removeClass("hideCh");
});
$('#send-it').click(function () {
    $("#chat-input").trigger({type: 'keydown', which: 13, keyCode: 13});
});
$("#chat-input").on("keydown", function (e) {
    if (e.which == 13) {
        let text = $(this).val();
        if (text !== "") {
            let dateNow = new Date();
            let msgObj = {};
            msgObj.text = text;
            msgObj.to = partnerEmail;
            msgObj.from = myEmail;
            msgObj.timestamp = dateNow;
            insertChat(true, msgObj.text, dateNow);
            stompClient.send("/chat/secured/room", {}, JSON.stringify(msgObj));
            $(this).val('');
        }
    }
});