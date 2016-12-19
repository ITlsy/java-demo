/**
 * Created by Administrator on 2016/12/6 0006.
 */
var ajax={};
 /*ajax.sendPost=function (url,data,fn,errorFn) {
     var xmlHttp=new XMLHttpRequest();
     xmlHttp.open("post",url);
     xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
     xmlHttp.onreadystatechange=function () {
         if(xmlHttp.readyState==4){
             if(xmlHttp.status==200){
                 var result=xmlHttp.responseText;
                 fn(result);


             }else{
                 errorFn();
             }
         }

     };
     xmlHttp.send(data);
    
};
*/

ajax.sendPost=function (obj) {
    var xmlHttp=new XMLHttpRequest();
    xmlHttp.open("post",obj.url);
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlHttp.onreadystatechange=function () {
        if(xmlHttp.readyState==4){
            if(xmlHttp.status==200){
                var result=xmlHttp.responseText;
                obj.success(result);


            }else{
                obj.error();
            }
        }

    };
    xmlHttp.send(obj.data);

};
