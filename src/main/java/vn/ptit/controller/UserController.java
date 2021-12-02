package vn.ptit.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
import vn.ptit.entities.Role;
import vn.ptit.entities.UserAttachment;
import vn.ptit.entities.UserInfo;
import vn.ptit.repositories.BillRepository;
import vn.ptit.repositories.LaptopManufacturerRepository;
import vn.ptit.repositories.LaptopRepository;
import vn.ptit.repositories.RoleRepository;
import vn.ptit.repositories.UserInfoRepository;
import vn.ptit.services.LaptopService;
import vn.ptit.services.UserService;
import vn.ptit.utils.GooglePojo;
import vn.ptit.utils.GoogleUtils;
import vn.ptit.utils.RestFB;

@Controller
public class UserController {

	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	LaptopRepository laptopRepository;
	@Autowired
	LaptopManufacturerRepository laptopManufacturerRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserService userService;
	@Autowired
	LaptopService laptopsService;
	@Autowired BillRepository billRepository;

	@Autowired
	private GoogleUtils googleUtils;

	@Autowired
	private RestFB restFb;

	@Autowired
	Cloudinary cloudinary;

	@Value("${file.upload.path}")
	private String attachmentPath;

	@RequestMapping(value = { "/register" }, method = { RequestMethod.GET })
	public String regis(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("userInfo", new UserInfo());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestParam("userImage") MultipartFile userImage,
			@ModelAttribute("userInfo") UserInfo userInfo, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws IllegalStateException, IOException {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		Boolean flag = false;
		Map uploadResult = null;
		for (int i = 0; i < userInfoRepository.findAll().size(); i++) {
			if (userInfo.getUsername().compareTo(userInfoRepository.findAll().get(i).getUsername()) == 0) {
				flag = true;
				break;
			}
		}
		if (userInfo.getFullname().length() <= 0) {
			model.addAttribute("status", "faileNameNotNull");
		} else if (!userInfo.getEmail().matches("^[a-zA-Z]+[a-zA-Z0-9]*@{1}[a-zA-Z]+mail.com$")) {
			model.addAttribute("status", "faileEmailNotFormat");
		} else if (!userInfo.getPhonenumber().matches("^0{1}[1-9]{1}\\d{8}$")) {
			model.addAttribute("status", "faileMobileNotFormat");
		} else if (userInfo.getAddress().isEmpty()) {
			model.addAttribute("status", "faileAddressNotNull");
		} else if (userInfo.getUsername().length() <= 0) {
			model.addAttribute("status", "faileTenUserNotNull");
		} else if (flag) {
			model.addAttribute("status", "faileTenBiTrung");
		} else if (userInfo.getPassword().length() <= 0) {
			model.addAttribute("status", "failePassNotNull");
		} else {
			if (userImage != null && userImage.getSize() > 0 && !userImage.isEmpty()) {
				UserAttachment userAttachment = new UserAttachment();
				userAttachment.setMime(userImage.getContentType());
				userAttachment.setPath(attachmentPath);
				uploadResult = cloudinary.uploader().upload(userImage.getBytes(),
						ObjectUtils.asMap("resource_type", "auto", "folder", "Users"));
				userAttachment.setName((String) uploadResult.get("public_id") + '.'
						+ userImage.getContentType().substring(userImage.getContentType().indexOf('/') + 1));
				userInfo.addUserAttachment(userAttachment);
			}

			List<Role> roles = new ArrayList<Role>();
			Role role = roleRepository.getOne(2);
			roles.add(role);
			userInfo.setRoles(roles);
			userInfo.setStatus(true);
			String pass = userInfo.getPassword();
			userInfo.setPassword(passwordEncoder.encode(pass));

			userInfoRepository.save(userInfo);

			model.addAttribute("status", "success");
		}

		return "register";
	}

	@RequestMapping(value = { "/user-details" }, method = { RequestMethod.GET })
	public String userDetail(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		return "user_details";
	}

	@RequestMapping(value = { "/edit-user" }, method = { RequestMethod.GET })
	public String editUser(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		return "edit_user";
	}

	@RequestMapping(value = "/edit-user", method = RequestMethod.POST)
	public String edit_user(@RequestParam("userImage") MultipartFile userImage,
			@ModelAttribute("userDis") UserInfo userInfo, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws IllegalStateException, IOException {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		if (userInfo.getFullname().isEmpty()) {
			model.addAttribute("status", "faileNameNotNull");
		} else if (!userInfo.getEmail().matches("^[a-zA-Z]+[a-zA-Z0-9]*@{1}[a-zA-Z]+mail.com$")) {
			model.addAttribute("status", "faileEmailNotFormat");
		} else if (!userInfo.getPhonenumber().matches("^0{1}[1-9]{1}\\d{8}$")) {
			model.addAttribute("status", "faileMobileNotFormat");
		} else if (userInfo.getAddress().isEmpty()) {
			model.addAttribute("status", "faileAddressNotNull");
		} else if (userInfo.getPassword().isEmpty()) {
			model.addAttribute("status", "failePassNotNull");
		} else {
			Map uploadResult = null;

			if (userImage != null && userImage.getSize() > 0 && !userImage.isEmpty()) {
				UserAttachment userImg = userService.loadUserByUsername(request.getRemoteUser()).getUserAttachment();
				if (userImg != null) {
					userInfo.removeUserAttachment(userImg);
				}

				UserAttachment userAttachment = new UserAttachment();
				userAttachment.setMime(userImage.getContentType());
				userAttachment.setPath(attachmentPath);
				uploadResult = cloudinary.uploader().upload(userImage.getBytes(),
						ObjectUtils.asMap("resource_type", "auto", "folder", "Users"));
				userAttachment.setName((String) uploadResult.get("public_id") + '.'
						+ userImage.getContentType().substring(userImage.getContentType().indexOf('/') + 1));
				userInfo.addUserAttachment(userAttachment);
			}

			if (userService.loadUserByUsername(request.getRemoteUser()).getRoles().get(0).getName()
					.equalsIgnoreCase("ROLE_USER")) {
				List<Role> roles = new ArrayList<Role>();
				Role role = roleRepository.getOne(2);
				roles.add(role);
				userInfo.setRoles(roles);
			} else {
				List<Role> roles = new ArrayList<Role>();
				Role role = roleRepository.getOne(1);
				roles.add(role);
				userInfo.setRoles(roles);
			}
			userInfo.setStatus(true);
			String pass = userInfo.getPassword();
			if (!pass.equalsIgnoreCase(userService.loadUserByUsername(request.getRemoteUser()).getPassword()))
				userInfo.setPassword(passwordEncoder.encode(pass));
			userInfo.setUsername(request.getRemoteUser());
			userInfoRepository.save(userInfo);
			model.addAttribute("status", "success");
		}

		return "edit_user";
	}

	@RequestMapping("/login-google")
	public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");

		if (code == null || code.isEmpty()) {
			return "redirect:/login?google=error";
		}
		String accessToken = googleUtils.getToken(code);

		GooglePojo googlePojo = googleUtils.getUserInfo(accessToken, request);

		Boolean flag = false;
		for (int i = 0; i < userInfoRepository.findAll().size(); i++) {
			if (googlePojo.getEmail().equalsIgnoreCase(userInfoRepository.findAll().get(i).getUsername())) {
				flag = true;
				break;
			}
		}

		if (!flag) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUsername(googlePojo.getEmail());
			userInfo.setEmail(googlePojo.getEmail());
			userInfo.setFullname(googlePojo.getName());
			userInfo.setStatus(true);
			userInfoRepository.save(userInfo);
		}

		UserDetails userDetail = googleUtils.buildUser(googlePojo);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("googleAcc", googlePojo);

		return "redirect:/";
	}

