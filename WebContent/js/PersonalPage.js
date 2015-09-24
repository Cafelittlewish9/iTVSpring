$(function(){
//		個人頁面資料
		$.ajax({
			url:'VideoServlet',
			type:'get',
			data:{'memberId':memberId,
				  'prodaction':'searchMemberId'},
			dataType:"JSON",
			success:function(data){
				$('.latest-news-header--title').append(memberAccount);
				if(data[0]!=null){
 				var dt = (data[0].videoUploadTime).split(' ');
 				$('.latest-news-header--date').append(dt[0]+", "+dt[1]+" "+dt[2]+", "+dt[5]);
					$.each(data,function(i,v){
						var temp = (v.videoUploadTime).split(' ');
						var vut = temp[0]+", "+temp[1]+" "+temp[2]+" "+temp[5];
						//順序=最新-舊
						$('#vs_videoId').append("<option value='"+v.videoId+"'>"+v.videoTitle+"</option>");
						
						$('.feed-page--main').prepend("<div class='feed-page--left'>"+
									"<article class='feed-card feed-card__promoted feed-card__promoted-landscape'>"+
									"<div class='feed-card--image'>"+
									"<img src='../img/"+v.videoName+".jpg' class='picture-img'/></div>"+
									"<div class='feed-card--meta'>"+
									"<time class='feed-card--time'>"+vut+"</time>"+
									"<button type='button' class='feed-card--category' id='video_setting' data-toggle='modal' data-target='#VideoSettingForm'>Setting</button></div>"+
									"<div class='feed-card--info'><h2 class='feed-card--title'><a href='PlayVideo.jsp?filename="+v.videoName+"'>"+v.videoTitle+"</a></h2>"+
									"<div class='feed-card--byline' style='float:left'><span>by "+ v.memberAccount+"</span></div></div></article><hr></div>");
						
				});
			}
		}
	});
		
// 		編輯影片
	$('#vs_submit1').click(function(){
    	if($('#vs_videoTitle').val()==""){
    		$('#vs_input2').addClass("has-error has-feedback");
        	return;
    	}else{
    		$('#vs_input2').removeClass("has-error has-feedback");
    	
        	var re = new RegExp("(?=.*[~`+@#$%^&*()\'\";:\/?,.|\\”“。，、？！@#￥……])")
            if (!re.test($('#vs_videoTitle').val())){
            	
            	$.get('VideoServlet',$('#VideoSetting').serialize(),function(data){
           		     	if(data){
           		     	//關閉列表 顯示成功畫面
                   	   		$('#VideoSettingForm').modal('hide');
                     	 	setTimeout(function() {
                     	 			$('#finishedModal h4').text('修改成功');
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
           		     	//關閉列表 顯示成功畫面
                   	   		$('#VideoSettingForm').modal('hide');
                     	 	setTimeout(function() {
                     	 			$('#finishedModal h4').text('修改失敗,請再嘗試一次');
        	                     	$('#finishedModal').modal('show');
        	                     }, 1500);
                          	//一秒半後關閉成功畫面
        	                setTimeout(function() {
        	                     	$('#finishedModal').modal('hide');
        	                     }, 3000);
        	                setTimeout(function() {
        	                		 location.reload();
        		                 }, 4000);
           		     	}
           	   		}) 
            }else{
            	setTimeout(function() {
            		$('#VideoSettingForm').modal('hide');
            		$('#vs_input2').addClass("has-error has-feedback");
            		$('#blockModal').modal('show');
            	}, 1000);

            	setTimeout(function() {
            		$('#blockModal').modal('hide');
            		$('#VideoSettingForm').modal('show');
            	}, 3000);
          	  return;
            } 
    	
    	}
	});
	
// 	刪除影片
	$('#vs_submit2').click(function(){
			$('#vs_prodaction').val('Delete');
			$('#DeleteVideoModal').modal('show');
			
				$('#dv_submit').click(function(){
					$.get('VideoServlet',$('#VideoSetting').serialize(),function(data){
           		     	if(data){
	           		     	//關閉列表 顯示成功畫面
           		     		$('#DeleteVideoModal').modal('hide');
	                   	   	$('#VideoSettingForm').modal('hide');
	                   	   	
	                     	setTimeout(function() {
	                     	 	$('#finishedModal h4').text('刪除成功');
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
	           		     	//關閉列表 顯示失敗畫面
	           		     	$('#VideoSettingForm').modal('hide');
	                     	setTimeout(function() {
	                     	 	$('#finishedModal h4').text('刪除失敗,請再嘗試一次');
	        	                $('#finishedModal').modal('show');
	        	                 }, 1500);
	                     	//一秒半後關閉失敗畫面
	        	            setTimeout(function() {
	        	                 $('#finishedModal').modal('hide');
	        	                 }, 3000);
	        	                 setTimeout(function() {
	        	                 location.reload();
	        		             }, 4000);	
           		     	}
           	   		})
				})
	});
	
//	回最上層
	$("#gotop").click(function(){
        jQuery("html,body").animate({
            scrollTop:0
        },1000);
    });
    $(window).scroll(function() {
        if ( $(this).scrollTop() > 400){
            $('#gotop').fadeIn("fast");
        } else {
            $('#gotop').stop().fadeOut("fast");
        }
    });
    
})