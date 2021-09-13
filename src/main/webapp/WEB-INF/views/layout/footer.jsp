<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- tich hop jstl vao jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!-- FOOTER -->
<div id="footer">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h5 class="font-weight-bold mt-5">THÔNG TIN ĐẠI LÝ</h5>
				<ul class="list-unstyled">
					<li>
						<h6 class="font-weight-bold">CÔNG TY COMPUTER CƯỜNG PHẠM</h6>
					</li>
					<li><a href="https://goo.gl/maps/NC2mDp9iitRBHZXq6"><i
							class="fas fa-map-marker-alt"></i> Địa chỉ: 19A Xa La, Hà Đông,
							Hà Nội</a></li>
					<li><a href="mailto:phamcuongth2000@gmail.com"><i
							class="fas fa-envelope"></i> Email: phamcuongth2000@gmail.com</a></li>
					<li><a href="tel:0961656067"><i class="fas fa-phone"></i>
							Hotline: 0961656067</a></li>
				</ul>
				<ul class="social-icons">
					<li><i class="fab fa-facebook"></i></li>
					<li><i class="fab fa-twitter-square"></i></li>
					<li><i class="fab fa-instagram"></i></li>
					<li><i class="fab fa-google-plus-square"></i></li>
				</ul>
			</div>
			<div class="col-md-2">
				<h5 class="font-weight-bold mt-5">MUA LAPTOP</h5>
				<ul class="list-unstyled thongtin">
					<li><a href="/shop-all-item/asus-1603819254385/?page=1">Laptop Asus</a></li>
					<li><a href="/shop-all-item/hp-1603819254387/?page=1">Laptop HP</a></li>
					<li><a href="/shop-all-item/dell-1603819254386/?page=1">Laptop Dell</a></li>
					<li><a href="/shop-all-item/apple-1603819254390/?page=1">Laptop Apple</a></li>
					<li><a href="/shop-all-item/msi-1603819254391/?page=1">Laptop MSI</a></li>
				</ul>
			</div>
			<div class="col-md-2">
				<h5 class="font-weight-bold mt-5">CHÍNH SÁCH</h5>
				<ul class="list-unstyled thongtin">
					<li><a href="/chinh-sach/chinh-sach-hang-chinh-hang">Chính sách
							hàng chính hãng</a></li>
					<li><a href="/chinh-sach/chinh-sach-giao-hang">Chính sách giao
							hàng</a></li>
					<li><a href="/chinh-sach/chinh-sach-hoan-tien">Chính sách đổi
							trả và hoàn tiền</a></li>
					<li><a href="/chinh-sach/chinh-sach-bao-hanh">Chính sách bảo
							hành</a></li>

				</ul>
			</div>
			<div class="col-md-4">
				<h5 class="font-weight-bold mt-5">KẾT NỐI VỚI CHÚNG TÔI</h5>
				<div class="fb-page"
					data-href="https://www.facebook.com/Computer-CuongPham-110156041221389"
					data-tabs="timeline" data-width="300" data-height="230"
					data-small-header="false" data-adapt-container-width="true"
					data-hide-cover="false" data-show-facepile="true">
					<blockquote
						cite="https://www.facebook.com/Computer-CuongPham-110156041221389"
						class="fb-xfbml-parse-ignore">
						<a
							href="https://www.facebook.com/Computer-CuongPham-110156041221389">Computer
							CuongPham</a>
					</blockquote>
				</div>
			</div>
		</div>
		<hr>
		<div class="footer-copyright text-center py-3">
			©
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<fmt:formatDate pattern="yyyy" value="${now}" />
			Copyright: <a href="https://www.facebook.com/cuongphamptit999/">
				CuongPham.com</a>
		</div>
	</div>
</div>

<div id="toTop">
	<img src="${pageContext.request.contextPath}/img/arrow5-up-512.png"
		id="onTop" />
</div>

<div id="hotline">
	<!-- Messenger Plugin chat Code -->
	<a href="tel:0961656067" class="call-icon" rel="nofollow"> <img
		src="${pageContext.request.contextPath}/img/icon-hotline.gif"
		id="hotline_img"></a>
</div>

<!-- Messenger Plugin chat Code -->
<div id="fb-root"></div>
<script>
	window.fbAsyncInit = function() {
		FB.init({
           	xfbml            : true,
            	version          : 'v10.0'
          	});
        };

        (function(d, s, id) {
          var js, fjs = d.getElementsByTagName(s)[0];
          if (d.getElementById(id)) return;
          js = d.createElement(s); js.id = id;
          js.src = 'https://connect.facebook.net/vi_VN/sdk/xfbml.customerchat.js';
          fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));
</script>
<!-- Your Plugin chat code -->
<div class="fb-customerchat"
     attribution="page_inbox"
     page_id="110156041221389">
</div>
