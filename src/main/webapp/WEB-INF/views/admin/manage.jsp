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
	href="${pageContext.request.contextPath}/css/manage.css">
<!-- ----------------- -->

<title>Laptop Project</title>
</head>

<body>

	<div id="fb-root"></div>
	<script async defer crossorigin="anonymous"
		src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v8.0"
		nonce="ChmFo6ST"></script>

	<!-- MAIN -->
	<div id="main">
		<%@ include file="/WEB-INF/views/admin/admin_header.jsp"%>
		<div class="page-container">
			<div class="header-container">
				<nav class="navbar">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbardrop"
							data-toggle="dropdown"> <c:choose>
									<c:when test="${empty userDis.userAttachment.name}">
										<img class="icon" alt="user" src="/img/anh-dai-dien-1.jpg">
									</c:when>
									<c:when test="${not empty userDis.userAttachment.name}">
										<img class="icon" alt="user"
											src="/files_users/${userDis.userAttachment.name }">
									</c:when>
								</c:choose> <span class="text-icon">${userDis.username }</span>
						</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="/user-details"><i
									class="far fa-user"></i>Profile</a> <a class="dropdown-item"
									href="/logout"><i class="fas fa-power-off"></i>Logout</a>
							</div></li>
					</ul>
				</nav>
			</div>

			<div class="main-content">
				<div class="row">
					<div class="col-md-3">
						<div class="r3_counter_box">
							<img src="/img/icons8-money-100.png" alt="manage">
							<div class="stats">
								<h5>
									<strong> <c:choose>
											<c:when test="${totalMoney>99999999999 }">
												<fmt:formatNumber type="number" maxFractionDigits="3"
													value="${totalMoney/1000000000}" /> tỷ
											</c:when>
											<c:when test="${totalMoney<=99999999999 }">
												<fmt:formatNumber type="number" maxFractionDigits="3"
													value="${totalMoney}" />
											</c:when>

										</c:choose>
									</strong>
								</h5>
								<span>Total Revenue</span>
							</div>
						</div>
					</div>

					<div class="col-md-3">
						<div class="r3_counter_box">
							<img src="/img/icons8-user-male-100.png" alt="manage">
							<div class="stats">
								<h5>
									<strong><fmt:formatNumber type="number"
											maxFractionDigits="3" value="${totalUser }" /> </strong>
								</h5>
								<span>Total Users</span>
							</div>
						</div>
					</div>

					<div class="col-md-3">
						<div class="r3_counter_box">
							<img src="/img/icons8-laptop-100.png" alt="manage">
							<div class="stats">
								<h5>
									<strong> <fmt:formatNumber type="number"
											maxFractionDigits="3" value="${totalOrder }" />
									</strong>
								</h5>
								<span>Total Orders</span>
							</div>
						</div>
					</div>

					<div class="col-md-3">
						<div class="r3_counter_box">
							<img src="/img/icons8-cursor-100.png" alt="manage">
							<div class="stats">
								<h5>
									<strong> <fmt:formatNumber type="number"
											maxFractionDigits="3" value="${totalVisit}" />
									</strong>
								</h5>
								<span>Total Visits</span>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-7 chart">
						<div class="charts">
							<canvas id="myChart"></canvas>
							<div class="btn-report">
								<a href="javascript:void(0);" onclick="Report.reportView()"><i
									class="fas fa-chart-bar"></i> Thống kê</a>
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="table-product scrollbar1" id="style-1">
							<table class="table">
								<thead style="background-color: #03a9f4; color: white;">
									<tr>
										<th>Name</th>
										<th>Amount</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="listProductRP" items="${listProductRP }">
										<tr>
											<td>${listProductRP.name }</td>
											<td>${listProductRP.quantity }</td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
					<script>
     					var datas=[0, 0, 0, 0, 0, 0];
     					var labels=['January', 'February', 'March', 'April', 'May', 'June'];
     					
                    	const data = {
                        	labels: labels,
                        	datasets: [{
                            label: 'Thống kê tổng thu nhập theo tháng',
                            backgroundColor: '#03a9f4',
                            borderColor: '#72777a',
                            data: datas,
                        	}]
                   		};
     					const config = {
                            type: 'line',
                            data,
                            options: {}
                    	};
                      	var myChart = new Chart(
                            document.getElementById('myChart'),
                            config
                        );

                        function updateData(chart) {    
                            myChart.data.datasets[0].data.splice(0,6); 
                            myChart.data.labels.splice(0,6); 
                            
                           	for(let i=0;i<6;i++){
                            	myChart.data.datasets[0].data.push(datas[i]);
                            	myChart.data.labels.push(labels[i]);
                            }
                            chart.update();
                      	}
                    	var Report = {
                    		reportView: function() {
                        		
                        		$.ajax({
                        			url: "/rest/api/report/chart",
                        			type: "get",
                        			
                        			dataType: "json", // dữ liệu từ Rest trả về là json.
                        			success: function(jsonResult) { // được gọi khi web-service
                        				// trả
                        				// về dữ liệu.
                        				if (jsonResult.status == "333") {
                        					datas=jsonResult.data.data;
                        					labels=jsonResult.data.label;
                        					updateData(myChart);  

                        				}
                        			}
                        		});
                        	},
                          }

                    </script>
				</div>

				<div class="row">
					<div class="col-md-6">
						<iframe
							src="https://calendar.google.com/calendar/embed?height=600&amp;wkst=1&amp;bgcolor=%23ffffff&amp;ctz=Asia%2FHo_Chi_Minh&amp;src=cGhhbWN1b25ndGgyMDAwQGdtYWlsLmNvbQ&amp;src=Y2xhc3Nyb29tMTE0Mzk5MjQ3MTIwMjM5NTc0MTk4QGdyb3VwLmNhbGVuZGFyLmdvb2dsZS5jb20&amp;src=YWRkcmVzc2Jvb2sjY29udGFjdHNAZ3JvdXAudi5jYWxlbmRhci5nb29nbGUuY29t&amp;src=Y2xhc3Nyb29tMTA2NDAwMzExNDc2NDc2Njk0OTk1QGdyb3VwLmNhbGVuZGFyLmdvb2dsZS5jb20&amp;color=%23039BE5&amp;color=%230047a8&amp;color=%2333B679&amp;color=%23007b83"
							width="100%" height="600px" frameborder="0" scrolling="no"></iframe>
					</div>
					<div class="col-md-6">
						<iframe
							src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3619.614774027218!2d105.78734215530791!3d20.96544475848515!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ad29b81cac63%3A0xe5796c9507084b90!2zMTlhIFBo4buRIFhhIExhLCBQLiBQaMO6YyBMYSwgSMOgIMSQw7RuZywgSMOgIE7hu5lpIDEyMTA4!5e0!3m2!1svi!2s!4v1619148320132!5m2!1svi!2s"
							width="100%" height="600px" style="border: 0;" allowfullscreen=""
							loading="lazy"></iframe>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 slide-view">
						<div id="demo" class="carousel slide" data-ride="carousel">

							<ul class="carousel-indicators">
								<li data-target="#demo" data-slide-to="0" class="active"></li>
								<li data-target="#demo" data-slide-to="1"></li>
							</ul>

							<div class="carousel-inner">
								<div class="carousel-item active">
									<div class="row">
										<div class="col-md-4">
											<img src="/img/slider1.jpg" alt="slide">
										</div>
										<div class="col-md-4">
											<img src="/img/slider2.jpg" alt="slide">
										</div>
										<div class="col-md-4">
											<img src="/img/slider3.jpg" alt="slide">
										</div>
									</div>

								</div>
								<div class="carousel-item">
									<div class="row">
										<div class="col-md-4">
											<img src="/img/slider4.jpg" alt="slide">
										</div>
										<div class="col-md-4">
											<img src="/img/slider5.jpg" alt="slide">
										</div>
										<div class="col-md-4">
											<img src="/img/slider6.jpg" alt="slide">
										</div>
									</div>
								</div>

							</div>

						</div>
					</div>
				</div>

			</div>
			<div class="footer">
				©
				<c:set var="now" value="<%=new java.util.Date()%>" />
				<fmt:formatDate pattern="yyyy" value="${now}" />
				Copyright: <a href="https://www.facebook.com/cuongphamptit999/">
					CuongPham.com</a>
			</div>

		</div>
	</div>

</body>

</html>
