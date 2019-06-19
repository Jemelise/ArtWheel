package org.launchcode.cheesemvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String nameOfCheese;
    @NotNull
    @Size(min=1, message="Description must not be empty")
    private String descriptionOfCheese;
    private CheeseType typeOfCheese;

    public Cheese(String nameOfCheese, String descriptionOfCheese) {
        this.nameOfCheese = nameOfCheese;
        this.descriptionOfCheese = descriptionOfCheese;
    }

    public Cheese() {
    }

    public int getId() {
        return id;
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


    public CheeseType getTypeOfCheese() {
        return typeOfCheese;
    }

    public void setTypeOfCheese(CheeseType typeOfCheese) {
        this.typeOfCheese = typeOfCheese;
    }
}
