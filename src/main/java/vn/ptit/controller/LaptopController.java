package vn.ptit.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import vn.ptit.beans.FilterMap;
import vn.ptit.beans.GooglePojo;
import vn.ptit.entities.Bill;
import vn.ptit.entities.BoughtLaptop;
import vn.ptit.entities.Laptop;
import vn.ptit.entities.Shipment;
import vn.ptit.entities.UserInfo;
import vn.ptit.repositories.BillRepository;
import vn.ptit.repositories.BannerRepository;
import vn.ptit.repositories.LaptopManufacturerRepository;
import vn.ptit.repositories.LaptopRepository;
import vn.ptit.repositories.UserInfoRepository;
import vn.ptit.services.LaptopService;
import vn.ptit.services.PaypalService;
import vn.ptit.services.ShipmentService;
import vn.ptit.services.UserService;

@Controller
public class LaptopController {
	@Autowired
	LaptopRepository laptopRepository;
	@Autowired
	LaptopManufacturerRepository laptopManufacturerRepository;
	@Autowired
	LaptopService laptopsService;
	@Autowired
	BillRepository billRepository;
	@Autowired
	UserService userService;
	@Autowired
	BannerRepository bannerRepository;
	@Autowired
	Configuration config;
	@Autowired
	JavaMailSender sender;
	@Autowired
	PaypalService service;
	@Autowired
	ShipmentService shipmentService;
	@Autowired UserInfoRepository userInfoRepository;

	@RequestMapping(value = { "/item-details/{laptopSeo}" }, method = { RequestMethod.GET })
	public String laptop_details(@PathVariable String laptopSeo, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("laptop", laptopsService.getLaptopBySeo(laptopSeo).get(0));

		List<Laptop> laptops = laptopsService.getLaptopBySeo(laptopSeo).get(0).getLaptopManufacturer().getLaptops();
		List<Laptop> laptops2 = new ArrayList<>();
		for (int i = 0; i < laptops.size(); i++) {
			if (!laptops.get(i).getSeo().equalsIgnoreCase(laptopSeo)) {
				laptops2.add(laptops.get(i));
			}
		}
		Collections.shuffle(laptops2);
		model.addAttribute("laptop_same", laptops2);

		return "item_details";
	}

	@RequestMapping(value = { "/shop-all-item" }, method = { RequestMethod.GET })
	public String laptops(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		int pageNumber = Integer.parseInt(request.getParameter("page"));

		String price = request.getParameter("price");
		String cpu = request.getParameter("cpu");
		String ram = request.getParameter("ram");
		String oCung = request.getParameter("oCung");
		String vga = request.getParameter("vga");
		String manHinh = request.getParameter("manHinh");
		String status = request.getParameter("status");
		String sort = request.getParameter("sort");

		List<FilterMap> listFilter = new ArrayList<>();
		if (price != null) {
			FilterMap filter = new FilterMap("price", price);
			listFilter.add(filter);
			model.addAttribute("price", price);
		}
		if (cpu != null) {
			FilterMap filter = new FilterMap("cpu", cpu);
			listFilter.add(filter);
			model.addAttribute("cpu", cpu);
		}
		if (ram != null) {
			FilterMap filter = new FilterMap("ram", ram);
			listFilter.add(filter);
			model.addAttribute("ram", ram);
		}
		if (oCung != null) {
			FilterMap filter = new FilterMap("oCung", oCung);
			listFilter.add(filter);
			model.addAttribute("oCung", oCung);
		}
		if (vga != null) {
			FilterMap filter = new FilterMap("vga", vga);
			listFilter.add(filter);
			model.addAttribute("vga", vga);
		}
		if (manHinh != null) {
			FilterMap filter = new FilterMap("manHinh", manHinh);
			listFilter.add(filter);
			model.addAttribute("manHinh", manHinh);
		}
		if (status != null) {
			FilterMap filter = new FilterMap("status", status);
			listFilter.add(filter);
			model.addAttribute("status", status);
		}
		if (sort != null) {
			FilterMap filter = new FilterMap("sort", sort);
			listFilter.add(filter);
			model.addAttribute("sort", sort);
		}

		model.addAttribute("laptop", laptopsService.searchAllItem(pageNumber, listFilter));
		model.addAttribute("banner", laptopsService.getBanner().get(laptopsService.getBanner().size() - 1));

		return "shop_all_item";
	}

