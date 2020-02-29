
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>
<%@ page import = "java.sql.*"%>
<!DOCTYPE html>
  

<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>team2 Dreamcatcher</title>
    <meta name="description" content="This is a free Bootstrap landing page theme created for BootstrapZero. Feature video background and one page design." />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="generator" content="Codeply">
    <link rel="stylesheet" href="./css/bootstrap.min.css" />
    <link rel="stylesheet" href="./css/animate.min.css" />
    <link rel="stylesheet" href="./css/ionicons.min.css" />
    <link rel="stylesheet" href="./css/styles.css" />
    
    

<script type="text/javascript"  charset="utf-8">

<%
Connection conn = null;
Statement stmt = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
Boolean connect = false;
String connectCheck = "";
String tableCheck = "";
ArrayList<String> memberIDList = new ArrayList<String>();


String query_select = "select * from dream.user_info";

memberIDList.clear(); // memberIDList를 불러올때마다 초기화

try {
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://10.10.10.109:3306/dream", "user", "1234");
	System.out.println("db ok!");

	try {
		stmt = conn.createStatement();

	} catch (Exception e) {
		System.out.println("statement creation failed !");
		response.sendRedirect("error.jsp");
	}

	rs = stmt.executeQuery(query_select);

	while (rs.next()) {
		memberIDList.add(rs.getString(2));
	}
	
	conn.close();


}catch(Exception e) {
	System.out.println("id found failed!!");
	response.sendRedirect("error.jsp");
}


