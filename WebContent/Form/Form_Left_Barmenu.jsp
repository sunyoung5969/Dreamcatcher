<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

	
	<%
		boolean guest_tf = false;
	
		if(session.getAttribute("id").equals("guest")){
			guest_tf = true;
		}
	%>
	
	
	<style>
		@import url('https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap');
		
		.btnevent {
			font-family: 'Kirang Haerang', cursive;
			background-color: #E6E6TA;
			padding: 15px 10px;
			margin: 2px;
			border: none;
			color: black;
			text-align: center;
			text-decoration: none;
			line-height:15px;
			font-size: 16px;
			font-family: 'Do Hyeon', sans-serif;
			display: inline-block;
			cursor: pointer;
			-webkit-transition-duration: 0.4s;
			transition-duration: 0.4s;
		}
		.btnevent:hover {
			background-color: #B57EB1;
			color: white;
		}
	</style>
	

</head>
<body>
	<form action="MainIndex.jsp">
		<input type="image" src ="https://i.imgur.com/hRR2Uii.png" width="150">
	</form>
	<br><br>
	<form action="MainForm.jsp">
		<button class="btnevent" style="width:150px; height:40px" > 드 림 캐 쳐 제 작 </button> 
	</form>
	<br><br>
	<form action="UserForm.jsp">
		<button class= "btnevent" style="width:150px; height:40px" > 게 시 판</button> 
		<input type="hidden" name="centerpage" value="005_BoardAllForward.jsp">
	</form>
	<br><br>
	<%if(guest_tf == false) {%>
	<form action="UserForm.jsp">
		<button class="btnevent" style="width:150px; height:40px" > 업 로 드 </button> 
		<input type="hidden" name="centerpage" value="001_UploadPage.jsp">
	</form>
	<br><br>
	<form action="UserForm.jsp" >
		
		<button class="btnevent" style="width:150px; height:40px" > 타 임 라 인 </button>
		<input type="hidden" name="centerpage" value="003_BoardMove.jsp">
	
	</form>
	<%} %>
</body>
</html>
