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
	href="${pageContext.request.contextPath}/css/chinhsach.css">
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
		<h4 class="tieude-tp">CHÍNH SÁCH HÀNG CHÍNH HÃNG</h4>
		<div class="row chinh_sach">
			<div class="col-md-12 chinh-hang">
				<div class="anh-chinh-hang">
					<img alt="baohanh"
						src="${pageContext.request.contextPath}/img/chinh-sach-hang-chinh-hang.jpg">
				</div>
				<div class="hnc-static-text-nav-bar">
					<p class="hnc-static-inline">
						đúng<br> nguồn gốc
					</p>
					<p class="hnc-static-inline">
						đúng<br> chất lượng
					</p>
					<p class="hnc-static-inline">
						cam kết<br> hoàn tiền 100%
					</p>
				</div>
				<p>Chất lượng hàng hóa là mối quan tâm số một của khách hàng khi
					quyết định mua sản phẩm, thấu hiểu sự trăn trở này, Computer CuongPham
					luôn luôn đảm bảo những sản phẩm do công ty chúng tôi kinh doanh,
					cung cấp, phân phối đều là mặt hàng chính hãng với chất lượng được
					cam kết ở mức cao nhất.</p>
				<p>Một trong những yếu tố để Computer CuongPham có thể cung cấp xuyên
					suốt các sản phẩm có chất lượng chính hãng chính là vì chúng tôi
					chỉ hợp tác với các đối tác uy tín, đáng tin cậy.</p>
				<p>Chúng tôi cam kết, nếu sản phẩm có thông số kỹ thuật, mẫu mã,
					chất lượng không đúng với các thông số của hãng, của nhà sản xuất,
					chúng tôi sẽ hoàn trả lại 100% số tiền đã thanh toán cho quý khách
					trong thời hạn 3 ngày kể từ ngày khách hàng mua sản phẩm và còn giữ
					đầy đủ vỏ hộp, không trầy xước.</p>
				<p>Cam kết này được đưa ra nhằm thực hiện sứ mệnh mang đến các
					sản phẩm chất lượng chính hãng cùng sự hài lòng ở mức cao nhất cho
					khách hàng khi trải nghiệm dịch vụ của Computer CuongPham.</p>
			</div>
		</div>
	</div>
	<!-- ---- -->

	<!--  FOOTER -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!-- ------- -->

</body>


</html>
