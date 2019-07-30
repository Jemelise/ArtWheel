package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;


    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors, @RequestParam int category, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }
        Category cat = categoryDao.findOne(category);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String displayRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {

            cheeseDao.delete(cheeseId);

            return "redirect:/cheese/remove";
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese c = cheeseDao.findOne(cheeseId);
        model.addAttribute("cheese", c);
        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(int cheeseId, String nameOfCheese, String descriptionOfCheese) {
        Cheese c = cheeseDao.findOne(cheeseId);

        c.setNameOfCheese(nameOfCheese);
        c.setDescriptionOfCheese(descriptionOfCheese);

        cheeseDao.save(c);
        return "redirect:";
    }

    @RequestMapping(value = "category/{id}", method = RequestMethod.GET)
    public String category(Model model, @PathVariable int id) {

        Iterable<Cheese> listofcheeses = cheeseDao.findAll();
        List<Cheese> catcheese = new ArrayList<Cheese>();
        Category category = categoryDao.findOne(id);

        for (Cheese cheese : listofcheeses) {
            int x = cheese.getCategory().getId();

            if (id == x) {
                catcheese.add(cheese);
            }
        }
        model.addAttribute("cheeses", catcheese);
        model.addAttribute("title", "Cheeses That Are " + category.getName());
        return "cheese/index";
    }
}