	@RequestMapping("/login-facebook")
	public String loginFacebook(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");

		if (code == null || code.isEmpty()) {
			return "redirect:/login?facebook=error";
		}
		String accessToken = restFb.getToken(code);

		com.restfb.types.User user = restFb.getUserInfo(accessToken, request);

		Boolean flag = false;
		for (int i = 0; i < userInfoRepository.findAll().size(); i++) {
			if (user.getEmail().compareToIgnoreCase(userInfoRepository.findAll().get(i).getUsername()) == 0) {
				flag = true;
				break;
			}
		}

		if (!flag) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUsername(user.getEmail());
			userInfo.setFullname(user.getName());
			userInfo.setEmail(user.getEmail());
			userInfo.setStatus(true);
			userInfoRepository.save(userInfo);
		}

		UserDetails userDetail = restFb.buildUser(user);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("faceAcc", user);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = { "/my-order" }, method = { RequestMethod.GET })
	public String myOrder(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		UserInfo userInfo = userService.loadUserByUsername(request.getRemoteUser());
		if(userInfo==null) return "redirect:/";
		model.addAttribute("userDis", userInfo);
		List<Bill> bills = billRepository.getBillByUser(userInfo.getId());
		model.addAttribute("bills", bills);
		
		return "myorder";
	}

}
