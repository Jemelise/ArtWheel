package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Sales;
import org.launchcode.cheesemvc.models.data.SalesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("sales")
public class SalesController {

    @Autowired
    private SalesDao salesDao;

    public Double sumOfPriceColumn (Iterable<Sales> listofsales) {

        double totalsales = 0;

        for ( Sales sale : listofsales) {
            totalsales += sale.getPriceOfSale();
        }
        return totalsales;
    }

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("allsales", salesDao.findAll());
        model.addAttribute("title", "My Sales");

        Iterable<Sales> listofsales = salesDao.findAll();
        Double totalsales = sumOfPriceColumn(listofsales);

        model.addAttribute("total", totalsales);

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

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveSalesForm(Model model) {
        model.addAttribute("allsales", salesDao.findAll());
        model.addAttribute("title", "Remove Sale(s)");
        return "sales/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String displayRemoveSalesForm(@RequestParam int[] salesIds) {

        for (int salesId : salesIds) {
            salesDao.delete(salesId);
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit/{salesId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int salesId) {
        Sales c = salesDao.findOne(salesId);
        model.addAttribute("sales", c);
        return "sales/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(int salesId, String itemOfSale, String dateOfSale, Double priceOfSale) {
        Sales c = salesDao.findOne(salesId);

        c.setItemOfSale(itemOfSale);
        c.setDateOfSale(dateOfSale);
        c.setPriceOfSale(priceOfSale);

        salesDao.save(c);
        return "redirect:";
    }
}