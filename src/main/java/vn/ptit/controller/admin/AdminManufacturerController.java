package vn.ptit.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.ptit.entities.LaptopManufacturer;
import vn.ptit.repositories.LaptopManufacturerRepository;
import vn.ptit.services.LaptopService;
import vn.ptit.services.UserService;
import vn.ptit.utils.Utilities;

@Controller
public class AdminManufacturerController {
	@Autowired
	LaptopService laptopsService;
	@Autowired
	UserService userService;
	@Autowired LaptopManufacturerRepository laptopManufacturerRepository;
	
	@RequestMapping(value = { "/admin/list-manufacturer" }, method = { RequestMethod.GET })
	public String listManufacturer(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("manufacturers", laptopManufacturerRepository.findByStatus(true));

		return "admin/list_manufacturer";
	}
	
	@RequestMapping(value = { "/admin/add-manufacturer" }, method = { RequestMethod.GET })
	public String addManufacturer(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("manufacturer", new LaptopManufacturer());

		return "admin/add_manufacturer";
	}
	
	@RequestMapping(value = { "/admin/add-manufacturer" }, method = { RequestMethod.POST })
	public String insertManufacturer(@ModelAttribute("manufacturer") LaptopManufacturer laptopManufacturer, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		Utilities utilities = new Utilities();
		laptopManufacturer.setSeo(utilities.createSeoLink(laptopManufacturer.getName()));
		laptopManufacturer.setStatus(true);
		laptopManufacturerRepository.save(laptopManufacturer);
		model.addAttribute("status", "success");

		return "admin/add_manufacturer";
	}
	
	@RequestMapping(value = { "/admin/edit-manufacturer/{id}" }, method = { RequestMethod.GET })
	public String editManufacturer(@PathVariable("id") int id, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("manufacturer", laptopManufacturerRepository.findById(id).get());

		return "admin/edit_manufacturer";
	}
	
	@RequestMapping(value = { "/admin/edit-manufacturer" }, method = { RequestMethod.POST })
	public String updateManufacturer(@ModelAttribute("manufacturer") LaptopManufacturer laptopManufacturer, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		laptopManufacturer.setStatus(true);
		laptopManufacturerRepository.save(laptopManufacturer);
		model.addAttribute("status", "success");

		return "admin/edit_manufacturer";
	}
}
