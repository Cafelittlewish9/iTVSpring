$(function(){
//	$('#videos_div1').empty();
//	$.ajax({
//		url:'VideoServlet',
//		type:'get',
////			data:{'categoryID':1},
//		dataType:"json",
//		success:function(data){
//			$.each(data,function(i,v){
//				var vn = v.videoName.substring(0,25);
//				var vd = v.videoDescription;
//				if(vd==null){
//					vd="";
//				}
//				//順序=最新-舊
//				$('#videos_div1').prepend("<td>")
//				$('#videos_div1').prepend("<div class='service'>"+
//						"<a href='PlayVideo.jsp?filename="+v.videoName + "'>"+
//						"<img src='../img/"+v.videoName+".jpg' width='200px'/></a><br>"+
//						"<a href='PlayVideo.jsp?filename="+v.videoName+"'>"+
//						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>"+v.videoName.substr(0,25)+"</div></span></a><br>"+
//						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>"+vd+"</p></div></span></a><br>"+
//						"<span id='v_watchtimes'>"+v.videoWatchTimes+ "views</span><br></td>"+
//						"</div>");
//				
//			});
//		}
//	})
	
	$('#browse_type_any').click(function(){
		console.log($(this).val());
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data.list,function(i,v){
//					var vn = v.videoName.substring(0,25);
//					var vd = v.videoDescription;
//					if(vd==null){
//						vd="";
//					}
					//順序=最新-舊
//				$('#videos_div1').prepend("<td>")
				$('#videos_div1').prepend("<div class='service'>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<img src='../img/" + v.videoName + ".jpg' width='200px'/></a><br>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>" + v.videoTitle + "</div></span></a><br>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>" + v.videoDescription +"</p></div></span></a><br>"+
						"<span id='v_watchtimes'>" + v.videoWatchTimes + " views</span><br></td>"+
						"</div>");
					
				});
			}
		})
	})
	
	
	$('#browse_type_MusicVideo').click(function(){
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					//順序=最新-舊
//					if(v.videoClassName=="mv"){
//						var vn = v.videoName.substring(0,25);
//						var vd = v.videoDescription;
//						if(vd==null){
//							vd="";
//						}
						//順序=最新-舊
//						$('#videos_div1').prepend("<td>")
						$('#videos_div1').prepend("<div class='service'>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName + "'>"+
								"<img src='../img/"+v.videoName+".jpg' width='200px'/></a><br>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName+"'>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>"+v.videoTitle+"</div></span></a><br>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>"+v.videoDescription+"</p></div></span></a><br>"+
								"<span id='v_watchtimes'>"+v.videoWatchTimes+ " views</span><br></td>"+
								"</div>");
//					}
				});
			}
		})
	})
	
	$('#browse_type_News').click(function(){
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
//					if(v.videoClassName=="News"){
//						var vn = v.videoName.substring(0,25);
//						var vd = v.videoDescription;
//						if(vd==null){
//							vd="";
//						}
						//順序=最新-舊
//						$('#videos_div1').prepend("<td>")
						$('#videos_div1').prepend("<div class='service'>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName + "'>"+
								"<img src='../img/"+v.videoName+".jpg' width='200px'/></a><br>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName+"'>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>"+v.videoTitle+"</div></span></a><br>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>"+v.videoDescription+"</p></div></span></a><br>"+
								"<span id='v_watchtimes'>"+v.videoWatchTimes+ " views</span><br></td>"+
								"</div>");
//					}
				});
			}
		})
	})
	
	$('#browse_type_Drama').click(function(){
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					
//					if(v.videoClassName=="Drama"){
//						var vn = v.videoName.substring(0,25);
//						var vd = v.videoDescription;
//						if(vd==null){
//							vd="";
//						}
						//順序=最新-舊
//						$('#videos_div1').prepend("<td>")
						$('#videos_div1').prepend("<div class='service'>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName + "'>"+
								"<img src='../img/"+v.videoName+".jpg' width='200px'/></a><br>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName+"'>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>"+v.videoTitle+"</div></span></a><br>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>"+v.videoDescription+"</p></div></span></a><br>"+
								"<span id='v_watchtimes'>"+v.videoWatchTimes+ " views</span><br></td>"+
								"</div>");
//					}
				});
			}
		})
	})
	
	$('#browse_type_Animation').click(function(){
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
//					
//					if(v.videoClassName=="Animation"){
//						var vn = v.videoName.substring(0,25);
//						var vd = v.videoDescription;
//						if(vd==null){
//							vd="";
//						}
						//順序=最新-舊
//						$('#videos_div1').prepend("<td>")
						$('#videos_div1').prepend("<div class='service'>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName + "'>"+
								"<img src='../img/"+v.videoName+".jpg' width='200px'/></a><br>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName+"'>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>"+v.videoTitle+"</div></span></a><br>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>"+v.videoDescription+"</p></div></span></a><br>"+
								"<span id='v_watchtimes'>"+v.videoWatchTimes+ " views</span><br></td>"+
								"</div>");
//					}
				});
			}
		})
	})
	
	$('#browse_type_Daily').click(function(){
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
//					console.log(data);
//					if(v.videoClassName=="Daily"){
//						var vn = v.videoName.substring(0,25);
//						var vd = v.videoDescription;
//						if(vd==null){
//							vd="";
//						}
						//順序=最新-舊
//						$('#videos_div1').prepend("<td>")
						$('#videos_div1').prepend("<div class='service'>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName + "'>"+
								"<img src='../img/"+v.videoName+".jpg' width='200px'/></a><br>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName+"'>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>"+v.videoTitle+"</div></span></a><br>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>"+v.videoDescription+"</p></div></span></a><br>"+
								"<span id='v_watchtimes'>"+v.videoWatchTimes+ " views</span><br></td>"+
								"</div>");
//					}
				});
			}
		})
	})
	
	$('#browse_type_Interest').click(function(){
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					
//					if(v.videoClassName=="Interest"){
//						var vn = v.videoName.substring(0,25);
//						var vd = v.videoDescription;
//						if(vd==null){
//							vd="";
//						}
						//順序=最新-舊
//						$('#videos_div1').prepend("<td>")
						$('#videos_div1').prepend("<div class='service'>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName + "'>"+
								"<img src='../img/"+v.videoName+".jpg' width='200px'/></a><br>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName+"'>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>"+v.videoTitle+"</div></span></a><br>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>"+v.videoDescription+"</p></div></span></a><br>"+
								"<span id='v_watchtimes'>"+v.videoWatchTimes+ " views</span><br></td>"+
								"</div>");
//					}
				});
			}
		})
	})
	
	$('#browse_type_Other').click(function(){
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					
//					if(v.videoClassName=="Other"){
//						var vn = v.videoName.substring(0,25);
//						var vd = v.videoDescription;
//						if(vd==null){
//							vd="";
//						}
						//順序=最新-舊
//						$('#videos_div1').prepend("<td>")
						$('#videos_div1').prepend("<div class='service'>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName + "'>"+
								"<img src='../img/"+v.videoName+".jpg' width='200px'/></a><br>"+
								"<a href='PlayVideo.jsp?filename="+v.videoName+"'>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>"+v.videoTitle+"</div></span></a><br>"+
								"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>"+v.videoDescription+"</p></div></span></a><br>"+
								"<span id='v_watchtimes'>"+v.videoWatchTimes+ " views</span><br></td>"+
								"</div>");
//					}
				});
			}
		})
	})
})