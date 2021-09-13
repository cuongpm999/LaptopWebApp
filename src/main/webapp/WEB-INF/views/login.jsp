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
	href="${pageContext.request.contextPath}/css/login.css">
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
	<!-- ----- -->
	<!-- MAIN -->
	<div id="main">
		<div class="header-w3l">
			<h1 class="mt-5">Đăng nhập</h1>
		</div>
		<div class="main-w3layouts-agileinfo">
			<!--form-stars-here-->
			<div class="wthree-form">
				<h2>Vui lòng điền để đăng nhập</h2>
				<a class="login-google"
					href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=https://computer-cuongpham.herokuapp.com/login-google&response_type=code
    &client_id=312227553133-icpkjofc6crhvtgdpje818q7ksvbn5qr.apps.googleusercontent.com&approval_prompt=force">
					<div class="btn-gg">
						<div class="btn-gg-img">
							<img src="/img/icons8-google-48.png" alt="logo">
						</div>
						<div class="btn-gg-text">
							Tiếp tục với <b>Gmail</b>
						</div>
					</div>
				</a> <a class="login-facebook"
					href="https://www.facebook.com/dialog/oauth?client_id=463826531507215&redirect_uri=https://computer-cuongpham.herokuapp.com/login-facebook">
					<div class="btn-f">
						<div class="btn-f-img">
							<img src="/img/icons8-facebook-48.png" alt="logo">
						</div>
						<div class="btn-f-text">
							Tiếp tục với <b>Facebook</b>
						</div>
					</div>
				</a>
				<div class="separator">Hoặc</div>
				<form action="/login" method="post">
					<div class="form-sub-w3">
						<input type="text" name="username" placeholder="Tên tài khoản"
							required />
						<div class="icon-w3">
							<i class="fa fa-user" aria-hidden="true"></i>
						</div>
					</div>
					<div class="form-sub-w3">
						<input type="password" name="password" placeholder="Mật khẩu"
							required />
						<div class="icon-w3">
							<i class="fa fa-unlock-alt" aria-hidden="true"></i>
						</div>
					</div>
					<div class="submit-agileits">
						<input type="submit" name="submit" value="Đăng nhập">
					</div>
				</form>
				<h2 style="margin-top: 30px;">Bạn chưa có tài khoản <br><a href="/register">Đăng kí ngay</a></h2>
			</div>

		</div>
	</div>

	<!--  FOOTER -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!-- ------- -->

</body>


</html>
