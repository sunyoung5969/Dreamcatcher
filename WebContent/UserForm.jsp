<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<%
	String center_page =(String)request.getParameter("centerpage");

	if(center_page == null){
		center_page = "Form/Form_Nullpage.jsp";
	}
	if(request.getAttribute("centerpage") != null)
	{
		center_page = (String)request.getAttribute("centerpage");
	}
	System.out.println(center_page);
%>

<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<style>
	   .hide{display: none;}
	   .btn:hover button{display: block;}
	   
	  @import url('https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap');
	  
		.btnevent {
			background-color: #E6E6TA;
			padding: 15px 10px;
			margin: 2px;
			border: none;
			color: black;
			text-align: center;
			text-decoration: none;
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
<body>
	
	<table align="center" width="1200" id="ssss">

		<tr height="920">
		
			<td style="background-position:center; background-size:cover; border:1px solid #fff" align="center" width="240">
			<jsp:include page="Form/Form_Left_Barmenu.jsp"></jsp:include>
			</td>
			
			<td align="center" width="960" height="920"><jsp:include	page='<%=center_page%>'></jsp:include></td>
			
		</tr>

	</table>
	
</body>
</html>