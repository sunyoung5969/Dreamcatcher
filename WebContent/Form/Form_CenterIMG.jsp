<%@page import="sta.StaticC"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/jquery.min.js"></script>
	
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js"></script>
  	   
  	
	<script type='text/javascript'>
		var img_L = 0;
		var img_T = 0;
		var targetObj;
		var left_x = 0;
		var top_y =0;
		var index_js = 0;
		var width_js = 0;
		
		function getLeft(o) {
			return parseInt(o.style.left.replace('px', ''));
		}
		function getTop(o) {
			return parseInt(o.style.top.replace('px', ''));
		}

		// 이미지 움직이기
		function moveDrag(e) {
	
			var e_obj = window.event ? window.event : e;
						
			var dmvx = parseInt(e_obj.clientX + img_L);
			var dmvy = parseInt(e_obj.clientY + img_T);
			
			targetObj.style.left = dmvx + "px";
			targetObj.style.top = dmvy + "px";
			
			var id = index_js+500;
			
			var preview = document.getElementById(id);
			var width = preview.clientWidth;
			
			console.log(width);
			
			left_x = dmvx;
			top_y = dmvy;
			width_js = width;
			return false;
		}

		// 드래그 시작
		function startDrag(e, obj, index) {
			targetObj = obj;
			//img.style.width= 500+"px";
			index_js = index;
			var e_obj = window.event ? window.event : e;

			img_L = getLeft(obj) - e_obj.clientX;			
			img_T = getTop(obj) - e_obj.clientY;
			
			document.onmousemove = moveDrag;
			document.onmouseup = stopDrag;
			
			if (e_obj.preventDefault)
				e_obj.preventDefault();
		}

		// 드래그 멈추기
		function stopDrag() {
			
			document.onmousemove = null;
			document.onmouseup = null;
		}
	
		
		function setparameter(){ // 좌표값, 크기 보내기 함수
			var form = document.createElement("form");
			form.setAttribute("action","lefttop");
			form.setAttribute("method", "get");
			
			var input_data = document.createElement("input");
			input_data.setAttribute("type", "hidden");
			input_data.setAttribute("name", "LEFT");
			input_data.setAttribute("value", left_x);
			form.appendChild(input_data);
			
			var input_data = document.createElement("input");
			input_data.setAttribute("type", "hidden");
			input_data.setAttribute("name", "TOP");
			input_data.setAttribute("value", top_y);
			form.appendChild(input_data);
			
			var input_data = document.createElement("input");
			input_data.setAttribute("type", "hidden");
			input_data.setAttribute("name", "INDEX");
			input_data.setAttribute("value", index_js);
			form.appendChild(input_data);
			
			var input_data = document.createElement("input");
			input_data.setAttribute("type", "hidden");
			input_data.setAttribute("name", "WIDTH");
			input_data.setAttribute("value", width_js);
			form.appendChild(input_data);
			
			document.body.appendChild(form);
			
			form.submit();
		}
	</script>
 
	<script>
		$( document ).ready(function() {
			// 이미지 위치 지정 제이쿼리
		<%	for(int i = 0; i<StaticC.left_arr.size(); i++){	%>
				$( '<%= "#" + i %>' ).offset({ left: <%=StaticC.left_arr.get(i)%>, top:<%=StaticC.top_arr.get(i)%>});
				$( '<%= "."+ (i+500) %>' ).width('<%=StaticC.width_arr.get(i) %>');
				$( '<%= "#"+ (i+500) %>' ).resizable({aspectRatio: true, autoHide: true,});
		<%	} %>
		});
	</script>
	<script>
	
	function sample() { // 이미지 캡쳐 기능 메소드
	    
	    var background = document.getElementById('example').style.background;
	    if(background == "") {
	        document.getElementById('example').style.background = "#fff"; 
	    }
	        
	    html2canvas(document.getElementById('example'), {
	        useCORS: true, onrendered: function(canvas) {
	            canvas.toBlob(function(blob) {
	                saveAs(blob, 'download.png');
	            });
	        }
	    });
	}
	
	</script>
	<script type="text/javascript">
      $(document).ready(function() {
           $( ".base" ).css( "margin-left", "30%" );
         });
   </script>
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

</head>
<body>

	<table height="70" width="680">
		<tr>
			<td style="padding-left:10px">
				<form action="INIT" method="get">
					<!-- <input type="image" src="Form/img/init.png" width="100"> -->
					<button class="btnevent" style="width:150px; height:40px" > 초 기 화 </button> 
				</form>
			</td>
			
			<td align="right" style="padding-right:10px">
				<!-- <input type="image" src="Form/img/imgsave.png" width="100" onclick="sample();">  -->
				<button class="btnevent" style="width:150px; height:40px" onclick="sample();"> 이 미 지 저 장 </button> 
			</td>
		</tr>
	</table>	

	<div id="example" style="width: 680px; height: 800px;">

	<%
		for (int i = 0; i < 500; i++) {
			if (session.getAttribute("IMG_PATH" + i) == null){
				break;}
	%>
	
	<div class="btn base" id ='<%=i%>' style="position:absolute; left: 400px; top: 100px; cursor: pointer; cursor: hand"
		onmousedown="startDrag(event, this, <%=i%>)">

	<img id ='<%=i+500%>' class='<%=i+500%>' src='<%=session.getAttribute("IMG_PATH" + i)%>'>
	
	<button class="hide" onclick='setparameter()' style="position:absolute; top:-30; width:50px">적용</button>
	
	</div>
	<% } %>
	</div>

</body>
</html>