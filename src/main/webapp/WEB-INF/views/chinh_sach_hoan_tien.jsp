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
		<h4 class="tieude-tp">CHÍNH SÁCH ĐỔI TRẢ VÀ HOÀN TIỀN</h4>
		<div class="row chinh_sach">
			<div class="col-md-12 hoan-tien">
				<h5>Định nghĩa việc nhập lại</h5>
				<p>Nhập lại là việc Công ty Comuter CuongPham nhận lại hàng hóa
					đã bán cho khách hàng và thanh toán tiền hoặc đổi hàng hóa khác có
					giá trị tương đương hoặc có giá trị chênh lệch cho khách hàng đó.</p>
				<h5>Điều kiện nhập lại hàng</h5>
				<img alt="hoantien"
					src="${pageContext.request.contextPath}/img/chinh-sach-doi-tra-hang.jpg"
					width="100%">
				<p>- Hàng hóa đã mua trong vòng 03 ngày tính đến thời điểm nhập
					lại của khách hàng (không áp dụng với Laptop, CPU, máy in, máy
					chiếu, máy photo, máy fax, các sản phẩm của Apple, surface, các sản
					phẩm tiêu hao), khi nhập lại khách hàng được hoàn trả lại tiền.</p>
				<p>- Sau 03 ngày, khách hàng chỉ được trả lại hàng hoặc đổi sang
					mặt hàng khác khi:</p>
				<ol>
					<li>Đối với các sản phẩm đã mua trong vòng 15 ngày: Hàng bị
						lỗi không phải nguyên nhân từ phía khách hàng, đã được Công ty
						khắc phục nhưng không có kết quả hoặc được nhận bảo hành với thời
						gian quá với quy chế được Bộ phận kỹ thuật - bảo hành của Công ty
						xác nhận.</li>
					<li>Đối với các sản phẩm đã mua ngoài thời hạn 15 ngày sẽ theo
						chính sách bảo hành của Công ty.</li>
				</ol>
				<p>- Hàng nhập lại phải đảm bảo các yếu tố sau:</p>
				<ol>
					<li>Hàng nhập lại phải sạch sẽ, còn nguyên tem, Serial, phiếu
						bảo hành, nguyên hình dạng, không xước, biến dạng, cháy nổ…</li>
					<li>Hàng phải còn đầy đủ vỏ hộp, sách hướng dẫn, đĩa cài đặt
						(nếu có) và các phụ kiện đi kèm. Ghi chú: Khách hàng vui lòng mang
						sản phẩm cần nhập lại qua nơi mua để được giải quyết theo quy
						định.</li>
					<li>Hàng thanh lý, giảm giá không được áp dụng chính sách nhập
						lại, đổi mới.</li>
				</ol>
				<img alt="hoantien"
					src="${pageContext.request.contextPath}/img/chinh-sach-doi-tra-hang1.jpg"
					width="100%">
				<h5>Chính sách hoàn tiền</h5>
				<p>- Quý khách hàng sẽ được hoàn tiền sau khi thực hiện xong thủ
					tục nhập lại sản phẩm.</p>
				<ol>
					<li>Số tiền được hoàn lại tính theo giá tại thời điểm trả lại
						hàng (tính theo giá niêm yết trên website công ty).</li>
					<li>Trường hợp giá tại thời điểm nhập lại cao hơn giá thời
						điểm khách mua hàng thì giá nhập lại được căn cứ vào giá tại thời
						điểm mua hàng.</li>
					<li>Trường hợp tại thời điểm nhập lại hàng mà loại hàng đó
						không có trên thị trường hoặc không định được giá của mặt hàng đó
						thì sẽ cùng thỏa thuận một mức giá hợp lý, cùng căn cứ mức giá này
						để tính toán số tiền hoàn lại.</li>
					<li>Những trường hợp đặc biệt về nhập lại hàng hóa mà Trưởng
						phòng kỹ thuật bảo hành không quyết định được sẽ do Tổng Giám đốc
						quyết định.</li>
				</ol>
				<p>- Tuỳ thuộc vào thời gian xử lý của từng ngân hàng, thông
					thường có thể mất từ 7 – 10 ngày làm việc để tiền vào tài khoản của
					Quý khách. Vui lòng liên hệ với chúng tôi qua hotline 1900 1903 nếu
					Quý khách không nhận được tiền vào tài khoản sau 10 ngày làm việc,
					để chúng tôi liên hệ ngay với ngân hàng nhờ kiểm tra và xử lý nhanh
					hơn cho Quý khách.</p>
				<p>Lưu ý: Chúng tôi chỉ hoàn lại số tiền Quý khách thanh toán
					cho sản phẩm đã mua, các chi phí khác phát sinh như chi phí giao
					hàng, chi phí thanh toán qua thẻ tín dụng, chi phí cài đặt hoặc hỗ
					trợ kỹ thuật,… sẽ không được hoàn.</p>
				<h5>Thủ tục nhập lại</h5>
				<p>- Bắt buộc khách hàng phải xuất trình phiếu xuất kho tại Kho
					và hóa đơn tài chính (nếu có) của mặt hàng cần nhập lại.</p>
				<p>- Trường hợp sản phẩm đã được Công ty Computer CuongPham
					viết hóa đơn tài chính, khách hàng muốn trả lại hàng phải viết trả
					lại hoá đơn tài chính hoặc làm thủ tục hủy hóa đơn này theo quy
					định của pháp luật.</p>
			</div>
		</div>
	</div>
	<!-- ---- -->

	<!--  FOOTER -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!-- ------- -->

</body>


</html>
