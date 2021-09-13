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
	href="${pageContext.request.contextPath}/css/hoadon.css">
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
		<div class="sidebar">
			<div class="sidebar-inner">
				<div class="sidebar-logo">
					<a class="sidebar-link" href="/"> <img class="icon"
						src="/img/logo.png">
						<h5 class="logo-text">Admin Manage</h5>
					</a>
				</div>

				<nav class="navbar scrollbar" id="style-1">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/admin/manage"><i
								class="fas fa-home"></i>Dashboard</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbardrop"
							data-toggle="dropdown"><i class="fas fa-list"></i>Tables</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="/admin/list-user">Account</a> <a
									class="dropdown-item" href="/admin/list-banner">Banner</a> <a
									class="dropdown-item" href="/admin/list-contact">Contact</a> <a
									class="dropdown-item" href="/admin/list-laptop">Laptop</a> <a
									class="dropdown-item" href="/admin/list-bill">Bill</a>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbardrop"
							data-toggle="dropdown"><i class="fas fa-pencil-alt"></i></i>Forms</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="/admin/add-laptop">Laptop</a> <a
									class="dropdown-item" href="/admin/add-banner">Banner</a>
							</div></li>
						<li class="nav-item"><a class="nav-link" href="#"><i
								class="fas fa-envelope"></i>Email</a></li>
						<li class="nav-item"><a class="nav-link" href="#"><i
								class="fas fa-map"></i>Maps</a></li>
						<li class="nav-item"><a class="nav-link" href="#"><i
								class="far fa-comment-dots"></i>Chat</a></li>
						<li class="nav-item"><a class="nav-link" href="#"><i
								class="far fa-calendar-alt"></i>Calendar</a></li>
						<li class="nav-item"><a class="nav-link" href="#"><i
								class="fas fa-chart-bar"></i>Charts</a></li>
						<li class="nav-item"><a class="nav-link" href="#"><i
								class="far fa-copy"></i>Pages</a></li>

					</ul>

				</nav>
			</div>
		</div>
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
								</c:choose>
								<span class="text-icon">${userDis.username }</span>
						</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="/user-details"><i
									class="far fa-user"></i>Profile</a> <a class="dropdown-item"
									href="/logout"><i class="fas fa-power-off"></i>Logout</a>
							</div></li>
					</ul>
				</nav>
			</div>

			<div class="main-content">

				<div class="form-add">
					<div class="hoadon">
						<div class="row header_hoadon">
							<div class="col-md-3">
								<img src="${pageContext.request.contextPath}/img/logo.png"
									alt="logo">
							</div>
							<div class="col-md-6">
								<h2 style="text-align: center;">HÓA ĐƠN</h2>
								<h3>GIÁ TRỊ GIA TĂNG</h3>
								<h6 style="text-align: center;">Liên 1: Lưu</h6>
								<h6 style="text-align: center;">${date }</h6>
							</div>
							<div class="col-md-3 ma_hoadon">
								<h6 style="text-align: center;">Mẫu số: 01GTKT3/001</h6>
								<h6 style="text-align: center;">Kí hiệu: CM/17P</h6>
								<h6 style="text-align: center;">Số: 000000</h6>
							</div>
						</div>
						<hr>
						<div class="row congty_hoadon">
							<ul class="list-unstyled">
								<li><span class="chuthich">Đơn vị bán hàng:</span> <span
									class="congty" style="margin-left: 64px;">CÔNG TY
										COMPUTER CƯỜNG PHẠM</span></li>
								<li><span class="chuthich">Mã số thuế:</span> <span
									class="thongtin" style="margin-left: 107px;">0123456789
										- ${hoa_don.id }</span></li>
								<li><span class="chuthich">Địa chỉ:</span> <span
									class="thongtin" style="margin-left: 144px;">19A Xa La,
										Hà Đông, Hà Nội</span></li>
								<li><span class="chuthich">Điện thoại:</span> <span
									class="thongtin" style="margin-left: 116px;">0961656067</span></li>
								<li><span class="chuthich">Số tài khoản:</span> <span
									class="thongtin" style="margin-left: 99px;">999969999</span></li>
							</ul>
						</div>
						<hr>
						<div class="row congty_hoadon">
							<ul class="list-unstyled">
								<li><span class="chuthich">Họ tên khách hàng:</span> <span
									class="tenkhach" style="margin-left: 45px;">${hoa_don.userInfo.fullname }</span></li>
								<li><span class="chuthich">Email:</span> <span
									class="thongtin" style="margin-left: 155px;">${hoa_don.userInfo.email }</span></li>
								<li><span class="chuthich">Điện thoại:</span> <span
									class="thongtin" style="margin-left: 115px;">${hoa_don.userInfo.phonenumber }</span></li>
								<li><span class="chuthich">Địa chỉ:</span> <span
									class="thongtin" style="margin-left: 145px;">${hoa_don.userInfo.address }</span></li>
								<li><span class="chuthich">Hình thức thanh toán:</span> <span
									class="thongtin" style="margin-left: 25px;">${hoa_don.payment.payment_type }</span></li>
								<li><span class="chuthich">Đơn vị vận chuyển:</span> <span
									class="thongtin" style="margin-left: 50px;">${hoa_don.shipment.name }&emsp;<fmt:formatNumber
										type="number" maxFractionDigits="3"
										value="${hoa_don.shipment.price }" /> Đ</span></li>
							</ul>
							<hr>

						</div>
						<div class="row">
							<table class="table">
								<thead class="thead-light">
									<tr>
										<th style="padding-left: 10%;">Sản phẩm</th>
										<th>Giá</th>
										<th>Số lượng</th>
										<th>Thành tiền</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="cart"
										items="${hoa_don.boughtLaptops }">
										<tr>
											<td class="tensanpham">
												<div class="row">
													<div class="col-md-5">
														<img
															src="${pageContext.request.contextPath}/files_laptops/${cart.laptop.getLaptopAttachments().get(0).getName() }"
															alt="sanpham">
													</div>
													<div class="col-md-7">
														<h5>${item_hoadons.tenLaptop }</h5>
														<span>Loại sản phẩm: </span>${cart.laptop.name }
														<br>
													</div>

												</div>
											</td>
											<td><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${cart.laptop.price }" />&nbsp;Đ</td>
											<td>${cart.amount }</td>
											<td><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${cart.amount*cart.laptop.price }" />&nbsp;Đ</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="card-body" style="text-align: right;">
								<div class="tongtienthanhtoan">
									Tổng tiền đơn hàng : <strong><span id="total_value"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${hoa_don.payment.all_money }" />
									</span> Đ</strong>
								</div>
							</div>
						</div>

						<hr>
						<div class="row footer_hoadon">
							<div class="col-md-4">
								<h5 style="text-align: center;">Người mua hàng</h5>
								<h6 style="text-align: center;">(Kí, ghi rõ họ tên)</h6>

							</div>
							<div class="col-md-4">
								<h5 style="text-align: center;">Người bán hàng</h5>
								<h6 style="text-align: center;">(Kí, ghi rõ họ tên)</h6>

							</div>
							<div class="col-md-4">
								<h5 style="text-align: center;">Thủ trưởng đơn vị</h5>
								<h6 style="text-align: center;">(Kí, đóng dấu, ghi rõ họ
									tên)</h6>

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
	<!-- ---- -->

</body>
</html>
