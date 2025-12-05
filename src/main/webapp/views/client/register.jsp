<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<header>
			<h1>
				<img alt="" src="${pageContext.request.contextPath}/images/logo.png" width="150">
			</h1>
			<hr>
		</header>

		<nav class="navbar navbar-expand-lg bg-body-tertiary">
			<div class="container-fluid">
				<a class="navbar-brand" href="#"></a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item">
							<a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/trang-chu">Trang chủ</a>
						</li>
						<li class="nav-item">
							<a class="nav-link " href="${pageContext.request.contextPath}/admin/categories">Quản lý loại tin</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.request.contextPath}/admin/news">Quản lý tin tức</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.request.contextPath}/admin/users">Quản lý phóng viên</a>
						</li>
						<li class="nav-item dropdown">
							<a	class="nav-link dropdown-toggle active"  href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"> ${sessionScope.user!= null? sessionScope.user.fullName :"Tài Khoản"} </a>
							<ul class="dropdown-menu">
								<c:if test="${sessionScope.user== null}">
									<li><a class="dropdown-item" href="${pageContext.request.contextPath}/dang-nhap">Đăng nhập</a></li>
									<li><a class="dropdown-item" href="${pageContext.request.contextPath}/dang-ky">Đăng ký</a></li>
									<li><a class="dropdown-item" href="${pageContext.request.contextPath}/quen-mat-khau">Quên mật khẩu</a></li>
									
								</c:if>
								
								<c:if test="${sessionScope.user!= null}">
									<li><a class="dropdown-item" href="${pageContext.request.contextPath}/edit-profile">Thông tin cá nhân</a></li>
									<li><a class="dropdown-item" href="${pageContext.request.contextPath}/change-pass">Đổi mật khẩu</a></li>
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/logout">Đăng xuất</a></li>
								</c:if>
							</ul>
						</li>
						
					</ul>
				
				</div>
			</div>
		</nav>
		<main>
			<div class="clearfix mt-2"></div>
			<div class="card">
				<div class="card-header">
					<h3 class="text-center">Đăng ký</h3>
				</div>
				<div class="card-body">
					<label class="text-danger">${message }</label>
					<form action="${pageContext.request.contextPath}/dang-ky" method="post">
						<div class="mb-3">
						  <label for="email" class="form-label">Email: </label>
						  <input type="email" class="form-control" id="email" name="email" placeholder="example@gmail.com" >
						</div>
						<div class="mb-3">
						  <label for="password" class="form-label">Mật khẩu: </label>
						  <input type="password" class="form-control" id="password" name=password placeholder="Mật khẩu" >
						</div>
						<div class="mb-3">
							<label class="text-danger">${error }</label>
						  <label for="repeatPassword" class="form-label">Xác nhận mật khẩu: </label>
						  <input type="password" class="form-control" id="repeatPassword" name=repeatPassword placeholder="Xác nhận mật khẩu" >
						</div>
						<div class="mb-3">
						  <label for="fullname" class="form-label">Họ và tên: </label>
						  <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Họ và tên" >
						</div>
						<div class="mb-3">
						  <label for="gender" class="form-label">Giới tính: </label>
						  <div class="form-check form-check-inline">
							  <input class="form-check-input" type="radio" name="gender" id="gender1" value="true">
							  <label class="form-check-label" for="gender1">Nam</label>
							</div>
							<div class="form-check form-check-inline">
							  <input class="form-check-input" type="radio" name="gender" id="gender2" value="false" checked>
							  <label class="form-check-label" for="gender2">Nữ</label>
							</div>
						</div>
						<div class="mb-3">
						  <label for="phone" class="form-label">Số điện thoại: </label>
						  <input type="text" class="form-control" id="phone" name="phone" placeholder="Số điện thoại" >
						</div>
						<button class="btn btn-primary">Đăng ký</button>
						
					</form>
				</div>
			</div>
		</main>
		<footer>
		
		</footer>
	</div>
</body>
</html>