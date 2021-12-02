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
		<h4 class="tieude-tp">GIỎ HÀNG CỦA BẠN</h4>
		<c:choose>
			<c:when test="${status=='success'}">
				<div class="alert alert-success">
					<strong>Success!</strong> Bạn đã đặt hàng thành công!
				</div>
			</c:when>
			<c:when test="${status=='failePayPal'}">
				<div class="alert alert-danger">
					<strong>Faile!</strong> Bạn đã đặt hàng thất bại!
				</div>
			</c:when>
			<c:when test="${status=='faile'}">
				<div class="alert alert-danger">
					<strong>Faile!</strong> Bạn hãy chọn hàng cần mua!
				</div>
			</c:when>
			<c:when test="${status=='faileEdit'}">
				<div class="alert alert-danger">
					<strong>Faile!</strong> Bạn sửa thông tin thất bại! Số lượng bạn
					thêm vượt quá số lượng tồn trong kho.
				</div>
			</c:when>
			<c:when test="${status=='successEdit'}">
				<div class="alert alert-success">
					<strong>Success!</strong> Bạn sửa hàng thành công.
				</div>
			</c:when>
			<c:when test="${status=='successDelete'}">
				<div class="alert alert-success">
					<strong>Success!</strong> Bạn xóa hàng thành công.
				</div>
			</c:when>
		</c:choose>
		<div class="row card">
			<div class="btnluachon">
				<a href="/" class="btn btn-warning" style="color: #243a76;"> <i
					class="fas fa-angle-left"></i> Tiếp tục mua hàng
				</a>
			</div>

			<c:choose>
				<c:when test="${not empty giohang }">
					<table class="table">
						<thead class="thead-light">
							<tr>
								<th style="padding-left: 10%;">Sản phẩm</th>
								<th class="gia">Giá</th>
								<th>Số lượng</th>
								<th class="tc-gia">Thành tiền</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="cart" items="${giohang }">
								<tr>
									<td class="tensanpham">
										<div class="row">
											<div class="col-md-4">
												<a href="/item-details/${cart.laptop.seo}"> <img
													src="${pageContext.request.contextPath}/files_laptops/${cart.laptop.getLaptopAttachments().get(0).getName()}"
													alt="sanpham" width="100%">
												</a>
											</div>
											<div class="col-md-8">
												<a href="/item-details/${cart.laptop.seo}"><h5>${cart.laptop.name}
													</h5></a> <span>Loại sản phẩm:</span>
												${cart.laptop.laptopManufacturer.name}<br> <span>Bảo
													hành:</span> 24 tháng
											</div>

										</div>
									</td>
									<td class="gia"><fmt:formatNumber type="number"
											maxFractionDigits="3" value="${cart.laptop.price}" /> Đ</td>
									<td class="so-luong"><input
										class="form-control text-center nhap-so-luong"
										value="${cart.amount}" type="number" min=1
										style="width: 70px;" id="amount${cart.laptop.seo}"></td>
									<td class="tc-gia"><fmt:formatNumber type="number"
											maxFractionDigits="3"
											value="${cart.amount*cart.laptop.price}" /> Đ</td>
									<td class="hanh-dong"><a href="javascript:void(0);"
										onclick="Shop.editCart('modalEditItem', '${cart.laptop.seo}')"
										class="btn btn-primary"><i class="fas fa-edit"></i></a> <a
										href="javascript:void(0);"
										onclick="Shop.deleteCart('modalDeleteItem', '${cart.laptop.seo}')"
										class="btn btn-danger"><i class="fas fa-trash-alt"></i></a></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>


					<div class="card-body" style="text-align: right;">
						<div class="tongtienthanhtoan">
							Tổng tiền đơn hàng : <strong><span id="total_value"><fmt:formatNumber
										type="number" maxFractionDigits="3" value="${tongTien }" /> </span>
								Đ</strong>
						</div>
					</div>
				</c:when>
				<c:when test="${empty giohang }">
					<div class="no-cart">
						<img alt="nocart" src="/img/no_cart.png">
					</div>
				</c:when>
			</c:choose>

		</div>
		<form action="/cart/checkout" method="post">
			<div class="row mt-5">
				<div class="col-md-4 thongtin">
					<div class="title_box_cart">ĐƠN VỊ VẬN CHUYỂN</div>
					<div class="form-group">
						<select name="shipmentId" class="form-control">
							<c:forEach var="shipment" items="${listShipment }">
								<option value="${shipment.id }">${shipment.name }&emsp;<fmt:formatNumber
										type="number" maxFractionDigits="3" value="${shipment.price }" />&nbsp;Đ
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col-md-4 thanhtoan">
					<div class="title_box_cart">HÌNH THỨC THANH TOÁN</div>
					<div class="form-group">
						<div class="form-check">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" name="paymentType"
								value="Thanh toán tiền mặt khi nhận hàng" checked>Thanh
								toán tiền mặt khi nhận hàng
							</label>
						</div>
						<div class="form-check">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" name="paymentType"
								value="Thanh toán qua chuyển khoản qua PayPal">Thanh
								toán qua chuyển khoản qua PayPal
							</label>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<c:choose>
							<c:when test="${status=='faileAddress'}">
								<div class="alert alert-danger">
									<strong>Faile!</strong> Địa chỉ không được để trống
								</div>
							</c:when>
						</c:choose>
						<div class="form-group lienhe">
							<label class="required">Địa chỉ giao hàng:</label> <input
								type="text" class="form-control"
								placeholder="Nhập địa chỉ giao hàng" name="address">
						</div>
					</div>
					<c:choose>
						<c:when test="${empty giohang }">
							<button type="submit" class="btn btn-success btn-thanh-toan"
								style="width: 100%" disabled>
								<div style="text-align: center; width: 100%">
									<span style="font-size: 16px; font-weight: 600;">MUA
										HÀNG</span><br> <span style="font-size: 12px;">Bạn phải
										đăng nhập để tiếp tục</span>
								</div>
							</button>
						</c:when>

						<c:when test="${not empty giohang }">
							<button type="submit" class="btn btn-success btn-thanh-toan"
								style="width: 100%">
								<div style="text-align: center; width: 100%">
									<span style="font-size: 16px; font-weight: 600;">MUA
										HÀNG</span><br> <span style="font-size: 12px;">Bạn phải
										đăng nhập để tiếp tục</span>
								</div>
							</button>
						</c:when>
					</c:choose>
				</div>
			</div>
		</form>
		<h1 class="mb-5"></h1>
	</div>

	<!-- ---- -->

	<!--  FOOTER -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!-- ------- -->

</body>
</html>
