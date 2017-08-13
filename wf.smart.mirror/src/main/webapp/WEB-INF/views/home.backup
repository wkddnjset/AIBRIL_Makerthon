<!DOCTYPE html>
 
 
<head>
    <title>Speech to text converter in JS</title>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.1/css/font-awesome.min.css"/>
    <style style="text/css">
        body{
            font-family:verdana;
        }
        #result{
            height:200px;
            border:1px solid #ccc;
            padding:10px;
            box-shadow: 0 0 10px 0 #bbb;
            margin-bottom: 30px;
            font-size: 14px;
            line-height: 25px;
        }
        button{
            font-size:20px;
            position: absolute;
            top:240px;
            left:50%;
        }
    </style>
</head>
 
    <h4 align="center">Speech to text converter in JS</h4>
    <div id="result"></div>
    <button onClick="startConverting();" type="button"><i class="fa fa-microphone"></i></button>
 
<script type="text/javascript">
 
//insert in html
var r=document.getElementById('result');
 
function startConverting ()
{
        //check this browser is chrome or not. because this application supported only in chrome browser
 
        if('webkitSpeechRecognition'in window){
            //Web speech API Function
            var speechRecognizer = new webkitSpeechRecognition();
            //continuous : you will catch mic only one time or not
            speechRecognizer.continuous = true;
            //interimResults : during capturing the mic you will send results or not
            speechRecognizer.interimResults = true;
            //lang : language (ko-KR : Korean, en-IN : englist)
            speechRecognizer.lang="ko-KR";
            //start!
            speechRecognizer.start();
 
            var finalTranscripts = '';
 
            //if the voice catched onresult function will start
            speechRecognizer.onresult=function(event){
                var interimTranscripts='';
                for(var i=event.resultIndex; i < event.results.length; i++)
                {
                    var transcript=event.results[i][0].transcript;
                    transcript.replace("\n","<br>");
 
                    //isFinal : if speech recognition is finished, isFinal = true
                    if(event.results[i].isFinal){
                        finalTranscripts+=transcript;
                    }
                    else{
                        interimTranscripts+=transcript;
                    }
                }
                //insert into HTML
                r.innerHTML=finalTranscripts+'<span style="color:#999">'+interimTranscripts+'</span>';
            };
            speechRecognizer.onerror = function(event){
            };
        }
        else{
            //if browser don't support this function. this message will show in your web
            r.innerHTML ="your browser is not supported. If google chrome. Please upgrade!";
        }
    }
</script>

