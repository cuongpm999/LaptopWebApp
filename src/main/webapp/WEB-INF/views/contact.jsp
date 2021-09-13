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
		<h4 class="tieude-tp">LIÊN HỆ</h4>
		<div class="row card">
			<div class="col-md-12" style="padding: 10px;">
				<c:choose>
					<c:when test="${status=='success'}">
						<div class="alert alert-success">
							<strong>Success!</strong> Bạn đã gửi thông tin thành công!
						</div>
					</c:when>
					<c:when test="${status=='faile'}">
						<div class="alert alert-danger">
							<strong>Faile!</strong> Bạn gửi thông tin thất bại!
						</div>
					</c:when>
				</c:choose>
				<div class="card-body">
					<div class="panel-body">
						<form:form modelAttribute="contact" action="/contact-bind-data">
							<div class="form-group">
								<c:choose>
									<c:when test="${status=='faileNameNotNull'}">
										<div class="alert alert-danger">
											<strong>Faile!</strong> Họ tên không được để trống!
										</div>
									</c:when>
								</c:choose>
								<label class="required">Họ tên:</label>
								<form:input path="fullName" class="form-control"
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
								<form:input path="mobile" class="form-control"
									placeholder="Nhập số điện thoại" />
							</div>
							<div class="form-group">
								<label>Ghi chú:</label>
								<form:textarea path="content" class="form-control"
									placeholder="Chú thích của bạn" />
							</div>
							<div class="card-body">
								<button type="submit" class="btn btn-success">
									<i class="fas fa-download"></i> Nộp phiếu
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
