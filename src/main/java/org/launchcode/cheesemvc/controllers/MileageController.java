package org.launchcode.cheesemvc.controllers;


import org.launchcode.cheesemvc.models.Event;
import org.launchcode.cheesemvc.models.Mileage;
import org.launchcode.cheesemvc.models.data.EventDao;
import org.launchcode.cheesemvc.models.data.MileageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("mileage")
public class MileageController {

    @Autowired
    private MileageDao mileageDao;

    @Autowired
    private EventDao eventDao;

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
        model.addAttribute("events", eventDao.findAll());
        return "mileage/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMileageForm(@ModelAttribute @Valid Mileage newMileage, Errors errors, @RequestParam int event, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Mileage");
            model.addAttribute("events", eventDao.findAll());
            return "mileage/add";
        }
        Event cat = eventDao.findOne(event);
        newMileage.setEvent(cat);
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
    public String processEditForm(int mileageId, String numberOfMileage) {
        Mileage c = mileageDao.findOne(mileageId);

        c.setNumberOfMileage(numberOfMileage);

        mileageDao.save(c);
        return "redirect:";
    }

    @RequestMapping(value = "event/{id}", method = RequestMethod.GET)
    public String event(Model model, @PathVariable int id) {

        Iterable<Mileage> listofmiles = mileageDao.findAll();
        List<Mileage> catmileage = new ArrayList<Mileage>();
        Event event = eventDao.findOne(id);

        for (Mileage mileage : listofmiles) {
            int x = mileage.getEvent().getId();

            if (id == x) {
                catmileage.add(mileage);
            }
        }
        model.addAttribute("miles", catmileage);
        model.addAttribute("title", "Mileage That Are " + event.getName());
        return "artwork/index";
    }

}
