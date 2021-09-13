<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- sử dụng taglibs của spring để bind-data từ end-point trả về. -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- tích hợp jstl vào jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="description"
	content="ban laptop uy tin, chat luong, dich vu tot">
<meta name="keywords"
	content="laptop, asus, dell, hp, lenovo, acer, apple, msi, lg">
<meta name="author" content="CuongPham">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS & JAVA_SCRIPT -->
<%@ include file="/WEB-INF/views/layout/includer.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/form.css">
<!-- ----------------- -->

<title>Laptop Project</title>
</head>

<body>

	<div id="fb-root"></div>
	<script async defer crossorigin="anonymous"
		src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v8.0"
		nonce="ChmFo6ST"></script>

	<!-- HEADER -->
	<%@ include file="/WEB-INF/views/layout/header.jsp"%>
	<!-- ------ -->

	<!-- MAIN -->
	<div id="main" class="container lienhe">
		<h4 class="tieude-tp">ĐĂNG KÍ</h4>
		<div class="row card">
			<div class="col-md-12" style="padding: 10px;">
				<c:choose>
					<c:when test="${status=='success'}">
						<div class="alert alert-success">
							<strong>Success!</strong> Bạn đăng kí tài khoản thành công!
						</div>
					</c:when>
					<c:when test="${status=='faile'}">
						<div class="alert alert-danger">
							<strong>Faile!</strong> Bạn đăng kí tài khoản thất bại!
						</div>
					</c:when>
				</c:choose>
				<div class="card-body">
					<div class="panel-body">
						<form:form modelAttribute="userInfo" action="/register" enctype="multipart/form-data">
							<div class="form-group">
								<c:choose>
									<c:when test="${status=='faileNameNotNull'}">
										<div class="alert alert-danger">
											<strong>Faile!</strong> Họ tên không được để trống!
										</div>
									</c:when>
								</c:choose>
								<label class="required">Họ tên:</label>
								<form:input path="fullname" class="form-control"
									placeholder="Nhập họ tên" />
							</div>
							<div class="form-group">
								<c:choose>
									<c:when test="${status=='faileEmailNotFormat'}">
										<div class="alert alert-danger">
											<strong>Faile!</strong> Email không đúng định dạng!
										</div>
									</c:when>
								</c:choose>
								<label class="required">E-mail:</label>
								<form:input path="email" class="form-control"
									placeholder="Nhập email" />
							</div>
							<div class="form-group">
								<c:choose>
									<c:when test="${status=='faileMobileNotFormat'}">
										<div class="alert alert-danger">
											<strong>Faile!</strong> Số điện thoại không đúng định dạng!
										</div>
									</c:when>
								</c:choose>
								<label class="required">Số điện thoại:</label>
								<form:input path="phonenumber" class="form-control"
									placeholder="Nhập số điện thoại" />
							</div>
							<div class="form-group">
								<c:choose>
									<c:when test="${status=='faileAddressNotNull'}">
										<div class="alert alert-danger">
											<strong>Faile!</strong> Địa chỉ không được để trống!
										</div>
									</c:when>
								</c:choose>
								<label class="required">Địa chỉ:</label>
								<form:input path="address" class="form-control"
									placeholder="Nhập địa chỉ" />
							</div>
							<div class="form-group">
								<c:choose>
									<c:when test="${status=='faileTenBiTrung'}">
										<div class="alert alert-danger">
											<strong>Faile!</strong> Tên tài khoản đã được sử dụng!
										</div>
									</c:when>
									<c:when test="${status=='faileTenUserNotNull'}">
										<div class="alert alert-danger">
											<strong>Faile!</strong> Tên tài khoản không được để trống!
										</div>
									</c:when>
								</c:choose>
								<label class="required">Tên tài khoản:</label>
								<form:input path="username" class="form-control"
									placeholder="Nhập tên tài khoản" />
							</div>
							<div class="form-group">
								<c:choose>
									<c:when test="${status=='failePassNotNull'}">
										<div class="alert alert-danger">
											<strong>Faile!</strong> Mật khẩu không được để trống!
										</div>
									</c:when>
								</c:choose>
								<label class="required">Mật khẩu:</label>
								<form:input path="password" class="form-control"
									placeholder="Nhập mật khẩu" />
							</div>
							
							<div class="form-group">
								<label>Chọn ảnh:&ensp;</label> <input
									type="file" name="userImage" />
							</div>
							
							<div class="card-body">
								<button type="submit" class="btn btn-success">
									<i class="fas fa-download"></i> Đăng kí
								</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ---- -->

	<!--  FOOTER -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!-- ------- -->
</body>


</html>
