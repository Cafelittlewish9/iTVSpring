<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.* , java.lang.Math"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
	<link href="css/SearchPageBorder.css" rel="stylesheet">

	<!-- searchbar所需的 -->
	<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />
	
	<!-- 檔案上傳所需的 -->
	<script src="js/bootstrap.file-input.js"></script>
	<script src="js/js.cookie.js"></script>
	
	<!-- 影片排版的css -->
	<link href="css/SearchPage.css" rel="stylesheet">


<script type="text/javascript">
	


$(function(){
		$.ajax({
			url:'VideoServlet',
			type:'get',
 			data:{'videoTitle':'${param.search}'},
			dataType:"json",
			success:function(data){
				console.log(data);
				$('.searchTitle').append('${param.search}');
				$.each(data,function(i,v){
					//順序=最新-舊
					
					$('#s_div1').prepend("<td>")
					$('#s_div1').prepend("<div class='service'>"+
							"<a href='PlayVideo.jsp?filename="+v.videoName + "'>"+
							"<img src='../img/"+v.videoName+".jpg' width='200px'/></a><br>"+
							"<a href='PlayVideo.jsp?filename="+v.videoName+"'>"+
							"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>"+v.videoTitle.substring(0,25)+"</div></span></a><br>"+
							"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>"+v.videoDescription.substring(0,25)+"</p></div></span></a><br>"+
							"<span id='v_watchtimes' >"+v.videoWatchTimes+ "views</span><br></td>"+
							"</div>");
					
				});
			}
		})
})
</script>
  
  
  <link href="css/SearchPageBorder.css" rel="stylesheet">

</head>

<body style="background:rgba(0,0,0,0.05);font-family:Microsoft JhengHei">

	<jsp:include page="/Header.jsp" />
   
   	<jsp:include page="/Bar.jsp" />
   	
   	

<center>
        <div style="background:white; width:73%; margin:80px 0px 20px 0px">
			
					<div style="height:40px;text-align:center;">
						<h4><span class="searchTitle">搜尋：</span></h4>
					</div>
					
					<div id="s_div1" style="margin-right: 60px;">
					
					</div>
			
		</div>
</center>

	<jsp:include page="/Footer.jsp" />
</body>
</html>