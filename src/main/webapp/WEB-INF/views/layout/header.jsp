<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- tich hop jstl vao jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- HEADER -->
<div id="header">
	<div class="head-top">
		<div class="container">
			<div class="head-top-row">
				<div class="item-logo">
					<a class="navbar-brand" href="/"> <img
						src="${pageContext.request.contextPath}/img/logo.png" alt="logo">
					</a>
				</div>
				<div class="btn-all-item">
					<div class="custom-btn">
						SẢN PHẨM<i class="fas fa-angle-down"></i>
					</div>
					<div class="dropdown-menu-pro">
						<a class="dropdown-item-pro" href="/shop-all-item/?page=1">Tất
							cả</a> <a class="dropdown-item-pro"
							href="/shop-all-item/filter-buy-sale/?page=1">Khuyến mại</a>
						<c:forEach var="laptopManufacturer_"
							items="${laptopManufacturer }">
							<a class="dropdown-item-pro"
								href="/shop-all-item/${laptopManufacturer_.seo }/?page=1">Laptop
								${laptopManufacturer_.name }</a>
						</c:forEach>
					</div>
				</div>
				<div class="item-search-action">
					<div class="widget_search">
						<form action="/shop-all-item/search-name/" method="get">
							<input name="name" type="search" class="form-control"
								placeholder="Tìm tên..." id="searchNameLaptop"/>
							<button type="submit" class="btn btn-success">
								<i class="fas fa-search"></i>
							</button>
						</form>
					</div>

					<div class="item-action">
						<div class="taikhoan">
							<span class="user-icon"> <i class="fas fa-user"></i>
							</span> <span class="user-text">Tài khoản</span>
							<div class="dropdown-menu-acc">
								<c:choose>
									<c:when test="${not empty googleAcc }">
										<a class="dropdown-item-acc" href="#"><img
											class="user-login-icon" alt="user"
											src="${googleAcc.getPicture() }"> <c:choose>
												<c:when test="${fn:length(googleAcc.getEmail())>15 }">
													${fn:substring(googleAcc.getEmail(), 0, 13)}...
												</c:when>
												<c:when test="${fn:length(googleAcc.getEmail())<=15 }">
													${googleAcc.getEmail() }
												</c:when>
											</c:choose> </a>
										<sec:authorize access="hasAnyRole('ADMIN','USER')">
											<a class="dropdown-item-acc" href="/logout"><img
												class="logInOut" alt="user"
												src="/img/icons8-logout-rounded-left-64.png">Log out</a>
										</sec:authorize>
									</c:when>

									<c:when test="${not empty faceAcc }">
										<a class="dropdown-item-acc" href="#"><img
											class="user-login-icon" alt="user"
											src="${faceAcc.getPicture().getUrl() }"> <c:choose>
												<c:when test="${fn:length(faceAcc.getName())>15 }">
													${fn:substring(faceAcc.getName(), 0, 13)}...
												</c:when>
												<c:when test="${fn:length(faceAcc.getName())<=15 }">
													${faceAcc.getName() }
												</c:when>
											</c:choose> </a>
										<sec:authorize access="hasAnyRole('ADMIN','USER')">
											<a class="dropdown-item-acc" href="/logout"><img
												class="logInOut" alt="user"
												src="/img/icons8-logout-rounded-left-64.png">Log out</a>
										</sec:authorize>
									</c:when>

									<c:when test="${not empty userDis}">
										<sec:authorize access="hasRole('ADMIN')">
											<a class="dropdown-item-acc" href="/admin/manage"><img
												class="logInOut" alt="user" src="/img/icons8-home-256.png">Dashboard</a>
										</sec:authorize>
										<a class="dropdown-item-acc" href="/user-details"><c:choose>
												<c:when test="${empty userDis.userAttachment.name}">
													<img class="user-login-icon" alt="user"
														src="/img/anh-dai-dien-1.jpg">
												</c:when>
												<c:when test="${not empty userDis.userAttachment.name}">
													<img class="user-login-icon" alt="user"
														src="/files_users/${userDis.userAttachment.name }">
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${fn:length(userDis.username)>15 }">
													${fn:substring(userDis.username, 0, 13)}...
												</c:when>
												<c:when test="${fn:length(userDis.username)<=15 }">
													${userDis.username }
												</c:when>
											</c:choose> </a>
										<sec:authorize access="hasAnyRole('ADMIN','USER')">
											<a class="dropdown-item-acc" href="/logout"><img
												class="logInOut" alt="user"
												src="/img/icons8-logout-rounded-left-64.png">Log out</a>
										</sec:authorize>
									</c:when>

									<c:when
										test="${empty userDis && empty googleAcc && empty faceAcc}">
										<a class="dropdown-item-acc" href="/login"><img
											class="logInOut" alt="user" src="/img/icons8-login-64.png">Log
											in</a>
									</c:when>
								</c:choose>
							</div>
						</div>

						<a class="d-flex giohang" href="/cart"> <span
							class="cart-icon"> <i class="fas fa-shopping-bag"></i>
						</span> <c:choose>
								<c:when test="${empty soLuongMua}">
									<b id="count_shopping_cart_store">0</b>
								</c:when>
								<c:when test="${not empty soLuongMua}">
									<b id="count_shopping_cart_store">${soLuongMua}</b>
								</c:when>
							</c:choose> <span class="cart-text">Giỏ hàng</span></a>
					</div>

				</div>
			</div>
		</div>
	</div>

	<div class="head-bottom">
		<div class="container">
			<nav class="navbar navbar-expand-md">
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#collapsibleNavbar">
					<i class="fas fa-bars"></i>
				</button>

				<div class="collapse navbar-collapse" id="collapsibleNavbar">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/">TRANG
								CHỦ</a></li>
						<li class="nav-item"><a class="nav-link" href="/gioi-thieu">GIỚI
								THIỆU</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/shop-all-item/filter-buy-sale/?page=1">KHUYẾN MẠI</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbardrop"
							data-toggle="dropdown">SẢN PHẨM</a>
							<div class="dropdown-menu pro">
								<a class="dropdown-item" href="/shop-all-item/?page=1">Tất
									cả</a>
								<c:forEach var="laptopManufacturer_"
									items="${laptopManufacturer }">
									<a class="dropdown-item"
										href="/shop-all-item/${laptopManufacturer_.seo }/?page=1">Laptop
										${laptopManufacturer_.name }</a>
								</c:forEach>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbardrop"
							data-toggle="dropdown">CHÍNH SÁCH</a>
							<div class="dropdown-menu policy">
								<a class="dropdown-item"
									href="/chinh-sach/chinh-sach-hang-chinh-hang">Chính sách
									hàng chính hãng</a> <a class="dropdown-item"
									href="/chinh-sach/chinh-sach-giao-hang">Chính sách giao
									hàng</a> <a class="dropdown-item"
									href="/chinh-sach/chinh-sach-hoan-tien">Chính sách đổi trả
									và hoàn tiền</a> <a class="dropdown-item"
									href="/chinh-sach/chinh-sach-bao-hanh">Chính sách bảo hành</a>
							</div></li>
						<li class="nav-item"><a class="nav-link" href="/contact">LIÊN
								HỆ</a></li>
						<li class="nav-item dropdown acc-display"><a
							class="nav-link dropdown-toggle" href="#" id="navbardrop"
							data-toggle="dropdown">TÀI KHOẢN</a>
							<div class="dropdown-menu acc">
								<c:choose>
									<c:when test="${not empty googleAcc }">
										<a class="dropdown-item" href="#"><img
											class="user-login-icon" alt="user"
											src="${googleAcc.getPicture() }"> <c:choose>
												<c:when test="${fn:length(googleAcc.getEmail())>15 }">
													${fn:substring(googleAcc.getEmail(), 0, 13)}...
												</c:when>
												<c:when test="${fn:length(googleAcc.getEmail())<=15 }">
													${googleAcc.getEmail() }
												</c:when>
											</c:choose> </a>
										<sec:authorize access="hasAnyRole('ADMIN','USER')">
											<a class="dropdown-item" href="/logout"><img
												class="logInOut" alt="user"
												src="/img/icons8-logout-rounded-left-64.png">Log out</a>
										</sec:authorize>
									</c:when>

									<c:when test="${not empty faceAcc }">
										<a class="dropdown-item" href="#"><img
											class="user-login-icon" alt="user"
											src="${faceAcc.getPicture().getUrl() }"> <c:choose>
												<c:when test="${fn:length(faceAcc.getName())>15 }">
													${fn:substring(faceAcc.getName(), 0, 13)}...
												</c:when>
												<c:when test="${fn:length(faceAcc.getName())<=15 }">
													${faceAcc.getName() }
												</c:when>
											</c:choose> </a>
										<sec:authorize access="hasAnyRole('ADMIN','USER')">
											<a class="dropdown-item" href="/logout"><img
												class="logInOut" alt="user"
												src="/img/icons8-logout-rounded-left-64.png">Log out</a>
										</sec:authorize>
									</c:when>

									<c:when test="${not empty userDis}">
										<sec:authorize access="hasRole('ADMIN')">
											<a class="dropdown-item" href="/admin/manage"><img
												class="logInOut" alt="user" src="/img/icons8-home-256.png">Dashboard</a>
										</sec:authorize>
										<a class="dropdown-item" href="/user-details"><img
											class="user-login-icon" alt="user"
											src="/img/anh-dai-dien-1.jpg">
											<c:choose>
												<c:when test="${fn:length(userDis.username)>15 }">
													${fn:substring(userDis.username, 0, 13)}...
												</c:when>
												<c:when test="${fn:length(userDis.username)<=15 }">
													${userDis.username }
												</c:when>
											</c:choose> </a>
										<sec:authorize access="hasAnyRole('ADMIN','USER')">
											<a class="dropdown-item" href="/logout"><img
												class="logInOut" alt="user"
												src="/img/icons8-logout-rounded-left-64.png">Log out</a>
										</sec:authorize>
									</c:when>

									<c:when
										test="${empty userDis && empty googleAcc && empty faceAcc}">
										<a class="dropdown-item" href="/login"><img
											class="logInOut" alt="user" src="/img/icons8-login-64.png">Log
											in</a>
									</c:when>
								</c:choose>
							</div></li>

					</ul>
				</div>
			</nav>
		</div>
	</div>
</div>
<!---->
