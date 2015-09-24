<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.* , java.lang.* , java.text.*, java.util.*"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LiveStram and Videos</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
	<link href="css/SearchPageBorder.css" rel="stylesheet">

	<!-- searchbar所需的 -->
	<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />
	
	<!-- 檔案上傳所需的 -->
	<script src="js/bootstrap.file-input.js"></script>
	<script src="js/js.cookie.js"></script>

	<!-- LiveStram and Videos所需的 -->
	<link rel="stylesheet" type="text/css" media="screen" href="css/LiveStream-Videos.css" />
	
 	<!-- Chang URLs to wherever Video.js files will be hosted -->
  <link href="video-js.css" rel="stylesheet" type="text/css">
  	<!-- video.js must be in the <head> for older IEs to work. -->
  <script src="video.js"></script>
  
  <!-- Unless using the CDN hosted version, update the URL to the Flash SWF -->
  <script>
    videojs.options.flash.swf = "video-js.swf";
  </script>

	<link href="css/LiveStream.css" rel="stylesheet">
	
	
<script type="text/javascript">
<%
SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
Date date = new Date();
%>

$(function(){
		$.ajax({
			url:'BroadcastOrderServlet',
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					
					//算總實況時間
					var totaltime = <%=date.getTime() %> - new Date(v.broadcastTime).getTime();
					var totalhour = (totaltime/(1000 * 60 )+'').substring(0,(totaltime/(1000 * 60 )+'').indexOf('.'));
					var totalmin = (totaltime%(1000 * 60 )/1000+'').substring(0,(totaltime%(1000 * 60 )/1000+'').indexOf('.'));
					
					//順序=最新-舊
					$('#s_div1').prepend("<td >")
					$('#s_div1').prepend("<div class='service'>"+
							"<a href='LiveShow.jsp?m="+v.memberAccount + "'>"+
							"<img src='img/test.jpg' width='200px'/></a><br>"+
							"<a href='LiveShow.jsp?m="+v.memberAccount+"'>"+
							"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>"+v.broadcastTitle.substr(0,25)+"</div></span></a><br>"+
							"<span class='font-right' id='v_uploadtime'>總實況："+totalhour+'分'+totalmin+'秒'+"</span><br>"+
							"<span class='font-right' id='v_watchtimes'>"+v.broadcastWatchTimes+ "views</span><br></td>"+
							"</div>");
					
				});
			}
		})
})
</script>

</head>



<body style="background:rgba(0,0,0,0.05);font-family:Microsoft JhengHei">

    <jsp:include page="/Header.jsp" />
   
   	<jsp:include page="/Bar.jsp" />
   	
   	 
   	<!-- Content -->
  <section class="content">

	    <!-- Page header -->
	    <section class="browse-header">
	      
	    </section><!-- End Page header -->
    
    <!-- Page wrapper -->
    <div class="browse-wrapper">

      <!-- Main part of page -->
      <section class="browse-main">
        
			<div class="browse-main-header-updated">
			
			  <ul class="browse-main-nav nav nav-tabs clearfix">
			    
			    <li  class="active" >
			      <a href="#">實況</a>
			    </li>
			    <li >
			      <a href="Videos.jsp">影片</a>
			    </li>
			    
			  </ul>
			</div>
			
			<div class="tab-content">
				<div class="tab-pane active" id="livestreams">
					<div class="browse-main-items">
						<div id="livestream_items" class="browse-main-videos clearfix">
						
									<div id="s_div1" >
									
									</div>
								
						</div>
					</div>
				</div>
			</div>
			
	   </section>
	   
   	</div>
   	
  </section> 	


<jsp:include page="/Footer.jsp" />
</body>
</html>