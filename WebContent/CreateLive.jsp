<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.lang.*"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<!-- <title>Create Live</title> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<!-- <script src="js/bootstrap.js"></script> -->

<!-- <link rel="stylesheet" href="css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" href="css/justified-nav.css"> -->

<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js">
</script>

<link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
<!-- <link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" /> -->

<!-- <link rel="stylesheet" href="css/layout.css"> -->
<!-- <link rel="stylesheet" href="css/bootstrap-responsive.css"> -->
<!-- <link rel="stylesheet" href="css/bootstrap.2.2.2.css"> -->

<!-- <script src="js/bootstrap-datepicker.en-GB.js"></script> -->
<!-- <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet"> -->

<link rel="stylesheet" href="css/CreateLive.css">

            		
<script>
            var initUploadComponent = function() {
                
            	
                	var server = "http://itvvm.cloudapp.net/ITV/LiveShow.jsp?m=";
                    $('#c_website').val(server+'${user.memberAccount}');
                 
                
                
//              判斷必要輸入欄位的用法               
                $('#showtime').blur(function(){
                	if( $('#showtime').val()==""){
                	$('#c_input2').addClass("has-error has-feedback");
                	}else{
                		$('#c_input2').removeClass("has-error has-feedback");
                	}
                });
                
//              建立實況
                $('#c_submit1').click(function(){
                	if($('#showtime').val()==""){
                		$('#c_input2').addClass("has-error has-feedback");
	                	return;
                	}else{
                		
                		$('#c_input2').removeClass("has-error has-feedback");
                		
//                     	console.log($('form').serializeArray());
                   	 	$.get('BroadcastOrderServlet',$('#createliveform').serialize(),function(data){
                 		   		console.log(data);
                 	   		})
                 	   		
                 	   		//關閉列表 顯示成功畫面
                 	   		$('#CreateModal').modal('hide');
                        	$('#createsuccessModal').modal('show');
                        	//一秒半後關閉成功畫面
	   	                	 setTimeout(function() {
	   	                     	$('#createsuccessModal').modal('hide');
	   	                     }, 1500);
	   	                	setTimeout(function() {
		                		 location.reload();
			                 }, 2000);
                	}

            	});
                
//              取消實況
                $('#c_submit2').click(function(){
                	$.ajax({
            			url:'BroadcastOrderServlet',
            			type:'get',
             			data:{'memberAccount':'${user.memberAccount}',
             				  'prodaction':'Delete'},
            			dataType:"json",
            			success:function(data){
            					console.log(data);
            						if(data=="true"){
            							$('#createlive').css('display','block');
            							$('#cancellive').css('display','none');
            						}else{
            							$('#createlive').css('display','none');
            						 	$('#cancellive').css('display','block');
            						}
            						setTimeout(function() {
       		                		 location.reload();
       			                 	}, 1000);
            			}
            		})
                });
                
                
            }
            window.addEventListener('load', initUploadComponent, false);
          
            
            $(function () {
            	$('#datetimepicker1').datetimepicker({
                    locale: 'en-GB'
                });
            });
            
	</script>
<!-- </head> -->

<!-- <body> -->
<!-- Create LiveShow -->
<center>
<!-- <button style="border-style: outset;border-color:black" id="createlive" type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#CreateModal"> -->
<!--   Create Your LiveShow -->
<!-- </button> -->
</center>

<!-- Modal -->
<div class="modal fade" id="CreateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">建立實況</h4>
      </div>
      <div class="modal-body">


			<div class="modal-body">

			<form id="createliveform">
				<fieldset style="text-align:center">
				
<!-- 						<div id="c_input" class="input-group"> -->
<!-- 						  <span  class="input-group-addon" id="basic-addon1" style="width:100px">Username</span> -->
						  <input id="c_memberAccount" type="hidden" value="${user.memberAccount}" name="memberAccount" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
<!-- 						</div> -->
					
					
					    <div id="c_input2" class="input-group">
					    		<span  class="input-group-addon" id="basic-addon1" style="width:100px">實況時間</span>
					    		
					                <div class='input-group date' id='datetimepicker1' style="width: 340px;">
					                    <input id="showtime" name="broadcastTime" data-format="yyyy-MM-dd hh:mm:ss" type="text" class="form-control"/>
					                    <span class="input-group-addon" style="width: 65px;">
					                        <span class="glyphicon glyphicon-calendar"></span>
					                    </span>
					                </div>
					    </div>
					    
					    <div id="c_input3" class="input-group">
							  <span  class="input-group-addon" id="basic-addon1">直播名稱</span>
							  <input id="broadcastTitle" type="text" name="broadcastTitle" class="form-control" placeholder="" aria-describedby="basic-addon1">
						</div>
					     
					
						<div id="c_input4" class="input-group">
						  <span  class="input-group-addon" id="basic-addon1" style="width:100px">直播網址 </span>
						  <input type="text" id="c_website" name="broadcastSite" class="form-control" placeholder="" aria-describedby="basic-addon1" readonly>
						</div>
						
						
						
						<input value="Insert" type="hidden" name="prodaction" class="form-control" aria-describedby="basic-addon1">
					
				</fieldset>
			</form>
			<div style="width: 450px; margin:0px auto;padding:5px" class="input-group">
				<input style="width: 440px" id="c_submit1"  value="Insert" class="btn btn-primary btn-small" type="submit">
			</div>

      </div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- 成功Modal -->

<div id="createsuccessModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
    
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">建立實況成功！</h4>
      </div>
    </div><!-- /.modal-content -->
    
    
    </div>
  </div>
</div>


<!-- 關閉實況狀態 -->

<div id="CancelModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
    
  
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">確定取消實況直播?</h4>
	        <div style='text-align:center'>
	        	<button  id="c_submit2"  value="送出" type="submit" class="btn btn-default" data-dismiss="modal">確定</button>
	        </div>
	      </div>
	      
	    </div><!-- /.modal-content -->
    
    
    </div>
  </div>
</div>


<!-- </body> -->
<!-- </html> -->