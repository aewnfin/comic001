function addCookie(objName,objValue,objHours){
	var str =objName+"="+encodeURI(objValue);
	if(objHours>0){
		var date=new Date();
		var ms=objHours*3600*1000;
		date.setTime(date.getTime()+ms);
		str+=";expires="+date.toGMTString();
		
	}
	document.cookie=str;
	
}
function getCookie(name) 
{ 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
 
    if(arr=document.cookie.match(reg))
 
        return decodeURI(arr[2]); 
    else 
        return null; 
} 
function delCookie(name) 
{ 
    var exp = new Date(); 
    exp.setTime(exp.getTime() - 1); 
    var cval=getCookie(name); 
    if(cval!=null) 
        document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
} 
