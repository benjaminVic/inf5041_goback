var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#start").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
        $("#start").show();
    }
    else {
        $("#conversation").hide();
        $("#start").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/webusocketu');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/response/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe('/response/verify', function (verif) {
            jsonmsg = JSON.parse(verif.body);
            if (jsonmsg.state == "play")
                playMove(jsonmsg.color, jsonmsg.x, jsonmsg.y);
        });
        stompClient.subscribe('/response/clear', function(clear){
            jsonmsg = JSON.parse(clear.body);
            if (jsonmsg.clear == "clear")
                board.removeAllObjects();
        });
    });


}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/query/hello", {}, JSON.stringify(
        {
            'name': $("#name").val()
        }
        ));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

