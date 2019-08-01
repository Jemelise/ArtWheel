package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Artwork;
import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.data.ArtworkDao;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
        model.addAttribute("title", "My Artwork");
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

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveArtworkForm(Model model) {
        model.addAttribute("artworks", artworkDao.findAll());
        model.addAttribute("title", "Remove Artwork");
        return "artwork/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String displayRemoveArtworkForm(@RequestParam int[] artworkIds) {

        for (int artworkId : artworkIds) {
            artworkDao.delete(artworkId);
        }
            return "redirect:";
    }

    @RequestMapping(value = "edit/{artworkId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int artworkId) {
        Artwork c = artworkDao.findOne(artworkId);
        model.addAttribute("artwork", c);
        return "artwork/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(int artworkId, String nameOfArtwork, String sizeOfArtwork, String priceOfArtwork) {
        Artwork c = artworkDao.findOne(artworkId);

        c.setNameOfArtwork(nameOfArtwork);
        c.setSizeOfArtwork(sizeOfArtwork);
        c.setPriceOfArtwork(priceOfArtwork);

        artworkDao.save(c);
        return "redirect:";
    }

    @RequestMapping(value = "category/{id}", method = RequestMethod.GET)
    public String category(Model model, @PathVariable int id) {

        Iterable<Artwork> listofartworks = artworkDao.findAll();
        List<Artwork> catartwork = new ArrayList<Artwork>();
        Category category = categoryDao.findOne(id);

        for (Artwork artwork : listofartworks) {
            int x = artwork.getCategory().getId();

            if (id == x) {
                catartwork.add(artwork);
            }
        }
        model.addAttribute("artworks", catartwork);
        model.addAttribute("title", "Artworks That Are " + category.getName());
        return "artwork/index";
    }
}