<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty alert}">
		<div class="alert alert-warning" role="alert">${alert}</div>
	</c:if>
	<!-- BEGIN CONTENT -->
	<div class="col-md-9 col-sm-9">
		<h1>Create an account</h1>
		<div class="content-form-page">
			<div class="row">
				<div class="col-md-7 col-sm-7">
					<form class="form-horizontal" role="form"
						action="${pageContext.request.contextPath}/register" method="post">
						<fieldset>
							<legend>Your personal details</legend>
							<div class="form-group">
								<label for="firstname" class="col-lg-4 control-label">First Name <span class="require">*</span>
								</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" id="username"
										name="firstName" placeholder="Enter your username" required>
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-lg-4 control-label">Last
									Name <span class="require">*</span>
								</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" id="fullname"
										name="lastName" placeholder="Enter your fullname" required>

								</div>
							</div>
							<div class="form-group">
								<label for="email" class="col-lg-4 control-label">Email
									<span class="require">*</span>
								</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" id="email" name="email"
										placeholder="Enter your email" required>
								</div>
							</div>
							<div class="form-group">
								<label for="email" class="col-lg-4 control-label">Phone
									Number <span class="require">*</span>
								</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" id="phone" name="phone"
										placeholder="Enter your phone" required>
								</div>
							</div>
							<div class="form-group">
								<label for="email" class="col-lg-4 control-label">Address <span class="require">*</span>
								</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" id="phone" name="address"
										placeholder="Enter your phone" required>
								</div>
							</div>
							<div class="form-group">
								<label for="email" class="col-lg-4 control-label">Role <span class="require">*</span>
								</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" id="phone" name="role"
										placeholder="Enter your phone" required>
								</div>
							</div>
						</fieldset>
						<fieldset>
							<legend>Your password</legend>
							<div class="form-group">
								<label for="password" class="col-lg-4 control-label">Password
									<span class="require">*</span>
								</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" id="password"
										name="password" placeholder="Enter your password" required>
								</div>
							</div>
							<div class="form-group">
								<label for="confirm-password" class="col-lg-4 control-label">Confirm
									password <span class="require">*</span>
								</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" id="confirm-password"
										placeholder="Retype your password" required>
								</div>
							</div>
						</fieldset>
						<fieldset>
							<legend>Newsletter</legend>
							<div class="checkbox form-group">
								<label>
									<div class="col-lg-4 col-sm-4">Singup for Newsletter</div>
									<div class="col-lg-8 col-sm-8">
										<input type="checkbox">
									</div>
								</label>
							</div>
						</fieldset>
						<div class="row">
							<div
								class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
								<button type="submit" class="btn btn-primary">Create an
									account</button>
								<button type="button" class="btn btn-default">Cancel</button>
							</div>
						</div>
					</form>
				</div>
				<div class="col-md-4 col-sm-4 pull-right">
					<div class="form-info">
						<h2>
							<em>Important</em> Information
						</h2>
						<p>Lorem ipsum dolor ut sit ame dolore adipiscing elit, sed
							sit nonumy nibh sed euismod ut laoreet dolore magna aliquarm erat
							sit volutpat. Nostrud exerci tation ullamcorper suscipit lobortis
							nisl aliquip commodo quat.</p>

						<p>Duis autem vel eum iriure at dolor vulputate velit esse vel
							molestie at dolore.</p>

						<button type="button" class="btn btn-default">More
							details</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END CONTENT -->

</body>
</html>