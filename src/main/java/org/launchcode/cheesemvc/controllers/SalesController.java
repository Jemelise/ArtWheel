package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Sales;
import org.launchcode.cheesemvc.models.data.SalesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("sales")
public class SalesController {

    @Autowired
    private SalesDao salesDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("allsales", salesDao.findAll());
        model.addAttribute("title", "My Sales");
        return "sales/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddSalesForm(Model model) {
        model.addAttribute("title", "Add Sales");
        model.addAttribute(new Sales());
        return "sales/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddSalesForm(@ModelAttribute @Valid Sales newSales, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Sales");
            return "sales/add";
        }

        salesDao.save(newSales);
        return "redirect:";
    }
}