	@RequestMapping(value = { "/shop-all-item/{laptopManufacturerSeo}" }, method = { RequestMethod.GET })
	public String indexWithProductType(@PathVariable String laptopManufacturerSeo, final ModelMap model,
			final HttpServletRequest request, final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		int pageNumber = Integer.parseInt(request.getParameter("page"));

		String price = request.getParameter("price");
		String cpu = request.getParameter("cpu");
		String ram = request.getParameter("ram");
		String oCung = request.getParameter("oCung");
		String vga = request.getParameter("vga");
		String manHinh = request.getParameter("manHinh");
		String status = request.getParameter("status");
		String sort = request.getParameter("sort");

		List<FilterMap> listFilter = new ArrayList<>();
		if (price != null) {
			FilterMap filter = new FilterMap("price", price);
			listFilter.add(filter);
			model.addAttribute("price", price);
		}
		if (cpu != null) {
			FilterMap filter = new FilterMap("cpu", cpu);
			listFilter.add(filter);
			model.addAttribute("cpu", cpu);
		}
		if (ram != null) {
			FilterMap filter = new FilterMap("ram", ram);
			listFilter.add(filter);
			model.addAttribute("ram", ram);
		}
		if (oCung != null) {
			FilterMap filter = new FilterMap("oCung", oCung);
			listFilter.add(filter);
			model.addAttribute("oCung", oCung);
		}
		if (vga != null) {
			FilterMap filter = new FilterMap("vga", vga);
			listFilter.add(filter);
			model.addAttribute("vga", vga);
		}
		if (manHinh != null) {
			FilterMap filter = new FilterMap("manHinh", manHinh);
			listFilter.add(filter);
			model.addAttribute("manHinh", manHinh);
		}
		if (status != null) {
			FilterMap filter = new FilterMap("status", status);
			listFilter.add(filter);
			model.addAttribute("status", status);
		}
		if (sort != null) {
			FilterMap filter = new FilterMap("sort", sort);
			listFilter.add(filter);
			model.addAttribute("sort", sort);
		}

		model.addAttribute("laptop",
				laptopsService.searchManufacturerItem(laptopManufacturerSeo, pageNumber, listFilter));
		model.addAttribute("seoLaptopManufacturer", laptopManufacturerSeo);
		model.addAttribute("banner", laptopsService.getBanner().get(laptopsService.getBanner().size() - 1));

		return "shop_all_item";
	}

	@RequestMapping(value = { "/cart" }, method = { RequestMethod.GET })
	public String viewGiohang(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("listShipment", shipmentService.findAllShipment());

		return "shop_cart";
	}

