  //<![CDATA[
        var HOST = "localhost:8080";
        var paletteNES = ["#7C7C7C","#0000FC","#0000BC","#4428BC","#940084","#A80020","#A81000","#881400",
                "#503000","#007800","#006800","#005800","#004058","#000000","#000000","#000000","#BCBCBC",
                "#0078F8","#0058F8","#6844FC","#D800CC","#E40058","#F83800","#E45C10","#AC7C00","#00B800",
                "#00A800","#00A844","#008888","#000000","#000000","#000000","#F8F8F8","#3CBCFC","#6888FC",
                "#9878F8","#F878F8","#F85898","#F87858","#FCA044","#F8B800","#B8F818","#58D854","#58F898",
                "#00E8D8","#787878","#000000","#000000","#FCFCFC","#A4E4FC","#B8B8F8","#D8B8F8","#F8B8F8",
                "#F8A4C0","#F0D0B0","#FCE0A8","#F8D878","#D8F878","#B8F8B8","#B8F8D8","#00FCFC","#F8D8F8",
                "#000000","#000000"];
        var field = [];
        var socket;
        var fieldCanvas;
        var paletteCanvas;
        var selectedColorCanvas;

        var selectedColorIndex = 0;

        var mouseOverDeskX = -1;
        var mouseOverDeskY = -1;


        function init() {
            initPalette();
            drawPalette();
            initWS();
            initSelected();
            drawSelected();
            initDesk();
            getDeskAndDrawDesk();
        }

        function initSelected() {
            selectedColorCanvas = document.getElementById("canvas_selected");

        }

        function drawSelected() {
            if (selectedColorCanvas.getContext) {
                var ctx = selectedColorCanvas.getContext('2d');
                ctx.fillStyle = getNESColorByIndex(selectedColorIndex);
                ctx.fillRect(0,0,20,20);
            }
        }

        function initDesk() {
            fieldCanvas = document.getElementById("canvas_field");

            fieldCanvas.addEventListener("mousemove", function (event) {
               var _x = fieldCanvas.getBoundingClientRect().left;
               var _y = fieldCanvas.getBoundingClientRect().top;
               var x = Math.floor((event.clientX - _x) / 10);
               var y = Math.floor((event.clientY - _y) / 10);
               drawSelectedStrokeOnDesk(x, y);
            });

            fieldCanvas.addEventListener("click", function (event) {
                var _x = fieldCanvas.getBoundingClientRect().left;
                var _y = fieldCanvas.getBoundingClientRect().top;
                var x = Math.floor((event.clientX - _x) / 10);
                var y = Math.floor((event.clientY - _y) / 10);
                var colorIndex = selectedColorIndex;
                sendPixelColor(x, y, colorIndex);
            });
        }

        function initPalette() {
            paletteCanvas = document.getElementById("canvas_palette");

            paletteCanvas.addEventListener("mousemove", function (event) {
                x = paletteCanvas.getBoundingClientRect().left;
                var mouseOnIndex = Math.floor((event.clientX - x) / 20);
                drawPalette();
                drawSelectedStrokeOnPalette(mouseOnIndex);
            });

            paletteCanvas.addEventListener("mouseout", function (event) {
                drawPalette();
            });

            paletteCanvas.addEventListener("click", function (event) {
                var x = paletteCanvas.getBoundingClientRect().left;
                selectedColorIndex = Math.floor((event.clientX - x) / 20);
                drawSelected();
                console.log("click");
            });
        }

        function drawSelectedStrokeOnPalette(index) {
            if (paletteCanvas.getContext) {
                var ctx = paletteCanvas.getContext('2d');
                ctx.strokeStyle = "#000000";
                ctx.strokeRect(index * 20, 0, 20, 20);
                ctx.strokeStyle = "#FFFFFF";
                ctx.strokeRect(index * 20 + 1, 1, 18, 18);
            }
        }

        function drawSelectedStrokeOnDesk(x, y) {
            if (fieldCanvas.getContext) {
                var ctx = fieldCanvas.getContext('2d');
                if (mouseOverDeskX !== -1 && mouseOverDeskY !== -1) {
                    var offset = mouseOverDeskX + 100 * mouseOverDeskY;
                    drawPixelOnDesk(ctx, offset, getNESColorByIndex(field[offset]));
                }
                mouseOverDeskX = x;
                mouseOverDeskY = y;
                ctx.strokeStyle = "rgba(0,0,0,1)";
                ctx.strokeRect(x * 10 + 1, y * 10 + 1, 8, 8);
                ctx.strokeStyle = "rgba(255,255,255,1)";
                ctx.strokeRect(x * 10 + 2, y * 10 + 2, 6, 6);
            }
        }

        function initWS() {
            socket = new WebSocket("ws://" + HOST + "/updates");
            socket.onopen = function() {
                console.log("Connection opened")
            };

            socket.onclose = function(){
                console.log("Connection closed")
            };

            socket.onmessage = function(event) {
                var response = event.data;
                response = JSON.parse(response);
                if (response.status === "OK") {
                    if (response.type === "POINT_UPDATE") {
                        field[response.x + 100 * response.y] = response.color;
                        updatePixel(response.x, response.y, response.color);
                    }
                    if (response.type === "ONLINE_UPDATE") {
                        var counter = document.getElementById("online_count");
                        counter.textContent = response.online_count;
                    }
                }
            };
        }

        function updatePixel(x, y, color) {
            fieldCanvas = document.getElementById('canvas_field');
            if (fieldCanvas.getContext) {
                var ctx = fieldCanvas.getContext('2d');
                drawPixelOnDesk(ctx, x + 100 * y, getNESColorByIndex(color));
            }
        }

        function getDeskAndDrawDesk() {
            var xhr = new XMLHttpRequest();
            var url = "http://" + HOST + "/api/desk";
            xhr.open("GET",url,false);
            xhr.send();
            var responce = xhr.responseText;
            responce = JSON.parse(responce);

            if (responce.field) {
                field = responce.field;
                drawDesk();
            }
        }

        function drawDesk() {
            fieldCanvas = document.getElementById('canvas_field');
            if (fieldCanvas.getContext) {
                var ctx = fieldCanvas.getContext('2d');

                field.forEach(function (color, offset, field) {
                    drawPixelOnDesk(ctx, offset, getNESColorByIndex(color));
                });
            }
        }

        function drawPixelOnDesk(ctx, offset, color) {
//            offset = x + y * 100;
            var y = Math.floor(offset / 100);
            var x = offset - 100 * y;
            ctx.fillStyle = color;
            ctx.fillRect(x * 10, y * 10, 10, 10)
        }

        function getNESColorByIndex(index) {
            return paletteNES[index];
        }

        function drawPalette() {
            paletteCanvas = document.getElementById('canvas_palette');
            if (paletteCanvas.getContext) {
                var ctx = paletteCanvas.getContext('2d');

                paletteNES.forEach(function (p1, x, p3) {
                    ctx.fillStyle = p1;
                    ctx.fillRect(x * 20, 0, 20, 20)
                });
            }

        }

        function sendPixelColor(x, y, color) {
            var xhr = new XMLHttpRequest();
            var url = "http://" + HOST + "/api/draw?x=" + x + "&y=" + y + "&color=" + color;
            xhr.open("PUT",url,false);
            xhr.send();
            var responce = xhr.responseText;
            responce = JSON.parse(responce);
            console.log(responce);
        }
        //]]>