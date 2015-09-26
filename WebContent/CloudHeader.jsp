<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="css/cloudHeader.css" rel="stylesheet">
<script src="js/cloudHeader.js"></script>

<!-- searchbar所需的 -->
<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />

<!-- 檔案上傳所需的 -->
<script src="js/bootstrap.file-input.js"></script>
<script src="js/js.cookie.js"></script>


</head>
<body>

<div class="container">
  <nav class="navbar navbar-inverse">
    <div class="navbar-header">
    	<button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">雲端硬碟</a>
	</div>
	
	<div class="collapse navbar-collapse js-navbar-collapse">
		<ul class="nav navbar-nav">
			<li class="dropdown mega-dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">檔案類型<span class="caret"></span></a>				
				<ul class="dropdown-menu mega-dropdown-menu">
					<li class="col-sm-3">
						<ul>
							<li class="dropdown-header">圖片</li>                            
                            <div id="menCollection" class="carousel slide" data-ride="carousel">
                              <div class="carousel-inner">
                              
                                <div class="item active">
                                    <a href="#"><img src="http://placehold.it/254x150/ff3546/f5f5f5/&text=New+Collection" class="img-responsive" alt="product 1"></a>
                                    <h4><small>皮卡丘的一生</small></h4>                                        
                                </div><!-- End Item -->
                                
                                <div class="item">
                                    <a href="#"><img src="http://placehold.it/254x150/3498db/f5f5f5/&text=New+Collection" class="img-responsive" alt="product 2"></a>
                                    <h4><small>皮卡丘的屁股</small></h4>                                        
                                </div><!-- End Item -->
                                
                                <div class="item">
                                    <a href="#"><img src="http://placehold.it/254x150/2ecc71/f5f5f5/&text=New+Collection" class="img-responsive" alt="product 3"></a>
                                    <h4><small>皮卡丘的鼻子</small></h4>                                        
                                </div><!-- End Item -->   
                                <li class="divider"></li>
                            	<li><a href="#">觀看所有圖片<span class="glyphicon glyphicon-chevron-right pull-right"></span></a></li>                         
                              </div><!-- End Carousel Inner -->
                             
						</ul>
					</li>
					
					
					
					<li class="col-sm-3">
						<ul>
						
							<li class="dropdown-header">文件</li>
							<li><a href="#">皮卡丘日記(一)</a></li>
                            <li><a href="#">皮卡丘日記(二)</a></li>
                            <li><a href="#">皮卡丘日記(三)</a></li>
							<li><a href="#">皮卡丘日記(四)</a></li>
							<li><a href="#">皮卡丘日記(四)</a></li>
							<li><a href="#">皮卡丘日記(四)</a></li>
							<li><a href="#">皮卡丘日記(四)</a></li>
							<br>
							<li class="divider"></li>
						<div>
							<li><a href="#">觀看所有文件<span class="glyphicon glyphicon-chevron-right pull-right"></span></a></li>
						</div>
						</ul>
					</li>
					<li class="col-sm-3">
						<ul>
							<li class="dropdown-header">影音</li>
							<li><a href="#">皮卡丘日記(一)</a></li>
                            <li><a href="#">皮卡丘日記(二)</a></li>
                            <li><a href="#">皮卡丘日記(三)</a></li>
							<li><a href="#">皮卡丘日記(四)</a></li>
							<li><a href="#">皮卡丘日記(四)</a></li>
							<li><a href="#">皮卡丘日記(四)</a></li>
							<li><a href="#">皮卡丘日記(四)</a></li>
							<br>
							<li class="divider"></li>
						<div>
							<li><a href="#">觀看所有文件<span class="glyphicon glyphicon-chevron-right pull-right"></span></a></li>
						</div>
						</ul>
					</li>
					
				</ul>				
			</li>
           
		</ul>
        <ul class="nav navbar-nav navbar-right">
        	
      </ul>
	</div><!-- /.nav-collapse -->
  </nav>
</div>




</body>
</html>