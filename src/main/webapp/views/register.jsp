<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="form-container">
					<h3 class="text-center mb-4">Đăng Ký</h3>
					<form>
						<!-- Avatar -->
						<div class="text-center mb-4">
							<label for="avatar" class="form-label">Ảnh đại diện</label> <input
								type="file" class="form-control" id="avatar" name="avatar"
								accept="image/*" />
						</div>

						<!-- Họ và Tên -->
						<div class="row mb-3">
							<div class="col">
								<label for="firstName" class="form-label">Họ</label> <input
									type="text" class="form-control" id="firstName"
									name="firstName" placeholder="Nhập họ" required />
							</div>
							<div class="col">
								<label for="lastName" class="form-label">Tên</label> <input
									type="text" class="form-control" id="lastName" name="lastName"
									placeholder="Nhập tên" required />
							</div>
						</div>

						<!-- Tên đăng nhập -->
						<div class="mb-3">
							<label for="email" class="form-label">Tên đăng nhập</label> <input
								type="email" class="form-control" id="email" name="email"
								placeholder="Nhập tên đăng nhập" required />
						</div>

						<!-- Số điện thoại -->
						<div class="mb-3">
							<label for="phone" class="form-label">Số điện thoại</label> <input
								type="tel" class="form-control" id="phone" name="phone"
								placeholder="Nhập số điện thoại" required />
						</div>

						<!-- Địa chỉ -->
						<div class="mb-3">
							<label for="address" class="form-label">Địa chỉ</label> <input
								type="text" class="form-control" id="address" name="address"
								placeholder="Nhập địa chỉ" required />
						</div>

						<!-- Mật khẩu -->
						<div class="mb-3">
							<label for="password" class="form-label">Mật khẩu</label> <input
								type="text" class="form-control" id="password" name="password"
								placeholder="Nhập mật khẩu" required />
						</div>

						<!-- Nhập lại mật khẩu -->
						<div class="mb-3">
							<label for="re-password" class="form-label">Nhập lại mật
								khẩu</label> <input type="text" class="form-control" id="re-password"
								name="re-password" placeholder="Nhập lại mật khẩu" required />
						</div>

						<!-- Nút đăng ký -->
						<div class="text-center">
							<button type="submit" class="btn btn-primary w-100">
								Đăng Ký</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
