<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" import="java.lang.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Setting Button的形狀 -->
<link rel="stylesheet" href="css/Setting.css">
 
<!-- </head> -->

<!-- <body> -->
<!-- Create LiveShow -->
<center>
<!-- <button style="border-style: outset;border-color:black" id="createlive" type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#CreateModal"> -->
<!--   Create Your LiveShow -->
<!-- </button> -->

</center>

<!-- Modal -->
<div class="modal fade" id="settingModal" tabindex="-1" role="dialog" aria-labelledby="demoSetting" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" style="text-align: center" id="demoSetting">設定</h4>
			</div>
			
			<div class="modal-body">
				<fieldset style="text-align: center">
					<form class="form-horizontal" role="form" action="<c:url value='/updateInfo'/>" method="POST">
					
						<div class="form-group">
							<label for="account" class="col-sm-3 control-label">帳號</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="account" name="memberAccount" placeholder="帳號無法變更" disabled />
							</div>
						</div>

						<div class="form-group">
							<label for="pwd1" class="col-sm-3 control-label">密碼</label>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="pwd1" name="memberPassword" placeholder="輸入密碼" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="pwd2" class="col-sm-3 control-label">確認密碼</label>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="pwd2" name="memberPassword" placeholder="再次輸入密碼" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="memberName" class="col-sm-3 control-label">姓名</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="memberName" placeholder="輸入姓名" />
							</div>
						</div>

						<div class="form-group">
							<label for="email" class="col-sm-3 control-label">電子郵件</label>
							<div class="col-sm-8">
								<input type="email" class="form-control" id="email" placeholder="修改電子郵件" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="nickName" class="col-sm-3 control-label">暱稱</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="nickName" placeholder="輸入暱稱" />
							</div>
						</div>

						<div class="form-group">
							<label for="birthday" class="col-sm-3 control-label">生日</label>
							<div class="col-sm-8">
								<input type="date" class="form-control" id="birthday" />
							</div>
						</div>

						<div class="form-group">
							<label for="photo1" class="col-sm-3 control-label">照片</label>
							<div class="col-sm-8">
								<input type="file" class="btn btn-default btn-file" id="photo1" />
							</div>
						</div>
			   			
					</form>
					
				</fieldset>
			</div>

			<div class="modal-footer" style="text-align:center">
				<button type="submit" class="btn btn-primary" value="submit">提交</button>
				<input type="reset" class="btn btn-default" value="重設">
			</div>
			

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
        <h4 class="modal-title">建立實況成功！</h4>
      </div>
    </div><!-- /.modal-content -->
    
    
    </div>
  </div>
</div>



<!-- </body> -->
<!-- </html> -->