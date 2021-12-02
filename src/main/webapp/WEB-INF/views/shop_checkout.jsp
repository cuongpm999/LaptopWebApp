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
	href="${pageContext.request.contextPath}/css/giohang.css">
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
	<div class="container giohang">
		<h4 class="tieude-tp">THANH TOÁN</h4>
		<div class="row card">
			<div class="thong-tin-khach" style="margin-bottom: 10px;">
				<h5 style="color: orange; margin-bottom: 20px;">
					<i class="fas fa-map-marker-alt"></i> Địa chỉ nhận hàng
				</h5>
				<div style="display: flex;">
					<span class="name-mobile">${bill.userInfo.fullname }&ensp;${bill.userInfo.phonenumber }</span><span class="address">${bill.userInfo.address }</span>
				</div>
			</div>
			<table class="table">
				<thead class="thead-light">
					<tr>
						<th style="padding-left: 10%;">Sản phẩm</th>
						<th class="gia">Giá</th>
						<th>Số lượng</th>
						<th class="tc-gia">Thành tiền</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cart" items="${bill.boughtLaptops }">
						<tr>
							<td class="tensanpham">
								<div class="row">
									<div class="col-md-4">
										<a href="javascript:void(0);"> <img
											src="${pageContext.request.contextPath}/files_laptops/${cart.laptop.getLaptopAttachments().get(0).getName()}"
											alt="sanpham" width="100%">
										</a>
									</div>
									<div class="col-md-8">
										<a href="javascript:void(0);"><h5>${cart.laptop.name}
											</h5></a> <span>Loại sản phẩm:</span>
										${cart.laptop.laptopManufacturer.name}<br> <span>Bảo
											hành:</span> 24 tháng
									</div>

								</div>
							</td>
							<td class="gia"><fmt:formatNumber type="number"
									maxFractionDigits="3" value="${cart.laptop.price}" /> Đ</td>
							<td class="so-luong">${cart.amount}</td>
							<td class="tc-gia"><fmt:formatNumber type="number"
									maxFractionDigits="3" value="${cart.amount*cart.laptop.price}" />
								Đ</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>

		</div>

		<div class="row mt-5">
			<div class="col-md-4 thongtin mb-4">
				<div class="title_box_cart">ĐƠN VỊ VẬN CHUYỂN</div>
				<table style="width: 100%; font-size: 15px;">
					<tbody>
						<tr>
							<td>${bill.shipment.name }</td>
							<td><fmt:formatNumber type="number" maxFractionDigits="3"
									value="${bill.shipment.price }" /> Đ</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-md-4 thanhtoan mb-4">
				<div class="title_box_cart">HÌNH THỨC THANH TOÁN</div>
				${bill.payment.payment_type }
			</div>
			<div class="col-md-4">
				<table style="width: 100%; padding: 10px 5px;">
					<tbody>
						<tr class="txt_16">
							<td class="txt2"><b>Tổng tiền</b></td>
							<td class="txt2 text-right"><strong class="total_cart_new"><fmt:formatNumber
										type="number" maxFractionDigits="3"
										value="${bill.payment.all_money }" /> Đ</strong></td>
						</tr>
						<tr class="txt_16">
							<td class="txt2"><b>Thành tiền</b></td>
							<td class="txt2 text-right"><strong
								class="red total_cart_new"></strong><strong
								class="total_cart_new"><fmt:formatNumber type="number"
										maxFractionDigits="3" value="${bill.payment.all_money }" /> Đ</strong><br>
								<span class="txt_12">(Giá chưa bao gồm VAT)</span></td>
						</tr>
					</tbody>
				</table>
				<div class="new-cart-button" style="margin-top: 10px;">
					<a href="/cart/checkout/finish" class="btn btn-dark"
						style="width: 100%"> Đặt hàng</a>
				</div>
			</div>
		</div>
		<h1 class="mb-5"></h1>
	</div>

	<!-- ---- -->

	<!--  FOOTER -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!-- ------- -->

</body>
</html>
