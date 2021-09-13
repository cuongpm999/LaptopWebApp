package vn.ptit.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.beans.CartUtils;
import vn.ptit.beans.ChartReport;
import vn.ptit.beans.ReportUtils;
import vn.ptit.entities.AjaxResponse;
import vn.ptit.entities.Bill;
import vn.ptit.entities.BoughtLaptop;
import vn.ptit.entities.Laptop;
import vn.ptit.repositories.ContactRepository;
import vn.ptit.repositories.BillRepository;
import vn.ptit.repositories.BannerRepository;
import vn.ptit.repositories.LaptopManufacturerRepository;
import vn.ptit.repositories.LaptopRepository;
import vn.ptit.repositories.UserInfoRepository;
import vn.ptit.services.LaptopService;

@RestController
@RequestMapping("/rest/api")
public class AjaxController {

	@Autowired
	ContactRepository contactRepository;
	@Autowired
	LaptopRepository laptopRepository;
	@Autowired
	LaptopManufacturerRepository laptopManufacturerRepository;
	@Autowired
	LaptopService laptopsService;
	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired BillRepository hoaDonRepository;
	@Autowired BannerRepository idBannerRepository;

	@PostMapping(value = "/cart/addToCart")
	public ResponseEntity<AjaxResponse> addToCart(@RequestBody final Map<String, Object> data, final ModelMap model,
			final HttpServletRequest request, final HttpServletResponse response) {

		String laptopSeo = (String) data.get("laptopSeo");
		Integer soLuongNhap = laptopsService.getLaptopBySeo(laptopSeo).get(0).getSoLuongNhap();
	
		List<BoughtLaptop> carts = new ArrayList<>();
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("giohang") != null) {
			carts = (List<BoughtLaptop>) httpSession.getAttribute("giohang");
		}
		
		Integer soLuongClick = 0;
		for (int i = 0; i < carts.size(); i++) {
			if (carts.get(i).getLaptop().getSeo().equalsIgnoreCase(laptopSeo)) {
				soLuongClick = carts.get(i).getAmount();
				break;
			}
		}

		
		if ((soLuongClick + 1) > soLuongNhap)
			return ResponseEntity.ok(new AjaxResponse(100, data));
		else {
			Laptop laptop = laptopsService.getLaptopBySeo(laptopSeo).get(0);
			
			// de hien khong bi loi lazy
			laptop.getLaptopAttachments().get(0);
			
			CartUtils.checkCart(laptop, request);
			CartUtils.tongTien(request);
			Integer soLuongMua = (Integer) httpSession.getAttribute("soLuongMua");
			return ResponseEntity.ok(new AjaxResponse(600, soLuongMua));
		}
	}

	@PostMapping(value = "/cart/deleteCart")
	public ResponseEntity<AjaxResponse> deleteCart(@RequestBody final Map<String, Object> data, final ModelMap model,
			final HttpServletRequest request, final HttpServletResponse response) {
		
		String laptopSeo = (String) data.get("laptopSeo");
		CartUtils.deleteCart(laptopSeo, request);
		CartUtils.tongTien(request);
		Integer soLuongMua = (Integer) request.getSession().getAttribute("soLuongMua");
		return ResponseEntity.ok(new AjaxResponse(500, soLuongMua));
	}

	@PostMapping(value = "/cart/editCart")
	public ResponseEntity<AjaxResponse> editCart(@RequestBody final Map<String, Object> data, final ModelMap model,
			final HttpServletRequest request, final HttpServletResponse response) {
		
		String laptopSeo = (String) data.get("laptopSeo");
		String amount = (String) data.get("amount");
		Integer soLuongNhap = laptopsService.getLaptopBySeo(laptopSeo).get(0).getSoLuongNhap();
		CartUtils.editCart(laptopSeo, amount, soLuongNhap, request);
		CartUtils.tongTien(request);
		Integer soLuongMua = (Integer) request.getSession().getAttribute("soLuongMua");
		return ResponseEntity.ok(new AjaxResponse(400, soLuongMua));
	}

	@PostMapping(value = "/contact/delete")
	public ResponseEntity<AjaxResponse> contactDelete(@RequestBody final Map<String, Object> data, final ModelMap model,
			final HttpServletRequest request, final HttpServletResponse response) {
		Integer entityId = (Integer) data.get("entityId");
		contactRepository.deleteById(entityId);
		return ResponseEntity.ok(new AjaxResponse(69, data));
	}

	@PostMapping(value = "/laptop/delete")
	public ResponseEntity<AjaxResponse> carsDelete(@RequestBody final Map<String, Object> data, final ModelMap model,
			final HttpServletRequest request, final HttpServletResponse response) {
		Integer entityId = (Integer) data.get("entityId");
		laptopRepository.deleteById(entityId);
		return ResponseEntity.ok(new AjaxResponse(69, data));
	}
	
	@PostMapping(value = "/user/delete")
	public ResponseEntity<AjaxResponse> userDelete(@RequestBody final Map<String, Object> data, final ModelMap model,
			final HttpServletRequest request, final HttpServletResponse response) {
		Integer entityId = (Integer) data.get("entityId");
		userInfoRepository.deleteById(entityId);
		return ResponseEntity.ok(new AjaxResponse(69, data));
	}
	
	@PostMapping(value = "/banner/delete")
	public ResponseEntity<AjaxResponse> bannerDelete(@RequestBody final Map<String, Object> data, final ModelMap model,
			final HttpServletRequest request, final HttpServletResponse response) {
		Integer entityId = (Integer) data.get("entityId");
		idBannerRepository.deleteById(entityId);
		return ResponseEntity.ok(new AjaxResponse(69, data));
	}

	@GetMapping(value = "/report/chart")
	public ResponseEntity<AjaxResponse> testData(final ModelMap model,
			final HttpServletRequest request, final HttpServletResponse response) {
		BigDecimal data[]= new BigDecimal[6];
		String label[] = new String[6];
		
		label[0]=ReportUtils.getStringMonth5();
		label[1]=ReportUtils.getStringMonth4();
		label[2]=ReportUtils.getStringMonth3();
		label[3]=ReportUtils.getStringMonth2();
		label[4]=ReportUtils.getStringMonth1();
		label[5]=ReportUtils.getStringMonth();
		
		List<Bill> bills = hoaDonRepository.findAll();
		
		data[0]=ReportUtils.getTotalMoneyMoth5(bills);
		data[1]=ReportUtils.getTotalMoneyMoth4(bills);
		data[2]=ReportUtils.getTotalMoneyMoth3(bills);
		data[3]=ReportUtils.getTotalMoneyMoth2(bills);
		data[4]=ReportUtils.getTotalMoneyMoth1(bills);
		data[5]=ReportUtils.getTotalMoneyMoth(bills);
		
		ChartReport x= new ChartReport(label, data);
		return ResponseEntity.ok(new AjaxResponse(333, x));
	}
	
	@GetMapping(value = "/data/autocomplete")
	public List<String> testDataAutoComplete(
			@RequestParam(value = "term", required = false, defaultValue = "") String term, final ModelMap model,
			final HttpServletRequest request, final HttpServletResponse response) {
		List<String> listName = new ArrayList<>();
		List<Laptop> laptops = laptopsService.searchNameAuto(term);
		for (int i = 0; i < laptops.size(); i++) {
			listName.add(laptops.get(i).getName());
		}
		return listName;
	}
}
