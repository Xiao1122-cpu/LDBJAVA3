<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa tin tức</title>
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
							<a class="nav-link active" href="${pageContext.request.contextPath}/phong-vien/tin-tuc">Quản lý tin tức</a>
						</li>
						
						<li class="nav-item dropdown">
							<a	class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"> ${sessionScope.user!= null? sessionScope.user.fullName :"Tài Khoản"} </a>
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
					<h3>Chỉnh sửa tin tức</h3>
				</div>
				<div class="card-body">
					<label class="text-success">${message}</label>
					<label class="text-danger">${error}</label>
					<form action="${pageContext.request.contextPath}/phong-vien/tin-tuc/chinh-sua?id=${news.id}" method="post" enctype="multipart/form-data">
						<div class="mb-3">
						  <label for="name" class="form-label">Tiêu đề</label>
						  <input type="text" class="form-control" id="title" name="title" value="${news.title}" >
						</div>
						<div class="mb-3">
						  <label for="name" class="form-label">Ảnh</label>
						  <img src="${pageContext.request.contextPath}/images/${news.image}" width="50" height="50"/>
						  <input type="file" class="form-control" id="image" name="image">
						</div>
						<div class="mb-3">
						  <label for="name" class="form-label">Loại tin</label>
						  <select class="form-control" id="categoryId" name="categoryId">
						  	<option value="0">Chọn loại tin</option>
						  	<c:forEach items="${categories}" var="cate">
						  		<option value="${cate.id }" ${cate.id==news.categoryId?'selected':'' }>${cate.name }</option>
						  	</c:forEach>
						  </select>
						</div>
						<div class="mb-3">
						  <label for="name" class="form-label">Nội dung</label>
						  <textarea class="form-control" id="content" name="content">${news.content}</textarea>
						</div>
						<button class="btn btn-primary">Lưu</button>
						<a href="${pageContext.request.contextPath}/phong-vien/tin-tuc" class="btn btn-secondary">Quay lại</a>
					</form>
				</div>
			</div>
		</main>
		<footer>
		
		</footer>
	</div>
</body>
</html>