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
		<h4 class="tieude-tp">CHÍNH SÁCH BẢO HÀNH</h4>
		<div class="row chinh_sach">
		
			<div class="col-md-12 bao-hanh">
				<div class="anh-bao-hanh">
					<img alt="baohanh"
						src="${pageContext.request.contextPath}/img/bao_hanh.jpg" class="anh1"> <img
						alt="baohanh"
						src="${pageContext.request.contextPath}/img/bao_hanh2.jpg" class="anh2">
				</div>
				<h5>Chính sách bảo hành</h5>
				<p>- Trong vòng 15 ngày đầu sau khi mua hàng, sản phẩm bị lỗi sẽ
					được đổi sản phẩm mới 100% (Sản phẩm phải có đầy đủ vỏ hộp, phụ
					kiện kèm theo và không bị trầy xước, không vi phạm điều kiện bảo
					hành khác và không phải là vật tư tiêu hao (Vật tiêu hao là vật khi
					đã qua sử dụng một lần thì mất đi hoặc không giữ được tính chất,
					hình dáng và tính năng sử dụng ban đầu). Không áp dụng đổi mới với
					các sản phẩm: CPU, máy in, máy chiếu, máy photo, máy fax, các sản
					phẩm của Apple, surface... (nếu lỗi chúng tôi sẽ tiếp nhận bảo
					hành).</p>
				<p>- Từ ngày 16 cho đến hết thời gian bảo hành, Computer CuongPham
					cam kết khắc phục sự cố và trả bảo hành trong thời gian 10 ngày
					(không tính thứ bảy, chủ nhật, các ngày lễ, Tết và các trường hợp
					khác được thỏa thuận trước)</p>
				<p>- Cộng thêm thời hạn bảo hành sản phẩm, Computer CuongPham cam kết
					cộng thêm thời hạn bảo hành số ngày tương ứng với số ngày Khách
					hàng gửi bảo hành sản phẩm, số ngày này được tính từ ngày Quý khách
					đi gửi bảo hành sản phẩm đến khi nhận được thông báo sản phẩm đã
					được bảo hành xong.</p>
				<p>- Trường hợp không bảo hành được hoặc thời gian bảo hành quá
					lâu (trên 30 ngày) Quý khách có thể lựa chọn một trong các phương
					án sau:</p>
				<ol>
					<li>Đổi sang thiết bị khác tương đương với sản phẩm bảo hành
						hoặc sản phẩm có thông số kỹ thuật cao hơn với chi phí thỏa thuận.</li>
					<li>Nhập lại sản phẩm theo giá thỏa thuận.</li>
				</ol>
				<p>- Bộ phận kỹ thuật - bảo hành sẽ có trách nhiệm trả hàng bảo
					hành tại nơi sử dụng hoặc nơi khách hàng đăng ký khi khách hàng yêu
					cầu trong vòng bán kính 20km tính từ điểm tiếp nhận bảo hành.</p>
				<h5>Điều kiện bảo hành</h5>
				<h6>Không bảo hành với các trường hợp sau</h6>
				<p>Tất cả các sản phẩm do Computer CuongPham bán ra đều tuân thủ điều
					kiện bảo hành của nhà cung cấp, của hãng sản xuất. Các trường hợp
					sau đây bị coi là vi phạm điều kiện bảo hành và không được bảo
					hành:</p>
				<ol>
					<li>Sản phẩm bị tiêu hao trong quá trình sử dụng. (hộp mực,
						cụm trống từ, băng mực, đầu kim, đầu in phun, quạt, các loại cáp,
						nắn dòng, công tắc nguồn…)(*)</li>
					<li>Các phần mềm được cung cấp kèm theo máy.</li>
					<li>Sản phẩm hết thời hạn bảo hành (thời hạn bảo hành sản phẩm
						được thể hiện trên phiếu xuất kho kiêm bảo hành…).</li>
					<li>Không có tem bảo hành của Computer CuongPham, nhà phân phối,
						hãng sản xuất; hoặc có nhưng tem bảo hành đó không hợp lệ (bị
						rách, bị tẩy xóa, sửa chữa, mờ không đọc được, bong/ tróc…).</li>
					<li>Số serial, mã vạch, thông số kỹ thuật trên sản phẩm không
						hợp lệ (bị mờ không đọc được, bị cạo, bị sửa, bị rách, bị bong/
						tróc, bị thay đổi).</li>
					<li>Sản phẩm bị lỗi do thiên tai (lũ lụt, hỏa hoạn, nguồn điện
						không bình thường, sai điện áp quy định…).</li>
					<li>Sản phẩm có những tình trạng như sau:</li>
					<ul>
						<li>Sản phẩm bị biến dạng vật lý như trầy, xước, lồi, lõm,
							móp, méo, nứt, vỡ,...</li>
						<li>Sản phẩm bị mốc, hoen rỉ, ẩm ướt, chất lỏng xâm nhập, ố
							vàng, mờ chữ, viết chữ không tẩy được,...</li>
					</ul>
					<li>Sản phẩm hư hỏng do chuột, bọ hoặc côn trùng xâm nhập</li>
					<li>Sản phẩm bị tháo dỡ, sửa chữa bởi các cá nhân hoặc kỹ
						thuật viên không được sự ủy quyền của Computer CuongPham.</li>
				</ol>
			</div>
		</div>

	</div>
	<!-- ---- -->

	<!--  FOOTER -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!-- ------- -->

</body>


</html>
