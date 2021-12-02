package vn.ptit.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import vn.ptit.entities.BoughtLaptop;
import vn.ptit.entities.Laptop;
import vn.ptit.services.LaptopService;

public class CartUtils {
	@Autowired
	LaptopService laptopsService;

	public static void checkCart(Laptop laptop, HttpServletRequest request) {
		String laptopSeo = laptop.getSeo();
		// 1. Lấy danh sách sản phẩm trong giỏ hàng từ Session.
		List<BoughtLaptop> carts = new ArrayList<>();
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("giohang") != null) {
			carts = (List<BoughtLaptop>) httpSession.getAttribute("giohang");
		}

		// 2. kiểm tra sản phẩm đã có trong giỏ hàng chưa?
		boolean isExists = false;
		for (BoughtLaptop cart : carts) {
			if (cart.getLaptop().getSeo().equalsIgnoreCase(laptopSeo)) {
				isExists = true;
				break;
			}
		}

		// 3.
		if (!isExists) { // nếu chưa có trong giỏ hàng thì thêm mới.
			BoughtLaptop cart = new BoughtLaptop();
			cart.setLaptop(laptop);
			cart.setAmount(1);
			carts.add(cart);
		} else { // ngược lại cộng thêm số lượng cho sản phẩm đó.
			for (BoughtLaptop cart : carts) {
				if (cart.getLaptop().getSeo().equalsIgnoreCase(laptopSeo)) {
					cart.setAmount(cart.getAmount() + 1);
					break;
				}
			}
		}
		
		int soLuongMua=0;
		for(BoughtLaptop cart: carts) {
			soLuongMua+=cart.getAmount();
		}

		httpSession.setAttribute("status", null);
		httpSession.setAttribute("giohang", carts);
		httpSession.setAttribute("soLuongMua", soLuongMua);

	}

	public static void deleteCart(String cartSeo, HttpServletRequest request) {
		List<BoughtLaptop> carts = new ArrayList<>();
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("giohang") != null) {
			carts = (List<BoughtLaptop>) httpSession.getAttribute("giohang");
		}

		for (int i = 0; i < carts.size(); i++) {
			if (carts.get(i).getLaptop().getSeo().equalsIgnoreCase(cartSeo)) {
				carts.remove(i);
			}
		}
		
		int soLuongMua=0;
		for(BoughtLaptop cart: carts) {
			soLuongMua+=cart.getAmount();
		}
		httpSession.setAttribute("giohang", carts);
		httpSession.setAttribute("status", "successDelete");
		httpSession.setAttribute("soLuongMua", soLuongMua);
	}

	public static void editCart(String cartSeo, String amount, Integer soLuongNhap, HttpServletRequest request) {
		List<BoughtLaptop> carts = new ArrayList<>();
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("giohang") != null) {
			carts = (List<BoughtLaptop>) httpSession.getAttribute("giohang");
		}
		Integer soLuong = Integer.parseInt(amount);
		if (soLuongNhap >= soLuong) {

			for (int i = 0; i < carts.size(); i++) {

				if (carts.get(i).getLaptop().getSeo().equalsIgnoreCase(cartSeo)) {
					carts.get(i).setAmount(soLuong);
				}
			}
			
			int soLuongMua=0;
			for(BoughtLaptop cart: carts) {
				soLuongMua+=cart.getAmount();
			}
			httpSession.setAttribute("giohang", carts);
			httpSession.setAttribute("status", "successEdit");
			httpSession.setAttribute("soLuongMua", soLuongMua);
		} else {
			httpSession.setAttribute("status", "faileEdit");
		}
	}

	public static void tongTien(HttpServletRequest request) {
		List<BoughtLaptop> carts = new ArrayList<>();
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("giohang") != null) {
			carts = (List<BoughtLaptop>) httpSession.getAttribute("giohang");
		}
		BigDecimal t = new BigDecimal(0);
		for (int i = 0; i < carts.size(); i++) {
			BigDecimal x = new BigDecimal(carts.get(i).getAmount());
			BigDecimal y = carts.get(i).getLaptop().getPrice();
			t = t.add(x.multiply(y));
		}
		httpSession.setAttribute("tongTien", t);
	}

}
