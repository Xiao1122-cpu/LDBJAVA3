<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
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
						<c:forEach items="${categories}" var="cate">
							<li class="nav-item">
								<a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/danh-muc?id=${cate.id}">${cate.name}</a>
							</li>
						</c:forEach>
						
						
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
									<c:if test="${!sessionScope.user.role}">
										<li><a class="dropdown-item" href="${pageContext.request.contextPath}/phong-vien/tin-tuc">Quản lý tin tức</a></li>
									</c:if>
									
									<c:if test="${sessionScope.user.role}">
										<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/categories">Quản lý loại tin</a></li>
										<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/news">Quản lý tin tức</a></li>
										<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/users">Quản lý phóng viên</a></li>
									</c:if>
									
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item" href="${pageContext.request.contextPath}/dang-xuat">Đăng xuất</a></li>
								</c:if>
								
							</ul>
						</li>
						
					</ul>
				
				</div>
			</div>
		</nav>
		<main>
			<div class="row">
				<div class="col-md-8">
					<div class="row">
						<c:forEach items="${list }" var = "news">
							<div class="col-md-6 mt-2">
								<div class="card">
								  <img src="${pageContext.request.contextPath}/images/${news.image}" class="card-img-top" alt="..." style="max-height:160px;">
								  <div class="card-body">
								    <h5 class="card-title">${news.title }</h5>
								    <p class="card-text"></p>
								    <a href="${pageContext.request.contextPath}/tin-tuc?id=${news.id}" class="btn btn-primary">Chi tiết</a>
								  </div>
								</div>
							</div>
							
						</c:forEach>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
					  <div class="card-header">
					    Bài viết xem nhiều
					  </div>
					  <div class="card-body">
					    <c:forEach items="${listTop5Views }" var = "news">
							<div class="card mt-2">
							  <img src="${pageContext.request.contextPath}/images/${news.image}" class="card-img-top" alt="..." style="max-height:160px;">
							  <div class="card-body">
							    <h5 class="card-title"><a href="${pageContext.request.contextPath}/tin-tuc?id=${news.id}" >${news.title }</a></h5>
							    <p class="card-text"></p>
							  </div>
							</div>
						</c:forEach>
					  </div>
					</div>
					<div class="card mt-5">
					  <div class="card-header">
					    Bài viết mới
					  </div>
					  <div class="card-body">
					    <c:forEach items="${listTop5 }" var = "news">
							<div class="card mt-2">
							  <img src="${pageContext.request.contextPath}/images/${news.image}" class="card-img-top" alt="..." style="max-height:160px;">
							  <div class="card-body">
							    <h5 class="card-title"><a href="${pageContext.request.contextPath}/tin-tuc?id=${news.id}" >${news.title }</a></h5>
							    <p class="card-text"></p>
							  </div>
							</div>
						</c:forEach>
					  </div>
					</div>
				</div>
			</div>
		</main>
		<footer>
		
		</footer>
	</div>
</body>
</html>