<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<%
	String right_page =(String)request.getParameter("rightpage");
	String center_page = (String)session.getAttribute("centerpage");

	if(right_page == null){
		right_page = "Form/Form_Nullpage";
	}else{
		String temp = right_page;
		right_page = "Form/";
		right_page+= temp;
	}
	
	if(center_page == null){
		center_page = "Form/Form_Nullpage";
	}else{
		String temp = center_page;
		center_page = "Form/";
		center_page+= temp;
	}
	

%>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
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
	
	<table align="center" width="1200" id="ssss">

		<tr height="920">
		
			<td style="background-position:center; background-size:cover; border:1px solid #fff" align="center" width="240" align="center" width="240"><jsp:include
					page="Form/Form_Left_Barmenu.jsp"></jsp:include>
			</td>
			
			<td align="center" width="140" style="background-color: rgba( 255, 255, 255, 0.8 );"><jsp:include	page="Form/Form_LeftMenu.jsp"></jsp:include></td>

			<td align="center" width="680" style="background-color: rgba( 255, 255, 255, 1 );"><jsp:include	page='<%=center_page + ".jsp"%>'></jsp:include></td>
			
			<td align="center" width="140" style="background-color: rgba( 255, 255, 255, 0.8 );"><jsp:include page='<%=right_page + ".jsp"%>'></jsp:include></td>
		
		</tr>

	</table>
	
</body>
</html>