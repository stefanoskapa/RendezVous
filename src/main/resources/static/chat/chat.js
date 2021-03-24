var me = {};
var you = {};
me.avatar = "https://lh6.googleusercontent.com/-lr2nyjhhjXw/AAAAAAAAAAI/AAAAAAAARmE/MdtfUmC0M4s/photo.jpg?sz=48";
you.avatar = "https://a11.t26.net/taringa/avatares/9/1/2/F/7/8/Demon_King1/48x48_5C5.jpg";
var role = $('#userrole').val();
var chatPartnerId = $('#userid').val();


function insertChat(who, text, time) {
    if (time === undefined) {
        time = 0;
    }
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
    setTimeout(
            function () {
                $("ul").append(control).scrollTop($("ul").prop('scrollHeight'));
            }, time);

}

function resetChat() {
    $("ul").empty();
}

function convertDate(date) {
    return date.getDate() + "/" + (date.getMonth() + 1) + " " + date.getHours() + ":" + date.getMinutes();
}

function sendMessage(message) {
    $.ajax({
        url: 'http://localhost:8080/rendezvous/api/v1/' + role + '/history/' + chatPartnerId,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(message)
    });

}
function loadMessages() {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let prevMsgs = JSON.parse(this.responseText);
            for (let i = 0; i < prevMsgs.length; i++) {
                insertChat(prevMsgs[i].sender, prevMsgs[i].message, prevMsgs[i].timeStamp);
            }
        }
    };
    xhttp.open("GET", "http://localhost:8080/rendezvous/api/v1/" + role + "/history/" + chatPartnerId);
    xhttp.send();
}

$(".mytext").on("keydown", function (e) {
    if (e.which == 13) {
        var text = $(this).val();
        if (text !== "") {
            var dateNow = new Date();
            insertChat("me", text, dateNow.toISOString());
            var msgObj = {};
            msgObj.message = text;
            msgObj.timeStamp = dateNow.toISOString();
            alert(msgObj.timeStamp);
            sendMessage(msgObj);
            $(this).val('');
        }
    }
});

$('body > div > div > div:nth-child(2) > span').click(function () {
    $(".mytext").trigger({type: 'keydown', which: 13, keyCode: 13});
})

//-- Clear Chat
resetChat();
loadMessages();


