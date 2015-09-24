
//從資料庫抓使用者加的影片

$(function(){
	$.ajax({
		url:'ShowServlet',
		type:'get',
		data:{'memberId':memberId},
		dataType:"json",
		success:function(data){
			var videos=[];
			$('#vls_memberId').val(data[0].memberId);
			$.each(data,function(i,v){
				var videolist = (v.website).substring((v.website).indexOf('=')+1);
//				console.log("videolist=" + videolist);
				 var listvideos = {
				                  src: ['../mp4/'+videolist+'.mp4'],
				                  poster: '../img/'+videolist+'.jpg',
				                  title: v.videoTitle
				              	  };
				 videos[i]=	listvideos;
				 
				 $('#vls_website').append("<option value='"+videolist+"'>"+videolist+"</option>");
				 
				 
			});
				
//				console.log(videos);
			
				var demoModule = {
						
				        init: function () {
				            this.els = {};
				            this.cacheElements();
				            this.initVideo();
				            this.createListOfVideos();
				            this.bindEvents();
				            this.overwriteConsole();
				        },
				        overwriteConsole: function () {
				            console._log = console.log;
				            console.log = this.log;
				        },
				        log: function (string) {
				            demoModule.els.log.append('<p>' + string + '</p>');
	//			            console._log(string);
				        },
				        cacheElements: function () {
				            this.els.$playlist = $('div.playlist > ul');
				            this.els.$next = $('#next');
				            this.els.$prev = $('#prev');
				            this.els.log = $('div.panels > pre');
				        },
				        initVideo: function () {
				            this.player = videojs('video');
				            this.player.playList(videos);
				        },
				        createListOfVideos: function () {
				            var html = '';
				            for (var i = 0, len = this.player.pl.videos.length; i < len; i++) {
				                html += '<li data-videoplaylist="' + i + '">' +
				                          '<span class="number">' + (i + 1) + '</span>' +
				                          '<span class="poster"><img src="' + videos[i].poster + '"></span>' +
				                          '<span class="title" style="font-family:sans-serif">' + videos[i].title + '</span>' +
				                        '</li>';
				            }
				            this.els.$playlist.empty().html(html);
				            this.updateActiveVideo();
				        },
				        updateActiveVideo: function () {
				            var activeIndex = this.player.pl.current;
		
				            this.els.$playlist.find('li').removeClass('active');
				            this.els.$playlist.find('li[data-videoplaylist="' + activeIndex + '"]').addClass('active');
				        },
				        bindEvents: function () {
				            var self = this;
				            this.els.$playlist.find('li').on('click', $.proxy(this.selectVideo, this));
				            this.els.$next.on('click', $.proxy(this.nextOrPrev, this));
				            this.els.$prev.on('click', $.proxy(this.nextOrPrev, this));
				            this.player.on('next', function (e) {
				                console.log('Next video');
				                self.updateActiveVideo.apply(self);
				            });
				            this.player.on('prev', function (e) {
				                console.log('Previous video');
				                self.updateActiveVideo.apply(self);
				            });
				            this.player.on('lastVideoEnded', function (e) {
				                console.log('Last video has finished');
				            });
				        },
				        nextOrPrev: function (e) {
				            var clicked = $(e.target);
				            this.player[clicked.attr('id')]();
				        },
				        selectVideo: function (e) {
				            var clicked = e.target.nodeName === 'LI' ? $(e.target) : $(e.target).closest('li');
		
				            if (!clicked.hasClass('active')) {
				                console.log('Selecting video');
				                var videoIndex = clicked.data('videoplaylist');
				                this.player.playList(videoIndex);
				                this.updateActiveVideo();
				            }
				        }
				    };
		
				    demoModule.init();
				
			}
	
	});
	
//		編輯影片烈表
			$('#vls_submit1').click(function(){
				$.ajax({
					url:'ShowServlet',
					type:'get',
					data:$('#VideoListSetting').serialize(),
					dataType:"json",
					success:function(data2){
						if(data2!=null){
							//關閉列表 顯示成功畫面
		               	$('#VideoListSettingForm').modal('hide');
		               	   	setTimeout(function() {
		             	 	$('#finishedModal h4').text('編輯成功');
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
		   		     	$('#VideoListSettingForm').modal('hide');
		   		     		setTimeout(function() {
		             	 	$('#finishedModal h4').text('編輯失敗,請再嘗試一次');
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
					}
				})
			});
	
	
})