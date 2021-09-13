package vn.ptit.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.ptit.entities.Contact;
import vn.ptit.entities.Laptop;
import vn.ptit.repositories.ContactRepository;
import vn.ptit.repositories.LaptopManufacturerRepository;
import vn.ptit.repositories.LaptopRepository;
import vn.ptit.services.LaptopService;
import vn.ptit.services.UserService;

@Controller
public class ContactController {
	@Autowired
	ContactRepository contactRepository;
	@Autowired
	LaptopManufacturerRepository laptopManufacturerRepository;
	@Autowired
	LaptopRepository laptopRepository;
	@Autowired
	UserService userService;
	@Autowired
	LaptopService laptopsService;

	@RequestMapping(value = { "/contact" }, method = { RequestMethod.GET })
	public String details(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("contact", new Contact());
		return "contact";
	}

	@RequestMapping(value = { "/contact-bind-data" }, method = { RequestMethod.POST })
	public String saveContact(@ModelAttribute("contact") Contact contact, final ModelMap model,
			final HttpServletRequest request, final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));
		
		if (contact.getFullName() == "") {
			model.addAttribute("status", "faileNameNotNull");
		} else if (!contact.getEmail().matches("^[a-zA-Z]+[a-zA-Z0-9]*@{1}[a-zA-Z]+mail.com$")) {
			model.addAttribute("status", "faileEmailNotFormat");
		} else if (!contact.getMobile().matches("^0{1}[1-9]{1}\\d{8}$")) {
			model.addAttribute("status", "faileMobileNotFormat");
		} else {
			contact.setStatus(true);
			contactRepository.save(contact);
			model.addAttribute("status", "success");
		}
		return "contact";

	}

}
