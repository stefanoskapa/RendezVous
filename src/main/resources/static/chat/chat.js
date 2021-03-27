var me = {};
var you = {};
me.avatar = "https://eu.ui-avatars.com/api/?name=" + $('#me').val() + "&background=90EE90";
you.avatar = "https://eu.ui-avatars.com/api/?name=" + $('#you').val() + "&background=B0C4DE";
var role = $('#userrole').val();
var chatPartnerId = $('#partnerId').val(); //companyID or clientID, is used in url
var myUserId = $('#myuid').val();
var hisUserId = $('#hisuid').val();
var convId = $('#convId').val();
var full = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');

function insertChat(who, text, time) {

    var control = "";
    var date = new Date(time);
    var timeStamp = convertDate(date);
    if (who == "me") {
        control = '<li style="width:100%">' +
                '<div class="msj macro">' +
                '<div class="avatar"><img class="img-circle" style="width:100%;" src="' + me.avatar + '" /></div>' +
                '<div class="text text-l">' +
                '<p>' + text + '</p>' +
                '<p><small>' + timeStamp + '</small></p>' +
                '</div>' +
                '</div>' +
                '</li>';
    } else {
        control = '<li style="width:100%;">' +
                '<div class="msj-rta macro">' +
                '<div class="text text-r">' +
                '<p>' + text + '</p>' +
                '<p><small>' + timeStamp + '</small></p>' +
                '</div>' +
                '<div class="avatar" style="padding:0px 0px 0px 10px !important"><img class="img-circle" style="width:100%;" src="' + you.avatar + '" /></div>' +
                '</li>';
    }
    $("ul").append(control).scrollTop($("ul").prop('scrollHeight'));
}

function resetChat() {
    $("ul").empty();
}

function convertDate(date) {
    return date.getDate() + "/" + (date.getMonth() + 1) + " " + date.getHours() + ":" + date.getMinutes();
}

function sendMessage(message) {
    $.ajax({
        url: full+'/rendezvous/api/v1/' + role + '/history/',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(message)
    });

}
function loadMessages() {
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let prevMsgs = JSON.parse(this.responseText);
            for (let i = 0; i < prevMsgs.length; i++) {
                insertChat(prevMsgs[i].userId == myUserId ? "me" : "you", prevMsgs[i].message, prevMsgs[i].timestamp);
            }
        }
    };
    xhttp.open("GET", full + "/rendezvous/api/v1/" + role + "/history/" + chatPartnerId);
    xhttp.send();
}

$(".mytext").on("keydown", function (e) {
    if (e.which == 13) {
        var text = $(this).val();
        if (text !== "") {
            var dateNow = new Date();
            var msgObj = {};
            msgObj.message = text;
            msgObj.timestamp = dateNow.toISOString();
            msgObj.userId = myUserId;
            msgObj.conversationId = convId;
            sendMessage(msgObj);
            $(this).val('');
        }
    }
});

$('body > div > div > div:nth-child(2) > span').click(function () {
    $(".mytext").trigger({type: 'keydown', which: 13, keyCode: 13});
})

resetChat();
loadMessages();

jQuery(function ($) {
    let stompClient;
    if (!stompClient) {
        const socket = new SockJS(full + "/rendezvous/ws");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function () {
            stompClient.subscribe('/user/topic/messages', function (response) { //recieve message from server and display it
                var messageBody = JSON.parse(response.body);
                if (messageBody.userId == myUserId || messageBody.userId == hisUserId) { //make sure it is the right conversation
                    insertChat(messageBody.userId == myUserId ? "me" : "you", messageBody.message, messageBody.timestamp);
                }
            });
            stompClient.send("/app/start", {});
        });
    }
});

