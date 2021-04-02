
let full = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');
let myEmail;
let myFullName;
let partnerEmail;
let myAvatar;
let yourAvatar;
let sessionId;
let xhttp;
var stompClient = null;
let compEmail = $("#compEmail").val();
let compName = $("#displayName").val();

fetchMyInfo();
connect();
if (compEmail) {
    $("a.blantershow-chat").html("Ask us a question!");
    loadMessages(compEmail); //will create a new conversation
}

function fetchMyInfo() {
//get personal info
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let myprops = JSON.parse(this.responseText);
            let fname = myprops.fname;
            let lname = myprops.lname;
            myEmail = myprops.email;
            myAvatar = "https://eu.ui-avatars.com/api/?name=" + fname +
                    "-" + lname + "&background=90EE90";
        }
    };
    xhttp.open("GET", full + "/myprops");
    xhttp.send();
}
//show partners
function fetchPartners() {
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let partners = JSON.parse(this.responseText);
            let showChatPartners = "";
            let companyName = "";
            let avatar;
            for (let i = 0; i < partners.length; i++) {
                if (partners[i].companyName) {
                    companyName = partners[i].companyName;
                }
                let fname = partners[i].fname;
                let lname = partners[i].lname;
                let emailWrap = partners[i].email;
                avatar = "https://eu.ui-avatars.com/api/?name=" + fname +
                        "-" + lname + "&background=B0C4DE";
                showChatPartners += ('<a class="informasi" onclick="loadMessages(\'' +
                        emailWrap + '\');"> <div class="info-avatar">' +
                        "<img src='" + avatar + "'/>" + "</div><div class='info-chat'>" +
                        "<span class='chat-label'>" + companyName + "</span><span class='chat-nama'>"
                        + fname + " " + lname + "</span>" + "</div></a>");
                
            }
            $('#partnerframe').html(showChatPartners);
            if (compName) {
                $("a.informasi:contains('" + compName + "')").trigger("click");
            }
        }

    }
    xhttp.open("GET", full + "/conv");
    xhttp.send();
}

function convertDate(date) {
    return date.getDate() + "/" + (date.getMonth() + 1) + " " + date.getHours() + ":" + date.getMinutes();
}

function loadMessages(pEmail) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let partnerName = $("#get-nama").html().replace(" ", "-");
            partnerEmail = pEmail;
            yourAvatar = "https://eu.ui-avatars.com/api/?name=" + partnerName + "&background=B0C4DE";
            let prevMsgs = JSON.parse(this.responseText);
            $("#msgframe").html("");
            for (let i = 0; i < prevMsgs.length; i++) {
                insertChat(prevMsgs[i].from === myEmail, prevMsgs[i].text, prevMsgs[i].timestamp);
            }
        }
    }
    xhttp.open("GET", full + "/load/" + pEmail);
    xhttp.send();
}

function insertChat(side, text, dateTime) {
    let date = new Date(dateTime);
    let timeStamp = convertDate(date);
    let speechBubble = $('#msgframe').html();
    if (side) {
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

function connect() {

    const socket = new SockJS("/secured/room");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        var url = stompClient.ws._transport.url;
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
                if ($("#whatsapp-chat").hasClass("hide")) {
                    $("a.blantershow-chat").css("animation-play-state", "running");
                }
                if (messageBody.from == partnerEmail) {
                    insertChat(false, messageBody.text, messageBody.timestamp);
                }
            });
}

$(document).on("click", ".informasi", function () {
    document.getElementById("get-number").innerHTML = $(this).children(".my-number").text(), $(".start-chat,.get-new").addClass("show").removeClass("hide"), $(".home-chat,.head-home").addClass("hide").removeClass("show"), document.getElementById("get-nama").innerHTML = $(this).children(".info-chat").children(".chat-nama").text(), document.getElementById("get-label").innerHTML = $(this).children(".info-chat").children(".chat-label").text();
});

$(document).on("click", ".close-chat", function () {
    $("#whatsapp-chat").addClass("hide").removeClass("show");
});
$(document).on("click", ".blantershow-chat", function () {
    $("a.blantershow-chat").css("animation-play-state", "paused");
    fetchPartners();
    $("#whatsapp-chat").addClass("show").removeClass("hide");

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
            stompClient.send("/app/secured/room", {}, JSON.stringify(msgObj));
            $(this).val('');
        }
    }
});