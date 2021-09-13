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
	href="${pageContext.request.contextPath}/css/gioithieu.css">
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
	<div id="main" class="container gioithieu">
		<h4 class="tieude-tp">GIỚI THIỆU</h4>
		<div class="row gioi_thieu">
			<div class="col-md-12">
				<h5>Tổ chức</h5>
				<ol>
					<li>Xuất thân từ cửa hàng kinh doanh máy tính được thành lập
						từ năm 1997, Computer CuongPham được biết đến là đơn vị bán lẻ lâu
						đời và uy tín tại Việt Nam. Computer CuongPham chuyên kinh doanh
						các sản phẩm công nghệ thông tin, thiết bị giải trí game, thiết bị
						văn phòng và thiết bị hi-tech của nhiều nhãn hàng lớn như Dell,
						Asus, HP, MSI, Lenovo…</li>
					<li>Sau 23 năm phát triển không ngừng, Computer CuongPham
						hướng đến mục tiêu không chỉ là nơi kinh doanh máy tính mà còn là
						nơi khách hàng có thể tìm thấy mọi tiện ích công nghệ hiện đại và
						dịch vụ chất lượng cao.</li>
				</ol>
				<h5 style="margin-top: 50px;">Sứ mệnh và mục tiêu</h5>
				<img alt="gioithieu"
					src="${pageContext.request.contextPath}/img/Sứ-mệnh.jpg"
					width="100%"> <img alt="gioithieu"
					src="${pageContext.request.contextPath}/img/Mục-tiêu.jpg"
					width="100%">
				<h5 id="waypoint_here" style="margin-top: 50px;">Giá trị cốt
					lõi</h5>
				<img alt="gioithieu"
					src="${pageContext.request.contextPath}/img/Giá-trị-cốt-lõi.jpg"
					width="100%">
				<h5 style="margin-top: 50px;">Cơ cấu tổ chức</h5>
				<img alt="gioithieu"
					src="${pageContext.request.contextPath}/img/Cơ-cấu-tổ-chức.jpg"
					width="100%">
				<h5 style="margin-top: 50px;">Hành trình phát triển</h5>
				<img alt="gioithieu"
					src="${pageContext.request.contextPath}/img/Hình-thành-và-phát-triển.jpg"
					width="100%">
				<ol>
					<li>1997: Bắt đầu từ một cửa hàng nhỏ bán lắp ráp phần cứng
						máy tính & PC tại TP.HN.</li>
					<li>2000: Khai trương trung tâm dịch vụ hàng đầu tại TP.HN.</li>
					<li>2007: Công ty Computer CuongPham được thành lập.</li>
					<li>2013: Khai trương showroom tại Hà Đông.</li>
					<li>2015: Mở Showroom ngoài khu vực TP.HN.</li>
					<li>2017: 5 showroom mới được mở tại TP.HN.</li>
					<li>2018: Computer CuongPham mở hơn 30 showroom tại Việt Nam.</li>
					<li>2019: Tập trung phát triển các chuỗi cửa hàng và chuẩn bị
						cho một tương lai mới.</li>
					<li>2020: Sẵn sàng cho tương lai mới phát triển mạnh mẽ.</li>
				</ol>
				<h5 style="margin-top: 50px;">Lĩnh vực kinh doanh</h5>
				<img alt="gioithieu"
					src="${pageContext.request.contextPath}/img/Lĩnh-vực-KD.jpg"
					width="100%">

				<ol>
					<li>Phong Vũ cung cấp đầy đủ các sản phẩm máy tính, thiết bị
						công nghệ thông tin & hi-end cho người dùng gia đình.</li>
					<li>Cung cấp giải pháp tích hợp hệ thống công nghệ thông tin
						cho doanh nghiệp.</li>
					<li>Cung cấp giải pháp đặc biệt cho các doanh nghiệp như hội
						nghị video, màn hình kỹ thuật số, hệ thống quản lý thanh toán,…</li>
					<li>Thêm sự lựa chọn, Phong Vũ mở rộng ngành hàng như các
						thiết bị điện tử tiêu dùng, thiết bị gia dụng và thiết bị thông
						minh.</li>
					<li>Đối tác chiến lược: Asus, Dell, Acer, Hp, Lenovo, AMD,
						MSI, LG, Intel, Apple, Samsung, Microsoft, GigaByte, Logitech,
						NVIDIA, Kingston, KB Vision, Xiaomi, JBL, Bose, Sony, Microlab,
						Razer,…</li>
				</ol>

				<h5 style="margin-top: 50px;">Thành tựu</h5>
				<img alt="gioithieu"
					src="${pageContext.request.contextPath}/img/Con-số-biết-nói.jpg"
					width="100%"> <img alt="gioithieu"
					src="${pageContext.request.contextPath}/img/Thành-tựu.jpg"
					width="100%">
				<p>Bằng những nỗ lực không mệt mỏi, Phong Vũ tự hào là nhà bán
					lẻ thiết bị công nghệ cao được khách hàng và các đối tác tin tưởng
					và yêu mến. Đó là giải thưởng và niềm tự hào vô giá của chúng tôi.</p>
				<h5 style="margin-top: 50px;">Con người</h5>
				<img alt="gioithieu"
					src="${pageContext.request.contextPath}/img/con-người.jpg"
					width="100%">
				<table class="table">
					<thead>
						<tr>
							<th>Hướng Đến Khách Hàng</th>
							<th>Quan Tâm</th>
							<th>Chính Trực</th>
							<th>Đồng Đội</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Được phục vụ và mang đến sự hài lòng cho khách hàng là
								mục tiêu hàng đầu của Nhân viên Phong Vũ</td>
							<td>Tận tụy với khách hàng, tận tâm với nhân viên là cách
								chúng tôi lan tỏa giá trị tích cực và tinh thần trách nhiệm</td>
							<td>Làm đúng những gì cam kết từ những việc đơn giản nhất,
								công bằng và công khai minh bạch</td>
							<td>Sức mạnh của chúng tôi được tạo nên bởi sự đoàn kết và
								sẻ chia</td>
						</tr>
					</tbody>
				</table>
				<h5 style="margin-top: 50px;">Tin Tưởng Và Cống Hiến Tận Tâm</h5>

				<p>Tương lai được kiến tạo từ những việc bạn làm hôm nay chứ
					không phải ngày mai, hãy gia nhập đại gia đình Computer CuongPham
					và cùng nhau tạo nên những giá trị tự hào!</p>
			</div>
		</div>
	</div>

	<!-- ---- -->
	<!--  FOOTER -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!-- ------- -->

</body>


</html>