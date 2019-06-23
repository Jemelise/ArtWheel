package org.launchcode.cheesemvc.models.forms;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {
    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    private Menu menu;

    private Iterable<Cheese> cheese;

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheese) {
        this.menu = menu;
        this.cheese = cheese;
    }

    public AddMenuItemForm() {

    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Iterable<Cheese> getCheese() {
        return cheese;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }
}
