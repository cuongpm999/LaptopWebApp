$(document).ready(function() {
	// jquery cho onTop
	$(document).scroll(function() {
		if ($(document).scrollTop() != 0) {
			$("#onTop").fadeIn();
		} else {
			$("#onTop").fadeOut();
		}
	});
	$("#onTop").click(function() {
		$("html, body").animate({ scrollTop: 0 }, 700);
	});

	// jquery cho sticky scroll
	$('#waypoint_here').waypoint(function(direction) {
		if (direction == "down") {
			$('.head-top').addClass('fixed-top');

			$('.head-top-row').addClass('head-top-row-justify-content');
			$('.item-logo').addClass('item-logo-display');
			$('.widget_search').addClass('widget_search-margin-top-text-align');
			$('.item-action').addClass('item-action-margin-top-justify-content-flex-start-padding');
			$('.taikhoan').addClass('d-flex-taikhoan-margin-right');
			$('.btn-all-item').addClass('btn-all-item-display');

		} else {
			$('.head-top').removeClass('fixed-top');

			$('.head-top-row').removeClass('head-top-row-justify-content');
			$('.item-logo').removeClass('item-logo-display');
			$('.widget_search').removeClass('widget_search-margin-top-text-align');
			$('.item-action').removeClass('item-action-margin-top-justify-content-flex-start-padding');
			$('.taikhoan').removeClass('d-flex-taikhoan-margin-right');
			$('.btn-all-item').removeClass('btn-all-item-display');

		}
	}, {
		offset: '700px'
	})

	$("#summernote").summernote({
		placeholder: 'Nội dung chi tiết',
		tabsize: 2,
		height: 300
	});

	$("#myInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#myTable tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});

	$("#searchNameLaptop").autocomplete({
		source: '/rest/api/data/autocomplete'
	});

});

var Shop = {

	addToCart: function(laptopSeo) {
		var data = {};
		data["laptopSeo"] = laptopSeo;

		$.ajax({
			url: "/rest/api/cart/addToCart",
			type: "post",
			contentType: "application/json", 		
			data: JSON.stringify(data), 

			dataType: "json", 
			success: function(jsonResult) { 
				if (jsonResult.status == "600") {
					$('#modalAddItem').modal('show');
					$("#count_shopping_cart_store").html(jsonResult.data);
				}
				else if (jsonResult.status == "100") {
					$('#modalAddItemFailed').modal('show');
				}
			}
		});
	},

	addToCartNow: function(laptopSeo) {
		var data = {};
		data["laptopSeo"] = laptopSeo;

		$.ajax({
			url: "/rest/api/cart/addToCart",
			type: "post",
			contentType: "application/json", 			
			data: JSON.stringify(data), 

			dataType: "json", 
			success: function(jsonResult) { 
				location.href = "/cart";
				$("#count_shopping_cart_store").html(jsonResult.data);
			}
		});
	},

	deleteCart: function(modalID, laptopSeo) {
		$("#deleteproduct").click(function() {
			var data = {};
			data["laptopSeo"] = laptopSeo;

			$.ajax({
				url: "/rest/api/cart/deleteCart",
				type: "post",
				contentType: "application/json", 				
				data: JSON.stringify(data), 

				dataType: "json", 
				success: function(jsonResult) { 
					if (jsonResult.status == "500") {
						location.href = "/cart";
						$("#count_shopping_cart_store").html(jsonResult.data);
					}
				}
			});
		});
		$('#' + modalID).modal('show');
	},

	editCart: function(modalID, laptopSeo) {
		$("#editOk").click(function() {
			var amount = $("#amount" + laptopSeo).val();
			var data = {};
			data["amount"] = amount;
			data["laptopSeo"] = laptopSeo;

			$.ajax({
				url: "/rest/api/cart/editCart",
				type: "post",
				contentType: "application/json", 		
				data: JSON.stringify(data), 

				dataType: "json", 
				success: function(jsonResult) { 
					if (jsonResult.status == "400") {
						location.href = "/cart";
						$("#count_shopping_cart_store").html(jsonResult.data);
					}
				}
			});
		});
		$('#' + modalID).modal('show');
	},

	deleteForm: function(modalID, entityId, restApi) {
		$("#deleteOk").click(function() {
			var data = {};
			data["entityId"] = entityId;
			$.ajax({
				url: restApi,
				type: "post",
				contentType: "application/json", 		
				data: JSON.stringify(data), 
		
				dataType: "json", 
				success: function(jsonResult) { 
					if (jsonResult.status == "69") {
						window.location.reload();

					}
				}
			});
		});
		$('#' + modalID).modal('show');
	},

	goNext() {
		var tech = Shop.getUrlParameter('page') || 1;
		Shop.addUrlParameter('page', (parseInt(tech) + 1));
	},

	getUrlParameter: function(sParam) {
		var sPageURL = window.location.search.substring(1),
			sURLVariables = sPageURL.split('&'),
			sParameterName,
			i;

		for (i = 0; i < sURLVariables.length; i++) {
			sParameterName = sURLVariables[i].split('=');

			if (sParameterName[0] === sParam) {
				return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
			}
		}
	},

	goPrev() {
		var tech = Shop.getUrlParameter('page') || 1;
		if (parseInt(tech) > 1)
			Shop.addUrlParameter('page', (parseInt(tech) - 1));

	},

	addUrlParameter(name, value) {
		var searchParams = new URLSearchParams(window.location.search);
		searchParams.set(name, value);
		window.location.search = searchParams.toString();
	},

	deleteUrlParameter(name) {
		var searchParams = new URLSearchParams(window.location.search);
		searchParams.delete(name);
		window.location.search = searchParams.toString();
	}

}
