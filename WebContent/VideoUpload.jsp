<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<!-- <title>Insert title here</title> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<!-- <script src="js/bootstrap.js"></script> -->
<!-- <link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" /> -->

<!-- <link rel="stylesheet" href="css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" href="css/justified-nav.css"> -->

<!-- <script src="js/bootstrap.file-input.js"></script> -->
<!-- <script src="js/js.cookie.js"></script> -->

<style>

.input-group-addon{
width: 100px;
background-color:#337ab7;
color:#fff;
}
/* 合併時修改用 */
#upload_form1 span,#VideoSetting span{
    width:112px;
}
#upload_form1 div,#VideoSetting div{
	width: 450px; 
	margin:0px auto;
	padding:5px;
}
.modal-title{
	text-align:center;
}

</style>

	<script src="js/VideoUpload.js"></script>

<!-- </head> -->

<!-- <body> -->

<!-- Button trigger modal -->
<!-- <center> -->
<!-- <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#uploadformModal"> -->
<!--   Upload File -->
<!-- </button> -->
<!-- </center> -->

<!-- Modal -->
<div class="modal fade" id="uploadformModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content">
    
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">上傳檔案</h4>
      </div>
      
      <div class="modal-body">
<!-- 		<div class="upload-form"> -->
			<form id="upload_form" enctype="multipart/form-data" method="post" action="VideoUpload">
				<fieldset style="text-align:center">
					<input  id="file_input" type="file" data-filename-placement="inside" name="videoName">
					<input  id="submit" class="btn btn-primary btn-small" type="submit" value="upload now!">
				</fieldset>
			</form>
<!-- 		</div> -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>



<!-- 上傳Modal -->

<div id="uploadModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    
  
    <div class="modal-content">
      <div class="modal-header">
        <button id="cancel_upload_btn" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">上傳中...</h4>
      </div>
    	<div class="modal-body">

			<div class="progress">
  				<div id="upload_progress" class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
    				0%
  				</div>
			</div>

        </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
    
    
    </div>
  </div>
</div>

<!-- 成功Modal -->

<div id="finishedModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
    
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">上傳成功！</h4>
      </div>
    </div><!-- /.modal-content -->
    
    
    </div>
  </div>
</div>

<!-- 阻擋Modal -->

<div id="blockModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"  aria-labelledby="myLargeModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
    
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        	<h4 class="modal-title">影片標題不允許特殊字元符號！</h4>
      </div>
    </div><!-- /.modal-content -->
    
    
    </div>
  </div>
</div>

<!-- VideoInformation表單 -->

<div class="modal fade" id="VideoForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">影片資訊</h4>
      </div>
      <div class="modal-body">

			<form id="upload_form1">
				<fieldset style="text-align:center">
				

					  <input id="memberId" type="hidden" name="memberId" value="${user.memberId}" class="form-control" placeholder="Username" aria-describedby="basic-addon1">

					
					<div id="input1" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">影片標題</span>
					  <input id="videoTitle" type="text" name="videoTitle" class="form-control" placeholder="videoTitle" aria-describedby="basic-addon1">
					</div>
					
					<div  class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">ClassName </span>
					  <select id="videoClassName" class="form-control" name="videoClassName">
					  	<option value="其他">其他</option>
					  	<option value="音樂影片">音樂影片</option>
						<option value="新聞">新聞</option>
						<option value="戲劇">戲劇</option>
						<option value="動畫">動畫</option>
						<option value="生活">生活</option>
						<option value="趣味">趣味</option>
					  </select>
<!-- 					<input id="videoClassName" type="text" name="videoClassName" class="form-control" placeholder="Ex:MV,NEWS..." aria-describedby="basic-addon1"> -->
					</div>
					
					
					
					<div  class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">檔案名稱  </span>
					  <input type="text" id="videoName" name="videoName" class="form-control" placeholder="VideoName " aria-describedby="basic-addon1" readonly>
					</div>
				
					<div class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">影片敘述</span>
					  <input id="videoDescription" type="text" name="videoDescription" class="form-control" placeholder="Write Something" aria-describedby="basic-addon1">
					</div>
					
					<input value="Insert" type="hidden" name="prodaction" class="form-control" aria-describedby="basic-addon1">
					
				</fieldset>
			</form>
			<div style="width: 450px; margin:0px auto;padding:5px" class="input-group">
				<input style="width: 440px" id="submit1"  value="Insert" class="btn btn-primary btn-small" type="submit">
			</div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<script>
$('input[type=file]').bootstrapFileInput();
$('.file-inputs').bootstrapFileInput();
// $('#finishedModal').modal('show');\
// $('#VideoForm').modal('show');
// $('#myModal').modal()                      // 採用預設值初始化
// $('#myModal').modal('hide')                // 初始化後立即呼叫 show

</script>

<!-- </body> -->
<!-- </html> -->