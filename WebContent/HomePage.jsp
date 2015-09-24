<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.* , java.lang.Math"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITV</title>

 	<!-- Chang URLs to wherever Video.js files will be hosted -->
  <link href="video-js.css" rel="stylesheet" type="text/css">
  	<!-- video.js must be in the <head> for older IEs to work. -->
  <script src="video.js"></script>
  
  <!-- Unless using the CDN hosted version, update the URL to the Flash SWF -->
  <script>
    videojs.options.flash.swf = "video-js.swf";
  </script>
  
  <style>
	   header {
	   position:fixed;
	   top:0;
	   z-index:1;
	   width:100%;
	    height: 40px;
	    background: rgba(0,0,0,0.7);
	    font-family: 'Rokkitt', serif;
	    color: #FFF;
	    padding-top: 10px;
	    text-align: center;
	    vertical-align: middle;
		}
		header h1 {
 			line-height:20px;
		    margin: 0;
		    padding-top:-20px;
			margin-left:20px;
		    padding: 0;
		    display: block;
		    text-transform: uppercase;
		    font-size: 24px;
		    text-shadow: 0 4px 3px rgba(0,0,0,0.4),
		    0 8px 13px rgba(0,0,0,0.1),
		    0 18px 23px rgba(0,0,0,0.1);
		}
				
		body{
		    margin: 0;
		}
		* { -moz-box-sizing: border-box; -webkit-box-sizing: border-box; box-sizing: border-box; }
  </style>
  
</head>
<body style="background:rgba(0,0,0,0.05)">

    <header>
    	<div style="float:left;">
       	<h1><a href="HomePage.jsp"><img src="logo/itv.jpg" /></a></h1>
        </div>
			<div style="text-align:right ;overflow:auto;">
				<a href="" style="text-decoration:none;color:white"><span style="line-height:20px;margin:10px;">SingUp</span></a>
				<a href="" style="text-decoration:none;color:white"><span style="line-height:20px;margin:10px;">LogIn</span></a>
			</div>
			
    </header>
   
<!-- 	<header style="background-color:black; height:40px ;"> -->
<!-- 			<div class="div" style="float:left" > -->
<!-- 					<a href="HomePage.jsp"><img src="logo/itv-cover3.jpg" /></a> -->
<!-- 			</div> -->
<!-- 			<div style="text-align:right ;overflow:auto;"> -->
<!-- 				<a href="" style="text-decoration:none;color:white"><span style="line-height:40px;margin:10px;">SingUp</span></a> -->
<!-- 				<a href="" style="text-decoration:none;color:white"><span style="line-height:40px;margin:10px;">LogIn</span></a> -->
<!-- 			</div> -->
<!-- 	</header> -->
	
	
<div style="margin:60px 0px 20px 0px; ">
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
	
		<sql:setDataSource var="ds"
	            dataSource="jdbc/DB"
	        />
        
       	<sql:query var="rs" dataSource="${ds}">
            select TOP 80percent * from video  order by newid()
        </sql:query>
        
        
		<center>
        <div style="background:white; width:93.5%; margin-top:20px">
 		<table>
 		<tr>
		<div style="height:40px;text-align:left;">
		<span style="line-height:50px;margin-left:40px;font-family:Microsoft JhengHei;color:#767676;">あなたへのおすすめ</span>
		</div>
        <c:forEach var="row" items="${rs.rows}">
     		<td style="margin:20px 20px 20px 30px ; float:left; list-style-type:none;" >
     			 <a href="PlayVideo.jsp?filename=<c:out value='${row.videoname}' />"><img src="../img/<c:out value='${row.videoname}' />.jpg"/></a><br>
     			 <a style="text-decoration:none;color:#167ac6;font-weight: 600;font-family:sans-serif; font-size:15px" href="PlayVideo.jsp?filename=<c:out value='${row.videoname}' />"><span><c:out value='${row.videoname}' /></span></a><br>
     			 <span style="font-weight: 550;font-family:Microsoft JhengHei; font-size:12px"><c:out value='${row.watch}' /> views</span>
			</td>
<%--                <li><c:out value='${row.videopath}' /></li>  --%>
		
         </c:forEach>
         </tr>
         </table>
         </div>
		</center>


</body>
</html>