
$(document).on("click", ".informasi", function () {
    document.getElementById("get-number").innerHTML = $(this).children(".my-number").text(),
            $(".start-chat,.get-new").addClass("show").removeClass("hide"),
            $(".home-chat,.head-home").addClass("hide").removeClass("show"),
            document.getElementById("get-nama").innerHTML = $(this).children(".info-chat").children(".chat-nama").text(),
            document.getElementById("get-label").innerHTML = $(this).children(".info-chat").children(".chat-label").text()
});
$(document).on("click", ".close-chat", function () {
    $("#whatsapp-chat").addClass("hide").removeClass("show")
    
});
$(document).on("click", ".blantershow-chat", function () {
    $("#whatsapp-chat").addClass("show").removeClass("hide")
});

var myRole;
var myUserId;
var myConvId;
var full = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');
var xhttp = new XMLHttpRequest();
var myAvatar;
var yourAvatar;

function convertDate(date) {
    return date.getDate() + "/" + (date.getMonth() + 1) + " " + date.getHours() + ":" + date.getMinutes();
}
xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
        var whoAmI = JSON.parse(this.responseText);
        myRole = whoAmI.role;
        myUserId = whoAmI.userId;
        myAvatar = "https://eu.ui-avatars.com/api/?name=" + whoAmI.fname + "-" + whoAmI.lname + "&background=90EE90";
        let showChatPartners = "";
        let companyName="";
        for (let i = 0; i < (whoAmI.convPartners).length; i++) {
            if (whoAmI.convPartners[i].companyName) {
                companyName = whoAmI.convPartners[i].companyName;
            }
            var fname = whoAmI.convPartners[i].fname;
            var lname = whoAmI.convPartners[i].lname;
            var avatar = "https://eu.ui-avatars.com/api/?name=" + fname + "-" + lname + "&background=B0C4DE";
            
            showChatPartners += ("<a class='informasi' onclick='loadMessages(" + whoAmI.convPartners[i].idByRole + ");'> <div class='info-avatar'>" +
                    "<img src='" + avatar + "'/>" +
                    "</div><div class='info-chat'><span class='chat-label'>" + companyName + "</span><span class='chat-nama'>" + fname + " " + lname + "</span>" +
                    "</div></a>")
        }
        $('.home-chat').html(showChatPartners);
    }
};
xhttp.open("GET", full + "/api/v1/whoami");
xhttp.send();
function loadMessages(partnerId) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            
            let partnerName = $("#get-nama").html().replace(" ","-");
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
 var date = new Date(dateTime);
    var timeStamp = convertDate(date);
    var speechBubble = $('#msgframe').html();
    
    if (uid==myUserId) {
    $('#msgframe').html(speechBubble + "<div class='container1'><img src='"+myAvatar+"' style='width:100%;'><p>"+text+"</p>" +
  "<span class='time-right'>"+timeStamp+"</span></div>"); 
    } else {
       $('#msgframe').html(speechBubble + "<div class='container1'><img src='"+yourAvatar+"' class='right' style='width:100%;'><p>"+text+"</p>" +
  "<span class='time-left'>"+timeStamp+"</span></div>");  
 
        
    }


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
                var messageBody = JSON.parse(response.body);
                if (messageBody.conversationId == myConvId) { //make sure it is the right conversation
                    insertChat(messageBody.userId, messageBody.message, messageBody.timestamp);
                }
            });
            stompClient.send("/app/start", {});

            $("#chat-input").on("keydown", function (e) {
                if (e.which == 13) {
                    var text = $(this).val();
                    if (text !== "") {
                        var dateNow = new Date();
                        var msgObj = {};
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

