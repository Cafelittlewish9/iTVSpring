<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<!-- searchbar所需的 -->
<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />

<style>

.input-group-addon{
width: 100px;
background-color:#337ab7;
color:#fff;
}
/* 合併時修改用 */
#Login_form span{
    width:112px;
}
#Login_form div{
width: 450px; margin:0px auto;padding:5px
}
.modal-title{
	text-align:center;
}
.Loginmodal{
    background-color: #22282e;
    padding-bottom: 55px;
    padding-top: 100px;
    display: block;
    height: 650px;
}
</style>

</head>
<body>

	<jsp:include page="/Header.jsp" />

	<jsp:include page="/Bar.jsp" />

<!-- Login Modal -->
<section class="Loginmodal">
<div class="modal-dialog" style="margin: 100px auto 80px auto;">
    <div class="modal-content">
    
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">會員登入</h4>
      </div>
      
      <div class="modal-body">

			<form id="Login_form" method="post" action="<c:url value='/login.do'/>">
				<fieldset style="text-align:center">
				
				<div id="l_input1-1"  class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">帳號</span>
					  <input id="Login_memberAccount" type="text" name="memberId" class="form-control" placeholder="請輸入" aria-describedby="basic-addon1">
				</div>
				
				<div id="l_input2-1"  class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">密碼</span>
					  <input id="Login_memberPassword" type="text" name="memberPassword" class="form-control" maxlength="20" placeholder="請輸入" aria-describedby="basic-addon1">
				</div>
				
				
				<input value="Select" type="hidden" name="operation" class="form-control" aria-describedby="basic-addon1">
						

			<div style="width: 450px; margin:0px auto;padding:5px" class="input-group2-1">
				<input style="width: 440px" id="l_submit2-1" name="operation" value="登入" class="btn btn-primary btn-small" type="submit">
			</div>
			<div style="width: 450px; margin:0px auto;padding:5px" class="input-group2-2">
				<a style="width: 440px" id="l_submit2-2" class="btn btn-primary btn-small" role="button"  href="GetPassword.jsp">忘記密碼</a>
			</div>
			</fieldset>
			</form>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
      </div>
      	  <div class="modal-footer">
	      </div>
    </div>
</div>
</section><!-- End -->  

<jsp:include page="/Footer.jsp" />
</body>
</html>