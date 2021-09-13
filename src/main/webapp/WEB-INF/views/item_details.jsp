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
	href="${pageContext.request.contextPath}/css/item-details.css">
<!-- ----------------- -->

<title>Laptop Project</title>
</head>

<body>

	<div id="fb-root"></div>
    	<script async defer crossorigin="anonymous"
        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v11.0&appId=463826531507215&autoLogAppEvents=1"
        nonce="8YtQejU0"></script>

	<!-- HEADER -->
	<%@ include file="/WEB-INF/views/layout/header.jsp"%>
	<!-- ------ -->

	<!-- MAIN -->
	<div id="main" class="container">
		<div class="buy-item">
			<h5 class="item-name">${laptop.name }</h5>
			<div class="row">
				<div class="col-md-4">
					<div class="tab-content">
						<div class="tab-pane fade active show"
							id="pic-${laptop.laptopAttachments.get(0).id }">
							<img
								src="${pageContext.request.contextPath}/files_laptops/${laptop.laptopAttachments.get(0).name }">
						</div>

						<c:forEach var="imgAttachment"
							items="${laptop.laptopAttachments }" varStatus="loop">
							<c:if test="${loop.index > 0}">
								<div class="tab-pane fade" id="pic-${imgAttachment.id }">
									<img
										src="${pageContext.request.contextPath}/files_laptops/${imgAttachment.name }"
										alt="laptop">
								</div>
							</c:if>
						</c:forEach>

					</div>
					<ul id="img_thumb" class="nav list-unstyled d-flex">
						<li class="active"><a data-toggle="tab"
							href="#pic-${laptop.laptopAttachments.get(0).id }"><img
								src="${pageContext.request.contextPath}/files_laptops/${laptop.laptopAttachments.get(0).name }"
								alt="laptop" width="100%"></a></li>

						<c:forEach var="imgAttachment"
							items="${laptop.laptopAttachments }" varStatus="loop">
							<c:if test="${loop.index > 0 && loop.index < 9}">
								<li><a data-toggle="tab" href="#pic-${imgAttachment.id }"
									data-target="#pic-${imgAttachment.id }" data-toggle="tab-pane"><img
										src="${pageContext.request.contextPath}/files_laptops/${imgAttachment.name }"
										alt="laptop" width="100%"></a></li>
							</c:if>
						</c:forEach>

					</ul>

				</div>
				<div class="col-md-5 scrollbar" id="style-1">
					<h6 class="tskt">Thông số kĩ thuật</h6>
					${laptop.table_content }
				</div>

				<div class="col-md-3 product-detail-static-content">
					<div class="static-item">
						<div class="title">
							<i class="fas fa-warehouse" style="margin-right: 10px;"></i>Thông
							tin kho hàng
						</div>
						<div class="static-nd">- ${laptop.getTrangThai()}</div>
					</div>

					<div class="static-item">
						<div class="title">
							<i class="fas fa-money-bill-wave" style="margin-right: 10px;"></i>Chính
							sách bán hàng
						</div>
						<div class="static-nd">
							<ul class="list-unstyled">
								<li>- Sản phẩm chính hãng 100%</li>
								<li>- Bán hàng online toàn quốc</li>
								<li>- Mua hàng trả góp lãi suất 0%</li>

								<li>- Trả bảo hành tận nơi sử dụng</li>
								<li>- Bảo hành tận nơi cho doanh nghiệp</li>
							</ul>
						</div>
					</div>

					<div class="static-item">
						<div class="title">
							<i class="fas fa-shipping-fast" style="margin-right: 10px;"></i>Chính
							sách giao hàng
						</div>
						<div class="static-nd">
							<ul class="list-unstyled">
								<li>- Giao hàng trước trả tiền sau COD</li>
								<li>- Miễn phí giao hàng (bán kính 20km) với đơn hàng trên
									500.000 đ</li>
								<li>- Miễn phí giao hàng 300km với khách hàng Games Net,
									Doanh Nghiệp, Dự Án</li>
							</ul>
						</div>
					</div>

				</div>
			</div>

			<div class="row gift-button">
				<div class="col-md-4"></div>
				<div class="col-md-5">
					<div class="gia-tien">
						<table>
							<tbody>
								<tr>
									<td class="tien-thitruong1">Giá thị trường:</td>
									<td class="tien-thitruong"><fmt:formatNumber type="number"
											maxFractionDigits="3" value="${laptop.price }" /> Đ<span
										style="color: red; font-size: 13px; margin-left: 10px;"><c:choose>
												<c:when test="${not empty laptop.khuyenMai}">(Tiết kiệm: ${laptop.khuyenMai }%)</div>
												</c:when>
											</c:choose></span></td>
								</tr>
								<tr>
									<td class="tien-ban1">Giá khuyến mại:</td>
									<td class="tien-ban"><fmt:formatNumber type="number"
											maxFractionDigits="3" value="${laptop.getGiaKhuyenMai() }" />
										Đ</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="button-buy">
						<a href="javascript:Shop.addToCartNow('${laptop.seo}');"
							class="btn"
							style="width: 100%; background: #ed1b24; color: white;">
							<h5 id="waypoint_here">ĐẶT MUA NGAY</h5>
							<h6>Giao hàng tận nơi nhanh chóng</h6>
						</a> <a href="javascript:Shop.addToCart('${laptop.seo}');"
							class="btn them-gio" style="background: #fa9f2c; color: white;">
							<h5>CHO VÀO GIỎ</h5>
							<h6>Cho vào giỏ để chọn tiếp</h6>
						</a>
					</div>
				</div>
				<div class="col-md-3"></div>
			</div>

		</div>

		<div class="video-content">
			<h4>Video unbox, review sản phẩm</h4>
			<iframe width="100%" src="${laptop.video }" frameborder="0"
				allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
				allowfullscreen></iframe>
		</div>

		<div class="sanpham-tuongtu">
			<h4>Sản phẩm tương tự</h4>
			<div class="row">
				<c:forEach var="laptop_" items="${laptop_same }" varStatus="loop">
					<c:if test="${loop.index <4 }">
						<div class="col-md-3">
							<a href="/item-details/${laptop_.seo}"><img
								src="${pageContext.request.contextPath}/files_laptops/${laptop_.laptopAttachments.get(0).name }"
								alt="asus"></a>
							<div class="card-body">
								<a href="/item-details/${laptop_.seo}">
									<h5 class="card-title">${laptop_.name }</h5>
								</a>

								<c:choose>
									<c:when test="${not empty laptop_.khuyenMai}">
										<div class="gia-goc">
											<fmt:formatNumber type="number" maxFractionDigits="3"
												value="${laptop_.price }" />
											Đ
										</div>
										<div class="khuyen-mai">(Tiết kiệm: ${laptop_.khuyenMai }%)</div>
									</c:when>
									<c:when test="${empty laptop_.khuyenMai }">
										<div class="gia-goc"></div>
										<div class="khuyen-mai"></div>
									</c:when>
								</c:choose>

								<h6 class="card-title">
									<fmt:formatNumber type="number" maxFractionDigits="3"
										value="${laptop_.getGiaKhuyenMai() }" />
									Đ
								</h6>
								<div class="check-item">
									<i class="fas fa-check"></i> ${laptop_.getTrangThai() }
								</div>
								<a href="javascript:Shop.addToCart('${laptop_.seo}');"
									class="shop-now"><i class="fas fa-shopping-cart"></i> Mua
									ngay</a>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>

		</div>

		<div class="tuvan">
			<h4>Hỏi đáp về sản phẩm</h4>
			<div class="fb-comments" data-href="http://127.0.0.1:${laptop.id}" data-width="100%" data-numposts="5"></div>
		</div>
	</div>
	<!-- --- -->

	<!--  FOOTER -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!-- ------- -->

</body>


</html>
