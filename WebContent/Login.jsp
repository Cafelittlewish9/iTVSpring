<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<!-- <script src="js/bootstrap.js"></script> -->
<!-- <link rel="stylesheet" href="css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" href="css/justified-nav.css"> -->
<!-- <script src="js/bootstrap.file-input.js"></script> -->
<!-- <script src="js/js.cookie.js"></script> -->

<script src="js/Login.js"></script>
<style>
.input-group-addon{
width: 100px;
background-color:#337ab7;
color:#fff;
}
/* 合併時修改用 */
#SignUp_form span,#Login_form span,#getpassword span{
    width:112px;
}
#SignUp_form div,#Login_form div,#getpassword div{
width: 450px; margin:0px auto;padding:5px
}

</style>
<script>
// $(function () {
<%-- 	<% --%>
// 	int i = 0; 
// 	int j = 0;
// 	String a = null;
// 	int num1[] = {1,2,3,4,5,6,7,8,9,0}; 
// 	char ch1[] = {'A','B','C','D','E','F','G','H','I','J'}; 
// 	char ch2[] = {'K','L','M','N','O','P','Q','R','S','T'}; 
// 	char ch3[] = {'U','V','W','X','Y','Z','A','B','C','D'}; 
// 	int num2[] = {1,2,3,4,5,6,7,8,9,0};
// 	char ch4[] = {'K','L','M','N','O','P','Q','R','S','T'};
<%-- 	%> --%>
<%-- 	<% for(i = 0; i < 2; i++) {%> --%>
<%-- 	<%  --%>
// 	int randNumber1 = (int)(Math.random()*(10)); 
// 	int randNumber2 = (int)(Math.random()*(10)); 
// 	int randNumber3 = (int)(Math.random()*(10)); 
// 	int randNumber4 = (int)(Math.random()*(10));
// 	int randNumber5 = (int)(Math.random()*(10));
// 	int randNumber6 = (int)(Math.random()*(10));
<%-- 	%> --%>
<%-- 	<% a = num1[randNumber1]+""+ch1[randNumber2]+ch2[randNumber3]+ch3[randNumber4]+num2[randNumber5]+ch4[randNumber6];%> --%>
		
<%-- 	<% }%> --%>
	
// 	$('#signup').on('click', function() {
// 		var server = "rtmp://itvvm.cloudapp.net/live/";
<%-- 	    $('#broadcastWebsite').val(server+"<%=a%>"); --%>
	    
// 	  });
// });
</script>
</head>
<body>
<!-- Button trigger modal -->
<%-- <center> --%>
<!-- <button type="button" class="btn btn-primary btn-lg" id="signup" data-toggle="modal" data-target="#SignUp">註冊</button> -->
<!-- <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#Login">登入</button> -->
<%-- </center> --%>

<!-- SignUp Modal -->
<div class="modal fade" id="SignUp" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 style="text-align: center" class="modal-title" id="myModalLabel">註冊</h4>
			</div>
			<div class="modal-body">
						<div class="upload-form">
				<form id="SignUp_form">
					<fieldset style="text-align: center">

<!-- 						<div id="l_input1" class="input-group"> -->
<!-- 							<span class="input-group-addon" id="basic-addon1">帳號</span>  -->
<!-- 							<input id="memberAccount" type="text" name="memberAccount" -->
<!-- 								class="form-control" placeholder="請輸入" -->
<!-- 								aria-describedby="basic-addon1"> -->
<!-- 						</div> -->
<!-- 						<div id="l_input2" class="input-group"> -->
<!-- 							<span class="input-group-addon" id="basic-addon1">密碼</span> -->
<!-- 							<input id="memberPassword" type="text" name="memberPassword" -->
<!-- 								class="form-control" maxlength="20" placeholder="請輸入" -->
<!-- 								aria-describedby="basic-addon1"> -->
<!-- 						</div> -->
<!-- 						<div id="l_input3" class="input-group"> -->
<!-- 							<span class="input-group-addon" id="basic-addon1">E-mail</span>  -->
<!-- 							<input id="memberEmail" type="email" name="memberEmail" -->
<!-- 								class="form-control" maxlength="40" placeholder="請輸入" -->
<!-- 								aria-describedby="basic-addon1"> -->
<!-- 						</div> -->
<!-- 						<div class="input-group"> -->
<!-- 							<span class="input-group-addon" id="basic-addon1">暱稱</span> -->
<!-- 							<input id="memberNickname" type="text" name="memberNickname" -->
<!-- 								class="form-control" maxlength="40" placeholder="請輸入" -->
<!-- 								aria-describedby="basic-addon1"> -->
<!-- 						</div> -->
<!-- 						<div class="input-group"> -->
<!-- 							<span class="input-group-addon" id="basic-addon1">生日</span> -->
<!-- 							<input id="memberBirthday" type="date" name="memberBirthday" -->
<!-- 								class="form-control" placeholder="請輸入" -->
<!-- 								aria-describedby="basic-addon1"> -->
<!-- 						</div> -->
<!-- 						<div class="input-group"> -->
<!-- 							<span class="input-group-addon" id="basic-addon1">直播Server</span> -->
<!-- 							<input id="broadcastWebsite" type="text" name="broadcastWebsite" -->
<!-- 								class="form-control" placeholder="" -->
<!-- 								aria-describedby="basic-addon1" readonly> -->
<!-- 						</div> -->
<!-- 						<div id="l_input4" class="input-group"> -->
<!-- 							<span class="input-group-addon" id="basic-addon1">頭像</span> -->
<!-- 							<input id="memberPhoto" type="file" name="memberPhoto" -->
<!-- 								class="form-control" placeholder="" -->
<!-- 								aria-describedby="basic-addon1"> -->
<!-- 						</div>						 -->

