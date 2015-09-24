<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.lang.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>PlayList</title>
<!--       <link href="video-js.css" rel="stylesheet" type="text/css"> -->
<!--     <link href="css/normalize.css" rel="stylesheet"> -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
	
    <link href="http://vjs.zencdn.net/4.0/video-js.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <script src="http://vjs.zencdn.net/4.0/video.js"></script>
    
    <link href="css/SearchPageBorder.css" rel="stylesheet">

	<!-- searchbar所需的 -->
	<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />
	
	<!-- 檔案上傳所需的 -->
	<script src="js/bootstrap.file-input.js"></script>
	<script src="js/js.cookie.js"></script>
<style>
#VideoListSetting span{
    width:112px;
	}
#VideoListSetting div{
		width: 450px; 
		margin:0px auto;
		padding:5px;
	}
.modal-title{
		text-align:center;
	}
</style>
	
</head>
<body style="font-family:Microsoft JhengHei">

	<jsp:include page="/Header.jsp" />
   
   	<jsp:include page="/Bar.jsp" />
   	
 
<section class="video-home">
    <div class="container-fluid" style="padding:10px 150px 20px 130px;">
      <div class="row" style="margin: 0;"> 
   
		<div id="wrapper">
		
		    <div class="video-holder centered">
		        <video id="video" class="video-js vjs-default-skin"
		               controls preload="auto" width="720.297" height="400"
		               data-setup=''
		               poster=""
		              >
		        </video>
		        <div class="playlist-components">
		            <div class="playlist" style="text-align:left">
		                <ul>
		                
		                </ul>
		            </div>
		            <div class="button-holder">
		                <img id="prev" alt="Previous video" src="img/Previous.png">
		                <img id="next" alt="Next video" src="img/Next.png">
		            </div>
		        </div>
		    </div>
		    
		    <div style="height:34px;text-align:left;margin:0px auto">
				<div class="stream-video-info--buttons">
					<button type="button" class="btn btn-primary btn-me" data-toggle="modal" data-target="#VideoListSettingForm">編輯影片列表</button>
				</div>
			</div>
		</div>

      </div>
    </div>
</section>
  
<!-- VideoListSetting -->

<div class="modal fade" id="VideoListSettingForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">影片列表編輯</h4>
      </div>
      <div class="modal-body">

			<form id="VideoListSetting">
				<fieldset style="text-align:center">
					
					<input id="vls_memberId" value="${user.memberId}" type="hidden" name="memberId" class="form-control" aria-describedby="basic-addon1">
					
					<div id="vls_input1" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">影片標題</span>
					  <select id="vls_website" class="form-control" name="website">
					  		
					  </select>
					</div>
				
					<input value="Delete" type="hidden" name="prodaction" class="form-control" aria-describedby="basic-addon1">
					
				</fieldset>
			</form>
			<div style="width: 450px; margin:0px auto;padding:5px" class="input-group">
				<input style="width: 440px" id="vls_submit1"  value="刪除" class="btn btn-primary btn-small" type="submit">
			</div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>  
  
<jsp:include page="/Footer.jsp" />

<script src="https://rawgithub.com/Belelros/videojs-playLists/master/lib/videojs-playlists.js" data-cover></script>

<script type="text/javascript">
        var memberId ='${user.memberId}';
</script>
<script src="js/PlayList.js"></script>


</body>
</html>