var no_serv = false;

var board = new WGo.Board(document.getElementById("board"), {
    width: 600,
    section: {
        top: -0.5,
        left: -0.5,
        right: -0.5,
        bottom: -0.5
    }
});

var tool = document.getElementById("tool"); // get the &lt;select&gt; element


// WGo.Board.DrawHandler which draws coordinates
var coordinates = {
    // draw on grid layer
    grid: {
        draw: function(args, board) {
            var ch, t, xright, xleft, ytop, ybottom;

            this.fillStyle = "rgba(0,0,0,0.7)";
            this.textBaseline="middle";
            this.textAlign="center";
            this.font = board.stoneRadius+"px "+(board.font || "");

            xright = board.getX(-0.75);
            xleft = board.getX(board.size-0.25);
            ytop = board.getY(-0.75);
            ybottom = board.getY(board.size-0.25);

            for(var i = 0; i < board.size; i++) {
                ch = i+"A".charCodeAt(0);
                if(ch >= "I".charCodeAt(0)) ch++;

                t = board.getY(i);
                this.fillText(board.size-i, xright, t);
                this.fillText(board.size-i, xleft, t);

                t = board.getX(i);
                this.fillText(String.fromCharCode(ch), t, ytop);
                this.fillText(String.fromCharCode(ch), t, ybottom);
            }

            this.fillStyle = "black";
        }
    }
}

board.addCustomObject(coordinates);

board.addEventListener("click", function(x, y) {

    if (!no_serv) {
        stompClient.send("/query/move", {}, JSON.stringify(
            {
                'color': tool.value,
                'x': x,
                'y': y
            }
        ));
    }
    else
        playMove(tool.value, x, y);
});


function playMove(color, x, y)
{
    if(color == "black") {
        board.addObject({
            x: x,
            y: y,
            c: WGo.B
        });
    }
    else if(color == "white") {
        board.addObject({
            x: x,
            y: y,
            c: WGo.W
        });
    }
    else if(tool.value == "remove") {
        board.removeObjectsAt(x, y);
    }
};

function sendStart()
{
    stompClient.send("/query/begin", {}, JSON.stringify(
        {
            'start' : 1
        }
    ));
}

function change_serv()
{
    no_serv = !no_serv;
    $("#greetings").append("<tr><td>" + "Noserv : " + no_serv + "</td></tr>");
}


$(function() {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });

    $("#start").click(function() {
        sendStart();
    });

    //Used to get rid of the server side. Local playing.
    $("#noserver").click(function(){
        change_serv();
    });




});