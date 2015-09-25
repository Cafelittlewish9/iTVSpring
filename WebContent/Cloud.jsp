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

<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-rc1/css/bootstrap.min.css" rel="stylesheet"/>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-rc1/js/bootstrap.min.js"></script>

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
  <link href="css/cloud.css" rel="stylesheet">
<!--   <link href="css/SearchPageBorder.css" rel="stylesheet"> -->
  <script src="js/cloudFileupLoad.js"></script>
  
  
  <style>

	.clearfix{*zoom:1;}
	.clearfix:before,
	.clearfix:after{display:table;content:"";line-height:0;}
	.clearfix:after{clear:both;}
	.hide-text{font:0/0 a;color:transparent;text-shadow:none;background-color:transparent;border:0;}
	.input-block-level{display:block;width:100%;min-height:30px;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;}
	.btn-file{overflow:hidden;position:relative;vertical-align:middle;}
	.btn-file>input{position:absolute;top:0;right:0;margin:0;opacity:0;filter:alpha(opacity=0);transform:translate(-300px, 0) scale(4);font-size:23px;direction:ltr;cursor:pointer;}
	.fileupload{margin-bottom:9px;}
	.fileupload .uneditable-input{display:inline-block;margin-bottom:0px;vertical-align:middle;cursor:text;}
	.fileupload 
	.thumbnail{overflow:hidden;display:inline-block;margin-bottom:5px;vertical-align:middle;text-align:center;}.fileupload .thumbnail>img{display:inline-block;vertical-align:middle;max-height:100%;}
	.fileupload .btn{vertical-align:middle;}
	.fileupload-exists 
	.fileupload-new,
	.fileupload-new 
	.fileupload-exists{display:none;}
	.fileupload-inline 
	.fileupload-controls{display:inline;}
	.fileupload-new 
	.input-append 
	.btn-file{-webkit-border-radius:0 3px 3px 0;-moz-border-radius:0 3px 3px 0;border-radius:0 3px 3px 0;}
	.thumbnail-borderless 
	.thumbnail{border:none;padding:0;-webkit-border-radius:0;-moz-border-radius:0;border-radius:0;-webkit-box-shadow:none;-moz-box-shadow:none;box-shadow:none;}
	.fileupload-new.thumbnail-borderless .thumbnail{border:1px solid #ddd;}
	.control-group.warning .fileupload .uneditable-input{color:#a47e3c;border-color:#a47e3c;}
	.control-group.warning .fileupload .fileupload-preview{color:#a47e3c;}
	.control-group.warning .fileupload .thumbnail{border-color:#a47e3c;}
	.control-group.error .fileupload .uneditable-input{color:#b94a48;border-color:#b94a48;}
	.control-group.error .fileupload .fileupload-preview{color:#b94a48;}
	.control-group.error .fileupload .thumbnail{border-color:#b94a48;}
	.control-group.success .fileupload .uneditable-input{color:#468847;border-color:#468847;}
	.control-group.success .fileupload .fileupload-preview{color:#468847;}
	.control-group.success .fileupload .thumbnail{border-color:#468847;}
	
  </style>
  
  
  <script type="text/javascript">
  
  
  
  </script>
  



</head>

<body style="background:rgba(0,0,0,0.05);font-family:Microsoft JhengHei">

	
	
    <jsp:include page="/Header.jsp" />
   
   	<jsp:include page="/Bar.jsp" />




<center>
        <div style="background:white; width:93%; margin:80px 0px 50px 0px">
        <BR>
        <BR>
        <BR>
        <div class="container">
		<div class="col-xs-12">

    	<div class="page-header">
    	<jsp:include page="/CloudHeader.jsp" />
    	</div>
    	
<!-- 	 	<span class="btn btn-default btn-file"> 上傳檔案 <input type="file">  </span> -->
		
  		<div class="fileupload fileupload-new" data-provides="fileupload">
  			<form action"<c:url value='/cloud' />" method="post">
    		<span class="btn btn-info btn-file"><span class="fileupload-new">選擇檔案</span>
    		<span class="fileupload-exists">變更檔案</span>         <input type="file" /></span>
    		<span class="fileupload-preview"></span>
    		<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none">×</a>
    		<input type="submit" value="上傳檔案" class="btn btn-info">
    		</form>
  		</div>
    	
    	<br>
    	<br>
    	<br>
        
    	<div class="carousel slide" id="myCarousel">
        	<div class="carousel-inner">
            	<div class="item active">
                    <ul class="thumbnails">
                    
                        <li class="col-sm-3 col-lg-3">
    						<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Present commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        <br>
                        <br>
                        <br>
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                    </ul>
              </div><!-- /Slide1 --> 
              
            <div class="item">
                    <ul class="thumbnails">
                    
                        <li class="col-sm-3 col-lg-3">
    						<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        <br>
                        <br>
                        <br>
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                    </ul>
              </div><!-- /Slide2 --> 
              
            <div class="item">
                    <ul class="thumbnails">
                    
                        <li class="col-sm-3 col-lg-3">
    						<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        <br>
                        <br>
                        <br>
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                        <li class="col-sm-3 col-lg-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
								</div>
								<div class="caption">
									<h4>Praesent commodo</h4>
								</div>
                            </div>
                        </li>
                        
                    </ul>
              </div><!-- /Slide3 --> 
        </div>
        
       
	   	<nav>
			<ul class="control-box pager">
			<li><a data-slide="prev" href="#myCarousel" class=""><i class="glyphicon glyphicon-chevron-left"></i></a></li>
			<li><a data-slide="next" href="#myCarousel" class=""><i class="glyphicon glyphicon-chevron-right"></i></li>
			</ul>
		</nav>
	   <!-- /.control-box -->   
                              
    	</div><!-- /#myCarousel -->
        
		</div><!-- /.col-xs-12 -->          

		</div><!-- /.container -->
			
	</div>
</center>

	<jsp:include page="/Footer.jsp" />
	
</body>
</html>