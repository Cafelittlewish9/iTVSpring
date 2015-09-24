<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.* , java.lang.Math"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link href="css/SearchPageBorder.css" rel="stylesheet">

<!-- searchbar所需的 -->
<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />

<!-- 檔案上傳所需的 -->
<script src="js/bootstrap.file-input.js"></script>
<script src="js/js.cookie.js"></script>

	<%
	String name = new String();
	name = request.getParameter("filename");
	%>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><%=name%></title>

 	<!-- Chang URLs to wherever Video.js files will be hosted -->
  <link href="video-js.css" rel="stylesheet" type="text/css">
  	<!-- video.js must be in the <head> for older IEs to work. -->
  <script src="video.js"></script>
  
  <!-- Unless using the CDN hosted version, update the URL to the Flash SWF -->
  <script>
    videojs.options.flash.swf = "video-js.swf";
  </script>
  
  <link href="css/PlayVideo.css" rel="stylesheet">
  
  <script>
  $(function(){
	  $('#textarea').focus(function(){
			$("#comment_button1").css('display','block');
		});
	  $('#comment_cancel').click(function(){
			$("#comment_button1").css('display','none');
			$("#textarea").val("");
		});
	  
	  
	  $('#textarea').keydown(function(){
		  var len = $(this).val().length;
		  if (len > 0) {
			  console.log(len);
			  $("#comment_submit").removeAttr('disabled');
		  }else{
			  console.log(len);
			  $("#comment_submit").attr('disabled','disabled');
		  }
		});
	  
	  
// 	  判斷使用者是否追加過影片
		$.ajax({
			  url:'ShowServlet',
			  type:'get',
			  data:{"memberId":'${user.memberId}',
				  	"website":'${param.filename}',
				  	'prodaction':'Check'},
			  dataType:"json",
			  success:function(data){
				  if(data==null){
					$('.addvideo').css({'display':'block'});
				  }else{
					$('.addvideo').css({'display':'none'});
				  }
			  }
		  })

	  
//		追加影片
	  $('.addvideo').click(function(){
		  $.ajax({
			  url:'ShowServlet',
			  type:'get',
			  data:{"memberId":'${user.memberId}',
				  	"website":'${param.filename}',
				  	'prodaction':'Insert'},
			  dataType:"json",
			  success:function(data){
					  $('#addvideoModal').modal('show');
	              		//一秒半後關閉成功畫面
	                	 setTimeout(function() {
	                     	$('#addvideoModal').modal('hide');
	                     }, 2000);
				  $.each(data,function(i,v){
					  console.log(v.website);
				  });
			  }
		  })
		});
	  
// 		影片回覆
	  $('#comment_submit').click(function(){
		  $.ajax({
			  url:'ReplyArticleServlet',
			  type:'post',
			  data:{"replyContent":$('#textarea').txt(),
				  	"memberId":4,
				  	"articleId":'????'},
			  dataType:"json",
			  success:function(data){
				  
				  
			  }
		  })
		});
	  
// 	  推薦影片畫面
	  $.ajax({
			url:'VideoServlet',
			type:'get',
			data:{'videoName':'${param.filename}',
				  'prodaction':'searchVideoName'},
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					console.log(v.videoClassName);
					
					
					 $.ajax({
						url:'VideoServlet',
						type:'get',
						data:{'videoTitle':null},
						dataType:"json",
						success:function(data){
							$.each(data,function(m,n){
								if(n.videoClassName==v.videoClassName){
									
				 					var vt = n.videoTitle.substring(0,25);
				 					var vd = n.videoDescription.substring(0,25);
				 					if(vd==null){
				 						vd="";
				 					}
				 					//順序=最新-舊
				 					if(10>=data.length-m){
				 					$('#P_div1').prepend("<div class='service' style='margin: 10px;padding-left: 10px;padding-right: 10px;'>"+"<div class='col-md-5' style='padding:5px 5px 0px 0px;'>"+
				 							     "<a href='PlayVideo.jsp?filename="+n.videoName + "'>"+
				 							     "<img src='../img/"+n.videoName+".jpg'  style='width:120px;height:68px'/></a></div>"+
				 							     "<div class='col-md-7' style='padding:5px 0px 0px 10px'>"+
				 							     "<a href='PlayVideo.jsp?filename="+n.videoName+"'>"+
				 							     "<span class='font-right'><div style='width:130px; display:inline-block;margin-left: 10px;' class='font-right'>"+vt+"</div></span></a><br>"+
				 							     "<span class='font-right'><div style='width:130px; display:inline-block;margin-left: 10px;' class='font-right'><p>"+vd+"</p></div></span></a><br>"+
				 							     "<div style='text-align:-webkit-auto;margin-left: 10px;width: 100%;'><span class='font-right' id='v_watchtimes'>"+"觀看次數:"+n.videoWatchTimes+ "views</span></div><br>"+
				 							     "</div>");
				 					}
								}
								
							});
						}
					 });

				});
			}
		})
		
		
	  
// 		會員資訊
		$.ajax({
			url:'VideoServlet',
			type:'get',
			data:{'videoName':'${param.filename}',
				  'prodaction':'searchVideoName'},
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					console.log(v.videoName);
						$('#info h4').append("<h3><span>"+v.videoTitle+"</span></h3>");
						$('#info h5').append("<span style='color:#767676;'>"+v.memberAccount+"</span>");
						$('video').prepend("<source src='../mp4/"+v.videoName+".mp4' type='video/mp4' />");
				});	
			}
		})
	  
		$('#selectview').change(function(){
    	var view =  $('#selectview>:selected').val(); //$(this).val();
    	
    		$.ajax({
    			url:'ReplyArticleServlet',
    			type:'get',
    			data:{'videoName':'${param.filename}'},
    			dataType:"json",
    			success:function(data){
    				if(view =='new'){
    				}else{
    				}
    			}
    		});
    		

    	
    	})
		
		
  })
  </script>

 
