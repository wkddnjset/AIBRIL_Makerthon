<!DOCTYPE html>
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Speech to text converter in JS</title>
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.1/css/font-awesome.min.css" />
<style style="text/css">
body {
	font-family: verdana;
}

#result {
	height: 200px;
	border: 1px solid #ccc;
	padding: 10px;
	box-shadow: 0 0 10px 0 #bbb;
	margin-bottom: 30px;
	font-size: 14px;
	line-height: 25px;
}

button {
	font-size: 20px;
	position: absolute;
	top: 240px;
	left: 50%;
}
</style>
<script type="text/javascript" src="../js/jquery.js"></script>
</head>
 <body>
 	<input type="hidden" name="port" id="port" value="${port}" ></input>
	<input type="hidden" name="ip" id="ip" value="${ip}" ></input>
 

    <h4 align="center">Speech to text converter in JS</h4>
    <div id="result"></div>
    <button onClick="fnStartConverting();" type="button"><i class="fa fa-microphone"></i></button>

<script type="text/javascript">
	var ip;
	var port;
	var socket;
	
	var r=document.getElementById('result');
	var textTimer;
	
	window.addEventListener('load', function() {
		ip = $('#ip').val();
		port = $('#port').val();
		new Sock(ip,port);
	}, false);
	
	
	var Sock = function() {
		
		if (!window.WebSocket) {
		    window.WebSocket = window.MozWebSocket;
		}
		
		if (window.WebSocket) {
			
		    //socket = new WebSocket("ws://localhost:9000/websocket");
		    var url = "ws://" + ip + ":" +port + "/websocket";
		    //alert("url:"+url);
		    socket = new WebSocket(url);
		    socket.onopen = onopen;
		    socket.onmessage = onmessage;
		    socket.onclose = onclose;
		} else {
		    alert("Your browser does not support Web Socket.");
		}
		
		function onopen(event) {
		    //getTextAreaElement().value = "Web Socket opened!";
		    //putTextArea("Web Socket opened!");
		}
		
		function onmessage(event) {
		    //appendTextArea(event.data);
			//putTextArea(event.data);
		
			var data = JSON.parse(event.data);
			
			r.innerHTML = r.innerHTML +' <p> <span style="color:#999">'+ data.msg + '</span>';
			
/* 			if( data.list == "" || data.list == null || data.list == undefined || ( data.list != null && typeof data.list == "object" && !Object.keys(data.list).length ) ){
				
			}else{
				//node status
				for(var i in data.list){
					
				}
			} */
			
		}
		
		function onclose(event) {
			//putTextArea("Web Socket closed");
		    alert("Please Re-connect!");
		}
		
		/* function putTextArea(newData) {
		    var el = getTextAreaElement();
		    el.value = newData + '\n';
		}
		 
		function appendTextArea(newData) {
		    var el = getTextAreaElement();
		    el.value = el.value + '\n' + newData;
		}
		
		function getTextAreaElement() {
		    return document.getElementById('debugBox');
		}
		*/
		function send(event) {
		    event.preventDefault();
		    if (window.WebSocket) {
				if (socket.readyState == WebSocket.OPEN) {
					/* var data={};
					data.text = text;
					var jsonData = JSON.stringify(data);
				    socket.send(jsonData);  */
				} else {
				    alert("The socket is not open.");
				}
		    }
		}	
	}
	
	function fnStartConverting() {
		//check this browser is chrome or not. because this application supported only in chrome browser

		if ('webkitSpeechRecognition' in window) {
			//Web speech API Function
			var speechRecognizer = new webkitSpeechRecognition();
			//continuous : you will catch mic only one time or not
			speechRecognizer.continuous = true;
			//interimResults : during capturing the mic you will send results or not
			speechRecognizer.interimResults = true;
			//lang : language (ko-KR : Korean, en-IN : englist)
			speechRecognizer.lang = "ko-KR";
			//start!
			speechRecognizer.start();

			//var finalTranscripts = '';

			//if the voice catched onresult function will start
			speechRecognizer.onresult = function(event) {
				var finalTranscripts = '';
				var interimTranscripts = '';
				for (var i = event.resultIndex; i < event.results.length; i++) {
					var transcript = event.results[i][0].transcript;
					transcript.replace("\n", "<br>");

					//isFinal : if speech recognition is finished, isFinal = true
					if (event.results[i].isFinal) {
						finalTranscripts += transcript;
					} else {
						interimTranscripts += transcript;
					}
				}
				
				clearTimeout(textTimer);
				
				textTimer = setTimeout(function(){
					//insert into HTML
					r.innerHTML = finalTranscripts + '<span style="color:#999">'
							+ interimTranscripts + '</span>';
						
					if (finalTranscripts != null && finalTranscripts != ''){
						var data = new Object();
						data['text'] = finalTranscripts;
						var jsonData = JSON.stringify(data); 
						socket.send(jsonData);
						clearTimeout(textTimer);
					}
				}, 1000);

			};
			speechRecognizer.onerror = function(event) {
			};
		} else {
			//if browser don't support this function. this message will show in your web
			r.innerHTML = "your browser is not supported. If google chrome. Please upgrade!";
		}
	}
</script>
</body>
