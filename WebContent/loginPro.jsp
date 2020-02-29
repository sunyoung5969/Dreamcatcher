<%@page import="java.sql.*" %>
<%@page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<html>
<head>
    <title>로그인 처리 JSP</title>
</head>
<body>

   <%
   
		Connection conn =null;
		Statement stmt = null;
		PreparedStatement patmt = null;
		ResultSet rs =null;
		Boolean connect = false;
		
		String query_select = "select * from dream.user_info";	// 검색
		String id = request.getParameter("id");
		System.out.println(id);
		String pass = request.getParameter("pw");
		System.out.println(pass);
		
		String sql = "SELECT * FROM dream.user_info WHERE user_id = '"+id+"'" ;
		String password;		
		String idcheck;
		String t;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://10.10.10.109:3306/dream", "user", "1234");
			System.out.println("db ok!");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);	
			System.out.println(rs);
			if(rs.next() == false){
				 	rs.close();
				   stmt.close();
				   conn.close();
				   out.println("<script>");
				   out.println("alert('존재하지 않는 아이디입니다!')");
				   out.println("location.href='MainIndex.jsp'");
				   out.println("</script>");			  	
			}
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			t = rs.getString(2);
			System.out.println(t);		
			idcheck = rs.getString("user_id");
			System.out.println(idcheck);
			if(idcheck.equals(id)){
				password = rs.getString("user_pw");	
				if(password.equals(pass)){
					   out.println("<script>");
					   out.println("alert('로그인 되었습니다. 환영합니다. :)')");
					   out.println("</script>");
					   session.setAttribute("id", id);
					   %>
					   	<jsp:forward page="START"></jsp:forward>
					   <%
				   }else{
					   out.println("<script>");
					   out.println("alert('정보가 틀렸습니다!')");
					   out.println("location.href='MainIndex.jsp'");
					   out.println("</script>");
					   
				   }
			   }else{
				   out.println("<script>");
				   out.println("alert('존재하지 않는 아이디입니다!')");
				   out.println("location.href='MainIndex.jsp'");
				   out.println("</script>");
				   
			   }
			
	      conn.close();
	 	}catch(Exception e){
	          e.printStackTrace();
	 }
      
   
   %>
</body>
</html>
