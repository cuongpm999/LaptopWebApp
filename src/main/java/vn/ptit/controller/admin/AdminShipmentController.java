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
import vn.ptit.entities.Shipment;
import vn.ptit.repositories.ShipmentRepository;
import vn.ptit.services.LaptopService;
import vn.ptit.services.UserService;
import vn.ptit.utils.Utilities;

@Controller
public class AdminShipmentController {
	@Autowired
	LaptopService laptopsService;
	@Autowired
	UserService userService;
	@Autowired ShipmentRepository shipmentRepository;
	
	@RequestMapping(value = { "/admin/list-shipment" }, method = { RequestMethod.GET })
	public String listShipment(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("shipments", shipmentRepository.findByStatus(true));

		return "admin/list_shipment";
	}
	
	@RequestMapping(value = { "/admin/add-shipment" }, method = { RequestMethod.GET })
	public String addShipment(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("shipment", new Shipment());

		return "admin/add_shipment";
	}
	
	@RequestMapping(value = { "/admin/add-shipment" }, method = { RequestMethod.POST })
	public String insertShipment(@ModelAttribute("shipment") Shipment shipment, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		shipment.setStatus(true);
		shipmentRepository.save(shipment);
		model.addAttribute("status", "success");

		return "admin/add_shipment";
	}
	
	@RequestMapping(value = { "/admin/edit-shipment/{id}" }, method = { RequestMethod.GET })
	public String editShipment(@PathVariable("id") int id, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		model.addAttribute("shipment", shipmentRepository.findById(id).get());

		return "admin/edit_shipment";
	}
	
	@RequestMapping(value = { "/admin/edit-shipment" }, method = { RequestMethod.POST })
	public String updateShipment(@ModelAttribute("shipment") Shipment shipment, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {

		// must have
		model.addAttribute("laptopManufacturer", laptopsService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));

		shipment.setStatus(true);
		shipmentRepository.save(shipment);
		model.addAttribute("status", "success");

		return "admin/edit_shipment";
	}
}
