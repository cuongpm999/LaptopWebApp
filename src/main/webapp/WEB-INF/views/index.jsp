<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- sử dụng taglibs của spring để bind-data từ end-point trả về. -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!-- tích hợp jstl vào jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

	<!-- BANNER -->
	<div id="banner">
		<div id="demo" class="carousel slide" data-ride="carousel">

			<ul class="carousel-indicators">
				<li data-target="#demo" data-slide-to="0" class="active"></li>
				<li data-target="#demo" data-slide-to="1"></li>
				<li data-target="#demo" data-slide-to="2"></li>
			</ul>

			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="/img/banner/banner1.jpg" alt="banner">
				</div>
				<div class="carousel-item">
					<img src="/img/banner/banner2.jpg" alt="banner">
				</div>
				<div class="carousel-item">
					<img src="/img/banner/banner3.jpg" alt="banner">
				</div>
			</div>

			<a class="carousel-control-prev" href="#demo" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a class="carousel-control-next" href="#demo" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>

		</div>
	</div>
	<!---->

	<!-- MAIN -->
	<div id="main" class="container">
		<h1 class="my-5"></h1>
		<h3 class="tieude-index">SẢN PHẨM</h3>
		<div id="sanpham-laptop">
			<h4 class="my-4">Laptop Asus</h4>
			<c:forEach var="laptopManufacturer_" items="${laptopManufacturer }"
				varStatus="loop">
				<c:if test="${loop.index==0 }">
					<a class="xemtc"
						href="/shop-all-item/${laptopManufacturer_.seo}/?page=1">Xem
						tất cả</a>
				</c:if>
			</c:forEach>
			<div class="row">
				<c:forEach var="asus_" items="${laptopManufacturer.get(0).laptops }" varStatus="loop">
					<c:if test="${loop.index <4 }">
						<div class="col-md-3">
							<a href="/item-details/${asus_.seo}"><img
								src="${pageContext.request.contextPath}/files_laptops/${asus_.laptopAttachments.get(0).name }"
								alt="asus"></a>
							<div class="card-body">
								<a href="/item-details/${asus_.seo}">
									<h5 class="card-title">${asus_.name }</h5>
								</a>

								<c:choose>
									<c:when test="${not empty asus_.khuyenMai}">
										<div class="gia-goc"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${asus_.price }" /> Đ</div>
										<div class="khuyen-mai">(Tiết kiệm: ${asus_.khuyenMai }%)</div>
									</c:when>
									<c:when test="${empty asus_.khuyenMai }">
										<div class="gia-goc"></div>
										<div class="khuyen-mai"></div>
									</c:when>
								</c:choose>

								<h6 class="card-title"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${asus_.getGiaKhuyenMai() }" /> Đ</h6>
								<div class="check-item">
									<i class="fas fa-check"></i> ${asus_.getTrangThai() }
								</div>
								<a href="javascript:Shop.addToCart('${asus_.seo}');"
									class="shop-now"><i class="fas fa-shopping-cart"></i> Mua
									ngay</a>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>

			<h4 id="waypoint_here" class="my-4">Laptop Dell</h4>
			<c:forEach var="laptopManufacturer_" items="${laptopManufacturer }"
				varStatus="loop">
				<c:if test="${loop.index==1 }">
					<a class="xemtc"
						href="/shop-all-item/${laptopManufacturer_.seo}/?page=1">Xem
						tất cả</a>
				</c:if>
			</c:forEach>
			<div class="row">
				<c:forEach var="dell_" items="${laptopManufacturer.get(1).laptops }" varStatus="loop">
					<c:if test="${loop.index <4 }">
						<div class="col-md-3">
							<a href="/item-details/${dell_.seo}"><img
								src="${pageContext.request.contextPath}/files_laptops/${dell_.laptopAttachments.get(0).name }"
								alt="dell"></a>
							<div class="card-body">
								<a href="/item-details/${dell_.seo}">
									<h5 class="card-title">${dell_.name }</h5>
								</a>
								<c:choose>
									<c:when test="${not empty dell_.khuyenMai }">
										<div class="gia-goc"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${dell_.price }" /> Đ</div>
										<div class="khuyen-mai">(Tiết kiệm: ${dell_.khuyenMai }%)</div>
									</c:when>
									<c:when test="${empty dell_.khuyenMai }">
										<div class="gia-goc"></div>
										<div class="khuyen-mai"></div>
									</c:when>
								</c:choose>
								<h6 class="card-title"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${dell_.getGiaKhuyenMai() }" /> Đ</h6>
								<div class="check-item">
									<i class="fas fa-check"></i> ${dell_.getTrangThai() }
								</div>
								<a href="javascript:Shop.addToCart('${dell_.seo}');"
									class="shop-now"><i class="fas fa-shopping-cart"></i> Mua
									ngay</a>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>

			<h4 class="my-4">Laptop HP</h4>
			<c:forEach var="laptopManufacturer_" items="${laptopManufacturer }"
				varStatus="loop">
				<c:if test="${loop.index==2 }">
					<a class="xemtc"
						href="/shop-all-item/${laptopManufacturer_.seo}/?page=1">Xem
						tất cả</a>
				</c:if>
			</c:forEach>
			<div class="row">
				<c:forEach var="hp_" items="${laptopManufacturer.get(2).laptops }" varStatus="loop">
					<c:if test="${loop.index <4 }">
						<div class="col-md-3">
							<a href="/item-details/${hp_.seo}"><img
								src="${pageContext.request.contextPath}/files_laptops/${hp_.laptopAttachments.get(0).name }"
								alt="hp"></a>
							<div class="card-body">
								<a href="/item-details/${hp_.seo}">
									<h5 class="card-title">${hp_.name }</h5>
								</a>
								<c:choose>
									<c:when test="${not empty hp_.khuyenMai }">
										<div class="gia-goc"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${hp_.price }" /> Đ</div>
										<div class="khuyen-mai">(Tiết kiệm: ${hp_.khuyenMai }%)</div>
									</c:when>
									<c:when test="${empty hp_.khuyenMai }">
										<div class="gia-goc"></div>
										<div class="khuyen-mai"></div>
									</c:when>
								</c:choose>
								<h6 class="card-title"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${hp_.getGiaKhuyenMai() }" /> Đ</h6>
								<div class="check-item">
									<i class="fas fa-check"></i> ${hp_.getTrangThai() }
								</div>
								<a href="javascript:Shop.addToCart('${hp_.seo}');" class="shop-now"><i
									class="fas fa-shopping-cart"></i> Mua ngay</a>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>

			<h4 class="my-4">Laptop Lenovo</h4>
			<c:forEach var="laptopManufacturer_" items="${laptopManufacturer }"
				varStatus="loop">
				<c:if test="${loop.index==3 }">
					<a class="xemtc"
						href="/shop-all-item/${laptopManufacturer_.seo}/?page=1">Xem
						tất cả</a>
				</c:if>
			</c:forEach>
			<div class="row">
				<c:forEach var="lenovo_" items="${laptopManufacturer.get(3).laptops }" varStatus="loop">
					<c:if test="${loop.index <4 }">
						<div class="col-md-3">
							<a href="/item-details/${lenovo_.seo}"><img
								src="${pageContext.request.contextPath}/files_laptops/${lenovo_.laptopAttachments.get(0).name }"
								alt="lenovo"></a>
							<div class="card-body">
								<a href="/item-details/${lenovo_.seo}">
									<h5 class="card-title">${lenovo_.name }</h5>
								</a>
								<c:choose>
									<c:when test="${not empty lenovo_.khuyenMai }">
										<div class="gia-goc"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${lenovo_.price }" /> Đ</div>
										<div class="khuyen-mai">(Tiết kiệm: ${lenovo_.khuyenMai }%)</div>
									</c:when>
									<c:when test="${empty lenovo_.khuyenMai }">
										<div class="gia-goc"></div>
										<div class="khuyen-mai"></div>
									</c:when>
								</c:choose>
								<h6 class="card-title"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${lenovo_.getGiaKhuyenMai() }" /> Đ</h6>
								<div class="check-item">
									<i class="fas fa-check"></i> ${lenovo_.getTrangThai() }
								</div>
								<a href="javascript:Shop.addToCart('${lenovo_.seo}');"
									class="shop-now"><i class="fas fa-shopping-cart"></i> Mua
									ngay</a>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>

			<h4 class="my-4">Laptop Acer</h4>
			<c:forEach var="laptopManufacturer_" items="${laptopManufacturer }"
				varStatus="loop">
				<c:if test="${loop.index==4 }">
					<a class="xemtc"
						href="/shop-all-item/${laptopManufacturer_.seo}/?page=1">Xem
						tất cả</a>
				</c:if>
			</c:forEach>
			<div class="row">
				<c:forEach var="acer_" items="${laptopManufacturer.get(4).laptops }" varStatus="loop">
					<c:if test="${loop.index <4 }">
						<div class="col-md-3">
							<a href="/item-details/${acer_.seo}"><img
								src="${pageContext.request.contextPath}/files_laptops/${acer_.laptopAttachments.get(0).name }"
								alt="acer"></a>
							<div class="card-body">
								<a href="/item-details/${acer_.seo}">
									<h5 class="card-title">${acer_.name }</h5>
								</a>
								<c:choose>
									<c:when test="${not empty acer_.khuyenMai }">
										<div class="gia-goc"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${acer_.price }" /> Đ</div>
										<div class="khuyen-mai">(Tiết kiệm: ${acer_.khuyenMai }%)</div>
									</c:when>
									<c:when test="${empty acer_.khuyenMai }">
										<div class="gia-goc"></div>
										<div class="khuyen-mai"></div>
									</c:when>
								</c:choose>
								<h6 class="card-title"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${acer_.getGiaKhuyenMai() }" /> Đ</h6>
								<div class="check-item">
									<i class="fas fa-check"></i> ${acer_.getTrangThai() }
								</div>
								<a href="javascript:Shop.addToCart('${acer_.seo}');"
									class="shop-now"><i class="fas fa-shopping-cart"></i> Mua
									ngay</a>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>

			<h4 class="my-4">Laptop Apple</h4>
			<c:forEach var="laptopManufacturer_" items="${laptopManufacturer }"
				varStatus="loop">
				<c:if test="${loop.index==5 }">
					<a class="xemtc"
						href="/shop-all-item/${laptopManufacturer_.seo}/?page=1">Xem
						tất cả</a>
				</c:if>
			</c:forEach>
			<div class="row">
				<c:forEach var="apple_" items="${laptopManufacturer.get(5).laptops }" varStatus="loop">
					<c:if test="${loop.index <4 }">
						<div class="col-md-3">
							<a href="/item-details/${apple_.seo}"><img
								src="${pageContext.request.contextPath}/files_laptops/${apple_.laptopAttachments.get(0).name }"
								alt="apple"></a>
							<div class="card-body">
								<a href="/item-details/${apple_.seo}">
									<h5 class="card-title">${apple_.name }</h5>
								</a>
								<c:choose>
									<c:when test="${not empty apple_.khuyenMai }">
										<div class="gia-goc"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${apple_.price }" /> Đ</div>
										<div class="khuyen-mai">(Tiết kiệm: ${apple_.khuyenMai }%)</div>
									</c:when>
									<c:when test="${empty apple_.khuyenMai }">
										<div class="gia-goc"></div>
										<div class="khuyen-mai"></div>
									</c:when>
								</c:choose>
								<h6 class="card-title"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${apple_.getGiaKhuyenMai() }" /> Đ</h6>
								<div class="check-item">
									<i class="fas fa-check"></i> ${apple_.getTrangThai() }
								</div>
								<a href="javascript:Shop.addToCart('${apple_.seo}');"
									class="shop-now"><i class="fas fa-shopping-cart"></i> Mua
									ngay</a>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>

			<h4 class="my-4">Laptop MSI</h4>
			<c:forEach var="laptopManufacturer_" items="${laptopManufacturer }"
				varStatus="loop">
				<c:if test="${loop.index==6 }">
					<a class="xemtc"
						href="/shop-all-item/${laptopManufacturer_.seo}/?page=1">Xem
						tất cả</a>
				</c:if>
			</c:forEach>
			<div class="row">
				<c:forEach var="msi_" items="${laptopManufacturer.get(6).laptops }" varStatus="loop">
					<c:if test="${loop.index <4 }">
						<div class="col-md-3">
							<a href="/item-details/${msi_.seo}"><img
								src="${pageContext.request.contextPath}/files_laptops/${msi_.laptopAttachments.get(0).name }"
								alt="msi"></a>
							<div class="card-body">
								<a href="/item-details/${msi_.seo}">
									<h5 class="card-title">${msi_.name }</h5>
								</a>
								<c:choose>
									<c:when test="${not empty msi_.khuyenMai }">
										<div class="gia-goc"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${msi_.price }" /> Đ</div>
										<div class="khuyen-mai">(Tiết kiệm: ${msi_.khuyenMai }%)</div>
									</c:when>
									<c:when test="${empty msi_.khuyenMai }">
										<div class="gia-goc"></div>
										<div class="khuyen-mai"></div>
									</c:when>
								</c:choose>
								<h6 class="card-title"><fmt:formatNumber type="number" maxFractionDigits="3"
													value="${msi_.getGiaKhuyenMai() }" /> Đ</h6>
								<div class="check-item">
									<i class="fas fa-check"></i> ${msi_.getTrangThai() }
								</div>
								<a href="javascript:Shop.addToCart('${msi_.seo}');"
									class="shop-now"><i class="fas fa-shopping-cart"></i> Mua
									ngay</a>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>

		</div>


		<h1 class="my-5" style="clear: both;"></h1>
		<h3 class="tieude-index">CHÍNH SÁCH</h3>
		<div id="dichvu">
			<div class="row">
				<div class="col-md-3 about-picture">
					<img src="/img/chinh-sach-giao-hang.jpg" alt="dichvu">
					<p class="picture-title">CHÍNH SÁCH GIAO HÀNG</p>

				</div>
				<div class="col-md-3 about-picture">
					<img src="/img/doi-tra-de-dang.png" alt="dichvu">
					<p class="picture-title">ĐỔI TRẢ DỄ DÀNG</p>


				</div>
				<div class="col-md-3 about-picture">
					<img src="/img/thanh-toan-tien-loi.png" alt="dichvu">
					<p class="picture-title">THANH TOÁN TIỆN LỢI</p>

				</div>
				<div class="col-md-3 about-picture">
					<img src="/img/ho-tro-nhiet-tinh.jpg" alt="dichvu">
					<p class="picture-title">HỖ TRỢ NHIỆT TÌNH</p>

				</div>
			</div>
		</div>
	</div>

	<!--  FOOTER -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!-- ------- -->

</body>


</html>
