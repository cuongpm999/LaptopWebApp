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
	<div class="container" style="background: white; margin-top: 60px;">
		<h1 style="text-align: center;">Send Bill Mail Error</h1>
		<ul style="list-style-type: circle; font-size: 18px; font-weight: 600; margin-top: 20px;">
			<li>Lỗi này là do chưa bật captcha của gmail để xác nhận quyền
				truy cập của bên thứ 3.</li>
			<li>Khi bạn bấm thanh toán nó sẽ gửi mail thông tin hóa đơn
				về Gmail của bạn đã đăng kí.</li>
			<li>Vì vậy muốn tiếp tục vui lòng liên hệ với tôi - Phạm Cường
				qua Facebook hoặc qua Chat Messenger để tôi bật captcha lên nhé!</li>
		</ul>

	</div>

	<!-- ---- -->


	<!--  FOOTER -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!-- ------- -->

</body>


</html>