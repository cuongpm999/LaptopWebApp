package vn.ptit.controller.admin;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import vn.ptit.entities.Bill;
import vn.ptit.entities.Laptop;
import vn.ptit.entities.LaptopAttachment;
import vn.ptit.entities.LaptopStat;
import vn.ptit.entities.UserInfo;
import vn.ptit.repositories.BillRepository;
import vn.ptit.repositories.LaptopManufacturerRepository;
import vn.ptit.repositories.LaptopRepository;
import vn.ptit.repositories.TotalVisitRepository;
import vn.ptit.repositories.UserInfoRepository;
import vn.ptit.services.LaptopService;
import vn.ptit.services.UserService;
import vn.ptit.utils.ChartReport;
import vn.ptit.utils.ReportUtils;
import vn.ptit.utils.Utilities;

@Controller
public class AdminLaptopController {
	@Autowired
	LaptopManufacturerRepository laptopManufacturerRepository;
	@Autowired
	LaptopRepository laptopRepository;
	@Autowired
	UserService userService;
	@Autowired
	BillRepository hoaDonRepository;
	@Autowired
	LaptopService laptopService;
	@Autowired
	TotalVisitRepository totalVisitRepository;
	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	Cloudinary cloudinary;

	@Value("${file.upload.path}")
	private String attachmentPath;

	private String seoEdit;

	@RequestMapping(value = { "/admin/add-laptop" }, method = { RequestMethod.GET })
	public String add_laptop(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("laptop", new Laptop());
		return "admin/add_laptop";
	}

	@RequestMapping(value = "/admin/add-laptop", method = RequestMethod.POST)
	public String add_laptop(@RequestParam("laptopImage") MultipartFile[] laptopImage,
			@ModelAttribute("laptop") Laptop laptop, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws IllegalStateException, IOException {

		// must have
		model.addAttribute("laptopManufacturer", laptopService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		Map uploadResult = null;

		if (laptopImage != null && laptopImage.length > 0) {
			for (MultipartFile multipartFile : laptopImage) {
				if (multipartFile.getSize() <= 0)
					continue;
				LaptopAttachment laptopAttachment = new LaptopAttachment();
				laptopAttachment.setMime(multipartFile.getContentType());
				laptopAttachment.setPath(attachmentPath);
				uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(),
						ObjectUtils.asMap("resource_type", "auto", "folder", "Laptops"));
				laptopAttachment.setName((String) uploadResult.get("public_id") + '.'
						+ multipartFile.getContentType().substring(multipartFile.getContentType().indexOf('/') + 1));
				laptop.addLaptopAttachment(laptopAttachment);
			}
		}

		Utilities utilities = new Utilities();
		if (laptop.getName().indexOf("(") >= 0)
			laptop.setSeo(utilities.createSeoLink(laptop.getName().substring(0, laptop.getName().indexOf("("))));
		else
			laptop.setSeo(utilities.createSeoLink(laptop.getName()));
		laptop.setStatus(true);
		laptopRepository.save(laptop);
		model.addAttribute("status", "success");

		return "admin/add_laptop";
	}

	@RequestMapping(value = { "/admin/manage" }, method = { RequestMethod.GET })
	public String quanly(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));
		model.addAttribute("totalVisit", totalVisitRepository.count());

		List<Bill> hoaDons = hoaDonRepository.findAll();
		BigDecimal t = new BigDecimal(0);
		for (Bill hoaDon : hoaDons) {
			t = t.add(hoaDon.getPayment().getAll_money());
		}

		model.addAttribute("totalMoney", t);

		model.addAttribute("totalUser", userInfoRepository.findAll().size());

		model.addAttribute("totalOrder", hoaDons.size());

		List<LaptopStat> laptopStats  = laptopService.stats();

		model.addAttribute("listProductRP", laptopStats);
		
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
		
		ChartReport chartReport= new ChartReport(label, data);
		model.addAttribute("chartReport",chartReport);

		return "admin/manage";
	}

	@RequestMapping(value = { "/admin/list-laptop" }, method = { RequestMethod.GET })
	public String listLaptop(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("laptop", laptopRepository.findByStatus(true));

		return "admin/list_laptop";
	}

	@RequestMapping(value = { "/admin/edit-laptop/{laptopId}" }, method = { RequestMethod.GET })
	public String edit_laptop(@PathVariable int laptopId, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("laptop", laptopRepository.getOne(laptopId));
		seoEdit = laptopRepository.getOne(laptopId).getSeo();

		return "admin/edit_laptop";
	}

	@RequestMapping(value = { "/admin/edit-laptop" }, method = { RequestMethod.POST })
	public String saveEditLaptop(@RequestParam("laptopImage") MultipartFile[] laptopImage,
			@ModelAttribute("laptop") Laptop laptop, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws IllegalStateException, IOException {

		// must have
		model.addAttribute("laptopManufacturer", laptopService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		laptop.setSeo(seoEdit);
		Map uploadResult = null;

		if (laptopImage != null && laptopImage.length > 0 && !laptopImage[0].isEmpty()) {

			List<LaptopAttachment> productImgs = laptopService.getLaptopBySeo(seoEdit).get(0).getLaptopAttachments();
			for (LaptopAttachment laptopAttachment : productImgs) {
				laptop.removeLaptopAttachment(laptopAttachment);
			}

			for (MultipartFile multipartFile : laptopImage) {
				if (multipartFile.getSize() <= 0)
					continue;
				LaptopAttachment laptopAttachment = new LaptopAttachment();
				laptopAttachment.setMime(multipartFile.getContentType());
				laptopAttachment.setPath(attachmentPath);
				uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(),
						ObjectUtils.asMap("resource_type", "auto", "folder", "Laptops"));
				laptopAttachment.setName((String) uploadResult.get("public_id") + '.'
						+ multipartFile.getContentType().substring(multipartFile.getContentType().indexOf('/') + 1));
				laptop.addLaptopAttachment(laptopAttachment);
			}
		}
		laptop.setStatus(true);

		laptopRepository.save(laptop);
		model.addAttribute("status", "success");
		return "admin/edit_laptop";
	}

	// hoa don

	@RequestMapping(value = { "/admin/bill/{hoadonId}" }, method = { RequestMethod.GET })
	public String hoadon(@PathVariable int hoadonId, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("hoa_don", hoaDonRepository.getOne(hoadonId));
		SimpleDateFormat f = new SimpleDateFormat("'Ngày tạo:' hh:mm:ss E dd/MM/yyyy");
		model.addAttribute("date", f.format(hoaDonRepository.getOne(hoadonId).getDate_create()));
		return "admin/bill";
	}

	@RequestMapping(value = { "/admin/list-bill" }, method = { RequestMethod.GET })
	public String list_hoadon(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("hoadons", hoaDonRepository.findAll());
		return "admin/list_bill";
	}

}
