<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.* , java.lang.* , java.text.*, java.util.*"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Live Show</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />

	<link href="css/SearchPageBorder.css" rel="stylesheet">
	
	<!-- searchbar所需的 -->
	<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />
	
	<!-- 檔案上傳所需的 -->
	<script src="js/bootstrap.file-input.js"></script>
	<script src="js/js.cookie.js"></script>

<!-- Chang URLs to wherever Video.js files will be hosted -->
  <link href="video-js.css" rel="stylesheet" type="text/css">
  	<!-- video.js must be in the <head> for older IEs to work. -->
  <script src="video.js"></script>
  
  <!-- Unless using the CDN hosted version, update the URL to the Flash SWF -->
  <script>
    videojs.options.flash.swf = "video-js.swf";
  </script>
  
  <link href="css/LiveShow.css" rel="stylesheet">

</head>

<script>

<%
SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
Date date = new Date();
%>

$(function(){
	
	$.ajax({
			url:'BroadcastOrderServlet',
			type:'get',
			data:{'memberAccount':'${param.m}',
				  'prodaction':'searchAccount'},
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					//算總實況時間
					var totaltime = <%=date.getTime() %> - new Date(v.broadcastTime).getTime();
					var totalhour = (totaltime/(1000 * 60 )+'').substring(0,(totaltime/(1000 * 60 )+'').indexOf('.'));
					var totalmin = (totaltime%(1000 * 60 )/1000+'').substring(0,(totaltime%(1000 * 60 )/1000+'').indexOf('.'));
					
					$('.live-title').text(v.broadcastTitle);
					$('.stream-title--username span').text(v.memberAccount);
					$('.stream-current-time--data span').text(totalhour+'分'+totalmin+'秒');
					$('video').prepend("<source src='" + v.server + "' type='rtmp/mp4'/>");
		 		});
			}		
		})
	
})
</script>

<body style="margin:0px;font-family:Microsoft JhengHei">

	<jsp:include page="/Header.jsp" />
   
   	<jsp:include page="/Bar.jsp" />
   	

<div class="stram-main">
	<div class="stram-left">
	
		<header class="stream-header" style="display: block;">
        <div class="span-8">
          <div class="stream-title clearfix" >
            <div class="stream-title--image">
              
                <img src="img/photo.png" />
              
            </div >
            <div class="stream-title--title" >
              	<h2  class="live-title " data-toggle="tooltip" data-placement="bottom"></h2>
            </div>
            <div class="stream-title--username">
              <p style="display: inline-block;color: #f1f1f1;font-weight: 300;line-height: 19px;">with <span></span></p>
            </div>
          </div>


          	<div class="stream-next">
          
			</div>
			
	          <div class="stream-current-time">
	            
	              <p class="stream-current-time--data">Total Stream Time | <span></span></p>
	            
	          </div>
		            <div class="stream-buttons js-showtour no-throbber">
	            
	            
	          		</div>
		  
        </div>

      </header><!-- End Stream header -->
		
		
		 <!-- Video and chat -->
      <div class="stream-video-chat">
        <div class="span-8">
          <div class="stream-video theater-mode-container">
          
            <video id="video_stream" class="video-js vjs-default-skin" controls preload="none" width="100%" height="400"
	      	data-setup='{"techOrder": ["html5", "flash"] }' preload="auto" poster="">
	      	
	      			<source src="rtmp://itvvm.cloudapp.net/live/5ASC9R" type='rtmp/mp4'/>
<!-- 	      		<source src="../mp4/Attack.mp4" type='video/mp4' /> -->
	      	
	      	</video>

          <div class="stream-video-info clearfix">
            <div class="stream-video-info--buttons clearfix">
              
                
                  
                    <a href="#" id="unfollow" data-ajax-action=""  style="display: block;">放棄追蹤</a>
                    <a href="#" id="follow" data-ajax-action=""   style="display: none;">追蹤我</a>
                  
                	<a href="#" id="unfollow" data-ajax-action=""  style="display: none;">刪除實況列表</a>
                    <a href="#" id="follow" data-ajax-action=""  style="display: block;">加入實況列表</a>
					
              
            </div>

            <div class="stream-video-info--counts">
              
              <span style="color:#6c6e71" data-toggle="tooltip" data-placement="bottom" title="Followers"><i class="fa fa-heart" style="color: #a6a8ab; font-size: 18px;"></i> <span id="followers_count"></span></span>
              <span style="color:#6c6e71" data-toggle="tooltip" data-placement="bottom" title="View count"><span class="icon-span-contact"></span> <span id="views_overall"></span></span>
              <span style="color:#6c6e71" data-toggle="tooltip" data-placement="bottom" title="Live viewers"><span class="icon-red-eay"></span> <span id="views_live"></span></span>
            </div>
          </div>
          </div>
        </div>

        <div class="span-4" style="background-color:white">
          <div class="stream-chat theater-mode-chat-container">
            
              <div class="placeholder-for-chat no-chat">
                <div class="box-container">
                  <h4>SIGN UP NOW TO:</h4>
                  <ul>
                  
                  <li>- JOIN Q&amp;A LIVE CHAT -</li><li>- CONTACT STREAMER -</li><li>- SEND PRIVATE MESSAGES -</li><li>- START BROADCASTING -</li>
                  </ul>
                  
                    
                    <a class="subscribe-button" href="/accounts/login/">Log in</a>
                    <a class="subscribe-button" href="/accounts/signup/">Sign up</a>
                  
                </div>
              </div>
            
          </div>
        </div>
      
      </div><!-- End Video and chat -->
      
	</div>
</div>
<jsp:include page="/Footer.jsp" />
</body>
</html>