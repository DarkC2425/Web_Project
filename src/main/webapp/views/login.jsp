<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<%@include file="../Components/common_css_js.jsp"%>
<style>
label{
	font-weight: bold;
}

.btn-outline-primary {
	background-color: white;
	color: #33cccc;
	border: 2px solid #33cccc;
}

.btn-outline-primary:hover {
	background-color: #33cccc;
        border: #33cccc;
	color: white;
}
</style>
</head>
<body >  


	<div class="container-fluid">
		<div class="row mt-5">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body px-5">

						<div class="container text-center">
							<img src="../Images/login.png" style="max-width: 100px;"
								class="img-fluid">
						</div>
						<h3 class="text-center">Đăng nhập</h3>
						<%@include file="../Components/alert_message.jsp" %>
						
						<!--login-form-->
						<form id="login-form" action="../LoginController" method="post">
							<input type="hidden" name="login" value="user"> 
							<div class="mb-3">
								<label class="form-label">Email</label> <input
									type="email" name="user_email" placeholder="Nhập địa chỉ Email"
									class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Password</label>
								<input type="password" name="user_password"
									placeholder="Nhập password" class="form-control" required>
							</div>
							<div id="login-btn" class="container text-center">
								<button type="submit" class="btn btn-outline-primary me-3">Đăng nhập</button>
							</div>
						</form>
						<div class="mt-3 text-center">
							<h6><a href="forgot_password.jsp" style="text-decoration: none">Quên mật khẩu?</a></h6>
							<h6>
								Bạn không có tài khoản?<a href="register.jsp"
									style="text-decoration: none"> Đăng ký</a>
							</h6>
						</div>
					</div>  

				</div>
			</div>
		</div>
	</div>
</body>
</html>