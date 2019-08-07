package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Event;
import org.launchcode.cheesemvc.models.data.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("event")
public class EventController {

    @Autowired
    private EventDao eventDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("events", eventDao.findAll());
        model.addAttribute("title", "Events");
        return "event/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Event");
        model.addAttribute("event", new Event());
        return "event/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Event event, Errors errors) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Event");
            model.addAttribute("event", event);
            return "event/add";
        }
        eventDao.save(event);
        return "redirect:";
    }
}


