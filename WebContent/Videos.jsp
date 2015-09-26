<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.* , java.lang.Math"%>
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
  
	<!--   影片排版的css  -->
  <link rel="stylesheet" type="text/css" media="screen" href="css/Videos.css" />
	<!--   轉換類型按鈕的jquery -->
  <script src="js/Videos.js"></script>
  


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
			    
			    <li >
			      <a href="LiveStream.jsp">實況</a>
			    </li>
			    <li class="active">
			      <a href="#">影片</a>
			    </li>
			    
			  </ul>
			</div>
			
			<div class="tab-content">
				<div class="tab-pane active" id="livestreams">
					<div class="browse-main-items">
						<div id="livestream_items" class="browse-main-videos clearfix">
						
 							
									 <div class="browse-main-filters">
							            <button type="button" value="" id="browse_type_any" class="browse-main-filters--button js-browse-product-type active">All</button>
							            <button type="button" value="MusicVideo" id="browse_type_MusicVideo" class="browse-main-filters--button js-browse-product-type ">音樂影片</button>
							            <button type="button" value="News" id="browse_type_News" class="browse-main-filters--button js-browse-product-type ">新聞</button>
							            <button type="button" value="Drama" id="browse_type_Drama" class="browse-main-filters--button js-browse-product-type ">戲劇</button>
							            <button type="button" value="Animation" id="browse_type_Animation" class="browse-main-filters--button js-browse-product-type ">動畫</button>
							            <button type="button" value="Daily" id="browse_type_Daily" class="browse-main-filters--button js-browse-product-type ">生活</button>
							            <button type="button" value="Interest" id="browse_type_Interest" class="browse-main-filters--button js-browse-product-type ">趣味</button>
							            <button type="button" value="Other" id="browse_type_Other" class="browse-main-filters--button js-browse-product-type ">其他</button>
							          </div>
				
									<div id="videos_div1" style="width: 950px">
										<c:forEach var="list" items="${videolist}">
											<div class='service'>
												<a href='PlayVideo.jsp?filename=${list.videoName}'> <img src='../img/${list.videoName}.jpg' width='200px' /></a><br> 
												<a href='PlayVideo.jsp?filename=${list.videoName}'> <span class='font-right'> <div style='width: 200px; display: inline-block' class='font-right'>${list.videoTitle}</div></span></a><br> 
												<span class='font-right'><div style='width: 200px; display: inline-block' class='font-right'><p>${list.videoDescription}</p></div></span></a><br> 
												<span id='v_watchtimes'>${list.videoWatchTimes} views</span><br>
											</div>
										</c:forEach>
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