package org.launchcode.cheesemvc.models;

public class Cheese {
    private String nameOfCheese;
    private String descriptionOfCheese;
    private int cheeseId;
    private static int nextId=1;

    public Cheese(String nameOfCheese, String descriptionOfCheese) {
        this();
        this.nameOfCheese = nameOfCheese;
        this.descriptionOfCheese = descriptionOfCheese;
    }

    public Cheese() {
        cheeseId = nextId;
        nextId++;
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

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }
}
