package vn.ptit.controller.admin;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import vn.ptit.entities.BannerAttachment;
import vn.ptit.entities.Banner;
import vn.ptit.entities.Laptop;
import vn.ptit.repositories.BannerRepository;
import vn.ptit.repositories.LaptopManufacturerRepository;
import vn.ptit.repositories.LaptopRepository;
import vn.ptit.services.LaptopService;
import vn.ptit.services.UserService;

@Controller
public class AdminBannerController {
	@Autowired
	BannerRepository bannerRepository;
	@Autowired
	LaptopManufacturerRepository laptopManufacturerRepository;
	@Autowired
	LaptopRepository laptopRepository;
	@Autowired
	UserService userService;
	@Autowired
	LaptopService laptopsService;
	@Autowired Cloudinary cloudinary;

	@Value("${file.upload.path}")
	private String attachmentPath;

	@RequestMapping(value = { "/admin/add-banner" }, method = { RequestMethod.GET })
	public String add_carsproduct(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("banner", new Banner());

		return "admin/add_banner";
	}

	@RequestMapping(value = "/admin/add-banner", method = RequestMethod.POST)
	public String add_movie(@RequestParam("bannerImg") MultipartFile[] bannerImg,
			@ModelAttribute("banner") Banner banner, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws IllegalStateException, IOException {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		Map uploadResult = null;
		
		if (bannerImg != null && bannerImg.length > 0) {
			for (MultipartFile multipartFile : bannerImg) {
				if (multipartFile.getSize() <= 0)
					continue;
				BannerAttachment bannerAttachment = new BannerAttachment();
				bannerAttachment.setMime(multipartFile.getContentType());
				bannerAttachment.setPath(attachmentPath);
				uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(),ObjectUtils.asMap("resource_type", "auto",
						"folder","Banners"));
				bannerAttachment.setName((String) uploadResult.get("public_id") + '.' +
						multipartFile.getContentType().substring(multipartFile.getContentType().indexOf('/')+1));
				banner.addBannerAttachment(bannerAttachment);
			}
		}
		banner.setNgayTao(new Date().toString());
		banner.setStatus(true);
		bannerRepository.save(banner);
		model.addAttribute("status", "success");

		return "admin/add_banner";
	}

	@RequestMapping(value = { "/admin/list-banner" }, method = { RequestMethod.GET })
	public String list_movie(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		List<Banner> banners = bannerRepository.findAll();
		model.addAttribute("banners", banners);

		return "admin/list_banner";
	}
}