</head>
<body style="background:rgba(0,0,0,0.05);font-family:Microsoft JhengHei">

   <jsp:include page="/Header.jsp" />
   
   <jsp:include page="/Bar.jsp" />
   
   
   
<div class="row" style="width:1140px;margin-left:auto;margin-right:auto">
	
			<!-- 	影片畫面	-->
			<div class="row" style="margin-top:100px;width:800px;float:left">
		
				<div class="col-md-6 col-md-offset-1" style="padding-left: 1px;margin-left: 7.2222%; width: 92.333333%;">
			
				   <video id="video_1" class="video-js vjs-default-skin" controls preload="none" width="100%" height="400"
				      data-setup='{"techOrder": ["html5", "flash"] }' preload="auto" poster="" autoplay="autoplay">
						
<%-- 				    	<source src="../mp4/<%=name%>.mp4" type='video/mp4' /> --%>
				    	
					</video>
		
			</div>
			
			
			<!-- 	會員資訊	-->
			<div id="info" class="col-md-6 col-md-offset-1" style="margin-top:10px;background:white;margin-left: 7.2222%;width: 90.333333%;">
		
									<div style="height:68px;text-align:left;margin:0px auto">
										<div style="padding-top:5px">
											<h4>
<!-- 												<span style="color:#767676;">v.videoName</span> -->
											</h4>
										</div>
									</div>
									
									<div style="height:58px;text-align:left;margin:0px auto">
										<div style="padding-top:5px">
											<div class="row">
												<div class="col-lg-1" style="top:8px">
													<img src="img/photo.png" style="width:50px;height:50px"/>
												</div>
												<div class="col-lg-4">
													<h5>
<!-- 														<span style="font-family:Microsoft JhengHei;color:#767676;">v.memberId</span> -->
													</h5>
													<p>
													  <button type="button" class="btn btn-primary btn-xs">追蹤我</button>
													</p>
												</div>
		
											</div>
										</div>
									</div>
									<hr style="box-shadow:1px 1px 1px #cccccc;width:98%;margin:10px">
									
									<div style="height:34px;text-align:left;margin:0px auto">
										<div class="stream-video-info--buttons">
											<a href="#" class="addvideo" >影片追加</a>
											<a href="#">讚</a>
											<a href="#">檢舉</a>
											
										</div>
									</div>
		
			</div>
			
			
			<!-- 	留言板 	-->
			<div class="col-md-6 col-md-offset-1" style="margin:10px 0px 15px 0px;background:white;margin-left: 7.2222%;width: 90.333333%;">
				<div >
				
					<div style="height:68px;text-align:left;margin:0px auto">
						<div style="padding-top:5px">
							<textarea id="textarea" class="form-control" rows="3" placeholder="新增留言..."></textarea>
						</div>
					</div>
				
					<div id="comment_button1" style="padding-top:20px;text-align:right;margin:0px auto;display:none;">
						<button  id="comment_submit" type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#" disabled="disabled">Submit</button>
						<button  id="comment_cancel" type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#">Cancel</button>
					</div>
					
					<div id="comment_button2" style="padding:20px 0px 10px 0px ;height:48px;width:20%;text-align:left;">
						<select id="selectview" class="form-control">
							<option  value="new">由新到舊</option>
							<option  value="old">由舊到新</option>
						</select>
					</div>
					
									<div style="height:58px;text-align:left;margin:0px auto">
									
										<div id ="comment_page" style="padding-top:15px">
										
											<div class="row" style="margin-bottom:15px">
												<div class="col-lg-1" style="top:8px">
													<img src="img/photo.png" style="width:50px;height:50px"/>
												</div>
												<div class="col-lg-4">
													<h5>
														<span style="color:#767676;">會員XXX</span>
													</h5>
													<p>
													  <span style="color:#767676;">留言........</span>
													</p>
												</div>
											</div>
											
											<div class="row" style="margin-bottom:15px">
												<div class="col-lg-1" style="top:8px">
													<img src="img/photo.png" style="width:50px;height:50px"/>
												</div>
												<div class="col-lg-4">
													<h5>
														<span style="color:#767676;">會員XXX</span>
													</h5>
													<p>
													  <span style="color:#767676;">留言........</span>
													</p>
												</div>
											</div>
											
											<div class="row" style="margin-bottom:15px">
												<div class="col-lg-1" style="top:8px">
													<img src="img/photo.png" style="width:50px;height:50px"/>
												</div>
												<div class="col-lg-4">
													<h5>
														<span style="color:#767676;">會員XXX</span>
													</h5>
													<p>
													  <span style="color:#767676;">留言........</span>
													</p>
												</div>
											</div>
											
										</div>
									</div>
									
				</div>
			</div>
		
		</div>

	
		<!-- 	右邊列表	-->
	
		<div class="col-md-3" style="background:white;margin: 100px 0px 20px 5px;width: 29%;">
		
								<div style="height:68px;text-align:left;margin:0px auto">
									<div style="padding:15px 0px 0px 10px">
										<h4>
											<span >相關類型影片:</span>
										</h4>
									</div>
								</div>
			
							<div id="P_div1">
							
							</div>
				
			</div>
</div>

<!-- add成功Modal -->

<div id="addvideoModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
    
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">追加影片成功！</h4>
      </div>
    </div><!-- /.modal-content -->
    
    
    </div>
  </div>
</div>

<jsp:include page="/Footer.jsp" />
</body>
</html>