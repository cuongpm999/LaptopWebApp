package vn.ptit.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.ptit.services.LaptopService;
import vn.ptit.services.ShipmentService;
import vn.ptit.services.UserService;

@Controller
public class AdminStatisticController {
	@Autowired
	LaptopService laptopService;
	@Autowired
	UserService userService;
	@Autowired ShipmentService shipmentService;

	@RequestMapping(value = { "/admin/statistic/laptop" }, method = { RequestMethod.GET })
	public String statisticLaptop(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		// must have
		model.addAttribute("laptopManufacturer", laptopService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));
		model.addAttribute("laptops", laptopService.stats());
		return "admin/statistic_laptop";
	}

	@RequestMapping(value = { "/admin/statistic/user" }, method = { RequestMethod.GET })
	public String statisticUser(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		// must have
		model.addAttribute("laptopManufacturer", laptopService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));
		model.addAttribute("userStats", userService.stats());
		return "admin/statistic_user";
	}

	@RequestMapping(value = { "/admin/statistic/shipment" }, method = { RequestMethod.GET })
	public String statisticShipment(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		// must have
		model.addAttribute("laptopManufacturer", laptopService.getAllLaptopManufacturer());
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));
		model.addAttribute("shipmentStats", shipmentService.stats());
		return "admin/statistic_shipment";
	}
}
