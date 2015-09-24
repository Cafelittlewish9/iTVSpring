var initUploadComponent = function() {

                function humanSize(bytes) {
                    const K = 1024;
                    const M = K * 1024;
                    const G = M * 1024;
                    const T = G * 1024;

                    if (bytes > T) {
                        return (Math.round(bytes * 100 / T) / 100) + " TB";
                    } else if (bytes > G) {
                        return (Math.round(bytes * 100 / G) / 100) + " GB";
                    } else if (bytes > M) {
                        return (Math.round(bytes * 100 / M) / 100) + " MB";
                    } else if (bytes > K) {
                        return (Math.round(bytes * 100 / K) / 100) + " KB";
                    } else {
                        return bytes + " Bytes";
                    }
                }

                function updateProgressBar(current, total) {
                    percent = Math.round((current * 100) / total) ;
                    console.log(percent);
                    document.querySelector('#upload_progress').style.width = percent + '%';
                    $('#upload_progress').text(percent+'%');
                }


                function handleProgressInfo(e) {
                    if (e.lengthComputable) {
                    	console.log(e.lengthComputable);
                        updateProgressBar(e.loaded, e.total);
                    }
                }

                var files = Cookies.get('files') || "";
                var flist = files.split(",");
                
                
                function handleUploadedInfo(e) {
                    updateProgressBar(1, 1);
                    
                    
                    var millisecondsToWait = 1000;
                    setTimeout(function() {
                    	$('#uploadModal').modal('hide');
                    	$('#finishedModal').modal('show');
                    }, millisecondsToWait);
                    
                    
                    setTimeout(function() {
                    	$('#finishedModal').modal('hide');
                    	$('#uploadformModal').modal('hide');
                    	$('#VideoForm').modal('show');
                    }, 2000);
                    
                    
        			
                }

                var xReq;
                function handleStartUpload(e) {
                    e.preventDefault();
                    if (document.querySelector('#file_input').files.length <= 0) {
                            return;
                    }else{
                    	var file = $('#file_input').val();
                        var exts = ['mkv','mp4','avi','mpg','3gp','mov','flv','asf','asx'];
                        // first check if file field has any value
	                    
	                        	console.log(file);
	                          // split file name at dot
	                          var get_ext = file.split('.');
	                          
	                          // reverse name to check extension
	                          get_ext = get_ext.reverse();
	                          // check file type is valid as given in 'exts' array
	                          if ( $.inArray ( get_ext[0].toLowerCase(), exts ) > -1 ){
	                        	  //特殊字元判斷
		                          var re = new RegExp("(?=.*[~`+@#$%^&*()\'\";:\/?,.|\\”“。，、？！@#￥……])")
		                          if (!re.test(get_ext[1].substring(get_ext[1].lastIndexOf("\\")+1))){
		                        	  
		                          }else{
		                        	  $('#blockModal').modal('show');
		                        	  return;
		                          } 
	                          } else {
		                        	  $('#finishedModal h4').text("副檔名只允許.mp4.mkv.avi.mpg.3gp.mov.flv");
		                        	  $('#finishedModal').modal('show');
	                        	  
	                        	  
	                            return;
	                          }
	                         
                    }

                    updateProgressBar(0, 1);
                   

                    xReq = new XMLHttpRequest();
                    xReq.upload.addEventListener('progress', handleProgressInfo, false)
                    xReq.addEventListener('load', handleUploadedInfo, false);

                    upload_form = document.querySelector('#upload_form');
                    xReq.open("post", "VideoUpload", true);
                    xReq.send(new FormData(upload_form));

                    $('#uploadModal').modal('show');
                    
                }
                document.querySelector('#submit').addEventListener('click', handleStartUpload, false);

                function handleCancelUpload(e) {
                    if (xReq) {
                        xReq.abort();
                    }
                }
                document.querySelector('#cancel_upload_btn').addEventListener('click', handleCancelUpload, false);
                
                $('#submit').on('click', function() {
                    $('#videoName').val($('#file_input').val().substring ($('#file_input').val().lastIndexOf("\\")+1, $('#file_input').val().lastIndexOf(".")));
                  });
                

                
                $('#videoTitle').blur(function(){
                	if( $('#videoTitle').val()==""){
                	$('#input1').addClass("has-error has-feedback");
                	}else{
                		$('#input1').removeClass("has-error has-feedback");
                	}
                });
                
                $('#submit1').click(function(){
                	if($('#videoTitle').val()==""){
                        $('#input1').addClass("has-error has-feedback");
                    	return;
                	}else{
                	$('#input1').removeClass("has-error has-feedback");
//                 	console.log($('form').serializeArray());
                	
	                	var re = new RegExp("(?=.*[~`+@#$%^&*()\'\";:\/?,.|\\”“。，、？！@#￥……])")
	                    if (!re.test($('#videoTitle').val())){
	                    	
	                    	$.get('VideoServlet',$('#upload_form1').serialize(),function(data){
	                   		     console.log(data);
	                   	   		})
	                   	   		
	                   	   	 $.post('Convert',{"videoName":$('#videoName').val()},function(data){
	     							console.log("轉檔成功");
	                   	   		})	
	                   	   		
	                   	   		//關閉列表 顯示成功畫面
	                   	   		$('#VideoForm').modal('hide');
	                     	 	setTimeout(function() {
	     	                     	$('#finishedModal').modal('show');
	     	                     }, 1500);
	                          	//一秒半後關閉成功畫面
	     	                	 setTimeout(function() {
	     	                     	$('#finishedModal').modal('hide');
	     	                     }, 3000);
	     	                	 setTimeout(function() {
	     	                		 location.reload();
	     		                 }, 4000);
	     	                	 
	                    }else{
	                    	setTimeout(function() {
	                    		$('#VideoForm').modal('hide');
	                    		$('#input1').addClass("has-error has-feedback");
	                    		$('#blockModal').modal('show');
	                    	}, 1000);

	                    	setTimeout(function() {
	                    		$('#blockModal').modal('hide');
	                    		$('#VideoForm').modal('show');
	                    	}, 3000);
	                  	  return;
	                    } 
                	
                	}
            	});
                
            }
            window.addEventListener('load', initUploadComponent, false);