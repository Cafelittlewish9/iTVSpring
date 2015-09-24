<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<FORM method='post' action='VideoServlet'> 
<table>
	<tr>
		<td>ID : </td>
		<td><input type="text" name="memberId" value="${param.memberId}"></td>
		<td><span class="error">${error.id}</span></td>
	</tr>
	<tr>
		<td>ClassName : </td>
		<td><input type="text" name="videoClassName" value="${param.videoClassName}"></td>
		<td></td>
	</tr>
	
		<input type="hidden" name="theFile1" value="${theFile1}">
		
	<tr>
		<td>Description : </td>
		<td><input type="text" name="videoDescription" value="${param.videoDescription}"></td>
		<td><span class="error">${error.price}</span></td>
	</tr>
	<tr>
		<td><INPUT TYPE='submit' name="prodaction" value="Insert"> </td>
	</tr>
</table>
</FORM> 
</body>
</html>