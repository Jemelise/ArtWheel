package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
// this is a common prefix as the base route
@RequestMapping("cheese")
public class CheeseController {


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, String cheeseDescription) {
        Cheese newCheese = new Cheese(cheeseName, cheeseDescription);
        CheeseData.add(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        return "cheese/remove";
    }

//    @RequestMapping(value = "remove", method = RequestMethod.POST)
////    public String displayRemoveCheeseForm(@RequestParam ArrayList<String> cheeseList) {
////        ArrayList<Cheese> cheesesToRemove = new ArrayList<Cheese>();
////
////        for(String toRemove : cheeseList) {
////            for(Cheese nomnom : cheeses) {
////
////                if (nomnom.getNameOfCheese().equals(toRemove)) {
////                    cheesesToRemove.add(nomnom);
////                }
////            }
////        }
////        cheeses.removeAll(cheesesToRemove);
////        return "redirect:";
////    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String displayRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for(int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }
        return "redirect:";
    }
}