%>
	var idArray = new Array();
	<% for(int i=0; i< memberIDList.size(); i++){%>
		idArray[<%=i%>] = '<%=memberIDList.get(i)%>';
	<%}%>

	var idchecked = false; // 중복체크를 했는지 안했는지 여부
	

	
	function idCheck(){ //입력받은 아이디가 중복인지 아닌지 체크하는 펑션

		var id = myform.joinid.value;
		var idduplicate = true; //중복인지 아닌지 여부
		
		
		for(var i =0; i < idArray.length; i++){
			if(id == idArray[i]){
				idduplicate = false;
			}
		}

		if(idduplicate == false){
			alert("중복된 아이디입니다. 다른 아이디를 입력해주세요.");	
		}else if(id.length == 0){
			alert("아이디를 입력해주세요.");	
		}else{
			alert("사용 가능한 아이디입니다.");
			idchecked = true;
		}

	}	


	
	

	function check(){ //입력받은 정보가 올바른지 체크하는 펑션
		var id = myform.joinid.value;
		var pw = myform.joinpw.value;
		var pwcheck = myform.joinpwcheck.value;
		var nickname = myform.joinnickname.value;


		if(pw != pwcheck){ //비밀번호 
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
		if(idchecked == false){
			alert("아이디 중복체크를 진행해주세요.");
			return false;
		}
		
		if(id.length == 0){
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		if(pw.length == 0){
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		
		if(pwcheck.length == 0){
			alert("비밀번호 확인을 입력해주세요.");
			return false;
		}
		
		if(nickname.length == 0){
			alert("닉네임을 입력해주세요.");
			return false;
		}
		
		
		if(idcheckString){
			alert("아이디는 15자 이내여야 합니다.");
			return false;
		}
		
		if(pw.length > 20){
			alert("비밀번호는 20자 이내여야 합니다.");
			return false;
		}
		
		if(nickname.length > 10){
			alert("닉네임은 10자 이내여야 합니다.");
			return false;
		}

	}
		
	
	function checkValue(){ //입력받은 정보가 올바른지 체크하는 펑션
		inputForm = eval("document.loginInfo");
		if(inputForm.id.value.length == 0){
			alert("아이디를 입력하세요");
			inputForm.id.focus();
			return false;
		}
		if(inputForm.pw.value.length == 0){
			alert("비밀번호를 입력하세요.");
			inputForm.pw.focus();
			return false;
		} 

	}
	
	
	
	function guestLogin(){
		<%session.setAttribute("id","guest");%>
		alert("업로드, 타임라인 기능은 로그인 후 이용 가능합니다.");
	}
	
</script>    

  </head>


  <body>

    <header id="first">
        <div class="header-content">
            <div class="inner" >
              	<img src ="https://i.imgur.com/hRR2Uii.png" width="150"><br><br><br><br><br>
                <h1 class="cursive" style="font-family: 'Shadows Into Light', cursive;"><b>Team2 Dreamcatcher</b></h1>
                <h4  style="font-family:'Roboto Condensed', sans-serif;">A free website for making custom dreamcatchers</h4>
                <hr>
                <a href="#video-background" class="btn btn-primary btn-xl" data-toggle ="collapse" data-target = "#signin">Sign In</a>
                <div class = "collapse" id = "signin">
					<form name="loginInfo" method="post" action="loginPro.jsp"
							onsubmit="return checkValue()">

					<br>
						<p align="center">
									ID<br>
									<input type="text" name="id" class="border_round">
								</p>
						
							<p align="center" >
									PASSWORD<br>
									<input type="password" name="pw" class="border_round">
								</p>
								<input type="submit" value="Submit"
							class="btn btn-primary btn-xsmall"/>
					
				</form>


				</div>
                <br><br>
                <a href="#video-background " class="btn btn-primary btn-xl page-scroll" data-toggle = "collapse" data-target = "#signup">Sign Up</a>
                <div class= "collapse" id = "signup">
                <br>
					<table align="center" >
						<tr>
							<td>
								<form action="cssjoin" name="myform" onsubmit="return check()" method = "post">
									<p align="center">
										<br>ID<br><input type="text" name="joinid"
											onblur="idcheckshow()" class = "border_round"><input type="button"
											value="Check" onclick="idCheck()" class = "btn btn-primary btn-xsmall" ><br>
									<p id=idalert align="center"
										style="font-size: 9pt;"></p>
									<p align="center">
										PASSWORD<br>
										<input type="password" name="joinpw" onblur="pwcheckshow()" class = "border_round"><br>
									<p id=pwalert align="center"
										style="font-size: 9pt;"></p>
									<p align="center">
										PW CHECK<br>
										<input type="password" name="joinpwcheck"
											onblur="pwcheckcheckshow()" class = "border_round"><br>
									<p id=pwcheckalert align="center"
										style="font-size: 9pt;"></p>
									<p align="center">
										USER NAME<br>
										<input type="text" name="joinnickname"
											onblur="nicknamecheckshow()" class = "border_round"><br>
									<p id=nicknamealert align="center"
										style="font-size: 9pt;"></p>
									<p align="center">
									<input type="submit" value="Submit" class = "btn btn-primary btn-xsmall">
									</p>
								</form>
							</td>
						</tr>
					</table>
				</div>
               <br><br>
               <a href="#video-background" class="btn btn-primary btn-xl page-scroll" data-toggle = "collapse" data-target = "#guest">Guest</a>
               <div class= "collapse" id = "guest">
               		<br>
               		<form action = "START" method="get" onsubmit="guestLogin()">
               		
               		<font size = "4px">Do you want to access as a guest?<br>(your dreamcatcher will not be saved on DB)</font><br><br>
               		<input type="submit" value="Yes!" class = "btn btn-primary btn-xsmall">
               		</form>
               </div>
            </div>
        </div>
        
   <script type="text/javascript">

	function idcheckshow() {
		var id = myform.joinid.value;
		if(id.length == 0){
			document.getElementById("idalert").innerHTML = "아이디를 입력하여야 합니다."
		}else if (id.length > 15) {
			document.getElementById("idalert").innerHTML = "아이디는 15자 이내여야합니다.";
		}else{
			document.getElementById("idalert").innerHTML = "";
		}
	}
	
	
	function pwcheckshow(){
		var pw = myform.joinpw.value;
		if(pw.length == 0){
			document.getElementById("pwalert").innerHTML = "비밀번호를 입력하여야 합니다."
		}else if (pw.length > 20) {
			document.getElementById("pwalert").innerHTML = "비밀번호는 20자 이내여야합니다.";
		}else{
			document.getElementById("pwalert").innerHTML = "";
		}
	}
	
	
	function pwcheckcheckshow(){
		var pw = myform.joinpw.value;
		var pwcheck = myform.joinpwcheck.value;
		if(pwcheck.length == 0){
			document.getElementById("pwcheckalert").innerHTML = "비밀번호 확인을 입력하여야 합니다."
		}else if (pw != pwcheck) {
			document.getElementById("pwcheckalert").innerHTML = "비밀번호가 일치하지 않습니다.";
		}else{
			document.getElementById("pwcheckalert").innerHTML = "";
		}
	}
	
	
	function nicknamecheckshow() {
		var nickname = myform.joinnickname.value;
		if(nickname.length == 0){
			document.getElementById("nicknamealert").innerHTML = "닉네임을 입력하여야 합니다."
		}else if (nickname.length > 15) {
			document.getElementById("nicknamealert").innerHTML = "닉네임은 15자 이내여야합니다.";
		}else{
			document.getElementById("nicknamealert").innerHTML = "";
		}
	}
	
	
	
	
</script>     
        
<script src="./js/jquery.min.js"></script>
										<script src="./js/bootstrap.min.js"></script>  
    <script src="./js/jquery.easing.min.js"></script> 
      <script src="./js/wow.js"></script> 
    <script src="./js/scripts.js"></script>  
  
									</body>
</html>