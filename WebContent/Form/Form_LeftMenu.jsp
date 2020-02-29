<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<%
	String center_page = (String)request.getParameter("centerpage");
	
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

<script type="text/javascript" >

function left_selectmenu(design) {

	var path = "";

	if (design == "RING")
		path = "Form_RightMenu_Ring";
	else if (design == "STRING")
		path = "Form_RightMenu_String";
	else if (design == "FEATHER")
		path = "Form_RightMenu_Feather";
	else if (design == "DECO")
		path = "Form_RightMenu_Deco";

	var form = document.createElement("form");
	form.setAttribute("action", "MainForm.jsp");

	var input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "rightpage");
	input.setAttribute("value", path);
	form.appendChild(input);

	document.body.appendChild(form);

	form.submit();
}


</script>

</head>
<body>


	<table align="center">
		<tr>
			<td align="center"><!-- <input type="image" src="http://10.10.10.109/img/left/left_event_ring.png"
				onclick='left_selectmenu("RING")'> -->
				<input type="image" src="Form/img/left_event_ring.png" onclick='left_selectmenu("RING")'>
			</td>
		</tr>
		<td><br><br><br></td>
		<tr>
			<td align="center"><!-- <input type="image" src="http://10.10.10.109/img/left/left_event_string.png"
				onclick='left_selectmenu("STRING")'> -->
				<input type="image" src="Form/img/left_event_string.png" onclick='left_selectmenu("STRING")'>
			</td>
		</tr>
		<td><br><br><br></td>
		<tr>
			<td align="center"><!-- <input type="image" src="http://10.10.10.109/img/left/left_event_feather.png"
				onclick='left_selectmenu("FEATHER")'> -->
				<input type="image" src="Form/img/left_event_feather.png" onclick='left_selectmenu("FEATHER")'>
			</td>
		</tr>
		<td><br><br><br></td>
		<tr>
			<td align="center"><!-- <input type="image" src="http://10.10.10.109/img/left/left_event_deco.png"
				onclick='left_selectmenu("DECO")'>-->
				<input type="image" src="Form/img/left_event_deco.png" onclick='left_selectmenu("DECO")'>
				
			</td>
		</tr>
	</table>

</body>
</html>