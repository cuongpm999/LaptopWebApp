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
	href="${pageContext.request.contextPath}/css/manage.css">
<!-- ----------------- -->

<title>Laptop Project</title>
</head>

<body>

	<div id="fb-root"></div>
	<script async defer crossorigin="anonymous"
		src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v8.0"
		nonce="ChmFo6ST"></script>

	<!-- MAIN -->
	<div id="main">
		<%@ include file="/WEB-INF/views/admin/admin_header.jsp"%>
		<div class="page-container">
			<div class="header-container">
				<nav class="navbar">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbardrop"
							data-toggle="dropdown"> <c:choose>
									<c:when test="${empty userDis.userAttachment.name}">
										<img class="icon" alt="user" src="/img/anh-dai-dien-1.jpg">
									</c:when>
									<c:when test="${not empty userDis.userAttachment.name}">
										<img class="icon" alt="user"
											src="/files_users/${userDis.userAttachment.name }">
									</c:when>
								</c:choose> <span class="text-icon">${userDis.username }</span>
						</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="/user-details"><i class="far fa-user"></i>Profile</a>
								<a class="dropdown-item" href="/logout"><i
									class="fas fa-power-off"></i>Logout</a>
							</div></li>
					</ul>
				</nav>
			</div>

			<div class="main-content">
				<div class="row">
					<div class="col-md-12">
						<div class="form-add">
							<c:choose>
								<c:when test="${status=='success'}">
									<div class="alert alert-success">
										<strong>Success!</strong> Bạn đã sửa thành công!
									</div>
								</c:when>
								<c:when test="${status=='faile'}">
									<div class="alert alert-danger">
										<strong>Faile!</strong> Bạn đã thêm thất bại!

									</div>
								</c:when>
							</c:choose>

							<div class="card-body">
								<div class="panel-body">
									<form:form modelAttribute="laptop" action="/admin/edit-laptop"
										enctype="multipart/form-data">
										<form:hidden path="id" />
										<div class="form-group">
											<label class="required" for="txtInput">Hãng sản xuất:</label>
											<form:select class="form-control"
												path="laptopManufacturer.id">
												<form:options items="${laptopManufacturer}" itemValue="id"
													itemLabel="name" />
											</form:select>
										</div>
										<div class="form-group">
											<label class="required">Tên:</label>
											<form:input path="name" class="form-control" />
										</div>
										<div class="form-group">
											<label class="required">Bộ vi xử lý:</label>
											<form:input path="cpu" class="form-control" />
										</div>
										<div class="form-group">
											<label class="required">Ổ cứng:</label>
											<form:input path="oCung" class="form-control" />
										</div>
										<div class="form-group">
											<label class="required">Bộ nhớ trong:</label>
											<form:input path="ram" class="form-control" />
										</div>
										<div class="form-group">
											<label class="required">VGA:</label>
											<form:input path="vga" class="form-control" />
										</div>
										<div class="form-group">
											<label class="required">Màn hình:</label>
											<form:input path="manHinh" class="form-control" />
										</div>
										<div class="form-group">
											<label class="required">Giá tiền:</label>
											<form:input path="price" class="form-control" />
										</div>
										<div class="form-group">
											<label class="required">Bảng thông số kĩ thuật:</label>
											<form:textarea path="table_content" class="form-control"
												rows="5" id="summernote"></form:textarea>
										</div>
										<div class="form-group">
											<label class="required">Số lượng nhập:</label>
											<form:input path="soLuongNhap" class="form-control" />
										</div>

										<div class="form-group">
											<label>Khuyến mại:</label>
											<form:input path="khuyenMai" class="form-control" />
										</div>

										<div class="form-group">
											<label>Link Video:</label>
											<form:input path="video" class="form-control" />
										</div>
										<div class="form-group">
											<label class="required">Hình ảnh:&ensp;</label> <input
												type="file" name="laptopImage" multiple="multiple" />
										</div>
									
										<button type="submit" class="btn btn-success">
											<i class="fas fa-download"></i> Save
										</button>

									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="footer">
				©
				<c:set var="now" value="<%=new java.util.Date()%>" />
				<fmt:formatDate pattern="yyyy" value="${now}" />
				Copyright: <a href="https://www.facebook.com/cuongphamptit999/">
					CuongPham.com</a>
			</div>
		</div>
	</div>
	<!-- --- -->

</body>


</html>
