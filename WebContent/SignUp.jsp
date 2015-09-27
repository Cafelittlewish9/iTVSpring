<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
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
#SignUp_form span,#Login_form span,#getpassword span{
    width:112px;
}
#SignUp_form div,#Login_form div,#getpassword div{
width: 450px; margin:0px auto;padding:5px
}
.modal-title{
	text-align:center;
}
.SignUpmodal{
    background-color: #22282e;
    padding-bottom: 55px;
    padding-top: 100px;
    display: block;
    height: 650px;
}
</style>

<script>
$(function () {
	<%
	int i = 0; 
	int j = 0;
	String a = null;
	int num1[] = {1,2,3,4,5,6,7,8,9,0}; 
	char ch1[] = {'A','B','C','D','E','F','G','H','I','J'}; 
	char ch2[] = {'K','L','M','N','O','P','Q','R','S','T'}; 
	char ch3[] = {'U','V','W','X','Y','Z','A','B','C','D'}; 
	int num2[] = {1,2,3,4,5,6,7,8,9,0};
	char ch4[] = {'K','L','M','N','O','P','Q','R','S','T'};
	%>
	<% for(i = 0; i < 2; i++) {%>
	<% 
	int randNumber1 = (int)(Math.random()*(10)); 
	int randNumber2 = (int)(Math.random()*(10)); 
	int randNumber3 = (int)(Math.random()*(10)); 
	int randNumber4 = (int)(Math.random()*(10));
	int randNumber5 = (int)(Math.random()*(10));
	int randNumber6 = (int)(Math.random()*(10));
	%>
	<% a = num1[randNumber1]+""+ch1[randNumber2]+ch2[randNumber3]+ch3[randNumber4]+num2[randNumber5]+ch4[randNumber6];%>
		
	<% }%>
	
	var server = "rtmp://itvvm.cloudapp.net/live/";
	$('#broadcastWebsite').val(server+"<%=a%>");
	console.log(server+"<%=a%>");
	
// 	 $('#l_submit1').click(function(){
//         	 $.get('registry.do',$('#SignUp_form').serialize(),function(data){
//       	   		})
//  	});
});
</script>

</head>
<body>

	<jsp:include page="/Header.jsp" />

	<jsp:include page="/Bar.jsp" />

<section class="SignUpmodal">
<div class="modal-dialog" style="margin: 0px auto 80px auto;">
    <div class="modal-content">
    	
	    <div class="modal-header">
<!-- 	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button> -->
	       <h4 class="modal-title" id="myModalLabel">註冊</h4>
	    </div>
      
      <div class="modal-body">
<!-- 		<div class="upload-form"> -->

				<fieldset style="text-align:center">
				<form id="SignUp_form" action="<c:url value='/registry'/>" method="post">
				<div id="l_input1"  class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">帳號</span>
					  <input value="${errorMsg}" id="memberAccount" type="text" name="memberAccount" class="form-control" placeholder="帳號限英文字母、數字，長度必須在6-20之間" aria-describedby="basic-addon1">
				</div>
				
				<div id="l_input2"  class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">密碼</span>
					  <input value="${errorMsg}" id="memberPassword" type="text" name="memberPassword" class="form-control" maxlength="20" placeholder="密碼限英文字母、數字，長度必須在8-20之間" aria-describedby="basic-addon1">
				</div>
				
				<div id="l_input3" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">E-mail</span>
					  <input value="${errorMsg}" id="memberEmail" type="text" name="memberEmail" class="form-control" maxlength="40" placeholder="此項為必填欄位" aria-describedby="basic-addon1">
				</div>
				<div class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">暱稱</span>
					  <input value="${param.memberNickname}" id="memberNickname" type="text" name="memberNickname" class="form-control" maxlength="40" placeholder="請輸入" aria-describedby="basic-addon1">
				</div>
				<div   class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">生日</span>
					  <input value="${param.memberBirthday}" id="memberBirthday" type="date" name="memberBirthday" class="form-control" placeholder="請輸入" aria-describedby="basic-addon1">
				</div>				
				<div  class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">直播Server</span>
					  <input id="broadcastWebsite" type="text" name="broadcastWebsite" class="form-control" placeholder="" aria-describedby="basic-addon1" readonly>
				</div>				

				<input value="Insert" type="hidden" name="operation" class="form-control" aria-describedby="basic-addon1">
								<div style="width: 450px; margin:0px auto;padding:5px" class="input-group1">
				<input style="width: 440px" id="l_submit1"  value="註冊" name="operation" class="btn btn-primary btn-small" type="submit">
				</div>		
			</form>
			</fieldset>

<!-- 			<div id="l_input4"  class="input-group"> -->
<!-- 					  <span  class="input-group-addon" id="basic-addon1">頭像</span> -->
<!-- 					  <input id="memberPhoto" type="file" name="memberPhoto" class="form-control" placeholder="" aria-describedby="basic-addon1"> -->
<!-- 				</div> -->
	   </div>
				<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><a href="HomePageVersion3.jsp" style="text-decoration: none">Close</a></button>
			</div>
			<div class="modal-footer">
		    </div>
  	</div>
</div>
</section><!-- End -->  
  
  <jsp:include page="/Footer.jsp" />
</body>
</html>