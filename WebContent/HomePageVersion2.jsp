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
<!-- <script src="//code.jquery.com/jquery-1.11.3.min.js"></script> -->
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />

 	<!-- Chang URLs to wherever Video.js files will be hosted -->
  <link href="video-js.css" rel="stylesheet" type="text/css">
  	<!-- video.js must be in the <head> for older IEs to work. -->
  <script src="video.js"></script>
  
  <!-- Unless using the CDN hosted version, update the URL to the Flash SWF -->
  <script>
    videojs.options.flash.swf = "video-js.swf";
  </script>

<script type="text/javascript">
var divs = document.getElementById("div1");


$(function(){
		$.ajax({
			url:'VideoServlet',
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					//順序=最新-舊
					$('#tr1').prepend("<td style='margin:20px 20px 20px 30px ; float:left ;  list-style-type:none;' >")
					$('#tr1 td:first').append("<a href='PlayVideo.jsp?filename="+v.videoName + "'><img src='../img/"+v.videoName +".jpg'/></a><br>");
					$('#tr1 td:first').append("<a style='text-decoration:none;color:#167ac6;font-weight: 600;font-family:sans-serif; font-size:15px' href='PlayVideo.jsp?filename="+v.videoName+"'><span>"+v.videoName+"</span></a><br>");
					$('#tr1 td:first').append("<span style='font-weight: 550;font-family:Microsoft JhengHei; font-size:12px'>"+v.videoWatchTimes+ "views</span><br></td>");
					
				});
			}
		})
})
</script>

</head>

<body style="background:rgba(0,0,0,0.05)">

	
	
    <jsp:include page="/Header.jsp" />
   
   	<jsp:include page="/Bar.jsp" />
   
   	<jsp:include page="/VideoUpload.jsp" />
   
   	<jsp:include page="/CreateLive.jsp" />
    
	<div style="margin:100px 0px 50px 0px; ">
	<center>
	    <table>
	  <tr>
	      <td>
	  <video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="640" height="360"
	      data-setup='{"techOrder": ["html5", "flash"] }' preload="auto" poster="">
	    <!--<source src="Attack.mp4" type='video/mp4' />-->
	    <%
		String PATH = "C:\\Spring\\workspace\\ITV\\WebContent\\Video";
		File fi = new File(PATH) ;
		String[] ff = fi.list();
		
	// 	int ii = (int)Math.floor(Math.random()*(ff.length)+1);
		%>
		<%=ff[0]%>
	    <source src="Video/<%=ff[0]%>" type='video/mp4' />
	
	      <!--<source src="rtmp://114.32.17.96/live/yosuke" type='rtmp/mp4' />-->
	
	  </video>
	      </td>
	   </tr>
		</table>
	</center> 
	</div>

	<hr style="box-shadow:1px 1.5px 3px #cccccc;">

<center>
        <div style="background:white; width:93.5%; margin-top:40px">
			<table>
				<tr id="tr1">
					<div style="height:40px;text-align:left;">
					<span style="line-height:50px;margin-left:40px;font-family:Microsoft JhengHei;color:#767676;">あなたへのおすすめ</span>
					</div>
<!-- 					<td id="td1" style="margin:20px 20px 20px 30px ; float:left ;  list-style-type:none;" > -->


<!-- 					</td> -->
				</tr>
			</table>
		</div>
</center>

	<jsp:include page="/Footer.jsp" />
	
</body>
</html>