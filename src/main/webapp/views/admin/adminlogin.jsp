<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập Admin</title>
<%@include file="../../Components/common_css_js.jsp"%>
<style>
label {
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
<body>  
	<div class="container-fluid">
		<div class="row mt-5">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-header px-5">
						<div class="container text-center">
							<img src="../../Images/admin.png" style="max-width: 100px;"
								class="img-fluid">
						</div>
						<h3 class="text-center">Đăng nhập</h3>
						<%@include file="../../Components/alert_message.jsp"%>
					</div>
					<div class="card-body px-5">
						<!--login-form-->
						<form id="login-form" action="../../LoginController" method="post">
							<input type="hidden" name="login" value="admin"> 
							
							<div class="mb-3">
								<label class="form-label">Email</label> <input type="email"
									name="email" placeholder="Địa chỉ Email" class="form-control"
									required>
							</div>
							<div class="mb-2">
								<label class="form-label">Mật khẩu</label> <input
									type="password" name="password"
									placeholder="Nhập mật khẩu" class="form-control" required>
							</div>

							<div id="login-btn" class="container text-center mt-5">
								<button type="submit" class="btn btn-outline-primary me-3">Đăng nhập</button>
							</div>
						</form>
					</div>  
				</div>
			</div>
		</div>
	</div>

</body>
</html>