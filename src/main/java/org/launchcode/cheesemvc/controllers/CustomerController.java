package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Customer;
import org.launchcode.cheesemvc.models.data.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("customers", customerDao.findAll());
        model.addAttribute("title", "My Customers");
        return "customer/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCustomerForm(Model model) {
        model.addAttribute("title", "Add Customer");
        model.addAttribute(new Customer());
        return "customer/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCustomerForm(@ModelAttribute @Valid Customer newCustomer, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Customer");
            return "customer/add";
        }
        customerDao.save(newCustomer);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCustomerForm(Model model) {
        model.addAttribute("customers", customerDao.findAll());
        model.addAttribute("title", "Remove Customer");
        return "customer/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String displayRemoveCustomerForm(@RequestParam int[] customerIds) {

        for (int customerId : customerIds) {
            customerDao.delete(customerId);
        }
        return "redirect:";
    }

}
