<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script>!window.jQuery && document.write("<script src='js/jquery-2.1.4.min.js'><\/script>")</script>




</head>
<body>

<form id="upload_form" style="text-align:center">
				
				
					<div style="width: 450px; margin:0px auto;padding:5px" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">Username</span>
					  <input id="memberId" type="text" name="memberId" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
					</div>
					
					<div style="width: 450px; margin:0px auto;padding:5px" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">ClassName </span>
					  <input id="videoClassName" type="text" name="videoClassName" class="form-control" placeholder="Ex:MV,NEWS..." aria-describedby="basic-addon1">
					</div>
					
					<div style="width: 450px; margin:0px auto;padding:5px" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">VideoName </span>
					  <input type="text" id="videoName" name="videoName" class="form-control" placeholder="VideoName " aria-describedby="basic-addon1">
					</div>
				
					<div style="width: 450px; margin:0px auto;padding:5px" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">Description</span>
					  <input id="videoDescription" type="text" name="videoDescription" class="form-control" placeholder="Write Something" aria-describedby="basic-addon1">
					</div>

					  <input value="Insert" type="hidden" name="prodaction" class="form-control" aria-describedby="basic-addon1">

					
</form>
<div style="width: 450px; margin:0px auto;padding:5px" class="input-group">
		<input style="width: 440px" id="submit1"  value="Insert" class="btn btn-primary btn-small" type="submit">
</div>
			
<script type="text/javascript">

$(function(){
    $('#submit1').click(function(){
//     	console.log($('form').serializeArray());
    	 $.get('VideoServlet',$('form').serialize(),function(data){
  		   console.log(data);
  	   })
	});
    
//     $('#submit1').click(function(){
//     		$.ajax({
//     			url:'VideoServlet',
//     			type:'get',
//     			data:$('form').serialize(),
//     			dataType:"json",
//     			success:function(datas){
//     				console.log(datas);
//     			}
//     		});
// 	});
    
    })
</script>
</body>
</html>