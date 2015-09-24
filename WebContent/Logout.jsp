<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logout</title>


</head>
<body>
<!-- 先將使用者名稱取出 -->

<!-- 移除放在session物件內的屬性物件 -->
<c:remove var="user" scope="session" />
<%-- <c:remove var="LoginOK" scope="session" /> --%>

<!-- 引入共同的頁首 -->

<!-- 下列六行敘述設定登出後要顯示的感謝訊息 -->
<%-- <c:set var="logoutMessage" scope="request"> --%>
<!-- <font color='blue' ><BR> -->
<%-- 訪客${ memberName }，感謝您使用本系統。<BR> --%>
<!-- 您已經登出<BR> -->
<!-- </font> -->
<%-- </c:set> --%>

<jsp:forward page="/HomePageVersion3.jsp"/>

</body> 
</html>