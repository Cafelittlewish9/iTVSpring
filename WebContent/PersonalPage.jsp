<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.* , java.lang.Math"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PersonalPage</title>
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
	<link href="css/SearchPageBorder.css" rel="stylesheet">
	
	<!-- searchbar所需的 -->
	<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />
	
	<!-- 檔案上傳所需的 -->
	<script src="js/bootstrap.file-input.js"></script>
	<script src="js/js.cookie.js"></script>
	
	<link href="css/PersonalPage.css" rel="stylesheet">
	
	<script type="text/javascript">
        var memberId ='${user.memberId}';
        var memberAccount ='${user.memberAccount}';
	</script>
	
<!-- 	個人頁面的jQuery -->
	<script type="text/javascript" src="js/PersonalPage.js"></script>

<style>
	hr{
	border:0; 
	height:1px; 
	background-color:#d4d4d4;
	color:#d4d4d4;
	}
</style>


</head>

<body style="background:rgba(0,0,0,0.05);font-family:Microsoft JhengHei">

	 <jsp:include page="/Header.jsp" />
   
   	<jsp:include page="/Bar.jsp" />
   	
	<div id="gotop" title="回到頁首"><img src="img/up-arrow.png"></div>
	
	
<div class="feed-page">
		
						<div class="latest-news-header">
								<h2 class="latest-news-header--title"></h2>
								<div class="latest-news-header--date"></div>
						</div>	
	
	<div class="feed-page--main">
	
<!-- 		<div class="feed-page--left"> -->
<!-- 			<article class="feed-card feed-card__promoted feed-card__promoted-landscape "> -->
<!-- 				<div class="feed-card--image"> -->
<!-- 					<a  href=""> -->
<!-- 						<img src="img/test.jpg" class="picture-img"/> -->
<!-- 					</a> -->
<!-- 				</div> -->
				
<!-- 				<div class="feed-card--meta"> -->
<!-- 					<time class="feed-card--time">39 minutes ago</time> -->
				
<!-- 					<button type="button" class="feed-card--category" id="video_setting" data-toggle="modal" data-target=""> -->
<!-- 							Setting -->
<!-- 					</button> -->
					
<!-- 				</div> -->
				
<!-- 				<div class="feed-card--info"> -->
<!-- 					<h2 class="feed-card--title"> -->
<!-- 						<a href=""></a> -->
<!-- 					</h2> -->
		
<!-- 					<div class="feed-card--byline" style="float:left"><span>by Steff Yotka</span>  -->
							
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</article>  -->
<!-- 			<hr> -->
<!-- 			</div> -->
			
	</div>	
	  
	<!-- 追蹤列表  -->
	  <div class="row">
		 <div class="col-md-4" style="background-color:white;width: 300px;margin-top:30px">
		          <div class="stream-chat theater-mode-chat-container">
		            
		              <div class="placeholder-for-chat no-chat">
		              
		                <div class="friendslist">
		                
			                <div class="friends">
				                <img src="img/photo.png" />
				            </div >
				            <div style="height: 38px;"><h4> KKSS</h4></div>
						     
						     <div class="friends" >
				                <img src="img/photo.png" />
				            </div >
				            <div style="height: 38px;"><h4> KKSS</h4></div>
				            
				            <div class="friends">
				                <img src="img/photo.png" />
				            </div >
				            <div style="height: 38px;"><h4> KKSS</h4></div>
				            
				            <div class="friends">
				                <img src="img/photo.png" />
				            </div >
				            <div style="height: 38px;"><h4>KKSS</h4></div>
		                  
		                </div>
		              </div>
		            
		          </div>
		  </div>
	  </div>
	  
</div>

<!-- VideoSetting編輯表單 -->

<div class="modal fade" id="VideoSettingForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">影片編輯，刪除</h4>
      </div>
      <div class="modal-body">

			<form id="VideoSetting">
				<fieldset style="text-align:center">
					
					
					<div id="vs_input1" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">影片標題</span>
					  <select id="vs_videoId" class="form-control" name="videoId">
					  		
					  </select>
					</div>
				
					<div id="vs_input2" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">修改標題</span>
					  <input id="vs_videoTitle" type="text" name="videoTitle" class="form-control" placeholder="Write Something" aria-describedby="basic-addon1">
					</div>
				
					<div class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">影片敘述</span>
					  <input id="vs_videoDescription" type="text" name="videoDescription" class="form-control" placeholder="Write Something" aria-describedby="basic-addon1">
					</div>
					
					<input id="vs_prodaction" value="Update" type="hidden" name="prodaction" class="form-control" aria-describedby="basic-addon1">
					
				</fieldset>
			</form>
			<div style="width: 450px; margin:0px auto;padding:5px" class="input-group">
				<input style="width: 440px" id="vs_submit1"  value="Update" class="btn btn-primary btn-small" type="submit">
			</div>
			
			<div style="width: 450px; margin:0px auto;padding:5px" class="input-group">
				<input style="width: 440px;background-color:#d9534f;" id="vs_submit2"  value="Delete" class="btn btn-primary btn-small" type="submit">
			</div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
	
<!-- 再次確認刪除影變 -->

<div id="DeleteVideoModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
    
  
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">確定刪除此影片?</h4>
	        <div style='text-align:center'>
	        	<button  id="dv_submit"  value="送出" type="submit" class="btn btn-default" data-dismiss="modal">確定</button>
	        </div>
	      </div>
	      
	    </div><!-- /.modal-content -->
    
    
    </div>
  </div>
</div>

<jsp:include page="/Footer.jsp" />

</body>
</html>