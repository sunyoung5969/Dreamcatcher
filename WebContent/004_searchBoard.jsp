<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.*" %>    
<!DOCTYPE html>
<%
ArrayList<String> titleList = (ArrayList<String>)request.getAttribute("titleList");
ArrayList<String> dateList = (ArrayList<String>)request.getAttribute("dateList");
ArrayList<String> imageList = (ArrayList<String>)request.getAttribute("imageList");
ArrayList<String> viewCountList = (ArrayList<String>)request.getAttribute("viewCountList");
%>
<html>
<head>
<meta charset="EUC-KR">
<title>타임라인</title>
<style>
	body {background:#000000; margin:12px 0 0 0;}
    body:after {
	    content : "";
	    display: block;
	    position: absolute;
	    top: 0;
	    left: 0;
	   	background-size:cover;
		background-image: url('https://i.imgur.com/Jq0PY4C.jpg'); 
	    width: 100%;
	    height: 100%;
	    opacity : 0.5;
	    z-index: -1;
	}
</style>

</head>
<body>

<div style="-ms-overflow-style: none; height: 920px; overflow-y: scroll; background:rgba(255,255,255,0.9)" >
 
<script type="text/javascript">

////////타이틀, 날짜, 이미지 주소, 조회수 가져오는 부분/////
var titleArray = new Array();
<% for(int i=0; i< titleList.size(); i++){%>
titleArray[<%=i%>] = '<%=titleList.get(i)%>';
<%}%>
var dateArray = new Array();
<% for(int i=0; i< dateList.size(); i++){%>
dateArray[<%=i%>] = '<%=dateList.get(i)%>';
<%}%>

var imageArray = new Array();
<% for(int i=0; i< imageList.size(); i++){%>
imageArray[<%=i%>] = '<%=imageList.get(i)%>';
<%}%>

var viewCountArray = new Array();
<% for(int i=0; i< viewCountList.size(); i++){%>
viewCountArray[<%=i%>] = '<%=viewCountList.get(i)%>';
<%}%>

<%
	String[] titleArray = new String[1000];
	for(int i=0; i< titleList.size(); i++)
	{
		if(titleList.get(i) == null)
			break;
		titleArray[i] = titleList.get(i);
	}


%>
////////타이틀, 날짜, 이미지 주소, 조회수 가져오는 부분/////

var name = '<%=(String)request.getAttribute("name")%>';
var result = '<%=(String)request.getAttribute("result")%>';

	if (result == null || result == "") {
		document.write("<p align = 'center'>");
		document.write("<br> <br> - " + name + " 타임라인 - <br>");

		for (var i = titleArray.length-1; i >= 0; i--) {
			document.write("<p align = 'center'>");
			document.write("title : " + titleArray[i] + "<br>");
			document.write("date : " + dateArray[i] + "<br>");
			document.write("by. " + name + "<br><br>");
			document.write("<img src = '" + imageArray[i] + "'><br><br>");
			document.write("<br><hr width  = '50%'><br>");
			document.write("</p>");
		}
	
	}else {
		alert(result);
	}
	
</script>
</div>

</body>
</html>