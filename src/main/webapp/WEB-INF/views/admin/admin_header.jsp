<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- sử dụng taglibs của spring để bind-data từ end-point trả về. -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- tích hợp jstl vào jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<div class="sidebar">
	<div class="sidebar-inner">
		<div class="sidebar-logo">
			<a class="sidebar-link" href="/"> <img class="icon"
				src="/img/logo.png">
				<h5 class="logo-text">Admin Manage</h5>
			</a>
		</div>

		<nav class="navbar scrollbar" id="style-1">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/admin/manage"><i
						class="fas fa-home"></i>Dashboard</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"><i class="fas fa-list"></i>Manages</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/admin/list-user">Account</a> <a
							class="dropdown-item" href="/admin/list-banner">Banner</a> <a
							class="dropdown-item" href="/admin/list-contact">Contact</a> <a
							class="dropdown-item" href="/admin/list-manufacturer">Manufacturer</a>
						<a class="dropdown-item" href="/admin/list-laptop">Laptop</a> <a
							class="dropdown-item" href="/admin/list-shipment">Shipment</a> <a
							class="dropdown-item" href="/admin/list-bill">Bill</a>
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"><i class="fas fa-chart-bar"></i>Statistics</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/admin/statistic/laptop">Laptop</a>
						<a class="dropdown-item" href="/admin/statistic/user">User</a> <a
							class="dropdown-item" href="/admin/statistic/shipment">Shipment</a>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="#"><i
						class="fas fa-pencil-alt"></i>Forms</a></li>
				<li class="nav-item"><a class="nav-link" href="#"><i
						class="fas fa-envelope"></i>Email</a></li>
				<li class="nav-item"><a class="nav-link" href="#"><i
						class="fas fa-map"></i>Maps</a></li>
				<li class="nav-item"><a class="nav-link" href="#"><i
						class="far fa-comment-dots"></i>Chat</a></li>
				<li class="nav-item"><a class="nav-link" href="#"><i
						class="far fa-calendar-alt"></i>Calendar</a></li>
				<li class="nav-item"><a class="nav-link" href="#"><i
						class="far fa-copy"></i>Pages</a></li>

			</ul>

		</nav>
	</div>
</div>
