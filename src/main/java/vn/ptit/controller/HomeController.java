package vn.ptit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.ptit.beans.FilterMap;
import vn.ptit.entities.TotalVisit;
import vn.ptit.repositories.BannerRepository;
import vn.ptit.repositories.LaptopManufacturerRepository;
import vn.ptit.repositories.LaptopRepository;
import vn.ptit.repositories.TotalVisitRepository;
import vn.ptit.services.LaptopService;
import vn.ptit.services.UserService;

@Controller
public class HomeController {

	@Autowired
	LaptopService laptopsService;
	@Autowired
	LaptopRepository laptopRepository;
	@Autowired
	LaptopManufacturerRepository laptopManufacturerRepository;
	@Autowired
	UserService userService;
	@Autowired
	BannerRepository bannerRepository;
	@Autowired
	TotalVisitRepository totalVisitRepository;
	int cnt = 0;

	@RequestMapping(value = { "/", "/index", "/home" }, method = { RequestMethod.GET })
	public String index(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));
		List<TotalVisit> visits = totalVisitRepository.findAll();

		if (cnt == 0) {
			cnt = visits.get(0).getTotal();
		}

		cnt++;

		visits.get(0).setTotal(cnt);
		totalVisitRepository.saveAll(visits);

		return "index";
	}

	@RequestMapping(value = { "/shop-all-item/search-name/" }, method = { RequestMethod.GET })
	public String search1(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		String nameLaptop = request.getParameter("name");
		int pageNumber = 1;
		if (request.getParameter("page") != null) {
			pageNumber = Integer.parseInt(request.getParameter("page"));
		}

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

		model.addAttribute("laptop", laptopsService.searchName(nameLaptop, pageNumber, listFilter));
		model.addAttribute("banner", laptopsService.getBanner().get(laptopsService.getBanner().size() - 1));
		return "shop_all_item";
	}

	@RequestMapping(value = { "/shop-all-item/filter-buy-sale" }, method = { RequestMethod.GET })
	public String filterBuySale(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

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

		model.addAttribute("laptop", laptopsService.filterBuySale(pageNumber, listFilter));
		model.addAttribute("banner", laptopsService.getBanner().get(laptopsService.getBanner().size() - 1));

		return "shop_all_item";
	}

	@RequestMapping(value = { "/gioi-thieu" }, method = { RequestMethod.GET })
	public String gioiThieu(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		return "gioi_thieu";
	}

	@RequestMapping(value = { "/chinh-sach/chinh-sach-bao-hanh" }, method = { RequestMethod.GET })
	public String chinhSachBaoHanh(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		return "chinh_sach_bao_hanh";
	}

	@RequestMapping(value = { "/chinh-sach/chinh-sach-giao-hang" }, method = { RequestMethod.GET })
	public String chinhSachGiaoHang(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		return "chinh_sach_giao_hang";
	}

	@RequestMapping(value = { "/chinh-sach/chinh-sach-hoan-tien" }, method = { RequestMethod.GET })
	public String chinhSachHoanTien(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		return "chinh_sach_hoan_tien";
	}

	@RequestMapping(value = { "/chinh-sach/chinh-sach-hang-chinh-hang" }, method = { RequestMethod.GET })
	public String chinhSachHangChinhHang(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		return "chinh_sach_hang_chinh_hang";
	}

	// Login
	@RequestMapping(value = { "/login" }, method = { RequestMethod.GET })
	public String login(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		return "login";

	}

	@RequestMapping(value = { "/errors/403" }, method = { RequestMethod.GET })
	public String error403(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		return "error_403";
	}

}
