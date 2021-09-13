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
		<h4 class="tieude-tp">CHÍNH SÁCH GIAO HÀNG</h4>
		<div class="row chinh_sach">
			<div class="col-md-12 giao-hang">
				<div class="anh-giao-hang">
					<img alt="giaohang"
						src="${pageContext.request.contextPath}/img/chinh_sach_van_chuyen_hang.jpg"
						width="100%"> <img alt="giaohang"
						src="${pageContext.request.contextPath}/img/banner-ship-16-05.png"
						width="100%">
				</div>
				<h5>Lưu ý:</h5>
				<p>- Sau khi bạn đặt hàng, trong vòng 12 giờ làm việc chúng tôi
					sẽ liên lạc lại để xác nhận và kiểm tra thông tin. - Những rủi ro
					phát sinh trong quá trình vận chuyển (va đập, ẩm ướt, tai nạn..) có
					thể ảnh hưởng đến hàng hóa, vì thế xin Quý Khách vui lòng kiểm tra
					hàng hóa thật kỹ trước khi ký nhận. Máy tính Hà Nội sẽ không chịu
					trách nhiệm với những sai lệch hình thức của hàng hóa sau khi Quý
					khách đã ký nhận hàng.</p>
				<h5>Bảng giá dịch vụ giao hàng</h5>
				<table class="table table-striped" style="margin-top: 10px;">
					<thead style="color: white; background: #243a76; line-height: 2;">
						<tr>
							<td style="width: 20%;"><strong>Theo giá trị đơn
									hàng</strong></td>
							<td style="width: 20%;"><strong>Số Km được Miễn
									Phí</strong></td>
							<td style="width: 20%;"><strong>Thời gian đáp ứng</strong></td>
							<td style="width: 40%;"><strong>Thu phí</strong></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Dưới 200.000đ</td>
							<td></td>
							<td>Giao hàng trong vòng 6h</td>
							<td>30.000đ / 1 lần giao (trong vòng 10km) 40.000đ / 1 lần
								giao (trong vòng 15km) 50.000đ / 1 lần giao (trong vòng 20km)
								Trên 20km thu 6.000đ/km - Tối đa 30km</td>
						</tr>
						<tr>
							<td>Trên 200.000đ</td>
							<td></td>
							<td>Giao hàng trong vòng 2h (*)</td>
							<td>20.000đ /1 lần giao (trong vòng 15km) Từ km thứ 16 thu
								phí 6.000/km (chở hàng bằng ô tô thu phí 10.000đ/km).</td>
						</tr>
						<tr>
							<td>Trên 500.000đ</td>
							<td>20km</td>
							<td>Giao hàng trong vòng 2h (*)</td>
							<td>Từ km thứ 21 thu phí 6.000/km (chở hàng bằng ô tô thu
								phí 10.000đ/km).</td>
						</tr>
						<tr>
							<td>Trên 1 triệu đồng</td>
							<td>25km</td>
							<td>Giao hàng trong vòng 3h</td>
							<td>Từ km thứ 26 thu phí 6.000/km (chở hàng bằng ô tô thu
								phí 10.000đ/km).</td>
						</tr>
						<tr>
							<td>Trên 3 triệu đồng</td>
							<td>35km</td>
							<td>Giao hàng trong vòng 4h Giao hàng trong 24h (36km-45km)</td>
							<td>Từ km thứ 36 thu phí 6.000/km (chở hàng bằng ô tô thu
								phí 10.000đ/km).</td>
						</tr>
						<tr>
							<td>Trên 50 triệu đồng</td>
							<td>50km</td>
							<td>Theo thỏa thuận với khách hàng</td>
							<td>Từ km thứ 51 thu phí 10.000/km hoặc theo tư vấn từ nhân
								viên bán hàng.</td>
						</tr>
						<tr>
							<td>Trên 100 triệu đồng</td>
							<td>100km</td>
							<td>Theo thỏa thuận với khách hàng</td>
							<td>Từ km thứ 101 thu phí 10.000/km hoặc theo tư vấn từ nhân
								viên bán hàng.</td>
						</tr>
						<tr>
							<td>Trên 200 triệu đồng</td>
							<td>200km</td>
							<td>Theo thỏa thuận với khách hàng</td>
							<td>Từ km thứ 201 thu phí 10.000/km hoặc theo tư vấn từ nhân
								viên bán hàng.</td>
						</tr>
						<tr>
							<td>Trên 300 triệu đồng</td>
							<td>300km</td>
							<td>Theo thỏa thuận với khách hàng</td>
							<td>Từ km thứ 301 thu phí 10.000/km hoặc theo tư vấn từ nhân
								viên bán hàng.</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- ---- -->

	<!--  FOOTER -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!-- ------- -->

</body>


</html>
