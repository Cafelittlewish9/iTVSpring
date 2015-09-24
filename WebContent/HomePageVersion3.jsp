<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.* , java.lang.Math"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITV</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link href="css/SearchPageBorder.css" rel="stylesheet">

<!-- searchbar所需的 -->
<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />

<!-- 檔案上傳所需的 -->
<script src="js/bootstrap.file-input.js"></script>
<script src="js/js.cookie.js"></script>

<!-- 影片播放所需的 -->
 	<!-- Chang URLs to wherever Video.js files will be hosted -->
  <link href="video-js.css" rel="stylesheet" type="text/css">
  	<!-- video.js must be in the <head> for older IEs to work. -->
  <script src="video.js"></script>
  
  <!-- Unless using the CDN hosted version, update the URL to the Flash SWF -->
  <script>
    videojs.options.flash.swf = "video-js.swf";
  </script>

  <link href="css/HomePage.css" rel="stylesheet">

<script type="text/javascript">

$(function(){
// 		$.get("VideoServlet",{"videoTitle":"girl"},function(data){
// 			console.log(data[0]);
// 		})
	
	
		$.ajax({
			url:'VideoServlet',
			type:'get',
 			data:{'videoTitle':null},
			dataType:"JSON",
			success:function(data){
				$.each(data,function(i,v){
					//順序=最新-舊
					var vt = v.videoTitle.substring(0,25);
					var vd = v.videoDescription;
					if(vd==null){
						vd="";
					}
					//最新10筆
					if(10>=data.length-i){
					$('#h_div1').prepend("<td>")
					$('#h_div1').prepend("<div class='service'>"+
							"<a href='PlayVideo.jsp?filename="+v.videoName + "'>"+
							"<img src='../img/"+v.videoName+".jpg' width='200px'/></a><br>"+
							"<a href='PlayVideo.jsp?filename="+v.videoName+"'>"+
							"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>"+vt+"</div></span></a><br>"+
							"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>"+vd+"</p></div></span></a><br>"+
							"<span id='v_watchtimes'>"+v.videoWatchTimes+ "views</span><br></td>"+
							"</div>");
					}
				});
			}
		})
})
alert('${sessionScope.user}');
</script>

</head>

<body style="background:rgba(0,0,0,0.05);font-family:Microsoft JhengHei">

	
	
    <jsp:include page="/Header.jsp" />
   
   	<jsp:include page="/Bar.jsp" />

    
 <section class="video-home">
    <div class="container-fluid">
      <div class="row" style="margin: 0;">
        
          <div class="col-md-offset-1 col-lg-offset-2 col-md-10 col-lg-8 padding-mod">
            <div class="video-home--player">
              <video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="100%" height="465"
			      data-setup='{"techOrder": ["html5", "flash"] }' preload="auto" poster="">
			    <!--<source src="Attack.mp4" type='video/mp4' />-->
			   
			      <source src="rtmp://itvvm.cloudapp.net/live/kkk" type='rtmp/mp4' />
			
			  </video>
            </div>
          </div>
        
      </div>
    </div>
  </section><!-- End Video slider -->


<center>
        <div style="background:white; width:93%; margin:50px 0px 50px 0px">
        
			
					<div style="height:40px;text-align:left;;text-align:center;">
						<h4><span style="line-height:50px;margin-left:40px;font-family:Microsoft JhengHei;color:#767676;">最新TOP10</span></h4>
					</div>
					
					
					<div id="h_div1" style="margin-right: 60px;">
						
					</div>
			
		</div>
</center>

	<jsp:include page="/Footer.jsp" />
	
</body>
</html>