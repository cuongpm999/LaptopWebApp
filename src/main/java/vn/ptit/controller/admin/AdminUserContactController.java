package vn.ptit.controller.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.ptit.entities.Contact;
import vn.ptit.entities.Laptop;
import vn.ptit.entities.Role;
import vn.ptit.entities.UserInfo;
import vn.ptit.repositories.ContactRepository;
import vn.ptit.repositories.LaptopManufacturerRepository;
import vn.ptit.repositories.LaptopRepository;
import vn.ptit.repositories.RoleRepository;
import vn.ptit.repositories.UserInfoRepository;
import vn.ptit.services.LaptopService;
import vn.ptit.services.UserService;

@Controller
public class AdminUserContactController {
	@Autowired
	LaptopManufacturerRepository laptopManufacturerRepository;
	@Autowired
	LaptopRepository laptopRepository;
	@Autowired
	UserService userService;
	@Autowired
	ContactRepository contactRepository;
	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	LaptopService laptopsService;

	@RequestMapping(value = { "/admin/edit-contact/{contactId}" }, method = { RequestMethod.GET })
	public String edit_laptop(@PathVariable int contactId, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("contact", contactRepository.getOne(contactId));
		return "admin/edit_contact";
	}

	@RequestMapping(value = { "/admin/list-contact" }, method = { RequestMethod.GET })
	public String listContact(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("contacts", contactRepository.findAll());

		return "admin/list_contact";
	}

	@RequestMapping(value = { "/admin/edit-contact" }, method = { RequestMethod.POST })
	public String saveEditLaptop(@ModelAttribute("contact") Contact contact, final ModelMap model,
			final HttpServletRequest request, final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		contact.setStatus(true);
		contactRepository.save(contact);
		model.addAttribute("status", "success");
		return "admin/edit_contact";
	}

	@RequestMapping(value = { "/admin/list-user" }, method = { RequestMethod.GET })
	public String listUser(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("user", userInfoRepository.findAll());

		return "admin/list_user";
	}

}
