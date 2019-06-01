package org.launchcode.cheesemvc.models;

public class Cheese {
    private String nameOfCheese;
    private String descriptionOfCheese;

    public Cheese(String nameOfCheese, String descriptionOfCheese) {
        this.nameOfCheese = nameOfCheese;
        this.descriptionOfCheese = descriptionOfCheese;
    }

    public String getNameOfCheese() {
        return nameOfCheese;
    }

    public void setNameOfCheese(String aNameOfCheese) {
       this.nameOfCheese = aNameOfCheese;
    }

    public String getDescriptionOfCheese() {
        return descriptionOfCheese;
    }

    public void setDescriptionOfCheese(String aDescriptionOfCheese) {
        this.descriptionOfCheese = aDescriptionOfCheese;
    }
}
