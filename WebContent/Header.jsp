<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<script src="js/bootstrap.js"></script>
<!-- <link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" /> -->
<!-- <link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" /> -->

	<link href="css/Header.css" rel="stylesheet">
  
  
<script>


$(function(){
	if('${user.memberAccount}'!=""){
// 		判斷是否已建立實況
		$.ajax({
			url:'BroadcastOrderServlet',
			type:'get',
 			data:{'memberAccount':'${user.memberAccount}',
 				  'prodaction':'searchAccount'},
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					console.log(data);
					console.log(data.length);
					console.log(v.memberAccount);
						if(v.memberAccount!=null){
							$('#createlive').css('display','none');
						 	$('#cancellive').css('display','block');
						}else{
						 	$('#createlive').css('display','block');
							$('#cancellive').css('display','none');
						}
				});
			}
		})
	}
})

</script>

<!-- </head> -->
<!-- <body> -->

		<jsp:include page="/Login.jsp" />
   
		<jsp:include page="/VideoUpload.jsp" />
		   
		<jsp:include page="/CreateLive.jsp" />
		   	
		<jsp:include page="/Setting.jsp" />
		
<header id="header">

<!--     	<div style="float:left;padding-top: 10px;"> -->
<!--        		<h1><a href="HomePageVersion2.jsp"><img src="logo/itv.jpg" /></a></h1> -->
<!--         </div> -->

			<div style="float:left;margin-left:30px;color:white;padding-top:10px">
	    		<div>
	       			<a href="HomePageVersion3.jsp" style="text-decoration: none;"><h1><p>I | T | V</p></h1></a>
	       		</div>
	        </div>
        	
	        <div class="container" style="float:left;width:780px">
			    <div class="row">
			        <div class="col-md-4 col-md-offset-5" style="margin-left:52%">
			            <form action="SearchPage.jsp" class="search-form">
			                <div class="form-group has-feedback">
			            		<label for="search" class="sr-only">Search</label>
			            			<input  type="text" class="form-control" name="search" id="search" placeholder="Search...">
			              		<span class="glyphicon glyphicon-search form-control-feedback"></span>
			            	</div>
			            </form>
			        </div>
			    </div>
			</div>
			
<%-- 			<c:set var="LoginOK" value='kkk' scope="session" /> --%>
			<div class="col-sm-4">
				<c:if test="${!empty user}">
					<div class="collapse navbar-collapse" id="navbar" style="margin-right:10px">
						<ul class="nav navbar-nav navbar-right">
							
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" style="padding:0px">
								<div class="stream-title--image">
					                <img src="${user.memberName}" />
					            </div >
					            </a>
								<ul class="dropdown-menu" role="menu">
									<li>
									<a href="PersonalPage.jsp" style="width: 169px;height: 34px;text-align: center;line-height: 34px;">
										個人頁面
									</a>
									</li>
								
									<li>
									<button type="button" class="btn btn-primary btn-me" id="createlive" data-toggle="modal" data-target="#CreateModal" style="display: block;">
									  	建立實況
									</button>
									</li>
									<li>
									<button type="button" class="btn btn-primary btn-me" id="cancellive" data-toggle="modal" data-target="#CancelModal" style="display: none;">
									  	取消實況
									</button>
									</li>
									<li>
									<button type="button" class="btn btn-primary btn-me" id="uploadfile" data-toggle="modal" data-target="#uploadformModal">
									 	檔案上傳
									</button>
									</li>
									<li>
									<button type="button" class="btn btn-primary btn-me" id="fallow" data-toggle="modal" data-target="#fallowModal">
									  	編輯追蹤名單
									</button>
									</li>
									<li>
									<button type="button" class="btn btn-primary btn-me" id="setting" data-toggle="modal" data-target="#settingModal">
									  	設定
									</button>
									</li>
									<li><a href="Logout.jsp?operation" style="width: 169px;height: 34px;text-align: center;line-height: 34px;">登出</a></li>
								</ul>
							</li>
						</ul>
			
					</div>
				</c:if>
			
			
			<c:if test="${empty user}">
			
	          <div class="header-home--nav">
	            
<!-- 	              <a class="header-home--link login" href="Login.jsp" id="woopra_login">登入</a> -->
	              <a class="header-home--link signup" href="SignUp.jsp" id="woopra_sign_up">註冊</a>

				<button type="button" class="header-home--link login" data-toggle="modal" data-target="#Login">登入</button>
<!-- 				<button type="button" class="header-home--link signup" id="signup" data-toggle="modal" data-target="#SignUp">註冊</button> -->
	            
	              <a class="header-home--link shows" href="LiveStream.jsp" style="height:39px;text-decoration: none">觀賞實況</a>
	          </div>
			</c:if>
	        </div>
			
    </header>
<!-- </body> -->
<!-- </html> -->