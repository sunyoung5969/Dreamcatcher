<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.*" %>    
<!DOCTYPE html>
<%


ArrayList<String> titleList = (ArrayList<String>)request.getAttribute("titleList");
ArrayList<String> dateList = (ArrayList<String>)request.getAttribute("dateList");
ArrayList<String> imageList = (ArrayList<String>)request.getAttribute("imageList");
ArrayList<String> nameList = (ArrayList<String>)request.getAttribute("nameList");
%>

<html>
<head>
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
	img {margin:20px;}
</style>
<meta charset="EUC-KR">
<title>�Խ���</title>


</head>
<body >
<div style="-ms-overflow-style: none; height: 920px; overflow-y: scroll; background:rgba(255,255,255,0.9)" >
<script type="text/javascript">

////////Ÿ��Ʋ, ��¥, �̹��� �ּ�, ��ȸ�� �������� �κ�/////
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

var nameArray = new Array();
<% for(int i=0; i< nameList.size(); i++){%>
nameArray[<%=i%>] = '<%=nameList.get(i)%>';
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
////////Ÿ��Ʋ, ��¥, �̹��� �ּ�, ��ȸ�� �������� �κ�/////



	
		document.write("<p align = 'center'>");
		document.write("<br> <br> - ��ü �̹��� ��� - <br>");

		for (var i = titleArray.length-1; i >= 0; i--) {
			document.write("<p align = 'center'>");
			document.write("title : " + titleArray[i] + "<br>");
			document.write("by. " + nameArray[i] + "<br>");
			document.write("date : " + dateArray[i] + "<br>");
			document.write("<img src = '" + imageArray[i] + "'><br><br>");
			document.write("<br><hr width  = '50%'><br>");
			document.write("</p>");
		}
	
	
</script>
</div>

</body>
</html>