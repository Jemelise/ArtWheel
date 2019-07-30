package org.launchcode.cheesemvc.controllers;


import org.launchcode.cheesemvc.models.Artwork;
import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.data.ArtworkDao;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("artwork")
public class ArtworkController {

    @Autowired
    private ArtworkDao artworkDao;

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("artworks", artworkDao.findAll());
        model.addAttribute("title", "My Artworks");
        return "artwork/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddArtworkForm(Model model) {
        model.addAttribute("title", "Add Artwork");
        model.addAttribute(new Artwork());
        model.addAttribute("categories", categoryDao.findAll());
        return "artwork/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddArtworkForm(@ModelAttribute @Valid Artwork newArtwork, Errors errors, @RequestParam int category, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Artwork");
            model.addAttribute("categories", categoryDao.findAll());
            return "artwork/add";
        }
        Category cat = categoryDao.findOne(category);
        newArtwork.setCategory(cat);
        artworkDao.save(newArtwork);
        return "redirect:";
    }
}
