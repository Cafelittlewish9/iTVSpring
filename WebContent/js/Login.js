$(function () {
//  判斷必要輸入欄位的用法
    $('#memberAccount').blur(function(){
    	if( $('#memberAccount').val()==""){
    	$('#l_input1').addClass("has-error has-feedback");
    	}else{
    		$('#l_input1').removeClass("has-error has-feedback");
    	}
    });
    $('#Login_memberAccount').blur(function(){
    	if( $('#Login_memberAccount').val()==""){
    	$('#l_input1-1').addClass("has-error has-feedback");
    	}else{
    		$('#l_input1-1').removeClass("has-error has-feedback");
    	}
    });
    $('#get_memberAccount').blur(function(){
    	if( $('#get_memberAccount').val()==""){
    	$('#l_input1-2').addClass("has-error has-feedback");
    	}else{
    		$('#l_input1-2').removeClass("has-error has-feedback");
    	}
    });
//  判斷必要輸入欄位的用法               
    $('#memberPassword').blur(function(){
    	if( $('#memberPassword').val()==""){
    	$('#l_input2').addClass("has-error has-feedback");
    	}else{
    		$('#l_input2').removeClass("has-error has-feedback");
    	}
    });
    $('#Login_memberPassword').blur(function(){
    	if( $('#Login_memberPassword').val()==""){
    	$('#l_input2-1').addClass("has-error has-feedback");
    	}else{
    		$('#l_input2-1').removeClass("has-error has-feedback");
    	}
    });
    $('#get_memberPassword').blur(function(){
    	if( $('#get_memberPassword').val()==""){
    	$('#l_input2-2').addClass("has-error has-feedback");
    	}else{
    		$('#l_input2-2').removeClass("has-error has-feedback");
    	}
    });
//  判斷必要輸入欄位的用法               
    $('#memberEmail').blur(function(){
    	if( $('#memberEmail').val()==""){
    	$('#l_input3').addClass("has-error has-feedback");
    	}else{
    		$('#l_input3').removeClass("has-error has-feedback");
    	}
    });
    
    
	    
	
	
	
	
    $('#l_submit1').click(function(){
    	if($('#memberAccount').val()==""||$('#memberPassword').val()==""||$('#memberEmail').val()==""){
    		if($('#memberAccount').val()==""){
    			$('#l_input1').addClass("has-error has-feedback");
    		}
        	if($('#memberPassword').val()==""){
            	$('#l_input2').addClass("has-error has-feedback");
        	}
        	if($('#memberEmail').val()==""){
        		$('#l_input3').addClass("has-error has-feedback");
    		}
        	return;
    	}else{
    		var file = $('#memberPhoto').val();
            var exts = ['jpg','jpeg','png'];
            var get_ext = file.split('.');
            get_ext = get_ext.reverse();
            
            
            if(document.querySelector('#memberPhoto').files.length > 0){
            	if ( $.inArray( get_ext[0].toLowerCase(), exts ) > -1 ){
                	
                }else{
                	$('#signupfinished h4').text("副檔名只允許.jpg.jpeg.png");
              	  	$('#signupfinished').modal('show');
          	  
          	  		return;
                }
            }
            
            $.get('MemberServlet',$('#SignUp_form').serialize(),function(data){
     		   console.log(data);
     	   		});
  		 
	  		//關閉列表 顯示成功畫面
	  		setTimeout(function() {
					$('#SignUp').modal('hide');
	            	$('#signupfinished').modal('show');
	            }, 500);
	  		
	        	//一秒半後關閉成功畫面
	       	 setTimeout(function() {
	            	$('#signupfinished').modal('hide');
	            }, 2000);
	       	 setTimeout(function() {
	      		 location.reload();
	           }, 3000);
    	}
    	
    });
    
    
//    $('#l_submit2-1').click(function(){
//    	if($('#Login_memberAccount').val()==""||$('#Login_memberPassword').val()==""){
//    		if($('#Login_memberAccount').val()==""){
//    			$('#l_input1-1').addClass("has-error has-feedback");
//    		}
//        	if($('#Login_memberPassword').val()==""){
//            	$('#l_input2-1').addClass("has-error has-feedback");
//        	}
//        	return;
//    	}else{
//    		 $.get('MemberServlet',$('#Login_form').serialize(),function(data){
//       		   console.log(data);
//       	   		});
//    		 
//    		 $('#signupfinished h4').text("登入成功");
//    		//關閉列表 顯示成功畫面
//    		setTimeout(function() {
//				$('#Login').modal('hide');
//              	$('#signupfinished').modal('show');
//              }, 500);
//    		
//          	//一秒半後關閉成功畫面
//         	 setTimeout(function() {
//              	$('#signupfinished').modal('hide');
//              }, 2000);
//         	setTimeout(function() {
//       		 location.reload();
//            }, 3000);
//    	}
//    	
//    });
    
    $('#l_submit3-1').click(function(){
    	if($('#get_memberAccount').val()==""||$('#get_memberEmail').val()==""){
    		if($('#get_memberAccount').val()==""){
    			$('#l_input1-2').addClass("has-error has-feedback");
    		}
        	if($('#get_memberEmail').val()==""){
            	$('#l_input2-2').addClass("has-error has-feedback");
        	}
        	return;
    	}else{
    		 $.get('MemberServlet',$('#getpassword').serialize(),function(data){
       		   console.log(data);
       	   		});
    		 
    		 $('#signupfinished h4').text("密碼已寄至信箱");
    		//關閉列表 顯示成功畫面
    		setTimeout(function() {
				$('#mypassword').modal('hide');
              	$('#signupfinished').modal('show');
              }, 500);
    		
          	//一秒半後關閉成功畫面
         	 setTimeout(function() {
              	$('#signupfinished').modal('hide');
              }, 2000);
         	setTimeout(function() {
       		 location.reload();
            }, 3000);
    	}
    	
    });
});