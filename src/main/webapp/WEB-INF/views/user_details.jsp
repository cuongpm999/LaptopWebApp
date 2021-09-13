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
	href="${pageContext.request.contextPath}/css/user-detail.css">
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
	<div id="main" class="container">
		<div class="show-info">
			<div class="row">
				<div class="col-md-4">
					<c:choose>
						<c:when test="${empty userDis.userAttachment.name}">
							<img alt="user" src="/img/anh-dai-dien-1.jpg">
						</c:when>
						<c:when test="${not empty userDis.userAttachment.name}">
							<img alt="user"
								src="/files_users/${userDis.userAttachment.name }">
						</c:when>
					</c:choose>
				</div>
				<div class="col-md-8">
					<h2>Thông tin cá nhân</h2>
					<table class="table-borderless">
						<tbody>
							<tr>
								<td class="noidung">Họ và tên</td>
								<td class="noidung1">${userDis.fullname }</td>
							</tr>
							<tr>
								<td class="noidung">Số điện thoại</td>
								<td class="noidung1">${userDis.phonenumber }</td>
							</tr>
							<tr>
								<td class="noidung">Email</td>
								<td class="noidung1">${userDis.email }</td>
							</tr>
						</tbody>
					</table>
					<a href="/edit-user" class="btn btn-primary"><i
						class="fas fa-edit"></i> Edit</a>
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
