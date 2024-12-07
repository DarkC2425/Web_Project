<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="form-container">
					<h3 class="text-center mb-4">Đăng Ký</h3>
					<form id="signupForm">
						<!-- Avatar -->
						<div class="text-center mb-4">
							<label for="avatar" class="form-label">Ảnh đại diện</label> <input
								type="file" class="form-control" id="avatar" accept="image/*">
						</div>

						<!-- Họ và Tên -->
						<div class="row mb-3">
							<div class="col">
								<label for="firstName" class="form-label">Họ</label> <input
									type="text" class="form-control" id="firstName"
									placeholder="Nhập họ" required>
							</div>
							<div class="col">
								<label for="lastName" class="form-label">Tên</label> <input
									type="text" class="form-control" id="lastName"
									placeholder="Nhập tên" required>
							</div>
						</div>

						<!-- Tên đăng nhập -->
						<div class="mb-3">
							<label for="username" class="form-label">Email</label> <input
								type="email" class="form-control" id="email"
								placeholder="Nhập địa chỉ email" required>
						</div>

						<!-- Số điện thoại -->
						<div class="mb-3">
							<label for="phone" class="form-label">Số điện thoại</label> <input
								type="tel" class="form-control" id="phone"
								placeholder="Nhập số điện thoại" required>
						</div>

						<!-- Địa chỉ -->
						<div class="mb-3">
							<label for="address" class="form-label">Địa chỉ</label> <input
								type="text" class="form-control" id="address"
								placeholder="Nhập địa chỉ" required>
						</div>

						<!-- Mật khẩu -->
						<div class="mb-3">
							<label for="password" class="form-label">Mật khẩu</label> <input
								type="password" class="form-control" id="password"
								placeholder="Nhập mật khẩu" required>
							<div id="passwordHelp" class="form-text text-danger"></div>
						</div>

						<!-- Nhập lại mật khẩu -->
						<div class="mb-3">
							<label for="confirmPassword" class="form-label">Nhập lại
								mật khẩu</label> <input type="password" class="form-control"
								id="confirmPassword" placeholder="Nhập lại mật khẩu" required>
							<div id="confirmPasswordHelp" class="form-text text-danger"></div>
						</div>

						<!-- Nút đăng ký -->
						<div class="text-center">
							<button type="submit" class="btn btn-primary w-100"><p color="#fff">Đăng
								Ký</p></button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		document
				.getElementById('signupForm')
				.addEventListener(
						'submit',
						function(event) {
							const password = document
									.getElementById('password').value;
							const confirmPassword = document
									.getElementById('confirmPassword').value;
							const passwordHelp = document
									.getElementById('passwordHelp');
							const confirmPasswordHelp = document
									.getElementById('confirmPasswordHelp');

							// Reset error messages
							passwordHelp.textContent = '';
							confirmPasswordHelp.textContent = '';

							// Validate password
							const passwordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
							if (!passwordRegex.test(password)) {
								event.preventDefault(); // Prevent form submission
								passwordHelp.textContent = 'Mật khẩu phải chứa ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt.';
								return;
							}

							// Validate confirm password
							if (password !== confirmPassword) {
								event.preventDefault(); // Prevent form submission
								confirmPasswordHelp.textContent = 'Mật khẩu và nhập lại mật khẩu không trùng khớp.';
								return;
							}
						});
	</script>