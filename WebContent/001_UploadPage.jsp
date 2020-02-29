<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
 <header id="first">
        <div class="header-content" style="height:920px; background:rgba(255,255,255,0.9)">
             <form action = "fileUpload99" method = "post" enctype="Multipart/form-data" style="padding-top:30%">
             <p align = "center">
                <h1 class="cursive"> File Upload Form  </h1>
                <h4>A free FileUpload page</h4>
                </p>
                <table align="center" border=0 cellpadding=0 cellspacing=0>
                <tr>
                	<td> <input style= "font-size:12pt; color:#000000" type = "hidden" name = "name" value= '<%=session.getAttribute("id")%>'> </td>
                </tr>
                <tr>
                	<td> <p class="p.text-primary "> Title &nbsp  </p> </td>
                	<td> <input style= "font-size:12pt; color:#000000" type = "text" name = "subject"> </td>
                </tr>
                <tr>
                	<td> <p class="p.text-primary "> FileName &nbsp  </p></td>
                	<td> <input style= "font-size:12pt; color:#000000" type = "file" name = "fileName1"> </td>
                </tr>
                	                
                <hr>
                <tr>
                <td colspan ="2" align="center"> <input type="submit" class="btn btn-primary btn-l page-scroll" value="Submit">       
                </tr></table>
                </form>
          
            </div>
        </div>       

 </header>



</body>
</html>