<!-- 						<input value="Insert" type="hidden" name="production" -->
<!-- 							class="form-control" aria-describedby="basic-addon1"> -->
					
<!-- 					<div style="width: 450px; margin: 0px auto; padding: 5px" -->
<!-- 						class="input-group1"> -->
<!-- 						<input style="width: 440px" id="l_submit1" name="operation" -->
<!-- 							value="註冊  " class="btn btn-primary btn-small" type="submit"> -->
<!-- 					</div> -->
					</fieldset>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
</div>

<!-- Login Modal -->
<div class="modal fade" id="Login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content">
    
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 style="text-align:center" class="modal-title" id="myModalLabel">會員登入</h4>
      </div>
      
      <div class="modal-body">
<!-- 		<div class="upload-form"> -->
			<form id="Login_form" method="post" action="<c:url value='/login' />">
				<fieldset style="text-align:center">
				
				<div id="l_input1-1"  class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">帳號</span>
					  <input id="Login_memberAccount" type="text" name="memberAccount" class="form-control" placeholder="請輸入" aria-describedby="basic-addon1">
				</div>
				
				<div id="l_input2-1"  class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">密碼</span>
					  <input id="Login_memberPassword" type="text" name="memberPassword" class="form-control" maxlength="20" placeholder="請輸入" aria-describedby="basic-addon1">
				</div>
				
				
<!-- 				<input value="Select" type="hidden" name="operation" class="form-control" aria-describedby="basic-addon1"> -->
						
				</fieldset>
<!-- 			</form> -->
<!-- 			<form > -->
			<div style="width: 450px; margin:0px auto;padding:5px" class="input-group2-1">
				<input style="width: 440px" id="l_submit2-1" name="operation" value="登入" class="btn btn-primary btn-small" type="submit">
			</div>
<!-- 			</form> -->
			<div style="width: 450px; margin:0px auto;padding:5px" class="input-group2-2">
				<button style="width: 440px" id="l_submit2-2" name="operation" class="btn btn-primary btn-small" data-dismiss="modal" data-toggle="modal" type="button" data-target="#mypassword">忘記密碼</button>
			</div>
			</form>
<!-- 		</div> -->
      </div>
      	  <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
    </div>
  </div>
</div>

<!-- 註冊成功Modal -->

<!-- <div id="signupfinished" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" data-backdrop="static"> -->
<!--   <div class="modal-dialog modal-sm"> -->
<!--     <div class="modal-content"> -->
    

<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
<!--         <h4 class="modal-title">註冊成功！</h4> -->
<!--       </div> -->
<!--     </div> -->
    
    
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->


<!-- 提取密碼Modal -->
<div class="modal fade" id="mypassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content">
    
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 style="text-align:center" class="modal-title" id="myModalLabel">提取密碼</h4>
      </div>
      
      <div class="modal-body">
<!-- 		<div class="upload-form"> -->
			<form id="getpassword" method="post" action="">
				<fieldset style="text-align:center">
				
				<div id="l_input1-2" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">帳號</span>
					  <input id="get_memberAccount" type="text" name="memberAccount" class="form-control" placeholder="請輸入" aria-describedby="basic-addon1">
				</div>
				
				
				<div id="l_input2-2" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">E-mail</span>
					  <input id="get_memberEmail" type="text" name="memberEmail" class="form-control" maxlength="40" placeholder="請輸入" aria-describedby="basic-addon1">
				</div>
				
				<input value="Select" type="hidden" name="prodaction" class="form-control" aria-describedby="basic-addon1">
				
				</fieldset>
			</form>
			<div style="width: 450px; margin:0px auto;padding:5px" class="input-group3-1">
				<input style="width: 440px" id="l_submit3-1" name="operation" value="提取密碼" class="btn btn-primary btn-small" type="submit">
			</div>
<!-- 		</div> -->
      </div>
	      	  <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
    </div>
  </div>
</div>

</body>
</html>
