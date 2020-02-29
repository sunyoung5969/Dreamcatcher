<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
	<style type="text/css">
	   div::-webkit-scrollbar {display:none;}
	</style>
</head>
<body>
	<div style="-ms-overflow-style: none; height: 850px; overflow: scroll;">
	<%
		String[] img_deco = (String[])session.getAttribute("FEATHER");
		int index = 0;
		for(int i =0; i<img_deco.length; i++){
			if(img_deco[i] == null)
				break;
			
			index++;
	%>
		<form action="RightClick" method="get">
			<input type="image" src ='<%=img_deco[i] %>' height="150"><br><br><br>
			<input type="hidden" name="rightpage" value="Form_RightMenu_Feather">
			<input type="hidden" name="centerpage" value="Form_CenterIMG">
			<input type="hidden" name="path" value='<%=img_deco[i]%>'>
			<input type="hidden" name="index" value='<%=String.valueOf(i)%>'>
		</form>
	<%} %>
	</div>
</body>
</html>