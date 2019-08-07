package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Mileage;
import org.launchcode.cheesemvc.models.data.MileageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("mileage")
public class MileageController {

    @Autowired
    private MileageDao mileageDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("miles", mileageDao.findAll());
        model.addAttribute("title", "My Mileage");
        return "mileage/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMileageForm(Model model) {
        model.addAttribute("title", "Add Mileage");
        model.addAttribute(new Mileage());
        return "mileage/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMileageForm(@ModelAttribute @Valid Mileage newMileage, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Mileage");
            return "mileage/add";
        }
        mileageDao.save(newMileage);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveMlieageForm(Model model) {
        model.addAttribute("miles", mileageDao.findAll());
        model.addAttribute("title", "Remove Mileage");
        return "mileage/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String displayRemoveMileageForm(@RequestParam int[] mileageIds) {

        for (int mileageId : mileageIds) {
            mileageDao.delete(mileageId);
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit/{mileageId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int mileageId) {
        Mileage c = mileageDao.findOne(mileageId);
        model.addAttribute("mileage", c);
        return "mileage/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(int mileageId, String eventOfMileage, String numberOfMileage) {
        Mileage c = mileageDao.findOne(mileageId);

        c.setEventOfMileage(eventOfMileage);
        c.setNumberOfMileage(numberOfMileage);

        mileageDao.save(c);
        return "redirect:";
    }

}
