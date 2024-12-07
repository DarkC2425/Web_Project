<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h3 class="text-center mb-4">Đăng Nhập</h3>

                <!-- Hiển thị thông báo nếu có lỗi -->
                <c:if test="${not empty alert}">
                    <div class="alert alert-danger" role="alert">
                        ${alert}
                    </div>
                </c:if>

                <!-- Form đăng nhập -->
                <form action="/login" method="post">
                    <!-- Email -->
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email" required>
                    </div>

                    <!-- Mật khẩu -->
                    <div class="mb-3">
                        <label for="password" class="form-label">Mật khẩu</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu" required>
                    </div>

                    <!-- Ghi nhớ đăng nhập -->
                    <div class="form-check mb-3">
                        <input type="checkbox" class="form-check-input" id="remember" name="remember">
                        <label class="form-check-label" for="remember">Ghi nhớ đăng nhập</label>
                    </div>

                    <!-- Nút đăng nhập -->
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Đăng Nhập</button>
                    </div>
                </form>

                <!-- Liên kết quên mật khẩu hoặc đăng ký -->
                <div class="text-center mt-3">
                    <a href="/forgot-password">Quên mật khẩu?</a> |
                    <a href="/DoAnCuoiKy/register">Đăng ký tài khoản</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</html>
