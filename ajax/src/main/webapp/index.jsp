<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<form action="" enctype="application/x-www-form-urlencoded"></form>

<input type="text" id="name">
<button id="btn">sendRequest</button>
<div id="result"></div>
<script src="static/js/ajax.js"></script>
<script>
    (function () {
       /*document.querySelector("#btn").onclick=function(){
           var name=document.querySelector("#name").value;
        ajax.sendPost("/ajax","name="+name+"&age=23",function(data){
        alert(data);
        },function () {
            alert("服务器异常");

        });

       };*/

        document.querySelector("#btn").onclick=function(){
         var name=document.querySelector("#name").value;
         ajax.sendPost({
             url:"/ajax",
             data:"name="+name+"&age=23",
             success:function(data){
                 alert(data);

             },
             error:function(){
                 alert("服务器异常");
             }

         });

         };
        
    })();
    /*(function() {
//创建XMLHttpRequest对象
        function createXmlHttp() {
            var xmlHttp=null;
            if(window.ActiveXObject){
                //IE8以下
                var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }else{
             var xmlHttp=new XMLHttpRequest();
            }
            return xmlHttp;
        }

        document.querySelector("#btn").onclick=function () {
        var name=document.querySelector("#name").value;
           //sendGet(name);
            sendPost(name);


        };

        function sendGet(name) {
            //1. 获取Ajax引擎
            var xmlHttp=createXmlHttp();
            //2. 指定请求方式(GET|POST)和请求地址
            xmlHttp.open("get","/ajax?name="+name+"&_="+new Date().getTime());
            //3. 发出请求
            xmlHttp.send();
        }

        function sendPost(name){
            //1. 获取Ajax引擎
         var xmlHttp=createXmlHttp();
            //2. 指定请求方式和地址
            xmlHttp.open("post","/ajax");
            xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            //3. 配置回调函数
            xmlHttp.onreadystatechange=function(){
                var state=xmlHttp.readyState;//获取服务器状态码
                if(state==4){
                    var httpState=xmlHttp.status;//获取HTTP状态码
                    if(httpState==200){
                        //获取服务端返回的字符串
                        var result=xmlHttp.responseText;
                        var div=document.querySelector("#result");
                        if(result=="yes"){
                            div.innerText="可以使用该账号";
                        }else{
                            div.innerText="该账号被注册";
                        }

                    }else{
                       alert("服务器错误:"+httpState);
                    }
                }
            };
            //4. 发出请求
            xmlHttp.send("name="+name);


        }

    })();*/
</script>
</body>
</html>