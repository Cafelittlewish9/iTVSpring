<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<!-- <script src="js/bootstrap.js"></script> -->
<!-- <link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" /> -->

	<link rel="stylesheet" href="css/Bar.css">
	<script src="js/Bar.js"></script>


<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<!-- <script src="js/bootstrap.js"></script> -->

<!-- <link rel="stylesheet" href="css/bootstrap.min.css"> -->

<!-- <link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" /> -->


<!-- </head> -->

<body style="background:rgba(0,0,0,0.05)">

		<div id="mwt_mwt_slider_scroll">
		<div id="mwt_fb_tab">
			<span class="glyphicon glyphicon-cog"></span>	
		</div>
		<div id="mwt_slider_content">
				<br>
				<br>
				
				<c:if test="${!empty user}">
					<ul>
						<a href="PlayList.jsp"><span class="glyphicon glyphicon-list-alt" > <span style="font-family:Microsoft JhengHei;">影片節目列表</span></span></a>
					</ul>
				</c:if>
				
				<ul>
					<a href="Videos.jsp"><span class="glyphicon glyphicon-hd-video" > <span style="font-family:Microsoft JhengHei;">熱門影片</span></span></a>
				</ul>	
				
				
				<ul>
					<a href="LiveStream.jsp"><span class="glyphicon glyphicon-facetime-video" > <span style="font-family:Microsoft JhengHei;">實況直播</span></span></a>
				</ul>
				<c:if test="${!empty user }">
				<ul>
					<a href="Cloud.jsp"><span class="glyphicon glyphicon-cloud" > <span style="font-family:Microsoft JhengHei;">雲端硬碟</span></span></a>
				</ul>	
				</c:if>
				<ul>
					<a href="#"><span class="glyphicon glyphicon-globe" > <span style="font-family:Microsoft JhengHei;">ITV論壇</span></span></a>
				</ul>
				
				
				
				<br><br><br><br><br><br><br><br><br><br><br>
				<ul>
					<a href="#"><span class="glyphicon glyphicon-info-sign" > <span style="font-family:Microsoft JhengHei;">ITV公告</span></span></a>
				</ul>	
		
<!-- 				<div style="padding:10px 0px 10px 0px"><button type="button" class="btn btn-default">Default button</button></div> -->
<!-- 				<button type="button" class="btn btn-default">Default button</button> -->
<!-- 				<button type="button" class="btn btn-default">Default button</button> -->
<!-- 				<button type="button" class="btn btn-default">Default button</button> -->
<!-- 				<button type="button" class="btn btn-default">Default button</button> -->

<!-- 			<iframe src="http://www.facebook.com/plugins/likebox.php?id=208757309728&amp;width=200&amp;connections=10&amp;stream=false&amp;header=false&amp;height=270" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:200px; height:270px;" allowTransparency="true"></iframe> -->
		</div>
		</div>
<!-- </body> -->

<!-- </html> -->