	@RequestMapping(value = { "/cart/checkout" }, method = { RequestMethod.POST })
	public String clickToCheckout(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));
		model.addAttribute("listShipment", shipmentService.findAllShipment());

		HttpSession httpSession = request.getSession();

		String address = request.getParameter("address");
		if (address.isEmpty()) {
			model.addAttribute("status", "faileAddress");
			return "shop_cart";
		}
		String paymentType = request.getParameter("paymentType");
		int shipmentId = Integer.parseInt(request.getParameter("shipmentId"));
		Shipment shipment = shipmentService.findShipmentById(shipmentId);

		vn.ptit.entities.Payment payment = new vn.ptit.entities.Payment();

		payment.setPayment_type(paymentType);
		payment.setAll_money(((BigDecimal) httpSession.getAttribute("tongTien")).add(shipment.getPrice()));

		List<BoughtLaptop> carts = new ArrayList<>();
		if (httpSession.getAttribute("giohang") != null) {
			carts = (List<BoughtLaptop>) httpSession.getAttribute("giohang");
		}

		Bill bill = new Bill();

		for (BoughtLaptop boughtLaptop : carts) {
			bill.addBoughtProduct(boughtLaptop);
		}

		bill.addPayment(payment);

		bill.setShipment(shipment);
		bill.setDate_create(new Date());
		bill.setStatus(true);

		httpSession.setAttribute("bill", bill);
		httpSession.setAttribute("address", address);

		return "redirect:/cart/checkout/view";
	}

	@RequestMapping(value = { "/cart/checkout/view" }, method = { RequestMethod.GET })
	public String viewCheckout(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		UserInfo userInfo = new UserInfo();

		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("googleAcc") != null) {
			GooglePojo googlePojo = (GooglePojo) httpSession.getAttribute("googleAcc");
			userInfo = userService.loadUserByUsername(googlePojo.getEmail()+"@gmail.com");
		} else if (httpSession.getAttribute("faceAcc") != null) {
			com.restfb.types.User user = (com.restfb.types.User) httpSession.getAttribute("faceAcc");
			userInfo = userService.loadUserByUsername(user.getEmail());
		} else {
			userInfo = userService.loadUserByUsername(request.getRemoteUser());
		}
		userInfo.setAddress((String) httpSession.getAttribute("address"));
		userInfoRepository.save(userInfo);
		
		Bill bill = new Bill();
		if (httpSession.getAttribute("bill") != null) {
			bill = (Bill) httpSession.getAttribute("bill");
		}
		bill.setUserInfo(userInfo);

		return "shop_checkout";
	}

	@RequestMapping(value = { "/cart/checkout/finish" }, method = { RequestMethod.GET })
	public String saveCheckout(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, MessagingException, IOException, TemplateException {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		HttpSession httpSession = request.getSession();
		Bill bill = new Bill();
		if (httpSession.getAttribute("bill") != null) {
			bill = (Bill) httpSession.getAttribute("bill");
		}

		for (int i = 0; i < bill.getBoughtLaptops().size(); i++) {
			Integer soLuongNhap = laptopsService.getLaptopBySeo(bill.getBoughtLaptops().get(i).getLaptop().getSeo())
					.get(0).getSoLuongNhap() - bill.getBoughtLaptops().get(i).getAmount();
			laptopsService.getLaptopBySeo(bill.getBoughtLaptops().get(i).getLaptop().getSeo()).get(0)
					.setSoLuongNhap(soLuongNhap);
		}

		if (bill.getPayment().getPayment_type().equalsIgnoreCase("Thanh toán qua chuyển khoản qua PayPal")) {
			// ---------------------------- Thanh toan qua paypal
			// -------------------------------------------------
			try {
				Payment payment = service.createPayment(
						bill.getPayment().getAll_money().multiply(new BigDecimal(getTyGia())), "USD", "paypal", "sale",
						"test", "https://computer-cuongpham.herokuapp.com/cart/checkout/finish/cancel",
						"https://computer-cuongpham.herokuapp.com/cart/checkout/finish/success");
				httpSession.setAttribute("bill_paypal", bill);
				for (Links link : payment.getLinks()) {
					if (link.getRel().equals("approval_url")) {
						return "redirect:" + link.getHref();
					}
				}

			} catch (PayPalRESTException e) {

				e.printStackTrace();
			}
		} else {

			// ---------------------------- Gửi mail
			// -------------------------------------------------
			sendMail(bill);

			billRepository.save(bill);
			model.addAttribute("status", "success");
			httpSession.setAttribute("giohang", null);
			httpSession.setAttribute("soLuongMua", 0);
			httpSession.setAttribute("tongTien", 0);
			httpSession.setAttribute("bill", null);
		}

		return "shop_cart";

	}

	@RequestMapping(value = { "/cart/checkout/finish/cancel" }, method = { RequestMethod.GET })
	public String cancelPay(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("status", "failePayPal");
		return "shop_cart";
	}

	@RequestMapping(value = { "/cart/checkout/finish/success" }, method = { RequestMethod.GET })
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
			final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, MessagingException,
			IOException, TemplateException {
		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		try {
			Payment payment = service.executePayment(paymentId, payerId);
			System.out.println(payment.toJSON());
			if (payment.getState().equals("approved")) {
				HttpSession httpSession = request.getSession();
				Bill bill = new Bill();
				if (httpSession.getAttribute("bill") != null) {
					bill = (Bill) httpSession.getAttribute("bill");
				}

				// ---------------------------- Gửi mail
				// -------------------------------------------------
				sendMail(bill);
				billRepository.save(bill);

				model.addAttribute("status", "success");
				httpSession.setAttribute("giohang", null);
				httpSession.setAttribute("soLuongMua", 0);
				httpSession.setAttribute("tongTien", 0);
				httpSession.setAttribute("bill", null);
				return "shop_cart";
			}
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
		}

		model.addAttribute("status", "failePayPal");
		return "shop_cart";
	}

	public void sendMail(Bill bill) throws MessagingException, TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Map<String, Object> map = new HashMap<>();
		map.put("date", bill.getDate_create().toString());
		map.put("bill", bill);
		MimeMessage message = sender.createMimeMessage();

		// set mediaType
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		// add attachment
		// helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

		Template t = config.getTemplate("email-template.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, map);

		helper.setTo(bill.getUserInfo().getEmail());
		helper.setText(html, true);
		helper.setSubject("HÓA ĐƠN COMPUTER");
		helper.setFrom("computercuongpham999@gmail.com", "CÔNG TY COMPUTER CuongPham");
		sender.send(message);
	}

	public double getTyGia() {
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL(
					"https://free.currconv.com/api/v7/convert?apiKey=73186a7f4f40e1abc58c&q=VND_USD&compact=y");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(url.openConnection().getInputStream()));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line);
			}
			bufferedReader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject object = new JSONObject(content.toString());
		JSONObject object1 = object.getJSONObject("VND_USD");
		double tyGia = object1.getDouble("val");
		return tyGia;
	}

}
